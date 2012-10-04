package com.cms.gwt.fs.client.rpc.action;



public class SaveServiceTicketCounter implements Action {

	private String ticketNumber;
	private String counterReading;
	
	protected SaveServiceTicketCounter()
	{
		// empty constructor for child classes.
	}
	
	protected SaveServiceTicketCounter(String ticketNumber, String counterReading)
	{
		this.ticketNumber = ticketNumber;
		this.counterReading = counterReading;
	}
	
	public String getTicketNumber() 
	{
		return ticketNumber;
	}	
	
	public String getCounterReading()
	{
		return counterReading;
	}
	
	public static SaveServiceTicketCounter newInstance(String ticketNumber, String counterReading)
	{
		return new SaveServiceTicketCounter(ticketNumber, counterReading);
	}
	
}
