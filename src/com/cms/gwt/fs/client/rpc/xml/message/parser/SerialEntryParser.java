package com.cms.gwt.fs.client.rpc.xml.message.parser;

import java.util.ArrayList;
import java.util.List;

import com.cms.gwt.fs.client.model.material.SerialEntry;
import com.cms.gwt.fs.client.rpc.xml.message.handler.ComplexElement;
import com.cms.gwt.fs.client.rpc.xml.message.handler.SimpleElement;
import com.cms.gwt.fs.client.rpc.xml.message.handler.XmlRepresentable;
import com.google.gwt.xml.client.Element;

public class SerialEntryParser extends FSModelParser 
{ 
	private static SerialEntryParser myself;
	
	final public static String XML_ELEMENT = "serialEntry";

	final private static String XML_ELEMENT_SERIAL_ENTRY_NUMBER = "serialEntryNumber";
	final private static String XML_ELEMENT_SERIAL_NUMBER = "serialNumber";
	final private static String XML_ELEMENT_SERIAL_QUANTITY = "serialQuantity";
	
	        
	public SerialEntry parse(Element element)
	{
		return new SerialEntry(
				parseInt(getElementValue(element, XML_ELEMENT_SERIAL_ENTRY_NUMBER)),
				getElementValue(element, XML_ELEMENT_SERIAL_NUMBER),
				parseDouble(getElementValue(element, XML_ELEMENT_SERIAL_QUANTITY)));						
	}	
	
	public static SerialEntryParser getInstance()
	{
		if (myself == null)
		{
			myself = new SerialEntryParser();
		}
		
		return myself;
	}
	
	@Override
	public String getXmlElement() 
	{		
		return XML_ELEMENT;
	}
	
	 /**
	  * Serialize the SerialEntry
	  * 
	  * @param entry 
	  * @return
	  */
	 public XmlRepresentable serialize(SerialEntry entry)
	 {		
		List<XmlRepresentable> childElements = new ArrayList<XmlRepresentable>();
		
		childElements.add(new SimpleElement(XML_ELEMENT_SERIAL_ENTRY_NUMBER, convertToStr(entry.getSerialEntryNumber()))); 
		childElements.add(new SimpleElement(XML_ELEMENT_SERIAL_NUMBER, entry.getSerialNumber()));
		childElements.add(new SimpleElement(XML_ELEMENT_SERIAL_QUANTITY, convertToStr(entry.getSerialQuantity())));				
		
		return new ComplexElement(XML_ELEMENT, childElements);				
	 }	
}
