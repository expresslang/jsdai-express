package com.sfm.ap210.jsdai;

import jsdai.SAssembly_module_design_mim.ALayered_assembly_module_design_view;
import jsdai.SAssembly_module_design_mim.APackaged_component;
import jsdai.SAssembly_module_design_mim.ELayered_assembly_module_design_view;
import jsdai.SAssembly_module_design_mim.EPackaged_component;
import jsdai.SAssembly_module_with_interconnect_component_mim.AInterconnect_module_component;
import jsdai.SAssembly_technology_mim.AAssembly_joint;
import jsdai.SAssembly_technology_mim.EAssembly_joint;
import jsdai.SClassification_schema.AClass;
import jsdai.SClassification_schema.EClass;
import jsdai.SClassification_with_attributes_mim.ACharacterized_class;
import jsdai.SFabrication_technology_mim.AStratum_technology_occurrence_link;
import jsdai.SFabrication_technology_mim.EStratum_stack_model;
import jsdai.SFabrication_technology_mim.EStratum_technology;
import jsdai.SFabrication_technology_mim.EStratum_technology_occurrence_link;
import jsdai.SGeometry_schema.ECartesian_transformation_operator_2d;
import jsdai.SInterconnect_module_usage_view_mim.EInterconnect_module_interface_terminal;
import jsdai.SInterconnect_module_usage_view_mim.ELayered_interconnect_module_usage_view;
import jsdai.SLand_mim.APlated_passage_dependent_land;
import jsdai.SLand_mim.EContact_size_dependent_land;
import jsdai.SLayered_interconnect_module_design_mim.AConductive_interconnect_element;
import jsdai.SLayered_interconnect_module_design_mim.AFootprint_occurrence;
import jsdai.SLayered_interconnect_module_design_mim.AGeneric_laminate_text_component;
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
import jsdai.SLayered_interconnect_module_design_mim.EConnected_area_component;
import jsdai.SLayered_interconnect_module_design_mim.EGeneric_laminate_text_component;
import jsdai.SLayered_interconnect_module_design_mim.EInter_stratum_feature;
import jsdai.SLayered_interconnect_module_design_mim.EInterconnect_module_edge;
import jsdai.SLayered_interconnect_module_design_mim.ELaminate_component;
import jsdai.SLayered_interconnect_module_design_mim.ELaminate_text_string_component;
import jsdai.SLayered_interconnect_module_design_mim.ELayer_connection_point;
import jsdai.SLayered_interconnect_module_design_mim.ELayered_interconnect_module_design_view;
import jsdai.SLayered_interconnect_module_design_mim.ELayered_interconnect_panel_design_view;
import jsdai.SLayered_interconnect_module_design_mim.EPhysical_network;
import jsdai.SLayered_interconnect_module_design_mim.EStratum;
import jsdai.SLayered_interconnect_module_design_mim.EStratum_feature;
import jsdai.SLayered_interconnect_module_design_mim.EStratum_feature_template_component;
import jsdai.SLayered_interconnect_module_design_mim.EStructured_layout_component;
import jsdai.SLayered_interconnect_module_design_mim.EStructured_layout_component_sub_assembly_relationship;
import jsdai.SMaterial_property_definition_schema.EMaterial_designation;
import jsdai.SModel_parameter_mim.AParameter_assignment;
import jsdai.SPackage_mim.EPackage;
import jsdai.SPackaged_part_black_box_model_mim.EPackaged_part_terminal;
import jsdai.SPart_feature_function_mim.APart_tooling_feature;
import jsdai.SPart_feature_function_mim.EPart_tooling_feature;
import jsdai.SPhysical_component_feature_mim.EPhysical_component_terminal;
import jsdai.SPhysical_unit_design_view_mim.ANext_assembly_usage_occurrence_relationship;
import jsdai.SPhysical_unit_design_view_mim.EAssembly_component;
import jsdai.SPhysical_unit_design_view_mim.ENext_assembly_usage_occurrence_relationship;
import jsdai.SPresentation_definition_schema.EText_literal;
import jsdai.SProduct_definition_schema.AProduct;
import jsdai.SProduct_definition_schema.AProduct_definition;
import jsdai.SProduct_definition_schema.AProduct_related_product_category;
import jsdai.SProduct_definition_schema.EProduct;
import jsdai.SProduct_definition_schema.EProduct_definition;
import jsdai.SProduct_definition_schema.EProduct_related_product_category;
import jsdai.SProduct_property_definition_schema.EProduct_definition_shape;
import jsdai.SProduct_property_definition_schema.EShape_aspect_relationship;
import jsdai.SProduct_property_representation_schema.AShape_representation;
import jsdai.SProduct_property_representation_schema.EShape_representation;
import jsdai.SProduct_structure_schema.EAssembly_component_usage;
import jsdai.SRepresentation_schema.EMapped_item;
import jsdai.SRepresentation_schema.ERepresentation;
import jsdai.lang.AEntity;
import jsdai.lang.EEntity;
import jsdai.lang.SdaiException;
import jsdai.lang.SdaiIterator;
import jsdai.lang.SdaiModel;

/**
 * The debugging wrapper implementation of the MIMqueries interface.
 * @author James Stori, SFM Technology, Inc.
 * @version 1.2
 * @see MIMqueries
 */
public class MIMqueriesDebugImpl implements MIMqueries {

	public int debug_mode;
	private SdaiModel model;
	private MIMops ops;
	private MIMqueries q;
	
