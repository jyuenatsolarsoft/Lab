package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.reflection.Instantiable;

/**
 * Request builder for the action GetServiceTicketItem.
 *
 */
public class GetServiceTicketItemXMLRequestBuilder 
 	extends GetServiceTicketPropertiesXMLRequestBuilder implements Instantiable
{
	final static private String XML_REQUEST_NAME = "getServiceTicketItem";
	
	final static private String RESPONSE_HANDLER_NAME = ReceiveServiceTicketItemXMLResponseHandler.class.getName();
		
	@Override
	protected String getRequestName() {

		return XML_REQUEST_NAME;
	}	
	
	@Override
	public String getResponseHandlerName() {

		return RESPONSE_HANDLER_NAME;
	}
}
