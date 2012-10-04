package com.cms.gwt.fs.client.rpc.xml.message.parser;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.skill.RequiredSkillsList;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;


public class SkillsListParser extends FSModelParser {
	
	private static SkillsListParser myself;
	
    public static final String XML_ELEMENT = "skillsList";
                   
    public RequiredSkillsList parse(Element element) throws FSParseException
    {    	    	
    	try
    	{
	    	RequiredSkillsList result = new RequiredSkillsList();    	
	    	
	    	NodeList entryNodeList = element.getElementsByTagName(SkillsListEntryParser.XML_ELEMENT );
	    	
	    	// Parse all the skill entries found in this response. 
	    	for (int i=0; i<entryNodeList.getLength(); i++)
	    	{    		    		
	    		Node entryNode = entryNodeList.item(i);
	    		result.addRequiredSkillsListEntry(
	    				SkillsListEntryParser.getInstance().parse((Element)entryNode));
	    	}
	    	    	    	
	    	return result;
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
	
    private SkillsListParser()
    {
    	// Disable default constructor.
    }
	
    public static SkillsListParser getInstance()
    {
    	if (myself == null)
    	{
    		myself = new SkillsListParser(); 
    	}
    	
    	return myself;
    }

    
    @Override
	public String getXmlElement() {
		// Auto-generated method stub
		return null;
	}
    
    	

}
