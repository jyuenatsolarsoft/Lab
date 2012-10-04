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

package com.cms.gwt.fs.client.model.calendar;

import java.util.Date;

public class EventData {

	private String id;
	private Date startTime;
	private Date endTime;
	private String data;
	private boolean isAllDayEvent;

	public EventData() {
		id = ""; // String.valueOf(Random.nextInt(999999));
		startTime = null;
		endTime = null;
		data = "";
		isAllDayEvent = false;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public boolean isAllDayEvent() {
		return isAllDayEvent;
	}

	public void setAllDayEvent(boolean isAllDayEvent) {
		if (isAllDayEvent) {
			this.endTime = null;
		}
		this.isAllDayEvent = isAllDayEvent;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(id).append("\n");
		sb.append(startTime).append(" - ").append(endTime).append("\n");
		sb.append(data);

		return sb.toString();
	}

}
