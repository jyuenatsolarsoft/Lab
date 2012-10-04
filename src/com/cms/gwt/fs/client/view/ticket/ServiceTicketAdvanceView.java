package com.cms.gwt.fs.client.view.ticket;

import com.cms.gwt.fs.client.TextConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.CheckBox;
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
 * View showing details for Service Ticket header.
 *
 */
public class ServiceTicketAdvanceView extends Composite {

	private final TextConstants txtConsts;
	private final FlexTable advancedServiceTicket;

	private final HasHTML lblDateOpened;
	private final HasText txtDateOpened;
	private final HasHTML lblMainContactNumber;
	private final HasText txtMainContactNumber;
	private final HasHTML lblSiteContact;
	private final HasText txtSiteContact;
	private final HasHTML lblSiteContactNumber;
	private final HasText txtSiteContactNumber;
	private final HasHTML lblSecondNumber;
	private final HasText txtSecondNumber;
	private final HasHTML lblContactEmail;
	private final HasText txtContactEmail;
	private final HasText chkRepeatIssue;
	private final HasHTML lblPreviousTicket;
	private final HasText txtPreviousTicket;
	private final HasHTML lblPriorityCode;
	private final HasName lstPriorityCode;
	private final HasHTML lblSubject;
	private final HasText txtSubject;
	private final HasText chkConfirmationNeeded;
	private final HasText chkConfirmed;
	private final HasHTML lblScheduledDate;
	private final HasText txtScheduledDate;
	private final HasHTML lblStartTime;
	private final HasText txtStartTime;
	private final HasHTML lblEffort;
	private final HasText txtEffort;
	private final HasHTML lblAssignedTo;
	private final HasName lstAssignedTo;
	private final HasHTML lblRespondBy;
	private final HasText txtRespondByDate;
	private final HasText txtRespondByTime;
	private final HasHTML lblEstimatedCost;
	private final HasText txtEstimatedCost;
	private final HasHTML lblCustomerPO;
	private final HasText txtCustomerPO;

