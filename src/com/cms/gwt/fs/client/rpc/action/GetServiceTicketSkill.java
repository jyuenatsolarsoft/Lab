package com.cms.gwt.fs.client.rpc.action;


public class GetServiceTicketSkill extends GetServiceTicketProperties 
{
	private String skill;
	
 	protected GetServiceTicketSkill(String ticketNumber, String skill)
 	{
 		this.ticketNumber = ticketNumber;
 		this.skill = skill;
 	}
 	
 	public String getSkill()
 	{
 		return skill;
 	}
 	
 	public static GetServiceTicketSkill newInstance(String ticketNumber, String skill)
 	{
 		return new GetServiceTicketSkill(ticketNumber, skill);
 	} 		
}
