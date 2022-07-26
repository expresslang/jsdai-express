package com.sfm.ap210.jsdai;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
import com.sfm.ap210.jsdai.param.ParameterAssignment;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.AComponent_functional_terminal;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.AFunctional_unit;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.AFunctional_unit_terminal_definition;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.ANetwork_node_definition;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.AParameter_assignment;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.AProduct;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.AProduct_definition;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.AProduct_definition_formation;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.AProperty_definition;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.AProperty_definition_representation;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.CProperty_definition_representation;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.ECartesian_point;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EComponent_functional_terminal;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EComponent_functional_unit;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EFunctional_unit;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EFunctional_unit_terminal_definition;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.ENetwork_node_definition;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EParameter_assignment;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EProduct;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EProduct_definition_context;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EProduct_definition_formation;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EProduct_definition_shape;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EProduct_specific_parameter_value_assignment;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EProperty_definition;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EProperty_definition_representation;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.ERepresentation;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.ERepresentation_item;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EThermal_network;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EThermal_network_node_definition;
import jsdai.lang.A_double;
import jsdai.lang.EEntity;
import jsdai.lang.SdaiException;
import jsdai.lang.SdaiIterator;
import jsdai.lang.SdaiModel;

public class MIMfunctionalQueriesImpl {
	MIMqueries mimQ;
	MIMparamQueries paramQ;
	SdaiModel model;
	MIMops ops;
	Map<EEntity, FunctionalModelObject> entityToFunctionalObject = new HashMap<EEntity, FunctionalModelObject>();
	
	public MIMfunctionalQueriesImpl(SdaiModel m, MIMops ops, MIMqueries q, MIMparamQueries pq)
	{
		this.paramQ = pq;
		this.model = m;
		this.mimQ = q;
		this.ops = ops;
	}
	
	public MIMfunctionalQueriesImpl(SdaiModel m) throws SdaiException
	{
		this.model = m;
		this.ops = new MIMopsImpl(m);
		this.mimQ = new MIMqueriesImpl(m, ops);
		this.paramQ = new MIMparamQueriesImpl(mimQ, ops);
	}

	public AProduct getAllFunctionalProductsInModel() throws SdaiException
	{
		return mimQ.getAllProductsOfASpecificPRPC("functional");
	}
	
	public EProduct getFunctionalProductWithGivenId(String id) throws SdaiException
	{
		AProduct aFunctional = this.getAllFunctionalProductsInModel();
		SdaiIterator it = aFunctional.createIterator();
		while (it.next()) {
			EProduct e_functional = (EProduct) aFunctional.getCurrentMemberEntity(it);
			if ((e_functional.testId(null)) && (e_functional.getId(null).equals(id)))
				return e_functional;
		}		
		return null;
	}
	
	public AProduct_definition_formation getFunctionalProductVersionsWithGivenProductId(String id) throws SdaiException
	{
		EProduct e_p = getFunctionalProductWithGivenId(id);
		return mimQ.getAllVersionsOfProduct(e_p);
	}
	
	public EProduct_definition_formation getFunctionalProductVersionWithGivenProductId(String id) throws SdaiException
	{
		EProduct e_p = getFunctionalProductWithGivenId(id);
		if (e_p == null)
			throw new IllegalArgumentException("Unable to find product with given id: "+id);
		AProduct_definition_formation a_pdf = mimQ.getAllVersionsOfProduct(e_p);
		if (a_pdf.getMemberCount() != 1)
			throw new IllegalArgumentException("Not exactly one version of given product.");
		return a_pdf.getByIndex(1);
	}
	
	public EFunctional_unit getFunctionalUnitOfGivenContext(AFunctional_unit a_fu, String context) throws SdaiException
	{
		SdaiIterator it = a_fu.createIterator();
		while (it.next()) {
			EFunctional_unit e_fu = (EFunctional_unit) a_fu.getCurrentMemberEntity(it);
			EProduct_definition_context e_pdc = e_fu.getFrame_of_reference(null);
			if (e_pdc.getName(null).equals(context))
				return e_fu;
		}
		return null;
	}
	
