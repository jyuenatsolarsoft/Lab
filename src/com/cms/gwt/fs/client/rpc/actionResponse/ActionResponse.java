package com.cms.gwt.fs.client.rpc.actionResponse;

import java.util.List;

import com.cms.gwt.fs.client.model.Message;
import com.cms.gwt.fs.client.model.Messages;


/**
 * ActionResponse contains the result of an Action performed by the UI presenter.
 * 
 */
public abstract class ActionResponse {
	
	/**
	 * Stores the warning, info or any errors returned from the backend for the Action.
	 */
	protected Messages messages; 
	
	protected ActionResponse()
	{
		
	}
			
	/**  
	 *  A method to notify all other UI presenters which are interested in the 
	 *  ActionResponse. This can be achieved by leveraging the GWT EventManager.
	 *  
	 *  When the ActionResponse is received, this method will throw an Event and
	 *  the EventManager will inform the registered UI presenter about this event.
	 * 
	 *  i.e.
	 *  An update on the task status may potentially change the ticket status.
	 *  When an update response is received, the EventManager will notify the
	 *  ticket presenter and let it decide if it wants to refresh the ticket status.
	 *  
	 *  Note that an infinite loop may happen if the chain reaction is not well designed.
	 *  
	 */
	public abstract void throwEvents();
	

	/**
	 * Retrieve the backend messages contained in this response.
	 * 
	 * @return The backend messages received.
	 */
	public Messages getMessages() {
		return messages;
	}

	
	/**
	 * Indicate whether the action has been performed successfully.
	 * 
	 * @return true if the Action has been executed successfully without any errors, false otherwise.
	 * 
	 */	
	public boolean hasError()
	{
		return hasError(messages);
	}
	
	/**
	 * Indicate whether there are any warning messages returned from the server. Note that warning
	 * messages can be present even the request is processed successfully.
	 * 
	 * @return true if the Action has been executed successfully without any errors, false otherwise.
	 * 
	 */	
	public boolean hasWarning()
	{
		return hasWarning(messages);
	}
	
	/**
	 * Check whether there is any warning contained in the response.
	 * 
	 * 
	 * @return true if the Action has been executed successfully without any errors, false otherwise.
	 * 
	 */	
	private boolean hasWarning(Messages msgs)
	{
    	if (msgs == null)
    	{
    		return false;    		
    	}
    	else
    	{
    		List<Message> msgList = msgs.getMessages();
    		for(Message msg : msgList)
    		{
    			if (Message.MessageType.WARNING.equals(msg.getMessageType()))
    			{
    				return true;
    			}
    		}
    		
    		return false;
    	}    	

	}		
		
	
    /**
     * Helper method to determine whether there are any errors in the messages.
     * 
     * @param msgs The messages returned from the backend.
     */
    protected boolean hasError(Messages msgs)
    {
    	if (msgs == null)
    	{
    		return false;    		
    	}
    	else
    	{
    		List<Message> msgList = msgs.getMessages();
    		for(Message msg : msgList)
    		{
    			if (Message.MessageType.ERROR.equals(msg.getMessageType()))
    			{
    				return true;
    			}
    		}
    		
    		return false;
    	}    	
    }	
	
}
