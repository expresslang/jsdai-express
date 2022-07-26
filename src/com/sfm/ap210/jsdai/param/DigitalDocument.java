package com.sfm.ap210.jsdai.param;

public class DigitalDocument {
	public String id; 
	public String version;
	public String source_type;
	public String source_id;
	public String description;
	
	public DigitalDocument(String id, String version, String source_type, String source_id, String description)
	{
		this.id = id;
		this.version = version;
		this.source_type = source_type;
		this.source_id = source_id;
		this.description = description;
	}
}
