package com.sfm.spice;

import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import com.sfm.ap210.jsdai.param.DerivedSiUnit;
import com.sfm.ap210.jsdai.param.SiUnit;
import com.sfm.ap210.jsdai.param.SiUnit.SiBase;
import com.sfm.ap210.jsdai.param.Unit;

public class SpiceModelParamSet {
	String modelName;
	List<SpiceParam> modelParams;
	
	public interface ModelParamDef
	{
		public String getElementName();
		public String getDescription();
	    public Unit getUnit();
	}
	
	public enum LTSpiceIparams implements ModelParamDef
	{
	    AC ("AC amplitude", SiBase.A),
	    Ioff ("Initial value", SiBase.A),
	    Ion ("Pulsed value", SiBase.A),
	    Tdelay ("Delay", SiBase.S),
	    Tr ("Rise time", SiBase.S),
	    Tf ("Fall time", SiBase.S),
	    Ton ("On time", SiBase.S),
	    Tperiod ("Period", SiBase.S),
	    Ncycles ("Number of cycles");
	    
	    private final String LTmodelName = "L";
	    private final String description;
	    private final Unit unit;
	    
	    LTSpiceIparams(String d, SiBase b) {
	        this.description = d;
	        this.unit = new SiUnit(b);
	    }
	    
	    LTSpiceIparams(String d) {
	        this.description = d;
	        this.unit = null;
	    }
	    
	    public String getElementName()   { return LTmodelName; }
	    public String getDescription()   { return description; }
	    public Unit getUnit() { return unit; }
	    
	    public static ModelParamDef fromText(String t)
		{
			for (LTSpiceIparams p : LTSpiceIparams.values())
			{
				if (t.toUpperCase().equals(p.toString().toUpperCase()))
					return p;
			}
			return null;
		}
	}
	
	public enum LTSpiceLparams implements ModelParamDef
	{
	    Rser ("Equivalent series resistance", SiBase.Ohm),
	    Rpar ("Equivalent parallel resistance", SiBase.Ohm),
	    Cpar ("Equivalent parallel capacitance", SiBase.F),
	    m ("Number of parallel units"),
	    ic ("Initial current", SiBase.A),
	    tc1 ("Linear inductance temperature coeff."),
	    Tc1 ("Quadratic inductance temperature coeff."),
	    temp ("Instance temperature", SiBase.degC);
	    
	    private final String LTmodelName = "L";
	    private final String description;
	    private final Unit unit;
	    
	    LTSpiceLparams(String d, SiBase b) {
	        this.description = d;
	        this.unit = new SiUnit(b);
	    }
	    
	    LTSpiceLparams(String d) {
	        this.description = d;
	        this.unit = null;
	    }
	    
	    public String getElementName()   { return LTmodelName; }
	    public String getDescription()   { return description; }
	    public Unit getUnit() { return unit; }
	    
	    public static ModelParamDef fromText(String t)
		{
			for (LTSpiceLparams p : LTSpiceLparams.values())
			{
				if (t.toUpperCase().equals(p.toString().toUpperCase()))
					return p;
			}
			return null;
		}
	}
	
	public enum LTSpiceQparams implements ModelParamDef
	{
	    Is ("Transport saturation current", SiBase.A),
	    Bf ("Ideal maximum forward beta"),
	    Cjc ("B-C zero-bias depletion capacitance", SiBase.F),
	    Cje ("B-E zero-bias depletion capacitance", SiBase.F),
	    Rb ("Zero-bias base resistance", SiBase.Ohm);
	    
	    private final String LTmodelName = "Q";
	    private final String description;
	    private final Unit unit;
	    
	    LTSpiceQparams(String d, SiBase b) {
	        this.description = d;
	        this.unit = new SiUnit(b);
	    }
	    
	    LTSpiceQparams(String d) {
	        this.description = d;
	        this.unit = null;
	    }
	    
	    public String getElementName()   { return LTmodelName; }
	    public String getDescription()   { return description; }
	    public Unit getUnit() { return unit; }
	    
	    public static ModelParamDef fromText(String t)
		{
			for (LTSpiceQparams p : LTSpiceQparams.values())
			{
				if (t.toUpperCase().equals(p.toString().toUpperCase()))
					return p;
			}
			return null;
		}
	}
	
	public enum LTSpiceJparams implements ModelParamDef
	{
	    Vto ("Threshold voltage", SiBase.V),
	    Beta ("Tansconductance parameter", new DerivedSiUnit(SiBase.A, 1, SiBase.V, -2)),
	    Lambda ("Channel-length modulation parameter", new DerivedSiUnit(SiBase.V, -1)),
	    Rd ("Drain ohmic resistance", SiBase.Ohm),
	    Rs ("Source ohmic resistance", SiBase.Ohm),
	    Cgs ("Zero-bias G-S junction capacitance", SiBase.F),
	    Cgd ("Zero-bias G-D junction capacitance", SiBase.F),
	    Pb ("Gate junction potential", SiBase.V),
	    Is ("Gate junction saturation current", SiBase.A),
	    B ("Doping tail parameter"),
	    Kf ("Flicker noise coefficient"),
	    AF ("Flicker noise exponent"),
	    Fc ("Coefficient for forward-depletion capacitance"),
	    Tnom ("Parameter measurement temperature", SiBase.degC),
	    BetaTce ("Transconductance parameter exponential temperature coefficient", new DerivedSiUnit(SiBase.degC, -1)),
	    VtoTc ("Threshold voltage temperature coefficient", new DerivedSiUnit(SiBase.V, 1, SiBase.degC, -1)),
	    N ("Gate junction emission coefficient"),
	    Isr ("Gate junction recombination current parameter", SiBase.A),
	    Nr ("Emission coefficient for Isr"),
	    alpha ("Ionization coefficient", new DerivedSiUnit(SiBase.V, -1)),
	    Vk ("Ionization knee voltage", SiBase.V),
	    Xti ("Saturation current temperature coefficient");
	    
