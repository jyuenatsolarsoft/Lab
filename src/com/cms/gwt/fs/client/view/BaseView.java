package com.cms.gwt.fs.client.view;

import com.google.gwt.user.client.ui.Widget;

/**
 * The base view interface for all the views in the GWT MVP design pattern.
 * 
 */
public interface BaseView {

	/**
	 * Gets the view
	 */
	Widget getWidget();

	/**
	 * Sets if the items are read-only in this view.
	 * 
	 * @param readOnly
	 *            If true then set widgets in this view to read-only else make
	 *            widgets in this view editable.
	 */
	void setReadOnly(boolean readOnly);

	/**
	 * Resets all widgets to their original state in this view.
	 */
	void reset();

}
