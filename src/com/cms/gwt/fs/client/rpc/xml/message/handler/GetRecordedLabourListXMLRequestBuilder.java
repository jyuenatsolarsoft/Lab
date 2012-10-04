package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.action.Action;
import com.cms.gwt.fs.client.rpc.action.GetRecordedLabourList;
import com.cms.gwt.fs.client.rpc.message.Request;
import com.cms.gwt.fs.client.rpc.message.Response;

public class GetRecordedLabourListXMLRequestBuilder extends GetServiceTicketPropertiesXMLRequestBuilder implements Instantiable 
{
	final private String XML_REQUEST_NAME = "getServiceTicketRecordedLabourList";
		
	final static private String XML_ELEMENT_EVENT_ID = "eventID";
			
	final private String RESPONSE_HANDLER_NAME = ReceiveRecordedLabourListXMLResponseHandler.class.getName();
	
	@Override
	public Request<Response> getRequest(Action action) {
		
		addRequestParameter(new SimpleElement(XML_ELEMENT_EVENT_ID, ((GetRecordedLabourList)action).getEventId()));
												
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
