/**
 * Work History:
 * 
 * AffanJ		2009-10-06		Leave the Arrival Info Date Time blank if no Date Time is returned from back-end.	
 */

package com.cms.gwt.fs.client.presenter;

import java.util.HashMap;
import java.util.Map;

import com.allen_sauer.gwt.log.client.Log;
import com.cms.gwt.common.client.ExtCmsMessageBox;
import com.cms.gwt.fs.client.HistoryConstants;
import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.exception.ActionNotSupportedException;
import com.cms.gwt.fs.client.model.workHistory.WorkHistory;
import com.cms.gwt.fs.client.notes.Notes;
import com.cms.gwt.fs.client.notes.NotesPage;
import com.cms.gwt.fs.client.notes.Notes.NotesType;
import com.cms.gwt.fs.client.presenter.NotesWidgetPresenter.NoteType;
import com.cms.gwt.fs.client.presenter.NotesWidgetPresenter.NotesSaveHandler;
import com.cms.gwt.fs.client.rpc.ActionServices;
import com.cms.gwt.fs.client.rpc.action.CompleteServiceTicketEvent;
import com.cms.gwt.fs.client.rpc.action.GetNotes;
import com.cms.gwt.fs.client.rpc.action.GetWorkHistory;
import com.cms.gwt.fs.client.rpc.action.SaveNotesPage;
import com.cms.gwt.fs.client.rpc.actionResponse.CompleteServiceTicketEventResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.SaveNotesPageResponse;
import com.cms.gwt.fs.client.rpc.callback.ActionAsyncCallback;
import com.cms.gwt.fs.client.rpc.callback.CompletedServiceTicketEvent;
import com.cms.gwt.fs.client.rpc.callback.GotNotes;
import com.cms.gwt.fs.client.rpc.callback.GotWorkHistory;
import com.cms.gwt.fs.client.rpc.callback.SavedNotesPage;
import com.cms.gwt.fs.client.util.DateUtil;
import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.inject.Inject;
import com.gwtext.client.util.Format;

