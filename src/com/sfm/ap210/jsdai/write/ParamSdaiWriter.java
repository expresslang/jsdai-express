package com.sfm.ap210.jsdai.write;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.*;
import jsdai.lang.EEntity;
import jsdai.lang.SdaiException;
import jsdai.lang.SdaiModel;

import com.sfm.ap210.jsdai.Length;
import com.sfm.ap210.jsdai.param.CategoryModelParameterWithUnit;
import com.sfm.ap210.jsdai.param.ClassWithAttributes;
import com.sfm.ap210.jsdai.param.DerivedSiUnit;
import com.sfm.ap210.jsdai.param.DigitalDocument;
import com.sfm.ap210.jsdai.param.DoubleParam;
import com.sfm.ap210.jsdai.param.MeasureParam;
import com.sfm.ap210.jsdai.param.ModelParameter;
import com.sfm.ap210.jsdai.param.Param;
import com.sfm.ap210.jsdai.param.ParameterAssignment;
import com.sfm.ap210.jsdai.param.SiUnit;
import com.sfm.ap210.jsdai.param.StringParam;
import com.sfm.ap210.jsdai.param.Unit;

public class ParamSdaiWriter {
	SdaiModel outModel;
	EApplication_context applicationContext = null;
	ELength_unit e_mm_length_unit = null;
	ELength_unit e_inch_length_unit = null;
	ERatio_unit e_ratio_unit = null;
	ESolid_angle_unit e_solid_angle_unit = null;
	EPlane_angle_unit e_degree_unit = null;
	Map<String, ESi_unit> siUnitMap = new HashMap<String, ESi_unit>();
	Map<String, EDerived_unit> derivedUnitMap = new HashMap<String, EDerived_unit>();
	Map<ModelParameter, EModel_parameter> modelParameterMap = new HashMap<ModelParameter, EModel_parameter>();
	Map<ClassWithAttributes, ECharacterized_class> classMap = new HashMap<ClassWithAttributes, ECharacterized_class>();
	Map<DigitalDocument, EApplied_document_reference> docMap = new HashMap<DigitalDocument, EApplied_document_reference>();
	
	public ParamSdaiWriter(SdaiModel m, EApplication_context e_app_context)
	{
		outModel = m;
		applicationContext = e_app_context;
	}
	
	/**
	 * Instantiate the product_specific_parameter_assignment and associated entities for the MIM mapping
	 * of a set of Parameter associated with the given product.
	 */
	public void addProductSpecificParameters(EProduct e_p, Set<ParameterAssignment> params) throws SdaiException
	{
		for (ParameterAssignment p: params)
		{
			addProductSpecificParameter(e_p, p);
		}
	}
	
	public void addProductSpecificParameters(EComponent_functional_unit e_cfu, Set<ParameterAssignment> params) throws SdaiException
	{
		for (ParameterAssignment p: params)
		{
			addProductSpecificParameter(e_cfu, p);
		}
	}
	
	/**
	 * Instantiate the MIM mapping of the given ModelParameter.
	 * If ModelParameter is part of a call (CategoryModelParameterWithUnit), populate the mapping to the class.
	 */
	EModel_parameter getModelParameter(ModelParameter mp) throws SdaiException
	{
		if (modelParameterMap.containsKey(mp))
			return modelParameterMap.get(mp);
		
		EModel_parameter e_mp = (EModel_parameter) outModel.createEntityInstance(CModel_parameter.class);
		modelParameterMap.put(mp, e_mp);
		e_mp.setId(null, mp.getId());
		e_mp.setName(null, mp.getPropertyType());
		e_mp.setDescription(null, mp.getDescription());
		
		if (mp instanceof CategoryModelParameterWithUnit)
		{
			CategoryModelParameterWithUnit cmp = (CategoryModelParameterWithUnit) mp;
			ECharacterized_class e_cc = getClassWithAttributes(cmp.getAssociatedClass());
			EProperty_definition e_pd = (EProperty_definition) outModel.createEntityInstance(CProperty_definition.class);
			e_pd.setDefinition(null, e_cc);
			e_pd.setName(null, mp.getId());
			e_pd.setDescription(null, "model parameter");
			EGeneral_property_association e_gpa = (EGeneral_property_association) outModel.createEntityInstance(CGeneral_property_association.class);
			e_gpa.setName(null, "attribute definition");
			e_gpa.setDerived_definition(null, e_pd);
			e_gpa.setBase_definition(null, e_mp);
			
			ClassWithAttributes assignedClass = cmp.getAssociatedClass();
			if (assignedClass.referenceDocument != null)
			{
				addDocumentReference(assignedClass.referenceDocument, e_mp);
			}
		}
		return e_mp;
	}
	
	/**
	 * OK for model_parameter or characterized_class
	 * @param doc
	 * @param e_mp
	 * @throws SdaiException
	 */
	void addDocumentReference(DigitalDocument doc, EEntity e_doc_ref_item) throws SdaiException
	{
		EApplied_document_reference e_adr = getDocumentReference(doc);
		ADocument_reference_item a_dr = null;
		if (e_adr.testItems(null))
			a_dr = e_adr.getItems(null);
		else
			a_dr = e_adr.createItems(null);
		a_dr.addUnordered(e_doc_ref_item);
	}
	
