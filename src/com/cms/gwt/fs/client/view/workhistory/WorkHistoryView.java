package com.cms.gwt.fs.client.view.workhistory;

import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.presenter.WorkHistoryPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class WorkHistoryView extends Composite implements
		WorkHistoryPresenter.View {

	private final VerticalPanel panel;
	private final TabPanel tabPanel;
	private final TextConstants txtConsts;

	private final Summary summary;

	private final HasClickHandlers btnCounter;
	private final HasClickHandlers btnTimeEntry;
	private final HasClickHandlers btnMaterial;
	private final HasClickHandlers btnComplete;
	private final HasClickHandlers btnArrivalInfo;
	private final HasClickHandlers btnCancel;

	/**
	 * Constructor.
	 */
	@Inject
	public WorkHistoryView(Summary summary) {
		panel = new VerticalPanel();
		panel.setStyleName("WorkHistory");
		initWidget(panel);

		tabPanel = new DecoratedTabPanel();
		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		this.summary = summary;

		btnCounter = new Button(txtConsts.WHCounter());
		btnTimeEntry = new Button(txtConsts.WHTimeEntry());
		btnMaterial = new Button(txtConsts.WHMaterial());
		btnComplete = new Button(txtConsts.WHComplete());
		btnArrivalInfo = new Button(txtConsts.WHArrivalInfo());
		btnCancel = new Button(txtConsts.Cancel());

		layout();

		// by-default select first tab
		tabPanel.selectTab(0);
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
		summary.reset();
	}

	/**
	 * {@inheritDoc}
	 */
	public void setReadOnly(boolean readOnly) {
		summary.setReadOnly(readOnly);
	}

	// Main panel
	public TabPanel getTabPanel() {
		return tabPanel;
	}

	public HasClickHandlers getBtnCounter() {
		return btnCounter;
	}

	public HasClickHandlers getBtnTimeEntry() {
		return btnTimeEntry;
	}

	public HasClickHandlers getBtnMaterial() {
		return btnMaterial;
	}

	public HasClickHandlers getBtnComplete() {
		return btnComplete;
	}

	public HasClickHandlers getBtnArrivalInfo() {
		return btnArrivalInfo;
	}

	public HasClickHandlers getBtnCancel() {
		return btnCancel;
	}

	// Summary Tab
	public HasText getTxtTicketNumber() {
		return summary.getTxtTicketNumber();
	}

	public HasText getTxtEventId() {
		return summary.getTxtEventId();
	}

	public HasText getTxtCounterReading() {
		return summary.getTxtCounterReading();
	}

	public HasText getTxtTechnician() {
		return summary.getTxtTechnician();
	}

	public HasText getTxtTechnicianDescription() {
		return summary.getTxtTechnicianDescription();
	}

	public HasText getTxtDate() {
		return summary.getTxtDate();
	}

	public HasText getTxtArrivalTime() {
		return summary.getTxtArrivalTime();
	}

	public HasText getTxtHoursReported() {
		return summary.getTxtHoursReported();
	}

	public HasName getChkMaterialReported() {
		return summary.getChkMaterialReported();
	}

	public HasText getTxtNumberOfItems() {
		return summary.getTxtNumberOfItems();
	}

	public HasName getChkOthersReported() {
		return summary.getChkOthersReported();
	}

	/**
	 * Arrange widgets in a nice layout.
	 */
	private void layout() {

		panel.add(tabPanel);

		tabPanel.add(summary.getWidget(), txtConsts.WHSummary());

		HorizontalPanel btnPanel = new HorizontalPanel();
		btnPanel.setStyleName("BtnPanel");
		btnPanel.add((Widget) btnCounter);
		btnPanel.add((Widget) btnTimeEntry);
		btnPanel.add((Widget) btnMaterial);
		btnPanel.add((Widget) btnComplete);
		btnPanel.add((Widget) btnArrivalInfo);
		btnPanel.add((Widget) btnCancel);
		panel.add(btnPanel);
	}
}
