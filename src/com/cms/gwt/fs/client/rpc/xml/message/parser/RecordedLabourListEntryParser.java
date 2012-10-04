package com.cms.gwt.fs.client.rpc.xml.message.parser;

import java.util.ArrayList;
import java.util.List;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.workHistory.RecordedLabourListEntry;
import com.cms.gwt.fs.client.rpc.xml.message.handler.ComplexElement;
import com.cms.gwt.fs.client.rpc.xml.message.handler.SimpleElement;
import com.cms.gwt.fs.client.rpc.xml.message.handler.XmlRepresentable;
import com.google.gwt.xml.client.Element;

public class RecordedLabourListEntryParser extends FSModelParser 
{ 
	private static RecordedLabourListEntryParser myself;
	
	final public static  String XML_ELEMENT = "entry";

    private static final String XML_ELEMENT_SEQUENCE = "sequence";
    private static final String XML_ELEMENT_DESCRIPTION = "description";
    private static final String XML_ELEMENT_LINE = "line";
    private static final String XML_ELEMENT_TIME = "time";
    private static final String XML_ELEMENT_OVERTIME = "overtime";
    private static final String XML_ELEMENT_WARRANTY = "warranty";
    private static final String XML_ELEMENT_POSTED = "posted";
        
	public RecordedLabourListEntry parse(Element element) throws FSParseException
	{
		try
		{
			RecordedLabourListEntry result = new RecordedLabourListEntry();
			
			result.setSequence(parseInt(getElementValue(element, XML_ELEMENT_SEQUENCE)));
			result.setDescription(getElementValue(element, XML_ELEMENT_DESCRIPTION));
			result.setLine(parseInt(getElementValue(element, XML_ELEMENT_LINE)));
			result.setTime(parseDouble(getElementValue(element, XML_ELEMENT_TIME)));		
			result.setOvertime(determineBoolValue(getElementValue(element, XML_ELEMENT_OVERTIME)));
			result.setWarranty(determineBoolValue((getElementValue(element, XML_ELEMENT_WARRANTY))));
			result.setPosted(parseBooleanYN(getElementValue(element, XML_ELEMENT_POSTED)));
					
			return result;
		}
		catch (Exception e)
		{
			throw new FSParseException(e);
		}
	}
	
			
	public static RecordedLabourListEntryParser getInstance()
	{
		if (myself == null)
		{
			myself = new RecordedLabourListEntryParser();
		}
		
		return myself;
	}
	
	@Override
	public String getXmlElement() 
	{		
		return XML_ELEMENT;
	}
	
	 /**
	  * Serialize the recorded labour.
	  * 
	  * @param entry 
	  * @return
	  */
	 public XmlRepresentable serialize(RecordedLabourListEntry entry)
	 {		
		List<XmlRepresentable> childElements = new ArrayList<XmlRepresentable>();
		
		childElements.add(new SimpleElement(XML_ELEMENT_SEQUENCE, convertToStr(entry.getSequence()))); 
		childElements.add(new SimpleElement(XML_ELEMENT_LINE, convertToStr(entry.getLine())));
		childElements.add(new SimpleElement(XML_ELEMENT_TIME, convertToStr(entry.getTime())));						
		childElements.add(new SimpleElement(XML_ELEMENT_OVERTIME, convertToStr(entry.isOvertime())));
		childElements.add(new SimpleElement(XML_ELEMENT_WARRANTY, convertToStr(entry.isWarranty())));
		
		return new ComplexElement(XML_ELEMENT, childElements);		
	 }	
}
