package com.cms.gwt.fs.client.presenter;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.allen_sauer.gwt.log.client.Log;
import com.cms.gwt.common.client.ExtCmsMessageBox;
import com.cms.gwt.fs.client.HistoryConstants;
import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.exception.ActionNotSupportedException;
import com.cms.gwt.fs.client.model.scheduledEvent.AddressDetails;
import com.cms.gwt.fs.client.model.scheduledEvent.TechnicianSchedule;
import com.cms.gwt.fs.client.model.scheduledEvent.TechnicianScheduledEvent;
import com.cms.gwt.fs.client.presenter.SchedulePresenter.SchedulePanelType;
import com.cms.gwt.fs.client.rpc.ActionServices;
import com.cms.gwt.fs.client.rpc.action.GetTechnicianSchedule;
import com.cms.gwt.fs.client.rpc.callback.GotTechnicianSchedule;
import com.cms.gwt.fs.client.util.DateUtil;
import com.cms.gwt.fs.client.util.StringUtil;
import com.cms.gwt.fs.client.view.BaseView;
import com.cms.gwt.fs.client.view.StatusIndicator;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.inject.Inject;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.Record;
import com.gwtext.client.util.Format;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.event.GridRowListenerAdapter;

/**
 * Service Ticket List presenter's implementation.
 * <p />
 * Displays service tickets.
 * 
 */
