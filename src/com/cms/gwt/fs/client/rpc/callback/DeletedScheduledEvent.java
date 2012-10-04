package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.DeleteScheduledEventResponse;

public abstract class DeletedScheduledEvent extends BaseActionCallback implements ActionAsyncCallback 
{
    /**
     * {@inheritDoc}
     */
	public void onSuccess (ActionResponse response)
	{	
		onSuccess((DeleteScheduledEventResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(DeleteScheduledEventResponse result) {
		// do nothing for now.
		// this is needed for the child class to overload this.
	}	
}
