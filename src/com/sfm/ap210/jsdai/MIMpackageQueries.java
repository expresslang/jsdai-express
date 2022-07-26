package com.sfm.ap210.jsdai;

import java.util.Set;

import com.sfm.ap210.jsdai.param.Param;

import jsdai.SFootprint_definition_mim.AFootprint_definition;
import jsdai.SGeometry_schema.EPlacement;
import jsdai.SLayered_interconnect_complex_template_mim.EStructured_template;
import jsdai.SNon_feature_shape_element_mim.ESeating_plane;
import jsdai.SPackage_mim.APackage_terminal;
import jsdai.SPackage_mim.EPackage;
import jsdai.SPackage_mim.EPackage_body;
import jsdai.SPackage_mim.EPackage_terminal;
import jsdai.SPackage_mim.EPackage_terminal_template_definition;
import jsdai.SProduct_property_definition_schema.EShape_aspect;
import jsdai.SProduct_property_representation_schema.EShape_representation;
import jsdai.SProduct_structure_schema.AAssembly_component_usage;
import jsdai.SProduct_structure_schema.EAssembly_component_usage;
import jsdai.lang.SdaiException;

/**
 * An interface for the MIMpackageQueries methods defined in the reference document below.
 * @author James Stori, SFM Technology, Inc.
 * @version 1.3
 * 
 * @see <a href=doc-files/MIMqueries_1.3-Package.pdf>MIMqueries_1.3-Package</a>
 */
public interface MIMpackageQueries {
	
	/**
	 * Returns all {@MIMandARM Package_terminal} of the given {@MIMandARM Package}.
	 * Returns an empty aggregate if no terminals are found.
	 * 
	 * @param e_p the given package
	 * @return an aggregate of package_terminal
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 */
	public APackage_terminal getTerminalsOfPackage(EPackage e_p) throws SdaiException;
	
	/**
	 * Returns all {@MIMandARM Footprint_definition} associated with the given {@MIMandARM Package}.
	 * Returns an empty aggregate if no footprint definitions are found.
	 * 
	 * @param e_p the given package
	 * @return an aggregate of footprint_definition associated with the given package
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 */
	public AFootprint_definition getFootprintsOfPackage(EPackage e_p) throws SdaiException;

	/**
	 * Returns the {@MIMandARM Package_terminal_template_definition} for the given {@MIMandARM Package_terminal} or 
	 * null if no associated terminal template is found.
	 * 
	 * @param e_pt the given package_terminal
	 * @return the associated package_terminal_template_definition
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 */
	public EPackage_terminal_template_definition getTemplateOfPackageTerminal(EPackage_terminal e_pt) throws SdaiException;
	
	/**
	 *  Returns the {@MIM shape_representation} ({@ARM Part_feature_template_shape_model}) of the given {@MIMandARM Package_terminal_template_definition}.
	 * 	Implementation is based on the assumption that there is only one shape_representation associated with the template definition.
	 *  Returns null if no associated shape_representation is found.
	 *  
	 * @param e_pttd the given package_terminal_template_definition
	 * @return the associated shape_representation
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 */
	public EShape_representation getShapeRepresentationOfPackageTerminalTemplate(EPackage_terminal_template_definition e_pttd) throws SdaiException;
	
	/**
	 * Returns the {@MIM shape_representation} ({@ARM Part_feature_template_shape_model}) for the given {@MIMandARM Package_terminal}.
	 * Implementation is based on the assumption that there is only one shape_representation associated with the {@MIMandARM Package_terminal_template_definition} of the terminal.
	 * 
	 * @param e_pt the given package_terminal
	 * @return the associated shape_representation
	 * @throws SdaiException
	 * @MIMinstanceDiagram
	 */
	public EShape_representation getShapeRepresentationOfPackageTerminal(EPackage_terminal e_pt) throws SdaiException;
	
