package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.workHistory.RecordedLabourList;
import com.cms.gwt.fs.client.model.workHistory.WorkHistory;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetRecordedLabourListResponse;
import com.cms.gwt.fs.client.rpc.message.handler.ResponseHandler;
import com.cms.gwt.fs.client.rpc.xml.message.parser.RecordedLabourListParser;
import com.cms.gwt.fs.client.rpc.xml.message.parser.WorkHistoryParser;
import com.google.gwt.xml.client.Element;

public class ReceiveRecordedLabourListXMLResponseHandler extends XmlResponseHandler implements ResponseHandler, Instantiable 
{
	final static private String XML_ELEMENT_TICKET_NUMBER = "ticketNumber";
	final static private String XML_ELEMENT_EVENT_ID = "eventID";
	final static private String XML_ELEMENT_TOTAL_TIME_RECORDED = "totalTimeRecorded";
	
	/**
	 * {@inheritDoc}
	 */
	protected ActionResponse parseResponse(Element rootElement) throws FSParseException	
	{
		try
		{
			Element labourListElement = getSingleElementByTagName(rootElement, RecordedLabourListParser.XML_ELEMENT);		
			RecordedLabourList labourList = RecordedLabourListParser.getInstance().parse(labourListElement);
			
			Element workHistoryElement = getSingleElementByTagName(rootElement, WorkHistoryParser.XML_ELEMENT);
			WorkHistory workHistory = WorkHistoryParser.getInstance().parse(workHistoryElement);
					
			String ticketNumber = getElementValue(rootElement, XML_ELEMENT_TICKET_NUMBER);
			int eventId = Integer.parseInt(getElementValue(rootElement, XML_ELEMENT_EVENT_ID));
			workHistory.setTicketNumber(ticketNumber);
			workHistory.setEventId(eventId);
			labourList.setTicketNumber(ticketNumber);
			labourList.setEventId(eventId);
					
			String totalTimeStr = getElementValue(rootElement, XML_ELEMENT_TOTAL_TIME_RECORDED);
			if (totalTimeStr != null && !"".equals(totalTimeStr.trim()))
			{
				double totalTime = Double.parseDouble(totalTimeStr);
				workHistory.setTotalTimeRecorded(totalTime);
			}
			 				 
			return new GetRecordedLabourListResponse(labourList, workHistory);
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
