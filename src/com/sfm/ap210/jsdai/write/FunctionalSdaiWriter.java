package com.sfm.ap210.jsdai.write;

import java.util.HashMap;
import java.util.Map;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.AProduct;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.AProduct_context;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.ARepresentation_item;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.CApplication_context;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.CApplication_protocol_definition;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.CComponent_functional_terminal;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.CComponent_functional_unit;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.CFunctional_unit;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.CFunctional_unit_terminal_definition;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.CName_attribute;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.CNetwork_node_definition;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.CProduct;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.CProduct_context;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.CProduct_definition_context;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.CProduct_definition_context_association;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.CProduct_definition_context_role;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.CProduct_definition_formation;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.CProduct_definition_relationship;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.CProduct_definition_shape;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.CProduct_related_product_category;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.CProperty_definition;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.CProperty_definition_relationship;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.CShape_aspect_relationship;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.CThermal_network;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.CThermal_network_node_definition;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EApplication_context;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EApplication_protocol_definition;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.ECartesian_point;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EComponent_functional_terminal;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EComponent_functional_unit;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EFunctional_unit;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EFunctional_unit_terminal_definition;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EName_attribute;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.ENetwork_node_definition;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EPackaged_part_terminal;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EProduct;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EProduct_context;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EProduct_definition;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EProduct_definition_context;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EProduct_definition_context_association;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EProduct_definition_context_role;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EProduct_definition_formation;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EProduct_definition_relationship;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EProduct_definition_shape;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EProduct_related_product_category;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EProperty_definition;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EProperty_definition_relationship;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EProperty_definition_representation;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.ERepresentation_context;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EShape_aspect_relationship;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EShape_representation;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EThermal_network;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EThermal_network_node_definition;
import jsdai.lang.A_double;
import jsdai.lang.EEntity;
import jsdai.lang.SdaiException;
import jsdai.lang.SdaiModel;

import com.sfm.ap210.jsdai.functional.FunctionalModelObject;
import com.sfm.ap210.jsdai.functional.FunctionalProductVersion;
import com.sfm.ap210.jsdai.functional.Functional_unit;
import com.sfm.ap210.jsdai.functional.Functional_unit_network_definition;
import com.sfm.ap210.jsdai.functional.Functional_unit_network_node_definition;
import com.sfm.ap210.jsdai.functional.Functional_unit_terminal;
import com.sfm.ap210.jsdai.functional.Functional_unit_usage_view;
import com.sfm.ap210.jsdai.functional.Scalar_terminal_definition;
import com.sfm.ap210.jsdai.functional.Thermal_functional_unit_network_node_definition;
import com.sfm.ap210.jsdai.functional.Thermal_network_definition;

public class FunctionalSdaiWriter { 
	SdaiModel outModel;
	EApplication_context applicationContext = null;
	EProduct_context functionalProductContext = null;
	EProduct_definition_context_role partDefinitionContextRole = null;
	EProduct_definition_context functionalNetworkDesignContext = null;
	EProduct_definition_context functionalDesignUsageContext = null;
	EProduct_related_product_category e_functional_prpc = null;
	EProduct_related_product_category e_information_prpc = null;
	AProduct a_functional_products = null;
	AProduct a_information_products = null;
	Map<FunctionalModelObject, EEntity> foMap = null;
	Map<Functional_unit, EProduct_definition_shape> pdsMap = null;
	Map<Scalar_terminal_definition, EPackaged_part_terminal> terminalMap = null;
	ParamSdaiWriter paramWriter = null;
	
	public FunctionalSdaiWriter(SdaiModel model) throws SdaiException
	{
		outModel = model;
		createFunctionalNetworkContext();
		paramWriter = new ParamSdaiWriter(outModel, applicationContext);
		foMap = new HashMap<FunctionalModelObject, EEntity>();
		pdsMap = new HashMap<Functional_unit, EProduct_definition_shape>();
		terminalMap = new HashMap<Scalar_terminal_definition, EPackaged_part_terminal>();
		
	}
	
