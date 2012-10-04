package com.cms.gwt.fs.server;

import org.apache.log4j.Logger;

import com.cms.dataaccess.DataAccessUtils;
import com.cms.dataaccess.LoginUser;
import com.cms.dataaccess.UserSpaceException;
import com.cms.gwt.common.client.exceptions.AuthenticationException;
import com.cms.gwt.fs.client.exception.DataServicesException;
import com.cms.gwt.fs.client.login.LoginServices;
import com.cms.gwt.fs.client.rpc.message.Request;
import com.cms.gwt.fs.client.rpc.message.Response;
import com.cms.gwt.fs.client.rpc.xml.XMLDataServices;
import com.cms.gwt.fs.client.rpc.xml.message.XMLResponse;
import com.cms.gwt.fs.server.util.Trace;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings( { "serial" })
public class XMLDataServicesImpl extends RemoteServiceServlet implements XMLDataServices {
	
	private static DataAccessUtils dataAccessUtility = new DataAccessUtils();
	
	private static Logger logger = Trace.getLogger(XMLDataServicesImpl.class);
		
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")	
	public <T extends Response> T execute(Request<T> request) throws AuthenticationException, DataServicesException {

		String requestMessage = (String)request.getRequestMessage();
		
		logger.info(("Received Request: [" + requestMessage.trim() + "]"));
		
		try
		{
		
			return (T) new XMLResponse(processRequest(requestMessage));
		}
		catch (AuthenticationException e)
		{
			throw e;
		}		
		catch (Exception e)
		{
			throw new DataServicesException(e);
		}
	}
	
	/**
	 * Process the request received from the client.
	 * 
	 * The request is submitted to the Data Services on the backend.
	 * 
	 * @param request client data request in XML format.
	 * 
	 * @return response in XML format.
	 * 
	 * @throws DataServicesException when the DataServices fails to handle the request.
	 * @throws AuthenticationException when no user session is found. This can caused by an attempt to access
	 * data services without login. It can also be due to session timeout on the server side.
	 */
    public String processRequest(String request) throws AuthenticationException, DataServicesException {
        
    	LoginUser loginUser = validateAuthentication();
    	
        String result = "";                                        
        
        try
        {        	
        	result = dataAccessUtility.execute(loginUser, request);
        }
        catch (UserSpaceException e)
        {
        	logger.error("Failed to forward the request to the data services.", e);
        	
            //logger.debug("Attempt to Ending AS400 connection.");            
            //loginUser.endConnections();
                        
            throw new DataServicesException("Failed to execute the request through the data services.", e);
        }
        
        logger.info("Response: [" + result.trim() + "]");                

        return result;
    }	
    

    /**
     * Helper method to perform a basic authentication check.
     *
     * @return An instance of LoginUser for the user of this session.
     * 
     * @throws AuthenticationException when user is not logged in.
     * 
     */
    protected LoginUser validateAuthentication() throws AuthenticationException
    {
    	LoginUser loginUser = (LoginUser) getThreadLocalRequest().getSession().getAttribute(LoginServices.SESSION_LOGIN_USER);
        
        if (loginUser == null) {        	
        	logger.debug("Detected a request without valid session.");
            throw new AuthenticationException("\nUser is not logged in or the session has expired. Please log in again.");
        }

        return loginUser;
    }	
}
