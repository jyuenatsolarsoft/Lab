package com.cms.gwt.fs.client.rpc.action;

/**
 * Get the required skill list for the service ticket.
 * 
 *
 */
public class GetServiceTicketSkillsList extends GetServiceTicketProperties 
{			
	protected GetServiceTicketSkillsList(String ticketNumber)
	{
		this.ticketNumber = ticketNumber;
	}
	
	public static GetServiceTicketSkillsList newInstance(String ticketNumber)
	{
		return new GetServiceTicketSkillsList(ticketNumber);
	}
}
