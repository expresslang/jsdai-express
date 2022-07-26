package com.sfm.ap210.jsdai.param;

/**
 * A category ModelParameter has an associated ClassWithAttributes. This serves as a grouping
 * mechanism for model parameters defined in the context of an external tool
 * @author James Stori
 *
 */
public class CategoryModelParameterWithUnit extends ModelParameterWithUnit  
{
	ClassWithAttributes associatedClass;
	
	public CategoryModelParameterWithUnit(String id, String property_type, String desc, Unit u, ClassWithAttributes c)
	{
		super(id, property_type, desc, u);
		associatedClass = c;	
	}
	
	public ClassWithAttributes getAssociatedClass()
	{
		return associatedClass;
	}
	
	public int compareTo(CategoryModelParameterWithUnit o) {
		String o1 = this.associatedClass.id + ":"+id;
		String o2 = o.associatedClass.id + ":"+o.id;
		return o1.compareTo(o2);
	}
}
