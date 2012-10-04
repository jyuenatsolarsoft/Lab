package com.cms.gwt.fs.client.rpc.message.factory;

import com.cms.gwt.fs.client.exception.ActionNotSupportedException;
import com.cms.gwt.fs.client.rpc.action.Action;
import com.cms.gwt.fs.client.rpc.message.handler.RequestBuilder;
import com.cms.gwt.fs.client.rpc.message.handler.ResponseHandler;

/**
 * Abstract factory providing the message handlers to create Requests and handle Responses.
 * 
 * 
 * The implementation and protocol of the client-server messages are hidden in the
 * concrete classes.
 *
 */
public interface MessageHandlerFactory  {

	/**
	 * Returns the handler which processes the response message returned from the server.
	 * 
	 * @param action The Action associated to the Response.
	 * @return ResponseHandler The handler to process the Response of the specified Action. 
	 * @throws ActionNotSupportedException The provided Action is not supported.
	 * 
	 * @see ResponseHandler
	 */
	ResponseHandler getResponseHandler(Action action) throws ActionNotSupportedException;
	

	/**
	 * Returns the handler which translates the requested Action into request message.
	 * 
	 * @param action The Action associated to the Request.
	 * @return RequestHandler The Action-to-Request is processed by this handler. 
	 * @throws ActionNotSupportedException The provided Action is not supported.
	 * 
	 * @see RequestBuilder 
	 */
	RequestBuilder getRequestHandler(Action action) throws ActionNotSupportedException;
}
