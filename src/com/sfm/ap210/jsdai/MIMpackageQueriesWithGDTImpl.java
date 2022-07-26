package com.sfm.ap210.jsdai;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

import com.sfm.ap210.jsdai.Length.LengthUnit;
import com.sfm.ap210.jsdai.ToleranceLengthValue.DimensionModifier;
import com.sfm.ap210.jsdai.gdt.CenterPlaneToleranceImpl;
import com.sfm.ap210.jsdai.gdt.CompositeDimensionalSize;
import com.sfm.ap210.jsdai.gdt.GeometricToleranceImpl;
import com.sfm.ap210.jsdai.gdt.ToleranceFeatureImpl;
import com.sfm.ap210.jsdai.gdt.CompositeGroupToleranceImpl;
import com.sfm.ap210.jsdai.gdt.CompositeTolerance;
import com.sfm.ap210.jsdai.gdt.Datum;
import com.sfm.ap210.jsdai.gdt.DimensionalLocation;
import com.sfm.ap210.jsdai.gdt.DimensionalSize;
import com.sfm.ap210.jsdai.gdt.FeatureOfSizeToleranceImpl;
import com.sfm.ap210.jsdai.gdt.GeometricTolerance;
import com.sfm.ap210.jsdai.gdt.GeometricTolerance.DatumReference;
import com.sfm.ap210.jsdai.gdt.GeometricTolerance.DatumReferenceModifier;
import com.sfm.ap210.jsdai.gdt.GeometricTolerance.ToleranceModifier;
import com.sfm.ap210.jsdai.gdt.GeometricTolerance.ToleranceType;
import com.sfm.ap210.jsdai.gdt.ToleranceFeature;
import com.sfm.ap210.jsdai.gdt.ToleranceFeature.FeatureType;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.*;
import jsdai.lang.AEntity;
import jsdai.lang.EEntity;
import jsdai.lang.SdaiException;
import jsdai.lang.SdaiIterator;

public class MIMpackageQueriesWithGDTImpl extends MIMpackageQueriesImpl implements MIMpackageQueriesWithGDT {

	public MIMpackageQueriesWithGDTImpl(MIMqueries mimQ, MIMops ops,
			MIMparamQueries paramQ) throws SdaiException {
		super(mimQ, ops, paramQ);
	}

	/**
	 * returns all associated shape aspects related to the given face through a 
	 */
	public AShape_aspect getAssociatedShapeAspectsForFace(EAdvanced_face e_af) throws SdaiException
	{
		return ops.All_sa_relatedTo_af_through_iiru(e_af);
		/*
		AItem_identified_representation_usage a_iiru = ops.All_iiru_referencing_af(e_af);
		AShape_aspect a_sa = new AShape_aspect(); 
		
		SdaiIterator it_iiru = a_iiru.createIterator();
		EItem_identified_representation_usage e_iiru = null;
		while (it_iiru.next())
		{
			e_iiru = a_iiru.getCurrentMember(it_iiru);
			EEntity e_e = e_iiru.getDefinition(null);
			if (e_e.isKindOf(EShape_aspect.class))
				a_sa.addUnordered((EAdvanced_face) e_e);
		}
		return a_sa;
		*/
	}
	
	public EPackage_terminal getAssociatedTerminalForFace(EAdvanced_face e_af) throws SdaiException
	{
		return ops.pt_relatedTo_af_through_iiru(e_af);
		/*
		AItem_identified_representation_usage a_iiru = ops.All_iiru_referencing_af(e_af);
		AShape_aspect a_sa = new AShape_aspect(); 
		
		SdaiIterator it_iiru = a_iiru.createIterator();
		EItem_identified_representation_usage e_iiru = null;
		while (it_iiru.next())
		{
			e_iiru = a_iiru.getCurrentMember(it_iiru);
			EEntity e_e = e_iiru.getDefinition(null);
			if (e_e.isKindOf(EPackage_terminal.class))
				return (EPackage_terminal) e_e;
		}
		return null;
		*/
	}
	
