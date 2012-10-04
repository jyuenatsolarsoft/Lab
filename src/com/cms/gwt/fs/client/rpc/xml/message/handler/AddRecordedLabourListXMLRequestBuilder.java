package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.model.workHistory.TimeEntryList;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.action.Action;
import com.cms.gwt.fs.client.rpc.action.AddRecordedLabourList;
import com.cms.gwt.fs.client.rpc.message.Request;
import com.cms.gwt.fs.client.rpc.message.Response;
import com.cms.gwt.fs.client.rpc.message.handler.RequestBuilder;
import com.cms.gwt.fs.client.rpc.xml.message.parser.TimeEntryListParser;

public class AddRecordedLabourListXMLRequestBuilder extends XmlRequestBuilder implements RequestBuilder, Instantiable {

	final static private String XML_REQUEST_NAME = "addRecordedLabourTimeEntries";
	
	final static protected String XML_RESPONSE_HANDLER = AddRecordedLabourListXMLResponseHandler.class.getName();
	
	final static private String XML_ELEMENT_TICKET_NUMBER = "ticketNumber";
	final static private String XML_ELEMENT_EVENT_ID = "eventID";
	
	public Request<Response> getRequest(Action action) 
	{	
		AddRecordedLabourList currAction = (AddRecordedLabourList) action;

		TimeEntryList timeEntries = currAction.getTimeEntryList();
		
		addRequestParameter(new SimpleElement(XML_ELEMENT_TICKET_NUMBER, timeEntries.getTicketNumber()));
		addRequestParameter(new SimpleElement(XML_ELEMENT_EVENT_ID, Integer.toString(timeEntries.getEventId())));
		addRequestParameter(TimeEntryListParser.getInstance().serialize(timeEntries));
					
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
