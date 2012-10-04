package com.cms.gwt.fs.client.rpc.xml.message.handler;

/**
 * Utility class to generate the XML element.
 *
 */
public class XmlGenerator {
	
    public static final String TEST_HARDCODED_ENCODING = "utf-8";
    public static final String XML_DECLARATION = "<?xml version='1.0' encoding='" + TEST_HARDCODED_ENCODING + "'?>";
	
    /**
     * Generate the start tag.
     * 
     * @param startElement element name.
     * 
     * @return the start tag.
     */
	public static String buildStartElement(String startElement)
	{
		return "<" + startElement + ">";
	}

    /**
     * Generate the element tag.
     * 
     * @param endElement element name.
     * 
     * @return the end tag.
     */	
	public static String buildEndElement(String endElement)
	{
		return "</" + endElement + ">";
	}
	

	public static String buildCDATA(String value)
	{
		if (value != null && !"".equals(value))
		{
			// escape any "]]>" in the value.
			String rval = value.replaceAll("]]>", "]]]]><![CDATA[>");
			return "<![CDATA[" + rval + "]]>";
		}
		
		return value;		
	}
}
