package com.sfm.ap210.jsdai;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EAdvanced_face;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EItem_identified_representation_usage;

public interface FaceReference extends Comparable {
	EAdvanced_face getFace();
	EItem_identified_representation_usage getIIRU();
	String getFaceID();
	String toString();
}
