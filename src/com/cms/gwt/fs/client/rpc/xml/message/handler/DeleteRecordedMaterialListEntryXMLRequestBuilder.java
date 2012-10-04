package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.action.Action;
import com.cms.gwt.fs.client.rpc.action.DeleteRecordedLabour;
import com.cms.gwt.fs.client.rpc.action.DeleteRecordedMaterialListEntry;
import com.cms.gwt.fs.client.rpc.message.Request;
import com.cms.gwt.fs.client.rpc.message.Response;
import com.cms.gwt.fs.client.rpc.message.handler.RequestBuilder;

public class DeleteRecordedMaterialListEntryXMLRequestBuilder extends XmlRequestBuilder implements RequestBuilder, Instantiable {

	final static private String XML_REQUEST_NAME = "deleteRecordedMaterialEntry";
	
	final static private String XML_RESPONSE_HANDLER = DeleteRecordedMaterialListEntryXMLResponseHandler.class.getName();
	
	final static private String XML_ELEMENT_EVENT_ID = "eventID";
	final static private String XML_ELEMENT_TICKET_NUMBER = "ticketNumber";
	final static private String XML_ELEMENT_SEQUENCE = "sequence";
	final static private String XML_ELEMENT_LINE = "line";
	
	public Request<Response> getRequest(Action action) 
	{	
		DeleteRecordedMaterialListEntry currAction = (DeleteRecordedMaterialListEntry) action;
				
		addRequestParameter(new SimpleElement(XML_ELEMENT_TICKET_NUMBER, currAction.getTicketNumber()));
		addRequestParameter(new SimpleElement(XML_ELEMENT_EVENT_ID, currAction.getEventId()));
		addRequestParameter(new SimpleElement(XML_ELEMENT_SEQUENCE, currAction.getSequence()));
		addRequestParameter(new SimpleElement(XML_ELEMENT_LINE, currAction.getLine()));
					
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