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
 * AffanJ		2009-10-08		Modified to fit requirements
 */

package com.cms.gwt.fs.client.view.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

import eu.future.earth.gwt.client.FtrGwtDateCss;
import eu.future.earth.gwt.client.date.DateEvent;
import eu.future.earth.gwt.client.date.DateEventListener;
import eu.future.earth.gwt.client.date.DatePanel;
import eu.future.earth.gwt.client.date.DateRenderer;
import eu.future.earth.gwt.client.date.DateUtils;
import eu.future.earth.gwt.client.date.EventPanel;
import eu.future.earth.gwt.client.date.month.MonthPanelDragController;
import eu.future.earth.gwt.client.date.month.MonthPanelDropController;

public class ExtMonthPanel extends AbsolutePanel implements ResizeHandler,
		DateEventListener, DatePanel {

	private ArrayList<MonthPanelDropController> dropControllers = new ArrayList<MonthPanelDropController>();

	private Calendar current = new GregorianCalendar();

	private Calendar helperCal = new GregorianCalendar();

	private Grid monthPanel = new Grid(8, 7);

	private int firstDayOfWeek = Calendar.MONDAY;

	private MonthPanelDragController dragController = null;

	private DateRenderer renderer = null;

	public ExtMonthPanel(DateRenderer newRenderer) {
		super();
		setRenderer(newRenderer);
		setStyleName(FtrGwtDateCss.MONTH_PANEL);
		monthPanel.setStyleName(FtrGwtDateCss.MONTH_PANEL);
		this.add(monthPanel, 0, 0);
	}

	public void setRenderer(DateRenderer newRenderer) {
		renderer = newRenderer;
		if (renderer.enableDragAndDrop()) {
			if (dragController != null) {
				for (int i = 0; i < dropControllers.size(); i++) {
					final MonthPanelDropController real = dropControllers
							.get(i);
					dragController.unregisterDropController(real);
				}
			}
			dragController = new MonthPanelDragController(this);
		}
		buildPanel();
	}

	public void setDate(Date newDate) {
		final Calendar test = new GregorianCalendar();
		test.setTime(newDate);
		test.set(Calendar.HOUR_OF_DAY, 0);
		test.set(Calendar.MINUTE, 0);
		test.set(Calendar.SECOND, 0);
		test.set(Calendar.MILLISECOND, 0);
		if (DateUtils.isSameDay(test, current)) {
			return;
		}
		current.setTime(newDate);
		current.set(Calendar.HOUR_OF_DAY, 0);
		current.set(Calendar.MINUTE, 0);
		current.set(Calendar.SECOND, 0);
		current.set(Calendar.MILLISECOND, 0);
		buildPanel();
	}

	private int xPos = 0;

	private int yPos = 0;

	private ArrayList<DateEventListener> dateListeners = new ArrayList<DateEventListener>();

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

	private Date firstShow = null;

	private Date lastShow = null;

	private Date firstLogical = null;

	private Date lastLogical = null;

	private void buildPanel() {
		if (renderer.enableDragAndDrop()) {
			for (int i = 0; i < dropControllers.size(); i++) {
				final MonthPanelDropController real = dropControllers.get(i);
				dragController.unregisterDropController(real);
			}
			dropControllers.clear();
		}

		monthPanel.clear();
		DOM.setStyleAttribute(monthPanel.getElement(), "width", "100%");
		DOM.setStyleAttribute(monthPanel.getElement(), "height", "100%");

		helperCal.setFirstDayOfWeek(firstDayOfWeek);
		xPos = 0;
		yPos = 0;
		final Calendar today = new GregorianCalendar();
		final DateTimeFormat dayFormatter = DateTimeFormat.getFormat("EEEE");
		for (int i = firstDayOfWeek; i < 7 + firstDayOfWeek; i++) {
			helperCal.set(Calendar.DAY_OF_WEEK, i);
			HTML heading = new HTML(dayFormatter.format(helperCal.getTime()));
			heading.setStyleName("Calendar-MonthHeading");
			monthPanel.setWidget(yPos, xPos, heading);
			monthPanel.getCellFormatter().setHorizontalAlignment(yPos, xPos,
					HorizontalPanel.ALIGN_CENTER);
			monthPanel.getCellFormatter().setHeight(yPos, xPos, "9%");
			monthPanel.getColumnFormatter().setWidth(xPos, "12%");
			xPos++;
		}
		yPos++;

		// set logical first day of the month
		helperCal.setTime(current.getTime());
		helperCal.set(Calendar.DAY_OF_MONTH, 1);
		helperCal.set(Calendar.HOUR_OF_DAY, 0);
		helperCal.set(Calendar.MINUTE, 0);
		helperCal.set(Calendar.SECOND, 0);
		helperCal.set(Calendar.MILLISECOND, 0);
		firstLogical = helperCal.getTime();
		// set logical last day of the month
		helperCal.add(Calendar.MONTH, 1);
		helperCal.add(Calendar.MILLISECOND, -1);
		lastLogical = helperCal.getTime();
		// now loop over full weeks for showing days
		helperCal.setTime(current.getTime());
		helperCal.add(Calendar.DAY_OF_MONTH, -(helperCal
				.get(Calendar.DAY_OF_MONTH)));
		while (helperCal.get(Calendar.DAY_OF_WEEK) != helperCal
				.getFirstDayOfWeek()) {
			helperCal.add(Calendar.DAY_OF_MONTH, -1);
		}
		firstShow = helperCal.getTime();
		for (int y = 1; y < 8; y++) {
			for (int x = 0; x < 7; x++) {
				monthPanel.getCellFormatter().setHeight(y, x, "13%");
				monthPanel.getCellFormatter().addStyleName(y, x, "Calendar-Month-Height");
				final ExtMonthPanelDayPanel result = makeDayPanel(helperCal);

				// apply styles here for last month's days, today and next
				// month's days
				if (DateUtils.isSameDay(today, helperCal)) {
					result.addStyleName("Calendar-Month-Today");

				} else if (current.get(Calendar.MONTH) == helperCal.get(Calendar.MONTH)) {
					result.addStyleName("Calendar-Month-CurrentMonth");

				} else {
					result.addStyleName("Calendar-Month-OtherMonth");					
				}

				monthPanel.setWidget(y, x, result);
				// we do NOT have dragging enabled
				// if (renderer.enableDragAndDrop() && dragController != null) {
				// final MonthPanelDropController dropController = new
				// MonthPanelDropController(
				// result);
				// dropControllers.add(dropController);
				// dragController.registerDropController(dropController);
				// }
				helperCal.add(Calendar.DAY_OF_MONTH, 1);
			}
		}
		lastShow = helperCal.getTime();
		Window.addResizeHandler(this);
	}

	private ExtMonthPanelDayPanel makeDayPanel(Calendar calender) {
		final ExtMonthPanelDayPanel result = new ExtMonthPanelDayPanel(calender
				.get(Calendar.DAY_OF_MONTH), calender.getTime(), this, renderer);
		return result;
	}

	public void addEventData(Object newEvent) {
		for (int y = 1; y < 8; y++) {
			for (int x = 0; x < 7; x++) {

				final Widget cur = monthPanel.getWidget(y, x);
				if (cur instanceof ExtMonthPanelDayPanel) {
					final ExtMonthPanelDayPanel result = (ExtMonthPanelDayPanel) cur;
					if (result.isDay(newEvent)) {
						final EventPanel entry = renderer.createPanel(newEvent,
								getType());
						// if (dragController != null && entry.isDraggable()) {
						// dragController.makeDraggable(entry, entry
						// .getDraggableItem());
						// }
						result.addEvent(entry);
					}
				}
			}
		}

	}

	public void onResize(ResizeEvent event) {

	}

	public void handleDateEvent(DateEvent newEvent) {
		switch (newEvent.getCommand()) {
		case ADD: {
			addEventData(newEvent.getData());
			break;
		}
		case UPDATE: {
			updateEventData(newEvent.getData());
			break;
		}
		case REMOVE: {
			removeEventData(newEvent.getData());
			break;
		}
		case EDIT: {
			renderer.editAfterClick(newEvent.getData(), this);
			break;
		}

		}

		final DateEvent toFire = new DateEvent(this, newEvent.getCommand(),
				newEvent.getData());
		fireDateEvent(toFire);

	}

	public DateRenderer getRenderer() {
		return renderer;
	}

	public Date next() {
		current.add(Calendar.MONTH, 1);
		buildPanel();
		return current.getTime();
	}

	public Date prev() {
		current.add(Calendar.MONTH, -1);
		buildPanel();
		return current.getTime();
	}

	public Date nextMore() {
		current.add(Calendar.YEAR, 1);
		buildPanel();
		return current.getTime();
	}

	public Date prevMore() {
		current.add(Calendar.YEAR, -1);
		buildPanel();
		return current.getTime();
	}

	public void updateEventData(Object newEvent) {
		removeEventData(newEvent);
		addEventData(newEvent);
	}

	public void removeEventData(Object newEvent) {
		for (int y = 1; y < 8; y++) {
			for (int x = 0; x < 7; x++) {
				Widget got = monthPanel.getWidget(y, x);
				if (got instanceof ExtMonthPanelDayPanel) {
					final ExtMonthPanelDayPanel result = (ExtMonthPanelDayPanel) got;
					result.removeEvent(newEvent);
				}
			}
		}

	}

	public String getDisplayText() {
		return renderer.getTitleDisplayText(current, getType());
	}

	public Date getFirstDateShow() {
		return firstShow;
	}

	public Date getLastDateShow() {
		return lastShow;
	}

	public Date getFirstDateLogical() {
		return firstLogical;
	}

	public Date getLastDateLogical() {
		return lastLogical;
	}

	public void clearData() {
		for (int y = 1; y < 8; y++) {
			for (int x = 0; x < 7; x++) {
				Widget got = monthPanel.getWidget(y, x);
				if (got instanceof ExtMonthPanelDayPanel) {
					final ExtMonthPanelDayPanel result = (ExtMonthPanelDayPanel) got;
					result.clearEvents();
				}
			}
		}

	}

	public void setHeight(int newHeight) {
		super.setHeight(newHeight + "px");
	}

	public int getType() {
		return DatePanel.MONTH;
	}

	public void addEventsByList(List<?> events) {
		if (events != null) {
			for (int i = 0; i < events.size(); i++) {
				addEventData(events.get(i));
			}
		}
	}

}