	EApplied_document_reference getDocumentReference(DigitalDocument doc) throws SdaiException
	{
		if (docMap.containsKey(doc))
			return docMap.get(doc);
		
		EApplied_document_reference e_adr = (EApplied_document_reference) outModel.createEntityInstance(CApplied_document_reference.class);
		docMap.put(doc, e_adr);
		
		EDocument e_doc = (EDocument) outModel.createEntityInstance(CDocument.class);
		e_doc.setId(null, "");
		e_doc.setName(null, "");
		e_adr.setAssigned_document(null, e_doc);
		EDocument_type e_doc_type = (EDocument_type) outModel.createEntityInstance(CDocument_type.class);
		e_doc.setKind(null, e_doc_type);
		e_doc_type.setProduct_data_type(null, "configuration controlled document definition");
		EDocument_product_equivalence e_dpe = (EDocument_product_equivalence) outModel.createEntityInstance(CDocument_product_equivalence.class);
		e_dpe.setName(null, "equivalence");
		e_dpe.setRelating_document(null, e_doc);
		EProduct_definition e_doc_def = (EProduct_definition) outModel.createEntityInstance(CProduct_definition.class);
		e_dpe.setRelated_product(null, e_doc_def);
		EProduct_definition_context e_pdc = (EProduct_definition_context) outModel.createEntityInstance(CProduct_definition_context.class);
		e_pdc.setName(null, "digital document definition");
		e_pdc.setFrame_of_reference(null, applicationContext);
		e_pdc.setLife_cycle_stage(null, "design");
		e_doc_def.setFrame_of_reference(null, e_pdc);
		EProduct_definition_formation e_pdf = (EProduct_definition_formation) outModel.createEntityInstance(CProduct_definition_formation.class);
		e_pdf.setId(null, doc.version);
		e_doc_def.setFormation(null, e_pdf);
		EProduct e_product = (EProduct) outModel.createEntityInstance(CProduct.class);
		e_product.setId(null, doc.id);
		e_product.setName(null, "");
		e_pdf.setOf_product(null, e_product);
		
		EProduct_related_product_category e_document_prpc = (EProduct_related_product_category) outModel.createEntityInstance(CProduct_related_product_category.class);
		e_document_prpc.setName(null, "document");
		AProduct a_document_products = (AProduct) e_document_prpc.createProducts(null);
		a_document_products.addUnordered(e_product);
		
		EApplied_external_identification_assignment e_aeia = (EApplied_external_identification_assignment) outModel.createEntityInstance(CApplied_external_identification_assignment.class);
		AExternal_identification_item a_items = e_aeia.createItems(null);
		a_items.addUnordered(e_doc_def);
		EIdentification_role e_role = (EIdentification_role) outModel.createEntityInstance(CIdentification_role.class);
		e_aeia.setRole(null, e_role);
		e_aeia.setAssigned_id(null, "identification of "+doc.description);
		e_role.setName(null, doc.source_type);
		e_role.setDescription(null, doc.description);
		EExternal_source e_source = (EExternal_source) outModel.createEntityInstance(CExternal_source.class);
		e_source.setSource_id(null, doc.source_id, (EIdentifier)null);
		e_aeia.setSource(null, e_source);
		return e_adr;
	}
	
	ECharacterized_class getClassWithAttributes(ClassWithAttributes classWithAtt) throws SdaiException
	{
		if (classMap.containsKey(classWithAtt))
			return classMap.get(classWithAtt);
		
		ECharacterized_class e_cc = (ECharacterized_class) outModel.createEntityInstance(CCharacterized_class.class);
		classMap.put(classWithAtt, e_cc);
		e_cc.setName((EGroup)null, classWithAtt.name);
		e_cc.setName((ECharacterized_object)null, "");
		e_cc.setDescription((EGroup)null, classWithAtt.description);
		EId_attribute e_id = (EId_attribute) outModel.createEntityInstance(CId_attribute.class);
		e_id.setAttribute_value(null, classWithAtt.id);
		e_id.setIdentified_item(null, e_cc);
		
		if (classWithAtt.referenceDocument != null)
		{
			addDocumentReference(classWithAtt.referenceDocument, e_cc);
		}
		return e_cc;
	}
	
	/**
	 * Add a descriptive_representation_item to the given representation.
	 * Sets descriptive_representation_item.name = driName
	 * Sets descriptive_representation_item.description = driDescription
	 */
	public void addDescriptiveRepresentationItem(ERepresentation e_r, String driName, String driDescription) throws SdaiException
	{
		EDescriptive_representation_item e_dri = (EDescriptive_representation_item) outModel.createEntityInstance(CDescriptive_representation_item.class);
		e_dri.setName(null, driName);
		e_dri.setDescription(null, driDescription);
		// only create new items aggregate if it does not already exist
		ARepresentation_item a_ri = null;
		if (e_r.testItems(null))
			a_ri = e_r.getItems(null);
		else
			a_ri = e_r.createItems(null);
		a_ri.addUnordered(e_dri);
	}
	