	public EPackage_body getAssociatedBodyForFace(EAdvanced_face e_af) throws SdaiException
	{
		return ops.pb_relatedTo_af_through_iiru(e_af);
		/*
		AItem_identified_representation_usage a_iiru = ops.All_iiru_referencing_af(e_af);
		AShape_aspect a_sa = new AShape_aspect(); 
		
		SdaiIterator it_iiru = a_iiru.createIterator();
		EItem_identified_representation_usage e_iiru = null;
		while (it_iiru.next())
		{
			e_iiru = a_iiru.getCurrentMember(it_iiru);
			EEntity e_e = e_iiru.getDefinition(null);
			if (e_e.isKindOf(EPackage_body.class))
				return (EPackage_body) e_e;
		}
		return null;
		*/
	}
	
	public AAdvanced_face getFacesOfShapeAspect(EShape_aspect e_sa)
			throws SdaiException {
		AItem_identified_representation_usage a_iiru = ops.All_iiru_referencing_sa(e_sa);
		
		AAdvanced_face a_af = new AAdvanced_face(); 
			
		SdaiIterator it_iiru = a_iiru.createIterator();
		EItem_identified_representation_usage e_iiru = null;
		while (it_iiru.next())
		{
			e_iiru = a_iiru.getCurrentMember(it_iiru);
			ERepresentation_item e_ri = e_iiru.getIdentified_item(null);
			if (e_ri.isKindOf(EAdvanced_face.class))
				a_af.addUnordered((EAdvanced_face) e_ri);
		}
		return a_af;
	}
	
	public EAdvanced_face getFaceOfShapeAspect(EShape_aspect e_sa) throws SdaiException 
	{
		EItem_identified_representation_usage e_iiru = ops.iiru_referencing_sa(e_sa);
		ERepresentation_item e_ri = e_iiru.getIdentified_item(null);
		if (e_ri.isKindOf(EAdvanced_face.class))
			return (EAdvanced_face) e_ri;

		return null;
	}
	
	public EPlane getPlaneOfShapeAspect(EShape_aspect e_sa) throws SdaiException 
	{
		EItem_identified_representation_usage e_iiru = ops.iiru_referencing_sa(e_sa);
		ERepresentation_item e_ri = e_iiru.getIdentified_item(null);
		if (e_ri.isKindOf(EPlane.class))
			return (EPlane) e_ri;

		return null;
	}
	
	FaceReference faceReference(EChain_based_geometric_item_specific_usage e_cbgisu) throws SdaiException
	{
		AChained_representation_link a_crl = e_cbgisu.getUndirected_link(null);
		EAdvanced_face e_af = null;
		
		if (a_crl.getMemberCount() != 1)
			throw new IllegalArgumentException("Unsupported reference: "+e_cbgisu);
		
		ERepresentation_item e_ri = e_cbgisu.getIdentified_item(null);
		if (e_ri.isKindOf(EAdvanced_face.class))
			e_af = (EAdvanced_face) e_ri; 
		else
			throw new IllegalArgumentException("Unsupported geometric_representation_item: "+e_cbgisu);
		
		EEntity e_e = a_crl.getByIndexEntity(1);
		if (!(e_e.isKindOf(EUsage_concept_usage_relationship.class)))
			throw new IllegalArgumentException("Unsupported reference: "+e_cbgisu);
		EUsage_concept_usage_relationship e_ucur = (EUsage_concept_usage_relationship) e_e;
		EEntity e_e2 = e_ucur.getDefinition(null);
	
		if (e_e2.isKindOf(EPackage_terminal.class))
			return new ChainBasedTerminalFaceReference(e_cbgisu, e_af, (EPackage_terminal) e_e2);
		else if (e_e2.isKindOf(EPackage_body.class))
			return new ChainBasedBodyFaceReference(e_cbgisu, e_af, (EPackage_body) e_e2);
		else
			return new ChainBasedPackageFaceReference(e_cbgisu, e_af);
	}
	
