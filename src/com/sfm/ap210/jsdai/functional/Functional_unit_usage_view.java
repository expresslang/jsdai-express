package com.sfm.ap210.jsdai.functional;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class Functional_unit_usage_view extends FunctionalModelObject {

	// changed from Map to Vector implementation to retain insertion sequence
	//Map<String, Scalar_terminal_definition> terminals = new TreeMap<String, Scalar_terminal_definition>();
	List<Scalar_terminal_definition> terminals = new Vector<Scalar_terminal_definition>();
	public FunctionalProductVersion fpv = null;
	String id;
	
	public Functional_unit_usage_view(FunctionalProductVersion fpd)
	{
		fpv = fpd;
		fpd.setFUUV(this);
		id = "usage view of version "+fpd.getVersionId()+" of "+fpd.getProductId();
	}
	
	public String getId()
	{
		return id;
	}
	
	public FunctionalProductVersion getFunctionalProductVersion()
	{
		return fpv;
	}
	
	public void addTerminal(Scalar_terminal_definition t)
	{
		terminals.add(t);
	}
	
	public Scalar_terminal_definition addTerminal(String signal_name)
	{
		//if (terminals.containsKey(signal_name))
		//	throw new IllegalArgumentException("terminal "+signal_name+" already exists");
		Scalar_terminal_definition t = new Scalar_terminal_definition(signal_name, this);
		//terminals.put(signal_name, t);
		terminals.add(t);
		return t;
	}
	
	public List<Scalar_terminal_definition> getTerminals()
	{
		//List<Scalar_terminal_definition> scalar_terminals = new Vector<Scalar_terminal_definition>(terminals.values());
		//Collections.sort(scalar_terminals);
		//return scalar_terminals;
		return terminals;
	}
	
	/**
	 * returns null if terminal with given signal_name does not exist
	 * @param signal_name
	 * @return
	 */
	public Scalar_terminal_definition getTerminalOfName(String signal_name)
	{
		for (Scalar_terminal_definition t : terminals)
		{
			if (t.getSignalName().equals(signal_name))
				return t;
		}
		return null;
	}
	
	public Functional_unit createInstanceOfUsageView(String refDes)
	{
		Functional_unit instance = new Functional_unit(refDes, fpv.getProductId(), this);
		instance.addTerminals(this.getTerminals());
		return instance;
	}
	
	public String toString()
	{
		return "FUUV id: "+id+" FPV: "+fpv.product_id+"-"+fpv.product_name+" "+terminals.toString();
	}
}
