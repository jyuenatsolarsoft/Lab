/**
 * Work History:
 * 
 * AffanJ		2009-10-06		Schedule Type will be always read-only and "Normal".
 */

package com.cms.gwt.fs.client.presenter;

import com.allen_sauer.gwt.log.client.Log;
import com.cms.gwt.common.client.ExtCmsMessageBox;
import com.cms.gwt.common.client.CmsMessageBox.CmsConfirmCallback;
import com.cms.gwt.fs.client.HistoryConstants;
import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.exception.ActionNotSupportedException;
import com.cms.gwt.fs.client.factory.CodeDictionaryFactory;
import com.cms.gwt.fs.client.model.ListenerInfo;
import com.cms.gwt.fs.client.model.scheduledEvent.ScheduledEvent;
import com.cms.gwt.fs.client.model.scheduledEvent.ScheduledEventList;
import com.cms.gwt.fs.client.model.scheduledEvent.ScheduledEventListEntry;
import com.cms.gwt.fs.client.rpc.ActionServices;
import com.cms.gwt.fs.client.rpc.action.AddScheduledEvent;
import com.cms.gwt.fs.client.rpc.action.DeleteScheduledEvent;
import com.cms.gwt.fs.client.rpc.action.GetScheduledEvent;
import com.cms.gwt.fs.client.rpc.action.GetScheduledEventList;
import com.cms.gwt.fs.client.rpc.action.UpdateScheduledEvent;
import com.cms.gwt.fs.client.rpc.actionResponse.DeleteScheduledEventResponse;
import com.cms.gwt.fs.client.rpc.callback.DeletedScheduledEvent;
import com.cms.gwt.fs.client.rpc.callback.GotScheduledEvent;
import com.cms.gwt.fs.client.rpc.callback.GotScheduledEventList;
import com.cms.gwt.fs.client.rpc.callback.UpdatedScheduledEvent;
import com.cms.gwt.fs.client.util.StringUtil;
import com.cms.gwt.fs.client.view.BaseView;
import com.cms.gwt.fs.client.view.schedule.ScheduleGridView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.inject.Inject;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.Record;
import com.gwtext.client.util.Format;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.event.GridRowListener;

