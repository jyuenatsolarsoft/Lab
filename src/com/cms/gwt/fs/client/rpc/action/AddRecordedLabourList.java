package com.cms.gwt.fs.client.rpc.action;

import com.cms.gwt.fs.client.model.workHistory.TimeEntryList;

public class AddRecordedLabourList implements Action 
{
	private TimeEntryList timeEntryList;
	
	protected AddRecordedLabourList()
	{
		// empty constructor for child classes.
	}
	
	protected AddRecordedLabourList(TimeEntryList timeEntryList)
	{
		this.timeEntryList = timeEntryList;
	}
	
	public TimeEntryList getTimeEntryList()
	{
		return timeEntryList;
	}
		
	public static AddRecordedLabourList newInstance(TimeEntryList timeEntryList)
	{
		return new AddRecordedLabourList(timeEntryList);
	}
	
	
}