	FaceReference faceReference(EGeometric_item_specific_usage e_gisu) throws SdaiException
	{
		EAdvanced_face e_af = null;
		if (e_gisu.isKindOf(EChain_based_geometric_item_specific_usage.class))
			throw new IllegalArgumentException("Chain-based reference cannot be represented by PackageFaceReference: "+e_gisu);

		ERepresentation_item e_ri = e_gisu.getIdentified_item(null);
		if (e_ri.isKindOf(EAdvanced_face.class))
			e_af = (EAdvanced_face) e_ri; 
		else
			throw new IllegalArgumentException("Unsupported geometric_representation_item: "+e_gisu);
		
		EPackage_body e_body = ops.pb_relatedTo_af_through_iiru(e_af);
		EPackage_terminal e_terminal = ops.pt_relatedTo_af_through_iiru(e_af);
		
		if (e_body != null)
			return new SingleSolidBodyFaceReference(e_gisu, e_af, e_body);
		else if (e_terminal != null)
			return new SingleSolidTerminalFaceReference(e_gisu, e_af, e_terminal);
		else
			return new SingleSolidPackageFaceReference(e_gisu, e_af);
	}
	
	
	public SortedSet<FaceReference> getFaceReferencesOfShapeAspect(EShape_aspect e_sa) throws SdaiException
	{
		AItem_identified_representation_usage a_iiru = ops.All_iiru_referencing_sa(e_sa);
		
		SortedSet<FaceReference> references = new TreeSet<FaceReference>(); 
			
		SdaiIterator it_iiru = a_iiru.createIterator();
		EItem_identified_representation_usage e_iiru = null;
		while (it_iiru.next())
		{
			e_iiru = a_iiru.getCurrentMember(it_iiru);
			if (e_iiru.isKindOf(EChain_based_geometric_item_specific_usage.class))
				try
				{ 
					references.add(faceReference((EChain_based_geometric_item_specific_usage) e_iiru));
				}
				catch (IllegalArgumentException e)
				{
					; //System.out.println("Ignoring... "+e.getMessage());
				}
			else if (e_iiru.isKindOf(EGeometric_item_specific_usage.class))
			{
				try
				{ 
					references.add(faceReference((EGeometric_item_specific_usage) e_iiru));
				}
				catch (IllegalArgumentException e)
				{
					; // System.out.println("Ignoring... "+e.getMessage());
				}
			}
		}
		
		AShape_aspect a_children = getAllComposingShapeAspects(e_sa);
		
		SdaiIterator it = a_children.createIterator();
		while (it.next())
		{
			EShape_aspect e_child = a_children.getCurrentMember(it);
			Set<FaceReference> childFaceRefs = getFaceReferencesOfShapeAspect(e_child);
			references.addAll(childFaceRefs);
		}
		return references;
	}
	
	public AShape_aspect getDerivedFromShapeAspects(EDerived_shape_aspect e_dsa) throws SdaiException
	{
		return ops.All_sa_relatedTo_dsa_through_sadr(e_dsa);
	}
	
