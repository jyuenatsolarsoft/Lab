package com.cms.gwt.fs.client.rpc.action;

import com.cms.gwt.fs.client.model.billing.BillingDetail;

public class AddBillingDetail extends UpdateBillingDetail {
	
	
	protected AddBillingDetail()
	{
		// empty constructor for child classes.
	}
	
	protected AddBillingDetail(BillingDetail detail)
	{
		this.detail = detail;
	}
		
	public static AddBillingDetail newInstance(BillingDetail detail)
	{
		return new AddBillingDetail(detail);
	}
	
	
}
