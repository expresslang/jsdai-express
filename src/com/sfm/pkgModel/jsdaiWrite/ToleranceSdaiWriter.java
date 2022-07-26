package com.sfm.pkgModel.jsdaiWrite;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.sfm.ap210.jsdai.Length;
import com.sfm.ap210.jsdai.ToleranceLengthValue;
import com.sfm.ap210.jsdai.gdt.DimensionalSize;
import com.sfm.ap210.jsdai.gdt.GeometricTolerance;
import com.sfm.ap210.jsdai.gdt.GeometricTolerance.ToleranceModifier;
import com.sfm.ap210.jsdai.gdt.GeometricTolerance.ToleranceType;
import com.sfm.pkgModel.shape.AxisPlacement3d;
import com.sfm.pkgModel.shape.Vector;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.*;
//import jsdai.SAp242_managed_model_based_3d_engineering_mim_lf.EShape_aspect;

/*
import jsdai.SAp242_managed_model_based_3d_engineering_mim_lf.AAdvanced_face;
import jsdai.SAp242_managed_model_based_3d_engineering_mim_lf.AProduct_definition_shape;
import jsdai.SAp242_managed_model_based_3d_engineering_mim_lf.CCentre_of_symmetry;
import jsdai.SAp242_managed_model_based_3d_engineering_mim_lf.CComposite_group_shape_aspect;
import jsdai.SAp242_managed_model_based_3d_engineering_mim_lf.CComposite_group_shape_aspect$datum_feature;
import jsdai.SAp242_managed_model_based_3d_engineering_mim_lf.CDatum_feature;
import jsdai.SAp242_managed_model_based_3d_engineering_mim_lf.CGeometric_item_specific_usage;
import jsdai.SAp242_managed_model_based_3d_engineering_mim_lf.CGeometric_tolerance;
import jsdai.SAp242_managed_model_based_3d_engineering_mim_lf.CGeometric_tolerance_with_datum_reference;
import jsdai.SAp242_managed_model_based_3d_engineering_mim_lf.CGeometric_tolerance_with_datum_reference$modified_geometric_tolerance$surface_profile_tolerance;
import jsdai.SAp242_managed_model_based_3d_engineering_mim_lf.CGeometric_tolerance_with_datum_reference$position_tolerance;
import jsdai.SAp242_managed_model_based_3d_engineering_mim_lf.CMeasure_representation_item$resistance_measure_with_unit;
import jsdai.SAp242_managed_model_based_3d_engineering_mim_lf.CModified_geometric_tolerance;
import jsdai.SAp242_managed_model_based_3d_engineering_mim_lf.CModified_geometric_tolerance$position_tolerance;
import jsdai.SAp242_managed_model_based_3d_engineering_mim_lf.CPosition_tolerance;
import jsdai.SAp242_managed_model_based_3d_engineering_mim_lf.CShape_aspect;
import jsdai.SAp242_managed_model_based_3d_engineering_mim_lf.CShape_aspect_relationship;
import jsdai.SAp242_managed_model_based_3d_engineering_mim_lf.EAdvanced_face;
import jsdai.SAp242_managed_model_based_3d_engineering_mim_lf.ECentre_of_symmetry;
import jsdai.SAp242_managed_model_based_3d_engineering_mim_lf.EComposite_group_shape_aspect;
import jsdai.SAp242_managed_model_based_3d_engineering_mim_lf.EDatum_feature;
import jsdai.SAp242_managed_model_based_3d_engineering_mim_lf.EGeometric_item_specific_usage;
import jsdai.SAp242_managed_model_based_3d_engineering_mim_lf.EGeometric_tolerance;
import jsdai.SAp242_managed_model_based_3d_engineering_mim_lf.EGeometric_tolerance_with_datum_reference;
import jsdai.SAp242_managed_model_based_3d_engineering_mim_lf.EModified_geometric_tolerance;
import jsdai.SAp242_managed_model_based_3d_engineering_mim_lf.EPosition_tolerance;
import jsdai.SAp242_managed_model_based_3d_engineering_mim_lf.EProduct_definition_shape;
import jsdai.SAp242_managed_model_based_3d_engineering_mim_lf.EShape_aspect;
import jsdai.SAp242_managed_model_based_3d_engineering_mim_lf.EShape_aspect_relationship;
*/

import jsdai.lang.A_double;
import jsdai.lang.EEntity;
import jsdai.lang.SdaiException;
import jsdai.lang.SdaiIterator;
import jsdai.lang.SdaiModel;

/**
 * GD&T annotation writing utilities for AP210 package models
 * @author James Stori
 *
 */
public class ToleranceSdaiWriter {

SdaiModel outModel;
EPackage e_package = null;
EShape_representation e_package_3d_sr = null;
int jsdaiTRUE = 2;
int jsdaiFALSE = 1;
SortedMap<String, EShape_aspect> sa_map = new TreeMap<String, EShape_aspect>();
// singleton unit entities
ELength_unit e_mm_length_unit = null;
ELength_unit e_inch_length_unit = null;
ERepresentation_context e_dimension_context = null;
EConstructive_geometry_representation e_cgr = null;

	public ToleranceSdaiWriter(SdaiModel model, EPackage e_p, EShape_representation e_psr) throws SdaiException
	{
		outModel = model;
		e_package = e_p;
		e_package_3d_sr = e_psr;
	}

	/**
	 * singleton context for dimension representations
	 * @return
	 */
	ERepresentation_context getDimensionContext() throws SdaiException
	{
		if (e_dimension_context == null)
		{
			e_dimension_context = (ERepresentation_context) outModel.createEntityInstance(ERepresentation_context.class);
			e_dimension_context.setContext_identifier(null, "dimension context");
			e_dimension_context.setContext_type(null, "dimension");
		}
		return e_dimension_context;
	}

	public void reportSAs()
	{
		for (String key: getKeys())
		{
			System.out.println(key+" : "+shapeAspectForKey(key));
		}
	}
	
	void addSAtoMap(String key, EShape_aspect e_sa)
	{
		if (sa_map.containsKey(key))
			throw new IllegalArgumentException("Keys must be unique: "+key);
		sa_map.put(key, e_sa);
	}
	
	public Set<String> getKeys()
	{
		return sa_map.keySet();	
	}
	
	public EShape_aspect shapeAspectForKey(String key)
	{
		if (sa_map.containsKey(key))
			return sa_map.get(key);
		else
			throw new IllegalArgumentException("Invalid key: "+key);
	}
	
	public AShape_aspect shapeAspectsForKeys(Set<String> keys) throws SdaiException
	{
		AShape_aspect a_sa = new AShape_aspect();
		for (String key : keys)
		{
			if (sa_map.containsKey(key))
				a_sa.addUnordered(sa_map.get(key));
			else
				throw new IllegalArgumentException("Invalid key: "+key);
		}
		return a_sa;
	}
	
	/**
	 * Instantiate a direction with the scalar values contained in Vector
	 */
	EDirection instantiateDirection(Vector v) throws SdaiException
	{
		EDirection e_d = (EDirection) outModel.createEntityInstance(CDirection.class);
		e_d.setName(null, "");
		A_double a_dr = e_d.createDirection_ratios(null);
		a_dr.addByIndex(1,v.getX());
		a_dr.addByIndex(2,v.getY());
		a_dr.addByIndex(3,v.getZ());
		return e_d;
	}
	
