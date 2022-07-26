package com.sfm.ap210.jsdai.gdt;

import java.util.Set;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EComposite_group_shape_aspect;

public interface CompositeTolerance extends ToleranceFeature {
	public EComposite_group_shape_aspect getGroup();
	public Set<ToleranceFeatureImpl> getFeatures();
}
