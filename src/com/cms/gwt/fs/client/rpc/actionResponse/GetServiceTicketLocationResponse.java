package com.cms.gwt.fs.client.rpc.actionResponse;

import com.cms.gwt.fs.client.model.location.ServiceTicketLocation;

public class GetServiceTicketLocationResponse extends ActionResponse {

	private ServiceTicketLocation location;
	
	public GetServiceTicketLocationResponse(ServiceTicketLocation location)
	{
		this.location = location;
	}
				
	public ServiceTicketLocation getTicketLocation() {
		return location;
	}	
	
	@Override
	public void throwEvents() {
		// Auto-generated method stub
		
	}
	

}
