package com.cms.gwt.fs.client.rpc.action;

public class GetAccessHours extends GetServiceTicketProperties {
			
	protected GetAccessHours(String ticketNumber)
	{
		this.ticketNumber = ticketNumber;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}
	
	public static GetAccessHours newInstance(String ticketNumber)
	{
		return new GetAccessHours(ticketNumber);
	}
	
	
}
