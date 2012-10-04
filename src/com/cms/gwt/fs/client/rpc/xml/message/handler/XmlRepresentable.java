package com.cms.gwt.fs.client.rpc.xml.message.handler;

/**
 * The class implementing this interface can be exported to XML format. 
 * 
 */
public interface XmlRepresentable {

	/**
	 * Tranform the object into an XML String
	 * 
	 * @return The object in XML format.
	 */
	String toXML();
}
