package com.sfm.ap210.jsdai;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.AAssembly_component_usage;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.AFootprint_definition;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.AItem_identified_representation_usage;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.APackage_terminal;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.AProduct_definition_relationship;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.AProperty_definition;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.AShape_aspect;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.AShape_aspect_relationship;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.AShape_representation;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.AUsage_concept_usage_relationship;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.CItem_identified_representation_usage;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.CProduct_definition_relationship;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.CProperty_definition;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.CShape_aspect;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.CShape_aspect_relationship;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EAssembly_component_usage;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EComposite_group_shape_aspect;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EComposite_shape_aspect;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EItem_identified_representation_usage;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EPackage;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EPackage_body;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EPackage_terminal;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EPackage_terminal_template_definition;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EPhysical_unit;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EPlacement;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EProduct_definition_relationship;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EProduct_definition_shape;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EProperty_definition;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EProperty_definition_representation;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.ERepresentation;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.ERepresentation_context;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.ERepresentation_item;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.ERepresentation_map;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.ESeating_plane;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EShape_aspect;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EShape_aspect_relationship;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EShape_representation;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EStructured_template;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EThermal_network;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EUsage_concept_usage_relationship;
import jsdai.lang.EEntity;
import jsdai.lang.SdaiException;
import jsdai.lang.SdaiIterator;
import jsdai.lang.SdaiModel;

import com.sfm.ap210.jsdai.param.Param;
import com.sfm.ap210.jsdai.utils.SdaiUtils;

/**
 * An implementation of the MIMpackageQueries interface.
 * @author James Stori, SFM Technology, Inc.
 * @version 1.3
 * 
 */
public class MIMpackageQueriesImpl implements MIMpackageQueries {

	MIMops ops;
	MIMqueries mimQ;
	MIMparamQueries paramQ;
	MIMdimensionalUnitUtils duc;
	
	Set<String> terminalTemplateParams = new HashSet<String>();	
	Set<String> packageParams = new HashSet<String>();	
	
	/**
	 * 
	 * @param mimQ
	 * @param ops
	 * @throws SdaiException
	 */
	public MIMpackageQueriesImpl(MIMqueries mimQ, MIMops ops, MIMparamQueries paramQ) throws SdaiException
	{
		this.ops = ops;
		this.mimQ = mimQ;
		this.paramQ = paramQ;
		this.duc = new MIMdimensionalUnitUtils(ops);
	}
	
	public MIMpackageQueriesImpl(SdaiModel model) throws SdaiException {
		this.ops = new MIMopsImpl(model);
		this.mimQ = new MIMqueriesImpl(model);
		this.paramQ = new MIMparamQueriesImpl(mimQ, ops);
		this.duc = new MIMdimensionalUnitUtils(ops);
	}
	

	public APackage_terminal getTerminalsOfPackage(EPackage e_p) throws SdaiException
	{
		return ops.All_pt_referencing_p(e_p);
	}
	
	public EPackage_terminal getTerminalOfPackageWithSpecifiedName(EPackage e_p, String name) throws SdaiException
	{
		APackage_terminal a_pt = getTerminalsOfPackage(e_p);
		SdaiIterator it_pt = a_pt.createIterator();
		EPackage_terminal e_pt = null;
		while (it_pt.next())
		{
			e_pt = a_pt.getCurrentMember(it_pt);
			String n = e_pt.getName(null);
			if (n.equals(name))
				return e_pt;
		}
		return null;
	}
	
	public EPackage_terminal_template_definition getTemplateOfPackageTerminal(EPackage_terminal e_pt) throws SdaiException
	{
		AProperty_definition a_pd1 = ops.All_pd_referencing_sa(e_pt);
		
		SdaiIterator it = a_pd1.createIterator();
		while (it.next()) 
		{
			EProperty_definition e_pd1 = (EProperty_definition) a_pd1.getCurrentMemberEntity(it);	
			EProperty_definition e_pd2 = ops.pd_relatedTo_pd_through_pdr_2(e_pd1, "definition");
			if (e_pd2 != null)
			{
				EEntity e_e = e_pd2.getDefinition(null);
				if (e_e.isKindOf(EPackage_terminal_template_definition.class))
					return (EPackage_terminal_template_definition) e_e;
			}
		}
		
		return null;
	}
	
