package com.sfm.ap210.jsdai;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EAdvanced_face;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EChain_based_geometric_item_specific_usage;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EPackage_terminal;
import jsdai.lang.SdaiException;

public class ChainBasedTerminalFaceReference extends
		ChainBasedPackageFaceReference implements TerminalFaceReference {
	EPackage_terminal e_terminal;
	String pinNo;
	
	ChainBasedTerminalFaceReference(EChain_based_geometric_item_specific_usage cbgisu,
			EAdvanced_face af, EPackage_terminal terminal) throws SdaiException {
		super(cbgisu, af);
		e_terminal = terminal;
		pinNo = e_terminal.getName(null);
	}

	public String toString()
	{
		return "Face: "+faceID+" of terminal "+pinNo;
	}

	public EPackage_terminal getTerminal() {
		return e_terminal;
	}

	public String getPinNo() {
		return pinNo;
	}
}
