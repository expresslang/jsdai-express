package com.sfm.ap210.jsdai.param;

import java.util.Set;
import java.util.TreeSet;


public class ClassWithAttributes {
	public String id;
	public String name;
	public String description;
	Set<CategoryModelParameterWithUnit> attributes = new TreeSet<CategoryModelParameterWithUnit>();
	public DigitalDocument referenceDocument; 
	
	public ClassWithAttributes(String i, String n, String d)
	{
		id = i;
		name = n;
		description = d;
		referenceDocument = null;
	}
	
	public ClassWithAttributes(String i, String n, String d, DigitalDocument reference)
	{
		id = i;
		name = n;
		description = d;
		referenceDocument = reference;
	}
	
	/**
	 * can't have two with the same id in the same class
	 * @param p
	 */
	public void addParameter(CategoryModelParameterWithUnit p)
	{
		CategoryModelParameterWithUnit existing = parameterWithId(p.id);
		if (existing == null)
			attributes.add(p);
		else
		{
			; // don't need to add it 
			//throw new IllegalArgumentException("Duplicate model parameter id: "+p);
		}
			
	}
	
	public DigitalDocument getDocumentReference()
	{
		return referenceDocument;
	}
	
	public CategoryModelParameterWithUnit parameterWithId(String id)
	{
		for (CategoryModelParameterWithUnit p : attributes)
		{
			if (p.id.equalsIgnoreCase(id))
				return p;
		}
		return null;
	}
}
