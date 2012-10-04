package com.cms.gwt.fs.client.rpc;

import com.cms.gwt.fs.client.rpc.message.Request;
import com.cms.gwt.fs.client.rpc.message.Response;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * This interface provides access to the data services on the backend.
 *
 */
public interface DataServices {

	/**
	 * Execute the request asynchronously.
	 * 
	 * @param <T> The Data Services Response. 
	 * @param request The Data Services Request.
	 * @return The Data services response.
	 */
	<T extends Response> void execute(Request<T> request, AsyncCallback<T> callback);
}
