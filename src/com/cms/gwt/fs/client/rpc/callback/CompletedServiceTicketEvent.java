package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.CompleteServiceTicketEventResponse;

public class CompletedServiceTicketEvent extends BaseActionCallback implements ActionAsyncCallback 
{
    /**
     * {@inheritDoc}
     */
	public void onSuccess(ActionResponse response)
	{	
		onSuccess((CompleteServiceTicketEventResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(CompleteServiceTicketEventResponse result) {
		// do nothing for now.
		// this is needed for the child class to overload this.
	}	
}
