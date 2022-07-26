package com.sfm.ap210.jsdai.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


import jsdai.dictionary.EAttribute;
import jsdai.lang.AEntity;
import jsdai.lang.EEntity;
import jsdai.lang.SdaiException;
import jsdai.lang.SdaiModel;
import jsdai.lang.Value;

/**
 * This utility supports writing a GraphML format connectivity graph of 
 * an entity subset. 
 *
 */
public class GraphMLWriter {
	//the SDAI entities in scope of the connectivity graph
	AEntity entities;
	
	/**
	 * Create a new writer that will dump the connectivity 
	 * graph of the given entities
	 * 
	 * @param entities
	 */
	public GraphMLWriter(AEntity entities) {
		this.entities = entities;
	}
	
	/**
	 * Create a new writer that will dump the connectivity
	 * graph of all instances in the given model
	 * 
	 * @param model
	 * @throws SdaiException
	 */
	public GraphMLWriter(SdaiModel model) throws SdaiException {
		this(model.getInstances());
	}
		
	private String getLabel(EEntity ent) throws SdaiException {
		return String.format("%s %s", ent.getPersistentLabel(), ent.getInstanceType().getName(null));
	}
	
	private String getAttrs(EEntity ent) throws SdaiException {
		return ent.toString();
	}
	
	private Map<EEntity, Set<EEntity>> buildGraph() throws SdaiException {
		Set<EEntity> nodes = new HashSet<EEntity>(SdaiUtils.Sequence(entities));
		Map<EEntity,Set<EEntity>> edges = new HashMap<EEntity, Set<EEntity>>();
		for(EEntity n1 : nodes) {						
			edges.put(n1, new HashSet<EEntity>());					
			for(EEntity n2 : SdaiUtils.Sequence(n1.getAllReferences())) {
				if(nodes.contains(n2)) {
					edges.get(n1).add(n2);
				}
			}			
		}
		return edges;
	}
	
	private Document initDocument() {
		Document xml = DomUtils.newDocument("graphml");		
		
		Element root = xml.getDocumentElement();
		root.setAttribute("xmlns", "http://graphml.graphdrawing.org/xmlns");
		root.setAttribute("xmlns:y", "http://www.yworks.com/xml/graphml");
		
		Element ldata = xml.createElement("key");		
		ldata.setAttribute("id", "node.label");
		ldata.setAttribute("for", "node");
		ldata.setAttribute("attr.name", "label");
		ldata.setAttribute("attr.type", "string");
		
		Element adata = xml.createElement("key");		
		adata.setAttribute("id", "node.attrs");
		adata.setAttribute("for", "node");
		adata.setAttribute("attr.name", "attributes");
		adata.setAttribute("attr.type", "string");
		
		root.appendChild(ldata);
		root.appendChild(adata);
		
		return xml;
	}
	
	private Element makeGraph(Document xml) {
		Element graph = xml.createElement("graph");			
		graph.setAttribute("id", "G");
		graph.setAttribute("edgedefault", "directed");
		return graph;
	}
	
	private Element makeNode(Document xml, EEntity e) throws SdaiException {
		Element node = xml.createElement("node");
		node.setAttribute("id", e.getPersistentLabel());
		//add label data
		Element label = xml.createElement("data");		
		label.setAttribute("key", "node.label");		
		label.setTextContent(getLabel(e));
		//add attributes data
		Element attrs = xml.createElement("data");
		attrs.setAttribute("key", "node.attrs");		
		attrs.setTextContent(getAttrs(e));
		//add property data
		node.appendChild(label);
		node.appendChild(attrs);
		return node;
	}
	
	private Element makeEdge(Document xml, EEntity s, EEntity t) throws SdaiException {
		Element edge = xml.createElement("edge");
		edge.setAttribute("id", String.format("%s->%s", s.getPersistentLabel(), t.getPersistentLabel()));
		edge.setAttribute("source", s.getPersistentLabel());
		edge.setAttribute("target", t.getPersistentLabel());
		return edge;
	}
	
	/**
	 * Dump a GraphML report of the entity connectivity graph to the file
	 * specified
	 * 
	 * @param file
	 * @throws SdaiException
	 * @throws IOException
	 */
	public void write(File file) throws SdaiException, IOException {
		Map<EEntity,Set<EEntity>> edges = buildGraph();
		
		Document xml = initDocument();
		Element root = xml.getDocumentElement();
		
		Element graph = makeGraph(xml);	
		root.appendChild(graph);		
		
		for(EEntity e : edges.keySet()) {			
			graph.appendChild(makeNode(xml, e));
		}
		for(EEntity s : edges.keySet()) {			
			for(EEntity t : edges.get(s)) {				
				graph.appendChild(makeEdge(xml, s, t));
			}			
		}
		DomUtils.writeXML(xml, file);
	}
}