	public EShape_representation getShapeRepresentationOfPackageTerminalTemplate(EPackage_terminal_template_definition e_pttd) throws SdaiException
	{
		EProduct_definition_shape e_pds = ops.pds_referencing_co(e_pttd);
		if (e_pds != null)
		{
			EShape_representation e_sr = ops.sr_relatedTo_pds_through_sdr(e_pds);
			return e_sr;
		}
		return null;
	}
	
	public EShape_representation getShapeRepresentationOfPackageTerminal(EPackage_terminal e_pt) throws SdaiException
	{
		EPackage_terminal_template_definition e_pttd = this.getTemplateOfPackageTerminal(e_pt);
		if (e_pttd == null)
			return null;
		
		return this.getShapeRepresentationOfPackageTerminalTemplate(e_pttd);
	}
	
	public EShape_representation get2dDesignShapeRepresentationOfPackage(EPackage p) throws SdaiException
	{
		return getQualifiedShapeRepresentationOfPackageWithSpecifiedPurpose(p, "ppsm", "design");
	}
	
	public EShape_representation get3dDesignShapeRepresentationOfPackage(EPackage p) throws SdaiException
	{
		return getQualifiedShapeRepresentationOfPackageWithSpecifiedPurpose(p, "3d bound volume shape", "design");
	}
	
	public EShape_representation get3dThermalAnalysisInputRepresentation(EPackage e_p) throws SdaiException {
		return getQualifiedShapeRepresentationOfPackageWithSpecifiedPurpose(e_p, "3d bound volume shape", "thermal_analysis_input");
	}
	
	public EShape_representation get3dShapeRepresentationOfPackage(EPackage p) throws SdaiException
	{
		return getQualifiedShapeRepresentationOfPackage(p, "3d bound volume shape");
	}
	
	/**
	 * Applicable for package body or seating plane
	 * @param e_sa
	 * @return the associated shape_representation
	 * @throws SdaiException
	 */
	public EShape_representation getShapeRepresentationOfShapeAspect(EShape_aspect e_sa) throws SdaiException
	{
		AProperty_definition a_pd1 = ops.All_pd_referencing_sa(e_sa);
		
		SdaiIterator it = a_pd1.createIterator();
		while (it.next()) 
		{
			EProperty_definition e_pd = (EProperty_definition) a_pd1.getCurrentMemberEntity(it);	
			EShape_representation e_sr = ops.sr_relatedTo_pd_through_sdr(e_pd);
			if (e_sr != null)
			{
				return e_sr;
			}
		}
		
		return null;
	}
	
	public EShape_representation getQualifiedShapeRepresentationOfPackageWithSpecifiedPurpose(EPackage p, String name, String purpose) throws SdaiException
	{
		AShape_representation a_sr = ops.All_sr_relatedTo_p_through_sdr(p);
		
		SdaiIterator it_sr = a_sr.createIterator();
		while (it_sr.next()) {
			EShape_representation e_sr = (EShape_representation) a_sr.getCurrentMemberEntity(it_sr);
			if (!(e_sr.getName(null).equals(name)))
				it_sr.remove();
		}
		return mimQ.getShapeRepresentationWithSpecifiedPurpose(a_sr, purpose);
	}
	
	public EShape_representation getQualifiedShapeRepresentationOfPackage(EPackage p, String name) throws SdaiException
	{
		AShape_representation a_sr = ops.All_sr_relatedTo_p_through_sdr(p);
		
		SdaiIterator it_sr = a_sr.createIterator();
		while (it_sr.next()) {
			EShape_representation e_sr = (EShape_representation) a_sr.getCurrentMemberEntity(it_sr);
			if (e_sr.getName(null).equals(name))
				return e_sr;
		}
		return null;
	}
	
	public EPackage_body getBodyOfPackage(EPackage e_p) throws SdaiException
	{
		return ops.pb_referencing_p(e_p);
	}
	
