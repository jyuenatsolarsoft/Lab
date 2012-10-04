package com.cms.gwt.fs.client.rpc.xml.message.handler;

/**
 * Represent an element that contains a name and value pair only. 
 * 
 */
public class SimpleElement implements XmlRepresentable {

    private String elementName;
    private String elementValue;

    public SimpleElement(String elementName, String elementValue)
    {
        this.elementName = elementName;
        this.elementValue = elementValue;
    }

    public String getElementName()
    {
        return elementName;
    }

    public String getElementValue()
    {
        return elementValue;
    }

    public void setElementName(String elementName)
    {
        this.elementName = elementName;
    }

    public void setElementValue(String elementValue)
    {
        this.elementValue = elementValue;
    }

    public String toString()
    {
        return "SimpleElement: " + elementName + " - " + elementValue;
    }
    
	public String toXML() {
		// 
		if (elementValue != null)
		{
			return XmlGenerator.buildStartElement(elementName) + XmlGenerator.buildCDATA(elementValue) + XmlGenerator.buildEndElement(elementName);
		}
		else
		{
			return "";
		}		
	}
	
}
