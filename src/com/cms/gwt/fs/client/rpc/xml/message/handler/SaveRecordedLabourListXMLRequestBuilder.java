package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.model.workHistory.RecordedLabourList;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.action.Action;
import com.cms.gwt.fs.client.rpc.action.SaveRecordedLabourList;
import com.cms.gwt.fs.client.rpc.message.Request;
import com.cms.gwt.fs.client.rpc.message.Response;
import com.cms.gwt.fs.client.rpc.message.handler.RequestBuilder;
import com.cms.gwt.fs.client.rpc.xml.message.parser.RecordedLabourListParser;

public class SaveRecordedLabourListXMLRequestBuilder extends XmlRequestBuilder implements RequestBuilder, Instantiable {

	final static private String XML_REQUEST_NAME = "saveServiceTicketRecordedLabourList";
	
	final static protected String XML_RESPONSE_HANDLER = SaveRecordedLabourListXMLResponseHandler.class.getName();
	
	final static private String XML_ELEMENT_TICKET_NUMBER = "ticketNumber";
	final static private String XML_ELEMENT_EVENT_ID = "eventID";
	
	public Request<Response> getRequest(Action action) 
	{	
		SaveRecordedLabourList currAction = (SaveRecordedLabourList) action;

		RecordedLabourList labourList = currAction.getRecordedLabourList();
		
		addRequestParameter(new SimpleElement(XML_ELEMENT_TICKET_NUMBER, labourList.getTicketNumber()));
		addRequestParameter(new SimpleElement(XML_ELEMENT_EVENT_ID, Integer.toString(labourList.getEventId())));
		addRequestParameter(RecordedLabourListParser.getInstance().serialize(labourList));
					
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
