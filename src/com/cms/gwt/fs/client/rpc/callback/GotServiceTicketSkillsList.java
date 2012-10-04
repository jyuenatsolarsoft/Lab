package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.model.skill.RequiredSkillsList;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetServiceTicketSkillsListResponse;

public abstract class GotServiceTicketSkillsList extends BaseActionCallback implements ActionAsyncCallback {

    /**
     * {@inheritDoc}
     */
	public void onSuccess (ActionResponse response)
	{	
		onSuccess((GetServiceTicketSkillsListResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(GetServiceTicketSkillsListResponse result) {
		got(result.getSkillList(), result.getTicketNumber());
	}
	
	/**
	 * Process the skills list retrieved from the backend.
	 * 
	 * @param ticket location requested in the Action.
	 */
	public abstract void got(RequiredSkillsList ticket, String ticketNumber);
	
}
