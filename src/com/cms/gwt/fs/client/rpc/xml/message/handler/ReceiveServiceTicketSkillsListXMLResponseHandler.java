package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.skill.RequiredSkillsList;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetServiceTicketSkillsListResponse;
import com.cms.gwt.fs.client.rpc.message.handler.ResponseHandler;
import com.cms.gwt.fs.client.rpc.xml.message.parser.SkillsListParser;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;

/**
 * Handler to parse the RecieveServiceTicketSkillsList response.
 */
public class ReceiveServiceTicketSkillsListXMLResponseHandler extends XmlResponseHandler implements ResponseHandler, Instantiable {

	final static String XML_ELEMENT_TICKET_NUMBER = "ticketNumber";
	
	protected ActionResponse parseResponse(Element rootElement) throws FSParseException
	{
		try
		{			
			Element skillsListElement = getSingleElementByTagName(rootElement, SkillsListParser.XML_ELEMENT);		
			
			// Extract the information from the response of the data services.
			RequiredSkillsList skillsList = SkillsListParser.getInstance().parse(skillsListElement);		
			String ticketNumber = getElementValue(rootElement, XML_ELEMENT_TICKET_NUMBER);
			
			// Create a new action response.
			return GetServiceTicketSkillsListResponse.newInstance(skillsList, ticketNumber);								
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