	public ESeating_plane getSeatingPlaneOfPackage(EPackage e_p) throws SdaiException
	{
		return ops.sp_referencing_p(e_p);
	}
	
	public EUsage_concept_usage_relationship getUCURofShapeAspect(EShape_aspect e_sa, EShape_representation e_sa_sr, EShape_representation e_placed_sr) throws SdaiException
	{
		AUsage_concept_usage_relationship a_ucur = ops.All_ucur_referencing_sa(e_sa); 
		EUsage_concept_usage_relationship e_ucur = ops.ucur_referencingGiven(a_ucur, e_placed_sr);
		return e_ucur;
	}
	
	public EPlacement getPlacementOfShapeAspect(EShape_aspect e_sa, EShape_representation e_sa_sr, EShape_representation e_placed_sr) throws SdaiException
	{
		AUsage_concept_usage_relationship a_ucur = ops.All_ucur_referencing_sa(e_sa); 
		EUsage_concept_usage_relationship e_ucur = ops.ucur_referencingGiven(a_ucur, e_placed_sr);
		if (e_ucur == null) 
			return null;
		
		ERepresentation_map e_rep_map = e_ucur.getMapping_source(null);
		ERepresentation e_mapped_representation = e_rep_map.getMapped_representation(null);
		
		if (e_mapped_representation != e_sa_sr)
			return null;
		
		ERepresentation_item e_mapping_target = e_ucur.getMapping_target(null);
		
		if (e_mapping_target.isKindOf(EPlacement.class))
			return (EPlacement) e_mapping_target;
		
		return null;
	}
	
	public AFootprint_definition getFootprintsOfPackage(EPackage e_p) throws SdaiException
	{
		return ops.All_fd_relatedTo_p_through_pdr(e_p, "reference package");
	}
	
	
	public AAssembly_component_usage getAllTTLISTforST(EStructured_template e_st) throws SdaiException
	{
		return ops.All_acu_referencing_st(e_st, "tlist");
	}
	
	public EShape_aspect getPartFeatureForTLIST(EAssembly_component_usage e_tlist) throws SdaiException
	{
		AProperty_definition a_pd = ops.All_pd_referencing_acu(e_tlist);
		SdaiIterator it_pd = a_pd.createIterator();
		while (it_pd.next()) 
		{
			EProperty_definition e_pd = (EProperty_definition) a_pd.getCurrentMemberEntity(it_pd);
			EProperty_definition e_pd2 = ops.pd_relatedTo_pd_through_pdr_2(e_pd, "reference feature");
			if (e_pd2 != null)
			{
				EShape_aspect e_sa = (EShape_aspect) e_pd2.getDefinition(null);
				return e_sa;
			}
		}
		return null;
	}
	
	public void addPackageParam(String name)
	{
		packageParams.add(name);
	}
	
	public void addTerminalParam(String name)
	{
		terminalTemplateParams.add(name);
	}
	
	public Set<Param> getParametricAttributesOfPackage(EPackage e_p) throws SdaiException
	{
		Set<Param> set = new HashSet<Param>();
		AProperty_definition a_pd = ops.All_pd_referencing_p(e_p);
		SdaiIterator it_pd = a_pd.createIterator();
		
		while (it_pd.next()) {
			EProperty_definition e_pd = (EProperty_definition) a_pd.getCurrentMemberEntity(it_pd);
			if (packageParams.contains(e_pd.getName(null)))
			{
				Param p = paramQ.parametricAttributeForPropertyDefinition(e_pd);
				if(p!=null)
					set.add(p);
			}
		}
		return set;
	}
	
