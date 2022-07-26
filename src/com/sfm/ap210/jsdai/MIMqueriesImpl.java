package com.sfm.ap210.jsdai;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import jsdai.SApplication_context_schema.EProduct_definition_context;
import jsdai.SAssembly_module_design_mim.ALayered_assembly_module_design_view;
import jsdai.SAssembly_module_design_mim.APackaged_component;
import jsdai.SAssembly_module_design_mim.ELayered_assembly_module_design_view;
import jsdai.SAssembly_module_design_mim.EPackaged_component;
import jsdai.SAssembly_module_with_interconnect_component_mim.AInterconnect_module_component;
import jsdai.SAssembly_module_with_interconnect_component_mim.EInterconnect_module_component;
import jsdai.SAssembly_technology_mim.AAssembly_joint;
import jsdai.SAssembly_technology_mim.EAssembly_joint;
import jsdai.SBasic_attribute_schema.EId_attribute;
import jsdai.SClassification_schema.AClass;
import jsdai.SClassification_schema.EClass;
import jsdai.SFabrication_technology_mim.AStratum_technology_occurrence_link;
import jsdai.SFabrication_technology_mim.EPassage_technology_allocation_to_stack_model;
import jsdai.SFabrication_technology_mim.EStratum_stack_model;
import jsdai.SFabrication_technology_mim.EStratum_technology;
import jsdai.SFabrication_technology_mim.EStratum_technology_occurrence;
import jsdai.SFabrication_technology_mim.EStratum_technology_occurrence_link;
import jsdai.SGeometry_schema.EAxis2_placement_2d;
import jsdai.SGeometry_schema.ECartesian_transformation_operator_2d;
import jsdai.SInterconnect_module_usage_view_mim.EInterconnect_module_interface_terminal;
import jsdai.SInterconnect_module_usage_view_mim.ELayered_interconnect_module_usage_view;
import jsdai.SLand_mim.APlated_passage_dependent_land;
import jsdai.SLand_mim.EContact_size_dependent_land;
import jsdai.SLayered_interconnect_complex_template_mim.EStructured_template;
import jsdai.SLayered_interconnect_module_design_mim.AConductive_interconnect_element;
import jsdai.SLayered_interconnect_module_design_mim.AFootprint_occurrence;
import jsdai.SLayered_interconnect_module_design_mim.AGeneric_laminate_text_component;
import jsdai.SLayered_interconnect_module_design_mim.AJoin_shape_aspect;
import jsdai.SLayered_interconnect_module_design_mim.ALaminate_component;
import jsdai.SLayered_interconnect_module_design_mim.ALaminate_component_feature;
import jsdai.SLayered_interconnect_module_design_mim.ALaminate_text_string_component;
import jsdai.SLayered_interconnect_module_design_mim.ALayer_connection_point;
import jsdai.SLayered_interconnect_module_design_mim.ALayered_interconnect_module_design_view;
import jsdai.SLayered_interconnect_module_design_mim.ALayered_interconnect_panel_design_view;
import jsdai.SLayered_interconnect_module_design_mim.AMaterial_removal_laminate_component;
import jsdai.SLayered_interconnect_module_design_mim.AMulti_stratum_special_symbol_component;
import jsdai.SLayered_interconnect_module_design_mim.APadstack_occurrence;
import jsdai.SLayered_interconnect_module_design_mim.APhysical_network;
import jsdai.SLayered_interconnect_module_design_mim.ASingle_stratum_special_symbol_component;
import jsdai.SLayered_interconnect_module_design_mim.AStratum;
import jsdai.SLayered_interconnect_module_design_mim.AStratum_feature_template_component;
import jsdai.SLayered_interconnect_module_design_mim.AStructured_layout_component;
import jsdai.SLayered_interconnect_module_design_mim.EAdditive_laminate_text_component;
import jsdai.SLayered_interconnect_module_design_mim.EConductive_interconnect_element;
import jsdai.SLayered_interconnect_module_design_mim.EConnected_area_component;
import jsdai.SLayered_interconnect_module_design_mim.EGeneric_laminate_text_component;
import jsdai.SLayered_interconnect_module_design_mim.EInter_stratum_feature;
import jsdai.SLayered_interconnect_module_design_mim.EInterconnect_module_edge;
import jsdai.SLayered_interconnect_module_design_mim.EJoin_shape_aspect;
import jsdai.SLayered_interconnect_module_design_mim.ELaminate_component;
import jsdai.SLayered_interconnect_module_design_mim.ELaminate_component_feature;
import jsdai.SLayered_interconnect_module_design_mim.ELaminate_component_interface_terminal;
import jsdai.SLayered_interconnect_module_design_mim.ELaminate_text_string_component;
import jsdai.SLayered_interconnect_module_design_mim.ELayer_connection_point;
import jsdai.SLayered_interconnect_module_design_mim.ELayered_interconnect_module_design_view;
import jsdai.SLayered_interconnect_module_design_mim.ELayered_interconnect_panel_design_view;
import jsdai.SLayered_interconnect_module_design_mim.EMaterial_removal_laminate_component;
import jsdai.SLayered_interconnect_module_design_mim.EPhysical_network;
import jsdai.SLayered_interconnect_module_design_mim.EPlated_passage;
import jsdai.SLayered_interconnect_module_design_mim.EStratum;
import jsdai.SLayered_interconnect_module_design_mim.EStratum_feature;
import jsdai.SLayered_interconnect_module_design_mim.EStratum_feature_template_component;
import jsdai.SLayered_interconnect_module_design_mim.EStructured_layout_component;
import jsdai.SLayered_interconnect_module_design_mim.EStructured_layout_component_sub_assembly_relationship;
import jsdai.SLayered_interconnect_simple_template_mim.EPart_text_template;
import jsdai.SLayered_interconnect_simple_template_mim.ESolid_character_glyph_2d_symbol;
import jsdai.SMaterial_property_definition_schema.EMaterial_designation;
import jsdai.SModel_parameter_mim.AParameter_assignment;
import jsdai.SModel_parameter_mim.AProduct_specific_parameter_value_assignment;
import jsdai.SModel_parameter_mim.EParameter_assignment;
import jsdai.SModel_parameter_mim.EProduct_specific_parameter_value_assignment;
import jsdai.SPackage_mim.EPackage;
import jsdai.SPackaged_part_black_box_model_mim.EPackaged_part;
import jsdai.SPackaged_part_black_box_model_mim.EPackaged_part_terminal;
import jsdai.SPart_feature_function_mim.APart_tooling_feature;
import jsdai.SPart_feature_function_mim.EPart_tooling_feature;
import jsdai.SPart_template_shape_with_parameters_mim.EGeometric_template;
import jsdai.SPhysical_component_feature_mim.EPhysical_component_terminal;
import jsdai.SPhysical_unit_2d_design_view_mim.AComponent_2d_location;
import jsdai.SPhysical_unit_2d_design_view_mim.EComponent_2d_location;
import jsdai.SPhysical_unit_design_view_mim.ANext_assembly_usage_occurrence_relationship;
import jsdai.SPhysical_unit_design_view_mim.CNext_assembly_usage_occurrence_relationship;
import jsdai.SPhysical_unit_design_view_mim.EAssembly_component;
import jsdai.SPhysical_unit_design_view_mim.ENext_assembly_usage_occurrence_relationship;
import jsdai.SPresentation_definition_schema.EText_literal;
import jsdai.SProduct_definition_schema.AProduct;
import jsdai.SProduct_definition_schema.AProduct_definition;
import jsdai.SProduct_definition_schema.AProduct_definition_context_association;
import jsdai.SProduct_definition_schema.AProduct_related_product_category;
import jsdai.SProduct_definition_schema.EProduct;
import jsdai.SProduct_definition_schema.EProduct_definition;
import jsdai.SProduct_definition_schema.EProduct_definition_context_association;
import jsdai.SProduct_definition_schema.EProduct_definition_context_role;
import jsdai.SProduct_definition_schema.EProduct_definition_formation;
import jsdai.SProduct_definition_schema.EProduct_definition_relationship;
import jsdai.SProduct_definition_schema.EProduct_related_product_category;
import jsdai.SProduct_property_definition_schema.AProduct_definition_shape;
import jsdai.SProduct_property_definition_schema.AProperty_definition;
import jsdai.SProduct_property_definition_schema.EProduct_definition_shape;
import jsdai.SProduct_property_definition_schema.EProperty_definition;
import jsdai.SProduct_property_definition_schema.EShape_aspect;
import jsdai.SProduct_property_representation_schema.AShape_representation;
import jsdai.SProduct_property_representation_schema.EItem_identified_representation_usage;
import jsdai.SProduct_property_representation_schema.EShape_representation;
import jsdai.SProduct_structure_schema.EAssembly_component_usage;
import jsdai.SRepresentation_schema.AMapped_item;
import jsdai.SRepresentation_schema.ARepresentation;
import jsdai.SRepresentation_schema.ARepresentation_item;
import jsdai.SRepresentation_schema.EMapped_item;
import jsdai.SRepresentation_schema.ERepresentation;
import jsdai.SRepresentation_schema.ERepresentation_map;
import jsdai.SShape_parameters_mim.EKeepout_design_object_category;
import jsdai.lang.EEntity;
import jsdai.lang.SdaiException;
import jsdai.lang.SdaiIterator;
import jsdai.lang.SdaiModel;

