package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.reflection.Instantiable;

public class GetScheduledEventListXMLRequestBuilder extends GetServiceTicketPropertiesXMLRequestBuilder implements Instantiable
{
	final private static String XML_REQUEST_NAME = "getServiceTicketScheduleList";
		
	final private String RESPONSE_HANDLER_NAME = ReceiveScheduledEventListXMLResponseHandler.class.getName();
		
	@Override
	protected String getRequestName() {

		return XML_REQUEST_NAME;
	}

	@Override
	public String getResponseHandlerName() {

		return RESPONSE_HANDLER_NAME;
	}
}
