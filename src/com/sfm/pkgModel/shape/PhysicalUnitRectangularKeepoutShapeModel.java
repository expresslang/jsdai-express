package com.sfm.pkgModel.shape;


public class PhysicalUnitRectangularKeepoutShapeModel extends
		PhysicalUnitPlanarKeepoutShapeModel implements RectangularShape
{
	double xLoc, yLoc, xDim, yDim;
	
	public PhysicalUnitRectangularKeepoutShapeModel(
			PredefinedKeepoutPurposeType purpose,
			double xl, double yl, double xd, double yd)
	{
		super(purpose);
		xLoc = xl;
		yLoc = yl;
		xDim = xd;
		yDim = yd;
	}
	
	public double getXloc()
	{
		return xLoc;
	}

	public double getYloc()
	{
		return yLoc;
	}
	
	public double getXdim()
	{
		return xDim;
	}

	public double getYdim()
	{
		return yDim;
	}

}
