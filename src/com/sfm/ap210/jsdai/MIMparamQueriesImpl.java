package com.sfm.ap210.jsdai;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.*;
import jsdai.lang.SdaiException;
import jsdai.lang.SdaiIterator;

import com.sfm.ap210.jsdai.param.BooleanParamImpl;
import com.sfm.ap210.jsdai.param.DerivedSiUnit;
import com.sfm.ap210.jsdai.param.DoubleParam;
import com.sfm.ap210.jsdai.param.DoubleParamImpl;
import com.sfm.ap210.jsdai.param.IntegerParamImpl;
import com.sfm.ap210.jsdai.param.MeasureParam;
import com.sfm.ap210.jsdai.param.MeasureParamImpl;
import com.sfm.ap210.jsdai.param.ModelParameter;
import com.sfm.ap210.jsdai.param.Param;
import com.sfm.ap210.jsdai.param.ParameterAssignment;
import com.sfm.ap210.jsdai.param.SiUnit;
import com.sfm.ap210.jsdai.param.SiUnit.SiBase;
import com.sfm.ap210.jsdai.param.StringParamImpl;
import com.sfm.ap210.jsdai.param.Unit;
import com.sfm.ap210.jsdai.param.Unit.SiPrefix;

/**
 * An implementation of the MIMparamQueries interface.
 * @author James Stori, SFM Technology, Inc.
 * @version 1.4
 */
public class MIMparamQueriesImpl implements MIMparamQueries {
	
	private MIMops ops;
	private MIMqueries mimQ;
	private MIMdimensionalUnitUtils duc;
	Map<EModel_parameter, ModelParameter> modelParameterMap = new HashMap<EModel_parameter, ModelParameter>();
	
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
	
	public Set<ParameterAssignment> allParameterAssignmentForProduct(EProduct e_p) throws SdaiException
	{
		AParameter_assignment a_pa = mimQ.getAllParameterAssignmentsForProduct(e_p);
		return allParameterAssignment(a_pa);
	}
	
	public Set<ParameterAssignment> allParameterAssignment(AParameter_assignment a_pa) throws SdaiException
	{
		Set<ParameterAssignment> set = new HashSet<ParameterAssignment>();
		SdaiIterator it_pa = a_pa.createIterator();
		
		while (it_pa.next()) {
			EParameter_assignment e_pa = (EParameter_assignment) a_pa.getCurrentMemberEntity(it_pa);
			if (e_pa.getDefinition(null).isKindOf(EModel_parameter.class))
			{
				EModel_parameter e_mp = (EModel_parameter) e_pa.getDefinition(null);
				ModelParameter mp = getModelParameter(e_mp);
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
				ParameterAssignment pa = new ParameterAssignment(p, mp);
				set.add(pa);
			}
		}
		return set;
	}
	/**
	 * 
	 */
	ModelParameter getModelParameter(EModel_parameter e_mp) throws SdaiException
	{
		if (modelParameterMap.containsKey(e_mp))
			return modelParameterMap.get(e_mp);
			
		String desc = "";
		if (e_mp.testDescription(null))
			desc = e_mp.getDescription(null);
		
		ModelParameter mp = new ModelParameter(e_mp.getId(null), e_mp.getName(null), desc);
		modelParameterMap.put(e_mp, mp);
		
		return mp;
	}
	
