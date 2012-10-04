package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.material.RecordedMaterialList;
import com.cms.gwt.fs.client.model.workHistory.WorkHistory;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetRecordedMaterialListResponse;
import com.cms.gwt.fs.client.rpc.message.handler.ResponseHandler;
import com.cms.gwt.fs.client.rpc.xml.message.parser.RecordedMaterialListParser;
import com.cms.gwt.fs.client.rpc.xml.message.parser.WorkHistoryParser;
import com.google.gwt.xml.client.Element;

public class ReceiveRecordedMaterialListXMLResponseHandler extends XmlResponseHandler implements ResponseHandler, Instantiable 
{
	final static private String XML_ELEMENT_TICKET_NUMBER = "ticketNumber";
	final static private String XML_ELEMENT_EVENT_ID = "eventID";
	
	/**
	 * {@inheritDoc}
	 */
	protected ActionResponse parseResponse(Element rootElement) throws FSParseException
	{				
		try
		{
			Element materialListElement = getSingleElementByTagName(rootElement, RecordedMaterialListParser.XML_ELEMENT);		
			RecordedMaterialList materialList = RecordedMaterialListParser.getInstance().parse(materialListElement);
			
			Element workHistoryElement = getSingleElementByTagName(rootElement, WorkHistoryParser.XML_ELEMENT);
			WorkHistory workHistory = WorkHistoryParser.getInstance().parse(workHistoryElement);
					
			String ticketNumber = getElementValue(rootElement, XML_ELEMENT_TICKET_NUMBER);
			int eventId = Integer.parseInt(getElementValue(rootElement, XML_ELEMENT_EVENT_ID));
			workHistory.setTicketNumber(ticketNumber);
			workHistory.setEventId(eventId);
			materialList.setTicketNumber(ticketNumber);
			materialList.setEventId(eventId);
								 				
			return new GetRecordedMaterialListResponse(materialList, workHistory);
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
