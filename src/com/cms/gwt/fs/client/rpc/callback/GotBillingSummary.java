package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.model.billing.BillingSummary;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetBillingSummaryResponse;

public abstract class GotBillingSummary extends BaseActionCallback implements ActionAsyncCallback 
{
    /**
     * {@inheritDoc}
     */
	public void onSuccess(ActionResponse response)
	{	
		onSuccess((GetBillingSummaryResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(GetBillingSummaryResponse result) {
		got(result.getBillingSummary());
	}
	
	/**
	 * Process the billing summary retrieved from the backend.
	 * 
	 * @param summary billing summary.
	 */
	public abstract void got(BillingSummary summary);

}
