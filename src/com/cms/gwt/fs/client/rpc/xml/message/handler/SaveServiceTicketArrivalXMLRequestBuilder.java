package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.action.Action;
import com.cms.gwt.fs.client.rpc.action.SaveServiceTicketArrival;
import com.cms.gwt.fs.client.rpc.message.Request;
import com.cms.gwt.fs.client.rpc.message.Response;
import com.cms.gwt.fs.client.rpc.message.handler.RequestBuilder;

public class SaveServiceTicketArrivalXMLRequestBuilder extends XmlRequestBuilder implements RequestBuilder, Instantiable {

	final static private String XML_REQUEST_NAME = "saveServiceTicketArrival";
	
	final static protected String XML_RESPONSE_HANDLER = SaveServiceTicketArrivalXMLResponseHandler.class.getName();
	
	final static private String XML_ELEMENT_TICKET_NUMBER = "ticketNumber";
	final static private String XML_ELEMENT_EVENT_ID = "eventID";
	final static private String XML_ELEMENT_DATE = "date";
	final static private String XML_ELEMENT_TIME_ARRIVED = "timeArrived";
	
	public Request<Response> getRequest(Action action) 
	{	
		SaveServiceTicketArrival currAction = (SaveServiceTicketArrival) action;
				
		addRequestParameter(new SimpleElement(XML_ELEMENT_TICKET_NUMBER, currAction.getTicketNumber()));
		addRequestParameter(new SimpleElement(XML_ELEMENT_EVENT_ID, currAction.getEventId()));
		
		if (currAction.getDate() != null)
		{
			addRequestParameter(new SimpleElement(XML_ELEMENT_DATE, currAction.getDate().toString()));
		}		
		
		addRequestParameter(new SimpleElement(XML_ELEMENT_TIME_ARRIVED, currAction.getTimeArrived()));
					
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
