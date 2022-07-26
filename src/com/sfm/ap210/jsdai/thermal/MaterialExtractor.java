package com.sfm.ap210.jsdai.thermal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EPhysical_unit;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.ERepresentation_context;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EShape_aspect;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EShape_representation;
import jsdai.lang.SdaiException;
import jsdai.lang.SdaiModel;

import com.sfm.ap210.jsdai.MIMpackageQueries;
import com.sfm.ap210.jsdai.MIMpackageQueriesImpl;
import com.sfm.ap210.jsdai.param.Param;

/**
 * This class illustrates the reading of material properties associated with {@MIM shape_aspect}
 * instances 
 * 
 */
public class MaterialExtractor {
	//the model scope for extracting materials
	SdaiModel model;
	//helper queries
	MIMpackageQueries pq;
	
	/**
	 * Create a new MaterialExtractor instance that will operate on the given model
	 * @param model
	 * @throws SdaiException
	 */
	public MaterialExtractor(SdaiModel model) throws SdaiException {		
		this.pq = new MIMpackageQueriesImpl(model);
	}
						
	/**
	 * Create a ThermalMaterial representation using the properties defined on the given
	 * {@MIM shape_aspect} instance in the given materials {@MIM representation_context} 
	 * @param aspect the {@MIM shape_aspect} with material characteristics
	 * @param materials_context the {@MIM representation_context} associated with material characteristic values
	 * @return a ThermalMaterial representation of the {@MIM shape_aspect}'s material characteristics 
	 * @throws SdaiException
	 */
	ThermalMaterial readMaterial(EShape_aspect aspect, ERepresentation_context materials_context) throws SdaiException {
		List<Param> materialProperties = pq.getParametersOfShapeAspectInContext(aspect, materials_context);
		if(materialProperties.isEmpty())
			return null;
		ThermalMaterial material = new ThermalMaterial("");	
		for(Param p : materialProperties) {
			if("material_name".equals(p.getName())) {
				material.setName((String)p.getValue());
			} else {
				material.setProperty(p);
			}
		}		
		return material;
	}
	
	/**
	 * Extracts a mapping from {@MIM shape_aspect} instances to each {@MIM shape_aspect}'s associated material characteristics.
	 * The method traverses all {@MIM shape_aspect}s of the provided {@MIM physical_unit} and {@MIM shape_representation} and extracts material
	 * characteristic values associated with the provided material {@MIM representation_context}.
	 * 
	 * @param e_pu
	 * 	The physcial_unit instance
	 * @param e_model
	 * 	A shape model of the provided physical_unit
	 * @param materials_context
	 * 	The representation_context associated with material characteristic values
	 * @return
	 * @throws SdaiException
	 */
	public Map<EShape_aspect, ThermalMaterial> extractMaterials(EPhysical_unit e_pu,  EShape_representation e_model, ERepresentation_context materials_context) throws SdaiException {					
								
		Map<EShape_aspect, ThermalMaterial> materialAssignments = new HashMap<EShape_aspect, ThermalMaterial>();
		
		for(EShape_aspect aspect : pq.getShapeAspectsOfPhysicalShapeRepresentation(e_pu, e_model)) {
			ThermalMaterial material = readMaterial(aspect, materials_context);
			if(material != null) {
				materialAssignments.put(aspect, material);
			}
		}		
		return materialAssignments;		
	}		
}
