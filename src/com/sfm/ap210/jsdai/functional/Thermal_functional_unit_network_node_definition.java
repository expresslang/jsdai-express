package com.sfm.ap210.jsdai.functional;

import java.util.HashSet;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.ERepresentation_context;

public class Thermal_functional_unit_network_node_definition extends
		Functional_unit_network_node_definition {

	public double[] location;
	public ERepresentation_context location_context;
	
	public Thermal_functional_unit_network_node_definition(String name, Functional_unit_network_definition fund)
	{
		super(name, fund);
	}
	
	public Thermal_functional_unit_network_node_definition(String name, Functional_unit_network_definition fund, Functional_unit_terminal fut)
	{
		super(name, fund, fut);
	}		
}
