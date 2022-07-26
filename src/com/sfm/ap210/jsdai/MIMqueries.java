package com.sfm.ap210.jsdai;

import jsdai.SAssembly_module_design_mim.ALayered_assembly_module_design_view;
import jsdai.SAssembly_module_design_mim.APackaged_component;
import jsdai.SAssembly_module_design_mim.ELayered_assembly_module_design_view;
import jsdai.SAssembly_module_design_mim.EPackaged_component;
import jsdai.SAssembly_module_with_interconnect_component_mim.AInterconnect_module_component;
import jsdai.SAssembly_technology_mim.AAssembly_joint;
import jsdai.SAssembly_technology_mim.EAssembly_joint;
import jsdai.SClassification_schema.AClass;
import jsdai.SFabrication_technology_mim.AStratum_technology_occurrence_link;
import jsdai.SFabrication_technology_mim.EStratum_stack_model;
import jsdai.SFabrication_technology_mim.EStratum_technology;
import jsdai.SFabrication_technology_mim.EStratum_technology_occurrence_link;
import jsdai.SGeometry_schema.ECartesian_transformation_operator_2d;
import jsdai.SInterconnect_module_usage_view_mim.EInterconnect_module_interface_terminal;
import jsdai.SInterconnect_module_usage_view_mim.ELayered_interconnect_module_usage_view;
import jsdai.SLand_mim.APlated_passage_dependent_land;
import jsdai.SLand_mim.EContact_size_dependent_land;
import jsdai.SLayered_interconnect_module_design_mim.AConductive_interconnect_element;
import jsdai.SLayered_interconnect_module_design_mim.AFootprint_occurrence;
import jsdai.SLayered_interconnect_module_design_mim.AGeneric_laminate_text_component;
import jsdai.SLayered_interconnect_module_design_mim.ALaminate_component;
import jsdai.SLayered_interconnect_module_design_mim.ALaminate_component_feature;
import jsdai.SLayered_interconnect_module_design_mim.ALaminate_text_string_component;
import jsdai.SLayered_interconnect_module_design_mim.ALayer_connection_point;
import jsdai.SLayered_interconnect_module_design_mim.ALayered_interconnect_module_design_view;
import jsdai.SLayered_interconnect_module_design_mim.ALayered_interconnect_panel_design_view;
import jsdai.SLayered_interconnect_module_design_mim.AMaterial_removal_laminate_component;
import jsdai.SLayered_interconnect_module_design_mim.AMulti_stratum_special_symbol_component;
import jsdai.SLayered_interconnect_module_design_mim.APadstack_occurrence;
import jsdai.SLayered_interconnect_module_design_mim.APhysical_network;
import jsdai.SLayered_interconnect_module_design_mim.ASingle_stratum_special_symbol_component;
import jsdai.SLayered_interconnect_module_design_mim.AStratum;
import jsdai.SLayered_interconnect_module_design_mim.AStratum_feature_template_component;
import jsdai.SLayered_interconnect_module_design_mim.AStructured_layout_component;
import jsdai.SLayered_interconnect_module_design_mim.EAdditive_laminate_text_component;
import jsdai.SLayered_interconnect_module_design_mim.EConnected_area_component;
import jsdai.SLayered_interconnect_module_design_mim.EGeneric_laminate_text_component;
import jsdai.SLayered_interconnect_module_design_mim.EInter_stratum_feature;
import jsdai.SLayered_interconnect_module_design_mim.EInterconnect_module_edge;
import jsdai.SLayered_interconnect_module_design_mim.ELaminate_component;
import jsdai.SLayered_interconnect_module_design_mim.ELaminate_text_string_component;
import jsdai.SLayered_interconnect_module_design_mim.ELayer_connection_point;
import jsdai.SLayered_interconnect_module_design_mim.ELayered_interconnect_module_design_view;
import jsdai.SLayered_interconnect_module_design_mim.ELayered_interconnect_panel_design_view;
import jsdai.SLayered_interconnect_module_design_mim.EPhysical_network;
import jsdai.SLayered_interconnect_module_design_mim.EStratum;
import jsdai.SLayered_interconnect_module_design_mim.EStratum_feature;
import jsdai.SLayered_interconnect_module_design_mim.EStratum_feature_template_component;
import jsdai.SLayered_interconnect_module_design_mim.EStructured_layout_component;
import jsdai.SLayered_interconnect_module_design_mim.EStructured_layout_component_sub_assembly_relationship;
import jsdai.SMaterial_property_definition_schema.EMaterial_designation;
import jsdai.SModel_parameter_mim.AParameter_assignment;
import jsdai.SPackage_mim.EPackage;
import jsdai.SPackaged_part_black_box_model_mim.EPackaged_part_terminal;
import jsdai.SPart_feature_function_mim.APart_tooling_feature;
import jsdai.SPart_feature_function_mim.EPart_tooling_feature;
import jsdai.SPhysical_component_feature_mim.EPhysical_component_terminal;
import jsdai.SPhysical_unit_design_view_mim.ANext_assembly_usage_occurrence_relationship;
import jsdai.SPhysical_unit_design_view_mim.EAssembly_component;
import jsdai.SPhysical_unit_design_view_mim.ENext_assembly_usage_occurrence_relationship;
import jsdai.SPresentation_definition_schema.EText_literal;
import jsdai.SProduct_definition_schema.AProduct;
import jsdai.SProduct_definition_schema.AProduct_definition;
import jsdai.SProduct_definition_schema.EProduct;
import jsdai.SProduct_definition_schema.EProduct_definition;
import jsdai.SProduct_property_definition_schema.EProduct_definition_shape;
import jsdai.SProduct_property_representation_schema.AShape_representation;
import jsdai.SProduct_property_representation_schema.EShape_representation;
import jsdai.SProduct_structure_schema.EAssembly_component_usage;
import jsdai.SRepresentation_schema.EMapped_item;
import jsdai.SRepresentation_schema.ERepresentation;
import jsdai.lang.SdaiException;

/**
 * An interface for the MIMqueries methods defined in the reference documents below.
 * @author James Stori, SFM Technology, Inc.
 * @version 1.3
 * 
 * @see <a href=doc-files/MIMqueries_1.3-LocationAndShapeOfAssemblyComponents.pdf>MIMqueries_1.3-LocationAndShapeOfAssemblyComponents</a>
 * @see <a href=doc-files/MIMqueries_1.3-StratumAndStratumFeaturesAndInterStratumFeatures.pdf>MIMqueries_1.3-StratumAndStratumFeaturesAndInterStratumFeatures</a>
 * @see <a href=doc-files/MIMqueries_1.3-Pca.pdf>MIMqueries_1.3-Pca</a>
 * @see <a href=doc-files/MIMqueries_1.3-Pcb.pdf>MIMqueries_1.3-Pcb</a>
 * @see <a href=doc-files/MIMqueries_1.3-LayerConnectionPointConnectivityAndPhysicalNets.pdf>MIMqueries_1.3-LayerConnectionPointConnectivityAndPhysicalNets</a>
 */
public interface MIMqueries {
	
	public void setOps(MIMops ops);
	
	/**
	 * Returns a {@MIM shape_representation} ({@ARM Geometric_model} for an {@MIMandARM Assembly_component} if applicable. 
	 * It is assumed that the shape_representation of the assembly component will be either directly related to the assembly_component ({@ARM Assembly_component_2d_shape_model}) 
	 * or related to a {@MIMandARM Geometric_template} in the case of a {@MIMandARM Laminate_component} with a geometric_template ({@ARM Part_template_shape_model}).
	 * If neither case is true, the query returns null.
	 * This query is not applicable for a {@MIMandARM Generic_laminate_text_component}.
	 *
	 * @param ac the given assembly_component
	 * @return the shape_representation of the assembly_component
	 * @throws SdaiException
	 * @MIMinstanceDiagram
	 * @see "MIMqueries_1.3-LocationAndShapeOfAssemblyComponents"
	 */
	public EShape_representation getShapeRepresentationOfAssemblyComponent(EAssembly_component ac) throws SdaiException;
	
