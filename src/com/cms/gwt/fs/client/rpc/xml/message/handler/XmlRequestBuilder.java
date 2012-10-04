package com.cms.gwt.fs.client.rpc.xml.message.handler;

import java.util.ArrayList;
import java.util.List;

import com.cms.gwt.fs.client.rpc.message.handler.RequestBuilder;
import com.cms.gwt.fs.client.rpc.xml.message.ClientDataRequest;
import com.cms.gwt.fs.client.rpc.xml.message.XMLRequest;

/**
 * Base class of the XML Request Handler.
 * 
 * All subclass should be named xxxxXmlRequestBuilder in order for the application 
 * to instantiate the request builder dynamically.
 * 
 * @see RequestBuilder
 */
public abstract class XmlRequestBuilder {
	
	List<XmlRepresentable> requestParameters = new ArrayList<XmlRepresentable>();

	/**
	 * Add request parameter.
	 * 
	 * @param newParameter
	 */
	protected void addRequestParameter(XmlRepresentable newParameter)
	{
		requestParameters.add(newParameter);
	}
	
	/**
	 * Return the request name of the XML request.
	 * 
	 * @return The name of the XML request.
	 */
	protected abstract String getRequestName();
	
	/**
	 * Return the request in XML format
	 * 
	 * @param requestParameters request parameters to be included in the XML message.
	 * 
	 * @return request in XML format.
	 */
	protected String generateRequest(List<XmlRepresentable> requestParameters)
	{
			
		ClientDataRequest request = new ClientDataRequest(getRequestName());
		
		request.setSubElements(requestParameters);
				
		return request.toXML();
	}
	
	/**
	 * Return the request in XML format
	 * 
	 * @param requestParameters request parameters to be included in the XML message.
	 * 
	 * @return request in XML format.
	 */
	protected String generateRequest()
	{
		return generateRequest(requestParameters);
	}
	
	/**
	 * Return the XMLRequest.
	 * 
	 * @return An instance of XMLRequest.
	 */
	protected XMLRequest generateXmlRequest()
	{
		return new XMLRequest(generateRequest());
	}	
	
	/**
	 * Returns the name of the response expected.
	 * 
	 * @return The name of the response associated to this request.
	 */
	public abstract String getResponseHandlerName();
}
