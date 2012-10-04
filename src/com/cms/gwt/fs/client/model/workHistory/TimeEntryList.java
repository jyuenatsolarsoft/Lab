package com.cms.gwt.fs.client.model.workHistory;

import java.util.ArrayList;
import java.util.List;

public class TimeEntryList {

    private String ticketNumber;
    private int eventId;
    private WorkHistory workHistory;
    private double totalHoursWorked;
    private List<TimeEntry> entries;
    
    public TimeEntryList()
    {
    	entries = new ArrayList<TimeEntry>();
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
	public WorkHistory getWorkHistory() {
		return workHistory;
	}
	public void setWorkHistory(WorkHistory workHistory) {
		this.workHistory = workHistory;
	}
	public double getTotalHoursWorked() {
		return totalHoursWorked;
	}
	public void setTotalHoursWorked(double totalHoursWorked) {
		this.totalHoursWorked = totalHoursWorked;
	}
	public List<TimeEntry> getEntries() {
		return entries;
	}

	public void addEntry(TimeEntry timeEntry)
	{
		entries.add(timeEntry);
	}
	    
}