/**
 * An implementation of the MIMqueries interface.
 * @author James Stori, SFM Technology, Inc.
 * @version 1.2
 * @see MIMqueries
 */
public class MIMqueriesImpl implements MIMqueries {
	
	SdaiModel model;
	MIMops ops;
	
	public MIMqueriesImpl(SdaiModel m, MIMops ops)
	{
		this.model = m;
		this.ops = ops;
	}
	
	public void setOps(MIMops ops)
	{
		this.ops = ops;
	}
	
	public MIMqueriesImpl(SdaiModel m)
	{
		this.model = m;
		ops = new MIMopsImpl(m);
	}
	
	public EShape_representation getShapeRepresentationOfAssemblyComponent(EAssembly_component ac) throws SdaiException
	{
		EShape_representation sr = ops.sr_relatedTo_ac_through_sdr(ac);

		if (sr != null) 
		{
			EId_attribute ia = ops.ia_referencing_sr(sr,"ac2dsm");
			if (ia != null) 
				return sr;
		}

		if (ac.isKindOf(ELaminate_component.class))
		{
			// both assembly component and laminate component are subtypes of product_definition_relationship.
			// the 'definition usage' product_definition_relationship that links the component to its template is
			// now the same entity as the component itself.
			
			// prior implementation will not work as it assume a distinct product_definition_relationship entity.
			//EGeometric_template gt = ops.gt_relatedTo_ac_through_pdr_2(ac,"definition usage");
			
			EGeometric_template gt = null;
			EProduct_definition e_pd = ac.getRelating_product_definition(null);
			if (e_pd.isKindOf(EGeometric_template.class))
				gt = (EGeometric_template) e_pd;

			if (gt != null)
			{
				//EShape_representation sr2 = ops.sr_relatedTo_gt_through_sdr(gt);
				EShape_representation sr2 = this.getShapeRepresentationOfProductDefinitionShape(gt);
				return sr2;
			}
		}
		
		// case of conductive_interconnect_element_with_pre_defined_transitions (i.e. trace)
		if (ac.isKindOf(EItem_identified_representation_usage.class))
		{
			EItem_identified_representation_usage e_iiru = (EItem_identified_representation_usage) ac;
			ERepresentation e_rep = e_iiru.getUsed_representation(null);
			if (e_rep.isKindOf(EShape_representation.class))
				return (EShape_representation) e_rep;
		}
		
		System.out.println("Warning: Could not find a shape_representation for: "+ac);
		return null;
	}
	
	public EShape_representation getShapeRepresentationOfSLC(EStructured_layout_component slc) throws SdaiException
	{
		EStructured_template st = ops.st_referencedBy_slc(slc);
		EShape_representation srOfslc = this.getShapeRepresentationOfProductDefinitionShape(st);
		return srOfslc;
	}

	public EShape_representation getShapeRepresentationOfGenericLaminateTextComponent(EGeneric_laminate_text_component gltc) throws SdaiException
	{		
		EPart_text_template ptt = (EPart_text_template) gltc.getRelating_product_definition((EProduct_definition_relationship)null);
		//EPart_text_template ptt = ops.ptt_relatedTo_gltc_through_pdr(gltc,"definition usage");
		
		if (ptt == null) 
			return null;
			
		EProperty_definition pd = ops.pd_referencing_ptt(ptt,"of character");

		if (pd == null) 
			return null;
		
		ESolid_character_glyph_2d_symbol scg2ds = ops.scg2ds_relatedTo_pd_through_pdr(pd);
		
		return scg2ds;
	}
	
	/*
	public ECartesian_transformation_operator_2d getCartesianTransformationOfNAUOR(
			ENext_assembly_usage_occurrence_relationship nauor) throws SdaiException
	{
		EProduct_definition_shape pds = ops.pds_referencing_nauor(nauor);

		if (pds !=null) 
			{
				EComponent_2d_location c2dl = ops.c2dl_referencing_pds(pds);
				ECartesian_transformation_operator_2d cto2d = ops.cto2d_referencedBy_c2dl(c2dl);
				return cto2d;
			}
		return null;
	}
	*/
	
	public ECartesian_transformation_operator_2d getCartesianTransformationOfNAUOR(
			ENext_assembly_usage_occurrence_relationship nauor,
			EShape_representation sr1,
			EShape_representation sr2) throws SdaiException
	{
		AProduct_definition_shape a_pds = ops.All_pds_referencing_nauor(nauor);
		
		SdaiIterator it_pds = a_pds.createIterator();
		while (it_pds.next()) 
		{
			EProduct_definition_shape pds = (EProduct_definition_shape) a_pds.getCurrentMemberEntity(it_pds);
		
			if (pds != null)
			{
				AComponent_2d_location a_c2dl = ops.All_c2dl_referencing_pds(pds);
		
				EComponent_2d_location e_c2dl = ops.c2dl_referencingGiven(a_c2dl, sr1, sr2);

				if (e_c2dl != null)
				{
					ECartesian_transformation_operator_2d cto2d = ops.cto2d_referencedBy_c2dl(e_c2dl);
					return cto2d;
				}
			}
		}
		return null;
	}
	
	public EMapped_item getTLISTTforTLIST(EAssembly_component_usage e_acu, EShape_representation e_assemblyShape) throws SdaiException
	{
		AProduct_definition_shape a_pds = ops.All_pds_referencing_acu(e_acu);
		SdaiIterator it_pds = a_pds.createIterator();
		while (it_pds.next()) 
		{
		EProduct_definition_shape e_pds = (EProduct_definition_shape) a_pds.getCurrentMemberEntity(it_pds);
		AShape_representation a_sr = ops.All_sr_relatedTo_pds_through_sdr(e_pds);
		
		SdaiIterator it = a_sr.createIterator();
		while (it.next()) 
		{
			EShape_representation e_sr = (EShape_representation) a_sr.getCurrentMemberEntity(it);
			AMapped_item a_mi = ops.All_mi_referencedBy_sr(e_sr, "tlistt");
			SdaiIterator it_mi = a_mi.createIterator();
			while (it_mi.next()) 
			{
				EMapped_item e_mi = (EMapped_item) a_mi.getCurrentMemberEntity(it_mi);
				AShape_representation a_assemblyShapeCandidates = ops.All_sr_referencing_mi(e_mi);
				SdaiIterator it_asc = a_assemblyShapeCandidates.createIterator();
				while (it_asc.next()) 
				{
					EShape_representation e_assemblyShapeCandidate = (EShape_representation) a_assemblyShapeCandidates.getCurrentMemberEntity(it_asc);
					if (e_assemblyShapeCandidate == e_assemblyShape)
						return e_mi;
				}				
			}
		}	
		}
		return null;
	}
	
	public MIMlocation getAxisPlacementOfSLCSAR(
		EStructured_layout_component_sub_assembly_relationship slcsar,
		EShape_representation sr1,
		EShape_representation sr2) throws SdaiException
	{
		EAxis2_placement_2d a2p2d1 = null;
		EAxis2_placement_2d a2p2d2 = null; 
		
		//EStructured_layout_component slc = ops.slc_referencedBy_slcsar(slcsar);	
		//EStructured_template st1 = ops.st_referencedBy_slc(slc);
		EProperty_definition pd1 = ops.pd_referencing_slcsar(slcsar);
		
		EProperty_definition pd2 = ops.pd_relatedTo_pd_through_pdr_2(pd1,"first location");		
		EAssembly_component_usage acu1 = ops.acu_referencedBy_pd(pd2,"tlist");
		
		EMapped_item mi1 = getTLISTTforTLIST(acu1, sr2); 
		ERepresentation_map rm1 = ops.rm_referencedBy_mi(mi1);
		EShape_representation sr5 = ops.sr_referencedBy_rm(rm1);
		a2p2d1 = ops.ap2d_referencedBy_mi(mi1);

		EProperty_definition pd3 = ops.pd_relatedTo_pd_through_pdr_2(pd1,"second location");
		if (pd3 !=null) 
			{
				EAssembly_component_usage acu2 = ops.acu_referencedBy_pd(pd3,"tlist");
				EMapped_item mi2 = getTLISTTforTLIST(acu2, sr5);
				a2p2d2 = ops.ap2d_referencedBy_mi(mi2);
			}
		
		MIMlocation mimLoc = new MIMlocation(null, a2p2d1, a2p2d2);
		return mimLoc;
	}

