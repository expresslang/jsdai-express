package com.sfm.ap210.jsdai;

import java.util.Set;

import com.sfm.ap210.jsdai.param.Param;

import jsdai.SAssembly_module_design_mim.ALayered_assembly_module_design_view;
import jsdai.SAssembly_module_design_mim.APackaged_component;
import jsdai.SAssembly_module_design_mim.ELayered_assembly_module_design_view;
import jsdai.SAssembly_module_design_mim.EPackaged_component;
import jsdai.SFabrication_technology_mim.EDesign_stack_model;
import jsdai.SFootprint_definition_mim.AFootprint_definition;
import jsdai.SFootprint_definition_mim.EFootprint_definition;
import jsdai.SGeometry_schema.EAxis2_placement_2d;
import jsdai.SGeometry_schema.EAxis2_placement_3d;
import jsdai.SGeometry_schema.ECartesian_transformation_operator_2d;
import jsdai.SGeometry_schema.EPlacement;
import jsdai.SInterconnect_module_usage_view_mim.ELayered_interconnect_module_usage_view;
import jsdai.SLand_mim.APlated_passage_dependent_land;
import jsdai.SLand_mim.EContact_size_dependent_land;
import jsdai.SLayered_interconnect_complex_template_mim.EStructured_template;
import jsdai.SLayered_interconnect_module_design_mim.AConductive_interconnect_element;
import jsdai.SLayered_interconnect_module_design_mim.AFootprint_occurrence;
import jsdai.SLayered_interconnect_module_design_mim.AGeneric_laminate_text_component;
import jsdai.SLayered_interconnect_module_design_mim.ALaminate_component;
import jsdai.SLayered_interconnect_module_design_mim.ALaminate_text_string_component;
import jsdai.SLayered_interconnect_module_design_mim.ALayer_connection_point;
import jsdai.SLayered_interconnect_module_design_mim.ALayered_interconnect_module_design_view;
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
import jsdai.SLayered_interconnect_module_design_mim.EFootprint_occurrence;
import jsdai.SLayered_interconnect_module_design_mim.EGeneric_laminate_text_component;
import jsdai.SLayered_interconnect_module_design_mim.EInter_stratum_feature;
import jsdai.SLayered_interconnect_module_design_mim.EInterconnect_module_edge;
import jsdai.SLayered_interconnect_module_design_mim.ELaminate_component;
import jsdai.SLayered_interconnect_module_design_mim.ELaminate_text_string_component;
import jsdai.SLayered_interconnect_module_design_mim.ELayer_connection_point;
import jsdai.SLayered_interconnect_module_design_mim.ELayered_interconnect_module_design_view;
import jsdai.SLayered_interconnect_module_design_mim.EMaterial_removal_laminate_component;
import jsdai.SLayered_interconnect_module_design_mim.EMulti_stratum_special_symbol_component;
import jsdai.SLayered_interconnect_module_design_mim.EPadstack_occurrence;
import jsdai.SLayered_interconnect_module_design_mim.EPhysical_network;
import jsdai.SLayered_interconnect_module_design_mim.ESingle_stratum_special_symbol_component;
import jsdai.SLayered_interconnect_module_design_mim.EStratum;
import jsdai.SLayered_interconnect_module_design_mim.EStratum_feature;
import jsdai.SLayered_interconnect_module_design_mim.EStratum_feature_template_component;
import jsdai.SLayered_interconnect_module_design_mim.EStructured_layout_component;
import jsdai.SLayered_interconnect_module_design_mim.EStructured_layout_component_sub_assembly_relationship;
import jsdai.SNon_feature_shape_element_mim.ESeating_plane;
import jsdai.SPackage_mim.APackage;
import jsdai.SPackage_mim.APackage_terminal;
import jsdai.SPackage_mim.EPackage;
import jsdai.SPackage_mim.EPackage_body;
import jsdai.SPackage_mim.EPackage_terminal;
import jsdai.SPackage_mim.EPackage_terminal_template_definition;
import jsdai.SPart_template_shape_with_parameters_mim.EGeometric_template;
import jsdai.SPhysical_unit_design_view_mim.ENext_assembly_usage_occurrence_relationship;
import jsdai.SPresentation_definition_schema.EText_literal;
import jsdai.SProduct_definition_schema.EProduct;
import jsdai.SProduct_definition_schema.EProduct_definition;
import jsdai.SProduct_property_definition_schema.ECharacterized_object;
import jsdai.SProduct_property_definition_schema.EShape_aspect;
import jsdai.SProduct_property_representation_schema.EShape_representation;
import jsdai.SProduct_structure_schema.AAssembly_component_usage;
import jsdai.SProduct_structure_schema.EAssembly_component_usage;
import jsdai.SRepresentation_schema.EMapped_item;
import jsdai.SRepresentation_schema.ERepresentation_map;
import jsdai.lang.AEntity;
import jsdai.lang.EEntity;
import jsdai.lang.SdaiException;
import jsdai.lang.SdaiIterator;
import jsdai.lang.SdaiModel;

