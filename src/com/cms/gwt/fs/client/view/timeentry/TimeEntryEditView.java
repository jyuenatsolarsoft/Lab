package com.cms.gwt.fs.client.view.timeentry;

import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.presenter.TimeEntryEditPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class TimeEntryEditView extends Composite implements
		TimeEntryEditPresenter.View {

	private final VerticalPanel panel;
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
	private final HasHTML lblArrivalDate;
	private final HasText txtArrivalDate;
	private final HasHTML lblArrivalTime;
	private final HasText txtArrivalTime;
	private final HasHTML lblSequence;
	private final HasText txtSequence;
	private final HasHTML lblDescription;
	private final HasText txtDescription;

	// Time Entry > Add Time > Edit
	private final HasHTML lblEstimate;
	private final HasText txtEstimate;
	private final HasHTML lblEntered;
	private final HasText txtEntered;
	private final HasHTML lblActual;
	private final HasText txtActual;

	// Time Entry > Edit
	private final HasHTML lblLine;
	private final HasText txtLine;
	private final HasHTML lblTime;
	private final HasText txtTime;

	private final HasHTML lblOvertime;
	private final HasName chkOvertime;
	private final HasHTML lblWarranty;
	private final HasName chkWarranty;

	private final HasClickHandlers btnUpdate;
	private final HasClickHandlers btnCancel;

	/**
	 * Constructor.
	 */
	public TimeEntryEditView() {
		panel = new VerticalPanel();
		panel.setStyleName("TimeEntry");
		initWidget(panel);

		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		lblTicketNumber = new HTML(txtConsts.TETicketNumber());
		txtTicketNumber = new TextBox();
		lblEventId = new HTML(txtConsts.TEEventID());
		txtEventId = new TextBox();
		lblCounterReading = new HTML(txtConsts.TECounterReading());
		txtCounterReading = new TextBox();
		lblTechnician = new HTML(txtConsts.TETechnician());
		txtTechnician = new TextBox();
		txtTechnicianDescription = new TextBox();
		lblArrivalDate = new HTML(txtConsts.TEArrivalDate());
		txtArrivalDate = new TextBox();
		lblArrivalTime = new HTML(txtConsts.TEArrivalTime());
		txtArrivalTime = new TextBox();
		lblSequence = new HTML(txtConsts.TESequence());
		txtSequence = new TextBox();
		lblDescription = new HTML(txtConsts.TEDescription());
		txtDescription = new TextBox();
		lblEstimate = new HTML(txtConsts.TEEstimate());
		txtEstimate = new TextBox();
		lblEntered = new HTML(txtConsts.TEEntered());
		txtEntered = new TextBox();
		lblActual = new HTML(txtConsts.TEActual());
		txtActual = new TextBox();
		lblLine = new HTML(txtConsts.TELine());
		txtLine = new TextBox();
		lblTime = new HTML(txtConsts.TETime());
		txtTime = new TextBox();
		lblOvertime = new HTML(txtConsts.TEOvertime());
		chkOvertime = new CheckBox();
		lblWarranty = new HTML(txtConsts.TEWarranty());
		chkWarranty = new CheckBox();

		btnUpdate = new Button(txtConsts.Update());
		btnCancel = new Button(txtConsts.Cancel());

		layout();

		// by-default set some fields to read-only
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
		txtTicketNumber.setText("");
		txtEventId.setText("");
		txtCounterReading.setText("");
		txtTechnician.setText("");
		txtTechnicianDescription.setText("");
		txtArrivalDate.setText("");
		txtArrivalTime.setText("");
		txtSequence.setText("");
		txtDescription.setText("");
		txtEstimate.setText("");
		txtEntered.setText("");
		txtActual.setText("");
		txtLine.setText("");
		txtTime.setText("");
		((CheckBox) chkOvertime).setValue(false);
		((CheckBox) chkWarranty).setValue(false);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setReadOnly(boolean readOnly) {
		// boolean enabled = !readOnly;
		((TextBox) txtTicketNumber).setReadOnly(readOnly);
		((TextBox) txtEventId).setReadOnly(readOnly);
		((TextBox) txtCounterReading).setReadOnly(readOnly);
		((TextBox) txtTechnician).setReadOnly(readOnly);
		((TextBox) txtTechnicianDescription).setReadOnly(readOnly);
		((TextBox) txtArrivalDate).setReadOnly(readOnly);
		((TextBox) txtArrivalTime).setReadOnly(readOnly);
		((TextBox) txtSequence).setReadOnly(readOnly);
		((TextBox) txtDescription).setReadOnly(readOnly);
		((TextBox) txtEstimate).setReadOnly(readOnly);
		((TextBox) txtEntered).setReadOnly(readOnly);
		// ((TextBox) txtActual).setReadOnly(readOnly);
		((TextBox) txtLine).setReadOnly(readOnly);
		// ((TextBox) txtTime).setReadOnly(readOnly);
		// ((CheckBox) chkOvertime).setEnabled(enabled);
		// ((CheckBox) chkWarranty).setEnabled(enabled);
	}

	public void setType(boolean isAdd) {
		((HTML) lblEstimate).setVisible(!isAdd);
		((TextBox) txtEstimate).setVisible(!isAdd);
		((HTML) lblEntered).setVisible(!isAdd);
		((TextBox) txtEntered).setVisible(!isAdd);
		((HTML) lblActual).setVisible(!isAdd);
		((TextBox) txtActual).setVisible(!isAdd);
		((HTML) lblLine).setVisible(isAdd);
		((TextBox) txtLine).setVisible(isAdd);
		((HTML) lblTime).setVisible(isAdd);
		((TextBox) txtTime).setVisible(isAdd);
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

	public HasText getTxtArrivalDate() {
		return txtArrivalDate;
	}

	public HasText getTxtArrivalTime() {
		return txtArrivalTime;
	}

	public HasText getTxtSequence() {
		return txtSequence;
	}

	public HasText getTxtDescription() {
		return txtDescription;
	}

	public HasText getTxtEstimate() {
		return txtEstimate;
	}

	public HasText getTxtEntered() {
		return txtEntered;
	}

	public HasText getTxtActual() {
		return txtActual;
	}

	public HasText getTxtLine() {
		return txtLine;
	}

	public HasText getTxtTime() {
		return txtTime;
	}

	public HasName getChkOvertime() {
		return chkOvertime;
	}

	public HasName getChkWarranty() {
		return chkWarranty;
	}

	public HasClickHandlers getBtnUpdate() {
		return btnUpdate;
	}

	public HasClickHandlers getBtnCancel() {
		return btnCancel;
	}

	/**
	 * Arrange widgets in a nice layout
	 */
	private void layout() {
		DecoratorPanel tableContainer = new DecoratorPanel();
		FlexTable table = new FlexTable();
		tableContainer.add(table);

		panel.add(tableContainer);

		table.getColumnFormatter().setWidth(0, "150px");
		table.getColumnFormatter().setWidth(1, "200px");
		table.getColumnFormatter().setWidth(2, "150px");
		table.getColumnFormatter().setWidth(3, "200px");
		int row = 0, column = 0;
		table.setWidget(row, column, (Widget) lblTicketNumber);
		column++;
		table.setWidget(row, column, (Widget) txtTicketNumber);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblEventId);
		column++;
		table.setWidget(row, column, (Widget) txtEventId);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblCounterReading);
		column++;
		table.setWidget(row, column, (Widget) txtCounterReading);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblTechnician);
		column++;
		table.setWidget(row, column, (Widget) txtTechnician);
		column++;
		((TextBox) txtTechnicianDescription).addStyleName("fsInput-Large");
		table.getFlexCellFormatter().setColSpan(row, column, 2);
		table.setWidget(row, column, (Widget) txtTechnicianDescription);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblArrivalDate);
		column++;
		table.setWidget(row, column, (Widget) txtArrivalDate);
		column++;
		table.setWidget(row, column, (Widget) lblArrivalTime);
		column++;
		table.setWidget(row, column, (Widget) txtArrivalTime);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblSequence);
		column++;
		table.setWidget(row, column, (Widget) txtSequence);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblDescription);
		column++;
		table.setWidget(row, column, (Widget) txtDescription);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblEstimate);
		column++;
		table.setWidget(row, column, (Widget) txtEstimate);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblEntered);
		column++;
		table.setWidget(row, column, (Widget) txtEntered);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblActual);
		column++;
		table.setWidget(row, column, (Widget) txtActual);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblLine);
		column++;
		table.setWidget(row, column, (Widget) txtLine);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblTime);
		column++;
		table.setWidget(row, column, (Widget) txtTime);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblOvertime);
		column++;
		table.setWidget(row, column, (Widget) chkOvertime);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblWarranty);
		column++;
		table.setWidget(row, column, (Widget) chkWarranty);

		HorizontalPanel btnPanel = new HorizontalPanel();
		btnPanel.setStyleName("BtnPanel");
		btnPanel.add((Widget) btnUpdate);
		btnPanel.add((Widget) btnCancel);
		panel.add(btnPanel);
	}
}