public class SchedulePresenterImpl extends BasePresenterImpl implements
		SchedulePresenter {

	private final GridView gridView;
	private final PanelView panelView;
	private final TextConstants txtConsts;

	// grid handlers
	private HandlerRegistration gBtnAddHandler;
	private HandlerRegistration gBtnUpdateHandler;
	private HandlerRegistration gBtnDeleteHandler;
	private HandlerRegistration gBtnViewHandler;
	private HandlerRegistration gBtnAcceptHandler;
	private HandlerRegistration gBtnDeclineHandler;
	private HandlerRegistration gBtnWorkHistoryHandler;

	// panel handlers
	private HandlerRegistration pBtnAddHandler;
	private HandlerRegistration pBtnUpdateHandler;
	private HandlerRegistration pBtnCancelHandler;
	private HandlerRegistration pBtnBackHandler;
	private HandlerRegistration pChkOverrideRatesHandler;

	private boolean areListenersRegistered;
	private boolean areGridListenersRegistered;
	private boolean isGridListenerRegistered;

	private ListenerInfo listenerInfo;

	/**
	 * Current scheduled event on the schedule panel.
	 */
	private ScheduledEvent currScheduledEvent;

	/**
	 * Constructor.
	 * 
	 * @param eventBus
	 *            The Events Manager
	 * @param gridView
	 *            Grid View of schedules
	 * @param panelView
	 *            Panel View of schedule
	 */
	@Inject
	public SchedulePresenterImpl(GridView gridView, PanelView panelView) {

		this.gridView = gridView;
		this.panelView = panelView;

		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		areListenersRegistered = false;
		areGridListenersRegistered = false;
		isGridListenerRegistered= false;

		listenerInfo = new ListenerInfo();

	}

	/**
	 * {@inheritDoc}
	 */
	public BaseView getView() {
		return gridView;
	}

	/**
	 * {@inheritDoc}
	 */
	public BaseView getPanelView() {
		return panelView;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset() {
		unRegisterListenersGrid();
		gridView.clearScheduleGrid();

		updateButtons(-1, -1);
	}

	/**
	 * {@inheritDoc}
	 */
	public void resetPanel() {
		unRegisterListenersPanel();
		panelView.reset();
	}

	/**
	 * {@inheritDoc}
	 */
	public void setVisible(boolean visible) {
		gridView.getWidget().setVisible(visible);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setPanelVisible(boolean visible) {
		panelView.getWidget().setVisible(visible);
	}

	/**
	 * {@inheritDoc}
	 */
	public void showScheduleGrid(String ticketNumber, String panelNumber) {
		getScheduleGrid(ticketNumber, panelNumber);
	}

	/**
	 * {@inheritDoc}
	 */
	public void showSchedulePanel(String ticketNumber, String panelNumber,
			String scheduleId, SchedulePanelType schedulePanelType) {

		if (SchedulePanelType.ADD.equals(schedulePanelType)) {
			renderPanel(ticketNumber, panelNumber, scheduleId,
					schedulePanelType, new ScheduledEvent());
		} else {
			// Get Data first.
			getSchedulePanelData(ticketNumber, panelNumber, scheduleId,
					schedulePanelType);
		}
	}

	/**
	 * Make RPC to get data for schedule grid.
	 */
	private void getScheduleGrid(String ticketNumber, String panelNumber) {

		getScheduleGrid(GetScheduledEventList.newInstance(ticketNumber),
				panelNumber);
	}

	private void getScheduleGrid(GetScheduledEventList action,
			String panelNumber) {

		try {
			ActionServices.App.getInstance().execute(action,
					new GetScheduleGridCallback(panelNumber));
		} catch (ActionNotSupportedException e) {
			
			final String ERR_MSG = "Unable to get scheduled events.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Make RPC(s) to get data for schedule panel.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param scheduleId
	 * @param schedulePanelType
	 *            panel type.
	 */
	private void getSchedulePanelData(String ticketNumber, String panelNumber,
			String scheduleId, SchedulePanelType schedulePanelType) {

		getScheduledEvent(ticketNumber, panelNumber, scheduleId,
				new GetSchedulePanelCallback(ticketNumber, panelNumber,
						scheduleId, schedulePanelType));
	}

	/**
	 * Make RPC to get data for scheduled event.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param scheduleId
	 */
	private void getScheduledEvent(String ticketNumber, String panelNumber,
			String scheduleId, GotScheduledEvent callback) {

		try {
			ActionServices.App.getInstance().execute(
					GetScheduledEvent.newInstance(ticketNumber, scheduleId),
					callback);
		} catch (ActionNotSupportedException e) {			
			final String ERR_MSG = "Unable to get scheduled event.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Update the status of the specified scheduled event.
	 * 
	 * It will retrieve the ScheduledEvent from the backend, update the status
	 * and then make another RPC to save the change.
	 * 
	 * @param ticketNumber
	 *            The ticket which the scheduled event belongs to.
	 * @param panelNumber
	 *            The current panel number.
	 * @param scheduleId
	 *            The event to be updated.
	 * @param statusCode
	 *            The new status code.
	 */
	private void updateEventStatus(String ticketNumber, String panelNumber,
			String scheduleId, int statusCode) {
		// TODO: For safety, check statusCode before updating.

		getScheduledEvent(ticketNumber, panelNumber, scheduleId,
				new UpdateEventStatusCallback(ticketNumber, panelNumber,
						statusCode));
	}

	/**
	 * This method saves the updated scheduled event on the backend, displays
	 * status change notification and refreshes the schedule grid.
	 * 
	 * @param ticketNumber
	 *            The ticket which the scheduled event belongs to.
	 * @param panelNumber
	 *            The current panel number.
	 * @param event
	 *            The scheduled event to be saved.
	 */
	private void saveEventStatus(String ticketNumber, String panelNumber,
			ScheduledEvent event) {
		updateScheduledEvent(event, new SaveEventStatusCallback(ticketNumber,
				panelNumber, Integer.toString(event.getEventId())));
	}

	/**
	 * Display schedule grid.
	 * 
	 * @param ticketNumber
	 * @param eventList
	 */
	private void renderGrid(String ticketNumber, String panelNumber,
			ScheduledEventList eventList) {

		Object[][] scheduleData = new Object[eventList.getEvents().size()][];

		int i = 0;
		for (ScheduledEventListEntry entry : eventList.getEvents()) {
			scheduleData[i++] = new Object[] {
					entry.getEventId(),
					entry.getTypeDescription(),
					entry.getManpower(),
					StringUtil.formatNumber(formatDouble(entry
							.getTimeEstimate()), 2), entry.getTechnician(),
					entry.getTechnicianDescription(),
					entry.getScheduledStartDate(),
					entry.getScheduledStartTime(), entry.getTimeZone(),
					entry.getStatusDescription(), entry.getStatus() };
		}

		String uniqueId = gridView.initScheduleGrid(scheduleData);
		registerListenersGrid(ticketNumber, panelNumber, uniqueId);
	}

	/**
	 * Display schedule panel.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param scheduleId
	 */
	private void renderPanel(String ticketNumber, String panelNumber,
			String scheduleId, SchedulePanelType schedulePanelType,
			ScheduledEvent event) {

		if (event != null) {
			panelView.setPanelType(schedulePanelType);

			panelView.getTxtEventId().setText(
					Integer.toString(event.getEventId()));

			// After populating select normal for schedule type.
			ListBox lstScheduleType = (ListBox) panelView.getLstScheduleType();
			populateListBox(lstScheduleType,
					CodeDictionaryFactory.SCHEDULE_EVENT_TYPE, event.getType());
			for (int i = 0; i < lstScheduleType.getItemCount(); i++) {
				if ("Normal".equals(lstScheduleType.getItemText(i))) {
					lstScheduleType.setSelectedIndex(i);
					break;
				}
			}

			panelView.getTxtParentEventId().setText(
					Integer.toString(event.getParentEventId()));
			populateListBox(((ListBox) panelView.getLstParentSplitType()),
					CodeDictionaryFactory.PARENT_SPLIT_TYPE, event
							.getParentSplitType());

			panelView.getTxtManPower().setText(
					StringUtil.formatNumber(formatDouble(event.getManpower()),
							0));
			panelView.getTxtTimeEstimate().setText(
					StringUtil.formatNumber(formatDouble(event
							.getTimeEstimate()), 2));

			populateListBox(((ListBox) panelView.getLstSpecialEventCode()),
					CodeDictionaryFactory.SPECIAL_EVENT_CODE, event
							.getSpecialEventCode(), true);

			if (schedulePanelType.equals(SchedulePanelType.VIEW)) {
				populateListBox(((ListBox) panelView.getLstTechnician()),
						CodeDictionaryFactory.TECHNICAN_CODE, event
								.getTechnician());								
			} 
			else 
			{
				// Use the current user for add.
				populateListBox(((ListBox) panelView.getLstTechnician()),
						CodeDictionaryFactory.TECHNICAN_CODE, event
								.getTechnician());
			}

			if (event.getScheduledStartDate() != null) {
				// Note that null value will crash the time picker.
				panelView.getDatScheduledTimeDate().setValue(
						event.getScheduledStartDate());
			}

			String startTimeStr = event.getScheduledStartTime();
			if (startTimeStr != null) {
				String[] time = startTimeStr.split("\\.", 3);
				panelView.getTxtScheduledTimeHour().setText(time[0]);
				panelView.getTxtScheduledTimeMinute().setText(time[1]);
			}

			panelView.getTxtTravelTimeEstimate().setText(
					StringUtil.formatNumber(formatDouble(event
							.getTravelTimeEstimate()), 2));
			populateListBox(((ListBox) panelView.getLstServiceCategory()),
					CodeDictionaryFactory.SERVICE_CATEGORY_CODE, event
							.getServiceCategory());

			// TODO: fix the down cast.
			((CheckBox) panelView.getChkOverrideRates()).setValue(event
					.isOverrideRates());

			panelView.getTxtLabourRate().setText(
					StringUtil.formatNumber(
							formatDouble(event.getLabourRate()), 2));
			panelView.getTxtOvertimeRate().setText(
					StringUtil.formatNumber(formatDouble(event
							.getOvertimeRate()), 2));

			((TextBox) panelView.getTxtLabourRate()).setEnabled(event
					.isOverrideRates());
			((TextBox) panelView.getTxtOvertimeRate()).setEnabled(event
					.isOverrideRates());

			if (schedulePanelType.equals(SchedulePanelType.ADD))
			{
				populateListBox(((ListBox) panelView.getLstStatus()),
						CodeDictionaryFactory.EVENT_STATUS_TYPE, Integer.toString(ScheduledEvent.STATUS_NEW));
			}
			else
			{
				populateListBox(((ListBox) panelView.getLstStatus()),
						CodeDictionaryFactory.EVENT_STATUS_TYPE, event.getStatus());				
			}

			registerListenersPanel(ticketNumber, panelNumber, schedulePanelType);

			// Set the current event.
			setCurrScheduledEvent(event);
		}
	}

	private void setCurrScheduledEvent(ScheduledEvent currScheduledEvent) {
		this.currScheduledEvent = currScheduledEvent;
	}

	/**
	 * Register Listeners for grid.
	 * 
	 * @param uniqueId
	 *            unique identifier.
	 */
	private void registerListenersGrid(final String ticketNumber,
			final String panelNumber, final String uniqueId) {

		listenerInfo.setTicketNumber(ticketNumber);
		listenerInfo.setPanelNumber(panelNumber);
		listenerInfo.setUniqueId(uniqueId);

		if (!isGridListenerRegistered) {

			gridView.getScheduleGrid().addGridRowListener(
					new GridRowListener() {
						public void onRowClick(GridPanel grid, int rowIndex,
								EventObject e) {
							try {
								Record selected = grid.getStore().getAt(
										rowIndex);

								final int statusCode = selected
										.getAsInteger(ScheduleGridView.STATUS_CODE_FIELD);

								updateButtons(rowIndex, statusCode);
							} catch (NumberFormatException ex) {
							} catch (Exception ex) {
							}
						}

						public void onRowContextMenu(GridPanel grid,
								int rowIndex, EventObject e) {
							// nothing
						}

						public void onRowDblClick(GridPanel grid, int rowIndex,
								EventObject e) {
							gridDblClick(grid, rowIndex);
						}
					});

			isGridListenerRegistered = true;
		}

		if (areGridListenersRegistered) {
			return;
		}

		gBtnAddHandler = gridView.getBtnAdd().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						History.newItem(Format.format(
								HistoryConstants.SCHEDULE_PANEL_VALUE,
								ticketNumber, panelNumber, "", String
										.valueOf(SchedulePanelType.ADD.value)));
					}
				});

		gBtnUpdateHandler = gridView.getBtnUpdate().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						Record selected = gridView.getScheduleGrid()
								.getSelectionModel().getSelected();
						if (selected == null) {
							return;
						}

						String scheduleId = selected.getAsString(uniqueId);

						History
								.newItem(Format
										.format(
												HistoryConstants.SCHEDULE_PANEL_VALUE,
												ticketNumber,
												panelNumber,
												scheduleId,
												String
														.valueOf(SchedulePanelType.UPDATE.value)));
					}
				});

		gBtnDeleteHandler = gridView.getBtnDelete().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {

						Record selected = gridView.getScheduleGrid()
								.getSelectionModel().getSelected();
						if (selected == null) {
							return;
						}

						final String scheduleId = selected
								.getAsString(uniqueId);

						ExtCmsMessageBox box = new ExtCmsMessageBox();
						box.confirm(txtConsts.Delete(), txtConsts.Confirm(
								txtConsts.Delete(), txtConsts.Schedule(),
								scheduleId), new DeleteConfirmation(
								ticketNumber, panelNumber, scheduleId));
					}
				});

		gBtnViewHandler = gridView.getBtnView().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {

						Record selected = gridView.getScheduleGrid()
								.getSelectionModel().getSelected();
						if (selected == null) {
							return;
						}

						final String scheduleId = selected
								.getAsString(uniqueId);
						History
								.newItem(Format
										.format(
												HistoryConstants.SCHEDULE_PANEL_VALUE,
												ticketNumber,
												panelNumber,
												scheduleId,
												String
														.valueOf(SchedulePanelType.VIEW.value)));
					}
				});

		gBtnAcceptHandler = gridView.getBtnAccept().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {

						Record selected = gridView.getScheduleGrid()
								.getSelectionModel().getSelected();
						if (selected == null) {
							return;
						}

						final String scheduleId = selected
								.getAsString(uniqueId);

						updateEventStatus(ticketNumber, panelNumber,
								scheduleId, ScheduledEvent.STATUS_ACCEPTED);

					}
				});

		gBtnDeclineHandler = gridView.getBtnDecline().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {

						Record selected = gridView.getScheduleGrid()
								.getSelectionModel().getSelected();
						if (selected == null) {
							return;
						}

						final String scheduleId = selected
								.getAsString(uniqueId);

						updateEventStatus(ticketNumber, panelNumber,
								scheduleId, ScheduledEvent.STATUS_DECLINED);

					}
				});

		gBtnWorkHistoryHandler = gridView.getBtnWorkHistory().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {

						Record selected = gridView.getScheduleGrid()
								.getSelectionModel().getSelected();
						if (selected == null) {
							return;
						}

						String eventId = selected.getAsString(uniqueId);
						String tabNumber = "0";

						History.newItem(Format.format(
								HistoryConstants.WORK_HISTORY_PANEL_VALUE,
								ticketNumber, panelNumber, eventId, tabNumber));
					}
				});

		areGridListenersRegistered = true;
	}

	/**
	 * Refresh the model with the data on the view.
	 * 
	 *@param ticketNumber
	 *            The ticket which this model belongs to.
	 */
	private void refreshScheduledEvent(String ticketNumber) {

		currScheduledEvent.setTicketNumber(ticketNumber);
		currScheduledEvent.setEventId(parseInt(panelView.getTxtEventId()
				.getText()));

		String type = (getSelectedValue((ListBox) panelView
				.getLstScheduleType()));
		// Set the type to be "1" by default.
		// TODO: define this default value somewhere else.
		type = (type == null || "".equals(type.trim())) ? "1" : type;
		currScheduledEvent.setType(type);

		currScheduledEvent.setParentEventId(parseInt(panelView
				.getTxtParentEventId().getText()));
		currScheduledEvent
				.setParentSplitType(getSelectedValue((ListBox) panelView
						.getLstParentSplitType()));
		currScheduledEvent.setManpower(parseDouble(panelView.getTxtManPower()
				.getText()));
		currScheduledEvent.setTimeEstimate(parseDouble(panelView
				.getTxtTimeEstimate().getText()));
		currScheduledEvent
				.setSpecialEventCode(getSelectedValue((ListBox) panelView
						.getLstSpecialEventCode()));
		currScheduledEvent.setTechnician(getSelectedValue((ListBox) panelView
				.getLstTechnician()));
		currScheduledEvent.setScheduledStartDate(getSqlDate(panelView
				.getDatScheduledTimeDate()));

		String scheduledHour = panelView.getTxtScheduledTimeHour().getText();
		scheduledHour = (scheduledHour == null || "".equals(scheduledHour
				.trim())) ? "00" : scheduledHour;
		String scheduledMinute = panelView.getTxtScheduledTimeMinute()
				.getText();
		scheduledMinute = (scheduledMinute == null || "".equals(scheduledMinute
				.trim())) ? "00" : scheduledMinute;

		currScheduledEvent.setScheduledStartTime(scheduledHour + "."
				+ scheduledMinute + ".00");

		currScheduledEvent.setTravelTimeEstimate(parseDouble(panelView
				.getTxtTravelTimeEstimate().getText()));
		currScheduledEvent
				.setServiceCategory(getSelectedValue((ListBox) panelView
						.getLstServiceCategory()));
		currScheduledEvent.setOverrideRates(((CheckBox) panelView
				.getChkOverrideRates()).getValue());
		currScheduledEvent.setLabourRate(parseDouble(panelView
				.getTxtLabourRate().getText()));
		currScheduledEvent.setOvertimeRate(parseDouble(panelView
				.getTxtOvertimeRate().getText()));
		currScheduledEvent.setStatus(getSelectedValue((ListBox) panelView
				.getLstStatus()));
	}

	/**
	 * Register listeners for panel.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 */
	private void registerListenersPanel(final String ticketNumber,
			final String panelNumber, final SchedulePanelType schedulePanelType) {

		if (areListenersRegistered) {
			return;
		}

		pBtnAddHandler = panelView.getBtnAdd().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {

						refreshScheduledEvent(ticketNumber);
						addScheduledEvent(ticketNumber, panelNumber);

					}
				});

		pBtnUpdateHandler = panelView.getBtnUpdate().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {

						refreshScheduledEvent(ticketNumber);
						updateScheduledEvent(ticketNumber, panelNumber);

					}
				});

		pBtnCancelHandler = panelView.getBtnCancel().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						History.newItem(Format.format(
								HistoryConstants.SERVICE_TICKET_VALUE,
								ticketNumber)
								+ Format.format(
										HistoryConstants.TAB_PANEL_VALUE,
										panelNumber));
					}
				});

		pBtnBackHandler = panelView.getBtnBack().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {

						if (schedulePanelType.value == SchedulePanelType.MAIN.value) {
							History
									.newItem(HistoryConstants.SERVICE_TICKET_LIST_VALUE);

						} else {
							History.newItem(Format.format(
									HistoryConstants.SERVICE_TICKET_VALUE,
									ticketNumber)
									+ Format.format(
											HistoryConstants.TAB_PANEL_VALUE,
											panelNumber));
						}
					}
				});

		pChkOverrideRatesHandler = ((CheckBox) panelView.getChkOverrideRates())
				.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						CheckBox overrideRates = (CheckBox) panelView
								.getChkOverrideRates();
						((TextBox) panelView.getTxtLabourRate())
								.setEnabled(overrideRates.getValue());
						((TextBox) panelView.getTxtOvertimeRate())
								.setEnabled(overrideRates.getValue());
					}
				});

		areListenersRegistered = true;
	}

	/**
	 * Update the current scheduled event on the backend.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 */
	private void updateScheduledEvent(String ticketNumber, String panelNumber) {

		updateScheduledEvent(ticketNumber, panelNumber, currScheduledEvent);
	}

	/**
	 * Update the specified scheduled event on the backend.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param event
	 *            scheduled event to be updated.
	 */
	private void updateScheduledEvent(String ticketNumber, String panelNumber,
			ScheduledEvent event) {

		updateScheduledEvent(event, new UpdateEventCallback(ticketNumber,
				panelNumber));
	}

	/**
	 * Update the specified scheduled event on the backend.
	 * 
	 * @param event
	 *            scheduled event to be updated.
	 * @param callback
	 *            to handle ther result of the update.
	 */
	private void updateScheduledEvent(ScheduledEvent event,
			UpdatedScheduledEvent callback) {
		try {
			ActionServices.App.getInstance().execute(
					UpdateScheduledEvent.newInstance(event), callback);
		} catch (ActionNotSupportedException e) {
			final String ERR_MSG = "Unable to update scheduled event.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Add the current scheduled event to the backend.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 */
	private void addScheduledEvent(String ticketNumber, String panelNumber) {
		try {
			ActionServices.App.getInstance().execute(
					AddScheduledEvent.newInstance(currScheduledEvent),
					new UpdateEventCallback(ticketNumber, panelNumber));
		} catch (ActionNotSupportedException e) {
			final String ERR_MSG = "Unable to add scheduled event.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Delete scheduled event from the backend.
	 * 
	 * @param ticketNumber
	 *            The number of ticket.
	 * @param eventId
	 *            The id of the event to be deleted.
	 * @param panelId
	 */
	private void deleteScheduledEvent(String ticketNumber, String eventId,
			String panelId) {
		try {
			ActionServices.App.getInstance().execute(
					DeleteScheduledEvent.newInstance(ticketNumber, eventId),
					new DeleteEventCallback(ticketNumber, eventId, panelId));
		} catch (ActionNotSupportedException e) {
			final String ERR_MSG = "Unable to delete scheduled event.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Clean-up listeners for grid.
	 */
	private void unRegisterListenersGrid() {
		// if (isGridListenerRegistered) {
		// gridView.getScheduleGrid().purgeListeners();
		// }
		// isGridListenerRegistered = false;

		if (!areGridListenersRegistered) {
			return;
		}
		
		gBtnAddHandler.removeHandler();
		gBtnUpdateHandler.removeHandler();
		gBtnDeleteHandler.removeHandler();
		gBtnViewHandler.removeHandler();
		gBtnAcceptHandler.removeHandler();
		gBtnDeclineHandler.removeHandler();
		gBtnWorkHistoryHandler.removeHandler();

		areGridListenersRegistered = false;
	}

	/**
	 * Clean-up listeners for panel
	 */
	private void unRegisterListenersPanel() {
		if (!areListenersRegistered) {
			return;
		}

		pBtnAddHandler.removeHandler();
		pBtnUpdateHandler.removeHandler();
		pBtnCancelHandler.removeHandler();
		pBtnBackHandler.removeHandler();
		pChkOverrideRatesHandler.removeHandler();

		areListenersRegistered = false;
	}

	private void updateButtons(int rowIndex, int statusCode) {

		boolean enabled = (rowIndex >= 0);

		((Button) gridView.getBtnUpdate()).setEnabled(enabled);
		((Button) gridView.getBtnDelete()).setEnabled(enabled);
		((Button) gridView.getBtnView()).setEnabled(enabled);
		((Button) gridView.getBtnWorkHistory()).setEnabled(enabled);

		Button btnAccept = (Button) gridView.getBtnAccept();
		Button btnDecline = (Button) gridView.getBtnDecline();

		// TODO: verify if the event belongs to the current user
		// before enabling the buttons? Or should we let the backend
		// do it?

		switch (statusCode) {
		case ScheduledEvent.STATUS_ASSIGNED:
			btnAccept.setEnabled(true);
			btnDecline.setEnabled(true);
			break;
		case ScheduledEvent.STATUS_CONTACTED:
			btnAccept.setEnabled(false);
			btnDecline.setEnabled(true);
			break;
		default:
			btnAccept.setEnabled(false);
			btnDecline.setEnabled(false);
			break;
		}
	}

	private void gridDblClick(GridPanel grid, int rowIndex) {
		Record selected = grid.getStore().getAt(rowIndex);
		final String scheduleId = selected.getAsString(listenerInfo
				.getUniqueId());

		History.newItem(Format.format(HistoryConstants.SCHEDULE_PANEL_VALUE,
				listenerInfo.getTicketNumber(), listenerInfo.getPanelNumber(),
				scheduleId, String.valueOf(SchedulePanelType.VIEW.value)));
	}

	private class DeleteConfirmation implements CmsConfirmCallback {
		private final String ticketNumber;
		private final String panelNumber;
		private final String scheduleId;

		public DeleteConfirmation(String ticketNumber, String panelNumber,
				String scheduleId) {
			this.ticketNumber = ticketNumber;
			this.panelNumber = panelNumber;
			this.scheduleId = scheduleId;
		}

		public void execute(String btnID) {
			if (!"yes".equalsIgnoreCase(btnID)) {
				return;
			}

			deleteScheduledEvent(ticketNumber, scheduleId, panelNumber);
		}
	}

	/**
	 * Call-Back class to get the scheduled events and render them.
	 * 
	 */
	protected class GetScheduleGridCallback extends GotScheduledEventList {

		private String panelNumber;

		public GetScheduleGridCallback(String panelNumber) {
			this.panelNumber = panelNumber;
		}

		public void got(String ticketNumber, ScheduledEventList eventList) {
			renderGrid(ticketNumber, panelNumber, eventList);
		}
	}

	/**
	 * Call-Back class to get the scheduled panel and render them.
	 * 
	 */
	protected class GetSchedulePanelCallback extends GotScheduledEvent {
		private String ticketNumber;
		private String panelNumber;
		private String scheduleId;
		private SchedulePanelType schedulePanelType;

		public GetSchedulePanelCallback(String ticketNumber,
				String panelNumber, String scheduleId,
				SchedulePanelType schedulePanelType) {
			this.ticketNumber = ticketNumber;
			this.panelNumber = panelNumber;
			this.scheduleId = scheduleId;
			this.schedulePanelType = schedulePanelType;
		}

		public void got(ScheduledEvent event) {
			renderPanel(ticketNumber, panelNumber, scheduleId,
					schedulePanelType, event);
		}
	}

	/**
	 * Callback class to handle the result of the model update.
	 * 
	 */
	protected class UpdateEventCallback extends UpdatedScheduledEvent {
		private String ticketNumber;
		private String panelNumber;

		public UpdateEventCallback(String ticketNumber, String panelNumber) {

			this.ticketNumber = ticketNumber;
			this.panelNumber = panelNumber;
		}

		@Override
		public void got(ScheduledEvent event) {

			reset();
			showScheduleGrid(ticketNumber, panelNumber);

			History.newItem(Format.format(
					HistoryConstants.SERVICE_TICKET_VALUE, ticketNumber)
					+ Format.format(HistoryConstants.TAB_PANEL_VALUE,
							panelNumber));
		}

		@Override
		public void onFailure(Throwable throwable) {

			super.displayErrorMsg(throwable, txtConsts
					.UpdateScheduledEventFailure(), new ExtCmsMessageBox());

		}
	}

	/**
	 * Callback class to handle the result of the model update.
	 * 
	 */
	protected class AddEventCallback extends UpdatedScheduledEvent {
		private String ticketNumber;
		private String panelNumber;

		public AddEventCallback(String ticketNumber, String panelNumber) {

			this.ticketNumber = ticketNumber;
			this.panelNumber = panelNumber;
		}

		@Override
		public void got(ScheduledEvent event) {
			History.newItem(Format.format(
					HistoryConstants.SERVICE_TICKET_VALUE, ticketNumber)
					+ Format.format(HistoryConstants.TAB_PANEL_VALUE,
							panelNumber));
		}

		@Override
		public void onFailure(Throwable throwable) {

			super.displayErrorMsg(throwable, txtConsts
					.AddScheduledEventFailure(), new ExtCmsMessageBox());
		}
	}

	/**
	 * Callback class to handle the result of the deletion.
	 * 
	 */
	protected class DeleteEventCallback extends DeletedScheduledEvent {
		private String ticketNumber;
		private String panelNumber;
		private String eventId;

		public DeleteEventCallback(String ticketNumber, String eventId,
				String panelNumber) {
			this.ticketNumber = ticketNumber;
			this.panelNumber = panelNumber;
			this.eventId = eventId;
		}

		/**
		 * 
		 * @param result
		 */
		protected void onSuccess(DeleteScheduledEventResponse result) {

			showNotifAndWarning(result, txtConsts
					.DeleteScheduledEventNotif(eventId));

			reset();
			showScheduleGrid(ticketNumber, panelNumber);
		}

		@Override
		public void onFailure(Throwable throwable) {

			super.displayErrorMsg(throwable, txtConsts
					.DeleteScheduledEventFailure(), new ExtCmsMessageBox());
		}
	}

	/**
	 * Callback class to handle the the GetScheduledEvent.
	 * 
	 */
	protected class UpdateEventStatusCallback extends GotScheduledEvent {

		private String ticketNumber;
		private String panelNumber;
		private int statusCode;

		public UpdateEventStatusCallback(String ticketNumber,
				String panelNumber, int statusCode) {
			this.ticketNumber = ticketNumber;
			this.panelNumber = panelNumber;
			this.statusCode = statusCode;
		}

		@Override
		public void onFailure(Throwable throwable) {

			super.displayErrorMsg(throwable, txtConsts
					.UpdateScheduledEventFailure(), new ExtCmsMessageBox());
		}

		@Override
		public void got(ScheduledEvent event) {

			//
			// Update the status code and save the change on the backend.
			//

			event.setStatus(Integer.toString(statusCode));

			saveEventStatus(ticketNumber, panelNumber, event);
		}
	}

	/**
	 * Callback class to handle the result of the status update.
	 * 
	 */
	protected class SaveEventStatusCallback extends UpdatedScheduledEvent {

		private String ticketNumber;
		private String panelNumber;
		private String eventId;

		public SaveEventStatusCallback(String ticketNumber, String panelNumber,
				String eventId) {
			this.ticketNumber = ticketNumber;
			this.panelNumber = panelNumber;
			this.eventId = eventId;
		}

		@Override
		public void onFailure(Throwable throwable) {

			super.displayErrorMsg(throwable, txtConsts
					.UpdateEventStatusFailure(), new ExtCmsMessageBox());
		}

		@Override
		public void got(ScheduledEvent event) {
			new ExtCmsMessageBox().alert(txtConsts
					.UpdateEventStatusNotif(eventId));

			reset();
			showScheduleGrid(ticketNumber, panelNumber);
		}
	}

}
