package com.cms.gwt.fs.client.model;

import java.util.Map;

/**
 * The TaxGroupDictionary contains the tax group code, tax description and the corresponding TaxRateDictionary.
 * 
 */
public class TaxGroupDictionary extends CodeDictionary 
{
	private Map<String, CodeDictionary> taxRateDictionaries;
	
	public TaxGroupDictionary(String dictionaryName, Map<String, String> descriptions,
			Map<String, CodeDictionary> taxRateDictionaries)
	{
		super(dictionaryName, descriptions);
		this.taxRateDictionaries = taxRateDictionaries;
	} 
	
	/**
	 * Return the CodeDictionary for the specified tax group.
	 * 
	 * @param taxGroup The tax group code.
	 * 
	 * @return The Code Dictionary containing tax code and the corresponding descriptions.
	 */
	public CodeDictionary getTaxRateDictionary(String taxGroup)
	{
		return taxRateDictionaries.get(taxGroup);
	}
}