/**
 * A sample implementation of utilizing the MIMquery methods to traverse either a PCA/PCB or package model.
 * @author James Stori, SFM Technology, Inc.
 * @version 1.3
 */
public class SampleModelTraversal {

	MIMqueries qImpl = null;
	MIMqueriesDebugImpl qDebugImpl = null;
	MIMqueries mimQ = null;
	MIMops ops = null;
	MIMops opsImpl = null;
	MIMops opsDebugImpl = null;
	MIMparamQueries paramQ = null;
	MIMpackageQueries packageQ = null;
	
	SdaiModel model = null;
	
	//Set<EEntity> traversedLaminateComponents = new HashSet<EEntity>();
	//Set<EEntity> traversedLCPs = new HashSet<EEntity>();
	
	int lc_count = 0;
	int interStratumFeature_count = 0;
	int pc_count = 0;
	int pn_count = 0;
	int lcp_count = 0;
	
	void MIMqueriesDebugOn()
	{
	//	mimQ = qDebugImpl;
	//	qDebugImpl.setMIMqueriesImpl(qImpl);
	}
	
	void MIMqueriesDebugOff()
	{
		mimQ = qImpl;
	}
	
	void MIMopsDebugOn()
	{
		ops = opsDebugImpl;
		qImpl.setOps(ops);
	}
	
	void MIMopsDebugOff()
	{
		ops = opsImpl;
		qImpl.setOps(ops);
	}
	
	/**
	 * Constructor for the sample model traversal class. Obtains the pca and pcb from the given model, and calls the top-level traversal
	 * methods: traverseAllPackagedComponents, traverseAllLaminateComponents, and traversePhysicalNets.
	 * 
	 * @param model 
	 * @throws SdaiException
	 * @see "MIMtraversal"
	 */
	public SampleModelTraversal(SdaiModel model) throws SdaiException {
		
		this.model = model;
		
		opsImpl = MIMopsFactory.getImpl(model);
		opsDebugImpl = MIMopsFactory.getDebugImpl(model);
		ops = opsImpl;
		
		//qImpl = MIMqueryFactory.getImpl(model, ops);
		qImpl = MIMqueryFactory.getMapImpl(model, ops);
		qDebugImpl = new MIMqueriesDebugImpl(model, ops);
		mimQ = qImpl;
		
		paramQ = new MIMparamQueriesImpl(mimQ, ops);
		
		packageQ = new MIMpackageQueriesImpl(mimQ, ops, paramQ);
		
		packageQ.addTerminalParam("predefined lead form");
		packageQ.addTerminalParam("seating plane intersection");
		packageQ.addTerminalParam("terminal diametrical extent");
		
		packageQ.addPackageParam("mounting technology");
		packageQ.addPackageParam("maximum body height above seating plane");
		packageQ.addPackageParam("nominal mounting lead pitch");
		packageQ.addPackageParam("nominal mounting lead span");
		packageQ.addPackageParam("body clearance above seating plane");
		packageQ.addPackageParam("body clearance below seating plane");
		
		MIMqueriesDebugOff();
		
		ALayered_assembly_module_design_view a_pca = mimQ.getPcas();
		ALayered_interconnect_module_design_view a_pcb = mimQ.getPcbs();
		
		if (((a_pca.getMemberCount()) > 0) && ((a_pcb.getMemberCount()) > 0))
		{
			TraversePCA_PCB((ELayered_assembly_module_design_view) a_pca.getByIndex(1), (ELayered_interconnect_module_design_view) a_pcb.getByIndex(1));
			return;
		}
		
		APackage a_package = ops.All_p_inModel_2();
		
		if (a_package.getMemberCount() > 0)
		{
			SdaiIterator it = a_package.createIterator();
			while (it.next()) {
				EPackage e_package = (EPackage) a_package.getCurrentMemberEntity(it);
				traversePackage(e_package);
			}
			return;
		}
		
		System.out.println("Warning: Unable to find PCA/PCB or package instances in file.");
		
		//ALaminate_component a_lc = (ALaminate_component) model.getInstances(ELaminate_component.class);
		//displayEntitiesMissingFromSet(a_lc, traversedLaminateComponents);
		
		//ALayer_connection_point a_lcp = (ALayer_connection_point) model.getInstances(ELayer_connection_point.class);
		//displayEntitiesMissingFromSet(a_lcp, traversedLCPs);
	}
	
