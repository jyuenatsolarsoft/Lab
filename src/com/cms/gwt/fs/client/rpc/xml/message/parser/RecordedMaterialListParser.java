package com.cms.gwt.fs.client.rpc.xml.message.parser;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.material.RecordedMaterialList;
import com.cms.gwt.fs.client.model.workHistory.RecordedLabourList;
import com.cms.gwt.fs.client.rpc.xml.message.handler.XmlRepresentable;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;

public class RecordedMaterialListParser extends FSModelParser 
{	
	private static RecordedMaterialListParser myself;
	
	final public static String XML_ELEMENT = "recordedMaterialList";
		
	public RecordedMaterialList parse(Element element) throws FSParseException
	{
		try
		{
			RecordedMaterialListEntryParser entryParser = RecordedMaterialListEntryParser.getInstance(); 
	
			NodeList entryList = element.getElementsByTagName(RecordedMaterialListEntryParser.XML_ELEMENT);
			
			RecordedMaterialList list = new RecordedMaterialList();
					
			for (int i=0; i<entryList.getLength(); i++)
			{
				list.addEntry(entryParser.parse((Element)entryList.item(i)));
			}		
	
			return list;
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
	
	 public static RecordedMaterialListParser getInstance()
	 {
	 	if (myself == null)
	 	{
	 		myself = new RecordedMaterialListParser();
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
//		List<XmlRepresentable> childElements = new ArrayList<XmlRepresentable>();
//		
//		for (RecordedLabourListEntry entry : labourList.getEntries())
//		{
//			childElements.add(RecordedLabourListEntryParser.getInstance().serialize(entry));
//		}
				
//		return new ComplexElement(XML_ELEMENT, childElements);
		 
		 return null;
	 }	 
}	
