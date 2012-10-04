package com.cms.gwt.fs.client.rpc.xml.message.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.Message;
import com.cms.gwt.fs.client.model.Message.MessageType;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;

/**
 * Parser for the Message element.
 * 
 * @see Message
 *
 */
public class MessageParser extends FSModelParser 
{	
	static private MessageParser myself;
	
	final static public String XML_ELEMENT = "Message";
	
	final static private String XML_ELEMENT_KEY = "Key";
	final static private String XML_ELEMENT_ELEMENT = "Element";
	final static private String XML_ELEMENT_ELEMENTS = "Elements";
	final static private String XML_ELEMENT_MESSAGE_ID = "MessageID";
	final static private String XML_ELEMENT_MESSAGE_TYPE = "MessageType";
	final static private String XML_ELEMENT_MESSAGE_TEXT = "MessageText";

	public Message parse(Element element) throws FSParseException
	{		
		try
		{
			Message msg = new Message();
			
			// Parse the elements in the Message element.
			Map<String, String> keys = new HashMap<String, String>();
			Element keysNode = getSingleElementByTagName(element, XML_ELEMENT_KEY);
			
			if (keysNode != null)
			{
				NodeList keysNodeList = keysNode.getChildNodes();		
				for (int i=0; i<keysNodeList.getLength(); i++)
				{
					keys.put(((Element)keysNodeList.item(i)).getTagName(), 
							getElementValue(((Element)keysNodeList.item(i))));
				}
				msg.setKeys(keys);
			}
			
			// Parse the elements in the Message element.
			List<String> elements = new ArrayList<String>();		
			Element elementsNode = getSingleElementByTagName(element, XML_ELEMENT_ELEMENTS);		
			NodeList elementNodeList = elementsNode.getElementsByTagName(XML_ELEMENT_ELEMENT);		
			for (int i=0; i<elementNodeList.getLength(); i++)
			{
				elements.add(getElementValue((Element)elementNodeList.item(0)));
			}				
			msg.setElements(elements);
			
					
			msg.setMessageId(getElementValue(element, XML_ELEMENT_MESSAGE_ID));
			msg.setMessageType(MessageType.get(getElementValue(element, XML_ELEMENT_MESSAGE_TYPE)));
			msg.setMessageText(getElementValue(element, XML_ELEMENT_MESSAGE_TEXT));
					
			return msg;
		}
		catch (Exception e)
		{
			throw new FSParseException(e);
		} 
	}	
	
		
	static public MessageParser getInstance()
	{
		if (myself == null)
		{
			myself = new MessageParser();
		}
		
		return myself;
	}

	@Override
	public String getXmlElement() {

		return XML_ELEMENT;
	}	
}
