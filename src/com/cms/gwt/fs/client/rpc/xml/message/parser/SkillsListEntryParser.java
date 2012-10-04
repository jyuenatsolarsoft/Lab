package com.cms.gwt.fs.client.rpc.xml.message.parser;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.skill.RequiredSkillsListEntry;
import com.google.gwt.xml.client.Element;

public class SkillsListEntryParser extends FSModelParser {
	
	private static SkillsListEntryParser myself;
	
	final public static  String XML_ELEMENT = "entry";
	
	final private static String XML_ELEMENT_SKILL = "skill";
	final private static String XML_ELEMENT_DESCRIPTION = "description";
	final private static String XML_ELEMENT_LEVEL_REQUIRED = "levelRequired";

	
	public RequiredSkillsListEntry parse(Element element) throws FSParseException
	{
		try
		{
			RequiredSkillsListEntry result = new RequiredSkillsListEntry();
					
			result.setSkill(getElementValue(element, XML_ELEMENT_SKILL));
			result.setDescription(getElementValue(element, XML_ELEMENT_DESCRIPTION));
			result.setLevelRequired(getElementValue(element, XML_ELEMENT_LEVEL_REQUIRED));
			
			return result;
		}
		catch (Exception e)
		{
			throw new FSParseException(e);
		}			
	}
	
	public static SkillsListEntryParser getInstance()
	{
		if (myself == null)
		{
			myself = new SkillsListEntryParser();
		}
		
		return myself;
	}

	@Override
	public String getXmlElement() {
		
		return XML_ELEMENT;
	}
	
}
