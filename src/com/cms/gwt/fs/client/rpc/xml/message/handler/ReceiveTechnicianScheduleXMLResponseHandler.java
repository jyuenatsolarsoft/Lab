package com.cms.gwt.fs.client.rpc.xml.message.handler;

import java.sql.Date;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.scheduledEvent.TechnicianSchedule;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetTechnicianScheduleResponse;
import com.cms.gwt.fs.client.rpc.message.handler.ResponseHandler;
import com.cms.gwt.fs.client.rpc.xml.message.parser.TechnicianScheduledEventParser;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;

public class ReceiveTechnicianScheduleXMLResponseHandler extends XmlResponseHandler implements ResponseHandler, Instantiable 
{	
	final static private String XML_ELEMENT_TECHNICIAN = "technician";
	final static private String XML_ELEMENT_START_DATE = "startDate";
	final static private String XML_ELEMENT_HORIZON = "horizon";
	final static private String XML_ELEMENT_SCHEDULED_EVENT_LIST = "scheduledEventList";
	
	/**
	 * {@inheritDoc}
	 */
	protected ActionResponse parseResponse(Element rootElement) throws FSParseException
	{		
		try
		{
			TechnicianSchedule schedule = new TechnicianSchedule();
			schedule.setTechnician(getElementValue(rootElement, XML_ELEMENT_TECHNICIAN));
			
			String startDateStr = getElementValue(rootElement, XML_ELEMENT_START_DATE);
			if (startDateStr != null)
			{
				schedule.setStartDate(Date.valueOf(startDateStr));
			}		
			schedule.setHorizon(getElementValue(rootElement, XML_ELEMENT_HORIZON));
							
			Element techScheduledEventListElement = getSingleElementByTagName(rootElement, XML_ELEMENT_SCHEDULED_EVENT_LIST);
			NodeList techScheduledEventNodeList = techScheduledEventListElement.getElementsByTagName(TechnicianScheduledEventParser.XML_ELEMENT);		
			
			Element techScheduledEventElement;
			for (int i=0; i<techScheduledEventNodeList.getLength(); i++)
			{
				techScheduledEventElement = (Element)techScheduledEventNodeList.item(i);
				schedule.addEvent(TechnicianScheduledEventParser.getInstance().parse(techScheduledEventElement));
			}
			
			return new GetTechnicianScheduleResponse(schedule);
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
