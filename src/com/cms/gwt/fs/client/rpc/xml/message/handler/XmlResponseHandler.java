package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.allen_sauer.gwt.log.client.Log;
import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.exception.InvalidResponseException;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.message.Response;
import com.cms.gwt.fs.client.rpc.message.handler.ResponseHandler;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.XMLParser;
import com.google.gwt.xml.client.impl.DOMParseException;

/**
 * Base class of the XML Response Handler.
 * 
 * @see ResponseHandler
 */
public abstract class XmlResponseHandler {
	
	/** XML element name for the data request id. */
	final protected String XML_DATA_REQUEST = "DataRequestID";
	
	
	/**
	 * Retrieve a single element by tag name.
	 * 
	 * @param document The XML document object.
	 * @param elementName The name of the element.
	 * @return The element with the specified element name under the document object.
	 */
	protected Element getSingleElementByTagName(Document document, String elementName)
	{
		return getSingleElementByTagName(document.getDocumentElement(), elementName);
	}
	
	/**
	 * Retrieve a single element by tag name.
	 * 
	 * @param document The XML document object.
	 * @param elementName The name of the element.
	 * @return The element with the specified element name under the document object.
	 */
	protected Element getSingleElementByTagName(Element element, String elementName)
	{
        if (element.getElementsByTagName(elementName).getLength() > 0)
        {
            return ((Element) element.getElementsByTagName(elementName).item(0));
        }
        else
        {
            return null;
        }
	}
	

    /**
     * Helper method to retrieve element value.
     *
     * @param parentElement The parent node
     * @param elementName The element name
     *
     * @return value of the element with the specified element name under the provided parent node.
     * null if no such element exists. Empty space if element exists, but doesn't contain anything.
     */
    protected String getElementValue(Element parentElement, String elementName)
    {
    	// check if parent is null
    	if (parentElement == null) 
    	{
    		return "";
    	}
    	
        if (parentElement.getElementsByTagName(elementName).getLength() > 0)
        {
             Element targetElement = (Element) parentElement.getElementsByTagName(elementName).item(0);
            
             Node valueNode = targetElement.getFirstChild();
             
             if (valueNode != null)
             {
            	 return valueNode.getNodeValue();
             }
             else
             {
            	 // Nothing contained in the element.
            	 return "";
             }                          
        }
        else
        {
        	// No such element
            return null;
        }
    }	
    
    
    /**
     * Build the XML DOM from the response.
     * 
     * @param response response from the server.
     * 
     * @return XML DOM - Document object. 
     * 
     * @throws InvalidResponseException when the xml dom cannot be built from the response. 
     */
    protected Document buildXmlDom(Response response) throws FSParseException
    {
    	try
    	{
			String xmlResponse = (String)response.getResponseMessage();		
			return XMLParser.parse(xmlResponse);
    	}
    	catch (DOMParseException e)
    	{
    		String errorMsg = "Unable to parse the reponse received from the backend. Response = [" 
				+ (String)response.getResponseMessage()+ "]"; 
    		
    		Log.fatal(errorMsg, e);    				

    		throw new FSParseException(errorMsg, e);
    	}
    }   
    
    /**
     * Validate parameter.
     *
     * @param paramName  The parameter name.
     * @param paramValue The parameter value.
     * @throws IllegalArgumentException This method will throw IllegalArgumentException when the value is null.
     */
    protected void validateParam(String paramName, Object paramValue)
    {
        if (paramValue == null)
        {
            throw new IllegalArgumentException("Parameter " + paramName + " cannot be null.");
        }
    }
    
    /**
     * Validate the response.
     * 
     * @param response the response to be validated.
     */
    protected void validateResponse(Response response)
    {
    	validateParam("response", response);
    }
        
	public ActionResponse getActionResponse(Response response) throws InvalidResponseException
	{
		validateResponse(response);
		
		try
		{		
			Document document = buildXmlDom(response);
			return parseResponse(document.getDocumentElement());
		}
		catch (Exception e)
		{
			throw new InvalidResponseException(e);
		}
																		 				
	}    
    
    protected abstract ActionResponse parseResponse(Element rootElement) throws FSParseException;
}
