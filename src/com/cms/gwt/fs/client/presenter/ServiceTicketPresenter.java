package com.cms.gwt.fs.client.presenter;

import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TabPanel;

/**
 * Service Ticket's presenter interface.
 * 
 */
public interface ServiceTicketPresenter extends BasePresenter {

	interface View extends BaseView {
		HasText getTxtTicketNumber();

		HasText getTxtServiceType();

		HasText getTxtServiceItem();

		HasText getTxtDescription();

		HasText getTxtMainContact();

		HasName getLstComplaint();

		HasName getLstServiceProcedure();

		HasName getLstStatus();

		HasText getTxtDateOpened();

		HasText getTxtMainContactNumber();

		HasText getTxtSiteContact();

		HasText getTxtSiteContactNumber();

		HasText getTxtSecondNumber();

		HasText getTxtContactEmail();

		HasText getChkRepeatIssue();

		HasText getTxtPreviousTicket();

		HasName getLstPriorityCode();

		HasText getTxtSubject();

		HasText getChkConfirmationNeeded();

		HasText getChkConfirmed();

		HasText getTxtScheduledDate();

		HasText getTxtStartTime();

		HasText getTxtEffort();

		HasName getLstAssignedTo();

		HasText getTxtRespondByDate();

		HasText getTxtRespondByTime();

		HasText getTxtEstimatedCost();

		HasText getTxtCustomerPO();

		HasClickHandlers getBtnClose();

		DisclosurePanel getServiceTicketAdvanceContainer();

		TabPanel getTabPanel();

	}

	void showServiceTicket(String ticketNumber);

	void showServiceTicketAdvance(boolean showAdvance);

	void selectTabPanel(int panelNumber);

}
