package com.cms.gwt.fs.client.rpc.action;

public class GetRecordedLabourList extends GetServiceTicketProperties 
{
	private String eventId;
	
 	protected GetRecordedLabourList(String ticketNumber, String eventId)
 	{
 		this.ticketNumber = ticketNumber;
 		this.eventId = eventId;
 	}
 	
 	public String getEventId()
 	{
 		return eventId;
 	}
 	
 	public static GetRecordedLabourList newInstance(String ticketNumber, String eventId)
 	{
 		return new GetRecordedLabourList(ticketNumber, eventId);
 	} 		
}