	/**
	 * returns all composing shape aspects recursively
	 * note, cannot recursively follow all shape_aspect_relationship because some (such as dimensional_location)
	 * do not represent a composing relationship
	 * @param e_sa
	 * @return
	 * @throws SdaiException
	 */
	public AShape_aspect getAllComposingShapeAspects(EShape_aspect e_sa) throws SdaiException
	{
		AShape_aspect a_sa_out = new AShape_aspect();
		
		AShape_aspect a_children = null;
		
		if (e_sa.isKindOf(EDerived_shape_aspect.class))
			a_children = ops.All_sa_relatedTo_dsa_through_sadr((EDerived_shape_aspect)e_sa);
		else if (e_sa.isKindOf(EComposite_group_shape_aspect.class))
			a_children = ops.All_sa_relatedTo_sa_through_sar(e_sa);
		else if (e_sa.isKindOf(EComposite_unit_shape_aspect.class))
			a_children = ops.All_sa_relatedTo_sa_through_sar(e_sa);
		else if (e_sa.isKindOf(ESymmetric_shape_aspect.class))
			a_children = ops.All_sa_relatedTo_sa_through_sar(e_sa);
		else if (e_sa.isKindOf(EDimensional_size.class))
			a_children = ops.All_sa_relatedTo_sa_through_sar(e_sa);
		else if (e_sa.isKindOf(EDatum_feature.class))
			a_children = ops.All_sa_relatedTo_sa_through_sar(e_sa);
		
		if (a_children == null)
			return a_sa_out;
		
		SdaiIterator it = a_children.createIterator();
		while (it.next())
		{
			EShape_aspect e_child = a_children.getCurrentMember(it);
			a_sa_out.addUnordered(e_child);
			AShape_aspect a_grandchildren = getAllComposingShapeAspects(e_child);
			SdaiIterator iti = a_grandchildren.createIterator();
			while (iti.next())
			{
				EShape_aspect e_grandchild = a_grandchildren.getCurrentMember(iti);
				a_sa_out.addUnordered(e_grandchild);
			}
		}
		return a_sa_out;
		//
		/*
		ops.All_sa_relatedTo_sa_through_sar(e_sa);
		EItem_identified_representation_usage e_iiru = ops.iiru_referencing_sa(e_sa);
		if (e_iiru != null)
			a_sa.addUnordered(e_sa);
		*/
	}
	
	public ADatum getDatumsInModel() throws SdaiException
	{
		return ops.All_d_inModel();
	}
	
	public ADimensional_size getAllDimensionalSizeInModel() throws SdaiException
	{
		return ops.All_ds_inModel();
	}
	
	public ADimensional_location getAllDimensionalLocationInModel() throws SdaiException
	{
		return ops.All_dl_inModel();
	}
	
	public AGeometric_tolerance getAllGeometricToleranceInModel() throws SdaiException
	{
		return ops.All_gt_inModel();
	}

	void addDatumReference(GeometricTolerance tol, EDatum_reference_compartment e_drc) throws SdaiException
	{
		EEntity e_e = e_drc.getBase(null);
		if (e_e.isKindOf(EDatum.class))
		{
			EDatum e_d = (EDatum) e_e;
			DatumReferenceModifier m = null;
			if (e_drc.testModifiers(null))
			{
				ADatum_reference_modifier a_drc = e_drc.getModifiers(null);
				if (a_drc.getMemberCount() != 1)
					throw new IllegalArgumentException("Unsupported datum reference modifier: "+a_drc);
				
				//EEntity e_e2 = a_drc.getByIndexEntity(1);
				//if (e_e2.isKindOf(ESimple_datum_reference_modifier.class))
				{
					int sdrmValue = a_drc.getByIndexInt(1);
					m = DatumReferenceModifier.fromMim(sdrmValue);
				}
			}
			tol.AddDatum(e_d, m);
		}
		else throw new IllegalArgumentException("Unsupported datum reference type: "+e_drc.getBase(null));
	}
	
	void addDatums(GeometricTolerance tol, EDatum_system e_ds) throws SdaiException
	{
		List<DatumReference> datumRefs = new Vector<DatumReference>();
		ADatum_reference_compartment a_drc = e_ds.getConstituents(null);
		SdaiIterator it = a_drc.createIterator();
		while (it.next())
		{
			EDatum_reference_compartment e_drc = (EDatum_reference_compartment) a_drc.getCurrentMember(it);
			addDatumReference(tol, e_drc);
		}
	}
	
	ToleranceType getType(EGeometric_tolerance e_tol) throws SdaiException
	{
		if (e_tol.isKindOf(EPosition_tolerance.class))
			return ToleranceType.POSITION;
		else if (e_tol.isKindOf(EFlatness_tolerance.class))
			return ToleranceType.FLATNESS;
		else if (e_tol.isKindOf(EParallelism_tolerance.class))
			return ToleranceType.PARALLELISM;
		else if (e_tol.isKindOf(ECylindricity_tolerance.class))
			return ToleranceType.CYLINDRICITY;
		else if (e_tol.isKindOf(EStraightness_tolerance.class))
			return ToleranceType.STRAIGHTNESS;
		else if (e_tol.isKindOf(ESurface_profile_tolerance.class))
			return ToleranceType.SURFACEPROFILE;
		else if (e_tol.isKindOf(ERoundness_tolerance.class))
			return ToleranceType.ROUNDNESS;
		else
			throw new IllegalArgumentException("Unsupported tolerance type: "+e_tol);
	}
	
