package com.cms.gwt.fs.client.presenter;

import com.allen_sauer.gwt.log.client.Log;
import com.cms.gwt.common.client.ExtCmsMessageBox;
import com.cms.gwt.fs.client.HistoryConstants;
import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.event.MaterialEditEvent;
import com.cms.gwt.fs.client.exception.ActionNotSupportedException;
import com.cms.gwt.fs.client.model.ListenerInfo;
import com.cms.gwt.fs.client.model.material.RecordedMaterialEntries;
import com.cms.gwt.fs.client.model.material.RecordedMaterialEntriesEntry;
import com.cms.gwt.fs.client.model.workHistory.WorkHistory;
import com.cms.gwt.fs.client.presenter.MaterialEditPresenter.MaterialEditType;
import com.cms.gwt.fs.client.rpc.ActionServices;
import com.cms.gwt.fs.client.rpc.action.AddRecordedMaterials;
import com.cms.gwt.fs.client.rpc.action.GetRecordedMaterialEntries;
import com.cms.gwt.fs.client.rpc.actionResponse.AddRecordedMaterialsResponse;
import com.cms.gwt.fs.client.rpc.callback.AddedRecordedMaterials;
import com.cms.gwt.fs.client.rpc.callback.GotRecordedMaterialEntries;
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