	public Set<Param> getParametricAttributesOfTerminalTemplate(EPackage_terminal_template_definition e_pttd) throws SdaiException
	{
		
		Set<Param> set = new HashSet<Param>();
		AProperty_definition a_pd = ops.All_pd_referencing_pttd(e_pttd);
		SdaiIterator it_pd = a_pd.createIterator();
		
		while (it_pd.next()) {
			EProperty_definition e_pd = (EProperty_definition) a_pd.getCurrentMemberEntity(it_pd);
			if (terminalTemplateParams.contains(e_pd.getName(null)))
			{
				Param p = paramQ.parametricAttributeForPropertyDefinition(e_pd);
				if(p!=null)
					set.add(p);
			}
		}
		return set;
	}
	
	
	public Set<EShape_aspect> getShapeAspectsOfPhysicalUnit(EPhysical_unit e_p) throws SdaiException {
		Set<EShape_aspect> result = new HashSet<EShape_aspect>();
		AShape_aspect aspects = new AShape_aspect();
		CShape_aspect.usedinOf_shape(null, e_p, null, aspects);
		for(SdaiIterator i = aspects.createIterator(); i.next();) {
			EShape_aspect e_sa = aspects.getCurrentMember(i);
			result.add(e_sa);
		}
		return result;
	}
		
	public Set<EShape_aspect> getShapeAspectsOfPhysicalShapeRepresentation(EPhysical_unit e_p, EShape_representation e_r) throws SdaiException {
		Set<EShape_aspect> results = new HashSet<EShape_aspect>();
		Set<EShape_aspect> aspects = getShapeAspectsOfPhysicalUnit(e_p);
		for(EShape_aspect aspect : aspects) {
			AItem_identified_representation_usage a_iiru = new AItem_identified_representation_usage();
			CItem_identified_representation_usage.usedinDefinition(null, aspect, null, a_iiru);
			for(SdaiIterator i = a_iiru.createIterator(); i.next();) {
				EItem_identified_representation_usage e_iiru = a_iiru.getCurrentMember(i);
				if(e_r.equals(e_iiru.getUsed_representation(null))) {
					results.add(aspect);
					break;
				}
			}
		}
		return results;
	}
	
	private Set<EProperty_definition> getAllPropertyDefinitionsOf(EShape_aspect aspect, Set<EShape_aspect> visited) throws SdaiException {
		Set<EProperty_definition> result = new HashSet<EProperty_definition>();
		if(visited.contains(aspect))
			return result;
		//add the aspect to the visited set to prevent infinite recursion
		//in the case of cycles in the relationship graph
		visited.add(aspect);		
		AProperty_definition defns = new AProperty_definition();
		//get all direct properties
		CProperty_definition.usedinDefinition(null, aspect, null, defns);
		for(SdaiIterator i = defns.createIterator(); i.next();) {
			result.add(defns.getCurrentMember(i));
		}
		//get all shape aspect relationships that group this aspect with a composite aspect
		AShape_aspect_relationship relationships = new AShape_aspect_relationship();
		CShape_aspect_relationship.usedinRelated_shape_aspect(null, aspect, null, relationships);
		for(EShape_aspect group : getCompositeGroupsOfShapeAspect(aspect)) {								
			Set<EProperty_definition> inherited = getAllPropertyDefinitionsOf(group, visited);
			result.addAll(inherited);							
		}
		return result;
	}
	
	public Set<EProperty_definition> getAllPropertyDefinitionsOf(EShape_aspect aspect) throws SdaiException {
		return getAllPropertyDefinitionsOf(aspect, new HashSet<EShape_aspect>());
	}
	
	public Set<EShape_aspect> getMembersOfCompositeShapeAspect(EComposite_shape_aspect composite) throws SdaiException {
		Set<EShape_aspect> result = new HashSet<EShape_aspect>();		
		AShape_aspect_relationship relations = composite.getComponent_relationships(null, null);		
		for(SdaiIterator i = relations.createIterator(); i.next();) {
			EShape_aspect_relationship relation = relations.getCurrentMember(i);
			result.add(relation.getRelated_shape_aspect(null));
		}
		return result;
	}
	
