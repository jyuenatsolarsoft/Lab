package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.model.material.RecordedMaterialEntries;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.action.Action;
import com.cms.gwt.fs.client.rpc.action.AddRecordedMaterials;
import com.cms.gwt.fs.client.rpc.message.Request;
import com.cms.gwt.fs.client.rpc.message.Response;
import com.cms.gwt.fs.client.rpc.message.handler.RequestBuilder;
import com.cms.gwt.fs.client.rpc.xml.message.parser.RecordedMaterialEntriesParser;

public class AddRecordedMaterialsXMLRequestBuilder extends XmlRequestBuilder implements RequestBuilder, Instantiable {

	final static private String XML_REQUEST_NAME = "saveRecordedMaterialEntries";
	
	final static protected String XML_RESPONSE_HANDLER = AddRecordedMaterialsXMLResponseHandler.class.getName();
	
	final static private String XML_ELEMENT_TICKET_NUMBER = "ticketNumber";
	final static private String XML_ELEMENT_EVENT_ID = "eventID";
	
	public Request<Response> getRequest(Action action) 
	{	
		AddRecordedMaterials currAction = (AddRecordedMaterials) action;

		RecordedMaterialEntries materials = currAction.getMaterialEntries();
		
		addRequestParameter(new SimpleElement(XML_ELEMENT_TICKET_NUMBER, materials.getTicketNumber()));
		addRequestParameter(new SimpleElement(XML_ELEMENT_EVENT_ID, Integer.toString(materials.getEventId())));
				
		addRequestParameter(RecordedMaterialEntriesParser.getInstance().serialize(materials));
					
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
