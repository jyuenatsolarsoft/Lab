package com.cms.gwt.fs.client.rpc.action;

public class GetServiceDetail  extends GetServiceTicketProperties {
	
	private String sequence;
	
	protected GetServiceDetail(String ticketNumber, String sequence)
	{
		this.ticketNumber = ticketNumber;
		this.sequence = sequence;
	}
	
	public String getSequence()
	{
		return sequence;
	}
	
	public static GetServiceDetail newInstance(String ticketNumber, String sequence)
	{
		return new GetServiceDetail(ticketNumber, sequence);
	}
}	