	/**
	 * Instantiate an axis2_placement_3d containing the MIM mapping for the given AxisPlacement
	 */
	EAxis2_placement_3d instantiateAxis2Placement3d(AxisPlacement3d placement) throws SdaiException
	{
		EAxis2_placement_3d e_ap3d = (EAxis2_placement_3d) outModel.createEntityInstance(CAxis2_placement_3d.class);
		e_ap3d.setName(null, "");
		e_ap3d.setLocation(null, instantiateCartesianPoint(placement.getLocation()));
		e_ap3d.setAxis(null, instantiateDirection(placement.getAxis()));
		e_ap3d.setRef_direction(null, instantiateDirection(placement.getRefDirection()));
		return e_ap3d;
	}
	
	/**
	 * Instantiate a cartesian_point with the coordinates contained in Vector
	 */
	ECartesian_point instantiateCartesianPoint(Vector v) throws SdaiException
	{
		ECartesian_point e_cp = (ECartesian_point) outModel.createEntityInstance(CCartesian_point.class);
		e_cp.setName(null, "");
		A_double a_c = e_cp.createCoordinates(null);
		a_c.addByIndex(1,v.getX());
		a_c.addByIndex(2,v.getY());
		a_c.addByIndex(3,v.getZ());
		return e_cp;
	}
	
	/**
	 * only for diagnostic purposes
	 * @param N
	 * @return
	 * @throws SdaiException
	 */
	AAdvanced_face getArbitraryAdvancedFaces(int N) throws SdaiException
	{
		AAdvanced_face a_all_face = (AAdvanced_face) outModel.getExactInstances(EAdvanced_face.class);
		AAdvanced_face a_out_faces = new AAdvanced_face();
		SdaiIterator it_faces = a_all_face.createIterator();
		int i = 0;
		while (it_faces.next())
		{
			i++;
			EAdvanced_face e_face = a_all_face.getCurrentMember(it_faces);
			a_out_faces.addByIndex(i, e_face);
			if (i == N)
				return a_out_faces;
		}
		return null;
	}
	
	EAdvanced_face getAdvancedFaceWithLabel(String label) throws SdaiException
	{
		AAdvanced_face a_face = (AAdvanced_face) outModel.getExactInstances(EAdvanced_face.class);
		SdaiIterator it_faces = a_face.createIterator();
		while (it_faces.next())
		{
			EAdvanced_face e_face = a_face.getCurrentMember(it_faces);
			System.out.println("Iterating: "+e_face);
			if (e_face.getPersistentLabel().equals(label))
			{
				System.out.println("Found: "+e_face);
				return e_face;
				
			}
		}
		System.out.println("Unable to find: "+label);
		return null;
	}
	
	/**
	 * only for testing
	 * @return
	 * @throws SdaiException
	 */
	EProduct_definition_shape getArbitraryPDS() throws SdaiException
	{
		AProduct_definition_shape a_pds = (AProduct_definition_shape) outModel.getExactInstances(EProduct_definition_shape.class);
		SdaiIterator it_pds = a_pds.createIterator();
		while (it_pds.next())
		{
			EProduct_definition_shape e_pds = a_pds.getCurrentMember(it_pds);
				return e_pds;
				
			}
		System.out.println("Unable to find pds");
		return null;
	}
	
	EDatum_feature createSingleFaceDatumFeature(EAdvanced_face e_af) throws SdaiException
	{
		EGeometric_item_specific_usage e_gisu = (EGeometric_item_specific_usage) outModel.createEntityInstance(CGeometric_item_specific_usage.class);
		EDatum_feature e_df = (EDatum_feature) outModel.createEntityInstance(CDatum_feature.class);
		e_df.setOf_shape(null, e_package);
		e_df.setProduct_definitional(null, jsdaiTRUE);
		e_gisu.setIdentified_item(null, e_af);
		e_gisu.setDefinition(null, e_df);
		return e_df;
	}
	
	// from PMI rec prac sect 6.4 - use composite_group_shape_aspect w/ .name = 'multiple elements'
	// sec 6.5 - use datum_feature.
	// here - complex 
	
	EComposite_group_shape_aspect createCompositeGroupMultipleFaceDatumFeature(String des, AAdvanced_face a_af, EShape_representation e_sr) throws SdaiException
	{
		EComposite_group_shape_aspect e_complex_cgsa_df = (EComposite_group_shape_aspect) outModel.createEntityInstance(CComposite_group_shape_aspect$datum_feature.class);
		e_complex_cgsa_df.setOf_shape(null, e_package);
		e_complex_cgsa_df.setName(null, "multiple elements");
		e_complex_cgsa_df.setDescription(null, des);
		e_complex_cgsa_df.setProduct_definitional(null, jsdaiTRUE);
		populateCompositeGroupShapeAspectFromMultipleFaces(e_complex_cgsa_df, a_af, e_sr);
		return e_complex_cgsa_df;
	}
	
	/**
	 * creates single face shape aspects for each given face and relates them to the given shape aspect
	 * the given shape aspect might be a composite_group_shape_aspect, or a derived_shape_aspect (such as centre_of_symmetry)
	 * or other
	 * @param e_cgsa
	 * @param a_af
	 * @throws SdaiException
	 */
	void populateCompositeGroupShapeAspectFromMultipleFaces(EComposite_group_shape_aspect e_cgsa, AAdvanced_face a_af, EShape_representation e_sr) throws SdaiException
	{
		String des = e_cgsa.getDescription();
		SdaiIterator it_af = a_af.createIterator();
		while (it_af.next())
		{
			EAdvanced_face e_af = a_af.getCurrentMember(it_af);
			EShape_aspect e_sa = createSingleFaceShapeAspect(des, e_af, e_sr);
			create_sa_composing_relationship(e_cgsa, e_sa);
		}
	}
	
	/**
	 * A composite_group_shape_aspect is a type of composite_shape_aspect. 
	 * Dimensions and geometric tolerances applied to a composite_group_shape_aspect
	 * apply to each member of the collection individually. In a technical drawing 
	 * this is indicate by indicating the number of members followed by "X" and then
	 * followed by the dimension or geometric tolerance. 
	 * @param e_cgsa
	 * @param a_af
	 * @throws SdaiException
	 */
	public EComposite_group_shape_aspect createCompositeGroupShapeAspect(String key, AShape_aspect a_sa) throws SdaiException
	{
		if (a_sa.getMemberCount() < 2)
			throw new IllegalArgumentException("Composite group shape aspect must be composed of at least 2 elements.");
		EComposite_group_shape_aspect e_cgsa = (EComposite_group_shape_aspect) outModel.createEntityInstance(CComposite_group_shape_aspect.class);
		e_cgsa.setName(null, "multiple elements");
		e_cgsa.setDescription(null, key);
		populateCompositeShapeAspect(e_cgsa, a_sa);
		addSAtoMap(key, e_cgsa);
		return e_cgsa;
	}
	
	public EComposite_unit_shape_aspect createCompositeUnitShapeAspect(String key, AShape_aspect a_sa) throws SdaiException
	{
		EComposite_unit_shape_aspect e_cusa = (EComposite_unit_shape_aspect) outModel.createEntityInstance(CComposite_unit_shape_aspect.class);
		e_cusa.setName(null, "multiple elements");
		e_cusa.setDescription(null, key);
		populateCompositeShapeAspect(e_cusa, a_sa);
		addSAtoMap(key, e_cusa);
		return e_cusa;
	}
	