	public MIMlocation getLocationOfLaminateComponent(ELaminate_component lc,
			EShape_representation sr1,
			EShape_representation sr2) throws SdaiException
	{
		MIMlocation mimLoc = null;
		ENext_assembly_usage_occurrence_relationship nauor = null;
		
		EStructured_layout_component_sub_assembly_relationship slcsar = ops.slcsar_referencing_lc(lc);

		if (slcsar !=null) 
		{
			EStructured_layout_component slc = ops.slc_referencedBy_slcsar(slcsar);
			EStructured_template st = ops.st_referencedBy_slc(slc);
			EShape_representation srOfslc = this.getShapeRepresentationOfProductDefinitionShape(st);
			mimLoc = this.getAxisPlacementOfSLCSAR(slcsar, sr1, srOfslc);
			nauor = ops.nauor_referencing_slc(slc);
			ECartesian_transformation_operator_2d cto2d = this.getCartesianTransformationOfNAUOR(nauor, srOfslc, sr2);
			mimLoc.setCto2d(cto2d);
		}
		else // Laminate_component is not part of a Structured_layout_component
		{
			nauor = ops.nauor_referencing_lc(lc);
			mimLoc = new MIMlocation();
			ECartesian_transformation_operator_2d cto2d = this.getCartesianTransformationOfNAUOR(nauor, sr1, sr2);
			mimLoc.setCto2d(cto2d);
		}
		
		return mimLoc;
	}
	
	/*
	public MIMlocation getLocationOfLaminateComponentInSLC(ELaminate_component lc,
			EStructured_layout_component_sub_assembly_relationship slcsar,
			EStructured_layout_component slc,
			EShape_representation sr1,
			EShape_representation sr2) throws SdaiException
	{
		EStructured_template st = ops.st_referencedBy_slc(slc);
		EShape_representation srOfslc = this.getShapeRepresentationOfProductDefinitionShape(st);
		MIMlocation mimLoc = this.getAxisPlacementOfSLCSAR(slcsar, sr1, srOfslc);
		return mimLoc;
	}
	*/
	
	public MIMlocation getLocationOfAssemblyComponentInSLC(EAssembly_component e_ac,
			EStructured_layout_component_sub_assembly_relationship slcsar,
			EStructured_layout_component slc,
			EShape_representation srOfac) throws SdaiException
	{
		EStructured_template st = ops.st_referencedBy_slc(slc);
		EShape_representation srOfslc = this.getShapeRepresentationOfProductDefinitionShape(st);
		MIMlocation mimLoc = this.getAxisPlacementOfSLCSAR(slcsar, srOfac, srOfslc);
		return mimLoc;
	}

	public EStratum_technology getStratumTechnologyOfStratum(EStratum s) throws SdaiException
	{
		EProperty_definition e_pd = ops.pd_relatedTo_pd_through_pdr_2(s, "definition");
		if (e_pd.isKindOf(EStratum_technology_occurrence.class))	
			return (EStratum_technology)((EStratum_technology_occurrence)e_pd).getDefinition(null);
		else
			return null;
	}
	
	public ERepresentation getThicknessOfStratum(EStratum s) throws SdaiException
	{
		EStratum_technology e_st = getStratumTechnologyOfStratum(s);
		
		if (e_st == null)
			return null;
		
		EProperty_definition e_pd = ops.pd_referencing_st(e_st, "stratum thickness");
		if (e_pd == null)
			return null;
		
		ERepresentation e_rep = ops.r_relatedTo_pd_through_pdr(e_pd);
		return e_rep;
	}
	
	public String getLayerPurposeOfStratum(EStratum e_s) throws SdaiException
	{
		EStratum_technology e_st = getStratumTechnologyOfStratum(e_s);
		if (e_st != null)
		{
			EProperty_definition e_pd = ops.pd_referencing_st(e_st, "layer purpose");
			if (e_pd != null)
			{
				if (e_pd.testDescription(null))
					return e_pd.getDescription(null);
			}
			return null;
		}
		return null;
	}
	
	public EMaterial_designation getMaterialDesignationOfStratum(EStratum e_s) throws SdaiException
	{
		EStratum_technology e_st = getStratumTechnologyOfStratum(e_s);
		if (e_st != null)
		{
			EMaterial_designation e_md = ops.md_referencing_st(e_st);
			return e_md;
		}
		return null;
	}

	public EStratum getStratumOfStratumFeature(EStratum_feature sf) throws SdaiException
	{
		EStratum s = ops.s_referencedBy_sf(sf);
		return 	s;
	}

	public EStratum getStratumOfLC(ELaminate_component lc) throws SdaiException
	{
		EStratum s = null;
		if (lc.isKindOf(EMaterial_removal_laminate_component.class))
			s = ops.s_relatedTo_lc_through_pdr_2(lc,"design intent");
		else
			s = ops.s_relatedTo_lc_through_pdr_2(lc,"resident stratum");
		return s;
	}
	
	public EStratum_feature getStratumFeatureOfSFTC(EStratum_feature_template_component sftc) throws SdaiException
	{
		if (sftc.isKindOf(EStratum_feature.class))
			{
				//System.out.println("sftc+sf: "+sftc.toString());
				return (EStratum_feature)sftc;
			}
				
		EShape_aspect sa = ops.sa_referencing_sftc(sftc);
		if (sa != null)
		{
			EStratum_feature sf = ops.sf_relatedTo_sa_through_sar(sa, "implementation");
			return sf;
		}
		return null;
	}
	
	public EStratum_feature getStratumFeatureOfALTC(EAdditive_laminate_text_component altc) throws SdaiException
	{
		EShape_aspect sa = ops.sa_referencing_altc(altc);
		EStratum_feature sf = ops.sf_relatedTo_sa_through_sar(sa, "stratum feature implementation");
		return sf;
	}
	
	public EStratum_feature_template_component getSFTCofMRLC(ELaminate_component lc) throws SdaiException
	{
		EShape_aspect sa1 = ops.sa_referencing_lc(lc);
		if (sa1 == null)
			return null;
		EShape_aspect sa2 = ops.sa_relatedTo_sa_through_sar(sa1, "design intent");
		if (sa2 == null)
			return null;
		EStratum_feature_template_component sftc = ops.sftc_referencedBy_sa(sa2);
		return sftc;
	}
	
	public EStratum getPrecedentStratum(EStratum currentStratum, EStratum_stack_model stackModel) throws SdaiException
	{
		EStratum_technology_occurrence e_sto = ops.sto_relatedTo_s_through_pdr(currentStratum, "definition");
		if (e_sto != null)
		{
			AStratum_technology_occurrence_link a_stol = ops.All_stol_referencing_sto(e_sto);
			SdaiIterator it_stol = a_stol.createIterator();
			while (it_stol.next()) 
			{
				EStratum_technology_occurrence_link e_stol = (EStratum_technology_occurrence_link) a_stol.getCurrentMemberEntity(it_stol);
				EEntity e_stol_definition = e_stol.getDefinition(null);
				if (e_stol_definition == stackModel)
				{
					EStratum_technology_occurrence e_sto2 = (EStratum_technology_occurrence) e_stol.getRelating_property_definition(null);
					EStratum e_precedentStratum = ops.s_relatedTo_sto_through_pdr(e_sto2, "definition");
					return e_precedentStratum;
				}
			}
		}
		return null;
	}

	public AStratum getAllAdjacentPrecedentStratum(EStratum currentStratum, EStratum_stack_model e_stack_model) throws SdaiException
	{
		EStratum_technology_occurrence e_sto = ops.sto_relatedTo_s_through_pdr(currentStratum, "definition");
		AStratum a_precedentStratum = new AStratum();
		if (e_sto != null)
		{
			AStratum_technology_occurrence_link a_stol = ops.All_stol_referencing_sto(e_sto);
			SdaiIterator it_stol = a_stol.createIterator();
			while (it_stol.next()) 
			{
				EStratum_technology_occurrence_link e_stol = (EStratum_technology_occurrence_link) a_stol.getCurrentMemberEntity(it_stol);
				EEntity e_stol_definition = e_stol.getDefinition(null);
				if (e_stol_definition == e_stack_model)
				{
					EStratum_technology_occurrence e_sto2 = (EStratum_technology_occurrence) e_stol.getRelating_property_definition(null);
					EStratum e_precedentStratum = ops.s_relatedTo_sto_through_pdr(e_sto2, "definition");
					a_precedentStratum.addUnordered(e_precedentStratum);
				}
			}
			return a_precedentStratum;
		}
		return a_precedentStratum;
	}
	
