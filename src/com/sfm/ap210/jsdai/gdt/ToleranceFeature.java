package com.sfm.ap210.jsdai.gdt;

import java.util.Set;

import com.sfm.ap210.jsdai.FaceReference;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EShape_aspect;

/**
 * A ToleranceFeature is a collection of face references. The face references may be either chain-based or single-solid-based
 * references. A ToleranceFeature has a FeatureType that identifies the feature type in the context of GDT annotations.
 * @author James Stori
 *
 */
public interface ToleranceFeature extends Comparable {
	public enum FeatureType {
		CENTERPLANE, FEATUREOFSIZE, CENTREOFSYMMETRY, GENERIC, TANGENTPLANE, TANGENT, ALIGNMENTPLANE, ALIGNMENT
	}
	
	public FeatureType getFeatureType();
	
	public EShape_aspect getShapeAspect();
	
	public Set<FaceReference> getFaces();
	
	public String reportFaces();
}
