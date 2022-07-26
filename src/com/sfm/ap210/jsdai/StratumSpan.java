package com.sfm.ap210.jsdai;

import jsdai.SLayered_interconnect_module_design_mim.EStratum;

/**
 * A class to store the pair of {@MIMandARM Stratum} representing a vertical span in a pcb.
 *    
 * @author James Stori, SFM Technology, Inc.
 * @version 1.3
 */
public class StratumSpan {

	private EStratum fromStratum;
	private EStratum toStratum;
	
	public StratumSpan() 
	{
		fromStratum = null;
		toStratum = null;
	}
	/**
	 * 
	 * @param from - the more 'precedent' (closer to the top of the pcb) {@MIMandARM Stratum} in the span.
	 * @param to - the more 'subsequent' (closer to the bottom of the pcb) {@MIMandARM Stratum} in the span.
	 */
	public StratumSpan(EStratum from, EStratum to) 
	{
		fromStratum = from;
		toStratum = to;
	}
	
	/**
	 * @param from - the more 'precedent' (closer to the top of the pcb) {@MIMandARM Stratum} in the span.
	 */
	public void setFromStratum(EStratum from)
	{
		fromStratum = from;
	}
	
	/**
	 * @param to - the more 'subsequent' (closer to the bottom of the pcb) {@MIMandARM Stratum} in the span.
	 */
	public void setToStratum(EStratum to)
	{
		toStratum = to;
	}

	/**
	 * @return the more 'precedent' (closer to the top of the pcb) {@MIMandARM Stratum} in the span.
	 */
	public EStratum getFromStratum()
	{
		return fromStratum;
	}
	
	/**
	 * @return the more 'subsequent' (closer to the bottom of the pcb) {@MIMandARM Stratum} in the span.
	 */
	public EStratum getToStratum()
	{
		return toStratum;
	}
}
