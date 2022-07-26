package com.sfm.ap210.jsdai;

import jsdai.lang.SdaiException;
import jsdai.lang.SdaiModel;

public class MIMopsFactory {

	public static MIMops getImpl(SdaiModel m) {
		return new MIMopsImpl(m);
	}
	
	public static MIMops getDebugImpl(SdaiModel m) throws SdaiException {
		return new MIMopsDebugImpl(m);
	}

}
