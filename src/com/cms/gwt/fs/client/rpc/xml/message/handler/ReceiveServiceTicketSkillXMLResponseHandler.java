package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.skill.RequiredSkill;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetServiceTicketSkillResponse;
import com.cms.gwt.fs.client.rpc.message.handler.ResponseHandler;
import com.cms.gwt.fs.client.rpc.xml.message.parser.ServiceTicketSkillParser;
import com.google.gwt.xml.client.Element;

public class ReceiveServiceTicketSkillXMLResponseHandler extends XmlResponseHandler implements ResponseHandler, Instantiable {

	@Override
	protected ActionResponse parseResponse(Element rootElement) throws FSParseException
	{
		try
		{
			Element skillElement = getSingleElementByTagName(rootElement, ServiceTicketSkillParser.XML_ELEMENT);		
			RequiredSkill skill = ServiceTicketSkillParser.getInstance().parse(skillElement);		
			
			return new GetServiceTicketSkillResponse(skill);
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