	void populateCompositeShapeAspect(EComposite_shape_aspect e_csa, AShape_aspect a_sa) throws SdaiException
	{
		e_csa.setOf_shape(null, e_package);
		e_csa.setProduct_definitional(null, jsdaiTRUE);
		SdaiIterator it_sa = a_sa.createIterator();
		while (it_sa.next())
		{
			EShape_aspect e_sa = a_sa.getCurrentMember(it_sa);
			create_sa_composing_relationship(e_csa, e_sa);
		}
	}
	
	int simple_datum_reference_modifier(GeometricTolerance.DatumReferenceModifier mod) throws SdaiException
	{
		if (mod == GeometricTolerance.DatumReferenceModifier.LMR)
			return ESimple_datum_reference_modifier.LEAST_MATERIAL_REQUIREMENT;
		else if (mod == GeometricTolerance.DatumReferenceModifier.MMR)
			return ESimple_datum_reference_modifier.MAXIMUM_MATERIAL_REQUIREMENT;
		else
			throw new IllegalArgumentException("Unsupported value for GeometricTolerance.ToleranceModifier: "+mod);
	}
	
	int limit_condition(GeometricTolerance.ToleranceModifier mod) throws SdaiException
	{
		return mod.toMim();
		/*
		if (mod == GeometricTolerance.ToleranceModifier.LMR)
			return ELimit_condition.LEAST_MATERIAL_CONDITION;
		else if (mod == GeometricTolerance.ToleranceModifier.MMR)
			return ELimit_condition.MAXIMUM_MATERIAL_CONDITION;
		else if (mod == GeometricTolerance.ToleranceModifier.ROFS)
			return ELimit_condition.REGARDLESS_OF_FEATURE_SIZE;
		else
			throw new IllegalArgumentException("Unsupported value for GeometricTolerance.ToleranceModifier: "+mod);
		*/
	}
	
	EDatum_system createDatumSystem(String name, List<GeometricTolerance.DatumReference> refs) throws SdaiException
	{
		EDatum_system e_ds =  (EDatum_system) outModel.createEntityInstance(CDatum_system.class);
		e_ds.setName(null, "datum system of "+name);
		//e_ds.setDescription(null, "datum system of "+name);
		e_ds.setOf_shape(null, e_package);
		e_ds.setProduct_definitional(null, jsdaiFALSE);
		ADatum_reference_compartment a_dfc = e_ds.createConstituents(null);
		SdaiIterator it_drc = a_dfc.createIterator();
		for (GeometricTolerance.DatumReference ref : refs)
		{
			EDatum_reference_compartment e_drc = (EDatum_reference_compartment) outModel.createEntityInstance(EDatum_reference_compartment.class);

			String datumID = ref.getDatum().getIdentification(null);
			// to satisfy uniqueness rule:
			e_drc.setName(null, name + " "+ datumID);
			//per wr2 of general_datum_reference, no description
			//e_drc.setDescription(null, "reference to datum "+datumID);
			e_drc.setOf_shape(null, e_package);
			e_drc.setProduct_definitional(null, jsdaiFALSE);
			e_drc.setBase(null, ref.getDatum());
			if (ref.hasModifier())
			{
				ADatum_reference_modifier a_drm = e_drc.createModifiers(null);
				a_drm.addUnordered(simple_datum_reference_modifier(ref.getModifier()), (ESimple_datum_reference_modifier)null);
			}
			a_dfc.addAfter(it_drc, e_drc);
			it_drc.next();
		}
		return e_ds;
	}
	
	void addDatumSystem(String name, EGeometric_tolerance_with_datum_reference e_gtwdr, List<GeometricTolerance.DatumReference> refs) throws SdaiException
	{
		ADatum_system_or_reference a_dsor = (ADatum_system_or_reference) e_gtwdr.createDatum_system(null);
		EDatum_system e_ds = createDatumSystem(name, refs);
		a_dsor.addUnordered(e_ds);
	}
	
	/**
	 * Return a singleton instance of length_unit containing the MIM mapping for
	 * the given Length.LengthUnit. Current implementation supports only 
	 * mm and inch units.
	 */
	ELength_unit getLengthUnit(Length.LengthUnit u) throws SdaiException
	{
		if (u == Length.LengthUnit.MM)
		{
			if (e_mm_length_unit != null)
				return e_mm_length_unit;
			
			EEntity e_e = outModel.createEntityInstance(CLength_unit$si_unit.class);
			e_mm_length_unit = (ELength_unit) e_e;
			ESi_unit e_siu = (ESi_unit) e_e;
			e_siu.setName(null, ESi_unit_name.METRE);
			e_siu.setPrefix(null, ESi_prefix.MILLI);
			return e_mm_length_unit;
		}
		else if (u == Length.LengthUnit.INCH)
		{
			if (e_inch_length_unit != null)
				return e_inch_length_unit;
			
			EEntity e_e = outModel.createEntityInstance(CConversion_based_unit$length_unit.class);
			e_inch_length_unit = (ELength_unit) e_e;
			EConversion_based_unit e_cbu = (EConversion_based_unit) e_e;
			e_cbu.setName(null, "INCH");
			EDimensional_exponents e_de = (EDimensional_exponents) outModel.createEntityInstance(CDimensional_exponents.class);
			e_de.setLength_exponent(null, 1.0);
			e_de.setMass_exponent(null, 0.0);
			e_de.setTime_exponent(null, 0.0);
			e_de.setElectric_current_exponent(null, 0.0);
			e_de.setThermodynamic_temperature_exponent(null, 0.0);
			e_de.setAmount_of_substance_exponent(null, 0.0);
			e_de.setLuminous_intensity_exponent(null, 0.0);
			e_cbu.setDimensions(null, e_de);
			ELength_measure_with_unit e_lmwu = (ELength_measure_with_unit) outModel.createEntityInstance(CLength_measure_with_unit.class);
			e_lmwu.setValue_component(null, 25.4, (ELength_measure) null);
			ELength_unit e_mm_unit = getLengthUnit(Length.LengthUnit.MM);
			e_lmwu.setUnit_component(null, e_mm_unit);
			e_cbu.setConversion_factor(null, e_lmwu);
			return e_inch_length_unit;
		}
		else throw new UnsupportedOperationException("SDAIwrite of unit unsupported at this time: "+u);
	}
	
	ELength_measure_with_unit createLengthMeasure(Length l) throws SdaiException
	{
		ELength_measure_with_unit e_lmwu = (ELength_measure_with_unit) outModel.createEntityInstance(CLength_measure_with_unit.class);
		return populateLengthMeasure(e_lmwu, l);
	}
	
	ELength_measure_with_unit populateLengthMeasure(ELength_measure_with_unit e_lmwu, Length l) throws SdaiException
	{
		e_lmwu.setValue_component(null, l.getValue(), (ELength_measure)null);
		ELength_unit e_lu = getLengthUnit(l.getUnit());
		e_lmwu.setUnit_component(null, e_lu);
		return e_lmwu;
	}
	
	void populateModifier(EModified_geometric_tolerance e_mgt, ToleranceModifier m) throws SdaiException
	{
		e_mgt.setModifier(null, limit_condition(m));
	}
	
