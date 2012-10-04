package com.cms.gwt.fs.client;

import com.cms.gwt.common.client.UserDetails;
import com.cms.gwt.common.client.login.Bootstrap;
import com.cms.gwt.common.client.login.Login;
import com.cms.gwt.common.client.login.LoginSuccess;
import com.cms.gwt.fs.client.factory.CodeDictionaryFactory;
import com.cms.gwt.fs.client.gin.AppGinjector;
import com.cms.gwt.fs.client.login.LoginServices;
import com.cms.gwt.fs.client.presenter.MainPresenter;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

public class FieldServices implements EntryPoint, Bootstrap {


	/**
	 * Google dependency injector. 
	 */
	final AppGinjector ginjector = GWT.create(AppGinjector.class);
	
	/**
	 * The main presenter of the entire application.
	 */
	final MainPresenter mainPresenter = ginjector.getMainPresenter();
	
	/**
	 * HTML anchor for the application content.
	 */
	final static private String HTML_CONTENT_ANCHOR = "content";
	final static private String HTML_INFO_ANCHOR = "info";
	
	/**
	 * Singleton
	 */
	private static FieldServices myself;
	
	/**
	 * The login module.
	 */
	private Login login;
	
	/**
	 * Contains the details of the login.
	 */
	private LoginSuccess loginDetail;
	
	/**
	 * The plant preprocess parameter key.
	 */
	private static final String PLANT_PREPROCESS_PARAM_KEY = "plant";
	
	/**
	 * Text constants.
	 */
	private TextConstants txtConsts = 
		(TextConstants) GWT.create(TextConstants.class);
	
	/**
	 * Entry point for the Field Services.
	 */
	public void onModuleLoad() {
	
		myself = this;
		login = new Login(LoginServices.App.getInstance(), this, HTML_CONTENT_ANCHOR);
		login.attemptLogin(); 		
	}
	
	/**
	 * Returns the entry point of this application.
	 * 
	 * @return The instance of the FieldServices.
	 */		
	public static FieldServices getInstance()
	{
		return myself;
	}
	
	/**
	 * Log out the application.
	 */
	public void logout()
	{
		// Clear up the header. No header should be shown before logging in.
		RootPanel.get(HTML_INFO_ANCHOR).clear();
		
		// Clear up the url so that it will go to the first page upon next login.
		History.newItem("", false);
		
		// Log out on the server side and go back to the login panel.
		login.logout();
	}
		
	/**
	 * {@inheritDoc}
	 */
	public String getUserId() 
	{
		return "";
	}

	/**
	 * {@inheritDoc}
	 */
	public void resumeApp(LoginSuccess loginSuccess) 
	{		
		startApp(loginSuccess);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setFooterAbsolute() 
	{
		// Leave it empty for now.		
	}

	/**
	 * {@inheritDoc}
	 */
	public void startApp(LoginSuccess loginSuccess) 
	{	
		setLoginDetail(loginSuccess);
		
		init();
		
		RootPanel.get(HTML_INFO_ANCHOR).clear();		
		RootPanel.get(HTML_INFO_ANCHOR).add(buildHeaderInfo(loginSuccess.getUserDetails()));
		RootPanel.get(HTML_CONTENT_ANCHOR).clear();	
		RootPanel.get(HTML_CONTENT_ANCHOR).add(mainPresenter.go().getWidget());
		History.fireCurrentHistoryState();
	}
	
	
	/**
	 * Builder the header for the entire application.
	 * 
	 * @param userDetails
	 * @return Panel containing the header info.
	 */
    public Panel buildHeaderInfo(UserDetails userDetails) {
        FSHeader header = FSHeader.getInstance(userDetails);
        Panel info = header.buildRightPanel();
        info.setStyleName("headerInfo");
        return info;
    }	
	
		
	/**
	 * Returns the login information.
	 * 
	 * @return LoginSucess.
	 */
	public LoginSuccess getLoginDetail() {
		return loginDetail;
	}

	private void setLoginDetail(LoginSuccess loginDetail) {
		this.loginDetail = loginDetail;
	}
	
	/**
	 * Returns the plant.
	 * 
	 * @return LoginSucess.
	 */
	public String getPlant() {
		
		return login.getPreprocessParamValues().get(PLANT_PREPROCESS_PARAM_KEY);
	}	

	/**
	 * Initialize the application.
	 */
	protected void init()
	{
		new CodeDictionaryFactory().init();
	}

	/**
	 * {@inheritDoc}
	 */
	public String getAppName()
	{
		return txtConsts.AppName();
	}
}
