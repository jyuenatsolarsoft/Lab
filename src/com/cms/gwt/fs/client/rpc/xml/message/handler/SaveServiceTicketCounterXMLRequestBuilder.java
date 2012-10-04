package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.action.Action;
import com.cms.gwt.fs.client.rpc.action.SaveServiceTicketCounter;
import com.cms.gwt.fs.client.rpc.message.Request;
import com.cms.gwt.fs.client.rpc.message.Response;
import com.cms.gwt.fs.client.rpc.message.handler.RequestBuilder;

public class SaveServiceTicketCounterXMLRequestBuilder extends XmlRequestBuilder implements RequestBuilder, Instantiable {

	final static private String XML_REQUEST_NAME = "saveServiceTicketCounter";
	
	final static protected String XML_RESPONSE_HANDLER = SaveServiceTicketCounterXMLResponseHandler.class.getName();
	
	final static private String XML_ELEMENT_TICKET_NUMBER = "ticketNumber";
	final static private String XML_ELEMENT_COUNTER_READING = "counterReading";
	
	public Request<Response> getRequest(Action action) 
	{	
		SaveServiceTicketCounter currAction = (SaveServiceTicketCounter) action;
			
		addRequestParameter(new SimpleElement(XML_ELEMENT_TICKET_NUMBER, currAction.getTicketNumber()));
		addRequestParameter(new SimpleElement(XML_ELEMENT_COUNTER_READING, currAction.getCounterReading()));
					
		return generateXmlRequest();
	}	
	
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
