package com.sfm.ap210.jsdai.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.dom.DOMImplementationImpl;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
/**
 * Generic utility methods and helpers for dealing with DOM data  
 *
 */
public abstract class DomUtils {
	/**
	 * Helper class to provide an iterable view of a node list 	 
	 * @param <T>
	 */
	private static class IterableNodeList <T> implements Iterable<T> {
		private NodeList nodes;
		
		private static class NodeListIterator<T> implements Iterator<T> {
			private NodeList nodes;
			private int currentIndex;
			
			public NodeListIterator(NodeList nodes) {
				this.nodes = nodes;
				this.currentIndex = 0;
			}
			public boolean hasNext() {
				return nodes.getLength() > currentIndex;
			}
			public T next() {				
				T item = (T) nodes.item(currentIndex);
				++currentIndex;
				return item;
			}
			public void remove() {
				throw new UnsupportedOperationException("Cannot remove nodes from NodeList");
			}
		}
		
		public IterableNodeList(NodeList nodes){
			this.nodes = nodes;
		}
		
		public Iterator<T> iterator() {
			return new NodeListIterator<T>(nodes);
		}		
	}
	
	/**
	 * Obtain an iterable view of the given NodeList 
	 * @param nodes
	 * @return
	 */
	public static Iterable<Node> NodeSequence(NodeList nodes) {
		return new IterableNodeList<Node>(nodes);
	}
	
	/**
	 * Return an iterable view of the given NodeList with items
	 * of the given type
	 * @param <T>
	 * @param nodes
	 * @return
	 */
	public static <T> Iterable<T> Sequence(NodeList nodes) {
		return new IterableNodeList<T>(nodes);
	}
	
	/**
	 * Get all elements of the given document with the given tag
	 * @param dom
	 * @param tag
	 * @return
	 */
	public static Iterable<Element> Elements(Document dom, String tag){
		return Sequence(dom.getElementsByTagName(tag));
	}
	
	/**
	 * Get all child elements of the given element
	 * @param element
	 * @return
	 */
	public static Iterable<Element> Children(Element element) {
		List<Element> children = new ArrayList<Element>();		
		for(Node child : NodeSequence(element.getChildNodes())) {
			if(!(child instanceof Element))
				continue;
			children.add((Element)child);			
		}		
		return children;
	}
	
	/**
	 * Get all child elements of the given node with the given tag
	 * @param node
	 * @param tag
	 * @return
	 */
	public static Iterable<Element> Children(Node node, String tag) {
		List<Element> matches = new ArrayList<Element>();
		for(Element e : Children((Element) node)) {
			if(tag.equalsIgnoreCase(e.getTagName())) {
				matches.add(e);
			}
		}
		return matches;
	}
	
	/**
	 * Get all descendent elements of the given node with the given tag
	 * @param root
	 * @param tag
	 * @return
	 */
	public static Iterable<Element> Descendents(Element root, String tag){
		return Sequence(root.getElementsByTagName(tag));
	}
	
	/**
	 * Get the first descendent node of the given element with the given tag
	 * @param root
	 * @param tag
	 * @return
	 */
	public static Element FirstDescendent(Element root, String tag) {
		Iterable<Element> elems = Sequence(root.getElementsByTagName(tag));
		Iterator<Element> it = elems.iterator();
		return it.hasNext() ? it.next() : null;		
	}
	
	/**
	 * Get the first element in the given docuemnt with the given tag
	 * @param dom
	 * @param tag
	 * @return
	 */
	public static Element FirstElement(Document dom, String tag) {
		Iterable<Element> elems = Sequence(dom.getElementsByTagName(tag));
		Iterator<Element> it = elems.iterator();
		return it.hasNext() ? it.next() : null;
	}
			
	private static void ParentsAccum(Element elem, String tag, List<Element> path) {
		Node parent = elem.getParentNode();
		if(parent == null || !(parent instanceof Element))
			return;
		Element parentElem = (Element)parent;
		
		String parentTag = parentElem.getTagName();		
		if(tag.equals(parentTag))
			path.add(parentElem);
							
		ParentsAccum(parentElem,tag,path);		
	}
	
	/**
	 * Get the parent elements of the given element with the given tag
	 * @param elem
	 * @param tag
	 * @return
	 */
	public static List<Element> Parents(Element elem, String tag) {
		List<Element> path = new ArrayList<Element>();
		ParentsAccum(elem,tag,path);
		Collections.reverse(path);
		return path;
	}
	

	/**
	 * Load an XML file into a DOM structure from file
	 * @param file
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 */
	public static Document loadXML(File file) throws SAXException, IOException {
		InputSource fileinput = new InputSource(new FileInputStream(file));							
		DOMParser parser = new DOMParser();
		parser.parse(fileinput);		
		return parser.getDocument();
	}
	
	/**
	 * Create a new DOM instance with the given root node
	 * @param root
	 * @return
	 */
	public static Document newDocument(String root) {
		DOMImplementation impl = DOMImplementationImpl.getDOMImplementation();
		return impl.createDocument(null, root, null);
	}
	
	/**
	 * Write the DOM document to the provided file
	 * @param xml
	 * @param file
	 * @throws IOException
	 */
	public static void writeXML(Document xml, File file) throws IOException {
		OutputFormat fmt = new OutputFormat();
		fmt.setIndenting(true);
		fmt.setIndent(4);
		XMLSerializer io = new XMLSerializer(new FileOutputStream(file), fmt);
		io.serialize(xml);
	}
}
