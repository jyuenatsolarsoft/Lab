package com.cms.gwt.fs.client.rpc.xml.message.parser;

import java.util.HashMap;
import java.util.Map;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.CodeDictionary;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;

public class CodeDictionaryParser extends FSModelParser 
{ 
	private static CodeDictionaryParser myself;
	
	private static final  String XML_ELEMENT_ENTRY = "entry";

	private static final String XML_ELEMENT_CODE = "code";
	private static final String XML_ELEMENT_DESCRIPTION = "value";
		
	public CodeDictionary parse(Element element) throws FSParseException
	{				
		try
		{		
			Map<String, String> codeDesc = new HashMap<String, String>();
			
			NodeList entryNodeList = element.getElementsByTagName(XML_ELEMENT_ENTRY);
	
			Element entryElement;
			
			for (int i=0; i<entryNodeList.getLength(); i++)
			{
				entryElement = (Element)entryNodeList.item(i);
				codeDesc.put(getElementValue(entryElement, XML_ELEMENT_CODE), 
						getElementValue(entryElement, XML_ELEMENT_DESCRIPTION));
			}
					
			// Another trick to retrieve the name of the dictionary.
			// It's okay as our team should get notified whenever there are changes on the
			// the name of the xml elements used by the data services.
			String dictionaryName = element.getParentNode().getNodeValue();
			
			return new CodeDictionary(dictionaryName, codeDesc);		
		}
		catch (Exception e)
		{
			throw new FSParseException(e);
		}		
	}
	
	public static CodeDictionaryParser getInstance()
	{
		if (myself == null)
		{
			myself = new CodeDictionaryParser();
		}
		
		return myself;
	}
	
	@Override
	public String getXmlElement() {
		
		// This parser works on all code description request.
		// No XML element name for this parser.

		return null;
	}
}