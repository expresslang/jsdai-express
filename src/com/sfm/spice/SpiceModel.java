package com.sfm.spice;

import java.util.Scanner;

import com.sfm.spice.SpiceElement.LTSpiceElements;

public class SpiceModel implements Comparable<SpiceModel> {
	String name;
	String baseModelName;
	LTSpiceElements element;
	SpiceModelParamSet paramSet;
	
	/**
	 * name is the instance name, elementName is the supporting LTSpice element (i.e. D, NPN, ...)
	 * .model DD D(Rs=20 Cjo=5p)
	 * @param name
	 * @param elementName
	 */
	public SpiceModel(String n, String baseName)
	{
		baseModelName = baseName;
		name = n;
		paramSet = null;
	}
	
	/**
	 * parses and populates given text into SpiceModel object
	 * .model NP NPN(Bf=150 Cjc=3p Cje=3p Rb=10)
	 * @param modelstring
	 */
	public SpiceModel(String modelstring)
	{
		  Scanner scanner = new Scanner(modelstring);
		  scanner.useDelimiter(" ");
		  name = scanner.next();
		  scanner.useDelimiter("[()]");
		  baseModelName = scanner.next().trim();
		  element = getElementForBaseModelName(baseModelName);
		  if (scanner.hasNext())
		  {
			  String paramText = scanner.next();
			  this.addParamsFromSpiceText(paramText);
		  }
		  else
			  log("No parameters found for model: "+baseModelName);
	  }
	
	public static LTSpiceElements getElementForBaseModelName(String modelName)
	{
		if (modelName.equals("D"))
			return LTSpiceElements.D;
		if (modelName.equals("NPN"))
			return LTSpiceElements.Q;
		if (modelName.equals("PNP"))
			return LTSpiceElements.Q;
		if (modelName.equals("NJF"))
			return LTSpiceElements.J;
		if (modelName.equals("PJF"))
			return LTSpiceElements.J;
		throw new IllegalArgumentException("Unsupported base model: "+modelName);
	}
	
	public void setParamSet(SpiceModelParamSet paramSet)
	{
		this.paramSet = paramSet;
	}
	
	public String getName()
	{
		return name;
	}
	
	public LTSpiceElements getElement()
	{
		return element;
	}
	
	public String getBaseModelName()
	{
		return baseModelName;
	}
	
	public SpiceModelParamSet getParamSet()
	{
		return paramSet;
	}
	
	public void addParam(SpiceParam p)
	{
		if (paramSet == null)
			paramSet = new SpiceModelParamSet(baseModelName);
		paramSet.addParam(p);
	}
	
	public void addParamsFromSpiceText(String paramText)
	{
		paramSet = new SpiceModelParamSet(baseModelName);
		paramSet.addFromSpiceText(paramText);
	}
	
	public String toString()
	{
		String s = "Model Name: " + name + " base model : " + baseModelName;
		if (paramSet != null)
			s = s + paramSet.toString();
		return s;
	}
	
	public String toNetFormatString()
	{
		String s = ".model " + name + " " + baseModelName;
		if (paramSet != null)
		{
			s = s + "(";
			s = s + paramSet.toNetFormatString();
			s = s + ")";
		}
		s = s + "\n";
		return s;
	}
	
	private static void log(Object aObject)
	{
	    System.out.println(String.valueOf(aObject));
	}

	public int compareTo(SpiceModel o) {
		return this.getName().compareTo(o.getName());
	}
}
