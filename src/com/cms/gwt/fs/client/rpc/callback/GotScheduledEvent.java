package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.model.scheduledEvent.ScheduledEvent;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetScheduledEventResponse;

public abstract class GotScheduledEvent extends BaseActionCallback implements ActionAsyncCallback 
{
    /**
     * {@inheritDoc}
     */
	public void onSuccess (ActionResponse response)
	{	
		onSuccess((GetScheduledEventResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(GetScheduledEventResponse result) {
		got(result.getEvent());
	}
	
	/**
	 * Process the scheduled event retrieved from the backend.
	 * 
	 * @param event with the same eventId in the 
	 */
	public abstract void got(ScheduledEvent event);

}
