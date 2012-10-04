package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.model.location.ServiceTicketLocation;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetServiceTicketLocationResponse;


public abstract class GotServiceTicketLocation extends BaseActionCallback implements ActionAsyncCallback {

    /**
     * {@inheritDoc}
     */
	public void onSuccess (ActionResponse response)
	{	
		onSuccess((GetServiceTicketLocationResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(GetServiceTicketLocationResponse result) {
		got(result.getTicketLocation());
	}
	
	/**
	 * Process the ticket location retrieved from the backend.
	 * 
	 * @param ticket location requested in the Action.
	 */
	public abstract void got(ServiceTicketLocation ticket);
	
}
