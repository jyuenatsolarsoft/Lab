package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.CodeDictionary;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetCodeDescriptionResponse;
import com.cms.gwt.fs.client.rpc.message.handler.ResponseHandler;
import com.cms.gwt.fs.client.rpc.xml.message.parser.CodeDictionaryParser;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;

public class ReceiveCodeDescriptionXMLResponseHandler extends XmlResponseHandler implements ResponseHandler, Instantiable 
{
	final static String XML_ELEMENT_ENTRY = "entry";
	
	protected ActionResponse parseResponse(Element rootElement) throws FSParseException 
	{
		try
		{
			// Suffix of the element tag for the code-description request. 
			final String TYPE_LIST = "TypeList";
			final String CODE_LIST = "CodeList";
					
			NodeList childList = rootElement.getChildNodes();
					
			Element element;		
			String elementTagName;
			for (int i=0; i<childList.getLength(); i++)
			{
				element = (Element)(childList.item(i));
				 elementTagName = element.getTagName();
				 
				 // An unsafe trick to recognize the element tag. 
				 if (elementTagName != null && (elementTagName.endsWith(TYPE_LIST) || elementTagName.endsWith(CODE_LIST)))
				 {
					 CodeDictionary dictionary = CodeDictionaryParser.getInstance().parse(element);
					 return new GetCodeDescriptionResponse(dictionary);
				 }					 
			}
					
			return new GetCodeDescriptionResponse(null);

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
		
}
