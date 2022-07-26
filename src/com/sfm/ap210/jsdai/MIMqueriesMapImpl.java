package com.sfm.ap210.jsdai;

import java.util.HashMap;
import java.util.Map;

import jsdai.SBasic_attribute_schema.EId_attribute;
import jsdai.SFabrication_technology_mim.AStratum_technology_occurrence_link;
import jsdai.SFabrication_technology_mim.EPassage_technology_allocation_to_stack_model;
import jsdai.SGeometry_schema.EAxis2_placement_2d;
import jsdai.SGeometry_schema.ECartesian_transformation_operator_2d;
import jsdai.SLayered_interconnect_complex_template_mim.EStructured_template;
import jsdai.SLayered_interconnect_module_design_mim.EInter_stratum_feature;
import jsdai.SLayered_interconnect_module_design_mim.ELaminate_component;
import jsdai.SLayered_interconnect_module_design_mim.EStratum;
import jsdai.SLayered_interconnect_module_design_mim.EStructured_layout_component;
import jsdai.SLayered_interconnect_module_design_mim.EStructured_layout_component_sub_assembly_relationship;
import jsdai.SMaterial_property_definition_schema.AProperty_definition_relationship;
import jsdai.SMaterial_property_definition_schema.EProperty_definition_relationship;
import jsdai.SPackage_mim.EPackage;
import jsdai.SPart_template_shape_with_parameters_mim.EGeometric_template;
import jsdai.SPhysical_unit_design_view_mim.EAssembly_component;
import jsdai.SPhysical_unit_design_view_mim.ENext_assembly_usage_occurrence_relationship;
import jsdai.SProduct_definition_schema.EProduct_definition;
import jsdai.SProduct_property_definition_schema.EProperty_definition;
import jsdai.SProduct_property_representation_schema.EItem_identified_representation_usage;
import jsdai.SProduct_property_representation_schema.EShape_representation;
import jsdai.SProduct_structure_schema.EAssembly_component_usage;
import jsdai.SRepresentation_schema.EMapped_item;
import jsdai.SRepresentation_schema.ERepresentation;
import jsdai.SRepresentation_schema.ERepresentation_map;
import jsdai.lang.SdaiException;
import jsdai.lang.SdaiIterator;
import jsdai.lang.SdaiModel;

/**
 * A map-accelerated implementation of the MIMqueries interface.
 * @author James Stori, SFM Technology, Inc.
 * @version 1.2
 * @see MIMqueries
 */

// @see <a href="C:\Documents and Settings\James Stori\My Documents\Workspace\AP2102ndEdExtraction\src\com\sfm\ap210\jsdai\MIMqueries_Pcb_v1.2.pdf">MIMqueries_Pcb_v1.2.pdf</a>
public class MIMqueriesMapImpl extends MIMqueriesImpl {

	Map<EGeometric_template, EShape_representation> geometricTemplateToShapeRepMap;
	Map<EStructured_template, EShape_representation> structuredTemplateToShapeRepMap;
	Map<String, EShape_representation> packageWithPurposeToShapeRepMap;
	Map<EAssembly_component_usage, EAxis2_placement_2d> firstLocationACUtoAxisPlacementMap;
	Map<EAssembly_component_usage, EShape_representation> firstLocationACUtoShapeRepresentationMap;
	Map<EAssembly_component_usage, EAxis2_placement_2d> secondLocationACUtoAxisPlacementMap;
	Map<EStructured_layout_component_sub_assembly_relationship, EAssembly_component_usage> slcsarToFirstLocationACUMap;
	Map<EStructured_layout_component_sub_assembly_relationship, EAssembly_component_usage> slcsarToSecondLocationACUMap;
	Map<EPassage_technology_allocation_to_stack_model, StratumSpan> ptatsmToSpanMap;
	Map<ENext_assembly_usage_occurrence_relationship, ECartesian_transformation_operator_2d> slcNauorToCto2dMap;
			
