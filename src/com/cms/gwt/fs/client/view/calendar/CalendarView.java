package com.cms.gwt.fs.client.view.calendar;

import com.cms.gwt.fs.client.HistoryConstants;
import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.model.calendar.EventCacheController;
import com.cms.gwt.fs.client.presenter.ServiceTicketCalendarPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class CalendarView extends Composite implements
		ServiceTicketCalendarPresenter.View {

	private final VerticalPanel panel;
	private final VerticalPanel calendarContainer;
	private final TextConstants txtConsts;

	private ExtMultiView calendar;

	/**
	 * Constructor.
	 */
	public CalendarView() {

		panel = new VerticalPanel();
		panel.setStyleName("TicketList");
		panel.setWidth("100%");
		panel.setSpacing(0);
		initWidget(panel);

		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		HorizontalPanel linkPanel = new HorizontalPanel();
		linkPanel.addStyleName("TicketList-LinkPanel");

		Hyperlink link = new Hyperlink(txtConsts.EventsList(), true,
				HistoryConstants.SERVICE_TICKET_LIST_KEY
						+ HistoryConstants.VALUE_SPLITTER
						+ HistoryConstants.SERVICE_TICKET_EVENT_LIST
						+ HistoryConstants.TOKEN_SPLITTER);
		link.setTitle(txtConsts.Show() + " " + txtConsts.EventsList());
		link.addStyleName("TicketList-Link");
		linkPanel.add(link);

		panel.add(linkPanel);

		calendarContainer = new VerticalPanel();
		calendarContainer.setWidth("100%");
		calendarContainer.setStyleName("TicketList-Calendar");
		panel.add(calendarContainer);

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
		if (calendar != null) {
			calendar.reset();
		}
		while (calendarContainer.getWidgetCount() > 0) {
			calendarContainer.remove(0);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void setReadOnly(boolean readOnly) {
	}

	/**
	 * {@inheritDoc}
	 */
	public ExtMultiView getTicketCalendar() {
		return calendar;
	}

	public void initTicketCalendar() {
		makeCalendar();
	}

	private void makeCalendar() {
		EventCacheController eventCacheController = new EventCacheController();
		calendar = new ExtMultiView(eventCacheController,
				new DefaultPanelRenderer());

		calendar.setHeight(500);
		calendar.setWidth("90%");

		reset();
		calendarContainer.add(calendar);
		calendarContainer.setCellHorizontalAlignment(calendar,
				HasHorizontalAlignment.ALIGN_LEFT);
	}
}
