package com.cms.gwt.fs.client.rpc.actionResponse;

import com.cms.gwt.fs.client.model.Messages;
import com.cms.gwt.fs.client.model.material.RecordedMaterial;
import com.cms.gwt.fs.client.model.workHistory.WorkHistory;

public class UpdateRecordedMaterialResponse extends ActionResponse {

	/** The updated recorded material returned from the backend. */
	private RecordedMaterial material;
	private WorkHistory workHistory;
		
	public UpdateRecordedMaterialResponse(RecordedMaterial material, WorkHistory workHistory)
	{
		this.material = material; 
		this.workHistory = workHistory;
	}	
	
	public UpdateRecordedMaterialResponse(Messages messages, RecordedMaterial material, WorkHistory workHistory)
	{
		this.messages = messages;
		this.material = material; 
	}		
			
	public RecordedMaterial getRecordedMaterial() {
		return material;
	}
	
	public WorkHistory getWorkHistory() {
		return workHistory;
	}			

	public void throwEvents() {
		// Auto-generated method stub
		
	}
}
