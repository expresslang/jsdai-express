package com.sfm.ap210.jsdai.utils;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.AShape_aspect;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.AShape_summary_request_with_representative_value;
import jsdai.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf;
import jsdai.dictionary.AEntity_definition;
import jsdai.dictionary.EAttribute;
import jsdai.dictionary.EEntity_definition;
import jsdai.lang.AEntity;
import jsdai.lang.Aggregate;
import jsdai.lang.EEntity;
import jsdai.lang.SdaiException;
import jsdai.lang.SdaiIterator;
import jsdai.lang.SdaiModel;
import jsdai.lang.SdaiRepository;
import jsdai.lang.SdaiSession;
import jsdai.lang.SdaiTransaction;

/**
 * This class contains generic utility methods for common SDAI operations
 * and other convenience utilities that may be helpful when dealing with 
 * the JSDAI.
 */
public class SdaiUtils {
	/**
	 * A type-specific Iterable implementation from an SDAI 
	 * aggregate and expected value type for the aggregate items.
	 * Currently this implementation is less than optimal as it requires
	 * creating its own internal copy of the aggregate data.
	 *
	 * @param <T> the type of items held by the aggregate
	 */
	private static class SdaiIterable <T> implements Iterable<T> {
		//the temporary list view of the aggregate data
		List<T> tempList = new ArrayList<T>();		
		/**
		 * Create an iterable view of the aggregate
		 * @param aggregate the aggregate instance
		 * @param klass the class of the aggregate items
		 * @throws SdaiException
		 */
		public SdaiIterable(Aggregate aggregate, Class<T> klass) throws SdaiException {
			SdaiIterator it = aggregate.createIterator();
			while(it.next()) {
				Object current = aggregate.getCurrentMemberObject(it);
				if(current == null || klass.isInstance(current)) {
					tempList.add((T)current);
				}
			}			
		}
		
		@Override
		public Iterator<T> iterator() {
			return tempList.iterator();
		}
	}
	
	/**
	 * Open a new read-write SDAI session with default configuration
	 * @return
	 * @throws SdaiException
	 */
	public static SdaiSession newRWSession() throws SdaiException {
		SdaiSession session = SdaiSession.openSession();
		session.setLogWriterSession(new PrintWriter(System.out));
		session.startTransactionReadWriteAccess();
		return session;
	}
	
	/**
	 * Open a new read-only SDAI session with default configuration
	 * @return
	 * @throws SdaiException
	 */
	public static SdaiSession newROSession() throws SdaiException {
		SdaiSession session = SdaiSession.openSession();
		session.setLogWriterSession(new PrintWriter(System.out));
		session.startTransactionReadOnlyAccess();
		return session;
	}
	
	/**
	 * Create a new read-write enabled AP210 model in the given repository with the given name
	 * @param repo
	 * @param name
	 * @return
	 * @throws SdaiException
	 */
	public static SdaiModel newRWModel(SdaiRepository repo, String name) throws SdaiException {
		SdaiModel model = repo.createSdaiModel(name, SAp210_electronic_assembly_interconnect_and_packaging_design_mim_lf.class);
		model.startReadWriteAccess();
		return model;
	}
	
	/**
	 * Create a new repository with the given name in the given SDAI session
	 * @param session
	 * @param name
	 * @return
	 * @throws SdaiException
	 */
	public static SdaiRepository newRepo(SdaiSession session, String name) throws SdaiException {
		SdaiRepository repo = session.createRepository(name, null);
		repo.openRepository();
		return repo;
	}
	
	/**
	 * Load the Part21 STEP model contained in the given file into the given repository 
	 * @param file
	 * @param repo
	 * @return
	 * @throws SdaiException
	 */
	public static SdaiModel loadModel(File file, SdaiRepository repo) throws SdaiException {
		SdaiSession s = repo.getSession();
		s.importClearTextEncoding(file.getAbsolutePath(), repo);
		return repo.getModels().getByIndex(1);
	}
	
	/**
	 * Create an iterable view of the given aggregate limited to instances that conform to the
	 * type given by klass
	 * @param <T>
	 * @param klass the type restriction of returned aggregate items
	 * @param aggregate the aggregate instance
	 * @return an iterable view of the items of the aggregate conforming to the given type restriction
	 * @throws SdaiException
	 */
	public static <T> Iterable<T> Iter(Class<T> klass, Aggregate aggregate) throws SdaiException {
		return new SdaiIterable<T>(aggregate, klass);
	}	
	
	/**
	 * Create a list view of the given aggregate limited to instances that conform to the
	 * type given by klass
	 * @param <T>
	 * @param klass the type restriction of returned aggregate items
	 * @param aggregate the aggregate instance
	 * @return a list view of the items of the aggregate conforming to the given type restriction
	 * @throws SdaiException
	 */
	public static <T> List<T> Sequence(Class<T> klass, Aggregate aggregate) throws SdaiException {
		return new SdaiIterable<T>(aggregate, klass).tempList;
	}
	
