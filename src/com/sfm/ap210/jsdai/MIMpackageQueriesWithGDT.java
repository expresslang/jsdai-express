package com.sfm.ap210.jsdai;

import java.util.Set;

import com.sfm.ap210.jsdai.gdt.Datum;
import com.sfm.ap210.jsdai.gdt.DimensionalLocation;
import com.sfm.ap210.jsdai.gdt.DimensionalSize;
import com.sfm.ap210.jsdai.gdt.GeometricTolerance;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.*;
import jsdai.lang.SdaiException;

public interface MIMpackageQueriesWithGDT extends MIMpackageQueries {

	public AAdvanced_face getFacesOfShapeAspect(EShape_aspect e_sa) throws SdaiException;
	
	public EAdvanced_face getFaceOfShapeAspect(EShape_aspect e_sa) throws SdaiException;
	
	public Set<FaceReference> getFaceReferencesOfShapeAspect(EShape_aspect e_sa) throws SdaiException;
	
	public AShape_aspect getAssociatedShapeAspectsForFace(EAdvanced_face e_af) throws SdaiException;
	
	public EPackage_terminal getAssociatedTerminalForFace(EAdvanced_face e_af) throws SdaiException;
	
	public EPackage_body getAssociatedBodyForFace(EAdvanced_face e_af) throws SdaiException;
	
	public GeometricTolerance readGeometricTolerance(EGeometric_tolerance e_tol) throws SdaiException;
	
	public Datum readDatum(EDatum e_datum) throws SdaiException;
	
	public ADatum getDatumsInModel() throws SdaiException;
	
	public ADimensional_size getAllDimensionalSizeInModel() throws SdaiException;
	
	public ADimensional_location getAllDimensionalLocationInModel() throws SdaiException;
	
	public AGeometric_tolerance getAllGeometricToleranceInModel() throws SdaiException;

	public ToleranceLengthValue getValueOfDimensionalSize(EDimensional_size e_ds) throws SdaiException;
	
	public ToleranceLengthValue getValueOfDimensionalLocation(EDimensional_location e_dl) throws SdaiException;
	
	public ToleranceLengthValue getValueOfShapeDimensionRepresentation(EShape_dimension_representation e_sdr) throws SdaiException;
	
	public AShape_aspect getAllComposingShapeAspects(EShape_aspect e_sa) throws SdaiException;
	
	public DimensionalSize readDimensionalSize(EDimensional_size e_ds) throws SdaiException;
	
	public DimensionalLocation readDimensionalLocation(EDimensional_location e_dl) throws SdaiException;
}
