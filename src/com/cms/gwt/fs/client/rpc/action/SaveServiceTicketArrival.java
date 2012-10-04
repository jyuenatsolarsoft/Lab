package com.cms.gwt.fs.client.rpc.action;

import java.sql.Date;

public class SaveServiceTicketArrival extends GetServiceTicketProperties 
{
	private String eventId;
	private Date date;
	private String timeArrived;
	
 	protected SaveServiceTicketArrival(String ticketNumber, String eventId, Date date, String timeArrived)
 	{
 		this.ticketNumber = ticketNumber;
 		this.eventId = eventId;
 		this.date = date;
 		this.timeArrived = timeArrived;
 	}
 	
 	public String getEventId()
 	{
 		return eventId;
 	}
 	
 	public Date getDate()
 	{
 		return date;
 	}

 	public String getTimeArrived()
 	{
 		return timeArrived; 
 	}
 	
 	public static SaveServiceTicketArrival newInstance(String ticketNumber, String eventId, Date date, String timeArrived)
 	{
 		return new SaveServiceTicketArrival(ticketNumber, eventId, date, timeArrived);
 	} 		
}
