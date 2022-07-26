package com.sfm.ap210.jsdai.functional;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

import com.sfm.ap210.jsdai.param.*;

public class Functional_unit extends FunctionalModelObject implements Comparable<Functional_unit> {

	public SortedSet<Functional_unit_terminal> terminals = new TreeSet<Functional_unit_terminal>();
	public String refdes;
	public String description;
	public Functional_unit_usage_view fuuv;
	public Set<ParameterAssignment> params = new TreeSet<ParameterAssignment>();
	
	public Functional_unit(String refdes, String description, Functional_unit_usage_view fuuv)
	{
		this.refdes = refdes;
		this.description = description;
		this.fuuv = fuuv;
	}
	
	public void addParam(ParameterAssignment p)
	{
		params.add(p);
	}
	
	public void addParams(Set<ParameterAssignment> pa_set)
	{
		params.addAll(pa_set);
	}
	
	public Param getParamWithModelParameterId(String mp_id)
	{
		for (ParameterAssignment pa : params)
		{
			if (pa.getModelParameter().id.equals(mp_id))
				return pa.getParam();
		}
		throw new IllegalArgumentException("Unable to find parameter assignment for given model parameter id: "+mp_id);
	}
	public Set<ParameterAssignment> getParams()
	{
		return params;
	}
	
	void addTerminals(Collection<Scalar_terminal_definition> terminals)
	{
		for(Scalar_terminal_definition t : terminals)
		{
			this.createTerminalBasedOnDefinition(t);
		}
	}
	
	void addTerminal(Functional_unit_terminal fut)
	{
		terminals.add(fut);
	}
	
	public void createTerminalsFromUsageView()
	{
		for (Scalar_terminal_definition std : fuuv.getTerminals())
			createTerminalBasedOnDefinition(std);
	}
	
	void createTerminalBasedOnDefinition(Scalar_terminal_definition scalar_terminal)
	{
		Functional_unit_terminal fut = new Functional_unit_terminal(scalar_terminal, this);
		terminals.add(fut);
	}
	
	public String getRefDes()
	{
		return this.refdes;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public Functional_unit_usage_view getFUUV()
	{
		return fuuv;
	}
	
	public Functional_unit_terminal getTerminalBySignalName(String signal_name)
	{
		for (Functional_unit_terminal t : terminals)
		{
			if (t.getDefiningScalarTerminal().getSignalName().equals(signal_name))
				return t;
		}
		throw new IllegalArgumentException("Unable to find terminal with signal name: "+signal_name);
	}
	
	public Collection<Functional_unit_terminal> getTerminals()
	{
		return terminals;
	}
	
	public int compareTo(Functional_unit o) {
		return this.getRefDes().compareTo(o.getRefDes());
	}
	
	/**
	 * returns null if terminal with given signal_name does not exist
	 * @param signal_name
	 * @return
	 */
	/*
	public Functional_unit_terminal getTerminalOfName(String signal_name)
	{
		if (terminals.containsKey(signal_name))
			return terminals.get(signal_name);
		else
			return null;
	}
	*/
	public String toString()
	{
		return "FU: "+refdes;
	}
}
