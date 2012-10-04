package com.cms.gwt.fs.client.rpc.actionResponse;

import com.cms.gwt.fs.client.model.billing.BillingDetail;

public class GetBillingDetailResponse extends ActionResponse {
	
	protected BillingDetail billingDetail;
		
	public GetBillingDetailResponse(BillingDetail detail)
	{
		this.billingDetail = detail; 
	}	
	
	public BillingDetail getBillingDetail() {
		return billingDetail;
	}

	public void throwEvents() {
		// Auto-generated method stub
		
	}
}