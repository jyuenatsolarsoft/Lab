package com.cms.gwt.fs.client.rpc.message.handler;

import com.cms.gwt.fs.client.exception.InvalidResponseException;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.message.Response;

/**
 * ResponseHandler will handle the incoming message from the server.
 * 
 * It analyzes the message received from the server side and build an instance of ActionResponse 
 * which allows the UI presenter to handle the action response from the server without knowing 
 * anything about the message protocol.
 * 
 */
public interface ResponseHandler {

	/**
	 * Extract the information from the server response and build an ActionResponse. 
	 * 
	 * @param response response from the server.
	 * 
	 * @return the ActionResponse which contains the result of the action previously performed.
	 * 
	 * @throws InvalidResponseException when the response is not well formed or the response content is not 
	 * expected.
	 *  
	 */
	ActionResponse getActionResponse(Response response) throws InvalidResponseException;
}
