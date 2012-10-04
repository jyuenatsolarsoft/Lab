package com.cms.gwt.fs.client.rpc.xml.message.factory;

import com.cms.gwt.fs.client.exception.ActionNotSupportedException;
import com.cms.gwt.fs.client.reflection.Factory;
import com.cms.gwt.fs.client.reflection.ReflectiveFactory;
import com.cms.gwt.fs.client.rpc.action.Action;
import com.cms.gwt.fs.client.rpc.message.factory.MessageHandlerFactory;
import com.cms.gwt.fs.client.rpc.message.handler.RequestBuilder;
import com.cms.gwt.fs.client.rpc.message.handler.ResponseHandler;
import com.cms.gwt.fs.client.rpc.xml.message.handler.XmlRequestBuilder;
import com.google.gwt.core.client.GWT;

/**
 * An implementation of the MessageHandlerFactory.
 * 
 * This concrete factory returns the handlers which build the XML requests 
 * and parse the XML responses received.
 * 
 * @see MessageHandlerFactory
 */
public class XMLMessageHandlerFactoryImpl implements MessageHandlerFactory {

	private Factory factory = (Factory) GWT.create(ReflectiveFactory.class);
	
	/**
	 * {@inheritDoc}
	 */
	public RequestBuilder getRequestHandler(Action action) throws ActionNotSupportedException {

		String actionName = action.getClass().getName();
		String simpleActionName = actionName.substring(actionName.lastIndexOf(".")+1, actionName.length());
		
		// TODO: investigate why Class.getPackage() and Class.getSimpleName() do not work here.
		//RequestBuilder handler = (RequestBuilder) factory.newInstance(XmlRequestBuilder.class.getPackage() + "." + simpleActionName + XmlRequestBuilder.class.getSimpleName());
		
		RequestBuilder handler = (RequestBuilder) factory.newInstance("com.cms.gwt.fs.client.rpc.xml.message.handler." + simpleActionName + "XMLRequestBuilder");
		
		if (handler == null)
		{
			throw new ActionNotSupportedException("Action " + action.getClass().getName() + " is not supported.");
		}
		
		return handler;		
	}

	/**
	 * {@inheritDoc}
	 *  
	 */	
	public ResponseHandler getResponseHandler(Action action) throws ActionNotSupportedException {
		
		XmlRequestBuilder requestHandler = (XmlRequestBuilder)(getRequestHandler(action));
		ResponseHandler handler = (ResponseHandler) factory.newInstance(requestHandler.getResponseHandlerName());			
		return handler;
				
	}	
}
