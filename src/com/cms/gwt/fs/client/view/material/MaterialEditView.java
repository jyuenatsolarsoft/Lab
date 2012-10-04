package com.cms.gwt.fs.client.view.material;

import com.cms.gwt.common.client.CmsListBox;
import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.presenter.MaterialEditPresenter;
import com.cms.gwt.fs.client.presenter.MaterialEditPresenter.MaterialEditType;
import com.cms.gwt.fs.client.util.StringUtil;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.EditorGridPanel;
import com.gwtext.client.widgets.grid.GridEditor;
import com.gwtext.client.widgets.grid.GridPanel;

public class MaterialEditView extends Composite implements
		MaterialEditPresenter.View {

	private final VerticalPanel panel;
	private final VerticalPanel gridContainer;
	private final TextConstants txtConsts;

	private boolean flagInit;
	private MaterialEditType materialEditType;

	private final HasHTML lblTicketNumber;
	private final HasText txtTicketNumber;
	private final HasHTML lblEventId;
	private final HasText txtEventId;
	private final HasHTML lblCounterReading;
	private final HasText txtCounterReading;
	private final HasText txtCounterReadingDescription;
	private final HasHTML lblTechnician;
	private final HasText txtTechnician;
	private final HasText txtTechnicianDescription;
	private final HasHTML lblArrivalDate;
	private final HasText txtArrivalDate;
	private final HasHTML lblArrivalTime;
	private final HasText txtArrivalTime;

	// Update Material
	private final HasHTML lblSequence;
	private final HasText txtSequence;
	private final HasText txtSequenceDescription;
	private final HasHTML lblLine;
	private final HasText txtLine;
	private final HasHTML lblPartNumber;
	private final HasText txtPartNumber;
	private final HasText txtPartNumberDescription;
	private final HasHTML lblQuantity;
	private final HasText txtQuantity;
	private final HasHTML lblUnitOfMeasure;
	private final HasText txtUnitOfMeasure;
	private final HasHTML lblCoveredByWarranty;
	private final HasName chkCoveredByWarranty;
	private final HasHTML lblStockLocation;
	private final HasName lstStockLocation;
	private final HasHTML lblBinLocation;
	private final HasName lstBinLocation;

	// Add > Edit Material - Serial
	// sequence
	// part
	private final HasHTML lblRequired;
	private final HasText txtRequired;
	// unit of measure
	private final HasHTML lblRecorded;
	private final HasText txtRecorded;
	// covered by warranty
	private final EditorGridPanel grid;

	// Add > Edit Material - Lot
	// same as Serial

	// Add > Edit Material - none
	// sequence
	// part
	// required
	// unit of measure
	// recorded
	// covered by warranty
	// quantity

	private final HasClickHandlers btnSave;
	private final HasClickHandlers btnOK;
	private final HasClickHandlers btnCancel;

	/**
	 * Constructor.
	 */
	public MaterialEditView() {
		panel = new VerticalPanel();
		panel.setStyleName("Material");
		initWidget(panel);

		gridContainer = new VerticalPanel();

		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		flagInit = false;

		lblTicketNumber = new HTML(txtConsts.MatTicketNumber());
		txtTicketNumber = new TextBox();
		lblEventId = new HTML(txtConsts.MatEventId());
		txtEventId = new TextBox();
		lblCounterReading = new HTML(txtConsts.MatCounterReading());
		txtCounterReading = new TextBox();
		txtCounterReadingDescription = new TextBox();
		lblTechnician = new HTML(txtConsts.MatTechnician());
		txtTechnician = new TextBox();
		txtTechnicianDescription = new TextBox();
		lblArrivalDate = new HTML(txtConsts.MatArrivalDate());
		txtArrivalDate = new TextBox();
		lblArrivalTime = new HTML(txtConsts.MatArrivalTime());
		txtArrivalTime = new TextBox();

		// Update Material
		lblSequence = new HTML(txtConsts.MatSequence());
		txtSequence = new TextBox();
		txtSequenceDescription = new TextBox();
		lblLine = new HTML(txtConsts.MatLine());
		txtLine = new TextBox();
		lblPartNumber = new HTML(txtConsts.MatPartNumber());
		txtPartNumber = new TextBox();
		txtPartNumberDescription = new TextBox();
		lblQuantity = new HTML(txtConsts.MatQuantity());
		txtQuantity = new TextBox();
		lblUnitOfMeasure = new HTML(txtConsts.MatUnitOfMeasure());
		txtUnitOfMeasure = new TextBox();
		lblCoveredByWarranty = new HTML(txtConsts.MatCoveredByWarranty());
		chkCoveredByWarranty = new CheckBox();
		lblStockLocation = new HTML(txtConsts.MatStockLocation());
		lstStockLocation = new CmsListBox(false);
		lblBinLocation = new HTML(txtConsts.MatBinLocation());
		lstBinLocation = new CmsListBox(false);

		lblRequired = new HTML(txtConsts.MatRequired());
		txtRequired = new TextBox();
		lblRecorded = new HTML(txtConsts.MatRecorded());
		txtRecorded = new TextBox();
		grid = new EditorGridPanel();

		btnSave = new Button(txtConsts.Save());
		btnOK = new Button(txtConsts.OK());
		btnCancel = new Button(txtConsts.Cancel());

		layout();

		// by-default set some fields read only
		setReadOnly(true);
	}

	/**
	 * {@inheritDoc}
	 */
	public Widget getWidget() {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setType(MaterialEditType newMaterialEditType) {

		materialEditType = newMaterialEditType;

		boolean update = false;
		boolean editSerial = false;
		boolean editLot = false;
		boolean editNone = false;

		if (MaterialEditType.UPDATE.value == materialEditType.value) {
			update = true;

		} else if (MaterialEditType.EDIT_SERIAL.value == materialEditType.value) {
			editSerial = true;

		} else if (MaterialEditType.EDIT_LOT.value == materialEditType.value) {
			editLot = true;

		} else if (MaterialEditType.EDIT_NONE.value == materialEditType.value) {
			editNone = true;

		}

		((HTML) lblLine).setVisible(update);
		((TextBox) txtLine).setVisible(update);
		((HTML) lblQuantity).setVisible(update || editNone);
		((TextBox) txtQuantity).setVisible(update || editNone);
		((HTML) lblStockLocation).setVisible(update);
		((ListBox) lstStockLocation).setVisible(update);
		((HTML) lblBinLocation).setVisible(update);
		((ListBox) lstBinLocation).setVisible(update);
		((HTML) lblRequired).setVisible(editSerial || editLot);
		((TextBox) txtRequired).setVisible(editSerial || editLot);
		((HTML) lblRecorded).setVisible(editSerial || editLot);
		((TextBox) txtRecorded).setVisible(editSerial || editLot);
		gridContainer.setVisible(editSerial || editLot);
		((Button) btnSave).setVisible(update);
		((Button) btnOK).setVisible(editSerial || editLot || editNone);
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset() {
		txtTicketNumber.setText("");
		txtEventId.setText("");
		txtCounterReading.setText("");
		txtCounterReadingDescription.setText("");
		txtTechnician.setText("");
		txtTechnicianDescription.setText("");
		txtArrivalDate.setText("");
		txtArrivalTime.setText("");
		txtSequence.setText("");
		txtSequenceDescription.setText("");
		txtLine.setText("");
		txtPartNumber.setText("");
		txtPartNumberDescription.setText("");
		txtQuantity.setText("");
		txtUnitOfMeasure.setText("");
		((CheckBox) chkCoveredByWarranty).setValue(false);
		((ListBox) lstStockLocation).clear();
		((ListBox) lstBinLocation).clear();
		txtRequired.setText("");
		txtRecorded.setText("");
		clearGrid();
	}

	/**
	 * {@inheritDoc}
	 */
	public void setReadOnly(boolean readOnly) {
		// boolean enabled = !readOnly;
		((TextBox) txtTicketNumber).setReadOnly(readOnly);
		((TextBox) txtEventId).setReadOnly(readOnly);
		((TextBox) txtCounterReading).setReadOnly(readOnly);
		((TextBox) txtCounterReadingDescription).setReadOnly(readOnly);
		((TextBox) txtTechnician).setReadOnly(readOnly);
		((TextBox) txtTechnicianDescription).setReadOnly(readOnly);
		((TextBox) txtArrivalDate).setReadOnly(readOnly);
		((TextBox) txtArrivalTime).setReadOnly(readOnly);
		((TextBox) txtSequence).setReadOnly(readOnly);
		((TextBox) txtSequenceDescription).setReadOnly(readOnly);
		((TextBox) txtLine).setReadOnly(readOnly);
		((TextBox) txtPartNumber).setReadOnly(readOnly);
		((TextBox) txtPartNumberDescription).setReadOnly(readOnly);
		// ((TextBox) txtQuantity).setReadOnly(readOnly);
		((TextBox) txtUnitOfMeasure).setReadOnly(readOnly);
		// ((CheckBox) chkCoveredByWarranty).setEnabled(enabled);
		// ((ListBox) lstStockLocation).setEnabled(enabled);
		// ((ListBox) lstBinLocation).setEnabled(enabled);
		((TextBox) txtRequired).setReadOnly(readOnly);
		((TextBox) txtRecorded).setReadOnly(readOnly);
	}

	/**
	 * {@inheritDoc}
	 */
	public String initGrid(Object[][] materials) {
		// display name and size

		String serialOrLot = "";
		if (materialEditType.value == MaterialEditType.EDIT_SERIAL.value) {
			serialOrLot = txtConsts.MatSerialNumber();
		} else if (materialEditType.value == MaterialEditType.EDIT_LOT.value) {
			serialOrLot = txtConsts.MatLotNumber();
		}

		String[][] metaData = new String[][] { { " ", "30" },
				{ serialOrLot, "200" }, { txtConsts.MatQuantity(), "70" } };

		// generate id's
		int metaDataLen = metaData.length;
		String[] metaDataId = new String[metaDataLen];
		for (int i = 0; i < metaDataLen; i++) {
			metaDataId[i] = StringUtil.makeId(metaData[i][0]);
		}

		// make fields and set to grid
		FieldDef[] fieldDef = new StringFieldDef[metaDataLen];
		for (int i = 0; i < metaDataLen; i++) {
			fieldDef[i] = new StringFieldDef(metaDataId[i]);
		}
		RecordDef recordDef = new RecordDef(fieldDef);
		MemoryProxy proxy = new MemoryProxy(materials);
		ArrayReader reader = new ArrayReader(recordDef);
		Store store = new Store(proxy, reader);

		// make columns and set to grid
		ColumnConfig[] columns = new ColumnConfig[metaDataLen];
		for (int i = 0; i < metaDataLen; i++) {
			columns[i] = new ColumnConfig(metaData[i][0], metaDataId[i],
					Integer.parseInt(metaData[i][1]), true, null, "id_"
							+ metaDataId[i]);
		}

		// custom renderer
		if (materialEditType.value == MaterialEditType.EDIT_SERIAL.value) {
			NumberField nf = new NumberField();
			nf.setAllowDecimals(false);
			nf.setMaxLength(11);
			columns[1].setEditor(new GridEditor(nf));
		} else if (materialEditType.value == MaterialEditType.EDIT_LOT.value) {
			TextField tf = new TextField();
			tf.setMaxLength(15);
			columns[1].setEditor(new GridEditor(tf));
		}

		NumberField nfQuantity = new NumberField();
		nfQuantity.setDecimalPrecision(4);
		columns[2].setEditor(new GridEditor(nfQuantity));

		ColumnModel columnModel = new ColumnModel(columns);

		if (flagInit) {
			// reconfigure the data in grid
			grid.reconfigure(store, columnModel);
			store.reload();

		} else {
			// first time setup
			store.load();
			grid.setStore(store);
			grid.setColumnModel(columnModel);

			// grid.setFrame(true);
			grid.setStripeRows(true);

			// grid.setAutoWidth(true); // does NOT work as expected
			grid.setHeight(250);
			grid.setWidth(325); // add extra 20px for sorting
			// grid.setAutoHeight(true);
			// grid.setTitle(txtConsts.TimeEntry());

			grid.setClicksToEdit(1);

			flagInit = true; // grid panel has been initialized
		}

		// this should be done in constructor. but if you try to add grid panel
		// without setting store it will throw IllegalStateException
		gridContainer.add(new HTML("&nbsp;"));
		gridContainer.add(grid);
		gridContainer.add(new HTML("&nbsp;"));

		// return the unique identifier
		return metaDataId[0];
	}

	public void clearGrid() {
		if (flagInit) {
			grid.clear();
		}
		gridContainer.clear();
	}

	public GridPanel getGrid() {
		return grid;
	}

	public HasText getTxtTicketNumber() {
		return txtTicketNumber;
	}

	public HasText getTxtEventId() {
		return txtEventId;
	}

	public HasText getTxtCounterReading() {
		return txtCounterReading;
	}

	public HasText getTxtCounterReadingDescription() {
		return txtCounterReadingDescription;
	}

	public HasText getTxtTechnician() {
		return txtTechnician;
	}

	public HasText getTxtTechnicianDescription() {
		return txtTechnicianDescription;
	}

	public HasText getTxtArrivalDate() {
		return txtArrivalDate;
	}

	public HasText getTxtArrivalTime() {
		return txtArrivalTime;
	}

	public HasText getTxtSequence() {
		return txtSequence;
	}

	public HasText getTxtSequenceDescription() {
		return txtSequenceDescription;
	}

	public HasText getTxtLine() {
		return txtLine;
	}

	public HasText getTxtPartNumber() {
		return txtPartNumber;
	}

	public HasText getTxtPartNumberDescription() {
		return txtPartNumberDescription;
	}

	public HasText getTxtQuantity() {
		return txtQuantity;
	}

	public HasText getTxtUnitOfMeasure() {
		return txtUnitOfMeasure;
	}

	public HasName getChkCoveredByWarranty() {
		return chkCoveredByWarranty;
	}

	public HasName getLstStockLocation() {
		return lstStockLocation;
	}

	public HasName getLstBinLocation() {
		return lstBinLocation;
	}

	public HasText getTxtRequired() {
		return txtRequired;
	}

	public HasText getTxtRecorded() {
		return txtRecorded;
	}

	public HasClickHandlers getBtnOK() {
		return btnOK;
	}

	public HasClickHandlers getBtnSave() {
		return btnSave;
	}

	public HasClickHandlers getBtnCancel() {
		return btnCancel;
	}

	/**
	 * Arrange widgets in a nice layout
	 */
	private void layout() {
		DecoratorPanel tableContainer = new DecoratorPanel();
		VerticalPanel verticalPanel = new VerticalPanel();
		FlexTable table = new FlexTable();

		panel.add(tableContainer);
		tableContainer.add(verticalPanel);
		verticalPanel.add(table);

		table.getColumnFormatter().setWidth(0, "150px");
		table.getColumnFormatter().setWidth(1, "200px");
		table.getColumnFormatter().setWidth(2, "150px");
		table.getColumnFormatter().setWidth(3, "200px");
		int row = 0, column = 0;
		table.setWidget(row, column, (Widget) lblTicketNumber);
		column++;
		table.setWidget(row, column, (Widget) txtTicketNumber);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblEventId);
		column++;
		table.setWidget(row, column, (Widget) txtEventId);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblCounterReading);
		column++;
		table.setWidget(row, column, (Widget) txtCounterReading);
		column++;
		((TextBox) txtCounterReadingDescription).addStyleName("fsInput-Large");
		table.getFlexCellFormatter().setColSpan(row, column, 2);
		table.setWidget(row, column, (Widget) txtCounterReadingDescription);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblTechnician);
		column++;
		table.setWidget(row, column, (Widget) txtTechnician);
		column++;
		((TextBox) txtTechnicianDescription).addStyleName("fsInput-Large");
		table.getFlexCellFormatter().setColSpan(row, column, 2);
		table.setWidget(row, column, (Widget) txtTechnicianDescription);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblArrivalDate);
		column++;
		table.setWidget(row, column, (Widget) txtArrivalDate);
		column++;
		table.setWidget(row, column, (Widget) lblArrivalTime);
		column++;
		table.setWidget(row, column, (Widget) txtArrivalTime);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblSequence);
		column++;
		table.setWidget(row, column, (Widget) txtSequence);
		column++;
		((TextBox) txtSequenceDescription).addStyleName("fsInput-Large");
		table.getFlexCellFormatter().setColSpan(row, column, 2);
		table.setWidget(row, column, (Widget) txtSequenceDescription);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblLine);
		column++;
		table.setWidget(row, column, (Widget) txtLine);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblPartNumber);
		column++;
		table.setWidget(row, column, (Widget) txtPartNumber);
		column++;
		((TextBox) txtPartNumberDescription).addStyleName("fsInput-Large");
		table.getFlexCellFormatter().setColSpan(row, column, 2);
		table.setWidget(row, column, (Widget) txtPartNumberDescription);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblRequired);
		column++;
		table.setWidget(row, column, (Widget) txtRequired);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblRecorded);
		column++;
		table.setWidget(row, column, (Widget) txtRecorded);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblQuantity);
		column++;
		table.setWidget(row, column, (Widget) txtQuantity);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblUnitOfMeasure);
		column++;
		table.setWidget(row, column, (Widget) txtUnitOfMeasure);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblCoveredByWarranty);
		column++;
		table.setWidget(row, column, (Widget) chkCoveredByWarranty);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblStockLocation);
		column++;
		table.getFlexCellFormatter().setColSpan(row, column, 3);
		table.setWidget(row, column, (Widget) lstStockLocation);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblBinLocation);
		column++;
		table.getFlexCellFormatter().setColSpan(row, column, 3);
		table.setWidget(row, column, (Widget) lstBinLocation);
		column = 0;
		row++;

		verticalPanel.add(gridContainer);

		HorizontalPanel btnPanel = new HorizontalPanel();
		btnPanel.setStyleName("BtnPanel");
		btnPanel.add((Widget) btnSave);
		btnPanel.add((Widget) btnOK);
		btnPanel.add((Widget) btnCancel);
		panel.add(btnPanel);

	}
}
