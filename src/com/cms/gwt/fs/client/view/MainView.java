package com.cms.gwt.fs.client.view;

import com.cms.gwt.fs.client.presenter.MainPresenter;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Main view.
 * <p />
 * This view holds all the panels, tabs, and widgets.
 * 
 */
public class MainView extends Composite implements MainPresenter.View {

	private final VerticalPanel panel;
	private Widget serviceTicketList;
	private Widget serviceTicketCalendar;
	private Widget serviceTicket;
	private Widget skillsPanel;
	private Widget detailsPanel;
	private Widget schedulePanel;
	private Widget workHistoryPanel;
	private Widget workHistoryCounter;
	private Widget workHistoryArrivalInfo;
	private Widget timeEntryPanel;
	private Widget timeEntryAddPanel;
	private Widget timeEntryEditPanel;
	private Widget materialPanel;
	private Widget materialAddPanel;
	private Widget materialEditPanel;
	private Widget billingPanel;

	/**
	 * Constructor.
	 */
	public MainView() {
		panel = new VerticalPanel();
		panel.setStyleName("MainPanel");
		initWidget(panel);
	}

	/**
	 * {@inheritDoc}
	 */
	public Widget getWidget() {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setReadOnly(boolean readOnly) {
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void addBreadCrumb(BaseView view) {
		if (panel.getWidgetCount() > 0) {
			panel.insert(view.getWidget(), 1);
		} else {
			panel.add(view.getWidget());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void addServiceTicketList(BaseView view) {
		serviceTicketList = view.getWidget();
		panel.add(serviceTicketList);
	}

	/**
	 * {@inheritDoc}
	 */
	public void addServiceTicketCalendar(BaseView view) {
		serviceTicketCalendar = view.getWidget();
		panel.add(serviceTicketCalendar);
	}

	/**
	 * {@inheritDoc}
	 */
	public void addServiceTicket(BaseView view) {
		serviceTicket = view.getWidget();
		panel.add(serviceTicket);
	}

	/**
	 * {@inheritDoc}
	 */
	public void addSkillsPanel(BaseView view) {
		skillsPanel = view.getWidget();
		panel.add(skillsPanel);
	}

	/**
	 * {@inheritDoc}
	 */
	public void addDetailsPanel(BaseView view) {
		detailsPanel = view.getWidget();
		panel.add(detailsPanel);
	}

	/**
	 * {@inheritDoc}
	 */
	public void addSchedulePanel(BaseView view) {
		schedulePanel = view.getWidget();
		panel.add(schedulePanel);
	}

	/**
	 * {@inheritDoc}
	 */
	public void addWorkHistoryPanel(BaseView view) {
		workHistoryPanel = view.getWidget();
		panel.add(workHistoryPanel);
	}

	/**
	 * {@inheritDoc}
	 */
	public void addWorkHistoryCounter(BaseView view) {
		workHistoryCounter = view.getWidget();
		panel.add(workHistoryCounter);
	}

	/**
	 * {@inheritDoc}
	 */
	public void addWorkHistoryArrivalInfo(BaseView view) {
		workHistoryArrivalInfo = view.getWidget();
		panel.add(workHistoryArrivalInfo);
	}

	/**
	 * {@inheritDoc}
	 */
	public void addTimeEntryPanel(BaseView view) {
		timeEntryPanel = view.getWidget();
		panel.add(timeEntryPanel);
	}

	/**
	 * {@inheritDoc}
	 */
	public void addTimeEntryAddPanel(BaseView view) {
		timeEntryAddPanel = view.getWidget();
		panel.add(timeEntryAddPanel);
	}

	/**
	 * {@inheritDoc}
	 */
	public void addBillingPanel(BaseView view) {
		billingPanel = view.getWidget();
		panel.add(billingPanel);
	}

	/**
	 * {@inheritDoc}
	 */
	public void addTimeEntryEditPanel(BaseView view) {
		timeEntryEditPanel = view.getWidget();
		panel.add(timeEntryEditPanel);
	}

	/**
	 * {@inheritDoc}
	 */
	public void addMaterialPanel(BaseView view) {
		materialPanel = view.getWidget();
		panel.add(materialPanel);
	}

	/**
	 * {@inheritDoc}
	 */
	public void addMaterialAddPanel(BaseView view) {
		materialAddPanel = view.getWidget();
		panel.add(materialAddPanel);
	}

	/**
	 * {@inheritDoc}
	 */
	public void addMaterialEditPanel(BaseView view) {
		materialEditPanel = view.getWidget();
		panel.add(materialEditPanel);
	}
}
