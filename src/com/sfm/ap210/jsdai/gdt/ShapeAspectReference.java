package com.sfm.ap210.jsdai.gdt;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EShape_aspect;

public class ShapeAspectReference {
	String key;
	EShape_aspect e_sa;
	
	public ShapeAspectReference(String k, EShape_aspect sa)
	{
		key = k;
		e_sa = sa;
	}
	
	public EShape_aspect getShapeAspect()
	{
		return e_sa;
	}
}
