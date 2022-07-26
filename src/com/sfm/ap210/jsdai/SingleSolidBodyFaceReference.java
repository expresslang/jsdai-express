package com.sfm.ap210.jsdai;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EAdvanced_face;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EGeometric_item_specific_usage;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EPackage_body;
import jsdai.lang.SdaiException;

public class SingleSolidBodyFaceReference extends
		SingleSolidPackageFaceReference implements BodyFaceReference {
	EPackage_body e_body;
	
	SingleSolidBodyFaceReference(EGeometric_item_specific_usage gisu,
			EAdvanced_face af, EPackage_body body) throws SdaiException {
		super(gisu, af);
		e_body = body;
	}

	public EPackage_body getBody() {
		return e_body;
	}

	public String toString()
	{
		return "Face: "+faceID+" (also face of body)";
	}
}
