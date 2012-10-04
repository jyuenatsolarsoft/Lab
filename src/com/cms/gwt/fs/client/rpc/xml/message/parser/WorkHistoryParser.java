package com.cms.gwt.fs.client.rpc.xml.message.parser;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.workHistory.WorkHistory;
import com.google.gwt.xml.client.Element;

public class WorkHistoryParser extends FSModelParser 
{	 	
	static private WorkHistoryParser myself;
	
	public static final String XML_ELEMENT = "serviceTicketWorkHistory";
	
    // XML Element Names               
    private static final String XML_ELEMENT_TECHNICIAN = "technician";
    private static final String XML_ELEMENT_TECHNICIAN_DESCRIPTION = "technicianDescription";
    private static final String XML_ELEMENT_DATE = "date";
    private static final String XML_ELEMENT_TIME_ARRIVED = "timeArrived";
    private static final String XML_ELEMENT_TOTAL_HOURS_REPORTED = "totalHoursReported";
    private static final String XML_ELEMENT_MATERIAL_REPORTED = "materialReported";
    private static final String XML_ELEMENT_NUMBER_OF_ITEMS = "numberOfItems";
    private static final String XML_ELEMENT_OTHER_REPORTED = "otherReported";
    private static final String XML_ELEMENT_COUNTER_READING = "counterReading";
    private static final String XML_ELEMENT_COUNTER_DESCRIPTION = "counterDescription";
    private static final String XML_ELEMENT_MESSAGES = "Messages";	
	            	 	
	 public WorkHistory parse(Element element) throws FSParseException
	 {
		try
		{
		 	WorkHistory workHistory = new WorkHistory();
		 	
		 	workHistory.setCounterReading(parseInt(getElementValue(element, XML_ELEMENT_COUNTER_READING)));	 	
		 	workHistory.setCounterDescription(getElementValue(element, XML_ELEMENT_COUNTER_DESCRIPTION));
		 	workHistory.setTechnician(getElementValue(element, XML_ELEMENT_TECHNICIAN));
		 	workHistory.setTechnicianDescription(getElementValue(element, XML_ELEMENT_TECHNICIAN_DESCRIPTION));
		 	workHistory.setArrivalDate(createSqlDate(getElementValue(element, XML_ELEMENT_DATE)));
		 	workHistory.setArrivalTime(getElementValue(element, XML_ELEMENT_TIME_ARRIVED));
		 	workHistory.setHoursReported(parseDouble(getElementValue(element, XML_ELEMENT_TOTAL_HOURS_REPORTED)));
		 	workHistory.setMaterialReported(parseBooleanInt(getElementValue(element, XML_ELEMENT_MATERIAL_REPORTED)));
		 	workHistory.setNumberOfItems(parseInt(getElementValue(element, XML_ELEMENT_NUMBER_OF_ITEMS)));
		 	workHistory.setOtherReported(parseBooleanInt(getElementValue(element, XML_ELEMENT_OTHER_REPORTED)));
		 		 	
		 	return workHistory;
		}
		catch (Exception e)
		{
			throw new FSParseException(e);
		}		
	 }	
	 
	 public static WorkHistoryParser getInstance()
	 {
	 	if (myself == null)
	 	{
	 		myself = new WorkHistoryParser();
	 	}
	 	
	 	return myself;
	 }

	 @Override
	 public String getXmlElement() {

	 	return XML_ELEMENT;
	 }
}
