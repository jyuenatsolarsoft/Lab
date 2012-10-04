package com.cms.gwt.fs.client.rpc.action;


public class CompleteServiceTicketEvent implements Action {

	protected String eventId;
	protected String ticketNumber;
	
	protected CompleteServiceTicketEvent()
	{
		// empty constructor for child classes.
	}
	
	protected CompleteServiceTicketEvent(String ticketNumber, String eventId)
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
	
	public static CompleteServiceTicketEvent newInstance(String ticketNumber, String eventId)
	{
		return new CompleteServiceTicketEvent(ticketNumber, eventId);
	}
	
}
