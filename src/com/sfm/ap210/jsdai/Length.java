package com.sfm.ap210.jsdai;

/**
 * A representation of a physical length quantity. A Length contains a double value and a LengthUnit.
 * Implementation currently supports only mm and inch length units.
 *
 */
public class Length
{
	double value;
	public enum LengthUnit { MM, INCH };
	LengthUnit unit;

	public Length(double v, LengthUnit u)
	{
		value = v;
		unit = u;
	}

	public double getValue()
	{
		return value;
	}
	
	public LengthUnit getUnit()
	{
		return unit;
	}
	
	public String toString()
	{
		return value + " "+unit.toString();
	}
}