	public Set<EComposite_group_shape_aspect> getCompositeGroupsOfShapeAspect(EShape_aspect e_sa) throws SdaiException {
		Set<EComposite_group_shape_aspect> composites = new HashSet<EComposite_group_shape_aspect>();
		
		AShape_aspect_relationship relations = new AShape_aspect_relationship();
		CShape_aspect_relationship.usedinRelated_shape_aspect(null, e_sa, null, relations);		
		for(SdaiIterator i = relations.createIterator(); i.next();) {
			EShape_aspect_relationship relation = relations.getCurrentMember(i);
			EShape_aspect other = relation.getRelating_shape_aspect(null);
			if(other.isKindOf(EComposite_group_shape_aspect.class)) {
				composites.add((EComposite_group_shape_aspect) other);
			}
		}
		return composites;
	}
	
	public EThermal_network getThermalNetworkOfPackage(EPackage e_p) throws SdaiException {
		AProduct_definition_relationship a_pdrs = new AProduct_definition_relationship();
		CProduct_definition_relationship.usedinRelated_product_definition(null, e_p, null, a_pdrs);
		for(SdaiIterator i=a_pdrs.createIterator(); i.next();) {			
			EProduct_definition_relationship e_pdr = a_pdrs.getCurrentMember(i);
			if(e_pdr.testName(null) && "thermal model assignment".equals(e_pdr.getName(null))) {
				return (EThermal_network) e_pdr.getRelating_product_definition(null);
			}						
		}
		return null;
	}
	
	public EProduct_definition_relationship assignThermalNetworkToPackage(EPackage e_pkg, EThermal_network e_net) throws SdaiException
	{
		SdaiModel model = e_pkg.findEntityInstanceSdaiModel();
		EProduct_definition_relationship e_pdr = 
			SdaiUtils.create(EProduct_definition_relationship.class, model);		
		e_pdr.setRelated_product_definition(null, e_pkg);
		e_pdr.setRelating_product_definition(null, e_net);
		e_pdr.setName(null, "thermal model assignment");
		return e_pdr;
	}
		
	public EProperty_definition addShapeAspectProperty(EShape_aspect aspect, String property, ERepresentation value) throws SdaiException {		
		SdaiModel model = aspect.findEntityInstanceSdaiModel();
		//create property definition
		EProperty_definition pdef =
			(EProperty_definition) model.createEntityInstance(EProperty_definition.class);			
		pdef.setName(null, property);
		pdef.setDefinition(null, aspect);
												
		//connect value representation to property definition
		EProperty_definition_representation pdr = 
			(EProperty_definition_representation) model.createEntityInstance(EProperty_definition_representation.class);			
		pdr.setDefinition(null, pdef);
		pdr.setUsed_representation(null, value);
		
		return pdef;
	}
	
	public EShape_aspect_relationship addShapeAspectToComposite(EShape_aspect member, EComposite_shape_aspect composite) throws SdaiException {
		SdaiModel model = member.findEntityInstanceSdaiModel();
		EShape_aspect_relationship relation = 
			(EShape_aspect_relationship)model.createEntityInstance(EShape_aspect_relationship.class);
		relation.setRelated_shape_aspect(null, member);
		relation.setRelating_shape_aspect(null, composite);
		return relation;
	}	
	
	public List<Param> getParametersOfShapeAspect(EShape_aspect aspect) throws SdaiException {
		List<Param> parameters = new ArrayList<Param>();
		for(EProperty_definition pdef : getAllPropertyDefinitionsOf(aspect)) {
			Param p = paramQ.parametricAttributeForPropertyDefinition(pdef);
			if(p != null) {
				parameters.add(p);
			}
		}
		return parameters;
	}
	
	public List<Param> getParametersOfShapeAspectInContext(EShape_aspect aspect, ERepresentation_context context) throws SdaiException {
		List<Param> parameters = new ArrayList<Param>();
		for(EProperty_definition pdef : getAllPropertyDefinitionsOf(aspect)) {
			ERepresentation value = ops.r_relatedTo_pd_through_pdr(pdef);
			if(value == null)
				continue;
			ERepresentation_context vctx = value.getContext_of_items(null);
			if(vctx != context)
				continue;
			Param p = paramQ.parametricAttributeForPropertyDefinition(pdef);	
			if(p == null)
				continue;
			parameters.add(p);
		}
		return parameters;
	}	
}
