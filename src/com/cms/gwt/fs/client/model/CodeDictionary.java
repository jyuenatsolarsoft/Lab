package com.cms.gwt.fs.client.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * Immutable class containing the codes and descriptions.
 * 
 */
public class CodeDictionary {

	/**
	 * The name of the code dictionary.
	 */
	private String dictionaryName;
	
	/**
	 * A map containing the code as they map key and the description
	 * of the code as the corresponding map value. 
	 */
	private Map<String, String> descriptions;
	
	public CodeDictionary(String dictionaryName, Map<String, String> descriptions)
	{
		this.dictionaryName = dictionaryName;
		this.descriptions = descriptions;
	}
	
	protected CodeDictionary()
	{
		// Disable the default constructor.
	}
	

	/**
	 * Return the name of this dictionary.
	 * 
	 * @return
	 */
	public String getDictionaryName()
	{
		return dictionaryName;
	}
	
	/**
	 * Returns the description of the specified code.
	 * 
	 * @param code
	 * @return
	 */
	public String get(String code)
	{
		return descriptions.get(code);
	}
	
	/**
	 * Get all the codes.
	 * 
	 * @return
	 */
	public Set<String> getCodes() 
	{
		return Collections.unmodifiableSet(descriptions.keySet());
	}

	/**
	 * Get all the descriptions.
	 * 
	 * @return
	 */
	public Collection<String> getDescriptions() 
	{
		return Collections.unmodifiableCollection(descriptions.values());
	}	
}