	public EStratum getSubsequentStratum(EStratum currentStratum, EStratum_stack_model e_stack_model) throws SdaiException
	{
		EStratum_technology_occurrence e_sto = ops.sto_relatedTo_s_through_pdr(currentStratum, "definition");
		if (e_sto != null)
		{
			AStratum_technology_occurrence_link a_stol = ops.All_stol_referencing_sto_2(e_sto);
			SdaiIterator it_stol = a_stol.createIterator();
			while (it_stol.next()) 
			{
				EStratum_technology_occurrence_link e_stol = (EStratum_technology_occurrence_link) a_stol.getCurrentMemberEntity(it_stol);
				EEntity e_stol_definition = e_stol.getDefinition(null);
				if (e_stol_definition == e_stack_model)
				{
					EStratum_technology_occurrence e_sto2 = (EStratum_technology_occurrence) e_stol.getRelated_property_definition(null);
					EStratum e_subsequentStratum = ops.s_relatedTo_sto_through_pdr(e_sto2, "definition");
					return e_subsequentStratum;
				}
			}
		}
		return null;
	}

	public AStratum getAllAdjacentSubsequentStratum(EStratum currentStratum, EStratum_stack_model e_stack_model) throws SdaiException
	{
		EStratum_technology_occurrence e_sto = ops.sto_relatedTo_s_through_pdr(currentStratum, "definition");
		AStratum a_subsequentStratum = new AStratum();
		if (e_sto != null)
		{
			AStratum_technology_occurrence_link a_stol = ops.All_stol_referencing_sto_2(e_sto);
			SdaiIterator it_stol = a_stol.createIterator();
			while (it_stol.next()) 
			{
				EStratum_technology_occurrence_link e_stol = (EStratum_technology_occurrence_link) a_stol.getCurrentMemberEntity(it_stol);
				EEntity e_stol_definition = e_stol.getDefinition(null);
				if (e_stol_definition == e_stack_model)
				{
					EStratum_technology_occurrence e_sto2 = (EStratum_technology_occurrence) e_stol.getRelated_property_definition(null);
					EStratum e_subsequentStratum = ops.s_relatedTo_sto_through_pdr(e_sto2, "definition");
					a_subsequentStratum.addUnordered(e_subsequentStratum);
				}
			}
			return a_subsequentStratum;
		}
		return a_subsequentStratum;
	}
	
	public AStratum_technology_occurrence_link getAllSTOLinVerticalExtentOfInterStratumFeature(EInter_stratum_feature isf) throws SdaiException
	{
		EPassage_technology_allocation_to_stack_model ptatsm = ops.ptatsm_relatedTo_isf_through_pdr(isf, "vertical extent");
		
		AStratum_technology_occurrence_link a_stol = ops.All_stol_relatedTo_ptatsm_through_pdr(ptatsm, "stratum technology sequence");

		return a_stol;
	}
	
	public EStratum_technology_occurrence_link getMostPrecedentSTOLinContiguousSetOfSTOL(AStratum_technology_occurrence_link a_stol) throws SdaiException
	{
		EStratum_stack_model e_ssm = null;
		
		Set<EStratum_technology_occurrence> referencedPrecedentSet = new HashSet<EStratum_technology_occurrence>();
		Map<EStratum_technology_occurrence, EStratum_technology_occurrence_link> referencedPrecedentMap = new HashMap<EStratum_technology_occurrence, EStratum_technology_occurrence_link>();
		
		Set<EStratum_technology_occurrence> referencedSubsequentSet = new HashSet<EStratum_technology_occurrence>();
		
		SdaiIterator it = a_stol.createIterator();
		while (it.next()) {
			EStratum_technology_occurrence_link e_stol = (EStratum_technology_occurrence_link) a_stol.getCurrentMemberEntity(it);
			if (e_ssm == null)
				e_ssm = ops.ssm_referencedBy_stol(e_stol);
			EStratum_technology_occurrence precedent_sto = ops.sto_referencedBy_stol(e_stol);
			EStratum_technology_occurrence subsequent_sto = ops.sto_referencedBy_stol_2(e_stol);
			referencedPrecedentSet.add(precedent_sto);
			referencedPrecedentMap.put(precedent_sto, e_stol);
			referencedSubsequentSet.add(subsequent_sto);
		}
		
		referencedPrecedentSet.removeAll(referencedSubsequentSet);
		if (referencedPrecedentSet.size() != 1)
		{
			System.out.println("Warning: getMostPrecedentSTOLinContiguousSetOfSTOL - unable to identify unique STOL");
			return null;
		}
		Iterator<EStratum_technology_occurrence> rps_it = referencedPrecedentSet.iterator();
		EStratum_technology_occurrence mp_sto = rps_it.next();
		return referencedPrecedentMap.get(mp_sto);
	}
	
	public EStratum_technology_occurrence_link getMostSubsequentSTOLinContiguousSetOfSTOL(AStratum_technology_occurrence_link a_stol) throws SdaiException
	{
		EStratum_stack_model e_ssm = null;
		
		Set<EStratum_technology_occurrence> referencedPrecedentSet = new HashSet<EStratum_technology_occurrence>();
		Map<EStratum_technology_occurrence, EStratum_technology_occurrence_link> referencedSubsequentMap = new HashMap<EStratum_technology_occurrence, EStratum_technology_occurrence_link>();
		
		Set<EStratum_technology_occurrence> referencedSubsequentSet = new HashSet<EStratum_technology_occurrence>();
		
		SdaiIterator it = a_stol.createIterator();
		while (it.next()) {
			EStratum_technology_occurrence_link e_stol = (EStratum_technology_occurrence_link) a_stol.getCurrentMemberEntity(it);
			if (e_ssm == null)
				e_ssm = ops.ssm_referencedBy_stol(e_stol);
			EStratum_technology_occurrence precedent_sto = ops.sto_referencedBy_stol(e_stol);
			EStratum_technology_occurrence subsequent_sto = ops.sto_referencedBy_stol_2(e_stol);
			referencedPrecedentSet.add(precedent_sto);
			referencedSubsequentSet.add(subsequent_sto);
			referencedSubsequentMap.put(subsequent_sto, e_stol);
		}
		
		referencedSubsequentSet.removeAll(referencedPrecedentSet);
		if (referencedSubsequentSet.size() != 1)
		{
			System.out.println("Warning: getMostSubsequentSTOLinContiguousSetOfSTOL - unable to identify unique STOL");
			return null;
		}
		Iterator<EStratum_technology_occurrence> rss_it = referencedSubsequentSet.iterator();
		EStratum_technology_occurrence ms_sto = rss_it.next();
		return referencedSubsequentMap.get(ms_sto);
	}
	
	/*
	public EShape_aspect_relationship getASSDofSTOL(EStratum_technology_occurrence_link stol) throws SdaiException
	{
		EStratum_technology_occurrence_to_stratum_mapping_relationship sto2smr = ops.stotsmr_relatedTo_stol_through_pdr(stol, "technology occurrence link");
		EProperty_definition pd = ops.pd_relatedTo_stotsmr_through_pdr(sto2smr, "stratum link");
		EShape_aspect_relationship sar = ops.sar_referencedBy_pd(pd, "adjacent stratum surface definition");
		return sar;
	}
	*/
	
	/*
	public EStratum getSubsequentStratumOfASSD(EShape_aspect_relationship sar) throws SdaiException
	{
		EStratum_surface primarySurfaceOfSubsequent = ops.ss_referencedBy_sar(sar, "primary surface");
		if (primarySurfaceOfSubsequent != null)
		{
			EStratum subsequentStratum = ops.s_referencedBy_ss(primarySurfaceOfSubsequent);
			return subsequentStratum;
		}
		return null;
	}
	
	public EStratum getPrecedentStratumOfASSD(EShape_aspect_relationship sar) throws SdaiException
	{
		EStratum_surface secondarySurfaceOfPrecedent = ops.ss_referencedBy_sar_2(sar, "secondary surface");
		if (secondarySurfaceOfPrecedent != null)
		{
			EStratum precendentStratum = ops.s_referencedBy_ss(secondarySurfaceOfPrecedent);
			return precendentStratum;
		}
		return null;
	}
	*/
	
	public EStratum getMostPrecedentStratumInContiguousSetOfSTOL(AStratum_technology_occurrence_link a_stol) throws SdaiException
	{
		EStratum_technology_occurrence_link mp_stol = this.getMostPrecedentSTOLinContiguousSetOfSTOL(a_stol);
		EStratum_technology_occurrence mp_sto = (EStratum_technology_occurrence) mp_stol.getRelating_property_definition(null);
		EStratum mp_stratum = ops.s_relatedTo_sto_through_pdr(mp_sto, "definition");
		//EShape_aspect_relationship mp_assd = this.getASSDofSTOL(mp_stol);
		//EStratum mp_stratum = this.getPrecedentStratumOfASSD(mp_assd);
		//return mp_stratum;
		return mp_stratum;
	}
	
	public EStratum getMostSubsequentStratumInContiguousSetOfSTOL(AStratum_technology_occurrence_link a_stol) throws SdaiException
	{
		EStratum_technology_occurrence_link ms_stol = this.getMostSubsequentSTOLinContiguousSetOfSTOL(a_stol);
		EStratum_technology_occurrence ms_sto = (EStratum_technology_occurrence) ms_stol.getRelated_property_definition(null);
		EStratum ms_stratum = ops.s_relatedTo_sto_through_pdr(ms_sto, "definition");
		//EShape_aspect_relationship ms_assd = this.getASSDofSTOL(ms_stol);
		//EStratum ms_stratum = this.getSubsequentStratumOfASSD(ms_assd);
		//return ms_stratum;
		return ms_stratum;
	}
	
