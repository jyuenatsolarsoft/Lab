package com.cms.gwt.fs.client.rpc.action;

import com.cms.gwt.fs.client.model.scheduledEvent.ScheduledEvent;

public class UpdateScheduledEvent implements Action {

	protected ScheduledEvent event;
	
	protected UpdateScheduledEvent()
	{
		// empty constructor for child classes.
	}
	
	protected UpdateScheduledEvent(ScheduledEvent event)
	{
		this.event = event;
	}
	
	public ScheduledEvent getScheduledEvent() 
	{
		return event;
	}	
	
	public static UpdateScheduledEvent newInstance(ScheduledEvent event)
	{
		return new UpdateScheduledEvent(event);
	}
	
}
