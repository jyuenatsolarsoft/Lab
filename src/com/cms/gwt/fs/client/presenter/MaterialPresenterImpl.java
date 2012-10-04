package com.cms.gwt.fs.client.presenter;

import com.allen_sauer.gwt.log.client.Log;
import com.cms.gwt.common.client.ExtCmsMessageBox;
import com.cms.gwt.common.client.CmsMessageBox.CmsConfirmCallback;
import com.cms.gwt.fs.client.HistoryConstants;
import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.event.MaterialAddEvent;
import com.cms.gwt.fs.client.event.MaterialEditEvent;
import com.cms.gwt.fs.client.exception.ActionNotSupportedException;
import com.cms.gwt.fs.client.model.ListenerInfo;
import com.cms.gwt.fs.client.model.material.RecordedMaterialList;
import com.cms.gwt.fs.client.model.material.RecordedMaterialListEntry;
import com.cms.gwt.fs.client.model.workHistory.WorkHistory;
import com.cms.gwt.fs.client.presenter.MaterialEditPresenter.MaterialEditType;
import com.cms.gwt.fs.client.rpc.ActionServices;
import com.cms.gwt.fs.client.rpc.action.DeleteRecordedMaterialListEntry;
import com.cms.gwt.fs.client.rpc.action.GetRecordedMaterialList;
import com.cms.gwt.fs.client.rpc.actionResponse.DeleteRecordedMaterialListEntryResponse;
import com.cms.gwt.fs.client.rpc.callback.DeletedRecordedMaterialListEntry;
import com.cms.gwt.fs.client.rpc.callback.GotRecordedMaterialList;
import com.cms.gwt.fs.client.util.DateUtil;
import com.cms.gwt.fs.client.util.StringUtil;
import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Button;
import com.google.inject.Inject;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.Record;
import com.gwtext.client.util.Format;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.event.GridRowListener;

