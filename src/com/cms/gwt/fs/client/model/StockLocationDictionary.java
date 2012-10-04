package com.cms.gwt.fs.client.model;

import java.util.Map;

/**
 * The StockBinDictionary contains the stock location code, stock location description and the corresponding bin location Dictionary.
 * 
 */
public class StockLocationDictionary extends CodeDictionary	
{
	private Map<String, CodeDictionary> binLocationDictionaries;

	public StockLocationDictionary(String dictionaryName, Map<String, String> descriptions,
			Map<String, CodeDictionary> binLocationDictionaries)
	{
		super(dictionaryName, descriptions);
		this.binLocationDictionaries = binLocationDictionaries;
	} 
	
	/**
	 * Return the CodeDictionary for the specified stock.
	 * 
	 * @param stock The stock location,
	 * 
	 * @return The Code Dictionary containing bin location code and the bin location descriptions.
	 */
	public CodeDictionary getBinLocationDictionary(String stock)
	{
		return binLocationDictionaries.get(stock);
	}
}
