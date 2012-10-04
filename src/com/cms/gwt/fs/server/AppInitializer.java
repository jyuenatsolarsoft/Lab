package com.cms.gwt.fs.server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.cms.gwt.fs.server.util.Trace;

public class AppInitializer implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sce) {
		// Empty method
		abc
	}

	public void contextInitialized(ServletContextEvent sce) {

		new Trace().init();
		
	}

}
