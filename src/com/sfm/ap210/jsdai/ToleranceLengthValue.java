package com.sfm.ap210.jsdai;

import java.util.HashSet;
import java.util.Set;

public class ToleranceLengthValue {
	
	public enum Qualifier {
		MINIMUM ("minimum"), 
		MAXIMUM ("maximum"),
		NOMINAL ("average");
		String mimVal;
	
		Qualifier(String mimV)
		{
			mimVal = mimV;
		}
		public String toMim()
		{
			return mimVal;
		}
		public static Qualifier fromMim(String v)
		{
			for (Qualifier m : Qualifier.values())
			{
				if (v.equalsIgnoreCase(m.toMim()))
					return m;
			}
			return null;
		}
	}
	public enum DimensionModifier {
		BASIC ("theoretical"),
		REFERENCE ("auxiliary"),
		RADIUS("radius"),
		DIAMETER("diameter");
		
		String mimVal;
		DimensionModifier(String mimV)
		{
			mimVal = mimV;
		}
		public String toMim()
		{
			return mimVal;
		}
		public static DimensionModifier fromMim(String v)
		{
			for (DimensionModifier m : DimensionModifier.values())
			{
				if (v.equalsIgnoreCase(m.toMim()))
					return m;
			}
			return null;
		}
	}
	
	String n = "";
	DimensionModifier m;
	
	
	public class QualifiedValue
	{
		public Length l;
		public Qualifier q;
		
		QualifiedValue(Length l, Qualifier q)
		{
			this.l = l;
			this.q = q;
		}
		
		QualifiedValue(Length l)
		{
			this.l = l;
			this.q = null;
		}
		
		public boolean isQualified()
		{
			return (q != null);
		}
		
		public String toString()
		{
			if (this.isQualified())
				return l.value+" "+l.unit+" ("+q+")";
			else
				return l.value+" "+l.unit;
		}
	}
	
	Set<QualifiedValue> values;
	
	public ToleranceLengthValue(String n)
	{
		this.n = n;
		values = new HashSet<QualifiedValue>();
		m = null;
	}
	
	public ToleranceLengthValue(String n, Length l, Qualifier q)
	{
		this.n = n;
		values = new HashSet<QualifiedValue>();
		values.add(new QualifiedValue(l, q));
		m = null;
	}
	
	/**
	 * for a basic value
	 * @param l
	 * @param m
	 */
	public ToleranceLengthValue(String n, Length l, DimensionModifier m)
	{
		this.n = n;
		values = new HashSet<QualifiedValue>();
		values.add(new QualifiedValue(l));
		this.m = m;
	}
	
	public static ToleranceLengthValue BasicDimension(String n, Length l)
	{
		return new ToleranceLengthValue(n, l, DimensionModifier.BASIC); 
	}
	
	public static ToleranceLengthValue ReferenceDimension(String n, Length l)
	{
		return new ToleranceLengthValue(n, l, DimensionModifier.REFERENCE); 
	}
	
	public static ToleranceLengthValue DiameterDimension(String n, Length l)
	{
		return new ToleranceLengthValue(n, l, DimensionModifier.DIAMETER); 
	}
	
	public static ToleranceLengthValue RadiusDimension(String n, Length l)
	{
		return new ToleranceLengthValue(n, l, DimensionModifier.RADIUS); 
	}
	
	public ToleranceLengthValue(String n, double min, double nom, double max, Length.LengthUnit u)
	{
		this.n = n;
		values = new HashSet<QualifiedValue>();
		values.add(new QualifiedValue(new Length(min, u), Qualifier.MINIMUM));
		values.add(new QualifiedValue(new Length(nom, u), Qualifier.NOMINAL));
		values.add(new QualifiedValue(new Length(max, u), Qualifier.MAXIMUM));
		m = null;
	}
	
	public ToleranceLengthValue(String n, double min, double max, Length.LengthUnit u)
	{
		this.n = n;
		values = new HashSet<QualifiedValue>();
		values.add(new QualifiedValue(new Length(min, u), Qualifier.MINIMUM));
		values.add(new QualifiedValue(new Length(max, u), Qualifier.MAXIMUM));
		m = null;
	}
	
	public ToleranceLengthValue(String n, Length l, Qualifier q, DimensionModifier m)
	{
		this.n = n;
		values = new HashSet<QualifiedValue>();
		values.add(new QualifiedValue(l, q));
		this.m = m;
	}
	
	public void addQualifiedValue(Length l, Qualifier q)
	{
		values.add(new QualifiedValue(l, q));
	}
	
	public Set<QualifiedValue> getValues()
	{
		return values;
	}
	
	public boolean hasDimensionModifier()
	{
		return (m != null);
	}
	
	public DimensionModifier getDimensionModifier()
	{
		return m;
	}
	
	public void setDimensionModifier(DimensionModifier m)
	{
		this.m = m;
	}
	
	public String getName()
	{
		return n;
	}
	
	public String toString()
	{
		String s = "'"+this.getName()+"'";
		if (m != null)
			s = s+" ("+m.toString()+")";
		s = s+" : ";
		for (QualifiedValue q : values)
		{
			s = s+"[ "+q.toString()+" ]";
		}
		return s;
	}
}
