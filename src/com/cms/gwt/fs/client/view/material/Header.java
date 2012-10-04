package com.cms.gwt.fs.client.view.material;

import com.cms.gwt.fs.client.TextConstants;
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
	private final HasText txtCounterReadingDescription;
	private final HasHTML lblTechnician;
	private final HasText txtTechnician;
	private final HasText txtTechnicianDescription;
	private final HasHTML lblArrivalDate;
	private final HasText txtArrivalDate;
	private final HasHTML lblArrivalTime;
	private final HasText txtArrivalTime;

	/**
	 * Constructor.
	 */
	public Header() {
		panel = new VerticalPanel();
		initWidget(panel);

		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		lblTicketNumber = new HTML(txtConsts.MatTicketNumber());
		txtTicketNumber = new TextBox();
		lblEventId = new HTML(txtConsts.MatEventId());
		txtEventId = new TextBox();
		lblCounterReading = new HTML(txtConsts.MatCounterReading());
		txtCounterReading = new TextBox();
		txtCounterReadingDescription = new TextBox();
		lblTechnician = new HTML(txtConsts.MatTechnician());
		txtTechnician = new TextBox();
		txtTechnicianDescription = new TextBox();
		lblArrivalDate = new HTML(txtConsts.MatArrivalDate());
		txtArrivalDate = new TextBox();
		lblArrivalTime = new HTML(txtConsts.MatArrivalTime());
		txtArrivalTime = new TextBox();

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
		txtCounterReadingDescription.setText("");
		txtTechnician.setText("");
		txtTechnicianDescription.setText("");
		txtArrivalDate.setText("");
		txtArrivalTime.setText("");
	}

	/**
	 * {@inheritDoc}
	 */
	public void setReadOnly(boolean readOnly) {
		((TextBox) txtTicketNumber).setReadOnly(readOnly);
		((TextBox) txtEventId).setReadOnly(readOnly);
		((TextBox) txtCounterReading).setReadOnly(readOnly);
		((TextBox) txtCounterReadingDescription).setReadOnly(readOnly);
		((TextBox) txtTechnician).setReadOnly(readOnly);
		((TextBox) txtTechnicianDescription).setReadOnly(readOnly);
		((TextBox) txtArrivalDate).setReadOnly(readOnly);
		((TextBox) txtArrivalTime).setReadOnly(readOnly);
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

	public HasText getTxtCounterReadingDescription() {
		return txtCounterReadingDescription;
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
		column++;
		((TextBox) txtCounterReadingDescription).addStyleName("fsInput-Large");
		table.getFlexCellFormatter().setColSpan(row, column, 2);
		table.setWidget(row, column, (Widget) txtCounterReadingDescription);
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

	}

}
