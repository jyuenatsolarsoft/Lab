package com.cms.gwt.fs.client.rpc.xml.message.handler;

public class CalculateBillingDetailXMLRequestBuilder extends UpdateBillingDetailXMLRequestBuilder
{	
	// CalculatedBillingDetail has a similar XML structure as the UpdateBillingDetail, 
	// therefore extends the UpdateBillDetailXMLRequestBuilder and reuse the code there.	
	
	final static private String XML_REQUEST_NAME = "calculateServiceTicketBillingDetail";
		
	final static protected String XML_RESPONSE_HANDLER = CalculateBillingDetailXMLResponseHandler.class.getName();
		
	@Override
	protected String getRequestName() 
	{		
		return XML_REQUEST_NAME;
	}
	
	@Override
	public String getResponseHandlerName() {
		return XML_RESPONSE_HANDLER;
	}		
}
