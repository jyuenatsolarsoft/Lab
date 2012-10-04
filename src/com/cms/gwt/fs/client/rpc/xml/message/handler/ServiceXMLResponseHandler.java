package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.Messages;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.message.handler.ResponseHandler;
import com.cms.gwt.fs.client.rpc.xml.message.parser.MessagesParser;
import com.google.gwt.xml.client.Element;

public abstract class ServiceXMLResponseHandler extends XmlResponseHandler implements ResponseHandler 
{
	/**
	 * {@inheritDoc}
	 */
	protected ActionResponse parseResponse(Element rootElement) throws FSParseException
	{
		try
		{
			Element messagesElement = getSingleElementByTagName(rootElement, MessagesParser.XML_ELEMENT);		
			Messages msgs = MessagesParser.getInstance().parse(messagesElement);
			
			return constructResponse(msgs);
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
	
	/**
	 * Constructs the ActionResponse.
	 * 
	 * @param msgs
	 * @return
	 */
	protected abstract ActionResponse constructResponse(Messages msgs);
}
