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
 * AffanJ		2009-10-08		Modified extensively to fit requirements.
 */

package com.cms.gwt.fs.client.view.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;

import eu.future.earth.gwt.client.FtrGwtDateCss;
import eu.future.earth.gwt.client.date.DateEvent;
import eu.future.earth.gwt.client.date.DateRenderer;
import eu.future.earth.gwt.client.date.DateUtils;
import eu.future.earth.gwt.client.date.DayView;
import eu.future.earth.gwt.client.date.EventPanel;
import eu.future.earth.gwt.client.date.DateEvent.DateEventActions;
import eu.future.earth.gwt.client.date.month.EventComparator;

public class ExtMonthPanelDayPanel extends DockPanel implements DayView,
		MouseDownHandler {

	private Label dayLabel = null;

	private FocusPanel clickPanel = new FocusPanel();

	private FlowPanel entryPanel = new FlowPanel();

	private Calendar helper = new GregorianCalendar();

	private Calendar theDay = new GregorianCalendar();

	private ExtMonthPanel parent = null;

	private DateRenderer renderer = null;

	private EventComparator sorter = null;

	public ExtMonthPanelDayPanel(int day, final Date newDate,
			final ExtMonthPanel newParent, DateRenderer newRenderer) {
		super();
		renderer = newRenderer;
		sorter = new EventComparator();
		parent = newParent;
		theDay.setTime(newDate);
		dayLabel = new Label(String.valueOf(day));
		dayLabel.setHorizontalAlignment(Label.ALIGN_RIGHT);
		dayLabel.setStyleName(FtrGwtDateCss.DATE_DAY_HEADER);
		dayLabel.addStyleName("Calendar-MonthDayHeader");
		// add click-handler to goto DayPanel from MonthPanel
		dayLabel.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				DateEvent toFire = new DateEvent();
				toFire.setCommand(DateEventActions.SELECT_DAY);
				toFire.setDate(newDate);
				newParent.fireDateEvent(toFire);
			}
		});
		super.setStyleName(FtrGwtDateCss.DATE_DAY);
		clickPanel.addMouseDownHandler(this);
		super.add(dayLabel, DockPanel.NORTH);
		super.setCellHeight(dayLabel, "10px");
		clickPanel.setStyleName(FtrGwtDateCss.DATE_MONTH_DAY_DATA);
		entryPanel.setStyleName(FtrGwtDateCss.DATE_DAY_DATA);
		super.add(entryPanel, DockPanel.CENTER);
		super.setCellHeight(entryPanel, "60px");

		entryPanel.add(clickPanel);
	}

	public boolean isDay(Object newEvent) {
		if (DateUtils.isSameDay(theDay.getTime(), renderer
				.getStartTime(newEvent))) {
			return true;
		} else {
			if (renderer.getEndTime(newEvent) != null) {
				if (DateUtils.isSameDay(theDay.getTime(), renderer
						.getEndTime(newEvent))) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}

	}

	private ArrayList<EventPanel> events = new ArrayList<EventPanel>();

	public void addEvent(EventPanel newEvent) {
		if (renderer.enableDragAndDrop()) {
			newEvent.addStyleName(FtrGwtDateCss.DND_GETTING_STARTED_LABEL);
		}

		events.add(newEvent);
		repaintPanel();
	}

	private void repaintPanel() {
		entryPanel.clear();
		Collections.sort(events, sorter);
		for (int i = 0; i < events.size(); i++) {
			final EventPanel event = events.get(i);
			entryPanel.add(event);
			event.repaintPanel();
		}
		entryPanel.add(clickPanel);
	}

	public void removeEvent(Object newEvent) {
		for (int i = 0; i < events.size(); i++) {
			final EventPanel ev = events.get(i);
			final String idOne = renderer.getIdentifier(ev.getData());
			final String idTwo = renderer.getIdentifier(newEvent);
			if (idOne.equals(idTwo)) {
				entryPanel.remove(ev);
				events.remove(ev);
			}
		}

	}

	public void clearEvents() {
		for (int i = 0; i < events.size(); i++) {
			final EventPanel ev = events.get(i);
			entryPanel.remove(ev);

		}
		events.clear();
	}

	public void addEventByDrop(EventPanel newEvent) {
		if (newEvent != null) {

			events.add(newEvent);

			newEvent.addStyleName(FtrGwtDateCss.DND_GETTING_STARTED_LABEL);
			{
				helper.setTime(newEvent.getStart());
				helper.set(Calendar.YEAR, theDay.get(Calendar.YEAR));
				helper.set(Calendar.MONTH, theDay.get(Calendar.MONTH));
				helper.set(Calendar.DAY_OF_MONTH, theDay
						.get(Calendar.DAY_OF_MONTH));
				newEvent.setStart(helper.getTime());
			}

			{
				if (newEvent.getEnd() != null) {
					helper.setTime(newEvent.getEnd());
					helper.set(Calendar.YEAR, theDay.get(Calendar.YEAR));
					helper.set(Calendar.MONTH, theDay.get(Calendar.MONTH));
					helper.set(Calendar.DAY_OF_MONTH, theDay
							.get(Calendar.DAY_OF_MONTH));
					newEvent.setEnd(helper.getTime());
				}
			}

			repaintPanel();
			final DateEvent newDateEvent = new DateEvent(
					DateEventActions.UPDATE, newEvent.getData());
			parent.handleDateEvent(newDateEvent);
		}
	}

	public void notifyParentOfUpdate(DateEvent newEvent) {
		GWT.log("Action " + newEvent.getCommand(), null);
		parent.handleDateEvent(newEvent);
	}

	public void onMouseDown(MouseDownEvent event) {
		if (event.getSource() == clickPanel) {
			final Calendar helper = new GregorianCalendar();
			helper.setTime(theDay.getTime());
			// Transplant the hour and minutes
			helper.set(Calendar.HOUR_OF_DAY, 0);
			helper.set(Calendar.MINUTE, 0);
			helper.set(Calendar.SECOND, 0);
			helper.set(Calendar.MILLISECOND, 0);
			final Date start = helper.getTime();
			parent.getRenderer().createNewAfterClick(start, parent);
		}

	}

}
