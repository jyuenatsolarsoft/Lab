package com.cms.gwt.fs.client.rpc.xml.message;

import java.util.ArrayList;
import java.util.List;

import com.cms.gwt.fs.client.rpc.xml.message.handler.SimpleElement;
import com.cms.gwt.fs.client.rpc.xml.message.handler.XmlGenerator;
import com.cms.gwt.fs.client.rpc.xml.message.handler.XmlRepresentable;

/**
 * Represents data client request used in the data services.
 *
 */
public class ClientDataRequest implements XmlRepresentable {

	/** Parent element required for all client data request. */	
	final private String XML_CLIENT_DATA_REQUEST = "CMS_ClientDataRequest";
	
	/** Paraent element identify the request id. */
	final private String XML_REQUEST_ID = "RequestID";
	
	/** 
	 * Keep track of the request id. This will be incremented by 1 for after 
	 * a request is sent to the server.
	 */	
	static private int requestId = 1;
	
	/**
	 * Contains the request parameters.
	 */
	private List<XmlRepresentable> subElements = new ArrayList<XmlRepresentable>();
	
	/**
	 * Name of this request. 
	 */
	private String requestName;
	
	/**
	 * Constructor.
	 * 
	 * @param requestName
	 */
	public ClientDataRequest(String requestName)
	{
		this.requestName = requestName;
	}
	
	public String toString()
	{
		return null;
	}
			
	public String getRequestName() {
		return requestName;
	}

	public void setRequestName(String elementName) {
		this.requestName = elementName;
	}


	public List<XmlRepresentable> getSubElements() {
		return subElements;
	}

	public void addElement(XmlRepresentable element)
	{
		subElements.add(element);
	}
	
	public void insertElement(int index, XmlRepresentable element)
	{
		subElements.add(index, element);
	}


	public void setSubElements(List<XmlRepresentable> subElements) {
		this.subElements = subElements;
	}

	/**
	 * Build the xml request based on the information stored in this request instance.
	 * 
	 * @return request in XML String.
	 */
	private String buildXml()
	{
		
		
		// Per Mike V., the two parameters below (RequestPrioerity and RequireMetaData)
		// are not really used by both the backend and the client. 
		//
		// For now, we will follow JGORich client and put these magic values
		// in the request.
		insertElement(0, new SimpleElement("RequireMetaData", "false"));
		insertElement(0, new SimpleElement("RequestPriority", "5"));
		
		insertElement(0, new SimpleElement(XML_REQUEST_ID, Integer.toString(requestId++)));
		
		StringBuffer result = new StringBuffer();
		result.append(XmlGenerator.XML_DECLARATION);
		result.append(XmlGenerator.buildStartElement(XML_CLIENT_DATA_REQUEST));	
		result.append(XmlGenerator.buildStartElement(getRequestName()));
		for (XmlRepresentable element : getSubElements())
		{
			result.append(element.toXML());
		}
		result.append(XmlGenerator.buildEndElement(getRequestName()));
		result.append(XmlGenerator.buildEndElement(XML_CLIENT_DATA_REQUEST));
		return result.toString();
	}

	/**
	 * Export the request in XML String.
	 */
	public String toXML() {
		return buildXml();
	}	



}
