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
import java.util.GregorianCalendar;

import com.cms.gwt.fs.client.model.calendar.EventData;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import eu.future.earth.gwt.client.date.DateRenderer;
import eu.future.earth.gwt.client.date.week.staend.AbstractDayField;

public class DefaultDayField extends AbstractDayField {

	private Label description = new Label();

	private DateTimeFormat format = DateTimeFormat.getFormat("HH:mm");

	public DefaultDayField(DateRenderer renderer) {
		super(renderer);
		description.addClickHandler(this);
		super.setBody(description);
	}

	public void setTitle() {
		final Object theData = super.getData();
		final EventData real = (EventData) theData;
		if (real.getEndTime() == null) {
			super.setTitle(format.format(real.getStartTime()));
		} else {
			super.setTitle(format.format(real.getStartTime()) + "-"
					+ format.format(real.getEndTime()));
		}

	}

	public Widget getClickableItem() {
		return description;
	}

	GregorianCalendar helper = new GregorianCalendar();

	@Override
	public void repaintPanel() {
		final Object theData = super.getData();
		if (theData != null) {
			final EventData real = (EventData) theData;
			helper.setTime(real.getStartTime());
			if (helper.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
				super.setEventStyleName(Css.EVENT_PANEL_MONDAY,
						Css.EVENT_HEADER_MONDAY);
			}
			description.setText((String) real.getData());
			description.setTitle((String) real.getData());
			if (real.getEndTime() == null) {
				super.setTitle(format.format(real.getStartTime()));
			} else {
				super.setTitle(format.format(real.getStartTime()) + "-"
						+ format.format(real.getEndTime()));
			}
		}

	}

	public void setWidth(int width) {
		if (width > 0) {
			setWidth(width + "px");
		}
	}

}
