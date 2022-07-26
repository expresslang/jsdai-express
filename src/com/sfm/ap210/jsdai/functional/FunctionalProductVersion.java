package com.sfm.ap210.jsdai.functional;

import java.util.Set;
import java.util.TreeSet;

import com.sfm.ap210.jsdai.param.ParameterAssignment;

public class FunctionalProductVersion extends FunctionalModelObject {

	String version_id;
	public String product_id;
	public String product_name;
	Functional_unit_usage_view fuuv;
	Functional_unit_network_definition fund;
	public Set<ParameterAssignment> params = new TreeSet<ParameterAssignment>();
	
	public FunctionalProductVersion(String product, String name, String version)
	{
		version_id = version;
		product_id = product;
		product_name = name;
		fuuv = null;
		fund = null;
	}
	
	public void addParam(ParameterAssignment p)
	{
		params.add(p);
	}
	
	public void addParams(Set<ParameterAssignment> pset)
	{
		params.addAll(pset);
	}
	
	public Set<ParameterAssignment> getParams()
	{
		return params;
	}
	
	public void setFUUV(Functional_unit_usage_view usage_view)
	{
		fuuv = usage_view;
	}
	
	public void setFUND(Functional_unit_network_definition network_definition)
	{
		fund = network_definition;
	}
	
	public Functional_unit_usage_view getFUUV()
	{
		return fuuv;
	}
	
	public Functional_unit_network_definition getFUND()
	{
		return fund;
	}
	
	public String getProductId()
	{
		return product_id;
	}
	
	public String getVersionId()
	{
		return version_id;
	}
}
