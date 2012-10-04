package com.cms.gwt.fs.client.presenter;

import java.util.ArrayList;
import java.util.List;

import com.cms.gwt.fs.client.HistoryConstants;
import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.event.MaterialAddEvent;
import com.cms.gwt.fs.client.event.MaterialAddEventHandler;
import com.cms.gwt.fs.client.event.MaterialEditEvent;
import com.cms.gwt.fs.client.event.MaterialEditEventHandler;
import com.cms.gwt.fs.client.model.breadcrumb.BreadCrumb;
import com.cms.gwt.fs.client.model.breadcrumb.BreadCrumbManager;
import com.cms.gwt.fs.client.model.material.RecordedMaterialEntriesEntry;
import com.cms.gwt.fs.client.model.workHistory.WorkHistory;
import com.cms.gwt.fs.client.presenter.BillingPresenter.BillingPanelType;
import com.cms.gwt.fs.client.presenter.MaterialEditPresenter.MaterialEditType;
import com.cms.gwt.fs.client.presenter.SchedulePresenter.SchedulePanelType;
import com.cms.gwt.fs.client.util.Logger;
import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.inject.Inject;
import com.gwtext.client.util.Format;

/**
 * Main presenter's implementation.
 * <p />
 * This is the main class that holds all panels and displays them based on user
 * actions.
 * 
 */
