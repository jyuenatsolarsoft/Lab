package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.model.scheduledEvent.ScheduledEvent;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.UpdateScheduledEventResponse;

public abstract class UpdatedScheduledEvent extends BaseActionCallback implements ActionAsyncCallback
{
    /**
     * {@inheritDoc}
     */
	public void onSuccess(ActionResponse response)
	{
		onSuccess((UpdateScheduledEventResponse)response);
	}
	
	protected void onSuccess(UpdateScheduledEventResponse result) {
		got(result.getEvent());
	}
	
	/**
	 * Got the new/updated scheduled event.
	 * 
	 * @param event The new/updated event.
	 */	
	public abstract void got(ScheduledEvent event);
}
