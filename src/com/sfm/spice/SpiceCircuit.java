package com.sfm.spice;

import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

public class SpiceCircuit {
	String name;
	public List<String> elementStringQueue = new Vector<String>();
	SortedSet<String> nodes;
	List<SpiceElement> elements;
	public SortedMap<String, SpiceModel> modelMap;
	public SortedMap<String, SpiceSubckt> subcktMap;
	
	public SpiceCircuit(String n)
	{
		name = n;
		nodes = new TreeSet<String>();
		elements = new Vector<SpiceElement>();
		modelMap = new TreeMap<String, SpiceModel>();
		subcktMap = new TreeMap<String, SpiceSubckt>();
	}
	
	public String getName()
	{
		return name;
	}
	
	public void addElement(SpiceElement e)
	{
		elements.add(e);
	}
	
	public String toNetFormatString()
	{
		String s = "";
		for (SpiceElement e : elements)
		{
			s = s + e.toNetFormatString()+"\n";
		}
		for (SpiceSubckt sub : subcktMap.values())
		{
			s = s + ".subckt " + sub.name;
			for (String n : sub.interfaceNodes)
			{
				s = s + " " + n;
			}
			s = s + "\n";
			s = s + sub.toNetFormatString();
			s = s + ".ends\n";
		}
		for (SpiceModel model : modelMap.values())
		{
			s = s + model.toNetFormatString();
		}
		return s;
	}
	
	public String reportElements()
	{
		String s = "";
		for (SpiceElement e : elements)
		{
			s = s + e.toString()+"\n";
		}
		return s;
	}
	
	public void addNode(String n)
	{
		nodes.add(n);
	}
	
	public String reportNodes()
	{
		  return nodes.toString()+"\n";
	}

	public String toString()
	{
		String s = "circuit: "+name+"\n";
		s = s + this.reportElements();
		s = s + this.reportNodes();
		s = s +"end circuit: "+name;
		return s;
	}
}
