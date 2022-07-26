package com.sfm.ap210.jsdai;

import jsdai.lang.SdaiModel;
import jsdai.lang.SdaiException;

public class MIMqueryFactory {

	public static MIMqueries getImpl(SdaiModel m, MIMops ops) {
		return new MIMqueriesImpl(m, ops);
	}
	
	public static MIMqueries getMapImpl(SdaiModel m, MIMops ops) throws SdaiException {
		return new MIMqueriesMapImpl(m, ops);
	}
}
