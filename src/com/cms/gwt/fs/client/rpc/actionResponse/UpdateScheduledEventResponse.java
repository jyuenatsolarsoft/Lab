package com.cms.gwt.fs.client.rpc.actionResponse;

import com.cms.gwt.fs.client.model.Messages;
import com.cms.gwt.fs.client.model.scheduledEvent.ScheduledEvent;

public class UpdateScheduledEventResponse  extends ActionResponse {

	/** The updated schedule event returned from the backend. */
	private ScheduledEvent event;
		
	public UpdateScheduledEventResponse(ScheduledEvent event)
	{
		this.event = event; 
	}	
	
	public UpdateScheduledEventResponse(Messages messages, ScheduledEvent event)
	{
		this.messages = messages;
		this.event = event; 
	}		
			
	public ScheduledEvent getEvent() {
		return event;
	}

	public void throwEvents() {
		// Auto-generated method stub
		
	}
}
