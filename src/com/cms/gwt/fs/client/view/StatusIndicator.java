package com.cms.gwt.fs.client.view;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Fancy class to show Status Indicator which indicates application is busy
 * making RPC to get data.
 * 
 */
public class StatusIndicator {

	private static int counter = 0;

	public static void show() {
		counter++;
		DOM.getElementById("loader").setAttribute("style", "display:block;");
		RootPanel.get().addStyleName("busyStatus");
		jnsiShow();
	}

	public static void hide() {
		counter--;
		if (counter <= 0) {
			DOM.getElementById("loader").setAttribute("style", "display:none;");
			RootPanel.get().removeStyleName("busyStatus");
			jnsiHide();
		}
	}

	public static void clear() {
		counter = 0;
		hide();
	}

	// IE hacks
	private native static void jnsiShow() /*-{
		$wnd.showLoader(true);
	}-*/;

	private native static void jnsiHide() /*-{
		$wnd.showLoader(false);
	}-*/;
}
