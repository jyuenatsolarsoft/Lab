package com.cms.gwt.fs.client.presenter;

import java.util.ArrayList;
import java.util.List;

import com.allen_sauer.gwt.log.client.Log;
import com.cms.gwt.common.client.ExtCmsMessageBox;
import com.cms.gwt.fs.client.HistoryConstants;
import com.cms.gwt.fs.client.event.MaterialAddEvent;
import com.cms.gwt.fs.client.exception.ActionNotSupportedException;
import com.cms.gwt.fs.client.factory.CodeDictionaryFactory;
import com.cms.gwt.fs.client.model.CodeDictionary;
import com.cms.gwt.fs.client.model.StockLocationDictionary;
import com.cms.gwt.fs.client.model.material.LotEntry;
import com.cms.gwt.fs.client.model.material.RecordedMaterial;
import com.cms.gwt.fs.client.model.material.RecordedMaterialEntriesEntry;
import com.cms.gwt.fs.client.model.material.SerialEntry;
import com.cms.gwt.fs.client.model.workHistory.WorkHistory;
import com.cms.gwt.fs.client.rpc.ActionServices;
import com.cms.gwt.fs.client.rpc.action.GetRecordedMaterial;
import com.cms.gwt.fs.client.rpc.action.UpdateRecordedMaterial;
import com.cms.gwt.fs.client.rpc.callback.GotRecordedMaterial;
import com.cms.gwt.fs.client.rpc.callback.UpdatedRecordedMaterial;
import com.cms.gwt.fs.client.util.DateUtil;
import com.cms.gwt.fs.client.util.StringUtil;
import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.inject.Inject;
import com.gwtext.client.data.Record;
import com.gwtext.client.util.Format;

