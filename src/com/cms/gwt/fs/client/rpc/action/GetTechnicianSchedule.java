package com.cms.gwt.fs.client.rpc.action;

import java.sql.Date;

public class GetTechnicianSchedule implements Action 
{		
	private String technician;
	private Date startDate;
	private String horizon;
	 	
	protected GetTechnicianSchedule()
	{
		// empty constructor for child classes.
	}
	
	protected GetTechnicianSchedule(String  technician, Date startDate, String horizon)
	{
		this.technician = technician;
		this.startDate = startDate;
		this.horizon = horizon;
	}
	
	public String getTechnician() 
	{
		return technician;
	}	
	
	public Date getStartDate()
	{
		return startDate;
	}
	
	public String getHorizon()
	{
		return horizon;
	}
	
	public static GetTechnicianSchedule newInstance(String technican, Date startDate, String horizon)
	{
		return new GetTechnicianSchedule(technican, startDate, horizon);
	}	
}
		
	

