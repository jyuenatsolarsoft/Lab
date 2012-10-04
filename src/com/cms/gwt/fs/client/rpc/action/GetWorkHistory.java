package com.cms.gwt.fs.client.rpc.action;

public class GetWorkHistory  extends GetServiceTicketProperties 
{
	private String eventId;
	
 	protected GetWorkHistory(String ticketNumber, String eventId)
 	{
 		this.ticketNumber = ticketNumber;
 		this.eventId = eventId;
 	}
 	
 	public String getEventId()
 	{
 		return eventId;
 	}
 	
 	public static GetWorkHistory newInstance(String ticketNumber, String eventId)
 	{
 		return new GetWorkHistory(ticketNumber, eventId);
 	} 		
}
