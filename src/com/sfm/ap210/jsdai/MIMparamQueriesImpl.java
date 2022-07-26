package com.sfm.ap210.jsdai;

import java.util.HashSet;
import java.util.Set;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.*;
import jsdai.lang.SdaiException;
import jsdai.lang.SdaiIterator;

import com.sfm.ap210.jsdai.param.BooleanParamImpl;
import com.sfm.ap210.jsdai.param.IntegerParamImpl;
import com.sfm.ap210.jsdai.param.MeasureParamImpl;
import com.sfm.ap210.jsdai.param.Param;
import com.sfm.ap210.jsdai.param.StringParamImpl;
import com.sfm.ap210.jsdai.param.MeasureParam.Units;

/**
 * An implementation of the MIMparamQueries interface.
 * @author James Stori, SFM Technology, Inc.
 * @version 1.3
 */
public class MIMparamQueriesImpl implements MIMparamQueries {
	
	private MIMops ops;
	private MIMqueries mimQ;
	private MIMdimensionalUnitUtils duc;
	
	/**
	  
	 */
	public MIMparamQueriesImpl(MIMqueries mimQ, MIMops ops) throws SdaiException
	{
		this.ops = ops;
		this.mimQ = mimQ;
		this.duc = new MIMdimensionalUnitUtils(ops);
	}	
	
	public Param parametricAttributeForPropertyDefinition(EProperty_definition e_pd) throws SdaiException
	{
		Param p = null;
		String propertyName = e_pd.getName(null);
		EProperty_definition_representation e_pdr = ops.pdr_referencing_pd_3(e_pd);
		if (e_pdr != null)
		{
			ERepresentation e_rep = e_pdr.getUsed_representation(null);
			ERepresentation_item e_ri = ops.ri_referencedBy_r(e_rep);
			p = parameterForRepresentationItem(propertyName, e_ri);
		}
		else
		{
			String propertyDescription = null;
			if (e_pd.testDescription(null))
				propertyDescription = e_pd.getDescription(null);
			else
				propertyDescription = "";
			p = new StringParamImpl(propertyName, propertyDescription);
		}
		return p;
	}
	
	
	public Set<Param> allParametersForProduct(EProduct e_p) throws SdaiException
	{
		Set<Param> set = new HashSet<Param>();
		AParameter_assignment a_pa = mimQ.getAllParameterAssignmentsForProduct(e_p);
		SdaiIterator it_pa = a_pa.createIterator();
		
		while (it_pa.next()) {
			EParameter_assignment e_pa = (EParameter_assignment) a_pa.getCurrentMemberEntity(it_pa);
			if (e_pa.getDefinition(null).isKindOf(EModel_parameter.class))
			{
				EModel_parameter e_mp = (EModel_parameter) e_pa.getDefinition(null);
				String parameterName = e_mp.getName(null);
				
				ERepresentation e_rep = e_pa.getUsed_representation(null);
				ERepresentation_item e_ri = ops.ri_referencedBy_r(e_rep);
				Param p = null;
				if (e_ri != null)
				{
					p = parameterForRepresentationItem(parameterName, e_ri);
				}
				else
				{
					p = new StringParamImpl(parameterName, "Unknown");
				}
				set.add(p);
			}
		}
		return set;
	}
	
	public Param parameterForRepresentationItem(String paramName, ERepresentation_item e_ri) throws SdaiException
	{
		if (e_ri.isKindOf(EMeasure_with_unit.class))
			return measureWithUnitParameter(paramName, (EMeasure_with_unit) e_ri);
		else if (e_ri.isKindOf(EDescriptive_representation_item.class))
			return descriptive_representation_itemParameter(paramName, (EDescriptive_representation_item) e_ri);
		else if (e_ri.isKindOf(EBoolean_representation_item.class))
			return booleanParameter(paramName, (EBoolean_representation_item) e_ri); 
		else
			return new StringParamImpl(paramName, "Unknown");
	}
	
