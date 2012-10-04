package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.model.scheduledEvent.ScheduledEventList;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetScheduledEventListResponse;


public abstract class GotScheduledEventList extends BaseActionCallback implements ActionAsyncCallback 
{
    /**
     * {@inheritDoc}
     */
	public void onSuccess (ActionResponse response)
	{	
		onSuccess((GetScheduledEventListResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(GetScheduledEventListResponse result) {
		got(result.getTicketNumber(), result.getEventList());
	}
	
	/**
	 * Process the detail list retrieved from the backend.
	 * 
	 * @param eventList  The service ticket detail list requested in the Action.
	 */
	public abstract void got(String ticketNumber, ScheduledEventList eventList);

}
