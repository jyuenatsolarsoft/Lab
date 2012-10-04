package com.cms.gwt.fs.client.model.scheduledEvent;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.cms.gwt.fs.client.model.FSModel;

public class TechnicianScheduledEvent extends FSModel 
{
	// TODO: extract the common fields to a base event class to avoid duplicate fields or code.	 
	// private ScheduledEvent event;
	
    private int eventID;
    private String customerCode;
    private String customerName;
    private Date scheduledStartDate;
    private String scheduledStartTime;
    private String typeDescription;
    private String ticketNumber;
    private String timeEstimate;
    private String description;
    private String statusDescription;

    private AddressDetails addressDetails;

	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * Returns a sql Date object which contains the start date.
	 * 
	 * Hours and seconds in this object should be ignored.
	 * 
	 */
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

	public String getTypeDescription() {
		return typeDescription;
	}

	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public String getTimeEstimate() {
		return timeEstimate;
	}

	public void setTimeEstimate(String timeEstimate) {
		this.timeEstimate = timeEstimate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public AddressDetails getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(AddressDetails addressDetails) {
		this.addressDetails = addressDetails;
	}     
	
	/**
	 * Returns the start time of the event.
	 * 
	 * @return
	 */
	public java.util.Date getStartTime()
	{
		if (getScheduledStartDate() != null)
		{
			String startTimeStr = getScheduledStartTime();
			
			int hourOfDay = 0;
			int minute = 0;
			if (startTimeStr != null)
			{
				String[] time = startTimeStr.split("\\.", 3);
				hourOfDay = Integer.parseInt(time[0]);
				minute = Integer.parseInt(time[1]);
			}
			
			Calendar startDate = new GregorianCalendar();
			startDate.setTime(getScheduledStartDate());
			startDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
			startDate.set(Calendar.MINUTE, minute);
							
			return startDate.getTime();
		}
		
		return null;
	}
	
	/**
	 * Returns the end time of this event.
	 * 
	 * In the case of the start time not available in this event, it will return null.
	 * 
	 * If no valid time estimate is found in this event, the end time will be considered
	 * the same as the start time. 
	 * 
	 * @return The end time of the event.
	 */
	public java.util.Date getEndTime()
	{
		if (getScheduledStartDate() != null)
		{															
			Calendar endDate = new GregorianCalendar();
			endDate.setTime(getStartTime());
			
 
			float durationInHours;
			
			try
			{
				durationInHours = Float.parseFloat(getTimeEstimate());
			}
			catch (NumberFormatException e)
			{
				// Not a valid time estimate.
				// Return the start time.
				return getScheduledStartDate();
			}
									
			// Convert hours into minutes and then round it up.
			int durationInMinutes = Math.round(durationInHours * 60);
						
			endDate.add(Calendar.MINUTE, durationInMinutes);		
			
			return endDate.getTime();
		}
		
		return null;
	}	
}
