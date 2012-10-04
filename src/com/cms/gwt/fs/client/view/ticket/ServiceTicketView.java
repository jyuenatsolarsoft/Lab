package com.cms.gwt.fs.client.view.ticket;

import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.presenter.ServiceTicketPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class ServiceTicketView extends Composite implements
		ServiceTicketPresenter.View {

	private final TextConstants txtConsts;
	private ServiceTicketBasicView basicView;
	private ServiceTicketAdvanceView advanceView;

	private final VerticalPanel panel;
	private final DecoratorPanel serviceTicketBorder;
	private final VerticalPanel serviceTicketContainer;
	private final DisclosurePanel serviceTicketAdvanceContainer;
	private final TabPanel tabPanel;
	private final HasClickHandlers btnClose;

	@Inject
	public ServiceTicketView(ServiceTicketBasicView basicView,
			ServiceTicketAdvanceView advanceView) {
		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		this.basicView = basicView;
		this.advanceView = advanceView;

		panel = new VerticalPanel();
		panel.setStyleName("ServiceTicket");
		initWidget(panel);

		serviceTicketBorder = new DecoratorPanel();
		panel.add(serviceTicketBorder);

		serviceTicketContainer = new VerticalPanel();
		serviceTicketBorder.add(serviceTicketContainer);

		serviceTicketContainer.add(basicView.getWidget());

		serviceTicketAdvanceContainer = new DisclosurePanel("  "
				+ txtConsts.TicketDetailMoreDetails());
		serviceTicketAdvanceContainer.setAnimationEnabled(true);
		serviceTicketContainer.add(serviceTicketAdvanceContainer);
		serviceTicketAdvanceContainer.setContent(advanceView.getWidget());

		tabPanel = new DecoratedTabPanel();
		panel.add(tabPanel);

		btnClose = new Button(txtConsts.Close());
		panel.add((Widget) btnClose);
	}

	public Widget getWidget() {
		return this;
	}
	
	public void setReadOnly(boolean readOnly) {
		basicView.setReadOnly(readOnly);
		advanceView.setReadOnly(readOnly);
	}

	public void reset() {
		basicView.reset();
		advanceView.reset();		
	}
	
	public HasName getLstComplaint() {
		return basicView.getLstComplaint();
	}

	public HasName getLstServiceProcedure() {
		return basicView.getLstServiceProcedure();
	}

	public HasName getLstStatus() {
		return basicView.getLstStatus();
	}

	public HasText getTxtDescription() {
		return basicView.getTxtDescription();
	}

	public HasText getTxtMainContact() {
		return basicView.getTxtMainContact();
	}

	public HasText getTxtServiceItem() {
		return basicView.getTxtServiceItem();
	}

	public HasText getTxtServiceType() {
		return basicView.getTxtServiceType();
	}

	public HasText getTxtTicketNumber() {
		return basicView.getTxtTicketNumber();
	}

	public HasText getTxtDateOpened() {
		return advanceView.getTxtDateOpened();
	}

	public HasText getTxtMainContactNumber() {
		return advanceView.getTxtMainContactNumber();
	}

	public HasText getTxtSiteContact() {
		return advanceView.getTxtSiteContact();
	}

	public HasText getTxtSiteContactNumber() {
		return advanceView.getTxtSiteContactNumber();
	}

	public HasText getTxtSecondNumber() {
		return advanceView.getTxtSecondNumber();
	}

	public HasText getTxtContactEmail() {
		return advanceView.getTxtContactEmail();
	}

	public HasText getChkRepeatIssue() {
		return advanceView.getChkRepeatIssue();
	}

	public HasText getTxtPreviousTicket() {
		return advanceView.getTxtPreviousTicket();
	}

	public HasName getLstPriorityCode() {
		return advanceView.getLstPriorityCode();
	}

	public HasText getTxtSubject() {
		return advanceView.getTxtSubject();
	}

	public HasText getChkConfirmationNeeded() {
		return advanceView.getChkConfirmationNeeded();
	}

	public HasText getChkConfirmed() {
		return advanceView.getChkConfirmed();
	}

	public HasText getTxtScheduledDate() {
		return advanceView.getTxtScheduledDate();
	}

	public HasText getTxtStartTime() {
		return advanceView.getTxtStartTime();
	}

	public HasText getTxtEffort() {
		return advanceView.getTxtEffort();
	}

	public HasName getLstAssignedTo() {
		return advanceView.getLstAssignedTo();
	}

	public HasText getTxtRespondByDate() {
		return advanceView.getTxtRespondByDate();
	}

	public HasText getTxtRespondByTime() {
		return advanceView.getTxtRespondByTime();
	}

	public HasText getTxtEstimatedCost() {
		return advanceView.getTxtEstimatedCost();
	}

	public HasText getTxtCustomerPO() {
		return advanceView.getTxtCustomerPO();
	}

	public HasClickHandlers getBtnClose() {
		return btnClose;
	}

	public DisclosurePanel getServiceTicketAdvanceContainer() {
		return serviceTicketAdvanceContainer;
	}

	public TabPanel getTabPanel() {
		return tabPanel;
	}

}