public class MaterialEditPresenterImpl extends BasePresenterImpl implements
		MaterialEditPresenter {

	private final HandlerManager eventBus;
	private final View view;

	private boolean areListenersRegistered;

	private HandlerRegistration btnSaveHandler;
	private HandlerRegistration btnOKHandler;
	private HandlerRegistration btnCancelHandler;

	private HandlerRegistration lstStockLocationHandler;

	private RecordedMaterial currRecordedMaterial;

	/**
	 * Constructor.
	 */
	@Inject
	public MaterialEditPresenterImpl(HandlerManager eventBus, View view) {
		this.eventBus = eventBus;
		this.view = view;

		areListenersRegistered = false;
	}

	/**
	 * {@inheritDoc}
	 */
	public void showMaterial(String ticketNumber, String panelNumber,
			String eventId, String tabNumber, String sequence, String line,
			MaterialEditType materialEditType) {
		view.setType(materialEditType); // set UI
		getMaterial(ticketNumber, panelNumber, eventId, tabNumber, sequence,
				line, materialEditType);
	}

	/**
	 * {@inheritDoc}
	 */
	public void showMaterial(String ticketNumber, String panelNumber,
			String eventId, String tabNumber, String sequence, String line,
			MaterialEditType materialEditType, WorkHistory workHistory,
			RecordedMaterialEntriesEntry recordedMaterialEntry) {
		view.setType(materialEditType);
		renderAddEditPanel(ticketNumber, panelNumber, eventId, tabNumber,
				sequence, line, materialEditType, workHistory,
				recordedMaterialEntry);
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
	 * Make RPC to get data.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param eventId
	 * @param tabNumber
	 * @param sequence
	 * @param line
	 * @param materialEditType
	 */
	private void getMaterial(String ticketNumber, String panelNumber,
			String eventId, String tabNumber, String sequence, String line,
			MaterialEditType materialEditType) {
		// NOTE: for only "Material > Update"
		try {

			if (materialEditType.value == MaterialEditType.UPDATE.value) {

				ActionServices.App.getInstance().execute(

						GetRecordedMaterial.newInstance(ticketNumber, eventId,
								sequence, line),
						new GetMaterialCallback(ticketNumber, panelNumber,
								eventId, tabNumber, sequence, line));
			}
		} catch (ActionNotSupportedException e) {
			final String ERR_MSG = "Unable to retrieve material details.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Display data for add > edit Material panel.
	 * 
	 */
	private void renderAddEditPanel(String ticketNumber, String panelNumber,
			String eventId, String tabNumber, String sequence, String line,
			MaterialEditType materialEditType, WorkHistory workHistory,
			RecordedMaterialEntriesEntry recordedMaterialEntry) {

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
				DateUtil.formatDate(workHistory.getArrivalDate()));
		view.getTxtArrivalTime().setText(workHistory.getArrivalTime());
		view.getTxtSequence().setText(
				formatInt(recordedMaterialEntry.getSequence()));
		view.getTxtSequenceDescription().setText(
				recordedMaterialEntry.getDescription());
		view.getTxtPartNumber().setText(recordedMaterialEntry.getPart());
		view.getTxtPartNumberDescription().setText(
				recordedMaterialEntry.getPartDescription());
		view.getTxtRequired().setText(
				formatDouble(recordedMaterialEntry.getRequiredQuantity()));
		view.getTxtRecorded().setText(
				formatDouble(recordedMaterialEntry.getRecordedQuantity()));
		view.getTxtUnitOfMeasure().setText(
				recordedMaterialEntry.getQuantityUOM());
		((CheckBox) view.getChkCoveredByWarranty())
				.setValue(recordedMaterialEntry.isWarranty());

		// in-it the array
		int lenMaterials = 100, dimMaterials = 3;
		Object[][] materials = new Object[lenMaterials][dimMaterials];
		for (int i = 0; i < lenMaterials; i++) {
			materials[i][0] = String.valueOf(i + 1);
			materials[i][1] = "";
			materials[i][2] = 0;
		}

		if (materialEditType.value == MaterialEditType.EDIT_SERIAL.value) {
			List<SerialEntry> serialNumbers = recordedMaterialEntry
					.getSerialNumbers();
			for (SerialEntry serialNumber : serialNumbers) {
				int num = serialNumber.getSerialEntryNumber() - 1;
				if (num >= 0 && num < lenMaterials) {
					materials[num][0] = String.valueOf(num + 1);
					materials[num][1] = serialNumber.getSerialNumber();
					materials[num][2] = serialNumber.getSerialQuantity();
				}
			}

		} else if (materialEditType.value == MaterialEditType.EDIT_LOT.value) {
			List<LotEntry> lotNumbers = recordedMaterialEntry.getLotNumbers();
			for (LotEntry lotNumber : lotNumbers) {
				int num = lotNumber.getLotEntryNumber() - 1;
				if (num >= 0 && num < lenMaterials) {
					materials[num][0] = String.valueOf(num + 1);
					materials[num][1] = lotNumber.getLotNumber();
					materials[num][2] = lotNumber.getLotQuantity();
				}
			}

		} else if (materialEditType.value == MaterialEditType.EDIT_NONE.value) {
			view.getTxtQuantity().setText(
					StringUtil.formatNumber(formatDouble(recordedMaterialEntry
							.getUsedQuantity()), 4));
		}

		view.initGrid(materials);

		registerAddEditListeners(ticketNumber, panelNumber, eventId, tabNumber,
				sequence, recordedMaterialEntry);
	}

	/**
	 * Register listeners for add > edit Material.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param eventId
	 * @param tabNumber
	 * @param sequence
	 */
	private void registerAddEditListeners(final String ticketNumber,
			final String panelNumber, final String eventId,
			final String tabNumber, final String sequence,
			final RecordedMaterialEntriesEntry recordedMaterialEntry) {

		if (areListenersRegistered) {
			return;
		}

		btnSaveHandler = null;

		btnOKHandler = view.getBtnOK().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				// save and then fire the event
				recordedMaterialEntry.setWarranty(((CheckBox) view
						.getChkCoveredByWarranty()).getValue());

				if (recordedMaterialEntry.isSerialControlled()) {
					List<SerialEntry> serialNumbers = new ArrayList<SerialEntry>();
					Record[] records = view.getGrid().getStore().getRecords();
					for (Record record : records) {
						int num = record.getAsInteger("_");
						String serial = record.getAsString(StringUtil
								.makeId(txtConsts.MatSerialNumber()));
						Double quantity = record.getAsDouble(StringUtil
								.makeId(txtConsts.MatQuantity()));

						if (serial != null && !"".equals(serial.trim())) {
							serialNumbers.add(new SerialEntry(num, serial,
									quantity));
						}
					}
					recordedMaterialEntry.setSerialNumbers(serialNumbers);

				} else if (recordedMaterialEntry.isLotControlled()) {
					List<LotEntry> lotNumbers = new ArrayList<LotEntry>();
					Record[] records = view.getGrid().getStore().getRecords();
					for (Record record : records) {
						int num = record.getAsInteger("_");
						String lot = record.getAsString(StringUtil
								.makeId(txtConsts.MatLotNumber()));
						Double quantity = record.getAsDouble(StringUtil
								.makeId(txtConsts.MatQuantity()));

						if (lot != null && !"".equals(lot.trim())) {
							lotNumbers.add(new LotEntry(num, lot, quantity));
						}
					}
					recordedMaterialEntry.setLotNumbers(lotNumbers);

				} else {
					recordedMaterialEntry.setUsedQuantity(parseDouble(view
							.getTxtQuantity().getText()));

				}

				eventBus.fireEvent(new MaterialAddEvent(ticketNumber,
						panelNumber, eventId, tabNumber, true));
			}
		});

		btnCancelHandler = view.getBtnCancel().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						eventBus.fireEvent(new MaterialAddEvent(ticketNumber,
								panelNumber, eventId, tabNumber, true));
					}
				});

		// Update the when there's a change on stock location
		lstStockLocationHandler = ((ListBox) view.getLstStockLocation())
				.addChangeHandler(new ChangeHandler() {

					public void onChange(ChangeEvent event) {

						ListBox stockLocListBox = ((ListBox) view
								.getLstStockLocation());

						String stockLocCode = stockLocListBox
								.getValue(stockLocListBox.getSelectedIndex());

						// Note that if stock location is null, then bin
						// location will
						// not be available.
						StockLocationDictionary stockLocDictionary = CodeDictionaryFactory
								.getStockLocationDictionary();
						CodeDictionary binLocDictionary = stockLocDictionary
								.getBinLocationDictionary(stockLocCode);

						if (binLocDictionary != null) {
							// Do not select bin location after a change has
							// been made on the location group.
							populateListBox(
									((ListBox) view.getLstBinLocation()),
									binLocDictionary, null);
						} else {
							// reset the listbox if no stock location has been
							// selected.
							((ListBox) view.getLstBinLocation()).clear();
						}
					}
				});

		areListenersRegistered = true;
	}

	public RecordedMaterial getCurrRecordedMaterial() {
		return currRecordedMaterial;
	}

	public void setCurrRecordedMaterial(RecordedMaterial currRecordedMaterial) {
		this.currRecordedMaterial = currRecordedMaterial;
	}

	/**
	 * Display data for update Material panel.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param eventId
	 * @param tabNumber
	 * @param sequence
	 * @param line
	 * @param workHistory
	 * @param material
	 */
	private void renderEditPanel(String ticketNumber, String panelNumber,
			String eventId, String tabNumber, String sequence, String line,
			WorkHistory workHistory, RecordedMaterial material) {

		setCurrRecordedMaterial(material);

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
		view.getTxtArrivalTime().setText(workHistory.getArrivalTime());
		view.getTxtSequence().setText(formatInt(material.getSequence()));
		view.getTxtSequenceDescription().setText(material.getOperation());
		view.getTxtLine().setText(formatInt(material.getLine()));
		view.getTxtPartNumber().setText(material.getPartNumber());
		view.getTxtPartNumberDescription().setText(
				material.getPartDescription());
		view.getTxtQuantity().setText(formatDouble(material.getQuantity()));
		view.getTxtUnitOfMeasure().setText(material.getQuantityUOM());
		((CheckBox) view.getChkCoveredByWarranty()).setValue(material
				.isWarranty());

		// Populate stock and bin location. Note that the bin location list is
		// dependent on the selected stock location.
		StockLocationDictionary stockLocationDictionary = CodeDictionaryFactory
				.getStockLocationDictionary();
		populateListBox(((ListBox) view.getLstStockLocation()),
				stockLocationDictionary, material.getStockLocation(), true);

		// Note that if stock location is null, then bin location will not be
		// available.
		CodeDictionary binLocationDictionary = stockLocationDictionary
				.getBinLocationDictionary(material.getStockLocation());

		if (binLocationDictionary != null) {
			populateListBox(((ListBox) view.getLstBinLocation()),
					binLocationDictionary, material.getBinLocation(), true);
		}

		registerEditListeners(ticketNumber, panelNumber, eventId, tabNumber,
				sequence, line);
	}

	/**
	 * Populate the current material model with the data on the screen.
	 */
	private void populateRecordedMaterial() {
		RecordedMaterial material = getCurrRecordedMaterial();

		material.setQuantity(parseDouble(view.getTxtQuantity().getText()));
		material.setWarranty(((CheckBox) view.getChkCoveredByWarranty())
				.getValue());
		material.setStockLocation(getSelectedValue((ListBox) view
				.getLstStockLocation()));
		material.setBinLocation(getSelectedValue((ListBox) view
				.getLstBinLocation()));
	}

	/**
	 * Update the modified material.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param eventId
	 * @param tabNumber
	 */
	private void updateRecordedMaterial(String ticketNumber,
			String panelNumber, String eventId, String tabNumber) {
		populateRecordedMaterial();

		try {
			ActionServices.App.getInstance().execute(
					UpdateRecordedMaterial
							.newInstance(getCurrRecordedMaterial()),
					new UpdateRecordedMaterialCallback(ticketNumber,
							panelNumber, eventId, tabNumber));
		} catch (ActionNotSupportedException e) {
			final String ERR_MSG = "Unable to update recorded materials.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());			
		}

	}

	/**
	 * Register Listeners for update Material.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param eventId
	 * @param tabNumber
	 * @param sequence
	 * @param line
	 */
	private void registerEditListeners(final String ticketNumber,
			final String panelNumber, final String eventId,
			final String tabNumber, final String sequence, final String line) {

		if (areListenersRegistered) {
			return;
		}

		btnSaveHandler = view.getBtnSave().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				updateRecordedMaterial(ticketNumber, panelNumber, eventId,
						tabNumber);
			}
		});

		btnOKHandler = null;

		btnCancelHandler = view.getBtnCancel().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						History.newItem(Format.format(
								HistoryConstants.MATERIAL_VALUE, ticketNumber,
								panelNumber, eventId, tabNumber));
					}
				});

		// Update the when there's a change on stock location
		lstStockLocationHandler = ((ListBox) view.getLstStockLocation())
				.addChangeHandler(new ChangeHandler() {

					public void onChange(ChangeEvent event) {

						ListBox stockLocListBox = ((ListBox) view
								.getLstStockLocation());

						String stockLocCode = stockLocListBox
								.getValue(stockLocListBox.getSelectedIndex());

						// Note that if stock location is null, then bin
						// location will
						// not be available.
						StockLocationDictionary stockLocDictionary = CodeDictionaryFactory
								.getStockLocationDictionary();
						CodeDictionary binLocDictionary = stockLocDictionary
								.getBinLocationDictionary(stockLocCode);

						if (binLocDictionary != null) {
							// Do not select bin location after a change has
							// been made on the location group.
							populateListBox(
									((ListBox) view.getLstBinLocation()),
									binLocDictionary, null, true);
						} else {
							// reset the listbox if no stock location has been
							// selected.
							((ListBox) view.getLstBinLocation()).clear();
						}
					}
				});

		areListenersRegistered = true;
	}

	/**
	 * Clean-up all registers.
	 */
	private void unRegisterListeners() {
		if (!areListenersRegistered) {
			return;
		}

		if (btnSaveHandler != null) {
			btnSaveHandler.removeHandler();
		}
		if (btnOKHandler != null) {
			btnOKHandler.removeHandler();
		}

		btnCancelHandler.removeHandler();

		lstStockLocationHandler.removeHandler();

		areListenersRegistered = false;
	}

	/**
	 * Call-Back class to get the scheduled panel and render them.
	 * 
	 */
	protected class GetMaterialCallback extends GotRecordedMaterial {

		private String ticketNumber;
		private String panelNumber;
		private String eventId;
		private String tabNumber;
		private String sequence;
		private String line;

		public GetMaterialCallback(String ticketNumber, String panelNumber,
				String eventId, String tabNumber, String sequence, String line) {
			this.ticketNumber = ticketNumber;
			this.panelNumber = panelNumber;
			this.eventId = eventId;
			this.tabNumber = tabNumber;
			this.sequence = sequence;
			this.line = line;
		}

		@Override
		public void got(RecordedMaterial recordedMaterial,
				WorkHistory workHistory) {

			renderEditPanel(ticketNumber, panelNumber, eventId, tabNumber,
					sequence, line, workHistory, recordedMaterial);

		}
	}

	/**
	 * Callback class to handle the update.
	 * 
	 */
	protected class UpdateRecordedMaterialCallback extends
			UpdatedRecordedMaterial {

		private String ticketNumber;
		private String panelNumber;
		private String eventId;
		private String tabNumber;

		public UpdateRecordedMaterialCallback(String ticketNumber,
				String panelNumber, String eventId, String tabNumber) {
			this.ticketNumber = ticketNumber;
			this.panelNumber = panelNumber;
			this.eventId = eventId;
			this.tabNumber = tabNumber;
		}

		@Override
		public void got(RecordedMaterial material) {

			History.newItem(Format.format(HistoryConstants.MATERIAL_VALUE,
					ticketNumber, panelNumber, eventId, tabNumber));
		}

		@Override
		public void onFailure(Throwable throwable) {

			super.displayErrorMsg(throwable, txtConsts
					.UpdateRecordedMaterialFailure(), new ExtCmsMessageBox());

		}
	}

}
