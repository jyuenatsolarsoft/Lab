package com.cms.gwt.fs.client.rpc.action;

import com.cms.gwt.fs.client.model.billing.BillingDetail;

/**
 * Calculate the billing details. i.e. subtotal, tax and total.
 * 
 *
 */
public class CalculateBillingDetail extends UpdateBillingDetail // parent class is needed for the request builder to work properly. 
{ 		
	protected CalculateBillingDetail()
	{
		// empty constructor for child classes.
	}
	
	protected CalculateBillingDetail(BillingDetail detail)
	{
		super(detail);
	}
		
	
	public static CalculateBillingDetail newInstance(BillingDetail detail)
	{
		return new CalculateBillingDetail(detail);
	}	
}
