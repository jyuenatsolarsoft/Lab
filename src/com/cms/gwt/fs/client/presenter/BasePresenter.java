package com.cms.gwt.fs.client.presenter;

import com.cms.gwt.fs.client.view.BaseView;

/**
 * The base presenter interface for all presenter implementations in the GWT MVP design pattern.
 */
public interface BasePresenter {

	/**
	 * Gets the view this presenter is associated with.
	 * 
	 * @return The view for this presenter.
	 */
	BaseView getView();

	BaseView getPanelView();
	
	/**
	 * Sets the visibility of the view of this presenter
	 * 
	 * @param visible
	 *            If true then make visible else hide the view.
	 */
	void setVisible(boolean visible);
	
	void setPanelVisible(boolean visible);

	/**
	 * Reset the view to its original state.
	 */
	void reset();

	void resetPanel();
}
