package com.sfm.ap210.jsdai.functional;

import java.util.HashSet;
import java.util.Set;

public class Functional_unit_network_node_definition extends FunctionalModelObject implements Comparable<Functional_unit_network_node_definition> {

	public String name;
	Functional_unit_network_definition fund;
	public Set<Functional_unit_terminal> terminals;
	Scalar_terminal_definition usage_view_terminal; // there can be at most one of these
	
	public Functional_unit_network_node_definition(String name, Functional_unit_network_definition fund)
	{
		terminals = new HashSet<Functional_unit_terminal>();
		this.name = name;
		this.fund = fund;
		this.usage_view_terminal = null;
	}
	
	public Functional_unit_network_node_definition(String name, Functional_unit_network_definition fund, Functional_unit_terminal fut)
	{
		terminals = new HashSet<Functional_unit_terminal>();
		this.name = name;
		this.fund = fund;
		terminals.add(fut);
		this.usage_view_terminal = null;
	}
	
	public void addTerminal(Functional_unit_terminal t)
	{
		terminals.add(t);
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setUsageViewTerminal(Scalar_terminal_definition usage_view_terminal)
	{
		this.usage_view_terminal = usage_view_terminal;
	}
	
	public Set<Functional_unit_terminal> getFunctionalUnitTerminals()
	{
		return terminals;
	}
	
	public Scalar_terminal_definition getUsageViewTerminal()
	{
		return usage_view_terminal;
	}
	
	public Functional_unit_network_definition getNetworkDefinition()
	{
		return fund;
	}
	
	public int compareTo(Functional_unit_network_node_definition o) {
		return this.getName().compareTo(o.getName());
	}
	
	public String toString()
	{
		return name;
	}
}
