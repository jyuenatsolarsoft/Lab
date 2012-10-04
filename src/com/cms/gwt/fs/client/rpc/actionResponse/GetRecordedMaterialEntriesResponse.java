package com.cms.gwt.fs.client.rpc.actionResponse;

import com.cms.gwt.fs.client.model.material.RecordedMaterialEntries;
import com.cms.gwt.fs.client.model.workHistory.WorkHistory;

public class GetRecordedMaterialEntriesResponse extends ActionResponse 
{	
 	private RecordedMaterialEntries materialEntries;	
 	private WorkHistory workHistory;
 		
 	public GetRecordedMaterialEntriesResponse(RecordedMaterialEntries materialEntries, WorkHistory workHistory)
 	{
 		this.materialEntries = materialEntries; 
 		this.workHistory = workHistory;
 	}	
 	
 	public RecordedMaterialEntries getRecordedMaterialEntries() 
 	{
 		return materialEntries;
 	}	
 	
 	public WorkHistory getWorkHistory() 
 	{
 		return workHistory;
 	}

 	public void throwEvents() {
 		// Auto-generated method stub
 		
 	}
}
