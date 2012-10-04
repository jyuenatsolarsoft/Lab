package com.cms.gwt.fs.client.rpc.xml.message.parser;

import java.util.ArrayList;
import java.util.List;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.workHistory.TimeEntry;
import com.cms.gwt.fs.client.rpc.xml.message.handler.ComplexElement;
import com.cms.gwt.fs.client.rpc.xml.message.handler.SimpleElement;
import com.cms.gwt.fs.client.rpc.xml.message.handler.XmlRepresentable;
import com.google.gwt.xml.client.Element;

public class TimeEntryParser extends FSModelParser 
{ 
	private static TimeEntryParser myself;
	
	final public static  String XML_ELEMENT = "entry";

    public static final String XML_ELEMENT_ENTRY = "entry";
    public static final String XML_ELEMENT_SEQUENCE = "sequence";
    public static final String XML_ELEMENT_DESCRIPTION = "description";
    public static final String XML_ELEMENT_ESTIMATED_TIME = "estimatedTime";
    public static final String XML_ELEMENT_ENTERED_TIME = "enteredTime";
    public static final String XML_ELEMENT_ACTUAL_TIME = "actualTime";
    public static final String XML_ELEMENT_OVERTIME = "overtime";
    public static final String XML_ELEMENT_WARRANTY = "warranty";

        
	public TimeEntry parse(Element element) throws FSParseException
	{
		try
		{
			TimeEntry result = new TimeEntry();
			
			result.setSequence(parseInt(getElementValue(element, XML_ELEMENT_SEQUENCE)));
			result.setDescription(getElementValue(element, XML_ELEMENT_DESCRIPTION));
			result.setEstimate(parseDouble(getElementValue(element, XML_ELEMENT_ESTIMATED_TIME)));
			result.setEntered(parseDouble(getElementValue(element, XML_ELEMENT_ENTERED_TIME)));
			result.setActual(parseDouble(getElementValue(element, XML_ELEMENT_ACTUAL_TIME)));
			
			// The backend returns ovetime as a double value, not boolean value.
			// As this field is not used, we'll ignore it for now.
			//result.setOvertime(parseBooleanInt(getElementValue(element, XML_ELEMENT_OVERTIME)));
			
			result.setWarranty(determineBoolValue(getElementValue(element, XML_ELEMENT_WARRANTY)));
							
			return result;
		}
		catch (Exception e)
		{
			throw new FSParseException(e);
		}
		
	}

	
	public static TimeEntryParser getInstance()
	{
		if (myself == null)
		{
			myself = new TimeEntryParser();
		}
		
		return myself;
	}
	
	@Override
	public String getXmlElement() 
	{		
		return XML_ELEMENT;
	}
	
	 /**
	  * Serialize the Time Entry.
	  * 
	  * @param entry 
	  * @return
	  */
	 public XmlRepresentable serialize(TimeEntry entry)
	 {		
		List<XmlRepresentable> childElements = new ArrayList<XmlRepresentable>();
		
		childElements.add(new SimpleElement(XML_ELEMENT_SEQUENCE, convertToStr(entry.getSequence()))); 
		childElements.add(new SimpleElement(XML_ELEMENT_DESCRIPTION, entry.getDescription()));
		
		
		// ESTIMATED_TIME and ENTERED_TIME is not needed on JGORich.
		// childElements.add(new SimpleElement(XML_ELEMENT_ESTIMATED_TIME, convertToStr(entry.getEstimate())));
		// childElements.add(new SimpleElement(XML_ELEMENT_ENTERED_TIME, convertToStr(entry.getEntered())));
		
		childElements.add(new SimpleElement(XML_ELEMENT_ACTUAL_TIME, convertToStr(entry.getActual())));
		childElements.add(new SimpleElement(XML_ELEMENT_OVERTIME, convertToStr(entry.isOvertime())));
		childElements.add(new SimpleElement(XML_ELEMENT_WARRANTY, convertToStr(entry.isWarranty())));
		
		return new ComplexElement(XML_ELEMENT, childElements);		
	 }	
}
