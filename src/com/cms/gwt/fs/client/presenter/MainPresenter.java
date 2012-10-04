package com.cms.gwt.fs.client.presenter;

import com.cms.gwt.fs.client.view.BaseView;

/**
 * Main presenter interface. This is the main class that holds all panels and
 * displays them based on user actions.
 * 
 */
public interface MainPresenter extends BasePresenter {

	interface View extends BaseView {

		void addBreadCrumb(BaseView view);

		void addServiceTicketList(BaseView view);

		void addServiceTicketCalendar(BaseView view);

		void addServiceTicket(BaseView view);

		void addSkillsPanel(BaseView view);

		void addDetailsPanel(BaseView view);

		void addSchedulePanel(BaseView view);

		void addWorkHistoryPanel(BaseView view);

		void addWorkHistoryCounter(BaseView view);

		void addWorkHistoryArrivalInfo(BaseView view);

		void addTimeEntryPanel(BaseView view);

		void addTimeEntryAddPanel(BaseView view);

		void addTimeEntryEditPanel(BaseView view);

		void addMaterialPanel(BaseView view);

		void addMaterialAddPanel(BaseView view);

		void addMaterialEditPanel(BaseView view);

		void addBillingPanel(BaseView view);

	}

	View go();

}