	void createFunctionalNetworkContext() throws SdaiException
	{
		applicationContext = (EApplication_context) outModel.createEntityInstance(CApplication_context.class);
		applicationContext.setApplication(null, "component functional model demonstration");
		
		functionalProductContext = (EProduct_context) outModel.createEntityInstance(CProduct_context.class);
		functionalProductContext.setName(null, "functional");
		functionalProductContext.setDiscipline_type(null, "");
		functionalProductContext.setFrame_of_reference(null, applicationContext);
		
		EApplication_protocol_definition app_protocol = (EApplication_protocol_definition) outModel.createEntityInstance(CApplication_protocol_definition.class); 
		app_protocol.setStatus(null, "INTERNATIONAL STANDARD"); 
		app_protocol.setApplication_interpreted_model_schema_name(null, "ap210_electronic_assembly_interconnect_and_packaging_design"); 
		app_protocol.setApplication_protocol_year(null, 2010); 
		app_protocol.setApplication(null, applicationContext); 
		
		functionalNetworkDesignContext = (EProduct_definition_context) outModel.createEntityInstance(CProduct_definition_context.class);
		functionalNetworkDesignContext.setName(null, "functional network design");
		functionalNetworkDesignContext.setLife_cycle_stage(null, "design");
		functionalNetworkDesignContext.setFrame_of_reference(null, applicationContext);
		
		functionalDesignUsageContext = (EProduct_definition_context) outModel.createEntityInstance(CProduct_definition_context.class);
		functionalDesignUsageContext.setName(null, "functional design usage");
		functionalDesignUsageContext.setLife_cycle_stage(null, "design");
		functionalDesignUsageContext.setFrame_of_reference(null, applicationContext);
			
		partDefinitionContextRole = (EProduct_definition_context_role) outModel.createEntityInstance(CProduct_definition_context_role.class);
		partDefinitionContextRole.setName(null, "part definition type");
		
		e_functional_prpc = (EProduct_related_product_category) outModel.createEntityInstance(CProduct_related_product_category.class);
		e_functional_prpc.setName(null, "functional");
		a_functional_products = (AProduct) e_functional_prpc.createProducts(null);
		
		e_information_prpc = (EProduct_related_product_category) outModel.createEntityInstance(CProduct_related_product_category.class);
		e_information_prpc.setName(null, "information");
		a_information_products = (AProduct) e_information_prpc.createProducts(null);
	}

	/**
	 * Implements MIM mapping requirements for Functional_version and Functional_product ARM AOs
	 * @param fpv
	 * @throws SdaiException 
	 */
	public EProduct_definition_formation instantiateFunctionalProductVersion(FunctionalProductVersion fpv) throws SdaiException
	{
		if (foMap.containsKey(fpv))
			return  (EProduct_definition_formation) foMap.get(fpv);
		
		// per mapping requirements of Functional_product and Information_product, product must have associated 
		// prpc of both "functional" and "information"
		EProduct e_p = (EProduct) outModel.createEntityInstance(CProduct.class);
		a_functional_products.addUnordered(e_p);
		a_information_products.addUnordered(e_p);
		e_p.setId(null, fpv.getProductId());
		e_p.setName(null, fpv.product_name);
		AProduct_context a_pc = e_p.createFrame_of_reference(null);
		a_pc.addUnordered(functionalProductContext);
		EProduct_definition_formation e_pdf = (EProduct_definition_formation) outModel.createEntityInstance(CProduct_definition_formation.class);
		e_pdf.setId(null, fpv.getVersionId());
		e_pdf.setOf_product(null, e_p);
		foMap.put(fpv, e_pdf);
		
		paramWriter.addProductSpecificParameters(e_p, fpv.params);
		
		return e_pdf;
	}
	