	FeatureType getFeatureType(EShape_aspect e_sa) throws SdaiException
	{
		if (e_sa == null)
			throw new IllegalArgumentException("Unexpected null shape aspect.");
		if (e_sa.isKindOf(EComposite_group_shape_aspect.class))
		{
			EShape_aspect e_child = ops.sa_relatedTo_cgsa_through_sar((EComposite_group_shape_aspect)e_sa);
			return getFeatureType(e_child);
		}
		if (e_sa.isKindOf(ETangent.class))
		{
			EPlane e_plane = getPlaneOfShapeAspect(e_sa);
			if (e_plane != null)
				return FeatureType.TANGENTPLANE;
			else
				return FeatureType.TANGENT;
		}
		if (e_sa.isKindOf(EGeometric_alignment.class))
		{
			EPlane e_plane = getPlaneOfShapeAspect(e_sa);
			if (e_plane != null)
				return FeatureType.ALIGNMENTPLANE;
			else
				return FeatureType.ALIGNMENT;
		}
		if (e_sa.isKindOf(EComposite_group_shape_aspect.class))
		{
			EShape_aspect e_child = ops.sa_relatedTo_cgsa_through_sar((EComposite_group_shape_aspect)e_sa);
			return getFeatureType(e_child);
		}
		if (e_sa.isKindOf(ECentre_of_symmetry.class))
		{
			EPlane e_plane = getPlaneOfShapeAspect(e_sa);
			if (e_plane != null)
				return FeatureType.CENTERPLANE;
			else
				return FeatureType.CENTREOFSYMMETRY;
		}
		else if (e_sa.isKindOf(ESymmetric_shape_aspect.class))
			return FeatureType.FEATUREOFSIZE;
		else if (e_sa.isKindOf(EDimensional_size.class))
			return FeatureType.FEATUREOFSIZE;
		else
			return FeatureType.GENERIC;
			
	}
	public GeometricTolerance readGeometricTolerance(EGeometric_tolerance e_tol) throws SdaiException
	{
		String n = e_tol.getName(null);
		Length l = duc.lengthMeasureWithUnit(e_tol.getMagnitude(null));
		ToleranceModifier m = null;
		ToleranceType t = getType(e_tol);
		EShape_aspect e_sa = (EShape_aspect) e_tol.getToleranced_shape_aspect(null);
		GeometricTolerance tol = null;
		if (e_sa.isKindOf(ECentre_of_symmetry.class))
			tol = new CenterPlaneToleranceImpl(n, l, m, (ECentre_of_symmetry)e_sa, t);
		else if (e_sa.isKindOf(EComposite_group_shape_aspect.class))
			tol = new CompositeGroupToleranceImpl(n, l, m, (EComposite_group_shape_aspect)e_sa, t, getFeatureType(e_sa));
		else if (e_sa.isKindOf(ESymmetric_shape_aspect.class))
			tol = new FeatureOfSizeToleranceImpl(n, l, m, (ESymmetric_shape_aspect)e_sa, t);
		else
		{
			tol = new GeometricToleranceImpl(n, l, m, e_sa, t, getFeatureType(e_sa));
			//throw new IllegalArgumentException("Unsupported tolerance: "+e_tol);
		}
		if (e_tol.isKindOf(EModified_geometric_tolerance.class))
		{
			EModified_geometric_tolerance e_mgt = (EModified_geometric_tolerance) e_tol;
			m = ToleranceModifier.fromMim(e_mgt.getModifier(null));
		}
		if (e_tol.isKindOf(EGeometric_tolerance_with_datum_reference.class))
		{
			EGeometric_tolerance_with_datum_reference e_mtwdr = (EGeometric_tolerance_with_datum_reference) e_tol;
			AEntity a_e = e_mtwdr.getDatum_system(null);
			if (a_e.getMemberCount() != 1)
				throw new IllegalArgumentException("Unsupported datum system: "+a_e);
			EEntity e_e = a_e.getByIndexEntity(1);
			if (e_e.isKindOf(EDatum_system.class))
			{
				addDatums(tol, (EDatum_system) e_e);
			}
		}
		
		Set<FaceReference> faces = getFaceReferencesOfShapeAspect(e_sa);
		for (FaceReference face : faces)
			tol.addFaceRef(face);
		
		if (tol instanceof CompositeGroupToleranceImpl)
		{
			Set<ToleranceFeatureImpl> features = getFeaturesForCompositeShapeAspect((EComposite_group_shape_aspect) e_sa);
			((CompositeGroupToleranceImpl)tol).addFeatures(features);
		}
		
		return tol;
	}
	