public class MainPresenterImpl extends BasePresenterImpl implements
		MainPresenter, ValueChangeHandler<String> {

	private static final String COOKIE_SERVICE_TICKETS_TYPE = "FS_ServiceTicketsType";

	private final View view;
	private final BreadCrumbPresenter breadCrumbPresenter;
	private final TextConstants txtConsts;

	private final ServiceTicketListPresenter serviceTicketListPresenter;
	private final ServiceTicketCalendarPresenter serviceTicketCalendarPresenter;
	private final ServiceTicketPresenter serviceTicketPresenter;
	private final SkillsPresenter skillsPresenter;
	private final DetailsPresenter detailsPresenter;
	private final SchedulePresenter schedulePresenter;
	private final WorkHistoryPresenter workHistoryPresenter;
	private final WorkHistoryCounterPresenter workHistoryCounterPresenter;
	private final WorkHistoryArrivalInfoPresenter workHistoryArrivalInfoPresenter;
	private final TimeEntryPresenter timeEntryPresenter;
	private final TimeEntryAddPresenter timeEntryAddPresenter;
	private final TimeEntryEditPresenter timeEntryEditPresenter;
	private final MaterialPresenter materialPresenter;
	private final MaterialAddPresenter materialAddPresenter;
	private final MaterialEditPresenter materialEditPresenter;
	private final BillingPresenter billingPresenter;

	private BasePresenter currPresenter;

	private final BreadCrumbManager breadCrumbManager;

	@Inject
	public MainPresenterImpl(HandlerManager eventBus, View view,
			BreadCrumbPresenter breadCrumbPresenter,
			ServiceTicketListPresenter ticketListPresenter,
			ServiceTicketCalendarPresenter ticketCalendarPresenter,
			ServiceTicketPresenter ticketDetailBasicPresenter,
			SkillsPresenter skillsPresenter, DetailsPresenter detailsPresenter,
			SchedulePresenter schedulePresenter,
			WorkHistoryPresenter workHistoryPresenter,
			WorkHistoryCounterPresenter workHistoryCounterPresenter,
			WorkHistoryArrivalInfoPresenter workHistoryArrivalInfoPresenter,
			TimeEntryPresenter timeEntryPresenter,
			TimeEntryAddPresenter timeEntryAddPresenter,
			TimeEntryEditPresenter timeEntryEditPresenter,
			MaterialPresenter materialPresenter,
			MaterialAddPresenter materialAddPresenter,
			MaterialEditPresenter materialEditPrensenter,
			BillingPresenter billingPresenter) {

		this.view = view;
		this.breadCrumbPresenter = breadCrumbPresenter;
		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		this.serviceTicketListPresenter = ticketListPresenter;
		this.serviceTicketCalendarPresenter = ticketCalendarPresenter;
		this.serviceTicketPresenter = ticketDetailBasicPresenter;
		this.skillsPresenter = skillsPresenter;
		this.detailsPresenter = detailsPresenter;
		this.schedulePresenter = schedulePresenter;
		this.workHistoryPresenter = workHistoryPresenter;
		this.workHistoryCounterPresenter = workHistoryCounterPresenter;
		this.workHistoryArrivalInfoPresenter = workHistoryArrivalInfoPresenter;
		this.timeEntryPresenter = timeEntryPresenter;
		this.timeEntryAddPresenter = timeEntryAddPresenter;
		this.timeEntryEditPresenter = timeEntryEditPresenter;
		this.materialPresenter = materialPresenter;
		this.materialAddPresenter = materialAddPresenter;
		this.materialEditPresenter = materialEditPrensenter;
		this.billingPresenter = billingPresenter;

		currPresenter = null;

		// make bread crumbs structure
		breadCrumbManager = new BreadCrumbManager();
		initBreadCrumbs();

		// bind events to the main page
		bindEvents(eventBus);

		// listen for history changes
		History.addValueChangeHandler(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public View go() {

		// add items to the main view
		view.addBreadCrumb(breadCrumbPresenter.getView());
		view.addServiceTicketList(serviceTicketListPresenter.getView());
		view.addServiceTicketCalendar(serviceTicketCalendarPresenter.getView());
		view.addServiceTicket(serviceTicketPresenter.getView());
		view.addSkillsPanel(skillsPresenter.getPanelView());
		view.addDetailsPanel(detailsPresenter.getPanelView());
		view.addSchedulePanel(schedulePresenter.getPanelView());
		view.addWorkHistoryPanel(workHistoryPresenter.getView());
		view.addWorkHistoryCounter(workHistoryCounterPresenter.getView());
		view.addWorkHistoryArrivalInfo(workHistoryArrivalInfoPresenter
				.getView());
		view.addTimeEntryPanel(timeEntryPresenter.getView());
		view.addTimeEntryAddPanel(timeEntryAddPresenter.getView());
		view.addTimeEntryEditPanel(timeEntryEditPresenter.getView());
		view.addMaterialPanel(materialPresenter.getView());
		view.addMaterialAddPanel(materialAddPresenter.getView());
		view.addMaterialEditPanel(materialEditPresenter.getView());
		view.addBillingPanel(billingPresenter.getPanelView());

		init();

		return view;
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
	 * {@inheritDoc}
	 */
	public void reset() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void resetPanel() {
	}

	/**
	 * Invoked by GWT History each time history changes. History changes are
	 * triggered by user using Back and Forward browser buttons, or
	 * programmatically if user clicks on a link.
	 */
	public void onValueChange(ValueChangeEvent<String> event) {
		decideWhatToShow(event.getValue());
	}

	private void init() {
		serviceTicketListPresenter.setVisible(false);
		serviceTicketCalendarPresenter.setVisible(false);
		serviceTicketPresenter.setVisible(false);
		skillsPresenter.setPanelVisible(false);
		detailsPresenter.setPanelVisible(false);
		schedulePresenter.setPanelVisible(false);
		workHistoryPresenter.setVisible(false);
		workHistoryCounterPresenter.setVisible(false);
		workHistoryArrivalInfoPresenter.setVisible(false);
		timeEntryPresenter.setVisible(false);
		timeEntryAddPresenter.setVisible(false);
		timeEntryEditPresenter.setVisible(false);
		materialPresenter.setVisible(false);
		materialAddPresenter.setVisible(false);
		materialEditPresenter.setVisible(false);
		billingPresenter.setPanelVisible(false);
	}

	/**
	 * Initialize the bread crumbs by making a web structure manually :(
	 */
	private void initBreadCrumbs() {

		final String idPlaceHolder = "###";

		// 1st level
		breadCrumbManager.insert(".", HistoryConstants.SERVICE_TICKET_LIST_KEY,
				new BreadCrumb(txtConsts.ServiceTicketList(),
						HistoryConstants.SERVICE_TICKET_LIST_VALUE));

		// 2nd level
		breadCrumbManager.insert(HistoryConstants.SERVICE_TICKET_LIST_KEY,
				HistoryConstants.SERVICE_TICKET_KEY, new BreadCrumb(txtConsts
						.ServiceTicket(idPlaceHolder),
						makeProperLink(HistoryConstants.SERVICE_TICKET_VALUE)));

		// 3rd level
		breadCrumbManager.insert(HistoryConstants.SERVICE_TICKET_KEY,
				HistoryConstants.SKILLS_PANEL_KEY, new BreadCrumb(txtConsts
						.SkillsPanel(idPlaceHolder),
						makeProperLink(HistoryConstants.SKILLS_PANEL_VALUE)));

		breadCrumbManager.insert(HistoryConstants.SERVICE_TICKET_KEY,
				HistoryConstants.DETAILS_PANEL_KEY, new BreadCrumb(txtConsts
						.DetailsPanel(idPlaceHolder),
						makeProperLink(HistoryConstants.DETAILS_PANEL_VALUE)));

		breadCrumbManager.insert(HistoryConstants.SERVICE_TICKET_KEY,
				HistoryConstants.SCHEDULE_PANEL_KEY, new BreadCrumb(txtConsts
						.SchedulePanel(idPlaceHolder),
						makeProperLink(HistoryConstants.SCHEDULE_PANEL_VALUE)));

		breadCrumbManager
				.insert(
						HistoryConstants.SERVICE_TICKET_KEY,
						HistoryConstants.WORK_HISTORY_PANEL_KEY,
						new BreadCrumb(
								txtConsts.WorkHistoryPanel(idPlaceHolder),
								makeProperLink(HistoryConstants.WORK_HISTORY_PANEL_VALUE)));

		breadCrumbManager.insert(HistoryConstants.SERVICE_TICKET_KEY,
				HistoryConstants.BILLING_PANEL_KEY, new BreadCrumb(txtConsts
						.BillingPanel(idPlaceHolder),
						makeProperLink(HistoryConstants.BILLING_PANEL_VALUE)));

		// 4th level
		breadCrumbManager
				.insert(
						HistoryConstants.WORK_HISTORY_PANEL_KEY,
						HistoryConstants.WORK_HISTORY_COUNTER_KEY,
						new BreadCrumb(
								txtConsts.WHCounter(),
								makeProperLink(HistoryConstants.WORK_HISTORY_COUNTER_VALUE)));

		breadCrumbManager
				.insert(
						HistoryConstants.WORK_HISTORY_PANEL_KEY,
						HistoryConstants.WORK_HISTORY_ARRIVAL_INFO_KEY,
						new BreadCrumb(
								txtConsts.WHArrivalInfo(),
								makeProperLink(HistoryConstants.WORK_HISTORY_ARRIVAL_INFO_VALUE)));

		breadCrumbManager.insert(HistoryConstants.WORK_HISTORY_PANEL_KEY,
				HistoryConstants.TIME_ENTRY_KEY, new BreadCrumb(txtConsts
						.TimeEntry(),
						makeProperLink(HistoryConstants.TIME_ENTRY_VALUE)));

		breadCrumbManager.insert(HistoryConstants.WORK_HISTORY_PANEL_KEY,
				HistoryConstants.MATERIAL_KEY, new BreadCrumb(txtConsts
						.Material(),
						makeProperLink(HistoryConstants.MATERIAL_VALUE)));

		// 5th level
		breadCrumbManager.insert(HistoryConstants.TIME_ENTRY_KEY,
				HistoryConstants.TIME_ENTRY_ADD_KEY, new BreadCrumb(txtConsts
						.Add()
						+ " " + txtConsts.TimeEntry(),
						makeProperLink(HistoryConstants.TIME_ENTRY_ADD_VALUE)));

		breadCrumbManager
				.insert(
						HistoryConstants.TIME_ENTRY_KEY,
						HistoryConstants.TIME_ENTRY_EDIT_KEY,
						new BreadCrumb(
								txtConsts.Edit() + " " + txtConsts.TimeEntry(),
								makeProperLink(HistoryConstants.TIME_ENTRY_EDIT_VALUE)));

		breadCrumbManager.insert(HistoryConstants.MATERIAL_KEY,
				HistoryConstants.MATERIAL_ADD_KEY, new BreadCrumb(txtConsts
						.Add()
						+ " " + txtConsts.Material(),
						makeProperLink(HistoryConstants.MATERIAL_ADD_VALUE)));

		breadCrumbManager.insert(HistoryConstants.MATERIAL_KEY,
				HistoryConstants.MATERIAL_EDIT_KEY, new BreadCrumb(txtConsts
						.Update()
						+ " " + txtConsts.Material(),
						makeProperLink(HistoryConstants.MATERIAL_EDIT_VALUE)));

		// 6th level
		breadCrumbManager
				.insert(
						HistoryConstants.TIME_ENTRY_ADD_KEY,
						HistoryConstants.TIME_ENTRY_ADD_EDIT_KEY,
						new BreadCrumb(
								txtConsts.Edit() + " " + txtConsts.TimeEntry(),
								makeProperLink(HistoryConstants.TIME_ENTRY_ADD_EDIT_VALUE)));

		breadCrumbManager
				.insert(
						HistoryConstants.MATERIAL_ADD_KEY,
						HistoryConstants.MATERIAL_ADD_EDIT_KEY,
						new BreadCrumb(
								txtConsts.Edit() + " " + txtConsts.Material(),
								makeProperLink(HistoryConstants.MATERIAL_ADD_EDIT_VALUE)));
	}

	/**
	 * Bind all the events to this main panel in order to control history, all
	 * panels, and bread crumbs from a single point.
	 * 
	 * @param eventBus
	 *            The Events Manager.
	 */
	private void bindEvents(HandlerManager eventBus) {

		eventBus.addHandler(MaterialAddEvent.TYPE,
				new MaterialAddEventHandler() {
					public void showMaterial(String ticketNumber,
							String panelNumber, String eventId,
							String tabNumber, boolean isEdit) {

						if (isEdit) {
							// by-pass History
							doShowMaterialAdd(ticketNumber, panelNumber,
									eventId, tabNumber, isEdit);

						} else {
							History.newItem(Format.format(
									HistoryConstants.MATERIAL_ADD_VALUE,
									ticketNumber, panelNumber, eventId,
									tabNumber));

						}
					}
				});

		eventBus.addHandler(MaterialEditEvent.TYPE,
				new MaterialEditEventHandler() {
					public void showMaterialEdit(String ticketNumber,
							String panelNumber, String eventId,
							String tabNumber, String sequence, String line,
							MaterialEditType materialEditType) {
						if (materialEditType.value == MaterialEditType.UPDATE.value) {
							History.newItem(Format.format(
									HistoryConstants.MATERIAL_EDIT_VALUE,
									ticketNumber, panelNumber, eventId,
									tabNumber, sequence, line,
									formatInt(materialEditType.value)));
						}
					}

					public void showMaterialAddEdit(String ticketNumber,
							String panelNumber, String eventId,
							String tabNumber, String sequence, String line,
							MaterialEditType materialEditType,
							WorkHistory workHistory,
							RecordedMaterialEntriesEntry recordedMaterialEntry) {
						// by-pass history
						doShowMaterialAddEdit(ticketNumber, panelNumber,
								eventId, tabNumber, sequence, line,
								materialEditType, workHistory,
								recordedMaterialEntry);
					}
				});

	}

	/**
	 * Triggered by History change. Based on token decides and shows the
	 * appropriate panel.
	 * <p />
	 * NOTE: BreadCrumbs are also generated in this method, based on the token.
	 * 
	 * @param token
	 *            The value based on which decide what to display.
	 */
	private void decideWhatToShow(String token) {

		Logger.debug("decideWhatToShow: " + token);

		boolean isServiceTicket = false;
		boolean isServiceTicketAdvanced = false;
		boolean serviceTicketAdvancedValue = false;
		boolean isTabPanelSelected = false;
		int tabPanelSelectedValue = 0;

		List<BreadCrumb> breadCrumbs = new ArrayList<BreadCrumb>();

		// if no token present then show ticket list on startup
		if (token.equals("")) {
			token = Format.format(HistoryConstants.SERVICE_TICKET_LIST_VALUE,
					HistoryConstants.SERVICE_TICKET_EVENT_LIST);
		}

		String[] tokens = token.split(HistoryConstants.TOKEN_SPLITTER_REGEX);
		for (int i = 0; i < tokens.length; i++) {

			String[] parts = tokens[i].split(
					HistoryConstants.VALUE_SPLITTER_REGEX, 2);
			if (parts.length != 2) {
				continue;
			}
			String key = parts[0];
			String value = parts[1];

			if (key.equals(HistoryConstants.SERVICE_TICKET_LIST_KEY)) {
				breadCrumbs = breadCrumbManager
						.getBreadCrumbs(HistoryConstants.SERVICE_TICKET_LIST_KEY);

				if ("".equals(value)) {
					String cookie = getCookie(COOKIE_SERVICE_TICKETS_TYPE);
					value = (cookie != null) ? cookie
							: HistoryConstants.SERVICE_TICKET_EVENT_LIST;
				}

				doShowServiceTicketList(value);

			} else if (key.equals(HistoryConstants.SERVICE_TICKET_KEY)) {
				isServiceTicket = true;
				breadCrumbs = breadCrumbManager.getBreadCrumbs(
						HistoryConstants.SERVICE_TICKET_KEY, new String[][] { {
								value, value } });
				doShowServiceTicket(value);

			} else if (key.equals(HistoryConstants.SERVICE_TICKET_ADVANCE_KEY)) {
				isServiceTicketAdvanced = true;
				serviceTicketAdvancedValue = "T".equals(value);

			} else if (key.equals(HistoryConstants.TAB_PANEL_KEY)) {
				isTabPanelSelected = true;
				try {
					tabPanelSelectedValue = parseInt(value);
				} catch (NumberFormatException e) {
				}

			} else if (key.equals(HistoryConstants.SKILLS_PANEL_KEY)) {
				String[] values = value.split(
						HistoryConstants.CONCAT_VALUES_REGEX, 3);
				String ticketNumber = values[0];
				String panelNumber = values[1];
				String skillId = values[2];

				String skillLink = ticketNumber
						+ HistoryConstants.CONCAT_VALUES + panelNumber
						+ HistoryConstants.CONCAT_VALUES + skillId;
				String ticketLink = ticketNumber
						+ HistoryConstants.TOKEN_SPLITTER
						+ HistoryConstants.TAB_PANEL_KEY
						+ HistoryConstants.VALUE_SPLITTER + panelNumber;

				String[][] args = new String[][] { { skillId, skillLink },
						{ ticketNumber, ticketLink } };
				breadCrumbs = breadCrumbManager.getBreadCrumbs(
						HistoryConstants.SKILLS_PANEL_KEY, args);
				doShowSkillsPanel(ticketNumber, panelNumber, skillId);

			} else if (key.equals(HistoryConstants.DETAILS_PANEL_KEY)) {
				String[] values = value.split(
						HistoryConstants.CONCAT_VALUES_REGEX, 3);
				String ticketNumber = values[0];
				String panelNumber = values[1];
				String detailId = values[2];

				String detailLink = ticketNumber
						+ HistoryConstants.CONCAT_VALUES + panelNumber
						+ HistoryConstants.CONCAT_VALUES + detailId;
				String ticketLink = ticketNumber
						+ HistoryConstants.TOKEN_SPLITTER
						+ HistoryConstants.TAB_PANEL_KEY
						+ HistoryConstants.VALUE_SPLITTER + panelNumber;

				String[][] args = new String[][] { { detailId, detailLink },
						{ ticketNumber, ticketLink } };
				breadCrumbs = breadCrumbManager.getBreadCrumbs(
						HistoryConstants.DETAILS_PANEL_KEY, args);
				doShowDetailsPanel(ticketNumber, panelNumber, detailId);

			} else if (key.equals(HistoryConstants.SCHEDULE_PANEL_KEY)) {
				String[] values = value.split(
						HistoryConstants.CONCAT_VALUES_REGEX, 4);
				String ticketNumber = values[0];
				String panelNumber = values[1];
				String scheduleId = values[2];
				String panelType = values[3];

				String scheduleLink = ticketNumber
						+ HistoryConstants.CONCAT_VALUES + panelNumber
						+ HistoryConstants.CONCAT_VALUES + scheduleId
						+ HistoryConstants.CONCAT_VALUES + panelType;
				String ticketLink = ticketNumber
						+ HistoryConstants.TOKEN_SPLITTER
						+ HistoryConstants.TAB_PANEL_KEY
						+ HistoryConstants.VALUE_SPLITTER + panelNumber;

				String[][] args = new String[][] {
						{ scheduleId, scheduleLink },
						{ ticketNumber, ticketLink } };
				breadCrumbs = breadCrumbManager.getBreadCrumbs(
						HistoryConstants.SCHEDULE_PANEL_KEY, args);
				doShowSchedulePanel(ticketNumber, panelNumber, scheduleId,
						panelType);

			} else if (key.equals(HistoryConstants.WORK_HISTORY_PANEL_KEY)) {
				String[] values = value.split(
						HistoryConstants.CONCAT_VALUES_REGEX, 4);
				String ticketNumber = values[0];
				String panelNumber = values[1];
				String eventId = values[2];
				String tabNumber = values[3];

				String eventLink = ticketNumber
						+ HistoryConstants.CONCAT_VALUES + panelNumber
						+ HistoryConstants.CONCAT_VALUES + eventId
						+ HistoryConstants.CONCAT_VALUES + tabNumber;
				String ticketLink = ticketNumber
						+ HistoryConstants.TOKEN_SPLITTER
						+ HistoryConstants.TAB_PANEL_KEY
						+ HistoryConstants.VALUE_SPLITTER + panelNumber;

				String[][] args = new String[][] { { eventId, eventLink },
						{ ticketNumber, ticketLink } };
				breadCrumbs = breadCrumbManager.getBreadCrumbs(
						HistoryConstants.WORK_HISTORY_PANEL_KEY, args);
				doShowWorkHistoryPanel(ticketNumber, panelNumber, eventId,
						tabNumber);

			} else if (key.equals(HistoryConstants.WORK_HISTORY_COUNTER_KEY)) {
				String[] values = value.split(
						HistoryConstants.CONCAT_VALUES_REGEX, 5);
				String ticketNumber = values[0];
				String panelNumber = values[1];
				String eventId = values[2];
				String tabNumber = values[3];

				String counterId = "", counterLink = "";
				String workHistoryLink = ticketNumber
						+ HistoryConstants.CONCAT_VALUES + panelNumber
						+ HistoryConstants.CONCAT_VALUES + eventId
						+ HistoryConstants.CONCAT_VALUES + tabNumber;
				String ticketLink = ticketNumber
						+ HistoryConstants.TOKEN_SPLITTER
						+ HistoryConstants.TAB_PANEL_KEY
						+ HistoryConstants.VALUE_SPLITTER + panelNumber;

				String[][] args = new String[][] { { counterId, counterLink },
						{ eventId, workHistoryLink },
						{ ticketNumber, ticketLink } };
				breadCrumbs = breadCrumbManager.getBreadCrumbs(
						HistoryConstants.WORK_HISTORY_COUNTER_KEY, args);
				doShowWorkHistoryCounter(ticketNumber, panelNumber, eventId,
						tabNumber);

			} else if (key
					.equals(HistoryConstants.WORK_HISTORY_ARRIVAL_INFO_KEY)) {
				String[] values = value.split(
						HistoryConstants.CONCAT_VALUES_REGEX, 5);
				String ticketNumber = values[0];
				String panelNumber = values[1];
				String eventId = values[2];
				String tabNumber = values[3];

				String arrivalInfoId = "", arrivalInfoLink = "";
				String workHistoryLink = ticketNumber
						+ HistoryConstants.CONCAT_VALUES + panelNumber
						+ HistoryConstants.CONCAT_VALUES + eventId
						+ HistoryConstants.CONCAT_VALUES + tabNumber;
				String ticketLink = ticketNumber
						+ HistoryConstants.TOKEN_SPLITTER
						+ HistoryConstants.TAB_PANEL_KEY
						+ HistoryConstants.VALUE_SPLITTER + panelNumber;

				String[][] args = new String[][] {
						{ arrivalInfoId, arrivalInfoLink },
						{ eventId, workHistoryLink },
						{ ticketNumber, ticketLink } };
				breadCrumbs = breadCrumbManager.getBreadCrumbs(
						HistoryConstants.WORK_HISTORY_ARRIVAL_INFO_KEY, args);
				doShowWorkHistoryArrivalInfo(ticketNumber, panelNumber,
						eventId, tabNumber);

			} else if (key.equals(HistoryConstants.TIME_ENTRY_KEY)) {
				String[] values = value.split(
						HistoryConstants.CONCAT_VALUES_REGEX, 4);
				String ticketNumber = values[0];
				String panelNumber = values[1];
				String eventId = values[2];
				String tabNumber = values[3];

				String timeEntryId = "";
				String timeEntryLink = ticketNumber
						+ HistoryConstants.CONCAT_VALUES + panelNumber
						+ HistoryConstants.CONCAT_VALUES + eventId
						+ HistoryConstants.CONCAT_VALUES + tabNumber;
				String workHistoryLink = timeEntryLink;
				String ticketLink = ticketNumber
						+ HistoryConstants.TOKEN_SPLITTER
						+ HistoryConstants.TAB_PANEL_KEY
						+ HistoryConstants.VALUE_SPLITTER + panelNumber;

				String[][] args = new String[][] {
						{ timeEntryId, timeEntryLink },
						{ eventId, workHistoryLink },
						{ ticketNumber, ticketLink } };
				breadCrumbs = breadCrumbManager.getBreadCrumbs(
						HistoryConstants.TIME_ENTRY_KEY, args);
				doShowTimeEntry(ticketNumber, panelNumber, eventId, tabNumber);

			} else if (key.equals(HistoryConstants.TIME_ENTRY_ADD_KEY)) {
				String[] values = value.split(
						HistoryConstants.CONCAT_VALUES_REGEX, 4);
				String ticketNumber = values[0];
				String panelNumber = values[1];
				String eventId = values[2];
				String tabNumber = values[3];

				String addTimeEntryId = "", timeEntryId = "";
				String addTimeEntryLink = ticketNumber
						+ HistoryConstants.CONCAT_VALUES + panelNumber
						+ HistoryConstants.CONCAT_VALUES + eventId
						+ HistoryConstants.CONCAT_VALUES + tabNumber;
				String timeEntryLink = addTimeEntryLink;
				String workHistoryLink = timeEntryLink;
				String ticketLink = ticketNumber
						+ HistoryConstants.TOKEN_SPLITTER
						+ HistoryConstants.TAB_PANEL_KEY
						+ HistoryConstants.VALUE_SPLITTER + panelNumber;

				String[][] args = new String[][] {
						{ addTimeEntryId, addTimeEntryLink },
						{ timeEntryId, timeEntryLink },
						{ eventId, workHistoryLink },
						{ ticketNumber, ticketLink } };
				breadCrumbs = breadCrumbManager.getBreadCrumbs(
						HistoryConstants.TIME_ENTRY_ADD_KEY, args);
				doShowTimeEntryAdd(ticketNumber, panelNumber, eventId,
						tabNumber);

			} else if (key.equals(HistoryConstants.TIME_ENTRY_EDIT_KEY)) {
				String[] values = value.split(
						HistoryConstants.CONCAT_VALUES_REGEX, 6);
				String ticketNumber = values[0];
				String panelNumber = values[1];
				String eventId = values[2];
				String tabNumber = values[3];
				String sequence = values[4];
				String line = values[5];

				String editTimeEntryId = "", timeEntryId = "";
				String editTimeEntryLink = ticketNumber
						+ HistoryConstants.CONCAT_VALUES + panelNumber
						+ HistoryConstants.CONCAT_VALUES + eventId
						+ HistoryConstants.CONCAT_VALUES + tabNumber
						+ HistoryConstants.CONCAT_VALUES + sequence
						+ HistoryConstants.CONCAT_VALUES + line;
				String timeEntryLink = ticketNumber
						+ HistoryConstants.CONCAT_VALUES + panelNumber
						+ HistoryConstants.CONCAT_VALUES + eventId
						+ HistoryConstants.CONCAT_VALUES + tabNumber;
				String workHistoryLink = timeEntryLink;
				String ticketLink = ticketNumber
						+ HistoryConstants.TOKEN_SPLITTER
						+ HistoryConstants.TAB_PANEL_KEY
						+ HistoryConstants.VALUE_SPLITTER + panelNumber;

				String[][] args = new String[][] {
						{ editTimeEntryId, editTimeEntryLink },
						{ timeEntryId, timeEntryLink },
						{ eventId, workHistoryLink },
						{ ticketNumber, ticketLink } };
				breadCrumbs = breadCrumbManager.getBreadCrumbs(
						HistoryConstants.TIME_ENTRY_EDIT_KEY, args);
				doShowTimeEntryEdit(ticketNumber, panelNumber, eventId,
						tabNumber, sequence, line, false);

			} else if (key.equals(HistoryConstants.TIME_ENTRY_ADD_EDIT_KEY)) {
				String[] values = value.split(
						HistoryConstants.CONCAT_VALUES_REGEX, 5);
				String ticketNumber = values[0];
				String panelNumber = values[1];
				String eventId = values[2];
				String tabNumber = values[3];
				String sequence = values[4];
				String line = "";

				String editTimeEntryId = "", addTimeEntryId = "", timeEntryId = "";
				String editTimeEntryLink = ticketNumber
						+ HistoryConstants.CONCAT_VALUES + panelNumber
						+ HistoryConstants.CONCAT_VALUES + eventId
						+ HistoryConstants.CONCAT_VALUES + tabNumber
						+ HistoryConstants.CONCAT_VALUES + sequence;
				String addTimeEntryLink = ticketNumber
						+ HistoryConstants.CONCAT_VALUES + panelNumber
						+ HistoryConstants.CONCAT_VALUES + eventId
						+ HistoryConstants.CONCAT_VALUES + tabNumber;
				String timeEntryLink = addTimeEntryLink;
				String workHistoryLink = timeEntryLink;
				String ticketLink = ticketNumber
						+ HistoryConstants.TOKEN_SPLITTER
						+ HistoryConstants.TAB_PANEL_KEY
						+ HistoryConstants.VALUE_SPLITTER + panelNumber;

				String[][] args = new String[][] {
						{ editTimeEntryId, editTimeEntryLink },
						{ addTimeEntryId, addTimeEntryLink },
						{ timeEntryId, timeEntryLink },
						{ eventId, workHistoryLink },
						{ ticketNumber, ticketLink } };
				breadCrumbs = breadCrumbManager.getBreadCrumbs(
						HistoryConstants.TIME_ENTRY_ADD_EDIT_KEY, args);
				doShowTimeEntryEdit(ticketNumber, panelNumber, eventId,
						tabNumber, sequence, line, true);

			} else if (key.equals(HistoryConstants.MATERIAL_KEY)) {
				String[] values = value.split(
						HistoryConstants.CONCAT_VALUES_REGEX, 4);
				String ticketNumber = values[0];
				String panelNumber = values[1];
				String eventId = values[2];
				String tabNumber = values[3];

				String materialId = "";
				String materialLink = ticketNumber
						+ HistoryConstants.CONCAT_VALUES + panelNumber
						+ HistoryConstants.CONCAT_VALUES + eventId
						+ HistoryConstants.CONCAT_VALUES + tabNumber;
				String workHistoryLink = materialLink;
				String ticketLink = ticketNumber
						+ HistoryConstants.TOKEN_SPLITTER
						+ HistoryConstants.TAB_PANEL_KEY
						+ HistoryConstants.VALUE_SPLITTER + panelNumber;

				String[][] args = new String[][] {
						{ materialId, materialLink },
						{ eventId, workHistoryLink },
						{ ticketNumber, ticketLink } };
				breadCrumbs = breadCrumbManager.getBreadCrumbs(
						HistoryConstants.MATERIAL_KEY, args);
				doShowMaterial(ticketNumber, panelNumber, eventId, tabNumber);

			} else if (key.equals(HistoryConstants.MATERIAL_ADD_KEY)) {
				String[] values = value.split(
						HistoryConstants.CONCAT_VALUES_REGEX, 4);
				String ticketNumber = values[0];
				String panelNumber = values[1];
				String eventId = values[2];
				String tabNumber = values[3];

				String addMaterialId = "", materialId = "";
				String addMaterialLink = ticketNumber
						+ HistoryConstants.CONCAT_VALUES + panelNumber
						+ HistoryConstants.CONCAT_VALUES + eventId
						+ HistoryConstants.CONCAT_VALUES + tabNumber;
				String materialLink = addMaterialLink;
				String workHistoryLink = materialLink;
				String ticketLink = ticketNumber
						+ HistoryConstants.TOKEN_SPLITTER
						+ HistoryConstants.TAB_PANEL_KEY
						+ HistoryConstants.VALUE_SPLITTER + panelNumber;

				String[][] args = new String[][] {
						{ addMaterialId, addMaterialLink },
						{ materialId, materialLink },
						{ eventId, workHistoryLink },
						{ ticketNumber, ticketLink } };
				breadCrumbs = breadCrumbManager.getBreadCrumbs(
						HistoryConstants.MATERIAL_ADD_KEY, args);
				doShowMaterialAdd(ticketNumber, panelNumber, eventId,
						tabNumber, false);

			} else if (key.equals(HistoryConstants.MATERIAL_EDIT_KEY)) {
				String[] values = value.split(
						HistoryConstants.CONCAT_VALUES_REGEX, 7);
				String ticketNumber = values[0];
				String panelNumber = values[1];
				String eventId = values[2];
				String tabNumber = values[3];
				String sequence = values[4];
				String line = values[5];
				String type = values[6];

				String editMaterialId = "", materialId = "";
				String editMaterialLink = ticketNumber
						+ HistoryConstants.CONCAT_VALUES + panelNumber
						+ HistoryConstants.CONCAT_VALUES + eventId
						+ HistoryConstants.CONCAT_VALUES + tabNumber
						+ HistoryConstants.CONCAT_VALUES + sequence
						+ HistoryConstants.CONCAT_VALUES + line;
				String materialLink = ticketNumber
						+ HistoryConstants.CONCAT_VALUES + panelNumber
						+ HistoryConstants.CONCAT_VALUES + eventId
						+ HistoryConstants.CONCAT_VALUES + tabNumber;
				String workHistoryLink = materialLink;
				String ticketLink = ticketNumber
						+ HistoryConstants.TOKEN_SPLITTER
						+ HistoryConstants.TAB_PANEL_KEY
						+ HistoryConstants.VALUE_SPLITTER + panelNumber;

				String[][] args = new String[][] {
						{ editMaterialId, editMaterialLink },
						{ materialId, materialLink },
						{ eventId, workHistoryLink },
						{ ticketNumber, ticketLink } };
				breadCrumbs = breadCrumbManager.getBreadCrumbs(
						HistoryConstants.MATERIAL_EDIT_KEY, args);
				doShowMaterialEdit(ticketNumber, panelNumber, eventId,
						tabNumber, sequence, line, type);

			} else if (key.equals(HistoryConstants.MATERIAL_ADD_EDIT_KEY)) {
				String[] values = value.split(
						HistoryConstants.CONCAT_VALUES_REGEX, 6);
				String ticketNumber = values[0];
				String panelNumber = values[1];
				String eventId = values[2];
				String tabNumber = values[3];
				String sequence = values[4];
				String line = "";
				String type = values[5];

				String editMaterialId = "", addMaterialId = "", materialId = "";
				String editMaterialLink = ticketNumber
						+ HistoryConstants.CONCAT_VALUES + panelNumber
						+ HistoryConstants.CONCAT_VALUES + eventId
						+ HistoryConstants.CONCAT_VALUES + tabNumber
						+ HistoryConstants.CONCAT_VALUES + sequence
						+ HistoryConstants.CONCAT_VALUES + line;
				String addMaterialLink = ticketNumber
						+ HistoryConstants.CONCAT_VALUES + panelNumber
						+ HistoryConstants.CONCAT_VALUES + eventId
						+ HistoryConstants.CONCAT_VALUES + tabNumber;
				String materialLink = addMaterialLink;
				String workHistoryLink = materialLink;
				String ticketLink = ticketNumber
						+ HistoryConstants.TOKEN_SPLITTER
						+ HistoryConstants.TAB_PANEL_KEY
						+ HistoryConstants.VALUE_SPLITTER + panelNumber;

				String[][] args = new String[][] {
						{ editMaterialId, editMaterialLink },
						{ addMaterialId, addMaterialLink },
						{ materialId, materialLink },
						{ eventId, workHistoryLink },
						{ ticketNumber, ticketLink } };
				breadCrumbs = breadCrumbManager.getBreadCrumbs(
						HistoryConstants.MATERIAL_ADD_EDIT_KEY, args);
				doShowMaterialEdit(ticketNumber, panelNumber, eventId,
						tabNumber, sequence, line, type);

			} else if (key.equals(HistoryConstants.BILLING_PANEL_KEY)) {
				String[] values = value.split(
						HistoryConstants.CONCAT_VALUES_REGEX, 4);
				String ticketNumber = values[0];
				String panelNumber = values[1];
				String billingId = values[2];
				String panelType = values[3];

				String billingLink = ticketNumber
						+ HistoryConstants.CONCAT_VALUES + panelNumber
						+ HistoryConstants.CONCAT_VALUES + billingId
						+ HistoryConstants.CONCAT_VALUES + panelType;
				String ticketLink = ticketNumber
						+ HistoryConstants.TOKEN_SPLITTER
						+ HistoryConstants.TAB_PANEL_KEY
						+ HistoryConstants.VALUE_SPLITTER + panelNumber;

				String[][] args = new String[][] { { billingId, billingLink },
						{ ticketNumber, ticketLink } };
				breadCrumbs = breadCrumbManager.getBreadCrumbs(
						HistoryConstants.BILLING_PANEL_KEY, args);
				doShowBillingPanel(ticketNumber, panelNumber, billingId,
						panelType);

			} else {
				doShowNull();
			}

		} // next

		if (isServiceTicket) {

			if (isServiceTicketAdvanced) {
				doShowServiceTicketAdvance(serviceTicketAdvancedValue);
			} else {
				// if no advance token present then don't show it
				doShowServiceTicketAdvance(false);
			}

			if (isTabPanelSelected) {
				doShowTabPanelNumber(tabPanelSelectedValue);
			} else {
				// by-default select first tab, iff none selected
				doShowTabPanelNumber(0);
			}
		}

		breadCrumbPresenter.showBreadCrumb(breadCrumbs);
	}

	private void doShowServiceTicketList(String value) {
		doShowNull();

		// save to cookie, the correct value
		if (!(HistoryConstants.SERVICE_TICKET_EVENT_CALENDAR.equals(value) || HistoryConstants.SERVICE_TICKET_EVENT_LIST
				.equals(value))) {
			value = HistoryConstants.SERVICE_TICKET_EVENT_LIST;
		}
		BasePresenterImpl.setCookie(COOKIE_SERVICE_TICKETS_TYPE, value);

		if (HistoryConstants.SERVICE_TICKET_EVENT_CALENDAR.equals(value)) {
			Logger.debug("Show Events Calendar");

			currPresenter = serviceTicketCalendarPresenter;
			serviceTicketCalendarPresenter.setVisible(true);
			serviceTicketCalendarPresenter.showServiceTicketCalendar();

		} else {
			Logger.debug("Show Events List");

			currPresenter = serviceTicketListPresenter;
			serviceTicketListPresenter.setVisible(true);
			serviceTicketListPresenter.showServiceTicketList();
		}

	}

	private void doShowServiceTicket(String ticketNumber) {
		doShowNull();

		Logger.debug("Show Service Ticket");

		currPresenter = serviceTicketPresenter;
		serviceTicketPresenter.setVisible(true);
		serviceTicketPresenter.showServiceTicket(ticketNumber);
	}

	private void doShowServiceTicketAdvance(boolean showAdvance) {
		Logger.debug(((showAdvance) ? "Show" : "Hide")
				+ " Service Ticket Advance");

		serviceTicketPresenter.showServiceTicketAdvance(showAdvance);
	}

	private void doShowTabPanelNumber(int panelNumber) {
		Logger.debug("Show Panel Service Ticket");

		serviceTicketPresenter.selectTabPanel(panelNumber);
	}

	private void doShowSkillsPanel(String ticketNumber, String panelNumber,
			String skillId) {
		doShowNull();

		Logger.debug("Show Panel Skills");

		currPresenter = skillsPresenter;
		skillsPresenter.getPanelView().getWidget().setVisible(true);
		skillsPresenter.showSkillsPanel(ticketNumber, panelNumber, skillId);
	}

	private void doShowDetailsPanel(String ticketNumber, String panelNumber,
			String detailId) {
		doShowNull();

		Logger.debug("Show Panel Details");

		currPresenter = detailsPresenter;
		detailsPresenter.getPanelView().getWidget().setVisible(true);
		detailsPresenter.showDetailsPanel(ticketNumber, panelNumber, detailId);
	}

	private void doShowSchedulePanel(String ticketNumber, String panelNumber,
			String scheduleId, String panelType) {
		doShowNull();

		Logger.debug("Show Panel Schedule");

		SchedulePanelType schedulePanelType;
		schedulePanelType = SchedulePanelType.get(parseInt(panelType));

		currPresenter = schedulePresenter;
		schedulePresenter.getPanelView().getWidget().setVisible(true);
		schedulePresenter.showSchedulePanel(ticketNumber, panelNumber,
				scheduleId, schedulePanelType);
	}

	private void doShowWorkHistoryPanel(String ticketNumber,
			String panelNumber, String eventId, String tabNumber) {
		doShowNull();

		Logger.debug("Show Panel Work History");

		currPresenter = workHistoryPresenter;
		workHistoryPresenter.setVisible(true);
		workHistoryPresenter.showWorkHistoryPanel(ticketNumber, panelNumber,
				eventId, tabNumber);
	}

	private void doShowWorkHistoryCounter(String ticketNumber,
			String panelNumber, String eventId, String tabNumber) {
		doShowNull();

		Logger.debug("Show Work History Counter");

		currPresenter = workHistoryCounterPresenter;
		workHistoryCounterPresenter.setVisible(true);
		workHistoryCounterPresenter.showWorkHistoryCounter(ticketNumber,
				panelNumber, eventId, tabNumber);
	}

	private void doShowWorkHistoryArrivalInfo(String ticketNumber,
			String panelNumber, String eventId, String tabNumber) {
		doShowNull();

		Logger.debug("Show Work History Arrival Info");

		currPresenter = workHistoryArrivalInfoPresenter;
		workHistoryArrivalInfoPresenter.setVisible(true);
		workHistoryArrivalInfoPresenter.showWorkHistoryArrivalInfo(
				ticketNumber, panelNumber, eventId, tabNumber);
	}

	private void doShowTimeEntry(String ticketNumber, String panelNumber,
			String eventId, String tabNumber) {
		doShowNull();

		Logger.debug("Show Time Entry");

		currPresenter = timeEntryPresenter;
		timeEntryPresenter.setVisible(true);
		timeEntryPresenter.showTimeEntry(ticketNumber, panelNumber, eventId,
				tabNumber);
	}

	private void doShowTimeEntryAdd(String ticketNumber, String panelNumber,
			String eventId, String tabNumber) {
		doShowNull();

		Logger.debug("Show Time Entry Add");

		currPresenter = timeEntryAddPresenter;
		timeEntryAddPresenter.setVisible(true);
		timeEntryAddPresenter.showTimeEntry(ticketNumber, panelNumber, eventId,
				tabNumber);
	}

	private void doShowTimeEntryEdit(String ticketNumber, String panelNumber,
			String eventId, String tabNumber, String sequence, String line,
			boolean isAdd) {
		doShowNull();

		Logger.debug("Show Time Entry Edit");

		currPresenter = timeEntryEditPresenter;
		timeEntryEditPresenter.setVisible(true);
		timeEntryEditPresenter.showTimeEntry(ticketNumber, panelNumber,
				eventId, tabNumber, sequence, line, isAdd);
	}

	private void doShowMaterial(String ticketNumber, String panelNumber,
			String eventId, String tabNumber) {
		doShowNull();

		Logger.debug("Show Material");

		currPresenter = materialPresenter;
		materialPresenter.setVisible(true);
		materialPresenter.showMaterial(ticketNumber, panelNumber, eventId,
				tabNumber);
	}

	private void doShowMaterialAdd(String ticketNumber, String panelNumber,
			String eventId, String tabNumber, boolean isEdit) {
		doShowNull();

		Logger.debug("Show Material Add");

		currPresenter = materialAddPresenter;
		materialAddPresenter.setVisible(true);
		materialAddPresenter.showMaterial(ticketNumber, panelNumber, eventId,
				tabNumber, isEdit);
	}

	private void doShowMaterialEdit(String ticketNumber, String panelNumber,
			String eventId, String tabNumber, String sequence, String line,
			String type) {
		doShowNull();

		Logger.debug("Show Material Edit");

		MaterialEditType materialEditType = MaterialEditType
				.get(parseInt(type));

		currPresenter = materialEditPresenter;
		materialEditPresenter.setVisible(true);
		materialEditPresenter.showMaterial(ticketNumber, panelNumber, eventId,
				tabNumber, sequence, line, materialEditType);
	}

	private void doShowMaterialAddEdit(String ticketNumber, String panelNumber,
			String eventId, String tabNumber, String sequence, String line,
			MaterialEditType materialEditType, WorkHistory workHistory,
			RecordedMaterialEntriesEntry recordedMaterialEntry) {
		hideAllPanels();
		// resetAllPanels();

		Logger.debug("Show Material Add>Edit");

		currPresenter = materialEditPresenter;
		materialEditPresenter.setVisible(true);
		materialEditPresenter.showMaterial(ticketNumber, panelNumber, eventId,
				tabNumber, sequence, line, materialEditType, workHistory,
				recordedMaterialEntry);
	}

	private void doShowBillingPanel(String ticketNumber, String panelNumber,
			String billingId, String panelType) {
		doShowNull();

		Logger.debug("Show Panel Billing");

		BillingPanelType billingPanelType = BillingPanelType
				.get(parseInt(panelType));

		currPresenter = billingPresenter;
		billingPresenter.getPanelView().getWidget().setVisible(true);
		billingPresenter.showBillingPanel(ticketNumber, panelNumber, billingId,
				billingPanelType);
	}

	private void doShowNull() {
		hideAllPanels();
		resetAllPanels();

		currPresenter = null;
	}

	private void hideAllPanels() {
		if (currPresenter == null) {
			return;
		}

		Logger.debug("Hide Panel " + getClassName(currPresenter));

		currPresenter.setVisible(false);
		currPresenter.setPanelVisible(false);
	}

	private void resetAllPanels() {
		if (currPresenter == null) {
			return;
		}

		Logger.debug("Reset Panel " + getClassName(currPresenter));

		currPresenter.reset();
		currPresenter.resetPanel();
	}

	private String makeProperLink(String link) {
		final String idPlaceHolder = "###";
		String rval = "";
		try {
			rval = link.substring(0, link.indexOf("{0}")) + idPlaceHolder
					+ HistoryConstants.TOKEN_SPLITTER;
		} catch (Exception e) {
		}
		return rval;
	}

}