	public MIMqueriesMapImpl(SdaiModel m, MIMops ops) throws SdaiException {
		super(m);
		model = m;
		this.ops = ops;
		geometricTemplateToShapeRepMap = new HashMap<EGeometric_template, EShape_representation>();
		structuredTemplateToShapeRepMap = new HashMap<EStructured_template, EShape_representation>();
		packageWithPurposeToShapeRepMap = new HashMap<String, EShape_representation>();
		firstLocationACUtoAxisPlacementMap = new HashMap<EAssembly_component_usage, EAxis2_placement_2d>();
		firstLocationACUtoShapeRepresentationMap = new HashMap<EAssembly_component_usage, EShape_representation>();
		secondLocationACUtoAxisPlacementMap = new HashMap<EAssembly_component_usage, EAxis2_placement_2d>();
		ptatsmToSpanMap = new HashMap<EPassage_technology_allocation_to_stack_model, StratumSpan>();
		slcNauorToCto2dMap = new HashMap<ENext_assembly_usage_occurrence_relationship, ECartesian_transformation_operator_2d>();
		populateSLCSAR2ACUMaps();
	}
	
	public void setOps(MIMops ops)
	{
		this.ops = ops;
	}
	
	private void populateSLCSAR2ACUMaps() throws SdaiException
	{
		slcsarToFirstLocationACUMap = new HashMap<EStructured_layout_component_sub_assembly_relationship, EAssembly_component_usage>();
		slcsarToSecondLocationACUMap = new HashMap<EStructured_layout_component_sub_assembly_relationship, EAssembly_component_usage>();
		AProperty_definition_relationship a_pdr = (AProperty_definition_relationship)model.getInstances(EProperty_definition_relationship.class);
		SdaiIterator it = a_pdr.createIterator();
		while (it.next()) 
		{
			EProperty_definition_relationship e_pdr = (EProperty_definition_relationship)a_pdr.getCurrentMemberEntity(it);
			if ((e_pdr.testName(null)) && (e_pdr.getName(null).equals("first location")))
			{
				EProperty_definition e_pd1 = e_pdr.getRelated_property_definition(null);
				EStructured_layout_component_sub_assembly_relationship e_slcsar = (EStructured_layout_component_sub_assembly_relationship) e_pd1.getDefinition(null);
				EProperty_definition e_pd2 = e_pdr.getRelating_property_definition(null);
				EAssembly_component_usage e_acu = (EAssembly_component_usage) e_pd2.getDefinition(null);
				slcsarToFirstLocationACUMap.put(e_slcsar, e_acu);
			}
			else if ((e_pdr.testName(null)) && (e_pdr.getName(null).equals("second location")))
			{
				EProperty_definition e_pd1 = e_pdr.getRelated_property_definition(null);
				EStructured_layout_component_sub_assembly_relationship e_slcsar = (EStructured_layout_component_sub_assembly_relationship) e_pd1.getDefinition(null);
				EProperty_definition e_pd3 = e_pdr.getRelating_property_definition(null);
				EAssembly_component_usage e_acu = (EAssembly_component_usage) e_pd3.getDefinition(null);
				slcsarToSecondLocationACUMap.put(e_slcsar, e_acu);
			}
		}
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
			// appears that the following relationship is no longer populated in v3.4 converter
			// possible that it has been removed from the mapping.
			
			//EGeometric_template gt = ops.gt_relatedTo_ac_through_pdr_2(ac,"definition usage");
			
			
			EGeometric_template gt = null;
			EProduct_definition e_pd = ac.getRelating_product_definition(null);
			if (e_pd.isKindOf(EGeometric_template.class))
				gt = (EGeometric_template) e_pd;
			
			
			if (gt != null)
			{
				if (geometricTemplateToShapeRepMap.containsKey(gt))
					return geometricTemplateToShapeRepMap.get(gt);
				
				//EShape_representation sr2 = ops.sr_relatedTo_gt_through_sdr(gt);
				EShape_representation sr2 = this.getShapeRepresentationOfProductDefinitionShape(gt);
				geometricTemplateToShapeRepMap.put(gt, sr2);
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
	
	//	second map implementation using slcsarToFirstLocationACUMap, slcsarToSecondLocationACUMap, 
	//	firstLocationACUtoAxisPlacementMap and secondLocationACUtoAxisPlacementMap
	public MIMlocation getAxisPlacementOfSLCSAR(
			EStructured_layout_component_sub_assembly_relationship slcsar,
			EShape_representation sr1,
			EShape_representation sr2) throws SdaiException
	{
		EAxis2_placement_2d a2p2d1 = null;
		EAxis2_placement_2d a2p2d2 = null; 
			
		//EStructured_layout_component slc = ops.slc_referencedBy_slcsar(slcsar);	
		//EStructured_template st1 = ops.st_referencedBy_slc(slc);
		
		EAssembly_component_usage acu1 = slcsarToFirstLocationACUMap.get(slcsar);
			
		EShape_representation sr5 = null;
		
		if (firstLocationACUtoAxisPlacementMap.containsKey(acu1))
		{
			a2p2d1 = firstLocationACUtoAxisPlacementMap.get(acu1);
			sr5 = firstLocationACUtoShapeRepresentationMap.get(acu1);
		}
		else
		{
			EMapped_item mi1 = getTLISTTforTLIST(acu1, sr2); 
			ERepresentation_map rm1 = ops.rm_referencedBy_mi(mi1);
			sr5 = ops.sr_referencedBy_rm(rm1);
			a2p2d1 = ops.ap2d_referencedBy_mi(mi1);
			firstLocationACUtoAxisPlacementMap.put(acu1, a2p2d1);
			firstLocationACUtoShapeRepresentationMap.put(acu1, sr5);
		}
		
		if (slcsarToSecondLocationACUMap.containsKey(slcsar)) 
		{
			EAssembly_component_usage acu2 = slcsarToSecondLocationACUMap.get(slcsar);
			if (secondLocationACUtoAxisPlacementMap.containsKey(acu2))
				a2p2d2 = secondLocationACUtoAxisPlacementMap.get(acu2);
			else
			{
				EMapped_item mi2 = getTLISTTforTLIST(acu2, sr5);
				a2p2d2 = ops.ap2d_referencedBy_mi(mi2);
				secondLocationACUtoAxisPlacementMap.put(acu2, a2p2d2);
			}
		}
			
		MIMlocation mimLoc = new MIMlocation(null, a2p2d1, a2p2d2);
		return mimLoc;
	}
	
	// intermediate map implementation using firstLocationACUtoAxisPlacementMap and secondLocationACUtoAxisPlacementMap
	public MIMlocation getAxisPlacementOfSLCSAR2(
			EStructured_layout_component_sub_assembly_relationship slcsar,
			EShape_representation sr1,
			EShape_representation sr2) throws SdaiException
	{
		EAxis2_placement_2d a2p2d1 = null;
		EAxis2_placement_2d a2p2d2 = null; 
			
		EStructured_layout_component slc = ops.slc_referencedBy_slcsar(slcsar);	
		//EStructured_template st1 = ops.st_referencedBy_slc(slc);
		EProperty_definition pd1 = ops.pd_referencing_slcsar(slcsar);
			
		EProperty_definition pd2 = ops.pd_relatedTo_pd_through_pdr_2(pd1,"first location");
		EAssembly_component_usage acu1 = ops.acu_referencedBy_pd(pd2,"tlist");
			
		EShape_representation sr5 = null;
		
		if (firstLocationACUtoAxisPlacementMap.containsKey(acu1))
		{
			a2p2d1 = firstLocationACUtoAxisPlacementMap.get(acu1);
			sr5 = firstLocationACUtoShapeRepresentationMap.get(acu1);
		}
		else
		{
			EMapped_item mi1 = getTLISTTforTLIST(acu1, sr2); 
			ERepresentation_map rm1 = ops.rm_referencedBy_mi(mi1);
			sr5 = ops.sr_referencedBy_rm(rm1);
			a2p2d1 = ops.ap2d_referencedBy_mi(mi1);
			firstLocationACUtoAxisPlacementMap.put(acu1, a2p2d1);
			firstLocationACUtoShapeRepresentationMap.put(acu1, sr5);
		}
		
		EProperty_definition pd3 = ops.pd_relatedTo_pd_through_pdr_2(pd1,"second location");
		if (pd3 !=null) 
		{
			EAssembly_component_usage acu2 = ops.acu_referencedBy_pd(pd3,"tlist");
			if (secondLocationACUtoAxisPlacementMap.containsKey(acu2))
				a2p2d2 = secondLocationACUtoAxisPlacementMap.get(acu2);
			else
			{
				EMapped_item mi2 = getTLISTTforTLIST(acu2, sr5);
				a2p2d2 = ops.ap2d_referencedBy_mi(mi2);
				secondLocationACUtoAxisPlacementMap.put(acu2, a2p2d2);
			}
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
			
			EShape_representation srOfslc = null;
			if (structuredTemplateToShapeRepMap.containsKey(st))
				srOfslc = structuredTemplateToShapeRepMap.get(st);
			else
			{
				srOfslc = this.getShapeRepresentationOfProductDefinitionShape(st);
				structuredTemplateToShapeRepMap.put(st, srOfslc);
			}
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
	
	public ECartesian_transformation_operator_2d getLocationOfSLC(
			ENext_assembly_usage_occurrence_relationship nauor, 
			EShape_representation srOfslc, 
			EShape_representation sr2) throws SdaiException
	{
		if (slcNauorToCto2dMap.containsKey(nauor))
			return slcNauorToCto2dMap.get(nauor);
		else
		{
			ECartesian_transformation_operator_2d cto2d = this.getCartesianTransformationOfNAUOR(nauor, srOfslc, sr2);
			slcNauorToCto2dMap.put(nauor, cto2d);
			return cto2d;
		}
	}
	
	public MIMlocation getLocationOfLaminateComponentInSLC(ELaminate_component lc,
			EStructured_layout_component_sub_assembly_relationship slcsar,
			EStructured_layout_component slc,
			EShape_representation sr1,
			EShape_representation sr2) throws SdaiException
	{
		MIMlocation mimLoc = null;
		EShape_representation srOfslc = getShapeRepresentationOfSLC(slc);
		mimLoc = this.getAxisPlacementOfSLCSAR(slcsar, sr1, srOfslc);
		return mimLoc;
	}
	
	
	public MIMlocation getLocationOfAssemblyComponentInSLC(EAssembly_component ac,
			EStructured_layout_component_sub_assembly_relationship slcsar,
			EStructured_layout_component slc,
			EShape_representation srOfac) throws SdaiException
	{
		MIMlocation mimLoc = null;
		EShape_representation srOfslc = getShapeRepresentationOfSLC(slc);
		mimLoc = this.getAxisPlacementOfSLCSAR(slcsar, srOfac, srOfslc);
		return mimLoc;
	}
	
	public StratumSpan getSpanOfInterStratumFeature(EInter_stratum_feature e_isf) throws SdaiException
	{
		EPassage_technology_allocation_to_stack_model ptatsm = ops.ptatsm_relatedTo_isf_through_pdr(e_isf, "vertical extent");
		
		if (ptatsmToSpanMap.containsKey(ptatsm))
			return ptatsmToSpanMap.get(ptatsm);
		else
		{
			AStratum_technology_occurrence_link a_stol = ops.All_stol_relatedTo_ptatsm_through_pdr(ptatsm, "stratum technology sequence");
			EStratum mp_stratum = getMostPrecedentStratumInContiguousSetOfSTOL(a_stol);
			EStratum ms_stratum = getMostSubsequentStratumInContiguousSetOfSTOL(a_stol);
			StratumSpan span = new StratumSpan(mp_stratum, ms_stratum);
			ptatsmToSpanMap.put(ptatsm, span);
			return span;
		}
	}
	
	public EShape_representation getShapeRepresentationOfSLC(EStructured_layout_component slc) throws SdaiException
	{
		EStructured_template st = ops.st_referencedBy_slc(slc);
		
		EShape_representation srOfslc = null;
		if (structuredTemplateToShapeRepMap.containsKey(st))
			srOfslc = structuredTemplateToShapeRepMap.get(st);
		else
		{
			srOfslc = this.getShapeRepresentationOfProductDefinitionShape(st);
			structuredTemplateToShapeRepMap.put(st, srOfslc);
		}
		return srOfslc;
	}

	public EShape_representation getShapeRepresentationOfPackageWithSpecifiedPurpose(EPackage p, String purpose) throws SdaiException
	{
		EShape_representation srOfpackage = null;
		String key = p.getPersistentLabel()+purpose;
		if (packageWithPurposeToShapeRepMap.containsKey(key))
			srOfpackage = packageWithPurposeToShapeRepMap.get(key);
		else
		{
			srOfpackage = super.getShapeRepresentationOfPackageWithSpecifiedPurpose(p, purpose);
			packageWithPurposeToShapeRepMap.put(key, srOfpackage);
		}
		return srOfpackage;
	}
}
