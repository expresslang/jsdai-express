package com.sfm.ap210.jsdai.param;

/**
 * A minimal implementation of IntegerParam.
 */
public class IntegerParamImpl implements IntegerParam {

	protected String name;
	protected int value;
	
	public IntegerParamImpl(String name, int value)
	{
		this.name = name;
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
	
	public String toString()
	{
		return "("+getName()+": "+getValue()+")";
	}
}
