package com.sfm.spice;

import java.text.NumberFormat;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.sfm.ap210.jsdai.param.DerivedSiUnit;
import com.sfm.ap210.jsdai.param.SiUnit;
import com.sfm.ap210.jsdai.param.Unit;
import com.sfm.ap210.jsdai.param.Unit.SiPrefix;

public class SpiceParam {
	String name;
	String description;
	Unit unit;
	double value;
	static NumberFormat floatFormat = NumberFormat.getInstance();
	
	public SpiceParam(String n, String d, double v, Unit u)
	{
		floatFormat.setMaximumFractionDigits(6);
		name = n;
		description = d;
		unit = u;
		value = v;
	}
	
	//@TODO - need to separate on trailing multiple characters
	// i.e. 1mH (interpreted as 1m), 1Hz (interpreted as 1), 1Volt (interpreted as 1) 1e-4Volt (interpreted as 1e-4)
	public static SpiceParam parseValue(String n, String d, Unit unit, String s)
	  {
		//log("parseValue: "+s);
		
		Pattern pa = Pattern.compile("[a-zA-Zµ&&[^e]]+$");  // one or more letters (excluding e) at the end of the input
		//Matcher m = p.matcher(s);
		Scanner sc = new Scanner(s);
		sc.useDelimiter(pa);
		
		
		//s.replaceFirst(regex, replacement)
		//log("suffix: "+endchars);
		if (!(sc.hasNext()))
			{
				throw new NumberFormatException("Unable to find value in given parameter string: "+s);
			}
		
		String valuesubstring = sc.next();
		
		Scanner sc2 = new Scanner(s);
		sc2.useDelimiter(pa);
		
		String endchars = sc2.findInLine(pa);
		
		//log("value: "+valuesubstring);
		//log("suffix: "+endchars);
		  SiPrefix p = null;
		  char last = ' ';
		  if (endchars != null)
		  {
			  endchars = endchars.toLowerCase();
			  if (endchars.startsWith("meg"))
				  p = SiPrefix.mega;
			  else if (endchars.startsWith("mil"))
				  throw new NumberFormatException("Mil units are unsupported: "+s);
			  else
			  {
				  last = endchars.charAt(0);
				  switch (last)
				  {
				  case 't' : p = SiPrefix.tera; break;
				  case 'g' : p = SiPrefix.giga; break;
				  case 'k' : p = SiPrefix.kilo; break;
				  case 'm' : p = SiPrefix.milli; break;
				  case 'u' : p = SiPrefix.micro; break;
				  case 'µ' : p = SiPrefix.micro; break;
				  case 'n' : p = SiPrefix.nano; break;
				  case 'p' : p = SiPrefix.pico; break;
				  case 'f' : p = SiPrefix.femto; break;
				  default: throw new NumberFormatException("Unrecongized multiplier: "+last+" in: "+s);
				  } 
			  }
		  }
		  Float f = new Float(valuesubstring);
		  double v = f.doubleValue();
		  if ((unit == null) && (p != null))
			  throw new IllegalArgumentException("Unsupported prefix for null unit.");
		  if (unit != null)
			  unit.setPrefix(p);
		  return new SpiceParam(n, d, v, unit);
	  }
	
	public String toString()
	{
		if (unit != null)
			return name+"="+floatFormat.format(value)+" "+unit.toString() + " ("+description+")";
		else
			return name+"="+floatFormat.format(value)+" ("+description+")";
	}
	
	public String spiceMultiplierForPrefix(SiPrefix prefix)
	{
		if (prefix == null)
			return "";
		
		if (prefix == SiPrefix.tera) return "t";
		else if (prefix == SiPrefix.giga) return "g";
		else if (prefix == SiPrefix.kilo) return "k";
		else if (prefix == SiPrefix.milli) return "m";
		else if (prefix == SiPrefix.micro) return "µ";
		else if (prefix == SiPrefix.nano) return "n";
		else if (prefix == SiPrefix.pico) return "p";
		else if (prefix == SiPrefix.femto) return "f";
		else
			throw new NumberFormatException("Unrecongized multiplier: "+prefix);

	}
	public String spiceMultiplier(Unit u)
	{
		SiPrefix prefix = null;
		if (u instanceof SiUnit)
			prefix = ((SiUnit)u).prefix;
		else if (u instanceof DerivedSiUnit)
			prefix = ((DerivedSiUnit)u).getTerms().iterator().next().siunit.getPrefix();
		return spiceMultiplierForPrefix(prefix);
	}
	
	public String toValueNetFormatString()
	{
		
		return floatFormat.format(value)+spiceMultiplier(unit);
	}
	
	private static void log(Object aObject)
	{
	    System.out.println(String.valueOf(aObject));
	}
}
