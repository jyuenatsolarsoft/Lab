package com.cms.gwt.fs.client.presenter;

import java.util.HashMap;
import java.util.Map;

import com.allen_sauer.gwt.log.client.Log;
import com.cms.gwt.common.client.ExtCmsMessageBox;
import com.cms.gwt.common.client.CmsMessageBox.CmsConfirmCallback;
import com.cms.gwt.fs.client.HistoryConstants;
import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.exception.ActionNotSupportedException;
import com.cms.gwt.fs.client.model.ListenerInfo;
import com.cms.gwt.fs.client.model.workHistory.RecordedLabourList;
import com.cms.gwt.fs.client.model.workHistory.RecordedLabourListEntry;
import com.cms.gwt.fs.client.model.workHistory.WorkHistory;
import com.cms.gwt.fs.client.rpc.ActionServices;
import com.cms.gwt.fs.client.rpc.action.DeleteRecordedLabour;
import com.cms.gwt.fs.client.rpc.action.GetRecordedLabourList;
import com.cms.gwt.fs.client.rpc.action.SaveRecordedLabourList;
import com.cms.gwt.fs.client.rpc.actionResponse.DeleteRecordedLabourResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.SaveRecordedLabourListResponse;
import com.cms.gwt.fs.client.rpc.callback.DeletedRecordedLabour;
import com.cms.gwt.fs.client.rpc.callback.GotRecordedLabourList;
import com.cms.gwt.fs.client.rpc.callback.SavedRecordedLabourList;
import com.cms.gwt.fs.client.util.DateUtil;
import com.cms.gwt.fs.client.util.StringUtil;
import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Button;
import com.google.inject.Inject;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.Record;
import com.gwtext.client.util.Format;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.event.EditorGridListener;
import com.gwtext.client.widgets.grid.event.GridCellListener;

