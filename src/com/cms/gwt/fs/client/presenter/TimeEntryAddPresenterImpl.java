/**
 * Work History:
 * 
 * AffanJ		2009-10-06		Always enable the Save button.
 */
package com.cms.gwt.fs.client.presenter;

import java.util.HashMap;
import java.util.Map;

import com.allen_sauer.gwt.log.client.Log;
import com.cms.gwt.common.client.ExtCmsMessageBox;
import com.cms.gwt.fs.client.HistoryConstants;
import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.exception.ActionNotSupportedException;
import com.cms.gwt.fs.client.model.ListenerInfo;
import com.cms.gwt.fs.client.model.workHistory.TimeEntry;
import com.cms.gwt.fs.client.model.workHistory.TimeEntryList;
import com.cms.gwt.fs.client.model.workHistory.WorkHistory;
import com.cms.gwt.fs.client.rpc.ActionServices;
import com.cms.gwt.fs.client.rpc.action.AddRecordedLabourList;
import com.cms.gwt.fs.client.rpc.action.GetTimeEntryList;
import com.cms.gwt.fs.client.rpc.actionResponse.AddRecordedLabourListResponse;
import com.cms.gwt.fs.client.rpc.callback.AddedRecordedLabourList;
import com.cms.gwt.fs.client.rpc.callback.GotTimeEntryList;
import com.cms.gwt.fs.client.util.DateUtil;
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

