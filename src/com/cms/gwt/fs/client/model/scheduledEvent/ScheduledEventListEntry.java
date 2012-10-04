package com.cms.gwt.fs.client.model.scheduledEvent;

import java.sql.Date;

import com.cms.gwt.fs.client.model.FSModel;

public class ScheduledEventListEntry extends FSModel {

    private int eventId;
    private String type;   
    private String typeDescription;
    private double manpower;
    private double timeEstimate;
    private String technician;
    private String technicianDescription;
    private Date scheduledStartDate;
    private String scheduledStartTime;    
    private String timeZone;
    private String status;
    private String statusDescription;
    
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypeDescription() {
		return typeDescription;
	}
	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}
	public double getManpower() {
		return manpower;
	}
	public void setManpower(double manpower) {
		this.manpower = manpower;
	}
	public double getTimeEstimate() {
		return timeEstimate;
	}
	public void setTimeEstimate(double timeEstimate) {
		this.timeEstimate = timeEstimate;
	}
	public String getTechnician() {
		return technician;
	}
	public void setTechnician(String technician) {
		this.technician = technician;
	}
	public String getTechnicianDescription() {
		return technicianDescription;
	}
	public void setTechnicianDescription(String technicianDescription) {
		this.technicianDescription = technicianDescription;
	}
	public Date getScheduledStartDate() {
		return scheduledStartDate;
	}
	public void setScheduledStartDate(Date scheduledStartDate) {
		this.scheduledStartDate = scheduledStartDate;
	}
	public String getScheduledStartTime() {
		return scheduledStartTime;
	}
	public void setScheduledStartTime(String scheduledStartTime) {
		this.scheduledStartTime = scheduledStartTime;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public String getStatusDescription() {
		return statusDescription;
	}
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}           	
}