	EGeometric_tolerance createGeometricToleranceComplexWithModifierAndDatumRefs(ToleranceType t) throws SdaiException
	{
		if (t == ToleranceType.POSITION)
			return (EPosition_tolerance) outModel.createEntityInstance(CGeometric_tolerance_with_datum_reference$modified_geometric_tolerance$position_tolerance.class);
		else if (t == ToleranceType.SURFACEPROFILE)
			return (ESurface_profile_tolerance) outModel.createEntityInstance(CGeometric_tolerance_with_datum_reference$modified_geometric_tolerance$surface_profile_tolerance.class);
		else if (t == ToleranceType.ROUNDNESS)
			return (ERoundness_tolerance) outModel.createEntityInstance(CGeometric_tolerance_with_datum_reference$modified_geometric_tolerance$roundness_tolerance.class);
		else if (t == ToleranceType.PARALLELISM)
			return (EParallelism_tolerance) outModel.createEntityInstance(CModified_geometric_tolerance$parallelism_tolerance.class);
		else if (t == ToleranceType.FLATNESS)
			return (EFlatness_tolerance) outModel.createEntityInstance(CFlatness_tolerance$geometric_tolerance_with_datum_reference$modified_geometric_tolerance.class);
		else if (t == ToleranceType.CYLINDRICITY)
			return (ECylindricity_tolerance) outModel.createEntityInstance(CCylindricity_tolerance$geometric_tolerance_with_datum_reference$modified_geometric_tolerance.class);
		else
			throw new IllegalArgumentException("Unsupported tolerance type: "+t);
	}
	
	EGeometric_tolerance createGeometricToleranceComplexWithModifierAndNoDatumRefs(ToleranceType t) throws SdaiException
	{
		if (t == ToleranceType.POSITION)
			return (EPosition_tolerance) outModel.createEntityInstance(CModified_geometric_tolerance$position_tolerance.class);
		else if (t == ToleranceType.SURFACEPROFILE)
			return (ESurface_profile_tolerance) outModel.createEntityInstance(CModified_geometric_tolerance$surface_profile_tolerance.class);
		else if (t == ToleranceType.ROUNDNESS)
			return (ERoundness_tolerance) outModel.createEntityInstance(CModified_geometric_tolerance$roundness_tolerance.class);
		else if (t == ToleranceType.FLATNESS)
			return (EFlatness_tolerance) outModel.createEntityInstance(CFlatness_tolerance$modified_geometric_tolerance.class);
		else if (t == ToleranceType.CYLINDRICITY)
			return (ECylindricity_tolerance) outModel.createEntityInstance(CCylindricity_tolerance$modified_geometric_tolerance.class);
		else
			throw new IllegalArgumentException("Unsupported tolerance type: "+t);
	}
	
	EGeometric_tolerance createGeometricToleranceComplexWithNoModifierAndDatumRefs(ToleranceType t) throws SdaiException
	{
		if (t == ToleranceType.POSITION)
			return (EPosition_tolerance) outModel.createEntityInstance(CGeometric_tolerance_with_datum_reference$position_tolerance.class);
		else if (t == ToleranceType.SURFACEPROFILE)
			return (ESurface_profile_tolerance) outModel.createEntityInstance(CGeometric_tolerance_with_datum_reference$surface_profile_tolerance.class);
		else if (t == ToleranceType.ROUNDNESS)
			return (ERoundness_tolerance) outModel.createEntityInstance(CGeometric_tolerance_with_datum_reference$roundness_tolerance.class);
		else if (t == ToleranceType.PARALLELISM)
			return (EParallelism_tolerance) outModel.createEntityInstance(CParallelism_tolerance.class);
		else if (t == ToleranceType.FLATNESS)
			return (EFlatness_tolerance) outModel.createEntityInstance(CFlatness_tolerance$geometric_tolerance_with_datum_reference.class);
		else if (t == ToleranceType.CYLINDRICITY)
			return (ECylindricity_tolerance) outModel.createEntityInstance(CCylindricity_tolerance$geometric_tolerance_with_datum_reference.class);
		else
			throw new IllegalArgumentException("Unsupported tolerance type: "+t);
	}
	
	EGeometric_tolerance createGeometricToleranceNoModifierAndNoDatumRefs(ToleranceType t) throws SdaiException
	{
		if (t == ToleranceType.POSITION)
			return (EPosition_tolerance) outModel.createEntityInstance(CPosition_tolerance.class);	
		else if (t == ToleranceType.SURFACEPROFILE)
			return (ESurface_profile_tolerance) outModel.createEntityInstance(CSurface_profile_tolerance.class);
		else if (t == ToleranceType.ROUNDNESS)
			return (ERoundness_tolerance) outModel.createEntityInstance(CRoundness_tolerance.class);
		else if (t == ToleranceType.FLATNESS)
			return (EFlatness_tolerance) outModel.createEntityInstance(CFlatness_tolerance.class);
		else
			throw new IllegalArgumentException("Unsupported tolerance type: "+t);
	}
	
	public void createGeometricTolerance(GeometricTolerance tol) throws SdaiException
	{
		EGeometric_tolerance e_tol;
		
		if (tol.hasModifier())
		{
			if (tol.hasDatumRefs()) 
			{	
				e_tol = createGeometricToleranceComplexWithModifierAndDatumRefs(tol.getType());
				populateModifier((EModified_geometric_tolerance) e_tol, tol.getModifier());
				addDatumSystem(tol.getName(), (EGeometric_tolerance_with_datum_reference) e_tol, tol.getDatums());
			}
			else
			{
				e_tol = createGeometricToleranceComplexWithModifierAndNoDatumRefs(tol.getType());
				populateModifier((EModified_geometric_tolerance) e_tol, tol.getModifier());
			}
		}
		else // no modifier
		{
			if (tol.hasDatumRefs()) 
			{	
				e_tol = createGeometricToleranceComplexWithNoModifierAndDatumRefs(tol.getType());
				EGeometric_tolerance_with_datum_reference e_gtwdr = (EGeometric_tolerance_with_datum_reference) e_tol;
				addDatumSystem(tol.getName(), e_gtwdr, tol.getDatums());
			}
			else
				e_tol = createGeometricToleranceNoModifierAndNoDatumRefs(tol.getType());	
		}
		
		e_tol.setName(null, tol.getName());
		EShape_aspect e_sa = tol.getShapeAspect();
		e_tol.setToleranced_shape_aspect(null, e_sa);
		e_tol.setMagnitude(null, createLengthMeasure(tol.getLength()));
	}

	/**
	 * @TODO rec practice seems to think a derived shape aspect should have its own representation (with a geometric element, etc) within
	 * a constructive_geometry_representation - i would prefer to avoid this, as it seems improper for GD&T representation (maybe needed
	 * for display, but I don't think so even then - seems it should be derived from the given model geometry)
	 * expecting 2 faces in given aggregate
	 * note: product_definitional='.FALSE.' for derived shape_aspect
	 * @param e_af1
	 * @param a_af
	 * @return
	 * @throws SdaiException
	 */
	public ECentre_of_symmetry createCentrePlane(AxisPlacement3d placement, String key, AShape_aspect a_sa) throws SdaiException
	{
		ECentre_of_symmetry e_cos = (ECentre_of_symmetry) outModel.createEntityInstance(CCentre_of_symmetry.class);
		populateCentrePlaneShapeAspect(placement, e_cos, key, a_sa);
		addSAtoMap(key, e_cos);
		return e_cos;
	}
	