	public MIMqueriesDebugImpl(SdaiModel m, MIMops ops)
	{
		this.model = m;
		this.ops = ops;
		q = null;
	}
	
	public void setOps(MIMops ops)
	{
		this.ops = ops;
	}
	
	public void setMIMqueriesImpl(MIMqueries qImpl)
	{
		q = qImpl;
	}
	
	protected void outputGivenDebugInfo() 
	{
	  Throwable t = new Throwable(); 
	  StackTraceElement[] elements = t.getStackTrace(); 
	  if (elements.length >= 2) 
	  {
		  String callingMethodName = elements[1].getMethodName();
		  System.out.println("----------------------");
		  System.out.println(callingMethodName);
	  }
	}
	
	protected void outputGivenDebugInfo(String value) 
	{
	  Throwable t = new Throwable(); 
	  StackTraceElement[] elements = t.getStackTrace(); 
	  if (elements.length >= 2) 
	  {
		  String callingMethodName = elements[1].getMethodName();
		  System.out.println("----------------------");
		  System.out.println(callingMethodName);
		  System.out.println("Given: '"+value+"'");
	  }
	}
	
	protected void outputGivenDebugInfo(EEntity e) 
	{
	  Throwable t = new Throwable(); 
	  StackTraceElement[] elements = t.getStackTrace(); 
	  if (elements.length >= 2) 
	  {
		  String callingMethodName = elements[1].getMethodName();
		  System.out.println("----------------------");
		  System.out.println(callingMethodName);
		  System.out.println("Given: "+e.toString());
	  }
	}
	
	protected void outputAdditionalGivenDebugInfo(EEntity e) 
	{
	  System.out.println("Given: "+e.toString());
	}
	
	protected void outputAdditionalGivenDebugInfo(String value) 
	{
		System.out.println("Given: '"+value+"'");
	}
	
	protected void outputGivenDebugInfo(EEntity e, String value) 
	{
	  Throwable t = new Throwable(); 
	  StackTraceElement[] elements = t.getStackTrace(); 
	  if (elements.length >= 2) 
	  {
		  String callingMethodName = elements[1].getMethodName();
		  System.out.println("----------------------");
		  System.out.println(callingMethodName);
		  System.out.println("Given: "+e.toString());
		  System.out.println("Given: '"+value+"'");
	  }
	}
	
	protected void outputGivenDebugInfo(AEntity a_e) throws SdaiException
	{
		Throwable t = new Throwable(); 
		  StackTraceElement[] elements = t.getStackTrace(); 
		  if (elements.length >= 2) 
		  {
			  String callingMethodName = elements[1].getMethodName();
			  System.out.println("----------------------");
			  System.out.println(callingMethodName);
			  if (a_e.getMemberCount() == 0)
					System.out.println("Given: empty aggregate");
				SdaiIterator it_entities = a_e.createIterator();
				while (it_entities.next()) 
				{
					EEntity e = (EEntity) a_e.getCurrentMemberEntity(it_entities);
					System.out.println("Given: "+e.toString());
				}
		  }
		System.out.println("");
	}
	
	protected void outputReturnDebugInfo(boolean b)
	{
		System.out.println("");
		if (b)
			System.out.println("Return: True");
		else
			System.out.println("Return: False");
	}
	
	protected void outputReturnDebugInfo(EEntity e) 
	{
		System.out.println("");
		if (e != null)
			System.out.println("Return: "+e.toString());
		else
			System.out.println("Return: null (none found)");
	}
	
	protected void outputReturnDebugInfo(AEntity a_e) throws SdaiException
	{
		System.out.println("");
		if (a_e.getMemberCount() == 0)
			System.out.println("Return: empty aggregate (none found)");
		SdaiIterator it_entities = a_e.createIterator();
		while (it_entities.next()) 
		{
			EEntity e = (EEntity) a_e.getCurrentMemberEntity(it_entities);
			System.out.println("Return: "+e.toString());
		}
	}
	
	protected void outputReturnPairDebugInfo(EEntity e1, EEntity e2) 
	{
		System.out.println("");
		if (e1 != null)
			System.out.print("Return e1: "+e1.toString());
		else
			System.out.print("Return e1: null (none found)");
		
		if (e2 != null)
			System.out.print("\te2: "+e2.toString());
		else
			System.out.print("\te2: null");
		
		System.out.println("");
		
	}
	
	protected void outputReturnTripleDebugInfo(EEntity e1, EEntity e2, EEntity e3) 
	{
		System.out.println("");
		if (e1 != null)
			System.out.print("Return e1: "+e1.toString());
		else
			System.out.print("Return e1: null (none found)");
		
		if (e2 != null)
			System.out.print("\te2: "+e2.toString());
		else
			System.out.print("\te2: null");
		
		if (e3 != null)
			System.out.print("\te3: "+e3.toString());
		else
			System.out.print("\te3: null");
		
		System.out.println("");
		
	}
	
	public AProduct getAllParts() throws SdaiException
	{
		AProduct a_p = q.getAllParts();
		outputReturnDebugInfo(a_p);
		return a_p;
	}
	
	public AProduct getAllTemplates() throws SdaiException
	{
		AProduct a_p = q.getAllTemplates();
		outputReturnDebugInfo(a_p);
		return a_p;
	}
	