public class TimeEntryAddPresenterImpl extends BasePresenterImpl implements
		TimeEntryAddPresenter {

	private final View view;
	private final TextConstants txtConsts;

	private boolean areListenersRegistered;
	private boolean areGridListenersRegistered;

	private HandlerRegistration btnSaveHandler;
	private HandlerRegistration btnCancelHandler;

	private final ListenerInfo listenerInfo;
	private int rowIndex;
	private Map<String, TimeEntry> dirtyRecords;

	private WorkHistory currWorkHistory;

	/**
	 * Constructor.
	 * 
	 * @param eventBus
	 * @param view
	 */
	@Inject
	public TimeEntryAddPresenterImpl(View view) {

		this.view = view;

		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		areListenersRegistered = false;
		areGridListenersRegistered = false;

		listenerInfo = new ListenerInfo();
		rowIndex = -1;
		dirtyRecords = new HashMap<String, TimeEntry>();
	}

	/**
	 * {@inheritDoc}
	 */
	public void showTimeEntry(String ticketNumber, String panelNumber,
			String eventId, String tabNumber) {
		getTimeEntry(ticketNumber, panelNumber, eventId, tabNumber);
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

	private WorkHistory getCurrWorkHistory() {
		return currWorkHistory;
	}

	private void setCurrWorkHistory(WorkHistory currWorkHistory) {
		this.currWorkHistory = currWorkHistory;
	}

	/**
	 * RPC to get data.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param eventId
	 */
	private void getTimeEntry(String ticketNumber, String panelNumber,
			String eventId, String tabNumber) {
		try {
			ActionServices.App.getInstance().execute(
					GetTimeEntryList.newInstance(ticketNumber, eventId),
					new GetTimeEntryCallback(ticketNumber, panelNumber,
							eventId, tabNumber));
		} catch (ActionNotSupportedException e) {
			final String ERR_MSG = "Unable to get the time entry.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Display panel data.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param eventId
	 * @param tabNumber
	 * @param workHistory
	 */
	private void renderPanel(String ticketNumber, String panelNumber,
			String eventId, String tabNumber, WorkHistory workHistory) {

		setCurrWorkHistory(workHistory);

		view.getTxtTicketNumber().setText(workHistory.getTicketNumber());
		view.getTxtEventId().setText(formatInt(workHistory.getEventId()));
		view.getTxtCounterReading().setText(
				formatInt(workHistory.getCounterReading()));
		view.getTxtTechnician().setText(workHistory.getTechnician());
		view.getTxtTechnicianDescription().setText(
				workHistory.getTechnicianDescription());
		view.getTxtArrivalDate().setText(
				formatSqlDate(workHistory.getArrivalDate()));
		view.getTxtArrivalTime().setText(
				DateUtil.formatTime(workHistory.getArrivalTime()));
		view.getTxtTotalHoursWorked().setText(
				formatDouble(workHistory.getTotalHoursWorked()));

		registerPanelListeners(ticketNumber, panelNumber, eventId, tabNumber);
	}

	/**
	 * Display grid data.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param eventId
	 * @param tabNumber
	 * @param timeEntryList
	 */
	private void renderGrid(String ticketNumber, String panelNumber,
			String eventId, String tabNumber, TimeEntryList timeEntryList) {

		Object[][] timeEntryData = new Object[timeEntryList.getEntries().size()][];

		int i = 0;

		// Always initializes the "is Overtime" as false;
		for (TimeEntry entry : timeEntryList.getEntries()) {
			timeEntryData[i++] = new Object[] { entry.getSequence(),
					entry.getDescription(), entry.getEstimate(),
					entry.getActual(), entry.getEntered(), "false",
					entry.isWarranty() };
		}

		view.initTimeEntryGrid(timeEntryData);

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

		btnSaveHandler = view.getBtnSave().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				TimeEntryList newEntries = new TimeEntryList();
				newEntries.setTicketNumber(getCurrWorkHistory()
						.getTicketNumber());
				newEntries.setEventId(getCurrWorkHistory().getEventId());

				for (String key : dirtyRecords.keySet()) {

					newEntries.addEntry(dirtyRecords.get(key));
				}

				addRecordedLabour(ticketNumber, panelNumber, eventId,
						tabNumber, newEntries);
			}
		});

		btnCancelHandler = view.getBtnCancel().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						History.newItem(Format.format(
								HistoryConstants.TIME_ENTRY_VALUE,
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
					TimeEntry timeEntry = new TimeEntry();
					timeEntry.setSequence(record.getAsInteger(txtConsts
							.TESequence()));
					timeEntry.setDescription(record.getAsString(txtConsts
							.TEDescription()));
					timeEntry.setEstimate(record.getAsDouble(txtConsts
							.TEEstimate()));
					timeEntry.setEntered(record.getAsDouble(txtConsts
							.TEEntered()));
					timeEntry.setActual(record
							.getAsDouble(txtConsts.TEActual()));
					timeEntry.setOvertime("true".equals(record
							.getAsString(txtConsts.TEOvertime())));
					timeEntry.setWarranty("true".equals(record
							.getAsString(txtConsts.TEWarranty())));

					String key = record.getAsString(txtConsts.TESequence());
					dirtyRecords.put(key, timeEntry);

				} catch (Exception e) {
					ExtCmsMessageBox msg = new ExtCmsMessageBox();
					msg.alert(txtConsts.Error(), e.getMessage());
				}

			}
		});

		areGridListenersRegistered = true;
	}

	/**
	 * Clean-up panel listeners.
	 */
	private void unRegisterPanelListeners() {
		if (!areListenersRegistered) {
			return;
		}

		btnSaveHandler.removeHandler();
		// btnEditHandler.removeHandler();
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
		((Button) view.getBtnSave()).setEnabled(true);
		// if (isEdit) {
		// ((Button) view.getBtnSave()).setEnabled(true);
		// }
	}

	private boolean isRowEditable(GridPanel grid) {
		boolean rval = false;

		if (rowIndex < 0) {
			return rval;
		}

		Record selected = grid.getStore().getAt(rowIndex);
		rval = (selected != null);
		return rval;
	}

	private void resetGridVars() {
		rowIndex = -1;
		// ((Button) view.getBtnSave()).setEnabled(false);
		dirtyRecords.clear();
	}

	/**
	 * Call-Back class to handle the recorded labour list and the work history.
	 * 
	 */
	protected class GetTimeEntryCallback extends GotTimeEntryList {

		private String ticketNumber;
		private String panelNumber;
		private String eventId;
		private String tabNumber;

		public GetTimeEntryCallback(String ticketNumber, String panelNumber,
				String eventId, String tabNumber) {
			this.ticketNumber = ticketNumber;
			this.panelNumber = panelNumber;
			this.eventId = eventId;
			this.tabNumber = tabNumber;
		}

		public void got(TimeEntryList timeEntryList, WorkHistory workHistory) {
			renderPanel(ticketNumber, panelNumber, eventId, tabNumber,
					workHistory);
			renderGrid(ticketNumber, panelNumber, eventId, tabNumber,
					timeEntryList);

		}
	}

	/**
	 * Add time entry to the recorded labour list.
	 * 
	 * @param ticketNum
	 * @param panelNum
	 * @param eventId
	 * @param tabNum
	 * @param timeEntries
	 */
	private void addRecordedLabour(String ticketNum, String panelNum,
			String eventId, String tabNum, TimeEntryList timeEntries) {
		try {
			ActionServices.App.getInstance().execute(
					AddRecordedLabourList.newInstance(timeEntries),
					new AddRecordedLabourCallback(ticketNum, panelNum, eventId,
							tabNum));
		} catch (ActionNotSupportedException e) {
			final String ERR_MSG = "Unable to add recorded labour list.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Call-Back class to handle the result and navigate back to the recorded
	 * labour list.
	 * 
	 */
	protected class AddRecordedLabourCallback extends AddedRecordedLabourList {

		private String ticketNumber;
		private String panelNumber;
		private String eventId;
		private String tabNumber;

		public AddRecordedLabourCallback(String ticketNumber,
				String panelNumber, String eventId, String tabNumber) {
			this.ticketNumber = ticketNumber;
			this.panelNumber = panelNumber;
			this.eventId = eventId;
			this.tabNumber = tabNumber;
		}

		@Override
		public void onFailure(Throwable throwable) {

			super.displayErrorMsg(throwable, txtConsts
					.AddRecordedLabourFailure(), new ExtCmsMessageBox());
		}

		@Override
		public void onSuccess(AddRecordedLabourListResponse response) {

			showNotifAndWarning(response, null);

			History.newItem(Format.format(HistoryConstants.TIME_ENTRY_VALUE,
					ticketNumber, panelNumber, eventId, tabNumber));
		}
	}
}
