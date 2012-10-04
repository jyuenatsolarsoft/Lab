package com.cms.gwt.fs.client.rpc.xml.message.handler;

import java.util.Map;

import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.action.Action;
import com.cms.gwt.fs.client.rpc.action.GetNotes;
import com.cms.gwt.fs.client.rpc.message.Request;
import com.cms.gwt.fs.client.rpc.message.Response;
import com.cms.gwt.fs.client.rpc.message.handler.RequestBuilder;

public class GetNotesXMLRequestBuilder extends XmlRequestBuilder implements RequestBuilder, Instantiable {
		
	final static protected String XML_RESPONSE_HANDLER = ReceiveNotesXMLResponseHandler.class.getName();
	
	final static private String XML_REQUEST_NAME_PREFIX = "Get";	
	
	private String requestName;
	
	public Request<Response> getRequest(Action action) 
	{	
		GetNotes currAction = (GetNotes) action;
		
		setRequestName(currAction.getType().getTypeName());
		
		Map<String, String> keys = currAction.getKeys();
		
		for (String key : keys.keySet())
		{
			addRequestParameter(new SimpleElement(key, keys.get(key)));
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
		return XML_RESPONSE_HANDLER;
	}	
	
}

