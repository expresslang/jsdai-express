package com.sfm.ap210.jsdai.param;

/**
 * A skeletal interface to support a unified treatment of parametric attributes.
 * Extending interfaces define an applicable getValue method.
 * 
 * @author James Stori, SFM Technology, Inc.
 * @version 1.3
 */
public abstract interface Param {
	String getName();
	String toString();
}
