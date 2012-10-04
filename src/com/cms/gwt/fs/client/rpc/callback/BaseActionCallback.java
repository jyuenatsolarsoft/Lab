package com.cms.gwt.fs.client.rpc.callback;

import java.util.ArrayList;
import java.util.List;

import com.allen_sauer.gwt.log.client.Log;
import com.cms.gwt.common.client.ExtCmsMessageBox;
import com.cms.gwt.common.client.exceptions.AuthenticationException;
import com.cms.gwt.common.client.rpc.CMSGwtCallback;
import com.cms.gwt.common.client.util.TextConstants;
import com.cms.gwt.fs.client.FieldServices;
import com.cms.gwt.fs.client.exception.DataServicesException;
import com.cms.gwt.fs.client.model.Message;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.InvocationException;
import com.gwtext.client.widgets.MessageBox;

/**
 * Base class of the Action callback. 
 *  
 */
@SuppressWarnings("unchecked")
public abstract class BaseActionCallback extends CMSGwtCallback  implements ActionAsyncCallback {
		
	private com.cms.gwt.fs.client.TextConstants appTxtConsts = 
		(com.cms.gwt.fs.client.TextConstants) GWT.create(com.cms.gwt.fs.client.TextConstants.class);
	
	/**
	 * {@inheritDoc}	
	 */
    public void onFailure(java.lang.Throwable throwable)   
    {
    	// TODO: 
    	// Better to update the onFailure(Throwable in GWTCommon) instead of overriding it here.
        if (throwable instanceof InvocationException)
        {
        	// This is client side connection issue.
            final String message =  ((TextConstants) GWT.create(TextConstants.class)).connectionError();            
            Log.info(this.getClass().getName() + " - " + message, throwable);
            new ExtCmsMessageBox().alert(message);
            handleConnectionIssue();
        }
        else if (throwable instanceof AuthenticationException)
        {
        	// Authentication error. This could be session time out or somebody trying to hack the system.
        	String message = throwable.getMessage();
            Log.info(this.getClass().getName() + " - " + message, throwable);
            new ExtCmsMessageBox().alert(message);
            handleAuthIssue();
        }
        else if (throwable instanceof DataServicesException)
        {
        	// Unexpected errors on the server side.
        	String message = throwable.getMessage();
            Log.info(this.getClass().getName() + " - " + message, throwable);
            new ExtCmsMessageBox().alert(message);             
        }
    }
    
    /**
     * Show all the warning messages in the provided response.
     * 
     * @param response 
     */
    protected void showWarning(ActionResponse response)
    {
    	showNotifAndWarning(response, null);
    }
    
    /**
     * Display the warning errors and notification message in 
     * separate dialog boxes. 
     * 
     * @param response
     * @param notifMsg
     */
    protected void showNotifAndWarning(ActionResponse response, String notifMsg)
    {
		List<DialogMessage> msgsToBeDisplayed = new ArrayList<DialogMessage>();
		
		if (response.hasWarning())
		{						
			for (Message msg : response.getMessages().getMessages())
			{
				if (Message.MessageType.WARNING.equals(msg.getMessageType())) 
				{
					msgsToBeDisplayed.add(new DialogMessage(appTxtConsts.DialogTitleWarning(), msg.getFormattedMessage()));
				}
			}
		}
		
		if (notifMsg != null && !"".equals(notifMsg.trim()))
		{
			msgsToBeDisplayed.add(new DialogMessage(appTxtConsts.DialogTitleNotif(), notifMsg));
		}
		
		displayMessages(msgsToBeDisplayed);
    }
    
    /**
     * Display Error message.
     * 
     * @param throwable
     * @param gadgetMessage
     * @param messageBox
     */
    protected void displayErrorMsg(java.lang.Throwable throwable, String gadgetMessage, ExtCmsMessageBox messageBox)
    {
    	onFailure(throwable, appTxtConsts.DialogTitleError(), gadgetMessage, messageBox);
    }    
	
			
    /**
     * Display the provided messages in individual dialog boxes.
     * 
     * @param messages
     */
	protected void displayMessages(final List<DialogMessage> messages)
	{				
		if (messages.size() > 0)
		{
			DialogMessage currMsg = messages.get(0);
					
			MessageBox.alert(currMsg.getTitle(), currMsg.getContent(), new MessageBox.AlertCallback() {
				
				public void execute() {
	
					// Remove the message that has been displayed already.
					messages.remove(0);
										
					if (messages.size() > 0)
					{
						// Display the rest of the messages. 
						displayMessages(messages);
					}					
				}
			});												
		}
	}
    	
    /**
     * {@inheritDoc}
     */
    protected void handleAuthIssue()
    {
    	// Implement the action that will be taken when there is an authenication exception.
    	FieldServices.getInstance().logout();
    }
    
    /**
     * A helper class to hold the dialog content and title.
     *
     */
    protected class DialogMessage
    {
    	private String content;
    	private String title;
    
    	public DialogMessage(String title, String content)
    	{
    		this.content = content;
    		this.title = title;
    	}
    	
    	public String getContent()
    	{
    		return content;
    	}
    	
    	public String getTitle()
    	{
    		return title;
    	}
    }      
    
    @Override
    protected String getUserId()
    {
        return FieldServices.getInstance().getUserId();
    }
}
