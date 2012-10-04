package com.cms.gwt.fs.client.login;

import java.util.HashMap;

import com.cms.gwt.common.client.login.ClientLoginService;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServicesAsync extends ClientLoginService
{
	/**
	 * {@inheritDoc}
	 */
	void login(String userId, String password, String database, HashMap<String, String> preprocessParams, AsyncCallback async);

	/**
	 * {@inheritDoc}
	 */	
	void isLoggedIn(AsyncCallback async);
	
	/**
	 * {@inheritDoc}
	 */	
	void logout(AsyncCallback async);

	/**
	 * {@inheritDoc}
	 */    
	void getPreprocessParameterHeaders(AsyncCallback async);

}