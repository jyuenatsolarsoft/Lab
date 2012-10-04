package com.cms.gwt.fs.client.view.timeentry;

import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.presenter.TimeEntryPresenter.TimeEntryHeaderType;
import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Header extends Composite implements BaseView {

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
	private final HasHTML lblTotalTimeRecorded;
	private final HasText txtTotalTimeRecorded;
	private final HasHTML lblTotalHoursWorked;
	private final HasText txtTotalHoursWorked;

	/**
	 * Constructor.
	 */
	public Header() {
		panel = new VerticalPanel();
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
		lblTotalTimeRecorded = new HTML(txtConsts.TETotalTimeRecorded());
		txtTotalTimeRecorded = new TextBox();
		lblTotalHoursWorked = new HTML(txtConsts.TETotalHoursWorked());
		txtTotalHoursWorked = new TextBox();

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
		txtTicketNumber.setText("");
		txtEventId.setText("");
		txtCounterReading.setText("");
		txtTechnician.setText("");
		txtTechnicianDescription.setText("");
		txtArrivalDate.setText("");
		txtArrivalTime.setText("");
		txtTotalTimeRecorded.setText("");
		txtTotalHoursWorked.setText("");
	}

	/**
	 * {@inheritDoc}
	 */
	public void setReadOnly(boolean readOnly) {
		((TextBox) txtTicketNumber).setReadOnly(readOnly);
		((TextBox) txtEventId).setReadOnly(readOnly);
		((TextBox) txtCounterReading).setReadOnly(readOnly);
		((TextBox) txtTechnician).setReadOnly(readOnly);
		((TextBox) txtTechnicianDescription).setReadOnly(readOnly);
		((TextBox) txtArrivalDate).setReadOnly(readOnly);
		((TextBox) txtArrivalTime).setReadOnly(readOnly);
		((TextBox) txtTotalTimeRecorded).setReadOnly(readOnly);
		((TextBox) txtTotalHoursWorked).setReadOnly(readOnly);
	}

	public void setHeaderType(TimeEntryHeaderType timeEntryHeaderType) {

		HTML lTotalTimeRecorded = (HTML) lblTotalTimeRecorded;
		TextBox totalTimeRecorded = (TextBox) txtTotalTimeRecorded;
		HTML lTotalHoursWorked = (HTML) lblTotalHoursWorked;
		TextBox totalHoursWorked = (TextBox) txtTotalHoursWorked;

		if (timeEntryHeaderType.value == TimeEntryHeaderType.LABOUR_RECORDED.value) {
			lTotalTimeRecorded.setVisible(true);
			totalTimeRecorded.setVisible(true);
			lTotalHoursWorked.setVisible(false);
			totalHoursWorked.setVisible(false);

		} else if (timeEntryHeaderType.value == TimeEntryHeaderType.TIME_ENTRY.value) {
			lTotalTimeRecorded.setVisible(false);
			totalTimeRecorded.setVisible(false);
			lTotalHoursWorked.setVisible(true);
			totalHoursWorked.setVisible(true);

		}
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

	public HasText getTxtTotalTimeRecorded() {
		return txtTotalTimeRecorded;
	}

	public HasText getTxtTotalHoursWorked() {
		return txtTotalHoursWorked;
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
		table.setWidget(row, column, (Widget) lblTotalTimeRecorded);
		column++;
		table.setWidget(row, column, (Widget) txtTotalTimeRecorded);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblTotalHoursWorked);
		column++;
		table.setWidget(row, column, (Widget) txtTotalHoursWorked);
	}

}
