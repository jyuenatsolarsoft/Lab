package com.cms.gwt.fs.client.exception;

import com.cms.gwt.common.client.exceptions.CMSGwtBaseException;

/**
 * This exception is thrown when the received response is not well formed
 * or the content of the response is not expected.
 *
 */
@SuppressWarnings("serial")
public class InvalidResponseException extends CMSGwtBaseException {

    public InvalidResponseException()
    {
        super();
    }

    public InvalidResponseException(Exception e)
    {
        super(e);
    }

    public InvalidResponseException(String message, Throwable e)
    {
        super(message, e);
    }

	public InvalidResponseException(String message) {
		super(message);
	}	
}