	public StratumSpan getSpanOfInterStratumFeature(EInter_stratum_feature e_isf) throws SdaiException
	{
		EPassage_technology_allocation_to_stack_model ptatsm = ops.ptatsm_relatedTo_isf_through_pdr(e_isf, "vertical extent");
		AStratum_technology_occurrence_link a_stol = ops.All_stol_relatedTo_ptatsm_through_pdr(ptatsm, "stratum technology sequence");
		EStratum mp_stratum = getMostPrecedentStratumInContiguousSetOfSTOL(a_stol);
		EStratum ms_stratum = getMostSubsequentStratumInContiguousSetOfSTOL(a_stol);
		StratumSpan span = new StratumSpan(mp_stratum, ms_stratum);
		return span;
	}

	public AProduct getAllProductsOfASpecificPRPC(String categoryName) throws SdaiException
	{
		AProduct a_return = new AProduct();
		SdaiIterator it_return = a_return.createIterator();
		AProduct_related_product_category a_prpc = ops.All_prpc_inModel(categoryName);

		SdaiIterator it = a_prpc.createIterator();
		while (it.next()) {
			EProduct_related_product_category e_prpc = (EProduct_related_product_category) a_prpc.getCurrentMemberEntity(it);
			AProduct a_current = ops.All_p_referencedBy_prpc(e_prpc);
			SdaiIterator it_current = a_current.createIterator();
			while (it_current.next()) {
				EProduct e_current = (EProduct) a_current.getCurrentMemberEntity(it_current);
				a_return.addAfter(it_return, e_current);
			}
		}
		return a_return;
	}
	
	public AProduct getAllParts() throws SdaiException
	{
		return getAllProductsOfASpecificPRPC("part");
	}
	
	public AProduct getAllTemplates() throws SdaiException
	{
		return getAllProductsOfASpecificPRPC("template model");
	}
	
	public AProduct getAllDocuments() throws SdaiException
	{
		return getAllProductsOfASpecificPRPC("document");
	}
	
	
	public boolean isProductInASpecificPRPC(EProduct e_p, String categoryName) throws SdaiException
	{
		AProduct_related_product_category a_prpc = ops.All_prpc_referencing_p(e_p);

		SdaiIterator it = a_prpc.createIterator();
		while (it.next()) {
			EProduct_related_product_category e_prpc = (EProduct_related_product_category) a_prpc.getCurrentMemberEntity(it);
			if (e_prpc.getName(null).equals(categoryName))
					return true;
		}
		return false;
	}
	
	public AProduct getAllProductsWithGivenAssignedClass(String className) throws SdaiException
	{
		AProduct a_productsOfCategory = new AProduct();

		AProduct all_products = ops.All_p_inModel();
		
		SdaiIterator it = all_products.createIterator();
		while (it.next()) {
			EProduct e_p = (EProduct) all_products.getCurrentMemberEntity(it);
		
			AClass a_c = ops.All_c_relatedTo_p_through_aca(e_p);
			
			SdaiIterator c_it = a_c.createIterator();
			while (c_it.next()) {
				EClass e_cc = (EClass) a_c.getCurrentMemberEntity(c_it);
				if (e_cc.getName(null).equals(className))
					a_productsOfCategory.addUnordered(e_p);
			}
		}	
		return a_productsOfCategory;
	}

	public AClass getAllAssigningClassForProduct(EProduct e_p) throws SdaiException
	{
		AClass a_c = ops.All_c_relatedTo_p_through_aca(e_p);
		return a_c;
	}
	
	public AParameter_assignment getAllParameterAssignmentsForProduct(EProduct p) throws SdaiException
	{
		AParameter_assignment a_pa = new AParameter_assignment();

		AProduct_specific_parameter_value_assignment a_pspva = ops.All_pspva_referencing_p(p);
		
		SdaiIterator it = a_pspva.createIterator();
		while (it.next()) {
			EProduct_specific_parameter_value_assignment e_pspva = (EProduct_specific_parameter_value_assignment) a_pspva.getCurrentMemberEntity(it);
			
			EProperty_definition pd = ops.pd_referencing_pspva(e_pspva, "assigned parameter");
			try
			{
				EParameter_assignment e_pa = ops.pa_relatedTo_pd_through_pdr(pd);
				a_pa.addUnordered(e_pa);
			}
			catch (SdaiException sdaiE)
			{
				// continue on if the used_representation attribute of the property_definition_representation
				// is not set - can happen for non-parameter property_definition
			}
		}		
		return a_pa;
	}

	public AProduct_definition getAllProductDefinitionsOfSpecifiedProductDefinitionContextRole(
			AProduct_definition a_pd, String contextRole, String contextRoleFrameOfReference) throws SdaiException
	{
		AProduct_definition a_qualifyingProductDefinition = new AProduct_definition();

		SdaiIterator it_pd = a_pd.createIterator();
		while (it_pd.next())
		{
			EProduct_definition e_pd = (EProduct_definition) a_pd.getCurrentMemberEntity(it_pd);
			AProduct_definition_context_association a_pdca = ops.All_pdca_referencing_pd(e_pd);
			
			SdaiIterator it_pdca = a_pdca.createIterator();
			while (it_pdca.next())
			{
				EProduct_definition_context_association e_pdca = (EProduct_definition_context_association) a_pdca.getCurrentMemberEntity(it_pdca);

				EProduct_definition_context_role e_pdcr = ops.pdcr_referencedBy_pdca(e_pdca, contextRole);
				
				if (e_pdcr != null)
				{
					EProduct_definition_context e_pdc = ops.pdc_referencedBy_pdca(e_pdca, contextRoleFrameOfReference);
					if (e_pdc != null)
						a_qualifyingProductDefinition.addUnordered(e_pd);
				}
			}
		}
		return a_qualifyingProductDefinition;
	}
	
	public AProduct_definition getQualifiedProductDefinitions( 
			String contextRole,
			String contextRoleFrameOfReference) throws SdaiException
	{
		AProduct_definition a_pd = new AProduct_definition();

		AProduct a_productsOfCategory = ops.All_p_inModel();

		SdaiIterator it = a_productsOfCategory.createIterator();
		while (it.next()) {
			EProduct e_p = (EProduct) a_productsOfCategory.getCurrentMemberEntity(it);

			EProduct_definition_formation e_pdf = ops.pdf_referencing_p(e_p);

			AProduct_definition a_pd1 = ops.All_pd_referencing_pdf(e_pdf);
			AProduct_definition a_pd2 = getAllProductDefinitionsOfSpecifiedProductDefinitionContextRole(a_pd1, 
					contextRole, contextRoleFrameOfReference);
			
			SdaiIterator it_pd = a_pd2.createIterator();
			while (it_pd.next()) {
				EProduct_definition e_pd = (EProduct_definition) a_pd2.getCurrentMemberEntity(it_pd);
				a_pd.addUnordered(e_pd);
			}
		}
		return a_pd;
	}
	

	public EShape_representation getShapeRepresentationOfProductDefinitionShape(EProduct_definition_shape pds) throws SdaiException
	{
		int ppsmCount = 0;
		EShape_representation sr = null;
		AShape_representation a_sr = ops.All_sr_relatedTo_pds_through_sdr(pds);
			SdaiIterator it_sr = a_sr.createIterator();
			while (it_sr.next())
			{
				EShape_representation current_sr = (EShape_representation) a_sr.getCurrentMemberEntity(it_sr);
				if (current_sr.getName(null).equals("ppsm"))
				{
					sr = current_sr;
					ppsmCount++;
				}
			}
		//EShape_representation sr = ops.sr_relatedTo_pds_through_sdr(pds);
		//if (ppsmCount != 1)
		//	System.out.println("Warning: Could not find a unique ppsm for: "+pds);
		return sr;
	}
	
