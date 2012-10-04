package com.cms.gwt.fs.client.rpc.xml.message.handler;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a parent XML element.
 */
public class ComplexElement implements XmlRepresentable 
{
	/**
	 * The element name.
	 */
    private String name;
    
    /**
     * The child elements.
     */
    private List<XmlRepresentable> childElements;

    /**
     * Constructor
     * 
     * @param name
     * @param childElements
     */
    public ComplexElement(String name, List<XmlRepresentable> childElements)
    {      
    	this.name = name;
    	this.childElements = childElements;
    }
    
    /**
     * Constructor
     * 
     * @param name 
     */
    public ComplexElement(String name)
    {      
    	this.name = name;
    	this.childElements = new ArrayList<XmlRepresentable>();
    }    

    public String getElementName() 
    {
        return name;
    }

    public List<XmlRepresentable> getChildren()
    {
        return childElements;
    }
    
    /**
     * Add child to this element.
     * 
     * @param child
     */
    public void addChild(XmlRepresentable child)
    {
    	childElements.add(child);
    }
    
 
    /**
     * {@inheritDoc}
     */
	public String toXML() {	
		
		StringBuffer result = new StringBuffer();		
		result.append(XmlGenerator.buildStartElement(name));
		for (XmlRepresentable element : childElements)
		{
			result.append(element.toXML());
		}
		result.append(XmlGenerator.buildEndElement(name));
		return result.toString();
	}
}