	public EFunctional_unit getFunctionalUnitNetworkDefinitionFromUsageView(EFunctional_unit e_fuuv) throws SdaiException
	{
		return ops.fu_relatedTo_fu_through_pdr_2(e_fuuv, "design usage");
	}
	
	public EFunctional_unit getFunctionalUnitNetworkDefinition(EProduct_definition_formation e_pdf) throws SdaiException
	{
		AFunctional_unit a_fu = ops.All_fu_referencing_pdf(e_pdf);
		return getFunctionalUnitOfGivenContext(a_fu, "functional network design");
	}
	
	public EFunctional_unit getFunctionalUnitUsageView(EProduct_definition_formation e_pdf) throws SdaiException
	{
		AFunctional_unit a_fu = ops.All_fu_referencing_pdf(e_pdf);
		return getFunctionalUnitOfGivenContext(a_fu, "functional design usage");
	}
	
	public EFunctional_unit getUsageViewOfNetworkDefinition(EFunctional_unit e_fu) throws SdaiException
	{
		return ops.fu_relatedTo_fu_through_pdr(e_fu, "design usage");
	}
	
	public ANetwork_node_definition getNodesOfNetworkDefinition(EFunctional_unit e_fund) throws SdaiException
	{
		return ops.All_nnd_relatedTo_fu_through_pdr(e_fund, "functional unit network node");
	}
	
	/**
	 * Get the location representation of the given thermal_network_node_definition
	 * 
	 * @param node
	 * @return
	 * @throws SdaiException
	 */
	public ERepresentation getLocationOfThermalNetworkNode(EThermal_network_node_definition node) throws SdaiException {
		AProperty_definition_representation a_pdr = new AProperty_definition_representation();
		CProperty_definition_representation.usedinDefinition(null, node, null, a_pdr);
		for(SdaiIterator i = a_pdr.createIterator(); i.next();) {
			EProperty_definition_representation pdr = a_pdr.getCurrentMember(i);
			ERepresentation rep = pdr.getUsed_representation(null);
			if(rep == null)
				continue;
			if(!rep.testName(null) || !rep.getName(null).equals("location")) 
				continue;
			return rep;
		}
		return null;
	}
	
	/**
	 * Add the Functional_unit_network_node_definition to the given Functional_unit_network_definition.
	 * If this node has an associated usage view terminal, set that as well.
	 * @param fund
	 * @param e_nnd
	 * @return
	 * @throws SdaiException
	 */
	public Functional_unit_network_node_definition addTerminalDefinitionToNetworkDefinition(Functional_unit_network_definition fund, ENetwork_node_definition e_nnd) throws SdaiException
	{
		if (entityToFunctionalObject.containsKey(e_nnd))
			return (Functional_unit_network_node_definition) entityToFunctionalObject.get(e_nnd);
		
		String node_name = e_nnd.getId(null);
		
		Functional_unit_network_node_definition funnd = null;
		
		if (e_nnd.isKindOf(EThermal_network_node_definition.class)) {
			Thermal_functional_unit_network_node_definition tfunnd =
				new Thermal_functional_unit_network_node_definition(node_name, fund);
			ERepresentation loc = getLocationOfThermalNetworkNode((EThermal_network_node_definition)e_nnd);
			if(loc != null) {
				ERepresentation_item item = ops.ri_referencedBy_r(loc);
				if(item instanceof ECartesian_point) {
					ECartesian_point point = (ECartesian_point) item;
					A_double a_c = point.getCoordinates(null);
					double[] coords = new double[a_c.getMemberCount()];
					for(int i=0; i < coords.length; ++i) {
						coords[i] = a_c.getByIndex(i+1);
					}
					tfunnd.location = coords;
					tfunnd.location_context = loc.getContext_of_items(null);
				}				
			}
			funnd = tfunnd;
		}
		else { 
			funnd = new Functional_unit_network_node_definition(node_name, fund);
		}
		
		entityToFunctionalObject.put(e_nnd, funnd);
		fund.addNode(funnd);
		EFunctional_unit_terminal_definition e_fuuvtd = getUsageViewTerminalDefinitionForNetworkNodeDefinition(e_nnd);
		if (e_fuuvtd != null)
		{
			if (entityToFunctionalObject.containsKey(e_fuuvtd))
			{
				Scalar_terminal_definition std = (Scalar_terminal_definition) entityToFunctionalObject.get(e_fuuvtd);
				funnd.setUsageViewTerminal(std);
			}
			else
				throw new IllegalArgumentException("Unable to find mapping for scalar terminal definition: "+e_fuuvtd);
		}
		return funnd;
	}
	
