package com.cms.gwt.fs.client.model.workHistory;

import java.sql.Date;

import com.cms.gwt.fs.client.model.FSModel;

public class WorkHistory extends FSModel 
{
    private String ticketNumber;
    private int eventId;
    private int counterReading;
    private String counterDescription;
    private String technician;
    private String technicianDescription;
    private Date arrivalDate;
    private String arrivalTime;
    private Double hoursReported;
    private boolean isMaterialReported;
    private int numberOfItems;
    private boolean isOtherReported;
    private double totalTimeRecorded; 
    private double totalHoursWorked;
    
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
	public int getCounterReading() {
		return counterReading;
	}
	public void setCounterReading(int counterReading) {
		this.counterReading = counterReading;
	}
	public String getCounterDescription() {
		return counterDescription;
	}
	public void setCounterDescription(String counterDescription) {
		this.counterDescription = counterDescription;
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
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public Double getHoursReported() {
		return hoursReported;
	}
	public void setHoursReported(Double hoursReported) {
		this.hoursReported = hoursReported;
	}
	public boolean isMaterialReported() {
		return isMaterialReported;
	}
	public void setMaterialReported(boolean isMaterialReported) {
		this.isMaterialReported = isMaterialReported;
	}
	public int getNumberOfItems() {
		return numberOfItems;
	}
	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}
	public boolean isOtherReported() {
		return isOtherReported;
	}
	public void setOtherReported(boolean isOtherReported) {
		this.isOtherReported = isOtherReported;
	}
	public double getTotalTimeRecorded() {
		return totalTimeRecorded;
	}
	public void setTotalTimeRecorded(double totalTimeRecorded) {
		this.totalTimeRecorded = totalTimeRecorded;
	}
	public double getTotalHoursWorked() {
		return totalHoursWorked;
	}
	public void setTotalHoursWorked(double totalHoursWorked) {
		this.totalHoursWorked = totalHoursWorked;
	}       	
}
