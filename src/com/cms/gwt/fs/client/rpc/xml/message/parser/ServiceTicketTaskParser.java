package com.cms.gwt.fs.client.rpc.xml.message.parser;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.factory.CodeDictionaryFactory;
import com.cms.gwt.fs.client.model.details.ServiceDetail;
import com.google.gwt.xml.client.Element;

public class ServiceTicketTaskParser extends FSModelParser 
{	 	
	static private ServiceTicketTaskParser myself;
	
	public static final String XML_ELEMENT = "serviceTicketTask";
	
    // XML Element Names
    private static final String XML_ELEMENT_SEQUENCE = "sequence";
    private static final String XML_ELEMENT_FROM_PROCEDURE = "fromProcedure";
    private static final String XML_ELEMENT_WORK_CODE = "workCode";
    private static final String XML_ELEMENT_DESCRIPTION1 = "descriptionLine1";
    private static final String XML_ELEMENT_DESCRIPTION2 = "descriptionLine2";
    private static final String XML_ELEMENT_DESCRIPTION3 = "descriptionLine3";
    private static final String XML_ELEMENT_MANPOWER = "manpower";
    private static final String XML_ELEMENT_TIME_ESTIMATE = "timeEstimate";
    private static final String XML_ELEMENT_WARRANTY_TASK = "warrantyTask";
    private static final String XML_ELEMENT_STATUS = "status";
//    private static final String XML_ELEMENT_STATUS_DESCRIPTION = "statusDescription";
//    private static final String XML_ELEMENT_MESSAGES = "Messages";
//    private static final String XML_ELEMENT_SERVICE_PROCEDURE = "serviceProcedure";	
			            	 
	public ServiceDetail parse(Element element) throws FSParseException
	{				 
		try
		{
			ServiceDetail detail = new ServiceDetail();
			
			detail.setSequence(parseInt(getElementValue(element, XML_ELEMENT_SEQUENCE)));
			detail.setFromProcedure(getElementValue(element, XML_ELEMENT_FROM_PROCEDURE));		
			detail.setWorkCode(getElementValue(element, XML_ELEMENT_WORK_CODE));
			
			detail.setWorkCodeDescription(
					getDesc(CodeDictionaryFactory.getCodeDictionary(CodeDictionaryFactory.WORK_CODE),
					getElementValue(element, XML_ELEMENT_WORK_CODE)));
			
			detail.setDescriptionLine1(getElementValue(element, XML_ELEMENT_DESCRIPTION1));
			detail.setDescriptionLine2(getElementValue(element, XML_ELEMENT_DESCRIPTION2));
			detail.setDescriptionLine3(getElementValue(element, XML_ELEMENT_DESCRIPTION3));
			detail.setManpower(parseDouble(getElementValue(element, XML_ELEMENT_MANPOWER)));
			detail.setTimeEstimate(parseDouble(getElementValue(element, XML_ELEMENT_TIME_ESTIMATE)));
			detail.setWarrantyTask(determineBoolValue(getElementValue(element, XML_ELEMENT_WARRANTY_TASK)));
			detail.setStatus(getElementValue(element, XML_ELEMENT_STATUS));
			
			detail.setStatusDescription(
					getDesc(CodeDictionaryFactory.TASK_STATUS_TYPE, element, XML_ELEMENT_STATUS));
				 			 	 			
			return detail;
		}
		catch (Exception e)
		{
			throw new FSParseException(e);
		}
	}	
	

	public static ServiceTicketTaskParser getInstance()
	{
		if (myself == null)
		{
			myself = new ServiceTicketTaskParser();
		}
		 	
		return myself;
	}

	 @Override
	 public String getXmlElement() {

	 	return XML_ELEMENT;
	 }
}	 