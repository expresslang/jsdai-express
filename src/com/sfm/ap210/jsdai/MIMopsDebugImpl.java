package com.sfm.ap210.jsdai;

import jsdai.lang.*;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.*;

/**
 * An implementation of the MIMops interface with debugging support.
 * @author James Stori, SFM Technology, Inc.
 * @version 1.3
 */
public class MIMopsDebugImpl implements MIMops {
private SdaiModel model;

public MIMopsDebugImpl(SdaiModel m) {
this.model = m;
}

public  AAssembly_component_usage  All_acu_referencing_st (EStructured_template e1, String v1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tAll_acu_referencing_st('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
		
	AAssembly_component_usage a_referencing = new AAssembly_component_usage();
	 AAssembly_component_usage a_return = new AAssembly_component_usage();
	CAssembly_component_usage.usedinRelating_product_definition(null, (EStructured_template)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EAssembly_component_usage e_return = (EAssembly_component_usage) a_referencing.getCurrentMemberEntity(it_entities);
		if ((e_return.testName(null)) && (e_return.getName(null).equals(v1)))
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			a_return.addUnordered(e_return);
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return a_return;
}

public  AAssembly_joint  All_aj_referencing_lamdv (ELayered_assembly_module_design_view e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tAll_aj_referencing_lamdv");
	System.out.println("\tGiven: "+e1.toString());
		
	AAssembly_joint a_referencing = new AAssembly_joint();
	 AAssembly_joint a_return = new AAssembly_joint();
	CAssembly_joint.usedinOf_shape(null, (ELayered_assembly_module_design_view)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EAssembly_joint e_return = (EAssembly_joint) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			a_return.addUnordered(e_return);
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return a_return;
}

public  AComponent_2d_location  All_c2dl_referencing_pds (EProduct_definition_shape e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tAll_c2dl_referencing_pds");
	System.out.println("\tGiven: "+e1.toString());
		
	AComponent_2d_location a_referencing = new AComponent_2d_location();
	 AComponent_2d_location a_return = new AComponent_2d_location();
	CComponent_2d_location.usedinRepresented_product_relation(null, (EProduct_definition_shape)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EComponent_2d_location e_return = (EComponent_2d_location) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			a_return.addUnordered(e_return);
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return a_return;
}

public  AClass All_c_relatedTo_p_through_aca (EProduct e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_c_relatedTo_p_through_aca");
	System.out.println("\tGiven: "+e1.toString());
	
	AApplied_classification_assignment a_relationship = new AApplied_classification_assignment();
	 AClass a_return = new AClass();
	CApplied_classification_assignment.usedinItems(null, (EProduct)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EApplied_classification_assignment e_relationship = (EApplied_classification_assignment) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getAssigned_class(null);
			if (e2.isKindOf(EClass.class))
			{
			EClass e_return = (EClass) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public ADatum All_d_inModel () throws SdaiException {
	System.out.println("");
	System.out.println("\tAll_d_inModel");
	
	ADatum a2 = (ADatum)model.getInstances(EDatum.class);
	ADatum a_return = new ADatum();
	SdaiIterator it_entities = a2.createIterator();
	while (it_entities.next()) 
	{
	EDatum e2 = (EDatum)a2.getCurrentMemberEntity(it_entities);	
		{
		
			a_return.addUnordered(e2);
		}
	}
	return a_return;
}

public ADimensional_location All_dl_inModel () throws SdaiException {
	System.out.println("");
	System.out.println("\tAll_dl_inModel");
	
	ADimensional_location a2 = (ADimensional_location)model.getInstances(EDimensional_location.class);
	ADimensional_location a_return = new ADimensional_location();
	SdaiIterator it_entities = a2.createIterator();
	while (it_entities.next()) 
	{
	EDimensional_location e2 = (EDimensional_location)a2.getCurrentMemberEntity(it_entities);	
		{
		
			a_return.addUnordered(e2);
		}
	}
	return a_return;
}

public ADimensional_size All_ds_inModel () throws SdaiException {
	System.out.println("");
	System.out.println("\tAll_ds_inModel");
	
	ADimensional_size a2 = (ADimensional_size)model.getInstances(EDimensional_size.class);
	ADimensional_size a_return = new ADimensional_size();
	SdaiIterator it_entities = a2.createIterator();
	while (it_entities.next()) 
	{
	EDimensional_size e2 = (EDimensional_size)a2.getCurrentMemberEntity(it_entities);	
		{
		
			a_return.addUnordered(e2);
		}
	}
	return a_return;
}

public  AFootprint_definition All_fd_relatedTo_p_through_pdr (EPackage e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_fd_relatedTo_p_through_pdr('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AProperty_definition_relationship a_relationship = new AProperty_definition_relationship();
	 AFootprint_definition a_return = new AFootprint_definition();
	CProperty_definition_relationship.usedinRelating_property_definition(null, (EPackage)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EProperty_definition_relationship e_relationship = (EProperty_definition_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testName(null)) && (e_relationship.getName(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelated_property_definition(null);
			if (e2.isKindOf(EFootprint_definition.class))
			{
			EFootprint_definition e_return = (EFootprint_definition) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  AFootprint_occurrence All_fo_relatedTo_limdv_through_nauor (ELayered_interconnect_module_design_view e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_fo_relatedTo_limdv_through_nauor");
	System.out.println("\tGiven: "+e1.toString());
	
	ANext_assembly_usage_occurrence_relationship a_relationship = new ANext_assembly_usage_occurrence_relationship();
	 AFootprint_occurrence a_return = new AFootprint_occurrence();
	CNext_assembly_usage_occurrence_relationship.usedinRelating_product_definition(null, (ELayered_interconnect_module_design_view)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		ENext_assembly_usage_occurrence_relationship e_relationship = (ENext_assembly_usage_occurrence_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getOccurrence(null);
			if (e2.isKindOf(EFootprint_occurrence.class))
			{
			EFootprint_occurrence e_return = (EFootprint_occurrence) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  AGeneric_laminate_text_component All_gltc_relatedTo_ltsc_through_nauor (ELaminate_text_string_component e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_gltc_relatedTo_ltsc_through_nauor");
	System.out.println("\tGiven: "+e1.toString());
	
	ANext_assembly_usage_occurrence_relationship a_relationship = new ANext_assembly_usage_occurrence_relationship();
	 AGeneric_laminate_text_component a_return = new AGeneric_laminate_text_component();
	CNext_assembly_usage_occurrence_relationship.usedinRelating_product_definition(null, (ELaminate_text_string_component)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		ENext_assembly_usage_occurrence_relationship e_relationship = (ENext_assembly_usage_occurrence_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getOccurrence(null);
			if (e2.isKindOf(EGeneric_laminate_text_component.class))
			{
			EGeneric_laminate_text_component e_return = (EGeneric_laminate_text_component) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public AGeometric_tolerance All_gt_inModel () throws SdaiException {
	System.out.println("");
	System.out.println("\tAll_gt_inModel");
	
	AGeometric_tolerance a2 = (AGeometric_tolerance)model.getInstances(EGeometric_tolerance.class);
	AGeometric_tolerance a_return = new AGeometric_tolerance();
	SdaiIterator it_entities = a2.createIterator();
	while (it_entities.next()) 
	{
	EGeometric_tolerance e2 = (EGeometric_tolerance)a2.getCurrentMemberEntity(it_entities);	
		{
		
			a_return.addUnordered(e2);
		}
	}
	return a_return;
}

public  AId_attribute  All_ia_referencing_sa (EShape_aspect e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tAll_ia_referencing_sa");
	System.out.println("\tGiven: "+e1.toString());
		
	AId_attribute a_referencing = new AId_attribute();
	 AId_attribute a_return = new AId_attribute();
	CId_attribute.usedinIdentified_item(null, (EShape_aspect)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EId_attribute e_return = (EId_attribute) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			a_return.addUnordered(e_return);
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return a_return;
}

public  AItem_identified_representation_usage  All_iiru_referencing_af (EAdvanced_face e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tAll_iiru_referencing_af");
	System.out.println("\tGiven: "+e1.toString());
		
	AItem_identified_representation_usage a_referencing = new AItem_identified_representation_usage();
	 AItem_identified_representation_usage a_return = new AItem_identified_representation_usage();
	CItem_identified_representation_usage.usedinIdentified_item(null, (EAdvanced_face)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EItem_identified_representation_usage e_return = (EItem_identified_representation_usage) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			a_return.addUnordered(e_return);
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return a_return;
}

public  AItem_identified_representation_usage  All_iiru_referencing_sa (EShape_aspect e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tAll_iiru_referencing_sa");
	System.out.println("\tGiven: "+e1.toString());
		
	AItem_identified_representation_usage a_referencing = new AItem_identified_representation_usage();
	 AItem_identified_representation_usage a_return = new AItem_identified_representation_usage();
	CItem_identified_representation_usage.usedinDefinition(null, (EShape_aspect)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EItem_identified_representation_usage e_return = (EItem_identified_representation_usage) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			a_return.addUnordered(e_return);
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return a_return;
}

public  AInterconnect_module_component  All_imc_referencing_pdf (EProduct_definition_formation e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tAll_imc_referencing_pdf");
	System.out.println("\tGiven: "+e1.toString());
		
	AInterconnect_module_component a_referencing = new AInterconnect_module_component();
	 AInterconnect_module_component a_return = new AInterconnect_module_component();
	CInterconnect_module_component.usedinFormation(null, (EProduct_definition_formation)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EInterconnect_module_component e_return = (EInterconnect_module_component) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			a_return.addUnordered(e_return);
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return a_return;
}

public  AInterconnect_module_component All_imc_relatedTo_lamdv_through_nauor (ELayered_assembly_module_design_view e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_imc_relatedTo_lamdv_through_nauor");
	System.out.println("\tGiven: "+e1.toString());
	
	ANext_assembly_usage_occurrence_relationship a_relationship = new ANext_assembly_usage_occurrence_relationship();
	 AInterconnect_module_component a_return = new AInterconnect_module_component();
	CNext_assembly_usage_occurrence_relationship.usedinRelating_product_definition(null, (ELayered_assembly_module_design_view)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		ENext_assembly_usage_occurrence_relationship e_relationship = (ENext_assembly_usage_occurrence_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getOccurrence(null);
			if (e2.isKindOf(EInterconnect_module_component.class))
			{
			EInterconnect_module_component e_return = (EInterconnect_module_component) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  AInterconnect_module_component All_imc_relatedTo_lipdv_through_nauor (ELayered_interconnect_panel_design_view e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_imc_relatedTo_lipdv_through_nauor");
	System.out.println("\tGiven: "+e1.toString());
	
	ANext_assembly_usage_occurrence_relationship a_relationship = new ANext_assembly_usage_occurrence_relationship();
	 AInterconnect_module_component a_return = new AInterconnect_module_component();
	CNext_assembly_usage_occurrence_relationship.usedinRelating_product_definition(null, (ELayered_interconnect_panel_design_view)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		ENext_assembly_usage_occurrence_relationship e_relationship = (ENext_assembly_usage_occurrence_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getOccurrence(null);
			if (e2.isKindOf(EInterconnect_module_component.class))
			{
			EInterconnect_module_component e_return = (EInterconnect_module_component) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  AJoin_shape_aspect All_jsa_relatedTo_pn_through_sar (EPhysical_network e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_jsa_relatedTo_pn_through_sar('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_aspect_relationship a_relationship = new AShape_aspect_relationship();
	 AJoin_shape_aspect a_return = new AJoin_shape_aspect();
	CShape_aspect_relationship.usedinRelating_shape_aspect(null, (EPhysical_network)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_aspect_relationship e_relationship = (EShape_aspect_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testName(null)) && (e_relationship.getName(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelated_shape_aspect(null);
			if (e2.isKindOf(EJoin_shape_aspect.class))
			{
			EJoin_shape_aspect e_return = (EJoin_shape_aspect) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  ALayered_assembly_module_design_view  All_lamdv_referencing_pdf (EProduct_definition_formation e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tAll_lamdv_referencing_pdf");
	System.out.println("\tGiven: "+e1.toString());
		
	ALayered_assembly_module_design_view a_referencing = new ALayered_assembly_module_design_view();
	 ALayered_assembly_module_design_view a_return = new ALayered_assembly_module_design_view();
	CLayered_assembly_module_design_view.usedinFormation(null, (EProduct_definition_formation)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		ELayered_assembly_module_design_view e_return = (ELayered_assembly_module_design_view) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			a_return.addUnordered(e_return);
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return a_return;
}

public  ALaminate_component All_lc_relatedTo_slc_through_slcsar (EStructured_layout_component e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_lc_relatedTo_slc_through_slcsar");
	System.out.println("\tGiven: "+e1.toString());
	
	AStructured_layout_component_sub_assembly_relationship a_relationship = new AStructured_layout_component_sub_assembly_relationship();
	 ALaminate_component a_return = new ALaminate_component();
	CStructured_layout_component_sub_assembly_relationship.usedinRelating_product_definition(null, (EStructured_layout_component)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EStructured_layout_component_sub_assembly_relationship e_relationship = (EStructured_layout_component_sub_assembly_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getOccurrence(null);
			if (e2.isKindOf(ELaminate_component.class))
			{
			ELaminate_component e_return = (ELaminate_component) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  ALaminate_component_feature All_lcf_relatedTo_jsa_through_sar (EJoin_shape_aspect e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_lcf_relatedTo_jsa_through_sar('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_aspect_relationship a_relationship = new AShape_aspect_relationship();
	 ALaminate_component_feature a_return = new ALaminate_component_feature();
	CShape_aspect_relationship.usedinRelated_shape_aspect(null, (EJoin_shape_aspect)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_aspect_relationship e_relationship = (EShape_aspect_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testName(null)) && (e_relationship.getName(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelating_shape_aspect(null);
			if (e2.isKindOf(ELaminate_component_feature.class))
			{
			ELaminate_component_feature e_return = (ELaminate_component_feature) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  ALaminate_component_feature All_lcf_relatedTo_lcp_through_sar (ELayer_connection_point e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_lcf_relatedTo_lcp_through_sar('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_aspect_relationship a_relationship = new AShape_aspect_relationship();
	 ALaminate_component_feature a_return = new ALaminate_component_feature();
	CShape_aspect_relationship.usedinRelating_shape_aspect(null, (ELayer_connection_point)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_aspect_relationship e_relationship = (EShape_aspect_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testName(null)) && (e_relationship.getName(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelated_shape_aspect(null);
			if (e2.isKindOf(ELaminate_component_feature.class))
			{
			ELaminate_component_feature e_return = (ELaminate_component_feature) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  ALaminate_component_feature All_lcf_relatedTo_pn_through_sar (EPhysical_network e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_lcf_relatedTo_pn_through_sar('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_aspect_relationship a_relationship = new AShape_aspect_relationship();
	 ALaminate_component_feature a_return = new ALaminate_component_feature();
	CShape_aspect_relationship.usedinRelating_shape_aspect(null, (EPhysical_network)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_aspect_relationship e_relationship = (EShape_aspect_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testName(null)) && (e_relationship.getName(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelated_shape_aspect(null);
			if (e2.isKindOf(ELaminate_component_feature.class))
			{
			ELaminate_component_feature e_return = (ELaminate_component_feature) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  ALaminate_component_join_terminal All_lcjt_relatedTo_pn_through_sar (EPhysical_network e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_lcjt_relatedTo_pn_through_sar('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_aspect_relationship a_relationship = new AShape_aspect_relationship();
	 ALaminate_component_join_terminal a_return = new ALaminate_component_join_terminal();
	CShape_aspect_relationship.usedinRelating_shape_aspect(null, (EPhysical_network)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_aspect_relationship e_relationship = (EShape_aspect_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testName(null)) && (e_relationship.getName(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelated_shape_aspect(null);
			if (e2.isKindOf(ELaminate_component_join_terminal.class))
			{
			ELaminate_component_join_terminal e_return = (ELaminate_component_join_terminal) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  ALayer_connection_point All_lcp_relatedTo_jsa_through_sar (EJoin_shape_aspect e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_lcp_relatedTo_jsa_through_sar('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_aspect_relationship a_relationship = new AShape_aspect_relationship();
	 ALayer_connection_point a_return = new ALayer_connection_point();
	CShape_aspect_relationship.usedinRelating_shape_aspect(null, (EJoin_shape_aspect)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_aspect_relationship e_relationship = (EShape_aspect_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testName(null)) && (e_relationship.getName(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelated_shape_aspect(null);
			if (e2.isKindOf(ELayer_connection_point.class))
			{
			ELayer_connection_point e_return = (ELayer_connection_point) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  ALayer_connection_point All_lcp_relatedTo_jsa_through_sar_2 (EJoin_shape_aspect e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_lcp_relatedTo_jsa_through_sar_2('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_aspect_relationship a_relationship = new AShape_aspect_relationship();
	 ALayer_connection_point a_return = new ALayer_connection_point();
	CShape_aspect_relationship.usedinRelated_shape_aspect(null, (EJoin_shape_aspect)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_aspect_relationship e_relationship = (EShape_aspect_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testName(null)) && (e_relationship.getName(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelating_shape_aspect(null);
			if (e2.isKindOf(ELayer_connection_point.class))
			{
			ELayer_connection_point e_return = (ELayer_connection_point) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  ALayered_interconnect_module_design_view  All_limdv_referencing_pdf (EProduct_definition_formation e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tAll_limdv_referencing_pdf");
	System.out.println("\tGiven: "+e1.toString());
		
	ALayered_interconnect_module_design_view a_referencing = new ALayered_interconnect_module_design_view();
	 ALayered_interconnect_module_design_view a_return = new ALayered_interconnect_module_design_view();
	CLayered_interconnect_module_design_view.usedinFormation(null, (EProduct_definition_formation)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		ELayered_interconnect_module_design_view e_return = (ELayered_interconnect_module_design_view) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			a_return.addUnordered(e_return);
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return a_return;
}

public  ALaminate_text_string_component All_ltsc_relatedTo_limdv_through_nauor (ELayered_interconnect_module_design_view e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_ltsc_relatedTo_limdv_through_nauor");
	System.out.println("\tGiven: "+e1.toString());
	
	ANext_assembly_usage_occurrence_relationship a_relationship = new ANext_assembly_usage_occurrence_relationship();
	 ALaminate_text_string_component a_return = new ALaminate_text_string_component();
	CNext_assembly_usage_occurrence_relationship.usedinRelating_product_definition(null, (ELayered_interconnect_module_design_view)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		ENext_assembly_usage_occurrence_relationship e_relationship = (ENext_assembly_usage_occurrence_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelated_product_definition(null);
			if (e2.isKindOf(ELaminate_text_string_component.class))
			{
			ELaminate_text_string_component e_return = (ELaminate_text_string_component) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}



public  AMapped_item All_mi_referencedBy_sr (EShape_representation e1, String v1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tAll_mi_referencedBy_sr('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	
	AEntity a2 = e1.getItems(null);
	 AMapped_item a_return = new AMapped_item();
	SdaiIterator it_entities = a2.createIterator();
	while (it_entities.next()) 
	{
	EEntity e2 = a2.getCurrentMemberEntity(it_entities);	
	
	if (e2.isKindOf(EMapped_item.class))
	{
		EMapped_item e_return = (EMapped_item) e2;
		if ((e_return.testName(null)) && (e_return.getName(null).equals(v1)))
		System.out.println("\tReferenced: "+e_return.toString());
		a_return.addUnordered(e_return);
	}
	}
	return a_return;
}

public  AMaterial_removal_laminate_component All_mrlc_relatedTo_limdv_through_nauor (ELayered_interconnect_module_design_view e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_mrlc_relatedTo_limdv_through_nauor");
	System.out.println("\tGiven: "+e1.toString());
	
	ANext_assembly_usage_occurrence_relationship a_relationship = new ANext_assembly_usage_occurrence_relationship();
	 AMaterial_removal_laminate_component a_return = new AMaterial_removal_laminate_component();
	CNext_assembly_usage_occurrence_relationship.usedinRelating_product_definition(null, (ELayered_interconnect_module_design_view)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		ENext_assembly_usage_occurrence_relationship e_relationship = (ENext_assembly_usage_occurrence_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getOccurrence(null);
			if (e2.isKindOf(EMaterial_removal_laminate_component.class))
			{
			EMaterial_removal_laminate_component e_return = (EMaterial_removal_laminate_component) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  AMulti_stratum_special_symbol_component All_msssc_relatedTo_limdv_through_nauor (ELayered_interconnect_module_design_view e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_msssc_relatedTo_limdv_through_nauor");
	System.out.println("\tGiven: "+e1.toString());
	
	ANext_assembly_usage_occurrence_relationship a_relationship = new ANext_assembly_usage_occurrence_relationship();
	 AMulti_stratum_special_symbol_component a_return = new AMulti_stratum_special_symbol_component();
	CNext_assembly_usage_occurrence_relationship.usedinRelating_product_definition(null, (ELayered_interconnect_module_design_view)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		ENext_assembly_usage_occurrence_relationship e_relationship = (ENext_assembly_usage_occurrence_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getOccurrence(null);
			if (e2.isKindOf(EMulti_stratum_special_symbol_component.class))
			{
			EMulti_stratum_special_symbol_component e_return = (EMulti_stratum_special_symbol_component) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  ANext_assembly_usage_occurrence_relationship  All_nauor_referencing_pd (EProduct_definition e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tAll_nauor_referencing_pd");
	System.out.println("\tGiven: "+e1.toString());
		
	ANext_assembly_usage_occurrence_relationship a_referencing = new ANext_assembly_usage_occurrence_relationship();
	 ANext_assembly_usage_occurrence_relationship a_return = new ANext_assembly_usage_occurrence_relationship();
	CNext_assembly_usage_occurrence_relationship.usedinOccurrence(null, (EProduct_definition)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		ENext_assembly_usage_occurrence_relationship e_return = (ENext_assembly_usage_occurrence_relationship) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			a_return.addUnordered(e_return);
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return a_return;
}

public ANamed_unit All_nu_inModel () throws SdaiException {
	System.out.println("");
	System.out.println("\tAll_nu_inModel");
	
	ANamed_unit a2 = (ANamed_unit)model.getInstances(ENamed_unit.class);
	ANamed_unit a_return = new ANamed_unit();
	SdaiIterator it_entities = a2.createIterator();
	while (it_entities.next()) 
	{
	ENamed_unit e2 = (ENamed_unit)a2.getCurrentMemberEntity(it_entities);	
		{
		
			a_return.addUnordered(e2);
		}
	}
	return a_return;
}

public AProduct All_p_inModel () throws SdaiException {
	System.out.println("");
	System.out.println("\tAll_p_inModel");
	
	AProduct a2 = (AProduct)model.getInstances(EProduct.class);
	AProduct a_return = new AProduct();
	SdaiIterator it_entities = a2.createIterator();
	while (it_entities.next()) 
	{
	EProduct e2 = (EProduct)a2.getCurrentMemberEntity(it_entities);	
		{
		
			a_return.addUnordered(e2);
		}
	}
	return a_return;
}

public APackage All_p_inModel_2 () throws SdaiException {
	System.out.println("");
	System.out.println("\tAll_p_inModel_2");
	
	APackage a2 = (APackage)model.getInstances(EPackage.class);
	APackage a_return = new APackage();
	SdaiIterator it_entities = a2.createIterator();
	while (it_entities.next()) 
	{
	EPackage e2 = (EPackage)a2.getCurrentMemberEntity(it_entities);	
		{
		
			a_return.addUnordered(e2);
		}
	}
	return a_return;
}


public  AProduct All_p_referencedBy_prpc (EProduct_related_product_category e1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tAll_p_referencedBy_prpc");
	System.out.println("\tGiven: "+e1.toString());
	
	
	AEntity a2 = e1.getProducts(null);
	 AProduct a_return = new AProduct();
	SdaiIterator it_entities = a2.createIterator();
	while (it_entities.next()) 
	{
	EEntity e2 = a2.getCurrentMemberEntity(it_entities);	
	
	if (e2.isKindOf(EProduct.class))
	{
		EProduct e_return = (EProduct) e2;
		
		System.out.println("\tReferenced: "+e_return.toString());
		a_return.addUnordered(e_return);
	}
	}
	return a_return;
}

public  APackaged_component All_pc_relatedTo_lamdv_through_nauor (ELayered_assembly_module_design_view e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_pc_relatedTo_lamdv_through_nauor");
	System.out.println("\tGiven: "+e1.toString());
	
	ANext_assembly_usage_occurrence_relationship a_relationship = new ANext_assembly_usage_occurrence_relationship();
	 APackaged_component a_return = new APackaged_component();
	CNext_assembly_usage_occurrence_relationship.usedinRelating_product_definition(null, (ELayered_assembly_module_design_view)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		ENext_assembly_usage_occurrence_relationship e_relationship = (ENext_assembly_usage_occurrence_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getOccurrence(null);
			if (e2.isKindOf(EPackaged_component.class))
			{
			EPackaged_component e_return = (EPackaged_component) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  APhysical_component_terminal  All_pct_referencing_imc (EInterconnect_module_component e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tAll_pct_referencing_imc");
	System.out.println("\tGiven: "+e1.toString());
		
	APhysical_component_terminal a_referencing = new APhysical_component_terminal();
	 APhysical_component_terminal a_return = new APhysical_component_terminal();
	CPhysical_component_terminal.usedinOf_shape(null, (EInterconnect_module_component)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EPhysical_component_terminal e_return = (EPhysical_component_terminal) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			a_return.addUnordered(e_return);
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return a_return;
}

public  AProperty_definition  All_pd_referencing_acu (EAssembly_component_usage e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tAll_pd_referencing_acu");
	System.out.println("\tGiven: "+e1.toString());
		
	AProperty_definition a_referencing = new AProperty_definition();
	 AProperty_definition a_return = new AProperty_definition();
	CProperty_definition.usedinDefinition(null, (EAssembly_component_usage)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EProperty_definition e_return = (EProperty_definition) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			a_return.addUnordered(e_return);
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return a_return;
}

public  AProperty_definition  All_pd_referencing_p (EPackage e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tAll_pd_referencing_p");
	System.out.println("\tGiven: "+e1.toString());
		
	AProperty_definition a_referencing = new AProperty_definition();
	 AProperty_definition a_return = new AProperty_definition();
	CProperty_definition.usedinDefinition(null, (EPackage)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EProperty_definition e_return = (EProperty_definition) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			a_return.addUnordered(e_return);
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return a_return;
}

public  AProduct_definition  All_pd_referencing_pdf (EProduct_definition_formation e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tAll_pd_referencing_pdf");
	System.out.println("\tGiven: "+e1.toString());
		
	AProduct_definition a_referencing = new AProduct_definition();
	 AProduct_definition a_return = new AProduct_definition();
	CProduct_definition.usedinFormation(null, (EProduct_definition_formation)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EProduct_definition e_return = (EProduct_definition) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			a_return.addUnordered(e_return);
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return a_return;
}

public  AProperty_definition  All_pd_referencing_pttd (EPackage_terminal_template_definition e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tAll_pd_referencing_pttd");
	System.out.println("\tGiven: "+e1.toString());
		
	AProperty_definition a_referencing = new AProperty_definition();
	 AProperty_definition a_return = new AProperty_definition();
	CProperty_definition.usedinDefinition(null, (EPackage_terminal_template_definition)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EProperty_definition e_return = (EProperty_definition) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			a_return.addUnordered(e_return);
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return a_return;
}

public  AProperty_definition  All_pd_referencing_sa (EShape_aspect e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tAll_pd_referencing_sa");
	System.out.println("\tGiven: "+e1.toString());
		
	AProperty_definition a_referencing = new AProperty_definition();
	 AProperty_definition a_return = new AProperty_definition();
	CProperty_definition.usedinDefinition(null, (EShape_aspect)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EProperty_definition e_return = (EProperty_definition) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			a_return.addUnordered(e_return);
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return a_return;
}

public  AProduct_definition All_pd_relatedTo_lamdv_through_nauor (ELayered_assembly_module_design_view e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_pd_relatedTo_lamdv_through_nauor");
	System.out.println("\tGiven: "+e1.toString());
	
	ANext_assembly_usage_occurrence_relationship a_relationship = new ANext_assembly_usage_occurrence_relationship();
	 AProduct_definition a_return = new AProduct_definition();
	CNext_assembly_usage_occurrence_relationship.usedinRelating_product_definition(null, (ELayered_assembly_module_design_view)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		ENext_assembly_usage_occurrence_relationship e_relationship = (ENext_assembly_usage_occurrence_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getOccurrence(null);
			if (e2.isKindOf(EProduct_definition.class))
			{
			EProduct_definition e_return = (EProduct_definition) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  AProperty_definition All_pd_relatedTo_sr_through_pdr (EShape_representation e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_pd_relatedTo_sr_through_pdr");
	System.out.println("\tGiven: "+e1.toString());
	
	AProperty_definition_representation a_relationship = new AProperty_definition_representation();
	 AProperty_definition a_return = new AProperty_definition();
	CProperty_definition_representation.usedinUsed_representation(null, (EShape_representation)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EProperty_definition_representation e_relationship = (EProperty_definition_representation) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getDefinition(null);
			if (e2.isKindOf(EProperty_definition.class))
			{
			EProperty_definition e_return = (EProperty_definition) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  AProduct_definition_context_association  All_pdca_referencing_pd (EProduct_definition e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tAll_pdca_referencing_pd");
	System.out.println("\tGiven: "+e1.toString());
		
	AProduct_definition_context_association a_referencing = new AProduct_definition_context_association();
	 AProduct_definition_context_association a_return = new AProduct_definition_context_association();
	CProduct_definition_context_association.usedinDefinition(null, (EProduct_definition)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EProduct_definition_context_association e_return = (EProduct_definition_context_association) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			a_return.addUnordered(e_return);
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return a_return;
}

public  AProduct_definition_shape  All_pds_referencing_acu (EAssembly_component_usage e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tAll_pds_referencing_acu");
	System.out.println("\tGiven: "+e1.toString());
		
	AProduct_definition_shape a_referencing = new AProduct_definition_shape();
	 AProduct_definition_shape a_return = new AProduct_definition_shape();
	CProduct_definition_shape.usedinDefinition(null, (EAssembly_component_usage)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EProduct_definition_shape e_return = (EProduct_definition_shape) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			a_return.addUnordered(e_return);
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return a_return;
}

public  AProduct_definition_shape  All_pds_referencing_nauor (ENext_assembly_usage_occurrence_relationship e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tAll_pds_referencing_nauor");
	System.out.println("\tGiven: "+e1.toString());
		
	AProduct_definition_shape a_referencing = new AProduct_definition_shape();
	 AProduct_definition_shape a_return = new AProduct_definition_shape();
	CProduct_definition_shape.usedinDefinition(null, (ENext_assembly_usage_occurrence_relationship)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EProduct_definition_shape e_return = (EProduct_definition_shape) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			a_return.addUnordered(e_return);
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return a_return;
}

public  APhysical_network  All_pn_referencing_limdv (ELayered_interconnect_module_design_view e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tAll_pn_referencing_limdv");
	System.out.println("\tGiven: "+e1.toString());
		
	APhysical_network a_referencing = new APhysical_network();
	 APhysical_network a_return = new APhysical_network();
	CPhysical_network.usedinOf_shape(null, (ELayered_interconnect_module_design_view)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EPhysical_network e_return = (EPhysical_network) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			a_return.addUnordered(e_return);
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return a_return;
}

public  APadstack_occurrence All_po_relatedTo_limdv_through_nauor (ELayered_interconnect_module_design_view e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_po_relatedTo_limdv_through_nauor");
	System.out.println("\tGiven: "+e1.toString());
	
	ANext_assembly_usage_occurrence_relationship a_relationship = new ANext_assembly_usage_occurrence_relationship();
	 APadstack_occurrence a_return = new APadstack_occurrence();
	CNext_assembly_usage_occurrence_relationship.usedinRelating_product_definition(null, (ELayered_interconnect_module_design_view)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		ENext_assembly_usage_occurrence_relationship e_relationship = (ENext_assembly_usage_occurrence_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getOccurrence(null);
			if (e2.isKindOf(EPadstack_occurrence.class))
			{
			EPadstack_occurrence e_return = (EPadstack_occurrence) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  APlated_passage_dependent_land All_ppdl_relatedTo_isf_through_pdr (EInter_stratum_feature e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_ppdl_relatedTo_isf_through_pdr('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AProduct_definition_relationship a_relationship = new AProduct_definition_relationship();
	 APlated_passage_dependent_land a_return = new APlated_passage_dependent_land();
	CProduct_definition_relationship.usedinRelating_product_definition(null, (EInter_stratum_feature)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EProduct_definition_relationship e_relationship = (EProduct_definition_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testName(null)) && (e_relationship.getName(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelated_product_definition(null);
			if (e2.isKindOf(EPlated_passage_dependent_land.class))
			{
			EPlated_passage_dependent_land e_return = (EPlated_passage_dependent_land) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public AProduct_related_product_category All_prpc_inModel (String v1) throws SdaiException {
	System.out.println("");
	System.out.println("\tAll_prpc_inModel('"+v1+"')");
	
	AProduct_related_product_category a2 = (AProduct_related_product_category)model.getInstances(EProduct_related_product_category.class);
	AProduct_related_product_category a_return = new AProduct_related_product_category();
	SdaiIterator it_entities = a2.createIterator();
	while (it_entities.next()) 
	{
	EProduct_related_product_category e2 = (EProduct_related_product_category)a2.getCurrentMemberEntity(it_entities);	
		{
		if ((e2.testName(null)) && (e2.getName(null).equals(v1)))
			a_return.addUnordered(e2);
		}
	}
	return a_return;
}

public  AProduct_related_product_category  All_prpc_referencing_p (EProduct e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tAll_prpc_referencing_p");
	System.out.println("\tGiven: "+e1.toString());
		
	AProduct_related_product_category a_referencing = new AProduct_related_product_category();
	 AProduct_related_product_category a_return = new AProduct_related_product_category();
	CProduct_related_product_category.usedinProducts(null, (EProduct)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EProduct_related_product_category e_return = (EProduct_related_product_category) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			a_return.addUnordered(e_return);
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return a_return;
}

public  AProduct_specific_parameter_value_assignment  All_pspva_referencing_p (EProduct e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tAll_pspva_referencing_p");
	System.out.println("\tGiven: "+e1.toString());
		
	AProduct_specific_parameter_value_assignment a_referencing = new AProduct_specific_parameter_value_assignment();
	 AProduct_specific_parameter_value_assignment a_return = new AProduct_specific_parameter_value_assignment();
	CProduct_specific_parameter_value_assignment.usedinProducts(null, (EProduct)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EProduct_specific_parameter_value_assignment e_return = (EProduct_specific_parameter_value_assignment) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			a_return.addUnordered(e_return);
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return a_return;
}

public  APackage_terminal  All_pt_referencing_p (EPackage e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tAll_pt_referencing_p");
	System.out.println("\tGiven: "+e1.toString());
		
	APackage_terminal a_referencing = new APackage_terminal();
	 APackage_terminal a_return = new APackage_terminal();
	CPackage_terminal.usedinOf_shape(null, (EPackage)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EPackage_terminal e_return = (EPackage_terminal) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			a_return.addUnordered(e_return);
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return a_return;
}

public  APart_tooling_feature All_ptf_relatedTo_sr_through_ucur (EShape_representation e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_ptf_relatedTo_sr_through_ucur");
	System.out.println("\tGiven: "+e1.toString());
	
	AUsage_concept_usage_relationship a_relationship = new AUsage_concept_usage_relationship();
	 APart_tooling_feature a_return = new APart_tooling_feature();
	CUsage_concept_usage_relationship.usedinUsed_representation(null, (EShape_representation)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EUsage_concept_usage_relationship e_relationship = (EUsage_concept_usage_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getDefinition(null);
			if (e2.isKindOf(EPart_tooling_feature.class))
			{
			EPart_tooling_feature e_return = (EPart_tooling_feature) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  ARepresentation All_r_relatedTo_sr_through_rr (EShape_representation e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_r_relatedTo_sr_through_rr");
	System.out.println("\tGiven: "+e1.toString());
	
	ARepresentation_relationship a_relationship = new ARepresentation_relationship();
	 ARepresentation a_return = new ARepresentation();
	CRepresentation_relationship.usedinRep_1(null, (EShape_representation)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		ERepresentation_relationship e_relationship = (ERepresentation_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRep_2(null);
			if (e2.isKindOf(ERepresentation.class))
			{
			ERepresentation e_return = (ERepresentation) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}



public  ARepresentation_item All_ri_referencedBy_r (ERepresentation e1, String v1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tAll_ri_referencedBy_r('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	
	AEntity a2 = e1.getItems(null);
	 ARepresentation_item a_return = new ARepresentation_item();
	SdaiIterator it_entities = a2.createIterator();
	while (it_entities.next()) 
	{
	EEntity e2 = a2.getCurrentMemberEntity(it_entities);	
	
	if (e2.isKindOf(ERepresentation_item.class))
	{
		ERepresentation_item e_return = (ERepresentation_item) e2;
		if ((e_return.testName(null)) && (e_return.getName(null).equals(v1)))
		System.out.println("\tReferenced: "+e_return.toString());
		a_return.addUnordered(e_return);
	}
	}
	return a_return;
}

public  AStratum All_s_relatedTo_limdv_through_acu (ELayered_interconnect_module_design_view e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_s_relatedTo_limdv_through_acu('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AAssembly_component_usage a_relationship = new AAssembly_component_usage();
	 AStratum a_return = new AStratum();
	CAssembly_component_usage.usedinRelating_product_definition(null, (ELayered_interconnect_module_design_view)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EAssembly_component_usage e_relationship = (EAssembly_component_usage) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testName(null)) && (e_relationship.getName(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelated_product_definition(null);
			if (e2.isKindOf(EStratum.class))
			{
			EStratum e_return = (EStratum) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  AShape_aspect  All_sa_referencing_pds (EProduct_definition_shape e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tAll_sa_referencing_pds");
	System.out.println("\tGiven: "+e1.toString());
		
	AShape_aspect a_referencing = new AShape_aspect();
	 AShape_aspect a_return = new AShape_aspect();
	CShape_aspect.usedinOf_shape(null, (EProduct_definition_shape)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EShape_aspect e_return = (EShape_aspect) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			a_return.addUnordered(e_return);
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return a_return;
}

public  AShape_aspect All_sa_relatedTo_af_through_iiru (EAdvanced_face e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_sa_relatedTo_af_through_iiru");
	System.out.println("\tGiven: "+e1.toString());
	
	AItem_identified_representation_usage a_relationship = new AItem_identified_representation_usage();
	 AShape_aspect a_return = new AShape_aspect();
	CItem_identified_representation_usage.usedinIdentified_item(null, (EAdvanced_face)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EItem_identified_representation_usage e_relationship = (EItem_identified_representation_usage) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getDefinition(null);
			if (e2.isKindOf(EShape_aspect.class))
			{
			EShape_aspect e_return = (EShape_aspect) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  AShape_aspect All_sa_relatedTo_cgsa_through_sar (EComposite_group_shape_aspect e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_sa_relatedTo_cgsa_through_sar");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_aspect_relationship a_relationship = new AShape_aspect_relationship();
	 AShape_aspect a_return = new AShape_aspect();
	CShape_aspect_relationship.usedinRelating_shape_aspect(null, (EComposite_group_shape_aspect)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_aspect_relationship e_relationship = (EShape_aspect_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelated_shape_aspect(null);
			if (e2.isKindOf(EShape_aspect.class))
			{
			EShape_aspect e_return = (EShape_aspect) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  AShape_aspect All_sa_relatedTo_dsa_through_sadr (EDerived_shape_aspect e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_sa_relatedTo_dsa_through_sadr");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_aspect_deriving_relationship a_relationship = new AShape_aspect_deriving_relationship();
	 AShape_aspect a_return = new AShape_aspect();
	CShape_aspect_deriving_relationship.usedinRelating_shape_aspect(null, (EDerived_shape_aspect)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_aspect_deriving_relationship e_relationship = (EShape_aspect_deriving_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelated_shape_aspect(null);
			if (e2.isKindOf(EShape_aspect.class))
			{
			EShape_aspect e_return = (EShape_aspect) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  AShape_aspect All_sa_relatedTo_sa_through_sar (EShape_aspect e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_sa_relatedTo_sa_through_sar");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_aspect_relationship a_relationship = new AShape_aspect_relationship();
	 AShape_aspect a_return = new AShape_aspect();
	CShape_aspect_relationship.usedinRelating_shape_aspect(null, (EShape_aspect)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_aspect_relationship e_relationship = (EShape_aspect_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelated_shape_aspect(null);
			if (e2.isKindOf(EShape_aspect.class))
			{
			EShape_aspect e_return = (EShape_aspect) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  AStratum_feature_template_component All_sftc_relatedTo_limdv_through_nauor (ELayered_interconnect_module_design_view e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_sftc_relatedTo_limdv_through_nauor");
	System.out.println("\tGiven: "+e1.toString());
	
	ANext_assembly_usage_occurrence_relationship a_relationship = new ANext_assembly_usage_occurrence_relationship();
	 AStratum_feature_template_component a_return = new AStratum_feature_template_component();
	CNext_assembly_usage_occurrence_relationship.usedinRelating_product_definition(null, (ELayered_interconnect_module_design_view)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		ENext_assembly_usage_occurrence_relationship e_relationship = (ENext_assembly_usage_occurrence_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getOccurrence(null);
			if (e2.isKindOf(EStratum_feature_template_component.class))
			{
			EStratum_feature_template_component e_return = (EStratum_feature_template_component) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  AStructured_layout_component All_slc_relatedTo_limdv_through_nauor (ELayered_interconnect_module_design_view e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_slc_relatedTo_limdv_through_nauor");
	System.out.println("\tGiven: "+e1.toString());
	
	ANext_assembly_usage_occurrence_relationship a_relationship = new ANext_assembly_usage_occurrence_relationship();
	 AStructured_layout_component a_return = new AStructured_layout_component();
	CNext_assembly_usage_occurrence_relationship.usedinRelating_product_definition(null, (ELayered_interconnect_module_design_view)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		ENext_assembly_usage_occurrence_relationship e_relationship = (ENext_assembly_usage_occurrence_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getOccurrence(null);
			if (e2.isKindOf(EStructured_layout_component.class))
			{
			EStructured_layout_component e_return = (EStructured_layout_component) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  AStructured_layout_component All_slc_relatedTo_slc_through_slcsar (EStructured_layout_component e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_slc_relatedTo_slc_through_slcsar");
	System.out.println("\tGiven: "+e1.toString());
	
	AStructured_layout_component_sub_assembly_relationship a_relationship = new AStructured_layout_component_sub_assembly_relationship();
	 AStructured_layout_component a_return = new AStructured_layout_component();
	CStructured_layout_component_sub_assembly_relationship.usedinRelating_product_definition(null, (EStructured_layout_component)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EStructured_layout_component_sub_assembly_relationship e_relationship = (EStructured_layout_component_sub_assembly_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getOccurrence(null);
			if (e2.isKindOf(EStructured_layout_component.class))
			{
			EStructured_layout_component e_return = (EStructured_layout_component) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  AShape_representation  All_sr_referencing_mi (EMapped_item e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tAll_sr_referencing_mi");
	System.out.println("\tGiven: "+e1.toString());
		
	AShape_representation a_referencing = new AShape_representation();
	 AShape_representation a_return = new AShape_representation();
	CShape_representation.usedinItems(null, (EMapped_item)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EShape_representation e_return = (EShape_representation) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			a_return.addUnordered(e_return);
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return a_return;
}

public  AShape_representation All_sr_relatedTo_p_through_sdr (EPackage e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_sr_relatedTo_p_through_sdr");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_definition_representation a_relationship = new AShape_definition_representation();
	 AShape_representation a_return = new AShape_representation();
	CShape_definition_representation.usedinDefinition(null, (EPackage)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_definition_representation e_relationship = (EShape_definition_representation) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getUsed_representation(null);
			if (e2.isKindOf(EShape_representation.class))
			{
			EShape_representation e_return = (EShape_representation) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  AShape_representation All_sr_relatedTo_pds_through_sdr (EProduct_definition_shape e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_sr_relatedTo_pds_through_sdr");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_definition_representation a_relationship = new AShape_definition_representation();
	 AShape_representation a_return = new AShape_representation();
	CShape_definition_representation.usedinDefinition(null, (EProduct_definition_shape)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_definition_representation e_relationship = (EShape_definition_representation) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getUsed_representation(null);
			if (e2.isKindOf(EShape_representation.class))
			{
			EShape_representation e_return = (EShape_representation) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  AShape_representation All_sr_relatedTo_pds_through_sdr_2 (EProduct_definition_shape e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_sr_relatedTo_pds_through_sdr_2");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_definition_representation a_relationship = new AShape_definition_representation();
	 AShape_representation a_return = new AShape_representation();
	CShape_definition_representation.usedinDefinition(null, (EProduct_definition_shape)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_definition_representation e_relationship = (EShape_definition_representation) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getUsed_representation(null);
			if (e2.isKindOf(EShape_representation.class))
			{
			EShape_representation e_return = (EShape_representation) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  AShape_representation All_sr_relatedTo_sr_through_rr (EShape_representation e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_sr_relatedTo_sr_through_rr('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	ARepresentation_relationship a_relationship = new ARepresentation_relationship();
	 AShape_representation a_return = new AShape_representation();
	CRepresentation_relationship.usedinRep_1(null, (EShape_representation)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		ERepresentation_relationship e_relationship = (ERepresentation_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testName(null)) && (e_relationship.getName(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRep_2(null);
			if (e2.isKindOf(EShape_representation.class))
			{
			EShape_representation e_return = (EShape_representation) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  AShape_representation All_sr_relatedTo_sr_through_srr (EShape_representation e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_sr_relatedTo_sr_through_srr");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_representation_relationship a_relationship = new AShape_representation_relationship();
	 AShape_representation a_return = new AShape_representation();
	CShape_representation_relationship.usedinRep_2(null, (EShape_representation)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_representation_relationship e_relationship = (EShape_representation_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRep_1(null);
			if (e2.isKindOf(EShape_representation.class))
			{
			EShape_representation e_return = (EShape_representation) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  AStratum_surface All_ss_relatedTo_ss_through_sar (EStratum_surface e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_ss_relatedTo_ss_through_sar('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_aspect_relationship a_relationship = new AShape_aspect_relationship();
	 AStratum_surface a_return = new AStratum_surface();
	CShape_aspect_relationship.usedinRelating_shape_aspect(null, (EStratum_surface)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_aspect_relationship e_relationship = (EShape_aspect_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testDescription(null)) && (e_relationship.getDescription(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelated_shape_aspect(null);
			if (e2.isKindOf(EStratum_surface.class))
			{
			EStratum_surface e_return = (EStratum_surface) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  AStratum_surface All_ss_relatedTo_ss_through_sar_2 (EStratum_surface e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_ss_relatedTo_ss_through_sar_2('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_aspect_relationship a_relationship = new AShape_aspect_relationship();
	 AStratum_surface a_return = new AStratum_surface();
	CShape_aspect_relationship.usedinRelated_shape_aspect(null, (EStratum_surface)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_aspect_relationship e_relationship = (EShape_aspect_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testDescription(null)) && (e_relationship.getDescription(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelating_shape_aspect(null);
			if (e2.isKindOf(EStratum_surface.class))
			{
			EStratum_surface e_return = (EStratum_surface) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  ASingle_stratum_special_symbol_component All_ssssc_relatedTo_limdv_through_nauor (ELayered_interconnect_module_design_view e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_ssssc_relatedTo_limdv_through_nauor");
	System.out.println("\tGiven: "+e1.toString());
	
	ANext_assembly_usage_occurrence_relationship a_relationship = new ANext_assembly_usage_occurrence_relationship();
	 ASingle_stratum_special_symbol_component a_return = new ASingle_stratum_special_symbol_component();
	CNext_assembly_usage_occurrence_relationship.usedinRelating_product_definition(null, (ELayered_interconnect_module_design_view)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		ENext_assembly_usage_occurrence_relationship e_relationship = (ENext_assembly_usage_occurrence_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getOccurrence(null);
			if (e2.isKindOf(ESingle_stratum_special_symbol_component.class))
			{
			ESingle_stratum_special_symbol_component e_return = (ESingle_stratum_special_symbol_component) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  AStratum_technology_occurrence_link  All_stol_referencing_sto (EStratum_technology_occurrence e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tAll_stol_referencing_sto");
	System.out.println("\tGiven: "+e1.toString());
		
	AStratum_technology_occurrence_link a_referencing = new AStratum_technology_occurrence_link();
	 AStratum_technology_occurrence_link a_return = new AStratum_technology_occurrence_link();
	CStratum_technology_occurrence_link.usedinRelated_property_definition(null, (EStratum_technology_occurrence)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EStratum_technology_occurrence_link e_return = (EStratum_technology_occurrence_link) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			a_return.addUnordered(e_return);
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return a_return;
}

public  AStratum_technology_occurrence_link  All_stol_referencing_sto_2 (EStratum_technology_occurrence e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tAll_stol_referencing_sto_2");
	System.out.println("\tGiven: "+e1.toString());
		
	AStratum_technology_occurrence_link a_referencing = new AStratum_technology_occurrence_link();
	 AStratum_technology_occurrence_link a_return = new AStratum_technology_occurrence_link();
	CStratum_technology_occurrence_link.usedinRelating_property_definition(null, (EStratum_technology_occurrence)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EStratum_technology_occurrence_link e_return = (EStratum_technology_occurrence_link) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			a_return.addUnordered(e_return);
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return a_return;
}

public  AStratum_technology_occurrence_link All_stol_relatedTo_ptatsm_through_pdr (EPassage_technology_allocation_to_stack_model e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tAll_stol_relatedTo_ptatsm_through_pdr('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AProperty_definition_relationship a_relationship = new AProperty_definition_relationship();
	 AStratum_technology_occurrence_link a_return = new AStratum_technology_occurrence_link();
	CProperty_definition_relationship.usedinRelated_property_definition(null, (EPassage_technology_allocation_to_stack_model)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EProperty_definition_relationship e_relationship = (EProperty_definition_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testName(null)) && (e_relationship.getName(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelating_property_definition(null);
			if (e2.isKindOf(EStratum_technology_occurrence_link.class))
			{
			EStratum_technology_occurrence_link e_return = (EStratum_technology_occurrence_link) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			a_return.addUnordered(e_return);
			}
		}
	}
	return a_return;
}


public  AUsage_concept_usage_relationship  All_ucur_referencing_sa (EShape_aspect e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tAll_ucur_referencing_sa");
	System.out.println("\tGiven: "+e1.toString());
		
	AUsage_concept_usage_relationship a_referencing = new AUsage_concept_usage_relationship();
	 AUsage_concept_usage_relationship a_return = new AUsage_concept_usage_relationship();
	CUsage_concept_usage_relationship.usedinDefinition(null, (EShape_aspect)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EUsage_concept_usage_relationship e_return = (EUsage_concept_usage_relationship) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			a_return.addUnordered(e_return);
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return a_return;
}


public EAssembly_component_usage acu_referencedBy_pd (EProperty_definition e1, String v1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tacu_referencedBy_pd('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	
	EEntity e2 = e1.getDefinition(null);
	
	if (e2.isKindOf(EAssembly_component_usage.class))
	{
		EAssembly_component_usage e_return = (EAssembly_component_usage) e2;
		if ((e_return.testName(null)) && (e_return.getName(null).equals(v1)))
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}

public EAssembly_joint aj_referencing_pct (EPhysical_component_terminal e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\taj_referencing_pct");
	System.out.println("\tGiven: "+e1.toString());
		
	AAssembly_joint a_referencing = new AAssembly_joint();
	
	CAssembly_joint.usedinRelating_shape_aspect(null, (EPhysical_component_terminal)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EAssembly_joint e_return = (EAssembly_joint) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}


public EAxis2_placement_2d ap2d_referencedBy_mi (EMapped_item e1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tap2d_referencedBy_mi");
	System.out.println("\tGiven: "+e1.toString());
	
	
	EEntity e2 = e1.getMapping_target(null);
	
	if (e2.isKindOf(EAxis2_placement_2d.class))
	{
		EAxis2_placement_2d e_return = (EAxis2_placement_2d) e2;
		
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}

public EComponent_2d_location c2dl_referencingGiven (
			AComponent_2d_location a1
			, EEntity e1, EEntity e2
			) throws SdaiException {
	SdaiIterator it = a1.createIterator();
	while (it.next()) 
	{
		EComponent_2d_location e_current = (EComponent_2d_location) a1.getCurrentMemberEntity(it);
		
			if ((e_current.testRep_1(null)) && (e_current.getRep_1(null).equals(e1)))
			if ((e_current.testRep_2(null)) && (e_current.getRep_2(null).equals(e2)))
				return e_current;
	}
	return null;
}

public EComponent_2d_location c2dl_referencingGiven_2 (
			AComponent_2d_location a1
			, EEntity e1
			) throws SdaiException {
	SdaiIterator it = a1.createIterator();
	while (it.next()) 
	{
		EComponent_2d_location e_current = (EComponent_2d_location) a1.getCurrentMemberEntity(it);
		
			if ((e_current.testRep_2(null)) && (e_current.getRep_2(null).equals(e1)))
				return e_current;
	}
	return null;
}

public EComponent_2d_location c2dl_referencing_pds (EProduct_definition_shape e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tc2dl_referencing_pds");
	System.out.println("\tGiven: "+e1.toString());
		
	AComponent_2d_location a_referencing = new AComponent_2d_location();
	
	CComponent_2d_location.usedinRepresented_product_relation(null, (EProduct_definition_shape)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EComponent_2d_location e_return = (EComponent_2d_location) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}


public EConnected_area_component cac_referencedBy_sa (EShape_aspect e1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tcac_referencedBy_sa");
	System.out.println("\tGiven: "+e1.toString());
	
	
	EEntity e2 = e1.getOf_shape(null);
	
	if (e2.isKindOf(EConnected_area_component.class))
	{
		EConnected_area_component e_return = (EConnected_area_component) e2;
		
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}


public EConductive_interconnect_element cie_referencedBy_lcf (ELaminate_component_feature e1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tcie_referencedBy_lcf");
	System.out.println("\tGiven: "+e1.toString());
	
	
	EEntity e2 = e1.getOf_shape(null);
	
	if (e2.isKindOf(EConductive_interconnect_element.class))
	{
		EConductive_interconnect_element e_return = (EConductive_interconnect_element) e2;
		
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}


public ECartesian_point cp_referencedBy_r (ERepresentation e1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tcp_referencedBy_r");
	System.out.println("\tGiven: "+e1.toString());
	
	
	AEntity a2 = e1.getItems(null);
	
	SdaiIterator it_entities = a2.createIterator();
	while (it_entities.next()) 
	{
	EEntity e2 = a2.getCurrentMemberEntity(it_entities);	
	
	if (e2.isKindOf(ECartesian_point.class))
	{
		ECartesian_point e_return = (ECartesian_point) e2;
		
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	}
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}


public EContact_size_dependent_land csdl_referencedBy_lcit (ELaminate_component_interface_terminal e1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tcsdl_referencedBy_lcit");
	System.out.println("\tGiven: "+e1.toString());
	
	
	EEntity e2 = e1.getOf_shape(null);
	
	if (e2.isKindOf(EContact_size_dependent_land.class))
	{
		EContact_size_dependent_land e_return = (EContact_size_dependent_land) e2;
		
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}


public ECartesian_transformation_operator_2d cto2d_referencedBy_c2dl (EComponent_2d_location e1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tcto2d_referencedBy_c2dl");
	System.out.println("\tGiven: "+e1.toString());
	
	
	EEntity e2 = e1.getTransformation_operator(null);
	
	if (e2.isKindOf(ECartesian_transformation_operator_2d.class))
	{
		ECartesian_transformation_operator_2d e_return = (ECartesian_transformation_operator_2d) e2;
		
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}

public EDatum_feature df_relatedTo_d_through_sar (EDatum e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tdf_relatedTo_d_through_sar");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_aspect_relationship a_relationship = new AShape_aspect_relationship();
	
	CShape_aspect_relationship.usedinRelated_shape_aspect(null, (EDatum)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_aspect_relationship e_relationship = (EShape_aspect_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelating_shape_aspect(null);
			if (e2.isKindOf(EDatum_feature.class))
			{
			EDatum_feature e_return = (EDatum_feature) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}


public EDesign_stack_model dsm_relatedTo_limdv_through_pdr (ELayered_interconnect_module_design_view e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tdsm_relatedTo_limdv_through_pdr('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AProperty_definition_relationship a_relationship = new AProperty_definition_relationship();
	
	CProperty_definition_relationship.usedinRelated_property_definition(null, (ELayered_interconnect_module_design_view)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EProperty_definition_relationship e_relationship = (EProperty_definition_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testName(null)) && (e_relationship.getName(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelating_property_definition(null);
			if (e2.isKindOf(EDesign_stack_model.class))
			{
			EDesign_stack_model e_return = (EDesign_stack_model) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}



public EDerived_unit_element due_referencedBy_du (EDerived_unit e1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tdue_referencedBy_du");
	System.out.println("\tGiven: "+e1.toString());
	
	
	AEntity a2 = e1.getElements(null);
	
	SdaiIterator it_entities = a2.createIterator();
	while (it_entities.next()) 
	{
	EEntity e2 = a2.getCurrentMemberEntity(it_entities);	
	
	if (e2.isKindOf(EDerived_unit_element.class))
	{
		EDerived_unit_element e_return = (EDerived_unit_element) e2;
		
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	}
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}

public EGeometric_template gt_relatedTo_ac_through_pdr (EAssembly_component e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tgt_relatedTo_ac_through_pdr");
	System.out.println("\tGiven: "+e1.toString());
	
	AProduct_definition_relationship a_relationship = new AProduct_definition_relationship();
	
	CProduct_definition_relationship.usedinRelated_product_definition(null, (EAssembly_component)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EProduct_definition_relationship e_relationship = (EProduct_definition_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelating_product_definition(null);
			if (e2.isKindOf(EGeometric_template.class))
			{
			EGeometric_template e_return = (EGeometric_template) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}


public EGeometric_template gt_relatedTo_ac_through_pdr_2 (EAssembly_component e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tgt_relatedTo_ac_through_pdr_2('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AProduct_definition_relationship a_relationship = new AProduct_definition_relationship();
	
	CProduct_definition_relationship.usedinRelated_product_definition(null, (EAssembly_component)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EProduct_definition_relationship e_relationship = (EProduct_definition_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testName(null)) && (e_relationship.getName(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelating_product_definition(null);
			if (e2.isKindOf(EGeometric_template.class))
			{
			EGeometric_template e_return = (EGeometric_template) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}


public EId_attribute ia_referencing_cc (ECharacterized_class e1, String v1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tia_referencing_cc('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
		
	AId_attribute a_referencing = new AId_attribute();
	
	CId_attribute.usedinIdentified_item(null, (ECharacterized_class)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EId_attribute e_return = (EId_attribute) a_referencing.getCurrentMemberEntity(it_entities);
		if ((e_return.testAttribute_value(null)) && (e_return.getAttribute_value(null).equals(v1)))
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}

public EId_attribute ia_referencing_sr (EShape_representation e1, String v1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tia_referencing_sr('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
		
	AId_attribute a_referencing = new AId_attribute();
	
	CId_attribute.usedinIdentified_item(null, (EShape_representation)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EId_attribute e_return = (EId_attribute) a_referencing.getCurrentMemberEntity(it_entities);
		if ((e_return.testAttribute_value(null)) && (e_return.getAttribute_value(null).equals(v1)))
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}

public EItem_identified_representation_usage iiru_referencing_sa (EShape_aspect e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tiiru_referencing_sa");
	System.out.println("\tGiven: "+e1.toString());
		
	AItem_identified_representation_usage a_referencing = new AItem_identified_representation_usage();
	
	CItem_identified_representation_usage.usedinDefinition(null, (EShape_aspect)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EItem_identified_representation_usage e_return = (EItem_identified_representation_usage) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}

public EInterconnect_module_edge ime_relatedTo_limdv_through_nauor (ELayered_interconnect_module_design_view e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\time_relatedTo_limdv_through_nauor");
	System.out.println("\tGiven: "+e1.toString());
	
	ANext_assembly_usage_occurrence_relationship a_relationship = new ANext_assembly_usage_occurrence_relationship();
	
	CNext_assembly_usage_occurrence_relationship.usedinRelating_product_definition(null, (ELayered_interconnect_module_design_view)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		ENext_assembly_usage_occurrence_relationship e_relationship = (ENext_assembly_usage_occurrence_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getOccurrence(null);
			if (e2.isKindOf(EInterconnect_module_edge.class))
			{
			EInterconnect_module_edge e_return = (EInterconnect_module_edge) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}


public EInterconnect_module_interface_terminal imit_relatedTo_lcit_through_sar (ELaminate_component_interface_terminal e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\timit_relatedTo_lcit_through_sar('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_aspect_relationship a_relationship = new AShape_aspect_relationship();
	
	CShape_aspect_relationship.usedinRelated_shape_aspect(null, (ELaminate_component_interface_terminal)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_aspect_relationship e_relationship = (EShape_aspect_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testDescription(null)) && (e_relationship.getDescription(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelating_shape_aspect(null);
			if (e2.isKindOf(EInterconnect_module_interface_terminal.class))
			{
			EInterconnect_module_interface_terminal e_return = (EInterconnect_module_interface_terminal) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}


public EInterconnect_module_interface_terminal imit_relatedTo_lcp_through_sar (ELayer_connection_point e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\timit_relatedTo_lcp_through_sar('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_aspect_relationship a_relationship = new AShape_aspect_relationship();
	
	CShape_aspect_relationship.usedinRelated_shape_aspect(null, (ELayer_connection_point)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_aspect_relationship e_relationship = (EShape_aspect_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testName(null)) && (e_relationship.getName(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelating_shape_aspect(null);
			if (e2.isKindOf(EInterconnect_module_interface_terminal.class))
			{
			EInterconnect_module_interface_terminal e_return = (EInterconnect_module_interface_terminal) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}


public EInterconnect_module_interface_terminal imit_relatedTo_pct_through_sar (EPhysical_component_terminal e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\timit_relatedTo_pct_through_sar('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_aspect_relationship a_relationship = new AShape_aspect_relationship();
	
	CShape_aspect_relationship.usedinRelated_shape_aspect(null, (EPhysical_component_terminal)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_aspect_relationship e_relationship = (EShape_aspect_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testName(null)) && (e_relationship.getName(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelating_shape_aspect(null);
			if (e2.isKindOf(EInterconnect_module_interface_terminal.class))
			{
			EInterconnect_module_interface_terminal e_return = (EInterconnect_module_interface_terminal) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}



public EInter_stratum_feature isf_referencedBy_sa (EShape_aspect e1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tisf_referencedBy_sa");
	System.out.println("\tGiven: "+e1.toString());
	
	
	EEntity e2 = e1.getOf_shape(null);
	
	if (e2.isKindOf(EInter_stratum_feature.class))
	{
		EInter_stratum_feature e_return = (EInter_stratum_feature) e2;
		
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}


public ELaminate_component lc_referencedBy_sa (EShape_aspect e1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tlc_referencedBy_sa");
	System.out.println("\tGiven: "+e1.toString());
	
	
	EEntity e2 = e1.getOf_shape(null);
	
	if (e2.isKindOf(ELaminate_component.class))
	{
		ELaminate_component e_return = (ELaminate_component) e2;
		
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}

public ELaminate_component_interface_terminal lcit_referencing_ac (EAssembly_component e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tlcit_referencing_ac");
	System.out.println("\tGiven: "+e1.toString());
		
	ALaminate_component_interface_terminal a_referencing = new ALaminate_component_interface_terminal();
	
	CLaminate_component_interface_terminal.usedinOf_shape(null, (EAssembly_component)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		ELaminate_component_interface_terminal e_return = (ELaminate_component_interface_terminal) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}

public ELaminate_component_interface_terminal lcit_relatedTo_imit_through_sar (EInterconnect_module_interface_terminal e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tlcit_relatedTo_imit_through_sar");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_aspect_relationship a_relationship = new AShape_aspect_relationship();
	
	CShape_aspect_relationship.usedinRelating_shape_aspect(null, (EInterconnect_module_interface_terminal)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_aspect_relationship e_relationship = (EShape_aspect_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelated_shape_aspect(null);
			if (e2.isKindOf(ELaminate_component_interface_terminal.class))
			{
			ELaminate_component_interface_terminal e_return = (ELaminate_component_interface_terminal) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}


public ELaminate_component_interface_terminal lcit_relatedTo_imit_through_sar_2 (EInterconnect_module_interface_terminal e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tlcit_relatedTo_imit_through_sar_2('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_aspect_relationship a_relationship = new AShape_aspect_relationship();
	
	CShape_aspect_relationship.usedinRelating_shape_aspect(null, (EInterconnect_module_interface_terminal)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_aspect_relationship e_relationship = (EShape_aspect_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testDescription(null)) && (e_relationship.getDescription(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelated_shape_aspect(null);
			if (e2.isKindOf(ELaminate_component_interface_terminal.class))
			{
			ELaminate_component_interface_terminal e_return = (ELaminate_component_interface_terminal) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}



public ELength_measure_with_unit lmwu_referencedBy_r (ERepresentation e1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tlmwu_referencedBy_r");
	System.out.println("\tGiven: "+e1.toString());
	
	
	AEntity a2 = e1.getItems(null);
	
	SdaiIterator it_entities = a2.createIterator();
	while (it_entities.next()) 
	{
	EEntity e2 = a2.getCurrentMemberEntity(it_entities);	
	
	if (e2.isKindOf(ELength_measure_with_unit.class))
	{
		ELength_measure_with_unit e_return = (ELength_measure_with_unit) e2;
		
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	}
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}


public ELength_unit lu_referencedBy_due (EDerived_unit_element e1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tlu_referencedBy_due");
	System.out.println("\tGiven: "+e1.toString());
	
	
	EEntity e2 = e1.getUnit(null);
	
	if (e2.isKindOf(ELength_unit.class))
	{
		ELength_unit e_return = (ELength_unit) e2;
		
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}


public ELength_unit lu_referencedBy_guac (EGlobal_unit_assigned_context e1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tlu_referencedBy_guac");
	System.out.println("\tGiven: "+e1.toString());
	
	
	AEntity a2 = e1.getUnits(null);
	
	SdaiIterator it_entities = a2.createIterator();
	while (it_entities.next()) 
	{
	EEntity e2 = a2.getCurrentMemberEntity(it_entities);	
	
	if (e2.isKindOf(ELength_unit.class))
	{
		ELength_unit e_return = (ELength_unit) e2;
		
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	}
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}

public EMaterial_designation md_referencing_st (EStratum_technology e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tmd_referencing_st");
	System.out.println("\tGiven: "+e1.toString());
		
	AMaterial_designation a_referencing = new AMaterial_designation();
	
	CMaterial_designation.usedinDefinitions(null, (EStratum_technology)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EMaterial_designation e_return = (EMaterial_designation) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}


public EMapped_item mi_referencedBy_sr (EShape_representation e1, String v1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tmi_referencedBy_sr('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	
	AEntity a2 = e1.getItems(null);
	
	SdaiIterator it_entities = a2.createIterator();
	while (it_entities.next()) 
	{
	EEntity e2 = a2.getCurrentMemberEntity(it_entities);	
	
	if (e2.isKindOf(EMapped_item.class))
	{
		EMapped_item e_return = (EMapped_item) e2;
		if ((e_return.testName(null)) && (e_return.getName(null).equals(v1)))
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	}
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}

public ENext_assembly_usage_occurrence nauo_referencing_ltsc (ELaminate_text_string_component e1, String v1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tnauo_referencing_ltsc('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
		
	ANext_assembly_usage_occurrence a_referencing = new ANext_assembly_usage_occurrence();
	
	CNext_assembly_usage_occurrence.usedinRelated_product_definition(null, (ELaminate_text_string_component)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		ENext_assembly_usage_occurrence e_return = (ENext_assembly_usage_occurrence) a_referencing.getCurrentMemberEntity(it_entities);
		if ((e_return.testName(null)) && (e_return.getName(null).equals(v1)))
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}

public ENext_assembly_usage_occurrence nauo_referencing_pd (EProduct_definition e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tnauo_referencing_pd");
	System.out.println("\tGiven: "+e1.toString());
		
	ANext_assembly_usage_occurrence a_referencing = new ANext_assembly_usage_occurrence();
	
	CNext_assembly_usage_occurrence.usedinRelated_product_definition(null, (EProduct_definition)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		ENext_assembly_usage_occurrence e_return = (ENext_assembly_usage_occurrence) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}

public ENext_assembly_usage_occurrence_relationship nauor_referencingGiven (
			ANext_assembly_usage_occurrence_relationship a1
			, EEntity e1
			) throws SdaiException {
	SdaiIterator it = a1.createIterator();
	while (it.next()) 
	{
		ENext_assembly_usage_occurrence_relationship e_current = (ENext_assembly_usage_occurrence_relationship) a1.getCurrentMemberEntity(it);
		
			if ((e_current.testRelating_product_definition(null)) && (e_current.getRelating_product_definition(null).equals(e1)))
				return e_current;
	}
	return null;
}

public ENext_assembly_usage_occurrence_relationship nauor_referencing_lc (ELaminate_component e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tnauor_referencing_lc");
	System.out.println("\tGiven: "+e1.toString());
		
	ANext_assembly_usage_occurrence_relationship a_referencing = new ANext_assembly_usage_occurrence_relationship();
	
	CNext_assembly_usage_occurrence_relationship.usedinOccurrence(null, (ELaminate_component)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		ENext_assembly_usage_occurrence_relationship e_return = (ENext_assembly_usage_occurrence_relationship) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}

public ENext_assembly_usage_occurrence_relationship nauor_referencing_pd (EProduct_definition e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tnauor_referencing_pd");
	System.out.println("\tGiven: "+e1.toString());
		
	ANext_assembly_usage_occurrence_relationship a_referencing = new ANext_assembly_usage_occurrence_relationship();
	
	CNext_assembly_usage_occurrence_relationship.usedinOccurrence(null, (EProduct_definition)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		ENext_assembly_usage_occurrence_relationship e_return = (ENext_assembly_usage_occurrence_relationship) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}

public ENext_assembly_usage_occurrence_relationship nauor_referencing_slc (EStructured_layout_component e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tnauor_referencing_slc");
	System.out.println("\tGiven: "+e1.toString());
		
	ANext_assembly_usage_occurrence_relationship a_referencing = new ANext_assembly_usage_occurrence_relationship();
	
	CNext_assembly_usage_occurrence_relationship.usedinOccurrence(null, (EStructured_layout_component)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		ENext_assembly_usage_occurrence_relationship e_return = (ENext_assembly_usage_occurrence_relationship) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}


public EProduct p_referencedBy_prpc (EProduct_related_product_category e1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tp_referencedBy_prpc");
	System.out.println("\tGiven: "+e1.toString());
	
	
	AEntity a2 = e1.getProducts(null);
	
	SdaiIterator it_entities = a2.createIterator();
	while (it_entities.next()) 
	{
	EEntity e2 = a2.getCurrentMemberEntity(it_entities);	
	
	if (e2.isKindOf(EProduct.class))
	{
		EProduct e_return = (EProduct) e2;
		
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	}
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}

public EPackage p_relatedTo_pp_through_pdr (EPackaged_part e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tp_relatedTo_pp_through_pdr('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AProduct_definition_relationship a_relationship = new AProduct_definition_relationship();
	
	CProduct_definition_relationship.usedinRelated_product_definition(null, (EPackaged_part)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EProduct_definition_relationship e_relationship = (EProduct_definition_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testName(null)) && (e_relationship.getName(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelating_product_definition(null);
			if (e2.isKindOf(EPackage.class))
			{
			EPackage e_return = (EPackage) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}


public EPackage p_relatedTo_sr_through_sdr (EShape_representation e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tp_relatedTo_sr_through_sdr");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_definition_representation a_relationship = new AShape_definition_representation();
	
	CShape_definition_representation.usedinUsed_representation(null, (EShape_representation)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_definition_representation e_relationship = (EShape_definition_representation) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getDefinition(null);
			if (e2.isKindOf(EPackage.class))
			{
			EPackage e_return = (EPackage) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}


public EParameter_assignment pa_relatedTo_pd_through_pdr (EProperty_definition e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tpa_relatedTo_pd_through_pdr");
	System.out.println("\tGiven: "+e1.toString());
	
	AProperty_definition_representation a_relationship = new AProperty_definition_representation();
	
	CProperty_definition_representation.usedinDefinition(null, (EProperty_definition)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EProperty_definition_representation e_relationship = (EProperty_definition_representation) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getUsed_representation(null);
			if (e2.isKindOf(EParameter_assignment.class))
			{
			EParameter_assignment e_return = (EParameter_assignment) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}


public EPackage_body pb_referencing_p (EPackage e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tpb_referencing_p");
	System.out.println("\tGiven: "+e1.toString());
		
	APackage_body a_referencing = new APackage_body();
	
	CPackage_body.usedinOf_shape(null, (EPackage)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EPackage_body e_return = (EPackage_body) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}

public EPackage_body pb_relatedTo_af_through_iiru (EAdvanced_face e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tpb_relatedTo_af_through_iiru");
	System.out.println("\tGiven: "+e1.toString());
	
	AItem_identified_representation_usage a_relationship = new AItem_identified_representation_usage();
	
	CItem_identified_representation_usage.usedinIdentified_item(null, (EAdvanced_face)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EItem_identified_representation_usage e_relationship = (EItem_identified_representation_usage) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getDefinition(null);
			if (e2.isKindOf(EPackage_body.class))
			{
			EPackage_body e_return = (EPackage_body) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}



public EPackaged_component pc_referencedBy_pct (EPhysical_component_terminal e1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tpc_referencedBy_pct");
	System.out.println("\tGiven: "+e1.toString());
	
	
	EEntity e2 = e1.getOf_shape(null);
	
	if (e2.isKindOf(EPackaged_component.class))
	{
		EPackaged_component e_return = (EPackaged_component) e2;
		
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}


public EPhysical_component_terminal pct_referencedBy_aj (EAssembly_joint e1, String v1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tpct_referencedBy_aj('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	
	EEntity e2 = e1.getRelated_shape_aspect(null);
	
	if (e2.isKindOf(EPhysical_component_terminal.class))
	{
		EPhysical_component_terminal e_return = (EPhysical_component_terminal) e2;
		if ((e_return.testDescription(null)) && (e_return.getDescription(null).equals(v1)))
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}


public EPhysical_component_terminal pct_referencedBy_aj_2 (EAssembly_joint e1, String v1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tpct_referencedBy_aj_2('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	
	EEntity e2 = e1.getRelating_shape_aspect(null);
	
	if (e2.isKindOf(EPhysical_component_terminal.class))
	{
		EPhysical_component_terminal e_return = (EPhysical_component_terminal) e2;
		if ((e_return.testDescription(null)) && (e_return.getDescription(null).equals(v1)))
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}

public EProperty_definition pd_referencing_lcp (ELayer_connection_point e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tpd_referencing_lcp");
	System.out.println("\tGiven: "+e1.toString());
		
	AProperty_definition a_referencing = new AProperty_definition();
	
	CProperty_definition.usedinDefinition(null, (ELayer_connection_point)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EProperty_definition e_return = (EProperty_definition) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}

public EProperty_definition pd_referencing_ltsc (ELaminate_text_string_component e1, String v1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tpd_referencing_ltsc('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
		
	AProperty_definition a_referencing = new AProperty_definition();
	
	CProperty_definition.usedinDefinition(null, (ELaminate_text_string_component)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EProperty_definition e_return = (EProperty_definition) a_referencing.getCurrentMemberEntity(it_entities);
		if ((e_return.testDescription(null)) && (e_return.getDescription(null).equals(v1)))
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}

public EProperty_definition pd_referencing_pd (EProduct_definition e1, String v1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tpd_referencing_pd('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
		
	AProperty_definition a_referencing = new AProperty_definition();
	
	CProperty_definition.usedinDefinition(null, (EProduct_definition)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EProperty_definition e_return = (EProperty_definition) a_referencing.getCurrentMemberEntity(it_entities);
		if ((e_return.testName(null)) && (e_return.getName(null).equals(v1)))
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}

public EProperty_definition pd_referencing_pspva (EProduct_specific_parameter_value_assignment e1, String v1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tpd_referencing_pspva('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
		
	AProperty_definition a_referencing = new AProperty_definition();
	
	CProperty_definition.usedinDefinition(null, (EProduct_specific_parameter_value_assignment)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EProperty_definition e_return = (EProperty_definition) a_referencing.getCurrentMemberEntity(it_entities);
		if ((e_return.testDescription(null)) && (e_return.getDescription(null).equals(v1)))
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}

public EProperty_definition pd_referencing_ptt (EPart_text_template e1, String v1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tpd_referencing_ptt('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
		
	AProperty_definition a_referencing = new AProperty_definition();
	
	CProperty_definition.usedinDefinition(null, (EPart_text_template)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EProperty_definition e_return = (EProperty_definition) a_referencing.getCurrentMemberEntity(it_entities);
		if ((e_return.testDescription(null)) && (e_return.getDescription(null).equals(v1)))
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}

public EProperty_definition pd_referencing_sa (EShape_aspect e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tpd_referencing_sa");
	System.out.println("\tGiven: "+e1.toString());
		
	AProperty_definition a_referencing = new AProperty_definition();
	
	CProperty_definition.usedinDefinition(null, (EShape_aspect)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EProperty_definition e_return = (EProperty_definition) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}

public EProperty_definition pd_referencing_slcsar (EStructured_layout_component_sub_assembly_relationship e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tpd_referencing_slcsar");
	System.out.println("\tGiven: "+e1.toString());
		
	AProperty_definition a_referencing = new AProperty_definition();
	
	CProperty_definition.usedinDefinition(null, (EStructured_layout_component_sub_assembly_relationship)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EProperty_definition e_return = (EProperty_definition) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}

public EProperty_definition pd_referencing_st (EStratum_technology e1, String v1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tpd_referencing_st('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
		
	AProperty_definition a_referencing = new AProperty_definition();
	
	CProperty_definition.usedinDefinition(null, (EStratum_technology)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EProperty_definition e_return = (EProperty_definition) a_referencing.getCurrentMemberEntity(it_entities);
		if ((e_return.testName(null)) && (e_return.getName(null).equals(v1)))
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}

public EProduct_definition pd_relatedTo_pd_through_pdr (EProduct_definition e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tpd_relatedTo_pd_through_pdr('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AProduct_definition_relationship a_relationship = new AProduct_definition_relationship();
	
	CProduct_definition_relationship.usedinRelated_product_definition(null, (EProduct_definition)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EProduct_definition_relationship e_relationship = (EProduct_definition_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testName(null)) && (e_relationship.getName(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelating_product_definition(null);
			if (e2.isKindOf(EProduct_definition.class))
			{
			EProduct_definition e_return = (EProduct_definition) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}


public EProperty_definition pd_relatedTo_pd_through_pdr_2 (EProperty_definition e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tpd_relatedTo_pd_through_pdr_2('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AProperty_definition_relationship a_relationship = new AProperty_definition_relationship();
	
	CProperty_definition_relationship.usedinRelated_property_definition(null, (EProperty_definition)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EProperty_definition_relationship e_relationship = (EProperty_definition_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testName(null)) && (e_relationship.getName(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelating_property_definition(null);
			if (e2.isKindOf(EProperty_definition.class))
			{
			EProperty_definition e_return = (EProperty_definition) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}



public EProduct_definition_context pdc_referencedBy_pdca (EProduct_definition_context_association e1, String v1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tpdc_referencedBy_pdca('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	
	EEntity e2 = e1.getFrame_of_reference(null);
	
	if (e2.isKindOf(EProduct_definition_context.class))
	{
		EProduct_definition_context e_return = (EProduct_definition_context) e2;
		if ((e_return.testName(null)) && (e_return.getName(null).equals(v1)))
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}


public EProduct_definition_context_role pdcr_referencedBy_pdca (EProduct_definition_context_association e1, String v1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tpdcr_referencedBy_pdca('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	
	EEntity e2 = e1.getRole(null);
	
	if (e2.isKindOf(EProduct_definition_context_role.class))
	{
		EProduct_definition_context_role e_return = (EProduct_definition_context_role) e2;
		if ((e_return.testName(null)) && (e_return.getName(null).equals(v1)))
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}

public EProduct_definition_formation pdf_referencing_p (EProduct e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tpdf_referencing_p");
	System.out.println("\tGiven: "+e1.toString());
		
	AProduct_definition_formation a_referencing = new AProduct_definition_formation();
	
	CProduct_definition_formation.usedinOf_product(null, (EProduct)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EProduct_definition_formation e_return = (EProduct_definition_formation) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}

public EProduct_definition_relationship pdr_referencing_pd (EProduct_definition e1, String v1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tpdr_referencing_pd('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
		
	AProduct_definition_relationship a_referencing = new AProduct_definition_relationship();
	
	CProduct_definition_relationship.usedinRelated_product_definition(null, (EProduct_definition)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EProduct_definition_relationship e_return = (EProduct_definition_relationship) a_referencing.getCurrentMemberEntity(it_entities);
		if ((e_return.testName(null)) && (e_return.getName(null).equals(v1)))
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}

public EProduct_definition_relationship pdr_referencing_pd_2 (EProduct_definition e1, String v1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tpdr_referencing_pd_2('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
		
	AProduct_definition_relationship a_referencing = new AProduct_definition_relationship();
	
	CProduct_definition_relationship.usedinRelating_product_definition(null, (EProduct_definition)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EProduct_definition_relationship e_return = (EProduct_definition_relationship) a_referencing.getCurrentMemberEntity(it_entities);
		if ((e_return.testName(null)) && (e_return.getName(null).equals(v1)))
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}

public EProperty_definition_representation pdr_referencing_pd_3 (EProperty_definition e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tpdr_referencing_pd_3");
	System.out.println("\tGiven: "+e1.toString());
		
	AProperty_definition_representation a_referencing = new AProperty_definition_representation();
	
	CProperty_definition_representation.usedinDefinition(null, (EProperty_definition)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EProperty_definition_representation e_return = (EProperty_definition_representation) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}

public EProduct_definition_shape pds_referencing_acu (EAssembly_component_usage e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tpds_referencing_acu");
	System.out.println("\tGiven: "+e1.toString());
		
	AProduct_definition_shape a_referencing = new AProduct_definition_shape();
	
	CProduct_definition_shape.usedinDefinition(null, (EAssembly_component_usage)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EProduct_definition_shape e_return = (EProduct_definition_shape) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}

public EProduct_definition_shape pds_referencing_co (ECharacterized_object e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tpds_referencing_co");
	System.out.println("\tGiven: "+e1.toString());
		
	AProduct_definition_shape a_referencing = new AProduct_definition_shape();
	
	CProduct_definition_shape.usedinDefinition(null, (ECharacterized_object)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EProduct_definition_shape e_return = (EProduct_definition_shape) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}

public EProduct_definition_shape pds_referencing_nauor (ENext_assembly_usage_occurrence_relationship e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tpds_referencing_nauor");
	System.out.println("\tGiven: "+e1.toString());
		
	AProduct_definition_shape a_referencing = new AProduct_definition_shape();
	
	CProduct_definition_shape.usedinDefinition(null, (ENext_assembly_usage_occurrence_relationship)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EProduct_definition_shape e_return = (EProduct_definition_shape) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}


public EPackaged_part pp_referencedBy_pc (EPackaged_component e1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tpp_referencedBy_pc");
	System.out.println("\tGiven: "+e1.toString());
	
	
	EEntity e2 = e1.getRelating_product_definition(null);
	
	if (e2.isKindOf(EPackaged_part.class))
	{
		EPackaged_part e_return = (EPackaged_part) e2;
		
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}

public EPlated_passage pp_relatedTo_ppdl_through_pdr (EPlated_passage_dependent_land e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tpp_relatedTo_ppdl_through_pdr('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AProduct_definition_relationship a_relationship = new AProduct_definition_relationship();
	
	CProduct_definition_relationship.usedinRelated_product_definition(null, (EPlated_passage_dependent_land)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EProduct_definition_relationship e_relationship = (EProduct_definition_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testName(null)) && (e_relationship.getName(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelating_product_definition(null);
			if (e2.isKindOf(EPlated_passage.class))
			{
			EPlated_passage e_return = (EPlated_passage) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}


public EPackaged_part_terminal ppt_relatedTo_pct_through_sar (EPhysical_component_terminal e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tppt_relatedTo_pct_through_sar('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_aspect_relationship a_relationship = new AShape_aspect_relationship();
	
	CShape_aspect_relationship.usedinRelated_shape_aspect(null, (EPhysical_component_terminal)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_aspect_relationship e_relationship = (EShape_aspect_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testName(null)) && (e_relationship.getName(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelating_shape_aspect(null);
			if (e2.isKindOf(EPackaged_part_terminal.class))
			{
			EPackaged_part_terminal e_return = (EPackaged_part_terminal) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}


public EPackage_terminal pt_relatedTo_af_through_iiru (EAdvanced_face e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tpt_relatedTo_af_through_iiru");
	System.out.println("\tGiven: "+e1.toString());
	
	AItem_identified_representation_usage a_relationship = new AItem_identified_representation_usage();
	
	CItem_identified_representation_usage.usedinIdentified_item(null, (EAdvanced_face)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EItem_identified_representation_usage e_relationship = (EItem_identified_representation_usage) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getDefinition(null);
			if (e2.isKindOf(EPackage_terminal.class))
			{
			EPackage_terminal e_return = (EPackage_terminal) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}


public EPassage_technology_allocation_to_stack_model ptatsm_relatedTo_isf_through_pdr (EInter_stratum_feature e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tptatsm_relatedTo_isf_through_pdr('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AProperty_definition_relationship a_relationship = new AProperty_definition_relationship();
	
	CProperty_definition_relationship.usedinRelated_property_definition(null, (EInter_stratum_feature)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EProperty_definition_relationship e_relationship = (EProperty_definition_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testName(null)) && (e_relationship.getName(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelating_property_definition(null);
			if (e2.isKindOf(EPassage_technology_allocation_to_stack_model.class))
			{
			EPassage_technology_allocation_to_stack_model e_return = (EPassage_technology_allocation_to_stack_model) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}


public EPart_text_template ptt_relatedTo_gltc_through_pdr (EGeneric_laminate_text_component e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tptt_relatedTo_gltc_through_pdr('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AProduct_definition_relationship a_relationship = new AProduct_definition_relationship();
	
	CProduct_definition_relationship.usedinRelated_product_definition(null, (EGeneric_laminate_text_component)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EProduct_definition_relationship e_relationship = (EProduct_definition_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testName(null)) && (e_relationship.getName(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelating_product_definition(null);
			if (e2.isKindOf(EPart_text_template.class))
			{
			EPart_text_template e_return = (EPart_text_template) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}


public ERepresentation r_referencing_rc (ERepresentation_context e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tr_referencing_rc");
	System.out.println("\tGiven: "+e1.toString());
		
	ARepresentation a_referencing = new ARepresentation();
	
	CRepresentation.usedinContext_of_items(null, (ERepresentation_context)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		ERepresentation e_return = (ERepresentation) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}

public ERepresentation r_relatedTo_pd_through_pdr (EProperty_definition e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tr_relatedTo_pd_through_pdr");
	System.out.println("\tGiven: "+e1.toString());
	
	AProperty_definition_representation a_relationship = new AProperty_definition_representation();
	
	CProperty_definition_representation.usedinDefinition(null, (EProperty_definition)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EProperty_definition_representation e_relationship = (EProperty_definition_representation) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getUsed_representation(null);
			if (e2.isKindOf(ERepresentation.class))
			{
			ERepresentation e_return = (ERepresentation) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}



public ERepresentation_item ri_referencedBy_r (ERepresentation e1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tri_referencedBy_r");
	System.out.println("\tGiven: "+e1.toString());
	
	
	AEntity a2 = e1.getItems(null);
	
	SdaiIterator it_entities = a2.createIterator();
	while (it_entities.next()) 
	{
	EEntity e2 = a2.getCurrentMemberEntity(it_entities);	
	
	if (e2.isKindOf(ERepresentation_item.class))
	{
		ERepresentation_item e_return = (ERepresentation_item) e2;
		
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	}
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}


public ERepresentation_map rm_referencedBy_mi (EMapped_item e1) throws SdaiException  {
	System.out.println("");
	System.out.println("\trm_referencedBy_mi");
	System.out.println("\tGiven: "+e1.toString());
	
	
	EEntity e2 = e1.getMapping_source(null);
	
	if (e2.isKindOf(ERepresentation_map.class))
	{
		ERepresentation_map e_return = (ERepresentation_map) e2;
		
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}


public EStratum s_referencedBy_sf (EStratum_feature e1) throws SdaiException  {
	System.out.println("");
	System.out.println("\ts_referencedBy_sf");
	System.out.println("\tGiven: "+e1.toString());
	
	
	EEntity e2 = e1.getOf_shape(null);
	
	if (e2.isKindOf(EStratum.class))
	{
		EStratum e_return = (EStratum) e2;
		
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}


public EStratum s_referencedBy_ss (EStratum_surface e1) throws SdaiException  {
	System.out.println("");
	System.out.println("\ts_referencedBy_ss");
	System.out.println("\tGiven: "+e1.toString());
	
	
	EEntity e2 = e1.getOf_shape(null);
	
	if (e2.isKindOf(EStratum.class))
	{
		EStratum e_return = (EStratum) e2;
		
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}

public EStratum s_relatedTo_lc_through_pdr (ELaminate_component e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\ts_relatedTo_lc_through_pdr('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AProperty_definition_relationship a_relationship = new AProperty_definition_relationship();
	
	CProperty_definition_relationship.usedinRelated_property_definition(null, (ELaminate_component)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EProperty_definition_relationship e_relationship = (EProperty_definition_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testName(null)) && (e_relationship.getName(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelating_property_definition(null);
			if (e2.isKindOf(EStratum.class))
			{
			EStratum e_return = (EStratum) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}


public EStratum s_relatedTo_lc_through_pdr_2 (ELaminate_component e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\ts_relatedTo_lc_through_pdr_2('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AProduct_definition_relationship a_relationship = new AProduct_definition_relationship();
	
	CProduct_definition_relationship.usedinRelated_product_definition(null, (ELaminate_component)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EProduct_definition_relationship e_relationship = (EProduct_definition_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testName(null)) && (e_relationship.getName(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelating_product_definition(null);
			if (e2.isKindOf(EStratum.class))
			{
			EStratum e_return = (EStratum) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}


public EStratum s_relatedTo_sto_through_pdr (EStratum_technology_occurrence e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\ts_relatedTo_sto_through_pdr('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AProperty_definition_relationship a_relationship = new AProperty_definition_relationship();
	
	CProperty_definition_relationship.usedinRelating_property_definition(null, (EStratum_technology_occurrence)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EProperty_definition_relationship e_relationship = (EProperty_definition_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testName(null)) && (e_relationship.getName(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelated_property_definition(null);
			if (e2.isKindOf(EStratum.class))
			{
			EStratum e_return = (EStratum) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}


public EShape_aspect sa_referencing_altc (EAdditive_laminate_text_component e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tsa_referencing_altc");
	System.out.println("\tGiven: "+e1.toString());
		
	AShape_aspect a_referencing = new AShape_aspect();
	
	CShape_aspect.usedinOf_shape(null, (EAdditive_laminate_text_component)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EShape_aspect e_return = (EShape_aspect) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}

public EShape_aspect sa_referencing_lc (ELaminate_component e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tsa_referencing_lc");
	System.out.println("\tGiven: "+e1.toString());
		
	AShape_aspect a_referencing = new AShape_aspect();
	
	CShape_aspect.usedinOf_shape(null, (ELaminate_component)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EShape_aspect e_return = (EShape_aspect) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}

public EShape_aspect sa_referencing_sftc (EStratum_feature_template_component e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tsa_referencing_sftc");
	System.out.println("\tGiven: "+e1.toString());
		
	AShape_aspect a_referencing = new AShape_aspect();
	
	CShape_aspect.usedinOf_shape(null, (EStratum_feature_template_component)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EShape_aspect e_return = (EShape_aspect) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}

public EShape_aspect sa_relatedTo_cgsa_through_sar (EComposite_group_shape_aspect e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tsa_relatedTo_cgsa_through_sar");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_aspect_relationship a_relationship = new AShape_aspect_relationship();
	
	CShape_aspect_relationship.usedinRelating_shape_aspect(null, (EComposite_group_shape_aspect)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_aspect_relationship e_relationship = (EShape_aspect_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelated_shape_aspect(null);
			if (e2.isKindOf(EShape_aspect.class))
			{
			EShape_aspect e_return = (EShape_aspect) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}


public EShape_aspect sa_relatedTo_lcp_through_sar (ELayer_connection_point e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tsa_relatedTo_lcp_through_sar('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_aspect_relationship a_relationship = new AShape_aspect_relationship();
	
	CShape_aspect_relationship.usedinRelated_shape_aspect(null, (ELayer_connection_point)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_aspect_relationship e_relationship = (EShape_aspect_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testName(null)) && (e_relationship.getName(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelating_shape_aspect(null);
			if (e2.isKindOf(EShape_aspect.class))
			{
			EShape_aspect e_return = (EShape_aspect) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}


public EShape_aspect sa_relatedTo_ptf_through_sar (EPart_tooling_feature e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tsa_relatedTo_ptf_through_sar('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_aspect_relationship a_relationship = new AShape_aspect_relationship();
	
	CShape_aspect_relationship.usedinRelating_shape_aspect(null, (EPart_tooling_feature)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_aspect_relationship e_relationship = (EShape_aspect_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testName(null)) && (e_relationship.getName(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelated_shape_aspect(null);
			if (e2.isKindOf(EShape_aspect.class))
			{
			EShape_aspect e_return = (EShape_aspect) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}


public EShape_aspect sa_relatedTo_sa_through_sar (EShape_aspect e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tsa_relatedTo_sa_through_sar('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_aspect_relationship a_relationship = new AShape_aspect_relationship();
	
	CShape_aspect_relationship.usedinRelated_shape_aspect(null, (EShape_aspect)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_aspect_relationship e_relationship = (EShape_aspect_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testName(null)) && (e_relationship.getName(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelating_shape_aspect(null);
			if (e2.isKindOf(EShape_aspect.class))
			{
			EShape_aspect e_return = (EShape_aspect) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}



public EShape_aspect_relationship sar_referencedBy_pd (EProperty_definition e1, String v1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tsar_referencedBy_pd('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	
	EEntity e2 = e1.getDefinition(null);
	
	if (e2.isKindOf(EShape_aspect_relationship.class))
	{
		EShape_aspect_relationship e_return = (EShape_aspect_relationship) e2;
		if ((e_return.testDescription(null)) && (e_return.getDescription(null).equals(v1)))
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}

public ESolid_character_glyph_2d_symbol scg2ds_relatedTo_pd_through_pdr (EProperty_definition e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tscg2ds_relatedTo_pd_through_pdr");
	System.out.println("\tGiven: "+e1.toString());
	
	AProperty_definition_representation a_relationship = new AProperty_definition_representation();
	
	CProperty_definition_representation.usedinDefinition(null, (EProperty_definition)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EProperty_definition_representation e_relationship = (EProperty_definition_representation) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getUsed_representation(null);
			if (e2.isKindOf(ESolid_character_glyph_2d_symbol.class))
			{
			ESolid_character_glyph_2d_symbol e_return = (ESolid_character_glyph_2d_symbol) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}


public EShape_dimension_representation sdr_referencing_rc (ERepresentation_context e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tsdr_referencing_rc");
	System.out.println("\tGiven: "+e1.toString());
		
	AShape_dimension_representation a_referencing = new AShape_dimension_representation();
	
	CShape_dimension_representation.usedinContext_of_items(null, (ERepresentation_context)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EShape_dimension_representation e_return = (EShape_dimension_representation) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}

public EShape_dimension_representation sdr_relatedTo_dl_through_dcr (EDimensional_location e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tsdr_relatedTo_dl_through_dcr");
	System.out.println("\tGiven: "+e1.toString());
	
	ADimensional_characteristic_representation a_relationship = new ADimensional_characteristic_representation();
	
	CDimensional_characteristic_representation.usedinDimension(null, (EDimensional_location)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EDimensional_characteristic_representation e_relationship = (EDimensional_characteristic_representation) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRepresentation(null);
			if (e2.isKindOf(EShape_dimension_representation.class))
			{
			EShape_dimension_representation e_return = (EShape_dimension_representation) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}


public EShape_dimension_representation sdr_relatedTo_ds_through_dcr (EDimensional_size e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tsdr_relatedTo_ds_through_dcr");
	System.out.println("\tGiven: "+e1.toString());
	
	ADimensional_characteristic_representation a_relationship = new ADimensional_characteristic_representation();
	
	CDimensional_characteristic_representation.usedinDimension(null, (EDimensional_size)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EDimensional_characteristic_representation e_relationship = (EDimensional_characteristic_representation) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRepresentation(null);
			if (e2.isKindOf(EShape_dimension_representation.class))
			{
			EShape_dimension_representation e_return = (EShape_dimension_representation) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}


public EStratum_feature sf_relatedTo_sa_through_sar (EShape_aspect e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tsf_relatedTo_sa_through_sar('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_aspect_relationship a_relationship = new AShape_aspect_relationship();
	
	CShape_aspect_relationship.usedinRelated_shape_aspect(null, (EShape_aspect)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_aspect_relationship e_relationship = (EShape_aspect_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testName(null)) && (e_relationship.getName(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelating_shape_aspect(null);
			if (e2.isKindOf(EStratum_feature.class))
			{
			EStratum_feature e_return = (EStratum_feature) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}



public EStratum_feature_template_component sftc_referencedBy_sa (EShape_aspect e1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tsftc_referencedBy_sa");
	System.out.println("\tGiven: "+e1.toString());
	
	
	EEntity e2 = e1.getOf_shape(null);
	
	if (e2.isKindOf(EStratum_feature_template_component.class))
	{
		EStratum_feature_template_component e_return = (EStratum_feature_template_component) e2;
		
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}


public EStructured_layout_component slc_referencedBy_slcsar (EStructured_layout_component_sub_assembly_relationship e1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tslc_referencedBy_slcsar");
	System.out.println("\tGiven: "+e1.toString());
	
	
	EEntity e2 = e1.getRelating_product_definition(null);
	
	if (e2.isKindOf(EStructured_layout_component.class))
	{
		EStructured_layout_component e_return = (EStructured_layout_component) e2;
		
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}

public EStructured_layout_component_sub_assembly_relationship slcsar_referencing_ac (EAssembly_component e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tslcsar_referencing_ac");
	System.out.println("\tGiven: "+e1.toString());
		
	AStructured_layout_component_sub_assembly_relationship a_referencing = new AStructured_layout_component_sub_assembly_relationship();
	
	CStructured_layout_component_sub_assembly_relationship.usedinOccurrence(null, (EAssembly_component)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EStructured_layout_component_sub_assembly_relationship e_return = (EStructured_layout_component_sub_assembly_relationship) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}

public EStructured_layout_component_sub_assembly_relationship slcsar_referencing_lc (ELaminate_component e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tslcsar_referencing_lc");
	System.out.println("\tGiven: "+e1.toString());
		
	AStructured_layout_component_sub_assembly_relationship a_referencing = new AStructured_layout_component_sub_assembly_relationship();
	
	CStructured_layout_component_sub_assembly_relationship.usedinOccurrence(null, (ELaminate_component)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EStructured_layout_component_sub_assembly_relationship e_return = (EStructured_layout_component_sub_assembly_relationship) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}

public ESeating_plane sp_referencing_p (EPackage e1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tsp_referencing_p");
	System.out.println("\tGiven: "+e1.toString());
		
	ASeating_plane a_referencing = new ASeating_plane();
	
	CSeating_plane.usedinOf_shape(null, (EPackage)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		ESeating_plane e_return = (ESeating_plane) a_referencing.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}


public EShape_representation sr_referencedBy_c2dl (EComponent_2d_location e1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tsr_referencedBy_c2dl");
	System.out.println("\tGiven: "+e1.toString());
	
	
	EEntity e2 = e1.getRep_1(null);
	
	if (e2.isKindOf(EShape_representation.class))
	{
		EShape_representation e_return = (EShape_representation) e2;
		
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}


public EShape_representation sr_referencedBy_c2dl_2 (EComponent_2d_location e1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tsr_referencedBy_c2dl_2");
	System.out.println("\tGiven: "+e1.toString());
	
	
	EEntity e2 = e1.getRep_2(null);
	
	if (e2.isKindOf(EShape_representation.class))
	{
		EShape_representation e_return = (EShape_representation) e2;
		
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}


public EShape_representation sr_referencedBy_rm (ERepresentation_map e1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tsr_referencedBy_rm");
	System.out.println("\tGiven: "+e1.toString());
	
	
	EEntity e2 = e1.getMapped_representation(null);
	
	if (e2.isKindOf(EShape_representation.class))
	{
		EShape_representation e_return = (EShape_representation) e2;
		
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}

public EShape_representation sr_relatedTo_ac_through_sdr (EAssembly_component e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tsr_relatedTo_ac_through_sdr");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_definition_representation a_relationship = new AShape_definition_representation();
	
	CShape_definition_representation.usedinDefinition(null, (EAssembly_component)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_definition_representation e_relationship = (EShape_definition_representation) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getUsed_representation(null);
			if (e2.isKindOf(EShape_representation.class))
			{
			EShape_representation e_return = (EShape_representation) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}


public EShape_representation sr_relatedTo_gt_through_sdr (EGeometric_template e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tsr_relatedTo_gt_through_sdr");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_definition_representation a_relationship = new AShape_definition_representation();
	
	CShape_definition_representation.usedinDefinition(null, (EGeometric_template)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_definition_representation e_relationship = (EShape_definition_representation) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getUsed_representation(null);
			if (e2.isKindOf(EShape_representation.class))
			{
			EShape_representation e_return = (EShape_representation) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}


public EShape_representation sr_relatedTo_pd_through_pdr (EProperty_definition e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tsr_relatedTo_pd_through_pdr");
	System.out.println("\tGiven: "+e1.toString());
	
	AProperty_definition_representation a_relationship = new AProperty_definition_representation();
	
	CProperty_definition_representation.usedinDefinition(null, (EProperty_definition)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EProperty_definition_representation e_relationship = (EProperty_definition_representation) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getUsed_representation(null);
			if (e2.isKindOf(EShape_representation.class))
			{
			EShape_representation e_return = (EShape_representation) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}


public EShape_representation sr_relatedTo_pd_through_sdr (EProperty_definition e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tsr_relatedTo_pd_through_sdr");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_definition_representation a_relationship = new AShape_definition_representation();
	
	CShape_definition_representation.usedinDefinition(null, (EProperty_definition)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_definition_representation e_relationship = (EShape_definition_representation) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getUsed_representation(null);
			if (e2.isKindOf(EShape_representation.class))
			{
			EShape_representation e_return = (EShape_representation) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}


public EShape_representation sr_relatedTo_pds_through_sdr (EProduct_definition_shape e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tsr_relatedTo_pds_through_sdr");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_definition_representation a_relationship = new AShape_definition_representation();
	
	CShape_definition_representation.usedinDefinition(null, (EProduct_definition_shape)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_definition_representation e_relationship = (EShape_definition_representation) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getUsed_representation(null);
			if (e2.isKindOf(EShape_representation.class))
			{
			EShape_representation e_return = (EShape_representation) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}


public EShape_representation sr_relatedTo_st_through_sdr (EStructured_template e1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tsr_relatedTo_st_through_sdr");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_definition_representation a_relationship = new AShape_definition_representation();
	
	CShape_definition_representation.usedinDefinition(null, (EStructured_template)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_definition_representation e_relationship = (EShape_definition_representation) a_relationship.getCurrentMemberEntity(it_entities);
		
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getUsed_representation(null);
			if (e2.isKindOf(EShape_representation.class))
			{
			EShape_representation e_return = (EShape_representation) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}



public EStratum_surface ss_referencedBy_sar (EShape_aspect_relationship e1, String v1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tss_referencedBy_sar('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	
	EEntity e2 = e1.getRelating_shape_aspect(null);
	
	if (e2.isKindOf(EStratum_surface.class))
	{
		EStratum_surface e_return = (EStratum_surface) e2;
		if ((e_return.testDescription(null)) && (e_return.getDescription(null).equals(v1)))
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}


public EStratum_surface ss_referencedBy_sar_2 (EShape_aspect_relationship e1, String v1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tss_referencedBy_sar_2('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	
	EEntity e2 = e1.getRelated_shape_aspect(null);
	
	if (e2.isKindOf(EStratum_surface.class))
	{
		EStratum_surface e_return = (EStratum_surface) e2;
		if ((e_return.testDescription(null)) && (e_return.getDescription(null).equals(v1)))
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}

public EStratum_surface ss_referencing_s (EStratum e1, String v1) throws SdaiException  {
	
	System.out.println("");
	System.out.println("\tss_referencing_s('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
		
	AStratum_surface a_referencing = new AStratum_surface();
	
	CStratum_surface.usedinOf_shape(null, (EStratum)e1, null, a_referencing);	
	
	SdaiIterator it_entities = a_referencing.createIterator();
	while (it_entities.next()) 
	{
		EStratum_surface e_return = (EStratum_surface) a_referencing.getCurrentMemberEntity(it_entities);
		if ((e_return.testDescription(null)) && (e_return.getDescription(null).equals(v1)))
		{
			
			System.out.println("\tReferencing: "+e_return.toString());
			
			return e_return;
		}
	}
	System.out.println("\tReferencing: None found. (returning null)");
	return null;
}

public EStratum_surface ss_relatedTo_ss_through_sar (EStratum_surface e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tss_relatedTo_ss_through_sar('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_aspect_relationship a_relationship = new AShape_aspect_relationship();
	
	CShape_aspect_relationship.usedinRelating_shape_aspect(null, (EStratum_surface)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_aspect_relationship e_relationship = (EShape_aspect_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testDescription(null)) && (e_relationship.getDescription(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelated_shape_aspect(null);
			if (e2.isKindOf(EStratum_surface.class))
			{
			EStratum_surface e_return = (EStratum_surface) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}


public EStratum_surface ss_relatedTo_ss_through_sar_2 (EStratum_surface e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tss_relatedTo_ss_through_sar_2('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AShape_aspect_relationship a_relationship = new AShape_aspect_relationship();
	
	CShape_aspect_relationship.usedinRelated_shape_aspect(null, (EStratum_surface)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EShape_aspect_relationship e_relationship = (EShape_aspect_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testDescription(null)) && (e_relationship.getDescription(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelating_shape_aspect(null);
			if (e2.isKindOf(EStratum_surface.class))
			{
			EStratum_surface e_return = (EStratum_surface) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}



public EStratum_stack_model ssm_referencedBy_stol (EStratum_technology_occurrence_link e1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tssm_referencedBy_stol");
	System.out.println("\tGiven: "+e1.toString());
	
	
	EEntity e2 = e1.getDefinition(null);
	
	if (e2.isKindOf(EStratum_stack_model.class))
	{
		EStratum_stack_model e_return = (EStratum_stack_model) e2;
		
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}


public EStructured_template st_referencedBy_slc (EStructured_layout_component e1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tst_referencedBy_slc");
	System.out.println("\tGiven: "+e1.toString());
	
	
	EEntity e2 = e1.getRelating_product_definition(null);
	
	if (e2.isKindOf(EStructured_template.class))
	{
		EStructured_template e_return = (EStructured_template) e2;
		
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}


public EStratum_technology_occurrence sto_referencedBy_stol (EStratum_technology_occurrence_link e1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tsto_referencedBy_stol");
	System.out.println("\tGiven: "+e1.toString());
	
	
	EEntity e2 = e1.getRelating_property_definition(null);
	
	if (e2.isKindOf(EStratum_technology_occurrence.class))
	{
		EStratum_technology_occurrence e_return = (EStratum_technology_occurrence) e2;
		
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}


public EStratum_technology_occurrence sto_referencedBy_stol_2 (EStratum_technology_occurrence_link e1) throws SdaiException  {
	System.out.println("");
	System.out.println("\tsto_referencedBy_stol_2");
	System.out.println("\tGiven: "+e1.toString());
	
	
	EEntity e2 = e1.getRelated_property_definition(null);
	
	if (e2.isKindOf(EStratum_technology_occurrence.class))
	{
		EStratum_technology_occurrence e_return = (EStratum_technology_occurrence) e2;
		
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}

public EStratum_technology_occurrence sto_relatedTo_s_through_pdr (EStratum e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tsto_relatedTo_s_through_pdr('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AProperty_definition_relationship a_relationship = new AProperty_definition_relationship();
	
	CProperty_definition_relationship.usedinRelated_property_definition(null, (EStratum)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EProperty_definition_relationship e_relationship = (EProperty_definition_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testName(null)) && (e_relationship.getName(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelating_property_definition(null);
			if (e2.isKindOf(EStratum_technology_occurrence.class))
			{
			EStratum_technology_occurrence e_return = (EStratum_technology_occurrence) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}


public EStratum_technology_occurrence_link stol_relatedTo_ptatsm_through_pdr (EPassage_technology_allocation_to_stack_model e1, String v1) throws SdaiException {
	
	System.out.println("");
	System.out.println("\tstol_relatedTo_ptatsm_through_pdr('"+v1+"')");
	System.out.println("\tGiven: "+e1.toString());
	
	AProperty_definition_relationship a_relationship = new AProperty_definition_relationship();
	
	CProperty_definition_relationship.usedinRelated_property_definition(null, (EPassage_technology_allocation_to_stack_model)e1, null, a_relationship);
	SdaiIterator it_entities = a_relationship.createIterator();
	while (it_entities.next()) 
	{
		EProperty_definition_relationship e_relationship = (EProperty_definition_relationship) a_relationship.getCurrentMemberEntity(it_entities);
		if ((e_relationship.testName(null)) && (e_relationship.getName(null).equals(v1)))
		{
			
			System.out.println("\tRelating: " + e_relationship.toString());
			
			EEntity e2 = e_relationship.getRelating_property_definition(null);
			if (e2.isKindOf(EStratum_technology_occurrence_link.class))
			{
			EStratum_technology_occurrence_link e_return = (EStratum_technology_occurrence_link) e2;
			
			System.out.println("\tRelated: " + e_return.toString());
			
			return e_return;
			}
		}
	}
	return null;
}



public EText_literal tl_referencedBy_r (ERepresentation e1) throws SdaiException  {
	System.out.println("");
	System.out.println("\ttl_referencedBy_r");
	System.out.println("\tGiven: "+e1.toString());
	
	
	AEntity a2 = e1.getItems(null);
	
	SdaiIterator it_entities = a2.createIterator();
	while (it_entities.next()) 
	{
	EEntity e2 = a2.getCurrentMemberEntity(it_entities);	
	
	if (e2.isKindOf(EText_literal.class))
	{
		EText_literal e_return = (EText_literal) e2;
		
		System.out.println("\tReferenced: "+e_return.toString());
		return e_return;
	}
	
	}
	
	System.out.println("\tReferenced: None found. (returning null)");
	
	return null;
	
}

public EUsage_concept_usage_relationship ucur_referencingGiven (
			AUsage_concept_usage_relationship a1
			, EEntity e1
			) throws SdaiException {
	SdaiIterator it = a1.createIterator();
	while (it.next()) 
	{
		EUsage_concept_usage_relationship e_current = (EUsage_concept_usage_relationship) a1.getCurrentMemberEntity(it);
		
			if ((e_current.testUsed_representation(null)) && (e_current.getUsed_representation(null).equals(e1)))
				return e_current;
	}
	return null;
}
}