	public ECentre_of_symmetry createCentrePlane(AxisPlacement3d placement, String key, ESymmetric_shape_aspect e_ssa) throws SdaiException
	{
		ECentre_of_symmetry e_cos = (ECentre_of_symmetry) outModel.createEntityInstance(CCentre_of_symmetry.class);
		populateCentrePlaneShapeAspect(placement, e_cos, key, e_ssa);
		addSAtoMap(key, e_cos);
		return e_cos;
	}
	
	/**
	 * generic centre of symmetry - no geometric representation populated
	 * @param key
	 * @param e_ssa
	 * @return
	 * @throws SdaiException
	 */
	public ECentre_of_symmetry createCentreOfSphere(Vector position, String key, ESymmetric_shape_aspect e_ssa) throws SdaiException
	{
		ECentre_of_symmetry e_cos = (ECentre_of_symmetry) outModel.createEntityInstance(CCentre_of_symmetry.class);
		populateCentrePointShapeAspect(position, e_cos, key, e_ssa);
		addSAtoMap(key, e_cos);
		return e_cos;
	}
	
	/**
	 * probably invalid - center plane + datum feature
	 * @param placement
	 * @param key
	 * @param a_sa
	 * @return
	 * @throws SdaiException
	 */
	/*
	public ECentre_of_symmetry createDatumFeatureCentrePlane(AxisPlacement3d placement, String key, AShape_aspect a_sa) throws SdaiException
	{
		ECentre_of_symmetry e_cos = (ECentre_of_symmetry) outModel.createEntityInstance(CCentre_of_symmetry$datum_feature.class);
		e_cos.setProduct_definitional(null, jsdaiFALSE);
		populateCentrePlaneShapeAspect(placement, e_cos, key, a_sa);
		addSAtoMap(key, e_cos);
		return e_cos;
	}
	*/
	public ETangent createDatumFeatureTangentPlane(AxisPlacement3d placement, String key, EShape_aspect e_given) throws SdaiException
	{
		ETangent e_t = (ETangent) outModel.createEntityInstance(CDatum_feature$tangent.class);
		addSAtoMap(key, e_t);
		populateTangentPlaneShapeAspect(placement, e_t, key, e_given);
		// tangent plane (derived shape aspect) sets product definitional to false... unclear in the case of datum_feature+tangent
		e_t.setProduct_definitional(null, jsdaiTRUE);
		return e_t;
	}
	
	public EGeometric_alignment createDatumFeatureAlignmentPlane(AxisPlacement3d placement, String key, EShape_aspect e_given) throws SdaiException
	{
		EGeometric_alignment e_ga = (EGeometric_alignment) outModel.createEntityInstance(CDatum_feature$geometric_alignment.class);
		addSAtoMap(key, e_ga);
		populateGeometricAlignmentPlaneShapeAspect(placement, e_ga, key, e_given);
		// alignment plane (derived shape aspect) sets product definitional to false... unclear in the case of datum_feature+geometric_alignment
		e_ga.setProduct_definitional(null, jsdaiTRUE);
		return e_ga;
	}
	
	public ETangent createTangentPlane(AxisPlacement3d placement, String key, EShape_aspect e_given) throws SdaiException
	{
		ETangent e_t = (ETangent) outModel.createEntityInstance(CTangent.class);
		addSAtoMap(key, e_t);
		populateTangentPlaneShapeAspect(placement, e_t, key, e_given);
		return e_t;
	}
	
	public EShape_aspect createDatumFeatureFeatureOfSize(String key, AShape_aspect a_sa) throws SdaiException
	{
		ESymmetric_shape_aspect e_ssa = (ESymmetric_shape_aspect) outModel.createEntityInstance(CComposite_shape_aspect$datum_feature$symmetric_shape_aspect.class);
		e_ssa.setProduct_definitional(null, jsdaiTRUE);
		populateFeatureOfSizeShapeAspect((EComposite_shape_aspect)e_ssa, key, a_sa);
		addSAtoMap(key, e_ssa);
		return e_ssa;
	}
	
	public EShape_aspect createDatumFeatureCompositeUnitShapeAspect(String key, AShape_aspect a_sa) throws SdaiException
	{
		EComposite_unit_shape_aspect e_cusa = (EComposite_unit_shape_aspect) outModel.createEntityInstance(CComposite_unit_shape_aspect$datum_feature.class);
		e_cusa.setProduct_definitional(null, jsdaiTRUE);
		e_cusa.setName(null, key);
		populateCompositeShapeAspect(e_cusa, a_sa);
		addSAtoMap(key, e_cusa);
		return e_cusa;
	}
	/**
	 * singleton cgr
	 * @return
	 * @throws SdaiException
	 */
	EConstructive_geometry_representation getConstructionGeometryRep() throws SdaiException
	{
		if (e_cgr == null)
		{
			e_cgr = (EConstructive_geometry_representation) outModel.createEntityInstance(EConstructive_geometry_representation.class);
			e_cgr.setName(null, "");
			EConstructive_geometry_representation_relationship e_cgrr = (EConstructive_geometry_representation_relationship) outModel.createEntityInstance(EConstructive_geometry_representation_relationship.class);
			e_cgrr.setName(null, "");
			e_cgrr.setRep_2(null, e_cgr);
			e_cgrr.setRep_1(null, e_package_3d_sr);
			e_cgr.setContext_of_items(null, e_package_3d_sr.getContext_of_items(null));
			e_cgr.createItems(null);
		}
		return e_cgr;
	}
	
	/**
	 * supports a singleton Construction_geometry representation for derived package geometric elements
	 *  
	 * @param e_ri
	 * @throws SdaiException
	 */
	void addToConstructionRep(ERepresentation_item e_ri) throws SdaiException
	{
		EConstructive_geometry_representation e_rep = getConstructionGeometryRep();
		ARepresentation_item a_ri = e_rep.getItems(null);
		a_ri.addUnordered(e_ri);
	}
	
	public EPlane addDerivedPlane(EDerived_shape_aspect e_dsa, AxisPlacement3d placement) throws SdaiException
	{
		EPlane e_p = (EPlane) outModel.createEntityInstance(EPlane.class);
		e_p.setName(null, "");
		EAxis2_placement_3d e_placement3d = instantiateAxis2Placement3d(placement);
		e_p.setPosition(null, e_placement3d);
		addToConstructionRep(e_p);
		
		EGeometric_item_specific_usage e_gisu = (EGeometric_item_specific_usage) outModel.createEntityInstance(EGeometric_item_specific_usage.class);
		e_gisu.setName(null, "");
		e_gisu.setIdentified_item(null, e_p);
		e_gisu.setUsed_representation(null, getConstructionGeometryRep());
		e_gisu.setDefinition(null, e_dsa);
		return e_p;
	}
	
