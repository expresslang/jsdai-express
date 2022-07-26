package com.sfm.spice;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import com.sfm.ap210.jsdai.functional.FunctionalProductVersion;
import com.sfm.ap210.jsdai.functional.Functional_unit;
import com.sfm.ap210.jsdai.functional.Functional_unit_network_definition;
import com.sfm.ap210.jsdai.functional.Functional_unit_network_node_definition;
import com.sfm.ap210.jsdai.functional.Functional_unit_terminal;
import com.sfm.ap210.jsdai.param.DoubleParam;
import com.sfm.ap210.jsdai.param.MeasureParam;
import com.sfm.ap210.jsdai.param.ModelParameter;
import com.sfm.ap210.jsdai.param.Param;
import com.sfm.ap210.jsdai.param.ParameterAssignment;
import com.sfm.spice.SpiceElement.LTSpiceElements;


public class FunctionalToSpiceMapper {
	Functional_unit_network_definition fund;
	Set<Functional_unit> functional_units = new TreeSet<Functional_unit>();
	Map<Functional_unit_network_definition, SpiceSubckt> subcktMap = new TreeMap<Functional_unit_network_definition, SpiceSubckt>();
	
	public FunctionalToSpiceMapper(Functional_unit_network_definition nd)
	{
		fund = nd;
	}
		
	public SpiceCircuit mappedCircuit()
	{
		SpiceCircuit c = new SpiceCircuit(fund.getFunctionalProductVersion().product_id);
		
		// copy all nodes
		for (Functional_unit_network_node_definition node : fund.getNodes())
		{
			c.addNode(node.getName());
			addAllConnectedFunctionalUnits(node);
		}
		
		addSpiceElementsAndModelsForFunctionalUnits(c);
		return c;
	}
	
	public SpiceSubckt mappedSubCircuit()
	{
		SpiceSubckt c = new SpiceSubckt(fund.getFunctionalProductVersion().product_id, fund.getFUUV());
		// copy all nodes
		for (Functional_unit_network_node_definition node : fund.getNodes())
		{
			c.addNode(node.getName());
			addAllConnectedFunctionalUnits(node);
		}
		
		addSpiceElementsAndModelsForFunctionalUnits(c);
		return c;
	}
	
	void addAllConnectedFunctionalUnits(Functional_unit_network_node_definition node)
	{
		Set<Functional_unit_terminal> futs = node.terminals;
		for (Functional_unit_terminal fut : futs)
		{
			if (!(functional_units.contains(fut.fu)))
				functional_units.add(fut.fu);
		}
	}
	
	void addSpiceElementsAndModelsForFunctionalUnits(SpiceCircuit c)
	{
		for (Functional_unit fu : functional_units)
		{
			SpiceElement e = elementForFunctionalUnit(c, fu);
			if (e != null)
				c.addElement(e);
			else
				log("Ignoring: "+fu.refdes);
		}
	}
	
	// public SpiceElement(SpiceCircuit c, String n, LTSpiceElements e, List<String> nodeNames)
	SpiceElement elementForFunctionalUnit(SpiceCircuit c, Functional_unit fu)
	{
		String name = fu.refdes;
		String modelName = fu.fuuv.fpv.product_id;
		String baseModelName = fu.fuuv.fpv.product_name;
		// the baseModel is the LT spice model element - i.e. NJF, D, etc.
		// is either a base element or a model
		LTSpiceElements e = LTSpiceElements.fromText(modelName);
		List<String> spiceNodes = new Vector<String>();
		
		for (Functional_unit_terminal fut : fu.terminals)
		{
			Functional_unit_network_node_definition node = getConnectedNode(fut);
			spiceNodes.add(node.name);
		}
		
		SpiceParam value = valueParamOfFunctionalUnit(fu);
		
		SpiceElement element = null;
		
		if (fu.fuuv.fpv.getFUND() != null) // a subckt
		{
			Functional_unit_network_definition fund = fu.fuuv.fpv.getFUND();
			SpiceSubckt sub;
			if (subcktMap.containsKey(fund))
				sub = subcktMap.get(fund);
			else
			{
				FunctionalToSpiceMapper subcktMapper = new FunctionalToSpiceMapper(fund);
				sub = subcktMapper.mappedSubCircuit();
				c.subcktMap.put(sub.getName(), sub);
			}
			element = new SpiceElement(c, name, LTSpiceElements.X, value, spiceNodes);
			element.subckt = sub;
		}
		else if (e != null) // a base element
		{
			element = new SpiceElement(c, name, e, value, spiceNodes);
		}
		else // a model
		{
			SpiceModel m = new SpiceModel(modelName, baseModelName);
			c.modelMap.put(m.name, m);
			addModelParamsFromFunctionalProduct(m, fu.fuuv.fpv);
			LTSpiceElements lte = SpiceModel.getElementForBaseModelName(baseModelName);
			element = new SpiceElement(c, name, lte, value, spiceNodes, m);
		}
		
		addElementParamsFromFunctionalUnit(element, fu);
		return element;
	}

