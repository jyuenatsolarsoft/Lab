package com.cms.gwt.fs.client.rpc.action;

public class GetRecordedMaterialEntries extends GetServiceTicketProperties 
{
	private String eventId;
	
 	protected GetRecordedMaterialEntries(String ticketNumber, String eventId)
 	{
 		this.ticketNumber = ticketNumber;
 		this.eventId = eventId;
 	}
 	
 	public String getEventId()
 	{
 		return eventId;
 	}
 	
 	public static GetRecordedMaterialEntries newInstance(String ticketNumber, String eventId)
 	{
 		return new GetRecordedMaterialEntries(ticketNumber, eventId);
 	} 		
}