	public ECartesian_point addDerivedPoint(EDerived_shape_aspect e_dsa, Vector position) throws SdaiException
	{
		ECartesian_point e_point3d = instantiateCartesianPoint(position);
		e_point3d.setName(null, "");
		addToConstructionRep(e_point3d);
		
		EGeometric_item_specific_usage e_gisu = (EGeometric_item_specific_usage) outModel.createEntityInstance(EGeometric_item_specific_usage.class);
		e_gisu.setName(null, "");
		e_gisu.setIdentified_item(null, e_point3d);
		e_gisu.setUsed_representation(null, getConstructionGeometryRep());
		e_gisu.setDefinition(null, e_dsa);
		return e_point3d;
	}
	
	public EShape_aspect createDatumFeatureTangent(String key, EShape_aspect e_sa) throws SdaiException
	{
		EDatum_feature e_df = (EDatum_feature) outModel.createEntityInstance(CDatum_feature$tangent.class);
		e_df.setOf_shape(null, e_package);
		e_df.setName(null, "tangent plane");
		e_df.setDescription(null, key);
		e_df.setProduct_definitional(null, jsdaiTRUE);
		
		create_sadr(e_df, e_sa);
		addSAtoMap(key, e_df);
		return e_df;
	}

	//populateCentreOfSymmetryShapeAspect
	/**
	 * also creates feature of size (symmetric_shape_aspect) based on given shape_aspects
	 * creates single face shape aspects for each given face and relates them to the given shape aspect
	 * the given shape aspect might be a composite_group_shape_aspect, or a derived_shape_aspect (such as centre_of_symmetry)
	 * or other
	 * @param e_cgsa
	 * @param a_af
	 * @throws SdaiException
	 */
	public ECentre_of_symmetry populateCentrePlaneShapeAspect(AxisPlacement3d placement, ECentre_of_symmetry e_cos, String des, AShape_aspect a_sa) throws SdaiException
	{
		e_cos.setOf_shape(null, e_package);
		e_cos.setName(null, "centre plane");
		e_cos.setDescription(null, des);
		e_cos.setProduct_definitional(null, jsdaiFALSE);
		addDerivedPlane(e_cos, placement);
		ESymmetric_shape_aspect e_ssa = createFeatureOfSizeSymmetricShapeAspect(des, a_sa);
		create_sadr(e_cos, e_ssa);
		return e_cos;
	}
	
	public ETangent populateTangentPlaneShapeAspect(AxisPlacement3d placement, ETangent e_tan, String des, EShape_aspect e_sa) throws SdaiException
	{
		e_tan.setOf_shape(null, e_package);
		e_tan.setName(null, "tangent plane");
		e_tan.setDescription(null, des);
		e_tan.setProduct_definitional(null, jsdaiFALSE);
		addDerivedPlane(e_tan, placement);
		create_sadr(e_tan, e_sa);
		return e_tan;
	}
	
	public EGeometric_alignment populateGeometricAlignmentPlaneShapeAspect(AxisPlacement3d placement, EGeometric_alignment e_ga, String des, EShape_aspect e_sa) throws SdaiException
	{
		e_ga.setOf_shape(null, e_package);
		e_ga.setName(null, "geometric alignment plane");
		e_ga.setDescription(null, des);
		e_ga.setProduct_definitional(null, jsdaiFALSE);
		addDerivedPlane(e_ga, placement);
		create_sadr(e_ga, e_sa);
		return e_ga;
	}
	
	/**
	 * given feature of size (symmetric_shape_aspect)
	 * @param placement
	 * @param e_cos
	 * @param des
	 * @param e_ssa
	 * @return
	 * @throws SdaiException
	 */
	public ECentre_of_symmetry populateCentrePlaneShapeAspect(AxisPlacement3d placement, ECentre_of_symmetry e_cos, String des, ESymmetric_shape_aspect e_ssa) throws SdaiException
	{
		e_cos.setOf_shape(null, e_package);
		e_cos.setName(null, "centre plane");
		e_cos.setDescription(null, des);
		e_cos.setProduct_definitional(null, jsdaiFALSE);
		addDerivedPlane(e_cos, placement);
		create_sadr(e_cos, e_ssa);
		return e_cos;
	}
	
	public ECentre_of_symmetry populateCentrePointShapeAspect(Vector position, ECentre_of_symmetry e_cos, String des, ESymmetric_shape_aspect e_ssa) throws SdaiException
	{
		e_cos.setOf_shape(null, e_package);
		e_cos.setName(null, "centre point");
		e_cos.setDescription(null, des);
		e_cos.setProduct_definitional(null, jsdaiFALSE);
		addDerivedPoint(e_cos, position);
		create_sadr(e_cos, e_ssa);
		return e_cos;
	}
	
	public ESymmetric_shape_aspect createFeatureOfSizeSymmetricShapeAspect(String des, AShape_aspect a_sa) throws SdaiException
	{
		ESymmetric_shape_aspect e_ssa = (ESymmetric_shape_aspect) outModel.createEntityInstance(CComposite_shape_aspect$symmetric_shape_aspect.class);
		populateFeatureOfSizeShapeAspect((EComposite_shape_aspect)e_ssa, des, a_sa);
		addSAtoMap(des, e_ssa);
		return e_ssa;
	}
	
	/**
	 * given may or may not be a symmetric_shape_aspect 
	 * (not necessary in case of dimensional_size_with_datum_feature or
	 * dimensional_location_with_datum_feature)
	 * @param e_fossa
	 * @param des
	 * @param a_sa
	 * @throws SdaiException
	 */
	public void populateFeatureOfSizeShapeAspect(EComposite_shape_aspect e_fossa, String des, AShape_aspect a_sa) throws SdaiException
	{
		e_fossa.setName(null, "feature of size");
		e_fossa.setOf_shape(null, e_package);
		e_fossa.setProduct_definitional(null, jsdaiTRUE);
		e_fossa.setDescription(null, des);
		
		SdaiIterator it_sa = a_sa.createIterator();
		while (it_sa.next())
		{
			EShape_aspect e_sa = a_sa.getCurrentMember(it_sa);
			create_sa_composing_relationship(e_fossa, e_sa);
		}
	}
	
	EShape_aspect_relationship create_sa_composing_relationship(EComposite_shape_aspect e_composite_sa, EShape_aspect e_related) throws SdaiException
	{
		EShape_aspect_relationship e_sar = (EShape_aspect_relationship) outModel.createEntityInstance(CShape_aspect_relationship.class);
		e_sar.setName(null, "");
		e_sar.setRelating_shape_aspect(null, e_composite_sa);
		e_sar.setRelated_shape_aspect(null, e_related);
		return e_sar;
	}
	
	EShape_aspect_relationship create_sar(EShape_aspect e_relating_sa, EShape_aspect e_related) throws SdaiException
	{
		EShape_aspect_relationship e_sar = (EShape_aspect_relationship) outModel.createEntityInstance(CShape_aspect_relationship.class);
		e_sar.setName(null, "");
		e_sar.setRelating_shape_aspect(null, e_relating_sa);
		e_sar.setRelated_shape_aspect(null, e_related);
		return e_sar;
	}
	
	/**
	 * @param e_derived
	 * @param e_related
	 * @return
	 * @throws SdaiException
	 */
	EShape_aspect_deriving_relationship create_sadr(EShape_aspect e_derived, EShape_aspect e_related) throws SdaiException
	{
		EShape_aspect_deriving_relationship e_sadr = (EShape_aspect_deriving_relationship) outModel.createEntityInstance(CShape_aspect_deriving_relationship.class);
		e_sadr.setName(null, "");
		e_sadr.setRelating_shape_aspect(null, e_derived);
		e_sadr.setRelated_shape_aspect(null, e_related);
		return e_sadr;
	}
	