public class MaterialAddPresenterImpl extends BasePresenterImpl implements
		MaterialAddPresenter {

	private final HandlerManager eventBus;
	private final View view;
	private final TextConstants txtConsts;

	private final ListenerInfo listenerInfo;

	private boolean areListenersRegistered;
	private boolean areGridListenersRegistered;

	private HandlerRegistration btnSaveHandler;
	private HandlerRegistration btnEditHandler;
	private HandlerRegistration btnAllMaterialHandler;
	private HandlerRegistration btnCancelHandler;

	private WorkHistory workHistory;
	private RecordedMaterialEntries recordedMaterialEntries;

	/**
	 * Constructor.
	 */
	@Inject
	public MaterialAddPresenterImpl(HandlerManager eventBus, View view) {
		this.eventBus = eventBus;
		this.view = view;

		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		listenerInfo = new ListenerInfo();

		areListenersRegistered = false;
		areGridListenersRegistered = false;

	}

	/**
	 * {@inheritDoc}
	 */
	public void showMaterial(String ticketNumber, String panelNumber,
			String eventId, String tabNumber) {
		showMaterial(ticketNumber, panelNumber, eventId, tabNumber, false);
	}

	/**
	 * {@inheritDoc}
	 */
	public void showMaterial(String ticketNumber, String panelNumber,
			String eventId, String tabNumber, boolean isEdit) {

		if (isEdit) {
			// coming from Edit panel. Just update the Grid.
			renderPanel(ticketNumber, panelNumber, eventId, tabNumber,
					workHistory);
			resetGrid();
			renderGrid(ticketNumber, panelNumber, eventId, tabNumber,
					recordedMaterialEntries);

		} else {
			getMaterial(ticketNumber, panelNumber, eventId, tabNumber);
		}
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
		resetGrid();

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
					GetRecordedMaterialEntries.newInstance(ticketNumber,
							eventId),
					new GetMaterialCallback(ticketNumber, panelNumber, eventId,
							tabNumber));
		} catch (ActionNotSupportedException e) {
			final String ERR_MSG = "Unable to get recorded material entries.<br>";
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

		this.workHistory = workHistory;

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
	 * Display grid data.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param eventId
	 * @param tabNumber
	 * @param materialEntries
	 */
	private void renderGrid(String ticketNumber, String panelNumber,
			String eventId, String tabNumber, RecordedMaterialEntries entries) {

		recordedMaterialEntries = entries;
		Object[][] materialData = new Object[entries.getEntries().size()][];

		int i = 0;
		for (RecordedMaterialEntriesEntry entry : entries.getEntries()) {
			materialData[i++] = new Object[] { entry.getSequence(),
					entry.getDescription(), entry.getPart(),
					entry.getPartDescription(), entry.getRequiredQuantity(),
					entry.getQuantityUOM(), entry.getRecordedQuantity(),
					entry.getUsedQuantity(), entry.isWarranty(),

					// TODO: fix this
					entry.isLotControlled(), // log control
					entry.isSerialControlled(), // serial control
					entry.getLine() };
		}

		view.initMaterialGrid(materialData);

		registerGridListeners(ticketNumber, panelNumber, eventId, tabNumber);
	}

	private void resetGrid() {
		unRegisterGridListeners();
		view.clearMaterialGrid();
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

				saveMaterialEntries(ticketNumber, eventId, panelNumber,
						tabNumber);
			}
		});

		btnEditHandler = view.getBtnEdit().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				gridDblClick(view.getMaterialGrid());
			}
		});

		btnAllMaterialHandler = view.getBtnAllMaterial().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {

						autoFillMaterialsUsed(recordedMaterialEntries);

						// now refresh the grid.
						renderGrid(ticketNumber, panelNumber, eventId,
								tabNumber, recordedMaterialEntries);
					}
				});

		btnCancelHandler = view.getBtnCancel().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						History.newItem(Format.format(
								HistoryConstants.MATERIAL_VALUE, ticketNumber,
								panelNumber, eventId, tabNumber));
					}
				});

		areListenersRegistered = true;
	}

	/**
	 * Add the current material entries to the backend.
	 * 
	 * @param ticketNumber
	 * @param eventId
	 * @param panelNumber
	 * @param tabNumber
	 */
	private void saveMaterialEntries(String ticketNumber, String eventId,
			String panelNumber, String tabNumber) {
		try {
			ActionServices.App.getInstance().execute(
					AddRecordedMaterials.newInstance(recordedMaterialEntries),
					new SaveMaterialEntriesCallback(ticketNumber, eventId,
							panelNumber, tabNumber));
		} catch (ActionNotSupportedException e) {
			final String ERR_MSG = "Unable to add recorded materials.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Automatically fill in the used quantities of the material entries.
	 * 
	 * @param materials
	 *            The material entries to be updated.
	 * 
	 */
	private void autoFillMaterialsUsed(RecordedMaterialEntries materials) {
		if (materials == null) {
			throw new IllegalArgumentException("materials cannot be null.");
		}

		for (RecordedMaterialEntriesEntry entry : materials.getEntries()) {
			if (!entry.isLotControlled() && !entry.isSerialControlled()) {
				entry.setUsedQuantity(entry.getRequiredQuantity()
						- entry.getRecordedQuantity());
			}
		}
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

		btnSaveHandler.removeHandler();
		btnEditHandler.removeHandler();
		btnAllMaterialHandler.removeHandler();
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

	private void updateButtons(GridPanel grid) {
		boolean enabled = isRowEditable(grid);
		((Button) view.getBtnEdit()).setEnabled(enabled);
	}

	private void gridDblClick(GridPanel grid) {
		if (!isRowEditable(grid)) {
			return;
		}

		Record selected = view.getMaterialGrid().getSelectionModel()
				.getSelected();
		if (selected == null) {
			return;
		}
		String sequence = selected.getAsString(StringUtil.makeId(txtConsts
				.MatSequence()));
		String line = selected.getAsString(StringUtil.makeId(txtConsts
				.MatLine()));

		int iSequence = -1, iLine = -1;
		try {
			iSequence = Integer.parseInt(sequence);
			iLine = Integer.parseInt(line);
		} catch (NumberFormatException e) {
		}

		RecordedMaterialEntriesEntry recordedMaterialEntriesEntry = null;
		for (RecordedMaterialEntriesEntry entry : recordedMaterialEntries
				.getEntries()) {
			if (entry.getSequence() == iSequence && entry.getLine() == iLine) {
				recordedMaterialEntriesEntry = entry;
				break;
			}
		}

		boolean isEditSerial = "true"
				.equalsIgnoreCase(selected.getAsString(StringUtil
						.makeId(txtConsts.MatSerialControlled())));
		boolean isEditLot = "true".equalsIgnoreCase(selected
				.getAsString(StringUtil.makeId(txtConsts.MatLotControlled())));

		if (isEditSerial) {
			eventBus.fireEvent(new MaterialEditEvent(listenerInfo
					.getTicketNumber(), listenerInfo.getPanelNumber(),
					listenerInfo.getEventId(), listenerInfo.getTabNumber(),
					sequence, line, MaterialEditType.EDIT_SERIAL, workHistory,
					recordedMaterialEntriesEntry));

		} else if (isEditLot) {
			eventBus.fireEvent(new MaterialEditEvent(listenerInfo
					.getTicketNumber(), listenerInfo.getPanelNumber(),
					listenerInfo.getEventId(), listenerInfo.getTabNumber(),
					sequence, line, MaterialEditType.EDIT_LOT, workHistory,
					recordedMaterialEntriesEntry));

		} else {
			eventBus.fireEvent(new MaterialEditEvent(listenerInfo
					.getTicketNumber(), listenerInfo.getPanelNumber(),
					listenerInfo.getEventId(), listenerInfo.getTabNumber(),
					sequence, line, MaterialEditType.EDIT_NONE, workHistory,
					recordedMaterialEntriesEntry));

		}
	}

	private boolean isRowEditable(GridPanel grid) {
		boolean rval = false;
		Record selected = grid.getSelectionModel().getSelected();
		rval = (selected != null);
		return rval;
	}

	/**
	 * Call-Back class to handle the recorded labour list and the work history.
	 * 
	 */
	protected class GetMaterialCallback extends GotRecordedMaterialEntries {

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
		public void got(RecordedMaterialEntries materialEntries,
				WorkHistory workHistory) {
			renderPanel(ticketNumber, panelNumber, eventId, tabNumber,
					workHistory);
			renderGrid(ticketNumber, panelNumber, eventId, tabNumber,
					materialEntries);

		}
	}

	/**
	 * Callback class to handle the result of saving the new materials.
	 * 
	 */
	protected class SaveMaterialEntriesCallback extends AddedRecordedMaterials {

		private String ticketNumber;
		private String panelNumber;
		private String eventId;
		private String tabNumber;

		public SaveMaterialEntriesCallback(String ticketNumber, String eventId,
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
		protected void onSuccess(AddRecordedMaterialsResponse result) {

			showWarning(result);

			History.newItem(Format.format(HistoryConstants.MATERIAL_VALUE,
					ticketNumber, panelNumber, eventId, tabNumber));
		}

		public void onFailure(Throwable throwable) {

			super.displayErrorMsg(throwable, txtConsts
					.SaveRecordedMaterialFailure(), new ExtCmsMessageBox());
		}

	}

}
