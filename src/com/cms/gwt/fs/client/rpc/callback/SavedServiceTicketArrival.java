package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.SaveServiceTicketArrivalResponse;

public class SavedServiceTicketArrival extends BaseActionCallback implements ActionAsyncCallback 
{
    /**
     * {@inheritDoc}
     */
	public void onSuccess (ActionResponse response)
	{	
		onSuccess((SaveServiceTicketArrivalResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(SaveServiceTicketArrivalResponse result) {
		// do nothing for now.
		// this is needed for the child class to overload this.
	}	
}
