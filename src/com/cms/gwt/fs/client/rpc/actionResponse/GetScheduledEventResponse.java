package com.cms.gwt.fs.client.rpc.actionResponse;

import com.cms.gwt.fs.client.model.scheduledEvent.ScheduledEvent;

public class GetScheduledEventResponse extends ActionResponse {
	
	private ScheduledEvent event;
		
	public GetScheduledEventResponse(ScheduledEvent event)
	{
		this.event = event; 
	}	
	
	public ScheduledEvent getEvent() {
		return event;
	}

	public void throwEvents() {
		// Auto-generated method stub
		
	}
}