package com.cms.gwt.fs.client.model.scheduledEvent;

import java.util.ArrayList;
import java.util.List;

import com.cms.gwt.fs.client.model.FSModel;

public class ScheduledEventList extends FSModel {

	private List<ScheduledEventListEntry> events = new ArrayList<ScheduledEventListEntry>();
	
	public void add(ScheduledEventListEntry event) {
		
		events.add(event);		
	}
	
	public List<ScheduledEventListEntry> getEvents()
	{
		return events;
	}	
}
