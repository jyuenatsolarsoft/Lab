package com.cms.gwt.fs.client.rpc.actionResponse;

import com.cms.gwt.fs.client.model.accessHours.AccessHours;

public class GetAccessHoursResponse extends ActionResponse {

	/** The ticket list returned from the backend. */
	private AccessHours accessHours;
		
	public GetAccessHoursResponse(AccessHours accessHours)
	{
		this.accessHours = accessHours; 
	}	
				
	public AccessHours getAccessHours() {
		return accessHours;
	}

	public void throwEvents() {
		// Auto-generated method stub
		
	}
}