	/**
	 * Returns a 'ppsm' {@MIM shape_representation} ({@ARM Planar_projected_shape_model}) for a {@MIM product_definition_shape} directly related through a {@MIM shape_definition_representation}. 
	 * This method is commonly used to obtain the shape_representation for a {@MIMandARM Structured_template}, a {@MIMandARM Geometric_template}, 
	 * an {@MIMandARM Layered_assembly_module_design_view} or an {@MIMandARM Layered_interconnect_module_design_view}.
	 * 
	 * @param pds the given product_definition_shape
	 * @return the associated 'ppsm' shape_representation
	 * @throws SdaiException
	 * @MIMinstanceDiagram
	 * @see "MIMqueries_1.3-LocationAndShapeOfAssemblyComponents"
	 * 
	 */
	public EShape_representation getShapeRepresentationOfProductDefinitionShape(EProduct_definition_shape pds) throws SdaiException;

	/**
	 * Returns the {@MIM shape_representation} ({@ARM Geometric_model} of a {@MIMandARM Structured_layout_component}.
	 * 
	 * @param slc the given structured_layout_component
	 * @return the associated shape_representation
	 * @throws SdaiException
	 * @MIMinstanceDiagram
	 * @see "MIMqueries_1.3-LocationAndShapeOfAssemblyComponents"
	 */
	public EShape_representation getShapeRepresentationOfSLC(EStructured_layout_component slc) throws SdaiException;
	
	/**
	 * Returns a {@MIM shape_representation} ({@ARM Geometric_model} for a {@MIMandARM Generic_laminate_text_component} (an individual character)
	 * If the geometry is not explicitly represented in a {@MIMandARM Solid_character_glyph_2d_symbol}, returns null.
	 * 
	 * @param gltc the given generic_laminate_text_component
	 * @return the shape_representation of the generic_laminate_text_component
	 * @throws SdaiException
	 * @MIMinstanceDiagram
	 * @see "MIMqueries_1.3-LocationAndShapeOfAssemblyComponents"
	 */
	public EShape_representation getShapeRepresentationOfGenericLaminateTextComponent(EGeneric_laminate_text_component gltc) throws SdaiException;

	/**
	 * Returns a {@MIM cartesian_transformation_operator_2d} ({@ARM Cartesian_transformation_2d}) in the case that a relating {@MIMandARM Component_2d_location} exists
	 * for the given {@MIMandARM Next_assembly_usage_occurrence_relationship}. The c2dl is qualified by the two given {@MIM shape_representation} ({@ARM Geometric_model}).
	 * If no such transformation exists, the query returns null.
	 * 
	 * @param nauor
	 * @param sr1 the shape_representation of the assembly_component
	 * @param sr2 the shape_representation of the pcb
	 * @return the cartesian_transformation_operator_2d locating sr1 relating to sr2
	 * @throws SdaiException
	 * @MIMinstanceDiagram
	 * @see "MIMqueries_1.3-LocationAndShapeOfAssemblyComponents" 
	 */
	public ECartesian_transformation_operator_2d getCartesianTransformationOfNAUOR(
			ENext_assembly_usage_occurrence_relationship nauor,
			EShape_representation sr1,
			EShape_representation sr2) throws SdaiException;
	
	/**
	 * Returns the {@MIM mapped_item} corresponding to the given {@MIM assembly_component_usage} that is qualified by the given {@MIM shape_representation}
	 * The assembly_component_usage is the MIM representation of the {@ARM Template_location_in_structured_template} while the mapped_item is the representation
	 * of the {@ARM Template_location_in_structured_template_transform}. 
	 * 
	 * @param e_acu	the given assembly_component_usage
	 * @param e_assemblyShape the shape_representation of the assembly_shape
	 * @return the qualified mapped_item corresponding to the given assembly_component_usage
	 * @throws SdaiException
	 * @MIMinstanceDiagram
	 * @see "MIMqueries_1.3-LocationAndShapeOfAssemblyComponents" 
	 */
	public EMapped_item getTLISTTforTLIST(EAssembly_component_usage e_acu, EShape_representation e_assemblyShape) throws SdaiException;

	/**
	 * Returns a {@link com.sfm.ap210.jsdai.MIMlocation} containing the one or two {@MIM axis2_placement_2d} ({@ARM Axis_placement_2d}) transforms associated with the ‘first location’
	 * and the ‘second location’ (if applicable) of the {@MIMandARM Structured_layout_component_sub_assembly_relationship}.
	 * 
	 * @param slcsar
	 * @param sr1 currently unused by implementation - the shape_representation of the assembly_component
	 * @param sr2 the shape_representation of the structured_layout_component
	 * @return a MIMlocation containing one or two axis2_placement_2d transforms
	 * @throws SdaiException
	 * @MIMinstanceDiagram
	 * @see com.sfm.ap210.jsdai.MIMlocation
	 * @see "MIMqueries_1.3-LocationAndShapeOfAssemblyComponents"
	 */
	public MIMlocation getAxisPlacementOfSLCSAR(
			EStructured_layout_component_sub_assembly_relationship slcsar,
			EShape_representation sr1,
			EShape_representation sr2) throws SdaiException;
	
	/**
	 * Returns a {@link com.sfm.ap210.jsdai.MIMlocation} containing between 0 and 3 transformations that must be applied sequentially to locate the {@MIM shape_representation} ({@ARM Geometric_model}
	 * of the {@MIMandARM Laminate_component} with respect to the shape_representation of the {@MIMandARM Layered_interconnect_module_design_view}.
	 * Query may be applied to either a laminate_component that is part of a {@MIMandARM Structured_layout_component} or a 
	 * laminate_component located directly on the pcb.
	 * 
	 * @param lc the given laminate_component
	 * @param sr1 the shape_representation of the laminate_component
	 * @param sr2 the shape_representation of the structured_layout_component
	 * @return a MIMlocation containing between 0 and 3 transformations
	 * @throws SdaiException
	 * @MIMinstanceDiagram
	 * @see com.sfm.ap210.jsdai.MIMlocation
	 * @see "MIMqueries_1.3-LocationAndShapeOfAssemblyComponents"
	 */
	public MIMlocation getLocationOfLaminateComponent(ELaminate_component lc,
			EShape_representation sr1,
			EShape_representation sr2) throws SdaiException;
	
	/**
	 * Returns between 1 and 2 {@MIM axis2_placement_2d} ({@ARM Axis_placement_2d}) that must be applied sequentially
	 * to locate the {@MIM shape_representation} ({@ARM Geometric_model} of the {@MIMandARM Assembly_component} with respect to
	 * the shape_representation of the {@MIMandARM Structured_layout_component}.
	 * It is possible for the assembly_component itself to be a (nested) structured_layout_component.
	 * 
	 * @param ac the given assembly_component
	 * @param slcsar the relating structured_layout_component_sub_assembly_relationship
	 * @param slc the given structured_layout_component
	 * @param srOfac currently unused - the shape_representation of the given assembly_component
	 * @return a MIMlocation containing one or two Axis2_placement_2d transforms
	 * @throws SdaiException
	 * @MIMinstanceDiagram
	 * @see "MIMqueries_1.3-LocationAndShapeOfAssemblyComponents"
	 */
	public MIMlocation getLocationOfAssemblyComponentInSLC(EAssembly_component ac,
			EStructured_layout_component_sub_assembly_relationship slcsar,
			EStructured_layout_component slc,
			EShape_representation srOfac) throws SdaiException;