public class TimeEntryPresenterImpl extends BasePresenterImpl implements
		TimeEntryPresenter {

	private final View view;
	private final TextConstants txtConsts;

	private boolean areListenersRegistered;
	private boolean areGridListenersRegistered;

	private HandlerRegistration btnAddTimeHandler;
	private HandlerRegistration btnSaveHandler;
	// private HandlerRegistration btnEditHandler;
	private HandlerRegistration btnDeleteHandler;
	private HandlerRegistration btnCancelHandler;

	private ListenerInfo listenerInfo;
	private int rowIndex;
	private Map<String, RecordedLabourListEntry> dirtyRecords;

	private WorkHistory currWorkHistory;

	/**
	 * Constructor.
	 * 
	 * @param view
	 */
	@Inject
	public TimeEntryPresenterImpl(View view) {
		this.view = view;

		areListenersRegistered = false;
		areGridListenersRegistered = false;

		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		listenerInfo = new ListenerInfo();
		rowIndex = -1;
		dirtyRecords = new HashMap<String, RecordedLabourListEntry>();
	}

	/**
	 * {@inheritDoc}
	 */
	public void showTimeEntry(String ticketNumber, String panelNumber,
			String eventId, String tabNumber) {
		getRecordedLabourList(ticketNumber, panelNumber, eventId, tabNumber);
	}

	/**
	 * {@inheritDoc}
	 */
	public BaseView getView() {
		return view;
	}

	/**
	 * {@inheritDoc}
	 */
	public BaseView getPanelView() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset() {
		unRegisterGridListeners();
		view.clearTimeEntryGrid();
		resetGridVars();

		unRegisterPanelListeners();
		view.reset();
	}

	/**
	 * {@inheritDoc}
	 */
	public void resetPanel() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void setVisible(boolean visible) {
		view.getWidget().setVisible(visible);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setPanelVisible(boolean visible) {
	}

	/**
	 * RPC to get data.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param eventId
	 */
	private void getRecordedLabourList(String ticketNumber, String panelNumber,
			String eventId, String tabNumber) {

		try {
			ActionServices.App.getInstance().execute(
					GetRecordedLabourList.newInstance(ticketNumber, eventId),
					new GetRecordedLabourCallback(ticketNumber, panelNumber,
							eventId, tabNumber));
		} catch (ActionNotSupportedException e) {
			final String ERR_MSG = "Unable to get recorded labour list.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Show the panel data.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param eventId
	 * @param tabNumber
	 */
	private void renderPanel(String ticketNumber, String panelNumber,
			String eventId, String tabNumber, WorkHistory workHistory) {

		setCurrWorkHistory(workHistory);

		view.getTxtTicketNumber().setText(workHistory.getTicketNumber());
		view.getTxtEventId().setText(formatInt((workHistory.getEventId())));
		view.getTxtCounterReading().setText(
				formatInt(workHistory.getCounterReading()));
		view.getTxtTechnician().setText(workHistory.getTechnician());
		view.getTxtTechnicianDescription().setText(
				workHistory.getTechnicianDescription());
		view.getTxtArrivalDate().setText(
				formatSqlDate(workHistory.getArrivalDate()));
		view.getTxtArrivalTime().setText(
				DateUtil.formatTime(workHistory.getArrivalTime()));
		view.getTxtTotalTimeRecorded().setText(
				formatDouble(workHistory.getTotalTimeRecorded()));

		registerPanelListeners(ticketNumber, panelNumber, eventId, tabNumber);
	}

	private WorkHistory getCurrWorkHistory() {
		return currWorkHistory;
	}

	private void setCurrWorkHistory(WorkHistory currWorkHistory) {
		this.currWorkHistory = currWorkHistory;
	}

	/**
	 * Show the grid data.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param eventId
	 * @param tabNumber
	 */
	private void renderGrid(String ticketNumber, String panelNumber,
			String eventId, String tabNumber, RecordedLabourList labourList) {

		Object[][] labourData = new Object[labourList.getEntries().size()][];

		int i = 0;
		for (RecordedLabourListEntry entry : labourList.getEntries()) {
			labourData[i++] = new Object[] { entry.getSequence(),
					entry.getDescription(), entry.getLine(), entry.getTime(),
					entry.isOvertime(), entry.isWarranty(), entry.isPosted() };
		}

		view.initTimeEntryGrid(labourData);

		registerGridListeners(ticketNumber, panelNumber, eventId, tabNumber);
	}

	/**
	 * Register listeners for panel.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param eventId
	 * @param tabNumber
	 */
	private void registerPanelListeners(final String ticketNumber,
			final String panelNumber, final String eventId,
			final String tabNumber) {
		if (areListenersRegistered) {
			return;
		}

		btnAddTimeHandler = view.getBtnAddTime().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						History.newItem(Format.format(
								HistoryConstants.TIME_ENTRY_ADD_VALUE,
								ticketNumber, panelNumber, eventId, tabNumber));
					}
				});

		btnSaveHandler = view.getBtnSave().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				RecordedLabourList entries = new RecordedLabourList();
				entries.setTicketNumber(getCurrWorkHistory().getTicketNumber());
				entries.setEventId(getCurrWorkHistory().getEventId());

				for (String key : dirtyRecords.keySet()) {
					entries.addEntry(dirtyRecords.get(key));
				}

				saveRecordedLabour(ticketNumber, eventId, panelNumber,
						tabNumber, entries);
			}
		});

		// btnEditHandler = view.getBtnEdit().addClickHandler(new ClickHandler()
		// {
		// public void onClick(ClickEvent event) {
		// Record selected = view.getTimeEntryGrid().getSelectionModel()
		// .getSelected();
		// if (selected == null) {
		// return;
		// }
		// String sequence = selected.getAsString(txtConsts.TESequence());
		// String line = selected.getAsString(txtConsts.TELine());
		// eventBus
		// .fireEvent(new TimeEntryEditEvent(ticketNumber,
		// panelNumber, eventId, tabNumber, sequence,
		// line, false));
		// }
		// });

		btnDeleteHandler = view.getBtnDelete().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						Record selected = view.getTimeEntryGrid().getStore()
								.getAt(rowIndex);
						if (selected == null) {
							return;
						}
						String sequence = selected.getAsString(txtConsts
								.TESequence());
						String line = selected.getAsString(txtConsts.TELine());

						ExtCmsMessageBox box = new ExtCmsMessageBox();
						box.confirm(txtConsts.Delete(), txtConsts.Confirm(
								txtConsts.Delete(), txtConsts.TimeEntry(),
								sequence + " " + line), new DeleteConfirmation(
								ticketNumber, panelNumber, eventId, tabNumber,
								sequence, line));
					}
				});

		btnCancelHandler = view.getBtnCancel().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						History.newItem(Format.format(
								HistoryConstants.WORK_HISTORY_PANEL_VALUE,
								ticketNumber, panelNumber, eventId, tabNumber));
					}
				});

		areListenersRegistered = true;
	}

	/**
	 * Register listeners for grid.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param eventId
	 * @param tabNumber
	 */
	private void registerGridListeners(final String ticketNumber,
			final String panelNumber, final String eventId,
			final String tabNumber) {

		listenerInfo.setTicketNumber(ticketNumber);
		listenerInfo.setPanelNumber(panelNumber);
		listenerInfo.setEventId(eventId);
		listenerInfo.setTabNumber(tabNumber);

		if (areGridListenersRegistered) {
			return;
		}

		view.getGridResetButton().addListener(new ButtonListenerAdapter() {
			@Override
			public void onClick(com.gwtext.client.widgets.Button button,
					EventObject e) {
				view.getTimeEntryGrid().clearSortState(true);
				resetGridVars();
			}
		});

		view.getTimeEntryGrid().addGridCellListener(new GridCellListener() {
			public void onCellClick(GridPanel grid, int rowIndex2,
					int colIndex, EventObject e) {
				rowIndex = rowIndex2;
				updateButtons(grid, false);
			}

			public void onCellContextMenu(GridPanel grid, int rowIndex,
					int cellIndex, EventObject e) {
				// nothing
			}

			public void onCellDblClick(GridPanel grid, int rowIndex,
					int colIndex, EventObject e) {
				// nothing
			}
		});

		view.getTimeEntryGrid().addEditorGridListener(new EditorGridListener() {
			public boolean doBeforeEdit(GridPanel grid, Record record,
					String field, Object value, int rowIndex2, int colIndex) {
				// nothing
				rowIndex = rowIndex2;
				return isRowEditable(grid);
			}

			public boolean doValidateEdit(GridPanel grid, Record record,
					String field, Object value, Object originalValue,
					int rowIndex, int colIndex) {
				// nothing
				return true;
			}

			public void onAfterEdit(GridPanel grid, Record record,
					String field, Object newValue, Object oldValue,
					int rowIndex2, int colIndex) {

				rowIndex = rowIndex2;
				updateButtons(grid, true);

				// TODO - fix this thing
				try {
					RecordedLabourListEntry recordedLabourListEntry = new RecordedLabourListEntry();
					recordedLabourListEntry.setSequence(record
							.getAsInteger(txtConsts.TESequence()));
					recordedLabourListEntry.setDescription(record
							.getAsString(txtConsts.TEDescription()));
					recordedLabourListEntry.setLine(record
							.getAsInteger(txtConsts.TELine()));
					recordedLabourListEntry.setTime(record
							.getAsInteger(txtConsts.TETime()));
					recordedLabourListEntry.setOvertime("true".equals(record
							.getAsString(txtConsts.TEOvertime())));
					recordedLabourListEntry.setWarranty("true".equals(record
							.getAsString(txtConsts.TEWarranty())));
					recordedLabourListEntry.setPosted("true".equals(record
							.getAsString(txtConsts.TEIsPosted())));

					String key = record.getAsString(txtConsts.TESequence())
							+ "_" + record.getAsString(txtConsts.TELine());
					dirtyRecords.put(key, recordedLabourListEntry);

				} catch (Exception e) {
					ExtCmsMessageBox msg = new ExtCmsMessageBox();
					msg.alert(txtConsts.Error(), e.getMessage());
				}

			}
		});

		areGridListenersRegistered = true;
	}

	/**
	 * Delete the specified recorded labour.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param eventId
	 * @param tabNumber
	 * @param sequence
	 * @param line
	 */
	private void deleteRecordedLabour(String ticketNumber, String panelNumber,
			String eventId, String tabNumber, String sequence, String line) {
		try {
			ActionServices.App.getInstance().execute(
					DeleteRecordedLabour.newInstance(ticketNumber, eventId,
							sequence, line),
					new DeleteRecordedLabourCallback(ticketNumber, eventId,
							panelNumber, tabNumber));
		} catch (ActionNotSupportedException e) {
			final String ERR_MSG = "Unable to delete recorded labour.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Clean-up panel listeners.
	 */
	private void unRegisterPanelListeners() {
		if (!areListenersRegistered) {
			return;
		}

		btnAddTimeHandler.removeHandler();
		btnSaveHandler.removeHandler();
		// btnEditHandler.removeHandler();
		btnDeleteHandler.removeHandler();
		btnCancelHandler.removeHandler();

		areListenersRegistered = false;
	}

	/**
	 * Clean-up grid listeners.
	 */
	private void unRegisterGridListeners() {
		// if (!areGridListenersRegistered) {
		// return;
		// }
		// areGridListenersRegistered = false;
	}

	/**
	 * Update buttons based on user grid selection.
	 * 
	 * @param grid
	 */
	private void updateButtons(GridPanel grid, boolean isEdit) {
		boolean enabled = isRowEditable(grid);

		if (isEdit) {
			((Button) view.getBtnSave()).setEnabled(true);
		}
		// ((Button) view.getBtnEdit()).setEnabled(enabled);
		((Button) view.getBtnDelete()).setEnabled(enabled);
	}

	/**
	 * Handle double-click on grid.
	 * 
	 * @param grid
	 */
	// private void gridDblClick(GridPanel grid) {
	// if (!isRowEditable(grid)) {
	// return;
	// }
	//
	// Record selected = grid.getSelectionModel().getSelected();
	// String sequence = selected.getAsString(txtConsts.TESequence());
	// String line = selected.getAsString(txtConsts.TELine());
	// eventBus.fireEvent(new TimeEntryEditEvent(listenerInfo
	// .getTicketNumber(), listenerInfo.getPanelNumber(), listenerInfo
	// .getEventId(), listenerInfo.getTabNumber(), sequence, line,
	// false));
	// }
	/**
	 * Checks if the selected row is edit-able or not.
	 * 
	 * @param grid
	 * @return
	 */
	private boolean isRowEditable(GridPanel grid) {
		boolean rval = false;
		if (rowIndex < 0) {
			return rval;
		}
		Record selected = grid.getStore().getAt(rowIndex);
		if (selected == null) {
			return rval;
		}

		// if NOT posted then editable
		rval = "false".equalsIgnoreCase(selected.getAsString(StringUtil
				.makeId(txtConsts.TEIsPosted())));
		return rval;
	}

	private void resetGridVars() {
		rowIndex = -1;
		((Button) view.getBtnSave()).setEnabled(false);
		dirtyRecords.clear();
	}

	/**
	 * Save the change on the recorded labour.
	 * 
	 */
	private void saveRecordedLabour(String ticketNumber, String eventId,
			String panelNumber, String tabNumber, RecordedLabourList labourList) {
		try {
			ActionServices.App.getInstance().execute(
					SaveRecordedLabourList.newInstance(labourList),
					new SaveRecordedLabourCallback(ticketNumber, eventId,
							panelNumber, tabNumber));
		} catch (ActionNotSupportedException e) {
			final String ERR_MSG = "Unable to save recorded labour.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Class for confirming if user really wants to DELETE the record.
	 * 
	 */
	private class DeleteConfirmation implements CmsConfirmCallback {
		private final String ticketNumber;
		private final String panelNumber;
		private final String eventId;
		private final String tabNumber;
		private final String sequence;
		private final String line;

		public DeleteConfirmation(String ticketNumber, String panelNumber,
				String eventId, String tabNumber, String sequence, String line) {
			this.ticketNumber = ticketNumber;
			this.panelNumber = panelNumber;
			this.eventId = eventId;
			this.tabNumber = tabNumber;
			this.sequence = sequence;
			this.line = line;
		}

		public void execute(String btnID) {
			if (!"yes".equalsIgnoreCase(btnID)) {
				return;
			}

			deleteRecordedLabour(ticketNumber, panelNumber, eventId, tabNumber,
					sequence, line);
		}
	}

	/**
	 * Call-Back class to handle the recorded labour list and the work history.
	 * 
	 */
	protected class GetRecordedLabourCallback extends GotRecordedLabourList {
		private String ticketNumber;
		private String panelNumber;
		private String eventId;
		private String tabNumber;

		public GetRecordedLabourCallback(String ticketNumber,
				String panelNumber, String eventId, String tabNumber) {
			this.ticketNumber = ticketNumber;
			this.panelNumber = panelNumber;
			this.eventId = eventId;
			this.tabNumber = tabNumber;
		}

		@Override
		public void got(RecordedLabourList labourList, WorkHistory workHistory) {
			renderPanel(ticketNumber, panelNumber, eventId, tabNumber,
					workHistory);
			renderGrid(ticketNumber, panelNumber, eventId, tabNumber,
					labourList);

		}
	}

	/**
	 * Callback class to handle the result of the updating the recorded labour.
	 * 
	 */
	protected class SaveRecordedLabourCallback extends SavedRecordedLabourList {

		private String ticketNumber;
		private String panelNumber;
		private String eventId;
		private String tabNumber;

		public SaveRecordedLabourCallback(String ticketNumber, String eventId,
				String panelNumber, String tabNumber) {
			this.ticketNumber = ticketNumber;
			this.panelNumber = panelNumber;
			this.eventId = eventId;
			this.tabNumber = tabNumber;
		}

		/**
		 * 
		 * @param result
		 */
		protected void onSuccess(SaveRecordedLabourListResponse result) {

			showNotifAndWarning(result, txtConsts.SaveRecordedLabourListNotif());

			reset();
			showTimeEntry(ticketNumber, panelNumber, eventId, tabNumber);
		}

		public void onFailure(Throwable throwable) {

			super.displayErrorMsg(throwable, txtConsts
					.SaveRecordedLabourListFailure(), new ExtCmsMessageBox());
		}

	}

	/**
	 * Callback class to handle the result of the deletion.
	 * 
	 */
	protected class DeleteRecordedLabourCallback extends DeletedRecordedLabour {

		private String ticketNumber;
		private String panelNumber;
		private String eventId;
		private String tabNumber;

		public DeleteRecordedLabourCallback(String ticketNumber,
				String eventId, String panelNumber, String tabNumber) {
			this.ticketNumber = ticketNumber;
			this.panelNumber = panelNumber;
			this.eventId = eventId;
			this.tabNumber = tabNumber;
		}

		/**
		 * 
		 * @param result
		 */
		protected void onSuccess(DeleteRecordedLabourResponse result) {

			showNotifAndWarning(result, txtConsts.DeleteRecordedLabourNotif());

			reset();
			showTimeEntry(ticketNumber, panelNumber, eventId, tabNumber);
		}

		@Override
		public void onFailure(Throwable throwable) {

			super.displayErrorMsg(throwable, txtConsts
					.DeleteRecordedLabourFailure(), new ExtCmsMessageBox());
		}
	}
}
