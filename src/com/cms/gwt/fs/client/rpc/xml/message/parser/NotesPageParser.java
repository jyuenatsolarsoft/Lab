package com.cms.gwt.fs.client.rpc.xml.message.parser;

import java.util.ArrayList;
import java.util.List;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.notes.NotesLine;
import com.cms.gwt.fs.client.notes.NotesPage;
import com.cms.gwt.fs.client.rpc.xml.message.handler.ComplexElement;
import com.cms.gwt.fs.client.rpc.xml.message.handler.SimpleElement;
import com.cms.gwt.fs.client.rpc.xml.message.handler.XmlRepresentable;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;

public class NotesPageParser extends FSModelParser 
{	
	private static NotesPageParser myself;
		
	public final static String XML_ELEMENT = "page";
		
	private final static String XML_ELEMENT_PAGE_NUMBER = "PageNumber"; 
	private final static String XML_ELEMENT_LINES = "Lines";
	private final static String XML_ELEMENT_LINE = "Line";
	private final static String XML_ELEMENT_LINE_NUMBER = "LineNumber";
	private final static String XML_ELEMENT_TEXT = "Text";			 
			
	public NotesPage parse(Element element) throws FSParseException
	{						 						
		try
		{			
			Element linesElement;		
			Element lineElement;
			Element textElement;
			int lineNumber;
			String lineText;
			int pageNumber;
			NotesPage page;
											
			pageNumber = parseInt(getElementValue(element, XML_ELEMENT_PAGE_NUMBER));
			linesElement = getSingleElementByTagName(element, XML_ELEMENT_LINES);			
			
			page = new NotesPage(pageNumber);
			
			NodeList linesNodeList = linesElement.getChildNodes();
			
			for (int j=0; j<linesNodeList.getLength(); j++)
			{
				lineElement = (Element)linesNodeList.item(j++);
				textElement = (Element)linesNodeList.item(j);
				
				lineNumber = parseInt(getElementValue(lineElement));
				lineText = getElementValue(textElement);
				
				page.addLine(new NotesLine(lineText, lineNumber));
			}		
			
			return page;
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
		return XML_ELEMENT;
	}
	
	 public static NotesPageParser getInstance()
	 {
	 	if (myself == null)
	 	{
	 		myself = new NotesPageParser();
	 	}
	 	
	 	return myself;
	 }	
	 
	 /**
	  * Serialize the NotesPage.
	  * 
	  * @param event
	  * @return
	  */
	 public XmlRepresentable serialize(NotesPage page)
	 {		
		List<XmlRepresentable> childElements = new ArrayList<XmlRepresentable>();
		
		childElements.add(new SimpleElement(XML_ELEMENT_PAGE_NUMBER, convertToStr(page.getPageNumber())));
						
		ComplexElement linesElement = new ComplexElement(XML_ELEMENT_LINES);
		ComplexElement lineElement;
				
		for (NotesLine line : page.getLines())
		{
			lineElement = new ComplexElement(XML_ELEMENT_LINE);
			
			lineElement.addChild(new SimpleElement(XML_ELEMENT_LINE_NUMBER, convertToStr(line.getLineNumber())));
			lineElement.addChild(new SimpleElement(XML_ELEMENT_TEXT, line.getLineText()));
			
			linesElement.addChild(lineElement);
		}
		
		childElements.add(linesElement);
				
		return new ComplexElement(XML_ELEMENT, childElements);		
	 }	 
}	