	void addElementParamsFromFunctionalUnit(SpiceElement e, Functional_unit fu)
	{
		SpiceModelParamSet spiceParams = paramSet(e.element.toString(), fu.params, fu.description);
		e.setParamSet(spiceParams);
	}
	
	void addModelParamsFromFunctionalProduct(SpiceModel m, FunctionalProductVersion fpv)
	{
		SpiceModelParamSet spiceParams = paramSet(m.baseModelName, fpv.params, null);
		m.setParamSet(spiceParams);
	}
	
	/**
	 * 
	 * @param modelKey
	 * @param pa_set
	 * @param excludeModelParamID - support exclusion of the 'value' parameter of the SpiceElement in the param set
	 * @return
	 */
	SpiceModelParamSet paramSet(String modelKey, Set<ParameterAssignment> pa_set, String excludeModelParamID)
	{
		SpiceModelParamSet spiceParams = new SpiceModelParamSet(modelKey);
		
		for (ParameterAssignment pa : pa_set)
		{
			ModelParameter mp = pa.getModelParameter();
			if ((excludeModelParamID != null) && (mp.getId().equalsIgnoreCase(excludeModelParamID)))
				continue;
			
			Param p = pa.getParam();
			if (p instanceof MeasureParam)
			{
				MeasureParam measure = (MeasureParam) p;
				SpiceParam sp = new SpiceParam(mp.getId(), mp.getDescription(), measure.getDoubleValue(), measure.getUnit());
				spiceParams.addParam(sp);
				
			}
			else if (p instanceof DoubleParam)
			{
				DoubleParam dp = (DoubleParam) p;
				SpiceParam sp = new SpiceParam(mp.getId(), mp.getDescription(), dp.getDoubleValue(), null);
				spiceParams.addParam(sp);
			}
			else
			{
				//throw new IllegalArgumentException("MeasureParam expected: "+pa);
				System.out.println("Unsupported parameter: "+pa.toString());
			}
		}
		return spiceParams;
	}
	
	SpiceParam valueParamOfFunctionalUnit(Functional_unit fu)
	{
		for (ParameterAssignment pa : fu.params)
		{
			ModelParameter mp = pa.getModelParameter();
			if (mp.getId().equalsIgnoreCase(fu.description))
			{
				Param p = pa.getParam();
				if (p instanceof MeasureParam)
				{
					MeasureParam measure = (MeasureParam) p;
					return new SpiceParam(mp.getId(), mp.getDescription(), measure.getDoubleValue(), measure.getUnit());
				}
				else if (p instanceof DoubleParam)
				{
					DoubleParam dp = (DoubleParam) p;
					return new SpiceParam(mp.getId(), mp.getDescription(), dp.getDoubleValue(), null);
				}
				else
					throw new IllegalArgumentException("MeasureParam expected: "+pa);
				
			}
		}
		return null;
		// throw new IllegalArgumentException("Unable to find value parameter for fu: "+fu);
	}
	
	Functional_unit_network_node_definition getConnectedNode(Functional_unit_terminal fut)
	{
		for (Functional_unit_network_node_definition node : fund.getNodes())
		{
			if (node.terminals.contains(fut))
				return node;
		}
		return null;
	}
	
	private static void log(Object aObject)
	{
	    System.out.println(String.valueOf(aObject));
	}
}
