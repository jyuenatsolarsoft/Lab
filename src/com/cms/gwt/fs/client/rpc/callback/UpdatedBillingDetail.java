package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.model.billing.BillingDetail;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.UpdateBillingDetailResponse;

public abstract class UpdatedBillingDetail extends BaseActionCallback implements ActionAsyncCallback
{
    /**
     * {@inheritDoc}
     */
	public void onSuccess(ActionResponse response)
	{
		onSuccess((UpdateBillingDetailResponse)response);
	}
	
	protected void onSuccess(UpdateBillingDetailResponse result) {
		got(result.getBillingDetail());
	}
	
	/**
	 * Got the new/updated BillingDetail..
	 * 
	 * @param event The new/updated event.
	 */	
	public abstract void got(BillingDetail detail);
}