	/**
	 * populates Parameter_assignment.
	 * TODO implement Parameter_assignment_override as applicable 
	 * @param e_cfu
	 * @param pa
	 * @throws SdaiException
	 */
	void addProductSpecificParameter(EComponent_functional_unit e_cfu, ParameterAssignment pa) throws SdaiException
	{
		EProperty_definition e_pd = (EProperty_definition) outModel.createEntityInstance(CProperty_definition.class);
		e_pd.setName(null, "");
		e_pd.setDescription(null, "assigned parameter");
		e_pd.setDefinition(null, e_cfu);
		
		EParameter_assignment e_pa = createParameterAssignment(pa);
		EProperty_definition_representation e_pdr = (EProperty_definition_representation) outModel.createEntityInstance(CProperty_definition_representation.class);
		e_pdr.setUsed_representation(null, e_pa);
		e_pdr.setDefinition(null, e_pd);
	}
	/**
	 * Instantiate the product_specific_parameter_assignment and associated entities for the MIM mapping
	 * of a given Parameter associated with the given product.
	 */
	void addProductSpecificParameter(EProduct e_p, ParameterAssignment pa) throws SdaiException
	{
		EProduct_specific_parameter_value_assignment e_pspva = (EProduct_specific_parameter_value_assignment) outModel.createEntityInstance(CProduct_specific_parameter_value_assignment.class);
		e_pspva.setName((ECharacterized_object) null, "");
		// description is optional
		//e_pspva.setDescription((ECharacterized_object) null, "");
		e_pspva.setName((EProduct_category) null, "");
		// description is optional
		//e_pspva.setDescription((EProduct_category) null, "");
		
		AProduct a_p = e_pspva.createProducts(null);
		a_p.addUnordered(e_p);
		EProperty_definition e_pd = (EProperty_definition) outModel.createEntityInstance(CProperty_definition.class);
		e_pd.setName(null, "");
		e_pd.setDescription(null, "assigned parameter");
		e_pd.setDefinition(null, e_pspva);
		
		EParameter_assignment e_pa = createParameterAssignment(pa);
		EProperty_definition_representation e_pdr = (EProperty_definition_representation) outModel.createEntityInstance(CProperty_definition_representation.class);
		e_pdr.setUsed_representation(null, e_pa);
		e_pdr.setDefinition(null, e_pd);
	}
	
	EParameter_assignment createParameterAssignment(ParameterAssignment pa) throws SdaiException
	{
		Param p = pa.getParam();
		
		//if (param instanceof RangeCharacteristicParameter)
		//	e_pa = (EParameter_assignment) outModel.createEntityInstance(CParameter_assignment$range_characteristic.class);
		//else
		EParameter_assignment e_pa = (EParameter_assignment) outModel.createEntityInstance(CParameter_assignment.class);

		//ERepresentation_context e_context = (ERepresentation_context) outModel.createEntityInstance(CRepresentation_context.class);
		//e_context.setContext_identifier(null, "parameter values");
		//e_context.setContext_type(null, "parameter");
		//e_pa.setContext_of_items(null, e_context);

		e_pa.setName(null, pa.getModelParameter().getId());
		
		EModel_parameter e_mp = getModelParameter(pa.getModelParameter());
		e_pa.setDefinition(null, e_mp);
		
		//ERepresentation e_rep = (ERepresentation) outModel.createEntityInstance(CRepresentation.class);
		ERepresentation e_rep = e_pa;
		e_pa.setUsed_representation(null, e_rep);
		
		if (p instanceof StringParam)
			addDescriptiveRepresentationItem(e_rep, "text", ((StringParam)p).getValue());
		else if (p instanceof MeasureParam)
			addMeasureRepresentationItem(e_rep, p.getName(), ((MeasureParam)p).getDoubleValue(), ((MeasureParam)p).getUnit());
		else if (p instanceof DoubleParam)
			addRealRepresentationItem(e_rep, p.getName(), ((DoubleParam)p).getDoubleValue());
		else
			throw new UnsupportedOperationException("Unable to instantiate product_specific_parameter_assignment for: "+p);
		return e_pa;
	}
	
	EDerived_unit getCompoundSiUnit(DerivedSiUnit u) throws SdaiException
	{
		if (derivedUnitMap.containsKey(u.toString()))
			return derivedUnitMap.get(u.toString());
		
		EDerived_unit e_du = (EDerived_unit) outModel.createEntityInstance(CDerived_unit.class);
		ADerived_unit_element a_due = e_du.createElements(null);
		
		for (DerivedSiUnit.SiUnitTerm t : u.getTerms())
		{
			EDerived_unit_element e_due = (EDerived_unit_element) outModel.createEntityInstance(CDerived_unit_element.class);
			ESi_unit e_due_unit = getSiUnit(t.siunit);
			e_due.setUnit(null, e_due_unit);
			e_due.setExponent(null, t.exponent);
			a_due.addUnordered(e_due);
		}
		derivedUnitMap.put(u.toString(), e_du);
		return e_du;
	}
	
