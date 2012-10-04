package com.cms.gwt.fs.client.model.workHistory;

import java.util.ArrayList;
import java.util.List;

import com.cms.gwt.fs.client.model.FSModel;
import com.cms.gwt.fs.client.model.Messages;

public class RecordedLabourList extends FSModel 
{	
    private String ticketNumber;
    private int eventId;
    private double totalTimeRecorded;
    private List<RecordedLabourListEntry> entries;
    private Messages messages;
    
    public RecordedLabourList()
    {
    	this.entries = new ArrayList<RecordedLabourListEntry>();
    }
    
    public RecordedLabourList(List<RecordedLabourListEntry> entries)
    {
    	this.entries = entries;
    }
    
	public String getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	
	public double getTotalTimeRecorded() {
		return totalTimeRecorded;
	}
	public void setTotalTimeRecorded(double totalTimeRecorded) {
		this.totalTimeRecorded = totalTimeRecorded;
	}
	
	public List<RecordedLabourListEntry> getEntries() {
		return entries;
	}
	
	public void addEntry(RecordedLabourListEntry entry)
	{
		entries.add(entry);
	}

	public Messages getMessages() {
		return messages;
	}
	public void setMessages(Messages messages) {
		this.messages = messages;
	}    
}