	/**
	 * Returns the {@MIMandARM Stratum_technology} used by the given {@MIMandARM Stratum}
	 * 
	 * @param s the given stratum
	 * @return the stratum_technology used by the given stratum
	 * @throws SdaiException
	 * @MIMinstanceDiagram
	 * @see "MIMqueries_1.3-StratumAndStratumFeaturesAndInterStratumFeatures"
	 */
	public EStratum_technology getStratumTechnologyOfStratum(EStratum s) throws SdaiException;	
	
	/** 
	 * Returns a {@MIM representation} containing the {@ARM Length_tolerance_characteristic} of the 
	 * thickness of the given stratum. The stratum_thickness is an attribute of the {@MIMandARM Stratum_technology} of the stratum.
	 * 
	 * @param s the given stratum
	 * @return a representation containing the length tolerance characteristic of the given stratum
	 * @throws SdaiException
	 * @MIMinstanceDiagram
	 * @see "MIMqueries_1.3-StratumAndStratumFeaturesAndInterStratumFeatures"
	 */
	public ERepresentation getThicknessOfStratum(EStratum s) throws SdaiException;
	
	/**
	 * Returns a string describing the ‘layer purpose’ of the {@MIMandARM Stratum_technology} associated with the given {@MIMandARM Stratum}.
	 * or null if no such description exists.
	 * The 'layer purpose' can be obtained for either a design or a documentation layer. 
	 * pre_defined_documentation_layer_purpose is an optional attribute of {@ARM Documentation_layer_technology} and design_layer_purpose is an optional attribute of {@ARM Design_layer_technology}.
	 *  
	 * @param e_s the given stratum
	 * @return the 'layer purpose' descriptive string
	 * @throws SdaiException
	 * @MIMinstanceDiagram
	 * @see "MIMqueries_1.3-StratumAndStratumFeaturesAndInterStratumFeatures"
	 */
	public String getLayerPurposeOfStratum(EStratum e_s) throws SdaiException;
	
	/**
	 * Returns a {@MIM material_designation} ({@ARM Material_identification}) for the {@MIMandARM Stratum_technology} associated with the given {@MIMandARM Stratum}..
	 * stratum_material is an inverse attribute of {@ARM Stratum_technology}
	 *  
	 * @param e_s the given stratum
	 * @return a representation containing the material_designation of the given stratum
	 * @throws SdaiException
	 * @MIMinstanceDiagram
	 * @see "MIMqueries_1.3-StratumAndStratumFeaturesAndInterStratumFeatures"
	 */
	public EMaterial_designation getMaterialDesignationOfStratum(EStratum e_s) throws SdaiException;
	
	/**
	 * Returns the associated {@MIMandARM Stratum} of the given {@MIMandARM Stratum_feature}.
	 * 
	 * @param sf the given stratum_feature
	 * @return the associated stratum
	 * @throws SdaiException
	 * @MIMinstanceDiagram
	 * @see "MIMqueries_1.3-StratumAndStratumFeaturesAndInterStratumFeatures"
	 */
	public EStratum getStratumOfStratumFeature(EStratum_feature sf) throws SdaiException;
	
	/**
	 * Returns the associated {@MIMandARM Stratum} of the given {@MIMandARM Laminate_component} if a direct relationship to the stratum exists.
	 * Applicable for both {@MIMandARM Stratum_feature_template_component} and {@MIMandARM Material_removal_laminate_component}.
	 * 
	 * @param lc the given laminate_component
	 * @return the associated stratum
	 * @throws SdaiException
	 * @MIMinstanceDiagram
	 * @see "MIMqueries_1.3-StratumAndStratumFeaturesAndInterStratumFeatures"
	 */
	public EStratum getStratumOfLC(ELaminate_component lc) throws SdaiException;
	
	/**
	 * Returns the associated {@MIMandARM Stratum_feature} of the given {@MIMandARM Stratum_feature_template_component}.
	 * 
	 * Use of this query on {@MIMandARM Stratum_feature_template_component} associated with {@MIMandARM Material_removal_laminate_component} 
	 * should be avoided. Execution will be slow, and often unsuccessful for such components (typically {@MIMandARM Area_component})
	 * relating to the mrlc. The reason is that the area components have many "design intent"
	 * relationships, but not "implementation" relationships. These area_components have often been "replaced by" other area
	 * components, which is the reason for that there is no associated stratum feature (they are typically "replaced by"
	 * multiple positive connected regions, and so they cannot map to a single stratum feature).
	 * 
	 * @param sftc the given stratum_feature_template_component
	 * @return the associated stratum_feature
	 * @throws SdaiException
	 * @MIMinstanceDiagram
	 * @see "MIMqueries_1.3-StratumAndStratumFeaturesAndInterStratumFeatures"
	 */
	public EStratum_feature getStratumFeatureOfSFTC(EStratum_feature_template_component sftc) throws SdaiException;

	/**
	 * Returns the associated {@MIMandARM Stratum_feature} of the given {@MIMandARM Additive_laminate_text_component}.
	 * 
	 * @param altc the given additive_laminate_text_component
	 * @return the associated stratum_feature
	 * @throws SdaiException
	 * @MIMinstanceDiagram
	 * @see "MIMqueries_1.3-StratumAndStratumFeaturesAndInterStratumFeatures"
	 */
	public EStratum_feature getStratumFeatureOfALTC(EAdditive_laminate_text_component altc) throws SdaiException;
	
	/**
	 * Returns the associated {@MIMandARM Stratum_feature_template_component} of either a {@MIMandARM Material_removal_laminate_component} or a
	 * {@MIMandARM Material_removal_laminate_text_component}. Note that it is not required that the mrlc or mrltc have an associated
	 * sftc. Although uncommon, it is possible for the material removal region to be associated only with the stratum itself.
	 * In such a case, this query returns null.
	 * 
	 * @param lc the given material_removal_laminate_component or material_removal_laminate_text_component
	 * @return the associated stratum_feature_template_component
	 * @throws SdaiException
	 * @MIMinstanceDiagram
	 * @see "MIMqueries_1.3-StratumAndStratumFeaturesAndInterStratumFeatures"
	 */
	public EStratum_feature_template_component getSFTCofMRLC(ELaminate_component lc) throws SdaiException;

	/**
	 * Returns the precedent {@MIMandARM Stratum} for the given stratum in the given {@MIMandARM Stratum_stack_model}.
	 * It is possible for there to exist multiple adjacent precedent stratum.
	 * In order to support this general stack-up model, it is preferable to use the
	 * query {@link #getAllAdjacentPrecedentStratum(EStratum, EStratum_stack_model)}
	 * Note: precedent -> closer to the “top” side of the pcb.
	 * The ‘primary design layer stratum’ is the {@MIMandARM Design_layer_stratum} that is closest to the top.
	 * 
	 * @param currentStratum the given stratum
	 * @param stackModel the given stratum_stack_model
	 * @return the precedent stratum
	 * @throws SdaiException
	 * @MIMinstanceDiagram
	 * @see #getAllAdjacentPrecedentStratum(EStratum, EStratum_stack_model)
	 * @see "MIMqueries_1.3-StratumAndStratumFeaturesAndInterStratumFeatures"
	 */
	public EStratum getPrecedentStratum(EStratum currentStratum, EStratum_stack_model stackModel) throws SdaiException;	
	
	/**
	 * Returns all adjacent precedent {@MIMandARM Stratum} for the given stratum in the given {@MIMandARM Stratum_stack_model}.
	 * Note: precedent -> closer to the “top” side of the pcb. The ‘primary design layer stratum’ is the {@MIMandARM Design_layer_stratum} that is closest to the top.
	 * 
	 * @param currentStratum the given stratum
	 * @param stackModel the given stratum_stack_model
	 * @return an aggregate containing all adjacent precedent stratum
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 */
	public AStratum getAllAdjacentPrecedentStratum(EStratum currentStratum, EStratum_stack_model stackModel) throws SdaiException;
	
