package com.cms.gwt.fs.client.rpc.action;

import com.cms.gwt.fs.client.model.material.RecordedMaterial;

public class UpdateRecordedMaterial implements Action {

	protected RecordedMaterial material;
	
	protected UpdateRecordedMaterial()
	{
		// empty constructor for child classes.
	}
	
	protected UpdateRecordedMaterial(RecordedMaterial material)
	{
		this.material = material;
	}
	
	public RecordedMaterial getRecordedMaterial() 
	{
		return material;
	}	
	
	public static UpdateRecordedMaterial newInstance(RecordedMaterial material)
	{
		return new UpdateRecordedMaterial(material);
	}
	
}
