package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.reflection.Instantiable;

public class GetServiceTicketSkillsListXMLRequestBuilder 
	extends GetServiceTicketPropertiesXMLRequestBuilder implements Instantiable
{	
	final static private String XML_REQUEST_NAME = "getServiceTicketSkillsList";		
	
	final static private String RESPONSE_HANDLER_NAME = ReceiveServiceTicketSkillsListXMLResponseHandler.class.getName();
		
	@Override
	protected String getRequestName() {

		return XML_REQUEST_NAME;
	}	
	
	@Override
	public String getResponseHandlerName() {

		return RESPONSE_HANDLER_NAME;
	}	
	

}
