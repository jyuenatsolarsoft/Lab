package com.cms.gwt.fs.client.presenter;

import java.util.HashMap;
import java.util.Map;

import com.allen_sauer.gwt.log.client.Log;
import com.cms.gwt.common.client.ExtCmsMessageBox;
import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.exception.ActionNotSupportedException;
import com.cms.gwt.fs.client.notes.Notes;
import com.cms.gwt.fs.client.notes.NotesPage;
import com.cms.gwt.fs.client.notes.Notes.NotesType;
import com.cms.gwt.fs.client.presenter.NotesWidgetPresenter.NoteType;
import com.cms.gwt.fs.client.presenter.NotesWidgetPresenter.NotesSaveHandler;
import com.cms.gwt.fs.client.rpc.ActionServices;
import com.cms.gwt.fs.client.rpc.action.GetNotes;
import com.cms.gwt.fs.client.rpc.action.SaveNotesPage;
import com.cms.gwt.fs.client.rpc.actionResponse.SaveNotesPageResponse;
import com.cms.gwt.fs.client.rpc.callback.GotNotes;
import com.cms.gwt.fs.client.rpc.callback.SavedNotesPage;
import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.inject.Inject;

public class NotesPresenterImpl extends BasePresenterImpl implements
		NotesPresenter {

	private final View view;
	private final NotesWidgetPresenter problemNotes;
	private final NotesWidgetPresenter solutionNotes;
	private final NotesWidgetPresenter timeSheetNotes;
	private final TextConstants txtConsts;

	private boolean areListenersRegistered;

	private boolean isInitProblemNotes;
	private boolean isInitSolutionNotes;
	private boolean isInitTimeSheetNotes;

	private HandlerRegistration tabPanelHandler;

	@Inject
	public NotesPresenterImpl(View view, NotesWidgetPresenter problemNotes,
			NotesWidgetPresenter solutionNotes,
			NotesWidgetPresenter timeSheetNotes) {
		this.view = view;
		this.problemNotes = problemNotes;
		this.solutionNotes = solutionNotes;
		this.timeSheetNotes = timeSheetNotes;
		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		// put the notes in the tab
		view.getPanel().add(problemNotes.getView().getWidget(),
				txtConsts.NotesProblem());
		view.getPanel().add(solutionNotes.getView().getWidget(),
				txtConsts.NotesSolution());
		view.getPanel().add(timeSheetNotes.getView().getWidget(),
				txtConsts.NotesTimeSheet());
		// select first tab
		view.getPanel().selectTab(0);

		areListenersRegistered = false;

		isInitProblemNotes = false;
		isInitSolutionNotes = false;
		isInitTimeSheetNotes = false;
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

		isInitProblemNotes = false;
		isInitSolutionNotes = false;
		isInitTimeSheetNotes = false;

		problemNotes.reset();
		solutionNotes.reset();
		timeSheetNotes.reset();

		view.getPanel().selectTab(0);
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
	public void showNotes(String ticketNumber, String panelNumber) {
		registerListeners(ticketNumber, panelNumber);
		getTabContent(ticketNumber, panelNumber, 0);
	}

	/**
	 * Decide which panel to show.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 */
	private void getTabContent(String ticketNumber, String panelNumber,
			int tabNumber) {
		switch (tabNumber) {
		case 0:
			if (!isInitProblemNotes) {
				getProblemNotes(ticketNumber, panelNumber);
			}
			isInitProblemNotes = true;
			break;
		case 1:
			if (!isInitSolutionNotes) {
				getSolutionNotes(ticketNumber, panelNumber);
			}
			isInitSolutionNotes = true;
			break;
		case 2:
			if (!isInitTimeSheetNotes) {
				getTimeSheetNotes(ticketNumber, panelNumber);
			}
			isInitTimeSheetNotes = true;
			break;
		}
	}

	/**
	 * Make RPC to get the Problem Notes.
	 * 
	 * @param ticketNumber
	 * @param pageNumber
	 */
	private void getProblemNotes(String ticketNumber, String panelNumber) {

		try {
			Map<String, String> key = new HashMap<String, String>();

			key
					.put(Notes.NotesType.PROBLEM.getKeyFieldNames()[0],
							ticketNumber);

			ActionServices.App.getInstance().execute(
					GetNotes.newInstance(Notes.NotesType.PROBLEM, key),
					new GetProblemNotesCallback());
		} catch (ActionNotSupportedException e) {
			final String ERR_MSG = "Unable to get the problem notes.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Make RPC to get the Solution Notes.
	 * 
	 * @param ticketNumber
	 * @param pageNumber
	 */
	private void getSolutionNotes(String ticketNumber, String panelNumber) {

		try {
			Map<String, String> key = new HashMap<String, String>();

			key.put(Notes.NotesType.SOLUTION.getKeyFieldNames()[0],
					ticketNumber);

			ActionServices.App.getInstance().execute(
					GetNotes.newInstance(Notes.NotesType.SOLUTION, key),
					new GetSolutionNotesCallback());
		} catch (ActionNotSupportedException e) {
			final String ERR_MSG = "Unable to get the solution notes.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Make RPC to get the Solution Notes.
	 * 
	 * @param ticketNumber
	 * @param pageNumber
	 */
	private void getTimeSheetNotes(String ticketNumber, String panelNumber) {

		try {
			Map<String, String> key = new HashMap<String, String>();

			key.put(Notes.NotesType.TIMESHEET.getKeyFieldNames()[0],
					ticketNumber);

			ActionServices.App.getInstance().execute(
					GetNotes.newInstance(Notes.NotesType.TIMESHEET, key),
					new GetTimeSheetNotesCallback());
		} catch (ActionNotSupportedException e) {
			final String ERR_MSG = "Unable to get the timesheet notes.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Render Problem page.
	 * 
	 * @param ticketNumber
	 *            The ticket number.
	 * @param text
	 *            The text to be rendered.
	 * @param panelNumber
	 *            The panel number.
	 * @param totalPages
	 *            The total nubmer of pages on this notes.
	 * @param pageNumber
	 *            The current page number.
	 */
	private void renderProblemNotes(Notes notes) {
		problemNotes.setNotes(notes, NoteType.ADVANCED);
	}

	/**
	 * Render Solution page.
	 * 
	 * @param ticketNumber
	 *            The ticket number.
	 * @param text
	 *            The text to be rendered.
	 * @param panelNumber
	 *            The panel number.
	 * @param totalPages
	 *            The total nubmer of pages on this notes.
	 * @param pageNumber
	 *            The current page number.
	 */
	private void renderSolutionNotes(Notes notes) {
		solutionNotes.setNotes(notes, NoteType.ADVANCED);
	}

	/**
	 * Render TimeSheet page.
	 * 
	 * @param ticketNumber
	 *            The ticket number.
	 * @param text
	 *            The text to be rendered.
	 * @param panelNumber
	 *            The panel number.
	 * @param totalPages
	 *            The total nubmer of pages on this notes.
	 * @param pageNumber
	 *            The current page number.
	 */
	private void renderTimeSheetNotes(Notes notes) {
		timeSheetNotes.setNotes(notes, NoteType.BASIC);
	}

	/**
	 * Register listeners for the whole parent tab.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 */
	private void registerListeners(final String ticketNumber,
			final String panelNumber) {
		if (areListenersRegistered) {
			return;
		}

		tabPanelHandler = view.getPanel().addBeforeSelectionHandler(
				new BeforeSelectionHandler<Integer>() {
					public void onBeforeSelection(
							BeforeSelectionEvent<Integer> event) {
						getTabContent(ticketNumber, panelNumber, event
								.getItem());
					}
				});

		problemNotes.addNotesSaveHandler(new NotesSaveHandler() {
			public boolean saveNotes(NotesPage notesPage, int pageNumber) {
				saveNotesRPC(SaveNotesPage.newInstance(ticketNumber,
						NotesType.PROBLEM, notesPage));
				return true;
			}
		});

		solutionNotes.addNotesSaveHandler(new NotesSaveHandler() {
			public boolean saveNotes(NotesPage notesPage, int pageNumber) {
				saveNotesRPC(SaveNotesPage.newInstance(ticketNumber,
						NotesType.SOLUTION, notesPage));
				return true;
			}
		});

		areListenersRegistered = true;
	}

	private void unRegisterListeners() {
		if (!areListenersRegistered) {
			return;
		}

		tabPanelHandler.removeHandler();

		areListenersRegistered = false;
	}

	private void saveNotesRPC(SaveNotesPage action) {
		try {
			ActionServices.App.getInstance().execute(action,
					new SaveNotesPageCallback());
		} catch (Exception e) {
			final String ERR_MSG = "Unable to save Notes page.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Callback class to handle the Problem Notes.
	 * 
	 */
	protected class GetProblemNotesCallback extends GotNotes {

		public GetProblemNotesCallback() {
		}

		@Override
		public void got(Notes notes) {
			renderProblemNotes(notes);
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
