package com.cms.gwt.fs.client.rpc.actionResponse;

import com.cms.gwt.fs.client.model.material.RecordedMaterialList;
import com.cms.gwt.fs.client.model.workHistory.WorkHistory;

public class GetRecordedMaterialListResponse extends ActionResponse 
{	
	private RecordedMaterialList materialList;	
	private WorkHistory workHistory;
		
	public GetRecordedMaterialListResponse(RecordedMaterialList materialList, WorkHistory workHistory)
	{
		this.materialList = materialList; 
		this.workHistory = workHistory;
	}	
	
	public RecordedMaterialList getMaterialList() 
	{
		return materialList;
	}	
	
	public WorkHistory getWorkHistory() 
	{
		return workHistory;
	}

	public void throwEvents() {
		// Auto-generated method stub
		
	}
}