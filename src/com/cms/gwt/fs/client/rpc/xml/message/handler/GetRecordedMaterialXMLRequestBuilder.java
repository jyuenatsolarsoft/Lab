package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.action.Action;
import com.cms.gwt.fs.client.rpc.action.GetRecordedMaterial;
import com.cms.gwt.fs.client.rpc.message.Request;
import com.cms.gwt.fs.client.rpc.message.Response;

public class GetRecordedMaterialXMLRequestBuilder extends GetServiceTicketPropertiesXMLRequestBuilder implements Instantiable 
{
	final private String XML_REQUEST_NAME = "getRecordedMaterialEntry";
		
	final static private String XML_ELEMENT_EVENT_ID = "eventID";
	final static private String XML_ELEMENT_SEQUENCE = "sequence";
	final static private String XML_ELEMENT_LINE = "line";
				
	final private String RESPONSE_HANDLER_NAME = ReceiveRecordedMaterialXMLResponseHandler.class.getName();
	
	@Override
	public Request<Response> getRequest(Action action) {
		
		addRequestParameter(new SimpleElement(XML_ELEMENT_EVENT_ID, ((GetRecordedMaterial)action).getEventId()));
		addRequestParameter(new SimpleElement(XML_ELEMENT_SEQUENCE, ((GetRecordedMaterial)action).getSequence()));
		addRequestParameter(new SimpleElement(XML_ELEMENT_LINE, ((GetRecordedMaterial)action).getLine()));
												
		return super.getRequest(action);
	}	
		
	@Override
	protected String getRequestName() {

		return XML_REQUEST_NAME;
	}	
	
	@Override
	public String getResponseHandlerName() {

		return RESPONSE_HANDLER_NAME;
	}	
}	

