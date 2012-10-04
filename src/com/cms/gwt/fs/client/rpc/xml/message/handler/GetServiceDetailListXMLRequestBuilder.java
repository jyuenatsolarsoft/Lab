package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.reflection.Instantiable;

public class GetServiceDetailListXMLRequestBuilder extends GetServiceTicketPropertiesXMLRequestBuilder implements Instantiable
{
	final private String XML_REQUEST_NAME = "getServiceTicketTaskList";
		
	final private String RESPONSE_HANDLER_NAME = ReceiveServiceDetailListXMLResponseHandler.class.getName();

	@Override
	protected String getRequestName() {

		return XML_REQUEST_NAME;
	}

	@Override
	public String getResponseHandlerName() {

		return RESPONSE_HANDLER_NAME;
	}
}
