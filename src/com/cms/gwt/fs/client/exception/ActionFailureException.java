package com.cms.gwt.fs.client.exception;

import com.cms.gwt.common.client.exceptions.CMSGwtBaseException;
import com.cms.gwt.fs.client.model.Message;
import com.cms.gwt.fs.client.model.Messages;

/**
 * Indicates that an error has occurred at the backend while executing the request.
 * The issue should not have anything to do with the RPC.
 *
 */
@SuppressWarnings("serial")
public class ActionFailureException extends CMSGwtBaseException {
	
	/**
	 * Contains the error message from the backend.
	 */
	private Messages msgs;
	
	@SuppressWarnings("unused")
	private ActionFailureException() {
		// Disable the default constructor.
	}

	public ActionFailureException(String message, Throwable e, Messages msgs) {
		super(message, e);
		this.msgs = msgs;
	}

	public ActionFailureException(String message, Messages msgs) {
		super(message);
		this.msgs = msgs;
	}
	
	public ActionFailureException(Messages msgs)
	{
		this.msgs = msgs;
	}
	
	public Messages getMessages()
	{
		return msgs;
	}
	
	public String getActionFailureMsg()
	{
		StringBuffer result = new StringBuffer();
		
		String exceptionMsg = (super.getMessage() != null) ? super.getMessage() : ""; 
		result.append(exceptionMsg + "<br>");
					
		for (Message msg : msgs.getMessages())
		{					
			result.append(msg.getFormattedMessage() + "<br>"); 
		}
				
		return result.toString(); 
	}
	
	@Override
	public String getMessage()
	{
		return getActionFailureMsg();
	}
}
