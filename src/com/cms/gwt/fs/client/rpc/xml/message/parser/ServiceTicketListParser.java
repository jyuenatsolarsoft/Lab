package com.cms.gwt.fs.client.rpc.xml.message.parser;

import java.util.ArrayList;
import java.util.List;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.ServiceTicketList;
import com.cms.gwt.fs.client.model.ServiceTicketListEntry;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;

public class ServiceTicketListParser extends FSModelParser {
	
	final public static String XML_ELEMENT = "serviceTicketList";
		
	public static ServiceTicketList parse(Element element) throws FSParseException
	{		
		try
		{
			List<ServiceTicketListEntry> entries = new ArrayList<ServiceTicketListEntry>();
			ServiceTicketListEntryParser entryParser = ServiceTicketListEntryParser.getInstance(); 
			
			NodeList entryList = element.getElementsByTagName(ServiceTicketListEntryParser.XML_ELEMENT);
					
			for (int i=0; i<entryList.getLength(); i++)
			{
				entries.add(entryParser.parse((Element)entryList.item(i)));
			}
			
			return new ServiceTicketList(entries);
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
	
	
}
