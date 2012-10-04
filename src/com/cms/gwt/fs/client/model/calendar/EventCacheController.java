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
 * AffanJ		2009-10-08		Modified extensively for ExtMultiView
 * 								This model will check for dates and make RPC if necessary to get the data.
 */

package com.cms.gwt.fs.client.model.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.allen_sauer.gwt.log.client.Log;
import com.cms.gwt.common.client.ExtCmsMessageBox;
import com.cms.gwt.fs.client.HistoryConstants;
import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.exception.ActionNotSupportedException;
import com.cms.gwt.fs.client.model.scheduledEvent.TechnicianSchedule;
import com.cms.gwt.fs.client.model.scheduledEvent.TechnicianScheduledEvent;
import com.cms.gwt.fs.client.rpc.ActionServices;
import com.cms.gwt.fs.client.rpc.action.GetTechnicianSchedule;
import com.cms.gwt.fs.client.rpc.callback.GotTechnicianSchedule;
import com.cms.gwt.fs.client.util.DateUtil;
import com.cms.gwt.fs.client.util.Logger;
import com.cms.gwt.fs.client.view.StatusIndicator;
import com.cms.gwt.fs.client.view.calendar.ExtMultiView;
import com.google.gwt.core.client.GWT;

import eu.future.earth.gwt.client.date.EventController;
import eu.future.earth.gwt.client.date.MultiView;

public class EventCacheController implements EventController {

	private Map<String, EventData> items;
	private Date startDate;
	private Date endDate;
	
	private TextConstants txtConsts = 
		(TextConstants) GWT.create(TextConstants.class);	

	public EventCacheController() {
		super();

		items = new HashMap<String, EventData>();
		startDate = null;
		endDate = null;
	}

	public void getEventsForRange(Date start, Date end, MultiView caller,
			boolean reloadData) {
		// DONT NEED THIS
	}

	public void getEventsForRange(Date start, Date end, ExtMultiView caller,
			boolean reloadData) {

		Logger.info("Current Range: "
				+ DateUtil.formatDate(startDate, "yyyy-MM-dd") + " - "
				+ DateUtil.formatDate(endDate, "yyyy-MM-dd"));
		Logger.info("Require Range: "
				+ DateUtil.formatDate(start, "yyyy-MM-dd") + " - "
				+ DateUtil.formatDate(end, "yyyy-MM-dd"));

		boolean makeRPC = (startDate == null || endDate == null
				|| start.compareTo(startDate) < 0 || end.compareTo(endDate) > 0);

		if (makeRPC) {
			Logger.info("Need to expand Range >> Make RPC.");

			Date rpcStartDate = new Date();
			Date rpcEndDate = new Date();

			// get outer ranges for making RPC
			if (startDate == null || start.compareTo(startDate) < 0) {
				rpcStartDate = start;

			} else if (start.compareTo(startDate) >= 0
					|| start.compareTo(endDate) >= 0) {
				rpcStartDate = endDate;
			}

			if (endDate == null || end.compareTo(endDate) > 0) {
				rpcEndDate = end;

			} else if (end.compareTo(endDate) <= 0
					|| end.compareTo(startDate) <= 0) {
				rpcEndDate = startDate;
			}

			int horizon = (int) Math.ceil((rpcEndDate.getTime() - rpcStartDate
					.getTime())
					/ (1000 * 60 * 60 * 24));

			String userName = ""; // NOT needed anymore
			getTechnicianSchedule(GetTechnicianSchedule.newInstance(userName,
					new java.sql.Date(rpcStartDate.getTime()), String
							.valueOf(horizon)), start, end, caller, reloadData);

			return;
		}

		Logger.info("Range Matched Successfully.");

		// otherwise get events and display them
		ArrayList<EventData> found = getEventsInRange(start, end);
		caller.setEvents((Object[]) found.toArray(new Object[0]));
	}

	public void updateEvent(Object updated) {
		// DONT NEED THIS
		// removeEvent(updated);
		// addEvent(updated);
	}

	public void removeEvent(Object updated) {
		// DONT NEED THIS
		// DefaultEventData data = (DefaultEventData) updated;
		// items.remove(data.getId());
	}

