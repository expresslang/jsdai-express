package com.sfm.ap210.jsdai.param;

/**
 * A minimal implementation of MeasureParam.
 */
public class StringParamImpl implements StringParam {

	protected String name;
	protected String value;
	
	public StringParamImpl(String name, String value)
	{
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public String toString()
	{
		return "("+getName()+": "+getValue()+")";
	}	
}
