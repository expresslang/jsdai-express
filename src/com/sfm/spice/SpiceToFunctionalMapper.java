package com.sfm.spice;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;

import com.sfm.ap210.jsdai.functional.FunctionalProductVersion;
import com.sfm.ap210.jsdai.functional.Functional_unit;
import com.sfm.ap210.jsdai.functional.Functional_unit_network_definition;
import com.sfm.ap210.jsdai.functional.Functional_unit_network_node_definition;
import com.sfm.ap210.jsdai.functional.Functional_unit_terminal;
import com.sfm.ap210.jsdai.functional.Functional_unit_usage_view;
import com.sfm.ap210.jsdai.param.CategoryModelParameterWithUnit;
import com.sfm.ap210.jsdai.param.ClassWithAttributes;
import com.sfm.ap210.jsdai.param.DigitalDocument;
import com.sfm.ap210.jsdai.param.DoubleParamImpl;
import com.sfm.ap210.jsdai.param.MeasureParam;
import com.sfm.ap210.jsdai.param.MeasureParamImpl;
import com.sfm.ap210.jsdai.param.Param;
import com.sfm.ap210.jsdai.param.ParameterAssignment;
import com.sfm.spice.SpiceElement.LTSpiceElements;

public class SpiceToFunctionalMapper {
	SpiceCircuit circuit;
	Map<String, Functional_unit_network_node_definition> nodeMap = new TreeMap<String, Functional_unit_network_node_definition>();
	Map<SpiceModel, Functional_unit_usage_view> modelMap = new TreeMap<SpiceModel, Functional_unit_usage_view>();
	Map<SpiceSubckt, Functional_unit_usage_view> subcktMap = new TreeMap<SpiceSubckt, Functional_unit_usage_view>();
	Map<LTSpiceElements, Functional_unit_usage_view> elementMap = new TreeMap<LTSpiceElements, Functional_unit_usage_view>();
	Map<LTSpiceElements, ClassWithAttributes> classMap = new TreeMap<LTSpiceElements, ClassWithAttributes>();
	public DigitalDocument referenceDocument;
	
	public SpiceToFunctionalMapper(SpiceCircuit c)
	{
		circuit = c;
		referenceDocument = new DigitalDocument("LTSpice Users Guide", "IV", "URL", "http://ltspice.linear.com/software/scad3.pdf", "LTspice IV Users Guide");
	}
	
	public Functional_unit_network_definition mappedFUND()
	{
		FunctionalProductVersion fpv = new FunctionalProductVersion(circuit.getName(), "circuit", "1");
		Functional_unit_network_definition fund = new Functional_unit_network_definition(fpv);
		
		for (String spice_node : circuit.nodes)
		{
			Functional_unit_network_node_definition node = new Functional_unit_network_node_definition(spice_node, fund);
			fund.addNode(node);
			nodeMap.put(spice_node, node);
		}
		
		for (SpiceElement element : circuit.elements)
		{
			Functional_unit fu = createFunctionalUnitForElement(element);
			List<String> element_nodes = element.nodes;
			SortedSet<Functional_unit_terminal> fu_terminals = fu.terminals;
			if (element_nodes.size() != fu_terminals.size())
				throw new IllegalArgumentException("Incompatible terminal count: "+element);
			Iterator<Functional_unit_terminal> fut_it = fu_terminals.iterator();
			//while (fut_it.hasNext())
			for (int i=0 ; i<element_nodes.size(); i++)
			{
				String spice_node = element_nodes.get(i);
				Functional_unit_network_node_definition fu_node = nodeMap.get(spice_node);
				if (fu_node == null)
					throw new IllegalArgumentException("Unable to map terminal: "+element);
				Functional_unit_terminal fut = fut_it.next();
				fu_node.addTerminal(fut);
			}
		}
		return fund;
	}
	
	/**
	 * need a Functional_product and corresponding Functional_unit_usage_view from which in create an 
	 * instance (Functional_unit) corresponding to the SpiceElement.
	 * If the SpiceElement has a model, that model is a Functional_product.
	 * If the SpiceElement does not have a model, there will be a Functional_product for the generic element type 
	 * (i.e L, C, D)
	 * 
	 * @param e
	 * @return
	 */
	Functional_unit createFunctionalUnitForElement(SpiceElement e)
	{
		Functional_unit_usage_view fuuv = getFUUVforElement(e);
		Functional_unit fu = fuuv.createInstanceOfUsageView(e.name);
		
		if (e.value != null)
		{
			Param p;
			CategoryModelParameterWithUnit cmp = getModelParamForSpiceElementValue(e);
			if (e.value.unit != null)
				p = new MeasureParamImpl(e.value.name, e.value.value, e.value.unit);
			else
				p = new DoubleParamImpl(e.value.name, e.value.value);
			ParameterAssignment pa = new ParameterAssignment(p, cmp);
			fu.addParam(pa);
			if (e.paramSet != null)
				attachElementParametersToFunctionalUnit(fu, e);
		}
		return fu;
	}
	
