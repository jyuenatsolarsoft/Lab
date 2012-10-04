package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.action.Action;
import com.cms.gwt.fs.client.rpc.message.Request;
import com.cms.gwt.fs.client.rpc.message.Response;
import com.cms.gwt.fs.client.rpc.message.handler.RequestBuilder;

public class GetTaxGroupDictionaryXMLRequestBuilder extends XmlRequestBuilder implements RequestBuilder, Instantiable {

	final private String XML_REQUEST_NAME = "Get_TaxCodes";
	
	final private String RESPONSE_HANDLER_NAME = ReceiveTaxGroupDictionaryXMLResponseHandler.class.getName();
			
	public Request<Response> getRequest(Action action) {
									
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


