package com.cms.gwt.fs.client.rpc;

import com.cms.gwt.fs.client.exception.ActionFailureException;
import com.cms.gwt.fs.client.exception.ActionNotSupportedException;
import com.cms.gwt.fs.client.exception.DataServicesException;
import com.cms.gwt.fs.client.exception.InvalidResponseException;
import com.cms.gwt.fs.client.rpc.action.Action;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.callback.ActionAsyncCallback;
import com.cms.gwt.fs.client.rpc.message.Request;
import com.cms.gwt.fs.client.rpc.message.Response;
import com.cms.gwt.fs.client.rpc.message.factory.MessageHandlerFactory;
import com.cms.gwt.fs.client.rpc.message.handler.RequestBuilder;
import com.cms.gwt.fs.client.rpc.message.handler.ResponseHandler;
import com.cms.gwt.fs.client.rpc.xml.XMLDataServices;
import com.cms.gwt.fs.client.util.Logger;
import com.cms.gwt.fs.client.view.StatusIndicator;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

/**
 * ActionServices class executes Action(s) requested by the UI presenter.
 * 
 * The Action(s) are executed in asynchronous manner. 
 * Result of the Action will be handled by the corresponding ActionAsyncCallback.
 *
 * ActionServices may also notify other components the result of the 
 * Actions performed. This is done through eventBus mechanism introduced in GWT 1.6.
 * Any components which has registered for the specific event will be informed 
 * by the GWT EventManager upon the arrival of the response. 
 * 
 */
public class ActionServicesImpl implements ActionServices {
	
	static protected ActionServicesImpl myself;
	
	/**
	 * Data services 
	 * All data requests are executed by this services.
	 */
	private DataServices dataServices = XMLDataServices.App.getInstance();

	
	/**
	 * Factory to create the client-server message handlers.
	 */
	private MessageHandlerFactory messageHandlerFactory;
	
	
	/** Disable the constructor. */
	@Inject
	private ActionServicesImpl(MessageHandlerFactory msgHandlerFactory) 
	{ 		
		this.messageHandlerFactory = msgHandlerFactory;
	}
			

	/**
	 * {@inheritDoc}
	 */
	public void execute(Action action, ActionAsyncCallback actionCallback) throws ActionNotSupportedException
	{
		StatusIndicator.show();
		
		RequestBuilder requestHandler = messageHandlerFactory.getRequestHandler(action);
		ResponseHandler responseHandler = messageHandlerFactory.getResponseHandler(action);
		 
		Request<Response> request =  requestHandler.getRequest(action);
		
		Logger.info("Sending Request:" + request.getRequestMessage());
		
		dataServices.execute(request, new DataServicesCallBack(actionCallback, responseHandler));				
	}
	
	
	
	/**
	 * Callback class to handle the response of the Data Services. 
	 * 
	 * The method in this class will process the server response. It informs the Action requester and other
	 * components which has registered for the response of the Action. 
	 *
	 */
	private class DataServicesCallBack implements AsyncCallback<Response>
	{
		ActionAsyncCallback actionCallback;
		
		ResponseHandler responseHandler;
		
		public  DataServicesCallBack(ActionAsyncCallback actionCallback, ResponseHandler responseHandler)
		{
			this.actionCallback = actionCallback;
			this.responseHandler = responseHandler;
		}
		

		public void onFailure(Throwable caught) {
			
			actionCallback.onFailure(caught);
			
			StatusIndicator.hide();
			
		}

		public void onSuccess(Response result) {
			
			Logger.info("Receiving Response:" + result.getResponseMessage());
			
			ActionResponse actionResponse;
			try
			{
				actionResponse = responseHandler.getActionResponse(result);
							
				// Check if there is any failure on the server side.
				if (!actionResponse.hasError())
				{
					
					actionCallback.onSuccess(actionResponse);
	
					// Throw the event to the EventManager				
					actionResponse.throwEvents();
				}
				else
				{
					actionCallback.onFailure(new ActionFailureException(actionResponse.getMessages()));
				}						
			}
			catch (InvalidResponseException e)
			{											
				actionCallback.onFailure(new DataServicesException(e));
			}
			finally
			{
				StatusIndicator.hide();				
			}
		}
	}	
}