	/**
	 * Return a singleton instance of named_unit containing the MIM mapping for
	 * the given Unit.
	 */
	ESi_unit getSiUnit(SiUnit u) throws SdaiException
	{
		ESi_unit e_siu = null;
		
		if (siUnitMap.containsKey(u.toString()))
			return siUnitMap.get(u.toString());
		
		if (u.base == SiUnit.SiBase.M)
			e_siu = getMeterUnit();
		else if (u.base == SiUnit.SiBase.V)
			e_siu = getVoltUnit();
		else if (u.base == SiUnit.SiBase.Hz)
			e_siu = getFrequencyUnit();
		else if (u.base == SiUnit.SiBase.Ohm)
			e_siu = getOhmUnit();
		else if (u.base == SiUnit.SiBase.W)
			e_siu = getWattUnit();
		else if (u.base == SiUnit.SiBase.J)
			e_siu = getJouleUnit();
		else if (u.base == SiUnit.SiBase.degC)
			e_siu = getDegCUnit();
		else if (u.base == SiUnit.SiBase.degK)
			e_siu = getDegKUnit();
		else if (u.base == SiUnit.SiBase.A)
			e_siu = getAmpUnit();
		else if (u.base == SiUnit.SiBase.F)
			e_siu = getFaradUnit();
		else if (u.base == SiUnit.SiBase.S)
			e_siu = getSecUnit();
		else if (u.base == SiUnit.SiBase.H)
			e_siu = getHenryUnit();
		else if (u.base == SiUnit.SiBase.kg)
			e_siu = getKgUnit();
		else
			throw new IllegalArgumentException("Unsupported unit: "+u.base);
		
		if (u.prefix == SiUnit.SiPrefix.femto)
			e_siu.setPrefix(null, ESi_prefix.FEMTO);
		else if (u.prefix == SiUnit.SiPrefix.pico)
			e_siu.setPrefix(null, ESi_prefix.PICO);
		else if (u.prefix == SiUnit.SiPrefix.nano)
			e_siu.setPrefix(null, ESi_prefix.NANO);
		else if (u.prefix == SiUnit.SiPrefix.micro)
			e_siu.setPrefix(null, ESi_prefix.MICRO);
		else if (u.prefix == SiUnit.SiPrefix.milli)
			e_siu.setPrefix(null, ESi_prefix.MILLI);
		else if (u.prefix == SiUnit.SiPrefix.kilo)
			e_siu.setPrefix(null, ESi_prefix.KILO);
		else if (u.prefix == SiUnit.SiPrefix.mega)
			e_siu.setPrefix(null, ESi_prefix.MEGA);
		else if (u.prefix == SiUnit.SiPrefix.giga)
			e_siu.setPrefix(null, ESi_prefix.GIGA);
		else if (u.prefix == SiUnit.SiPrefix.tera)
			e_siu.setPrefix(null, ESi_prefix.TERA);
		else if (u.prefix != null)
			throw new IllegalArgumentException("Unsupported prefix: "+u.prefix);
		siUnitMap.put(u.toString(), e_siu);
		return e_siu;
	}
	
	ESi_unit getFrequencyUnit() throws SdaiException
	{
		//ESi_unit e_siu = (ESi_unit) outModel.createEntityInstance(CSi_unit.class);
		ESi_frequency_unit e_si_frequency_unit = (ESi_frequency_unit) outModel.createEntityInstance(CSi_frequency_unit.class);
		e_si_frequency_unit.setName(null, ESi_unit_name.HERTZ);
		ADerived_unit_element a_due = e_si_frequency_unit.createElements(null);
		EDerived_unit_element e_due_second = (EDerived_unit_element) outModel.createEntityInstance(CDerived_unit_element.class);
		ESi_unit e_second = getSecUnit();
		e_due_second.setUnit(null, e_second);
		e_due_second.setExponent(null, -1.0);
		a_due.addUnordered(e_due_second);
		
		return e_si_frequency_unit;
	}		
	
	ESi_unit getDegCUnit() throws SdaiException
	{
		ESi_unit e_si_degc_unit = (ESi_unit) outModel.createEntityInstance(CSi_unit$thermodynamic_temperature_unit.class);
		e_si_degc_unit.setName(null, ESi_unit_name.DEGREE_CELSIUS);
		return e_si_degc_unit;
	}
	
	ESi_unit getDegKUnit() throws SdaiException
	{
		ESi_unit e_si_degk_unit = (ESi_unit) outModel.createEntityInstance(CSi_unit$thermodynamic_temperature_unit.class);
		e_si_degk_unit.setName(null, ESi_unit_name.KELVIN);
		return e_si_degk_unit;	
	}
	