	public AProduct getAllDocuments() throws SdaiException
	{
		AProduct a_p = q.getAllDocuments();
		outputReturnDebugInfo(a_p);
		return a_p;
	}
	
	public boolean isProductInASpecificPRPC(EProduct e_p, String categoryName) throws SdaiException
	{
		outputGivenDebugInfo(e_p, categoryName);
		boolean productInPRPC = q.isProductInASpecificPRPC(e_p, categoryName);
		outputReturnDebugInfo(productInPRPC);
		return productInPRPC;
	}
	
	public AProduct getAllProductsWithGivenAssignedClass(String className) throws SdaiException
	{
		outputGivenDebugInfo(className);
		AProduct a_p = q.getAllProductsWithGivenAssignedClass(className);
		outputReturnDebugInfo(a_p);
		return a_p;
	}
	
	public String getLayerPurposeOfStratum(EStratum e_s) throws SdaiException
	{
		outputGivenDebugInfo(e_s);
		String s = q.getLayerPurposeOfStratum(e_s);
		return s;
	}
	
	//	 Returns a shape_representation for an assembly_component if applicable. 
	//	 It is assumed that the shape_representation of the assembly component will be either directly related to the assembly_component
	//	 or related to a geometric_template in the case of a laminate component with a geometric_template.
	//	 If neither case is true, the query returns null.
	//	 This query is not applicable to a generic_laminate_text_component.

	public EShape_representation getShapeRepresentationOfAssemblyComponent(EAssembly_component ac) throws SdaiException
	{
		outputGivenDebugInfo(ac);
		EShape_representation sr = q.getShapeRepresentationOfAssemblyComponent(ac);
		outputReturnDebugInfo(sr);
		return sr;
	}
	
	//	 Returns a shape_representation for a generic_laminate_text_component (an individual character)
	//	 If the geometry is not explicity represented in a solid_character_glyph_2d_symbol, returns null.
	
	public EShape_representation getShapeRepresentationOfGenericLaminateTextComponent(EGeneric_laminate_text_component gltc) throws SdaiException
	{
		outputGivenDebugInfo(gltc);
		EShape_representation sr = q.getShapeRepresentationOfGenericLaminateTextComponent(gltc);
		outputReturnDebugInfo(sr);
		return sr;
	}
	
	
	public ECartesian_transformation_operator_2d getCartesianTransformationOfNAUOR(
			ENext_assembly_usage_occurrence_relationship nauor,
			EShape_representation sr1,
			EShape_representation sr2) throws SdaiException
	{
		outputGivenDebugInfo(nauor);
		outputAdditionalGivenDebugInfo(sr1);
		outputAdditionalGivenDebugInfo(sr2);
		ECartesian_transformation_operator_2d e_cto2d = q.getCartesianTransformationOfNAUOR(nauor, sr1, sr2);
		outputReturnDebugInfo(e_cto2d);
		return e_cto2d;
	}
	
	
	public MIMlocation getAxisPlacementOfSLCSAR(
		EStructured_layout_component_sub_assembly_relationship slcsar,
		EShape_representation sr1,
		EShape_representation sr2) throws SdaiException
	{
		outputGivenDebugInfo(slcsar);
		outputAdditionalGivenDebugInfo(sr1);
		outputAdditionalGivenDebugInfo(sr2);
		MIMlocation mimLoc = q.getAxisPlacementOfSLCSAR(slcsar, sr1, sr2);
		outputReturnPairDebugInfo(mimLoc.getAp2d1(), mimLoc.getAp2d2());
		return mimLoc;
	}
	

	public MIMlocation getLocationOfLaminateComponent(ELaminate_component lc,
			EShape_representation sr1,
			EShape_representation sr2) throws SdaiException
	{
		outputGivenDebugInfo(lc);
		outputAdditionalGivenDebugInfo(sr1);
		//outputAdditionalGivenDebugInfo(sr2);
		MIMlocation mimLoc = q.getLocationOfLaminateComponent(lc, sr1, sr2);
		outputReturnTripleDebugInfo(mimLoc.getCto2d(), mimLoc.getAp2d1(), mimLoc.getAp2d2());
		return mimLoc;
	}

	public EStratum getStratumOfStratumFeature(EStratum_feature sf) throws SdaiException 
	{
		outputGivenDebugInfo(sf);
		EStratum s = q.getStratumOfStratumFeature(sf);
		outputReturnDebugInfo(s);
		return s;
	}
	
	public EStratum getStratumOfLC(ELaminate_component lc) throws SdaiException
	{
		outputGivenDebugInfo(lc);
		EStratum s = q.getStratumOfLC(lc);
		outputReturnDebugInfo(s);
		return s;
	}
	
	public EStratum_feature getStratumFeatureOfSFTC(EStratum_feature_template_component sftc) throws SdaiException
	{
		outputGivenDebugInfo(sftc);
		EStratum_feature sf = q.getStratumFeatureOfSFTC(sftc);
		outputReturnDebugInfo(sf);
		return sf;
	}
	
	public EStratum_feature getStratumFeatureOfALTC(EAdditive_laminate_text_component altc) throws SdaiException
	{
		outputGivenDebugInfo(altc);
		EStratum_feature sf = q.getStratumFeatureOfALTC(altc);
		outputReturnDebugInfo(sf);
		return sf;
	}
	
