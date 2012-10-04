package com.cms.gwt.fs.client.rpc.actionResponse;

import com.cms.gwt.fs.client.model.skill.RequiredSkillsList;

public class GetServiceTicketSkillsListResponse extends ActionResponse {

	/** 
	 * The skill list from the backend. It contains the required skills
	 * of the specified service ticket.
	 */
	private RequiredSkillsList skillList;
	
	/**
	 * The key of the service ticket which requires the skills contained in this response.
	 */
	private String ticketNumber;
	
	protected GetServiceTicketSkillsListResponse(RequiredSkillsList skillList, String ticketNumber)
	{
		this.skillList = skillList;
		this.ticketNumber = ticketNumber;
	}
				
	public RequiredSkillsList getSkillList() {
		return skillList;
	}
	
	public String getTicketNumber() {
		return ticketNumber;
	}

	public void throwEvents() {
		// Auto-generated method stub
		
	}
	
	public static GetServiceTicketSkillsListResponse newInstance(RequiredSkillsList skillList, String ticketNumber)
	{
		return new GetServiceTicketSkillsListResponse(skillList, ticketNumber);
	}
	
}
