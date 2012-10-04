package com.cms.gwt.fs.client.rpc.actionResponse;

import com.cms.gwt.fs.client.model.scheduledEvent.TechnicianSchedule;

public class GetTechnicianScheduleResponse extends ActionResponse 
{	
	private TechnicianSchedule schedule;		
		
	public GetTechnicianScheduleResponse(TechnicianSchedule schedule)
	{
		this.schedule = schedule; 
	}	
	
	public TechnicianSchedule getTechnicianSchedule() 
	{
		return schedule;
	}	
	
	public void throwEvents() {
		// Auto-generated method stub
		
	}
}