package com.cms.gwt.fs.server.util;

import java.io.File;
import java.net.URL;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Trace
{    
    /** The name of the appender. It is also used in log4j.xml */
    final static private String APPENDER_NAME = "TraceAppender";

    /** The log filename. */
    final static private String LOG_FILENAME = "fieldServices.log";

    /**
     * The system property key for log directory.
     */
    public final String SYS_LOG_DIR_PROPERTY_KEY =  "com.cms.fs.log.directory";
    
    /**
     * The system property key for log level.
     */
    public final String SYS_LOG_LEVEL_PROPERTY_KEY =  "com.cms.fs.log.level";
    
    /**
     * Log4j log level.
     */
    public final String LEVEL_ERROR =  "ERROR";

    public void init()
    {
        // Load the log configuration from xml file.
        loadLogConfig();

        // Grab the root logger.
        Logger logger = Logger.getRootLogger();

        // Set the log path. It is retrieved from the properties file.
        setLogPath(logger);

        // Load the debugging level from the properties file. 
        setDebuggingLevel(logger);
    }

    private void loadLogConfig()
    {
        // log4j.xml contains the settings of the log4j.
        URL url = getClass().getResource("log4j.xml");
        org.apache.log4j.xml.DOMConfigurator.configure(url);
    }

	/**
     * Set the logging level from the properties file.
     *
     * If no such propery found, the default level will be "ERROR".
     *
     */
    private void setDebuggingLevel(Logger logger)
    {                
        logger.setLevel(Level.toLevel(System.getProperty(SYS_LOG_LEVEL_PROPERTY_KEY, LEVEL_ERROR)));
    }

    /**
     * Retrieve the log path from the system properties and set the log file path in log4j.
     */
    private void setLogPath(Logger logger) 
    {   
    	String logFolderPath = System.getProperty(SYS_LOG_DIR_PROPERTY_KEY);
    	
    	if (logFolderPath != null && !"".equals(logFolderPath))
    	{
            File logFolder = new File(logFolderPath);
            
            if (!logFolder.exists())
            {
                // Create the folder if it doesn't exist. Otherwise, log4j will fail.
                logFolder.mkdir();            
            }
    		    		
            FileAppender appender = (FileAppender)logger.getAppender(APPENDER_NAME);
            
            appender.setFile(logFolder + File.separator + LOG_FILENAME);
            
            appender.activateOptions();    		
    	}    
    }

    public static Logger getLogger(Class<?> clazz)
    {
        return Logger.getLogger(clazz);
    }
}
