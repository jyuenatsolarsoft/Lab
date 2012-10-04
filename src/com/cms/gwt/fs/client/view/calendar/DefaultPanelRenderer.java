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
 */

package com.cms.gwt.fs.client.view.calendar;

import java.util.Calendar;
import java.util.Date;

import com.cms.gwt.fs.client.model.calendar.EventData;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

import eu.future.earth.gwt.client.date.BaseDateRenderer;
import eu.future.earth.gwt.client.date.DateEventListener;
import eu.future.earth.gwt.client.date.DatePanel;
import eu.future.earth.gwt.client.date.EventPanel;

public class DefaultPanelRenderer extends BaseDateRenderer {

	public DefaultPanelRenderer() {
		super();
	}

	public void editAfterClick(Object data, DateEventListener listener) {
		// final DefaultStringEventDataDialog dialog = new
		// DefaultStringEventDataDialog(
		// this, listener, data, DateEventActions.UPDATE);
		// dialog.show();
		// dialog.center();
	}

	public void createNewAfterClick(Date currentDate, DateEventListener listener) {
		// final DefaultEventData data = new DefaultEventData();
		// data.setStartTime(currentDate);
		// final DefaultStringEventDataDialog dialog = new
		// DefaultStringEventDataDialog(
		// this, listener, data);
		// dialog.show();
		// dialog.center();
	}
	
	public void createNewAfterClick(Date currentDate, Date endDate,
			DateEventListener listener) {
		// final DefaultEventData data = new DefaultEventData();
		// data.setStartTime(currentDate);
		// data.setEndTime(endDate);
		// final DefaultStringEventDataDialog dialog = new
		// DefaultStringEventDataDialog(
		// this, listener, data);
		// dialog.show();
		// dialog.center();
	}

	public Widget createPickerPanel(Object newData, int day) {
		return null;
	}

	public boolean supportDayView() {
		return true;
	}

	public boolean supportMonthView() {
		return true;
	}

	public boolean showWholeDayEventView() {
		return false;
	}

	public boolean supportWeekView() {
		return true;
	}

	public boolean enableDragAndDrop() {
		return false;
	}

	public int getEndHour() {
		return 24;
	}

	public int getStartHour() {
		return 0;
	}

	public int showDaysInWeek() {
		return 7;
	}

	public Date getEndTime(Object event) {
		final EventData data = getData(event);
		return data.getEndTime();
	}

	private EventData getData(Object event) {
		if (event instanceof EventData) {
			return (EventData) event;
		} else {
			Window.alert("Not the Right type " + event.getClass());
			return null;
		}
	}

	public String getIdentifier(Object event) {
		final EventData data = getData(event);
		return data.getId();
	}

	public Date getStartTime(Object event) {
		final EventData data = getData(event);
		return data.getStartTime();
	}

	public void setEndTime(Object event, Date newEnd) {
		final EventData data = getData(event);
		data.setEndTime(newEnd);

	}

	public void setStartTime(Object event, Date newStart) {
		final EventData data = getData(event);
		data.setStartTime(newStart);
	}

	public boolean isWholeDayEvent(Object event) {
		final EventData data = getData(event);
		if (data != null) {
			return data.isAllDayEvent();
		} else {
			Window.alert("Programming Error " + event);
			return true;
		}
	}

	public EventPanel createPanel(Object newData, int viewType) {
		final EventData data = getData(newData);

		if (data.isAllDayEvent()) {
			DefaultWholeDayField panel = new DefaultWholeDayField(this);
			panel.setData(newData);
			return panel;
		} else {

			switch (viewType) {
			case DatePanel.MONTH: {
				final DefaultMonthField panel = new DefaultMonthField(this);
				panel.setData(newData);
				return panel;

			}
			case DatePanel.WEEK:
			case DatePanel.DAY:
			default: {
				final DefaultDayField panel = new DefaultDayField(this);
				panel.setData(newData);
				return panel;
			}
			}
		}
	}

	public boolean useShowMore() {
		return true;
	}

	public int getEventBottomHeight() {
		return 6;
	}

	public int getEventCornerSize() {
		return 3;
	}

	public int getEventMinimumHeight() {
		return 30;
	}

	public int getEventTopHeight() {
		return 18;
	}

	public int getIntervalHeight() {
		return 20;
	}

	public int getIntervalsPerHour() {
		return 4;
	}

	public int getScrollHour() {
		return 9;
	}

	public boolean isDurationAcceptable(int minutes) {
		return minutes >= (60 / getIntervalsPerHour());
	}

	public boolean show24HourClock() {
		return false;
	}

	public boolean showIntervalTimes() {
		return false;
	}

	// *** OVERRIDE METHODS ***//
	public String getTitleDisplayText(Calendar current, int viewType) {

		switch (viewType) {
		case DatePanel.MONTH: {
			DateTimeFormat formatter = DateTimeFormat.getFormat("MMMM yyyy");
			return formatter.format(current.getTime());

		}
		case DatePanel.WEEK: {
			return dateTexts.week() + " "
					+ (current.get(Calendar.WEEK_OF_YEAR));
		}
		case DatePanel.DAY:
		default: {
			DateTimeFormat formatter = DateTimeFormat.getFormat("MMMM dd");
			return formatter.format(current.getTime());
		}
		}

	}

	public DateTimeFormat getDayHeaderDateTimeFormatter() {
		return DateTimeFormat.getFormat("EEEE dd/MM");
	}
}
