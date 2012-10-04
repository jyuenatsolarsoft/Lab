package com.cms.gwt.fs.client.rpc.xml.message.parser;

import java.util.ArrayList;
import java.util.List;

import com.cms.gwt.fs.client.model.material.LotEntry;
import com.cms.gwt.fs.client.rpc.xml.message.handler.ComplexElement;
import com.cms.gwt.fs.client.rpc.xml.message.handler.SimpleElement;
import com.cms.gwt.fs.client.rpc.xml.message.handler.XmlRepresentable;
import com.google.gwt.xml.client.Element;

public class LotEntryParser extends FSModelParser 
{ 
	private static LotEntryParser myself;
	
	final public static String XML_ELEMENT = "LotEntry";

	final private static String XML_ELEMENT_LOT_ENTRY_NUMBER = "lotEntryNumber";
	final private static String XML_ELEMENT_LOT_NUMBER = "lotNumber";
	final private static String XML_ELEMENT_LOT_QUANTITY = "lotQuantity";
	
	        
	public LotEntry parse(Element element)
	{
		return new LotEntry(
				parseInt(getElementValue(element, XML_ELEMENT_LOT_ENTRY_NUMBER)),
				getElementValue(element, XML_ELEMENT_LOT_NUMBER),
				parseDouble(getElementValue(element, XML_ELEMENT_LOT_QUANTITY)));						
	}	
	
	public static LotEntryParser getInstance()
	{
		if (myself == null)
		{
			myself = new LotEntryParser();
		}
		
		return myself;
	}
	
	@Override
	public String getXmlElement() 
	{		
		return XML_ELEMENT;
	}
	
	 /**
	  * Serialize the LotEntry
	  * 
	  * @param entry 
	  * @return
	  */
	 public XmlRepresentable serialize(LotEntry entry)
	 {		
		List<XmlRepresentable> childElements = new ArrayList<XmlRepresentable>();
		
		childElements.add(new SimpleElement(XML_ELEMENT_LOT_ENTRY_NUMBER, convertToStr(entry.getLotEntryNumber()))); 
		childElements.add(new SimpleElement(XML_ELEMENT_LOT_NUMBER, entry.getLotNumber()));
		childElements.add(new SimpleElement(XML_ELEMENT_LOT_QUANTITY, convertToStr(entry.getLotQuantity())));				
		
		return new ComplexElement(XML_ELEMENT, childElements);				
	 }	
}
