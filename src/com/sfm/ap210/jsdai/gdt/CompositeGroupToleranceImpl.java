package com.sfm.ap210.jsdai.gdt;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.ECentre_of_symmetry;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EComposite_group_shape_aspect;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EShape_aspect;

import com.sfm.ap210.jsdai.Length;
import com.sfm.ap210.jsdai.gdt.GeometricTolerance.DatumReference;
import com.sfm.ap210.jsdai.gdt.GeometricTolerance.ToleranceType;

public class CompositeGroupToleranceImpl extends GeometricTolerance implements CompositeTolerance{
	EComposite_group_shape_aspect e_cgsa = null;
	// composite tolerance must apply to 
	FeatureType featureType;
	Set<ToleranceFeatureImpl> features;
	
	public CompositeGroupToleranceImpl(String n, Length l, EComposite_group_shape_aspect cgsa, ToleranceType t, FeatureType f)
	{
		this(n, l, null, cgsa, t, f);
	}
	
	public CompositeGroupToleranceImpl(String n, Length l, ToleranceModifier m, EComposite_group_shape_aspect cgsa, ToleranceType t, FeatureType f)
	{
		super(n, l, m, t);
		featureType = f;
		e_cgsa = cgsa;
		features = new TreeSet<ToleranceFeatureImpl>();
	}

	public void addFeature(ToleranceFeatureImpl f)
	{
		if (f.getFeatureType() == featureType)
			features.add(f);
		else
			throw new IllegalArgumentException("Inconsistent feature types disallowed in composite tolerance: "+f);
	}
	
	public void addFeatures(Set<ToleranceFeatureImpl> addedFeatures)
	{
		for (ToleranceFeatureImpl f : addedFeatures)
			{
			if (f.getFeatureType() == featureType)
				features.add(f);
			else
				throw new IllegalArgumentException("Inconsistent feature types disallowed in composite tolerance: "+f);
			}
	}
	
	public FeatureType getFeatureType() {
		return featureType;
	}

	public EShape_aspect getShapeAspect() {
		return e_cgsa;
	}
	
	public Set<ToleranceFeatureImpl> getFeatures()
	{
		return features;
	}

	public EComposite_group_shape_aspect getGroup() {
		return e_cgsa;
	}
	
	public String toString()
	{
		String s = "Composite Tolerance ("+tolType.toString()+"): '"+this.name+"' ";
		s = s + this.value.toString();
		if (this.hasDatumRefs())
		{
			s = s + "\n Datum System [";
			for (DatumReference ref : this.datumRefs)
			{
				s = s + ref.toString()+" ";
			}
			s = s+"]";
		}
		
		int featureCount = 1;
		for(ToleranceFeature feature : features)
		{
			//s=s+"\n("+featureCount+++")";
			s = s + feature.toString();
		}
		return s;
	}
	
	public int compareTo(Object o) {
		if (o instanceof CompositeGroupToleranceImpl)
			return this.toString().compareTo(((CompositeGroupToleranceImpl)o).toString());
		else
			throw new ClassCastException("CompositeGroupToleranceImpl expected.");
	}

	
}
