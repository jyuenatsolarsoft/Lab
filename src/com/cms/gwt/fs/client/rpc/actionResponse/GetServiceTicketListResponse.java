package com.cms.gwt.fs.client.rpc.actionResponse;

import com.cms.gwt.fs.client.model.ServiceTicketList;

/**
 * The ActionResponse of the GetServiceTicketListAction.
 * 
 */
public class GetServiceTicketListResponse extends ActionResponse {

	/** The ticket list returned from the backend. */
	private ServiceTicketList ticketList;
	
	
	public GetServiceTicketListResponse(ServiceTicketList ticketList)
	{
		this.ticketList = ticketList; 
	}
		
	
	public ServiceTicketList getTicketList() {
		return ticketList;
	}


	public void throwEvents() {
	}

}
