package com.sfm.ap210.jsdai.thermal;

import java.util.List;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EComposite_group_shape_aspect;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.ERepresentation;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.ERepresentation_context;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EShape_aspect;
import jsdai.lang.ELogical;
import jsdai.lang.SdaiException;
import jsdai.lang.SdaiModel;

import com.sfm.ap210.jsdai.MIMpackageQueries;
import com.sfm.ap210.jsdai.MIMpackageQueriesImpl;
import com.sfm.ap210.jsdai.param.DoubleParam;
import com.sfm.ap210.jsdai.param.MeasureParam;
import com.sfm.ap210.jsdai.param.Param;
import com.sfm.ap210.jsdai.param.StringParam;
import com.sfm.ap210.jsdai.param.StringParamImpl;
import com.sfm.ap210.jsdai.utils.SdaiUtils;
import com.sfm.ap210.jsdai.write.ParamSdaiWriter;

/**
 * This class illustrates assigning a material representation to a collection
 * of {@MIM shape_aspect} instances from a particular {@MIM shape_representation}
 * 
 */
public class MaterialAssigner {
	//the SdaiModel instance context
	SdaiModel model;	
	/**
	 * Create a new MaterialAssigner that will assign materials in the given
	 * SdaiModel
	 * @param model
	 */
	public MaterialAssigner(SdaiModel model) {
		this.model = model;				
	}
	
	/**
	 * Create a value representation for a material characteristic with a value representation in 
	 * the given materials {@MIM representation_context} 
	 * @param value the property object representing the material characteristic
	 * @param materialsContext the material properties context
	 * @return the created value representation instance
	 * @throws SdaiException
	 */
	ERepresentation createMaterialValue(Param value, ERepresentation_context materialsContext) throws SdaiException {
		//create value representation
		ERepresentation rep = 
			SdaiUtils.create(ERepresentation.class, model);
		
		rep.setName(null, "");
		rep.setContext_of_items(null, materialsContext);
		rep.createItems(null);
		
		ParamSdaiWriter writer = new ParamSdaiWriter(model, null);
				
		if(value instanceof StringParam) {
			StringParam sp = (StringParam) value;
			writer.addDescriptiveRepresentationItem(rep, "text", sp.getValue());
		} else if(value instanceof MeasureParam) {			
			MeasureParam mp = (MeasureParam) value;
			writer.addMeasureRepresentationItem(rep, mp.getName(), mp.getDoubleValue(), mp.getUnit());
		} else if (value instanceof DoubleParam) {
			DoubleParam dp = (DoubleParam) value;
			writer.addRealRepresentationItem(rep, dp.getName(), dp.getDoubleValue());
		}
		else {		
			System.out.println("UNHANDLED MATERIAL PROPERTY TYPE");
		}		
		return rep;
	}
	
	/**
	 * Assigns the given material properties to all given {@MIM shape_aspect} instances and associates the material
	 * characteristic value representations with the given {@MIM representation_context}.
	 * 
	 * @param material the material 
	 * @param materialsContext the {@MIM representation_context} for material property values
	 * @param assignments {@MIM shape_aspect} instances that will receive the material characteristic assignments
	 * @throws SdaiException
	 */
	public void assignMaterial(ThermalMaterial material, ERepresentation_context materialsContext, List<EShape_aspect> assignments) throws SdaiException {
		if(assignments.isEmpty())
			return;
		
		MIMpackageQueries pq = new MIMpackageQueriesImpl(model);
		
		EShape_aspect assignment = null;
		if(assignments.size() == 1) {
			assignment = assignments.get(0);
		} else {
			EComposite_group_shape_aspect composite = 
				SdaiUtils.create(EComposite_group_shape_aspect.class, model);
			composite.setName(null, "");
			composite.setProduct_definitional(null, ELogical.FALSE);
			composite.setOf_shape(null, assignments.get(0).getOf_shape(null));
			
			for(EShape_aspect member : assignments) {
				pq.addShapeAspectToComposite(member, composite);				
			}
			assignment = composite;
		}
				
		for(String name : material.getProperties()) {				
			Param value = material.getProperty(name);
			
			ERepresentation valueRep = createMaterialValue(value, materialsContext);
			pq.addShapeAspectProperty(assignment, name, valueRep);
		}		
		pq.addShapeAspectProperty(assignment, "material_name", 
				createMaterialValue(new StringParamImpl("material_name", material.getName()),  materialsContext));	
	}
				
}
