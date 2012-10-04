package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.model.billing.BillingDetail;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.CalculateBillingDetailResponse;

public abstract class CalculatedBillingDetail extends GotBillingDetail
{
    /**
     * {@inheritDoc}
     */
	public void onSuccess (ActionResponse response)
	{	
		onSuccess((CalculateBillingDetailResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(CalculateBillingDetailResponse result) {
		got(result.getBillingDetail());
	}
	
	/**
	 * Process the billing details retrieved from the backend.
	 * 
	 * @param detail billing details. 
	 */
	public abstract void got(BillingDetail detail);

}