	public EShape_aspect createSingleFaceShapeAspect(String key, EAdvanced_face e_af, EShape_representation e_sr) throws SdaiException
	{
		EShape_aspect e_sa = (EShape_aspect) outModel.createEntityInstance(CShape_aspect.class);
		e_sa.setOf_shape(null, e_package);
		e_sa.setName(null, "single element");
		e_sa.setDescription(null, key);
		e_sa.setProduct_definitional(null, jsdaiTRUE);
		associateSingleFaceShapeAspect(key, e_sa, e_af, e_sr);
		addSAtoMap(key, e_sa);
		return e_sa;
	}
	
	public EShape_aspect createMultiFaceShapeAspect(String key, AAdvanced_face a_af, EShape_representation e_sr) throws SdaiException
	{
		EShape_aspect e_sa = (EShape_aspect) outModel.createEntityInstance(CShape_aspect.class);
		e_sa.setOf_shape(null, e_package);
		if (a_af.getMemberCount() > 1)
			e_sa.setName(null, "single geometric feature");
		else
			e_sa.setName(null, "single element");
		e_sa.setDescription(null, key);
		e_sa.setProduct_definitional(null, jsdaiTRUE);
		SdaiIterator it = a_af.createIterator();
		while (it.next())
		{
			EAdvanced_face e_af = a_af.getCurrentMember(it);
			associateSingleFaceShapeAspect(key, e_sa, e_af, e_sr);
		}
		addSAtoMap(key, e_sa);
		return e_sa;
	}
	
	EShape_aspect associateSingleFaceShapeAspect(String des, EShape_aspect e_sa, EAdvanced_face e_af, EShape_representation e_sr) throws SdaiException
	{
		e_sa.setProduct_definitional(null, jsdaiTRUE);
		EGeometric_item_specific_usage e_gisu = (EGeometric_item_specific_usage) outModel.createEntityInstance(CGeometric_item_specific_usage.class);
		e_gisu.setName(null, "");
		e_gisu.setDescription(null, des);
		e_gisu.setIdentified_item(null, e_af);
		e_gisu.setDefinition(null, e_sa);
		e_gisu.setUsed_representation(null, e_sr);
		return e_sa;
	}
	
	/**
	 * Create a shape_aspect for calling out a single-face in an instance of a UCUR shape rep
	 * Can be used for a single-level assembly-style representation based on UCUR 
	 * @param e_af
	 * @return
	 * @throws SdaiException
	 */
	public EShape_aspect createUCURChainBasedSingleFaceShapeAspect(String key, EShape_representation e_root_sr, EShape_representation e_leaf_sr, EUsage_concept_usage_relationship e_ucur, EAdvanced_face e_af) throws SdaiException
	{
		EShape_aspect e_sa = (EShape_aspect) outModel.createEntityInstance(CShape_aspect.class);
		e_sa.setOf_shape(null, e_package);
		e_sa.setName(null, "single element");
		e_sa.setDescription(null, key);
		e_sa.setProduct_definitional(null, jsdaiTRUE);
		associateUCURChainBasedSingleFaceShapeAspect(key, e_sa, e_root_sr, e_leaf_sr, e_ucur, e_af);
		addSAtoMap(key, e_sa);
		return e_sa;
	}
	
	public EShape_aspect createUCURChainBasedMultiFaceShapeAspect(String key, EShape_representation e_root_sr, EShape_representation e_leaf_sr, EUsage_concept_usage_relationship e_ucur, AAdvanced_face a_af) throws SdaiException
	{
		EShape_aspect e_sa = (EShape_aspect) outModel.createEntityInstance(CShape_aspect.class);
		e_sa.setOf_shape(null, e_package);
		if (a_af.getMemberCount() > 1)
			e_sa.setName(null, "single geometric feature");
		else
			e_sa.setName(null, "single element");
		e_sa.setDescription(null, key);
		e_sa.setProduct_definitional(null, jsdaiTRUE);
		SdaiIterator it = a_af.createIterator();
		while (it.next())
		{
			EAdvanced_face e_af = (EAdvanced_face) a_af.getCurrentMember(it);
			associateUCURChainBasedSingleFaceShapeAspect(key, e_sa, e_root_sr, e_leaf_sr, e_ucur, e_af);
		}
		
		addSAtoMap(key, e_sa);
		return e_sa;
	}
	
	public EDatum createDatum(String datumID, EDatum_feature e_df) throws SdaiException
	{
		EDatum e_datum = (EDatum) outModel.createEntityInstance(CDatum.class);
		e_datum.setOf_shape(null, e_package);
		e_datum.setIdentification(null, datumID);
		e_datum.setProduct_definitional(null, jsdaiFALSE);
		e_datum.setDescription(null, "plane");
		e_datum.setName(null, "");
		create_sar(e_df, e_datum);
		addSAtoMap(datumID, e_datum);
		return e_datum;
	}
	
	/**
	 * Associate an existing shape_aspect with a given face in an instance of a UCUR shape rep
	 * Can be used for a single-level assembly-style representation based on UCUR 
	 * @param e_af
	 * @return
	 * @throws SdaiException
	 */
	public EShape_aspect associateUCURChainBasedSingleFaceShapeAspect(String des, EShape_aspect e_sa, EShape_representation e_root_sr, EShape_representation e_leaf_sr, EUsage_concept_usage_relationship e_ucur, EAdvanced_face e_af) throws SdaiException
	{
		EChain_based_geometric_item_specific_usage e_cbgisu = (EChain_based_geometric_item_specific_usage) outModel.createEntityInstance(CChain_based_geometric_item_specific_usage.class);
		e_cbgisu.setName(null, e_sa.getName(null));
		e_cbgisu.setDescription(null, des);
		e_cbgisu.setIdentified_item(null, e_af);
		e_cbgisu.setDefinition(null, e_sa);
		// used_representation is derived
		//e_cbgisu.setUsed_representation((EGeometric_item_specific_usage)null, (ERepresentation)e_leaf_sr);
		ARepresentation a_nodes = e_cbgisu.createNodes(null);
		a_nodes.addByIndex(1, e_root_sr);
		a_nodes.addByIndex(2, e_leaf_sr);
		AChained_representation_link a_undirected_link = e_cbgisu.createUndirected_link(null);
		a_undirected_link.addByIndex(1, e_ucur);
		return e_sa;
	}

	public EDimensional_location createLinearDimensionalLocation(ToleranceLengthValue v, EShape_aspect e_relating, EShape_aspect e_related) throws SdaiException
	{
		EDimensional_location e_dl = (EDimensional_location) outModel.createEntityInstance(CDimensional_location.class);
		e_dl.setName(null, "linear distance");
		e_dl.setRelated_shape_aspect(null, e_related);
		e_dl.setRelating_shape_aspect(null, e_relating);
		populateToleranceLengthValue(e_dl, v);
		return e_dl;
	}
	