	void TraversePCA_PCB(ELayered_assembly_module_design_view e_pca, ELayered_interconnect_module_design_view e_pcb) throws SdaiException
	{
		EShape_representation e_pcb_sr = mimQ.getShapeRepresentationOfProductDefinitionShape(e_pcb);
		ELayered_interconnect_module_usage_view e_pcb_usage_view = (ELayered_interconnect_module_usage_view) mimQ.getUsageViewOfProductDefinition(e_pcb);
		EShape_representation e_pcb_usage_view_sr = mimQ.getShapeRepresentationOfProductDefinitionShape(e_pcb_usage_view);
		EInterconnect_module_edge e_pcb_ime = mimQ.getEdgeOfPcb(e_pcb);
		
		traverseStratumStack(e_pcb);
		
		traverseAllPackagedComponents(e_pca);
		traverseAllLaminateComponentsInPcb(e_pcb);
		traversePhysicalNets(e_pcb);
		
		System.out.println("Packaged components: "+pc_count);
		System.out.println("Laminate components: "+lc_count);
		System.out.println("Inter-stratum features: "+interStratumFeature_count);
		System.out.println("Physical nets: "+pn_count);
		System.out.println("Layer connection points: "+lcp_count);
		
	}
	
	/**
	 * Utility function to report entities of a given aggregate that are not contained in the given set.
	 * May be used to validate intended coverage of model entities.
	 * 
	 * @param a_e
	 * @param traversedSet
	 * @throws SdaiException
	 * @see MIMtraversal
	 */
	void displayEntitiesMissingFromSet(AEntity a_e, Set<EEntity> traversedSet) throws SdaiException
	{
		System.out.println();
		SdaiIterator it = a_e.createIterator();
		while (it.next()) {
			EEntity e_e = a_e.getCurrentMemberEntity(it);
			if (!(traversedSet.contains(e_e)))
			{
				System.out.println(e_e);
			}
		}
	}
	
	void traverseStratumStack(ELayered_interconnect_module_design_view e_pcb) throws SdaiException
	{
		System.out.println();
		System.out.println("====================");
		System.out.println("traverseStratumStack");
		System.out.println();
		EDesign_stack_model e_dsm = ops.dsm_relatedTo_limdv_through_pdr(e_pcb, "used stack model");
		System.out.println("Design stack model: "+e_dsm.toString());
		
		AStratum a_stratum = mimQ.getAllStrataInPcb(e_pcb);
		SdaiIterator it_stratum = a_stratum.createIterator();
		while (it_stratum.next()) {
			EStratum e_s = (EStratum) a_stratum.getCurrentMemberEntity(it_stratum);
			System.out.println("Current stratum: "+e_s.toString());
			System.out.println("Precedent: "+mimQ.getPrecedentStratum(e_s, e_dsm));
			AStratum a_precedent = mimQ.getAllAdjacentPrecedentStratum(e_s, e_dsm);
			System.out.println("All precedent: "+a_precedent.toString());
			System.out.println("Subsequent: "+mimQ.getSubsequentStratum(e_s, e_dsm));
			AStratum a_subsequent = mimQ.getAllAdjacentSubsequentStratum(e_s, e_dsm);
			System.out.println("All subsequent: "+a_subsequent.toString());
		}
	}
	
