package com.cms.gwt.fs.client.rpc.actionResponse;

import com.cms.gwt.fs.client.model.workHistory.RecordedLabourList;
import com.cms.gwt.fs.client.model.workHistory.WorkHistory;

public class GetRecordedLabourListResponse extends ActionResponse 
{	
	private RecordedLabourList labourList;	
	private WorkHistory workHistory;
		
	public GetRecordedLabourListResponse(RecordedLabourList labourList, WorkHistory workHistory)
	{
		this.labourList = labourList; 
		this.workHistory = workHistory;
	}	
	
	public RecordedLabourList getLabourList() 
	{
		return labourList;
	}	
	
	public WorkHistory getWorkHistory() 
	{
		return workHistory;
	}

	public void throwEvents() {
		// Auto-generated method stub
		
	}
}