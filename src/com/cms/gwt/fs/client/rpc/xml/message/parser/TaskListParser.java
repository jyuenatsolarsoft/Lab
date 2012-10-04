package com.cms.gwt.fs.client.rpc.xml.message.parser;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.details.ServiceDetailList;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

public class TaskListParser {

	private static TaskListParser myself;
	
    public static final String XML_ELEMENT = "taskList";
                   
    public ServiceDetailList parse(Element element) throws FSParseException
    {    	    	
    	try
    	{
	    	ServiceDetailList result = new ServiceDetailList();    	
	    	
	    	NodeList entryNodeList = element.getElementsByTagName(TaskListEntryParser.XML_ELEMENT);
	    	
	    	// Parse all the skill entries found in this response. 
	    	for (int i=0; i<entryNodeList.getLength(); i++)
	    	{    		    		
	    		Node entryNode = entryNodeList.item(i);
	    		result.addServiceDetailList(TaskListEntryParser.getInstance().parse((Element)entryNode));    		    				
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
	
    private TaskListParser()
    {
    	// Disable default constructor.
    }
	
    public static TaskListParser getInstance()
    {
    	if (myself == null)
    	{
    		myself = new TaskListParser(); 
    	}
    	
    	return myself;
    }

    
	public String getXmlElement() {
		return XML_ELEMENT;
	}
    
    	

}
