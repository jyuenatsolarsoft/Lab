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
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import eu.future.earth.gwt.client.date.DateRenderer;
import eu.future.earth.gwt.client.date.month.staend.AbstractMonthField;

public class DefaultMonthField extends AbstractMonthField implements
		ClickHandler {

	private Label description = new Label();

	private DateTimeFormat format = DateTimeFormat.getFormat("HH:mm");

	public DefaultMonthField(DateRenderer renderer) {
		super(renderer);
		description.addClickHandler(this);
		super.setBody(description);
	}

	public Widget getClickableItem() {
		return description;
	}

	GregorianCalendar helper = new GregorianCalendar();

	public void repaintPanel() {
		final Object theData = getData();

		if (theData != null) {
			if (theData instanceof EventData) {
				final EventData real = (EventData) theData;
				helper.setTime(real.getStartTime());
				if (helper.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
					super.setEventStyleName(Css.WHOLEDAY_PANEL_MONDAY);
				}
				description.setText(real.getData().trim());
				description.setTitle(real.getData());
				super.setTitle(format.format(real.getStartTime()));
			} else {
				Window.alert("Programming error " + theData);
			}
		}

	}

	public void setWidth(int width) {
		if (width > 0) {
			setWidth(width + "px");
		}
	}

}
