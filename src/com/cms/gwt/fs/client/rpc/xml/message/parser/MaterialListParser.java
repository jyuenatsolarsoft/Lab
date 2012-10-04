package com.cms.gwt.fs.client.rpc.xml.message.parser;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.material.ServiceDetailMaterialList;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;

public class MaterialListParser  extends FSModelParser 
{
	
	private static MaterialListParser myself;
	
	final public static String XML_ELEMENT = "materialList";
		
	public ServiceDetailMaterialList parse(Element element) throws FSParseException
	{				
		try
		{
			MaterialListEntryParser entryParser = MaterialListEntryParser.getInstance(); 
			
			NodeList entryList = element.getElementsByTagName(MaterialListEntryParser.XML_ELEMENT);
			
			ServiceDetailMaterialList entries = new ServiceDetailMaterialList();
					
			for (int i=0; i<entryList.getLength(); i++)
			{
				entries.add(entryParser.parse((Element)entryList.item(i)));
			}
			
			return entries;					
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

	@Override
	public String getXmlElement() {
 
		return XML_ELEMENT;
	}
	
	 public static MaterialListParser getInstance()
	 {
	 	if (myself == null)
	 	{
	 		myself = new MaterialListParser();
	 	}
	 	
	 	return myself;
	 }	
}	
