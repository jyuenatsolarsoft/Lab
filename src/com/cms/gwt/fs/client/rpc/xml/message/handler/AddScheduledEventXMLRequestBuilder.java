package com.cms.gwt.fs.client.rpc.xml.message.handler;

public class AddScheduledEventXMLRequestBuilder extends UpdateScheduledEventXMLRequestBuilder
{	
	// AddScheduledEvent has the same XML structure as the UpdateScheduledEvent.
	// The only difference is the request element tag name.
	
	final static private String XML_REQUEST_NAME = "addServiceTicketScheduledEvent";
		
	@Override
	protected String getRequestName() 
	{		
		return XML_REQUEST_NAME;
	}
}
