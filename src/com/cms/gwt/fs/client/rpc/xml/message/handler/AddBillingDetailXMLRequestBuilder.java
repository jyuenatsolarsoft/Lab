package com.cms.gwt.fs.client.rpc.xml.message.handler;

public class AddBillingDetailXMLRequestBuilder  extends UpdateBillingDetailXMLRequestBuilder
{	
	// AddBillingDetail has the same XML structure as the UpdateBillingDetail.
	// The only difference is the request element tag name.
	
	final static private String XML_REQUEST_NAME = "addServiceTicketBillingDetail";
		
	@Override
	protected String getRequestName() 
	{		
		return XML_REQUEST_NAME;
	}
}
