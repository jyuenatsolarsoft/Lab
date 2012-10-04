package com.cms.gwt.fs.client.presenter;

import java.util.Date;

import com.cms.gwt.fs.client.view.BaseView;
import com.cms.gwt.fs.client.view.calendar.ExtMultiView;

public interface ServiceTicketCalendarPresenter extends BasePresenter {

	interface View extends BaseView {
		void initTicketCalendar();

		ExtMultiView getTicketCalendar();
	}

	interface OnClickHandler {
		void onClick(Date centerDate, int viewType);
	}
	
	void showServiceTicketCalendar();
}
