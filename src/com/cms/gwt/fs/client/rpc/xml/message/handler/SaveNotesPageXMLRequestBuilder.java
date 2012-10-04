package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.action.Action;
import com.cms.gwt.fs.client.rpc.action.GetCodeDescription;
import com.cms.gwt.fs.client.rpc.action.SaveNotesPage;
import com.cms.gwt.fs.client.rpc.message.Request;
import com.cms.gwt.fs.client.rpc.message.Response;
import com.cms.gwt.fs.client.rpc.message.handler.RequestBuilder;
import com.cms.gwt.fs.client.rpc.xml.message.parser.NotesPageParser;

public class SaveNotesPageXMLRequestBuilder extends XmlRequestBuilder implements RequestBuilder, Instantiable {	

	// This is only a partial name only.
	final private static String XML_REQUEST_NAME_PREFIX = "Save";		

	final private static String RESPONSE_HANDLER_NAME = SaveNotesPageXMLResponseHandler.class.getName();
	
	final private static String XML_ELEMENT_TICKET_NUMBER = "ticketNumber";
	
	private String requestName;
		
	public Request<Response> getRequest(Action action) {
		
		SaveNotesPage currAction = (SaveNotesPage) action;
					
		setRequestName(currAction.getNotesType().getTypeName());
				
		addRequestParameter(new SimpleElement(XML_ELEMENT_TICKET_NUMBER, currAction.getTicketNumber()));
		addRequestParameter(NotesPageParser.getInstance().serialize(currAction.getNotesPage()));
							
		return generateXmlRequest();													
	}
	
	/**
	 * Set the request name.
	 */
	protected void setRequestName(String requestNameSuffix)
	{
		requestName = XML_REQUEST_NAME_PREFIX + requestNameSuffix;
	}

	@Override
	protected String getRequestName() {
	
		return requestName;
	}
	
	@Override
	public String getResponseHandlerName() {
	
		return RESPONSE_HANDLER_NAME;
	}
}

