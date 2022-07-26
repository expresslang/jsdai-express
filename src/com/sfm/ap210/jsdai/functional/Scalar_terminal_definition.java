package com.sfm.ap210.jsdai.functional;


public class Scalar_terminal_definition extends FunctionalModelObject implements Comparable<Scalar_terminal_definition> {

	String name;
	Functional_unit_usage_view fuuv;
	
	public Scalar_terminal_definition(String signal_name, Functional_unit_usage_view usage_view)
	{
		name = signal_name;
		fuuv = usage_view;
	}
	
	public String getSignalName()
	{
		return name;
	}
	
	public Functional_unit_usage_view getUsageView()
	{
		return fuuv;
	}

	public int compareTo(Scalar_terminal_definition o) {
		return this.getSignalName().compareTo(o.getSignalName());
	}
	
	public String toString()
	{
		return name;
	}
}