	public EStratum_feature_template_component getSFTCofMRLC(ELaminate_component lc) throws SdaiException
	{
		outputGivenDebugInfo(lc);
		EStratum_feature_template_component sftc = q.getSFTCofMRLC(lc);
		outputReturnDebugInfo(sftc);
		return sftc;
	}
	
	public EStratum getPrecedentStratum(EStratum currentStratum, EStratum_stack_model stackModel) throws SdaiException
	{
		outputGivenDebugInfo(currentStratum);
		EStratum s = q.getPrecedentStratum(currentStratum, stackModel);
		outputReturnDebugInfo(s);
		return s;
	}
	
	public EStratum getSubsequentStratum(EStratum currentStratum, EStratum_stack_model stackModel) throws SdaiException
	{
		outputGivenDebugInfo(currentStratum);
		EStratum s = q.getSubsequentStratum(currentStratum, stackModel);
		outputReturnDebugInfo(s);
		return s;
	}
	
	public AStratum_technology_occurrence_link getAllSTOLinVerticalExtentOfInterStratumFeature(EInter_stratum_feature isf) throws SdaiException
	{
		outputGivenDebugInfo(isf);
		AStratum_technology_occurrence_link a_stol = q.getAllSTOLinVerticalExtentOfInterStratumFeature(isf);
		outputReturnDebugInfo(a_stol);
		return a_stol;
	}
	
	public EStratum_technology_occurrence_link getMostPrecedentSTOLinContiguousSetOfSTOL(AStratum_technology_occurrence_link a_stol) throws SdaiException
	{
		outputGivenDebugInfo(a_stol);
		EStratum_technology_occurrence_link e_stol = q.getMostPrecedentSTOLinContiguousSetOfSTOL(a_stol);
		outputReturnDebugInfo(e_stol);
		return e_stol;
	}
	
	public EStratum_technology_occurrence_link getMostSubsequentSTOLinContiguousSetOfSTOL(AStratum_technology_occurrence_link a_stol) throws SdaiException
	{
		outputGivenDebugInfo(a_stol);
		EStratum_technology_occurrence_link e_stol = q.getMostSubsequentSTOLinContiguousSetOfSTOL(a_stol);
		outputReturnDebugInfo(e_stol);
		return e_stol;
	}
	
	/*
	public EShape_aspect_relationship getASSDofSTOL(EStratum_technology_occurrence_link stol) throws SdaiException
	{
		outputGivenDebugInfo(stol);
		EShape_aspect_relationship sar = q.getASSDofSTOL(stol);
		outputReturnDebugInfo(sar);
		return sar;
	}
	*/
	
	/*
	public EStratum getSubsequentStratumOfASSD(EShape_aspect_relationship sar) throws SdaiException
	{
		outputGivenDebugInfo(sar);
		EStratum s = q.getSubsequentStratumOfASSD(sar);
		outputReturnDebugInfo(s);
		return s;
	}
	
	public EStratum getPrecedentStratumOfASSD(EShape_aspect_relationship sar) throws SdaiException
	{
		outputGivenDebugInfo(sar);
		EStratum s = q.getPrecedentStratumOfASSD(sar);
		outputReturnDebugInfo(s);
		return s;
	}
	*/
	
	public EStratum getMostPrecedentStratumInContiguousSetOfSTOL(AStratum_technology_occurrence_link a_stol) throws SdaiException
	{
		outputGivenDebugInfo(a_stol);
		EStratum mp_stratum = q.getMostPrecedentStratumInContiguousSetOfSTOL(a_stol);	
		outputReturnDebugInfo(mp_stratum);
		return mp_stratum;
	}
	
	public EStratum getMostSubsequentStratumInContiguousSetOfSTOL(AStratum_technology_occurrence_link a_stol) throws SdaiException
	{
		outputGivenDebugInfo(a_stol);
		EStratum ms_stratum = q.getMostSubsequentStratumInContiguousSetOfSTOL(a_stol);	
		outputReturnDebugInfo(ms_stratum);
		return ms_stratum;
	}
	
	public AProduct getAllProductsOfASpecificPRPC(String categoryName) throws SdaiException
	{
		outputGivenDebugInfo(categoryName);
		AProduct a_p = q.getAllProductsOfASpecificPRPC(categoryName);	
		outputReturnDebugInfo(a_p);
		return a_p;
	}
	
	public AParameter_assignment getAllParameterAssignmentsForProduct(EProduct p) throws SdaiException
	{
		outputGivenDebugInfo(p);
		AParameter_assignment a_pa = q.getAllParameterAssignmentsForProduct(p);	
		outputReturnDebugInfo(a_pa);
		return a_pa;
	}
	
	public ALayered_assembly_module_design_view getPcas() throws SdaiException
	{
		outputGivenDebugInfo();
		ALayered_assembly_module_design_view a_ad = q.getPcas();	
		outputReturnDebugInfo(a_ad);
		return a_ad;
	}
	
	public EProduct_definition getUsageViewOfProductDefinition(EProduct_definition e_pd) throws SdaiException
	{
		outputGivenDebugInfo(e_pd);
		EProduct_definition e_usageView = q.getUsageViewOfProductDefinition(e_pd);	
		outputReturnDebugInfo(e_usageView);
		return e_usageView;
	}
	
	public EShape_representation getShapeRepresentationOfProductDefinitionShape(EProduct_definition_shape pds) throws SdaiException
	{
		outputGivenDebugInfo(pds);
		EShape_representation e_sr = q.getShapeRepresentationOfProductDefinitionShape(pds);	
		outputReturnDebugInfo(e_sr);
		return e_sr;
	}
	
