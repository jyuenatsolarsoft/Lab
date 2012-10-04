package com.cms.gwt.fs.client.rpc.xml.message.handler;

import java.util.ArrayList;
import java.util.List;

import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.action.Action;
import com.cms.gwt.fs.client.rpc.action.GetServiceTicket;
import com.cms.gwt.fs.client.rpc.message.Request;
import com.cms.gwt.fs.client.rpc.message.Response;
import com.cms.gwt.fs.client.rpc.message.handler.RequestBuilder;
import com.cms.gwt.fs.client.rpc.xml.message.XMLRequest;


public class GetServiceTicketXMLRequestBuilder extends XmlRequestBuilder implements RequestBuilder, Instantiable 
{
	final private String XML_REQUEST_NAME = "getServiceTicket";
	final private String XML_SERVICE_TICKET = "ticketNumber";
	
	final private String RESPONSE_HANDLER_NAME = ReceiveServiceTicketXMLResponseHandler.class.getName();
	
	public Request<Response> getRequest(Action action) {
		

		GetServiceTicket currAction = (GetServiceTicket) action;
		
		List<XmlRepresentable> requestParameters = new ArrayList<XmlRepresentable>();		
		requestParameters.add(new SimpleElement(XML_SERVICE_TICKET, currAction.getTicketNumber()));
					
		return new XMLRequest(generateRequest(requestParameters));
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