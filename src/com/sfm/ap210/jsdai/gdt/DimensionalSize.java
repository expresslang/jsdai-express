package com.sfm.ap210.jsdai.gdt;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.sfm.ap210.jsdai.FaceReference;
import com.sfm.ap210.jsdai.ToleranceLengthValue;
import com.sfm.ap210.jsdai.gdt.GeometricTolerance.DatumReference;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EDimensional_size;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EShape_aspect;

public class DimensionalSize implements ToleranceFeature {
	EShape_aspect e_sa = null;
	EDimensional_size e_ds;
	ToleranceLengthValue v;
	FeatureType t;
	SortedSet<FaceReference> faces;
	
	public DimensionalSize(ToleranceLengthValue v, EDimensional_size ds, EShape_aspect e_sa, FeatureType ft)
	{
		this.e_sa = e_sa;
		e_ds = ds;
		this.v = v;
		this.t = ft;
		faces = new TreeSet<FaceReference>();
	}
	
	public void addFace(FaceReference f)
	{
		faces.add(f);
	}
	
	public ToleranceLengthValue getValue()
	{
		return v;
	}
	
	public EDimensional_size getDimensionalSize()
	{
		return e_ds;
	}
	
	public EShape_aspect getShapeAspect()
	{
		return e_sa;
	}

	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public FeatureType getFeatureType() {
		return t;
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
		String s = "Dimensional size: ";
		s = s + this.v.toString();
		s = s + reportFaces();
		return s;
	}
}
