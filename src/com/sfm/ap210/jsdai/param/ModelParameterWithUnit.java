package com.sfm.ap210.jsdai.param;

public class ModelParameterWithUnit extends ModelParameter {
	Unit unit;
	
	public ModelParameterWithUnit(String id, String property_type, String desc, Unit u) 
	{
		super(id, property_type, desc);
		this.unit = u;
	}
	
	public Unit getUnit()
	{
		return unit;
	}

}
