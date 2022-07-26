package com.sfm.ap210.jsdai.param;

/**
 * A minimal implementation of MeasureParam.
 */
public class MeasureParamImpl implements MeasureParam {

	protected String name;
	protected Unit unit;
	protected double value;
	
	public MeasureParamImpl(String name, double value, Unit unit)
	{
		this.name = name;
		this.value = value;
		this.unit = unit;
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
		return "("+getName()+": "+getDoubleValue()+" "+getUnit()+")";
	}
	
	public Unit getUnit()
	{
		return unit;
	}
}
