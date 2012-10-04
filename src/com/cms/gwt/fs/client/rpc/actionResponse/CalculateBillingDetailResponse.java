package com.cms.gwt.fs.client.rpc.actionResponse;

import com.cms.gwt.fs.client.model.Messages;
import com.cms.gwt.fs.client.model.billing.BillingDetail;

public class CalculateBillingDetailResponse extends GetBillingDetailResponse {
	
	public CalculateBillingDetailResponse(BillingDetail detail)
	{
		super(detail); 
	}	
	
	public CalculateBillingDetailResponse(Messages messages, BillingDetail detail)
	{		
		super(detail);
		this.messages = messages;
	}		

	public void throwEvents() {
		// Auto-generated method stub
		
	}
}