	/**
	 * Returns the subsequent {@MIMandARM Stratum} for the given stratum in the given {@MIMandARM Stratum_stack_model}.
	 * It is possible for there to exist multiple adjacent subsequent stratum.
	 * In order to support this general stack-up model, it is preferable to use the
	 * query {@link #getAllAdjacentSubsequentStratum(EStratum, EStratum_stack_model)}
	 * Note: subsequent -> closer to the “bottom” side of the pcb.
	 * The ‘primary design layer stratum’ is the {@MIMandARM Design_layer_stratum} that is closest to the top.
	 * 
	 * @param currentStratum the given stratum
	 * @param stackModel the given stratum_stack_model
	 * @return the subsequent stratum
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see #getAllAdjacentSubsequentStratum(EStratum, EStratum_stack_model)
	 * @see "MIMqueries_1.3-StratumAndStratumFeaturesAndInterStratumFeatures"
	 */
	public EStratum getSubsequentStratum(EStratum currentStratum, EStratum_stack_model stackModel) throws SdaiException;
	
	/**
	 * Returns all adjacent subsequent stratum for the given stratum in the given {@MIMandARM Stratum_stack_model}.
	 * Note: subsequent -> closer to the “bottom” side of the pcb. The ‘primary design layer stratum’ is the {@MIMandARM Design_layer_stratum} that is closest to the top.
	 * 
	 * @param currentStratum the given stratum
	 * @param stackModel the given stratum_stack_model
	 * @return an aggregate containing all adjacent subsequent stratum
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 */
	public AStratum getAllAdjacentSubsequentStratum(EStratum currentStratum, EStratum_stack_model stackModel) throws SdaiException;
	
	/**
	 * Returns an aggregate of {@MIMandARM Stratum_technology_occurrence_link} that comprise the vertical extent of the given {@MIMandARM Inter_stratum_feature}.
	 * 
	 * @param isf the given inter_stratum_feature
	 * @return an aggregate containing all stratum_technology_occurrence_link that comprise the vertical extent
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-StratumAndStratumFeaturesAndInterStratumFeatures"
	 */
	public AStratum_technology_occurrence_link getAllSTOLinVerticalExtentOfInterStratumFeature(EInter_stratum_feature isf) throws SdaiException;	
	
	/**
	 * Returns the most precedent (closest to the "top") {@MIMandARM Stratum_technology_occurrence_link} in a given contiguous set of STOL. If the given set of STOL is not contiguous, 
	 * the implementation is not guaranteed to return the most precedent in the set. 
	 * 
	 * @param a_stol an aggregate containing the given contiguous set of stratum_technology_occurrence_link
	 * @return the most precedent stratum_technology_occurrence_link in the given aggregate
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-StratumAndStratumFeaturesAndInterStratumFeatures"
	 */
	public EStratum_technology_occurrence_link getMostPrecedentSTOLinContiguousSetOfSTOL(AStratum_technology_occurrence_link a_stol) throws SdaiException;
	
	/**
	 * Returns the most subsequent (closest to the "bottom") {@MIMandARM Stratum_technology_occurrence_link} in a given contiguous set of STOL. If the given set of STOL is not contiguous,
	 * the implementation is not guaranteed to return the most precedent in the set.
	 * 
	 * @param a_stol an aggregate containing the given contiguous set of stratum_technology_occurrence_link
	 * @return the most subsequent stratum_technology_occurrence_link in the given aggregate
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-StratumAndStratumFeaturesAndInterStratumFeatures"
	 */
	public EStratum_technology_occurrence_link getMostSubsequentSTOLinContiguousSetOfSTOL(AStratum_technology_occurrence_link a_stol) throws SdaiException;
		
	/**
	 * Returns the most precedent (closest to the "top") {@MIMandARM Stratum} corresponding to a given contiguous set of {@MIMandARM Stratum_technology_occurrence_link}. If the given set of STOL
	 * is not contiguous, the implementation is not guaranteed to return the most precedent in the set.
	 *  
	 * @param a_stol an aggregate containing the given contiguous set of stratum_technology_occurrence_link
	 * @return the most precedent stratum
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-StratumAndStratumFeaturesAndInterStratumFeatures"
	 */
	public EStratum getMostPrecedentStratumInContiguousSetOfSTOL(AStratum_technology_occurrence_link a_stol) throws SdaiException;

	/**
	 * Returns the most subsequent (closest to the "bottom") {@MIMandARM Stratum} corresponding to a given contiguous set of {@MIMandARM Stratum_technology_occurrence_link}. If the given set of STOL
	 * is not contiguous, the implementation is not guaranteed to return the most subsequent in the set.
	 * 
	 * @param a_stol an aggregate containing the given contiguous set of stratum_technology_occurrence_link
	 * @return the most subsequent stratum
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-StratumAndStratumFeaturesAndInterStratumFeatures"
	 */
	public EStratum getMostSubsequentStratumInContiguousSetOfSTOL(AStratum_technology_occurrence_link a_stol) throws SdaiException;
	
	/**
	 * Returns a pair of {@MIMandARM Stratum} corresponding to the most precedent and most subsequent stratum
	 * included in the vertical extent of the given {@MIMandARM Inter_stratum_feature}.
	 * 
	 * @param e_isf the given inter_stratum_feature
	 * @return a StratumSpan containing a pair of stratum corresponding to the most precedent and most subsequent stratum
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-StratumAndStratumFeaturesAndInterStratumFeatures"
	 */
	public StratumSpan getSpanOfInterStratumFeature(EInter_stratum_feature e_isf) throws SdaiException;
	
	/**
	 * Returns an aggregate of all {@MIM product} entities in the model that are associated with a given {@MIM product_related_product_category}.
	 * 
	 * @param categoryName the name of the {@MIM product_related_product_category}
	 * @return an aggregate of associated {@MIM product}
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pca"
	 */
	public AProduct getAllProductsOfASpecificPRPC(String categoryName) throws SdaiException;
	
	/**
	 * Returns an aggregate of all {@MIM product} entities in the model satisfying the MIM mapping of the ARM AO {@ARM Part}.
	 * Note: raw materials are not included in the results of this query.
	 * These products have an associated {@MIM product_related_product_category} of ‘part’.
	 * 
	 * @return an aggregate of {@MIM product} satisfying the MIM mapping of the ARM AO {@ARM Part}.
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pca"
	 */
	public AProduct getAllParts() throws SdaiException;
	
	/**
	 * Returns an aggregate of all {@MIM product} entities in the model satisfying the MIM mapping of the ARM AO {@ARM Template}.
	 * These products have an associated {@MIM product_related_product_category} of ‘template model’
	 * 
	 * @return an aggregate of {@MIM product} satisfying the MIM mapping of the ARM AO {@ARM Template}.
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pca"
	 */
	public AProduct getAllTemplates() throws SdaiException;
	
	/**
	 * Returns an aggregate of all {@MIM product} entities in the model satisfying the MIM mapping of the ARM AO {@ARM Document}.
	 * These products have an associated {@MIM product_related_product_category} of ‘document’.
	 * 
	 * @return an aggregate of {@MIM product} satisfying the MIM mapping of the ARM AO {@ARM Document}.
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pca"
	 */
	public AProduct getAllDocuments() throws SdaiException;

	/**
	 * Returns true if there exists a {@MIM product_related_product_category} of the specified categoryName referencing the 
	 * given {@MIM product} through its products attribute.
	 * 
	 * @param categoryName
	 * @return boolean indicating whether {@MIM product} is in the prescribed category
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pca"
	 */
	public boolean isProductInASpecificPRPC(EProduct e_p, String categoryName) throws SdaiException;

