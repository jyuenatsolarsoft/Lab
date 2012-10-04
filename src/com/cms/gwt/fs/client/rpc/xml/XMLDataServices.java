package com.cms.gwt.fs.client.rpc.xml;

import com.cms.gwt.common.client.exceptions.AuthenticationException;
import com.cms.gwt.fs.client.exception.DataServicesException;
import com.cms.gwt.fs.client.rpc.message.Request;
import com.cms.gwt.fs.client.rpc.message.Response;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public interface XMLDataServices extends RemoteService {

	/**
	 * Execute the XMLRequest asynchronously.
	 * 
	 * @param <T> The XMLResponse 
	 * @param request XMLRequest
	 * @return The XMLResponse
	 * @throws DataServicesException when the DataServices fails to handle the request.
	 * @throws AuthenticationException when no user session is found. This can caused by an attempt to access
	 * data services without login. It can also be due to session timeout on the server side.  
	 *  
	 */
	<T extends Response> T execute(Request<T> request) throws AuthenticationException, DataServicesException;
	
    /**
     * Utility/Convenience class.
     * Use XMLDataServices.App.getInstance() to access static instance of XMLDataServicesAsync
     */
    public static class App
    {
        private static XMLDataServicesAsync ourInstance = null;

        public static synchronized XMLDataServicesAsync getInstance()
        {
            if (ourInstance == null)
            {
                ourInstance = (XMLDataServicesAsync) GWT.create(XMLDataServices.class);

                ((ServiceDefTarget) ourInstance).setServiceEntryPoint(GWT.getModuleBaseURL() + "XMLDataServices");
            }
            return ourInstance;
        }
    }
}