	public Param measureWithUnitParameter(String paramName, EMeasure_with_unit e_mwu) throws SdaiException
	{
		if (e_mwu.getUnit_component(null).isKindOf(EContext_dependent_unit.class))
		{
			EContext_dependent_unit e_cdu = (EContext_dependent_unit) e_mwu.getUnit_component(null);
			String unitName = e_cdu.getName(null);
			if (unitName.equals("count"))
				return integerParameter(paramName, e_mwu);
			else
				return otherMeasureWithUnitParameter(paramName, e_mwu);
		}
		else if (e_mwu.isKindOf(ELength_measure_with_unit.class))
			return new MeasureParamImpl(paramName, duc.lengthMeasureWithUnitInMM((ELength_measure_with_unit) e_mwu), Units.MILLIMETERS);
		else if (e_mwu.isKindOf(EArea_measure_with_unit.class))
			return new MeasureParamImpl(paramName, duc.areaMeasureWithUnitInSqMM((EArea_measure_with_unit) e_mwu), Units.SQUARE_MILLIMETERS);
		else
			return otherMeasureWithUnitParameter(paramName, e_mwu);
	}
	
	public Param otherMeasureWithUnitParameter(String paramName, EMeasure_with_unit e_mwu) throws SdaiException
	{
		if (e_mwu.isKindOf(ETime_measure_with_unit.class))
		{
			double measure_value = e_mwu.getValue_component(null, (ETime_measure) null);
			
			ENamed_unit e_u = (ENamed_unit) e_mwu.getUnit_component(null);
			if (!(e_u.isKindOf(ESi_unit.class)))
				throw new UnsupportedOperationException("Unsupported measure_with_unit encountered: "+e_mwu);
			
			if ((((ESi_unit)e_u).getName(null)==ESi_unit_name.SECOND) && (!(((ESi_unit)e_u).testPrefix(null))))
				return new MeasureParamImpl(paramName, measure_value, Units.SECONDS);
			else
				throw new UnsupportedOperationException("Unsupported measure_with_unit encountered: "+e_mwu);
			
		}
		else if (e_mwu.isKindOf(ECelsius_temperature_measure_with_unit.class))
		{
			double measure_value = e_mwu.getValue_component(null, (ECelsius_temperature_measure) null);
			ENamed_unit e_u = (ENamed_unit) e_mwu.getUnit_component(null);
			if (!(e_u.isKindOf(ESi_unit.class)))
				throw new UnsupportedOperationException("Unsupported measure_with_unit encountered: "+e_mwu);
			
			if ((((ESi_unit)e_u).getName(null)==ESi_unit_name.DEGREE_CELSIUS) && (!(((ESi_unit)e_u).testPrefix(null))))
				return new MeasureParamImpl(paramName, measure_value, Units.DEG_CELSIUS);
			else
				throw new UnsupportedOperationException("Unsupported measure_with_unit encountered: "+e_mwu);
		}
		else
		{
			throw new UnsupportedOperationException("Unsupported measure_with_unit encountered: "+e_mwu);
		}
	}
	
	Param integerParameter(String paramName, EMeasure_with_unit e_mwu) throws SdaiException
	{
		double countValue = e_mwu.getValue_component(null,(ECount_measure)null);
		int count = (int) countValue;
		return new IntegerParamImpl(paramName, count);
	}
	
	Param descriptive_representation_itemParameter(String paramName, EDescriptive_representation_item e_dri) throws SdaiException
	{
		String value = e_dri.getDescription(null);
		if (e_dri.getName(null).equals("text"))
		{
			return new StringParamImpl(paramName, value);
		}
		else if (value.equalsIgnoreCase("true"))
		{
			return new BooleanParamImpl(paramName, true);
		}
		else if (value.equalsIgnoreCase("false"))
		{
			return new BooleanParamImpl(paramName, false);
		}
		else
		{
			throw new UnsupportedOperationException("Unsupported descriptive_representation_item encountered: "+e_dri);
		}
	}
	
	BooleanParamImpl booleanParameter(String paramName, EBoolean_representation_item e_bri) throws SdaiException
	{
		boolean value = e_bri.getThe_value(null);
		return new BooleanParamImpl(paramName, value);
	}

}
