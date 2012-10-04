package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.action.Action;
import com.cms.gwt.fs.client.rpc.action.GetServiceDetailMaterialList;
import com.cms.gwt.fs.client.rpc.message.Request;
import com.cms.gwt.fs.client.rpc.message.Response;

public class GetServiceDetailMaterialListXMLRequestBuilder extends GetServiceTicketPropertiesXMLRequestBuilder implements Instantiable
{
	final private static String XML_REQUEST_NAME = "getServiceTicketTaskMaterialList";
	
	final private static String XML_ELEMENT_SEQUENCE = "sequence";
		
	final private String RESPONSE_HANDLER_NAME = ReceiveServiceDetailMaterialListXMLResponseHandler.class.getName();
	
	@Override
	public Request<Response> getRequest(Action action) {
		
		addRequestParameter(new SimpleElement(XML_ELEMENT_SEQUENCE, ((GetServiceDetailMaterialList)action).getSequence()));
												
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
