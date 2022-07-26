package com.sfm.pkgModel.shape;


public class PhysicalUnit3dShapeModel extends PhysicalUnitShapeModel
{
	public static enum Predefined3dPurposeType
	{
		ANALYSIS_INPUT("analysis input"),
		ANALYSIS_OUTPUT("analysis output"),
		DESIGN("design"),
		THERMAL_ANALYSIS_INPUT("thermal analysis input"),
		THERMAL_ANALYSIS_OUTPUT("thermal analysis output");

		private String stringVal;
		
		Predefined3dPurposeType(String str)
		{
			this.stringVal = str;
		}
		
		public String toString()
		{
			return stringVal;
		}
	}
	
	Predefined3dPurposeType shapePurpose;

	public PhysicalUnit3dShapeModel(Predefined3dPurposeType purpose)
	{
		super();
		shapePurpose = purpose;
	}
	
	public Predefined3dPurposeType getShapePurpose()
	{
		return shapePurpose;
	}
}
