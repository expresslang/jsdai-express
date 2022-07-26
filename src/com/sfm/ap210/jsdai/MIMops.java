package com.sfm.ap210.jsdai;

import jsdai.lang.*;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.*;

/**
 * An interface for the query operations required by the MIMqueries methods.
 * @author James Stori, SFM Technology, Inc.
 * @version 1.3
 */
public interface MIMops {



/**
 * Given a structured_template, e1
 * this method will return an aggregate containing all assembly_component_usage
 * that references the structured_template through its relating_product_definition attribute. 
 * The return entity is qualified by a specified value of the name attribute.
 * <p>
 * e1 <- assembly_component_usage.relating_product_definition
 * <p>assembly_component_usage.name = v1
 * <p>
 * @param e1 the starting entity of type structured_template
 * @param v1 the qualifying attribute value for the referencing entity's name attribute
 * @return an aggregate containing all referencing assembly_component_usage
 */
public  AAssembly_component_usage  All_acu_referencing_st (EStructured_template e1, String v1) throws SdaiException;  


/**
 * Given a layered_assembly_module_design_view, e1
 * this method will return an aggregate containing all assembly_joint
 * that references the layered_assembly_module_design_view through its of_shape attribute. 
 * <p>
 * e1 <- assembly_joint.of_shape
 * <p>
 * @param e1 the starting entity of type layered_assembly_module_design_view
 * @return an aggregate containing all referencing assembly_joint
 */
public  AAssembly_joint  All_aj_referencing_lamdv (ELayered_assembly_module_design_view e1) throws SdaiException;  


/**
 * Given a product_definition_shape, e1
 * this method will return an aggregate containing all component_2d_location
 * that references the product_definition_shape through its represented_product_relation attribute. 
 * <p>
 * e1 <- component_2d_location.represented_product_relation
 * <p>
 * @param e1 the starting entity of type product_definition_shape
 * @return an aggregate containing all referencing component_2d_location
 */
public  AComponent_2d_location  All_c2dl_referencing_pds (EProduct_definition_shape e1) throws SdaiException;  


/**
 * Given a product, e1
 * this method will return an aggregate containing all class
 * related by a applied_classification_assignment
 * <p>
 * e1 <- applied_classification_assignment.items
 * <br>applied_classification_assignment.assigned_class -> class
 * <p>
 * @param e1 the starting entity of product
 * @return an aggregate containing all related class
 */
public  AClass All_c_relatedTo_p_through_aca (EProduct e1) throws SdaiException; 


/**
 * This method will return an aggregate containing all datum
 * in the current SDAI model. 
 * <p>
 * <p>
 * @return an aggregate containing all datum
 */
public ADatum All_d_inModel () throws SdaiException; 

/**
 * This method will return an aggregate containing all dimensional_location
 * in the current SDAI model. 
 * <p>
 * <p>
 * @return an aggregate containing all dimensional_location
 */
public ADimensional_location All_dl_inModel () throws SdaiException; 

/**
 * This method will return an aggregate containing all dimensional_size
 * in the current SDAI model. 
 * <p>
 * <p>
 * @return an aggregate containing all dimensional_size
 */
public ADimensional_size All_ds_inModel () throws SdaiException; 


/**
 * Given a package, e1
 * this method will return an aggregate containing all footprint_definition
 * related by a property_definition_relationship
 * and qualified by a specified value of the name attribute, v1
 * <p>
 * e1 <- property_definition_relationship.relating_property_definition
 * <br>property_definition_relationship.name = v1
 * <br>property_definition_relationship.related_property_definition -> footprint_definition
 * <p>
 * @param e1 the starting entity of package
 * @param v1 the qualifying attribute value for the relating entity's name attribute
 * @return an aggregate containing all related footprint_definition
 */
public  AFootprint_definition All_fd_relatedTo_p_through_pdr (EPackage e1, String v1) throws SdaiException; 



/**
 * Given a layered_interconnect_module_design_view, e1
 * this method will return an aggregate containing all footprint_occurrence
 * related by a next_assembly_usage_occurrence_relationship
 * <p>
 * e1 <- next_assembly_usage_occurrence_relationship.relating_product_definition
 * <br>next_assembly_usage_occurrence_relationship.occurrence -> footprint_occurrence
 * <p>
 * @param e1 the starting entity of layered_interconnect_module_design_view
 * @return an aggregate containing all related footprint_occurrence
 */
public  AFootprint_occurrence All_fo_relatedTo_limdv_through_nauor (ELayered_interconnect_module_design_view e1) throws SdaiException; 



/**
 * Given a laminate_text_string_component, e1
 * this method will return an aggregate containing all generic_laminate_text_component
 * related by a next_assembly_usage_occurrence_relationship
 * <p>
 * e1 <- next_assembly_usage_occurrence_relationship.relating_product_definition
 * <br>next_assembly_usage_occurrence_relationship.occurrence -> generic_laminate_text_component
 * <p>
 * @param e1 the starting entity of laminate_text_string_component
 * @return an aggregate containing all related generic_laminate_text_component
 */
public  AGeneric_laminate_text_component All_gltc_relatedTo_ltsc_through_nauor (ELaminate_text_string_component e1) throws SdaiException; 


/**
 * This method will return an aggregate containing all geometric_tolerance
 * in the current SDAI model. 
 * <p>
 * <p>
 * @return an aggregate containing all geometric_tolerance
 */
public AGeometric_tolerance All_gt_inModel () throws SdaiException; 


/**
 * Given a shape_aspect, e1
 * this method will return an aggregate containing all id_attribute
 * that references the shape_aspect through its identified_item attribute. 
 * <p>
 * e1 <- id_attribute.identified_item
 * <p>
 * @param e1 the starting entity of type shape_aspect
 * @return an aggregate containing all referencing id_attribute
 */
public  AId_attribute  All_ia_referencing_sa (EShape_aspect e1) throws SdaiException;  


/**
 * Given a advanced_face, e1
 * this method will return an aggregate containing all item_identified_representation_usage
 * that references the advanced_face through its identified_item attribute. 
 * <p>
 * e1 <- item_identified_representation_usage.identified_item
 * <p>
 * @param e1 the starting entity of type advanced_face
 * @return an aggregate containing all referencing item_identified_representation_usage
 */
public  AItem_identified_representation_usage  All_iiru_referencing_af (EAdvanced_face e1) throws SdaiException;  


/**
 * Given a shape_aspect, e1
 * this method will return an aggregate containing all item_identified_representation_usage
 * that references the shape_aspect through its definition attribute. 
 * <p>
 * e1 <- item_identified_representation_usage.definition
 * <p>
 * @param e1 the starting entity of type shape_aspect
 * @return an aggregate containing all referencing item_identified_representation_usage
 */
public  AItem_identified_representation_usage  All_iiru_referencing_sa (EShape_aspect e1) throws SdaiException;  


/**
 * Given a product_definition_formation, e1
 * this method will return an aggregate containing all interconnect_module_component
 * that references the product_definition_formation through its formation attribute. 
 * <p>
 * e1 <- interconnect_module_component.formation
 * <p>
 * @param e1 the starting entity of type product_definition_formation
 * @return an aggregate containing all referencing interconnect_module_component
 */
public  AInterconnect_module_component  All_imc_referencing_pdf (EProduct_definition_formation e1) throws SdaiException;  


/**
 * Given a layered_assembly_module_design_view, e1
 * this method will return an aggregate containing all interconnect_module_component
 * related by a next_assembly_usage_occurrence_relationship
 * <p>
 * e1 <- next_assembly_usage_occurrence_relationship.relating_product_definition
 * <br>next_assembly_usage_occurrence_relationship.occurrence -> interconnect_module_component
 * <p>
 * @param e1 the starting entity of layered_assembly_module_design_view
 * @return an aggregate containing all related interconnect_module_component
 */
public  AInterconnect_module_component All_imc_relatedTo_lamdv_through_nauor (ELayered_assembly_module_design_view e1) throws SdaiException; 



/**
 * Given a layered_interconnect_panel_design_view, e1
 * this method will return an aggregate containing all interconnect_module_component
 * related by a next_assembly_usage_occurrence_relationship
 * <p>
 * e1 <- next_assembly_usage_occurrence_relationship.relating_product_definition
 * <br>next_assembly_usage_occurrence_relationship.occurrence -> interconnect_module_component
 * <p>
 * @param e1 the starting entity of layered_interconnect_panel_design_view
 * @return an aggregate containing all related interconnect_module_component
 */
public  AInterconnect_module_component All_imc_relatedTo_lipdv_through_nauor (ELayered_interconnect_panel_design_view e1) throws SdaiException; 



/**
 * Given a physical_network, e1
 * this method will return an aggregate containing all join_shape_aspect
 * related by a shape_aspect_relationship
 * and qualified by a specified value of the name attribute, v1
 * <p>
 * e1 <- shape_aspect_relationship.relating_shape_aspect
 * <br>shape_aspect_relationship.name = v1
 * <br>shape_aspect_relationship.related_shape_aspect -> join_shape_aspect
 * <p>
 * @param e1 the starting entity of physical_network
 * @param v1 the qualifying attribute value for the relating entity's name attribute
 * @return an aggregate containing all related join_shape_aspect
 */
public  AJoin_shape_aspect All_jsa_relatedTo_pn_through_sar (EPhysical_network e1, String v1) throws SdaiException; 



/**
 * Given a product_definition_formation, e1
 * this method will return an aggregate containing all layered_assembly_module_design_view
 * that references the product_definition_formation through its formation attribute. 
 * <p>
 * e1 <- layered_assembly_module_design_view.formation
 * <p>
 * @param e1 the starting entity of type product_definition_formation
 * @return an aggregate containing all referencing layered_assembly_module_design_view
 */
public  ALayered_assembly_module_design_view  All_lamdv_referencing_pdf (EProduct_definition_formation e1) throws SdaiException;  


/**
 * Given a structured_layout_component, e1
 * this method will return an aggregate containing all laminate_component
 * related by a structured_layout_component_sub_assembly_relationship
 * <p>
 * e1 <- structured_layout_component_sub_assembly_relationship.relating_product_definition
 * <br>structured_layout_component_sub_assembly_relationship.occurrence -> laminate_component
 * <p>
 * @param e1 the starting entity of structured_layout_component
 * @return an aggregate containing all related laminate_component
 */
public  ALaminate_component All_lc_relatedTo_slc_through_slcsar (EStructured_layout_component e1) throws SdaiException; 



/**
 * Given a join_shape_aspect, e1
 * this method will return an aggregate containing all laminate_component_feature
 * related by a shape_aspect_relationship
 * and qualified by a specified value of the name attribute, v1
 * <p>
 * e1 <- shape_aspect_relationship.related_shape_aspect
 * <br>shape_aspect_relationship.name = v1
 * <br>shape_aspect_relationship.relating_shape_aspect -> laminate_component_feature
 * <p>
 * @param e1 the starting entity of join_shape_aspect
 * @param v1 the qualifying attribute value for the relating entity's name attribute
 * @return an aggregate containing all related laminate_component_feature
 */
public  ALaminate_component_feature All_lcf_relatedTo_jsa_through_sar (EJoin_shape_aspect e1, String v1) throws SdaiException; 



/**
 * Given a layer_connection_point, e1
 * this method will return an aggregate containing all laminate_component_feature
 * related by a shape_aspect_relationship
 * and qualified by a specified value of the name attribute, v1
 * <p>
 * e1 <- shape_aspect_relationship.relating_shape_aspect
 * <br>shape_aspect_relationship.name = v1
 * <br>shape_aspect_relationship.related_shape_aspect -> laminate_component_feature
 * <p>
 * @param e1 the starting entity of layer_connection_point
 * @param v1 the qualifying attribute value for the relating entity's name attribute
 * @return an aggregate containing all related laminate_component_feature
 */
public  ALaminate_component_feature All_lcf_relatedTo_lcp_through_sar (ELayer_connection_point e1, String v1) throws SdaiException; 



/**
 * Given a physical_network, e1
 * this method will return an aggregate containing all laminate_component_feature
 * related by a shape_aspect_relationship
 * and qualified by a specified value of the name attribute, v1
 * <p>
 * e1 <- shape_aspect_relationship.relating_shape_aspect
 * <br>shape_aspect_relationship.name = v1
 * <br>shape_aspect_relationship.related_shape_aspect -> laminate_component_feature
 * <p>
 * @param e1 the starting entity of physical_network
 * @param v1 the qualifying attribute value for the relating entity's name attribute
 * @return an aggregate containing all related laminate_component_feature
 */
public  ALaminate_component_feature All_lcf_relatedTo_pn_through_sar (EPhysical_network e1, String v1) throws SdaiException; 



/**
 * Given a physical_network, e1
 * this method will return an aggregate containing all laminate_component_join_terminal
 * related by a shape_aspect_relationship
 * and qualified by a specified value of the name attribute, v1
 * <p>
 * e1 <- shape_aspect_relationship.relating_shape_aspect
 * <br>shape_aspect_relationship.name = v1
 * <br>shape_aspect_relationship.related_shape_aspect -> laminate_component_join_terminal
 * <p>
 * @param e1 the starting entity of physical_network
 * @param v1 the qualifying attribute value for the relating entity's name attribute
 * @return an aggregate containing all related laminate_component_join_terminal
 */
public  ALaminate_component_join_terminal All_lcjt_relatedTo_pn_through_sar (EPhysical_network e1, String v1) throws SdaiException; 



/**
 * Given a join_shape_aspect, e1
 * this method will return an aggregate containing all layer_connection_point
 * related by a shape_aspect_relationship
 * and qualified by a specified value of the name attribute, v1
 * <p>
 * e1 <- shape_aspect_relationship.relating_shape_aspect
 * <br>shape_aspect_relationship.name = v1
 * <br>shape_aspect_relationship.related_shape_aspect -> layer_connection_point
 * <p>
 * @param e1 the starting entity of join_shape_aspect
 * @param v1 the qualifying attribute value for the relating entity's name attribute
 * @return an aggregate containing all related layer_connection_point
 */
public  ALayer_connection_point All_lcp_relatedTo_jsa_through_sar (EJoin_shape_aspect e1, String v1) throws SdaiException; 



/**
 * Given a join_shape_aspect, e1
 * this method will return an aggregate containing all layer_connection_point
 * related by a shape_aspect_relationship
 * and qualified by a specified value of the name attribute, v1
 * <p>
 * e1 <- shape_aspect_relationship.related_shape_aspect
 * <br>shape_aspect_relationship.name = v1
 * <br>shape_aspect_relationship.relating_shape_aspect -> layer_connection_point
 * <p>
 * @param e1 the starting entity of join_shape_aspect
 * @param v1 the qualifying attribute value for the relating entity's name attribute
 * @return an aggregate containing all related layer_connection_point
 */
public  ALayer_connection_point All_lcp_relatedTo_jsa_through_sar_2 (EJoin_shape_aspect e1, String v1) throws SdaiException; 



/**
 * Given a product_definition_formation, e1
 * this method will return an aggregate containing all layered_interconnect_module_design_view
 * that references the product_definition_formation through its formation attribute. 
 * <p>
 * e1 <- layered_interconnect_module_design_view.formation
 * <p>
 * @param e1 the starting entity of type product_definition_formation
 * @return an aggregate containing all referencing layered_interconnect_module_design_view
 */
public  ALayered_interconnect_module_design_view  All_limdv_referencing_pdf (EProduct_definition_formation e1) throws SdaiException;  


/**
 * Given a layered_interconnect_module_design_view, e1
 * this method will return an aggregate containing all laminate_text_string_component
 * related by a next_assembly_usage_occurrence_relationship
 * <p>
 * e1 <- next_assembly_usage_occurrence_relationship.relating_product_definition
 * <br>next_assembly_usage_occurrence_relationship.related_product_definition -> laminate_text_string_component
 * <p>
 * @param e1 the starting entity of layered_interconnect_module_design_view
 * @return an aggregate containing all related laminate_text_string_component
 */
public  ALaminate_text_string_component All_ltsc_relatedTo_limdv_through_nauor (ELayered_interconnect_module_design_view e1) throws SdaiException; 



/**
 * Given a shape_representation, e1
 * this method will return an aggregate containing all mapped_item
 * referenced by the shape_representation through its items attribute. 
 * The return entity is qualified by a specified value of the name attribute.
 * <p>
 * e1.items ->
 * mapped_item
 * <p>mapped_item.name = v1
 * <p>
 * @param e1 the starting entity of type shape_representation
 * @param v1 the qualifying attribute value for the referencing entity's name attribute
  * @return an aggregate containing all referenced mapped_item
 */

public  AMapped_item All_mi_referencedBy_sr (EShape_representation e1, String v1) throws SdaiException;  


/**
 * Given a layered_interconnect_module_design_view, e1
 * this method will return an aggregate containing all material_removal_laminate_component
 * related by a next_assembly_usage_occurrence_relationship
 * <p>
 * e1 <- next_assembly_usage_occurrence_relationship.relating_product_definition
 * <br>next_assembly_usage_occurrence_relationship.occurrence -> material_removal_laminate_component
 * <p>
 * @param e1 the starting entity of layered_interconnect_module_design_view
 * @return an aggregate containing all related material_removal_laminate_component
 */
public  AMaterial_removal_laminate_component All_mrlc_relatedTo_limdv_through_nauor (ELayered_interconnect_module_design_view e1) throws SdaiException; 



/**
 * Given a layered_interconnect_module_design_view, e1
 * this method will return an aggregate containing all multi_stratum_special_symbol_component
 * related by a next_assembly_usage_occurrence_relationship
 * <p>
 * e1 <- next_assembly_usage_occurrence_relationship.relating_product_definition
 * <br>next_assembly_usage_occurrence_relationship.occurrence -> multi_stratum_special_symbol_component
 * <p>
 * @param e1 the starting entity of layered_interconnect_module_design_view
 * @return an aggregate containing all related multi_stratum_special_symbol_component
 */
public  AMulti_stratum_special_symbol_component All_msssc_relatedTo_limdv_through_nauor (ELayered_interconnect_module_design_view e1) throws SdaiException; 



/**
 * Given a product_definition, e1
 * this method will return an aggregate containing all next_assembly_usage_occurrence_relationship
 * that references the product_definition through its occurrence attribute. 
 * <p>
 * e1 <- next_assembly_usage_occurrence_relationship.occurrence
 * <p>
 * @param e1 the starting entity of type product_definition
 * @return an aggregate containing all referencing next_assembly_usage_occurrence_relationship
 */
public  ANext_assembly_usage_occurrence_relationship  All_nauor_referencing_pd (EProduct_definition e1) throws SdaiException;  

/**
 * This method will return an aggregate containing all named_unit
 * in the current SDAI model. 
 * <p>
 * <p>
 * @return an aggregate containing all named_unit
 */
public ANamed_unit All_nu_inModel () throws SdaiException; 

/**
 * This method will return an aggregate containing all product
 * in the current SDAI model. 
 * <p>
 * <p>
 * @return an aggregate containing all product
 */
public AProduct All_p_inModel () throws SdaiException; 

/**
 * This method will return an aggregate containing all package
 * in the current SDAI model. 
 * <p>
 * <p>
 * @return an aggregate containing all package
 */
public APackage All_p_inModel_2 () throws SdaiException; 


/**
 * Given a product_related_product_category, e1
 * this method will return an aggregate containing all product
 * referenced by the product_related_product_category through its products attribute. 
 * <p>
 * e1.products ->
 * product
 * <p>
 * @param e1 the starting entity of type product_related_product_category
  * @return an aggregate containing all referenced product
 */

public  AProduct All_p_referencedBy_prpc (EProduct_related_product_category e1) throws SdaiException;  


/**
 * Given a layered_assembly_module_design_view, e1
 * this method will return an aggregate containing all packaged_component
 * related by a next_assembly_usage_occurrence_relationship
 * <p>
 * e1 <- next_assembly_usage_occurrence_relationship.relating_product_definition
 * <br>next_assembly_usage_occurrence_relationship.occurrence -> packaged_component
 * <p>
 * @param e1 the starting entity of layered_assembly_module_design_view
 * @return an aggregate containing all related packaged_component
 */
public  APackaged_component All_pc_relatedTo_lamdv_through_nauor (ELayered_assembly_module_design_view e1) throws SdaiException; 



/**
 * Given a interconnect_module_component, e1
 * this method will return an aggregate containing all physical_component_terminal
 * that references the interconnect_module_component through its of_shape attribute. 
 * <p>
 * e1 <- physical_component_terminal.of_shape
 * <p>
 * @param e1 the starting entity of type interconnect_module_component
 * @return an aggregate containing all referencing physical_component_terminal
 */
public  APhysical_component_terminal  All_pct_referencing_imc (EInterconnect_module_component e1) throws SdaiException;  


/**
 * Given a assembly_component_usage, e1
 * this method will return an aggregate containing all property_definition
 * that references the assembly_component_usage through its definition attribute. 
 * <p>
 * e1 <- property_definition.definition
 * <p>
 * @param e1 the starting entity of type assembly_component_usage
 * @return an aggregate containing all referencing property_definition
 */
public  AProperty_definition  All_pd_referencing_acu (EAssembly_component_usage e1) throws SdaiException;  


/**
 * Given a package, e1
 * this method will return an aggregate containing all property_definition
 * that references the package through its definition attribute. 
 * <p>
 * e1 <- property_definition.definition
 * <p>
 * @param e1 the starting entity of type package
 * @return an aggregate containing all referencing property_definition
 */
public  AProperty_definition  All_pd_referencing_p (EPackage e1) throws SdaiException;  


/**
 * Given a product_definition_formation, e1
 * this method will return an aggregate containing all product_definition
 * that references the product_definition_formation through its formation attribute. 
 * <p>
 * e1 <- product_definition.formation
 * <p>
 * @param e1 the starting entity of type product_definition_formation
 * @return an aggregate containing all referencing product_definition
 */
public  AProduct_definition  All_pd_referencing_pdf (EProduct_definition_formation e1) throws SdaiException;  


/**
 * Given a package_terminal_template_definition, e1
 * this method will return an aggregate containing all property_definition
 * that references the package_terminal_template_definition through its definition attribute. 
 * <p>
 * e1 <- property_definition.definition
 * <p>
 * @param e1 the starting entity of type package_terminal_template_definition
 * @return an aggregate containing all referencing property_definition
 */
public  AProperty_definition  All_pd_referencing_pttd (EPackage_terminal_template_definition e1) throws SdaiException;  


/**
 * Given a shape_aspect, e1
 * this method will return an aggregate containing all property_definition
 * that references the shape_aspect through its definition attribute. 
 * <p>
 * e1 <- property_definition.definition
 * <p>
 * @param e1 the starting entity of type shape_aspect
 * @return an aggregate containing all referencing property_definition
 */
public  AProperty_definition  All_pd_referencing_sa (EShape_aspect e1) throws SdaiException;  


/**
 * Given a layered_assembly_module_design_view, e1
 * this method will return an aggregate containing all product_definition
 * related by a next_assembly_usage_occurrence_relationship
 * <p>
 * e1 <- next_assembly_usage_occurrence_relationship.relating_product_definition
 * <br>next_assembly_usage_occurrence_relationship.occurrence -> product_definition
 * <p>
 * @param e1 the starting entity of layered_assembly_module_design_view
 * @return an aggregate containing all related product_definition
 */
public  AProduct_definition All_pd_relatedTo_lamdv_through_nauor (ELayered_assembly_module_design_view e1) throws SdaiException; 



/**
 * Given a shape_representation, e1
 * this method will return an aggregate containing all property_definition
 * related by a property_definition_representation
 * <p>
 * e1 <- property_definition_representation.used_representation
 * <br>property_definition_representation.definition -> property_definition
 * <p>
 * @param e1 the starting entity of shape_representation
 * @return an aggregate containing all related property_definition
 */
public  AProperty_definition All_pd_relatedTo_sr_through_pdr (EShape_representation e1) throws SdaiException; 



/**
 * Given a product_definition, e1
 * this method will return an aggregate containing all product_definition_context_association
 * that references the product_definition through its definition attribute. 
 * <p>
 * e1 <- product_definition_context_association.definition
 * <p>
 * @param e1 the starting entity of type product_definition
 * @return an aggregate containing all referencing product_definition_context_association
 */
public  AProduct_definition_context_association  All_pdca_referencing_pd (EProduct_definition e1) throws SdaiException;  


/**
 * Given a assembly_component_usage, e1
 * this method will return an aggregate containing all product_definition_shape
 * that references the assembly_component_usage through its definition attribute. 
 * <p>
 * e1 <- product_definition_shape.definition
 * <p>
 * @param e1 the starting entity of type assembly_component_usage
 * @return an aggregate containing all referencing product_definition_shape
 */
public  AProduct_definition_shape  All_pds_referencing_acu (EAssembly_component_usage e1) throws SdaiException;  


/**
 * Given a next_assembly_usage_occurrence_relationship, e1
 * this method will return an aggregate containing all product_definition_shape
 * that references the next_assembly_usage_occurrence_relationship through its definition attribute. 
 * <p>
 * e1 <- product_definition_shape.definition
 * <p>
 * @param e1 the starting entity of type next_assembly_usage_occurrence_relationship
 * @return an aggregate containing all referencing product_definition_shape
 */
public  AProduct_definition_shape  All_pds_referencing_nauor (ENext_assembly_usage_occurrence_relationship e1) throws SdaiException;  


/**
 * Given a layered_interconnect_module_design_view, e1
 * this method will return an aggregate containing all physical_network
 * that references the layered_interconnect_module_design_view through its of_shape attribute. 
 * <p>
 * e1 <- physical_network.of_shape
 * <p>
 * @param e1 the starting entity of type layered_interconnect_module_design_view
 * @return an aggregate containing all referencing physical_network
 */
public  APhysical_network  All_pn_referencing_limdv (ELayered_interconnect_module_design_view e1) throws SdaiException;  


/**
 * Given a layered_interconnect_module_design_view, e1
 * this method will return an aggregate containing all padstack_occurrence
 * related by a next_assembly_usage_occurrence_relationship
 * <p>
 * e1 <- next_assembly_usage_occurrence_relationship.relating_product_definition
 * <br>next_assembly_usage_occurrence_relationship.occurrence -> padstack_occurrence
 * <p>
 * @param e1 the starting entity of layered_interconnect_module_design_view
 * @return an aggregate containing all related padstack_occurrence
 */
public  APadstack_occurrence All_po_relatedTo_limdv_through_nauor (ELayered_interconnect_module_design_view e1) throws SdaiException; 



/**
 * Given a inter_stratum_feature, e1
 * this method will return an aggregate containing all plated_passage_dependent_land
 * related by a product_definition_relationship
 * and qualified by a specified value of the name attribute, v1
 * <p>
 * e1 <- product_definition_relationship.relating_product_definition
 * <br>product_definition_relationship.name = v1
 * <br>product_definition_relationship.related_product_definition -> plated_passage_dependent_land
 * <p>
 * @param e1 the starting entity of inter_stratum_feature
 * @param v1 the qualifying attribute value for the relating entity's name attribute
 * @return an aggregate containing all related plated_passage_dependent_land
 */
public  APlated_passage_dependent_land All_ppdl_relatedTo_isf_through_pdr (EInter_stratum_feature e1, String v1) throws SdaiException; 


/**
 * This method will return an aggregate containing all product_related_product_category
 * in the current SDAI model. 
 * The return entity is qualified by a specified value of the name attribute.
 * <p>
 * product_related_product_category.name = v1
 * <p>
 * @param v1 the qualifying attribute value for the referencing entity's name attribute
 * @return an aggregate containing all product_related_product_category
 */
public AProduct_related_product_category All_prpc_inModel (String v1) throws SdaiException; 


/**
 * Given a product, e1
 * this method will return an aggregate containing all product_related_product_category
 * that references the product through its products attribute. 
 * <p>
 * e1 <- product_related_product_category.products
 * <p>
 * @param e1 the starting entity of type product
 * @return an aggregate containing all referencing product_related_product_category
 */
public  AProduct_related_product_category  All_prpc_referencing_p (EProduct e1) throws SdaiException;  


/**
 * Given a product, e1
 * this method will return an aggregate containing all product_specific_parameter_value_assignment
 * that references the product through its products attribute. 
 * <p>
 * e1 <- product_specific_parameter_value_assignment.products
 * <p>
 * @param e1 the starting entity of type product
 * @return an aggregate containing all referencing product_specific_parameter_value_assignment
 */
public  AProduct_specific_parameter_value_assignment  All_pspva_referencing_p (EProduct e1) throws SdaiException;  


/**
 * Given a package, e1
 * this method will return an aggregate containing all package_terminal
 * that references the package through its of_shape attribute. 
 * <p>
 * e1 <- package_terminal.of_shape
 * <p>
 * @param e1 the starting entity of type package
 * @return an aggregate containing all referencing package_terminal
 */
public  APackage_terminal  All_pt_referencing_p (EPackage e1) throws SdaiException;  


/**
 * Given a shape_representation, e1
 * this method will return an aggregate containing all part_tooling_feature
 * related by a usage_concept_usage_relationship
 * <p>
 * e1 <- usage_concept_usage_relationship.used_representation
 * <br>usage_concept_usage_relationship.definition -> part_tooling_feature
 * <p>
 * @param e1 the starting entity of shape_representation
 * @return an aggregate containing all related part_tooling_feature
 */
public  APart_tooling_feature All_ptf_relatedTo_sr_through_ucur (EShape_representation e1) throws SdaiException; 



/**
 * Given a shape_representation, e1
 * this method will return an aggregate containing all representation
 * related by a representation_relationship
 * <p>
 * e1 <- representation_relationship.rep_1
 * <br>representation_relationship.rep_2 -> representation
 * <p>
 * @param e1 the starting entity of shape_representation
 * @return an aggregate containing all related representation
 */
public  ARepresentation All_r_relatedTo_sr_through_rr (EShape_representation e1) throws SdaiException; 



/**
 * Given a representation, e1
 * this method will return an aggregate containing all representation_item
 * referenced by the representation through its items attribute. 
 * The return entity is qualified by a specified value of the name attribute.
 * <p>
 * e1.items ->
 * representation_item
 * <p>representation_item.name = v1
 * <p>
 * @param e1 the starting entity of type representation
 * @param v1 the qualifying attribute value for the referencing entity's name attribute
  * @return an aggregate containing all referenced representation_item
 */

public  ARepresentation_item All_ri_referencedBy_r (ERepresentation e1, String v1) throws SdaiException;  


/**
 * Given a layered_interconnect_module_design_view, e1
 * this method will return an aggregate containing all stratum
 * related by a assembly_component_usage
 * and qualified by a specified value of the name attribute, v1
 * <p>
 * e1 <- assembly_component_usage.relating_product_definition
 * <br>assembly_component_usage.name = v1
 * <br>assembly_component_usage.related_product_definition -> stratum
 * <p>
 * @param e1 the starting entity of layered_interconnect_module_design_view
 * @param v1 the qualifying attribute value for the relating entity's name attribute
 * @return an aggregate containing all related stratum
 */
public  AStratum All_s_relatedTo_limdv_through_acu (ELayered_interconnect_module_design_view e1, String v1) throws SdaiException; 



/**
 * Given a product_definition_shape, e1
 * this method will return an aggregate containing all shape_aspect
 * that references the product_definition_shape through its of_shape attribute. 
 * <p>
 * e1 <- shape_aspect.of_shape
 * <p>
 * @param e1 the starting entity of type product_definition_shape
 * @return an aggregate containing all referencing shape_aspect
 */
public  AShape_aspect  All_sa_referencing_pds (EProduct_definition_shape e1) throws SdaiException;  


/**
 * Given a advanced_face, e1
 * this method will return an aggregate containing all shape_aspect
 * related by a item_identified_representation_usage
 * <p>
 * e1 <- item_identified_representation_usage.identified_item
 * <br>item_identified_representation_usage.definition -> shape_aspect
 * <p>
 * @param e1 the starting entity of advanced_face
 * @return an aggregate containing all related shape_aspect
 */
public  AShape_aspect All_sa_relatedTo_af_through_iiru (EAdvanced_face e1) throws SdaiException; 



/**
 * Given a composite_group_shape_aspect, e1
 * this method will return an aggregate containing all shape_aspect
 * related by a shape_aspect_relationship
 * <p>
 * e1 <- shape_aspect_relationship.relating_shape_aspect
 * <br>shape_aspect_relationship.related_shape_aspect -> shape_aspect
 * <p>
 * @param e1 the starting entity of composite_group_shape_aspect
 * @return an aggregate containing all related shape_aspect
 */
public  AShape_aspect All_sa_relatedTo_cgsa_through_sar (EComposite_group_shape_aspect e1) throws SdaiException; 



/**
 * Given a derived_shape_aspect, e1
 * this method will return an aggregate containing all shape_aspect
 * related by a shape_aspect_deriving_relationship
 * <p>
 * e1 <- shape_aspect_deriving_relationship.relating_shape_aspect
 * <br>shape_aspect_deriving_relationship.related_shape_aspect -> shape_aspect
 * <p>
 * @param e1 the starting entity of derived_shape_aspect
 * @return an aggregate containing all related shape_aspect
 */
public  AShape_aspect All_sa_relatedTo_dsa_through_sadr (EDerived_shape_aspect e1) throws SdaiException; 



/**
 * Given a shape_aspect, e1
 * this method will return an aggregate containing all shape_aspect
 * related by a shape_aspect_relationship
 * <p>
 * e1 <- shape_aspect_relationship.relating_shape_aspect
 * <br>shape_aspect_relationship.related_shape_aspect -> shape_aspect
 * <p>
 * @param e1 the starting entity of shape_aspect
 * @return an aggregate containing all related shape_aspect
 */
public  AShape_aspect All_sa_relatedTo_sa_through_sar (EShape_aspect e1) throws SdaiException; 



/**
 * Given a layered_interconnect_module_design_view, e1
 * this method will return an aggregate containing all stratum_feature_template_component
 * related by a next_assembly_usage_occurrence_relationship
 * <p>
 * e1 <- next_assembly_usage_occurrence_relationship.relating_product_definition
 * <br>next_assembly_usage_occurrence_relationship.occurrence -> stratum_feature_template_component
 * <p>
 * @param e1 the starting entity of layered_interconnect_module_design_view
 * @return an aggregate containing all related stratum_feature_template_component
 */
public  AStratum_feature_template_component All_sftc_relatedTo_limdv_through_nauor (ELayered_interconnect_module_design_view e1) throws SdaiException; 



/**
 * Given a layered_interconnect_module_design_view, e1
 * this method will return an aggregate containing all structured_layout_component
 * related by a next_assembly_usage_occurrence_relationship
 * <p>
 * e1 <- next_assembly_usage_occurrence_relationship.relating_product_definition
 * <br>next_assembly_usage_occurrence_relationship.occurrence -> structured_layout_component
 * <p>
 * @param e1 the starting entity of layered_interconnect_module_design_view
 * @return an aggregate containing all related structured_layout_component
 */
public  AStructured_layout_component All_slc_relatedTo_limdv_through_nauor (ELayered_interconnect_module_design_view e1) throws SdaiException; 



/**
 * Given a structured_layout_component, e1
 * this method will return an aggregate containing all structured_layout_component
 * related by a structured_layout_component_sub_assembly_relationship
 * <p>
 * e1 <- structured_layout_component_sub_assembly_relationship.relating_product_definition
 * <br>structured_layout_component_sub_assembly_relationship.occurrence -> structured_layout_component
 * <p>
 * @param e1 the starting entity of structured_layout_component
 * @return an aggregate containing all related structured_layout_component
 */
public  AStructured_layout_component All_slc_relatedTo_slc_through_slcsar (EStructured_layout_component e1) throws SdaiException; 



/**
 * Given a mapped_item, e1
 * this method will return an aggregate containing all shape_representation
 * that references the mapped_item through its items attribute. 
 * <p>
 * e1 <- shape_representation.items
 * <p>
 * @param e1 the starting entity of type mapped_item
 * @return an aggregate containing all referencing shape_representation
 */
public  AShape_representation  All_sr_referencing_mi (EMapped_item e1) throws SdaiException;  


/**
 * Given a package, e1
 * this method will return an aggregate containing all shape_representation
 * related by a shape_definition_representation
 * <p>
 * e1 <- shape_definition_representation.definition
 * <br>shape_definition_representation.used_representation -> shape_representation
 * <p>
 * @param e1 the starting entity of package
 * @return an aggregate containing all related shape_representation
 */
public  AShape_representation All_sr_relatedTo_p_through_sdr (EPackage e1) throws SdaiException; 



/**
 * Given a product_definition_shape, e1
 * this method will return an aggregate containing all shape_representation
 * related by a shape_definition_representation
 * <p>
 * e1 <- shape_definition_representation.definition
 * <br>shape_definition_representation.used_representation -> shape_representation
 * <p>
 * @param e1 the starting entity of product_definition_shape
 * @return an aggregate containing all related shape_representation
 */
public  AShape_representation All_sr_relatedTo_pds_through_sdr (EProduct_definition_shape e1) throws SdaiException; 



/**
 * Given a product_definition_shape, e1
 * this method will return an aggregate containing all shape_representation
 * related by a shape_definition_representation
 * <p>
 * e1 <- shape_definition_representation.definition
 * <br>shape_definition_representation.used_representation -> shape_representation
 * <p>
 * @param e1 the starting entity of product_definition_shape
 * @return an aggregate containing all related shape_representation
 */
public  AShape_representation All_sr_relatedTo_pds_through_sdr_2 (EProduct_definition_shape e1) throws SdaiException; 



/**
 * Given a shape_representation, e1
 * this method will return an aggregate containing all shape_representation
 * related by a representation_relationship
 * and qualified by a specified value of the name attribute, v1
 * <p>
 * e1 <- representation_relationship.rep_1
 * <br>representation_relationship.name = v1
 * <br>representation_relationship.rep_2 -> shape_representation
 * <p>
 * @param e1 the starting entity of shape_representation
 * @param v1 the qualifying attribute value for the relating entity's name attribute
 * @return an aggregate containing all related shape_representation
 */
public  AShape_representation All_sr_relatedTo_sr_through_rr (EShape_representation e1, String v1) throws SdaiException; 



/**
 * Given a shape_representation, e1
 * this method will return an aggregate containing all shape_representation
 * related by a shape_representation_relationship
 * <p>
 * e1 <- shape_representation_relationship.rep_2
 * <br>shape_representation_relationship.rep_1 -> shape_representation
 * <p>
 * @param e1 the starting entity of shape_representation
 * @return an aggregate containing all related shape_representation
 */
public  AShape_representation All_sr_relatedTo_sr_through_srr (EShape_representation e1) throws SdaiException; 



/**
 * Given a stratum_surface, e1
 * this method will return an aggregate containing all stratum_surface
 * related by a shape_aspect_relationship
 * and qualified by a specified value of the description attribute, v1
 * <p>
 * e1 <- shape_aspect_relationship.relating_shape_aspect
 * <br>shape_aspect_relationship.description = v1
 * <br>shape_aspect_relationship.related_shape_aspect -> stratum_surface
 * <p>
 * @param e1 the starting entity of stratum_surface
 * @param v1 the qualifying attribute value for the relating entity's description attribute
 * @return an aggregate containing all related stratum_surface
 */
public  AStratum_surface All_ss_relatedTo_ss_through_sar (EStratum_surface e1, String v1) throws SdaiException; 



/**
 * Given a stratum_surface, e1
 * this method will return an aggregate containing all stratum_surface
 * related by a shape_aspect_relationship
 * and qualified by a specified value of the description attribute, v1
 * <p>
 * e1 <- shape_aspect_relationship.related_shape_aspect
 * <br>shape_aspect_relationship.description = v1
 * <br>shape_aspect_relationship.relating_shape_aspect -> stratum_surface
 * <p>
 * @param e1 the starting entity of stratum_surface
 * @param v1 the qualifying attribute value for the relating entity's description attribute
 * @return an aggregate containing all related stratum_surface
 */
public  AStratum_surface All_ss_relatedTo_ss_through_sar_2 (EStratum_surface e1, String v1) throws SdaiException; 



/**
 * Given a layered_interconnect_module_design_view, e1
 * this method will return an aggregate containing all single_stratum_special_symbol_component
 * related by a next_assembly_usage_occurrence_relationship
 * <p>
 * e1 <- next_assembly_usage_occurrence_relationship.relating_product_definition
 * <br>next_assembly_usage_occurrence_relationship.occurrence -> single_stratum_special_symbol_component
 * <p>
 * @param e1 the starting entity of layered_interconnect_module_design_view
 * @return an aggregate containing all related single_stratum_special_symbol_component
 */
public  ASingle_stratum_special_symbol_component All_ssssc_relatedTo_limdv_through_nauor (ELayered_interconnect_module_design_view e1) throws SdaiException; 



/**
 * Given a stratum_technology_occurrence, e1
 * this method will return an aggregate containing all stratum_technology_occurrence_link
 * that references the stratum_technology_occurrence through its related_property_definition attribute. 
 * <p>
 * e1 <- stratum_technology_occurrence_link.related_property_definition
 * <p>
 * @param e1 the starting entity of type stratum_technology_occurrence
 * @return an aggregate containing all referencing stratum_technology_occurrence_link
 */
public  AStratum_technology_occurrence_link  All_stol_referencing_sto (EStratum_technology_occurrence e1) throws SdaiException;  


/**
 * Given a stratum_technology_occurrence, e1
 * this method will return an aggregate containing all stratum_technology_occurrence_link
 * that references the stratum_technology_occurrence through its relating_property_definition attribute. 
 * <p>
 * e1 <- stratum_technology_occurrence_link.relating_property_definition
 * <p>
 * @param e1 the starting entity of type stratum_technology_occurrence
 * @return an aggregate containing all referencing stratum_technology_occurrence_link
 */
public  AStratum_technology_occurrence_link  All_stol_referencing_sto_2 (EStratum_technology_occurrence e1) throws SdaiException;  


/**
 * Given a passage_technology_allocation_to_stack_model, e1
 * this method will return an aggregate containing all stratum_technology_occurrence_link
 * related by a property_definition_relationship
 * and qualified by a specified value of the name attribute, v1
 * <p>
 * e1 <- property_definition_relationship.related_property_definition
 * <br>property_definition_relationship.name = v1
 * <br>property_definition_relationship.relating_property_definition -> stratum_technology_occurrence_link
 * <p>
 * @param e1 the starting entity of passage_technology_allocation_to_stack_model
 * @param v1 the qualifying attribute value for the relating entity's name attribute
 * @return an aggregate containing all related stratum_technology_occurrence_link
 */
public  AStratum_technology_occurrence_link All_stol_relatedTo_ptatsm_through_pdr (EPassage_technology_allocation_to_stack_model e1, String v1) throws SdaiException; 



/**
 * Given a shape_aspect, e1
 * this method will return an aggregate containing all usage_concept_usage_relationship
 * that references the shape_aspect through its definition attribute. 
 * <p>
 * e1 <- usage_concept_usage_relationship.definition
 * <p>
 * @param e1 the starting entity of type shape_aspect
 * @return an aggregate containing all referencing usage_concept_usage_relationship
 */
public  AUsage_concept_usage_relationship  All_ucur_referencing_sa (EShape_aspect e1) throws SdaiException;  


/**
 * Given a property_definition, e1
 * this method will return a assembly_component_usage
 * referenced by the property_definition through its definition attribute. 
 * The return entity is qualified by a specified value of the name attribute.
 * <p>
 * e1.definition ->
 * assembly_component_usage
 * <p>assembly_component_usage.name = v1
 * <p>
 * @param e1 the starting entity of type property_definition
 * @param v1 the qualifying attribute value for the referencing entity's name attribute
  * @return the referenced assembly_component_usage
 */

public EAssembly_component_usage acu_referencedBy_pd (EProperty_definition e1, String v1) throws SdaiException;  


/**
 * Given a physical_component_terminal, e1
 * this method will return a assembly_joint
 * that references the physical_component_terminal through its relating_shape_aspect attribute. 
 * <p>
 * e1 <- assembly_joint.relating_shape_aspect
 * <p>
 * @param e1 the starting entity of type physical_component_terminal
 * @return the referencing assembly_joint
 */
public EAssembly_joint aj_referencing_pct (EPhysical_component_terminal e1) throws SdaiException;  


/**
 * Given a mapped_item, e1
 * this method will return a axis2_placement_2d
 * referenced by the mapped_item through its mapping_target attribute. 
 * <p>
 * e1.mapping_target ->
 * axis2_placement_2d
 * <p>
 * @param e1 the starting entity of type mapped_item
  * @return the referenced axis2_placement_2d
 */

public EAxis2_placement_2d ap2d_referencedBy_mi (EMapped_item e1) throws SdaiException;  


/**
 * Given an aggregate of component_2d_location,
 * this method will return the first entity in the aggregate that 
 * that references the given entities through the qualifying attributes.
 * <p>
 * <br>component_2d_location.rep_1 = e1
 * <br>component_2d_location.rep_2 = e2
 * <p> 
 * @param a1 the given aggregate of type component_2d_location
 * @param e1 given referenced entity 1
 * @param e2 given referenced entity 2
 * @return the first component_2d_location that references the given entities
 */
public EComponent_2d_location c2dl_referencingGiven (
			AComponent_2d_location a1
			, EEntity e1, EEntity e2
			) throws SdaiException; 


/**
 * Given an aggregate of component_2d_location,
 * this method will return the first entity in the aggregate that 
 * that references the given entities through the qualifying attributes.
 * <p>
 * <br>component_2d_location.rep_2 = e1
 * <p> 
 * @param a1 the given aggregate of type component_2d_location
 * @param e1 given referenced entity 1
 * @return the first component_2d_location that references the given entities
 */
public EComponent_2d_location c2dl_referencingGiven_2 (
			AComponent_2d_location a1
			, EEntity e1
			) throws SdaiException; 


/**
 * Given a product_definition_shape, e1
 * this method will return a component_2d_location
 * that references the product_definition_shape through its represented_product_relation attribute. 
 * <p>
 * e1 <- component_2d_location.represented_product_relation
 * <p>
 * @param e1 the starting entity of type product_definition_shape
 * @return the referencing component_2d_location
 */
public EComponent_2d_location c2dl_referencing_pds (EProduct_definition_shape e1) throws SdaiException;  


/**
 * Given a shape_aspect, e1
 * this method will return a connected_area_component
 * referenced by the shape_aspect through its of_shape attribute. 
 * <p>
 * e1.of_shape ->
 * connected_area_component
 * <p>
 * @param e1 the starting entity of type shape_aspect
  * @return the referenced connected_area_component
 */

public EConnected_area_component cac_referencedBy_sa (EShape_aspect e1) throws SdaiException;  


/**
 * Given a laminate_component_feature, e1
 * this method will return a conductive_interconnect_element
 * referenced by the laminate_component_feature through its of_shape attribute. 
 * <p>
 * e1.of_shape ->
 * conductive_interconnect_element
 * <p>
 * @param e1 the starting entity of type laminate_component_feature
  * @return the referenced conductive_interconnect_element
 */

public EConductive_interconnect_element cie_referencedBy_lcf (ELaminate_component_feature e1) throws SdaiException;  


/**
 * Given a representation, e1
 * this method will return a cartesian_point
 * referenced by the representation through its items attribute. 
 * <p>
 * e1.items ->
 * cartesian_point
 * <p>
 * @param e1 the starting entity of type representation
  * @return the referenced cartesian_point
 */

public ECartesian_point cp_referencedBy_r (ERepresentation e1) throws SdaiException;  


/**
 * Given a laminate_component_interface_terminal, e1
 * this method will return a contact_size_dependent_land
 * referenced by the laminate_component_interface_terminal through its of_shape attribute. 
 * <p>
 * e1.of_shape ->
 * contact_size_dependent_land
 * <p>
 * @param e1 the starting entity of type laminate_component_interface_terminal
  * @return the referenced contact_size_dependent_land
 */

public EContact_size_dependent_land csdl_referencedBy_lcit (ELaminate_component_interface_terminal e1) throws SdaiException;  


/**
 * Given a component_2d_location, e1
 * this method will return a cartesian_transformation_operator_2d
 * referenced by the component_2d_location through its transformation_operator attribute. 
 * <p>
 * e1.transformation_operator ->
 * cartesian_transformation_operator_2d
 * <p>
 * @param e1 the starting entity of type component_2d_location
  * @return the referenced cartesian_transformation_operator_2d
 */

public ECartesian_transformation_operator_2d cto2d_referencedBy_c2dl (EComponent_2d_location e1) throws SdaiException;  


/**
 * Given a datum, e1
 * this method will return a datum_feature
 * related by a shape_aspect_relationship
 * <p>
 * e1 <- shape_aspect_relationship.related_shape_aspect
 * <br>shape_aspect_relationship.relating_shape_aspect -> datum_feature
 * <p>
 * @param e1 the starting entity of datum
 * @return the related datum_feature
 */
public EDatum_feature df_relatedTo_d_through_sar (EDatum e1) throws SdaiException; 



/**
 * Given a layered_interconnect_module_design_view, e1
 * this method will return a design_stack_model
 * related by a property_definition_relationship
 * and qualified by a specified value of the name attribute, v1
 * <p>
 * e1 <- property_definition_relationship.related_property_definition
 * <br>property_definition_relationship.name = v1
 * <br>property_definition_relationship.relating_property_definition -> design_stack_model
 * <p>
 * @param e1 the starting entity of layered_interconnect_module_design_view
 * @param v1 the qualifying attribute value for the relating entity's name attribute
 * @return the related design_stack_model
 */
public EDesign_stack_model dsm_relatedTo_limdv_through_pdr (ELayered_interconnect_module_design_view e1, String v1) throws SdaiException; 



/**
 * Given a derived_unit, e1
 * this method will return a derived_unit_element
 * referenced by the derived_unit through its elements attribute. 
 * <p>
 * e1.elements ->
 * derived_unit_element
 * <p>
 * @param e1 the starting entity of type derived_unit
  * @return the referenced derived_unit_element
 */

public EDerived_unit_element due_referencedBy_du (EDerived_unit e1) throws SdaiException;  


/**
 * Given a assembly_component, e1
 * this method will return a geometric_template
 * related by a product_definition_relationship
 * <p>
 * e1 <- product_definition_relationship.related_product_definition
 * <br>product_definition_relationship.relating_product_definition -> geometric_template
 * <p>
 * @param e1 the starting entity of assembly_component
 * @return the related geometric_template
 */
public EGeometric_template gt_relatedTo_ac_through_pdr (EAssembly_component e1) throws SdaiException; 



/**
 * Given a assembly_component, e1
 * this method will return a geometric_template
 * related by a product_definition_relationship
 * and qualified by a specified value of the name attribute, v1
 * <p>
 * e1 <- product_definition_relationship.related_product_definition
 * <br>product_definition_relationship.name = v1
 * <br>product_definition_relationship.relating_product_definition -> geometric_template
 * <p>
 * @param e1 the starting entity of assembly_component
 * @param v1 the qualifying attribute value for the relating entity's name attribute
 * @return the related geometric_template
 */
public EGeometric_template gt_relatedTo_ac_through_pdr_2 (EAssembly_component e1, String v1) throws SdaiException; 



/**
 * Given a characterized_class, e1
 * this method will return a id_attribute
 * that references the characterized_class through its identified_item attribute. 
 * The return entity is qualified by a specified value of the attribute_value attribute.
 * <p>
 * e1 <- id_attribute.identified_item
 * <p>id_attribute.attribute_value = v1
 * <p>
 * @param e1 the starting entity of type characterized_class
 * @param v1 the qualifying attribute value for the referencing entity's attribute_value attribute
 * @return the referencing id_attribute
 */
public EId_attribute ia_referencing_cc (ECharacterized_class e1, String v1) throws SdaiException;  


/**
 * Given a shape_representation, e1
 * this method will return a id_attribute
 * that references the shape_representation through its identified_item attribute. 
 * The return entity is qualified by a specified value of the attribute_value attribute.
 * <p>
 * e1 <- id_attribute.identified_item
 * <p>id_attribute.attribute_value = v1
 * <p>
 * @param e1 the starting entity of type shape_representation
 * @param v1 the qualifying attribute value for the referencing entity's attribute_value attribute
 * @return the referencing id_attribute
 */
public EId_attribute ia_referencing_sr (EShape_representation e1, String v1) throws SdaiException;  


/**
 * Given a shape_aspect, e1
 * this method will return a item_identified_representation_usage
 * that references the shape_aspect through its definition attribute. 
 * <p>
 * e1 <- item_identified_representation_usage.definition
 * <p>
 * @param e1 the starting entity of type shape_aspect
 * @return the referencing item_identified_representation_usage
 */
public EItem_identified_representation_usage iiru_referencing_sa (EShape_aspect e1) throws SdaiException;  


/**
 * Given a layered_interconnect_module_design_view, e1
 * this method will return a interconnect_module_edge
 * related by a next_assembly_usage_occurrence_relationship
 * <p>
 * e1 <- next_assembly_usage_occurrence_relationship.relating_product_definition
 * <br>next_assembly_usage_occurrence_relationship.occurrence -> interconnect_module_edge
 * <p>
 * @param e1 the starting entity of layered_interconnect_module_design_view
 * @return the related interconnect_module_edge
 */
public EInterconnect_module_edge ime_relatedTo_limdv_through_nauor (ELayered_interconnect_module_design_view e1) throws SdaiException; 



/**
 * Given a laminate_component_interface_terminal, e1
 * this method will return a interconnect_module_interface_terminal
 * related by a shape_aspect_relationship
 * and qualified by a specified value of the description attribute, v1
 * <p>
 * e1 <- shape_aspect_relationship.related_shape_aspect
 * <br>shape_aspect_relationship.description = v1
 * <br>shape_aspect_relationship.relating_shape_aspect -> interconnect_module_interface_terminal
 * <p>
 * @param e1 the starting entity of laminate_component_interface_terminal
 * @param v1 the qualifying attribute value for the relating entity's description attribute
 * @return the related interconnect_module_interface_terminal
 */
public EInterconnect_module_interface_terminal imit_relatedTo_lcit_through_sar (ELaminate_component_interface_terminal e1, String v1) throws SdaiException; 



/**
 * Given a layer_connection_point, e1
 * this method will return a interconnect_module_interface_terminal
 * related by a shape_aspect_relationship
 * and qualified by a specified value of the name attribute, v1
 * <p>
 * e1 <- shape_aspect_relationship.related_shape_aspect
 * <br>shape_aspect_relationship.name = v1
 * <br>shape_aspect_relationship.relating_shape_aspect -> interconnect_module_interface_terminal
 * <p>
 * @param e1 the starting entity of layer_connection_point
 * @param v1 the qualifying attribute value for the relating entity's name attribute
 * @return the related interconnect_module_interface_terminal
 */
public EInterconnect_module_interface_terminal imit_relatedTo_lcp_through_sar (ELayer_connection_point e1, String v1) throws SdaiException; 



/**
 * Given a physical_component_terminal, e1
 * this method will return a interconnect_module_interface_terminal
 * related by a shape_aspect_relationship
 * and qualified by a specified value of the name attribute, v1
 * <p>
 * e1 <- shape_aspect_relationship.related_shape_aspect
 * <br>shape_aspect_relationship.name = v1
 * <br>shape_aspect_relationship.relating_shape_aspect -> interconnect_module_interface_terminal
 * <p>
 * @param e1 the starting entity of physical_component_terminal
 * @param v1 the qualifying attribute value for the relating entity's name attribute
 * @return the related interconnect_module_interface_terminal
 */
public EInterconnect_module_interface_terminal imit_relatedTo_pct_through_sar (EPhysical_component_terminal e1, String v1) throws SdaiException; 



/**
 * Given a shape_aspect, e1
 * this method will return a inter_stratum_feature
 * referenced by the shape_aspect through its of_shape attribute. 
 * <p>
 * e1.of_shape ->
 * inter_stratum_feature
 * <p>
 * @param e1 the starting entity of type shape_aspect
  * @return the referenced inter_stratum_feature
 */

public EInter_stratum_feature isf_referencedBy_sa (EShape_aspect e1) throws SdaiException;  


/**
 * Given a shape_aspect, e1
 * this method will return a laminate_component
 * referenced by the shape_aspect through its of_shape attribute. 
 * <p>
 * e1.of_shape ->
 * laminate_component
 * <p>
 * @param e1 the starting entity of type shape_aspect
  * @return the referenced laminate_component
 */

public ELaminate_component lc_referencedBy_sa (EShape_aspect e1) throws SdaiException;  


/**
 * Given a assembly_component, e1
 * this method will return a laminate_component_interface_terminal
 * that references the assembly_component through its of_shape attribute. 
 * <p>
 * e1 <- laminate_component_interface_terminal.of_shape
 * <p>
 * @param e1 the starting entity of type assembly_component
 * @return the referencing laminate_component_interface_terminal
 */
public ELaminate_component_interface_terminal lcit_referencing_ac (EAssembly_component e1) throws SdaiException;  


/**
 * Given a interconnect_module_interface_terminal, e1
 * this method will return a laminate_component_interface_terminal
 * related by a shape_aspect_relationship
 * <p>
 * e1 <- shape_aspect_relationship.relating_shape_aspect
 * <br>shape_aspect_relationship.related_shape_aspect -> laminate_component_interface_terminal
 * <p>
 * @param e1 the starting entity of interconnect_module_interface_terminal
 * @return the related laminate_component_interface_terminal
 */
public ELaminate_component_interface_terminal lcit_relatedTo_imit_through_sar (EInterconnect_module_interface_terminal e1) throws SdaiException; 



/**
 * Given a interconnect_module_interface_terminal, e1
 * this method will return a laminate_component_interface_terminal
 * related by a shape_aspect_relationship
 * and qualified by a specified value of the description attribute, v1
 * <p>
 * e1 <- shape_aspect_relationship.relating_shape_aspect
 * <br>shape_aspect_relationship.description = v1
 * <br>shape_aspect_relationship.related_shape_aspect -> laminate_component_interface_terminal
 * <p>
 * @param e1 the starting entity of interconnect_module_interface_terminal
 * @param v1 the qualifying attribute value for the relating entity's description attribute
 * @return the related laminate_component_interface_terminal
 */
public ELaminate_component_interface_terminal lcit_relatedTo_imit_through_sar_2 (EInterconnect_module_interface_terminal e1, String v1) throws SdaiException; 



/**
 * Given a representation, e1
 * this method will return a length_measure_with_unit
 * referenced by the representation through its items attribute. 
 * <p>
 * e1.items ->
 * length_measure_with_unit
 * <p>
 * @param e1 the starting entity of type representation
  * @return the referenced length_measure_with_unit
 */

public ELength_measure_with_unit lmwu_referencedBy_r (ERepresentation e1) throws SdaiException;  


/**
 * Given a derived_unit_element, e1
 * this method will return a length_unit
 * referenced by the derived_unit_element through its unit attribute. 
 * <p>
 * e1.unit ->
 * length_unit
 * <p>
 * @param e1 the starting entity of type derived_unit_element
  * @return the referenced length_unit
 */

public ELength_unit lu_referencedBy_due (EDerived_unit_element e1) throws SdaiException;  


/**
 * Given a global_unit_assigned_context, e1
 * this method will return a length_unit
 * referenced by the global_unit_assigned_context through its units attribute. 
 * <p>
 * e1.units ->
 * length_unit
 * <p>
 * @param e1 the starting entity of type global_unit_assigned_context
  * @return the referenced length_unit
 */

public ELength_unit lu_referencedBy_guac (EGlobal_unit_assigned_context e1) throws SdaiException;  


/**
 * Given a stratum_technology, e1
 * this method will return a material_designation
 * that references the stratum_technology through its definitions attribute. 
 * <p>
 * e1 <- material_designation.definitions
 * <p>
 * @param e1 the starting entity of type stratum_technology
 * @return the referencing material_designation
 */
public EMaterial_designation md_referencing_st (EStratum_technology e1) throws SdaiException;  


/**
 * Given a shape_representation, e1
 * this method will return a mapped_item
 * referenced by the shape_representation through its items attribute. 
 * The return entity is qualified by a specified value of the name attribute.
 * <p>
 * e1.items ->
 * mapped_item
 * <p>mapped_item.name = v1
 * <p>
 * @param e1 the starting entity of type shape_representation
 * @param v1 the qualifying attribute value for the referencing entity's name attribute
  * @return the referenced mapped_item
 */

public EMapped_item mi_referencedBy_sr (EShape_representation e1, String v1) throws SdaiException;  


/**
 * Given a laminate_text_string_component, e1
 * this method will return a next_assembly_usage_occurrence
 * that references the laminate_text_string_component through its related_product_definition attribute. 
 * The return entity is qualified by a specified value of the name attribute.
 * <p>
 * e1 <- next_assembly_usage_occurrence.related_product_definition
 * <p>next_assembly_usage_occurrence.name = v1
 * <p>
 * @param e1 the starting entity of type laminate_text_string_component
 * @param v1 the qualifying attribute value for the referencing entity's name attribute
 * @return the referencing next_assembly_usage_occurrence
 */
public ENext_assembly_usage_occurrence nauo_referencing_ltsc (ELaminate_text_string_component e1, String v1) throws SdaiException;  


/**
 * Given a product_definition, e1
 * this method will return a next_assembly_usage_occurrence
 * that references the product_definition through its related_product_definition attribute. 
 * <p>
 * e1 <- next_assembly_usage_occurrence.related_product_definition
 * <p>
 * @param e1 the starting entity of type product_definition
 * @return the referencing next_assembly_usage_occurrence
 */
public ENext_assembly_usage_occurrence nauo_referencing_pd (EProduct_definition e1) throws SdaiException;  


/**
 * Given an aggregate of next_assembly_usage_occurrence_relationship,
 * this method will return the first entity in the aggregate that 
 * that references the given entities through the qualifying attributes.
 * <p>
 * <br>next_assembly_usage_occurrence_relationship.relating_product_definition = e1
 * <p> 
 * @param a1 the given aggregate of type next_assembly_usage_occurrence_relationship
 * @param e1 given referenced entity 1
 * @return the first next_assembly_usage_occurrence_relationship that references the given entities
 */
public ENext_assembly_usage_occurrence_relationship nauor_referencingGiven (
			ANext_assembly_usage_occurrence_relationship a1
			, EEntity e1
			) throws SdaiException; 


/**
 * Given a laminate_component, e1
 * this method will return a next_assembly_usage_occurrence_relationship
 * that references the laminate_component through its occurrence attribute. 
 * <p>
 * e1 <- next_assembly_usage_occurrence_relationship.occurrence
 * <p>
 * @param e1 the starting entity of type laminate_component
 * @return the referencing next_assembly_usage_occurrence_relationship
 */
public ENext_assembly_usage_occurrence_relationship nauor_referencing_lc (ELaminate_component e1) throws SdaiException;  


/**
 * Given a product_definition, e1
 * this method will return a next_assembly_usage_occurrence_relationship
 * that references the product_definition through its occurrence attribute. 
 * <p>
 * e1 <- next_assembly_usage_occurrence_relationship.occurrence
 * <p>
 * @param e1 the starting entity of type product_definition
 * @return the referencing next_assembly_usage_occurrence_relationship
 */
public ENext_assembly_usage_occurrence_relationship nauor_referencing_pd (EProduct_definition e1) throws SdaiException;  


/**
 * Given a structured_layout_component, e1
 * this method will return a next_assembly_usage_occurrence_relationship
 * that references the structured_layout_component through its occurrence attribute. 
 * <p>
 * e1 <- next_assembly_usage_occurrence_relationship.occurrence
 * <p>
 * @param e1 the starting entity of type structured_layout_component
 * @return the referencing next_assembly_usage_occurrence_relationship
 */
public ENext_assembly_usage_occurrence_relationship nauor_referencing_slc (EStructured_layout_component e1) throws SdaiException;  


/**
 * Given a product_related_product_category, e1
 * this method will return a product
 * referenced by the product_related_product_category through its products attribute. 
 * <p>
 * e1.products ->
 * product
 * <p>
 * @param e1 the starting entity of type product_related_product_category
  * @return the referenced product
 */

public EProduct p_referencedBy_prpc (EProduct_related_product_category e1) throws SdaiException;  


/**
 * Given a packaged_part, e1
 * this method will return a package
 * related by a product_definition_relationship
 * and qualified by a specified value of the name attribute, v1
 * <p>
 * e1 <- product_definition_relationship.related_product_definition
 * <br>product_definition_relationship.name = v1
 * <br>product_definition_relationship.relating_product_definition -> package
 * <p>
 * @param e1 the starting entity of packaged_part
 * @param v1 the qualifying attribute value for the relating entity's name attribute
 * @return the related package
 */
public EPackage p_relatedTo_pp_through_pdr (EPackaged_part e1, String v1) throws SdaiException; 



/**
 * Given a shape_representation, e1
 * this method will return a package
 * related by a shape_definition_representation
 * <p>
 * e1 <- shape_definition_representation.used_representation
 * <br>shape_definition_representation.definition -> package
 * <p>
 * @param e1 the starting entity of shape_representation
 * @return the related package
 */
public EPackage p_relatedTo_sr_through_sdr (EShape_representation e1) throws SdaiException; 



/**
 * Given a property_definition, e1
 * this method will return a parameter_assignment
 * related by a property_definition_representation
 * <p>
 * e1 <- property_definition_representation.definition
 * <br>property_definition_representation.used_representation -> parameter_assignment
 * <p>
 * @param e1 the starting entity of property_definition
 * @return the related parameter_assignment
 */
public EParameter_assignment pa_relatedTo_pd_through_pdr (EProperty_definition e1) throws SdaiException; 



/**
 * Given a package, e1
 * this method will return a package_body
 * that references the package through its of_shape attribute. 
 * <p>
 * e1 <- package_body.of_shape
 * <p>
 * @param e1 the starting entity of type package
 * @return the referencing package_body
 */
public EPackage_body pb_referencing_p (EPackage e1) throws SdaiException;  


/**
 * Given a advanced_face, e1
 * this method will return a package_body
 * related by a item_identified_representation_usage
 * <p>
 * e1 <- item_identified_representation_usage.identified_item
 * <br>item_identified_representation_usage.definition -> package_body
 * <p>
 * @param e1 the starting entity of advanced_face
 * @return the related package_body
 */
public EPackage_body pb_relatedTo_af_through_iiru (EAdvanced_face e1) throws SdaiException; 



/**
 * Given a physical_component_terminal, e1
 * this method will return a packaged_component
 * referenced by the physical_component_terminal through its of_shape attribute. 
 * <p>
 * e1.of_shape ->
 * packaged_component
 * <p>
 * @param e1 the starting entity of type physical_component_terminal
  * @return the referenced packaged_component
 */

public EPackaged_component pc_referencedBy_pct (EPhysical_component_terminal e1) throws SdaiException;  


/**
 * Given a assembly_joint, e1
 * this method will return a physical_component_terminal
 * referenced by the assembly_joint through its related_shape_aspect attribute. 
 * The return entity is qualified by a specified value of the description attribute.
 * <p>
 * e1.related_shape_aspect ->
 * physical_component_terminal
 * <p>physical_component_terminal.description = v1
 * <p>
 * @param e1 the starting entity of type assembly_joint
 * @param v1 the qualifying attribute value for the referencing entity's description attribute
  * @return the referenced physical_component_terminal
 */

public EPhysical_component_terminal pct_referencedBy_aj (EAssembly_joint e1, String v1) throws SdaiException;  


/**
 * Given a assembly_joint, e1
 * this method will return a physical_component_terminal
 * referenced by the assembly_joint through its relating_shape_aspect attribute. 
 * The return entity is qualified by a specified value of the description attribute.
 * <p>
 * e1.relating_shape_aspect ->
 * physical_component_terminal
 * <p>physical_component_terminal.description = v1
 * <p>
 * @param e1 the starting entity of type assembly_joint
 * @param v1 the qualifying attribute value for the referencing entity's description attribute
  * @return the referenced physical_component_terminal
 */

public EPhysical_component_terminal pct_referencedBy_aj_2 (EAssembly_joint e1, String v1) throws SdaiException;  


/**
 * Given a layer_connection_point, e1
 * this method will return a property_definition
 * that references the layer_connection_point through its definition attribute. 
 * <p>
 * e1 <- property_definition.definition
 * <p>
 * @param e1 the starting entity of type layer_connection_point
 * @return the referencing property_definition
 */
public EProperty_definition pd_referencing_lcp (ELayer_connection_point e1) throws SdaiException;  


/**
 * Given a laminate_text_string_component, e1
 * this method will return a property_definition
 * that references the laminate_text_string_component through its definition attribute. 
 * The return entity is qualified by a specified value of the description attribute.
 * <p>
 * e1 <- property_definition.definition
 * <p>property_definition.description = v1
 * <p>
 * @param e1 the starting entity of type laminate_text_string_component
 * @param v1 the qualifying attribute value for the referencing entity's description attribute
 * @return the referencing property_definition
 */
public EProperty_definition pd_referencing_ltsc (ELaminate_text_string_component e1, String v1) throws SdaiException;  


/**
 * Given a product_definition, e1
 * this method will return a property_definition
 * that references the product_definition through its definition attribute. 
 * The return entity is qualified by a specified value of the name attribute.
 * <p>
 * e1 <- property_definition.definition
 * <p>property_definition.name = v1
 * <p>
 * @param e1 the starting entity of type product_definition
 * @param v1 the qualifying attribute value for the referencing entity's name attribute
 * @return the referencing property_definition
 */
public EProperty_definition pd_referencing_pd (EProduct_definition e1, String v1) throws SdaiException;  


/**
 * Given a product_specific_parameter_value_assignment, e1
 * this method will return a property_definition
 * that references the product_specific_parameter_value_assignment through its definition attribute. 
 * The return entity is qualified by a specified value of the description attribute.
 * <p>
 * e1 <- property_definition.definition
 * <p>property_definition.description = v1
 * <p>
 * @param e1 the starting entity of type product_specific_parameter_value_assignment
 * @param v1 the qualifying attribute value for the referencing entity's description attribute
 * @return the referencing property_definition
 */
public EProperty_definition pd_referencing_pspva (EProduct_specific_parameter_value_assignment e1, String v1) throws SdaiException;  


/**
 * Given a part_text_template, e1
 * this method will return a property_definition
 * that references the part_text_template through its definition attribute. 
 * The return entity is qualified by a specified value of the description attribute.
 * <p>
 * e1 <- property_definition.definition
 * <p>property_definition.description = v1
 * <p>
 * @param e1 the starting entity of type part_text_template
 * @param v1 the qualifying attribute value for the referencing entity's description attribute
 * @return the referencing property_definition
 */
public EProperty_definition pd_referencing_ptt (EPart_text_template e1, String v1) throws SdaiException;  


/**
 * Given a shape_aspect, e1
 * this method will return a property_definition
 * that references the shape_aspect through its definition attribute. 
 * <p>
 * e1 <- property_definition.definition
 * <p>
 * @param e1 the starting entity of type shape_aspect
 * @return the referencing property_definition
 */
public EProperty_definition pd_referencing_sa (EShape_aspect e1) throws SdaiException;  


/**
 * Given a structured_layout_component_sub_assembly_relationship, e1
 * this method will return a property_definition
 * that references the structured_layout_component_sub_assembly_relationship through its definition attribute. 
 * <p>
 * e1 <- property_definition.definition
 * <p>
 * @param e1 the starting entity of type structured_layout_component_sub_assembly_relationship
 * @return the referencing property_definition
 */
public EProperty_definition pd_referencing_slcsar (EStructured_layout_component_sub_assembly_relationship e1) throws SdaiException;  


/**
 * Given a stratum_technology, e1
 * this method will return a property_definition
 * that references the stratum_technology through its definition attribute. 
 * The return entity is qualified by a specified value of the name attribute.
 * <p>
 * e1 <- property_definition.definition
 * <p>property_definition.name = v1
 * <p>
 * @param e1 the starting entity of type stratum_technology
 * @param v1 the qualifying attribute value for the referencing entity's name attribute
 * @return the referencing property_definition
 */
public EProperty_definition pd_referencing_st (EStratum_technology e1, String v1) throws SdaiException;  


/**
 * Given a product_definition, e1
 * this method will return a product_definition
 * related by a product_definition_relationship
 * and qualified by a specified value of the name attribute, v1
 * <p>
 * e1 <- product_definition_relationship.related_product_definition
 * <br>product_definition_relationship.name = v1
 * <br>product_definition_relationship.relating_product_definition -> product_definition
 * <p>
 * @param e1 the starting entity of product_definition
 * @param v1 the qualifying attribute value for the relating entity's name attribute
 * @return the related product_definition
 */
public EProduct_definition pd_relatedTo_pd_through_pdr (EProduct_definition e1, String v1) throws SdaiException; 



/**
 * Given a property_definition, e1
 * this method will return a property_definition
 * related by a property_definition_relationship
 * and qualified by a specified value of the name attribute, v1
 * <p>
 * e1 <- property_definition_relationship.related_property_definition
 * <br>property_definition_relationship.name = v1
 * <br>property_definition_relationship.relating_property_definition -> property_definition
 * <p>
 * @param e1 the starting entity of property_definition
 * @param v1 the qualifying attribute value for the relating entity's name attribute
 * @return the related property_definition
 */
public EProperty_definition pd_relatedTo_pd_through_pdr_2 (EProperty_definition e1, String v1) throws SdaiException; 



/**
 * Given a product_definition_context_association, e1
 * this method will return a product_definition_context
 * referenced by the product_definition_context_association through its frame_of_reference attribute. 
 * The return entity is qualified by a specified value of the name attribute.
 * <p>
 * e1.frame_of_reference ->
 * product_definition_context
 * <p>product_definition_context.name = v1
 * <p>
 * @param e1 the starting entity of type product_definition_context_association
 * @param v1 the qualifying attribute value for the referencing entity's name attribute
  * @return the referenced product_definition_context
 */

public EProduct_definition_context pdc_referencedBy_pdca (EProduct_definition_context_association e1, String v1) throws SdaiException;  


/**
 * Given a product_definition_context_association, e1
 * this method will return a product_definition_context_role
 * referenced by the product_definition_context_association through its role attribute. 
 * The return entity is qualified by a specified value of the name attribute.
 * <p>
 * e1.role ->
 * product_definition_context_role
 * <p>product_definition_context_role.name = v1
 * <p>
 * @param e1 the starting entity of type product_definition_context_association
 * @param v1 the qualifying attribute value for the referencing entity's name attribute
  * @return the referenced product_definition_context_role
 */

public EProduct_definition_context_role pdcr_referencedBy_pdca (EProduct_definition_context_association e1, String v1) throws SdaiException;  


/**
 * Given a product, e1
 * this method will return a product_definition_formation
 * that references the product through its of_product attribute. 
 * <p>
 * e1 <- product_definition_formation.of_product
 * <p>
 * @param e1 the starting entity of type product
 * @return the referencing product_definition_formation
 */
public EProduct_definition_formation pdf_referencing_p (EProduct e1) throws SdaiException;  


/**
 * Given a product_definition, e1
 * this method will return a product_definition_relationship
 * that references the product_definition through its related_product_definition attribute. 
 * The return entity is qualified by a specified value of the name attribute.
 * <p>
 * e1 <- product_definition_relationship.related_product_definition
 * <p>product_definition_relationship.name = v1
 * <p>
 * @param e1 the starting entity of type product_definition
 * @param v1 the qualifying attribute value for the referencing entity's name attribute
 * @return the referencing product_definition_relationship
 */
public EProduct_definition_relationship pdr_referencing_pd (EProduct_definition e1, String v1) throws SdaiException;  


/**
 * Given a product_definition, e1
 * this method will return a product_definition_relationship
 * that references the product_definition through its relating_product_definition attribute. 
 * The return entity is qualified by a specified value of the name attribute.
 * <p>
 * e1 <- product_definition_relationship.relating_product_definition
 * <p>product_definition_relationship.name = v1
 * <p>
 * @param e1 the starting entity of type product_definition
 * @param v1 the qualifying attribute value for the referencing entity's name attribute
 * @return the referencing product_definition_relationship
 */
public EProduct_definition_relationship pdr_referencing_pd_2 (EProduct_definition e1, String v1) throws SdaiException;  


/**
 * Given a property_definition, e1
 * this method will return a property_definition_representation
 * that references the property_definition through its definition attribute. 
 * <p>
 * e1 <- property_definition_representation.definition
 * <p>
 * @param e1 the starting entity of type property_definition
 * @return the referencing property_definition_representation
 */
public EProperty_definition_representation pdr_referencing_pd_3 (EProperty_definition e1) throws SdaiException;  


/**
 * Given a assembly_component_usage, e1
 * this method will return a product_definition_shape
 * that references the assembly_component_usage through its definition attribute. 
 * <p>
 * e1 <- product_definition_shape.definition
 * <p>
 * @param e1 the starting entity of type assembly_component_usage
 * @return the referencing product_definition_shape
 */
public EProduct_definition_shape pds_referencing_acu (EAssembly_component_usage e1) throws SdaiException;  


/**
 * Given a characterized_object, e1
 * this method will return a product_definition_shape
 * that references the characterized_object through its definition attribute. 
 * <p>
 * e1 <- product_definition_shape.definition
 * <p>
 * @param e1 the starting entity of type characterized_object
 * @return the referencing product_definition_shape
 */
public EProduct_definition_shape pds_referencing_co (ECharacterized_object e1) throws SdaiException;  


/**
 * Given a next_assembly_usage_occurrence_relationship, e1
 * this method will return a product_definition_shape
 * that references the next_assembly_usage_occurrence_relationship through its definition attribute. 
 * <p>
 * e1 <- product_definition_shape.definition
 * <p>
 * @param e1 the starting entity of type next_assembly_usage_occurrence_relationship
 * @return the referencing product_definition_shape
 */
public EProduct_definition_shape pds_referencing_nauor (ENext_assembly_usage_occurrence_relationship e1) throws SdaiException;  


/**
 * Given a packaged_component, e1
 * this method will return a packaged_part
 * referenced by the packaged_component through its relating_product_definition attribute. 
 * <p>
 * e1.relating_product_definition ->
 * packaged_part
 * <p>
 * @param e1 the starting entity of type packaged_component
  * @return the referenced packaged_part
 */

public EPackaged_part pp_referencedBy_pc (EPackaged_component e1) throws SdaiException;  


/**
 * Given a plated_passage_dependent_land, e1
 * this method will return a plated_passage
 * related by a product_definition_relationship
 * and qualified by a specified value of the name attribute, v1
 * <p>
 * e1 <- product_definition_relationship.related_product_definition
 * <br>product_definition_relationship.name = v1
 * <br>product_definition_relationship.relating_product_definition -> plated_passage
 * <p>
 * @param e1 the starting entity of plated_passage_dependent_land
 * @param v1 the qualifying attribute value for the relating entity's name attribute
 * @return the related plated_passage
 */
public EPlated_passage pp_relatedTo_ppdl_through_pdr (EPlated_passage_dependent_land e1, String v1) throws SdaiException; 



/**
 * Given a physical_component_terminal, e1
 * this method will return a packaged_part_terminal
 * related by a shape_aspect_relationship
 * and qualified by a specified value of the name attribute, v1
 * <p>
 * e1 <- shape_aspect_relationship.related_shape_aspect
 * <br>shape_aspect_relationship.name = v1
 * <br>shape_aspect_relationship.relating_shape_aspect -> packaged_part_terminal
 * <p>
 * @param e1 the starting entity of physical_component_terminal
 * @param v1 the qualifying attribute value for the relating entity's name attribute
 * @return the related packaged_part_terminal
 */
public EPackaged_part_terminal ppt_relatedTo_pct_through_sar (EPhysical_component_terminal e1, String v1) throws SdaiException; 



/**
 * Given a advanced_face, e1
 * this method will return a package_terminal
 * related by a item_identified_representation_usage
 * <p>
 * e1 <- item_identified_representation_usage.identified_item
 * <br>item_identified_representation_usage.definition -> package_terminal
 * <p>
 * @param e1 the starting entity of advanced_face
 * @return the related package_terminal
 */
public EPackage_terminal pt_relatedTo_af_through_iiru (EAdvanced_face e1) throws SdaiException; 



/**
 * Given a inter_stratum_feature, e1
 * this method will return a passage_technology_allocation_to_stack_model
 * related by a property_definition_relationship
 * and qualified by a specified value of the name attribute, v1
 * <p>
 * e1 <- property_definition_relationship.related_property_definition
 * <br>property_definition_relationship.name = v1
 * <br>property_definition_relationship.relating_property_definition -> passage_technology_allocation_to_stack_model
 * <p>
 * @param e1 the starting entity of inter_stratum_feature
 * @param v1 the qualifying attribute value for the relating entity's name attribute
 * @return the related passage_technology_allocation_to_stack_model
 */
public EPassage_technology_allocation_to_stack_model ptatsm_relatedTo_isf_through_pdr (EInter_stratum_feature e1, String v1) throws SdaiException; 



/**
 * Given a generic_laminate_text_component, e1
 * this method will return a part_text_template
 * related by a product_definition_relationship
 * and qualified by a specified value of the name attribute, v1
 * <p>
 * e1 <- product_definition_relationship.related_product_definition
 * <br>product_definition_relationship.name = v1
 * <br>product_definition_relationship.relating_product_definition -> part_text_template
 * <p>
 * @param e1 the starting entity of generic_laminate_text_component
 * @param v1 the qualifying attribute value for the relating entity's name attribute
 * @return the related part_text_template
 */
public EPart_text_template ptt_relatedTo_gltc_through_pdr (EGeneric_laminate_text_component e1, String v1) throws SdaiException; 



/**
 * Given a representation_context, e1
 * this method will return a representation
 * that references the representation_context through its context_of_items attribute. 
 * <p>
 * e1 <- representation.context_of_items
 * <p>
 * @param e1 the starting entity of type representation_context
 * @return the referencing representation
 */
public ERepresentation r_referencing_rc (ERepresentation_context e1) throws SdaiException;  


/**
 * Given a property_definition, e1
 * this method will return a representation
 * related by a property_definition_representation
 * <p>
 * e1 <- property_definition_representation.definition
 * <br>property_definition_representation.used_representation -> representation
 * <p>
 * @param e1 the starting entity of property_definition
 * @return the related representation
 */
public ERepresentation r_relatedTo_pd_through_pdr (EProperty_definition e1) throws SdaiException; 



/**
 * Given a representation, e1
 * this method will return a representation_item
 * referenced by the representation through its items attribute. 
 * <p>
 * e1.items ->
 * representation_item
 * <p>
 * @param e1 the starting entity of type representation
  * @return the referenced representation_item
 */

public ERepresentation_item ri_referencedBy_r (ERepresentation e1) throws SdaiException;  


/**
 * Given a mapped_item, e1
 * this method will return a representation_map
 * referenced by the mapped_item through its mapping_source attribute. 
 * <p>
 * e1.mapping_source ->
 * representation_map
 * <p>
 * @param e1 the starting entity of type mapped_item
  * @return the referenced representation_map
 */

public ERepresentation_map rm_referencedBy_mi (EMapped_item e1) throws SdaiException;  


/**
 * Given a stratum_feature, e1
 * this method will return a stratum
 * referenced by the stratum_feature through its of_shape attribute. 
 * <p>
 * e1.of_shape ->
 * stratum
 * <p>
 * @param e1 the starting entity of type stratum_feature
  * @return the referenced stratum
 */

public EStratum s_referencedBy_sf (EStratum_feature e1) throws SdaiException;  


/**
 * Given a stratum_surface, e1
 * this method will return a stratum
 * referenced by the stratum_surface through its of_shape attribute. 
 * <p>
 * e1.of_shape ->
 * stratum
 * <p>
 * @param e1 the starting entity of type stratum_surface
  * @return the referenced stratum
 */

public EStratum s_referencedBy_ss (EStratum_surface e1) throws SdaiException;  


/**
 * Given a laminate_component, e1
 * this method will return a stratum
 * related by a property_definition_relationship
 * and qualified by a specified value of the name attribute, v1
 * <p>
 * e1 <- property_definition_relationship.related_property_definition
 * <br>property_definition_relationship.name = v1
 * <br>property_definition_relationship.relating_property_definition -> stratum
 * <p>
 * @param e1 the starting entity of laminate_component
 * @param v1 the qualifying attribute value for the relating entity's name attribute
 * @return the related stratum
 */
public EStratum s_relatedTo_lc_through_pdr (ELaminate_component e1, String v1) throws SdaiException; 



/**
 * Given a laminate_component, e1
 * this method will return a stratum
 * related by a product_definition_relationship
 * and qualified by a specified value of the name attribute, v1
 * <p>
 * e1 <- product_definition_relationship.related_product_definition
 * <br>product_definition_relationship.name = v1
 * <br>product_definition_relationship.relating_product_definition -> stratum
 * <p>
 * @param e1 the starting entity of laminate_component
 * @param v1 the qualifying attribute value for the relating entity's name attribute
 * @return the related stratum
 */
public EStratum s_relatedTo_lc_through_pdr_2 (ELaminate_component e1, String v1) throws SdaiException; 



/**
 * Given a stratum_technology_occurrence, e1
 * this method will return a stratum
 * related by a property_definition_relationship
 * and qualified by a specified value of the name attribute, v1
 * <p>
 * e1 <- property_definition_relationship.relating_property_definition
 * <br>property_definition_relationship.name = v1
 * <br>property_definition_relationship.related_property_definition -> stratum
 * <p>
 * @param e1 the starting entity of stratum_technology_occurrence
 * @param v1 the qualifying attribute value for the relating entity's name attribute
 * @return the related stratum
 */
public EStratum s_relatedTo_sto_through_pdr (EStratum_technology_occurrence e1, String v1) throws SdaiException; 



/**
 * Given a additive_laminate_text_component, e1
 * this method will return a shape_aspect
 * that references the additive_laminate_text_component through its of_shape attribute. 
 * <p>
 * e1 <- shape_aspect.of_shape
 * <p>
 * @param e1 the starting entity of type additive_laminate_text_component
 * @return the referencing shape_aspect
 */
public EShape_aspect sa_referencing_altc (EAdditive_laminate_text_component e1) throws SdaiException;  


/**
 * Given a laminate_component, e1
 * this method will return a shape_aspect
 * that references the laminate_component through its of_shape attribute. 
 * <p>
 * e1 <- shape_aspect.of_shape
 * <p>
 * @param e1 the starting entity of type laminate_component
 * @return the referencing shape_aspect
 */
public EShape_aspect sa_referencing_lc (ELaminate_component e1) throws SdaiException;  


/**
 * Given a stratum_feature_template_component, e1
 * this method will return a shape_aspect
 * that references the stratum_feature_template_component through its of_shape attribute. 
 * <p>
 * e1 <- shape_aspect.of_shape
 * <p>
 * @param e1 the starting entity of type stratum_feature_template_component
 * @return the referencing shape_aspect
 */
public EShape_aspect sa_referencing_sftc (EStratum_feature_template_component e1) throws SdaiException;  


/**
 * Given a composite_group_shape_aspect, e1
 * this method will return a shape_aspect
 * related by a shape_aspect_relationship
 * <p>
 * e1 <- shape_aspect_relationship.relating_shape_aspect
 * <br>shape_aspect_relationship.related_shape_aspect -> shape_aspect
 * <p>
 * @param e1 the starting entity of composite_group_shape_aspect
 * @return the related shape_aspect
 */
public EShape_aspect sa_relatedTo_cgsa_through_sar (EComposite_group_shape_aspect e1) throws SdaiException; 



/**
 * Given a layer_connection_point, e1
 * this method will return a shape_aspect
 * related by a shape_aspect_relationship
 * and qualified by a specified value of the name attribute, v1
 * <p>
 * e1 <- shape_aspect_relationship.related_shape_aspect
 * <br>shape_aspect_relationship.name = v1
 * <br>shape_aspect_relationship.relating_shape_aspect -> shape_aspect
 * <p>
 * @param e1 the starting entity of layer_connection_point
 * @param v1 the qualifying attribute value for the relating entity's name attribute
 * @return the related shape_aspect
 */
public EShape_aspect sa_relatedTo_lcp_through_sar (ELayer_connection_point e1, String v1) throws SdaiException; 



/**
 * Given a part_tooling_feature, e1
 * this method will return a shape_aspect
 * related by a shape_aspect_relationship
 * and qualified by a specified value of the name attribute, v1
 * <p>
 * e1 <- shape_aspect_relationship.relating_shape_aspect
 * <br>shape_aspect_relationship.name = v1
 * <br>shape_aspect_relationship.related_shape_aspect -> shape_aspect
 * <p>
 * @param e1 the starting entity of part_tooling_feature
 * @param v1 the qualifying attribute value for the relating entity's name attribute
 * @return the related shape_aspect
 */
public EShape_aspect sa_relatedTo_ptf_through_sar (EPart_tooling_feature e1, String v1) throws SdaiException; 



/**
 * Given a shape_aspect, e1
 * this method will return a shape_aspect
 * related by a shape_aspect_relationship
 * and qualified by a specified value of the name attribute, v1
 * <p>
 * e1 <- shape_aspect_relationship.related_shape_aspect
 * <br>shape_aspect_relationship.name = v1
 * <br>shape_aspect_relationship.relating_shape_aspect -> shape_aspect
 * <p>
 * @param e1 the starting entity of shape_aspect
 * @param v1 the qualifying attribute value for the relating entity's name attribute
 * @return the related shape_aspect
 */
public EShape_aspect sa_relatedTo_sa_through_sar (EShape_aspect e1, String v1) throws SdaiException; 



/**
 * Given a property_definition, e1
 * this method will return a shape_aspect_relationship
 * referenced by the property_definition through its definition attribute. 
 * The return entity is qualified by a specified value of the description attribute.
 * <p>
 * e1.definition ->
 * shape_aspect_relationship
 * <p>shape_aspect_relationship.description = v1
 * <p>
 * @param e1 the starting entity of type property_definition
 * @param v1 the qualifying attribute value for the referencing entity's description attribute
  * @return the referenced shape_aspect_relationship
 */

public EShape_aspect_relationship sar_referencedBy_pd (EProperty_definition e1, String v1) throws SdaiException;  


/**
 * Given a property_definition, e1
 * this method will return a solid_character_glyph_2d_symbol
 * related by a property_definition_representation
 * <p>
 * e1 <- property_definition_representation.definition
 * <br>property_definition_representation.used_representation -> solid_character_glyph_2d_symbol
 * <p>
 * @param e1 the starting entity of property_definition
 * @return the related solid_character_glyph_2d_symbol
 */
public ESolid_character_glyph_2d_symbol scg2ds_relatedTo_pd_through_pdr (EProperty_definition e1) throws SdaiException; 



/**
 * Given a representation_context, e1
 * this method will return a shape_dimension_representation
 * that references the representation_context through its context_of_items attribute. 
 * <p>
 * e1 <- shape_dimension_representation.context_of_items
 * <p>
 * @param e1 the starting entity of type representation_context
 * @return the referencing shape_dimension_representation
 */
public EShape_dimension_representation sdr_referencing_rc (ERepresentation_context e1) throws SdaiException;  


/**
 * Given a dimensional_location, e1
 * this method will return a shape_dimension_representation
 * related by a dimensional_characteristic_representation
 * <p>
 * e1 <- dimensional_characteristic_representation.dimension
 * <br>dimensional_characteristic_representation.representation -> shape_dimension_representation
 * <p>
 * @param e1 the starting entity of dimensional_location
 * @return the related shape_dimension_representation
 */
public EShape_dimension_representation sdr_relatedTo_dl_through_dcr (EDimensional_location e1) throws SdaiException; 



/**
 * Given a dimensional_size, e1
 * this method will return a shape_dimension_representation
 * related by a dimensional_characteristic_representation
 * <p>
 * e1 <- dimensional_characteristic_representation.dimension
 * <br>dimensional_characteristic_representation.representation -> shape_dimension_representation
 * <p>
 * @param e1 the starting entity of dimensional_size
 * @return the related shape_dimension_representation
 */
public EShape_dimension_representation sdr_relatedTo_ds_through_dcr (EDimensional_size e1) throws SdaiException; 



/**
 * Given a shape_aspect, e1
 * this method will return a stratum_feature
 * related by a shape_aspect_relationship
 * and qualified by a specified value of the name attribute, v1
 * <p>
 * e1 <- shape_aspect_relationship.related_shape_aspect
 * <br>shape_aspect_relationship.name = v1
 * <br>shape_aspect_relationship.relating_shape_aspect -> stratum_feature
 * <p>
 * @param e1 the starting entity of shape_aspect
 * @param v1 the qualifying attribute value for the relating entity's name attribute
 * @return the related stratum_feature
 */
public EStratum_feature sf_relatedTo_sa_through_sar (EShape_aspect e1, String v1) throws SdaiException; 



/**
 * Given a shape_aspect, e1
 * this method will return a stratum_feature_template_component
 * referenced by the shape_aspect through its of_shape attribute. 
 * <p>
 * e1.of_shape ->
 * stratum_feature_template_component
 * <p>
 * @param e1 the starting entity of type shape_aspect
  * @return the referenced stratum_feature_template_component
 */

public EStratum_feature_template_component sftc_referencedBy_sa (EShape_aspect e1) throws SdaiException;  


/**
 * Given a structured_layout_component_sub_assembly_relationship, e1
 * this method will return a structured_layout_component
 * referenced by the structured_layout_component_sub_assembly_relationship through its relating_product_definition attribute. 
 * <p>
 * e1.relating_product_definition ->
 * structured_layout_component
 * <p>
 * @param e1 the starting entity of type structured_layout_component_sub_assembly_relationship
  * @return the referenced structured_layout_component
 */

public EStructured_layout_component slc_referencedBy_slcsar (EStructured_layout_component_sub_assembly_relationship e1) throws SdaiException;  


/**
 * Given a assembly_component, e1
 * this method will return a structured_layout_component_sub_assembly_relationship
 * that references the assembly_component through its occurrence attribute. 
 * <p>
 * e1 <- structured_layout_component_sub_assembly_relationship.occurrence
 * <p>
 * @param e1 the starting entity of type assembly_component
 * @return the referencing structured_layout_component_sub_assembly_relationship
 */
public EStructured_layout_component_sub_assembly_relationship slcsar_referencing_ac (EAssembly_component e1) throws SdaiException;  


/**
 * Given a laminate_component, e1
 * this method will return a structured_layout_component_sub_assembly_relationship
 * that references the laminate_component through its occurrence attribute. 
 * <p>
 * e1 <- structured_layout_component_sub_assembly_relationship.occurrence
 * <p>
 * @param e1 the starting entity of type laminate_component
 * @return the referencing structured_layout_component_sub_assembly_relationship
 */
public EStructured_layout_component_sub_assembly_relationship slcsar_referencing_lc (ELaminate_component e1) throws SdaiException;  


/**
 * Given a package, e1
 * this method will return a seating_plane
 * that references the package through its of_shape attribute. 
 * <p>
 * e1 <- seating_plane.of_shape
 * <p>
 * @param e1 the starting entity of type package
 * @return the referencing seating_plane
 */
public ESeating_plane sp_referencing_p (EPackage e1) throws SdaiException;  


/**
 * Given a component_2d_location, e1
 * this method will return a shape_representation
 * referenced by the component_2d_location through its rep_1 attribute. 
 * <p>
 * e1.rep_1 ->
 * shape_representation
 * <p>
 * @param e1 the starting entity of type component_2d_location
  * @return the referenced shape_representation
 */

public EShape_representation sr_referencedBy_c2dl (EComponent_2d_location e1) throws SdaiException;  


/**
 * Given a component_2d_location, e1
 * this method will return a shape_representation
 * referenced by the component_2d_location through its rep_2 attribute. 
 * <p>
 * e1.rep_2 ->
 * shape_representation
 * <p>
 * @param e1 the starting entity of type component_2d_location
  * @return the referenced shape_representation
 */

public EShape_representation sr_referencedBy_c2dl_2 (EComponent_2d_location e1) throws SdaiException;  


/**
 * Given a representation_map, e1
 * this method will return a shape_representation
 * referenced by the representation_map through its mapped_representation attribute. 
 * <p>
 * e1.mapped_representation ->
 * shape_representation
 * <p>
 * @param e1 the starting entity of type representation_map
  * @return the referenced shape_representation
 */

public EShape_representation sr_referencedBy_rm (ERepresentation_map e1) throws SdaiException;  


/**
 * Given a assembly_component, e1
 * this method will return a shape_representation
 * related by a shape_definition_representation
 * <p>
 * e1 <- shape_definition_representation.definition
 * <br>shape_definition_representation.used_representation -> shape_representation
 * <p>
 * @param e1 the starting entity of assembly_component
 * @return the related shape_representation
 */
public EShape_representation sr_relatedTo_ac_through_sdr (EAssembly_component e1) throws SdaiException; 



/**
 * Given a geometric_template, e1
 * this method will return a shape_representation
 * related by a shape_definition_representation
 * <p>
 * e1 <- shape_definition_representation.definition
 * <br>shape_definition_representation.used_representation -> shape_representation
 * <p>
 * @param e1 the starting entity of geometric_template
 * @return the related shape_representation
 */
public EShape_representation sr_relatedTo_gt_through_sdr (EGeometric_template e1) throws SdaiException; 



/**
 * Given a property_definition, e1
 * this method will return a shape_representation
 * related by a property_definition_representation
 * <p>
 * e1 <- property_definition_representation.definition
 * <br>property_definition_representation.used_representation -> shape_representation
 * <p>
 * @param e1 the starting entity of property_definition
 * @return the related shape_representation
 */
public EShape_representation sr_relatedTo_pd_through_pdr (EProperty_definition e1) throws SdaiException; 



/**
 * Given a property_definition, e1
 * this method will return a shape_representation
 * related by a shape_definition_representation
 * <p>
 * e1 <- shape_definition_representation.definition
 * <br>shape_definition_representation.used_representation -> shape_representation
 * <p>
 * @param e1 the starting entity of property_definition
 * @return the related shape_representation
 */
public EShape_representation sr_relatedTo_pd_through_sdr (EProperty_definition e1) throws SdaiException; 



/**
 * Given a product_definition_shape, e1
 * this method will return a shape_representation
 * related by a shape_definition_representation
 * <p>
 * e1 <- shape_definition_representation.definition
 * <br>shape_definition_representation.used_representation -> shape_representation
 * <p>
 * @param e1 the starting entity of product_definition_shape
 * @return the related shape_representation
 */
public EShape_representation sr_relatedTo_pds_through_sdr (EProduct_definition_shape e1) throws SdaiException; 



/**
 * Given a structured_template, e1
 * this method will return a shape_representation
 * related by a shape_definition_representation
 * <p>
 * e1 <- shape_definition_representation.definition
 * <br>shape_definition_representation.used_representation -> shape_representation
 * <p>
 * @param e1 the starting entity of structured_template
 * @return the related shape_representation
 */
public EShape_representation sr_relatedTo_st_through_sdr (EStructured_template e1) throws SdaiException; 



/**
 * Given a shape_aspect_relationship, e1
 * this method will return a stratum_surface
 * referenced by the shape_aspect_relationship through its relating_shape_aspect attribute. 
 * The return entity is qualified by a specified value of the description attribute.
 * <p>
 * e1.relating_shape_aspect ->
 * stratum_surface
 * <p>stratum_surface.description = v1
 * <p>
 * @param e1 the starting entity of type shape_aspect_relationship
 * @param v1 the qualifying attribute value for the referencing entity's description attribute
  * @return the referenced stratum_surface
 */

public EStratum_surface ss_referencedBy_sar (EShape_aspect_relationship e1, String v1) throws SdaiException;  


/**
 * Given a shape_aspect_relationship, e1
 * this method will return a stratum_surface
 * referenced by the shape_aspect_relationship through its related_shape_aspect attribute. 
 * The return entity is qualified by a specified value of the description attribute.
 * <p>
 * e1.related_shape_aspect ->
 * stratum_surface
 * <p>stratum_surface.description = v1
 * <p>
 * @param e1 the starting entity of type shape_aspect_relationship
 * @param v1 the qualifying attribute value for the referencing entity's description attribute
  * @return the referenced stratum_surface
 */

public EStratum_surface ss_referencedBy_sar_2 (EShape_aspect_relationship e1, String v1) throws SdaiException;  


/**
 * Given a stratum, e1
 * this method will return a stratum_surface
 * that references the stratum through its of_shape attribute. 
 * The return entity is qualified by a specified value of the description attribute.
 * <p>
 * e1 <- stratum_surface.of_shape
 * <p>stratum_surface.description = v1
 * <p>
 * @param e1 the starting entity of type stratum
 * @param v1 the qualifying attribute value for the referencing entity's description attribute
 * @return the referencing stratum_surface
 */
public EStratum_surface ss_referencing_s (EStratum e1, String v1) throws SdaiException;  


/**
 * Given a stratum_surface, e1
 * this method will return a stratum_surface
 * related by a shape_aspect_relationship
 * and qualified by a specified value of the description attribute, v1
 * <p>
 * e1 <- shape_aspect_relationship.relating_shape_aspect
 * <br>shape_aspect_relationship.description = v1
 * <br>shape_aspect_relationship.related_shape_aspect -> stratum_surface
 * <p>
 * @param e1 the starting entity of stratum_surface
 * @param v1 the qualifying attribute value for the relating entity's description attribute
 * @return the related stratum_surface
 */
public EStratum_surface ss_relatedTo_ss_through_sar (EStratum_surface e1, String v1) throws SdaiException; 



/**
 * Given a stratum_surface, e1
 * this method will return a stratum_surface
 * related by a shape_aspect_relationship
 * and qualified by a specified value of the description attribute, v1
 * <p>
 * e1 <- shape_aspect_relationship.related_shape_aspect
 * <br>shape_aspect_relationship.description = v1
 * <br>shape_aspect_relationship.relating_shape_aspect -> stratum_surface
 * <p>
 * @param e1 the starting entity of stratum_surface
 * @param v1 the qualifying attribute value for the relating entity's description attribute
 * @return the related stratum_surface
 */
public EStratum_surface ss_relatedTo_ss_through_sar_2 (EStratum_surface e1, String v1) throws SdaiException; 



/**
 * Given a stratum_technology_occurrence_link, e1
 * this method will return a stratum_stack_model
 * referenced by the stratum_technology_occurrence_link through its definition attribute. 
 * <p>
 * e1.definition ->
 * stratum_stack_model
 * <p>
 * @param e1 the starting entity of type stratum_technology_occurrence_link
  * @return the referenced stratum_stack_model
 */

public EStratum_stack_model ssm_referencedBy_stol (EStratum_technology_occurrence_link e1) throws SdaiException;  


/**
 * Given a structured_layout_component, e1
 * this method will return a structured_template
 * referenced by the structured_layout_component through its relating_product_definition attribute. 
 * <p>
 * e1.relating_product_definition ->
 * structured_template
 * <p>
 * @param e1 the starting entity of type structured_layout_component
  * @return the referenced structured_template
 */

public EStructured_template st_referencedBy_slc (EStructured_layout_component e1) throws SdaiException;  


/**
 * Given a stratum_technology_occurrence_link, e1
 * this method will return a stratum_technology_occurrence
 * referenced by the stratum_technology_occurrence_link through its relating_property_definition attribute. 
 * <p>
 * e1.relating_property_definition ->
 * stratum_technology_occurrence
 * <p>
 * @param e1 the starting entity of type stratum_technology_occurrence_link
  * @return the referenced stratum_technology_occurrence
 */

public EStratum_technology_occurrence sto_referencedBy_stol (EStratum_technology_occurrence_link e1) throws SdaiException;  


/**
 * Given a stratum_technology_occurrence_link, e1
 * this method will return a stratum_technology_occurrence
 * referenced by the stratum_technology_occurrence_link through its related_property_definition attribute. 
 * <p>
 * e1.related_property_definition ->
 * stratum_technology_occurrence
 * <p>
 * @param e1 the starting entity of type stratum_technology_occurrence_link
  * @return the referenced stratum_technology_occurrence
 */

public EStratum_technology_occurrence sto_referencedBy_stol_2 (EStratum_technology_occurrence_link e1) throws SdaiException;  


/**
 * Given a stratum, e1
 * this method will return a stratum_technology_occurrence
 * related by a property_definition_relationship
 * and qualified by a specified value of the name attribute, v1
 * <p>
 * e1 <- property_definition_relationship.related_property_definition
 * <br>property_definition_relationship.name = v1
 * <br>property_definition_relationship.relating_property_definition -> stratum_technology_occurrence
 * <p>
 * @param e1 the starting entity of stratum
 * @param v1 the qualifying attribute value for the relating entity's name attribute
 * @return the related stratum_technology_occurrence
 */
public EStratum_technology_occurrence sto_relatedTo_s_through_pdr (EStratum e1, String v1) throws SdaiException; 



/**
 * Given a passage_technology_allocation_to_stack_model, e1
 * this method will return a stratum_technology_occurrence_link
 * related by a property_definition_relationship
 * and qualified by a specified value of the name attribute, v1
 * <p>
 * e1 <- property_definition_relationship.related_property_definition
 * <br>property_definition_relationship.name = v1
 * <br>property_definition_relationship.relating_property_definition -> stratum_technology_occurrence_link
 * <p>
 * @param e1 the starting entity of passage_technology_allocation_to_stack_model
 * @param v1 the qualifying attribute value for the relating entity's name attribute
 * @return the related stratum_technology_occurrence_link
 */
public EStratum_technology_occurrence_link stol_relatedTo_ptatsm_through_pdr (EPassage_technology_allocation_to_stack_model e1, String v1) throws SdaiException; 



/**
 * Given a representation, e1
 * this method will return a text_literal
 * referenced by the representation through its items attribute. 
 * <p>
 * e1.items ->
 * text_literal
 * <p>
 * @param e1 the starting entity of type representation
  * @return the referenced text_literal
 */

public EText_literal tl_referencedBy_r (ERepresentation e1) throws SdaiException;  


/**
 * Given an aggregate of usage_concept_usage_relationship,
 * this method will return the first entity in the aggregate that 
 * that references the given entities through the qualifying attributes.
 * <p>
 * <br>usage_concept_usage_relationship.used_representation = e1
 * <p> 
 * @param a1 the given aggregate of type usage_concept_usage_relationship
 * @param e1 given referenced entity 1
 * @return the first usage_concept_usage_relationship that references the given entities
 */
public EUsage_concept_usage_relationship ucur_referencingGiven (
			AUsage_concept_usage_relationship a1
			, EEntity e1
			) throws SdaiException; 
}
