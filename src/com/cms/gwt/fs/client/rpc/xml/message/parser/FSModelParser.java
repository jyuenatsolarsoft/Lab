package com.cms.gwt.fs.client.rpc.xml.message.parser;

import java.sql.Date;

import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.factory.CodeDictionaryFactory;
import com.cms.gwt.fs.client.model.CodeDictionary;
import com.cms.gwt.fs.client.util.ParameterValidator;
import com.google.gwt.core.client.GWT;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;

/**
 * XML DOM Parser for the models used in Field Services.
 * 
 */
public abstract class FSModelParser {
	
	private TextConstants txtConsts = (TextConstants) GWT.create(TextConstants.class);

    /**
     * Helper method to retrieve element value.
     *
     * @param parentElement The parent node
     * @param elementName The element name
     *
     * @return value of the element with the specified element name under the provided parent node.
     * null if no such element exists.
     */
    protected String getElementValue(Document document, String elementName)
    {
    	return getElementValue(document.getDocumentElement(), elementName);
    }
    
    /**
     * Helper method to retrieve element value.
     *
     * @param parentElement The parent node
     * @param elementName The element name
     *
     * @return value of the element with the specified element name under the provided parent node.
     * null if no such element exists.
     */
    protected String getElementValue(Element parentElement, String elementName)
    {
        if (parentElement.getElementsByTagName(elementName).getLength() > 0)
        {
        	Element element = ((Element) parentElement.getElementsByTagName(elementName).item(0));
        	
        	if (element.getFirstChild() != null)
        	{
        		return element.getFirstChild().getNodeValue();
        	}
        	else
        	{
        		return "";
        	}        	             
        }
        else
        {
            return null;
        }
    }   
    
    
    /**
     * Helper method to create a sql Date object from a date String. 
     *  
     * @param dateStr The date in String format.
     * 
     * @return sql Date object with the specified date. null if dateStr is null.
     */
    protected Date createSqlDate(String dateStr)
    {
    	Date result = null;
    	
    	// TODO: 
    	// This seems to be the default empty date from the backend.
    	// This date may not be handled properly by the sql Date and the UI Date widget. 
    	// The quick workaround is to return null. To be revisited.    
    	final String EMPTY_DATE = "0001-01-01";
    	
    	if (dateStr != null && !"".equals(dateStr.trim()) && !EMPTY_DATE.equals(dateStr.trim()))
    	{
    		result = Date.valueOf(dateStr);	
    	}
    	
    	return result;
    }
    
    
    /**
     * Helper method to create a Double object from a double string 
     *  
     * @param doubleStr double in String
     * 
     * @return 
     */
    protected Double parseDouble(String doubleStr)
    {
    	Double result = 0.0;
    	    	
    	if (doubleStr != null && !"".equals(doubleStr.trim()))
    	{
    		result = new Double(doubleStr.trim());	
    	}
    	
    	return result;
    }    
    
    /**
     * Helper method to create a Integer object from a integer string 
     *  
     * @param integerStr integer String
     * 
     * @return Integer
     */
    protected int parseInt(String integerStr)
    {
    	int result = 0;
    	
    	if (integerStr != null && !"".equals(integerStr.trim()))
    	{
    		result = new Integer(integerStr);	
    	}
    	
    	return result;
    }    
    
	/**
	 * Retrieve a single element by tag name.
	 * 
	 * @param document The XML document object.
	 * @param elementName The name of the element.
	 * @return The element with the specified element name under the document object.
	 */
	protected Element getSingleElementByTagName(Document document, String elementName)
	{
		return getSingleElementByTagName(document.getDocumentElement(), elementName);
	}
	
	/**
	 * Retrieve a single element by tag name.
	 * 
	 * @param parentElement The parent element.
	 * @param elementName The name of the element.
	 * @return The element with the specified element name under the document object.
	 */
	protected Element getSingleElementByTagName(Element parentElement, String elementName)
	{
        if (parentElement.getElementsByTagName(elementName).getLength() > 0)
        {
            return ((Element) parentElement.getElementsByTagName(elementName).item(0));
        }
        else
        {
            return null;
        }
	}
	
