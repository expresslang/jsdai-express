package com.sfm.ap210.jsdai.functional;

public class Functional_unit_terminal extends FunctionalModelObject implements Comparable<Functional_unit_terminal> {
	
	Scalar_terminal_definition definition;
	public Functional_unit fu;
	
	public Functional_unit_terminal(Scalar_terminal_definition scalar_terminal, Functional_unit fu)
	{
		definition = scalar_terminal;
		this.fu = fu;
		fu.addTerminal(this);
	}
	
	public Scalar_terminal_definition getDefiningScalarTerminal()
	{
		return definition;
	}
	
	public Functional_unit getFunctionalUnit()
	{
		return fu;
	}
	
	public int compareTo(Functional_unit_terminal o2) {
		String s1 = this.fu.refdes+":"+this.definition.getSignalName();
		String s2 = o2.fu.refdes+":"+o2.definition.getSignalName();
		return s1.compareTo(s2);
	}
	
	public String toString()
	{
		return "FUT: "+definition.toString()+" of "+fu.toString();
	}
}