	/**
	 * Top-level traversal of laminate components in the given e_pcb (interconnect_definition). 
	 * Obtains aggregates of stratum_feature_template_component, material_removal_laminate_component,
	 * padstack_occurrence, footprint_occurrence, multi_stratum_special_symbol_component,
	 * single_stratum_special_symbol_component, and laminate_text_string_component that have
	 * next_assembly_usage_occurrence_relationships with the given e_pcb.
	 * 
	 * @param e_pcb
	 * @throws SdaiException
	 * @see MIMtraversal
	 */
	void traverseAllLaminateComponentsInPcb(ELayered_interconnect_module_design_view e_pcb) throws SdaiException
	{
		System.out.println();
		System.out.println("====================");
		System.out.println("traverseAllLaminateComponents");
		System.out.println();
		
		EShape_representation e_pcb_sr = mimQ.getShapeRepresentationOfProductDefinitionShape(e_pcb);
		
		System.out.println("-- getAllSftcDirectlyRelatedToPcb");
		AStratum_feature_template_component a_sftc = mimQ.getAllSftcDirectlyRelatedToPcb(e_pcb);
		traverseAllLaminateComponentsInAggregate(a_sftc, e_pcb_sr);
		
		System.out.println("-- getAllMrlcDirectlyRelatedToPcb");
		AMaterial_removal_laminate_component a_mrlc = mimQ.getAllMrlcDirectlyRelatedToPcb(e_pcb);
		traverseAllLaminateComponentsInAggregate(a_mrlc, e_pcb_sr);
		
		System.out.println("-- getAllPadstackOccurrenceInPcb");
		APadstack_occurrence a_po = mimQ.getAllPadstackOccurrenceInPcb(e_pcb);
		SdaiIterator it_po = a_po.createIterator();
		while (it_po.next()) {
			EPadstack_occurrence e_po = (EPadstack_occurrence) a_po.getCurrentMemberEntity(it_po);
			traverseStructuredLayoutComponent(e_po, e_pcb_sr);
		}
		
		System.out.println("-- getAllFootprintOccurrenceInPcb");
		AFootprint_occurrence a_fo = mimQ.getAllFootprintOccurrenceInPcb(e_pcb);
		SdaiIterator it_fo = a_fo.createIterator();
		while (it_fo.next()) {
			EFootprint_occurrence e_fo = (EFootprint_occurrence) a_fo.getCurrentMemberEntity(it_fo);
			traverseStructuredLayoutComponent(e_fo, e_pcb_sr);
		}
		
		System.out.println("-- getAllMultiStratumSpecialSymbolComponentsInPcb");
		AMulti_stratum_special_symbol_component a_msssc = mimQ.getAllMultiStratumSpecialSymbolComponentsInPcb(e_pcb);
		SdaiIterator it_msssc = a_msssc.createIterator();
		while (it_msssc.next()) {
			EMulti_stratum_special_symbol_component e_msssc = (EMulti_stratum_special_symbol_component) a_msssc.getCurrentMemberEntity(it_msssc);
			traverseStructuredLayoutComponent(e_msssc, e_pcb_sr);
		}
		
		System.out.println("-- getAllSingleStratumSpecialSymbolComponentsInPcb");
		ASingle_stratum_special_symbol_component a_ssssc = mimQ.getAllSingleStratumSpecialSymbolComponentsInPcb(e_pcb);
		SdaiIterator it_ssssc = a_ssssc.createIterator();
		while (it_ssssc.next()) {
			ESingle_stratum_special_symbol_component e_ssssc = (ESingle_stratum_special_symbol_component) a_ssssc.getCurrentMemberEntity(it_ssssc);
			traverseStructuredLayoutComponent(e_ssssc, e_pcb_sr);
		}
		
		System.out.println("-- getAllLaminateTextStringComponents");
		ALaminate_text_string_component a_ltsc = mimQ.getAllLaminateTextStringComponents(e_pcb);
		
		SdaiIterator it_ltsc = a_ltsc.createIterator();
		while (it_ltsc.next()) {
			ELaminate_text_string_component e_ltsc = (ELaminate_text_string_component) a_ltsc.getCurrentMemberEntity(it_ltsc);
			lc_count++;
			//traversedLaminateComponents.add(e_ltsc);
			EText_literal e_tl = mimQ.getMessageOfLaminateTextStringComponent(e_ltsc);
			String message = e_tl.getLiteral(null);
			//System.out.println(e_ltsc);
			//System.out.println("Message: "+message);
			AGeneric_laminate_text_component a_gltc = mimQ.getGenericLaminateTextComponentsForLTSC(e_ltsc);
			traverseAllLaminateComponentsInAggregate(a_gltc, e_pcb_sr);
		}
	}

	/**
	 * Iterates over a given aggregate of laminate_components. Delegates to getDetailsOfLaminateComponent.
	 * Requires the shape_representation of the pcb for qualifying the transformations.
	 * 
	 * @param a_lc
	 * @throws SdaiException
	 * @see MIMtraversal
	 */
	void traverseAllLaminateComponentsInAggregate(AEntity a_lc, EShape_representation pcb_sr) throws SdaiException
	{
		SdaiIterator it_lc = a_lc.createIterator();
		while (it_lc.next()) {
			ELaminate_component e_lc = (ELaminate_component) a_lc.getCurrentMemberEntity(it_lc);
			getDetailsOfLaminateComponent(e_lc, pcb_sr);
		}
	}
	