public class MaterialPresenterImpl extends BasePresenterImpl implements
		MaterialPresenter {

	private final HandlerManager eventBus;
	private final View view;
	private final TextConstants txtConsts;

	private boolean areListenersRegistered;
	private boolean areGridListenersRegistered;

	private HandlerRegistration btnAddHandler;
	private HandlerRegistration btnUpdateHandler;
	private HandlerRegistration btnDeleteHandler;
	private HandlerRegistration btnCancelHandler;

	private ListenerInfo listenerInfo;

	/**
	 * Constructor.
	 */
	@Inject
	public MaterialPresenterImpl(HandlerManager eventBus, View view) {
		this.eventBus = eventBus;
		this.view = view;

		areListenersRegistered = false;
		areGridListenersRegistered = false;

		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		listenerInfo = new ListenerInfo();
	}

	/**
	 * {@inheritDoc}
	 */
	public void showMaterial(String ticketNumber, String panelNumber,
			String eventId, String tabNumber) {
		getMaterial(ticketNumber, panelNumber, eventId, tabNumber);
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
		view.clearMaterialGrid();

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
	private void getMaterial(String ticketNumber, String panelNumber,
			String eventId, String tabNumber) {

		try {
			ActionServices.App.getInstance().execute(
					GetRecordedMaterialList.newInstance(ticketNumber, eventId),
					new GetMaterialCallback(ticketNumber, panelNumber, eventId,
							tabNumber));
		} catch (ActionNotSupportedException e) {
			final String ERR_MSG = "Unable to get recorded material list.<br>";
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
	 * @param workHistory
	 */
	private void renderPanel(String ticketNumber, String panelNumber,
			String eventId, String tabNumber, WorkHistory workHistory) {

		view.getTxtTicketNumber().setText(workHistory.getTicketNumber());
		view.getTxtEventId().setText(formatInt(workHistory.getEventId()));
		view.getTxtCounterReading().setText(
				formatInt(workHistory.getCounterReading()));
		view.getTxtCounterReadingDescription().setText(
				workHistory.getCounterDescription());
		view.getTxtTechnician().setText(workHistory.getTechnician());
		view.getTxtTechnicianDescription().setText(
				workHistory.getTechnicianDescription());
		view.getTxtArrivalDate().setText(
				formatSqlDate(workHistory.getArrivalDate()));
		view.getTxtArrivalTime().setText(
				DateUtil.formatTime(workHistory.getArrivalTime()));

		registerPanelListeners(ticketNumber, panelNumber, eventId, tabNumber);
	}

	/**
	 * Show the grid data.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param eventId
	 * @param tabNumber
	 * @param materialList
	 */
	private void renderGrid(String ticketNumber, String panelNumber,
			String eventId, String tabNumber, RecordedMaterialList materialList) {

		Object[][] materialData = new Object[materialList.getEntries().size()][];

		int i = 0;
		for (RecordedMaterialListEntry entry : materialList.getEntries()) {
			materialData[i++] = new Object[] { entry.getSequence(),
					entry.getDescription(), entry.getLine(),
					entry.getPartNumber(), entry.getPartDescription(),
					entry.getQuantity(), entry.isWarranty(),
					entry.getLotNumber(), entry.getSerialNumber(),
					entry.isPosted() };
		}

		view.initMaterialGrid(materialData);

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

		btnAddHandler = view.getBtnAdd().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new MaterialAddEvent(ticketNumber,
						panelNumber, eventId, tabNumber, false));
			}
		});

		btnUpdateHandler = view.getBtnUpdate().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						Record selected = view.getMaterialGrid()
								.getSelectionModel().getSelected();
						if (selected == null) {
							return;
						}
						String sequence = selected.getAsString(txtConsts
								.MatSequence());
						String line = selected.getAsString(txtConsts.MatLine());
						eventBus.fireEvent(new MaterialEditEvent(ticketNumber,
								panelNumber, eventId, tabNumber, sequence,
								line, MaterialEditType.UPDATE));
					}
				});

		btnDeleteHandler = view.getBtnDelete().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						Record selected = view.getMaterialGrid()
								.getSelectionModel().getSelected();
						if (selected == null) {
							return;
						}
						String sequence = selected.getAsString(txtConsts
								.MatSequence());
						String line = selected.getAsString(txtConsts.MatLine());

						ExtCmsMessageBox box = new ExtCmsMessageBox();
						box.confirm(txtConsts.Delete(), txtConsts.Confirm(
								txtConsts.Delete(), txtConsts.Material(),
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

		view.getMaterialGrid().addGridRowListener(new GridRowListener() {

			public void onRowClick(GridPanel grid, int rowIndex, EventObject e) {
				updateButtons(grid);
			}

			public void onRowContextMenu(GridPanel grid, int rowIndex,
					EventObject e) {
				// nothing
			}

			public void onRowDblClick(GridPanel grid, int rowIndex,
					EventObject e) {
				updateButtons(grid);
				gridDblClick(grid);
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

		btnAddHandler.removeHandler();
		btnUpdateHandler.removeHandler();
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
	private void updateButtons(GridPanel grid) {
		boolean enabled = isRowEditable(grid);

		((Button) view.getBtnUpdate()).setEnabled(enabled);
		((Button) view.getBtnDelete()).setEnabled(enabled);
	}

	/**
	 * Handle double-click on grid.
	 * 
	 * @param grid
	 */
	private void gridDblClick(GridPanel grid) {
		if (!isRowEditable(grid)) {
			return;
		}

		Record selected = grid.getSelectionModel().getSelected();
		String sequence = selected.getAsString(txtConsts.MatSequence());
		String line = selected.getAsString(txtConsts.MatLine());
		eventBus.fireEvent(new MaterialEditEvent(
				listenerInfo.getTicketNumber(), listenerInfo.getPanelNumber(),
				listenerInfo.getEventId(), listenerInfo.getTabNumber(),
				sequence, line, MaterialEditType.UPDATE));
	}

	/**
	 * Checks if the selected row is edit-able or not.
	 * 
	 * @param grid
	 * @return
	 */
	private boolean isRowEditable(GridPanel grid) {
		boolean rval = false;
		Record selected = grid.getSelectionModel().getSelected();
		if (selected == null) {
			return rval;
		}

		// if NOT posted then editable
		rval = "false".equalsIgnoreCase(selected.getAsString(StringUtil
				.makeId(txtConsts.MatIsPosted())));
		return rval;
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

			deleteRecordedMaterial(ticketNumber, eventId, sequence, line,
					panelNumber, tabNumber);
		}
	}

	/**
	 * delete the recorded material
	 * 
	 * @param ticketNumber
	 * @param eventId
	 * @param sequence
	 * @param line
	 * @param panelNumber
	 * @param tabNumber
	 */
	private void deleteRecordedMaterial(String ticketNumber, String eventId,
			String sequence, String line, String panelNumber, String tabNumber) {
		try {
			ActionServices.App.getInstance().execute(
					DeleteRecordedMaterialListEntry.newInstance(ticketNumber,
							eventId, sequence, line),
					new DeleteRecordedMaterialCallback(ticketNumber, eventId,
							panelNumber, tabNumber));
		} catch (ActionNotSupportedException e) {
			final String ERR_MSG = "Unable to delete recorded material.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Call-Back class to handle the recorded labour list and the work history.
	 * 
	 */
	protected class GetMaterialCallback extends GotRecordedMaterialList {

		private String ticketNumber;
		private String panelNumber;
		private String eventId;
		private String tabNumber;

		public GetMaterialCallback(String ticketNumber, String panelNumber,
				String eventId, String tabNumber) {
			this.ticketNumber = ticketNumber;
			this.panelNumber = panelNumber;
			this.eventId = eventId;
			this.tabNumber = tabNumber;
		}

		@Override
		public void got(RecordedMaterialList materialList,
				WorkHistory workHistory) {
			renderPanel(ticketNumber, panelNumber, eventId, tabNumber,
					workHistory);
			renderGrid(ticketNumber, panelNumber, eventId, tabNumber,
					materialList);

		}
	}

	/**
	 * Callback class to handle the result of the deletion.
	 * 
	 */
	protected class DeleteRecordedMaterialCallback extends
			DeletedRecordedMaterialListEntry {

		private String ticketNumber;
		private String panelNumber;
		private String eventId;
		private String tabNumber;

		public DeleteRecordedMaterialCallback(String ticketNumber,
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
		protected void onSuccess(DeleteRecordedMaterialListEntryResponse result) {

			showNotifAndWarning(result, txtConsts
					.DeleteRecordedMaterialListEntryNotif());

			reset();
			showMaterial(ticketNumber, panelNumber, eventId, tabNumber);
		}

		@Override
		public void onFailure(Throwable throwable) {

			super.displayErrorMsg(throwable, txtConsts
					.DeleteRecordedMaterialListEntryFailure(),
					new ExtCmsMessageBox());
		}
	}

}