	ClassWithAttributes getClassForElement(LTSpiceElements e)
	{
		if (!(classMap.containsKey(e)))
		{
			ClassWithAttributes c = new ClassWithAttributes(e.toString(),e.toString(), "LT Spice Element", referenceDocument);
			classMap.put(e, c);
		}
		return classMap.get(e);
	}
	
	Functional_unit_usage_view getFUUVforElement(SpiceElement e)
	{
		if (e.subckt != null)
		{
			SpiceSubckt sub = e.subckt;
			if (!(subcktMap.containsKey(sub)))
			{
				Functional_unit_usage_view fuuv = createSubCkt(sub);
				subcktMap.put(sub, fuuv);
			}
			return subcktMap.get(sub);
		}
		else if (e.model != null)
		{
			SpiceModel m = e.model;
			if (!(modelMap.containsKey(m)))
			{
				Functional_unit_usage_view fuuv = createFunctionalProductAndUsageViewForModel(m);
				modelMap.put(m, fuuv);
			}
			return modelMap.get(m);
		}
		else // e.model == null
		{
			LTSpiceElements lte = e.element;
			if (!(elementMap.containsKey(lte)))
			{
				Functional_unit_usage_view fuuv = createFunctionalProductAndUsageViewForElement(lte);
				elementMap.put(lte, fuuv);
			}
			return elementMap.get(lte);
		}
	}
	
	Functional_unit_usage_view createSubCkt(SpiceSubckt subckt)
	{
		SpiceToFunctionalMapper subcktMapper = new SpiceToFunctionalMapper(subckt);
		Functional_unit_network_definition subcktFUND = subcktMapper.mappedFUND();
		subcktFUND.createUsageView(subckt.interfaceNodes);
		Functional_unit_usage_view subcktFUUV = subcktFUND.getFUUV();
		return subcktFUUV;
	}
	
	Functional_unit_usage_view createFunctionalProductAndUsageViewForModel(SpiceModel m)
	{
		FunctionalProductVersion fpv = new FunctionalProductVersion(m.getName(), m.baseModelName, "1");
		attachModelParametersToFunctionalProduct(fpv, m);
		return createFunctionalProductAndUsageViewForElement(fpv, m.element);
	}
	
	CategoryModelParameterWithUnit getModelParamForSpiceElementValue(SpiceElement e)
	{
		ClassWithAttributes c = getClassForElement(e.element);
		CategoryModelParameterWithUnit cmp = c.parameterWithId(e.value.name);
		if (cmp != null)
		{
			return cmp;
		}
		cmp = new CategoryModelParameterWithUnit(e.value.name, "", e.value.description, e.value.unit, c);
		c.addParameter(cmp);
		return cmp;
	}

	void attachModelParametersToFunctionalProduct(FunctionalProductVersion fpv, SpiceModel m)
	{
		ClassWithAttributes c = getClassForElement(m.element);
		SpiceModelParamSet spiceParamSet = m.paramSet;
		for (SpiceParam p : spiceParamSet.modelParams)
		{
			CategoryModelParameterWithUnit cmp = new CategoryModelParameterWithUnit(p.name, "", p.description, p.unit, c);
			c.addParameter(cmp);
			
			Param param;
			if (p.unit != null)
				param = new MeasureParamImpl(p.name, p.value, p.unit);
			else
				param = new DoubleParamImpl(p.name, p.value);
			
			//MeasureParam measure = new MeasureParamImpl(p.name, p.value, p.unit);
			ParameterAssignment pa = new ParameterAssignment(param, cmp);
			fpv.addParam(pa);
		}
	}
	
	void attachElementParametersToFunctionalUnit(Functional_unit fu, SpiceElement e)
	{
		ClassWithAttributes c = getClassForElement(e.element);
		SpiceModelParamSet spiceParamSet = e.paramSet;
		for (SpiceParam p : spiceParamSet.modelParams)
		{
			CategoryModelParameterWithUnit cmp = new CategoryModelParameterWithUnit(p.name, "", p.description, p.unit, c);
			c.addParameter(cmp);
			
			Param param;
			if (p.unit != null)
				param = new MeasureParamImpl(p.name, p.value, p.unit);
			else
				param = new DoubleParamImpl(p.name, p.value);
			
			//MeasureParam measure = new MeasureParamImpl(p.name, p.value, p.unit);
			ParameterAssignment pa = new ParameterAssignment(param, cmp);
			fu.addParam(pa);
		}
	
	}
	
	Functional_unit_usage_view createFunctionalProductAndUsageViewForElement(LTSpiceElements e)
	{
		FunctionalProductVersion fpv = new FunctionalProductVersion(e.toString(), e.toString(), "1");
		return createFunctionalProductAndUsageViewForElement(fpv, e);
	}
	
	Functional_unit_usage_view createFunctionalProductAndUsageViewForElement(FunctionalProductVersion fpv, LTSpiceElements e)
	{
		Functional_unit_usage_view fuuv = new Functional_unit_usage_view(fpv);
		for (int i=1 ; i<=e.getNodeCount(); i++)
		{
			fuuv.addTerminal(""+i);
		}
		return fuuv;
	}
}
