package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.reflection.Instantiable;

public class GetAccessHoursXMLRequestBuilder 
	extends GetServiceTicketPropertiesXMLRequestBuilder implements Instantiable
{
	final private String XML_REQUEST_NAME = "getServiceTicketAccessHours";
		
	final private String RESPONSE_HANDLER_NAME = ReceiveAccessHoursXMLResponseHandler.class.getName();				

	@Override
	protected String getRequestName() {

		return XML_REQUEST_NAME;
	}

	@Override
	public String getResponseHandlerName() {

		return RESPONSE_HANDLER_NAME;
	}
}
