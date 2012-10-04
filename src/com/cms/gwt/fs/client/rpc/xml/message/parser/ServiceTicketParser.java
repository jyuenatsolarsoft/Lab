package com.cms.gwt.fs.client.rpc.xml.message.parser;

import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.factory.CodeDictionaryFactory;
import com.cms.gwt.fs.client.model.ServiceTicket;
import com.google.gwt.core.client.GWT;
import com.google.gwt.xml.client.Element;

public class ServiceTicketParser extends FSModelParser {

	static private ServiceTicketParser myself;
	
    public static final String XML_ELEMENT = "serviceTicket";
    
    private static final String XML_ELEMENT_TICKET_NUMBER = "ticketNumber";
    private static final String XML_ELEMENT_DATE_OPENED = "dateOpened";
    private static final String XML_ELEMENT_TIME_OPENED = "timeOpened";
    private static final String XML_ELEMENT_SERVICE_TYPE = "serviceType";
    private static final String XML_ELEMENT_SERVICE_ITEM_TYPE = "serviceItemType";
    private static final String XML_ELEMENT_SERVICE_ITEM = "serviceItem";
    private static final String XML_ELEMENT_SERVICE_ITEM_DESCRIPTION = "serviceItemDescription";
    private static final String XML_ELEMENT_MAIN_CONTACT = "mainContact";
    private static final String XML_ELEMENT_MAIN_CONTACT_NUMBER = "mainContactNumber";
    private static final String XML_ELEMENT_SITE_CONTACT = "siteContact";
    private static final String XML_ELEMENT_SITE_CONTACT_NUMBER = "siteContactNumber";
    private static final String XML_ELEMENT_SECOND_NUMBER = "secondNumber";
    private static final String XML_ELEMENT_CONTACT_EMAIL = "contactEmail";
    private static final String XML_ELEMENT_COMPLAINT = "complaint";
    private static final String XML_ELEMENT_REPEAT_ISSUE = "repeatIssue";
    private static final String XML_ELEMENT_PRIORITY_CODE = "priorityCode";
    private static final String XML_ELEMENT_SERVICE_PROCEDURE = "serviceProcedure";
    private static final String XML_ELEMENT_SUBJECT = "subject";
    private static final String XML_ELEMENT_SCHEDULED_DATE = "scheduledDate";
    private static final String XML_ELEMENT_START_TIME = "startTime";
    private static final String XML_ELEMENT_EFFORT = "effort";
    private static final String XML_ELEMENT_CONFIRMATION_REQUIRED = "confirmationRequired";
    private static final String XML_ELEMENT_CONFIRMED = "confirmed";
    private static final String XML_ELEMENT_ASSIGNED_TO = "assignedTo";
    private static final String XML_ELEMENT_RESPOND_BY_DATE = "respondByDate";
    private static final String XML_ELEMENT_RESPOND_BY_TIME = "respondByTime";
    private static final String XML_ELEMENT_STATUS = "status";
    private static final String XML_ELEMENT_ESTIMATED_COST = "estimatedCost";
    private static final String XML_ELEMENT_ESTIMATED_COST_UOM = "estimatedCostUOM";
    private static final String XML_ELEMENT_CUSTOMER_PO = "customerPO";
    private static final String XML_ELEMENT_CUSTOMER = "customer";
    private static final String XML_ELEMENT_PREVIOUS_TICKET = "previousTicket";
    
    //private static final String XML_ELEMENT_FOR_UPDATE = "forUpdate";
    
	private TextConstants txtConsts = (TextConstants) GWT.create(TextConstants.class);
	