	/**
	 * Create an iterable view of all entities in the given aggregate
	 * @param aggregate
	 * @return
	 * @throws SdaiException
	 */
	public static Iterable<EEntity> Iter(Aggregate aggregate) throws SdaiException {
		return Iter(EEntity.class, aggregate);
	}
	
	/**
	 * Create a list view of all entities in the given aggregate
	 * @param aggregate
	 * @return
	 * @throws SdaiException
	 */
	public static List<EEntity> Sequence(AEntity aggregate) throws SdaiException {
		return Sequence(EEntity.class, aggregate);
	}
	
	/**
	 * Create a list view of all entities in the given model conforming to 
	 * the given type restriction
	 * @param <T> the EEntity type restriction
	 * @param klass the type to filter for
	 * @param model the SDAI model instance to search
	 * @return all instances from the given model conforming to the given type restriction
	 * @throws SdaiException
	 */
	public static <T> List<T> Instances(Class<T> klass, SdaiModel model) throws SdaiException {
		return Sequence(klass, model.getInstances(klass));
	}
	
	/**
	 * Close the given SDAI session instance
	 * @param session
	 * @throws SdaiException
	 */
	public static void close(SdaiSession session) throws SdaiException {
		session.getActiveTransaction().endTransactionAccessAbort(); 
		for(SdaiRepository r : Iter(SdaiRepository.class, session.getActiveServers())) {			
			if(r == session.getSystemRepository())
				continue;
			r.closeRepository();
			r.deleteRepository();
		}		
		session.closeSession();
	}
	
	/**
	 * Create a new instance of the given type in the given model and return the newly
	 * create instance
	 * @param <T> the entity type to create in the model
	 * @param type the class object of the entity type to create 
	 * @param model the model instance
	 * @return the newly created entity instance
	 * @throws SdaiException
	 */
	public static <T> T create(Class<T> type, SdaiModel model) throws SdaiException {
		return (T) model.createEntityInstance(type);
	}
	
	/**
	 * Get all super types of the given entity definition
	 * @param def
	 * @return
	 * @throws SdaiException
	 */
	public static Set<EEntity_definition> allSupers(EEntity_definition def) throws SdaiException {
		AEntity_definition supers = def.getSupertypes(null);
		Set<EEntity_definition> all = new HashSet<EEntity_definition>();
		for(EEntity_definition base : Iter(EEntity_definition.class, supers)) {
			all.add(base);
			all.addAll(allSupers(base));			
		}
		return all;
	}
	
	/**
	 * Get all explicit attributes defined for the given entity definition
	 * @param def
	 * @return
	 * @throws SdaiException
	 */
	public static Iterable<EAttribute> explicitAttributes(EEntity_definition def) throws SdaiException {
		return Iter(EAttribute.class,def.getExplicit_attributes(null));
	}
	
	/**
	 * Get all explicit attributes defined for the given entity
	 * @param ent
	 * @return
	 * @throws SdaiException
	 */
	public static Iterable<EAttribute> explicitAttributes(EEntity ent) throws SdaiException{
		return explicitAttributes(ent.getInstanceType());		
	}
	
	/**
	 * Get all attributes defined for the given entity definition
	 * @param def
	 * @return
	 * @throws SdaiException
	 */
	public static Set<EAttribute> allAttributes(EEntity_definition def) throws SdaiException {
		Set<EAttribute> attrs = new HashSet<EAttribute>();
		for(EAttribute attr : explicitAttributes(def)) {
			attrs.add(attr);
		}
		AEntity_definition supers = def.getSupertypes(null);
		for(EEntity_definition base : Iter(EEntity_definition.class, supers)) {
			attrs.addAll(allAttributes(base));			
		}
		
		return attrs;
	}
	
	/**
	 * Get all attributes defined for the given entity 
	 * @param ent
	 * @return
	 * @throws SdaiException
	 */
	public static Set<EAttribute> allAttributes(EEntity ent) throws SdaiException {		
		return allAttributes(ent.getInstanceType());
	}
	
	/**
	 * Find the first entity of the given type in the aggregate instance
	 * @param <T> the type of entity to find
	 * @param klass the class object of the entity's type
	 * @param agg the aggregate to search
	 * @return the first instance found or null if no instance is found
	 * @throws SdaiException
	 */
	public static <T> T findFirst(Class<T> klass, Aggregate agg) throws SdaiException {
		Iterator<T> iter = Iter(klass, agg).iterator();
		return iter.hasNext() ? iter.next() : null;
	}
	
	/**
	 * Find the first entity of the given type in the given SDAI model
	 * @param <T> the type of entity to find
	 * @param klass the class object of the entity's type
	 * @param model the model to search
	 * @return the first instance found or null if no instance is found
	 * @throws SdaiException
	 */
	public static <T> T findFirst(Class<T> klass, SdaiModel model) throws SdaiException {
		List<T> instances = Instances(klass, model);
		return !instances.isEmpty() ? instances.get(0) : null;
	}
	
}
