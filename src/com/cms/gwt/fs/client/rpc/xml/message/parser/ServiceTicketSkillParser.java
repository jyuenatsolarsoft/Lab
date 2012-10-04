package com.cms.gwt.fs.client.rpc.xml.message.parser;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.skill.RequiredSkill;
import com.google.gwt.xml.client.Element;

public class ServiceTicketSkillParser  extends FSModelParser 
{	 	
	static private ServiceTicketSkillParser myself;
	
	public static final String XML_ELEMENT = "serviceTicketSkill";
	
	private static final String XML_ELEMENT_SKILL = "skill";
	private static final String XML_ELEMENT_LEVEL_REQUIRED = "levelRequired";
	            	 	
	public RequiredSkill parse(Element element) throws FSParseException 
	{
		try 
		{
			RequiredSkill skill = new RequiredSkill();

			skill.setSkillCode(getElementValue(element, XML_ELEMENT_SKILL));
			skill.setLevelRequired(parseInt(getElementValue(element,
					XML_ELEMENT_LEVEL_REQUIRED)));

			return skill;
		} 
		catch (Exception e) 
		{
			throw new FSParseException(e);
		}
	}

	 public static ServiceTicketSkillParser getInstance()
	 {
	 	if (myself == null)
	 	{
	 		myself = new ServiceTicketSkillParser();
	 	}
	 	
	 	return myself;
	 }

	 @Override
	 public String getXmlElement() {

	 	return XML_ELEMENT;
	 }
}
