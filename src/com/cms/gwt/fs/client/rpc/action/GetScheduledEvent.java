package com.cms.gwt.fs.client.rpc.action;

public class GetScheduledEvent extends GetServiceTicketProperties 
{
	private String eventId;
	
 	protected GetScheduledEvent(String ticketNumber, String eventId)
 	{
 		this.ticketNumber = ticketNumber;
 		this.eventId = eventId;
 	}
 	
 	public String getEventId()
 	{
 		return eventId;
 	}
 	
 	public static GetScheduledEvent newInstance(String ticketNumber, String eventId)
 	{
 		return new GetScheduledEvent(ticketNumber, eventId);
 	} 		
}