	    private final String LTmodelName = "J";
	    private final String description;
	    private final Unit unit;
	    
	    /**
	     * no unit will be treated as double param
	     * @param d
	     */
	    LTSpiceJparams(String d) {
	        this.description = d;
	        this.unit = null;
	    }
	    
	    LTSpiceJparams(String d, SiBase b) {
	        this.description = d;
	        this.unit = new SiUnit(b);
	    }
	    
	    LTSpiceJparams(String d, DerivedSiUnit u) {
	        this.description = d;
	        this.unit = u;
	    }
	    
	    public String getElementName()   { return LTmodelName; }
	    public String getDescription()   { return description; }
	    public Unit getUnit() { return unit; }
	    
	    public static ModelParamDef fromText(String t)
		{
			for (LTSpiceJparams p : LTSpiceJparams.values())
			{
				if (t.toUpperCase().equals(p.toString().toUpperCase()))
					return p;
			}
			return null;
		}
	}
	
	public enum LTSpiceDparams implements ModelParamDef
	{
	    Ron ("Resistance in forward conduction", SiBase.Ohm),
	    Roff ("Resistance when off", SiBase.Ohm),
	    Vfwd ("Forward threshold voltage to enter conduction", SiBase.V),
	    Vrev ("Reverse breakdown voltage", SiBase.V),
	    Rrev ("Breakdown impedance", SiBase.Ohm),
	    Ilimit ("Forward current limit", SiBase.A),
	    Revilimit ("Reverse current limit", SiBase.A),
	    Epsilon ("Width of quadratic region", SiBase.V),
	    Revepsilon ("Width of reverse quadratic region", SiBase.V),
	    Is ("Saturation current", SiBase.A),
	    Rs ("Ohmic resistance", SiBase.Ohm),
	    N ("Emission coefficient"),
	    M ("Grading coeffficient"),
	    Tt ("Transit-time", SiBase.S),
	    Cjo ("Zero-bias junction cap.", SiBase.F),
	    Iave ("Ave current rating", SiBase.A),
	    Vpk ("Peak voltage rating", SiBase.V);
	    //@TODO no current support for string parameters - param values all parsed as float
	    //type ("Classification", null),
	    //mfg ("Manufacturer", null);
	    
	    
	    private final String LTmodelName = "D";
	    private final String description;
	    private final Unit unit;
	    
	    LTSpiceDparams(String d, SiBase b) {
	        this.description = d;
	        this.unit = new SiUnit(b);
	    }
	    
	    LTSpiceDparams(String d) {
	        this.description = d;
	        this.unit = null;
	    }
	    
	    public String getElementName()   { return LTmodelName; }
	    public String getDescription()   { return description; }
	    public Unit getUnit() { return unit; }
	    
	    public static ModelParamDef fromText(String t)
		{
			for (LTSpiceDparams p : LTSpiceDparams.values())
			{
				if (t.toUpperCase().equals(p.toString().toUpperCase()))
					return p;
			}
			return null;
		}
	}

	ModelParamDef getModelParamDef(String modelName, String paramName)
	{
		if (modelName.equals("D"))
			return LTSpiceDparams.fromText(paramName);
		if (modelName.equals("I"))
			return LTSpiceIparams.fromText(paramName);
		if (modelName.equals("L"))
			return LTSpiceLparams.fromText(paramName);
		if (modelName.equals("NPN"))
			return LTSpiceQparams.fromText(paramName);
		if (modelName.equals("PNP"))
			return LTSpiceQparams.fromText(paramName);
		if (modelName.equals("NJF"))
			return LTSpiceJparams.fromText(paramName);
		if (modelName.equals("PJF"))
			return LTSpiceJparams.fromText(paramName);
		return null;
	}
	
	
	public SpiceModelParamSet(String name)
	{
		this.modelName = name;
		modelParams = new Vector<SpiceParam>();
	}
	
	public void addParam(SpiceParam p)
	{
		modelParams.add(p);
	}
	
	/**
	 * given text of form: "Bf=150 Cjc=3p Cje=3p Rb=10"
	 * parses into name/value pairs
	 * @param spiceText
	 */
	public void addFromSpiceText(String spiceText)
	{
		Scanner paramscanner = new Scanner(spiceText);
		while (paramscanner.hasNext())
		{
			String tupleString = paramscanner.next();
			String[] tuple = tupleString.split("=");
			if (tuple.length < 2)
			{
				log("Unexpected substring during parameter parsing (ignoring): "+tupleString);
				continue;
			}
			String name = tuple[0];
			String textValue = tuple[1];
			ModelParamDef p = getModelParamDef(modelName, name);
			if (p != null)
			{
				SpiceParam sp = SpiceParam.parseValue(name, p.getDescription(), p.getUnit(), textValue);
				modelParams.add(sp);
			}
			else
				log("Unsupported parameter: "+name + " in model: "+modelName);
		}
	}
	
	public String toString()
	{
		String s = "";
		for (SpiceParam sp : modelParams)
		{
			s = s + "\n\t "+sp.toString();
		}
		return s;
	}
	
	public boolean isEmpty()
	{
		return modelParams.isEmpty();
	}
	
	public String toNetFormatString()
	{
		String s = "";
		for (SpiceParam p : modelParams)
		{
			s = s + p.name+"="+p.toValueNetFormatString()+" ";
		}
		return s;
	}
	
	private static void log(Object aObject)
	{
	    System.out.println(String.valueOf(aObject));
	}
}
