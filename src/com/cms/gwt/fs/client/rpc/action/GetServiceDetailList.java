package com.cms.gwt.fs.client.rpc.action;


/**
 * Retrieve the service ticket details from the backend.
 *
 */
public class GetServiceDetailList extends GetServiceTicketProperties {

	protected GetServiceDetailList(String ticketNumber)
	{
		this.ticketNumber = ticketNumber;
	}
	
	public static GetServiceDetailList newInstance(String ticketNumber)
	{
		return new GetServiceDetailList(ticketNumber);
	}	
}
