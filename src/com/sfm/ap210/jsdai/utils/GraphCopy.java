package com.sfm.ap210.jsdai.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jsdai.dictionary.EEntity_definition;
import jsdai.lang.AEntity;
import jsdai.lang.EEntity;
import jsdai.lang.SdaiException;
import jsdai.lang.SdaiModel;

/**
 * This utility class supports the copying of a set of entities from a 
 * given SdaiModel to a new SdaiModel while selectively preserving 
 * entity connectivity information. Additionally, it provides 
 * bi-directional mapping information between the original 
 * source entities and the newly created target entities of the 
 * copy operation.
 */
public class GraphCopy {
	//mapping from source entities to target entities
	private Map<EEntity, EEntity> sourceMappings;
	//mapping from target entities to source entities
	private Map<EEntity, EEntity> targetMappings;
	//list of source entities that should be skipped in 
	//during the copy operation
	private Set<EEntity> excluded;
	//list of source entities that should be specifically
	//included during the copy operation
	private Set<EEntity> included;
	//the source model
	private SdaiModel src;
	//the target model
	private SdaiModel dst;
	
	/**
	 * Create a new GraphCopy operation that will copy entities from the 
	 * src model to the dst model
	 * 
	 * @param src the source model
	 * @param dst the destination model
	 */
	public GraphCopy(SdaiModel src, SdaiModel dst) {
		this.src = src;
		this.dst = dst;
		this.sourceMappings = new HashMap<EEntity, EEntity>();
		this.targetMappings = new HashMap<EEntity, EEntity>();		
		this.excluded = new HashSet<EEntity>();
		this.included = new HashSet<EEntity>();
	}
		
	/**
	 * Exclude the given source model entity 
	 * from the copy operation
	 * 
	 * @param ent the entity to exclude
	 */
	public void exclude(EEntity ent) {
		excluded.add(ent);
	}
	
	/**
	 * Exclude the given source model entities
	 * from the copy operation
	 * 
	 * @param ents the entities to exclude
	 */
	public void exclude(Iterable<EEntity> ents) {
		for(EEntity ent : ents) {
			exclude(ent);
		}
	}
	
	/**
	 * Include the given source model entity in the
	 * copy operation
	 * 
	 * @param ent the entity to include
	 */
	public void include(EEntity ent) {
		included.add(ent);
	}
	
	/**
	 * Include the given source model entities in the
	 * copy operation
	 *  
	 * @param ents the entities to include
	 */
	public void include(Iterable<EEntity> ents) {
		for(EEntity ent : ents) {
			include(ent);
		}
	}
	
	/**
	 * Include all source model entities of the given class 
	 * (including subtypes) in the copy operation
	 *  
	 * @param klass the class object
	 * @throws SdaiException
	 */
	public void include(Class<?> klass) throws SdaiException {
		include(SdaiUtils.Iter(src.getInstances(klass)));
	}
	
	/**
	 * Include all source model entities of the given entity 
	 * type (including subtypes) in the copy operation
	 * @param type the type to include
	 * @throws SdaiException
	 */
	public void include(EEntity_definition type) throws SdaiException {
		include(SdaiUtils.Iter(src.getInstances(type)));
	}
	
	/**
	 * Exclude all source model entities of the given type (including subtypes)
	 * from the copy operation
	 * @param type the type to exclude
	 * @throws SdaiException
	 */
	public void exclude(EEntity_definition type) throws SdaiException {
		exclude(SdaiUtils.Iter(src.getInstances(type)));		
	}
	
	/**
	 * Exclude all source model entities of the given type (including subtypes)
	 * from the copy operation
	 * @param klass the type to exclude
	 * @throws SdaiException
	 */
	public void exclude(Class<?> klass) throws SdaiException {
		exclude(SdaiUtils.Iter(src.getInstances(klass)));
	}
	
	/**
	 * Get the newly created entity in the destination model corresponding
	 * to the given entity from the source model
	 * 
	 * @param source the source model entity
	 * @return the corresponding destination model entity
	 */
	public EEntity findTarget(EEntity source) {
		return sourceMappings.get(source);
	}
	
	/**
	 * Returns true if there is a source to target mapping for the given entity
	 * 
	 * @param source the source model entity 
	 * @return true if there is a target mapping for the given entity, false otherwise
	 */
	public boolean hasTarget(EEntity source) {
		return sourceMappings.containsKey(source);
	}
	
	/**
	 * Get the corresponding entity from the source model for the given entity from
	 * the destination model
	 * 
	 * @param target the destination model entity 
	 * @return the corresponding entity from the source model, null if no such entity exists 
	 */
	public EEntity findSource(EEntity target) {
		return targetMappings.get(target);
	}
	
	/**
	 * Returns true if there is a corresponding entity in the source model
	 * 
	 * @param target the destination model eneitty
	 * @return true if there is a source mapping for the given entity, false otherwise
	 */
	public boolean hasSource(EEntity target) {
		return targetMappings.containsKey(target);
	}
		
	/**
	 * Returns true if the given source model entity is explicitly excluded from the copy operation
	 * @param ent the source model entity
	 * @return
	 */
	public boolean isExcluded(EEntity ent) {
		return excluded.contains(ent);
	}
	
	/**
	 * Returns true if the given source model entity is included in the copy operation, i.e. 
	 * a corresponding entity will be created in the destination model
	 * @param ent the source model entity
	 * @return
	 */
	public boolean isIncluded(EEntity ent) {
		return included.isEmpty() || (included.contains(ent) && !excluded.contains(ent));
	}
	
	/**
	 * Get a listing of all entities copied from the source model
	 * 
	 * @return
	 */
	public Set<EEntity> getSourceEntities() {
		return sourceMappings.keySet();
	}
	
	/**
	 * Get a listing of all entities created in the destination model by the 
	 * copy operation
	 * 
	 * @return
	 */
	public Set<EEntity> getTargetEntities() {
		return targetMappings.keySet();
	}
	
	/**
	 * Executes the copy operation and creates source/target mapping data
	 * 
	 * @throws SdaiException
	 */
	public void execute() throws SdaiException {
		AEntity toCopy = new AEntity();
		//create all initial copies of entities
		for(EEntity source : SdaiUtils.Iter(src.getInstances())) {
			if(!isIncluded(source))
				continue;			
			toCopy.addUnordered(source);
		}		
		List<EEntity> sources = SdaiUtils.Sequence(toCopy);
		List<EEntity> targets = SdaiUtils.Sequence(dst.copyInstances(toCopy));
		int idx = 0;
		for(EEntity source: sources) {
			EEntity target = targets.get(idx);
			idx++;
			sourceMappings.put(source, target);
			targetMappings.put(target, source);
		}		
	}
}
