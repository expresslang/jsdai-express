package com.sfm.ap210.jsdai.gdt;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EShape_aspect;

import com.sfm.ap210.jsdai.FaceReference;

public class ToleranceFeatureImpl implements ToleranceFeature  {
	SortedSet<FaceReference> faces;
	FeatureType featureType;
	EShape_aspect e_sa;
	
	public ToleranceFeatureImpl(FeatureType t, EShape_aspect sa)
	{
		featureType = t;
		e_sa = sa;
		faces = new TreeSet<FaceReference>();
	}
	
	public ToleranceFeatureImpl(FeatureType t, EShape_aspect sa, Set<FaceReference> faceRefs)
	{
		this(t, sa);
		faces.addAll(faceRefs);
	}
	
	public FeatureType getFeatureType() {
		return featureType;
	}

	public EShape_aspect getShapeAspect() {
		return e_sa;
	}

	public Set<FaceReference> getFaces() {
		return faces;
	}

	public String reportFaces()
	{
		String s="\n   "+getFeatureType().toString();
		for(FaceReference r : faces)
		{
			s = s+"\n\t"+r.toString();
		}
		return s;
	}
	
	public String toString()
	{
		return reportFaces();
	}
	
	public int compareTo(Object o) {
		if (o instanceof ToleranceFeatureImpl)
			return this.reportFaces().compareTo(((ToleranceFeatureImpl)o).reportFaces());
		else
			throw new ClassCastException("CompositeElementFeature expected.");
	}
}
