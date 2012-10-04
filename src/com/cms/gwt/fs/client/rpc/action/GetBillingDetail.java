package com.cms.gwt.fs.client.rpc.action;

public class GetBillingDetail extends GetServiceTicketProperties 
{
	private String line;
	
 	protected GetBillingDetail(String ticketNumber, String line)
 	{
 		this.ticketNumber = ticketNumber;
 		this.line = line;
 	}
 	
 	public String getLine()
 	{
 		return line;
 	}
 	
 	public static GetBillingDetail newInstance(String ticketNumber, String line)
 	{
 		return new GetBillingDetail(ticketNumber, line);
 	} 		
}
