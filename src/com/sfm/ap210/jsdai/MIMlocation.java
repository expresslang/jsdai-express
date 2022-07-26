package com.sfm.ap210.jsdai;

import jsdai.SGeometry_schema.*;

/**
 * A class to store the set of transformations necessary to locate an {@MIMandARM Assembly_component} on the pcb.
 * 
 * This includes 0 or 1 {@MIM cartesian_transformation_operator_2d} ({@ARM Cartesian_transformation_2d}) and 0, 1, or 2 {@MIM axis2_placement_2d} ({@ARM Axis_placement_2d})
 * Note that in the instance of a nested {@MIMandARM Structured_layout_component}, multiple stacked instances of {@link com.sfm.ap210.jsdai.MIMlocation} will be necessary to locate the assembly_component on the pcb.
 * 
 * The 0 to 3 transformations contained within a {@link com.sfm.ap210.jsdai.MIMlocation} instance represent a transformation stack with the transformation returned by {@link #getCto2d()} as the top-level transformation relative to the pcb and the transformation returned by {@link #getAp2d2()} being the transformation at the bottom of the stack.
 * For example, {@link #getAp2d2()} could position a pad within a padtack, {@link #getAp2d1()} might position a padstack within a footprint, and {@link #getCto2d()} might position a footprint on the pcb.
 *     
 * @author James Stori, SFM Technology, Inc.
 * @version 1.3
 */
public class MIMlocation {
	private ECartesian_transformation_operator_2d cto2d;
	private EAxis2_placement_2d ap2d1;
	private EAxis2_placement_2d ap2d2;
	
	public MIMlocation()
	{
		cto2d = null;
		ap2d1 = null;
		ap2d2 = null;
	}
	
	public MIMlocation(ECartesian_transformation_operator_2d cto2d, 
			EAxis2_placement_2d ap2d1, 
			EAxis2_placement_2d ap2d2)
	{
		this.cto2d = cto2d;
		this.ap2d1 = ap2d1;
		this.ap2d2 = ap2d2;
	}
	
	public ECartesian_transformation_operator_2d getCto2d()
	{
		return cto2d;
	}
	
	public EAxis2_placement_2d getAp2d1()
	{
		return ap2d1;
	}
	
	public EAxis2_placement_2d getAp2d2()
	{
		return ap2d2;
	}
	
	public void setCto2d(ECartesian_transformation_operator_2d cto2d)
	{
		this.cto2d = cto2d;
	}
}
