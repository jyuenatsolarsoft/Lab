package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.model.ServiceTicket;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetServiceTicketResponse;

/**
 * An abstract callback class to handle the result of GetServiceTicket Action.
 * 
 * UI presenter will use the implementation of this class to handle the result of the Action requested.
 *
 */
public abstract class GotServiceTicket extends BaseActionCallback implements ActionAsyncCallback {

	//<T extends ActionResponse> void onSuccess(T actionResponse);
	
    /**
     * {@inheritDoc}
     */
	public void onSuccess (ActionResponse response)
	{	
		onSuccess((GetServiceTicketResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(GetServiceTicketResponse result) {
		got(result.getTicket());
	}
	
	/**
	 * Process the ticket retrieved from the backend.
	 * 
	 * @param ticket Ticket requested in the Action.
	 */
	public abstract void got(ServiceTicket ticket);
}
