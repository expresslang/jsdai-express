package com.sfm.ap210.jsdai.thermal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sfm.ap210.jsdai.param.Param;

public class ThermalMaterial {
	protected String name;
	protected List<Param> properties = new ArrayList<Param>();
	
	public ThermalMaterial(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	private Param findPropertyByName(String name) {
		for(Param p : properties) {
			if(p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}
	
	public Param getProperty(String name) {
		return findPropertyByName(name);
	}
	
	public void setProperty(Param property) {
		if(property == null)
			return;
		Param current = findPropertyByName(property.getName());
		if(current != null) {
			properties.remove(current);
		}
		properties.add(property);		
	}
	
	public Set<String> getProperties() {
		Set<String> names = new HashSet<String>();
		for(Param p : properties) {
			names.add(p.getName());
		}
		return names;
	}
	
	public void removeProperty(String name) {
		properties.remove(getProperty(name));			
	}
	
	public String toString() {
		return String.format("ThermalMaterial(%s)", name);
	}
}
