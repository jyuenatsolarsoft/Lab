package com.cms.gwt.fs.client.model.scheduledEvent;

import java.sql.Date;

import com.cms.gwt.fs.client.model.FSModel;
import com.cms.gwt.fs.client.model.Messages;

/**
 * Represents the scheduled event.
 * 
 *
 */
public class ScheduledEvent extends FSModel {

	
    private String ticketNumber;
    private int eventId;
    private String type;
    private String typeDescription;
    private int parentEventId;
    private String parentSplitType;
    private String parentSplitTypeDescription;
    private double manpower;
    private double timeEstimate;
    private String specialEventCode;
    private String technician;
    private String technicianDescription;
    private Date scheduledStartDate;
    private String scheduledStartTime;
    private double travelTimeEstimate;
    private String timeZone;
    private String serviceCategory;
    private boolean overrideRates;
    private double labourRate;
    private double overtimeRate;
    private String status;
    private String statusDescription;
    private Messages messages;
    
	// TODO: Enum?
    /**
     * Event Status - Ready for dispatch.
     */
    final public static int STATUS_NEW = 1;    
    
    /**
     * Event Status - Ready for dispatch.
     */
    final public static int STATUS_READY_FOR_DISPATCH = 2;
    
    /**
     * Event Status - Assigned
     */
    final public static int STATUS_ASSIGNED = 3;
    
    /**
     * Event Status - Contacted/Confirmed.
     */    
    final public static int STATUS_CONTACTED = 4;
    
    /**
     * Event Status - Accepted
     */    
    final public static int STATUS_ACCEPTED = STATUS_CONTACTED;
    
    /**
     * Event Status - Declined.
     */    
    final public static int STATUS_DECLINED = STATUS_READY_FOR_DISPATCH;
        
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
	public int getParentEventId() {
		return parentEventId;
	}
	public void setParentEventId(int parentEventId) {
		this.parentEventId = parentEventId;
	}
	public String getParentSplitType() {
		return parentSplitType;
	}
	public void setParentSplitType(String parentSplitType) {
		this.parentSplitType = parentSplitType;
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
	public String getSpecialEventCode() {
		return specialEventCode;
	}
	public void setSpecialEventCode(String specialEventCode) {
		this.specialEventCode = specialEventCode;
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
	public double getTravelTimeEstimate() {
		return travelTimeEstimate;
	}
	public void setTravelTimeEstimate(double travelTimeEstimate) {
		this.travelTimeEstimate = travelTimeEstimate;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public String getServiceCategory() {
		return serviceCategory;
	}
	public void setServiceCategory(String serviceCategory) {
		this.serviceCategory = serviceCategory;
	}
	public boolean isOverrideRates() {
		return overrideRates;
	}
	public void setOverrideRates(boolean overrideRates) {
		this.overrideRates = overrideRates;
	}
	public double getLabourRate() {
		return labourRate;
	}
	public void setLabourRate(double labourRate) {
		this.labourRate = labourRate;
	}
	public double getOvertimeRate() {
		return overtimeRate;
	}
	public void setOvertimeRate(double overtimeRate) {
		this.overtimeRate = overtimeRate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusDescription() {
		return statusDescription;
	}
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
	public Messages getMessages() {
		return messages;
	}
	public void setMessages(Messages messages) {
		this.messages = messages;
	}
	public String getParentSplitTypeDescription() {
		return parentSplitTypeDescription;
	}
	public void setParentSplitTypeDescription(String parentSplitTypeDescription) {
		this.parentSplitTypeDescription = parentSplitTypeDescription;
	}
}