	/**
	 * 5.1.3 Functional_unit
	 * 5.1.8.1 Functional_unit_terminal to Functional_unit (as accessed_functional_unit)
	 * 5.1.3.1 reference_designation
	 * 5.1.3.4 Functional_unit to Functional_unit_definition (as definition)
	 * 
	 * @param fu
	 * @return
	 * @throws SdaiException
	 */
	EComponent_functional_unit instantiateFunctionalUnit(Functional_unit fu) throws SdaiException
	{
		if (foMap.containsKey(fu))
			return  (EComponent_functional_unit) foMap.get(fu);
		EProduct_definition_formation e_pdf = instantiateFunctionalProductVersion(fu.getFUUV().getFunctionalProductVersion());
		
		EComponent_functional_unit e_cfu = (EComponent_functional_unit) outModel.createEntityInstance(CComponent_functional_unit.class);
		e_cfu.setId(null, fu.getRefDes());
		e_cfu.setDescription(null, fu.getDescription());
		e_cfu.setFormation(null, e_pdf);
		e_cfu.setFrame_of_reference(null, functionalNetworkDesignContext);
		EProduct_definition_shape e_pds = (EProduct_definition_shape) outModel.createEntityInstance(CProduct_definition_shape.class);
		e_pds.setName(null, "pds of "+fu.getRefDes());
		e_pds.setDefinition(null, e_cfu);
		foMap.put(fu, e_cfu);
		pdsMap.put(fu, e_pds);
		
		EFunctional_unit e_fuuv = instantiateFunctionalUnitUsageView(fu.getFUUV());
		
		if (fu.getFUUV().fpv.getFUND() != null)
			instantiateFunctionalUnitNetworkDefinition(fu.getFUUV().fpv.getFUND());
		
		EProduct_definition_relationship e_pdr = (EProduct_definition_relationship) outModel.createEntityInstance(CProduct_definition_relationship.class);
		e_pdr.setId(null, "");
		e_pdr.setName(null, "instantiated functional unit");
		e_pdr.setRelated_product_definition(null, e_cfu);
		e_pdr.setRelating_product_definition(null, e_fuuv);
		
		for (Functional_unit_terminal t: fu.getTerminals())
		{
			EComponent_functional_terminal e_cft = instantiateFunctionalUnitTerminal(t);
		}
		
		paramWriter.addProductSpecificParameters(e_cfu, fu.getParams());
		return e_cfu;
	}
	
	/**
	 * 5.1.8 Functional_unit_terminal
	 * 5.1.8.1 Functional_unit_terminal to Functional_unit (as accessed_functional_unit)
	 * 5.1.8.2 Functional_unit_terminal to Functional_unit_usage_view_terminal_definition (as definition)
	 * 
	 * @param fut
	 */
	EComponent_functional_terminal instantiateFunctionalUnitTerminal(Functional_unit_terminal fut) throws SdaiException
	{
		if (foMap.containsKey(fut))
			return  (EComponent_functional_terminal) foMap.get(fut);
		
		// 5.1.8 Functional_unit_terminal
		EComponent_functional_terminal e_cft = (EComponent_functional_terminal) outModel.createEntityInstance(CComponent_functional_terminal.class);
		foMap.put(fut, e_cft);
		e_cft.setName(null, fut.getDefiningScalarTerminal().getSignalName()+fut.getFunctionalUnit().getRefDes());
		e_cft.setProduct_definitional(null, 1);
		
		// 5.1.8.1 Functional_unit_terminal to Functional_unit (as accessed_functional_unit)
		EComponent_functional_unit e_cfu = instantiateFunctionalUnit(fut.getFunctionalUnit());
		e_cft.setOf_shape(null, pdsMap.get(fut.getFunctionalUnit()));
		
		// 5.1.8.2 Functional_unit_terminal to Functional_unit_usage_view_terminal_definition (as definition)
		EFunctional_unit_terminal_definition e_futd = instantiateScalarTerminalDefinition(fut.getDefiningScalarTerminal());
		EShape_aspect_relationship e_sar = (EShape_aspect_relationship) outModel.createEntityInstance(CShape_aspect_relationship.class);
		e_sar.setName(null, "instantiated terminal");
		e_sar.setRelated_shape_aspect(null, e_cft);
		e_sar.setRelating_shape_aspect(null, e_futd);
		
		
		return e_cft;
	}
	