	/**
	 * Returns an aggregate of all {@MIM product} entities in the model that are associated with a given {@MIMandARM Class} through
	 * an {@MIM applied_classification_assignment} ({@ARM Classification_assignment}). 
	 * 
	 * @param className the name of the class
	 * @return an aggregate of associated product entities
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pca"
	 */
	public AProduct getAllProductsWithGivenAssignedClass(String className) throws SdaiException;
	
	/**
	 * Returns an aggregate of all {@MIMandARM Class} entities that are associated with a given {@MIM product} through
	 * an {@MIM applied_classification_assignment} ({@ARM Classification_assignment}). 
	 * 
	 * @param e_p the given product
	 * @return an aggregate of all associated class entities
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pca"
	 */
	public AClass getAllAssigningClassForProduct(EProduct e_p) throws SdaiException;
	
	/**
	 * Returns an aggregate of all {@MIM parameter_assignment} entities that are associated with a given {@MIM product}.
	 * This query satisfies the mapping of ARM AOs {@ARM Product} and {@ARM Parameter_assignment} related by a {@ARM Product_specific_parameter_value_assignment}.
	 * @param p	the given {@MIM product}
	 * @return an aggregate of all associated {@MIM parameter_assignment}
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pca"
	 */
	public AParameter_assignment getAllParameterAssignmentsForProduct(EProduct p) throws SdaiException;
	
	/**
	 * Returns a subset of the given aggregate of {@MIM product_definition} that have a given specified {@MIM product_definition_context_role} (role)
	 * and {@MIM product_definition_context} (frame of reference) related through a {@MIM product_definition_context_association}.
	 * 
	 * @param a_pd the given aggregate of product_definition
	 * @param contextRole a String containing the qualifying 'name' of the product_definition_context_role (role)  
	 * @param contextRoleFrameOfReference a String containing the qualifying 'name' of the product_definition_context (frame of reference)
	 * @return an aggregate containing the qualifying subset of the given aggregate of product_definition
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pca"
	 */
	public AProduct_definition getAllProductDefinitionsOfSpecifiedProductDefinitionContextRole(
			AProduct_definition a_pd, String contextRole, String contextRoleFrameOfReference) throws SdaiException;
	
	/**
	 * Returns an aggregate of all @MIM product_definition} entities in the model that are instances of {@MIM product} with 
	 * a given specified {@MIM product_definition_context_role} and frame
	 * of reference related through a {@MIM product_definition_context_association}.
	 *
	 * @param contextRole a String containing the qualifying 'name' of the product_definition_context_role (role)
	 * @param contextRoleFrameOfReference a String containing the qualifying 'name' of the product_definition_context (frame of reference)
	 * @return an aggregate containing the qualifying product_definitions
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pca"
	 */
	public AProduct_definition getQualifiedProductDefinitions(
			String contextRole,
			String contextRoleFrameOfReference) throws SdaiException;

	/**
	 * Returns the {@MIM product_definition} corresponding to the usage view of the given {@MIM product_definition}.
	 * For example, returns the pcb usage view given the pcb design view or the pca usage view given the
	 * pca design view.
	 * 
	 * @param e_pd the given product_definition (design view)
	 * @return the product_definition corresponding to the usage view of the given design view
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pca"
	 */
	public EProduct_definition getUsageViewOfProductDefinition(EProduct_definition e_pd) throws SdaiException;

	/**
	 * Returns an aggregate of all {@MIMandARM Layered_assembly_module_design_view} entities in the model that 
	 * have a given specified {@MIM product_definition_context_role} of
	 * 'part definition type' and frame of reference 'physical design' related through a {@MIM product_definition_context_association}.
	 * These entities represent the design view of a pca.
	 * 
	 * @return an agggregate of {@MIM layered_assembly_module_design_view} that represent the design view of a pca
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pca"
	 */
	public ALayered_assembly_module_design_view getPcas() throws SdaiException;
		
	/**
	 * Returns an aggregate of all {@MIMandARM Layered_interconnect_module_design_view} entities in the model that 
	 * have a given specified {@MIM product_definition_context_role} of
	 * 'part definition type' and frame of reference 'physical design' related through a {@MIM product_definition_context_association}.
	 * These entities represent the design view of a pcb.
	 * 
	 * @return an agggregate of {@MIM layered_interconnect_module_design_view} that represent the design view of a pcb
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pca"
	 */
	public ALayered_interconnect_module_design_view getPcbs() throws SdaiException;
	
	/**
	 * Returns an aggregate of all {@MIMandARM Layered_interconnect_panel_design_view} entities in the model that 
	 * have a given specified {@MIM product_definition_context_role} of
	 * 'part definition type' and frame of reference 'physical design' related through a {@MIM product_definition_context_association}.
	 * These entities represent the design view of a panel.
	 * 
	 * @return an aggregate of layered_interconnect_panel_design_view that represent the design view of a panel
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pca"
	 */
	public ALayered_interconnect_panel_design_view getPanels() throws SdaiException;
	
	/**
	 * Returns an aggregate of all {@MIMandARM Interconnect_module_component} entities (instances of a pcb) in the model that are instances of products with
	 * associated class of ‘interconnect’.
	 * 
	 * @return an aggregate of {@MIM interconnect_module_component}
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pca"
	 */
	public AInterconnect_module_component getInterconnectModuleComponents() throws SdaiException;
	
	/**
	 * Returns an aggregate of all {@MIMandARM Interconnect_module_component} (instances of a pcb) that are located in a 
	 * {@MIMandARM Layered_interconnect_panel_design_view} (panel design view).
	 * 
	 * The interconnect_module_component are instances of a product. To locate these IMCs in the panel, the associated
	 * {@MIM shape_representation} of the pcb usage view must be obtained.
	 * 
	 * @param e_panel the given {@MIM layered_interconnect_panel_design_view} representing a panel design view
	 * @return an aggregate of {@MIM interconnect_module_component} representing instances of a pcb in the given panel
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pca"
	 */
	public AInterconnect_module_component getAllInterconnectModuleComponentsInPanel(ELayered_interconnect_panel_design_view e_panel) throws SdaiException;
	
	/**
	 * Returns an aggregate of all {@MIMandARM Interconnect_module_component} (instances of a pcb) that are located in a 
	 * {@MIMandARM Layered_assembly_module_design_view} (PCA design view).
	 * 
	 * The interconnect_module_component are instances of a product. To locate these IMCs in the panel, the associated
	 * {@MIM shape_representation} of the pcb usage view must be obtained.
	 * 
	 * @param pca the given {@MIM layered_assembly_module_design_view} (PCA design view)
	 * @return an aggregate of {@MIM interconnect_module_component} representing instances of a pcb in the given PCA
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 */
	public AInterconnect_module_component getAllInterconnectModuleComponentsInPCA(ELayered_assembly_module_design_view pca) throws SdaiException;
	
	/**
	 * Returns an aggregate of all {@MIMandARM Packaged_component} that are occurrences in an {@MIMandARM Layered_assembly_module_design_view}.
	 * 
	 * @param e_ad the given {@MIM layered_assembly_module_design_view} that represent the design view of a pca
	 * @return an aggregate of all {@MIM packaged_component} in the given {@MIM layered_assembly_module_design_view}
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pca"
	 */
	public APackaged_component getAllPackagedComponentsInAssembly(ELayered_assembly_module_design_view e_ad) throws SdaiException;
	
	/**
	 * Returns the {@MIMandARM Product} of which the given {@MIMandARM Packaged_component} is an instance.
	 * 
	 * @param e_pc the given {@MIM packaged_component}
	 * @return the {@MIM product} of which the given {@MIM packaged_component} is an instance
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pca"
	 */
	public EProduct getProductOfPackagedComponent(EPackaged_component e_pc) throws SdaiException;

