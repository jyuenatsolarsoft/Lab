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
import com.google.gwt.user.client.Window;

import eu.future.earth.gwt.client.date.AbstractWholeDayField;
import eu.future.earth.gwt.client.date.DateRenderer;

public class DefaultWholeDayField extends AbstractWholeDayField {

	public DefaultWholeDayField(DateRenderer renderer) {
		super(renderer);
	}

	GregorianCalendar helper = new GregorianCalendar();

	public void repaintPanel() {
		final Object theData = getData();
		if (theData != null) {
			if (theData instanceof EventData) {
				final EventData real = (EventData) theData;
				helper.setTime(real.getStartTime());
				if (helper.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
					super.setEventStyleName(Css.EVENT_HEADER_MONDAY);
				}

				setTitle(real.getData());
			} else {
				Window.alert("Programming error " + theData);
			}
		}

	}

}
