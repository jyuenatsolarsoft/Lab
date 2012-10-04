package com.cms.gwt.fs.client.rpc.action;

public class GetServiceTicketLocation extends GetServiceTicketProperties
{
	@SuppressWarnings("unused")
	private GetServiceTicketLocation() {
		// Disable the default constructor.
	}
	
	public GetServiceTicketLocation(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
}