	public DimensionalSize readDimensionalSize(EDimensional_size e_ds) throws SdaiException
	{
		ToleranceLengthValue v = getValueOfDimensionalSize(e_ds);
		EShape_aspect e_applies_to_sa = (EShape_aspect) e_ds.getApplies_to(null);
		FeatureType t = getFeatureType(e_applies_to_sa);
		
		AShape_aspect a_composing_sa = getAllComposingShapeAspects(e_applies_to_sa);
		Set<FaceReference> faceRefs = getFaceReferencesOfShapeAspect(e_applies_to_sa);
		
		DimensionalSize ds;
		if (e_applies_to_sa.isKindOf(EComposite_group_shape_aspect.class))
			ds = new CompositeDimensionalSize(v, e_ds, (EComposite_group_shape_aspect) e_applies_to_sa, t);
		else
			ds = new DimensionalSize(v, e_ds, e_applies_to_sa, t);
		for (FaceReference face : faceRefs)
		{
			ds.addFace(face);
		}
		
		if (ds instanceof CompositeDimensionalSize)
		{
			Set<ToleranceFeatureImpl> features = getFeaturesForCompositeShapeAspect((EComposite_group_shape_aspect) e_applies_to_sa);
			((CompositeDimensionalSize)ds).addFeatures(features);
		}
		
		return ds;
	}
	
	public DimensionalLocation readDimensionalLocation(EDimensional_location e_dl) throws SdaiException
	{
		ToleranceLengthValue v = getValueOfDimensionalLocation(e_dl);
		EShape_aspect e_sa1 = e_dl.getRelating_shape_aspect(null);
		EShape_aspect e_sa2 = e_dl.getRelated_shape_aspect(null);
		ToleranceFeature f1 = getFeatureForShapeAspect(e_sa1);
		ToleranceFeature f2 = getFeatureForShapeAspect(e_sa2);
		DimensionalLocation dl = new DimensionalLocation(e_dl, v, f1, f2);
		return dl;
	}
	
	ToleranceFeature getFeatureForShapeAspect(EShape_aspect e_sa) throws SdaiException
	{
		Set<FaceReference> faces = getFaceReferencesOfShapeAspect(e_sa);
		FeatureType type = getFeatureType(e_sa);
		ToleranceFeatureImpl f = new ToleranceFeatureImpl(type, e_sa, faces);
		return f;
	}
	
	Set<ToleranceFeatureImpl> getFeaturesForCompositeShapeAspect(EComposite_group_shape_aspect e_cgsa) throws SdaiException
	{
		Set<ToleranceFeatureImpl> features = new TreeSet<ToleranceFeatureImpl>();
		AShape_aspect a_children = ops.All_sa_relatedTo_cgsa_through_sar(e_cgsa);
		SdaiIterator it = a_children.createIterator();
		while (it.next())
		{
			EShape_aspect e_child = (EShape_aspect) a_children.getCurrentMember(it);
			Set<FaceReference> faces = getFaceReferencesOfShapeAspect(e_child);
			FeatureType type = getFeatureType(e_child);
			ToleranceFeatureImpl f = new ToleranceFeatureImpl(type, e_child, faces);
			features.add(f);
		}
		return features;
	}
	
