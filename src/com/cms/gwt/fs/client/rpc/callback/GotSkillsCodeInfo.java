package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.model.skill.SkillsCodeInfo;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetSkillsCodeInfoResponse;

public abstract class GotSkillsCodeInfo extends BaseActionCallback implements ActionAsyncCallback 
{
		
    /**
     * {@inheritDoc}
     */
	public void onSuccess (ActionResponse response)
	{	
		onSuccess((GetSkillsCodeInfoResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(GetSkillsCodeInfoResponse result) {
		got(result.getSkillCodeInfo());
	}
	
	/**
	 * Process the ticket retrieved from the backend.
	 * 
	 * @param skillsCodeInfo Ticket requested in the Action.
	 */
	public abstract void got(SkillsCodeInfo skillsCodeInfo);
}
