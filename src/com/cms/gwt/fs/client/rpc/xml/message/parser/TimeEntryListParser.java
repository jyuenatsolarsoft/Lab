package com.cms.gwt.fs.client.rpc.xml.message.parser;

import java.util.ArrayList;
import java.util.List;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.workHistory.TimeEntry;
import com.cms.gwt.fs.client.model.workHistory.TimeEntryList;
import com.cms.gwt.fs.client.rpc.xml.message.handler.ComplexElement;
import com.cms.gwt.fs.client.rpc.xml.message.handler.XmlRepresentable;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;

public class TimeEntryListParser extends FSModelParser 
{	
	private static TimeEntryListParser myself;
	
	final public static String XML_ELEMENT = "recordedLabourTimeEntries";
		
	public TimeEntryList parse(Element element) throws FSParseException
	{				
		try
		{
			TimeEntryParser entryParser = TimeEntryParser.getInstance(); 
			
			NodeList entryList = element.getElementsByTagName(TimeEntryParser.XML_ELEMENT);
			
			TimeEntryList entries = new TimeEntryList();
					
			for (int i=0; i<entryList.getLength(); i++)
			{
				entries.addEntry(entryParser.parse((Element)entryList.item(i)));
			}
			
			return entries;
		}
		catch (FSParseException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			throw new FSParseException(e);
		}
	}

	@Override
	public String getXmlElement() {
 
		return XML_ELEMENT;
	}
	
	 public static TimeEntryListParser getInstance()
	 {
	 	if (myself == null)
	 	{
	 		myself = new TimeEntryListParser();
	 	}
	 	
	 	return myself;
	 }
	 
	 /**
	  * Serialize the TimeEntryList.
	  * 
	  * @param timeEntries
	  * @return
	  */
	 public XmlRepresentable serialize(TimeEntryList timeEntries)
	 {
		List<XmlRepresentable> childElements = new ArrayList<XmlRepresentable>();
		
		for (TimeEntry entry : timeEntries.getEntries())
		{
			childElements.add(TimeEntryParser.getInstance().serialize(entry));
		}
				
		return new ComplexElement(XML_ELEMENT, childElements);				 
	 }
}	
