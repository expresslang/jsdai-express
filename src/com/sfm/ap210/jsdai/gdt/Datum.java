package com.sfm.ap210.jsdai.gdt;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EDatum;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EDatum_feature;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EShape_aspect;

import com.sfm.ap210.jsdai.FaceReference;
import com.sfm.ap210.jsdai.gdt.GeometricTolerance.DatumReference;

public class Datum implements ToleranceFeature {
	String id;
	FeatureType type;
	EDatum e_datum;
	EDatum_feature e_datum_feature;
	SortedSet<FaceReference> faces;

	public Datum(String i, FeatureType t, EDatum datum, EDatum_feature e_df)
	{
		id = i;
		type = t;
		e_datum = datum;
		e_datum_feature = e_df;
		faces = new TreeSet<FaceReference>();
	}
	
	public FeatureType getFeatureType() {
		// TODO Auto-generated method stub
		return null;
	}

	public EDatum getDatum()
	{
		return e_datum;
	}
	
	public EDatum_feature getDatumFeature()
	{
		return e_datum_feature;
	}
	
	public EShape_aspect getShapeAspect() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addFaceRef(FaceReference r)
	{
		faces.add(r);
	}
	public Set<FaceReference> getFaces()
	{
		return faces;
	}
	
	public String reportFaces()
	{
		String s="";
		for(FaceReference r : faces)
		{
			s = s+"\n\t"+r.toString();
		}
		return s;
	}
	
	public String toString()
	{
		String s = "Datum: '"+this.id+"'\n   "+type.toString();
		s = s + reportFaces();
		return s;
	}
	
	public int compareTo(Object o) {
		if (o instanceof Datum)
			return this.toString().compareTo(((Datum)o).toString());
		else
			throw new ClassCastException("Datum expected.");
	}
}
