package com.cms.gwt.fs.server;

import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.cms.dataaccess.LoginUser;
import com.cms.dataaccess.LoginUserException;
import com.cms.gwt.common.client.UserDetails;
import com.cms.gwt.common.client.login.LoginSuccess;
import com.cms.gwt.common.client.login.PreprocessParameterHeader;
import com.cms.gwt.fs.client.login.LoginServices;
import com.cms.gwt.fs.server.util.Trace;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Testing2
 * 
 */
@SuppressWarnings("serial")
public class LoginServicesImpl extends RemoteServiceServlet implements LoginServices, HttpSessionListener {
		
	private static Logger logger = Trace.getLogger(LoginServicesImpl.class);

	//private static String APP_CONTEXT_NAME = PropertiesUtil.getProperty("context.name", "yourcontextname");
	
    /**
     * The system property key for server address.
     */	
    public static final String IVP_HOSTNAME_PROPERTY_KEY =  "com.cms.fs.ivp.hostname";
    
    /**
     * Default hostname for the iVP backend. 
     */    
    final public static String DEFAULT_IVP_ADDRESS = "localhost";
	
	/**
	 * The plant preprocess parameter key.
	 */
	private static final String PLANT_PREPROCESS_PARAM_KEY = "plant";
		

	/**
	 * Default constructor.
	 */
    public LoginServicesImpl() {

    }

    /**
     * {@inheritDoc}
     */
    public void logout() {
        cleanUp(getThreadLocalRequest().getSession());
    }

    /**
     * Clean up the resource.
     */
    private void cleanUp(HttpSession session) {
        if (session != null) {
            closeConnection(session);

            cleanUpSession(session);
        }
    }


    /**
     * Helper method to close connection.
     */
    private void closeConnection(HttpSession session) {
    	//TODO stub
    }

    /**
     * Clean up the session attributes.
     *
     * @param session session being cleaned up.
     */
    private void cleanUpSession(HttpSession session) {    	
    	
    	LoginUser loginUser = (LoginUser) session.getAttribute(LoginServices.SESSION_LOGIN_USER);
    	
    	if (loginUser != null)
    	{
    		logger.debug(loginUser.getUserId() + "has been removed from session.");
    		
    		session.removeAttribute(LoginServices.SESSION_LOGIN_USER);
    	}
    }


    public LoginSuccess isLoggedIn() {

    	LoginUser loginUser = (LoginUser) this.getThreadLocalRequest().getSession().getAttribute(LoginServices.SESSION_LOGIN_USER);

        if(loginUser != null) 
        {        	
        	UserDetails details = new UserDetails();
        	details.setUserId(loginUser.getName());
        	details.setDatabase(loginUser.getDatabase());
        	return new LoginSuccess(true, "Succeeded", details, "whatiscontextname"); 
        }
        
        return new LoginSuccess("No session founded.");

    }

    public LoginSuccess login(String userId, String password, String database, HashMap<String, String> preprocessParams) {

    	    	
    	String hostname = System.getProperty(IVP_HOSTNAME_PROPERTY_KEY, DEFAULT_IVP_ADDRESS);
    	
        LoginUser loginUser;

        try
        {            
            loginUser = LoginUser.registerLoginUser(userId, password, hostname, database, preprocessParams.get(PLANT_PREPROCESS_PARAM_KEY));
            
        }
        catch (LoginUserException e)
        {
            e.printStackTrace();
            return new LoginSuccess(e.getMessage());
        }

        logger.debug(userId + "has logged in.");
        
        // end the other connection established by the LoginUser to authenticate the user:
        //loginUser.cleanup();
        
        getThreadLocalRequest().getSession().setAttribute(LoginServices.SESSION_LOGIN_USER, loginUser);                
    	
    	UserDetails details = new UserDetails();
    	details.setUserId(userId);
    	details.setDatabase(database);
    	return new LoginSuccess(true, "Succeeded", details, "whatiscontextname");
    }

    /**
     * Returns the preprocess parameter headers.
     *
     * @return A Map containing the preprocess parameter key as the Map key and the preprocess parameter header as the Map value.
     */
    public HashMap<String, PreprocessParameterHeader> getPreprocessParameterHeaders() {
    	
    	HashMap<String, PreprocessParameterHeader> result = new HashMap<String, PreprocessParameterHeader>();
    	PreprocessParameterHeader plant = createPlant();
    	result.put(plant.getKey(), plant);
    	return result;
    }


    /**
     * Helper method is create the plant.
     *
     * @return preprocess parameter header for the plant.
     */
    private PreprocessParameterHeader createPlant() {

    	PreprocessParameterHeader plantParam = new PreprocessParameterHeader();
        plantParam.setKey(PLANT_PREPROCESS_PARAM_KEY);
        plantParam.setAllowMultipleValues(false);
        plantParam.setDefaultValue("");
        plantParam.setDescription("Plant");
        plantParam.setLabel("Plant");
        plantParam.setRequired(true);
        plantParam.setVisibleOnLogin(true);
        plantParam.setUpperCase(true);

        return plantParam;    	
    }

    /**
     * {@inheritDoc}
     */
    public void sessionCreated(HttpSessionEvent se) {
        // do nothing
    }


    /**
     * {@inheritDoc}
     */
    public void sessionDestroyed(HttpSessionEvent se) {
        // Do housekeeping.
        cleanUp(se.getSession());
    }

}
