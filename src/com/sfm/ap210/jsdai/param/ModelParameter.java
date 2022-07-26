package com.sfm.ap210.jsdai.param;

/**
 * ModelParameter is an implementation of the ARM Model_parameter application object.
 * A ModelParameter contains an id, property_type, and description.
 *  
 */
public class ModelParameter implements Comparable<ModelParameter>
{
	public String id;
	public String property_type;
	public String description;

	public ModelParameter(String id, String property_type, String desc)
	{
		this.id = id;
		this.property_type = property_type;
		this.description = desc;
	}

	public String getId()
	{
		return id;
	}
	
	public String getPropertyType()
	{
		return property_type;
	}
	
	public String getDescription()
	{
		return description;
	}

	public String toString()
	{
		return "ModelParam: "+id+":"+property_type+":"+description;
	}
	public int compareTo(ModelParameter o) {
		return this.toString().compareTo(o.toString());
	}
}
