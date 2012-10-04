package com.cms.gwt.fs.client.view.notes;

import com.cms.gwt.fs.client.presenter.NotesPresenter;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class NotesView extends Composite implements NotesPresenter.View {

	private final VerticalPanel containerPanel;
	private final DecoratedTabPanel panel;

	/**
	 * Constructor.
	 */
	@Inject
	public NotesView() {
		containerPanel = new VerticalPanel();
		containerPanel.addStyleName("Notes");
		initWidget(containerPanel);

		panel = new DecoratedTabPanel();
		containerPanel.add(panel);

		layout();
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
		//
	}

	/**
	 * {@inheritDoc}
	 */
	public void setReadOnly(boolean readOnly) {
		//
	}

	public DecoratedTabPanel getPanel() {
		return panel;
	}

	/**
	 * Arrange widgets in a nice layout.
	 */
	private void layout() {
	}

}
