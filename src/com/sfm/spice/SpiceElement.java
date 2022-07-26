package com.sfm.spice;

import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import com.sfm.ap210.jsdai.param.SiUnit;
import com.sfm.ap210.jsdai.param.SiUnit.SiBase;
import com.sfm.demo.SpiceReader;

public class SpiceElement {
	SpiceCircuit circuit;
	String name;
	LTSpiceElements element;
	List<String> nodes;
	SpiceModel model = null;
	SpiceSubckt subckt = null;
	SpiceParam value;
	SpiceModelParamSet paramSet;
	
	public enum LTSpiceElements
	{
	    C (2, SiBase.F, false),
		D (2, null, true),
		I (2, SiBase.A, false),
		J (3, null, true),
		L (2, SiBase.H, false),
	    Q (4, null, true),
	    R (2, SiBase.Ohm, false),
	    V (2, SiBase.V, false),
	    X (-1, null, false),
	    unknown (-1, null, false);
	    
	    private final int nodeCount;
	    private final boolean isModelElement;
	    private final SiBase unit;
	    
	    LTSpiceElements(int nodeCount, SiBase unit, boolean hasModel) {
	        this.nodeCount = nodeCount;
	        this.isModelElement = hasModel;
	        this.unit = unit;
	    }
	    public int getNodeCount()   { return nodeCount; }
	    public boolean hasModel()   { return isModelElement; }
	    public SiBase getUnit() { return unit; }
	    
	    public static LTSpiceElements fromText(String t)
		{
			for (LTSpiceElements e : LTSpiceElements.values())
			{
				if (t.equalsIgnoreCase(e.toString()))
					return e;
			}
			return null;
		}
	}
	
	static public boolean isElement(char c)
	{
		LTSpiceElements element = LTSpiceElements.fromText(""+c);
		return (element != null);
	}
	
	public SpiceElement(SpiceCircuit c, String n, LTSpiceElements e, SpiceParam v, List<String> nodeNames)
	{
		circuit = c;
		value = v;
		name = n;
		element = e;
		nodes = nodeNames;
	}
	
	public SpiceElement(SpiceCircuit c, String n, LTSpiceElements e, SpiceParam v, List<String> nodeNames, SpiceModel m)
	{
		circuit = c;
		value = v;
		name = n;
		element = e;
		nodes = nodeNames;
		model = m;
	}
	
	public SpiceElement(SpiceCircuit c, String aLine, SpiceReader reader)
	  {
		circuit = c;
		nodes = new Vector<String>();
		
		char first = aLine.charAt(0);
		element = LTSpiceElements.fromText(""+first);
		
		Scanner scanner = new Scanner(aLine);
		scanner.useDelimiter(" ");
		String elementNameText = scanner.next();
		name = elementNameText;
		
		int nodeCount = element.getNodeCount();
		
		// special case for 'X' element
		if (nodeCount < 0)
		{
			subckt = findSubCkt(c, aLine);
			nodeCount = subckt.interfaceNodes.size(); 
		}
		for (int i = 0; i<nodeCount; i++)
		{
			String nodei = scanner.next();
			circuit.nodes.add(nodei);
			nodes.add(nodei);
		}
		  
		SiBase valueSiBase = element.getUnit();
		
		if (valueSiBase != null)
		{
			String valuetext = scanner.next();
			value = SpiceParam.parseValue(element.toString(), "value", new SiUnit(valueSiBase), valuetext);
		}
		
		if (element.hasModel())
		{
			String modelText = scanner.next();
			if (circuit.modelMap.containsKey(modelText))
				model = circuit.modelMap.get(modelText);
			else
				log("Unable to map model: "+modelText);
		}
		
		String paramText = "";
		if ((scanner.hasNext()) && (subckt == null))
		{
			paramText = scanner.nextLine().trim();
			paramSet = new SpiceModelParamSet(element.toString());
			paramSet.addFromSpiceText(paramText);
			
			//log("Ignoring remainder: "+paramText);
		}
	  }
	
	public void setParamSet(SpiceModelParamSet pset)
	{
		paramSet = pset;
	}
	
	SpiceSubckt findSubCkt(SpiceCircuit c, String aLine)
	{
		for (String subName : c.subcktMap.keySet())
		{
			if (aLine.contains(subName))
				return c.subcktMap.get(subName);
		}
		return null;
	}
	
	public String toString()
	{
		String s = element.toString();
		s = s + " '"+name+"' (";
		for (String n : nodes)
		{
			s = s + " " + n;
		}
		s = s + ") ";
		if (value != null)
			s = s + value.toString();
		if (model != null)
			s = s + model.toString();
		if (subckt != null)
			s = s + subckt.toString();
		if (paramSet != null)
			s = s + paramSet.toString();
		return s;
	}
	
	public String toNetFormatString()
	{
		String s = name;
		for (String n : nodes)
		{
			s = s + " " + n;
		}
		s = s + " ";
		if (value != null)
			s = s + value.toValueNetFormatString();
		if (model != null)
			s = s + model.name;
		if (subckt != null)
			s = s + subckt.name;
		if ((paramSet != null) && (!(paramSet.isEmpty())))
		{
			s = s + " ";
			s = s + paramSet.toNetFormatString();
		}
		return s;
	}
	
	 private static void log(Object aObject)
	 {
		 System.out.println(String.valueOf(aObject));
	 }
}