	/**
	 * Iterates over a given structured_layout_component. Delegates to traverseAllLaminateComponentsInSLC.
	 * Requires the shape_representation of the pcb for qualifying the transformations.
	 * 
	 * @param slc
	 * @throws SdaiException
	 * @see MIMtraversal
	 */
	void traverseStructuredLayoutComponent(EStructured_layout_component slc, EShape_representation pcb_sr) throws SdaiException
	{
		ENext_assembly_usage_occurrence_relationship nauor = ops.nauor_referencing_slc(slc);
		EShape_representation slc_sr = mimQ.getShapeRepresentationOfSLC(slc);
		ECartesian_transformation_operator_2d cto2d = mimQ.getCartesianTransformationOfNAUOR(nauor, slc_sr, pcb_sr);
		traverseAllLaminateComponentsInSLC(slc, pcb_sr);
		AStructured_layout_component a_slc_slc = mimQ.getAllStructuredLayoutComponentsInStructuredLayoutComponent(slc);
		SdaiIterator it_slc = a_slc_slc.createIterator();
		while (it_slc.next()) {
			EStructured_layout_component e_slc_slc = (EStructured_layout_component) a_slc_slc.getCurrentMemberEntity(it_slc);
			traverseAllLaminateComponentsInSLC(e_slc_slc, slc_sr);
		}
	}
	
	/**
	 * Iterates over all laminate_components related to the given structured_layout_component
	 * through a structured_layout_component_sub_assembly_relationship. Delegates to getDetailsOfLaminateComponentInSLC.
	 * Requires the shape_representation of the pcb for qualifying the transformations.
	 * 
	 * @param slc
	 * @throws SdaiException
	 * @see MIMtraversal
	 */
	void traverseAllLaminateComponentsInSLC(EStructured_layout_component slc, EShape_representation pcb_sr) throws SdaiException
	{
		EShape_representation slc_sr = mimQ.getShapeRepresentationOfSLC(slc);
		ALaminate_component a_lc = mimQ.getAllLaminateComponentsInStructuredLayoutComponent(slc);
		SdaiIterator it_lc = a_lc.createIterator();
		while (it_lc.next()) {
			ELaminate_component e_lc = (ELaminate_component) a_lc.getCurrentMemberEntity(it_lc);
			EStructured_layout_component_sub_assembly_relationship slcsar = ops.slcsar_referencing_lc(e_lc);
			getDetailsOfLaminateComponentInSLC(e_lc, slcsar, slc, slc_sr, pcb_sr);
		}
	}
	
	/**
	 * Iterates over all packaged_component within the given e_pca assembly. 
	 * Delegates to getDetailsOfPackagedComponent.
	 * Obtains the shape_representation of the pca for qualifying the transformations.
	 * 
	 * @param e_pca
	 * @throws SdaiException
	 * @see MIMtraversal
	 */
	void traverseAllPackagedComponents(ELayered_assembly_module_design_view e_pca) throws SdaiException
	{	
		System.out.println();
		System.out.println("====================");
		System.out.println("traverseAllPackagedComponents");
		System.out.println();

		EShape_representation pca_sr = mimQ.getShapeRepresentationOfProductDefinitionShape(e_pca);
		APackaged_component a_pc = mimQ.getAllPackagedComponentsInAssembly(e_pca);
		
		SdaiIterator it_pc = a_pc.createIterator();
		
		while (it_pc.next()) {
			EPackaged_component e_pc = (EPackaged_component) a_pc.getCurrentMemberEntity(it_pc);
			getDetailsOfPackagedComponent(e_pc, pca_sr);
			pc_count++;
		}
	}
	
	/**
	 * Template method to obtain two shape_representations for a particular package.
	 * Obtains the locating transformation for each, if applicable. 
	 * Qualifies the transformations using the appropriate shape_representation.
	 * 
	 * @param e_pc
	 * @throws SdaiException
	 * @see MIMtraversal
	 */
	void getDetailsOfPackagedComponent(EPackaged_component e_pc, EShape_representation pca_sr) throws SdaiException
	{
		EProduct e_product = mimQ.getProductOfPackagedComponent(e_pc);
		getParamsOfPackagedComponentProduct(e_product);
		ENext_assembly_usage_occurrence_relationship e_nauor = ops.nauor_referencing_pd(e_pc);
		EPackage e_p = mimQ.getPackageOfPackagedComponent(e_pc, pca_sr);
		
		traversePackage(e_p);
		
		EShape_representation assembly_shape = mimQ.getShapeRepresentationOfPackageWithSpecifiedPurpose(e_p, "assembly symbol");
		if (assembly_shape != null)
		{
			ECartesian_transformation_operator_2d e_assemblyShapeCto2d = mimQ.getCartesianTransformationOfNAUOR(e_nauor, assembly_shape, pca_sr);
		}
		
		EShape_representation design_shape = mimQ.getShapeRepresentationOfPackageWithSpecifiedPurpose(e_p, "design");
		if (design_shape != null)
		{
			ECartesian_transformation_operator_2d e_designShapeCto2d = mimQ.getCartesianTransformationOfNAUOR(e_nauor, design_shape, pca_sr);
		}
	}
	
