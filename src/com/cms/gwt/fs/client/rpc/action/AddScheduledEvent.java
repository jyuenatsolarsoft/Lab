package com.cms.gwt.fs.client.rpc.action;

import com.cms.gwt.fs.client.model.scheduledEvent.ScheduledEvent;

public class AddScheduledEvent extends UpdateScheduledEvent {
		
	protected AddScheduledEvent()
	{
		// empty constructor for child classes.
	}
	
	protected AddScheduledEvent(ScheduledEvent event)
	{
		this.event = event;
	}
		
	public static AddScheduledEvent newInstance(ScheduledEvent event)
	{
		return new AddScheduledEvent(event);
	}	
}
