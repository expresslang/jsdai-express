package com.sfm.ap210.jsdai.functional;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

public class Functional_unit_network_definition extends FunctionalModelObject implements Comparable<Functional_unit_network_definition>
{

	FunctionalProductVersion product;
	Functional_unit_usage_view usage_view;
	String id;
	List<Functional_unit_network_node_definition> nodes = new Vector<Functional_unit_network_node_definition>();
	
	public Functional_unit_network_definition(FunctionalProductVersion fpv)
	{
		product = fpv;
		fpv.setFUND(this);
		id = "network definition of version "+fpv.getVersionId()+" of "+fpv.getProductId();
	}
	
	public String getId()
	{
		return id;
	}
	
	public FunctionalProductVersion getFunctionalProductVersion()
	{
		return product;
	}

	public void addNode(Functional_unit_network_node_definition node)
	{
		nodes.add(node);
	}
	
	public void addNodesForFunctionalUnit(Functional_unit fu)
	{
		Collection<Functional_unit_terminal> terminals = fu.getTerminals();
		for (Functional_unit_terminal t : terminals)
		{
			String nodeName = t.getDefiningScalarTerminal().getSignalName()+fu.getRefDes();
			Functional_unit_network_node_definition node = new Functional_unit_network_node_definition(nodeName, this, t);
			nodes.add(node);
		}
	}
	
	public List<Functional_unit_network_node_definition> getNodes()
	{
		return nodes;
	}
	
	/*
	public Collection<Functional_unit_network_node_definition> getNodes()
	{
		List<Functional_unit_network_node_definition> nodeList = new Vector<Functional_unit_network_node_definition>(nodes);
		//Collections.sort(nodeList);
		return nodeList;
	}
	*/
	
	/**
	 * usage view and network definition share a common product
	 * @return
	 */
	public void createUsageView()
	{
		Functional_unit_usage_view fuuv = new Functional_unit_usage_view(product);
		usage_view = fuuv;
		for (Functional_unit_network_node_definition node : nodes)
		{
			Scalar_terminal_definition uvt = fuuv.addTerminal(node.getName());
			node.setUsageViewTerminal(uvt);
		}
	}
	
	public void createUsageView(SortedSet<String> interfaceNodes)
	{
		Functional_unit_usage_view fuuv = new Functional_unit_usage_view(product);
		usage_view = fuuv;
		for (Functional_unit_network_node_definition node : nodes)
		{
			if (interfaceNodes.contains(node.name))
			{
				Scalar_terminal_definition uvt = fuuv.addTerminal(node.getName());
				node.setUsageViewTerminal(uvt);
			}
		}
	}
	
	public void setFUUV(Functional_unit_usage_view uv)
	{
		this.usage_view = uv;
	}
	
	public Functional_unit_usage_view getFUUV()
	{
		return usage_view;
	}
	
	public String toString()
	{
		return "FUND id: "+id+" FPV: "+product.product_id+"-"+product.product_name+" "+nodes.toString();
	}

	@Override
	public int compareTo(Functional_unit_network_definition o) {
		return this.toString().compareTo(o.toString());
	}
	
	public Functional_unit getFunctionalUnitBetweenNodesOfGivenNames(String name1, String name2)
	{
		Functional_unit_network_node_definition node1 = getNodeByName(name1);
		Functional_unit_network_node_definition node2 = getNodeByName(name2);
		Set<Functional_unit> connected = connectedFunctionalUnits(node1);
		Set<Functional_unit> connectedToNode2 = connectedFunctionalUnits(node2);
		connected.retainAll(connectedToNode2);
		if (connected.size() != 1)
			throw new IllegalArgumentException("Not exactly one functional unit found between given nodes.");
		return connected.iterator().next();
	}
	
	public Set<Functional_unit> connectedFunctionalUnits(Functional_unit_network_node_definition node)
	{
		Set<Functional_unit> connected = new TreeSet<Functional_unit>();
		for (Functional_unit_terminal t : node.getFunctionalUnitTerminals())
			connected.add(t.getFunctionalUnit());
		return connected;
	}
	
	public Functional_unit_network_node_definition getNodeByName(String name)
	{
		for (Functional_unit_network_node_definition node : nodes)
		{
			if (node.name.equals(name))
				return node;
		}
		throw new IllegalArgumentException("Unable to find node of name: "+name);
	}
}
