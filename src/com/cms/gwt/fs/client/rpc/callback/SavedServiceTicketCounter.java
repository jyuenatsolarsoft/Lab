package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.SaveServiceTicketCounterResponse;

public abstract class SavedServiceTicketCounter extends BaseActionCallback implements ActionAsyncCallback 
{
    /**
     * {@inheritDoc}
     */
	public void onSuccess(ActionResponse response)
	{	
		onSuccess((SaveServiceTicketCounterResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(SaveServiceTicketCounterResponse result) {
		// do nothing for now.
		// this is needed for the child class to overload this.
	}	
}
