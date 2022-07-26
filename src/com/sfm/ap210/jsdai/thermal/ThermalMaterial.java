package com.sfm.ap210.jsdai.thermal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sfm.ap210.jsdai.param.Param;

/**
 * Illustrates a thermal material representation as a name and a listing of properties
 * describing the physical characteristics of the material.
 *
 */
public class ThermalMaterial {
	//the material's name
	protected String name;
	//the material's characteristics
	protected List<Param> properties = new ArrayList<Param>();
	
	/**
	 * Create a new material with the given name and no physical characteristics
	 * @param name
	 */
	public ThermalMaterial(String name) {
		this.name = name;
	}
	
	/**
	 * Get the name of the material
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the material's name
	 * @param name
	 */
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
	
	/**
	 * Find the property with the given name defined on the material
	 * @param name
	 * @return
	 */
	public Param getProperty(String name) {
		return findPropertyByName(name);
	}
	
	/**
	 * Attach the given characteristic to the material, if this characteristic
	 * already exists, it is overwritten
	 * @param property
	 */
	public void setProperty(Param property) {
		if(property == null)
			return;
		Param current = findPropertyByName(property.getName());
		if(current != null) {
			properties.remove(current);
		}
		properties.add(property);		
	}
	
	/**
	 * Get all characteristics of the material
	 * @return
	 */
	public Set<String> getProperties() {
		Set<String> names = new HashSet<String>();
		for(Param p : properties) {
			names.add(p.getName());
		}
		return names;
	}
	
	/**
	 * Remove the property with the given name from 
	 * this material
	 * 
	 * @param name the property to remove
	 */
	public void removeProperty(String name) {
		properties.remove(getProperty(name));			
	}
	
	public String toString() {
		return String.format("ThermalMaterial(%s)", name);
	}
}
