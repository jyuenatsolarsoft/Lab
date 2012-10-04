package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.action.Action;
import com.cms.gwt.fs.client.rpc.action.GetShipToBillTo;
import com.cms.gwt.fs.client.rpc.action.GetTechnicianSchedule;
import com.cms.gwt.fs.client.rpc.message.Request;
import com.cms.gwt.fs.client.rpc.message.Response;
import com.cms.gwt.fs.client.rpc.message.handler.RequestBuilder;

public class GetTechnicianScheduleXMLRequestBuilder extends XmlRequestBuilder implements RequestBuilder, Instantiable 
{
	// This is only a partial name only.
	final static private String XML_REQUEST_NAME = "getServiceTicketTechnicianSchedule";
	
	final static private String XML_ELEMENT_TECHNICIAN = "technician";
	final static private String XML_ELEMENT_START_DATE = "startDate";
	final static private String XML_ELEMENT_HORIZON = "horizon";
	
	final private String RESPONSE_HANDLER_NAME = ReceiveTechnicianScheduleXMLResponseHandler.class.getName();
				
	public Request<Response> getRequest(Action action) {
		
		GetTechnicianSchedule currAction = (GetTechnicianSchedule) action;

		//	No longer need this technician tag as it'll be defaulted to the
		// technician ID under the iSeries user profile.
		//addRequestParameter(new SimpleElement(XML_ELEMENT_TECHNICIAN, currAction.getTechnician()));

		addRequestParameter(new SimpleElement(XML_ELEMENT_START_DATE, currAction.getStartDate().toString()));
		addRequestParameter(new SimpleElement(XML_ELEMENT_HORIZON, currAction.getHorizon()));
									
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
