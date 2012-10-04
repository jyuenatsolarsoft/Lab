package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.action.Action;
import com.cms.gwt.fs.client.rpc.action.GetServiceTicketList;
import com.cms.gwt.fs.client.rpc.message.Request;
import com.cms.gwt.fs.client.rpc.message.Response;
import com.cms.gwt.fs.client.rpc.message.handler.RequestBuilder;

public class GetServiceTicketListXMLRequestBuilder extends XmlRequestBuilder implements RequestBuilder, Instantiable {

	final private String XML_REQUEST_NAME = "getServiceTicketList";	 
	final private String XML_TICKET_NUM = "ticketNumber";
	final private String XML_SERIAL_NUM = "serialnumber"; 
	final private String XML_CUST_ID = "customerID";
	final private String XML_PART_NUM = "partnumber";
	final private String XML_TECHNICIAN = "technician";
	final private String XML_STATUS = "status";

	final private String RESPONSE_HANDLER_NAME = ReceiveServiceTicketListXMLResponseHandler.class.getName(); 
			
	public Request<Response> getRequest(Action action) {
			
		GetServiceTicketList currAction = (GetServiceTicketList) action;
								
		addRequestParameter(new SimpleElement(XML_SERIAL_NUM, currAction.getSerialNumber()));
		addRequestParameter(new SimpleElement(XML_CUST_ID, currAction.getCustomerID()));
		addRequestParameter(new SimpleElement(XML_PART_NUM, currAction.getPartnumber()));
		addRequestParameter(new SimpleElement(XML_TECHNICIAN, currAction.getTechnician()));
		addRequestParameter(new SimpleElement(XML_STATUS, currAction.getStatus()));
					
		return generateXmlRequest();
	}



	@Override
	protected String getRequestName() {

		return XML_REQUEST_NAME;
	}



	@Override
	public String getResponseHandlerName() {

		return RESPONSE_HANDLER_NAME;
	}

	  
}