	public void addEvent(Object updated) {
		EventData data = (EventData) updated;

		Date startDate = data.getStartTime();
		Date endDate = data.getEndTime();
		if (data.getStartTime().compareTo(endDate) >= 0) {
			return;
		}

		// decompose event into different items if event is spanned on days.
		Calendar startCal = new GregorianCalendar();
		Calendar endCal = new GregorianCalendar();
		Calendar dayStart = new GregorianCalendar();
		Calendar dayEnd = new GregorianCalendar();

		startCal.setTime(startDate);
		endCal.setTime(endDate);

		boolean isBlocks = false;
		int counter = 0;

		while (startCal.get(Calendar.DATE) != endCal.get(Calendar.DATE)
				|| startCal.get(Calendar.MONTH) != endCal.get(Calendar.MONTH)) {

			if (!isBlocks) {
				isBlocks = true;

				dayEnd.set(Calendar.YEAR, startCal.get(Calendar.YEAR));
				dayEnd.set(Calendar.MONTH, startCal.get(Calendar.MONTH));
				dayEnd.set(Calendar.DATE, startCal.get(Calendar.DATE));
				dayEnd.set(Calendar.HOUR_OF_DAY, 23);
				dayEnd.set(Calendar.MINUTE, 59);
				dayEnd.set(Calendar.SECOND, 59);
				dayEnd.set(Calendar.MILLISECOND, 999);

				EventData tmpData1 = new EventData();
				tmpData1.setId(data.getId() + HistoryConstants.CONCAT_VALUES
						+ String.valueOf(counter));
				tmpData1.setData(data.getData());
				tmpData1.setStartTime(startDate);
				tmpData1.setEndTime(dayEnd.getTime());
				items.put(tmpData1.getId(), tmpData1);

			} else {
				dayStart.set(Calendar.YEAR, startCal.get(Calendar.YEAR));
				dayStart.set(Calendar.MONTH, startCal.get(Calendar.MONTH));
				dayStart.set(Calendar.DATE, startCal.get(Calendar.DATE));
				dayStart.set(Calendar.HOUR_OF_DAY, 0);
				dayStart.set(Calendar.MINUTE, 0);
				dayStart.set(Calendar.SECOND, 0);
				dayStart.set(Calendar.MILLISECOND, 1);

				dayEnd.set(Calendar.YEAR, startCal.get(Calendar.YEAR));
				dayEnd.set(Calendar.MONTH, startCal.get(Calendar.MONTH));
				dayEnd.set(Calendar.DATE, startCal.get(Calendar.DATE));
				dayEnd.set(Calendar.HOUR_OF_DAY, 23);
				dayEnd.set(Calendar.MINUTE, 59);
				dayEnd.set(Calendar.SECOND, 59);
				dayEnd.set(Calendar.MILLISECOND, 999);

				EventData tmpData2 = new EventData();
				tmpData2.setId(data.getId() + HistoryConstants.CONCAT_VALUES
						+ String.valueOf(counter));
				tmpData2.setData(data.getData());
				tmpData2.setStartTime(dayStart.getTime());
				tmpData2.setEndTime(dayEnd.getTime());
				items.put(tmpData2.getId(), tmpData2);
			}

			startCal.add(Calendar.DATE, 1);
			counter++;

		} // loop

		if (isBlocks) {
			dayStart.set(Calendar.YEAR, startCal.get(Calendar.YEAR));
			dayStart.set(Calendar.MONTH, startCal.get(Calendar.MONTH));
			dayStart.set(Calendar.DATE, startCal.get(Calendar.DATE));
			dayStart.set(Calendar.HOUR_OF_DAY, 0);
			dayStart.set(Calendar.MINUTE, 0);
			dayStart.set(Calendar.SECOND, 0);
			dayStart.set(Calendar.MILLISECOND, 1);

			EventData tmpData3 = new EventData();
			tmpData3.setId(data.getId() + HistoryConstants.CONCAT_VALUES
					+ String.valueOf(counter));
			tmpData3.setData(data.getData());
			tmpData3.setStartTime(dayStart.getTime());
			tmpData3.setEndTime(endDate);
			items.put(tmpData3.getId(), tmpData3);

		} else {
			items.put(data.getId(), data);
		}

	}

	/**
	 * Clear all items in cache.
	 */
	public void clearEvents() {
		items.clear();
		startDate = null;
		endDate = null;
	}

	/**
	 * Get the events that are after start and before end, inclusive
	 * 
	 * @param start
	 *            the start date
	 * @param end
	 *            the end date
	 */
	public ArrayList<EventData> getEventsInRange(Date start, Date end) {
		ArrayList<EventData> found = new ArrayList<EventData>();
		for (String key : items.keySet()) {
			EventData data = items.get(key);
			if (data.getStartTime().compareTo(start) >= 0
					&& data.getEndTime().compareTo(end) <= 0) {
				found.add(data);
			}
		}
		return found;
	}

	// *** RPC METHODS *** //
	/**
	 * Retrieve the scheduled event based on the action details provided.
	 * 
	 * @param action
	 *            An instance of GetTechnicianSchedule which contains the
	 *            criteria of the schedule.
	 */
	private void getTechnicianSchedule(GetTechnicianSchedule action,
			Date start, Date end, ExtMultiView caller, boolean reloadData) {

		try {
			ActionServices.App.getInstance().execute(
					action,
					new GetTechnicianScheduleCallback(start, end, caller,
							reloadData));
		} catch (ActionNotSupportedException e) {			
			final String ERR_MSG = "Unable to get technician schedule.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());			
		}
	}

	class GetTechnicianScheduleCallback extends GotTechnicianSchedule {

		private final Date start;
		private final Date end;
		private final ExtMultiView caller;
		private final boolean reloadData;

		public GetTechnicianScheduleCallback(Date start, Date end,
				ExtMultiView caller, boolean reloadData) {
			this.start = start;
			this.end = end;
			this.caller = caller;
			this.reloadData = reloadData;
		}

		public void got(TechnicianSchedule schedule) {

			List<EventData> eventDataList = new ArrayList<EventData>();
			for (TechnicianScheduledEvent event : schedule.getEvents()) {
				EventData currEventData = new EventData();

				StringBuffer text = new StringBuffer();
				text.append(event.getCustomerName() + "\n");
				text.append(event.getDescription());
				currEventData.setData(text.toString());

				// EventData.id is composed of: TicketNumber|EventId
				currEventData.setId(event.getTicketNumber()
						+ HistoryConstants.CONCAT_VALUES + event.getEventID());
				currEventData.setStartTime(event.getStartTime());
				currEventData.setEndTime(event.getEndTime());
				eventDataList.add(currEventData);
			}
			// add data to map
			for (EventData defaultEventData : eventDataList) {
				addEvent(defaultEventData);
			}

			if (startDate == null || start.compareTo(startDate) < 0) {
				startDate = start;
			}
			if (endDate == null || end.compareTo(endDate) > 0) {
				endDate = end;
			}

			getEventsForRange(start, end, caller, reloadData);
			StatusIndicator.clear();
		}
	}
}
