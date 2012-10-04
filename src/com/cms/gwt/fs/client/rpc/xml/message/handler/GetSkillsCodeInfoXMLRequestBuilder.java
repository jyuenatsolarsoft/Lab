package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.action.Action;
import com.cms.gwt.fs.client.rpc.action.GetSkillsCodeInfo;
import com.cms.gwt.fs.client.rpc.message.Request;
import com.cms.gwt.fs.client.rpc.message.Response;
import com.cms.gwt.fs.client.rpc.message.handler.RequestBuilder;

public class GetSkillsCodeInfoXMLRequestBuilder extends XmlRequestBuilder implements RequestBuilder, Instantiable 
{
	final private String XML_REQUEST_NAME = "getSkillsCodeInfo";
	final private String XML_ELEMENT_SKILLS_CODE = "skillsCode";
	
	final private String RESPONSE_HANDLER_NAME = ReceiveSkillsCodeInfoXMLResponseHandler.class.getName();
	
	public Request<Response> getRequest(Action action) {
		
		GetSkillsCodeInfo currAction = (GetSkillsCodeInfo) action;
					
		addRequestParameter(new SimpleElement(XML_ELEMENT_SKILLS_CODE, currAction.getSkillsCode()));
					
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