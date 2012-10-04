package com.cms.gwt.fs.client.presenter;

import com.cms.gwt.fs.client.HistoryConstants;
import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.gwtext.client.util.Format;

public class TimeEntryEditPresenterImpl extends BasePresenterImpl implements
		TimeEntryEditPresenter {

	private final View view;

	private boolean areListenersRegistered;

	private HandlerRegistration btnUpdateHandler;
	private HandlerRegistration btnCancelHandler;

	/**
	 * Constructor.
	 */
	@Inject
	public TimeEntryEditPresenterImpl(View view) {
		this.view = view;

		areListenersRegistered = false;
	}

	/**
	 * {@inheritDoc}
	 */
	public void showTimeEntry(String ticketNumber, String panelNumber,
			String eventId, String tabNumber) {
		showTimeEntry(ticketNumber, panelNumber, eventId, tabNumber, "", "",
				false);
	}

	/**
	 * {@inheritDoc}
	 */
	public void showTimeEntry(String ticketNumber, String panelNumber,
			String eventId, String tabNumber, String sequence, String line,
			boolean isAdd) {
		view.setType(isAdd); // set UI
		getTimeEntry(ticketNumber, panelNumber, eventId, tabNumber, sequence,
				line, isAdd);
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
	 * Make RPC to get data for Time Entry Edit panel.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param eventId
	 * @param tabNumber
	 */
	private void getTimeEntry(String ticketNumber, String panelNumber,
			String eventId, String tabNumber, String sequence, String line,
			boolean isAdd) {
		// TODO make RPC
		// depending if its "Time Entry > Add Time > Edit" or
		// "Time Entry > Edit" make different RPC and render differently

		if (isAdd) {
			renderAddEditPanel(ticketNumber, panelNumber, eventId, tabNumber,
					sequence);
		} else {
			renderEditPanel(ticketNumber, panelNumber, eventId, tabNumber,
					sequence, line);
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
	private void renderAddEditPanel(String ticketNumber, String panelNumber,
			String eventId, String tabNumber, String sequence) {
		// TODO display data here
		registerAddEditListeners(ticketNumber, panelNumber, eventId, tabNumber,
				sequence);
	}

	/**
	 * Register listeners.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param eventId
	 * @param tabNumber
	 */
	private void registerAddEditListeners(final String ticketNumber,
			final String panelNumber, final String eventId,
			final String tabNumber, final String sequence) {

		if (areListenersRegistered) {
			return;
		}

		btnUpdateHandler = view.getBtnUpdate().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						// TODO - Update button on implemented.
						Window.alert("TO IMPLEMENT");
					}
				});

		btnCancelHandler = view.getBtnCancel().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						History.newItem(Format.format(
								HistoryConstants.TIME_ENTRY_ADD_VALUE,
								ticketNumber, panelNumber, eventId, tabNumber));
					}
				});

		areListenersRegistered = true;
	}

	/**
	 * Display data.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param eventId
	 * @param tabNumber
	 */
	private void renderEditPanel(String ticketNumber, String panelNumber,
			String eventId, String tabNumber, String sequence, String line) {
		// TODO display data here
		registerEditListeners(ticketNumber, panelNumber, eventId, tabNumber,
				sequence, line);
	}

	/**
	 * Register listeners.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param eventId
	 * @param tabNumber
	 */
	private void registerEditListeners(final String ticketNumber,
			final String panelNumber, final String eventId,
			final String tabNumber, final String sequence, final String line) {

		if (areListenersRegistered) {
			return;
		}

		btnUpdateHandler = view.getBtnUpdate().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						// TODO - update button not implemented.
						Window.alert("TO IMPLEMENT");
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
	 * Clean-up listeners.
	 */
	private void unRegisterListeners() {

		if (!areListenersRegistered) {
			return;
		}

		btnUpdateHandler.removeHandler();
		btnCancelHandler.removeHandler();

		areListenersRegistered = false;

	}
}
