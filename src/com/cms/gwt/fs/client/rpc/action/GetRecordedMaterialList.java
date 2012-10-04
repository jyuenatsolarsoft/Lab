package com.cms.gwt.fs.client.rpc.action;

public class GetRecordedMaterialList extends GetServiceTicketProperties	
{
	private String eventId;

	protected GetRecordedMaterialList(String ticketNumber, String eventId)
	{
		this.ticketNumber = ticketNumber;
		this.eventId = eventId;
	}
	
	public String getEventId()
	{
		return eventId;
	}
	
	public static GetRecordedMaterialList newInstance(String ticketNumber, String eventId)
	{
		return new GetRecordedMaterialList(ticketNumber, eventId);
	} 		
}