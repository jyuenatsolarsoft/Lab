package com.cms.gwt.fs.client.view.workhistory;

import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class Summary extends Composite implements BaseView {

	private final FlexTable panel;
	private final TextConstants txtConsts;

	private final HasHTML lblTicketNumber;
	private final HasText txtTicketNumber;
	private final HasHTML lblEventId;
	private final HasText txtEventId;
	private final HasHTML lblCounterReading;
	private final HasText txtCounterReading;
	private final HasHTML lblTechnician;
	private final HasText txtTechnician;
	private final HasText txtTechnicianDescription;
	private final HasHTML lblDate;
	private final HasText txtDate;
	private final HasHTML lblArrivalTime;
	private final HasText txtArrivalTime;
	private final HasHTML lblHoursReported;
	private final HasText txtHoursReported;
	private final HasHTML lblMaterialReported;
	private final HasName chkMaterialReported;
	private final HasHTML lblNumberOfItems;
	private final HasText txtNumberOfItems;
	private final HasHTML lblOthersReported;
	private final HasName chkOthersReported;

	/**
	 * Constructor.
	 */
	public Summary() {
		panel = new FlexTable();
		panel.setStyleName("WHSummaryTab");
		initWidget(panel);

		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		lblTicketNumber = new HTML(txtConsts.WHTicketNumber());
		txtTicketNumber = new TextBox();
		lblEventId = new HTML(txtConsts.WHEventId());
		txtEventId = new TextBox();
		lblCounterReading = new HTML(txtConsts.WHCounterReading());
		txtCounterReading = new TextBox();
		lblTechnician = new HTML(txtConsts.WHTechnician());
		txtTechnician = new TextBox();
		txtTechnicianDescription = new TextBox();
		lblDate = new HTML(txtConsts.WHDate());
		txtDate = new TextBox();
		lblArrivalTime = new HTML(txtConsts.WHArrivalTime());
		txtArrivalTime = new TextBox();
		lblHoursReported = new HTML(txtConsts.WHHoursReported());
		txtHoursReported = new TextBox();
		lblMaterialReported = new HTML(txtConsts.WHMaterialReported());
		chkMaterialReported = new CheckBox();
		lblNumberOfItems = new HTML(txtConsts.WHNumberOfItems());
		txtNumberOfItems = new TextBox();
		lblOthersReported = new HTML(txtConsts.WHOtherReported());
		chkOthersReported = new CheckBox();

		layout();

		// by-default read only
		setReadOnly(true);
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
		((TextBox) txtTicketNumber).setText("");
		((TextBox) txtEventId).setText("");
		((TextBox) txtCounterReading).setText("");
		((TextBox) txtTechnician).setText("");
		((TextBox) txtTechnicianDescription).setText("");
		((TextBox) txtDate).setText("");
		((TextBox) txtArrivalTime).setText("");
		((TextBox) txtHoursReported).setText("");
		((CheckBox) chkMaterialReported).setValue(false);
		((TextBox) txtNumberOfItems).setText("");
		((CheckBox) chkOthersReported).setValue(false);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setReadOnly(boolean readOnly) {
		boolean enabled = !readOnly;
		((TextBox) txtTicketNumber).setReadOnly(readOnly);
		((TextBox) txtEventId).setReadOnly(readOnly);
		((TextBox) txtCounterReading).setReadOnly(readOnly);
		((TextBox) txtTechnician).setReadOnly(readOnly);
		((TextBox) txtTechnicianDescription).setReadOnly(readOnly);
		((TextBox) txtDate).setReadOnly(readOnly);
		((TextBox) txtArrivalTime).setReadOnly(readOnly);
		((TextBox) txtHoursReported).setReadOnly(readOnly);
		((CheckBox) chkMaterialReported).setEnabled(enabled);
		((TextBox) txtNumberOfItems).setReadOnly(readOnly);
		((CheckBox) chkOthersReported).setEnabled(enabled);
	}

	public HasText getTxtTicketNumber() {
		return txtTicketNumber;
	}

	public HasText getTxtEventId() {
		return txtEventId;
	}

	public HasText getTxtCounterReading() {
		return txtCounterReading;
	}

	public HasText getTxtTechnician() {
		return txtTechnician;
	}

	public HasText getTxtTechnicianDescription() {
		return txtTechnicianDescription;
	}

	public HasText getTxtDate() {
		return txtDate;
	}

	public HasText getTxtArrivalTime() {
		return txtArrivalTime;
	}

	public HasText getTxtHoursReported() {
		return txtHoursReported;
	}

	public HasName getChkMaterialReported() {
		return chkMaterialReported;
	}

	public HasText getTxtNumberOfItems() {
		return txtNumberOfItems;
	}

	public HasName getChkOthersReported() {
		return chkOthersReported;
	}

	/**
	 * Arrange widgets in a nice layout.
	 */
	private void layout() {
		int row = 0, column = 0;

		panel.setWidget(row, column, (Widget) lblTicketNumber);
		column++;
		panel.setWidget(row, column, (Widget) txtTicketNumber);
		column = 0;
		row++;
		panel.setWidget(row, column, (Widget) lblEventId);
		column++;
		panel.setWidget(row, column, (Widget) txtEventId);
		column = 0;
		row++;
		panel.setWidget(row, column, (Widget) lblCounterReading);
		column++;
		panel.setWidget(row, column, (Widget) txtCounterReading);
		column = 0;
		row++;
		panel.setWidget(row, column, (Widget) lblTechnician);
		column++;
		panel.setWidget(row, column, (Widget) txtTechnician);
		column++;
		((TextBox) txtTechnicianDescription).addStyleName("fsInput-Large");
		panel.getFlexCellFormatter().setColSpan(row, column, 2);
		panel.setWidget(row, column, (Widget) txtTechnicianDescription);
		column = 0;
		row++;
		panel.setWidget(row, column, (Widget) lblDate);
		column++;
		panel.setWidget(row, column, (Widget) txtDate);
		column++;
		panel.setWidget(row, column, (Widget) lblArrivalTime);
		column++;
		panel.setWidget(row, column, (Widget) txtArrivalTime);
		column = 0;
		row++;
		panel.setWidget(row, column, (Widget) lblHoursReported);
		column++;
		panel.setWidget(row, column, (Widget) txtHoursReported);
		column = 0;
		row++;
		panel.setWidget(row, column, (Widget) lblMaterialReported);
		column++;
		panel.setWidget(row, column, (Widget) chkMaterialReported);
		column++;
		panel.setWidget(row, column, (Widget) lblNumberOfItems);
		column++;
		panel.setWidget(row, column, (Widget) txtNumberOfItems);
		column = 0;
		row++;
		panel.setWidget(row, column, (Widget) lblOthersReported);
		column++;
		panel.setWidget(row, column, (Widget) chkOthersReported);

	}
}
