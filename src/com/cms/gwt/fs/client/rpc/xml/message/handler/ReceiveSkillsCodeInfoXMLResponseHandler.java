package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.skill.SkillsCodeInfo;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetSkillsCodeInfoResponse;
import com.cms.gwt.fs.client.rpc.message.handler.ResponseHandler;
import com.cms.gwt.fs.client.rpc.xml.message.parser.SkillsCodeInfoDescriptionParser;
import com.google.gwt.xml.client.Element;

public class ReceiveSkillsCodeInfoXMLResponseHandler extends XmlResponseHandler implements ResponseHandler, Instantiable {

	final String XML_ELEMENT_SKILLS_CODE = "skillsCode";
			
	protected ActionResponse parseResponse(Element rootElement) throws FSParseException 
	{			
		try
		{
			Element skillsCodeInfoDescElement = getSingleElementByTagName(rootElement, SkillsCodeInfoDescriptionParser.XML_ELEMENT);						
			SkillsCodeInfo skillsCodeInfo = SkillsCodeInfoDescriptionParser.getInstance().parse(skillsCodeInfoDescElement);
			skillsCodeInfo.setSkillsCode(getElementValue(rootElement, XML_ELEMENT_SKILLS_CODE));		
	
			return new GetSkillsCodeInfoResponse(skillsCodeInfo);
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
