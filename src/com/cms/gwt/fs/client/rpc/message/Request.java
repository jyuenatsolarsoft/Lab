package com.cms.gwt.fs.client.rpc.message;


/**
 * Represents a request which will be sent to the server via GWT RPC. 
 *
 */
public interface Request<T extends Response>   {

	String getRequestMessage();

}
