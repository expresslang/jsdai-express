package com.sfm.ap210.jsdai;

import java.util.Set;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.*;
import jsdai.lang.SdaiException;

import com.sfm.ap210.jsdai.param.Param;
import com.sfm.ap210.jsdai.param.ParameterAssignment;

/**
 * An interface for the MIMparamQueries methods.
 * 
 * @author James Stori, SFM Technology, Inc.
 * @version 1.4
 * 
 */
public interface MIMparamQueries {
	
	/**
	 * Extracts parametric data from the most commonly used {@MIM property_definition} representations
	 * for boolean, textual, count, and measure based parametric data. Returns the extracted name and value through
	 * an implementing class of the {@link com.sfm.ap210.jsdai.param.Param} interface.
	 * 
	 * @param e_pd the given property_definition
	 * @return an implementation of Param containing the represented data.
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 */
	public Param parametricAttributeForPropertyDefinition(EProperty_definition e_pd) throws SdaiException;
	
	/**
	 * Extracts parametric data and associated model parameters from the most commonly used implementations of {@MIMandARM Parameter_assignment}.
	 * Implementation supports boolean, textual, count, and measure based parametric data.
	 * Returns a set of model parameters and associated parameter representations through an implementing class of the {@link com.sfm.ap210.jsdai.param.Param} interface.
	 * 
	 * @param a_pa
	 * @return
	 * @throws SdaiException
	 */
	public Set<ParameterAssignment> allParameterAssignment(AParameter_assignment a_pa) throws SdaiException;
	
	/**
	 * Extracts parametric data from the most commonly used implementations of {@MIMandARM Parameter_assignment}.
	 * Implementation supports boolean, textual, count, and measure based parametric data.
	 * Returns a set of model parameters and associated parameter representations through an implementing class of the {@link com.sfm.ap210.jsdai.param.Param} interface.
	 * 
	 * @param a_pa
	 * @return
	 * @throws SdaiException
	 */
	public Set<Param> allParametersForParameterAssignment(AParameter_assignment a_pa) throws SdaiException;
	
	/**
	 * Extracts parametric data from the most commonly used implementations of {@MIMandARM Parameter_assignment} related to a
	 * given {@MIMandARM Product} through a {@MIM product_specific_parameter_value_assignment}.
	 * Implementation supports boolean, textual, count, and measure based parametric data.
	 * Returns a set of associated product parameters through an implementing class of the {@link com.sfm.ap210.jsdai.param.Param} interface.
	 * 
	 * @param e_p the given product
	 * @return a set of {@link com.sfm.ap210.jsdai.param.Param} containing the extracted product specific parameters
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 */
	public Set<Param> allParametersForProduct(EProduct e_p) throws SdaiException;
	
	/**
	 * Extracts model parameters and associated parameter representations from the most commonly used implementations of 
	 * {@MIMandARM Parameter_assignment} related to a given {@MIMandARM Product} through a 
	 * {@MIM product_specific_parameter_value_assignment}.
	 * Implementation supports boolean, textual, count, and measure based parametric data.
	 * Returns a set of associated product parameters through an implementing class of the {@link com.sfm.ap210.jsdai.param.Param} interface.
	 * 
	 * @param e_p
	 * @return
	 * @throws SdaiException
	 */
	public Set<ParameterAssignment> allParameterAssignmentForProduct(EProduct e_p) throws SdaiException;
	
	/**
	 * Extracts parametric data from the certain commonly used {@MIM representation_item} representations
	 * for boolean, textual, count, and measure based parametric data. Returns the extracted name and value through
	 * an implementing class of the {@link com.sfm.ap210.jsdai.param.Param} interface.
	 * 
	 * @param paramName the name of the parameter associated with the given representation_item
	 * @param e_ri the given representation_item
	 * @return an implementation of {@link com.sfm.ap210.jsdai.param.Param} containing the represented data
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 */
	public Param parameterForRepresentationItem(String paramName, ERepresentation_item e_ri) throws SdaiException;
	
	/**
	 * Extracts parametric data from certain commonly used {@MIM measure_with_unit} subtypes and representations
	 * for integral parameters, area measures, length measures, time measures, and temperature measures.
	 * Returns the extracted name and value through an implementing class of the {@link com.sfm.ap210.jsdai.param.Param} interface.
	 * 
	 * @param paramName the name of the parameter associated with the given measure_with_unit
	 * @param e_mwu the given measure_with_unit
	 * @return an implementation of {@link com.sfm.ap210.jsdai.param.Param}
	 * 
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 */
	public Param measureWithUnitParameter(String paramName, EMeasure_with_unit e_mwu) throws SdaiException;
	
	/**
	 * Extracts parametric data from certain time and temperature representations.
	 * Present implementation only supports second and degree celcius measures.
	 * Returns the extracted name and value through an implementing class of the {@link com.sfm.ap210.jsdai.param.Param} interface.
	 * 
	 * @param paramName the name of the parameter associated with the given measure_with_unit 
	 * @param e_mwu the given measure_with_unit
	 * @return an implementation of {@link com.sfm.ap210.jsdai.param.MeasureParam} containing the represented data
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 */
	public Param otherMeasureWithUnitParameter(String paramName, EMeasure_with_unit e_mwu) throws SdaiException;
}
