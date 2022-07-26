package com.sfm.pkgModel.shape;


public class AxisPlacement3d
{
	Vector location;
	Vector axis;
	Vector ref_direction;
	
	public AxisPlacement3d(Vector l, Vector a, Vector d)
	{
		location = l;
		axis = a;
		ref_direction = d;
	}
		
	public Vector getLocation()
	{
		return location;
	}
	
	public Vector getAxis()
	{
		return axis;
	}
	
	public Vector getRefDirection()
	{
		return ref_direction;
	}
	
	/**
	 * origin on the z-axis, oriented in positive-z direction
	 * @param zOffset
	 * @return
	 */
	public static AxisPlacement3d XYplane(double zOffset)
	{
		Vector l = new Vector(0.0, 0.0, zOffset);
		Vector a = new Vector(0.0, 0.0, 1.0);
		Vector d = new Vector(1.0, 0.0, 0.0);
		return new AxisPlacement3d(l, a, d);
	}
	
	/**
	 * origin on the y-axis, oriented in positive-y direction
	 * @param yOffset
	 * @return
	 */
	public static AxisPlacement3d XZplane(double yOffset)
	{
		Vector l = new Vector(0.0, yOffset, 0.0);
		Vector a = new Vector(0.0, 1.0, 0.0);
		Vector d = new Vector(1.0, 0.0, 0.0);
		return new AxisPlacement3d(l, a, d);
	}
	
	/**
	 * origin on the x-axis, oriented in positive-x direction
	 * @param xOffset
	 * @return
	 */
	public static AxisPlacement3d YZplane(double xOffset)
	{
		Vector l = new Vector(xOffset, 0.0, 0.0);
		Vector a = new Vector(1.0, 0.0, 0.0);
		Vector d = new Vector(0.0, 1.0, 0.0);
		return new AxisPlacement3d(l, a, d);
	}
}