	ESi_unit getWattUnit() throws SdaiException
	{
		ESi_power_unit e_si_watt_unit = (ESi_power_unit) outModel.createEntityInstance(CSi_power_unit.class);
		e_si_watt_unit.setName(null, ESi_unit_name.WATT);
		ADerived_unit_element a_due = e_si_watt_unit.createElements(null);
		
		EDerived_unit_element e_due_meter = (EDerived_unit_element) outModel.createEntityInstance(CDerived_unit_element.class);
		ESi_unit e_meter = getMeterUnit();
		e_due_meter.setUnit(null, e_meter);
		e_due_meter.setExponent(null, 2.0);
		a_due.addUnordered(e_due_meter);
		
		EDerived_unit_element e_due_kg = (EDerived_unit_element) outModel.createEntityInstance(CDerived_unit_element.class);
		ESi_unit e_kg = getKgUnit();
		e_due_kg.setUnit(null, e_kg);
		e_due_kg.setExponent(null, 1.0);
		a_due.addUnordered(e_due_kg);
		
		EDerived_unit_element e_due_sec = (EDerived_unit_element) outModel.createEntityInstance(CDerived_unit_element.class);
		ESi_unit e_sec = getSecUnit();
		e_due_sec.setUnit(null, e_sec);
		e_due_sec.setExponent(null, -3.0);
		a_due.addUnordered(e_due_sec);
		
		return e_si_watt_unit;
	}
	
	ESi_unit getJouleUnit() throws SdaiException {
		ESi_energy_unit e_si_joule_unit = (ESi_energy_unit) outModel.createEntityInstance(CSi_energy_unit.class);
		e_si_joule_unit.setName(null, ESi_unit_name.JOULE);
		
		ADerived_unit_element a_due = e_si_joule_unit.createElements(null);
		
		EDerived_unit_element e_due_meter = (EDerived_unit_element) outModel.createEntityInstance(CDerived_unit_element.class);
		ESi_unit e_meter = getMeterUnit();
		e_due_meter.setUnit(null, e_meter);
		e_due_meter.setExponent(null, 2.0);
		a_due.addUnordered(e_due_meter);
		
		EDerived_unit_element e_due_kg = (EDerived_unit_element) outModel.createEntityInstance(CDerived_unit_element.class);
		ESi_unit e_kg = getKgUnit();
		e_due_kg.setUnit(null, e_kg);
		e_due_kg.setExponent(null, 1.0);
		a_due.addUnordered(e_due_kg);
		
		EDerived_unit_element e_due_sec = (EDerived_unit_element) outModel.createEntityInstance(CDerived_unit_element.class);
		ESi_unit e_sec = getSecUnit();
		e_due_sec.setUnit(null, e_sec);
		e_due_sec.setExponent(null, -2.0);
		a_due.addUnordered(e_due_sec);
		
		return e_si_joule_unit;
	}
	
	ESi_unit getVoltUnit() throws SdaiException
	{
		//ESi_unit e_siu = (ESi_unit) outModel.createEntityInstance(CSi_unit.class);
		ESi_electric_potential_unit e_si_volt_unit = (ESi_electric_potential_unit) outModel.createEntityInstance(CSi_electric_potential_unit.class);
		e_si_volt_unit.setName(null, ESi_unit_name.VOLT);
		ADerived_unit_element a_due = e_si_volt_unit.createElements(null);
		
		EDerived_unit_element e_due_meter = (EDerived_unit_element) outModel.createEntityInstance(CDerived_unit_element.class);
		ESi_unit e_meter = getMeterUnit();
		e_due_meter.setUnit(null, e_meter);
		e_due_meter.setExponent(null, 2.0);
		a_due.addUnordered(e_due_meter);
		
		EDerived_unit_element e_due_kg = (EDerived_unit_element) outModel.createEntityInstance(CDerived_unit_element.class);
		ESi_unit e_kg = getKgUnit();
		e_due_kg.setUnit(null, e_kg);
		e_due_kg.setExponent(null, 1.0);
		a_due.addUnordered(e_due_kg);
		
		EDerived_unit_element e_due_sec = (EDerived_unit_element) outModel.createEntityInstance(CDerived_unit_element.class);
		ESi_unit e_sec = getSecUnit();
		e_due_sec.setUnit(null, e_sec);
		e_due_sec.setExponent(null, -3.0);
		a_due.addUnordered(e_due_sec);
		
		EDerived_unit_element e_due_amp = (EDerived_unit_element) outModel.createEntityInstance(CDerived_unit_element.class);
		ESi_unit e_amp = getAmpUnit();
		e_due_amp.setUnit(null, e_amp);
		e_due_amp.setExponent(null, -1.0);
		a_due.addUnordered(e_due_amp);
		
		return e_si_volt_unit;
	}
	
