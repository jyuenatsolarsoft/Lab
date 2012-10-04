package com.cms.gwt.fs.client.rpc;

import com.cms.gwt.fs.client.exception.ActionNotSupportedException;
import com.cms.gwt.fs.client.gin.AppGinjector;
import com.cms.gwt.fs.client.rpc.action.Action;
import com.cms.gwt.fs.client.rpc.callback.ActionAsyncCallback;
import com.google.gwt.core.client.GWT;

/**
 * ActionServices class executes all Action(s) requested by the UI presenter.
 * 
 * The Action(s) are executed in asynchronous manner. 
 * Result of the Action will be processed by the corresponding ActionAsyncCallback.
 *
 * ActionServices will also notify other components the result of the 
 * Actions performed. This is done through eventBus mechanism introduced in GWT 1.6.
 * Any components which has registered for the specific event will be informed 
 * by the GWT EventManager upon the arrival of the response. 
 * 
 */
public interface ActionServices {
		

	/**
	 * Execute the backend Action asynchronously.  
	 * 
	 * Result of the Action will be handled by the ActionAsyncCallback class.
	 * 
	 * @param action The Action Action requested to perform on the backend.
	 * @param actionCallback The callback class to handle the result of the action.
	 * @throws ActionNotSupportedException The provided Action is not supported.
	 */
	public void execute(Action action, ActionAsyncCallback actionCallback) throws ActionNotSupportedException;
	
	
    /**
     * Utility/Convenience class.
     * Use ActionServices.App.getInstance() to access static instance of DataServices
     */
    public static class App
    {
        private static ActionServices ourInstance = null;

        public static synchronized ActionServices getInstance()
        {
            if (ourInstance == null)
            {
            	final AppGinjector ginjector = GWT.create(AppGinjector.class); 
            	
                ourInstance = ginjector.getActionServices();

            }
            return ourInstance;
        }
    }	
}
