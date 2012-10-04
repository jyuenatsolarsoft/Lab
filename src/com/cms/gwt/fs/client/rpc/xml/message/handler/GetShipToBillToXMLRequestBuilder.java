package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.action.Action;
import com.cms.gwt.fs.client.rpc.action.GetShipToBillTo;
import com.cms.gwt.fs.client.rpc.message.Request;
import com.cms.gwt.fs.client.rpc.message.Response;
import com.cms.gwt.fs.client.rpc.message.handler.RequestBuilder;

public class GetShipToBillToXMLRequestBuilder extends XmlRequestBuilder implements RequestBuilder, Instantiable {	

	// This is only a partial name only.
	final static private String XML_REQUEST_NAME = "Get_ShipToBillTo";
	
	final static private String XML_ELEMENT_CUSTOMER_CODE = "CustomerCode";
	final static private String XML_ELEMENT_PLANT = "Plant";
	
	final private String RESPONSE_HANDLER_NAME = ReceiveShipToBillToXMLResponseHandler.class.getName();
				
	public Request<Response> getRequest(Action action) {
		
		GetShipToBillTo currAction = (GetShipToBillTo) action;
		
		addRequestParameter(new SimpleElement(XML_ELEMENT_CUSTOMER_CODE, currAction.getCustomerCode()));
		addRequestParameter(new SimpleElement(XML_ELEMENT_PLANT, currAction.getPlant()));
									
		return generateXmlRequest();													
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

