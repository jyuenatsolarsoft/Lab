package com.cms.gwt.fs.client.rpc.action;

/**
 * Parent class for the action which retrieves the properties of a particular service ticket.
 *
 */
public abstract class GetServiceTicketProperties implements Action {
	
	protected String ticketNumber;
	
	protected GetServiceTicketProperties()
	{
		// empty constructor for child classes.
	}
	
	protected GetServiceTicketProperties(String ticketNumber)
	{
		this.ticketNumber = ticketNumber;
	}
	
	public String getTicketNumber() {
		return ticketNumber;
	}	

}
