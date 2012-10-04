package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.rpc.action.Action;
import com.cms.gwt.fs.client.rpc.action.GetServiceTicketProperties;
import com.cms.gwt.fs.client.rpc.message.Request;
import com.cms.gwt.fs.client.rpc.message.Response;
import com.cms.gwt.fs.client.rpc.message.handler.RequestBuilder;

/**
 * Parent request builder for all actions requesting the properties of a particular service ticket.
 *
 * @see RequestBuilder
 */
public abstract class GetServiceTicketPropertiesXMLRequestBuilder extends XmlRequestBuilder implements RequestBuilder
{	
	final static private String XML_SERVICE_TICKET = "ticketNumber";
	
	public Request<Response> getRequest(Action action) {
		
		GetServiceTicketProperties currAction = (GetServiceTicketProperties) action;
						
		addRequestParameter(new SimpleElement(XML_SERVICE_TICKET, currAction.getTicketNumber()));
					
		return generateXmlRequest();
	}
	
}
