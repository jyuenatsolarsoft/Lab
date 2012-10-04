package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.DeleteBillingDetailResponse;

public abstract class DeletedBillingDetail extends BaseActionCallback implements ActionAsyncCallback 
{
    /**
     * {@inheritDoc}
     */
	public void onSuccess(ActionResponse response)
	{	
		onSuccess((DeleteBillingDetailResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(DeleteBillingDetailResponse result) {
		// do nothing for now.
		// this is needed for the child class to overload this.
	}	
}
