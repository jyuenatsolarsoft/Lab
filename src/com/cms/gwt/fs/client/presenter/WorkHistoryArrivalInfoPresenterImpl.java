package com.cms.gwt.fs.client.presenter;

import java.sql.Date;

import com.allen_sauer.gwt.log.client.Log;
import com.cms.gwt.common.client.ExtCmsMessageBox;
import com.cms.gwt.fs.client.HistoryConstants;
import com.cms.gwt.fs.client.exception.ActionNotSupportedException;
import com.cms.gwt.fs.client.model.workHistory.WorkHistory;
import com.cms.gwt.fs.client.rpc.ActionServices;
import com.cms.gwt.fs.client.rpc.action.GetWorkHistory;
import com.cms.gwt.fs.client.rpc.action.SaveServiceTicketArrival;
import com.cms.gwt.fs.client.rpc.actionResponse.SaveServiceTicketArrivalResponse;
import com.cms.gwt.fs.client.rpc.callback.GotWorkHistory;
import com.cms.gwt.fs.client.rpc.callback.SavedServiceTicketArrival;
import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;
import com.google.inject.Inject;
import com.gwtext.client.util.Format;

public class WorkHistoryArrivalInfoPresenterImpl extends BasePresenterImpl
		implements WorkHistoryArrivalInfoPresenter {

	private final View view;

	private boolean areListenersRegistered;
	private HandlerRegistration btnCancelHandler;
	private HandlerRegistration btnSaveHandler;

	/**
	 * Constructor.
	 */
	@Inject
	public WorkHistoryArrivalInfoPresenterImpl(View view) {
		this.view = view;

		areListenersRegistered = false;
	}

	/**
	 * {@inheritDoc}
	 */
	public void showWorkHistoryArrivalInfo(String ticketNumber,
			String panelNumber, String eventId, String tabNumber) {
		getArrivalInfo(ticketNumber, eventId, new GetArrivalInfoCallback(
				ticketNumber, panelNumber, eventId, tabNumber));
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
	 * Make RPC to get specified work history.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param eventId
	 */
	private void getArrivalInfo(String ticketNumber, String eventId,
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
	 * Display data.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param eventId
	 * @param tabNumber
	 */
	private void renderArrivalInfo(String ticketNumber, String panelNumber,
			String eventId, String tabNumber, WorkHistory workHistory) {

		view.getTxtTicketNumber().setText(ticketNumber);
		view.getTxtEventId().setText(eventId);

		if (workHistory != null && workHistory.getArrivalDate() != null) {
			// Populate the time and date with the info in the current work
			// history.
			view.getDfArrivalDate().setValue(workHistory.getArrivalDate());

			String arrivalTime = workHistory.getArrivalTime();
			if (arrivalTime != null) {
				String[] time = arrivalTime.split("\\.", 3);
				view.getTxtArrivalTimeHour().setText(time[0]);
				view.getTxtArrivalTimeMinute().setText(time[1]);
			}
		} else {
			// Populate the date and time with the current system time.
			// arrivalInfoView.getDfArrivalDate().setValue(new
			// java.util.Date());
			// arrivalInfoView.getTxtArrivalTimeHour().setText(
			// formatInt(new java.util.Date().getHours() - 1));
			// arrivalInfoView.getTxtArrivalTimeMinute().setText(
			// formatInt(new java.util.Date().getMinutes()));
		}

		registerListeners(ticketNumber, panelNumber, eventId, tabNumber);
	}

	private void registerListeners(final String ticketNumber,
			final String panelNumber, final String eventId,
			final String tabNumber) {

		if (areListenersRegistered) {
			return;
		}

		btnSaveHandler = view.getBtnSave().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				Date date = getSqlDate(view.getDfArrivalDate());
				String hour = view.getTxtArrivalTimeHour().getText();
				String minute = view.getTxtArrivalTimeMinute().getText();

				if (minute.length() == 1) {
					// Add preceding 0 to the minute if it's single
					// digit. Otherwise, the server will complain.
					minute = "0" + minute;
				}

				saveArrivalInfo(ticketNumber, panelNumber, tabNumber, eventId,
						date, hour + "." + minute + ".00");
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
	 * Save the Arrival information.
	 * 
	 * @param ticketNumber
	 * @param eventId
	 * @param date
	 * @param time
	 */
	private void saveArrivalInfo(String ticketNumber, String panelNumber,
			String tabNumber, String eventId, Date date, String timeArrived) {
		try {
			ActionServices.App.getInstance().execute(
					SaveServiceTicketArrival.newInstance(ticketNumber, eventId,
							date, timeArrived),
					new SaveArrivalInfoCallback(ticketNumber, panelNumber,
							tabNumber, eventId));
		} catch (ActionNotSupportedException e) {
			final String ERR_MSG = "Unable to get the ticket arrival info.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Call-Back class to get the counter and render them.
	 * 
	 */
	protected class GetArrivalInfoCallback extends GotWorkHistory {

		private String ticketNumber;
		private String panelNumber;
		private String eventId;
		private String tabNumber;

		public GetArrivalInfoCallback(String ticketNumber, String panelNumber,
				String eventId, String tabNumber) {
			this.ticketNumber = ticketNumber;
			this.panelNumber = panelNumber;
			this.eventId = eventId;
			this.tabNumber = tabNumber;
		}

		public void got(WorkHistory workHistory) {
			renderArrivalInfo(ticketNumber, panelNumber, eventId, tabNumber,
					workHistory);
		}
	}

	/**
	 * Call-Back class to handle the result of saving the counter reading.
	 * 
	 */
	protected class SaveArrivalInfoCallback extends SavedServiceTicketArrival {

		private String ticketNumber;
		private String panelNumber;
		private String eventId;
		private String tabNumber;

		public SaveArrivalInfoCallback(String ticketNumber, String panelNumber,
				String tabNumber, String eventId) {
			this.ticketNumber = ticketNumber;
			this.panelNumber = panelNumber;
			this.eventId = eventId;
			this.tabNumber = tabNumber;
		}

		public void onFailure(Throwable throwable) {

			super.displayErrorMsg(throwable,
					txtConsts.SaveArrivalInfoFailure(), new ExtCmsMessageBox());
		}

		/**
		 * 
		 * @param result
		 */
		protected void onSuccess(SaveServiceTicketArrivalResponse result) {
			History.newItem(Format.format(
					HistoryConstants.WORK_HISTORY_PANEL_VALUE, ticketNumber,
					panelNumber, eventId, tabNumber));
		}
	}

}
