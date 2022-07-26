package com.sfm.pkgModel.shape;


public class PhysicalUnitPlanarShapeModel extends PhysicalUnitShapeModel
{
	public static enum PredefinedPlanarPurposeType
	{
		ASSEMBLY_SYMBOL("assembly symbol"),						
		DESIGN("design"),
		THERMAL_ANALYSIS_OUTPUT("thermal analysis output"),
		PHYSICAL_EXTENT("physical extent");
		
		private String stringVal;
		
		PredefinedPlanarPurposeType(String str)
		{
			this.stringVal = str;
		}
		
		public String toString()
		{
			return stringVal;
		}
	}
	
	PredefinedPlanarPurposeType shapePurpose;

	public PhysicalUnitPlanarShapeModel(PredefinedPlanarPurposeType purpose)
	{
		super();
		shapePurpose = purpose;
	}
	
	public PredefinedPlanarPurposeType getShapePurpose()
	{
		return shapePurpose;
	}

}
