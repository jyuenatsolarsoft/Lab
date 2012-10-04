package com.cms.gwt.fs.client.rpc.xml.message.parser;

import java.util.ArrayList;
import java.util.List;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.Message;
import com.cms.gwt.fs.client.model.Messages;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;

/**
 * XML DOM parser for the Messages element.
 * 
 * @see Message
 *
 */
public class MessagesParser extends FSModelParser {
	
	static private MessagesParser myself;
	
	final static public String XML_ELEMENT = "Messages";
	
	public Messages parse(Element element) throws FSParseException
	{
		if (element == null)
		{
			return null;
		}
				
		try
		{		
			List<Message> messages = new ArrayList<Message>();
			MessageParser messageParser = MessageParser.getInstance(); 
			
			NodeList messageNodeList = element.getElementsByTagName(MessageParser.XML_ELEMENT);
					
			for (int i=0; i<messageNodeList.getLength(); i++)
			{
				messages.add(messageParser.parse((Element)messageNodeList.item(i)));
			}
							
			return new Messages(messages);
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
	
	private MessagesParser() {};
	
	public static MessagesParser getInstance()
	{
		if (myself == null)
		{
			myself = new MessagesParser();
		}
		
		return myself;
	}

	@Override
	public String getXmlElement() {
		return XML_ELEMENT;
	}
}