	/**
	 * Returns the {@MIMandARM Package} that is used by a particular {@MIMandARM Packaged_component}. Note that there will often by multiple packages
	 * used by a particular {@MIMandARM Packaged_part}. In order to find the correct package, it is necessary to determine which {@MIM shape_representation} ({@ARM Physical_unit_planar_shape_model}) is
	 * located in the assembly through the {@MIMandARM Component_2d_location}. The component_2d_location will be related to the corresponding {@MIMandARM Next_assembly_usage_occurrence_relationship}
	 * through the {@MIM product_definition_shape} ({@ARM Contextual_item_shape}).
	 * 
	 * @param pc the given {@MIM packaged_component}
	 * @param sr1 the {@MIM shape_representation} of the {@MIM layered_assembly_module_design_view} (pca)
	 * @return the {@MIM package} used by the given {@MIM packaged_component}
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pca"
	 */
	public EPackage getPackageOfPackagedComponent(EPackaged_component pc, EShape_representation sr1) throws SdaiException;
	
	/**
	 * Returns the {@MIM shape_representation} of the given {@MIMandARM Package} that has a specified ‘predefined shape_purpose.’
	 * If no such {@MIM shape_representation} exists, return null.
	 * 
	 * This query supports both the mapping for ARM AO {@ARM Physical_unit_planar_shape_model} whose shape_purpose is of type predefined_planar_purpose
	 * and ARM AO {@ARM Physical_unit_3d_shape_model} whose shape_purpose is of type predefined_3d_purpose.
	 * 
	 * For additional support related to 2D and 3D shape models of packages see also {@link com.sfm.ap210.jsdai.MIMpackageQueries}. 
	 * 
	 * @param p	the given package
	 * @param purpose the specified name of the representation_item of the ‘predefined shape_purpose’ representation
	 * @return the associated shape_representation
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pca"
	 * @see com.sfm.ap210.jsdai.MIMpackageQueries
	 */
	public EShape_representation getShapeRepresentationOfPackageWithSpecifiedPurpose(EPackage p, String purpose) throws SdaiException;
	
	/**
	 * Returns a {@MIM shape_representation} within the given set of {@MIM shape_representation} that has a specified ‘predefined shape purpose’
	 * matching the given purpose string.
	 * If no such shape_representation exists, return null.
	 * This query supports the mapping for predefined_planar_purpose (shape_purpose of ARM AO {@ARM Physical_unit_planar_shape_model})
	 * and predefined_3d_purpose (shape_purpose of ARM AO {@ARM Physical_unit_3d_shape_model}).
	 * 
	 * @param a_sr a aggregate of shape_representation
	 * @param purpose the specified name of the representation_item of the ‘predefined shape_purpose’ representation
	 * @return the qualifying shape_representation
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pca"
	 */
	public EShape_representation getShapeRepresentationWithSpecifiedPurpose(AShape_representation a_sr, String purpose) throws SdaiException;
	
	/**
	 * Given a {@MIM shape_representation} corresponding to a mapping of ARM AO {@ARM Physical_unit_planar_shape_model}, this method will
	 * return all associated {@MIM shape_representation} corresponding to mappings of associated {@ARM Physical_unit_planar_keepout_shape_model}.
	 * 
	 * @param e_pupsm the given shape_representation which is a mapping of ARM AO Physical_unit_planar_shape_model
	 * @return all associated shape_representations corresponding to mappings of Physical_unit_planar_keepout_shape_model
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pca"
	 */
	public AShape_representation getAllKeepoutsForPhysicalUnitShapeModel(EShape_representation e_pupsm) throws SdaiException;
	
	/**
	 * Given an aggregate of {@MIM shape_representation} corresponding to {@ARM Physical_unit_planar_keepout_shape_model},
	 * Returns the first {@MIM shape_representation} whose associated {@MIM keepout_design_object_category} matches the given description
	 * This satisfies the mapping of the constrained_design_object_category attribute of {@ARM Physical_unit_keepout_shape_model}.
	 *   
	 * @param a_sr the given aggregate of shape_representation corresponding to Physical_unit_planar_keepout_shape_model
	 * @param givenDescription the required description of the associated keepout_design_object_category 
	 * @return the satisfying shape_representation
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pca"
	 */
	public EShape_representation getKeepoutShapeRepresentationWithSpecifiedKeepoutCategory(AShape_representation a_sr, String givenDescription) throws SdaiException;
	
	/**
	 * Returns an aggregate of all {@MIMandARM Assembly_joint} in a {@MIMandARM Layered_assembly_module_design_view}. 
	 * There is an assembly joint for each terminal of a {@MIMandARM Packaged_component}.
	 * 
	 * @param e_ad the given layered_assembly_module_design_view (pca)
	 * @return an aggregate of all assembly_joint in the given layered_assembly_module_design_view
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pca"
	 */
	public AAssembly_joint getAllAssemblyJointsInPca(ELayered_assembly_module_design_view e_ad) throws SdaiException;
	
	/**
	 * Returns the {@MIM packaged_part_terminal} (‘join terminal’) for a given {@MIM assembly_joint}.
	 * This satisfies the mapping of the ARM AO {@ARM Packaged_part_join_terminal}
	 * 
	 * @param e_aj the given assembly_joint
	 * @return the associated packaged_part_terminal
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pca"
	 */
	public EPackaged_part_terminal getJoinTerminalForAssemblyJoint(EAssembly_joint e_aj) throws SdaiException;
	
	/**
	 * Returns the associated {@MIMandARM Laminate_component} for a given {@MIMandARM Assembly_joint}. The returned entity is typically either a
	 * {@MIMandARM Contact_size_dependent_land} in the case of an assembly_joint for a surface mount terminal, or a 
	 * {@MIMandARM Component_termination_passage} in the case of an assembly_joint for a through hole terminal.
	 * 
	 * @param aj the given assembly_joint
	 * @return the associated laminate_component
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pca"
	 */
	public ELaminate_component getLaminateComponentForAssemblyJoint(EAssembly_joint aj) throws SdaiException;
	
	/**
	 * Returns the associated {@MIMandARM Laminate_component} for a given {@MIMandARM Interconnect_module_interface_terminal}.
	 * The returned entity is typically either a {@MIMandARM Contact_size_dependent_land} 
	 * in the case of an assembly_joint for a surface mount terminal, or a {@MIMandARM Component_termination_passage}
	 * in the case of an assembly_joint for a through hole terminal.
	 * 
	 * @param e_imct the given interconnect_module_interface_terminal
	 * @return the associate laminate_component
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pca"
	 */
	public ELaminate_component getLaminateComponentForIMCT(EPhysical_component_terminal e_imct) throws SdaiException;
	
	/**
	 * Returns the associated {@MIMandARM Interconnect_module_interface_terminal}
	 * for a given assembly_component if applicable.
	 * 
	 * The given assembly_component may be a {@MIMandARM Contact_size_dependent_land}
	 * in the case of an assembly_joint for a surface mount terminal or a {@MIMandARM Component_termination_passage}
	 * in the case of an assembly_joint for a through hole terminal.
	 * 
	 * @param e_ac the given assembly component (typically either a contact_size_dependent_land or component_termination_passage)
	 * @return the associated interconnect_module_interface_terminal
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pca"
	 */
	public EInterconnect_module_interface_terminal getIMITforAC(EAssembly_component e_ac) throws SdaiException;
	
