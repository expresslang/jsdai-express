package com.sfm.ap210.jsdai.gdt;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.sfm.ap210.jsdai.FaceReference;
import com.sfm.ap210.jsdai.ToleranceLengthValue;
import com.sfm.ap210.jsdai.gdt.GeometricTolerance.DatumReference;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EComposite_group_shape_aspect;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EDimensional_size;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EShape_aspect;

public class CompositeDimensionalSize extends DimensionalSize implements CompositeTolerance {
	EComposite_group_shape_aspect e_cgsa = null;
	EDimensional_size e_ds;
	Set<ToleranceFeatureImpl> features;
	
	public CompositeDimensionalSize(ToleranceLengthValue v, EDimensional_size ds, EComposite_group_shape_aspect e_sa, FeatureType ft)
	{
		super(v, ds, e_sa, ft);
		features = new TreeSet<ToleranceFeatureImpl>();
	}
	
	public void addFeature(ToleranceFeatureImpl f)
	{
		if (f.getFeatureType() == t)
			features.add(f);
		else
			throw new IllegalArgumentException("Inconsistent feature types disallowed in composite dimensional size: "+e_sa);
	}
	
	public void addFeatures(Set<ToleranceFeatureImpl> addedFeatures)
	{
		for (ToleranceFeatureImpl f : addedFeatures)
			{
			if (f.getFeatureType() == t)
				features.add(f);
			else
				throw new IllegalArgumentException("Inconsistent feature types disallowed in composite dimensional size: "+e_sa);
			}
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
		return e_cgsa;
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
	
	public Set<ToleranceFeatureImpl> getFeatures() {
		return features;
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
		String s = "Composite Dimensional size: ";
		s = s + this.v.toString();

		int featureCount = 1;
		for(ToleranceFeature feature : features)
		{
			//s=s+"\n("+featureCount+++")";
			s = s + feature.toString();
		}
		return s;
	}

	public EComposite_group_shape_aspect getGroup() {
		return e_cgsa;
	}
}