	public Set<Param> allParametersForParameterAssignment(AParameter_assignment a_pa) throws SdaiException
	{
		Set<Param> set = new HashSet<Param>();
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
	
	public Set<Param> allParametersForProduct(EProduct e_p) throws SdaiException
	{
		AParameter_assignment a_pa = mimQ.getAllParameterAssignmentsForProduct(e_p);
		return allParametersForParameterAssignment(a_pa);
	}
	
	public Param parameterForRepresentationItem(String paramName, ERepresentation_item e_ri) throws SdaiException
	{
		if (e_ri.isKindOf(EMeasure_with_unit.class))
			return measureWithUnitParameter(paramName, (EMeasure_with_unit) e_ri);
		else if (e_ri.isKindOf(EDescriptive_representation_item.class))
			return descriptive_representation_itemParameter(paramName, (EDescriptive_representation_item) e_ri);
		else if (e_ri.isKindOf(EBoolean_representation_item.class))
			return booleanParameter(paramName, (EBoolean_representation_item) e_ri); 
		else if (e_ri.isKindOf(EReal_representation_item.class))
			return doubleParameter(paramName, (EReal_representation_item) e_ri);
		else
			return new StringParamImpl(paramName, "Unknown");
	}
	
	public DoubleParam doubleParameter(String name, EReal_representation_item e_rri) throws SdaiException
	{
		return new DoubleParamImpl(name, e_rri.getThe_value(null));
	}
	
	public Param measureWithUnitParameter(String paramName, EMeasure_with_unit e_mwu) throws SdaiException
	{
		if (e_mwu.getUnit_component(null).isKindOf(ESi_unit.class))
			return getSiUnitParameter(paramName, e_mwu, (ESi_unit)e_mwu.getUnit_component(null));
		else if (e_mwu.getUnit_component(null).isKindOf(EDerived_unit.class))
			return getDerivedUnitParameter(paramName, e_mwu, (EDerived_unit)e_mwu.getUnit_component(null));
		else if (e_mwu.getUnit_component(null).isKindOf(EContext_dependent_unit.class))
		{
			EContext_dependent_unit e_cdu = (EContext_dependent_unit) e_mwu.getUnit_component(null);
			String unitName = e_cdu.getName(null);
			if (unitName.equals("count"))
				return integerParameter(paramName, e_mwu);
			else
				return otherMeasureWithUnitParameter(paramName, e_mwu);
		}
		else if (e_mwu.isKindOf(ELength_measure_with_unit.class))
			return new MeasureParamImpl(paramName, duc.lengthMeasureWithUnitInMM((ELength_measure_with_unit) e_mwu), new SiUnit(SiUnit.SiBase.M, Unit.SiPrefix.milli));
		else if (e_mwu.isKindOf(EArea_measure_with_unit.class))
			return new MeasureParamImpl(paramName, duc.areaMeasureWithUnitInSqMM((EArea_measure_with_unit) e_mwu), new DerivedSiUnit(SiUnit.SiBase.M, 2, Unit.SiPrefix.micro));
		else
			return otherMeasureWithUnitParameter(paramName, e_mwu);
	}
	
	SiUnit.SiBase getSiBase(ESi_unit e_siu) throws SdaiException
	{	
		if (e_siu.getName(null) == ESi_unit_name.METRE)
			return SiBase.M;
		else if (e_siu.getName(null) == ESi_unit_name.VOLT)
			return SiBase.V;
		else if (e_siu.getName(null) == ESi_unit_name.HERTZ)
			return SiBase.H;
		else if (e_siu.getName(null) == ESi_unit_name.OHM)
			return SiBase.Ohm;
		else if (e_siu.getName(null) == ESi_unit_name.WATT)
			return SiBase.W;
		else if (e_siu.getName(null) == ESi_unit_name.JOULE)
			return SiBase.J; 	
		else if (e_siu.getName(null) == ESi_unit_name.DEGREE_CELSIUS)
			return SiBase.degC;
		else if (e_siu.getName(null) == ESi_unit_name.KELVIN)
			return SiBase.degK;
		else if (e_siu.getName(null) == ESi_unit_name.AMPERE)
			return SiBase.A;
		else if (e_siu.getName(null) == ESi_unit_name.FARAD)
			return SiBase.F;
		else if (e_siu.getName(null) == ESi_unit_name.SECOND)
			return SiBase.S;
		else if (e_siu.getName(null) == ESi_unit_name.HENRY)
			return SiBase.H;
		else if (e_siu.getName(null) == ESi_unit_name.GRAM && e_siu.getPrefix(null) == ESi_prefix.KILO)
			return SiBase.kg;
		else
			throw new IllegalArgumentException("Unsupported si unit: "+e_siu);
	}
	
	/**
	 * unset is OK for the prefix-> returns null
	 * @param e_siu
	 * @return
	 * @throws SdaiException
	 */
	SiPrefix getSiPrefix(ESi_unit e_siu) throws SdaiException
	{
		if (!(e_siu.testPrefix(null)))
			return null;
		else if (e_siu.getPrefix(null) == ESi_prefix.FEMTO)
			return SiPrefix.femto;
		else if (e_siu.getPrefix(null) == ESi_prefix.PICO)
			return SiPrefix.pico;
		else if (e_siu.getPrefix(null) == ESi_prefix.NANO)
			return SiPrefix.nano;
		else if (e_siu.getPrefix(null) == ESi_prefix.MICRO)
			return SiPrefix.micro;
		else if (e_siu.getPrefix(null) == ESi_prefix.MILLI)
			return SiPrefix.milli;
		else if (e_siu.getPrefix(null) == ESi_prefix.KILO)
			return SiPrefix.kilo;
		else if (e_siu.getPrefix(null) == ESi_prefix.MEGA)
			return SiPrefix.mega;
		else if (e_siu.getPrefix(null) == ESi_prefix.GIGA)
			return SiPrefix.giga;
		else if (e_siu.getPrefix(null) == ESi_prefix.TERA)
			return SiPrefix.tera;
		else 
			throw new IllegalArgumentException("Unsupported prefix: "+e_siu);
	}
	
	SiUnit getSiUnit(ESi_unit e_siu) throws SdaiException
	{
		SiUnit.SiBase base = getSiBase(e_siu);
		SiPrefix prefix = getSiPrefix(e_siu);
		return new SiUnit(base, prefix);
	}
	
	public MeasureParam getSiUnitParameter(String paramName, EMeasure_with_unit e_mwu, ESi_unit e_si_unit) throws SdaiException
	{
		SiUnit unit = getSiUnit(e_si_unit);
		double value = 0.0;
		
		if (unit.base == SiUnit.SiBase.V)
			value = e_mwu.getValue_component(null, (EElectric_potential_measure) null);
		else if (unit.base == SiUnit.SiBase.Ohm)
			value = e_mwu.getValue_component(null, (EResistance_measure) null);
		else if (unit.base == SiUnit.SiBase.degC || unit.base == SiUnit.SiBase.degK)
			value = e_mwu.getValue_component(null, (EThermodynamic_temperature_measure) null);		
		else if (unit.base == SiUnit.SiBase.A)
			value = e_mwu.getValue_component(null, (EElectric_current_measure) null);
		else if (unit.base == SiUnit.SiBase.Hz)
			value = e_mwu.getValue_component(null, (EFrequency_measure) null);
		else if (unit.base == SiUnit.SiBase.F)
			value = e_mwu.getValue_component(null, (ECapacitance_measure) null);
		else if (unit.base == SiUnit.SiBase.S)
			value = e_mwu.getValue_component(null, (ETime_measure) null);
		else if (unit.base == SiUnit.SiBase.H)
			value = e_mwu.getValue_component(null, (EInductance_measure) null);	
		else
			throw new IllegalArgumentException("unsupported or invalid measure with unit: "+e_mwu);
		
		return new MeasureParamImpl(paramName, value, unit);
	}
	
	public DerivedSiUnit getCompoundSiUnit(EDerived_unit e_derived) throws SdaiException
	{
		DerivedSiUnit unit = new DerivedSiUnit();
		ADerived_unit_element a_due = e_derived.getElements(null);
		SdaiIterator it = a_due.createIterator();
		while (it.next())
		{
			EDerived_unit_element e_due = a_due.getCurrentMember(it);
			ENamed_unit e_nu = e_due.getUnit(null);
			if (!(e_nu.isKindOf(ESi_unit.class)))
				throw new IllegalArgumentException("Unsupported units in: "+e_derived);
			SiUnit siu = getSiUnit((ESi_unit) e_nu);
			double e = e_due.getExponent(null);
			int i = new Double(e).intValue();
			unit.addTerm(siu, i);
		}
		return unit;
	}
	
	public MeasureParam getDerivedUnitParameter(String paramName, EMeasure_with_unit e_mwu, EDerived_unit e_derived_unit) throws SdaiException
	{
		double value = e_mwu.getValue_component(null, (ENumeric_measure) null);
		DerivedSiUnit unit = getCompoundSiUnit(e_derived_unit);
		 
		return new MeasureParamImpl(paramName, value, unit);
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
				return new MeasureParamImpl(paramName, measure_value, new SiUnit(SiUnit.SiBase.S));
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
				return new MeasureParamImpl(paramName, measure_value, new SiUnit(SiUnit.SiBase.degC));
			else if ((((ESi_unit)e_u).getName(null)==ESi_unit_name.KELVIN) && (!(((ESi_unit)e_u).testPrefix(null))))
				return new MeasureParamImpl(paramName, measure_value, new SiUnit(SiUnit.SiBase.degK));
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