public class ServiceTicketListPresenterImpl extends BasePresenterImpl implements
		ServiceTicketListPresenter {

	public final static String GOOGLE_MAPS_URL = "http://maps.google.com/maps?q=";
	public final static String FS_STARTDATE = "FS_StartDate";
	public final static String FS_HORIZON = "FS_Horizon";
	public final static String FS_STARTDATE_TODAY = "today";

	private final View view;
	private final TextConstants txtConsts;

	private static DialogBox optionsDialog;
	private HandlerRegistration linkOptions;
	private HandlerRegistration btnOKHandler;
	private HandlerRegistration btnCancelHandler;
	private HandlerRegistration rbDateFieldHandler;
	private HandlerRegistration rbTodayHandler;

	private boolean areListenersRegistered;
	private boolean areGridListenersRegistered;
	private boolean areOptionsListenersRegistered;

	private TechnicianSchedule schedule;

	@Inject
	public ServiceTicketListPresenterImpl(View view) {
		this.view = view;

		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		// in-it from cookies
		Calendar calendar = new GregorianCalendar();
		String defaultStartDate = DateUtil.formatDate(calendar.getTime());
		String cookieStartDate = getCookie(FS_STARTDATE);
		if (cookieStartDate == null) {
			setCookie(FS_STARTDATE, defaultStartDate, 30);
		}

		String defaultHorizon = "60";
		String cookieHorizon = getCookie(FS_HORIZON);
		if (cookieHorizon == null) {
			setCookie(FS_HORIZON, defaultHorizon, 30);
		}

		areListenersRegistered = false;
		areGridListenersRegistered = false;
		areOptionsListenersRegistered = false;
	}

	/**
	 * {@inheritDoc}
	 */
	public void showServiceTicketList() {
		// getTicketList();
		getTechnicianSchedule();

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
	public void reset() {
		unRegisterListeners();
		view.clearTicketListGrid();
		if (optionsDialog != null) {
			optionsDialog.hide();
			optionsDialog.clear();
			optionsDialog = null;
		}
		view.reset();
	}

	/**
	 * {@inheritDoc}
	 */
	public void resetPanel() {
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
	 * Retrieve the entire ticket lists from the action services and show it on
	 * the list.
	 */
	private void getTechnicianSchedule() {

		String userName = "";
		String strStartDate = getCookie(FS_STARTDATE);
		String horizon = getCookie(FS_HORIZON);

		Calendar startDate = new GregorianCalendar();
		if (!FS_STARTDATE_TODAY.equals(strStartDate)) {
			startDate.setTime(DateUtil.getDateFromString(strStartDate));
		}

		getTechnicianSchedule(GetTechnicianSchedule.newInstance(userName,
				new java.sql.Date(startDate.getTime().getTime()), horizon));
	}

	/**
	 * Retrieve the scheduled event based on the action details provided.
	 * 
	 * @param action
	 *            An instance of GetTechnicianSchedule which contains the
	 *            criteria of the schedule.
	 */
	private void getTechnicianSchedule(GetTechnicianSchedule action) {
		try {
			ActionServices.App.getInstance().execute(action,
					new GetTechnicianScheduleCallback());
		} catch (ActionNotSupportedException e) {

			final String ERR_MSG = "Unable to get technician schedule.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Render the screen with the specified tickets.
	 * 
	 * This method basically populates the UI widget (i.e. a list widget) with
	 * the data received.
	 * 
	 * @param tickets
	 *            The service ticket list.
	 */
	private void render(TechnicianSchedule schedule) {

		this.schedule = schedule;

		int size = schedule.getEvents().size();
		Object[][] tickets = new Object[size][10];
		int i = 0;
		for (TechnicianScheduledEvent event : schedule.getEvents()) {
			int j = 0;
			tickets[i][j++] = event.getEventID();
			tickets[i][j++] = event.getCustomerCode();
			tickets[i][j++] = event.getCustomerName();
			tickets[i][j++] = DateUtil
					.formatDate(event.getScheduledStartDate());
			tickets[i][j++] = event.getScheduledStartTime();
			tickets[i][j++] = event.getTypeDescription();
			tickets[i][j++] = event.getTicketNumber();
			tickets[i][j++] = event.getTimeEstimate();
			tickets[i][j++] = event.getDescription();
			tickets[i][j++] = event.getStatusDescription();
			i++;
		}

		view.initTicketListGrid(tickets);
		registerListeners();
	}

	/**
	 * Register listeners for widgets that will trigger events.
	 * 
	 */
	private void registerListeners() {

		if (!areGridListenersRegistered) {
			view.getTicketListGrid().addGridRowListener(
					new GridRowListenerAdapter() {

						@Override
						public void onRowClick(GridPanel grid, int rowIndex,
								EventObject e) {

							Record selected = grid.getStore().getAt(rowIndex);
							final int eventId = selected
									.getAsInteger(StringUtil.makeId(txtConsts
											.TicketListEventId()));

							StringBuffer sb = new StringBuffer();

							String space = "";
							for (TechnicianScheduledEvent event : schedule
									.getEvents()) {
								if (eventId == event.getEventID()) {
									AddressDetails addressDetails = event
											.getAddressDetails();

									if (addressDetails != null) {
										for (String address : addressDetails
												.getAddress()) {
											if (!(address == null || ""
													.equals(address))) {
												sb.append(space)
														.append(address);
												space = " ";
											}
										}
										String city = addressDetails.getCity();
										if (!(city == null || "".equals(city))) {
											sb.append(space).append(city);
										}
										String state = addressDetails
												.getProvinceState();
										if (!(state == null || "".equals(state))) {
											sb.append(space).append(state);
										}
										String country = addressDetails
												.getCountry();
										if (!(country == null || ""
												.equals(country))) {
											sb.append(space).append(country);
										}
										String postalCode = addressDetails
												.getPostalCode();
										if (!(postalCode == null || ""
												.equals(postalCode))) {
											sb.append(space).append(postalCode);
										}
									}
									break;
								}
							}

							String url = sb.toString();
							if (!"".equals(url)) {
								view.getMapFrameInfo().setText(url);
								view.getMapFrameInfo().setVisible(true);
								view.getMapFrame().setUrl(GOOGLE_MAPS_URL + url + "&output=embed");
								view.getMapFrame().setVisible(true);							
								
							} else {
								
								view.getMapFrameInfo().setText("");
								view.getMapFrameInfo().setVisible(false);
								view.getMapFrame().setUrl("");
								view.getMapFrame().setVisible(false);
							}
						}

						@Override
						public void onRowDblClick(GridPanel grid, int rowIndex,
								EventObject e) {

							Record selected = grid.getStore().getAt(rowIndex);
							final String ticketNumber = selected
									.getAsString(StringUtil.makeId(txtConsts
											.TicketListTicketNumber()));
							final String panelNumber = "5";
							final String scheduleId = selected
									.getAsString(StringUtil.makeId(txtConsts
											.TicketListEventId()));

							History
									.newItem(Format
											.format(
													HistoryConstants.SCHEDULE_PANEL_VALUE,
													ticketNumber,
													panelNumber,
													scheduleId,
													String
															.valueOf(SchedulePanelType.MAIN.value)));
						}
					});

			areGridListenersRegistered = true;
		}

		if (areListenersRegistered) {
			return;
		}

		linkOptions = view.getOptionsLink().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				showOptionsDialog();
			}
		});

		areListenersRegistered = true;
	}

	/**
	 * Clean-up by removing listeners.
	 */
	private void unRegisterListeners() {
		if (!areListenersRegistered) {
			return;
		}

		areGridListenersRegistered = false;
		linkOptions.removeHandler();

		if (areOptionsListenersRegistered) {
			btnOKHandler.removeHandler();
			btnCancelHandler.removeHandler();
			rbDateFieldHandler.removeHandler();
			rbTodayHandler.removeHandler();
		}

		areListenersRegistered = false;
		areOptionsListenersRegistered = false;
	}

	private void showOptionsDialog() {

		final DateBox dfStartDate = new DateBox();
		final NumberField nfHorizon = new NumberField();
		final RadioButton rbDateField = new RadioButton("FS_OPTIONS_START_DATE");
		final RadioButton rbToday = new RadioButton("FS_OPTIONS_START_DATE");

		dfStartDate.setFormat(new DateBox.DefaultFormat(DateTimeFormat
				.getFormat("yyyy-MM-dd")));
		nfHorizon.setAllowDecimals(false);
		nfHorizon.setDecimalPrecision(0);

		if (optionsDialog == null) {
			optionsDialog = buildOptionsDialog(dfStartDate, nfHorizon,
					rbDateField, rbToday);
		}

		String strDate = getCookie(FS_STARTDATE);
		if (FS_STARTDATE_TODAY.equals(strDate)) {
			rbToday.setValue(true);
			dfStartDate.setEnabled(false);
			dfStartDate.setValue(new GregorianCalendar().getTime());
		} else {
			rbDateField.setValue(true);
			dfStartDate.setEnabled(true);
			dfStartDate.setValue(DateUtil.getDateFromString((strDate)));
		}

		nfHorizon.setValue(getCookie(FS_HORIZON));

		optionsDialog.center();
		optionsDialog.show();

	}

	private DialogBox buildOptionsDialog(final DateBox dfStartDate,
			final NumberField nfHorizon, final RadioButton rbDateField,
			final RadioButton rbToday) {
		final DialogBox dialog = new DialogBox();
		dialog.setText(txtConsts.Options());

		FlexTable layout = new FlexTable();
		layout.setCellPadding(10);
		layout.setCellSpacing(10);

		HTML lblStartDate = new HTML(txtConsts.StartDate());
		HTML lblHorizon = new HTML(txtConsts.Horizon());

		int x = 0, y = 0;
		layout.setWidget(y, x++, lblStartDate);
		layout.setWidget(y, x++, rbDateField);
		layout.setWidget(y++, x, dfStartDate);
		x = 1;
		layout.setWidget(y, x++, rbToday);
		layout.setWidget(y++, x, new HTML(txtConsts.Today()));
		x = 0;
		layout.setWidget(y, x++, lblHorizon);
		layout.getFlexCellFormatter().setColSpan(y, x, 2);
		layout.setWidget(y++, x, nfHorizon);

		HasClickHandlers btnOk = new Button(txtConsts.OK());
		btnOKHandler = btnOk.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				String startDateValue = (rbToday.getValue()) ? FS_STARTDATE_TODAY
						: DateUtil.formatDate(dfStartDate.getValue());

				setCookie(FS_STARTDATE, startDateValue, 30);
				setCookie(FS_HORIZON, nfHorizon.getValueAsString(), 30);

				dialog.hide();

				reset();
				getTechnicianSchedule();
			}
		});

		HasClickHandlers btnCancel = new Button(txtConsts.Cancel());
		btnCancelHandler = btnCancel.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialog.hide();
			}
		});

		rbDateFieldHandler = rbDateField.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dfStartDate.setEnabled(true);
			}
		});

		rbTodayHandler = rbToday.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dfStartDate.setEnabled(false);
			}
		});

		HorizontalPanel buttonPanel = new HorizontalPanel();
		buttonPanel.addStyleName("BtnPanel");
		buttonPanel.add((Widget) btnOk);
		buttonPanel.add((Widget) btnCancel);

		FlexTable optionsPanel = new FlexTable();
		optionsPanel.setStyleName("Options");
		optionsPanel.setWidget(1, 0, layout);
		optionsPanel.setWidget(2, 0, buttonPanel);
		optionsPanel.getFlexCellFormatter().setHorizontalAlignment(2, 0,
				HasHorizontalAlignment.ALIGN_RIGHT);

		areOptionsListenersRegistered = true;

		dialog.add(optionsPanel);
		return dialog;
	}

	/**
	 * Call back to handle the ticket list retrieved from the back end.
	 * 
	 */
	class GetTechnicianScheduleCallback extends GotTechnicianSchedule {
		public void got(TechnicianSchedule schedule) {
			render(schedule);

			// in-case if something really goes wrong
			StatusIndicator.clear();
		}
	}
}