	public Datum readDatum(EDatum e_datum) throws SdaiException
	{
		String id = e_datum.getIdentification(null);
		EDatum_feature e_df = ops.df_relatedTo_d_through_sar(e_datum);
		Set<FaceReference> faces = getFaceReferencesOfShapeAspect(e_df);
		Datum d = new Datum(id, getFeatureType(e_df), e_datum, e_df);
		for (FaceReference face : faces)
			d.addFaceRef(face);
		return d;
	}
	
	public ToleranceLengthValue getValueOfDimensionalSize(EDimensional_size e_ds) throws SdaiException
	{
		EShape_dimension_representation e_sdr = ops.sdr_relatedTo_ds_through_dcr(e_ds);
		return getValueOfShapeDimensionRepresentation(e_sdr);
	}
	
	public ToleranceLengthValue getValueOfDimensionalLocation(EDimensional_location e_dl) throws SdaiException
	{
		EShape_dimension_representation e_sdr = ops.sdr_relatedTo_dl_through_dcr(e_dl);
		return getValueOfShapeDimensionRepresentation(e_sdr);
	}
	
	public ToleranceLengthValue.DimensionModifier getDimensionalModifier(ARepresentation_item a_dri) throws SdaiException
	{
		SdaiIterator it = a_dri.createIterator();
		while (it.next())
		{
			ERepresentation_item e_ri = a_dri.getCurrentMember(it);
			if (e_ri.isKindOf(EDescriptive_representation_item.class))
			{
				EDescriptive_representation_item e_dri = (EDescriptive_representation_item) e_ri;
				if (e_dri.getName(null).equals("dimensional note"))
				{
					return DimensionModifier.fromMim(e_dri.getDescription(null));
				}
			}
		}
		return null;
	}
	
	public void addQualifiedLength(ToleranceLengthValue tlv, ERepresentation_item e_ri) throws SdaiException
	{
		if (!(e_ri.isKindOf(ELength_measure_with_unit.class)))
			return;
		
		ToleranceLengthValue.Qualifier q = null;
		if (e_ri.isKindOf(EQualified_representation_item.class))
		{
			EQualified_representation_item e_qri = (EQualified_representation_item) e_ri;
			AValue_qualifier a_vq = e_qri.getQualifiers(null);
			if (a_vq.getMemberCount() != 1)
				throw new IllegalArgumentException("Multiple qualifiers unsupported: "+e_qri);
			EEntity e_e = a_vq.getByIndex(1);
			if (!(e_e.isKindOf(EType_qualifier.class)))
				throw new IllegalArgumentException("Unsupported qualifier: "+e_qri);
			EType_qualifier e_tq = (EType_qualifier) e_e;
			q =  ToleranceLengthValue.Qualifier.fromMim(e_tq.getName(null));
		}
		
		//double length_in_mm = duc.lengthMeasureWithUnitInMM((ELength_measure_with_unit) e_ri);
		//tlv.addQualifiedValue(new Length(length_in_mm, LengthUnit.MM), q);
		Length l = duc.lengthMeasureWithUnit((ELength_measure_with_unit) e_ri);
		tlv.addQualifiedValue(l, q);
	}
	
	public ToleranceLengthValue getValueOfShapeDimensionRepresentation(EShape_dimension_representation e_sdr) throws SdaiException
	{
		String n = e_sdr.getName(null);
		ARepresentation_item a_ri = e_sdr.getItems(null); 
		ToleranceLengthValue.DimensionModifier m = getDimensionalModifier(a_ri);
		ToleranceLengthValue v = new ToleranceLengthValue(n);
		SdaiIterator it = a_ri.createIterator();
		while (it.next())
		{
			ERepresentation_item e_ri = a_ri.getCurrentMember(it);
			addQualifiedLength(v, e_ri);
		}
		if (m != null)
			v.setDimensionModifier(m);
		return v;
	}
}