	void getParamsOfPackagedComponentProduct(EProduct e_p) throws SdaiException
	{
		System.out.println("product id: "+e_p.getId(null));
		System.out.println("product name: "+e_p.getName(null));
		Set<Param> productParams = paramQ.allParametersForProduct(e_p);
		for (Param p : productParams)
		{
			System.out.println(p);
		}
	}
	
	void traversePackage(EPackage e_p) throws SdaiException
	{
		getParamsOfPackage(e_p);
		
		APackage_terminal a_pt = packageQ.getTerminalsOfPackage(e_p);
		EShape_representation e_2d_package_sr = packageQ.get2dDesignShapeRepresentationOfPackage(e_p);
		EShape_representation e_3d_package_sr = packageQ.get3dDesignShapeRepresentationOfPackage(e_p);
		EPackage_body e_pb = packageQ.getBodyOfPackage(e_p);
		EShape_representation e_body_sr = null;
		if (e_pb != null)
			e_body_sr = packageQ.getShapeRepresentationOfShapeAspect(e_pb);
		ESeating_plane e_sp = packageQ.getSeatingPlaneOfPackage(e_p);
		EShape_representation e_seating_plane_sr = packageQ.getShapeRepresentationOfShapeAspect(e_sp);
		AFootprint_definition a_fd = packageQ.getFootprintsOfPackage(e_p);
		System.out.println("Found "+a_fd.getMemberCount()+" footprints for "+e_p);
		
		SdaiIterator it_fd = a_fd.createIterator();
		while (it_fd.next()) {
			EFootprint_definition e_fd = (EFootprint_definition) a_fd.getCurrentMemberEntity(it_fd);
			if (e_fd.getPersistentLabel().equals("#646"))
				System.out.println("Found #646");
			traverseStructuredTemplate(e_fd);
		}
		
		SdaiIterator it_pt = a_pt.createIterator();
		
		while (it_pt.next()) {
			EPackage_terminal e_pt = (EPackage_terminal) a_pt.getCurrentMemberEntity(it_pt);
			EShape_representation e_terminal_sr1 = packageQ.getShapeRepresentationOfPackageTerminal(e_pt);
			EPackage_terminal_template_definition e_pttd = packageQ.getTemplateOfPackageTerminal(e_pt);
			getParamsOfTerminalTemplate(e_pttd);
			EShape_representation e_terminal_sr2 = packageQ.getShapeRepresentationOfPackageTerminalTemplate(e_pttd);
			EPlacement e_terminal_placement = null;
			if (e_2d_package_sr != null)
			{
				e_terminal_placement = packageQ.getPlacementOfShapeAspect(e_pt, e_terminal_sr1, e_2d_package_sr);
				if (!(e_terminal_placement.isKindOf(EAxis2_placement_2d.class)))
					System.out.println("Unable to find 2d placement for terminal.");
			}
			if (e_3d_package_sr != null)
			{
				e_terminal_placement = packageQ.getPlacementOfShapeAspect(e_pt, e_terminal_sr1, e_3d_package_sr);
				if (!(e_terminal_placement.isKindOf(EAxis2_placement_3d.class)))
					System.out.println("Unable to find 3d placement for terminal.");
			}
		}
		
		if ((e_3d_package_sr != null) && (e_body_sr != null))
		{
			EPlacement e_body_placement = packageQ.getPlacementOfShapeAspect(e_pb, e_body_sr, e_3d_package_sr);
			if (!(e_body_placement.isKindOf(EAxis2_placement_3d.class)))
				System.out.println("Unable to find 3d placement for body.");
		}
		
		if ((e_3d_package_sr != null) && (e_seating_plane_sr != null))
		{
			EPlacement e_seating_plane_placement = packageQ.getPlacementOfShapeAspect(e_sp, e_seating_plane_sr, e_3d_package_sr);
			if (!(e_seating_plane_placement.isKindOf(EAxis2_placement_3d.class)))
				System.out.println("Unable to find 3d placement for body.");
		}
	}
	
	void traverseStructuredTemplate(EStructured_template e_st) throws SdaiException
	{
		System.out.println("Structured template: "+e_st);
		EShape_representation e_st_sr = mimQ.getShapeRepresentationOfProductDefinitionShape(e_st);
		AAssembly_component_usage a_acu = packageQ.getAllTTLISTforST(e_st);
		SdaiIterator it_acu = a_acu.createIterator();
		while (it_acu.next()) {
			EAssembly_component_usage e_acu = (EAssembly_component_usage) a_acu.getCurrentMemberEntity(it_acu);
			traverseTLIST(e_acu, e_st_sr);
		}
	}
	
