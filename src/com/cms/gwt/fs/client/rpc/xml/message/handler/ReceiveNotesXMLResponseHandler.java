package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.notes.Notes;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetNotesResponse;
import com.cms.gwt.fs.client.rpc.message.handler.ResponseHandler;
import com.cms.gwt.fs.client.rpc.xml.message.parser.NotesParser;
import com.google.gwt.xml.client.Element;

public class ReceiveNotesXMLResponseHandler extends XmlResponseHandler implements ResponseHandler, Instantiable 
{	
	final static private String XML_ELEMENT_TICKET_NUMBER = "ticketNumber";
	
	/**
	 * {@inheritDoc}
	 */
	protected ActionResponse parseResponse(Element rootElement) throws FSParseException
	{
		try
		{
			Notes notes = NotesParser.getInstance().parse(rootElement);
			notes.setTicketNumber(getElementValue(rootElement, XML_ELEMENT_TICKET_NUMBER));
			 				 
			return new GetNotesResponse(notes);				
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