	/**
	 * Constructor.
	 */
	public ServiceTicketAdvanceView() {
		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		advancedServiceTicket = new FlexTable();
		initWidget(advancedServiceTicket);

		advancedServiceTicket.setWidget(0, 0, new HTML(
				"TEST THIS WOOO HOOO !!!"));

		lblDateOpened = new HTML(txtConsts.TicketDetailDateOpened());
		txtDateOpened = new TextBox();
		lblMainContactNumber = new HTML(txtConsts
				.TicketDetailMainContactNumber());
		txtMainContactNumber = new TextBox();
		lblSiteContact = new HTML(txtConsts.TicketDetailSiteContact());
		txtSiteContact = new TextBox();
		lblSiteContactNumber = new HTML(txtConsts
				.TicketDetailSiteContactNumber());
		txtSiteContactNumber = new TextBox();
		lblSecondNumber = new HTML(txtConsts.TicketDetailSecondNumber());
		txtSecondNumber = new TextBox();
		lblContactEmail = new HTML(txtConsts.TicketDetailContactEmail());
		txtContactEmail = new TextBox();
		chkRepeatIssue = new CheckBox(txtConsts.TicketDetailRepeatIssue());
		lblPreviousTicket = new HTML(txtConsts.TicketDetailPreviousTicket());
		txtPreviousTicket = new TextBox();
		lblPriorityCode = new HTML(txtConsts.TicketDetailPriorityCode());
		lstPriorityCode = new ListBox(false);
		lblSubject = new HTML(txtConsts.TicketDetailSubject());
		txtSubject = new TextBox();
		chkConfirmationNeeded = new CheckBox(txtConsts
				.TicketDetailConfirmationNeeded());
		chkConfirmed = new CheckBox(txtConsts.TicketDetailConfirmed());
		lblScheduledDate = new HTML(txtConsts.TicketDetailScheduledDate());
		txtScheduledDate = new TextBox();
		lblStartTime = new HTML(txtConsts.TicketDetailStartTime());
		txtStartTime = new TextBox();
		lblEffort = new HTML(txtConsts.TicketDetailEffort());
		txtEffort = new TextBox();
		lblAssignedTo = new HTML(txtConsts.TicketDetailAssignedTo());
		lstAssignedTo = new ListBox();
		lblRespondBy = new HTML(txtConsts.TicketDetailRespondBy());
		txtRespondByDate = new TextBox();
		txtRespondByTime = new TextBox();
		lblEstimatedCost = new HTML(txtConsts.TicketDetailEstimatedCost());
		txtEstimatedCost = new TextBox();
		lblCustomerPO = new HTML(txtConsts.TicketDetailCustomerPO());
		txtCustomerPO = new TextBox();

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

		((TextBox) txtDateOpened).setReadOnly(readOnly);
		((TextBox) txtMainContactNumber).setReadOnly(readOnly);
		((TextBox) txtSiteContact).setReadOnly(readOnly);
		((TextBox) txtSiteContactNumber).setReadOnly(readOnly);
		((TextBox) txtSecondNumber).setReadOnly(readOnly);
		((TextBox) txtContactEmail).setReadOnly(readOnly);
		((CheckBox) chkRepeatIssue).setEnabled(enabled);
		((TextBox) txtPreviousTicket).setReadOnly(readOnly);
		((ListBox) lstPriorityCode).setEnabled(enabled);
		((TextBox) txtSubject).setReadOnly(readOnly);
		((CheckBox) chkConfirmationNeeded).setEnabled(enabled);
		((CheckBox) chkConfirmed).setEnabled(enabled);
		((TextBox) txtScheduledDate).setReadOnly(readOnly);
		((TextBox) txtStartTime).setReadOnly(readOnly);
		((TextBox) txtEffort).setReadOnly(readOnly);
		((ListBox) lstAssignedTo).setEnabled(enabled);
		((TextBox) txtRespondByDate).setReadOnly(readOnly);
		((TextBox) txtRespondByTime).setReadOnly(readOnly);
		((TextBox) txtEstimatedCost).setReadOnly(readOnly);
		((TextBox) txtCustomerPO).setReadOnly(readOnly);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void reset() {
		((TextBox) txtDateOpened).setText("");
		((TextBox) txtMainContactNumber).setText("");
		((TextBox) txtSiteContact).setText("");
		((TextBox) txtSiteContactNumber).setText("");
		((TextBox) txtSecondNumber).setText("");
		((TextBox) txtContactEmail).setText("");
		((CheckBox) chkRepeatIssue).setValue(false);
		((TextBox) txtPreviousTicket).setText("");
		((ListBox) lstPriorityCode).clear();
		((TextBox) txtSubject).setText("");
		((CheckBox) chkConfirmationNeeded).setValue(false);
		((CheckBox) chkConfirmed).setValue(false);
		((TextBox) txtScheduledDate).setText("");
		((TextBox) txtStartTime).setText("");
		((TextBox) txtEffort).setText("");
		((ListBox) lstAssignedTo).clear();
		((TextBox) txtRespondByDate).setText("");
		((TextBox) txtRespondByTime).setText("");
		((TextBox) txtEstimatedCost).setText("");
		((TextBox) txtCustomerPO).setText("");
	}
	
	public HasText getTxtDateOpened() {
		return txtDateOpened;
	}

	public HasText getTxtMainContactNumber() {
		return txtMainContactNumber;
	}

	public HasText getTxtSiteContact() {
		return txtSiteContact;
	}

	public HasText getTxtSiteContactNumber() {
		return txtSiteContactNumber;
	}

	public HasText getTxtSecondNumber() {
		return txtSecondNumber;
	}

	public HasText getTxtContactEmail() {
		return txtContactEmail;
	}

	public HasText getChkRepeatIssue() {
		return chkRepeatIssue;
	}

	public HasText getTxtPreviousTicket() {
		return txtPreviousTicket;
	}

	public HasName getLstPriorityCode() {
		return lstPriorityCode;
	}

	public HasText getTxtSubject() {
		return txtSubject;
	}

	public HasText getChkConfirmationNeeded() {
		return chkConfirmationNeeded;
	}

	public HasText getChkConfirmed() {
		return chkConfirmed;
	}

	public HasText getTxtScheduledDate() {
		return txtScheduledDate;
	}

	public HasText getTxtStartTime() {
		return txtStartTime;
	}

	public HasText getTxtEffort() {
		return txtEffort;
	}

	public HasName getLstAssignedTo() {
		return lstAssignedTo;
	}

	public HasText getTxtRespondByDate() {
		return txtRespondByDate;
	}

	public HasText getTxtRespondByTime() {
		return txtRespondByTime;
	}

	public HasText getTxtEstimatedCost() {
		return txtEstimatedCost;
	}

	public HasText getTxtCustomerPO() {
		return txtCustomerPO;
	}

	/**
	 * Arrange widgets in a nice layout.
	 */
	private void layout() {
		advancedServiceTicket.setStyleName("ServiceTicketAdvanced");
		advancedServiceTicket.getColumnFormatter().setWidth(0, "150px");
		advancedServiceTicket.getColumnFormatter().setWidth(1, "180px");
		advancedServiceTicket.getColumnFormatter().setWidth(2, "150px");
		advancedServiceTicket.getColumnFormatter().setWidth(3, "180px");

		int row = 0, column = 0;
		advancedServiceTicket.setWidget(row, column, (Widget) lblDateOpened);
		column++;
		advancedServiceTicket.setWidget(row, column, (Widget) txtDateOpened);
		column = 0;
		row++;
		advancedServiceTicket
				.setWidget(row, column, (Widget) lblMainContactNumber);
		column++;
		advancedServiceTicket
				.setWidget(row, column, (Widget) txtMainContactNumber);
		column = 0;
		row++;
		advancedServiceTicket.setWidget(row, column, (Widget) lblSiteContact);
		column++;
		advancedServiceTicket.setWidget(row, column, (Widget) txtSiteContact);
		column++;
		advancedServiceTicket
				.setWidget(row, column, (Widget) lblSiteContactNumber);
		column++;
		advancedServiceTicket
				.setWidget(row, column, (Widget) txtSiteContactNumber);
		column = 2;
		row++;
		advancedServiceTicket.setWidget(row, column, (Widget) lblSecondNumber);
		column++;
		advancedServiceTicket.setWidget(row, column, (Widget) txtSecondNumber);
		column = 0;
		row++;
		advancedServiceTicket.setWidget(row, column, (Widget) lblContactEmail);
		column++;
		advancedServiceTicket.setWidget(row, column, (Widget) txtContactEmail);
		column = 1;
		row++;
		advancedServiceTicket.setWidget(row, column, (Widget) chkRepeatIssue);
		column++;
		advancedServiceTicket.setWidget(row, column, (Widget) lblPreviousTicket);
		column++;
		advancedServiceTicket.setWidget(row, column, (Widget) txtPreviousTicket);
		column = 0;
		row++;
		advancedServiceTicket.setWidget(row, column, (Widget) lblPriorityCode);
		column++;
		advancedServiceTicket.setWidget(row, column, (Widget) lstPriorityCode);
		column = 0;
		row++;
		advancedServiceTicket.setWidget(row, column, (Widget) lblSubject);
		column++;
		advancedServiceTicket.setWidget(row, column, (Widget) txtSubject);
		column = 1;
		row++;
		advancedServiceTicket.setWidget(row, column,
				(Widget) chkConfirmationNeeded);
		row++;
		advancedServiceTicket.setWidget(row, column, (Widget) chkConfirmed);
		column = 0;
		row++;
		advancedServiceTicket.setWidget(row, column, (Widget) lblScheduledDate);
		column++;
		advancedServiceTicket.setWidget(row, column, (Widget) txtScheduledDate);
		column = 0;
		row++;
		advancedServiceTicket.setWidget(row, column, (Widget) lblStartTime);
		column++;
		advancedServiceTicket.setWidget(row, column, (Widget) txtStartTime);
		column = 0;
		row++;
		advancedServiceTicket.setWidget(row, column, (Widget) lblEffort);
		column++;
		advancedServiceTicket.setWidget(row, column, (Widget) txtEffort);
		column = 0;
		row++;
		advancedServiceTicket.setWidget(row, column, (Widget) lblAssignedTo);
		column++;
		advancedServiceTicket.setWidget(row, column, (Widget) lstAssignedTo);
		column = 0;
		row++;
		advancedServiceTicket.setWidget(row, column, (Widget) lblRespondBy);
		column++;
		advancedServiceTicket.setWidget(row, column, (Widget) txtRespondByDate);
		column++;
		advancedServiceTicket.setWidget(row, column, (Widget) txtRespondByTime);
		column = 0;
		row++;
		advancedServiceTicket.setWidget(row, column, (Widget) lblEstimatedCost);
		column++;
		advancedServiceTicket.setWidget(row, column, (Widget) txtEstimatedCost);
		column = 0;
		row++;
		advancedServiceTicket.setWidget(row, column, (Widget) lblCustomerPO);
		column++;
		advancedServiceTicket.setWidget(row, column, (Widget) txtCustomerPO);

	}

}