	/**
	 * Returns an aggregate of {@MIMandARM Part_tooling_feature} that are located on the 
	 * {@MIMandARM Layered_interconnect_module_usage_view} (pcb usage view).
	 * 
	 * This includes {@MIMandARM Part_tooling_feature} and its subtype {@MIMandARM Fiducial_part_feature}.
	 * 
	 * Each of these part_tooling_feature are associated with a ‘stratum concept’ which is the mapping to an
	 * element of the pcb.
	 * 
	 * Note that the {@MIM part_tooling_feature} are obtained through a {@MIM usage_concept_usage_relationship} with the
	 * {@MIM shape_representation} of the {@MIM layered_interconnect_module_usage_view} (pcb usage view).
	 * 
	 * @param e_pcb_usage_view the given layered_interconnect_module_usage_view (pcb usage view)
	 * @param e_sr the shape_representation of the given layered_interconnect_module_usage_view (pcb usage view)
	 * @return an aggregate of part_tooling_feature for the given layered_interconnect_module_usage_view (pcb usage view)
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pca"
	 */
	public APart_tooling_feature getPartToolingFeaturesInPcb(ELayered_interconnect_module_usage_view e_pcb_usage_view, EShape_representation e_sr) throws SdaiException;
	
	/**
	 * Returns an associated {@MIM laminate_component} for a {@MIMandARM Part_tooling_feature} or its subtype {@MIMandARM Fiducial_part_feature}.
	 * Examples include an {@MIM unsupported_passage} (a tooling hole) in the case of a {@MIM part_tooling_feature} or a
	 * complex {@MIM fiducial}+{@MIM stratum_feature_template_component} in the case of a {@MIM fiducial_part_feature}.
	 * In the event that there is not an associated {@MIM laminate_component}, the query returns null.
	 * 
	 * @param e_ptf the given part_tooling_feature (or fiducial_part_feature)
	 * @return the associated laminate_component
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pca"
	 */
	public ELaminate_component getLaminateComponentForPartToolingFeature(EPart_tooling_feature e_ptf) throws SdaiException;
	
	/**
	 * Returns an {@MIMandARM Interconnect_module_edge} representing the outline of the given {@MIMandARM Layered_interconnect_module_design_view}.
	 * 
	 * @param id the given layered_interconnect_module_design_view (pcb)
	 * @return the interconnect_module_edge representing the outline of the pcb
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pcb"
	 */
	public EInterconnect_module_edge getEdgeOfPcb(ELayered_interconnect_module_design_view id) throws SdaiException;
	
	/**
	 * Returns a {@MIM representation} containing the mapping of the ARM AO {@ARM Length_tolerance_characteristic} for the 
	 * thickness of the given {@MIMandARM Layered_interconnect_module_usage_view}. The two thickness measures are attributes of the ARM AO {@ARM Layered_interconnect_module_usage_view}
	 * The propertyName argument determines which of the two thickness characteristics ('thickness over metal requirement' or 'thickness over dielectric requirement') will
	 * be returned. Note that both of the possible thickness characteristics are optional. 
	 * 
	 * @param e_id the given layered_interconnect_module_usage_view (pcb usage view)
	 * @param propertyName a String that should be one of 'thickness over metal requirement' or 'thickness over dielectric requirement'. 
	 * @return a representation containing the length tolerance characteristic for the thickness of the pcb
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pcb"
	 */
	public ERepresentation getThicknessOfPcb(ELayered_interconnect_module_usage_view e_id, String propertyName) throws SdaiException;
	
	/**
	 * Returns an aggregate of all {@MIMandARM Stratum} that compose the given {@MIMandARM Layered_interconnect_module_design_view}.
	 * 
	 * @param e_id the given layered_interconnect_module_design_view (pcb)
	 * @return an aggregate of all strata that compose the given layered_interconnect_module_design_view (pcb).
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pcb"
	 */
	public AStratum getAllStrataInPcb(ELayered_interconnect_module_design_view e_id) throws SdaiException;
	
	/**
	 * Returns an aggregate of all {@MIMandARM Stratum_feature_template_component} that are directly located on the given {@MIMandARM Layered_interconnect_module_design_view}
	 * through a {@MIMandARM Next_assembly_usage_occurrence_relationship}.
	 * 
	 * @param e_id the given layered_interconnect_module_design_view (pcb)
	 * @return an aggregate of all stratum_feature_template_component that are directly associated with the pcb through a nauor
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pcb"
	 */
	public AStratum_feature_template_component getAllSftcDirectlyRelatedToPcb(ELayered_interconnect_module_design_view e_id) throws SdaiException;
	
	/**
	 * Returns an aggregate of all {@MIMandARM Material_removal_laminate_component} that are directly located on the given {@MIMandARM Layered_interconnect_module_design_view}
	 * through a {@MIMandARM Next_assembly_usage_occurrence_relationship}.
	 * 
	 * @param e_id the given layered_interconnect_module_design_view (pcb)
	 * @return an aggregate of all material_removal_laminate_component that are directly associated with the pcb through a nauor
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pcb"
	 */
	public AMaterial_removal_laminate_component getAllMrlcDirectlyRelatedToPcb(ELayered_interconnect_module_design_view e_id) throws SdaiException;
	
	/**
	 * Returns an aggregate of all {@MIMandARM Padstack_occurrence} that are directly located on the given {@MIMandARM Layered_interconnect_module_design_view} through a
	 * {@MIMandARM Next_assembly_usage_occurrence_relationship}.
	 * 
	 * @param e_id the given layered_interconnect_module_design_view (pcb)
	 * @return an aggregate of all padstack_occurrence that are directly associated with the pcb through a nauor
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pcb"
	 */
	public APadstack_occurrence getAllPadstackOccurrenceInPcb(ELayered_interconnect_module_design_view e_id) throws SdaiException;
	
	/**
	 * Returns an aggregate of all {@MIMandARM Footprint_occurrence} that are directly located on the given {@MIMandARM Layered_interconnect_module_design_view} through a
	 * {@MIMandARM Next_assembly_usage_occurrence_relationship}.
	 * 
	 * @param e_id the given layered_interconnect_module_design_view (pcb)
	 * @return an aggregate of all footprint_occurrence that are directly associated with the pcb through a nauor
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pcb"
	 */
	public AFootprint_occurrence getAllFootprintOccurrenceInPcb(ELayered_interconnect_module_design_view e_id) throws SdaiException;

	/**
	 * Returns an aggregate of all {@MIMandARM Structured_layout_component} that are elements of the given structured_layout_component.
	 * {@MIMandARM Padstack_occurrence} and {@MIMandARM Footprint_occurrence} are both subtypes of structured_layout_component.
	 * 
	 * @param e_slc the given structured_layout_component
	 * @return an aggregate of all structured_layout_component that are elements of the given structured_layout_component
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pcb"
	 */
	public AStructured_layout_component getAllStructuredLayoutComponentsInStructuredLayoutComponent(EStructured_layout_component e_slc) throws SdaiException;
	
	/**
	 * Returns an aggregate of all {@MIMandARM Laminate_component} that compose the {@MIMandARM Structured_layout_component}.
	 * {@MIMandARM Padstack_occurrence} and {@MIMandARM Footprint_occurrence} are both subtypes of structured_layout_component.
	 * 
	 * @param e_slc the given structured_layout_component
	 * @return an aggregate of all laminate_component that compose the structured_layout_component
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pcb"
	 */
	public ALaminate_component getAllLaminateComponentsInStructuredLayoutComponent(EStructured_layout_component e_slc) throws SdaiException;

	/**
	 * Returns an aggregate of all {@MIMandARM Multi_stratum_special_symbol_component} that are directly 
	 * located on the given {@MIMandARM Layered_interconnect_module_design_view} through a {@MIMandARM Next_assembly_usage_occurrence_relationship}.
	 * 
	 * @param e_id the given layered_interconnect_module_design_view (pcb)
	 * @return an aggregate of all multi_stratum_special_symbol_component that are directly associated with the pcb through a nauor
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pcb"
	 */
	public AMulti_stratum_special_symbol_component getAllMultiStratumSpecialSymbolComponentsInPcb(ELayered_interconnect_module_design_view e_id) throws SdaiException;
	
