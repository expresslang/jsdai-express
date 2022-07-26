package com.sfm.ap210.jsdai;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EAdvanced_face;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EChain_based_geometric_item_specific_usage;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EGeometric_item_specific_usage;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EItem_identified_representation_usage;
import jsdai.lang.SdaiException;

public class ChainBasedPackageFaceReference implements FaceReference {
	EAdvanced_face e_af;
	String faceID;
	EChain_based_geometric_item_specific_usage e_cbgisu;
	
	ChainBasedPackageFaceReference(EChain_based_geometric_item_specific_usage cbgisu, EAdvanced_face af) throws SdaiException
	{
		e_cbgisu = cbgisu;
		e_af = af;
		faceID = e_af.getPersistentLabel();
	}
	
	public int compareTo(Object o) {
		if (o instanceof FaceReference)
			return this.toString().compareTo(((FaceReference)o).toString());
		else
			throw new ClassCastException("FaceReference expected.");
	}

	public EAdvanced_face getFace() {
		return e_af;
	}

	public EItem_identified_representation_usage getIIRU() {
		return e_cbgisu;
	}

	public String getFaceID()
	{
		return faceID;
	}
	
	public String toString()
	{
		return "Face: "+faceID+" of package";
	}

}
