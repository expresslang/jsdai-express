package com.sfm.ap210.jsdai.gdt;

import java.util.Set;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.ECentre_of_symmetry;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EComposite_group_shape_aspect;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EShape_aspect;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.ESymmetric_shape_aspect;

import com.sfm.ap210.jsdai.Length;

public class FeatureOfSizeToleranceImpl extends GeometricTolerance implements FeatureOfSizeTolerance {
	ESymmetric_shape_aspect e_ssa = null;

	public FeatureOfSizeToleranceImpl(String n, Length l, ESymmetric_shape_aspect ssa, ToleranceType t)
	{
		this(n, l, null, ssa, t);
	}
	
	public FeatureOfSizeToleranceImpl(String n, Length l, ToleranceModifier m, ESymmetric_shape_aspect ssa, ToleranceType t)
	{
		super(n, l, m, t);
		e_ssa = ssa;
	}
	
	public ESymmetric_shape_aspect getFeatureOfSize()
	{
		return e_ssa;
	}

	public FeatureType getFeatureType() {
		return FeatureType.FEATUREOFSIZE;
	}

	public EShape_aspect getShapeAspect() {
		return e_ssa;
	}
	
	public int compareTo(Object o) {
		if (o instanceof FeatureOfSizeToleranceImpl)
			return this.reportFaces().compareTo(((FeatureOfSizeToleranceImpl)o).reportFaces());
		else
			throw new ClassCastException("FeatureOfSizeToleranceImpl expected.");
	}

}
