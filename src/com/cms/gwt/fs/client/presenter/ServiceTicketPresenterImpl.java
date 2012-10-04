package com.cms.gwt.fs.client.presenter;

import com.allen_sauer.gwt.log.client.Log;
import com.cms.gwt.common.client.ExtCmsMessageBox;
import com.cms.gwt.fs.client.HistoryConstants;
import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.exception.ActionNotSupportedException;
import com.cms.gwt.fs.client.model.ServiceTicket;
import com.cms.gwt.fs.client.rpc.ActionServices;
import com.cms.gwt.fs.client.rpc.action.GetServiceTicket;
import com.cms.gwt.fs.client.rpc.callback.GotServiceTicket;
import com.cms.gwt.fs.client.util.DateUtil;
import com.cms.gwt.fs.client.util.Logger;
import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.inject.Inject;

/**
 * Service Ticket presenter's implementation.
 * <p />
 * This class holds that basic ticket header, advance ticket details, and all
 * the tabs.
 * 
 */
public class ServiceTicketPresenterImpl extends BasePresenterImpl implements
		ServiceTicketPresenter {

	private final View view;
	private final TextConstants txtConsts;

	private final ServiceItemPresenter serviceItemPresenter;
	private final LocationPresenter locationPresenter;
	private final AccessHoursPresenter accessHoursPresenter;
	private final SkillsPresenter skillsPresenter;
	private final DetailsPresenter detailsPresenter;
	private final SchedulePresenter schedulePresenter;
	private final BillingPresenter billingPresenter;
	private final NotesPresenter notesPresenter;

	private HandlerRegistration btnCloseHandler;
	private HandlerRegistration advanceContainerOpenHandler;
	private HandlerRegistration advanceContainerCloseHandler;
	private HandlerRegistration tabPanelHandler;

	private String ticketNumber;
	private ServiceTicket ticket;
	private boolean areListenersRegistered;

	private BasePresenter currPresenter;

	@Inject
	public ServiceTicketPresenterImpl(View view,
			ServiceItemPresenter serviceItemPresenter,
			LocationPresenter locationPresenter,
			AccessHoursPresenter accessHoursPresenter,
			SkillsPresenter skillsPresenter, DetailsPresenter detailsPresenter,
			SchedulePresenter schedulePresenter,
			BillingPresenter billingPresenter, NotesPresenter notesPresenter) {
		this.view = view;

		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		this.serviceItemPresenter = serviceItemPresenter;
		this.locationPresenter = locationPresenter;
		this.accessHoursPresenter = accessHoursPresenter;
		this.skillsPresenter = skillsPresenter;
		this.detailsPresenter = detailsPresenter;
		this.schedulePresenter = schedulePresenter;
		this.billingPresenter = billingPresenter;
		this.notesPresenter = notesPresenter;

		ticketNumber = "";
		areListenersRegistered = false;

		currPresenter = null;

		setupTabs();
	}

	/**
	 * {@inheritDoc}
	 */
	public void showServiceTicket(String ticketNumber) {
		this.ticketNumber = ticketNumber;
		retrieveTicket();
	}

	/**
	 * {@inheritDoc}
	 */
	public void showServiceTicketAdvance(boolean showAdvance) {
		view.getServiceTicketAdvanceContainer().setOpen(showAdvance);
	}

	/**
	 * {@inheritDoc}
	 */
	public void selectTabPanel(int panelNumber) {
		TabPanel tabPanel = view.getTabPanel();
		if (panelNumber < 0 || panelNumber >= tabPanel.getWidgetCount()) {
			panelNumber = 0;
		}
		tabPanel.selectTab(panelNumber);

		showTabPanelContent(panelNumber);
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset() {
		unRegisterListeners();

		view.reset();

		ticketNumber = "";
		view.getServiceTicketAdvanceContainer().setOpen(false);

		resetAllTabs();
		view.getTabPanel().selectTab(0);
	}

	/**
	 * {@inheritDoc}
	 */
	public void resetPanel() {
	}

	/**
	 * {@inheritDoc}
	 */
	public BaseView getView() {
		return view;
	}

	/**
	 * {@inheritDoc}
	 */
	public BaseView getPanelView() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setVisible(boolean visible) {
		view.getWidget().setVisible(visible);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setPanelVisible(boolean visible) {
	}

	/**
	 * Make RPC to get ticket data
	 */
	private void retrieveTicket() {
		retrieveTicket(new GetServiceTicket(ticketNumber));
	}

	/**
	 * Overloaded call to make RPC to get ticket data. Assign a call-back
	 * function.
	 */
	private void retrieveTicket(GetServiceTicket action) {

		try {
			ActionServices.App.getInstance().execute(action,
					new RetrieveTicketCallback());
		} catch (ActionNotSupportedException e) {			
			final String ERR_MSG = "Unable to get the ticket details.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}

	}

	/**
	 * Display ticket data.
	 * 
	 * @param ticket
	 *            The ticket data model.
	 */
	private void render(ServiceTicket ticket) {
		view.getTxtTicketNumber().setText(ticket.getTicketNumber());
		view.getTxtServiceType().setText(ticket.getServiceTypeDescription());
		view.getTxtServiceItem().setText(ticket.getServiceItem());
		view.getTxtDescription().setText(ticket.getServiceItemDescription());
		view.getTxtMainContact().setText(ticket.getMainContact());
		((ListBox) view.getLstComplaint()).addItem(ticket.getComplaint());
		((ListBox) view.getLstServiceProcedure()).addItem(ticket
				.getServiceProcedure());
		((ListBox) view.getLstStatus()).addItem(ticket.getStatus());
		view.getTxtDateOpened().setText(
				DateUtil.formatDate(ticket.getDateOpened(), "MMM d, yyyy"));
		view.getTxtMainContactNumber().setText(ticket.getMainContactNumber());
		view.getTxtSiteContact().setText(ticket.getSiteContact());
		view.getTxtSiteContactNumber().setText(ticket.getSiteContactNumber());
		view.getTxtSecondNumber().setText(ticket.getSecondNumber());
		view.getTxtContactEmail().setText(ticket.getContactEmail());
		((CheckBox) view.getChkRepeatIssue()).setValue(ticket.isRepeatIssue());
		view.getTxtPreviousTicket().setText(ticket.getPreviousTicket());
		((ListBox) view.getLstPriorityCode()).addItem(ticket.getPriorityCode());
		view.getTxtSubject().setText(ticket.getSubject());
		((CheckBox) view.getChkConfirmationNeeded()).setValue(ticket
				.isConfirmationRequired());
		((CheckBox) view.getChkConfirmed()).setValue(ticket.isConfirmed());
		view.getTxtScheduledDate().setText(
				DateUtil.formatDate(ticket.getScheduledDate()));
		view.getTxtStartTime().setText(
				DateUtil.formatTime(ticket.getStartTime()));
		view.getTxtEffort().setText(String.valueOf(ticket.getEffort()));
		((ListBox) view.getLstAssignedTo()).addItem(ticket.getAssignedTo());
		view.getTxtRespondByDate().setText(
				DateUtil.formatDate(ticket.getRespondByDate(), "MMM d, yyyy"));
		view.getTxtRespondByTime().setText(
				DateUtil.formatTime(ticket.getRespondByTime()));
		view.getTxtEstimatedCost().setText(
				String.valueOf(ticket.getEstimatedCost()));
		view.getTxtCustomerPO().setText(ticket.getCustomerPO());

		registerListeners();
	}

	/**
	 * Add all the tabs for the ticket.
	 */
	private void setupTabs() {
		TabPanel tabPanel = view.getTabPanel();
		tabPanel.add(serviceItemPresenter.getView().getWidget(), txtConsts
				.ServiceItem());
		tabPanel.add(locationPresenter.getView().getWidget(), txtConsts
				.Location());
		tabPanel.add(accessHoursPresenter.getView().getWidget(), txtConsts
				.AccessHours());
		tabPanel.add(skillsPresenter.getView().getWidget(), txtConsts.Skills());
		tabPanel.add(detailsPresenter.getView().getWidget(), txtConsts
				.Details());
		tabPanel.add(schedulePresenter.getView().getWidget(), txtConsts
				.Schedule());
		tabPanel.add(billingPresenter.getView().getWidget(), txtConsts
				.Billing());
		tabPanel.add(notesPresenter.getView().getWidget(), txtConsts.Notes());

		serviceItemPresenter.reset();
		locationPresenter.reset();
		accessHoursPresenter.reset();
		skillsPresenter.reset();
		detailsPresenter.reset();
		schedulePresenter.reset();
		billingPresenter.reset();
		notesPresenter.reset();
	}

	/**
	 * Display the tab where tab number = panel number.
	 * 
	 * @param panelNumber
	 *            The tab number to display.
	 */
	private void showTabPanelContent(int panelNumber) {
		resetAllTabs();

		String strPanelNumber = String.valueOf(panelNumber);

		switch (panelNumber) {
		case 0:
		default:
			Logger.debug("Show Tab Service Item");
			currPresenter = serviceItemPresenter;
			serviceItemPresenter.showServiceItem(ticketNumber);
			break;
		case 1:
			Logger.debug("Show Tab Location");
			currPresenter = locationPresenter;
			locationPresenter.showLocation(ticketNumber);
			break;
		case 2:
			Logger.debug("Show Tab Access Hours");
			currPresenter = accessHoursPresenter;
			accessHoursPresenter.showAccessHours(ticketNumber);
			break;
		case 3:
			Logger.debug("Show Tab Skills");
			currPresenter = skillsPresenter;
			skillsPresenter.showSkillsGrid(ticketNumber, strPanelNumber);
			break;
		case 4:
			Logger.debug("Show Tab Details");
			currPresenter = detailsPresenter;
			detailsPresenter.showDetailsGrid(ticketNumber, strPanelNumber);
			break;
		case 5:
			Logger.debug("Show Tab Schedule");
			currPresenter = schedulePresenter;
			schedulePresenter.showScheduleGrid(ticketNumber, strPanelNumber);
			break;
		case 6:
			Logger.debug("Show Tab Billing");
			currPresenter = billingPresenter;
			billingPresenter.showBillingGrid(ticketNumber, strPanelNumber);
			break;
		case 7:
			Logger.debug("Show Tab Notes");
			currPresenter = notesPresenter;
			notesPresenter.showNotes(ticketNumber, strPanelNumber);
			break;

		}
	}

	/**
	 * Reset all the tabs view
	 */
	private void resetAllTabs() {
		if (currPresenter == null) {
			return;
		}

		Logger.debug("Reset Tab " + getClassName(currPresenter));

		currPresenter.reset();
	}

	/**
	 * Determine if the service type of the ticket is internal asset or not.
	 * 
	 * @return true if the service type is internal asset, false otherwise.
	 */
	protected boolean isInternalAsset(ServiceTicket ticket) {

		if (ServiceTicket.SERVICE_TYPE_CUSTOMER.equals(ticket.getServiceType())) {

			return false;

		} else {

			return true;
		}
	}

	private void setTicket(ServiceTicket ticket) {

		this.ticket = ticket;

		render(ticket);

		notifyComponents(ticket);
	}

	@SuppressWarnings("unused")
	private ServiceTicket getTicket() {
		return ticket;
	}

	/**
	 * Notify other components about the new ticket properties.
	 */
	protected void notifyComponents(ServiceTicket ticket) {
		// TODO: use eventbus to avoid coupling?
		locationPresenter.setLocationType(isInternalAsset(ticket));
	}

	/**
	 * Register Listeners that will trigger events.
	 */
	private void registerListeners() {
		if (areListenersRegistered) {
			return;
		}

		btnCloseHandler = view.getBtnClose().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						History
								.newItem(HistoryConstants.SERVICE_TICKET_LIST_VALUE);
					}
				});
		advanceContainerOpenHandler = view.getServiceTicketAdvanceContainer()
				.addOpenHandler(new OpenHandler<DisclosurePanel>() {
					public void onOpen(OpenEvent<DisclosurePanel> event) {
						updateTicketAdvanceToken(HistoryConstants.SERVICE_TICKET_ADVANCE_TRUE);
					}
				});
		advanceContainerCloseHandler = view.getServiceTicketAdvanceContainer()
				.addCloseHandler(new CloseHandler<DisclosurePanel>() {
					public void onClose(CloseEvent<DisclosurePanel> event) {
						updateTicketAdvanceToken(HistoryConstants.SERVICE_TICKET_ADVANCE_FALSE);
					}
				});
		tabPanelHandler = view.getTabPanel().addSelectionHandler(
				new SelectionHandler<Integer>() {
					public void onSelection(SelectionEvent<Integer> event) {
						int panelNumber = event.getSelectedItem();
						showTabPanelContent(panelNumber);
						updateTicketPanelToken(panelNumber);
					}
				});

		areListenersRegistered = true;
	}

	/**
	 * Clean-up the listeners.
	 */
	private void unRegisterListeners() {
		if (!areListenersRegistered) {
			return;
		}

		btnCloseHandler.removeHandler();
		advanceContainerOpenHandler.removeHandler();
		advanceContainerCloseHandler.removeHandler();
		tabPanelHandler.removeHandler();

		areListenersRegistered = false;
	}

	/**
	 * Call-Back class to get tickets data
	 * 
	 */
	class RetrieveTicketCallback extends GotServiceTicket {
		public void got(ServiceTicket ticket) {
			setTicket(ticket);
		}
	}

}