	public Scalar_terminal_definition getTerminalDefinition(EFunctional_unit_terminal_definition e_futd) throws SdaiException
	{
		if (entityToFunctionalObject.containsKey(e_futd))
			return (Scalar_terminal_definition) entityToFunctionalObject.get(e_futd);
		
		throw new IllegalArgumentException("Unable to map terminal definition: "+e_futd);
	}
	
	public Scalar_terminal_definition addTerminalDefinitionToUsageView(Functional_unit_usage_view fuuv, EFunctional_unit_terminal_definition e_futd) throws SdaiException
	{
		if (entityToFunctionalObject.containsKey(e_futd))
			return (Scalar_terminal_definition) entityToFunctionalObject.get(e_futd);
		
		String signal_name = e_futd.getName(null);
		Scalar_terminal_definition std = new Scalar_terminal_definition(signal_name, fuuv);
		entityToFunctionalObject.put(e_futd, std);
		fuuv.addTerminal(std);
		return std;
	}

	/**
	 * not guantanteed to exist. 
	 * Returns Functional_unit_usage_view_terminal_definition (most likely Scalar_terminal_definition)
	 * give a Functional_network_node_definition. These are related by a 
	 * Functional_unit_network_terminal_definition_node_assignment.
	 * @return
	 */
	EFunctional_unit_terminal_definition getUsageViewTerminalDefinitionForNetworkNodeDefinition(ENetwork_node_definition e_nnd) throws SdaiException
	{
		AProperty_definition a_pd = ops.All_pd_referencing_nnd(e_nnd);
		
		SdaiIterator it = a_pd.createIterator();
		while (it.next()) {
			EProperty_definition e_pd1 = (EProperty_definition) a_pd.getCurrentMemberEntity(it);
			EProperty_definition e_pd2 = ops.pd_relatedTo_pd_through_pdr_3(e_pd1, "functional unit network terminal definition node assignment");
			if (e_pd2 != null)
			{
				EEntity e_e = e_pd2.getDefinition(null);
				if (e_e.isKindOf(EFunctional_unit_terminal_definition.class))
					return (EFunctional_unit_terminal_definition) e_e;
			}
		}
		return null;
	}

	/**
	 * Get all Functional_unit_terminal (component_functional_terminal) related to the given 
	 * Functional_unit_network_node_definition (network_node_definition) through a 
	 * Functional_unit_terminal_node_assignment
	 * @param e_nnd
	 * @return
	 * @throws SdaiException
	 */
	public AComponent_functional_terminal getFunctionalUnitTerminalsForNetworkNodeDefinition(ENetwork_node_definition e_nnd) throws SdaiException
	{
		AComponent_functional_terminal a_cft = new AComponent_functional_terminal();
		AProperty_definition a_pd = ops.All_pd_referencing_nnd(e_nnd);
		
		SdaiIterator it = a_pd.createIterator();
		while (it.next()) {
			EProperty_definition e_pd1 = (EProperty_definition) a_pd.getCurrentMemberEntity(it);
			EProperty_definition e_pd2 = ops.pd_relatedTo_pd_through_pdr_3(e_pd1, "functional unit terminal node assignment");
			if (e_pd2 != null)
			{
				EEntity e_e = e_pd2.getDefinition(null);
				if (e_e.isKindOf(EComponent_functional_terminal.class))
					a_cft.addUnordered((EComponent_functional_terminal) e_e);
			}
		}
		return a_cft;
	}
	