	public ServiceTicket parse(Element element) throws FSParseException
	{
		try
		{
			ServiceTicket ticket = new ServiceTicket();
			 							    
		    ticket.setTicketNumber(getElementValue(element, XML_ELEMENT_TICKET_NUMBER));
		    ticket.setDateOpened(createSqlDate(getElementValue(element, XML_ELEMENT_DATE_OPENED)));
		    ticket.setTimeOpened(getElementValue(element, XML_ELEMENT_TIME_OPENED));
		    
		    ticket.setServiceType(getElementValue(element, XML_ELEMENT_SERVICE_TYPE));
		    
		    // Service Type description not availabe from the backend. It's hardcoded in 
		    // the client properties file.
		    ticket.setServiceTypeDescription(
		    		getServiceTypeDesc(getElementValue(element, XML_ELEMENT_SERVICE_TYPE)));
		    
		    ticket.setServiceItemType(getElementValue(element, XML_ELEMENT_SERVICE_ITEM_TYPE));	    	    
		    ticket.setServiceItem(getElementValue(element, XML_ELEMENT_SERVICE_ITEM)); 
		    ticket.setServiceItemDescription(getElementValue(element, XML_ELEMENT_SERVICE_ITEM_DESCRIPTION));
		    ticket.setMainContact(getElementValue(element, XML_ELEMENT_MAIN_CONTACT));
		    ticket.setMainContactNumber(getElementValue(element, XML_ELEMENT_MAIN_CONTACT_NUMBER));
		    ticket.setSiteContact(getElementValue(element, XML_ELEMENT_SITE_CONTACT));
		    ticket.setSiteContactNumber((getElementValue(element, XML_ELEMENT_SITE_CONTACT_NUMBER)));
		    ticket.setSecondNumber(getElementValue(element, XML_ELEMENT_SECOND_NUMBER));
		    ticket.setContactEmail(getElementValue(element, XML_ELEMENT_CONTACT_EMAIL));
		    
		    ticket.setComplaint(
		    		getDesc(CodeDictionaryFactory.COMPLAIN_CODE, element, XML_ELEMENT_COMPLAINT));
		    	    
		    ticket.setRepeatIssue(new Boolean(getElementValue(element, XML_ELEMENT_REPEAT_ISSUE)));
		    
		    ticket.setPriorityCode(
		    		getDesc(CodeDictionaryFactory.PRIORITY_CODE, element, XML_ELEMENT_PRIORITY_CODE));
		    
		    ticket.setServiceProcedure(getElementValue(element, XML_ELEMENT_SERVICE_PROCEDURE));
		    ticket.setSubject(getElementValue(element, XML_ELEMENT_SUBJECT));
		    ticket.setScheduledDate(createSqlDate(getElementValue(element, XML_ELEMENT_SCHEDULED_DATE)));
		    ticket.setStartTime(getElementValue(element, XML_ELEMENT_START_TIME));
		    ticket.setEffort(parseDouble(getElementValue(element, XML_ELEMENT_EFFORT)));
		    	    
		    ticket.setConfirmationRequired(parseBooleanInt(getElementValue(element, XML_ELEMENT_CONFIRMATION_REQUIRED)));
		    ticket.setConfirmed(parseBooleanInt(getElementValue(element, XML_ELEMENT_CONFIRMED)));
		    
		    ticket.setAssignedTo(
		    		getDesc(CodeDictionaryFactory.TECHNICAN_CODE, element, XML_ELEMENT_ASSIGNED_TO));
		    
		    ticket.setRespondByDate(createSqlDate(getElementValue(element, XML_ELEMENT_RESPOND_BY_DATE)));
		    ticket.setRespondByTime(getElementValue(element, XML_ELEMENT_RESPOND_BY_TIME));
		    
		    ticket.setStatus(
		    		getDesc(CodeDictionaryFactory.HEADER_STATUS_TYPE, element, XML_ELEMENT_STATUS));	    
		    
		    ticket.setEstimatedCost(parseDouble(getElementValue(element, XML_ELEMENT_ESTIMATED_COST)));	    
		    ticket.setEstimatedCostUOM(getElementValue(element, XML_ELEMENT_ESTIMATED_COST_UOM));
		    ticket.setCustomerPO(getElementValue(element, XML_ELEMENT_CUSTOMER_PO)); 
		    ticket.setCustomer(getElementValue(element, XML_ELEMENT_CUSTOMER));
		    ticket.setPreviousTicket((getElementValue(element, XML_ELEMENT_PREVIOUS_TICKET))); 
		    //ticket.setForUpdate(getElementValue(element, XML_ELEMENT_FOR_UPDATE));
		    	    				
		    ticket.setMessages(MessagesParser.getInstance().parse(getSingleElementByTagName(element, MessagesParser.XML_ELEMENT)));
		    
			return ticket;
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
	
	public static ServiceTicketParser getInstance()
	{
		if (myself == null)
		{
			myself = new ServiceTicketParser();
		}
		
		return myself;
	}

	@Override
	public String getXmlElement() {

		return XML_ELEMENT;
	}
	
	/**
	 * Retrieve the service type description from the properties file.
	 * 
	 * @return 
	 */
	private String getServiceTypeDesc(String type)
	{
		final String TYPE1 = "1";
		final String TYPE2 = "2";
		
		String desc = "";
		
		if (type != null && TYPE1.equals(type.trim()))
		{
			return txtConsts.TicketHeaderServiceType1(); 
		}
		else if (type != null && TYPE2.equals(type.trim()))
		{
			return txtConsts.TicketHeaderServiceType2();
		}
		else if (type != null && !"".equals(type.trim()))
		{
			return type + " " + txtConsts.DescNotAvail();
		}
		
		return desc;
	}
	
}
