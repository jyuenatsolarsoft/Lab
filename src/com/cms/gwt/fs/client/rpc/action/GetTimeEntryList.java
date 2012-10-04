package com.cms.gwt.fs.client.rpc.action;

public class GetTimeEntryList extends GetServiceTicketProperties 
{
	private String eventId;
	
 	protected GetTimeEntryList(String ticketNumber, String eventId)
 	{
 		this.ticketNumber = ticketNumber;
 		this.eventId = eventId;
 	}
 	
 	public String getEventId()
 	{
 		return eventId;
 	}
 	
 	public static GetTimeEntryList newInstance(String ticketNumber, String eventId)
 	{
 		return new GetTimeEntryList(ticketNumber, eventId);
 	} 		
}
