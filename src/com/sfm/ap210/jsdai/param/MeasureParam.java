package com.sfm.ap210.jsdai.param;

/**
 * A skeletal interface for a physical measurement parameter supporting a subset of commonly used measure units.
 */
public interface MeasureParam extends Param {
	
	public static enum Units {
	    UNKNOWN_UNITS,
		MILLIMETERS,
		SQUARE_MILLIMETERS,		
		DEG_CELSIUS,
		SECONDS 
	}
	
	double getValue();
	Units getUnit();
}
