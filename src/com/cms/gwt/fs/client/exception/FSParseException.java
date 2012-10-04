package com.cms.gwt.fs.client.exception;

import com.cms.gwt.common.client.exceptions.CMSGwtBaseException;

/**
 * This exception is thrown when the the application is not able to parse
 * and create a model from the received message.
 */
@SuppressWarnings("serial")
public class FSParseException extends CMSGwtBaseException {

    public FSParseException()
    {
        super();
    }

    public FSParseException(Exception e)
    {
        super(e);
    }

    public FSParseException(String message, Throwable e)
    {
        super(message, e);
    }

	public FSParseException(String message) {
		super(message);
	}	
	
	
}
