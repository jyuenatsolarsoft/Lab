package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.reflection.Instantiable;

public class GetServiceTicketLocationXMLRequestBuilder 
	extends GetServiceTicketPropertiesXMLRequestBuilder implements Instantiable 
{
	final private String XML_REQUEST_NAME = "getServiceTicketLocation";
			
	final private String RESPONSE_HANDLER_NAME = ReceiveServiceTicketLocationXMLResponseHandler.class.getName();
		
	@Override
	protected String getRequestName() {

		return XML_REQUEST_NAME;
	}	
	
	@Override
	public String getResponseHandlerName() {

		return RESPONSE_HANDLER_NAME;
	}	
	

}
