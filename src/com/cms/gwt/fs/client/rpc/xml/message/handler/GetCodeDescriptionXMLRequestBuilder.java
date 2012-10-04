package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.action.Action;
import com.cms.gwt.fs.client.rpc.action.GetCodeDescription;
import com.cms.gwt.fs.client.rpc.message.Request;
import com.cms.gwt.fs.client.rpc.message.Response;
import com.cms.gwt.fs.client.rpc.message.handler.RequestBuilder;

public class GetCodeDescriptionXMLRequestBuilder extends XmlRequestBuilder implements RequestBuilder, Instantiable {	

	// This is only a partial name only.
	final private String XML_REQUEST_NAME_PREFIX = "get";
	
	final private String XML_ELEMENT_TYPE = "type";

	final private String RESPONSE_HANDLER_NAME = ReceiveCodeDescriptionXMLResponseHandler.class.getName();
	
	private String requestName;
		
	public Request<Response> getRequest(Action action) {
		
		GetCodeDescription currAction = (GetCodeDescription) action;
					
		setRequestName(currAction.getCodeName());
		
		if (currAction.getType() != null)
		{
			addRequestParameter(new SimpleElement(XML_ELEMENT_TYPE, ((GetCodeDescription)action).getType()));
		}
							
		return generateXmlRequest();													
	}
	
	/**
	 * Set the request name.
	 */
	protected void setRequestName(String requestNameSuffix)
	{
		requestName = XML_REQUEST_NAME_PREFIX + requestNameSuffix;
	}

	@Override
	protected String getRequestName() {
	
		return requestName;
	}
	
	@Override
	public String getResponseHandlerName() {
	
		return RESPONSE_HANDLER_NAME;
	}
}

