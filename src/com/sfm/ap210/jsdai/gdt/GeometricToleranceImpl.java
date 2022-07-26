package com.sfm.ap210.jsdai.gdt;

import java.util.Set;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.ECentre_of_symmetry;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EComposite_group_shape_aspect;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EShape_aspect;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.ESymmetric_shape_aspect;

import com.sfm.ap210.jsdai.Length;

public class GeometricToleranceImpl extends GeometricTolerance implements ToleranceFeature {
	EShape_aspect e_sa;
	FeatureType featType;
	
	public GeometricToleranceImpl(String n, Length l, EShape_aspect sa, ToleranceType t, FeatureType ft)
	{
		this(n, l, null, sa, t, ft);
	}
	
	public GeometricToleranceImpl(String n, Length l, ToleranceModifier m, EShape_aspect sa, ToleranceType t, FeatureType ft)
	{
		super(n, l, m, t);
		e_sa = sa;
		featType = ft;
	}

	public FeatureType getFeatureType() {
		return featType;
	}

	public EShape_aspect getShapeAspect() {
		return e_sa;
	}
	
	public int compareTo(Object o) {
		if (o instanceof GeometricToleranceImpl)
			return this.reportFaces().compareTo(((GeometricToleranceImpl)o).reportFaces());
		else
			throw new ClassCastException("GeometricToleranceImpl expected.");
	}

}
