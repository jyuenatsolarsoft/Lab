package com.cms.gwt.fs.client.rpc.xml.message.parser;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.skill.SkillsCodeInfo;
import com.google.gwt.xml.client.Element;

public class SkillsCodeInfoDescriptionParser extends FSModelParser {
	
	private static SkillsCodeInfoDescriptionParser myself;
	
	final public static  String XML_ELEMENT = "description";
	
    private static final String XML_ELEMENT_LINE1 = "line1";
    private static final String XML_ELEMENT_LINE2 = "line2";
    private static final String XML_ELEMENT_LINE3 = "line3";
    
	
    public SkillsCodeInfo parse(Element element) throws FSParseException
    {
    	try
    	{
	    	SkillsCodeInfo result = new SkillsCodeInfo();
	    	    	    	
	    	result.setDescription1(getElementValue(element, XML_ELEMENT_LINE1));
	    	result.setDescription2(getElementValue(element, XML_ELEMENT_LINE2));
	    	result.setDescription3(getElementValue(element, XML_ELEMENT_LINE3));
	    	
	    	return result;
		}
		catch (Exception e)
		{
			throw new FSParseException(e);
		}
    }
    
    
    public static SkillsCodeInfoDescriptionParser getInstance()
    {
    	if (myself == null)
    	{
    		myself = new SkillsCodeInfoDescriptionParser();    
    	}
    	
    	return myself;
    }
    
    private SkillsCodeInfoDescriptionParser()
    {
    	// Disable default constructor.
    }
    
	@Override
	public String getXmlElement() {
		// Do not have an XmlElement since this could be monday, tuesday, wednesday.....
		return XML_ELEMENT;
	}

}
