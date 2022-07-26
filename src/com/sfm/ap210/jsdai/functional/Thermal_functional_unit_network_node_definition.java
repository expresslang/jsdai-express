package com.sfm.ap210.jsdai.functional;

import java.util.HashSet;

public class Thermal_functional_unit_network_node_definition extends
		Functional_unit_network_node_definition {

	public Thermal_functional_unit_network_node_definition(String name, Functional_unit_network_definition fund)
	{
		super(name, fund);
	}
	
	public Thermal_functional_unit_network_node_definition(String name, Functional_unit_network_definition fund, Functional_unit_terminal fut)
	{
		super(name, fund, fut);
	}
}
