package com.sfm.ap210.jsdai.gdt;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

import com.sfm.ap210.jsdai.FaceReference;
import com.sfm.ap210.jsdai.Length;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.*;
import jsdai.lang.SdaiException;

public abstract class GeometricTolerance implements ToleranceFeature {
	String name;
	//EShape_aspect e_sa;
	//EAdvanced_face e_af;
	Length value;
	List<Datum> datums; 
	//Set<ShapeAspectReference> faces;
	ToleranceModifier mod = null;
	List<DatumReference> datumRefs = new Vector<DatumReference>();
	ToleranceType tolType = null;
	SortedSet<FaceReference> faces;
	
	public enum ToleranceType {
		FLATNESS, PARALLELISM, POSITION, STRAIGHTNESS, CYLINDRICITY, SURFACEPROFILE, ROUNDNESS
	}
	
	public enum ToleranceModifier {
		MMR (ELimit_condition.LEAST_MATERIAL_CONDITION), 
		LMR (ELimit_condition.MAXIMUM_MATERIAL_CONDITION),
		ROFS (ELimit_condition.REGARDLESS_OF_FEATURE_SIZE);
		int mimVal;
	
		ToleranceModifier(int mimV)
		{
			mimVal = mimV;
		}
		public int toMim()
		{
			return mimVal;
		}
		public static ToleranceModifier fromMim(int v)
		{
			
			for (ToleranceModifier m : ToleranceModifier.values())
			{
				if (v == (m.toMim()))
					return m;
			}
			return null;
		}
	}
	
	public enum DatumReferenceModifier {
		MMR (ESimple_datum_reference_modifier.MAXIMUM_MATERIAL_REQUIREMENT), 
		LMR (ESimple_datum_reference_modifier.LEAST_MATERIAL_REQUIREMENT);
		
		int mimVal;
	
		DatumReferenceModifier(int mimV)
		{
			mimVal = mimV;
		}
		public int toMim()
		{
			return mimVal;
		}
		public static DatumReferenceModifier fromMim(int v)
		{
			for (DatumReferenceModifier m : DatumReferenceModifier.values())
			{
				if (v == (m.toMim()))
					return m;
			}
			return null;
		}
	}

	public class DatumReference {
		EDatum e_d;
		String datumID;
		DatumReferenceModifier mod = null;
		
		public DatumReference(EDatum d) throws SdaiException
		{
			this(d, null);
		}
		
		public DatumReference(EDatum datum, DatumReferenceModifier m) throws SdaiException
		{
			this.e_d = datum;
			mod = m;
			datumID = datum.getIdentification(null);
		}
		
		public EDatum getDatum()
		{
			return e_d;
		}
		
		public String getDatumID()
		{
			return datumID;
		}
		
		public DatumReferenceModifier getModifier()
		{
			return mod;
		}
		
		public boolean hasModifier()
		{
			return (mod != null);
		}
		
		public String toString()
		{
			String s = this.getDatumID();
			if (this.hasModifier())
				s = s + "("+mod.toString()+")";
			return s;
		}
		
	}
	
	public GeometricTolerance(String n, Length v, ToleranceType t)
	{
		name = n;
		value = v;
		tolType = t;
		faces = new TreeSet<FaceReference>();
	}
	
	public GeometricTolerance(String n, Length v, ToleranceModifier m, ToleranceType t)
	{
		this(n, v, t);
		this.mod = m;
	}
	/*
	public GeometricTolerance(String n, Length v, Set<ShapeAspectReference> f)
	{
		name = n;
		value = v;
		faces = f;
	}
	
	public GeometricTolerance(String n, Length v, ToleranceModifier m, Set<ShapeAspectReference> f)
	{
		name = n;
		value = v;
		faces = f;
		mod = m;
	}
	*/
	public String getName()
	{
		return name;
	}
	
	public Length getLength()
	{
		return value;
	}
	
	public void AddDatum(EDatum d) throws SdaiException
	{
		DatumReference r = new DatumReference(d);
		datumRefs.add(r);
	}
	
	public void AddDatum(EDatum d, DatumReferenceModifier m) throws SdaiException
	{
		DatumReference r = new DatumReference(d, m);
		datumRefs.add(r);
	}
	
	public ToleranceType getType()
	{
		return tolType;
	}
	
	public boolean hasDatumRefs()
	{
		return (datumRefs.size()>0);
	}
	
	public List<DatumReference> getDatums()
	{
		return datumRefs;
	}
	
	void setModifier(ToleranceModifier m)
	{
		mod = m;
	}
	
	public boolean hasModifier()
	{
		return (mod != null);
	}
	
	public ToleranceModifier getModifier()
	{
		return mod;
	}
	
	public String toString()
	{
		String s = "Tolerance ("+tolType.toString()+"): '"+this.name+"' ";
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
		s = s + reportFaces();
		return s;
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
		String s="\n   "+getFeatureType().toString();
		for(FaceReference r : faces)
		{
			s = s+"\n\t"+r.toString();
		}
		return s;
	}
}