	/**
	 * AO: Functional_unit_usage_view
	 * Application module: Functional usage view 
	 * 5.1.10.2 Functional_unit_usage_view_terminal_definition to Functional_unit_usage_view (as associated_functional_unit_definition)
	 * @param fuuv
	 * @return
	 * @throws SdaiException
	 */
	public EFunctional_unit instantiateFunctionalUnitUsageView(Functional_unit_usage_view fuuv) throws SdaiException
	{
		if (foMap.containsKey(fuuv))
			return  (EFunctional_unit) foMap.get(fuuv);
		
		EProduct_definition_formation e_pdf = instantiateFunctionalProductVersion(fuuv.getFunctionalProductVersion());
		EFunctional_unit e_fu = (EFunctional_unit) outModel.createEntityInstance(CFunctional_unit.class);
		e_fu.setId(null, fuuv.getId());
		e_fu.setName(null, "");
		e_fu.setFrame_of_reference(null, functionalDesignUsageContext);
		EProduct_definition_context_association e_pdca = (EProduct_definition_context_association) outModel.createEntityInstance(CProduct_definition_context_association.class);
		e_pdca.setDefinition(null, e_fu);
		e_pdca.setRole(null, partDefinitionContextRole);
		e_pdca.setFrame_of_reference(null, functionalDesignUsageContext);
		
		for (Scalar_terminal_definition std: fuuv.getTerminals())
		{
			EFunctional_unit_terminal_definition e_futd = instantiateScalarTerminalDefinition(std);
			e_futd.setOf_shape(null, e_fu);
		}
		
		e_fu.setFormation(null, e_pdf);
		foMap.put(fuuv, e_fu);
		return e_fu;
	}
	
	/**
	 * AO: Scalar_terminal_definition
	 * Application module: Functional usage view 
	 * satisfies the following mapping table requirements:
	 * 5.1.10 Functional_unit_usage_view_terminal_definition
	 * 5.1.10.1 signal_name
	 * 5.1.13 Scalar_terminal_definition
	 * 
	 * @param std
	 * @return
	 * @throws SdaiException
	 */
	public EFunctional_unit_terminal_definition instantiateScalarTerminalDefinition(Scalar_terminal_definition std) throws SdaiException 
	{
		if (foMap.containsKey(std))
			return  (EFunctional_unit_terminal_definition) foMap.get(std);
		
		EFunctional_unit_terminal_definition e_futd = (EFunctional_unit_terminal_definition) outModel.createEntityInstance(CFunctional_unit_terminal_definition.class);
		e_futd.setDescription(null, "scalar terminal");
		e_futd.setName(null, std.getSignalName());
		// guessing shape_aspect.product_definitional=”.T.” = 1
		e_futd.setProduct_definitional(null, 1);
		foMap.put(std, e_futd);
		return e_futd;
	}
	
	/**
	 * Top-level method... includes the product and usage view, as well as underlying hierarchy
	 * need to first instantiate used functional units
	 * 
	 * 5.1.4 Functional_unit_network_definition
	 * 5.1.4.1 Functional_unit_network_definition to Functional_unit_usage_view (as usage_view)
	 * 5.1.5.2 Functional_unit_network_node_definition to Functional_unit_network_definition (as associated_functional_unit_definition)
	 * 
	 * @param fund
	 * @throws SdaiException
	 */
	public EFunctional_unit instantiateFunctionalUnitNetworkDefinition(Functional_unit_network_definition fund) throws SdaiException
	{
		if (foMap.containsKey(fund))
			return  (EFunctional_unit) foMap.get(fund);
		
		EProduct_definition_formation e_pdf = instantiateFunctionalProductVersion(fund.getFunctionalProductVersion());
		
		// 5.1.4 Functional_unit_network_definition
		EFunctional_unit e_fund = null;
		
		if (!(fund instanceof Thermal_network_definition))
			e_fund = (EFunctional_unit) outModel.createEntityInstance(CFunctional_unit.class);
		else
			e_fund = (EThermal_network) outModel.createEntityInstance(CThermal_network.class);
		
		e_fund.setFrame_of_reference(null, functionalNetworkDesignContext);
		foMap.put(fund, e_fund);
		
		e_fund.setId(null, fund.getId());
		e_fund.setName(null, "");
		EProduct_definition_context_association e_pdca = (EProduct_definition_context_association) outModel.createEntityInstance(CProduct_definition_context_association.class);
		e_pdca.setDefinition(null, e_fund);
		e_pdca.setRole(null, partDefinitionContextRole);
		e_pdca.setFrame_of_reference(null, functionalNetworkDesignContext);
		
		e_fund.setFormation(null, e_pdf);
		
		// 5.1.4.1 Functional_unit_network_definition to Functional_unit_usage_view (as usage_view)
		
		EFunctional_unit e_fuuv = instantiateFunctionalUnitUsageView(fund.getFUUV());
		
		for (Functional_unit_network_node_definition node : fund.getNodes())
		{
			ENetwork_node_definition e_nnd = instantiateNetworkNodeDefinition(node);
			EProduct_definition_relationship e_pdr_network_node = (EProduct_definition_relationship) outModel.createEntityInstance(CProduct_definition_relationship.class);
			// 5.1.5.2 Functional_unit_network_node_definition to Functional_unit_network_definition (as associated_functional_unit_definition)
			e_pdr_network_node.setName(null, "functional unit network node");
			e_pdr_network_node.setId(null, "");
			e_pdr_network_node.setRelated_product_definition(null, e_nnd);
			e_pdr_network_node.setRelating_product_definition(null, e_fund);
			
			Scalar_terminal_definition usageViewTerminal = node.getUsageViewTerminal();
			EFunctional_unit_terminal_definition e_futd = instantiateScalarTerminalDefinition(usageViewTerminal);
			EProperty_definition_relationship e_pdr = (EProperty_definition_relationship) outModel.createEntityInstance(CProperty_definition_relationship.class);
			e_pdr.setDescription(null, "");
			e_pdr.setName(null, "functional unit network terminal definition node assignment");
			EProperty_definition e_node_pd = (EProperty_definition) outModel.createEntityInstance(CProperty_definition.class);
			e_node_pd.setName(null, e_nnd.getId(null));
			e_node_pd.setDefinition(null, e_nnd);
			EProperty_definition e_futd_pd = (EProperty_definition) outModel.createEntityInstance(CProperty_definition.class);
			e_futd_pd.setName(null, e_futd.getName(null));
			e_futd_pd.setDefinition(null, e_futd);
			e_pdr.setRelating_property_definition(null, e_node_pd);
			e_pdr.setRelated_property_definition(null, e_futd_pd);
		}
		
		
		EProduct_definition_relationship e_pdr = (EProduct_definition_relationship) outModel.createEntityInstance(CProduct_definition_relationship.class);
		e_pdr.setName(null, "design usage");
		e_pdr.setId(null, "");
		e_pdr.setRelated_product_definition(null, e_fund);
		e_pdr.setRelating_product_definition(null, e_fuuv);
		
		return e_fund;
	}
	
