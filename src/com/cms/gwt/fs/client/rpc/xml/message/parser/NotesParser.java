package com.cms.gwt.fs.client.rpc.xml.message.parser;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.notes.Notes;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;

public class NotesParser extends FSModelParser 
{	
	private static NotesParser myself;
		
	private final static String XML_ELEMENT_PAGES = "pages";
			 	
	private final static String RESPONSE_PREFIX = "Receive";
	
	public Notes parse(Element element) throws FSParseException
	{						 				
		try
		{
			Element pagesNode = getSingleElementByTagName(element, XML_ELEMENT_PAGES);		
			NodeList pageNodeList = pagesNode.getElementsByTagName(NotesPageParser.XML_ELEMENT);
			
			// Initialize Notes
			String tagName = element.getTagName();
			String typeName = tagName.substring(RESPONSE_PREFIX.length(), tagName.length());
			Notes notes = new Notes(Notes.NotesType.get(typeName));
											
			for (int i=0; i<pageNodeList.getLength(); i++)
			{
				notes.addNotesPage(NotesPageParser.getInstance().parse((Element)pageNodeList.item(i)));			
			}
			
			return notes;
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
	public String getXmlElement() 
	{
		// This xml element depends on the notes type.
		return null;
	}
	
	 public static NotesParser getInstance()
	 {
	 	if (myself == null)
	 	{
	 		myself = new NotesParser();
	 	}
	 	
	 	return myself;
	 }	
}	
