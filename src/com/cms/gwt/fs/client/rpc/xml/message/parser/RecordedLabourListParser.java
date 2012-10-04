package com.cms.gwt.fs.client.rpc.xml.message.parser;

import java.util.ArrayList;
import java.util.List;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.workHistory.RecordedLabourList;
import com.cms.gwt.fs.client.model.workHistory.RecordedLabourListEntry;
import com.cms.gwt.fs.client.rpc.xml.message.handler.ComplexElement;
import com.cms.gwt.fs.client.rpc.xml.message.handler.XmlRepresentable;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;

public class RecordedLabourListParser extends FSModelParser 
{	
	private static RecordedLabourListParser myself;
	
	final public static String XML_ELEMENT = "recordedLabourList";
		
	public RecordedLabourList parse(Element element) throws FSParseException
	{						
		try
		{
			RecordedLabourListEntryParser entryParser = RecordedLabourListEntryParser.getInstance(); 
			
			NodeList entryList = element.getElementsByTagName(RecordedLabourListEntryParser.XML_ELEMENT);
			
			RecordedLabourList entries = new RecordedLabourList();
					
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
	
	 public static RecordedLabourListParser getInstance()
	 {
	 	if (myself == null)
	 	{
	 		myself = new RecordedLabourListParser();
	 	}
	 	
	 	return myself;
	 }
	 
	 /**
	  * Serialize the TimeEntryList.
	  * 
	  * @param timeEntries
	  * @return
	  */
	 public XmlRepresentable serialize(RecordedLabourList labourList)
	 {
		List<XmlRepresentable> childElements = new ArrayList<XmlRepresentable>();
		
		for (RecordedLabourListEntry entry : labourList.getEntries())
		{
			childElements.add(RecordedLabourListEntryParser.getInstance().serialize(entry));
		}
				
		return new ComplexElement(XML_ELEMENT, childElements);				 
	 }	 
}	
