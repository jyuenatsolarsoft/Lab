package com.cms.gwt.fs.client.rpc.actionResponse;

import com.cms.gwt.fs.client.model.skill.RequiredSkill;

public class GetServiceTicketSkillResponse extends ActionResponse {
	
	private RequiredSkill skill;
		
	public GetServiceTicketSkillResponse(RequiredSkill skill)
	{
		this.skill = skill; 
	}	
			
	public RequiredSkill getSkill() {
		return skill;
	}

	public void throwEvents() {
		// Auto-generated method stub
		
	}
}