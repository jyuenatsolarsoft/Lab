package com.cms.gwt.fs.client.rpc.actionResponse;

import com.cms.gwt.fs.client.model.material.RecordedMaterial;
import com.cms.gwt.fs.client.model.workHistory.WorkHistory;

public class GetRecordedMaterialResponse extends ActionResponse {
	
	private RecordedMaterial entry;
	private WorkHistory workHistory;
		
	public GetRecordedMaterialResponse(RecordedMaterial entry, WorkHistory workHistory)
	{
		this.entry = entry;
		this.workHistory = workHistory;
	}	
	
	public RecordedMaterial getEntry() {
		return entry;
	}
	
	public WorkHistory getWorkHistory() {
		return workHistory;
	}

	public void throwEvents() {
		// Auto-generated method stub
		
	}
}