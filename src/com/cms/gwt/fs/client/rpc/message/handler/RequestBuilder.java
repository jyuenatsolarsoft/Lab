package com.cms.gwt.fs.client.rpc.message.handler;

import com.cms.gwt.fs.client.rpc.action.Action;
import com.cms.gwt.fs.client.rpc.message.Request;
import com.cms.gwt.fs.client.rpc.message.Response;

/**
 * The job of the RequestBuilder is to translate the Action performed by the UI presenter 
 * into a request which can be understood by the backend. 
 * 
 * It shields the message details and other communication details from the UI presenter. 
 */
public interface RequestBuilder {
	
	 /**
	  * Translate the Action performed by the UI presenter into a server request message.
	  * 
	  * @param action The Action performed by the UI presenter.
	  * 
	  * @return The server request which is to be sent out to the backend.
	  */
	 Request<Response> getRequest(Action action);
}