	public EProduct_definition getUsageViewOfProductDefinition(EProduct_definition e_pd) throws SdaiException
	{
		EProduct_definition e_usageView = ops.pd_relatedTo_pd_through_pdr(e_pd, "design usage");
		return e_usageView;
	}
	
	 
	public ALayered_assembly_module_design_view getPcas() throws SdaiException
	{
		AProduct_definition a_pd = getQualifiedProductDefinitions(
				"part definition type", "physical design");
		
		ALayered_assembly_module_design_view a_pca = new ALayered_assembly_module_design_view();
		
		SdaiIterator it_pd = a_pd.createIterator();
		while (it_pd.next()) {
			EProduct_definition e_pd = (EProduct_definition) a_pd.getCurrentMemberEntity(it_pd);
			if (e_pd.isKindOf(ELayered_assembly_module_design_view.class))
			{
				ELayered_assembly_module_design_view e_ad = (ELayered_assembly_module_design_view) a_pd.getCurrentMemberEntity(it_pd);
				a_pca.addUnordered(e_ad);
			}
		}
		return a_pca;
	}
	
	
	public ALayered_interconnect_module_design_view getPcbs() throws SdaiException
	{
		AProduct_definition a_pd = getQualifiedProductDefinitions(
				"part definition type", "physical design");
		
		ALayered_interconnect_module_design_view a_pcb = new ALayered_interconnect_module_design_view();
		
		SdaiIterator it_pd = a_pd.createIterator();
		while (it_pd.next()) {
			EProduct_definition e_pd = (EProduct_definition) a_pd.getCurrentMemberEntity(it_pd);
			if (e_pd.isKindOf(ELayered_interconnect_module_design_view.class))
			{
				if ((!e_pd.isKindOf(ELayered_interconnect_panel_design_view.class)))
				{
					ELayered_interconnect_module_design_view e_pcb = (ELayered_interconnect_module_design_view) a_pd.getCurrentMemberEntity(it_pd);
					a_pcb.addUnordered(e_pcb);
				}
			}
		}
		return a_pcb;
	}
	
	
	public ALayered_interconnect_panel_design_view getPanels() throws SdaiException
	{
		AProduct_definition a_pd = getQualifiedProductDefinitions(
				"part definition type", "physical design");
		
		ALayered_interconnect_panel_design_view a_pcb = new ALayered_interconnect_panel_design_view();
		
		SdaiIterator it_pd = a_pd.createIterator();
		while (it_pd.next()) {
			EProduct_definition e_pd = (EProduct_definition) a_pd.getCurrentMemberEntity(it_pd);
			if (e_pd.isKindOf(ELayered_interconnect_panel_design_view.class))
			{
				ELayered_interconnect_panel_design_view e_pcb = (ELayered_interconnect_panel_design_view) a_pd.getCurrentMemberEntity(it_pd);
				a_pcb.addUnordered(e_pcb);
			}
		}
		return a_pcb;
	}
	
	
	public AInterconnect_module_component getInterconnectModuleComponents() throws SdaiException
	{
		AInterconnect_module_component a_return = new AInterconnect_module_component();

		AProduct a_productsOfCategory = this.getAllProductsWithGivenAssignedClass("interconnect");

		SdaiIterator it = a_productsOfCategory.createIterator();
		while (it.next()) {
			EProduct e_p = (EProduct) a_productsOfCategory.getCurrentMemberEntity(it);

			EProduct_definition_formation e_pdf = ops.pdf_referencing_p(e_p);

			AInterconnect_module_component a_imc = ops.All_imc_referencing_pdf(e_pdf);
			
			SdaiIterator it_imc = a_imc.createIterator();
			while (it_imc.next()) {
				EInterconnect_module_component e_imc = (EInterconnect_module_component) a_imc.getCurrentMemberEntity(it_imc);
				a_return.addUnordered(e_imc);
			}
		}
		return a_return;
	}

	
	public AInterconnect_module_component getAllInterconnectModuleComponentsInPanel(ELayered_interconnect_panel_design_view panel) throws SdaiException
	{
		AInterconnect_module_component a_imc = ops.All_imc_relatedTo_lipdv_through_nauor(panel);
		return 	a_imc;
	}
	
	public AInterconnect_module_component getAllInterconnectModuleComponentsInPCA(ELayered_assembly_module_design_view pca) throws SdaiException
	{
		AInterconnect_module_component a_imc = ops.All_imc_relatedTo_lamdv_through_nauor(pca);
		return 	a_imc;
	}
	
	
	public APackaged_component getAllPackagedComponentsInAssembly(ELayered_assembly_module_design_view e_ad) throws SdaiException
	{
		APackaged_component a_pc = ops.All_pc_relatedTo_lamdv_through_nauor(e_ad);								
		return 	a_pc;
	}
	
	public EProduct getProductOfPackagedComponent(EPackaged_component e_pc) throws SdaiException
	{
		EPackaged_part e_pp = (EPackaged_part) e_pc.getRelating_product_definition(null);
		EProduct_definition_formation e_pdf = e_pp.getFormation(null);
		EProduct e_p = e_pdf.getOf_product(null);
		return e_p;
	}
	
	public EPackage getPackageOfPackagedComponent(EPackaged_component pc, EShape_representation sr1) throws SdaiException
	{
		ENext_assembly_usage_occurrence_relationship nauor = ops.nauor_referencing_pd(pc);
		AProduct_definition_shape a_pds = ops.All_pds_referencing_nauor(nauor);
		
		AComponent_2d_location a_c2dl = null;
		EComponent_2d_location e_c2dl = null;
		SdaiIterator it_pds = a_pds.createIterator();
		while (it_pds.next()) {
			EProduct_definition_shape e_pds = (EProduct_definition_shape) a_pds.getCurrentMemberEntity(it_pds);
			a_c2dl = ops.All_c2dl_referencing_pds(e_pds);
			e_c2dl = ops.c2dl_referencingGiven_2(a_c2dl, sr1);
			if (e_c2dl != null)
				break;
		}
		EShape_representation e_sr2 = ops.sr_referencedBy_c2dl(e_c2dl);
		EPackage e_p = ops.p_relatedTo_sr_through_sdr(e_sr2);
		return e_p;
	}
	
	/**
	 * @deprecated
	 * this implementation, although direct, is slow in practice due to the large number of 
	 * product_definition_relationships that typically reference a packaged_part
	 */
	public EPackage oldGetPackageOfPackagedComponent(EPackaged_component pc, EShape_representation sr1) throws SdaiException
	{
		EPackaged_part e_pp = ops.pp_referencedBy_pc(pc);
		EPackage e_p = ops.p_relatedTo_pp_through_pdr(e_pp, "used package");
		return e_p;
	}

	
	public EShape_representation getShapeRepresentationOfPackageWithSpecifiedPurpose(EPackage p, String purpose) throws SdaiException
	{
		AShape_representation a_sr = ops.All_sr_relatedTo_p_through_sdr(p);
		
		
		return getShapeRepresentationWithSpecifiedPurpose(a_sr, purpose);
	}
	
	public EShape_representation getShapeRepresentationWithSpecifiedPurpose(AShape_representation a_sr, String purpose) throws SdaiException
	{
		SdaiIterator it_sr = a_sr.createIterator();
		while (it_sr.next()) {
			EShape_representation e_sr = (EShape_representation) a_sr.getCurrentMemberEntity(it_sr);
			ARepresentation a_rep = ops.All_r_relatedTo_sr_through_rr(e_sr);
			
			SdaiIterator it_rep = a_rep.createIterator();
			while (it_rep.next()) {
				ERepresentation e_rep = (ERepresentation) a_rep.getCurrentMemberEntity(it_rep);
				if (e_rep.getName(null).equals("predefined shape purpose"))
				{
					ARepresentation_item a_ri = ops.All_ri_referencedBy_r(e_rep,purpose);
					if (a_ri.getMemberCount()>0)
						return e_sr;
				}
			}
		}								
		return null;
	}
	
	public AShape_representation getAllKeepoutsForPhysicalUnitShapeModel(EShape_representation e_pupsm) throws SdaiException
	{
		AShape_representation a_sr = ops.All_sr_relatedTo_sr_through_rr(e_pupsm, "model shape");
		
		AShape_representation a_keepouts = new AShape_representation();
		
		SdaiIterator it_sr = a_sr.createIterator();
		while (it_sr.next()) 
		{
			EShape_representation e_sr = (EShape_representation) a_sr.getCurrentMemberEntity(it_sr);
			if ((e_sr.testName(null)) && (e_sr.getName(null).equals("pupksm")))
			{
				a_keepouts.addUnordered(e_sr);
			}
		}
		return a_keepouts;
	}
	
