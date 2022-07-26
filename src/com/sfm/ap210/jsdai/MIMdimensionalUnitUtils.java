package com.sfm.ap210.jsdai;

import jsdai.SMeasure_schema.EArea_measure;
import jsdai.SMeasure_schema.EArea_measure_with_unit;
import jsdai.SMeasure_schema.EConversion_based_unit;
import jsdai.SMeasure_schema.EDerived_unit;
import jsdai.SMeasure_schema.EDerived_unit_element;
import jsdai.SMeasure_schema.ELength_measure;
import jsdai.SMeasure_schema.ELength_measure_with_unit;
import jsdai.SMeasure_schema.ELength_unit;
import jsdai.SMeasure_schema.EMeasure_with_unit;
import jsdai.SMeasure_schema.ESi_prefix;
import jsdai.SMeasure_schema.ESi_unit;
import jsdai.SMeasure_schema.ESi_unit_name;
import jsdai.lang.SdaiException;

/**
 * Some basic utilities to demonstrate the extraction of length and area physical measures.
 * Supports unit types of both conversion_based_unit and si_unit.
 * Measure quantities are converted to and returned as either mm or sq mm.
 * 
 * @author James Stori, SFM Technology, Inc.
 * @version 1.3
 */
public class MIMdimensionalUnitUtils {

	private MIMops ops;
	
	public MIMdimensionalUnitUtils(MIMops mimOps) {
		this.ops = mimOps;
	}

	/**
	 * Evaluates a given length_measure_with_unit and returns the measure value as a scalar expressed in mm.
	 * Supports unit types of both conversion_based_unit and si_unit. 
	 * @param e_lmwu the given length_measure_with_unit
	 * @return a scalar quantity representing the value of the given length_measure_with_unit expressed as a double in mm 
	 * @throws SdaiException
	 */
	public double lengthMeasureWithUnitInMM(ELength_measure_with_unit e_lmwu) throws SdaiException
	{
		double measure_value = e_lmwu.getValue_component(null, (ELength_measure) null);
		ELength_unit lengthUnit = (ELength_unit) e_lmwu.getUnit_component(null);
		double conversionFactor = getConversionFactorToMM(lengthUnit);
		return measure_value*conversionFactor;
	}
	
	/**
	 * Evaluates a given area_measure_with_unit and returns the measure value as a scalar quantity expressed in square mm.
	 * Supports unit types of both conversion_based_unit and si_unit. 
	 * @param e_amwu the given area_measure_with_unit
	 * @return a scalar quantity representing the value of the given area_measure_with_unit expressed as a double in sq. mm. 
	 * @throws SdaiException
	 */
	public double areaMeasureWithUnitInSqMM(EArea_measure_with_unit e_amwu) throws SdaiException
	{
		EDerived_unit e_du = (EDerived_unit)e_amwu.getUnit_component(null);
		EDerived_unit_element e_due = ops.due_referencedBy_du(e_du);
		ELength_unit e_lu = ops.lu_referencedBy_due(e_due);
		return areaMeasureWithUnitToSqMM(e_amwu, e_lu);
	}
	
	/**
	 * Supporting method to evaluate a given area_measure_with_unit and return the measure value as a scalar quantity
	 * expressed in square mm. Requires that both the given area_measure_with_unit and associated underlying length_unit
	 * be provided.
	 * @param e_amwu the given area_measure_with_unit
	 * @param e_lu the underlying length_unit associated with the area_measure_with_unit
	 * @return a scalar quantity representing the value of the given area_measure_with_unit expressed as a double in sq. mm.
	 * @throws SdaiException
	 */
	double areaMeasureWithUnitToSqMM(EArea_measure_with_unit e_amwu, ELength_unit e_lu) throws SdaiException
	{
		double measure_value = e_amwu.getValue_component(null, (EArea_measure) null);
		double scalarConversionFactor = getConversionFactorToMM(e_lu);
		return measure_value*scalarConversionFactor*scalarConversionFactor;
	}
	
	/**
	 * Returns a scalar conversion factor from the given length_unit to mm. 
	 * Supports unit types of both conversion_based_unit and si_unit.
	 * 
	 * @param e_lu the given length_unit
	 * @return scalar conversion factor from the given length_unit to mm.
	 * @throws SdaiException
	 */
	public double getConversionFactorToMM(ELength_unit e_lu) throws SdaiException
	{
		if (e_lu.isKindOf(ESi_unit.class))
			return getConversionFactorToMM((ESi_unit) e_lu);
		else if (e_lu.isKindOf(EConversion_based_unit.class))
			return getConversionFactorToMM((EConversion_based_unit) e_lu);
		else
			throw new IllegalArgumentException("No conversion factor available for: "+e_lu);
	}
	
	/**
	 * Returns a scalar conversion factor from the given si_unit to mm for the limited case of linear SI measures
	 * (meter with associated prefix).
	 * In all other cases (currently unsupported by implementation) throws an IllegalArgumentException
	 *  
	 * @param e_siu the given si_unit
	 * @return scalar conversion factor from the given si_unit to mm.
	 * @throws SdaiException
	 */
	double getConversionFactorToMM(ESi_unit e_siu) throws SdaiException
	{
		if (e_siu.getName(null)==ESi_unit_name.METRE)
		{
			if (!(e_siu.testPrefix(null)))
					return 1000.0;
			
			int prefix = e_siu.getPrefix(null);
			if (prefix==ESi_prefix.NANO)
				return 1E-6;
			else if (prefix==ESi_prefix.MICRO)
				return 1E-3;
			else if (prefix==ESi_prefix.MILLI)
				return 1.0;
			else if (prefix==ESi_prefix.CENTI)
				return 10.0;
			
			throw new IllegalArgumentException("No conversion factor available for: "+e_siu);
		}
		else
		{
			throw new IllegalArgumentException("No conversion factor available for: "+e_siu);
		}	
	}
	
	/**
	 * Returns a scalar conversion factor from the given conversion_based_unit to mm.
	 *  
	 * @param e_cbu the given conversion_based_unit
	 * @return scalar conversion factor from the given conversion_based_unit to mm.
	 * @throws SdaiException
	 */
	double getConversionFactorToMM(EConversion_based_unit e_cbu) throws SdaiException
	{
		EMeasure_with_unit e_mwu = e_cbu.getConversion_factor(null);
		double value = e_mwu.getValue_component(null, (ELength_measure)null);
		ELength_unit e_lu = (ELength_unit) e_mwu.getUnit_component(null);
		return value*getConversionFactorToMM(e_lu);
	}
}