public class WorkHistoryPresenterImpl extends BasePresenterImpl implements
		WorkHistoryPresenter {

	public enum WorkHistoryPanelType {
		NORMAL, COUNTER, ARRIVAL_INFO;
	}

	private final View view;
	private final NotesWidgetPresenter solutionNotes;
	private final NotesWidgetPresenter timeSheetNotes;
	private final TextConstants txtConsts;

	private int currTabNumber;
	private boolean isInitWorkHistory;
	private boolean isInitSolutionNotepad;
	private boolean isInitTimeSheetNotepad;

	private boolean areListenersRegistered;
	private HandlerRegistration tabPanelHandler;
	private HandlerRegistration btnCounterHandler;
	private HandlerRegistration btnTimeEntryHandler;
	private HandlerRegistration btnMaterialHandler;
	private HandlerRegistration btnCompleteHandler;
	private HandlerRegistration btnArrivalInfoHandler;
	private HandlerRegistration btnCancelHandler;

	/**
	 * Constructor.
	 * 
	 * @param eventBus
	 *            The Events Manager
	 */
	@Inject
	public WorkHistoryPresenterImpl(View view,
			NotesWidgetPresenter solutionNotes,
			NotesWidgetPresenter timeSheetNotes) {

		this.view = view;
		this.solutionNotes = solutionNotes;
		this.timeSheetNotes = timeSheetNotes;

		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		// put the notes in the tab
		view.getTabPanel().add(solutionNotes.getView().getWidget(),
				txtConsts.WHSolutionNotepad());
		view.getTabPanel().add(timeSheetNotes.getView().getWidget(),
				txtConsts.WHTimeSheetNotepad());

		areListenersRegistered = false;

		setInstanceVars();
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
		unRegisterListeners();
		view.reset();
		view.getTabPanel().selectTab(0);
		solutionNotes.reset();
		timeSheetNotes.reset();

		setInstanceVars();
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
	 * {@inheritDoc}
	 */
	public void showWorkHistoryPanel(String ticketNumber, String panelNumber,
			String eventId, String tabNumber) {
		getTabContent(ticketNumber, panelNumber, eventId, tabNumber);
	}

	/**
	 * Set and Resets instance variables
	 */
	private void setInstanceVars() {
		currTabNumber = 0;
		isInitWorkHistory = false;
		isInitSolutionNotepad = false;
		isInitTimeSheetNotepad = false;
	}

	/**
	 * Decides which panel to show and then calls appropriate function to
	 * populate data in the panel.
	 * 
	 * @param tabNumber
	 */
	private void getTabContent(String ticketNumber, String panelNumber,
			String eventId, String tabNumber) {
		int currTabNum = 0;
		try {
			currTabNum = Integer.parseInt(tabNumber);
		} catch (NumberFormatException e) {
		}
		getTabContent(ticketNumber, panelNumber, eventId, currTabNum);
	}

	private void getTabContent(String ticketNumber, String panelNumber,
			String eventId, int tabNumber) {
		currTabNumber = tabNumber;
		boolean isTabGood = true;
		switch (tabNumber) {
		case 0:
			// work history
			if (!isInitWorkHistory) {
				getWorkHistoryForPanel(ticketNumber, panelNumber, eventId);
				isInitWorkHistory = true;
			}
			break;
		case 1:
			// solution notepad
			if (!isInitSolutionNotepad) {
				getSolutionNotepad(ticketNumber, panelNumber);
			}
			isInitSolutionNotepad = true;
			break;
		case 2:
			// timesheet notepad
			if (!isInitTimeSheetNotepad) {
				getTimeSheetNotepad(ticketNumber, panelNumber);
			}
			isInitTimeSheetNotepad = true;
			break;
		default:
			isTabGood = false;
			break;
		}

		if (isTabGood && !areListenersRegistered) {
			view.getTabPanel().selectTab(currTabNumber);
			registerListeners(ticketNumber, panelNumber, eventId);
		}

	}

	/**
	 * Make RPC to get specified work history for the work history panel.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param eventId
	 */
	private void getWorkHistoryForPanel(String ticketNumber,
			String panelNumber, String eventId) {

		getWorkHistory(ticketNumber, eventId,
				new GetWorkHistoryForPanelCallback(ticketNumber, panelNumber,
						eventId));
	}

	/**
	 * Make RPC to get specified work history.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param eventId
	 */
	private void getWorkHistory(String ticketNumber, String eventId,
			GotWorkHistory callback) {

		try {
			ActionServices.App.getInstance()
					.execute(GetWorkHistory.newInstance(ticketNumber, eventId),
							callback);
		} catch (ActionNotSupportedException e) {
			final String ERR_MSG = "Unable to get the work history.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Complete the scheduled event.
	 * 
	 * @param ticketNumber
	 * @param eventId
	 * @param panelNumber
	 * @param tabNumber
	 */
	private void completeScheduledEvent(String ticketNumber, String eventId,
			String panelNumber, String tabNumber) {
		completeScheduledEvent(ticketNumber, eventId,
				new CompleteScheduledEventCallback(ticketNumber, panelNumber));
	}

	/**
	 * Complete the scheduled event.
	 * 
	 * @param ticketNumber
	 * @param eventId
	 * @param callback
	 */
	private void completeScheduledEvent(String ticketNumber, String eventId,
			ActionAsyncCallback callback) {
		try {
			ActionServices.App.getInstance().execute(
					CompleteServiceTicketEvent.newInstance(ticketNumber,
							eventId), callback);
		} catch (ActionNotSupportedException e) {
			final String ERR_MSG = "Unable to complete service ticket event.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Gets data for the notepad.
	 * 
	 * @param pageNumber
	 */
	private void getSolutionNotepad(String ticketNumber, String panelNumber) {
		try {
			Map<String, String> key = new HashMap<String, String>();

			key.put(Notes.NotesType.SOLUTION.getKeyFieldNames()[0],
					ticketNumber);

			ActionServices.App.getInstance().execute(
					GetNotes.newInstance(Notes.NotesType.SOLUTION, key),
					new GetSolutionNotesCallback());
		} catch (ActionNotSupportedException e) {
			final String ERR_MSG = "Unable to get solution notes.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Gets data for the notepad.
	 * 
	 * @param pageNumber
	 */
	private void getTimeSheetNotepad(String ticketNumber, String panelNumber) {
		try {
			Map<String, String> key = new HashMap<String, String>();

			key.put(Notes.NotesType.TIMESHEET.getKeyFieldNames()[0],
					ticketNumber);

			ActionServices.App.getInstance().execute(
					GetNotes.newInstance(Notes.NotesType.TIMESHEET, key),
					new GetTimeSheetNotesCallback());
		} catch (ActionNotSupportedException e) {
			final String ERR_MSG = "Unable to save timesheet notes.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/*
	 * Set the data for displaying.
	 * 
	 * @param ticketNumber
	 * 
	 * @param panelNumber
	 * 
	 * @param eventId
	 */
	private void renderPanel(String ticketNumber, String panelNumber,
			String eventId, WorkHistory workHistory) {

		view.getTxtTicketNumber().setText(workHistory.getTicketNumber());
		view.getTxtEventId().setText(formatInt(workHistory.getEventId()));
		view.getTxtCounterReading().setText(
				formatInt(workHistory.getCounterReading()));
		view.getTxtTechnician().setText(workHistory.getTechnician());
		view.getTxtTechnicianDescription().setText(
				workHistory.getTechnicianDescription());
		view.getTxtDate().setText(formatSqlDate(workHistory.getArrivalDate()));

		view.getTxtArrivalTime().setText(
				DateUtil.formatTime(workHistory.getArrivalTime()));

		view.getTxtHoursReported().setText(
				formatDouble(workHistory.getHoursReported()));
		((CheckBox) view.getChkMaterialReported()).setValue(workHistory
				.isMaterialReported());
		((CheckBox) view.getChkOthersReported()).setValue(workHistory
				.isOtherReported());
		view.getTxtNumberOfItems().setText(
				formatInt(workHistory.getNumberOfItems()));
	}

	/**
	 * Display data in notepad.
	 * 
	 * @param data
	 *            Text to display
	 */
	private void renderSolutionNotes(Notes notes) {
		solutionNotes.setNotes(notes, NoteType.ADVANCED);
	}

	/**
	 * Display data in notepad.
	 * 
	 * @param data
	 *            Text to display
	 */
	private void renderTimeSheetNotes(Notes notes) {
		timeSheetNotes.setNotes(notes, NoteType.ADVANCED);
	}

	/**
	 * Register listeners to trigger events
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param eventId
	 */
	private void registerListeners(final String ticketNumber,
			final String panelNumber, final String eventId) {

		if (areListenersRegistered) {
			return;
		}

		tabPanelHandler = view.getTabPanel().addSelectionHandler(
				new SelectionHandler<Integer>() {
					public void onSelection(SelectionEvent<Integer> event) {
						getTabContent(ticketNumber, panelNumber, eventId, event
								.getSelectedItem());
					}
				});

		btnCounterHandler = view.getBtnCounter().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						History.newItem(Format.format(
								HistoryConstants.WORK_HISTORY_COUNTER_VALUE,
								ticketNumber, panelNumber, eventId, String
										.valueOf(currTabNumber)));
					}
				});

		btnTimeEntryHandler = view.getBtnTimeEntry().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						History.newItem(Format.format(
								HistoryConstants.TIME_ENTRY_VALUE,
								ticketNumber, panelNumber, eventId, String
										.valueOf(currTabNumber)));
					}
				});

		btnMaterialHandler = view.getBtnMaterial().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						History.newItem(Format.format(
								HistoryConstants.MATERIAL_VALUE, ticketNumber,
								panelNumber, eventId, String
										.valueOf(currTabNumber)));
					}
				});

		btnCompleteHandler = view.getBtnComplete().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						completeScheduledEvent(ticketNumber, eventId,
								panelNumber, String.valueOf(currTabNumber));
					}
				});

		btnArrivalInfoHandler = view.getBtnArrivalInfo().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						History
								.newItem(Format
										.format(
												HistoryConstants.WORK_HISTORY_ARRIVAL_INFO_VALUE,
												ticketNumber, panelNumber,
												eventId, String
														.valueOf(currTabNumber)));
					}
				});

		btnCancelHandler = view.getBtnCancel().addClickHandler(
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

		solutionNotes.addNotesSaveHandler(new NotesSaveHandler() {
			public boolean saveNotes(NotesPage notesPage, int pageNumber) {
				saveNotesRPC(SaveNotesPage.newInstance(ticketNumber,
						NotesType.SOLUTION, notesPage));
				return true;
			}
		});

		timeSheetNotes.addNotesSaveHandler(new NotesSaveHandler() {
			public boolean saveNotes(NotesPage notesPage, int pageNumber) {
				saveNotesRPC(SaveNotesPage.newInstance(ticketNumber,
						NotesType.TIMESHEET, notesPage));
				return true;
			}
		});

		areListenersRegistered = true;
	}

	/**
	 * Clean-up listeners.
	 */
	private void unRegisterListeners() {
		if (!areListenersRegistered) {
			return;
		}

		tabPanelHandler.removeHandler();
		btnCounterHandler.removeHandler();
		btnTimeEntryHandler.removeHandler();
		btnMaterialHandler.removeHandler();
		btnCompleteHandler.removeHandler();
		btnArrivalInfoHandler.removeHandler();
		btnCancelHandler.removeHandler();

		areListenersRegistered = false;
	}

	private void saveNotesRPC(SaveNotesPage action) {
		try {
			ActionServices.App.getInstance().execute(action,
					new SaveNotesPageCallback());
		} catch (ActionNotSupportedException e) {
			final String ERR_MSG = "Unable to save notes.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Call-Back class to get the scheduled panel and render them.
	 * 
	 */
	protected class GetWorkHistoryForPanelCallback extends GotWorkHistory {

		private String ticketNumber;
		private String panelNumber;
		private String eventId;

		public GetWorkHistoryForPanelCallback(String ticketNumber,
				String panelNumber, String eventId) {
			this.ticketNumber = ticketNumber;
			this.panelNumber = panelNumber;
			this.eventId = eventId;
		}

		public void got(WorkHistory workHistory) {
			renderPanel(ticketNumber, panelNumber, eventId, workHistory);
		}
	}

	/**
	 * Callback class to handle the Solution Notes.
	 * 
	 */
	protected class GetSolutionNotesCallback extends GotNotes {

		public GetSolutionNotesCallback() {
		}

		@Override
		public void got(Notes notes) {
			renderSolutionNotes(notes);
		}
	}

	/**
	 * Callback class to handle the TimeSheet Notes.
	 * 
	 */
	protected class GetTimeSheetNotesCallback extends GotNotes {

		public GetTimeSheetNotesCallback() {
		}

		@Override
		public void got(Notes notes) {
			renderTimeSheetNotes(notes);
		}
	}

	/**
	 * Call-Back class to handle the result of saving the counter reading.
	 * 
	 */
	protected class CompleteScheduledEventCallback extends
			CompletedServiceTicketEvent {
		private String ticketNumber;
		private String panelNumber;

		public CompleteScheduledEventCallback(String ticketNumber,
				String panelNumber) {
			this.ticketNumber = ticketNumber;
			this.panelNumber = panelNumber;
		}

		@Override
		public void onFailure(Throwable throwable) {

			super.displayErrorMsg(throwable, txtConsts
					.CompleteScheduleFailure(), new ExtCmsMessageBox());
		}

		/**
		 * 
		 * @param result
		 */
		protected void onSuccess(CompleteServiceTicketEventResponse result) {
			History.newItem(Format.format(
					HistoryConstants.SERVICE_TICKET_VALUE, ticketNumber)
					+ Format.format(HistoryConstants.TAB_PANEL_VALUE,
							panelNumber));
		}
	}

	/**
	 * Callback class to handle the result of saving the notes.
	 * 
	 */
	protected class SaveNotesPageCallback extends SavedNotesPage {

		/**
		 * 
		 * @param result
		 */
		protected void onSuccess(SaveNotesPageResponse result) {
			// TODO: display success
		}

		@Override
		public void onFailure(Throwable throwable) {

			super.displayErrorMsg(throwable, txtConsts.SaveNotesPageFailure(),
					new ExtCmsMessageBox());
		}
	}

}
