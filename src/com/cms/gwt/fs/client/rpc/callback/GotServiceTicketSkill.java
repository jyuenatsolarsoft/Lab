package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.model.skill.RequiredSkill;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetServiceTicketSkillResponse;

public abstract class GotServiceTicketSkill extends BaseActionCallback implements ActionAsyncCallback 
{
    /**
     * {@inheritDoc}
     */
	public void onSuccess (ActionResponse response)
	{	
		onSuccess((GetServiceTicketSkillResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(GetServiceTicketSkillResponse result) {
		got(result.getSkill());
	}
	
	/**
	 * Process the required skill retrieved from the backend.
	 * 
	 * @param skill a skill required for the service ticket.
	 */
	public abstract void got(RequiredSkill skill);

}
