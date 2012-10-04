package com.cms.gwt.fs.client.gin;

import com.cms.gwt.fs.client.presenter.AccessHoursPresenter;
import com.cms.gwt.fs.client.presenter.AccessHoursPresenterImpl;
import com.cms.gwt.fs.client.presenter.BillingPresenter;
import com.cms.gwt.fs.client.presenter.BillingPresenterImpl;
import com.cms.gwt.fs.client.presenter.BreadCrumbPresenter;
import com.cms.gwt.fs.client.presenter.BreadCrumbPresenterImpl;
import com.cms.gwt.fs.client.presenter.DetailsPresenter;
import com.cms.gwt.fs.client.presenter.DetailsPresenterImpl;
import com.cms.gwt.fs.client.presenter.LocationPresenter;
import com.cms.gwt.fs.client.presenter.LocationPresenterImpl;
import com.cms.gwt.fs.client.presenter.MainPresenter;
import com.cms.gwt.fs.client.presenter.MainPresenterImpl;
import com.cms.gwt.fs.client.presenter.MaterialAddPresenter;
import com.cms.gwt.fs.client.presenter.MaterialAddPresenterImpl;
import com.cms.gwt.fs.client.presenter.MaterialEditPresenter;
import com.cms.gwt.fs.client.presenter.MaterialEditPresenterImpl;
import com.cms.gwt.fs.client.presenter.MaterialPresenter;
import com.cms.gwt.fs.client.presenter.MaterialPresenterImpl;
import com.cms.gwt.fs.client.presenter.NotesPresenter;
import com.cms.gwt.fs.client.presenter.NotesPresenterImpl;
import com.cms.gwt.fs.client.presenter.NotesWidgetPresenter;
import com.cms.gwt.fs.client.presenter.NotesWidgetPresenterImpl;
import com.cms.gwt.fs.client.presenter.SchedulePresenter;
import com.cms.gwt.fs.client.presenter.SchedulePresenterImpl;
import com.cms.gwt.fs.client.presenter.ServiceItemPresenter;
import com.cms.gwt.fs.client.presenter.ServiceItemPresenterImpl;
import com.cms.gwt.fs.client.presenter.ServiceTicketCalendarPresenter;
import com.cms.gwt.fs.client.presenter.ServiceTicketCalendarPresenterImpl;
import com.cms.gwt.fs.client.presenter.ServiceTicketListPresenter;
import com.cms.gwt.fs.client.presenter.ServiceTicketListPresenterImpl;
import com.cms.gwt.fs.client.presenter.ServiceTicketPresenter;
import com.cms.gwt.fs.client.presenter.ServiceTicketPresenterImpl;
import com.cms.gwt.fs.client.presenter.SkillsPresenter;
import com.cms.gwt.fs.client.presenter.SkillsPresenterImpl;
import com.cms.gwt.fs.client.presenter.TimeEntryAddPresenter;
import com.cms.gwt.fs.client.presenter.TimeEntryAddPresenterImpl;
import com.cms.gwt.fs.client.presenter.TimeEntryEditPresenter;
import com.cms.gwt.fs.client.presenter.TimeEntryEditPresenterImpl;
import com.cms.gwt.fs.client.presenter.TimeEntryPresenter;
import com.cms.gwt.fs.client.presenter.TimeEntryPresenterImpl;
import com.cms.gwt.fs.client.presenter.WorkHistoryArrivalInfoPresenter;
import com.cms.gwt.fs.client.presenter.WorkHistoryArrivalInfoPresenterImpl;
import com.cms.gwt.fs.client.presenter.WorkHistoryCounterPresenter;
import com.cms.gwt.fs.client.presenter.WorkHistoryCounterPresenterImpl;
import com.cms.gwt.fs.client.presenter.WorkHistoryPresenter;
import com.cms.gwt.fs.client.presenter.WorkHistoryPresenterImpl;
import com.cms.gwt.fs.client.rpc.ActionServices;
import com.cms.gwt.fs.client.rpc.ActionServicesImpl;
import com.cms.gwt.fs.client.rpc.message.factory.MessageHandlerFactory;
import com.cms.gwt.fs.client.rpc.xml.message.factory.XMLMessageHandlerFactoryImpl;
import com.cms.gwt.fs.client.view.BreadCrumbView;
import com.cms.gwt.fs.client.view.MainView;
import com.cms.gwt.fs.client.view.ServiceTicketListView;
import com.cms.gwt.fs.client.view.accesshours.AccessHoursView;
import com.cms.gwt.fs.client.view.billing.BillingGridView;
import com.cms.gwt.fs.client.view.billing.BillingPanelView;
import com.cms.gwt.fs.client.view.calendar.CalendarView;
import com.cms.gwt.fs.client.view.details.DetailsGridView;
import com.cms.gwt.fs.client.view.details.DetailsPanelView;
import com.cms.gwt.fs.client.view.item.ServiceItemView;
import com.cms.gwt.fs.client.view.location.LocationView;
import com.cms.gwt.fs.client.view.material.MaterialAddView;
import com.cms.gwt.fs.client.view.material.MaterialEditView;
import com.cms.gwt.fs.client.view.material.MaterialView;
import com.cms.gwt.fs.client.view.notes.NotesView;
import com.cms.gwt.fs.client.view.notes.NotesWidgetView;
import com.cms.gwt.fs.client.view.schedule.ScheduleGridView;
import com.cms.gwt.fs.client.view.schedule.SchedulePanelView;
import com.cms.gwt.fs.client.view.skills.SkillsGridView;
import com.cms.gwt.fs.client.view.skills.SkillsPanelView;
import com.cms.gwt.fs.client.view.ticket.ServiceTicketView;
import com.cms.gwt.fs.client.view.timeentry.TimeEntryAddView;
import com.cms.gwt.fs.client.view.timeentry.TimeEntryEditView;
import com.cms.gwt.fs.client.view.timeentry.TimeEntryView;
import com.cms.gwt.fs.client.view.workhistory.ArrivalInfoView;
import com.cms.gwt.fs.client.view.workhistory.CounterView;
import com.cms.gwt.fs.client.view.workhistory.WorkHistoryView;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class AppModule extends AbstractGinModule {

	@Override
	protected void configure() {

		bind(HandlerManager.class).toProvider(HandlerManagerProvider.class).in(
				Singleton.class);

		bind(MainPresenter.class).to(MainPresenterImpl.class);
		bind(MainPresenter.View.class).to(MainView.class);

		bind(BreadCrumbPresenter.class).to(BreadCrumbPresenterImpl.class);
		bind(BreadCrumbPresenter.View.class).to(BreadCrumbView.class);

		bind(ServiceTicketListPresenter.class).to(
				ServiceTicketListPresenterImpl.class);
		bind(ServiceTicketListPresenter.View.class).to(
				ServiceTicketListView.class);

		bind(ServiceTicketCalendarPresenter.class).to(
				ServiceTicketCalendarPresenterImpl.class);
		bind(ServiceTicketCalendarPresenter.View.class).to(CalendarView.class);

		bind(ServiceTicketPresenter.class).to(ServiceTicketPresenterImpl.class);
		bind(ServiceTicketPresenter.View.class).to(ServiceTicketView.class);

		bind(ServiceItemPresenter.class).to(ServiceItemPresenterImpl.class);
		bind(ServiceItemPresenter.View.class).to(ServiceItemView.class);

		bind(LocationPresenter.class).to(LocationPresenterImpl.class);
		bind(LocationPresenter.View.class).to(LocationView.class);

		bind(AccessHoursPresenter.class).to(AccessHoursPresenterImpl.class);
		bind(AccessHoursPresenter.View.class).to(AccessHoursView.class);

		bind(SkillsPresenter.class).to(SkillsPresenterImpl.class);
		bind(SkillsPresenter.GridView.class).to(SkillsGridView.class);
		bind(SkillsPresenter.PanelView.class).to(SkillsPanelView.class);

		bind(DetailsPresenter.class).to(DetailsPresenterImpl.class);
		bind(DetailsPresenter.GridView.class).to(DetailsGridView.class);
		bind(DetailsPresenter.PanelView.class).to(DetailsPanelView.class);

		bind(SchedulePresenter.class).to(SchedulePresenterImpl.class);
		bind(SchedulePresenter.GridView.class).to(ScheduleGridView.class);
		bind(SchedulePresenter.PanelView.class).to(SchedulePanelView.class);

		bind(WorkHistoryPresenter.class).to(WorkHistoryPresenterImpl.class);
		bind(WorkHistoryPresenter.View.class).to(WorkHistoryView.class);

		bind(WorkHistoryCounterPresenter.class).to(
				WorkHistoryCounterPresenterImpl.class);
		bind(WorkHistoryCounterPresenter.View.class).to(CounterView.class);

		bind(WorkHistoryArrivalInfoPresenter.class).to(
				WorkHistoryArrivalInfoPresenterImpl.class);
		bind(WorkHistoryArrivalInfoPresenter.View.class).to(
				ArrivalInfoView.class);

		bind(TimeEntryPresenter.class).to(TimeEntryPresenterImpl.class);
		bind(TimeEntryPresenter.View.class).to(TimeEntryView.class);

		bind(TimeEntryAddPresenter.class).to(TimeEntryAddPresenterImpl.class);
		bind(TimeEntryAddPresenter.View.class).to(TimeEntryAddView.class);

		bind(TimeEntryEditPresenter.class).to(TimeEntryEditPresenterImpl.class);
		bind(TimeEntryEditPresenter.View.class).to(TimeEntryEditView.class);

		bind(MaterialPresenter.class).to(MaterialPresenterImpl.class);
		bind(MaterialPresenter.View.class).to(MaterialView.class);

		bind(MaterialAddPresenter.class).to(MaterialAddPresenterImpl.class);
		bind(MaterialAddPresenter.View.class).to(MaterialAddView.class);

		bind(MaterialEditPresenter.class).to(MaterialEditPresenterImpl.class);
		bind(MaterialEditPresenter.View.class).to(MaterialEditView.class);

		bind(BillingPresenter.class).to(BillingPresenterImpl.class);
		bind(BillingPresenter.GridView.class).to(BillingGridView.class);
		bind(BillingPresenter.PanelView.class).to(BillingPanelView.class);

		bind(NotesPresenter.class).to(NotesPresenterImpl.class);
		bind(NotesPresenter.View.class).to(NotesView.class);

		bind(NotesWidgetPresenter.class).to(NotesWidgetPresenterImpl.class);
		bind(NotesWidgetPresenter.View.class).to(NotesWidgetView.class);

		bind(ActionServices.class).to(ActionServicesImpl.class);
		bind(MessageHandlerFactory.class)
				.to(XMLMessageHandlerFactoryImpl.class);

	}

}
