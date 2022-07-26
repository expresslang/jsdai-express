package com.sfm.ap210.jsdai.param;

public interface Unit extends Comparable<Unit>{
	public enum SiPrefix {femto, pico, nano, micro, milli, kilo, mega, giga, tera};
	public String toString();
	public void setPrefix(SiPrefix p);
	public int compareTo(Unit o2);
}
