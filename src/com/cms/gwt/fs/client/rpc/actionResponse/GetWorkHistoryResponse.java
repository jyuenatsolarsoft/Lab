package com.cms.gwt.fs.client.rpc.actionResponse;

import com.cms.gwt.fs.client.model.workHistory.WorkHistory;

public class GetWorkHistoryResponse extends ActionResponse {
	
	private WorkHistory workHistory;
		
	public GetWorkHistoryResponse(WorkHistory workHistory)
	{
		this.workHistory = workHistory; 
	}	
	
	public WorkHistory getWorkHistory() {
		return workHistory;
	}

	public void throwEvents() {
		// Auto-generated method stub
		
	}
}
