package com.sfm.ap210.jsdai.gdt;

import java.util.SortedSet;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EDimensional_location;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EDimensional_size;

import com.sfm.ap210.jsdai.FaceReference;
import com.sfm.ap210.jsdai.ToleranceLengthValue;
import com.sfm.ap210.jsdai.gdt.ToleranceFeature.FeatureType;

public class DimensionalLocation {
	EDimensional_location e_dl;
	ToleranceLengthValue tlv;
	ToleranceFeature feature1;
	ToleranceFeature feature2;
	
	public DimensionalLocation(EDimensional_location dl, ToleranceLengthValue v, ToleranceFeature f1, ToleranceFeature f2)
	{
		e_dl = dl;
		tlv = v;
		feature1 = f1;
		feature2 = f2;
	}
	
	public ToleranceLengthValue getValue()
	{
		return tlv;
	}
	
	public EDimensional_location getDimensionalLocation()
	{
		return e_dl;
	}
	
	ToleranceFeature getFeature1()
	{
		return feature1;
	}
	
	ToleranceFeature getFeature2()
	{
		return feature2;
	}
	
	public String toString()
	{
		String s = "Dimensional location: ";
		s = s + this.tlv.toString();
		s = s + "\n Feature 1: "+getFeature1().toString();
		s = s + "\n Feature 2: "+getFeature2().toString();
		return s;
	}
}