	/**
	 * 5.1.2.3 Thermal_functional_unit_network_node_definition to Cartesian_point (as location) 
	 *
	 * @param node
	 * @param location
	 * @param context
	 * @return
	 * @throws SdaiException
	 */
	void attachNodeLocation(ENetwork_node_definition node, double[] location, ERepresentation_context context) throws SdaiException {
		//create location representation to hold location point
		EShape_representation location_rep = 
			(EShape_representation) outModel.createEntityInstance(EShape_representation.class);
		//assign name and context of representation
		location_rep.setName(null, "location");
		location_rep.setContext_of_items(null, context);
		ARepresentation_item items = location_rep.createItems(null);
		//create point for location
		ECartesian_point point = (ECartesian_point)outModel.createEntityInstance(ECartesian_point.class);
		point.setName(null, "");
		A_double coords = point.createCoordinates(null);
		int idx = 1;
		for(double coord : location) {
			coords.addByIndex(idx, coord);
			idx++;
		}
		//add point to location representation
		items.addUnordered(point);
		//tie location representation to the network definition node
		EProperty_definition_representation pdef_rep = 
			(EProperty_definition_representation) outModel.createEntityInstance(EProperty_definition_representation.class);
		pdef_rep.setDefinition(null, node);
		pdef_rep.setUsed_representation(null, location_rep);
	}
	