	ESi_unit getOhmUnit() throws SdaiException
	{
		ESi_resistance_unit e_si_ohm_unit = (ESi_resistance_unit) outModel.createEntityInstance(CSi_resistance_unit.class);
		e_si_ohm_unit.setName(null, ESi_unit_name.OHM);
		ADerived_unit_element a_due = e_si_ohm_unit.createElements(null);
		
		EDerived_unit_element e_due_meter = (EDerived_unit_element) outModel.createEntityInstance(CDerived_unit_element.class);
		ESi_unit e_meter = getMeterUnit();
		e_due_meter.setUnit(null, e_meter);
		e_due_meter.setExponent(null, 2.0);
		a_due.addUnordered(e_due_meter);
		
		EDerived_unit_element e_due_kg = (EDerived_unit_element) outModel.createEntityInstance(CDerived_unit_element.class);
		ESi_unit e_kg = getKgUnit();
		e_due_kg.setUnit(null, e_kg);
		e_due_kg.setExponent(null, 1.0);
		a_due.addUnordered(e_due_kg);
		
		EDerived_unit_element e_due_sec = (EDerived_unit_element) outModel.createEntityInstance(CDerived_unit_element.class);
		ESi_unit e_sec = getSecUnit();
		e_due_sec.setUnit(null, e_sec);
		e_due_sec.setExponent(null, -3.0);
		a_due.addUnordered(e_due_sec);
		
		EDerived_unit_element e_due_amp = (EDerived_unit_element) outModel.createEntityInstance(CDerived_unit_element.class);
		ESi_unit e_amp = getAmpUnit();
		e_due_amp.setUnit(null, e_amp);
		e_due_amp.setExponent(null, -2.0);
		a_due.addUnordered(e_due_amp);
		
		return e_si_ohm_unit;
	}
	
	ESi_unit getHenryUnit() throws SdaiException
	{
		ESi_inductance_unit e_si_henry_unit = (ESi_inductance_unit) outModel.createEntityInstance(CSi_inductance_unit.class);
		e_si_henry_unit.setName(null, ESi_unit_name.HENRY);
		ADerived_unit_element a_due = e_si_henry_unit.createElements(null);
		
		EDerived_unit_element e_due_kg = (EDerived_unit_element) outModel.createEntityInstance(CDerived_unit_element.class);
		ESi_unit e_kg = getKgUnit();
		e_due_kg.setUnit(null, e_kg);
		e_due_kg.setExponent(null, 1.0);
		a_due.addUnordered(e_due_kg);
		
		EDerived_unit_element e_due_meter = (EDerived_unit_element) outModel.createEntityInstance(CDerived_unit_element.class);
		ESi_unit e_meter = getMeterUnit();
		e_due_meter.setUnit(null, e_meter);
		e_due_meter.setExponent(null, 2.0);
		a_due.addUnordered(e_due_meter);
		
		EDerived_unit_element e_due_sec = (EDerived_unit_element) outModel.createEntityInstance(CDerived_unit_element.class);
		ESi_unit e_sec = getSecUnit();
		e_due_sec.setUnit(null, e_sec);
		e_due_sec.setExponent(null, -2.0);
		a_due.addUnordered(e_due_sec);
		
		EDerived_unit_element e_due_amp = (EDerived_unit_element) outModel.createEntityInstance(CDerived_unit_element.class);
		ESi_unit e_amp = getAmpUnit();
		e_due_amp.setUnit(null, e_amp);
		e_due_amp.setExponent(null, -2.0);
		a_due.addUnordered(e_due_amp);
		
		return e_si_henry_unit;
	}
	
	ESi_unit getFaradUnit() throws SdaiException
	{
		ESi_capacitance_unit e_si_farad_unit = (ESi_capacitance_unit) outModel.createEntityInstance(CSi_capacitance_unit.class);
		e_si_farad_unit.setName(null, ESi_unit_name.FARAD);
		ADerived_unit_element a_due = e_si_farad_unit.createElements(null);
		
		EDerived_unit_element e_due_meter = (EDerived_unit_element) outModel.createEntityInstance(CDerived_unit_element.class);
		ESi_unit e_meter = getMeterUnit();
		e_due_meter.setUnit(null, e_meter);
		e_due_meter.setExponent(null, -2.0);
		a_due.addUnordered(e_due_meter);
		
		EDerived_unit_element e_due_kg = (EDerived_unit_element) outModel.createEntityInstance(CDerived_unit_element.class);
		ESi_unit e_kg = getKgUnit();
		e_due_kg.setUnit(null, e_kg);
		e_due_kg.setExponent(null, -1.0);
		a_due.addUnordered(e_due_kg);
		
		EDerived_unit_element e_due_sec = (EDerived_unit_element) outModel.createEntityInstance(CDerived_unit_element.class);
		ESi_unit e_sec = getSecUnit();
		e_due_sec.setUnit(null, e_sec);
		e_due_sec.setExponent(null, 4.0);
		a_due.addUnordered(e_due_sec);
		
		EDerived_unit_element e_due_amp = (EDerived_unit_element) outModel.createEntityInstance(CDerived_unit_element.class);
		ESi_unit e_amp = getAmpUnit();
		e_due_amp.setUnit(null, e_amp);
		e_due_amp.setExponent(null, 2.0);
		a_due.addUnordered(e_due_amp);
		
		return e_si_farad_unit;
	}
	
	ESi_unit getAmpUnit() throws SdaiException
	{
		ESi_unit e_amp_unit = (ESi_unit) outModel.createEntityInstance(CElectric_current_unit$si_unit.class);
		e_amp_unit.setName(null, ESi_unit_name.AMPERE);
		return e_amp_unit;
	}
	
