package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.model.material.RecordedMaterial;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.action.Action;
import com.cms.gwt.fs.client.rpc.action.UpdateRecordedMaterial;
import com.cms.gwt.fs.client.rpc.message.Request;
import com.cms.gwt.fs.client.rpc.message.Response;
import com.cms.gwt.fs.client.rpc.message.handler.RequestBuilder;
import com.cms.gwt.fs.client.rpc.xml.message.parser.RecordedMaterialEntryParser;

public class UpdateRecordedMaterialXMLRequestBuilder extends XmlRequestBuilder implements RequestBuilder, Instantiable {

	final static private String XML_REQUEST_NAME = "updateRecordedMaterialEntry";
	
	final static protected String XML_RESPONSE_HANDLER = UpdateRecordedMaterialXMLResponseHandler.class.getName();
	
	final static private String XML_ELEMENT_TICKET_NUMBER = "ticketNumber";
	final static private String XML_ELEMENT_EVENTID = "eventID";
	final static private String XML_ELEMENT_SEQUENCE = "sequence";
	final static private String XML_ELEMENT_LINE = "line";		
	
	public Request<Response> getRequest(Action action) 
	{	
		UpdateRecordedMaterial currAction = (UpdateRecordedMaterial) action;

		RecordedMaterial material = currAction.getRecordedMaterial();
		
		addRequestParameter(new SimpleElement(XML_ELEMENT_TICKET_NUMBER, material.getTicketNumber()));
		addRequestParameter(new SimpleElement(XML_ELEMENT_EVENTID, Integer.toString(material.getEventId())));
		addRequestParameter(new SimpleElement(XML_ELEMENT_SEQUENCE, Integer.toString(material.getSequence())));
		addRequestParameter(new SimpleElement(XML_ELEMENT_LINE, Integer.toString(material.getLine())));
		
		addRequestParameter(RecordedMaterialEntryParser.getInstance().serialize(material));							
					
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
