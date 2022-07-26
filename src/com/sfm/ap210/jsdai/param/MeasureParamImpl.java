package com.sfm.ap210.jsdai.param;

/**
 * A minimal implementation of MeasureParam.
 */
public class MeasureParamImpl implements MeasureParam {

	protected String name;
	protected Units unit;
	protected double value;
	
	public MeasureParamImpl(String name, double value, Units unit)
	{
		this.name = name;
		this.value = value;
		if (unit==Units.MILLIMETERS)
			this.value = Math.round(100*value)/100.0;
		this.unit = unit;
	}
	
	public double getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
	
	public String toString()
	{
		return "("+getName()+": "+getValue()+" "+getUnit()+")";
	}
	
	public Units getUnit()
	{
		return unit;
	}
}