	void traverseTLIST(EAssembly_component_usage e_acu, EShape_representation e_st_sr) throws SdaiException
	{
		System.out.println("TLIST: "+e_acu);
		EShape_aspect e_sa = packageQ.getPartFeatureForTLIST(e_acu);
		if (e_sa != null)
			System.out.println("Associated part feature: "+e_sa);
		EGeometric_template e_gt = (EGeometric_template) e_acu.getRelated_product_definition(null);
		EShape_representation e_gt_sr = mimQ.getShapeRepresentationOfProductDefinitionShape(e_gt);
		EMapped_item e_mi = mimQ.getTLISTTforTLIST(e_acu, e_st_sr);
		ERepresentation_map e_mapping_source = e_mi.getMapping_source(null);
		EShape_representation e_mapped_representation = (EShape_representation) e_mapping_source.getMapped_representation(null);
		EAxis2_placement_2d e_a2p2d = (EAxis2_placement_2d) e_mi.getMapping_target(null);
		if (e_mapped_representation != e_gt_sr)
			throw new IllegalStateException("Failed to find correct placement mapping for: "+e_acu);
		System.out.println("     Placement of "+e_gt+" at "+e_a2p2d);
		if (e_gt.isKindOf(EStructured_template.class))
		{
			traverseStructuredTemplate((EStructured_template) e_gt);
		}
	}
	
	void getParamsOfPackage(EPackage e_p) throws SdaiException
	{
		System.out.println("package id: "+e_p.getId((EProduct_definition) null));
		Set<Param> packageParams = packageQ.getParametricAttributesOfPackage(e_p);
		for (Param p : packageParams)
		{
			System.out.println(p);
		}
	}
	
	void getParamsOfTerminalTemplate(EPackage_terminal_template_definition e_pttd) throws SdaiException
	{
		System.out.println("package terminal template definition: "+e_pttd.getName((ECharacterized_object) null));
		Set<Param> packageParams = packageQ.getParametricAttributesOfTerminalTemplate(e_pttd);
		for (Param p : packageParams)
		{
			System.out.println(p);
		}
	}
	
	/**
	 * Template method to obtain various key attributes of a given laminate_component, including the 
	 * shape_representation, the stratum_feature (when applicable), the stratum, and the location
	 * (maximum of three transformations) 
	 * Obtains the locating transformation for each, if applicable. 
	 * Qualifies the transformations using the appropriate shape_representation.
	 * 
	 * @param lc
	 * @throws SdaiException
	 * @see MIMtraversal
	 */
	void getDetailsOfLaminateComponent(ELaminate_component lc, EShape_representation pcb_sr) throws SdaiException
	{
		lc_count++;
		//traversedLaminateComponents.add(lc);
		
		EShape_representation lc_sr = null;
		
		if (lc.isKindOf(EGeneric_laminate_text_component.class))
			lc_sr = mimQ.getShapeRepresentationOfGenericLaminateTextComponent((EGeneric_laminate_text_component) lc);
		else
			lc_sr = mimQ.getShapeRepresentationOfAssemblyComponent(lc);
		
		try
		{
			mimQ.getLocationOfLaminateComponent(lc, lc_sr, pcb_sr);
		}
		catch (Exception e)
		{
			MIMqueriesDebugOn();
			MIMopsDebugOn();
			mimQ.getLocationOfLaminateComponent(lc, lc_sr, pcb_sr);
		}
		getSFandStratumOfLaminateComponent(lc, pcb_sr);
	}
	
	/**
	 * Template method to obtain various key attributes of a given laminate_component that is
	 * known to be an element of a structured_layout_component, including the 
	 * shape_representation, the stratum_feature (when applicable), the stratum, and the location.
	 * The transformation locating the slc on the pcb is not included here to avoid unneccessary 
	 * iteration.
	 *  
	 * Obtains the locating transformation for each, if applicable. 
	 * Qualifies the transformations using the appropriate shape_representation.
	 * 
	 * @param lc
	 * @throws SdaiException
	 * @see MIMtraversal
	 */
	void getDetailsOfLaminateComponentInSLC(
			ELaminate_component lc, 
			EStructured_layout_component_sub_assembly_relationship slcsar,
			EStructured_layout_component slc,
			EShape_representation slc_sr,
			EShape_representation pcb_sr) throws SdaiException
	{
		lc_count++;
		//traversedLaminateComponents.add(lc);
		
		EShape_representation lc_sr = null;
		
		if (lc.isKindOf(EGeneric_laminate_text_component.class))
			lc_sr = mimQ.getShapeRepresentationOfGenericLaminateTextComponent((EGeneric_laminate_text_component) lc);
		else
			lc_sr = mimQ.getShapeRepresentationOfAssemblyComponent(lc);
		
		//MIMlocation mimL = mimQ.getLocationOfLaminateComponentInSLC(lc, slcsar, slc, lc_sr, slc_sr);
		MIMlocation mimL = mimQ.getLocationOfAssemblyComponentInSLC(lc, slcsar, slc, lc_sr);
				
		getSFandStratumOfLaminateComponent(lc, pcb_sr);
	}
	
