package com.cms.gwt.fs.client.rpc.xml.message;

import com.cms.gwt.fs.client.rpc.message.Request;
import com.cms.gwt.fs.client.rpc.message.Response;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * XML Request for the data services.
 */
public class XMLRequest implements Request<Response>, IsSerializable 
{	
	private String xmlMessage;
	
	public XMLRequest()
	{
		
	}
	
	public XMLRequest(String xmlMessage)
	{
		this.xmlMessage = xmlMessage;
	}
	
	public String getRequestMessage() {		
		return xmlMessage;
	}

}