	public AFunctional_unit_terminal_definition getTerminalsOfUsageView(EFunctional_unit e_fuuv) throws SdaiException
	{
		return ops.All_futd_referencing_fu(e_fuuv);
	}

	public Functional_unit_network_definition getFunctionalUnitNetworkDefinition(EFunctional_unit e_fund) throws SdaiException
	{
		if (entityToFunctionalObject.containsKey(e_fund))
			return (Functional_unit_network_definition) entityToFunctionalObject.get(e_fund);
		
		EProduct_definition_formation e_pdf = e_fund.getFormation(null);
		
		Functional_unit_network_definition fund = null;
		
		if (e_fund.isKindOf(EThermal_network.class))
			fund = new Thermal_network_definition(getFunctionalProductVersion(e_pdf));
		else
			fund = new Functional_unit_network_definition(getFunctionalProductVersion(e_pdf));
		
		
		entityToFunctionalObject.put(e_fund, fund);
		
		EFunctional_unit e_fuuv = getUsageViewOfNetworkDefinition(e_fund);
		Functional_unit_usage_view fuuv = getFunctionalUnitUsageView(e_fuuv);
		fund.setFUUV(fuuv);
		
		ANetwork_node_definition a_nnd = getNodesOfNetworkDefinition(e_fund);
		SdaiIterator it = a_nnd.createIterator();
		while (it.next()) {
			ENetwork_node_definition e_nnd = (ENetwork_node_definition) a_nnd.getCurrentMemberEntity(it);
			Functional_unit_network_node_definition funnd = addTerminalDefinitionToNetworkDefinition(fund, e_nnd);
			addFunctionalUnitTerminalsToNetworkNode(funnd, e_nnd);
		}
		return fund;
	}
	
	public void addFunctionalUnitTerminalsToNetworkNode(Functional_unit_network_node_definition funnd, 
			ENetwork_node_definition e_nnd) throws SdaiException
	{
		AComponent_functional_terminal a_cft = getFunctionalUnitTerminalsForNetworkNodeDefinition(e_nnd);
		
		SdaiIterator it = a_cft.createIterator();
		while (it.next()) {
			EComponent_functional_terminal e_cft = (EComponent_functional_terminal) a_cft.getCurrentMemberEntity(it);
			addFunctionalUnitTerminalToNetworkNode(funnd, e_cft);
		}
	}
	
	public EComponent_functional_unit getFunctionalUnitOfTerminal(EComponent_functional_terminal e_cft) throws SdaiException
	{
		EProduct_definition_shape e_pds = e_cft.getOf_shape(null);
		return (EComponent_functional_unit) e_pds.getDefinition(null);
	}
	
	public EFunctional_unit getUsageView(EComponent_functional_unit e_cfu) throws SdaiException
	{
		return ops.fu_relatedTo_cfu_through_pdr(e_cfu, "instantiated functional unit");
	}
	
	public Functional_unit getFunctional_unit(EComponent_functional_unit e_cfu) throws SdaiException
	{
		if (entityToFunctionalObject.containsKey(e_cfu))
			return (Functional_unit) entityToFunctionalObject.get(e_cfu);
		
		String id = e_cfu.getId(null);
		String description = e_cfu.getDescription(null);
		
		EFunctional_unit e_fuuv = getUsageView(e_cfu);
		EFunctional_unit e_fund =  getFunctionalUnitNetworkDefinitionFromUsageView(e_fuuv);
		
			
		Functional_unit_usage_view fuuv = getFunctionalUnitUsageView(e_fuuv);
		Functional_unit fu = new Functional_unit(id, description, fuuv);
		
		if (e_fund != null)
		{
			Functional_unit_network_definition fund = getFunctionalUnitNetworkDefinition(e_fund);
			fund.setFUUV(fuuv);
		}
		
		entityToFunctionalObject.put(e_cfu, fu);
		
		fu.addParams(allParameterAssignmentForComponentFunctionalUnit(e_cfu));
		return fu;
	}
	
