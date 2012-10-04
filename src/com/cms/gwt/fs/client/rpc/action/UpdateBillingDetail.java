package com.cms.gwt.fs.client.rpc.action;

import com.cms.gwt.fs.client.model.billing.BillingDetail;

public class UpdateBillingDetail implements Action {

	protected BillingDetail detail;
	
	protected UpdateBillingDetail()
	{
		// empty constructor for child classes.
	}
	
	protected UpdateBillingDetail(BillingDetail detail)
	{
		this.detail = detail;
	}
	
	public BillingDetail getBillingDetail() 
	{
		return detail;
	}	
	
	public static UpdateBillingDetail newInstance(BillingDetail detail)
	{
		return new UpdateBillingDetail(detail);
	}	
}