	ESi_unit getKgUnit() throws SdaiException
	{
		ESi_unit e_kg_unit = (ESi_unit) outModel.createEntityInstance(CMass_unit$si_unit.class);
		e_kg_unit.setName(null, ESi_unit_name.GRAM);
		e_kg_unit.setPrefix(null, ESi_prefix.KILO);
		return e_kg_unit;
	}
	
	ESi_unit getSecUnit() throws SdaiException
	{
		ESi_unit e_sec_unit = (ESi_unit) outModel.createEntityInstance(CSi_unit$time_unit.class);
		e_sec_unit.setName(null, ESi_unit_name.SECOND);
		return e_sec_unit;
	}
	
	ESi_unit getMeterUnit() throws SdaiException
	{
		ESi_unit e_meter_unit = (ESi_unit) outModel.createEntityInstance(CLength_unit$si_unit.class);
		e_meter_unit.setName(null, ESi_unit_name.METRE);
		return e_meter_unit;
	}
	
	/**
	 * Return a singleton instance of length_unit containing the MIM mapping for
	 * the given Length.LengthUnit. Current implementation supports only 
	 * mm and inch units.
	 */
	ELength_unit getLengthUnit(Length.LengthUnit u) throws SdaiException
	{
		if (u == Length.LengthUnit.MM)
		{
			if (e_mm_length_unit != null)
				return e_mm_length_unit;
			
			EEntity e_e = outModel.createEntityInstance(CLength_unit$si_unit.class);
			e_mm_length_unit = (ELength_unit) e_e;
			ESi_unit e_siu = (ESi_unit) e_e;
			e_siu.setName(null, ESi_unit_name.METRE);
			e_siu.setPrefix(null, ESi_prefix.MILLI);
			return e_mm_length_unit;
		}
		else if (u == Length.LengthUnit.INCH)
		{
			if (e_inch_length_unit != null)
				return e_inch_length_unit;
			
			EEntity e_e = outModel.createEntityInstance(CConversion_based_unit$length_unit.class);
			e_inch_length_unit = (ELength_unit) e_e;
			EConversion_based_unit e_cbu = (EConversion_based_unit) e_e;
			e_cbu.setName(null, "INCH");
			EDimensional_exponents e_de = (EDimensional_exponents) outModel.createEntityInstance(CDimensional_exponents.class);
			e_de.setLength_exponent(null, 1.0);
			e_de.setMass_exponent(null, 0.0);
			e_de.setTime_exponent(null, 0.0);
			e_de.setElectric_current_exponent(null, 0.0);
			e_de.setThermodynamic_temperature_exponent(null, 0.0);
			e_de.setAmount_of_substance_exponent(null, 0.0);
			e_de.setLuminous_intensity_exponent(null, 0.0);
			e_cbu.setDimensions(null, e_de);
			ELength_measure_with_unit e_lmwu = (ELength_measure_with_unit) outModel.createEntityInstance(CLength_measure_with_unit.class);
			e_lmwu.setValue_component(null, 25.4, (ELength_measure) null);
			ELength_unit e_mm_unit = getLengthUnit(Length.LengthUnit.MM);
			e_lmwu.setUnit_component(null, e_mm_unit);
			e_cbu.setConversion_factor(null, e_lmwu);
			return e_inch_length_unit;
		}
		else throw new UnsupportedOperationException("SDAIwrite of unit unsupported at this time: "+u);
	}
	
	/**
	 * Return a singleton instance of ratio_unit.
	 * Populate ratio_unit.dimensions
	 */
	ERatio_unit getRatioUnit() throws SdaiException
	{
		if (e_ratio_unit == null)
		{
			e_ratio_unit = (ERatio_unit) outModel.createEntityInstance(CRatio_unit.class);
			EDimensional_exponents e_de = (EDimensional_exponents) outModel.createEntityInstance(CDimensional_exponents.class);
			e_de.setLength_exponent(null, 0.0);
			e_de.setMass_exponent(null, 0.0);
			e_de.setTime_exponent(null, 0.0);
			e_de.setElectric_current_exponent(null, 0.0);
			e_de.setThermodynamic_temperature_exponent(null, 0.0);
			e_de.setAmount_of_substance_exponent(null, 0.0);
			e_de.setLuminous_intensity_exponent(null, 0.0);
			e_ratio_unit.setDimensions(null, e_de);
		}
		return e_ratio_unit;
	}

	/**
	 * Add a descriptive_representation_item to the given representation.
	 * Sets descriptive_representation_item.name = driName
	 * Sets descriptive_representation_item.description = driDescription
	 */
	public void addMeasureRepresentationItem(ERepresentation e_r, String name, double value, Unit u) throws SdaiException
	{
		EMeasure_representation_item e_mri = null;
		if (u instanceof SiUnit)
		{
			e_mri = measureRepresentationItem(name, value, (SiUnit)u);
		}
		else if (u instanceof DerivedSiUnit)
			e_mri = measureRepresentationItem(name, value, (DerivedSiUnit)u);
		else
			throw new IllegalArgumentException("Unsupported unit: "+u);
		// only create new items aggregate if it does not already exist
		ARepresentation_item a_ri = null;
		if (e_r.testItems(null))
			a_ri = e_r.getItems(null);
		else
			a_ri = e_r.createItems(null);
		a_ri.addUnordered(e_mri);
	}
	
