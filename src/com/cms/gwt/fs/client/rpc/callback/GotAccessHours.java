package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.model.accessHours.AccessHours;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetAccessHoursResponse;

public abstract class GotAccessHours extends BaseActionCallback implements ActionAsyncCallback 
{
    /**
     * {@inheritDoc}
     */
	public void onSuccess (ActionResponse response)
	{	
		onSuccess((GetAccessHoursResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(GetAccessHoursResponse result) {
		got(result.getAccessHours());
	}
	
	/**
	 * Process the ticket access hours retrieved from the backend.
	 * 
	 * @param accessHours access hours requested in the Action.
	 */
	public abstract void got(AccessHours accessHours);

}