	public ALayered_interconnect_module_design_view getPcbs() throws SdaiException
	{
		outputGivenDebugInfo();
		ALayered_interconnect_module_design_view a_id = q.getPcbs();	
		outputReturnDebugInfo(a_id);
		return a_id;
	}
	
	public ALayered_interconnect_panel_design_view getPanels() throws SdaiException
	{
		outputGivenDebugInfo();
		ALayered_interconnect_panel_design_view a_panel = q.getPanels();	
		outputReturnDebugInfo(a_panel);
		return a_panel;
	}
	
	public AInterconnect_module_component getInterconnectModuleComponents() throws SdaiException
	{
		outputGivenDebugInfo();
		AInterconnect_module_component a_imc = q.getInterconnectModuleComponents();	
		outputReturnDebugInfo(a_imc);
		return a_imc;
	}
	
	public AInterconnect_module_component getAllInterconnectModuleComponentsInPanel(ELayered_interconnect_panel_design_view panel) throws SdaiException
	{
		outputGivenDebugInfo();
		AInterconnect_module_component a_imc = q.getAllInterconnectModuleComponentsInPanel(panel);	
		outputReturnDebugInfo(a_imc);
		return a_imc;
	}
	
	public AInterconnect_module_component getAllInterconnectModuleComponentsInPCA(ELayered_assembly_module_design_view pca) throws SdaiException
	{
		outputGivenDebugInfo();
		AInterconnect_module_component a_imc = q.getAllInterconnectModuleComponentsInPCA(pca);	
		outputReturnDebugInfo(a_imc);
		return a_imc;
	}
	
	public APackaged_component getAllPackagedComponentsInAssembly(ELayered_assembly_module_design_view e_ad) throws SdaiException
	{
		outputGivenDebugInfo(e_ad);
		APackaged_component a_pc = q.getAllPackagedComponentsInAssembly(e_ad);	
		outputReturnDebugInfo(a_pc);
		return a_pc;
	}
	
	public EPackage getPackageOfPackagedComponent(EPackaged_component pc, EShape_representation sr1) throws SdaiException
	{
		outputGivenDebugInfo(pc);
		EPackage e_p = q.getPackageOfPackagedComponent(pc, sr1);	
		outputReturnDebugInfo(e_p);
		return e_p;
	}
	
	public EShape_representation getShapeRepresentationOfPackageWithSpecifiedPurpose(EPackage p, String purpose) throws SdaiException
	{
		outputGivenDebugInfo(p, purpose);
		EShape_representation e_sr = q.getShapeRepresentationOfPackageWithSpecifiedPurpose(p, purpose);	
		outputReturnDebugInfo(e_sr);
		return e_sr;
	}
		
	public AShape_representation getAllKeepoutsForPhysicalUnitShapeModel(EShape_representation e_pupsm) throws SdaiException
	{
		outputGivenDebugInfo(e_pupsm);
		AShape_representation a_sr = q.getAllKeepoutsForPhysicalUnitShapeModel(e_pupsm);	
		outputReturnDebugInfo(a_sr);
		return a_sr;
	}
	
	public EShape_representation getKeepoutShapeRepresentationWithSpecifiedKeepoutCategory(AShape_representation a_sr, String description) throws SdaiException
	{
		outputGivenDebugInfo(a_sr);
		outputGivenDebugInfo(description);
		EShape_representation e_sr = q.getKeepoutShapeRepresentationWithSpecifiedKeepoutCategory(a_sr, description);	
		outputReturnDebugInfo(e_sr);
		return e_sr;
	}
	
	
	public AAssembly_joint getAllAssemblyJointsInPca(ELayered_assembly_module_design_view e_ad) throws SdaiException
	{
		outputGivenDebugInfo(e_ad);
		AAssembly_joint a_aj = q.getAllAssemblyJointsInPca(e_ad);	
		outputReturnDebugInfo(a_aj);
		return a_aj;
	}
	
	public EPackaged_part_terminal getJoinTerminalForAssemblyJoint(EAssembly_joint e_aj) throws SdaiException
	{
		outputGivenDebugInfo(e_aj);
		EPackaged_part_terminal e_ppt = q.getJoinTerminalForAssemblyJoint(e_aj);	
		outputReturnDebugInfo(e_ppt);
		return e_ppt;
	}
	
	public ELaminate_component getLaminateComponentForAssemblyJoint(EAssembly_joint e_aj) throws SdaiException
	{
		outputGivenDebugInfo(e_aj);
		ELaminate_component e_lc = q.getLaminateComponentForAssemblyJoint(e_aj);	
		outputReturnDebugInfo(e_lc);
		return e_lc;
	}
	
	public ELaminate_component getLaminateComponentForIMCT(EPhysical_component_terminal e_imct) throws SdaiException
	{
		outputGivenDebugInfo(e_imct);
		ELaminate_component e_lc = q.getLaminateComponentForIMCT(e_imct);	
		outputReturnDebugInfo(e_lc);
		return e_lc;
	}
	
	public EInterconnect_module_interface_terminal getIMITforAC(EAssembly_component e_ac) throws SdaiException
	{
		outputGivenDebugInfo(e_ac);
		EInterconnect_module_interface_terminal e_imit = q.getIMITforAC(e_ac);	
		outputReturnDebugInfo(e_imit);
		return e_imit;
	}
	
