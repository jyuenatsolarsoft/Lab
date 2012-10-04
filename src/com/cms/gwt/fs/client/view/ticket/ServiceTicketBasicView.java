package com.cms.gwt.fs.client.view.ticket;

import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * View showing basics for Service Ticket header.
 *
 */
public class ServiceTicketBasicView extends Composite implements BaseView {

	private final TextConstants txtConsts;
	private final FlexTable basicServiceTicket;

	private final HasHTML lblTicketNumber;
	private final HasText txtTicketNumber;
	private final HasHTML lblServiceType;
	private final HasText txtServiceType;
	private final HasHTML lblServiceItem;
	private final HasText txtServiceItem;
	private final HasHTML lblDescription;
	private final HasText txtDescription;
	private final HasHTML lblMainContact;
	private final HasText txtMainContact;
	private final HasHTML lblComplaint;
	private final HasName lstComplaint;
	private final HasHTML lblServiceProcedure;
	private final HasName lstServiceProcedure;
	private final HasHTML lblStatus;
	private final HasName lstStatus;

	/**
	 * Constructor.
	 */
	public ServiceTicketBasicView() {
		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		basicServiceTicket = new FlexTable();
		initWidget(basicServiceTicket);

		lblTicketNumber = new HTML(txtConsts.TicketDetailTicketNumber());
		txtTicketNumber = new TextBox();
		lblServiceType = new HTML(txtConsts.TicketDetailServiceType());
		txtServiceType = new TextBox();
		lblServiceItem = new HTML(txtConsts.TicketDetailServiceItem());
		txtServiceItem = new TextBox();
		lblDescription = new HTML(txtConsts.TicketDetailDescription());
		txtDescription = new TextBox();
		lblMainContact = new HTML(txtConsts.TicketDetailMainContact());
		txtMainContact = new TextBox();
		lblComplaint = new HTML(txtConsts.TicketDetailComplaint());
		lstComplaint = new ListBox(false);
		lblServiceProcedure = new HTML(txtConsts.TicketDetailServiceProcedure());
		lstServiceProcedure = new ListBox(false);
		lblStatus = new HTML(txtConsts.TicketDetailStatus());
		lstStatus = new ListBox(false);

		layout();

		// by-default read-only
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
	public void setReadOnly(boolean readOnly) {
		boolean enabled = !readOnly;
		((TextBox) txtTicketNumber).setReadOnly(readOnly);
		((TextBox) txtServiceType).setReadOnly(readOnly);
		((TextBox) txtServiceItem).setReadOnly(readOnly);
		((TextBox) txtDescription).setReadOnly(readOnly);
		((TextBox) txtMainContact).setReadOnly(readOnly);
		((ListBox) lstComplaint).setEnabled(enabled);
		((ListBox) lstServiceProcedure).setEnabled(enabled);
		((ListBox) lstStatus).setEnabled(enabled);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void reset() {
		((TextBox) txtTicketNumber).setText("");
		((TextBox) txtServiceType).setText("");
		((TextBox) txtServiceItem).setText("");
		((TextBox) txtDescription).setText("");
		((TextBox) txtMainContact).setText("");
		((ListBox) lstComplaint).clear();
		((ListBox) lstServiceProcedure).clear();
		((ListBox) lstStatus).clear();
	}
	
	public HasText getTxtTicketNumber() {
		return txtTicketNumber;
	}

	public HasText getTxtServiceType() {
		return txtServiceType;
	}

	public HasText getTxtServiceItem() {
		return txtServiceItem;
	}

	public HasText getTxtDescription() {
		return txtDescription;
	}

	public HasText getTxtMainContact() {
		return txtMainContact;
	}

	public HasName getLstComplaint() {
		return lstComplaint;
	}

	public HasName getLstServiceProcedure() {
		return lstServiceProcedure;
	}

	public HasName getLstStatus() {
		return lstStatus;
	}

	/**
	 * Arrange widgets in a nice layout.
	 */
	private void layout() {
		basicServiceTicket.setStyleName("ServiceTicketBasic");
		basicServiceTicket.getColumnFormatter().setWidth(0, "150px");
		basicServiceTicket.getColumnFormatter().setWidth(1, "180px");
		basicServiceTicket.getColumnFormatter().setWidth(2, "150px");
		basicServiceTicket.getColumnFormatter().setWidth(3, "180px");

		int row = 0, column = 0;
		basicServiceTicket.setWidget(row, column, (Widget) lblTicketNumber);
		column++;
		basicServiceTicket.setWidget(row, column, (Widget) txtTicketNumber);
		column++;
		basicServiceTicket.setWidget(row, column, (Widget) lblServiceType);
		column++;
		basicServiceTicket.setWidget(row, column, (Widget) txtServiceType);
		column = 0;
		row++;
		basicServiceTicket.setWidget(row, column, (Widget) lblServiceItem);
		column++;
		basicServiceTicket.setWidget(row, column, (Widget) txtServiceItem);
		column++;
		basicServiceTicket.setWidget(row, column, (Widget) lblDescription);
		column++;
		basicServiceTicket.setWidget(row, column, (Widget) txtDescription);
		column = 0;
		row++;
		basicServiceTicket.setWidget(row, column, (Widget) lblMainContact);
		column++;
		basicServiceTicket.setWidget(row, column, (Widget) txtMainContact);
		column++;
		basicServiceTicket.setWidget(row, column, (Widget) lblComplaint);
		column++;
		basicServiceTicket.setWidget(row, column, (Widget) lstComplaint);
		column = 0;
		row++;
		basicServiceTicket.setWidget(row, column, (Widget) lblServiceProcedure);
		column++;
		basicServiceTicket.setWidget(row, column, (Widget) lstServiceProcedure);
		column++;
		basicServiceTicket.setWidget(row, column, (Widget) lblStatus);
		column++;
		basicServiceTicket.setWidget(row, column, (Widget) lstStatus);
	}

}
