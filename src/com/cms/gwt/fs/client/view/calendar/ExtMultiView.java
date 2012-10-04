/*
 * Copyright 2007 Future Earth, info@future-earth.eu
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * Work History:
 * 
 * AffanJ		2009-10-07		Renamed from MultiView to ExtMultiView
 * 								Modified extensively from original version.
 * AffanJ		2009-10-08		Using custom MonthPanel.
 */

package com.cms.gwt.fs.client.view.calendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.cobogw.gwt.user.client.ui.Button;
import org.cobogw.gwt.user.client.ui.RoundedPanel;

import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.model.calendar.EventCacheController;
import com.cms.gwt.fs.client.presenter.ServiceTicketCalendarPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import eu.future.earth.gwt.client.FtrGwtDateCss;
import eu.future.earth.gwt.client.date.DateEvent;
import eu.future.earth.gwt.client.date.DateEventListener;
import eu.future.earth.gwt.client.date.DateImages;
import eu.future.earth.gwt.client.date.DatePanel;
import eu.future.earth.gwt.client.date.DateRenderer;
import eu.future.earth.gwt.client.date.EventController;
import eu.future.earth.gwt.client.date.week.WeekPanel;

public class ExtMultiView extends DockPanel implements ClickHandler,
		DateEventListener {

	protected static final DateImages images = (DateImages) GWT
			.create(DateImages.class);
	protected final TextConstants txtConsts = (TextConstants) GWT
			.create(TextConstants.class);

	/** DAY label */
	protected Label day = new Label(txtConsts.Day());
	/** DAY panel */
	protected WeekPanel dayPanel = null;
	/** DAY tab */
	protected RoundedPanel dayRounded = new RoundedPanel(day, RoundedPanel.TOP,
			2);

	/** WEEK label */
	protected Label week = new Label(txtConsts.Week());
	/** WEEK panel */
	protected WeekPanel weekPanel = null;
	/** WEEK tab */
	protected RoundedPanel weekRounded = new RoundedPanel(week,
			RoundedPanel.TOP, 2);

	/** MONTH label */
	protected Label month = new Label(txtConsts.Month());
	/** MONTH panel */
	protected ExtMonthPanel monthPanel = null;
	/** MONTH tab */
	protected RoundedPanel monthRounded = new RoundedPanel(month,
			RoundedPanel.TOP, 2);

	/** Panel at the top with all the options */
	protected HorizontalPanel topPanel = new HorizontalPanel();

	/** Panel (at the top) for navigation */
	protected HorizontalPanel navigation = new HorizontalPanel();

	/** Panel (at the top) holding tab options */
	protected HorizontalPanel viewType = new HorizontalPanel();

	/** Panel with the actual calendar */
	protected RoundedPanel body = new RoundedPanel(RoundedPanel.ALL, 3);

	/** Either MONTH, WEEK, or DAY */
	protected int type = -1;
	/** Either MONTH, WEEK, or DAY */
	protected DatePanel currentView = null;

	protected Image prevMore = images.btn_prev_2().createImage();
	protected Image prev = images.btn_prev().createImage();
	protected Label dayInfo = new Label();
	protected Image next = images.btn_next().createImage();
	protected Image nextMore = images.btn_next_2().createImage();
	protected Button today = new Button(txtConsts.Today());
	protected Label eventInfo = new Label();

	protected ArrayList<Widget> items = new ArrayList<Widget>();
	protected Date centerDate = new Date();
	protected EventController controller = null;
	protected DateRenderer renderer = null;

	protected ArrayList<DateEventListener> dateListeners = new ArrayList<DateEventListener>();
	protected int height = 300;

	/** Handler to fire events on Service Ticket Calendar */
	protected ServiceTicketCalendarPresenter.OnClickHandler onClickHandler;

	public ExtMultiView(EventController newController, DateRenderer newRenderer) {
		this(newController, newRenderer, DatePanel.WEEK);
	}

	public ExtMultiView(EventController newController,
			DateRenderer newRenderer, int defaultType) {
		this(newController, newRenderer, defaultType, false, true);
	}

	public ExtMultiView(EventController newController,
			DateRenderer newRenderer, int defaultType,
			boolean alignNavigationCenter, boolean displayTodayButton) {

		super();
		super.setSpacing(0);
		super.setStyleName(FtrGwtDateCss.DATE_TYPE_PANEL);

		renderer = newRenderer;
		controller = newController;

		topPanel.setSpacing(0);
		topPanel.setBorderWidth(0);
		topPanel.setStyleName(FtrGwtDateCss.DATE_TYPE_PANEL);
		topPanel.setWidth("100%");
		if (alignNavigationCenter) {
			topPanel
					.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		}

		prevMore.addClickHandler(this);
		DOM.setStyleAttribute(prevMore.getElement(), "cursor", "pointer");
		prev.addClickHandler(this);
		DOM.setStyleAttribute(prev.getElement(), "cursor", "pointer");
		next.addClickHandler(this);
		DOM.setStyleAttribute(next.getElement(), "cursor", "pointer");
		nextMore.addClickHandler(this);
		DOM.setStyleAttribute(nextMore.getElement(), "cursor", "pointer");
		today.addClickHandler(this);

		navigation.setSpacing(0);
		navigation.addStyleName("Calendar-Navigation");
		navigation.setVerticalAlignment(ALIGN_MIDDLE);
		if (alignNavigationCenter) {
			navigation
					.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		}

		if (renderer.useShowMore()) {
			navigation.add(prevMore);
			navigation.setCellVerticalAlignment(prevMore, ALIGN_MIDDLE);
			navigation.add(new HTML("&nbsp;"));
		}
		navigation.add(prev);
		navigation.setCellVerticalAlignment(prev, ALIGN_MIDDLE);
		navigation.add(new HTML("&nbsp;"));
		navigation.add(dayInfo);
		dayInfo.setStyleName("Calendar-DayInfo");
		navigation.setCellVerticalAlignment(dayInfo, ALIGN_MIDDLE);
		navigation.add(new HTML("&nbsp;"));
		navigation.add(next);
		navigation.setCellVerticalAlignment(next, ALIGN_MIDDLE);
		navigation.add(new HTML("&nbsp;"));
		if (renderer.useShowMore()) {
			navigation.add(nextMore);
			navigation.setCellVerticalAlignment(nextMore, ALIGN_MIDDLE);
			navigation.add(new HTML("&nbsp;"));
		}
		if (displayTodayButton) {
			navigation.add(today);
			today.addStyleName("Calendar-BtnToday");
			today.setSize(55);
			navigation.setCellVerticalAlignment(today, ALIGN_MIDDLE);
			navigation.add(new HTML("&nbsp;"));
		}

		navigation.add(eventInfo);
		eventInfo.setStyleName("Calendar-EventInfo");

		topPanel.add(navigation);
		topPanel.setCellVerticalAlignment(navigation,
				HorizontalPanel.ALIGN_BOTTOM);

		topPanel.add(viewType);
		topPanel.setCellHorizontalAlignment(viewType,
				HorizontalPanel.ALIGN_RIGHT);
		topPanel.setCellVerticalAlignment(viewType,
				HorizontalPanel.ALIGN_BOTTOM);

		super.add(topPanel, DockPanel.NORTH);
		super.setCellHeight(topPanel, "15px");

		viewType.setSpacing(0);
		viewType.setBorderWidth(0);
		viewType.setStyleName(FtrGwtDateCss.DATE_TYPE_PANEL);
		viewType.addStyleName("Calendar-ViewType");

		int tabCount = 0;
		if (renderer.supportDayView()) {
			tabCount++;
		}
		if (renderer.supportWeekView()) {
			tabCount++;
		}
		if (renderer.supportMonthView()) {
			tabCount++;
		}

		if (tabCount > 1) {
			if (renderer.supportDayView()) {
				addTab(dayRounded, day);
			}
			if (renderer.supportWeekView()) {
				addTab(weekRounded, week);
			}
			if (renderer.supportMonthView()) {
				addTab(monthRounded, month);
			}
			viewType.add(new HTML("&nbsp;"));
			viewType.add(new HTML("&nbsp;"));
		}

		super.add(body, DockPanel.CENTER);
		super.setCellVerticalAlignment(body, DockPanel.ALIGN_TOP);

		final HTML space = new HTML("&nbsp;");
		super.add(space, DockPanel.SOUTH);
		space.setHeight("10px");

		// do NOT initialize in constructor. Implementor is responsible for
		// setting the initial center date and initial view type.
		// setType(defaultType);
	}

	public DateRenderer getRenderer() {
		return renderer;
	}

	public EventController getController() {
		return controller;
	}

	protected void onAttach() {
		super.onAttach();
		scrollToHour(renderer.getScrollHour());
	}

	public void setController(EventController controller) {
		this.controller = controller;
		// reload event data after changing the event controller
		reloadData();
	}

	private void addTab(RoundedPanel label, Label button) {
		items.add(label);
		items.add(button);
		button.addClickHandler(this);
		viewType.add(new HTML("&nbsp;"));
		label.addStyleName("Calendar-ViewTypeOptions");
		viewType.add(label);
	}

	public void setHeight(int newHeight) {
		height = newHeight;
		if (currentView != null) {
			getCurrent().setHeight(height);
		}
	}

	public int getHeight() {
		return height;
	}

	public void rebuildPanel() {
		if (weekPanel != null) {
			weekPanel.buildPanel();
		}
		if (dayPanel != null) {
			dayPanel.buildPanel();
		}
	}

	public void scrollToHour(int hour) {
		DatePanel current = getCurrent();
		if (current instanceof WeekPanel) {
			WeekPanel real = (WeekPanel) current;
			real.scrollToHour(hour);
		}
	}

	private void showCurrent(DatePanel newShows) {
		if (currentView != null) {
			Widget real = (Widget) currentView;
			body.remove(real);
		}

		Widget newReal = (Widget) newShows;

		currentView = newShows;
		body.setWidget(newReal);
		body.setCornerStyleName(FtrGwtDateCss.TAB_SELECTED);
		newReal.setWidth("100%");
		setHeight(height);
		setDate(centerDate);
	}

	public void setSelected(RoundedPanel label, Label button) {
		for (int i = 0; i < items.size(); i++) {
			final Widget wi = items.get(i);
			if (wi instanceof RoundedPanel) {
				final RoundedPanel real = (RoundedPanel) wi;
				real.setCornerStyleName(FtrGwtDateCss.TAB);
			} else {
				wi.setStyleName(FtrGwtDateCss.TAB_BODY);
			}
		}
		label.setCornerStyleName(FtrGwtDateCss.TAB_SELECTED);
		button.setStyleName(FtrGwtDateCss.TAB_SELECTED_BODY);
	}

	public void addDateListener(DateEventListener listener) {
		if (!dateListeners.contains(listener)) {
			dateListeners.add(listener);
		}
	}

	public void removeDateListener(DateEventListener listener) {
		dateListeners.remove(listener);
	}

	public void fireDateEvent(DateEvent newDateEvent) {
		for (int i = 0; i < dateListeners.size(); i++) {
			final DateEventListener shouldHandle = dateListeners.get(i);
			shouldHandle.handleDateEvent(newDateEvent);
		}
	}

	public void setType(int newType) {
		if (type != newType) {
			type = newType;

			switch (type) {
			case DatePanel.MONTH: {
				if (monthPanel == null) {
					monthPanel = new ExtMonthPanel(renderer);
					monthPanel.addDateListener(this);
				}
				showCurrent(monthPanel);
				setSelected(monthRounded, month);
				break;
			}
			case DatePanel.WEEK: {
				if (weekPanel == null) {
					weekPanel = new WeekPanel(renderer.showDaysInWeek(),
							renderer);
					weekPanel.addDateListener(this);
				}
				showCurrent(weekPanel);
				setSelected(weekRounded, week);
				break;
			}
			case DatePanel.DAY: {
				if (dayPanel == null) {
					dayPanel = new WeekPanel(1, renderer);
					dayPanel.addDateListener(this);
				}
				showCurrent(dayPanel);
				setSelected(dayRounded, day);
				break;
			}
			}

			scrollToHour(renderer.getScrollHour());
		}
	}

	public void setDate(Date newCenterDate) {
		setDate(newCenterDate, true);
	}

	public void setDate(Date newCenterDate, boolean setDatapanelDate) {
		setDate(newCenterDate, setDatapanelDate, true);
	}

	/**
	 * Method that will check if the date's already exists in cache. Otherwise
	 * make RPC and then render the calendar panel.
	 * 
	 * Add a reloadData argument so that the controller can handle if it uses
	 * cached data or make a RPC call.
	 * 
	 * @param newCenterDate
	 * @param setDatapanelDate
	 * @param reloadData
	 */
	public void setDate(Date newCenterDate, boolean setDatapanelDate,
			boolean reloadData) {
		centerDate = newCenterDate;
		final DatePanel datapanel = getCurrent();
		if (setDatapanelDate) {
			datapanel.setDate(centerDate);
		}
		datapanel.clearData();
		((EventCacheController) controller).getEventsForRange(datapanel
				.getFirstDateShow(), datapanel.getLastDateShow(), this,
				reloadData);
		dayInfo.setText(datapanel.getDisplayText());
	}

	public void clearData() {
		final DatePanel datapanel = getCurrent();
		datapanel.clearData();
	}

	public void setEvents(Object[] events) {
		// set total # events for the current view
		eventInfo.setText(txtConsts.NumberOfEvents() + " " + events.length);

		final DatePanel datapanel = getCurrent();
		datapanel.clearData();
		addEvents(events);
	}

	public void addEvents(Object[] events) {
		final DatePanel datapanel = getCurrent();
		if (events != null) {
			for (int i = 0; i < events.length; i++) {
				datapanel.addEventData(events[i]);
			}
		}

	}

	public void setEventsByList(List<?> events) {
		final DatePanel datapanel = getCurrent();
		datapanel.clearData();
		addEventsList(events);
	}

	public void addEventsList(List<?> events) {
		final DatePanel datapanel = getCurrent();
		if (events != null) {
			for (int i = 0; i < events.size(); i++) {
				datapanel.addEventData(events.get(i));
			}
		}

	}

	public void setEventsByArrayList(ArrayList<?> events) {
		final DatePanel datapanel = getCurrent();
		datapanel.clearData();
		datapanel.addEventsByList(events);
	}

	public void addEventsArrayList(List<?> events) {
		final DatePanel datapanel = getCurrent();
		if (events != null) {
			datapanel.addEventsByList(events);
		}
	}

	public void reloadData() {
		setDate(centerDate, true, true);
	}

	public void repaintData() {
		setDate(centerDate, true, false);
	}

	public DatePanel getCurrent() {
		if (currentView instanceof DatePanel) {
			return currentView;
		} else {
			return null;
		}
	}

	public void handleDateEvent(DateEvent newEvent) {
		switch (newEvent.getCommand()) {
		case ADD: {
			controller.addEvent(newEvent.getData());
			break;
		}
		case REPAINT: {
			repaintData();
			break;
		}
		case RELOAD: {
			reloadData();
			break;
		}
		case REMOVE: {
			controller.removeEvent(newEvent.getData());
			break;
		}
		case SELECT_DAY: {
			if (type != DatePanel.DAY) {
				setType(DatePanel.DAY);
			}
			setDate(newEvent.getDate());

			// fire event to implementor
			if (onClickHandler != null) {
				onClickHandler.onClick(centerDate, currentView.getType());
			}

			break;
		}
		case UPDATE: {
			controller.updateEvent(newEvent.getData());
			break;
		}
		case DRAG_DROP: {
			controller.updateEvent(newEvent.getData());
			break;
		}

		}

		fireDateEvent(newEvent);

	}

	public void onClick(ClickEvent event) {
		if (event.getSource() == day) {
			setType(DatePanel.DAY);

		} else if (event.getSource() == week) {
			setType(DatePanel.WEEK);

		} else if (event.getSource() == month) {
			setType(DatePanel.MONTH);

		} else if (event.getSource() == prev) {
			final DatePanel datapanel = getCurrent();
			setDate(datapanel.prev(), false);

		} else if (event.getSource() == next) {
			final DatePanel datapanel = getCurrent();
			setDate(datapanel.next(), false);

		} else if (event.getSource() == prevMore) {
			final DatePanel datapanel = getCurrent();
			setDate(datapanel.prevMore(), false);

		} else if (event.getSource() == nextMore) {
			final DatePanel datapanel = getCurrent();
			setDate(datapanel.nextMore(), false);

		} else if (event.getSource() == today) {
			setDate(new Date());
		}

		// fire event to implementor
		if (onClickHandler != null) {
			onClickHandler.onClick(centerDate, currentView.getType());
		}

	}

	// *** MORE CUSTOM METHODS *** //

	public void setCenterDate(Date centerDate) {
		this.centerDate = centerDate;
	}

	public void addOnClickHandler(
			ServiceTicketCalendarPresenter.OnClickHandler onClickHandler) {
		this.onClickHandler = onClickHandler;
	}

	public void removeOnClickHandler() {
		onClickHandler = null;
	}

	public void reset() {
		removeOnClickHandler();
	}

}
