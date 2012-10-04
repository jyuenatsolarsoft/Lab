package com.cms.gwt.fs.client.view;

import com.cms.gwt.fs.client.presenter.BreadCrumbPresenter;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Bread Crumb view 
 *
 */
public class BreadCrumbView extends Composite implements BreadCrumbPresenter.View {
	
	private final HorizontalPanel panel;

	/**
	 * Constructor.
	 */
	public BreadCrumbView() {
		HorizontalPanel decoratorPanel = new HorizontalPanel();
		decoratorPanel.setStyleName("BreadCrumb");
		
		panel = new HorizontalPanel();	
		decoratorPanel.add(panel);
		
		initWidget(decoratorPanel);		
	}

	/**
	 * {@inheritDoc}
	 */
	public Widget getWidget() {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset() {
		panel.clear();
	}

	/**
	 * {@inheritDoc}
	 */
	public void setReadOnly(boolean readOnly) {
	}
	
	/**
	 * {@inheritDoc}
	 */
	public HorizontalPanel getPanel() {
		return panel;
	}

}