	/**
	 * Returns a qualified {@MIM shape_representation} ({@ARM Geometric_model}) with a particular purpose.
	 * The qualification on the name of the {@MIM shape_representation} supports the mapping requirements
	 * of the subtypes of the ARM AO {@ARM Geometric_model}, including the commonly implemented subtypes
	 * for 2D and 3D shape models and keepouts:<br>
	 * <br>
	 * {@ARM Physical_unit_planar_shape_model}<br>
	 * {@ARM Physical_unit_planar_keepout_shape_model}<br>
	 * {@ARM Physical_unit_3d_shape_model}<br>
	 * {@ARM Physical_unit_3d_keepout_shape_model}<br>
	 * <br>
	 * The shapePurpose argument supports the mapping of the shape_purpose attribute of the 
	 * ARM AOs {@ARM Physical_unit_3d_shape_model}, {@ARM Physical_unit_keepout_shape_model}, 
	 * and {@ARM Physical_unit_planar_shape_model}.
	 * 
	 * The method will return a {@MIM shape_representation} with name = qualifyingName
	 * and shape purpose equal to the given shapePurpose parameter. 
	 * 
	 * @param p the given package
	 * @param qualifyingName the qualifying value for the name attribute of shape_representation
	 * @param shapePurpose the qualifying value for the shape_purpose attribute
	 * @return the qualifying shape_representation
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 */
	public EShape_representation getQualifiedShapeRepresentationOfPackageWithSpecifiedPurpose(EPackage p, String qualifyingName, String shapePurpose) throws SdaiException;
	
	/**
	 * Returns a 2D {@MIM shape_representation} associated with the given {@MIMandARM Package} that satisfies the mapping 
	 * requirements for a {@ARM Physical_unit_planar_shape_model} with a shape purpose of ‘design.’
	 * 
	 * @param e_p the given package
	 * @return the qualifying shape_representation
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 */
	public EShape_representation get2dDesignShapeRepresentationOfPackage(EPackage e_p) throws SdaiException;
	
	/**
	 * Returns a 2D {@MIM shape_representation} associated with the given {@MIMandARM Package} that satisfies the 
	 * mapping requirements for a {@ARM Physical_unit_3d_shape_model} with a shape purpose of ‘design.’
	 * 
	 * @param e_p the given package
	 * @return the qualifying shape_representation
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 */
	public EShape_representation get3dDesignShapeRepresentationOfPackage(EPackage e_p) throws SdaiException;
	
	/**
	 * Finds the {@MIM shape_representation} ({@ARM Geometric_model}) associated with a {@MIM shape_aspect} ({@ARM Shape_element}) such as the 
	 * {@MIMandARM Package_body} or {@MIMandARM Seating_plane} of the {@MIMandARM Package}
	 * Assumes a single shape_representation of the shape_aspect.
	 * Returns null if no shape_representation is found.
	 * 
	 * @param e_sa the given shape_aspect
	 * @return the associated shape_representation
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 */
	public EShape_representation getShapeRepresentationOfShapeAspect(EShape_aspect e_sa) throws SdaiException;
	
	/**
	 * Returns the placement used to position the {@MIM shape_representation} ({@ARM Geometric_model}) of a {@MIM shape_aspect} ({@ARM Shape_element}) with respect to the shape_representation
	 * of its containing shape. In the context of a package model, the shape_aspect (e_sa_sr) may be a {@MIMandARM Package_terminal},
	 * {@MIMandARM Package_body}, or {@MIMandARM Seating_plane}. The containing shape (e_placed_sr) would typically be the design shape of the package.
	 * The method must be qualified by both the package shape representation and the shape representation of the shape_aspect.
	 * If the applicable shape_representations are 3d, the returned placement will be of type {@MIM axis2_placement_3d} ({@ARM Axis_placement_3d}). 
	 * If the applicable shape_representations are planar shape models, the returned placement will be of type {@MIM axis2_placement_2d} ({@ARM Axis_placement_2d}).
	 * 
	 * @param e_sa the given shape_aspect
	 * @param e_sa_sr the shape_representation of the given shape_aspect
	 * @param e_placed_sr the qualifying shape_representation of the containing shape (i.e. package)
	 * @return the placement that positions the shape_representation of the shape_aspect with respect to that of the containing shape.
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 */
	public EPlacement getPlacementOfShapeAspect(EShape_aspect e_sa, EShape_representation e_sa_sr, EShape_representation e_placed_sr) throws SdaiException;

	/**
	 * Returns the {@MIMandARM Package_body} for the given {@MIMandARM Package}.
	 * Returns null if no package_body is found.
	 * 
	 * @param e_p the given package
	 * @return the associated package_body
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 */
	public EPackage_body getBodyOfPackage(EPackage e_p) throws SdaiException;
	
	/**
	 * Returns the {@MIMandARM Seating_plane} for the given {@MIMandARM Package}.
	 * Returns null if no seating_plane is found.
	 * 
	 * @param e_p the given package
	 * @return the associated seating_plane
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 */
	public ESeating_plane getSeatingPlaneOfPackage(EPackage e_p) throws SdaiException;
	
