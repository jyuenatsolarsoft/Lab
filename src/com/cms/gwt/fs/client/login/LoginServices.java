package com.cms.gwt.fs.client.login;


import java.util.HashMap;

import com.cms.gwt.common.client.login.LoginSuccess;
import com.cms.gwt.common.client.login.PreprocessParameterHeader;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 * 
 * 
 */
public interface LoginServices extends RemoteService
{
	/**
	 * Session key for the user login information.
	 */
	public static final String SESSION_LOGIN_USER = "loginUser";

    /**
     * Process login asynchronously.
     *
     * @param userId unique userid.
     * @param password user password.
     * @param database database selected to login.
     * @param preprocessParams preprocessParameters preprocess parameters.
     * The map key is preprocess parameter name and the corresponding value is the preprocess parameter value.
     * 
     * @return LoginSuccess Indicate whether the login is successful or not.
     */	
    LoginSuccess login(String userId, String password, String database, HashMap<String, String> preprocessParams);

    /**
     * Check whether the user is logged in or not on the server.
     *
     * @return LoginSuccess Indicate whether the login is successful or not.
     */
    LoginSuccess isLoggedIn();

    /**
     * Returns the preprocess parameter headers.
     *
     * @return A Map containing the preprocess parameter key as the Map key and the preprocess parameter header as the Map value.
     */
    HashMap<String, PreprocessParameterHeader> getPreprocessParameterHeaders();

    /**
     * Log the user out on the server side.
     *
     * @param async
     */
    void logout();

    /**
     * Utility/Convenience class.
     * Use LoginService.App.getInstance() to access static instance of LoginServiceAsync
     */
    public static class App
    {
        private static LoginServicesAsync ourInstance = null;

        public static synchronized LoginServicesAsync getInstance()
        {
            if (ourInstance == null)
            {
                ourInstance = (LoginServicesAsync) GWT.create(LoginServices.class);
                                
                ((ServiceDefTarget) ourInstance).setServiceEntryPoint(GWT.getModuleBaseURL() + "loginServices");
            }
            return ourInstance;
        }
    }
}

