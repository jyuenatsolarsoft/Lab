package com.cms.gwt.fs.client.rpc.actionResponse;

import com.cms.gwt.fs.client.model.billing.BillingSummary;

public class GetBillingSummaryResponse extends ActionResponse {

	/** The billing summary returned from the backend. */
	private BillingSummary billingSummary;
		
	public GetBillingSummaryResponse(BillingSummary summary)
	{
		this.billingSummary = summary; 
	}	
				
	public BillingSummary getBillingSummary() {
		return billingSummary;
	}

	public void throwEvents() {
		// Auto-generated method stub
		
	}
}
