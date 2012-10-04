package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.model.scheduledEvent.ScheduledEvent;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.action.Action;
import com.cms.gwt.fs.client.rpc.action.UpdateScheduledEvent;
import com.cms.gwt.fs.client.rpc.message.Request;
import com.cms.gwt.fs.client.rpc.message.Response;
import com.cms.gwt.fs.client.rpc.message.handler.RequestBuilder;
import com.cms.gwt.fs.client.rpc.xml.message.parser.ScheduledEventParser;

public class UpdateScheduledEventXMLRequestBuilder extends XmlRequestBuilder implements RequestBuilder, Instantiable {

	final static private String XML_REQUEST_NAME = "updateServiceTicketScheduledEvent";
	
	final static protected String XML_RESPONSE_HANDLER = UpdateScheduledEventXMLResponseHandler.class.getName();
	
	final static private String XML_ELEMENT_TICKET_NUMBER = "ticketNumber";
	
	public Request<Response> getRequest(Action action) 
	{	
		UpdateScheduledEvent currAction = (UpdateScheduledEvent) action;

		ScheduledEvent event = currAction.getScheduledEvent();
		
		addRequestParameter(new SimpleElement(XML_ELEMENT_TICKET_NUMBER, event.getTicketNumber()));
		addRequestParameter(ScheduledEventParser.getInstance().serialize(event));
					
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