	public EShape_representation getKeepoutShapeRepresentationWithSpecifiedKeepoutCategory(AShape_representation a_sr, String description) throws SdaiException
	{
		// need to iterate over all shape_representations
		// for each, must iterate over all possible pdr's to find the one that links to the keepout_design_object_category
		SdaiIterator it_sr = a_sr.createIterator();
		while (it_sr.next()) {
			EShape_representation e_sr = (EShape_representation) a_sr.getCurrentMemberEntity(it_sr);
			AProperty_definition a_pd = ops.All_pd_relatedTo_sr_through_pdr(e_sr);
			
			SdaiIterator it_pd = a_pd.createIterator();
			while (it_pd.next()) {
				EProperty_definition e_pd = (EProperty_definition) a_pd.getCurrentMemberEntity(it_pd);
				EEntity e_e = e_pd.getDefinition(null);
				if (e_e.isKindOf(EKeepout_design_object_category.class))
				{
					EKeepout_design_object_category e_kdoc = (EKeepout_design_object_category) e_e;
					String category = e_kdoc.getDescription(null);
					if (category.equals(description))
						return e_sr;
				}
			}
		}
		return null;
	}
	
	
	public AAssembly_joint getAllAssemblyJointsInPca(ELayered_assembly_module_design_view e_ad) throws SdaiException
	{
		AAssembly_joint a_aj = ops.All_aj_referencing_lamdv(e_ad);
		return 	a_aj;
	}

	
	public EPackaged_part_terminal getJoinTerminalForAssemblyJoint(EAssembly_joint e_aj) throws SdaiException
	{
		EPhysical_component_terminal e_pct = ops.pct_referencedBy_aj(e_aj, "packaged component join terminal");
		EPackaged_part_terminal e_ppt = ops.ppt_relatedTo_pct_through_sar(e_pct, "instantiated feature");
		if (e_ppt.getDescription(null).equals("join terminal"))
			return e_ppt;
		else
			return null;
	}
	
	
	public ELaminate_component getLaminateComponentForAssemblyJoint(EAssembly_joint aj) throws SdaiException
	{
		EPhysical_component_terminal e_imct = ops.pct_referencedBy_aj_2(aj, "interconnect module component terminal");
		EInterconnect_module_interface_terminal e_imit = ops.imit_relatedTo_pct_through_sar(e_imct, "instantiated feature");
		ELaminate_component_interface_terminal e_lcit = ops.lcit_relatedTo_imit_through_sar_2(e_imit, "component terminal to interconnect module interface terminal assignment");
		if (e_lcit != null)
		{
			ELaminate_component e_lc = ops.lc_referencedBy_sa(e_lcit);
			return e_lc;
		}
		return null;
	}
	
	public ELaminate_component getLaminateComponentForIMCT(EPhysical_component_terminal e_imct) throws SdaiException
	{
		EInterconnect_module_interface_terminal e_imit = ops.imit_relatedTo_pct_through_sar(e_imct, "instantiated feature");
		if (e_imit == null)
		{
			//Console.DEBUGprintln("No imit found for pct: "+e_imct);
			return null;
		}
		ELaminate_component_interface_terminal e_lcit = ops.lcit_relatedTo_imit_through_sar_2(e_imit, "component terminal to interconnect module interface terminal assignment");
		if (e_lcit != null)
		{
			ELaminate_component e_lc = ops.lc_referencedBy_sa(e_lcit);
			return e_lc;
		}
		return null;
	}
	
	
	public EInterconnect_module_interface_terminal getIMITforAC(EAssembly_component e_ac) throws SdaiException
	{
		ELaminate_component_interface_terminal e_lcit = ops.lcit_referencing_ac(e_ac);
		if (e_lcit == null)
			return null;
		EInterconnect_module_interface_terminal e_imit = ops.imit_relatedTo_lcit_through_sar(e_lcit, "component terminal to interconnect module interface terminal assignment");
		return e_imit;
	}
	
	
	public APart_tooling_feature getPartToolingFeaturesInPcb(ELayered_interconnect_module_usage_view e_pcb_usage_view, EShape_representation e_sr) throws SdaiException
	{
		APart_tooling_feature a_ptf = ops.All_ptf_relatedTo_sr_through_ucur(e_sr);		
		return 	a_ptf;
	}
	
	
	public ELaminate_component getLaminateComponentForPartToolingFeature(EPart_tooling_feature e_ptf) throws SdaiException
	{
		EShape_aspect e_sa = ops.sa_relatedTo_ptf_through_sar(e_ptf, "stratum concept to physical usage view assignment");
		if (e_sa != null)
		{
			ELaminate_component e_lc = ops.lc_referencedBy_sa(e_sa);
			return 	e_lc;
		}
		return 	null;
	}
	
	public EInterconnect_module_edge getEdgeOfPcb(ELayered_interconnect_module_design_view id) throws SdaiException
	{
		EInterconnect_module_edge e_ime = ops.ime_relatedTo_limdv_through_nauor(id);
		return 	e_ime;
	}
	
	public ERepresentation getThicknessOfPcb(ELayered_interconnect_module_usage_view e_pcb_usage_view, String propertyName) throws SdaiException
	{
		EProperty_definition e_pd = ops.pd_referencing_pd(e_pcb_usage_view, propertyName);
		if (e_pd == null)
			return null;
		
		ERepresentation e_rep = ops.r_relatedTo_pd_through_pdr(e_pd);
		return e_rep;
	}
	
	public AStratum getAllStrataInPcb(ELayered_interconnect_module_design_view e_id) throws SdaiException
	{
		AStratum a_stratum = ops.All_s_relatedTo_limdv_through_acu(e_id, "interconnect module stratum assembly relationship");								
		return 	a_stratum;
	}
	
	
	public AStratum_feature_template_component getAllSftcDirectlyRelatedToPcb(ELayered_interconnect_module_design_view e_id) throws SdaiException
	{
		AStratum_feature_template_component a_sftc = ops.All_sftc_relatedTo_limdv_through_nauor(e_id);
		return 	a_sftc;
	}
	
	
	public AMaterial_removal_laminate_component getAllMrlcDirectlyRelatedToPcb(ELayered_interconnect_module_design_view e_id) throws SdaiException
	{
		AMaterial_removal_laminate_component a_mrlc = ops.All_mrlc_relatedTo_limdv_through_nauor(e_id);
		return 	a_mrlc;
	}
	
	
	public APadstack_occurrence getAllPadstackOccurrenceInPcb(ELayered_interconnect_module_design_view e_id) throws SdaiException
	{
		APadstack_occurrence a_po = ops.All_po_relatedTo_limdv_through_nauor(e_id);
		return 	a_po;
	}
	
	
	public AFootprint_occurrence getAllFootprintOccurrenceInPcb(ELayered_interconnect_module_design_view e_id) throws SdaiException
	{
		AFootprint_occurrence a_fo = ops.All_fo_relatedTo_limdv_through_nauor(e_id);
		return 	a_fo;
	}
	
	public AStructured_layout_component getAllStructuredLayoutComponentsInStructuredLayoutComponent(EStructured_layout_component e_slc) throws SdaiException
	{
		AStructured_layout_component a_slc = ops.All_slc_relatedTo_slc_through_slcsar(e_slc);
		return 	a_slc;
	}
	
	public ALaminate_component getAllLaminateComponentsInStructuredLayoutComponent(EStructured_layout_component e_slc) throws SdaiException
	{
		//AStructured_layout_component a_nested_slc = ops.All_slc_relatedTo_slc_through_slcsar(e_slc);
		//if (a_nested_slc.getMemberCount()> 0)
		//	System.out.println("Warning - Unsupported nested slc in: "+e_slc);
		ALaminate_component a_lc = ops.All_lc_relatedTo_slc_through_slcsar(e_slc);
		return 	a_lc;
	}
	
	public AMulti_stratum_special_symbol_component getAllMultiStratumSpecialSymbolComponentsInPcb(ELayered_interconnect_module_design_view e_id) throws SdaiException {
		AMulti_stratum_special_symbol_component a_msssc = ops.All_msssc_relatedTo_limdv_through_nauor(e_id);
		return 	a_msssc;
	}
	
	public ASingle_stratum_special_symbol_component getAllSingleStratumSpecialSymbolComponentsInPcb(ELayered_interconnect_module_design_view e_id) throws SdaiException {
		ASingle_stratum_special_symbol_component a_msssc = ops.All_ssssc_relatedTo_limdv_through_nauor(e_id);
		return 	a_msssc;
	}

	public ALaminate_text_string_component getAllLaminateTextStringComponents(ELayered_interconnect_module_design_view e_id) throws SdaiException
	{
		ALaminate_text_string_component a_ltsc = ops.All_ltsc_relatedTo_limdv_through_nauor(e_id);
		return 	a_ltsc;
	}
	
	public ANext_assembly_usage_occurrence_relationship getAllLaminateTextStringNAUOR(ELayered_interconnect_module_design_view e_id) throws SdaiException
	{
		ANext_assembly_usage_occurrence_relationship a_return = new ANext_assembly_usage_occurrence_relationship();
		ANext_assembly_usage_occurrence_relationship a_relationship = new ANext_assembly_usage_occurrence_relationship();
		CNext_assembly_usage_occurrence_relationship.usedinRelating_product_definition(null, (ELayered_interconnect_module_design_view)e_id, null, a_relationship);
		SdaiIterator it_entities = a_relationship.createIterator();
		while (it_entities.next()) 
		{
			ENext_assembly_usage_occurrence_relationship e_relationship = (ENext_assembly_usage_occurrence_relationship) a_relationship.getCurrentMemberEntity(it_entities);
			{
				EEntity e2 = e_relationship.getOccurrence(null);
				if (e2.isKindOf(ELaminate_text_string_component.class))
				{
					//ELaminate_text_string_component e_return = (ELaminate_text_string_component) e2;
					a_return.addUnordered(e_relationship);
				}
			}
		}
		return a_return;
	}
	

