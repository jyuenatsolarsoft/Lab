package com.cms.gwt.fs.client.view.workhistory;

import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.presenter.WorkHistoryArrivalInfoPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtext.client.widgets.form.DateField;

public class ArrivalInfoView extends Composite implements
		WorkHistoryArrivalInfoPresenter.View {

	private final VerticalPanel panel;
	private final DecoratorPanel decorPanel;
	private final FlexTable flexTable;
	private final TextConstants txtConsts;

	private final HasHTML lblTicketNumber;
	private final HasText txtTicketNumber;
	private final HasHTML lblEventId;
	private final HasText txtEventId;
	private final HasHTML lblArrivalDate;
	private final DateField dfArrivalDate;
	private final HasHTML lblArrivalTime;
	private final HasText txtArrivalTimeHour;
	private final HasText txtArrivalTimeMinute;
	private final HasClickHandlers btnSave;
	private final HasClickHandlers btnCancel;

	/**
	 * Constructor.
	 */
	public ArrivalInfoView() {
		panel = new VerticalPanel();
		panel.setStyleName("WHCounter");
		initWidget(panel);

		decorPanel = new DecoratorPanel();
		flexTable = new FlexTable();
		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		lblTicketNumber = new HTML(txtConsts.WHTicketNumber());
		txtTicketNumber = new TextBox();
		lblEventId = new HTML(txtConsts.WHEventId());
		txtEventId = new TextBox();
		lblArrivalDate = new HTML(txtConsts.WHArrivalDate());
		dfArrivalDate = new DateField();
		lblArrivalTime = new HTML(txtConsts.WHArrivalTime());
		txtArrivalTimeHour = new TextBox();
		txtArrivalTimeMinute = new TextBox();
		btnSave = new Button(txtConsts.Save());
		btnCancel = new Button(txtConsts.Cancel());

		layout();

		// by-default some items read-only
		setReadOnly(true);

		dfArrivalDate.setFormat("Y-m-d");
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
		dfArrivalDate.setValue("");
		((TextBox) txtArrivalTimeHour).setText("");
		((TextBox) txtArrivalTimeMinute).setText("");
	}

	/**
	 * {@inheritDoc}
	 */
	public void setReadOnly(boolean readOnly) {
		((TextBox) txtTicketNumber).setReadOnly(readOnly);
		((TextBox) txtEventId).setReadOnly(readOnly);
		// dfArrivalDate.setReadOnly(readOnly);
		// dfArrivalDate.setDisabled(readOnly);
		// ((TextBox) txtArrivalTimeHour).setReadOnly(readOnly);
		// ((TextBox) txtArrivalTimeMinute).setReadOnly(readOnly);
	}

	public HasText getTxtTicketNumber() {
		return txtTicketNumber;
	}

	public HasText getTxtEventId() {
		return txtEventId;
	}

	public DateField getDfArrivalDate() {
		return dfArrivalDate;
	}

	public HasText getTxtArrivalTimeHour() {
		return txtArrivalTimeHour;
	}

	public HasText getTxtArrivalTimeMinute() {
		return txtArrivalTimeMinute;
	}

	public HasClickHandlers getBtnSave() {
		return btnSave;
	}

	public HasClickHandlers getBtnCancel() {
		return btnCancel;
	}

	/**
	 * Arrange widgets in a nice layout.
	 */
	private void layout() {
		panel.add(decorPanel);
		decorPanel.add(flexTable);

		flexTable.getColumnFormatter().setWidth(0, "150px");
		flexTable.getColumnFormatter().setWidth(1, "180px");

		int row = 0, column = 0;
		flexTable.setWidget(row, column, (Widget) lblTicketNumber);
		column++;
		flexTable.setWidget(row, column, (Widget) txtTicketNumber);
		column = 0;
		row++;
		flexTable.setWidget(row, column, (Widget) lblEventId);
		column++;
		flexTable.setWidget(row, column, (Widget) txtEventId);
		column = 0;
		row++;
		flexTable.setWidget(row, column, (Widget) lblArrivalDate);
		column++;
		flexTable.setWidget(row, column, (Widget) dfArrivalDate);
		column = 0;
		row++;
		flexTable.setWidget(row, column, (Widget) lblArrivalTime);
		column++;
		HorizontalPanel hpArrivalTime = new HorizontalPanel();
		((TextBox) txtArrivalTimeHour).setStyleName("fsInput-Small");
		hpArrivalTime.add((Widget) txtArrivalTimeHour);
		hpArrivalTime.add(new HTML(":"));
		((TextBox) txtArrivalTimeMinute).setStyleName("fsInput-Small");
		hpArrivalTime.add((Widget) txtArrivalTimeMinute);
		flexTable.setWidget(row, column, (Widget) hpArrivalTime);

		HorizontalPanel btnPanel = new HorizontalPanel();
		btnPanel.setStyleName("BtnPanel");
		btnPanel.add((Widget) btnSave);
		btnPanel.add((Widget) btnCancel);
		panel.add(btnPanel);

	}

}