	/**
	 * Obtains the stratum_feature (when applicable) and stratum for a given laminate_component.
	 * In the case of an inter_stratum_feature, a StratumSpan is obtained, containing the from and
	 * to strata.
	 * 
	 * @param lc
	 * @throws SdaiException
	 * @see MIMtraversal
	 */
	void getSFandStratumOfLaminateComponent(ELaminate_component lc, EShape_representation pcb_sr) throws SdaiException
	{
		if (lc.isKindOf(EInter_stratum_feature.class))
		{
			interStratumFeature_count++;
			StratumSpan span = mimQ.getSpanOfInterStratumFeature((EInter_stratum_feature) lc);
			EStratum e_from = span.getFromStratum();
			EStratum e_to = span.getToStratum();
			if (e_from == null)
				System.out.println("Warning: From stratum not found for: "+lc);
			if (e_to == null)
				System.out.println("Warning: To stratum not found for: "+lc);
			
		}
	
		EStratum_feature sf = null;
		if (lc.isKindOf(EMaterial_removal_laminate_component.class))
		{
			EStratum_feature_template_component sftc = mimQ.getSFTCofMRLC(lc);
		}	

		if (lc.isKindOf(EAdditive_laminate_text_component.class))
			sf = mimQ.getStratumFeatureOfALTC((EAdditive_laminate_text_component) lc);
		else if (lc.isKindOf(EStratum_feature_template_component.class))
		{
			sf = mimQ.getStratumFeatureOfSFTC((EStratum_feature_template_component) lc);
		}

		if (sf != null)
			mimQ.getStratumOfStratumFeature(sf);
		else
			mimQ.getStratumOfLC(lc);
	}
	
	/**
	 * Top-level method to traverse all physical_nets in the given e_pcb.  
	 * Delegates to traversePhysicalNet.
	 * 
	 * @param e_pcb
	 * @throws SdaiException
	 * @see MIMtraversal
	 */
	void traversePhysicalNets(ELayered_interconnect_module_design_view e_pcb) throws SdaiException
	{
		System.out.println();
		System.out.println("====================");
		System.out.println("traversePhysicalNets");
		System.out.println();

		APhysical_network a_pn = mimQ.getAllPhysicalNetworksInPcb(e_pcb);
		
		SdaiIterator it_pn = a_pn.createIterator();
		while (it_pn.next()) 
		{
			EPhysical_network e_pn = (EPhysical_network) a_pn.getCurrentMemberEntity(it_pn);
			traversePhysicalNet(e_pn);
			pn_count++;
		}
	}
	
	/**
	 * Obtains the LCPs for a given physical_network.  
	 * Delegates to getDetailsOfLCP.
	 * 
	 * @param e_pn
	 * @throws SdaiException
	 * @see MIMtraversal
	 */
	void traversePhysicalNet(EPhysical_network e_pn) throws SdaiException
	{
		ALayer_connection_point a_lcp = mimQ.getLCPsForPhysicalNetwork(e_pn);
		SdaiIterator it_lcp = a_lcp.createIterator();
		while (it_lcp.next()) 
		{
			ELayer_connection_point e_lcp = (ELayer_connection_point) a_lcp.getCurrentMemberEntity(it_lcp);
			getDetailsOfLCP(e_lcp);
			//traversedLCPs.add(e_lcp);
			lcp_count++;
		}
	}
	
	/**
	 * Obtains the connected laminate_components for a given e_lcp  
	 * 
	 * @param e_lcp
	 * @throws SdaiException
	 * @see MIMtraversal
	 */
	void getDetailsOfLCP(ELayer_connection_point e_lcp) throws SdaiException
	{
		EInter_stratum_feature e_isf = mimQ.getInterStratumFeatureForDLLCP(e_lcp);
		APlated_passage_dependent_land a_ppdl = mimQ.getPPDLandsforDLLCP(e_lcp);
		EContact_size_dependent_land e_csdl = mimQ.getCSDLandforDLLCP(e_lcp);
		AConductive_interconnect_element a_cie = mimQ.getCIEforLCP(e_lcp);
		EConnected_area_component e_cac = mimQ.getConnectedAreaComponentforLCP(e_lcp);
	}
}
