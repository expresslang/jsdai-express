package com.sfm.ap210.jsdai.param;

/**
 * A minimal implementation of BooleanParam.
 */
public class BooleanParamImpl implements BooleanParam {

	protected String name;
	protected boolean value;
	
	public BooleanParamImpl(String name,boolean value)
	{
		this.name = name;
		this.value = value;
	}
	
	public String getName()
	{
		return name;
	}
	
	public boolean getBooleanValue()
	{
		return value;
	}
	
	public Boolean getValue() {
		return Boolean.valueOf(getBooleanValue());
	}
	
	public String toString()
	{
		return "("+getName()+": "+getBooleanValue()+")";
	}	
}
