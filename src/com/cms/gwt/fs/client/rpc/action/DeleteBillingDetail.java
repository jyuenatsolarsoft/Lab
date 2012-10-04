package com.cms.gwt.fs.client.rpc.action;

public class DeleteBillingDetail implements Action {

	private String ticketNumber;
	private String line;
	
	protected DeleteBillingDetail()
	{
		// empty constructor for child classes.
	}
	
	protected DeleteBillingDetail(String ticketNumber, String line)
	{
		this.ticketNumber = ticketNumber;
		this.line = line;
	}
	
	public String getLine() 
	{
		return line;
	}	
	
	public String getTicketNumber() 
	{
		return ticketNumber;
	}
	
	public static DeleteBillingDetail newInstance(String ticketNumber, String line)
	{
		return new DeleteBillingDetail(ticketNumber, line);
	}	
}
