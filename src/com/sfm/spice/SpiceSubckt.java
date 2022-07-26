package com.sfm.spice;

import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

import com.sfm.ap210.jsdai.functional.Functional_unit_usage_view;
import com.sfm.ap210.jsdai.functional.Scalar_terminal_definition;

public class SpiceSubckt extends SpiceCircuit implements Comparable<SpiceSubckt> {
	//public SpiceNodeList interfaceNodes;
	public SortedSet<String> interfaceNodes;
	public SpiceSubckt(String subTextFromSpice)
	{
		super("");
		
		interfaceNodes = new TreeSet<String>();
		
		Scanner scanner = new Scanner(subTextFromSpice);
		scanner.useDelimiter(" ");
		String elementNameText = scanner.next();
		name = elementNameText;
		
		while (scanner.hasNext())
		{
			interfaceNodes.add(scanner.next());
		}
	}
	
	public SpiceSubckt(String name, Functional_unit_usage_view fuuv)
	{
		super(name);
		interfaceNodes = new TreeSet<String>();
		for (Scalar_terminal_definition std : fuuv.getTerminals())
		{
			interfaceNodes.add(std.getSignalName());
		}
	}
	
	public String toString()
	{
		String s = "subckt: "+name+" ";
		s = s + "\ninterface of subckt: "+name+" "+interfaceNodes.toString();
		s = s + "\n"+this.reportElements();
		s = s + "nodes of subckt: "+name+" "+this.reportNodes();
		s = s +"end subckt: "+name;
		return s;
	}

	@Override
	public int compareTo(SpiceSubckt o2) {
		return this.toString().compareTo(o2.toString());
	}
}
