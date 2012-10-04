package com.cms.gwt.fs.client.rpc.xml.message.parser;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.scheduledEvent.ScheduledEventList;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;

public class ScheduleListParser extends FSModelParser 
{
	
	private static ScheduleListParser myself;
	
	final public static String XML_ELEMENT = "scheduleList";
		
	public ScheduledEventList parse(Element element) throws FSParseException
	{				
		try
		{
			ScheduleListEntryParser entryParser = ScheduleListEntryParser.getInstance(); 
			
			NodeList entryList = element.getElementsByTagName(ScheduleListEntryParser.XML_ELEMENT);
			
			ScheduledEventList entries = new ScheduledEventList();
					
			for (int i=0; i<entryList.getLength(); i++)
			{
				entries.add(entryParser.parse((Element)entryList.item(i)));
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
	
	 public static ScheduleListParser getInstance()
	 {
	 	if (myself == null)
	 	{
	 		myself = new ScheduleListParser();
	 	}
	 	
	 	return myself;
	 }	
}	
