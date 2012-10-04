/**
 * Work History:
 * 
 * AffanJ		2009-10-08		take out RPC from here and put it in the model: EventCacheController.
 */
package com.cms.gwt.fs.client.presenter;

import java.util.ArrayList;
import java.util.Date;

import com.cms.gwt.fs.client.HistoryConstants;
import com.cms.gwt.fs.client.model.calendar.EventCacheController;
import com.cms.gwt.fs.client.model.calendar.EventData;
import com.cms.gwt.fs.client.presenter.SchedulePresenter.SchedulePanelType;
import com.cms.gwt.fs.client.util.DateUtil;
import com.cms.gwt.fs.client.view.BaseView;
import com.cms.gwt.fs.client.view.calendar.ExtMultiView;
import com.google.gwt.user.client.History;
import com.google.inject.Inject;
import com.gwtext.client.util.Format;

import eu.future.earth.gwt.client.date.DateEvent;
import eu.future.earth.gwt.client.date.DateEventListener;
import eu.future.earth.gwt.client.date.DatePanel;

public class ServiceTicketCalendarPresenterImpl extends BasePresenterImpl
		implements ServiceTicketCalendarPresenter {

	private static final String COOKIE_CALENDAR_CENTER_DATE_KEY = "FS_CalendarCenterDate";
	private static final String COOKIE_CALENDAR_VIEW_TYPE_KEY = "FS_CalendarViewType";

	private final View view;

	private DateEventListener dateEventListener;
	private boolean areListenersRegistered;

	@Inject
	public ServiceTicketCalendarPresenterImpl(View view) {
		this.view = view;

		areListenersRegistered = false;
	}

	/**
	 * {@inheritDoc}
	 */
	public void showServiceTicketCalendar() {
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
		view.reset();
		while (view.getTicketCalendar().getWidgetCount() > 0) {
			view.getTicketCalendar().remove(0);
		}
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
		render();
	}

	/**
	 * Render the screen with the specified tickets.
	 * 
	 * This method basically populates the UI widget (i.e. a list widget) with
	 * the data received.
	 * 
	 */
	private void render() {

		view.initTicketCalendar();
		ExtMultiView calendar = view.getTicketCalendar();

		// set the center date and then the calendar view.
		// this will be from cookies, if any value exists, otherwise default
		// will be displayed.
		String strDate = getCookie(COOKIE_CALENDAR_CENTER_DATE_KEY);
		Date date = DateUtil.getDateFromString(strDate);
		if (date == null) {
			date = new Date();
		}
		calendar.setCenterDate(date);
		String strViewType = getCookie(COOKIE_CALENDAR_VIEW_TYPE_KEY);
		int viewType = parseInt(strViewType);
		if (!(viewType == DatePanel.DAY || viewType == DatePanel.WEEK || viewType == DatePanel.MONTH)) {
			viewType = DatePanel.WEEK;
		}
		calendar.setType(viewType);

		registerListeners();
	}

	/**
	 * Register listeners for widgets that will trigger events.
	 * 
	 * @param uniqueId
	 *            The id to retrieve from the grid's row which will determine
	 *            unique ticket id.
	 */
	private void registerListeners() {
		if (areListenersRegistered) {
			return;
		}

		dateEventListener = new DateEventListener() {
			public void handleDateEvent(DateEvent newEvent) {
				switch (newEvent.getCommand()) {
				case ADD:
				case UPDATE:
				case REMOVE:
				case DRAG_DROP:
				case REPAINT:
				case SELECT_DAY:
				case SELECT_MONTH:
				case RELOAD: {
					break;
				}
				case EDIT: {
					// since we want a non-edit-able calendar, therefore, treat
					// EDIT as a single-click to invoke other actions: show
					// TicketDetails
					EventData data = (EventData) newEvent.getData();
					checkEventData(data);
					break;
				}
				}
			}

		};
		view.getTicketCalendar().addDateListener(dateEventListener);

		view.getTicketCalendar().addOnClickHandler(new OnClickHandler() {
			public void onClick(Date centerDate, int viewType) {
				setSessionCookie(COOKIE_CALENDAR_CENTER_DATE_KEY, DateUtil
						.formatDate(centerDate, "yyyy-MM-dd"));
				setSessionCookie(COOKIE_CALENDAR_VIEW_TYPE_KEY,
						formatInt(viewType));
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

		view.getTicketCalendar().removeDateListener(dateEventListener);

		areListenersRegistered = false;
	}

	/**
	 * If the view is MONTH and there is more than 1 ticket then switch to DAY
	 * view so that user may pick the exact ticket they want to view. Otherwise,
	 * open the ticket details.
	 * 
	 * @param data
	 *            EventData containing information, such as ticketNumber.
	 */
	@SuppressWarnings("deprecation")
	private void checkEventData(EventData data) {

		ExtMultiView calendar = view.getTicketCalendar();

		Date startTime = data.getStartTime();
		Date startDate = new Date(startTime.getYear(), startTime.getMonth(),
				startTime.getDate(), 0, 0, 0);
		Date endTime = data.getEndTime();
		Date endDate = new Date(endTime.getYear(), endTime.getMonth(), endTime
				.getDate(), 23, 59, 59);

		ArrayList<EventData> events = ((EventCacheController) calendar
				.getController()).getEventsInRange(startDate, endDate);
		int numEvents = events.size();

		if (numEvents > 1 && calendar.getCurrent().getType() == DatePanel.MONTH) {
			// show DAY view
			calendar.setType(DatePanel.DAY);
			calendar.setDate(startDate);
			return;
		}

		// show the schedule panel
		String id = data.getId();
		String[] ids = id.split(HistoryConstants.CONCAT_VALUES_REGEX, 3);
		String ticketNumber = ids[0];
		String panelNumber = "5";
		String scheduleId = ids[1];

		History.newItem(Format.format(HistoryConstants.SCHEDULE_PANEL_VALUE,
				ticketNumber, panelNumber, scheduleId, String
						.valueOf(SchedulePanelType.MAIN.value)));
	}

}
