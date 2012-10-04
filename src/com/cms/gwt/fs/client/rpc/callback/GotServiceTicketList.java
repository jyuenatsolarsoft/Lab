package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.model.ServiceTicketList;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetServiceTicketListResponse;

/**
 * An abstract callback class to handle the result of GetServiceTicketList Action.
 * 
 * UI presenter will use the implementation of this class to handle the result of the Action requested.
 *
 */
public abstract class GotServiceTicketList extends BaseActionCallback implements ActionAsyncCallback
{

    /**
     * {@inheritDoc}
     */
	public void onSuccess(ActionResponse response)
	{
		onSuccess((GetServiceTicketListResponse)response);
	}
	
	protected void onSuccess(GetServiceTicketListResponse result) {
		got(result.getTicketList());
	}
	
	/**
	 * Process the ticket list retrieved from the backend.
	 * 
	 * @param ticket The ticket list requested in the Action.
	 */	
	public abstract void got(ServiceTicketList ticketList);
}
