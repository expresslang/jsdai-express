package com.sfm.ap210.jsdai;

import java.util.Set;

import jsdai.SBasic_data_representation_mim.EBoolean_representation_item;
import jsdai.SMeasure_schema.EMeasure_with_unit;
import jsdai.SPackage_mim.EPackage;
import jsdai.SPackage_mim.EPackage_terminal_template_definition;
import jsdai.SProduct_definition_schema.EProduct;
import jsdai.SProduct_property_definition_schema.EProperty_definition;
import jsdai.SQualified_measure_schema.EDescriptive_representation_item;
import jsdai.SRepresentation_schema.ERepresentation_item;
import jsdai.lang.SdaiException;

import com.sfm.ap210.jsdai.param.Param;

/**
 * An interface for the MIMparamQueries methods defined in the reference document below.
 * 
 * @author James Stori, SFM Technology, Inc.
 * @version 1.3
 * 
 * @see <a href=doc-files/MIMqueries_1.3-Parameters.pdf>MIMqueries_1.3-Parameters</a>
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
	 * @return an implementation of {@link com.sfm.ap210.jsdai.param.MeasureParam} or {@link com.sfm.ap210.jsdai.param.IntegerParam} containing the represented data
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
