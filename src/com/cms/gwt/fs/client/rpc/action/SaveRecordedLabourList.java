package com.cms.gwt.fs.client.rpc.action;

import com.cms.gwt.fs.client.model.workHistory.RecordedLabourList;

public class SaveRecordedLabourList implements Action 
{
	private RecordedLabourList labourList;
	
	protected SaveRecordedLabourList()
	{
		// empty constructor for child classes.
	}
	
	protected SaveRecordedLabourList(RecordedLabourList labourList)
	{
		this.labourList = labourList;
	}
	
	public RecordedLabourList getRecordedLabourList()
	{
		return labourList;
	}
		
	public static SaveRecordedLabourList newInstance(RecordedLabourList labourList)
	{
		return new SaveRecordedLabourList(labourList);
	}
	
	
}
