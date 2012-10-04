package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.model.billing.BillingDetail;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.action.Action;
import com.cms.gwt.fs.client.rpc.action.UpdateBillingDetail;
import com.cms.gwt.fs.client.rpc.message.Request;
import com.cms.gwt.fs.client.rpc.message.Response;
import com.cms.gwt.fs.client.rpc.message.handler.RequestBuilder;
import com.cms.gwt.fs.client.rpc.xml.message.parser.BillingDetailParser;

public class UpdateBillingDetailXMLRequestBuilder extends XmlRequestBuilder implements RequestBuilder, Instantiable {

	final static private String XML_REQUEST_NAME = "updateServiceTicketBillingDetail";
	
	final static protected String XML_RESPONSE_HANDLER = UpdateBillingDetailXMLResponseHandler.class.getName();
	
	final static private String XML_ELEMENT_TICKET_NUMBER = "ticketNumber";
	
	public Request<Response> getRequest(Action action) 
	{	
		UpdateBillingDetail currAction = (UpdateBillingDetail) action;

		BillingDetail detail = currAction.getBillingDetail();
		
		addRequestParameter(new SimpleElement(XML_ELEMENT_TICKET_NUMBER, detail.getTicketNumber()));
		addRequestParameter(BillingDetailParser.getInstance().serialize(detail));
					
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
