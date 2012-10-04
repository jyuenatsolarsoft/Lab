package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.model.billing.BillingDetail;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.CalculateBillingDetailResponse;

public class CalculateBillingDetailXMLResponseHandler extends ReceiveBillingDetailXMLResponseHandler 
{
	/**
	 * Construct the ActionResponse.
	 * 
	 * @param event The event received in the response
	 * @return The response.
	 */
	protected ActionResponse constructResponse(BillingDetail detail)
	{
		// Override the method in the parent class to determine whether the update fails or not
		// by checking if there are any <Messages> element in the response.
		
		if (detail == null)
		{
			return new CalculateBillingDetailResponse(detail);
		}
		else
		{
			return new CalculateBillingDetailResponse(detail.getMessages(), detail);
		}
	}
}