	public EText_literal getMessageOfLaminateTextStringComponent(ELaminate_text_string_component e_ltsc) throws SdaiException
	{
		EProperty_definition e_pd = ops.pd_referencing_ltsc(e_ltsc, "message contents");
		ERepresentation e_r = ops.r_relatedTo_pd_through_pdr(e_pd);
		EText_literal e_tl = ops.tl_referencedBy_r(e_r);
		return e_tl;
	}
	
	public AGeneric_laminate_text_component getGenericLaminateTextComponentsForLTSC(ELaminate_text_string_component e_ltsc) throws SdaiException
	{
		AGeneric_laminate_text_component a_gltc = ops.All_gltc_relatedTo_ltsc_through_nauor(e_ltsc);
		return 	a_gltc;
	}
	
	
	public APhysical_network getAllPhysicalNetworksInPcb(ELayered_interconnect_module_design_view e_id) throws SdaiException
	{
		APhysical_network a_pn = ops.All_pn_referencing_limdv(e_id);
		return 	a_pn;
	}

	
	public ALayer_connection_point getLCPsForPhysicalNetwork(EPhysical_network e_pn) throws SdaiException
	{
		ALayer_connection_point a_lcp_for_pn = new ALayer_connection_point();
		Set<ELayer_connection_point> Set_lcp_for_pn = new HashSet<ELayer_connection_point>();
		
		AJoin_shape_aspect a_jsa = ops.All_jsa_relatedTo_pn_through_sar(e_pn, "network topology");

		SdaiIterator it_jsa = a_jsa.createIterator();
		while (it_jsa.next()) 
		{
			EJoin_shape_aspect e_jsa = (EJoin_shape_aspect) a_jsa.getCurrentMemberEntity(it_jsa);
			ALayer_connection_point a_lcpi = ops.All_lcp_relatedTo_jsa_through_sar(e_jsa, "connected point");
			
			SdaiIterator it_lcp = a_lcpi.createIterator();
			while (it_lcp.next())
			{
				ELayer_connection_point e_lcp = (ELayer_connection_point) a_lcpi.getCurrentMemberEntity(it_lcp);
				if (!(Set_lcp_for_pn.contains(e_lcp)))
				{
					a_lcp_for_pn.addUnordered(e_lcp);
					Set_lcp_for_pn.add(e_lcp);
				}
			}
		}
		
		AJoin_shape_aspect a_jsa_ucr = ops.All_jsa_relatedTo_pn_through_sar(e_pn, "unrouted connectivity requirement");

		SdaiIterator it_jsa_ucr = a_jsa_ucr.createIterator();
		while (it_jsa_ucr.next()) 
		{
			EJoin_shape_aspect e_jsa = (EJoin_shape_aspect) a_jsa_ucr.getCurrentMemberEntity(it_jsa_ucr);
			ALayer_connection_point a_lcpi = ops.All_lcp_relatedTo_jsa_through_sar_2(e_jsa, "unrouted lcp");
			
			SdaiIterator it_lcp = a_lcpi.createIterator();
			while (it_lcp.next())
			{
				ELayer_connection_point e_lcp = (ELayer_connection_point) a_lcpi.getCurrentMemberEntity(it_lcp);
				if (!(Set_lcp_for_pn.contains(e_lcp)))
				{
					a_lcp_for_pn.addUnordered(e_lcp);
					Set_lcp_for_pn.add(e_lcp);
				}
			}
		}
		return a_lcp_for_pn;
	}
	
	public ALaminate_component_feature getLCFsForPhysicalNetwork(EPhysical_network e_pn) throws SdaiException
	{
		ALaminate_component_feature a_lcf_for_pn = new ALaminate_component_feature();
		Set<ELaminate_component_feature> Set_lcf_for_pn = new HashSet<ELaminate_component_feature>();
		
		ALaminate_component_feature a_lcf_cr = ops.All_lcf_relatedTo_pn_through_sar(e_pn, "connectivity requirement");
		
		SdaiIterator it_lcf_cr = a_lcf_cr.createIterator();
		while (it_lcf_cr.next())
		{
			ELaminate_component_feature e_lcf = (ELaminate_component_feature) a_lcf_cr.getCurrentMemberEntity(it_lcf_cr);
			if (!(Set_lcf_for_pn.contains(e_lcf)))
			{
				a_lcf_for_pn.addUnordered(e_lcf);
				Set_lcf_for_pn.add(e_lcf);
			}
		}
		
		
		AJoin_shape_aspect a_jsa = ops.All_jsa_relatedTo_pn_through_sar(e_pn, "unrouted connectivity requirement");

		SdaiIterator it_jsa = a_jsa.createIterator();
		while (it_jsa.next()) 
		{
			EJoin_shape_aspect e_jsa = (EJoin_shape_aspect) a_jsa.getCurrentMemberEntity(it_jsa);
			ALaminate_component_feature a_lcfi = ops.All_lcf_relatedTo_jsa_through_sar(e_jsa, "unrouted terminals");
			
			SdaiIterator it_lcf = a_lcfi.createIterator();
			while (it_lcf.next())
			{
				ELaminate_component_feature e_lcf = (ELaminate_component_feature) a_lcfi.getCurrentMemberEntity(it_lcf);
				if (!(Set_lcf_for_pn.contains(e_lcf)))
				{
					a_lcf_for_pn.addUnordered(e_lcf);
					Set_lcf_for_pn.add(e_lcf);
				}
			}
		}
		return a_lcf_for_pn;
	}
	
	
	public EInter_stratum_feature  getInterStratumFeatureForDLLCP(ELayer_connection_point e_lcp) throws SdaiException
	{
		if (!(e_lcp.getDescription(null).equals("dependently located")))
			return null;
		EShape_aspect e_sa = ops.sa_relatedTo_lcp_through_sar(e_lcp, "associated design object");
		EInter_stratum_feature e_isf = ops.isf_referencedBy_sa(e_sa);
		return e_isf;		
	}

	
	public APlated_passage_dependent_land getPPDLandsforDLLCP(ELayer_connection_point e_lcp) throws SdaiException
	{ 	
		EInter_stratum_feature e_isf = this.getInterStratumFeatureForDLLCP(e_lcp);
		if ((e_isf == null) || (!(e_isf.isKindOf(EPlated_passage.class))))
			return new APlated_passage_dependent_land();

		APlated_passage_dependent_land a_ppdl = ops.All_ppdl_relatedTo_isf_through_pdr(e_isf, "reference plated passage");
		return a_ppdl;
	}

	
	public EContact_size_dependent_land getCSDLandforDLLCP(ELayer_connection_point e_lcp) throws SdaiException
	{
		if (!(e_lcp.getDescription(null).equals("dependently located")))
			return null;

		EInterconnect_module_interface_terminal e_imit = ops.imit_relatedTo_lcp_through_sar(e_lcp, "associated design object");

		if (e_imit == null)
			return null;

		ELaminate_component_interface_terminal e_lcit = ops.lcit_relatedTo_imit_through_sar_2(e_imit, "component terminal to interconnect module interface terminal assignment");

		if (e_lcit == null)
			return null;

		if (!(e_lcit.getDescription(null).equals("land interface terminal")))
			return null;
		
		EContact_size_dependent_land e_csdl = ops.csdl_referencedBy_lcit(e_lcit);

		return e_csdl;
	}
	
	
	public AConductive_interconnect_element getCIEforLCP(ELayer_connection_point e_lcp) throws SdaiException
	{
		AConductive_interconnect_element a_cie = new AConductive_interconnect_element();
		Set<EConductive_interconnect_element> set_cie = new HashSet<EConductive_interconnect_element>();

		ALaminate_component_feature a_lcf = ops.All_lcf_relatedTo_lcp_through_sar(e_lcp, "terminal location");
		
		SdaiIterator it_lcf = a_lcf.createIterator();
		while (it_lcf.next())
		{
			ELaminate_component_feature e_lcf = (ELaminate_component_feature) a_lcf.getCurrentMemberEntity(it_lcf);
			if ((e_lcf.testDescription(null) && (e_lcf.getDescription(null).equals("conductive interconnect element terminal"))))
			{
				EConductive_interconnect_element e_cie = ops.cie_referencedBy_lcf(e_lcf);
				if (!(set_cie.contains(e_cie)))
				{
					set_cie.add(e_cie);
					a_cie.addUnordered(e_cie);
				}
			}
		}
		return a_cie;
	}
	
	
	public EConnected_area_component getConnectedAreaComponentforLCP(ELayer_connection_point e_lcp) throws SdaiException
	{
		EShape_aspect e_sa = ops.sa_relatedTo_lcp_through_sar(e_lcp, "associated layer connection point");
		
		if (e_sa == null)		
			return null;

		EConnected_area_component e_cac = ops.cac_referencedBy_sa(e_sa);
			
		return e_cac;
	}

	

	
	
	
	
	
	
}
