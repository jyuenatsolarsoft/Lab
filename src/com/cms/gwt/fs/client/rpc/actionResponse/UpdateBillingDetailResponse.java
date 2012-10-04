package com.cms.gwt.fs.client.rpc.actionResponse;

import com.cms.gwt.fs.client.model.Messages;
import com.cms.gwt.fs.client.model.billing.BillingDetail;

public class UpdateBillingDetailResponse extends ActionResponse {

	/** The updated BillingDetail returned from the backend. */
	private BillingDetail detail;
		
	public UpdateBillingDetailResponse(BillingDetail detail)
	{
		this.detail = detail; 
	}	
	
	public UpdateBillingDetailResponse(Messages messages, BillingDetail detail)
	{
		this.messages = messages;
		this.detail = detail; 
	}		
			
	public BillingDetail getBillingDetail() {
		return detail;
	}

	public void throwEvents() {
		// Auto-generated method stub
		
	}
}
