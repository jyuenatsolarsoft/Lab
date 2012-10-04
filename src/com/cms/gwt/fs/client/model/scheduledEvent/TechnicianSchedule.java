package com.cms.gwt.fs.client.model.scheduledEvent;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.cms.gwt.fs.client.model.FSModel;

/**
 * Technician Schedule contains the scheduled events of a specific technicians. 
 *
 */
public class TechnicianSchedule extends FSModel
{
	/**
	 * The name of the technician.
	 */
	private String technician;
	
	/**
	 * Start date of the events listed in the schedule.
	 */
	private Date startDate;
	
	/**
	 * To be clarified.
	 */
	private String horizon;
	
	/**
	 * A list of events which have been assigned to the technician.
	 */
	private List<TechnicianScheduledEvent> events = new ArrayList<TechnicianScheduledEvent>();
	
	
	public void addEvent(TechnicianScheduledEvent event) {
		
		events.add(event);		
	}
	
	public List<TechnicianScheduledEvent> getEvents()
	{
		return events;
	}

	public String getTechnician() {
		return technician;
	}

	public void setTechnician(String technician) {
		this.technician = technician;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getHorizon() {
		return horizon;
	}

	public void setHorizon(String horizon) {
		this.horizon = horizon;
	}			

}
