package com.sfm.pkgModel.shape;


public class CircularShapeRepresentation extends ShapeRepresentation implements CircularShape
{
	double xLoc, yLoc, dia;

	public CircularShapeRepresentation(double xl, double yl, double d)
	{
		xLoc = xl;
		yLoc = yl;
		dia = d;
	}
	
	public double getXloc()
	{
		return xLoc;
	}

	public double getYloc()
	{
		return yLoc;
	}
	
	public double getDiameter()
	{
		return dia;
	}
}
