package com.cms.gwt.fs.client.rpc.actionResponse;

import com.cms.gwt.fs.client.model.skill.SkillsCodeInfo;

public class GetSkillsCodeInfoResponse extends ActionResponse {

	/** The ticket list returned from the backend. */
	private SkillsCodeInfo skillsCodeInfo;
		
	public GetSkillsCodeInfoResponse(SkillsCodeInfo skillsCodeInfo)
	{
		this.skillsCodeInfo = skillsCodeInfo; 
	}	
				
	public SkillsCodeInfo getSkillCodeInfo() {
		return skillsCodeInfo;
	}

	public void throwEvents() {
		// Auto-generated method stub
		
	}
}