	public APart_tooling_feature getPartToolingFeaturesInPcb(ELayered_interconnect_module_usage_view e_pcb_usage_view, EShape_representation e_sr) throws SdaiException
	{
		outputGivenDebugInfo(e_sr);
		APart_tooling_feature a_ptf = q.getPartToolingFeaturesInPcb(e_pcb_usage_view, e_sr);	
		outputReturnDebugInfo(a_ptf);
		return a_ptf;
	}
	
	public ELaminate_component getLaminateComponentForPartToolingFeature(EPart_tooling_feature e_ptf) throws SdaiException
	{
		outputGivenDebugInfo(e_ptf);
		ELaminate_component e_lc = q.getLaminateComponentForPartToolingFeature(e_ptf);	
		outputReturnDebugInfo(e_lc);
		return e_lc;
	}
	
	public EInterconnect_module_edge getEdgeOfPcb(ELayered_interconnect_module_design_view id) throws SdaiException
	{
		outputGivenDebugInfo(id);
		EInterconnect_module_edge e_ime = q.getEdgeOfPcb(id);	
		outputReturnDebugInfo(e_ime);
		return e_ime;
	}
	
	public AStratum getAllStrataInPcb(ELayered_interconnect_module_design_view e_id) throws SdaiException
	{
		outputGivenDebugInfo(e_id);
		AStratum a_s = q.getAllStrataInPcb(e_id);	
		outputReturnDebugInfo(a_s);
		return a_s;
	}
	
	public AStratum_feature_template_component getAllSftcDirectlyRelatedToPcb(ELayered_interconnect_module_design_view e_id) throws SdaiException
	{
		outputGivenDebugInfo(e_id);
		AStratum_feature_template_component a_sftc = q.getAllSftcDirectlyRelatedToPcb(e_id);	
		outputReturnDebugInfo(a_sftc);
		return a_sftc;
	}
	
	public AMaterial_removal_laminate_component getAllMrlcDirectlyRelatedToPcb(ELayered_interconnect_module_design_view e_id) throws SdaiException
	{
		outputGivenDebugInfo(e_id);
		AMaterial_removal_laminate_component a_mrlc = q.getAllMrlcDirectlyRelatedToPcb(e_id);	
		outputReturnDebugInfo(a_mrlc);
		return a_mrlc;
	}
	
	public APadstack_occurrence getAllPadstackOccurrenceInPcb(ELayered_interconnect_module_design_view e_id) throws SdaiException
	{
		outputGivenDebugInfo(e_id);
		APadstack_occurrence a_po = q.getAllPadstackOccurrenceInPcb(e_id);	
		outputReturnDebugInfo(a_po);
		return a_po;
	}
	
	public AFootprint_occurrence getAllFootprintOccurrenceInPcb(ELayered_interconnect_module_design_view e_id) throws SdaiException
	{
		outputGivenDebugInfo(e_id);
		AFootprint_occurrence a_fo = q.getAllFootprintOccurrenceInPcb(e_id);	
		outputReturnDebugInfo(a_fo);
		return a_fo;
	}
	
	public ALaminate_component getAllLaminateComponentsInStructuredLayoutComponent(EStructured_layout_component e_slc) throws SdaiException
	{
		outputGivenDebugInfo(e_slc);
		ALaminate_component a_lc = q.getAllLaminateComponentsInStructuredLayoutComponent(e_slc);	
		outputReturnDebugInfo(a_lc);
		return a_lc;
	}
	
	public ALaminate_text_string_component getAllLaminateTextStringComponents(ELayered_interconnect_module_design_view e_id) throws SdaiException
	{
		outputGivenDebugInfo(e_id);
		ALaminate_text_string_component a_ltsc = q.getAllLaminateTextStringComponents(e_id);	
		outputReturnDebugInfo(a_ltsc);
		return a_ltsc;
	}
	
	public ANext_assembly_usage_occurrence_relationship getAllLaminateTextStringNAUOR(ELayered_interconnect_module_design_view e_id) throws SdaiException
	{
		outputGivenDebugInfo(e_id);
		ANext_assembly_usage_occurrence_relationship a_nauor = q.getAllLaminateTextStringNAUOR(e_id);	
		outputReturnDebugInfo(a_nauor);
		return a_nauor;
	}
	
	public AGeneric_laminate_text_component getGenericLaminateTextComponentsForLTSC(ELaminate_text_string_component e_ltsc) throws SdaiException
	{
		outputGivenDebugInfo(e_ltsc);
		AGeneric_laminate_text_component a_glts = q.getGenericLaminateTextComponentsForLTSC(e_ltsc);	
		outputReturnDebugInfo(a_glts);
		return a_glts;
	}
	
	public APhysical_network getAllPhysicalNetworksInPcb(ELayered_interconnect_module_design_view e_id) throws SdaiException
	{
		outputGivenDebugInfo(e_id);
		APhysical_network a_pn = q.getAllPhysicalNetworksInPcb(e_id);	
		outputReturnDebugInfo(a_pn);
		return a_pn;
	}
	
	public ALayer_connection_point getLCPsForPhysicalNetwork(EPhysical_network e_pn) throws SdaiException
	{
		outputGivenDebugInfo(e_pn);
		ALayer_connection_point a_lcp = q.getLCPsForPhysicalNetwork(e_pn);	
		outputReturnDebugInfo(a_lcp);
		return a_lcp;
	}
	
