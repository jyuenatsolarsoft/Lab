package com.cms.gwt.fs.client.exception;

import com.cms.gwt.common.client.exceptions.CMSGwtBaseException;

@SuppressWarnings("serial")
public class ActionNotSupportedException extends CMSGwtBaseException
{
    public ActionNotSupportedException()
    {
        super();
    }

    public ActionNotSupportedException(Exception e)
    {
        super(e);
    }

    public ActionNotSupportedException(String message, Throwable e)
    {
        super(message, e);
    }

	public ActionNotSupportedException(String message) {
		super(message);
	}

}
