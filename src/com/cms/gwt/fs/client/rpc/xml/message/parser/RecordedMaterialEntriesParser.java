package com.cms.gwt.fs.client.rpc.xml.message.parser;

import java.util.ArrayList;
import java.util.List;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.material.RecordedMaterialEntries;
import com.cms.gwt.fs.client.model.material.RecordedMaterialEntriesEntry;
import com.cms.gwt.fs.client.rpc.xml.message.handler.ComplexElement;
import com.cms.gwt.fs.client.rpc.xml.message.handler.XmlRepresentable;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;

public class RecordedMaterialEntriesParser extends FSModelParser 
{	
	private static RecordedMaterialEntriesParser myself;
	
	final public static String XML_ELEMENT = "recordedMaterialEntries";
		
	public RecordedMaterialEntries parse(Element element) throws FSParseException
	{				
		try
		{
			RecordedMaterialEntriesEntryParser entryParser = RecordedMaterialEntriesEntryParser.getInstance(); 
	
			NodeList entryList = element.getElementsByTagName(RecordedMaterialEntriesEntryParser.XML_ELEMENT);
	
			RecordedMaterialEntries entries = new RecordedMaterialEntries();
			
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
	
	 public static RecordedMaterialEntriesParser getInstance()
	 {
	 	if (myself == null)
	 	{
	 		myself = new RecordedMaterialEntriesParser();
	 	}
	 	
	 	return myself;
	 }
	 
	 /**
	  * Serialize the RecordedMaterialEntriies.
	  * 
	  * @param timeEntries
	  * @return
	  */
	 public XmlRepresentable serialize(RecordedMaterialEntries materialEntries)
	 {
		List<XmlRepresentable> childElements = new ArrayList<XmlRepresentable>();
		
		for (RecordedMaterialEntriesEntry entry : materialEntries.getEntries())
		{
			childElements.add(RecordedMaterialEntriesEntryParser.getInstance().serialize(entry));
		}
		
		return new ComplexElement(XML_ELEMENT, childElements);
	 }	 
}	
