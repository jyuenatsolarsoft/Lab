package com.cms.gwt.fs.client.view.workhistory;

import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.presenter.WorkHistoryCounterPresenter;
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

public class CounterView extends Composite implements
		WorkHistoryCounterPresenter.View {

	private final VerticalPanel panel;
	private final DecoratorPanel decorPanel;
	private final FlexTable flexTable;
	private final TextConstants txtConsts;

	private final HasHTML lblTicketNumber;
	private final HasText txtTicketNumber;
	private final HasHTML lblCounterDescription;
	private final HasText txtCounterDescription;
	private final HasHTML lblCounterReading;
	private final HasText txtCounterReading;
	private final HasClickHandlers btnSave;
	private final HasClickHandlers btnCancel;

	/**
	 * Constructor.
	 */
	public CounterView() {
		panel = new VerticalPanel();
		panel.setStyleName("WHCounter");
		initWidget(panel);

		decorPanel = new DecoratorPanel();
		flexTable = new FlexTable();
		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		lblTicketNumber = new HTML(txtConsts.WHTicketNumber());
		txtTicketNumber = new TextBox();
		lblCounterDescription = new HTML(txtConsts.WHCounterDescription());
		txtCounterDescription = new TextBox();
		lblCounterReading = new HTML(txtConsts.WHCounterReading());
		txtCounterReading = new TextBox();
		btnSave = new Button(txtConsts.Save());
		btnCancel = new Button(txtConsts.Cancel());

		layout();

		// by-default some items read-only
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
		((TextBox) txtCounterDescription).setText("");
		((TextBox) txtCounterReading).setText("");
	}

	/**
	 * {@inheritDoc}
	 */
	public void setReadOnly(boolean readOnly) {
		((TextBox) txtTicketNumber).setReadOnly(readOnly);
		((TextBox) txtCounterDescription).setReadOnly(readOnly);
		// ((TextBox) txtCounterReading).setReadOnly(readOnly);
	}

	public HasText getTxtTicketNumber() {
		return txtTicketNumber;
	}

	public HasText getTxtCounterDescription() {
		return txtCounterDescription;
	}

	public HasText getTxtCounterReading() {
		return txtCounterReading;
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
		flexTable.setWidget(row, column, (Widget) lblCounterDescription);
		column++;
		flexTable.setWidget(row, column, (Widget) txtCounterDescription);
		column = 0;
		row++;
		flexTable.setWidget(row, column, (Widget) lblCounterReading);
		column++;
		flexTable.setWidget(row, column, (Widget) txtCounterReading);

		HorizontalPanel btnPanel = new HorizontalPanel();
		btnPanel.setStyleName("BtnPanel");
		btnPanel.add((Widget) btnSave);
		btnPanel.add((Widget) btnCancel);
		panel.add(btnPanel);

	}

}
