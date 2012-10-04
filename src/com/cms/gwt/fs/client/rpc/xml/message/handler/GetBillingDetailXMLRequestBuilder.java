package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.action.Action;
import com.cms.gwt.fs.client.rpc.action.GetBillingDetail;
import com.cms.gwt.fs.client.rpc.message.Request;
import com.cms.gwt.fs.client.rpc.message.Response;

public class GetBillingDetailXMLRequestBuilder extends GetServiceTicketPropertiesXMLRequestBuilder implements Instantiable 
{
	final private String XML_REQUEST_NAME = "getServiceTicketBillingDetail";
		
	final static private String XML_ELEMENT_LINE = "line";
			
	final private String RESPONSE_HANDLER_NAME = ReceiveBillingDetailXMLResponseHandler.class.getName();
	
	@Override
	public Request<Response> getRequest(Action action) {
		
		addRequestParameter(new SimpleElement(XML_ELEMENT_LINE, ((GetBillingDetail)action).getLine()));
												
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
