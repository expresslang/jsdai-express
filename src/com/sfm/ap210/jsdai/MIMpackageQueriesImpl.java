package com.sfm.ap210.jsdai;

import java.util.HashSet;
import java.util.Set;

import com.sfm.ap210.jsdai.param.Param;
import com.sfm.ap210.jsdai.MIMparamQueries;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.*;
import jsdai.lang.EEntity;
import jsdai.lang.SdaiException;
import jsdai.lang.SdaiIterator;

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
	
}
