package com.sfm.ap210.jsdai.param;

/**
 * prefix is optional (null prefix = unit multiplier)
 * @author James Stori
 *
 */
public class SiUnit implements Unit
{
	public enum SiBase {V, M, Hz, S, g, W, A, Ohm, degC, degK, F, H, J};
	
	public SiBase base;
	public SiPrefix prefix;

	
	public SiUnit(SiBase base)
	{
		this.base = base;
		this.prefix = null;
	}
	
	public SiUnit(SiBase base, SiPrefix prefix)
	{
		this.base = base;
		this.prefix = prefix;
	}
	
	public void setPrefix(SiPrefix prefix)
	{
		this.prefix = prefix;
	}
	
	public String toString()
	{
		if ((prefix != null) && (base != null))
			return prefix.toString()+base.toString();
		else if (base != null)
			return base.toString();
		else
			return "";
	}

	public SiPrefix getPrefix() {
		return prefix;
	}
	
	public int compareTo(Unit o2) {
		return this.toString().compareTo(o2.toString());
	}
	
	public static SiUnit kg() {
		return new SiUnit(SiBase.g, SiPrefix.kilo);
	}
}