	public ALaminate_component_feature getLCFsForPhysicalNetwork(EPhysical_network e_pn) throws SdaiException
	{
		outputGivenDebugInfo(e_pn);
		ALaminate_component_feature a_lcf = q.getLCFsForPhysicalNetwork(e_pn);	
		outputReturnDebugInfo(a_lcf);
		return a_lcf;
	}
	
	public EInter_stratum_feature getInterStratumFeatureForDLLCP(ELayer_connection_point e_lcp) throws SdaiException
	{
		outputGivenDebugInfo(e_lcp);
		EInter_stratum_feature e_isf = q.getInterStratumFeatureForDLLCP(e_lcp);	
		outputReturnDebugInfo(e_isf);
		return e_isf;
	}
	
	public APlated_passage_dependent_land getPPDLandsforDLLCP(ELayer_connection_point e_lcp) throws SdaiException
	{
		outputGivenDebugInfo(e_lcp);
		APlated_passage_dependent_land a_ppdl = q.getPPDLandsforDLLCP(e_lcp);	
		outputReturnDebugInfo(a_ppdl);
		return a_ppdl;
	}
	
	public EContact_size_dependent_land getCSDLandforDLLCP(ELayer_connection_point e_lcp) throws SdaiException
	{
		outputGivenDebugInfo(e_lcp);
		EContact_size_dependent_land e_csdl = q.getCSDLandforDLLCP(e_lcp);	
		outputReturnDebugInfo(e_csdl);
		return e_csdl;
	}
	
	public AConductive_interconnect_element getCIEforLCP(ELayer_connection_point e_lcp) throws SdaiException
	{
		outputGivenDebugInfo(e_lcp);
		AConductive_interconnect_element a_cie = q.getCIEforLCP(e_lcp);	
		outputReturnDebugInfo(a_cie);
		return a_cie;
	}
	
	public EConnected_area_component getConnectedAreaComponentforLCP(ELayer_connection_point e_lcp) throws SdaiException
	{
		outputGivenDebugInfo(e_lcp);
		EConnected_area_component e_cac = q.getConnectedAreaComponentforLCP(e_lcp);	
		outputReturnDebugInfo(e_cac);
		return e_cac;
	}

	public EMapped_item getTLISTTforTLIST(EAssembly_component_usage e_acu, EShape_representation e_assemblyShape) throws SdaiException {
		outputGivenDebugInfo(e_acu);
		outputAdditionalGivenDebugInfo(e_assemblyShape);
		EMapped_item e_mi = q.getTLISTTforTLIST(e_acu, e_assemblyShape);
		outputReturnDebugInfo(e_mi);
		return e_mi;
	}

	public AProduct_definition getAllProductDefinitionsOfSpecifiedProductDefinitionContextRole(AProduct_definition a_pd, String contextRole, String contextRoleFrameOfReference) throws SdaiException {
		outputGivenDebugInfo(a_pd);
		outputAdditionalGivenDebugInfo(contextRole);
		outputAdditionalGivenDebugInfo(contextRoleFrameOfReference);
		AProduct_definition a_return = q.getAllProductDefinitionsOfSpecifiedProductDefinitionContextRole(a_pd, contextRole, contextRoleFrameOfReference);
		outputReturnDebugInfo(a_return);
		return a_return;
	}

	public AProduct_definition getQualifiedProductDefinitions(String contextRole, String contextRoleFrameOfReference) throws SdaiException {
		outputAdditionalGivenDebugInfo(contextRole);
		outputAdditionalGivenDebugInfo(contextRoleFrameOfReference);
		AProduct_definition a_pd = q.getQualifiedProductDefinitions(contextRole, contextRoleFrameOfReference);
		return a_pd;
	}

	public StratumSpan getSpanOfInterStratumFeature(EInter_stratum_feature e_isf) throws SdaiException {
		outputGivenDebugInfo(e_isf);
		StratumSpan span = q.getSpanOfInterStratumFeature(e_isf);
		outputReturnPairDebugInfo(span.getFromStratum(), span.getToStratum());
		return span;
	}

	/*
	public MIMlocation getLocationOfLaminateComponentInSLC(ELaminate_component lc, EStructured_layout_component_sub_assembly_relationship slcsar, EStructured_layout_component slc, EShape_representation sr1, EShape_representation sr2) throws SdaiException {
		outputGivenDebugInfo(lc);
		outputAdditionalGivenDebugInfo(slcsar);
		outputAdditionalGivenDebugInfo(slc);
		outputAdditionalGivenDebugInfo(sr1);
		outputAdditionalGivenDebugInfo(sr2);
		MIMlocation mimLoc = q.getLocationOfLaminateComponentInSLC(lc, slcsar, slc, sr1, sr2);
		outputReturnTripleDebugInfo(mimLoc.getCto2d(), mimLoc.getAp2d1(), mimLoc.getAp2d2());
		return mimLoc;
	}
	*/
	
	public MIMlocation getLocationOfAssemblyComponentInSLC(EAssembly_component ac,
			EStructured_layout_component_sub_assembly_relationship slcsar,
			EStructured_layout_component slc,
			EShape_representation srOfac) throws SdaiException {
		outputGivenDebugInfo(ac);
		outputAdditionalGivenDebugInfo(slcsar);
		outputAdditionalGivenDebugInfo(slc);
		outputAdditionalGivenDebugInfo(srOfac);
		MIMlocation mimLoc = q.getLocationOfAssemblyComponentInSLC(ac, slcsar, slc, srOfac);
		outputReturnTripleDebugInfo(mimLoc.getCto2d(), mimLoc.getAp2d1(), mimLoc.getAp2d2());
		return mimLoc;
	}
	
