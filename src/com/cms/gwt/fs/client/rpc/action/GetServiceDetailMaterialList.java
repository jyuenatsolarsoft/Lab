package com.cms.gwt.fs.client.rpc.action;

public class GetServiceDetailMaterialList extends GetServiceTicketProperties 
{			
	private String sequence;
	
	protected GetServiceDetailMaterialList(String ticketNumber, String sequence)
	{	
		this.ticketNumber = ticketNumber;
		this.sequence = sequence;
	}
	
	public String getSequence()
	{
		return sequence;
	}
	
	public static GetServiceDetailMaterialList newInstance(String ticketNumber, String sequence)
	{
		return new GetServiceDetailMaterialList(ticketNumber, sequence);
	}
}