	/**
	 * Returns all template placements composing the given {@MIMandARM Structured_template}.
	 * In the context of a package model, applicable to both {@MIMandARM Footprint_definition} and {@MIMandARM Padstack_definition}.
	 * These {@MIM assembly_component_usage} reflect the MIM mapping of the {@ARM Template_location_in_structured_template}.
	 * 
	 * @param e_st the given {@MIM structured_template}
	 * @return an aggregate of {@MIM assembly_component_usage}
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 */
	public AAssembly_component_usage getAllTTLISTforST(EStructured_template e_st) throws SdaiException;
	
	/**
	 * Returns a {@MIM shape_aspect} representing the {@ARM Part_feature} associated with the given
	 * {@MIM assembly_component_usage} corresponding to a {@ARM Part_feature_based_template_location} subtype of
	 * {@ARM Template_location_in_structured_template}.
	 * In the context of a package model, this query is most commonly used to obtain the {@MIMandARM Package_terminal}
	 * associated with a placement of a {@MIMandARM Padstack_definition} within a {@MIMandARM Footprint_definition}.
	 *  
	 * @param e_tlist the given assembly_component_usage
	 * @return a shape_aspect corresponding to the associated Part_feature
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 */
	public EShape_aspect getPartFeatureForTLIST(EAssembly_component_usage e_tlist) throws SdaiException;
	
	/**
	 * Returns a specific set of read-only parameters ({@link com.sfm.ap210.jsdai.param.Param}) associated with the given {@MIMandARM Package}. 
	 * The set of package parameters to be returned is initialized through the {@link #addPackageParam(String)} method.
	 * An empty set is returned if none of the relevant parameters are found.
	 * 
	 * @param e_p the given package
	 * @return a set of read-only package parameters
	 * @throws SdaiException
	 * @MIMinstanceDiagram
	 * @see #addPackageParam(String)
	 * @see com.sfm.ap210.jsdai.param.Param
	 */
	public Set<Param> getParametricAttributesOfPackage(EPackage e_p) throws SdaiException;
	
	/**
	 * Adds a named package parameter to the set returned by {@link #getParametricAttributesOfPackage(EPackage)}.
	 * Valid package parameters map to selected attributes of the {@ARM Package}.
	 * 
	 * The following package attributes are supported:
	 * 
	 * "mounting technology"
	 * "body clearance above seating plane"
	 * "body clearance below seating plane"
	 * "maximum body height above seating plane"
	 * "maximum body height below seating plane"
	 * "maximum lead length below seating plane"
	 * "maximum seating plane installation offset"
	 * "least lead length below seating plane"
	 * "nominal mounting lead pitch"
	 * "nominal mounting lead pitch"
	 * 
	 * @param name the name of the package parameter to be included.
	 * @see #getParametricAttributesOfPackage(EPackage)
	 */
	public void addPackageParam(String name);
	
	/**
	 * Returns a specific set of read-only parameters ({@link com.sfm.ap210.jsdai.param.Param}) associated with the given {@MIMandARM Package_terminal_template_definition}.
	 * Provides a simplified interface for accessing the MIM mappings of the parametric attributes of the {@ARM Package_terminal_template_definition}.
	 * The set of terminal template parameters to be returned is initialized through the {@link #addTerminalParam(String)} method.
	 * See {@link #addTerminalParam(String)} for a list of supported attributes.
	 * An empty set is returned if none of the relevant parameters are found.
	 * The attributes of the {@ARM Package_terminal_template_definition} are mapped to instances of {@MIM property_definition} 
	 * with identifying names. Values are extracted directly from the {@MIM property_definition} description in the case of enumeration
	 * attributes, and from the associated representation in the case of physical measurements.
	 * An empty set is returned if none of the relevant parameters are found. 
	 *
	 * @param e_pttd the given {@MIM package_terminal_template_definition}
	 * @return a set of read-only terminal template parameters
	 * @throws SdaiException
	 * @see #addTerminalParam(String)
	 * @see com.sfm.ap210.jsdai.param.Param
	 */
	public Set<Param> getParametricAttributesOfTerminalTemplate(EPackage_terminal_template_definition e_pttd) throws SdaiException;
	
	/**
	 * Adds a named terminal template parameter to the set returned by {@link #getParametricAttributesOfTerminalTemplate(EPackage_terminal_template_definition)}.
	 * Valid terminal template parameters map to selected attributes of the {@ARM Package_terminal_template_definition}.
	 * 
	 * The following package terminal template attributes are supported:
	 * 
	 * "predefined lead form"
	 * "seating plane intersection"
	 * "terminal diametrical extent"
	 * 
	 * @param paramName the name of the terminal template parameter to be included.

	 */
	public void addTerminalParam(String paramName);

}
