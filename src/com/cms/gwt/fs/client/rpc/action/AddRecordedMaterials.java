package com.cms.gwt.fs.client.rpc.action;

import com.cms.gwt.fs.client.model.material.RecordedMaterialEntries;

public class AddRecordedMaterials implements Action 
{
	private RecordedMaterialEntries materialEntries;
	
	protected AddRecordedMaterials()
	{
		// empty constructor for child classes.
	}
	
	protected AddRecordedMaterials(RecordedMaterialEntries materialEntries)
	{
		this.materialEntries = materialEntries;
	}
	
	public RecordedMaterialEntries getMaterialEntries()
	{
		return materialEntries;
	}
		
	public static AddRecordedMaterials newInstance(RecordedMaterialEntries materialEntries)
	{
		return new AddRecordedMaterials(materialEntries);
	}
	
	
}
