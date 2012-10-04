package com.cms.gwt.fs.client.rpc.actionResponse;

import com.cms.gwt.fs.client.model.scheduledEvent.ScheduledEventList;

public class GetScheduledEventListResponse  extends ActionResponse 
{		
	/** 
	 * The scheduled event list from the backend. It contains the materials for
	 * a specific task/detail.
	 *  
	 */
	private ScheduledEventList eventList;
	
	private String ticketNumber;
		
	protected GetScheduledEventListResponse(ScheduledEventList eventList, String ticketNumber)
	{
		this.eventList = eventList;
		this.ticketNumber = ticketNumber;
	}
				
	public ScheduledEventList getEventList() {
		return eventList;
	}
	
	public String getTicketNumber() {
		return ticketNumber;
	}
	
	public void throwEvents() {
		// Auto-generated method stub
		
	}
	
	public static GetScheduledEventListResponse newInstance(ScheduledEventList eventList, String ticketNumber)
	{
		return new GetScheduledEventListResponse(eventList, ticketNumber);
	}
}
