package com.cms.gwt.fs.client.rpc.actionResponse;

import com.cms.gwt.fs.client.model.workHistory.TimeEntryList;
import com.cms.gwt.fs.client.model.workHistory.WorkHistory;

public class GetTimeEntryListResponse extends ActionResponse 
{	
 	private TimeEntryList timeEntryList;	
 	private WorkHistory workHistory;
 		
 	public GetTimeEntryListResponse(TimeEntryList timeEntryList, WorkHistory workHistory)
 	{
 		this.timeEntryList = timeEntryList; 
 		this.workHistory = workHistory;
 	}	
 	
 	public TimeEntryList getTimeEntryList() 
 	{
 		return timeEntryList;
 	}	
 	
 	public WorkHistory getWorkHistory() 
 	{
 		return workHistory;
 	}

 	public void throwEvents() {
 		// Auto-generated method stub
 		
 	}
}