	/**
	 * Functional_unit_network_node_definition
	 * Application module: Network functional design view
	 * 
	 * 
	 * 5.1.5 Functional_unit_network_node_definition
	 * 5.1.5.1 functional_unit_network_node_name
	 * 
	 * 5.1.10 Functional_unit_terminal_node_assignment
	 * 5.1.10.1 Functional_unit_terminal_node_assignment to Functional_unit_network_node_definition (as composed_node)
	 * 
	 * @param node
	 * @return
	 * @throws SdaiException
	 */
	public ENetwork_node_definition instantiateNetworkNodeDefinition(Functional_unit_network_node_definition node) throws SdaiException 
	{
		if (foMap.containsKey(node))
			return  (ENetwork_node_definition) foMap.get(node);
		
		ENetwork_node_definition e_nnd = null;
		
		if (!(node instanceof Thermal_functional_unit_network_node_definition)) {			
			e_nnd = (ENetwork_node_definition) outModel.createEntityInstance(CNetwork_node_definition.class);
		} else {
			e_nnd = (EThermal_network_node_definition) outModel.createEntityInstance(CThermal_network_node_definition.class);
			Thermal_functional_unit_network_node_definition thermal_node = 
				(Thermal_functional_unit_network_node_definition) node;
			if(thermal_node.location != null) {
				attachNodeLocation(e_nnd, thermal_node.location, thermal_node.location_context);
			}
		}
		
		e_nnd.setFrame_of_reference(null, functionalNetworkDesignContext);
		EProduct_definition_context_association e_pdca = (EProduct_definition_context_association) outModel.createEntityInstance(CProduct_definition_context_association.class);
		e_pdca.setDefinition(null, e_nnd);
		e_pdca.setRole(null, partDefinitionContextRole);
		e_pdca.setFrame_of_reference(null, functionalNetworkDesignContext);
		e_nnd.setId(null, node.getName());
		setProductDefinitionName(e_nnd, node.getName());
		
		e_nnd.setFormation(null, instantiateFunctionalProductVersion(node.getNetworkDefinition().getFunctionalProductVersion()));
		
		
		
		for (Functional_unit_terminal fut : node.getFunctionalUnitTerminals())
		{
			EProperty_definition_relationship e_pdr = (EProperty_definition_relationship) outModel.createEntityInstance(CProperty_definition_relationship.class);
			e_pdr.setName(null, "functional unit terminal node assignment");
			e_pdr.setDescription(null, "");
			EProperty_definition e_pdToFunctionalUnitNND = (EProperty_definition) outModel.createEntityInstance(CProperty_definition.class);
			e_pdToFunctionalUnitNND.setDefinition(null, e_nnd);
			e_pdToFunctionalUnitNND.setName(null, "pd of "+node.getName());
			EComponent_functional_terminal e_fut = instantiateFunctionalUnitTerminal(fut);
			e_pdToFunctionalUnitNND.setDescription(null, "nnd "+node.getName()+" to cft "+e_fut.getName(null));
			EProperty_definition e_pdToFunctionalUnitTerminal = (EProperty_definition) outModel.createEntityInstance(CProperty_definition.class);
			e_pdToFunctionalUnitTerminal.setDefinition(null, e_fut);
			e_pdToFunctionalUnitTerminal.setName(null, "pd of "+e_fut.getName(null));
			e_pdToFunctionalUnitTerminal.setDescription(null, "cft "+e_fut.getName(null)+" to nnd "+node.getName());
			e_pdr.setRelating_property_definition(null, e_pdToFunctionalUnitNND);
			e_pdr.setRelated_property_definition(null, e_pdToFunctionalUnitTerminal);
		}
		foMap.put(node, e_nnd);
		return e_nnd;
	}
	
	/**
	 * product_definition.name is a derived attribute
	 * name : label := get_name_value(SELF);
	 * @param name
	 * @throws SdaiException
	 */
	void setProductDefinitionName(EProduct_definition e_pd, String name) throws SdaiException 
	{
		EName_attribute e_na = (EName_attribute) outModel.createEntityInstance(CName_attribute.class);
		e_na.setAttribute_value(null, name);
		e_na.setNamed_item(null, e_pd);
	}
	
	/*
	public void populatePackagedPartTerminalToFunctionalTerminalRelationship(String ppt_name,  Functional_unit_usage_view fuuv, String signal_name) throws SdaiException
	{
		Scalar_terminal_definition scalarTerminal = fuuv.getTerminalOfName(signal_name);
		EPackaged_part_terminal e_prt_term = this.getPackagedPartTerminalOfName(ppt_name);
		terminalMap.put(scalarTerminal, e_prt_term);

		EFunctional_unit_terminal_definition e_futd = instantiateScalarTerminalDefinition(scalarTerminal);
		EShape_aspect_relationship e_sar = (EShape_aspect_relationship) outModel.createEntityInstance(CShape_aspect_relationship.class);
		e_sar.setName(null, "functional terminal allocation");
		e_sar.setRelating_shape_aspect(null, e_futd);
		e_sar.setRelated_shape_aspect(null, e_prt_term);
	}
	*/
}