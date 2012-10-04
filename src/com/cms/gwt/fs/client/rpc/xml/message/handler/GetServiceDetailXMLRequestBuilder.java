package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.action.Action;
import com.cms.gwt.fs.client.rpc.action.GetServiceDetail;
import com.cms.gwt.fs.client.rpc.message.Request;
import com.cms.gwt.fs.client.rpc.message.Response;

public class GetServiceDetailXMLRequestBuilder 	extends GetServiceTicketPropertiesXMLRequestBuilder implements Instantiable
{
	final private String XML_REQUEST_NAME = "getServiceTicketTask";
		
	final private String RESPONSE_HANDLER_NAME = ReceiveServiceDetailXMLResponseHandler.class.getName();
	
	final private String XML_ELEMENT_SEQUENCE = "sequence";
	
	@Override
	public Request<Response> getRequest(Action action) {
		
		addRequestParameter(new SimpleElement(XML_ELEMENT_SEQUENCE, ((GetServiceDetail)action).getSequence()));
												
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
