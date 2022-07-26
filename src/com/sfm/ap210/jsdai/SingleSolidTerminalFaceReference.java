package com.sfm.ap210.jsdai;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EAdvanced_face;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EGeometric_item_specific_usage;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EPackage_body;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EPackage_terminal;
import jsdai.lang.SdaiException;

public class SingleSolidTerminalFaceReference extends
		SingleSolidPackageFaceReference implements TerminalFaceReference {
	EPackage_terminal e_terminal;
	String pinNo;
	
	SingleSolidTerminalFaceReference(EGeometric_item_specific_usage gisu,
			EAdvanced_face af, EPackage_terminal terminal) throws SdaiException {
		super(gisu, af);
		e_terminal = terminal;
		pinNo = e_terminal.getName(null);
	}

	public String toString()
	{
		return "Face: "+faceID+" (also face of terminal "+pinNo+")";
	}

	public EPackage_terminal getTerminal() {
		return e_terminal;
	}

	public String getPinNo() {
		return pinNo;
	}
}
