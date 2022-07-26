package com.sfm.ap210.jsdai.gdt;

import java.util.Set;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.ECentre_of_symmetry;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EComposite_group_shape_aspect;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EShape_aspect;

import com.sfm.ap210.jsdai.FaceReference;
import com.sfm.ap210.jsdai.Length;

public class CenterPlaneToleranceImpl extends GeometricTolerance implements CenterPlaneTolerance {
	ECentre_of_symmetry e_cos = null;
	
	
	public CenterPlaneToleranceImpl(String n, Length l, ECentre_of_symmetry cos, ToleranceType t)
	{
		this(n, l, null, cos, t);
	}
	
	public CenterPlaneToleranceImpl(String n, Length l, ToleranceModifier m, ECentre_of_symmetry cos, ToleranceType t)
	{
		super(n, l, m, t);
		e_cos = cos;
	}
	
	public ECentre_of_symmetry getCentreOfSymmetry()
	{
		return e_cos;
	}

	public FeatureType getFeatureType() {
		return FeatureType.CENTERPLANE;
	}

	public EShape_aspect getShapeAspect() {
		return e_cos;
	}
	
	public int compareTo(Object o) {
		if (o instanceof CenterPlaneToleranceImpl)
			return this.reportFaces().compareTo(((CenterPlaneToleranceImpl)o).reportFaces());
		else
			throw new ClassCastException("CenterPlaneToleranceImpl expected.");
	}

	public Set<FaceReference> getFaces() {
		return faces;
	}
	
}