	void addRealRepresentationItem(ERepresentation e_r, String name, double value) throws SdaiException
	{
		EReal_representation_item e_rri = (EReal_representation_item) outModel.createEntityInstance(CReal_representation_item.class);;
		e_rri.setName(null, name);
		e_rri.setThe_value(null, value);
		// only create new items aggregate if it does not already exist
		ARepresentation_item a_ri = null;
		if (e_r.testItems(null))
			a_ri = e_r.getItems(null);
		else
			a_ri = e_r.createItems(null);
		a_ri.addUnordered(e_rri);
	}
	
	EMeasure_representation_item measureRepresentationItem(String name, double value, DerivedSiUnit u) throws SdaiException
	{
		EMeasure_representation_item e_mri = null;
		e_mri = (EMeasure_representation_item) outModel.createEntityInstance(CMeasure_representation_item.class);
		e_mri.setValue_component(null, value, (ENumeric_measure) null);
		e_mri.setName(null, name);
		EDerived_unit e_unit = getCompoundSiUnit(u);
		if (e_unit == null)
		{
			EDerived_unit e_unit2 = getCompoundSiUnit(u);
			throw new IllegalArgumentException("unsupported unit type: "+u.toString()+" name: "+name+" value: "+value);
		}
		e_mri.setUnit_component(null, e_unit);
		return e_mri;
	}
	
	EMeasure_representation_item measureRepresentationItem(String name, double value, SiUnit u) throws SdaiException
	{
		EMeasure_representation_item e_mri = null;
		if (u.base == SiUnit.SiBase.V)
		{
			e_mri = (EMeasure_representation_item) outModel.createEntityInstance(CElectric_potential_measure_with_unit$measure_representation_item.class);
			e_mri.setValue_component(null, value, (EElectric_potential_measure) null);
		}
		else if (u.base == SiUnit.SiBase.Ohm)
		{
			e_mri = (EMeasure_representation_item) outModel.createEntityInstance(CMeasure_representation_item$resistance_measure_with_unit.class);
			e_mri.setValue_component(null, value, (EResistance_measure) null);
		}
		else if (u.base == SiUnit.SiBase.degC)
		{
			e_mri = (EMeasure_representation_item) outModel.createEntityInstance(CMeasure_representation_item$thermodynamic_temperature_measure_with_unit.class);
			e_mri.setValue_component(null, value, (EThermodynamic_temperature_measure) null);
		}
		else if (u.base == SiUnit.SiBase.degK)
		{
			e_mri = (EMeasure_representation_item) outModel.createEntityInstance(CMeasure_representation_item$thermodynamic_temperature_measure_with_unit.class);
			e_mri.setValue_component(null, value, (EThermodynamic_temperature_measure) null);
		}
		else if (u.base == SiUnit.SiBase.A)
		{
			e_mri = (EMeasure_representation_item) outModel.createEntityInstance(CElectric_current_measure_with_unit$measure_representation_item.class);
			e_mri.setValue_component(null, value, (EElectric_current_measure) null);
		}
		else if (u.base == SiUnit.SiBase.Hz)
		{
			e_mri = (EMeasure_representation_item) outModel.createEntityInstance(CFrequency_measure_with_unit$measure_representation_item.class);
			e_mri.setValue_component(null, value, (EFrequency_measure) null);
		}
		else if (u.base == SiUnit.SiBase.F)
		{
			e_mri = (EMeasure_representation_item) outModel.createEntityInstance(CCapacitance_measure_with_unit$measure_representation_item.class);
			e_mri.setValue_component(null, value, (ECapacitance_measure) null);
		}
		else if (u.base == SiUnit.SiBase.S)
		{
			e_mri = (EMeasure_representation_item) outModel.createEntityInstance(CMeasure_representation_item$time_measure_with_unit.class);
			e_mri.setValue_component(null, value, (ETime_measure) null);
		}
		else if (u.base == SiUnit.SiBase.H)
		{
			e_mri = (EMeasure_representation_item) outModel.createEntityInstance(CInductance_measure_with_unit$measure_representation_item.class);
			e_mri.setValue_component(null, value, (EInductance_measure) null);
		}
		else
			throw new IllegalArgumentException("unsupported unit type: "+u.base+" name: "+name+" value: "+value);
		e_mri.setName(null, name);
		ESi_unit e_unit = getSiUnit(u);
		if (e_unit == null)
		{
			ESi_unit e_unit2 = getSiUnit(u);
			throw new IllegalArgumentException("unsupported unit type: "+u.base+" name: "+name+" value: "+value);
		}
		e_mri.setUnit_component(null, e_unit);
		return e_mri;
	}
	

}
