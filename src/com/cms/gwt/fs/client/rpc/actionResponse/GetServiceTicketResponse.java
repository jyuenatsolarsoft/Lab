package com.cms.gwt.fs.client.rpc.actionResponse;

import com.cms.gwt.fs.client.model.ServiceTicket;

/**
 * The ActionResponse of the GetServiceTicketAction.
 *
 */
public class GetServiceTicketResponse extends ActionResponse {

	/** 
	 * The ticket retrieved from the backend. It contains the detailed information
	 * of the requested ticket.
	 */
	private ServiceTicket ticket;
	
	public GetServiceTicketResponse(ServiceTicket ticket)
	{
		this.ticket = ticket; 
	}	
			
	public ServiceTicket getTicket() {
		return ticket;
	}

	public void throwEvents() {
		// Auto-generated method stub
		
	}
	
	
}
