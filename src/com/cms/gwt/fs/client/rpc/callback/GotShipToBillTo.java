package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetShipToBillToResponse;

public abstract class GotShipToBillTo extends BaseActionCallback implements ActionAsyncCallback 
{		
    /**
     * {@inheritDoc}
     */
	public void onSuccess(ActionResponse response)
	{	
		onSuccess((GetShipToBillToResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(GetShipToBillToResponse result) {
		got(result.getCustomerName());
	}
	
	/**
	 * Process the customer name retrieved from the backend.
	 * 
	 * @param 
	 */
	public abstract void got(String CustomerName);
}