	/**
	 * Parse the boolean value represented in integer string.
	 * 
	 * @param booleanIntStr The boolean value to be parsed.
	 * 
	 * @return False if booleanInt is null. True if the integer parsed is equal to 0, false otherwise. 
	 */
	protected boolean parseBooleanInt(String booleanIntStr)
	{
		if (booleanIntStr == null)
		{
			return false;
		}
		
		int booleanInt = Integer.parseInt(booleanIntStr);
		
		if (booleanInt != 0)
		{
			return true;
		}
		else
		{
			return false;
		}		
	}
	
	/**
	 * Helper method to retrieve the value from the provided element.
	 * 
	 * @param element  
	 * 
	 * @return The value in the specified element.
	 */
    protected String getElementValue(Element element)
    {        
    	if (element.getFirstChild() != null)
    	{
    		return element.getFirstChild().getNodeValue();
    	}
    	else
    	{
    		return null;
    	}        	             
    }	
    
    /**
     * Retrieve the description of the code.
     *    
     * @param dictionary dictionary to look up.
     * @param code The code to be translated.
     * @return The human readable description of the provided code. Null if the code is null.
     */
    protected String getDesc(CodeDictionary dictionary, String code)
    {    	
     	String result = null; 
    	if (code != null)
    	{
	    	result = dictionary.get(code);
	    	
	    	if (result == null)
	    	{
	    		result = code + " " + txtConsts.DescNotAvail();
	    	}
    	}
    	return result;
    }
    
    
    /**
     * Helper method to retrieve the description of the code stored under
     * the element with the specified element name under the provided 
     * parent element.
     * 
     * @param dictionaryName the name of the dictionary the description will be stored.
     * 		                 @see CodeDictionary
     *                       @see CodeDictionaryFactory 
     * @param parentElement The parent element.
     * @param elementName The name of the element storing the code value.
     */
    protected String getDesc(String dictionaryName, Element parentElement, String elementName)
    {
    	String code = getElementValue(parentElement, elementName);
    	
    	if (code == null)
    	{
    		return null;
    	}
    	
		String desc = CodeDictionaryFactory.getCodeDictionary(dictionaryName).get(code);
		
		if (desc == null)
		{
			return code + " " + txtConsts.DescNotAvail(); 			
		}
		else
		{
			return desc;
		}
    }

    
    /**
     * Convert the specified double into XML element value String.
     * 
     * @param number double value
     * 
     * @return The double value in String format.
     */
    protected String convertToStr(double number)
    {    	
    	// use Number Formatter?    	
    	return Double.toString(number);
    }
    
    /**
     * Convert the specified integer into XML element value String.
     * 
     * @param number int value
     * 
     * @return The int value in String format.
     */
    protected String convertToStr(int number)
    {
    	// use Number Formatter?
    	return Integer.toString(number);
    }    
    
    /**
     * Convert the specified sql date into String.
     * 
     * @param date The sql date.
     * 
     * @return The date in String value. Empty string if the date is null.
     */
    protected String convertToStr(Date date)
    {
    	// use DateTime Formatter?
    	if (date != null)
    	{
    		return date.toString();
    	}
    	
    	return "";
    }    
    
    /**
     * Convert boolean into String value.
     * 
     * @param bool The specified boolean value.s
     * 
     * @return The specified boolean value in String format.
     */
    protected String convertToStr(boolean bool)
    {
    	return bool ? "1" : "2";
    }
    
	/**
	 * Helper method to determine the boolean value.
	 * 
	 * @return boolean if the string value is 1, then it returns true, false otherwise.s
	 */
	protected boolean determineBoolValue(String value)
	{		
		final String TRUE = "1";
				
		if (value != null && value.trim().equals(TRUE))
		{
			return true;
		}
		
		return false;
	}    
	
	/**
	 * Helper method to parse boolean value 'Y' and 'N'.
	 * 
	 * @param value
	 * @return
	 */
	protected boolean parseBooleanYN(String value)
	{
		if (value != null && "Y".equals(value))
		{
			return true;
		}
		return false;
	}	
    
    /**
     * Returns the XML element name that this class will be able to parse.
     * 
     * @return
     */
    public abstract String getXmlElement();
    
    /**
     * Validate the input element.
     */
    protected void validateElement(Element element)
    {
    	ParameterValidator.validateParam("element", element);
    }

}
