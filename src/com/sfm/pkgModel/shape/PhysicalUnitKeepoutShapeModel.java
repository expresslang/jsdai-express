package com.sfm.pkgModel.shape;


public abstract class PhysicalUnitKeepoutShapeModel extends PhysicalUnitShapeModel
{
	public static enum PredefinedKeepoutPurposeType
	{
		THERMAL("thermal"),						
		GENERIC_CLEARANCE("generic clearance"),
		SHOCK("shock"),
		VIBRATION("vibration"),
		ELECTROMAGNETIC_COMPATABILITY("electromagnetic_compatability");

		
		private String stringVal;
		
		PredefinedKeepoutPurposeType(String str)
		{
			this.stringVal = str;
		}
		
		public String toString()
		{
			return stringVal;
		}
	}
	
	PredefinedKeepoutPurposeType shapePurpose;

	public PhysicalUnitKeepoutShapeModel(PredefinedKeepoutPurposeType purpose)
	{
		super();
		shapePurpose = purpose;
	}
	
	public PredefinedKeepoutPurposeType getShapePurpose()
	{
		return shapePurpose;
	}
}
