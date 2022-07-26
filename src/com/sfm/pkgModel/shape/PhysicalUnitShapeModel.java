package com.sfm.pkgModel.shape;


public abstract class PhysicalUnitShapeModel extends ShapeRepresentation
{
	public static enum ShapeMaterialConditionType
	{
		MAXIMUM_MATERIAL_CONDITION("maximum material condition"),
		MINIMUM_MATERIAL_CONDITION("minimum material condition"),
		NOMINAL_MATERIAL_CONDITION("nominal material condition");
		
		private String stringVal;
		
		ShapeMaterialConditionType(String str)
		{
			this.stringVal = str;
		}
		
		public String toString()
		{
			return stringVal;
		}
	}
	
	public static enum ApplicationEnvironmentType
	{
		MANUFACTURING("manufacturing"),						
		END_USER_ENVIRONMENT("end user environment");
		
		private String stringVal;
		
		ApplicationEnvironmentType(String str)
		{
			this.stringVal = str;
		}
		
		public String toString()
		{
			return stringVal;
		}
	}
	
	public static enum ShapeExtentType
	{
		ENVELOPE("envelope"),						
		OVER_BODY("over body"),
		OVER_LANDS("over lands"),
		OVER_BREAKOUT("over breakout");
		
		private String stringVal;
		
		ShapeExtentType(String str)
		{
			this.stringVal = str;
		}
		
		public String toString()
		{
			return stringVal;
		}
	}
	
	public static enum ShapeApproximationLevelType
	{
		COARSE("coarse"),						
		DETAILED("detailed"),
		IS_UNKNOWN("is unknown");
		
		private String stringVal;
		
		ShapeApproximationLevelType(String str)
		{
			this.stringVal = str;
		}
		
		public String toString()
		{
			return stringVal;
		}
	}
	
	ShapeMaterialConditionType shapeMaterialCondition;
	ApplicationEnvironmentType shapeEnvironment;
	ShapeExtentType shapeExtent;
	ShapeApproximationLevelType shapeApproximationLevel;

	public PhysicalUnitShapeModel()
	{
		super();
		shapeEnvironment = ApplicationEnvironmentType.END_USER_ENVIRONMENT;
		shapeExtent = ShapeExtentType.ENVELOPE;
		shapeApproximationLevel = ShapeApproximationLevelType.COARSE;
		shapeMaterialCondition = ShapeMaterialConditionType.NOMINAL_MATERIAL_CONDITION;
		
	}
	
	public ShapeMaterialConditionType getShapeMaterialCondition()
	{
		return shapeMaterialCondition;
	}
	
	public ApplicationEnvironmentType getShapeEnvironment()
	{
		return shapeEnvironment;
	}

	public ShapeExtentType getShapeExtent()
	{
		return shapeExtent;
	}
	
	public ShapeApproximationLevelType getShapeApproximationLevel()
	{
		return shapeApproximationLevel;
	}
}