	public EDimensional_size createDimensionalSize(DimensionalSize d) throws SdaiException
	{
		EDimensional_size e_ds = (EDimensional_size) outModel.createEntityInstance(CDimensional_size.class);
		e_ds.setApplies_to(null, d.getShapeAspect());
		e_ds.setName(null, "");
		populateToleranceLengthValue(e_ds, d.getValue());
		return e_ds;
	}
	
	
	public ESymmetric_shape_aspect createDimensionedFeatureOfSize(String key, ToleranceLengthValue v, AShape_aspect a_sa) throws SdaiException
	{
		EDimensional_size e_ds = (EDimensional_size) outModel.createEntityInstance(CDimensional_size.class);
		ESymmetric_shape_aspect e_ssa = createFeatureOfSizeSymmetricShapeAspect(key, a_sa);
		e_ds.setName(null, "");
		e_ds.setApplies_to(null, e_ssa);
		populateToleranceLengthValue(e_ds, v);
		return e_ssa;
	}
	

	public EDimensional_size createDimensionedGroupFeatureOfSize(ToleranceLengthValue v, EComposite_group_shape_aspect e_cgsa) throws SdaiException
	{
		EDimensional_size e_ds = (EDimensional_size) outModel.createEntityInstance(CDimensional_size.class);
		e_ds.setName(null, "");
		e_ds.setApplies_to(null, e_cgsa);
		populateToleranceLengthValue(e_ds, v);
		return e_ds;
	}
	
	public EDatum_feature createDimensionalSizeWithDatumFeature(String key, ToleranceLengthValue v, AShape_aspect a_sa) throws SdaiException
	{
		EDatum_feature e_df = (EDatum_feature) outModel.createEntityInstance(CComposite_shape_aspect$dimensional_size_with_datum_feature.class);
		e_df.setProduct_definitional(null, jsdaiTRUE);
		EDimensional_size e_ds = (EDimensional_size) e_df;
		e_ds.setApplies_to(null, e_df);
		e_ds.setName(null, "");
		populateFeatureOfSizeShapeAspect((EComposite_shape_aspect)e_df, key, a_sa);
		populateToleranceLengthValue(e_ds, v);
		addSAtoMap(key, e_df);
		return e_df;
	}
	
	/**
	 * per pmi rec practice
	 * @param m
	 * @return
	 * @throws SdaiException
	 */
	EDescriptive_representation_item dimensionModifier(ToleranceLengthValue.DimensionModifier m) throws SdaiException
	{
		EDescriptive_representation_item e_dri = (EDescriptive_representation_item) outModel.createEntityInstance(CDescriptive_representation_item.class);
		e_dri.setName(null, "dimensional note");
		String des = m.toMim();
		/*
		if (m == ToleranceLengthValue.DimensionModifier.BASIC)
			des = "theoretical";
		else if (m == ToleranceLengthValue.DimensionModifier.DIAMETER)
			des = "diameter";
		else if (m == ToleranceLengthValue.DimensionModifier.RADIUS)
			des = "radius";
		else if (m == ToleranceLengthValue.DimensionModifier.REFERENCE)
			des = "auxiliary";
		else
			throw new IllegalArgumentException("Unsupported value of dimension modifier: "+m.toString());
			*/
		e_dri.setDescription(null, des);
		return e_dri;
	}
	
	public EDimensional_characteristic_representation createDimensionalCharacteristicRepresentation(ToleranceLengthValue v) throws SdaiException
	{
		EDimensional_characteristic_representation e_dcr = (EDimensional_characteristic_representation) outModel.createEntityInstance(CDimensional_characteristic_representation.class); 
		EShape_dimension_representation e_sdr = (EShape_dimension_representation) outModel.createEntityInstance(CShape_dimension_representation.class);
		e_sdr.setContext_of_items(null, getDimensionContext());
		e_sdr.setName(null, v.getName());
		e_dcr.setRepresentation(null, e_sdr);
		ARepresentation_item a_ri = e_sdr.createItems(null);
		for (ToleranceLengthValue.QualifiedValue q : v.getValues())
		{
			EMeasure_representation_item e_mri;
			if (q.isQualified())
				e_mri = createQualifiedLengthValue(q);
			else
				e_mri = createLengthValue(q);
			a_ri.addUnordered(e_mri);
		}
		if (v.hasDimensionModifier())
			a_ri.addUnordered(dimensionModifier(v.getDimensionModifier()));
		return e_dcr;
	}
	
	public void populateToleranceLengthValue(EDimensional_size e_ds, ToleranceLengthValue v) throws SdaiException
	{
		EDimensional_characteristic_representation e_dcr = createDimensionalCharacteristicRepresentation(v);
		e_ds.setName(null, "length");
		e_dcr.setDimension(null, e_ds);
	}
	
	public void populateToleranceLengthValue(EDimensional_location e_dl, ToleranceLengthValue v) throws SdaiException
	{
		EDimensional_characteristic_representation e_dcr = createDimensionalCharacteristicRepresentation(v);
		e_dl.setName(null, "linear distance");
		e_dcr.setDimension(null, e_dl);
	}
	
	String TypeQualifierName(ToleranceLengthValue.Qualifier q)
	{
		if (q == null)
			throw new IllegalArgumentException("Null tolerance value qualifier: ");
		if (q == ToleranceLengthValue.Qualifier.MAXIMUM)
			return "maximum";
		else if (q == ToleranceLengthValue.Qualifier.MINIMUM)
			return "minimum";
		else if (q == ToleranceLengthValue.Qualifier.NOMINAL)
			return "average";
		else
			throw new IllegalArgumentException("Unsupported tolerance value qualifier: "+q.toString());
	}
	
	/**
	 * only supports a single qualifier per value
	 * @param e_qri
	 * @param q
	 * @throws SdaiException
	 */
	void createQualifier(EQualified_representation_item e_qri, ToleranceLengthValue.Qualifier q) throws SdaiException
	{
		AValue_qualifier a_vq = e_qri.createQualifiers(null);
		EType_qualifier e_tq = (EType_qualifier) outModel.createEntityInstance(CType_qualifier.class);
		e_tq.setName(null, TypeQualifierName(q));
		a_vq.addUnordered(e_tq);
	}
	
	EMeasure_representation_item createQualifiedLengthValue(ToleranceLengthValue.QualifiedValue q) throws SdaiException
	{
		if (!(q.isQualified()))
			throw new IllegalArgumentException("Non-qualified value encountered: "+q.toString());
		EMeasure_representation_item e_mri = (EMeasure_representation_item) outModel.createEntityInstance(CLength_measure_with_unit$measure_representation_item$qualified_representation_item.class);
		e_mri.setName(null, "");
		EQualified_representation_item e_qri = (EQualified_representation_item) e_mri;
		ELength_measure_with_unit e_lmwu = (ELength_measure_with_unit) e_mri;
		populateLengthMeasure(e_lmwu, q.l);
		createQualifier(e_qri, q.q);
		return e_mri;
	}
	
	EMeasure_representation_item createLengthValue(ToleranceLengthValue.QualifiedValue q) throws SdaiException
	{
		if (q.isQualified())
			throw new IllegalArgumentException("Qualified value encountered: "+q.toString());
		EMeasure_representation_item e_mri = (EMeasure_representation_item) outModel.createEntityInstance(CLength_measure_with_unit$measure_representation_item.class);
		e_mri.setName(null, "");
		ELength_measure_with_unit e_lmwu = (ELength_measure_with_unit) e_mri;
		populateLengthMeasure(e_lmwu, q.l);
		return e_mri;
	}
}