	/**
	 * Returns an aggregate of all {@MIMandARM Single_stratum_special_symbol_component} that are directly
	 * located on the given {@MIMandARM Layered_interconnect_module_design_view} through a {@MIMandARM Next_assembly_usage_occurrence_relationship}.
	 * 
	 * @param e_id the given layered_interconnect_module_design_view (pcb)
	 * @return an aggregate of all single_stratum_special_symbol_component that are directly associated with the pcb through a nauor
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pcb"
	 */
	public ASingle_stratum_special_symbol_component getAllSingleStratumSpecialSymbolComponentsInPcb(ELayered_interconnect_module_design_view e_id) throws SdaiException;

	/**
	 * Returns an aggregate of all {@MIMandARM Laminate_text_string_component} that are directly located on the given {@MIMandARM Layered_interconnect_module_design_view}
	 * through a {@MIMandARM Next_assembly_usage_occurrence_relationship}.
	 * 
	 * @param e_id the given layered_interconnect_module_design_view (pcb)
	 * @return an aggregate of all laminate_text_string_component that are directly associated with the pcb through a nauor
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pcb"
	 */
	public ALaminate_text_string_component getAllLaminateTextStringComponents(ELayered_interconnect_module_design_view e_id) throws SdaiException;
	
	/**
	 * Returns an aggregate of {@MIMandARM Next_assembly_usage_occurrence_relationship} that relate instances of {@MIMandARM Laminate_text_string_component}
	 * composing the given {@MIMandARM Layered_interconnect_module_design_view}.
	 * 
	 * @param e_id the given layered_interconnect_module_design_view (pcb)
	 * @return an aggregate of next_assembly_usage_occurrence_relationship
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 */
	public ANext_assembly_usage_occurrence_relationship getAllLaminateTextStringNAUOR(ELayered_interconnect_module_design_view e_id) throws SdaiException;
	
	/**
	 * Returns a {@MIMandARM Text_literal} containing the ‘message contents’ for the given {@MIMandARM Laminate_text_string_component}.
	 * 
	 * @param e_ltsc the given laminate_text_string_component
	 * @return a text literal containing the ‘message contents’
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pcb"
	 */
	public EText_literal getMessageOfLaminateTextStringComponent(ELaminate_text_string_component e_ltsc) throws SdaiException;

	/**
	 * Returns an aggregate of all {@MIMandARM Generic_laminate_text_component} that compose a given {@MIMandARM Laminate_text_string_component}.
	 * These contain the individual characters of the string.
	 * 
	 * @param e_ltsc the given laminate_text_string_component
	 * @return an aggregate of all generic_laminate_text_components that compose a given laminate_text_string_component
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pcb"
	 */
	public AGeneric_laminate_text_component getGenericLaminateTextComponentsForLTSC(ELaminate_text_string_component e_ltsc) throws SdaiException;
	
	/**
	 * Returns an aggregate of all {@MIM physical_network} ({@ARM Generic_physical_network}) that are currently associated with the given {@MIMandARM Layered_interconnect_module_design_view}.
	 * 
	 * @param e_id the given layered_interconnect_module_design_view (pcb)
	 * @return an aggregate of all physical_networks that are currently associated with the given layered_interconnect_module_design_view (Pcb)
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-Pcb"
	 */
	public APhysical_network getAllPhysicalNetworksInPcb(ELayered_interconnect_module_design_view e_id) throws SdaiException;
	
	/**
	 * Returns an aggregate of {@MIMandARM Layer_connection_point} containing all LCPs explicitly joined to a {@MIM physical_network} ({@ARM Generic_physical_network}).
	 * Query may be applied to either routed or unrouted physical networks.
	 * Uniqueness of the layer_connection_point contained in the aggregate is ensured by the implementation.
	 *   
	 * @param e_pn the given physical_network
	 * @return an aggregate of layer_connection_point containing all LCPs explicity joined to the given physical_network
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-LayerConnectionPointConnectivityAndPhysicalNets"
	 */
	public ALayer_connection_point getLCPsForPhysicalNetwork(EPhysical_network e_pn) throws SdaiException;
	
	/**
	 * Returns an aggregate of {@MIMandARM Laminate_component_feature} containing the LCFs explicitly joined to a {@MIM physical_network} ({@ARM Generic_physical_network}).
	 * Query may be applied to either routed or unrouted physical networks.
	 * Uniqueness of the laminate_component_feature contained in the aggregate is ensured by the implementation.
	 *   
	 * @param e_pn the given physical_network
	 * @return an aggregate of laminate_component_feature containing all LCFs explicitly joined to the given physical_network
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-LayerConnectionPointConnectivityAndPhysicalNets"
	 */
	public ALaminate_component_feature getLCFsForPhysicalNetwork(EPhysical_network e_pn) throws SdaiException;
	
	/**
	 * Returns an {@MIMandARM Inter_stratum_feature} associated with a 'dependently located' {@MIM layer_connection_point} ({@ARM Dependently_located_layer_connection_point}) if one exists.
	 * 
	 * @param e_lcp the given 'dependently located' layer_connection_point
	 * @return an inter_stratum_feature associated with the given lcp if one exists
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-LayerConnectionPointConnectivityAndPhysicalNets"
	 */
	public EInter_stratum_feature  getInterStratumFeatureForDLLCP(ELayer_connection_point e_lcp) throws SdaiException;
	
	/**
	 * Returns an aggregate of {@MIMandARM Plated_passage_dependent_land} associated with a ‘dependently located’ {@MIM layer_connection_point} ({@ARM Dependently_located_layer_connection_point}) if a
	 * {@MIMandARM Plated_passage} is the ‘associated design object’ of the layer_connection_point.
	 *  
	 * @param e_lcp the given dependently located layer_connection_point
	 * @return an aggregate of associated plated_passage_dependent_land, if applicable
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-LayerConnectionPointConnectivityAndPhysicalNets"
	 */
	public APlated_passage_dependent_land getPPDLandsforDLLCP(ELayer_connection_point e_lcp) throws SdaiException;
	
	/**
	 * Returns a {@MIMandARM Contact_size_dependent_land} associated with a ‘dependently located’ {@MIM layer_connection_point} ({@ARM Dependently_located_layer_connection_point}) if an
	 * {@MIMandARM Interconnect_module_interface_terminal} is the ‘associated design object’ of the DLLCP.
	 * 
	 * @param e_lcp the given dependently located layer_connection_point
	 * @return an associated contact_size_dependent_land, if applicable
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-LayerConnectionPointConnectivityAndPhysicalNets"
	 */
	public EContact_size_dependent_land getCSDLandforDLLCP(ELayer_connection_point e_lcp) throws SdaiException;
	
	/**
	 * Returns an aggregate of {@MIMandARM Conductive_interconnect_element} associated with a {@MIMandARM Layer_connection_point} if a
	 * ‘conductive interconnect element terminal’ is associated with the LCP
	 * 
	 * @param e_lcp the given layer_connection_point
	 * @return an aggregate of associated conductive_interconnect_element, if applicable
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-LayerConnectionPointConnectivityAndPhysicalNets"
	 */
	public AConductive_interconnect_element getCIEforLCP(ELayer_connection_point e_lcp) throws SdaiException;
	
	/**
	 * Returns a {MIMandARM Connected_area_component} that is associated with a given {@MIMandARM Layer_connection_point} if one exists.
	 * 
	 * @param e_lcp the given layer_connection_point
	 * @return an associated connected_area_component, if applicable
	 * @MIMinstanceDiagram
	 * @throws SdaiException
	 * @see "MIMqueries_1.3-LayerConnectionPointConnectivityAndPhysicalNets"
	 */
	public EConnected_area_component getConnectedAreaComponentforLCP(ELayer_connection_point e_lcp) throws SdaiException;
	
}