	public EShape_representation getShapeRepresentationOfSLC(EStructured_layout_component slc) throws SdaiException {
		outputGivenDebugInfo(slc);
		EShape_representation e_sr = q.getShapeRepresentationOfSLC(slc);
		outputReturnDebugInfo(e_sr);
		return e_sr;
	}
	
	public EText_literal getMessageOfLaminateTextStringComponent(ELaminate_text_string_component e_ltsc) throws SdaiException
	{
		outputGivenDebugInfo(e_ltsc);
		EText_literal e_tl = q.getMessageOfLaminateTextStringComponent(e_ltsc);
		outputReturnDebugInfo(e_tl);
		return e_tl;
	}
	
	public AMulti_stratum_special_symbol_component getAllMultiStratumSpecialSymbolComponentsInPcb(ELayered_interconnect_module_design_view e_id) throws SdaiException 
	{
		outputGivenDebugInfo(e_id);
		AMulti_stratum_special_symbol_component a_msssc = q.getAllMultiStratumSpecialSymbolComponentsInPcb(e_id);
		outputReturnDebugInfo(a_msssc);
		return 	a_msssc;
	}
	
	public ASingle_stratum_special_symbol_component getAllSingleStratumSpecialSymbolComponentsInPcb(ELayered_interconnect_module_design_view e_id) throws SdaiException {
		outputGivenDebugInfo(e_id);
		ASingle_stratum_special_symbol_component a_ssssc = q.getAllSingleStratumSpecialSymbolComponentsInPcb(e_id);
		outputReturnDebugInfo(a_ssssc);
		return 	a_ssssc;
	}
	
	public AStructured_layout_component getAllStructuredLayoutComponentsInStructuredLayoutComponent(EStructured_layout_component e_slc) throws SdaiException {
		outputGivenDebugInfo(e_slc);
		AStructured_layout_component a_slc = q.getAllStructuredLayoutComponentsInStructuredLayoutComponent(e_slc);	
		outputReturnDebugInfo(a_slc);
		return a_slc;
	}
	
	public EMaterial_designation getMaterialDesignationOfStratum(EStratum e_s) throws SdaiException
	{
		outputGivenDebugInfo(e_s);
		EMaterial_designation e_md = q.getMaterialDesignationOfStratum(e_s);	
		outputReturnDebugInfo(e_md);
		return e_md;
	}
	
	public AStratum getAllAdjacentPrecedentStratum(EStratum e_s, EStratum_stack_model e_stack_model) throws SdaiException
	{
		outputGivenDebugInfo(e_s);
		AStratum a_s = q.getAllAdjacentPrecedentStratum(e_s, e_stack_model);
		outputReturnDebugInfo(a_s);
		return a_s;
	}
	
	public AStratum getAllAdjacentSubsequentStratum(EStratum e_s, EStratum_stack_model e_sm) throws SdaiException
	{
		outputGivenDebugInfo(e_s);
		AStratum a_s = q.getAllAdjacentSubsequentStratum(e_s, e_sm);
		outputReturnDebugInfo(a_s);
		return a_s;
	}
	
	public EProduct getProductOfPackagedComponent(EPackaged_component e_pc) throws SdaiException
	{
		outputGivenDebugInfo(e_pc);
		EProduct e_p = q.getProductOfPackagedComponent(e_pc);
		outputReturnDebugInfo(e_p);
		return e_p;
	}
	
	public AClass getAllAssigningClassForProduct(EProduct e_p) throws SdaiException
	{
		outputGivenDebugInfo(e_p);
		AClass a_cc = q.getAllAssigningClassForProduct(e_p);
		outputReturnDebugInfo(a_cc);
		return a_cc;
	}
	
	public ERepresentation getThicknessOfPcb(ELayered_interconnect_module_usage_view e_pcb_usage_view, String propertyName) throws SdaiException
	{
		outputGivenDebugInfo(e_pcb_usage_view);
		outputGivenDebugInfo(propertyName);
		ERepresentation e_rep = q.getThicknessOfPcb(e_pcb_usage_view, propertyName);
		outputReturnDebugInfo(e_rep);
		return e_rep;
	}
	
	public EStratum_technology getStratumTechnologyOfStratum(EStratum s) throws SdaiException
	{
		outputGivenDebugInfo(s);
		EStratum_technology e_st = q.getStratumTechnologyOfStratum(s);
		outputReturnDebugInfo(e_st);
		return e_st;
	}
	
	public ERepresentation getThicknessOfStratum(EStratum e_s) throws SdaiException
	{
		outputGivenDebugInfo(e_s);
		ERepresentation e_rep = q.getThicknessOfStratum(e_s);
		outputReturnDebugInfo(e_rep);
		return e_rep;
	}

	public EShape_representation getShapeRepresentationWithSpecifiedPurpose(
			AShape_representation a_sr, String purpose) throws SdaiException {
		outputGivenDebugInfo(a_sr);
		EShape_representation e_sr = q.getShapeRepresentationWithSpecifiedPurpose(a_sr, purpose);
		outputReturnDebugInfo(e_sr);
		return e_sr;
	}
}
