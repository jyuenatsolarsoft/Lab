package com.cms.gwt.fs.client.presenter;

import com.allen_sauer.gwt.log.client.Log;
import com.cms.gwt.common.client.ExtCmsMessageBox;
import com.cms.gwt.fs.client.HistoryConstants;
import com.cms.gwt.fs.client.exception.ActionNotSupportedException;
import com.cms.gwt.fs.client.model.workHistory.WorkHistory;
import com.cms.gwt.fs.client.rpc.ActionServices;
import com.cms.gwt.fs.client.rpc.action.GetWorkHistory;
import com.cms.gwt.fs.client.rpc.action.SaveServiceTicketCounter;
import com.cms.gwt.fs.client.rpc.actionResponse.SaveServiceTicketCounterResponse;
import com.cms.gwt.fs.client.rpc.callback.GotWorkHistory;
import com.cms.gwt.fs.client.rpc.callback.SavedServiceTicketCounter;
import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;
import com.google.inject.Inject;
import com.gwtext.client.util.Format;

public class WorkHistoryCounterPresenterImpl extends BasePresenterImpl
		implements WorkHistoryCounterPresenter {

	private final View view;

	private boolean areListenersRegistered;
	private HandlerRegistration btnCancelHandler;
	private HandlerRegistration btnSaveHandler;

	/**
	 * Constructor
	 */
	@Inject
	public WorkHistoryCounterPresenterImpl(View view) {
		this.view = view;

		areListenersRegistered = false;
	}

	/**
	 * {@inheritDoc}
	 */
	public void showWorkHistoryCounter(String ticketNumber, String panelNumber,
			String eventId, String tabNumber) {
		getCounter(ticketNumber, panelNumber, eventId, tabNumber);
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
	 * Make RPC to get specified work history for the counter screen.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param eventId
	 */
	private void getCounter(String ticketNumber, String panelNumber,
			String eventId, String tabNumber) {

		getWorkHistory(ticketNumber, eventId, new GetCounterCallback(
				ticketNumber, panelNumber, eventId, tabNumber));
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
	 * Display data for Work History Counter.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param eventId
	 * @param tabNumber
	 */
	private void renderCounter(String ticketNumber, String panelNumber,
			String eventId, String tabNumber, WorkHistory workHistory) {

		view.getTxtTicketNumber().setText(workHistory.getTicketNumber());
		view.getTxtCounterDescription().setText(
				workHistory.getCounterDescription());
		view.getTxtCounterReading().setText(
				formatInt(workHistory.getCounterReading()));

		registerListeners(ticketNumber, panelNumber, eventId, tabNumber);
	}

	/**
	 * Update the info on the counter page.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param tabNumber
	 * @param workHistory
	 */
	private void saveCounter(String ticketNumber, String panelNumber,
			String eventId, String tabNumber, int counterReading) {
		try {
			ActionServices.App.getInstance().execute(
					SaveServiceTicketCounter.newInstance(ticketNumber, Integer
							.toString(counterReading)),
					new SaveCounterCallback(ticketNumber, panelNumber,
							tabNumber, eventId));
		} catch (ActionNotSupportedException e) {
			final String ERR_MSG = "Unable to save the service ticket counter.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Register Listeners.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param eventId
	 */
	private void registerListeners(final String ticketNumber,
			final String panelNumber, final String eventId,
			final String tabNumber) {

		if (areListenersRegistered) {
			return;
		}

		btnSaveHandler = view.getBtnSave().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				String counterReading = view.getTxtCounterReading().getText();

				if (validateIntField(counterReading, txtConsts
						.WHCounterReading(), false)) {
					saveCounter(ticketNumber, panelNumber, eventId, tabNumber,
							Integer.parseInt(counterReading.trim()));
				}
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
	 * Clean-up.
	 */
	private void unRegisterListeners() {
		if (!areListenersRegistered) {
			return;
		}

		btnSaveHandler.removeHandler();
		btnCancelHandler.removeHandler();

		areListenersRegistered = false;
	}

	/**
	 * Call-Back class to get the counter and render them.
	 * 
	 */
	protected class GetCounterCallback extends GotWorkHistory {

		private String ticketNumber;
		private String panelNumber;
		private String eventId;
		private String tabNumber;

		public GetCounterCallback(String ticketNumber, String panelNumber,
				String eventId, String tabNumber) {
			this.ticketNumber = ticketNumber;
			this.panelNumber = panelNumber;
			this.eventId = eventId;
			this.tabNumber = tabNumber;
		}

		public void got(WorkHistory workHistory) {
			renderCounter(ticketNumber, panelNumber, eventId, tabNumber,
					workHistory);
		}
	}

	/**
	 * Call-Back class to handle the result of saving the counter reading.
	 * 
	 */
	protected class SaveCounterCallback extends SavedServiceTicketCounter {
		private String ticketNumber;
		private String panelNumber;
		private String eventId;
		private String tabNumber;

		public SaveCounterCallback(String ticketNumber, String panelNumber,
				String tabNumber, String eventId) {
			this.ticketNumber = ticketNumber;
			this.panelNumber = panelNumber;
			this.eventId = eventId;
			this.tabNumber = tabNumber;
		}

		@Override
		public void onFailure(Throwable throwable) {

			super.displayErrorMsg(throwable, txtConsts.SaveWHCounterFailure(),
					new ExtCmsMessageBox());
		}

		/**
		 * 
		 * @param result
		 */
		protected void onSuccess(SaveServiceTicketCounterResponse result) {
			History.newItem(Format.format(
					HistoryConstants.WORK_HISTORY_PANEL_VALUE, ticketNumber,
					panelNumber, eventId, tabNumber));
		}
	}

}
