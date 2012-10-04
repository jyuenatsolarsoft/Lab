package com.cms.gwt.fs.client.rpc.action;

public class GetScheduledEventList extends GetServiceTicketProperties
{	
	
	protected GetScheduledEventList(String ticketNumber)
	{	
		super(ticketNumber);
	}
	
	
	public static GetScheduledEventList newInstance(String ticketNumber)
	{
		return new GetScheduledEventList(ticketNumber);
	}
}