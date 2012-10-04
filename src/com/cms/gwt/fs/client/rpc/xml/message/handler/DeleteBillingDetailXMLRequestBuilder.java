package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.action.Action;
import com.cms.gwt.fs.client.rpc.action.DeleteBillingDetail;
import com.cms.gwt.fs.client.rpc.message.Request;
import com.cms.gwt.fs.client.rpc.message.Response;
import com.cms.gwt.fs.client.rpc.message.handler.RequestBuilder;

public class DeleteBillingDetailXMLRequestBuilder extends XmlRequestBuilder implements RequestBuilder, Instantiable {

	final static private String XML_REQUEST_NAME = "deleteServiceTicketBillingDetail";
	
	final static private String XML_RESPONSE_HANDLER = DeleteBillingDetailXMLResponseHandler.class.getName();
	
	final static private String XML_ELEMENT_LINE = "line";
	final static private String XML_ELEMENT_TICKET_NUMBER = "ticketNumber";
	
	public Request<Response> getRequest(Action action) 
	{	
		DeleteBillingDetail currAction = (DeleteBillingDetail) action;
				
		addRequestParameter(new SimpleElement(XML_ELEMENT_TICKET_NUMBER, currAction.getTicketNumber()));
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
