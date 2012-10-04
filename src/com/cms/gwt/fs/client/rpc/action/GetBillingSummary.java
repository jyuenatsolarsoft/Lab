package com.cms.gwt.fs.client.rpc.action;

public class GetBillingSummary  extends GetServiceTicketProperties {
			
	protected GetBillingSummary(String ticketNumber)
	{
		this.ticketNumber = ticketNumber;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}
	
	public static GetBillingSummary newInstance(String ticketNumber)
	{
		return new GetBillingSummary(ticketNumber);
	}
	
	
}