	public void addFunctionalUnitTerminalToNetworkNode(Functional_unit_network_node_definition funnd, 
			EComponent_functional_terminal e_cft) throws SdaiException
	{
		 
		EFunctional_unit_terminal_definition e_futd = getFunctionalUnitTerminalDefinition(e_cft);
		
		
		EComponent_functional_unit e_cfu = getFunctionalUnitOfTerminal(e_cft);
		Functional_unit fu = getFunctional_unit(e_cfu);
		
		Scalar_terminal_definition std = addTerminalDefinitionToUsageView(fu.getFUUV(), e_futd);
		
		Functional_unit_terminal fut = null;
		if (entityToFunctionalObject.containsKey(e_cft))
			fut = (Functional_unit_terminal) entityToFunctionalObject.get(e_cft);
		else
		{
			fut = new Functional_unit_terminal(std, fu);
			entityToFunctionalObject.put(e_cft, fut);
		}
		
		funnd.addTerminal(fut);
	}

	public EFunctional_unit_terminal_definition getFunctionalUnitTerminalDefinition(EComponent_functional_terminal e_cft) throws SdaiException
	{
		return ops.futd_relatedTo_cft_through_sar(e_cft, "instantiated terminal");
	}
	
	public Functional_unit_usage_view getFunctionalUnitUsageView(EFunctional_unit e_fuuv) throws SdaiException
	{
		if (entityToFunctionalObject.containsKey(e_fuuv))
			return (Functional_unit_usage_view) entityToFunctionalObject.get(e_fuuv);
		
		EProduct_definition_formation e_pdf = e_fuuv.getFormation(null);
		Functional_unit_usage_view fuuv = new Functional_unit_usage_view(getFunctionalProductVersion(e_pdf));
		entityToFunctionalObject.put(e_fuuv, fuuv);
		
		AFunctional_unit_terminal_definition a_futd = getTerminalsOfUsageView(e_fuuv);
		SdaiIterator it = a_futd.createIterator();
		while (it.next()) {
			EFunctional_unit_terminal_definition e_futd = (EFunctional_unit_terminal_definition) a_futd.getCurrentMemberEntity(it);
			addTerminalDefinitionToUsageView(fuuv, e_futd);
		}
		return fuuv;
	}
	
	/**
	 * given a Functional_unit_definition (either Functional_unit_usage_view or Functional_unit_network_definition)
	 * @param e_fu
	 * @return
	 * @throws SdaiException 
	 */
	public FunctionalProductVersion getFunctionalProductVersion(EProduct_definition_formation e_pdf) throws SdaiException
	{
		if (entityToFunctionalObject.containsKey(e_pdf))
			return (FunctionalProductVersion) entityToFunctionalObject.get(e_pdf);
		
		EProduct e_p = e_pdf.getOf_product(null);
		FunctionalProductVersion fpv = new FunctionalProductVersion(e_p.getId(null), e_p.getName(null), e_pdf.getId(null));
		entityToFunctionalObject.put(e_pdf, fpv);
		
		fpv.addParams(paramQ.allParameterAssignmentForProduct(e_p));
		return fpv;
	}
	
	public AParameter_assignment parameterAssignmentForComponentFunctionalUnit(EComponent_functional_unit e_cfu) throws SdaiException
	{
		AParameter_assignment a_pa = new AParameter_assignment();
		AProperty_definition a_pd = ops.All_pd_referencing_cfu(e_cfu);
		SdaiIterator it = a_pd.createIterator();
		while (it.next())
		{
			EProperty_definition e_pd = (EProperty_definition) a_pd.getCurrentMember(it);
			EParameter_assignment e_pa = ops.pa_relatedTo_pd_through_pdr(e_pd);
			if (e_pa != null)
				a_pa.addUnordered(e_pa);
		}
		return a_pa;
	}
	
	public Set<ParameterAssignment> allParameterAssignmentForComponentFunctionalUnit(EComponent_functional_unit e_cfu) throws SdaiException
	{
		AParameter_assignment a_pa = parameterAssignmentForComponentFunctionalUnit(e_cfu);
		return paramQ.allParameterAssignment(a_pa);
	}
}
