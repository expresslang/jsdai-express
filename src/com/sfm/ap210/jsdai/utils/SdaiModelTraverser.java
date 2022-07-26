package com.sfm.ap210.jsdai.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.ECoordinated_geometric_relationship_with_2d_3d_placement_transformation;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EGeometric_representation_context;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EPackage;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.ERepresentation;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.ERepresentation_relationship;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.ESeating_plane;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EShape_definition_representation;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EShape_representation;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.EUsage_concept_usage_relationship;
import jsdai.lang.AEntity;
import jsdai.lang.EEntity;
import jsdai.lang.SdaiException;
import jsdai.lang.SdaiModel;

/**
 * This is a utility class used to control a generic traversal of an SDAI model
 * instance starting from a root entity instance and finding/marking all connected
 * instances. The class can be subclassed to customize the exact traversal conditions.
 */
public class SdaiModelTraverser {		
	//the model
	protected SdaiModel model;
	//the root traversal entity
	protected EEntity root;	
	
	protected Set<EEntity> cuts = new HashSet<EEntity>();
	//the current traversal path
	protected List<EEntity> path = new ArrayList<EEntity>();
	//the discovery path mappings
	protected Map<EEntity, List<EEntity>> paths = new HashMap<EEntity, List<EEntity>>();						
	//the set of visited instances
	public Set<EEntity> visited = new HashSet<EEntity>();
	//the set of marked instances
	public Set<EEntity> marked = new HashSet<EEntity>();
	
	/**
	 * Create a new model traversal of the given model instance starting at the 
	 * given root entity
	 * 
	 * @param model the model instance
	 * @param root the root entity to start the traversal
	 * @throws SdaiException
	 */
	public SdaiModelTraverser(SdaiModel model, EEntity root) throws SdaiException {		
		this.model = model;
		this.root = root; 
	}
	
	/**
	 * Find/record all cut-entity instances specified by this traversal class
	 * @throws SdaiException
	 */
	protected void findCuts() throws SdaiException {
		AEntity insts = model.getInstances();
		for(EEntity ent : SdaiUtils.Iter(insts)) {
			updateCuts(ent);
		}
	}
	
	/**
	 * Traverse and mark all covered entities in the model
	 * 
	 * @throws SdaiException
	 */
	public void traverse() throws SdaiException {			
		findCuts();			
		markAndSweep(root);
	}
	
	/**
	 * Mark the given entity (if applicable) and traverse all forward and
	 * backward references of the entity (if applicable)
	 * @param ent
	 * @throws SdaiException
	 */
	protected void markAndSweep(EEntity ent) throws SdaiException {		
		if(ent == null)
			return;
		if(visited.contains(ent))
			return;			
		visited.add(ent);			
		if(cuts.contains(ent))
			return;		
		marked.add(ent);
		paths.put(ent, new ArrayList<EEntity>(path));
		path.add(ent);		
		if(checkForward(ent)) {
			traverseForwardRefs(ent);
		}
		if(checkBackward(ent)) {
			traverseBackwardRefs(ent);
		}
		path.remove(path.size() - 1);
	}
	
	/**
	 * Traverse all entities directly referencing the given entity
	 * @param ent
	 * @throws SdaiException
	 */
	protected void traverseBackwardRefs(EEntity ent) throws SdaiException {
		AEntity users = new AEntity();
		ent.findEntityInstanceUsers(model.getQuerySourceDomain(), users);					
		for(EEntity user : SdaiUtils.Iter(users)) {				
			markAndSweep(user);			
		}
	}
	
	/**
	 * Traverse all entities directly referenced by the given entity 
	 * @param ent
	 * @throws SdaiException
	 */
	protected void traverseForwardRefs(EEntity ent) throws SdaiException {
		AEntity erefs = ent.getAllReferences();
		for(EEntity eref : SdaiUtils.Iter(erefs)) {				
			markAndSweep(eref);
		}
	}
	
	/**
	 * Print the path of discovery of each marked entity. That is, print the 
	 * traversal path from the root entity for each marked entity 
	 * @throws SdaiException
	 */
	public void printDiscoveryPaths() throws SdaiException {
		List<EEntity> ents = new ArrayList<EEntity>(marked);
		Collections.sort(ents, new Comparator<EEntity>(){			
			public int compare(EEntity e1, EEntity e2) {
				int s1 = paths.get(e1).size();
				int s2 = paths.get(e2).size();
				return Integer.valueOf(s1).compareTo(Integer.valueOf(s2));
			}
		});
		for(EEntity ent : ents) {
			System.out.println(ent);
			System.out.print("\t");
			for(EEntity e : paths.get(ent)) {
				String node = String.format("(%s %s)", e.getPersistentLabel(), e.getInstanceType().getName(null));
				System.out.print(node);				
			}
			System.out.println();
		}
	}
	
	/**
	 * Update cut-nodes for the given entity. Allows subclasses to do pre-processing of the 
	 * traversal cut-nodes (or other internal data) prior to any actual traversal. This method is called for every
	 * entity in the model prior to starting the traversal
	 * @param ent
	 * @throws SdaiException
	 */
	protected void updateCuts(EEntity ent) throws SdaiException {}
	
	/**
	 * Extension method to allow subclasses to customize the traversal. Return true if
	 * the entity's forward references should be traversed.
	 * @param ent
	 * @return
	 * @throws SdaiException
	 */
	protected boolean checkForward(EEntity ent) throws SdaiException {
		return true;
	}
	
	/**
	 * Extension method to allow subclasses to customize the traversal. Return true if 
	 * the entity's backward references (other entities referencing the given entity) 
	 * should be traversed.
	 * @param ent
	 * @return
	 * @throws SdaiException
	 */
	protected boolean checkBackward(EEntity ent) throws SdaiException {
		return true;
	}
	
}
