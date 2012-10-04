package com.cms.gwt.fs.client.rpc.action;


public class DeleteScheduledEvent implements Action {

	private String ticketNumber;
	private String eventId;
	
	protected DeleteScheduledEvent()
	{
		// empty constructor for child classes.
	}
	
	protected DeleteScheduledEvent(String ticketNumber, String eventId)
	{
		this.ticketNumber = ticketNumber;
		this.eventId = eventId;
	}
	
	public String getEventId() 
	{
		return eventId;
	}	
	
	public String getTicketNumber() 
	{
		return ticketNumber;
	}
	
	public static DeleteScheduledEvent newInstance(String ticketNumber, String eventId)
	{
		return new DeleteScheduledEvent(ticketNumber, eventId);
	}	
}
