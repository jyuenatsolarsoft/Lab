package com.cms.gwt.fs.client.reflection;

/**
 * Factory Interface to create a new instance of the specified class name. 
 *
 * The concrete implementation will be generated by the extended Generator during compile time.
 */
public interface Factory {

	Instantiable newInstance(String className);
	
}