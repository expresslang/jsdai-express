package com.sfm.ap210.jsdai.param;

/**
 * A ParameterAssignment associates a ModelParameter with a Param.  
 */
public class ParameterAssignment implements Comparable<ParameterAssignment>
{
	Param param;
	ModelParameter modelParam;

	public ParameterAssignment(Param p, ModelParameter mp)
	{
		param = p;
		modelParam = mp;
	}
	
	public ModelParameter getModelParameter()
	{
		return modelParam;
	}
	
	public Param getParam()
	{
		return param;
	}
	
	public String toString()
	{
		return modelParam.toString() + " : " + param.toString();
	}
	public int compareTo(ParameterAssignment o) {
		return this.toString().compareTo(o.toString());
	}
}
