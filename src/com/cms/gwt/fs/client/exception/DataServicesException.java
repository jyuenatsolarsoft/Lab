package com.cms.gwt.fs.client.exception;

import com.cms.gwt.common.client.exceptions.CMSGwtBaseException;

/**
 * This exception is thrown when the backend fails to process the request
 * or when the backend returns an unexpected response.
 *
 */	
@SuppressWarnings("serial")
public class DataServicesException extends CMSGwtBaseException
{
    public DataServicesException()
    {
        super();
    }

    public DataServicesException(Exception e)
    {
        super(e);
    }

    public DataServicesException(String message, Throwable e)
    {
        super(message, e);
    }

}
