package com.cms.gwt.fs.client.util;

import com.allen_sauer.gwt.log.client.Log;
import com.cms.gwt.fs.client.FieldServices;

public class Logger {

	public static void debug(String msg) {
		String userid = FieldServices.getInstance().getUserId();

		Log.debug(userid + ": " + msg);
	}
	
	public static void info(String msg) {
		String userid = FieldServices.getInstance().getUserId();

		Log.info(userid + ": " + msg);
	}

	public static void error(String msg) {
		String userid = FieldServices.getInstance().getUserId();

		Log.error(userid + ": " + msg);
	}

	public static void error(String msg, Throwable e) {
		String userid = FieldServices.getInstance().getUserId();

		Log.error(userid + ": " + msg, e);
	}
}
