package com.sfm.ap210.jsdai.param;

/**
 * A minimal implementation of MeasureParam.
 */
public class DoubleParamImpl implements DoubleParam {

	protected String name;
	protected double value;
	
	public DoubleParamImpl(String name, double value)
	{
		this.name = name;
		this.value = value;
	}
	
	public double getDoubleValue() {
		return value;
	}
	
	public Double getValue() {
		return Double.valueOf(getDoubleValue());
	}

	public String getName() {
		return name;
	}
	
	public String toString()
	{
		return "("+getName()+": "+getDoubleValue()+")";
	}
}
