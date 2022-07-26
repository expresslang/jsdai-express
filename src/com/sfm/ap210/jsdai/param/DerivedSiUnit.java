package com.sfm.ap210.jsdai.param;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import com.sfm.ap210.jsdai.param.SiUnit.SiBase;

/**
 * Implementation of a subset of possible derived units based on SiUnit.
 * This maps to the ARM AO Derived_unit (derived_unit). See also Derived_unit_element (derived_unit_element), 
 * Unit, and Value_with_unit from ISO/TS 10303-1054:2011-12 Value with unit.
 * See also Part 41.
 * @author James Stori
 *
 */
public class DerivedSiUnit implements Unit {
	List<SiUnitTerm> terms;
	public static class SiUnitTerm
	{
		public SiUnit siunit;
		public int exponent;
		
		/**
		 * @param b
		 * @param p null means no multiplier
		 * @param e
		 */
		public SiUnitTerm(SiUnit u, int e)
		{
			this.siunit = u;
			this.exponent = e;
		}
		
		public SiUnitTerm(SiBase b, int e)
		{
			this(new SiUnit(b), e);			
		}
		
		public SiUnitTerm(SiUnit u) {
			this(u, 1);
		}
		
		public SiUnitTerm(SiBase b) {
			this(b, 1);
		}
				
	}
	
	public List<SiUnitTerm> getTerms()
	{
		return terms;
	}
	
	public DerivedSiUnit()
	{
		terms = new Vector<SiUnitTerm>();
	}
	
	public DerivedSiUnit(SiBase b, int e)
	{
		terms = new Vector<SiUnitTerm>();
		terms.add(new SiUnitTerm(b, e));
	}
	
	public DerivedSiUnit(SiBase b, int e, SiPrefix p)
	{
		terms = new Vector<SiUnitTerm>();
		terms.add(new SiUnitTerm(new SiUnit(b, p), e));
	}
	
	public DerivedSiUnit(SiBase b1, int e1, SiBase b2, int e2)
	{
		terms = new Vector<SiUnitTerm>();
		terms.add(new SiUnitTerm(b1, e1));
		terms.add(new SiUnitTerm(b2, e2));
	}
	
	public DerivedSiUnit(SiBase b1, int e1, SiBase b2, int e2, SiBase b3, int e3)
	{
		terms = new Vector<SiUnitTerm>();
		terms.add(new SiUnitTerm(b1, e1));
		terms.add(new SiUnitTerm(b2, e2));
		terms.add(new SiUnitTerm(b3, e3));
	}
	
	public DerivedSiUnit(SiUnitTerm... terms) {
		this.terms = new Vector<SiUnitTerm>(Arrays.asList(terms));
	}
	
	public void addTerm(SiUnitTerm t)
	{
		terms.add(t);
	}
	
	public void addTerm(SiUnit u, int e)
	{
		terms.add(new SiUnitTerm(u,e));
	}
	
	public String toString()
	{
		String s = "";

		for (SiUnitTerm t : terms)
		{
			if (t.exponent != 1)
				s = s + t.siunit.toString() + t.exponent;
			else if (t.siunit != null)
				s = s + t.siunit.toString();
		}
		return s;
	}
	
	public int compareTo(Unit o2) {
		return this.toString().compareTo(o2.toString());
	}

	/**
	 * implementation only supports operation in limited cases
	 */
	public void setPrefix(SiPrefix p) {
		if (terms.isEmpty())
			throw new IllegalArgumentException("Unable to set prefix on empty compound unit.");
		
		SiUnitTerm firstterm = terms.get(0);
		if ((firstterm.siunit.prefix != null) || (Math.abs(firstterm.exponent) > 1))
			throw new IllegalArgumentException("Unable to set prefix: "+this.toString());
		
		firstterm.siunit.setPrefix(p);
	}
}
