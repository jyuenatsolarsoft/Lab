package com.cms.gwt.fs.client.rpc.xml.message;

import com.cms.gwt.fs.client.rpc.message.Response;
import com.google.gwt.user.client.rpc.IsSerializable;


/**
 * XML Response from the data services.
 * 
 */
public class XMLResponse implements Response, IsSerializable 
{
	private String response;
	
	public  XMLResponse()
	{
		
	}
	
	public XMLResponse(String response)
	{
		this.response = response;
	}

	public String getResponseMessage() {
		
		return response;
	}
}
