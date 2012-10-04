package com.cms.gwt.fs.client.view.material;

import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.presenter.MaterialAddPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.grid.CellMetadata;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.Renderer;

public class MaterialAddView extends Composite implements
		MaterialAddPresenter.View {

	private final VerticalPanel panel;
	private final VerticalPanel gridContainer;
	private final GridPanel grid;
	private final TextConstants txtConsts;

	private Header header;

	private final HasClickHandlers btnSave;
	private final HasClickHandlers btnEdit;
	private final HasClickHandlers btnAllMaterial;
	private final HasClickHandlers btnCancel;

	private boolean flagInit;

	/**
	 * Constructor.
	 */
	public MaterialAddView() {
		panel = new VerticalPanel();
		panel.setStyleName("Material");
		initWidget(panel);

		gridContainer = new VerticalPanel();
		grid = new GridPanel();

		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		header = new Header();

		btnSave = new Button(txtConsts.Save());
		btnEdit = new Button(txtConsts.Edit());
		btnAllMaterial = new Button(txtConsts.MatAllMaterial());
		btnCancel = new Button(txtConsts.Cancel());

		flagInit = false;

		layout();

		// by-default set read-only
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
	public void clearMaterialGrid() {
		if (flagInit) {
			grid.clear();
		}
		gridContainer.clear();
	}

	/**
	 * {@inheritDoc}
	 */
	public GridPanel getMaterialGrid() {
		return grid;
	}

	/**
	 * {@inheritDoc}
	 */
	public String initMaterialGrid(Object[][] materials) {
		// display name and size
		String[][] metaData = new String[][] {
				{ txtConsts.MatSequence(), "80" },
				{ txtConsts.MatDescription(), "200" },
				{ txtConsts.MatPartNumber(), "100" },
				{ txtConsts.MatDescription() + " 2", "200" },
				{ txtConsts.MatRequired(), "80" },
				{ txtConsts.MatUnitOfMeasure(), "100" },
				{ txtConsts.MatRecorded(), "80" },
				{ txtConsts.MatUsed(), "80" },
				{ txtConsts.MatWarranty(), "80" },
				{ txtConsts.MatLotControlled(), "100" },
				{ txtConsts.MatSerialControlled(), "100" },
				{ txtConsts.MatLine(), "0" } };

		// generate id's
		int metaDataLen = metaData.length;
		String[] metaDataId = new String[metaDataLen];
		for (int i = 0; i < metaDataLen; i++) {
			metaDataId[i] = metaData[i][0].replaceAll("\\s+", "_");
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
		columns[8].setRenderer(new Renderer() {
			public String render(Object value, CellMetadata cellMetadata,
					Record record, int rowIndex, int colNum, Store store) {
				String checked = ("true".equals(value)) ? "checked"
						: "unchecked";
				return "<img class=\"checkbox\" src=\"images/" + checked
						+ ".gif\"/>";
			}
		});
		columns[9].setRenderer(new Renderer() {
			public String render(Object value, CellMetadata cellMetadata,
					Record record, int rowIndex, int colNum, Store store) {
				String checked = ("true".equals(value)) ? "checked"
						: "unchecked";
				return "<img class=\"checkbox\" src=\"images/" + checked
						+ ".gif\"/>";
			}
		});
		columns[10].setRenderer(new Renderer() {
			public String render(Object value, CellMetadata cellMetadata,
					Record record, int rowIndex, int colNum, Store store) {
				String checked = ("true".equals(value)) ? "checked"
						: "unchecked";
				return "<img class=\"checkbox\" src=\"images/" + checked
						+ ".gif\"/>";
			}
		});
		columns[11].setHidden(true);

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
			grid.setWidth("1220px"); // add extra 20px for sorting
			grid.setAutoHeight(true);
			// grid.setTitle(txtConsts.Material());

			Toolbar bottomToolbar = new Toolbar();
			bottomToolbar.addFill();
			bottomToolbar.addButton(new ToolbarButton(txtConsts
					.TicketListClearSort(), new ButtonListenerAdapter() {
				@Override
				public void onClick(com.gwtext.client.widgets.Button button,
						EventObject e) {
					grid.clearSortState(true);
				}
			}));
			grid.setBottomToolbar(bottomToolbar);

			flagInit = true; // grid panel has been initialized
		}

		// this should be done in constructor. but if you try to add grid panel
		// without setting store it will throw IllegalStateException
		gridContainer.add(grid);

		// return the unique identifier for time entry
		return metaDataId[0];
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset() {
		header.reset();
		((Button) btnEdit).setEnabled(false);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setReadOnly(boolean readOnly) {
		boolean enabled = !readOnly;
		header.setReadOnly(readOnly);
		((Button) btnEdit).setEnabled(enabled);
	}

	public HasClickHandlers getBtnAdd() {
		return null;
	}

	public HasClickHandlers getBtnCancel() {
		return btnCancel;
	}

	public HasClickHandlers getBtnDelete() {
		return null;
	}

	public HasClickHandlers getBtnEdit() {
		return btnEdit;
	}

	public HasClickHandlers getBtnUpdate() {
		return null;
	}

	public HasClickHandlers getBtnSave() {
		return btnSave;
	}

	public HasClickHandlers getBtnAllMaterial() {
		return btnAllMaterial;
	}

	public HasText getTxtArrivalDate() {
		return header.getTxtArrivalDate();
	}

	public HasText getTxtArrivalTime() {
		return header.getTxtArrivalTime();
	}

	public HasText getTxtCounterReading() {
		return header.getTxtCounterReading();
	}

	public HasText getTxtCounterReadingDescription() {
		return header.getTxtCounterReadingDescription();
	}

	public HasText getTxtEventId() {
		return header.getTxtEventId();
	}

	public HasText getTxtTechnician() {
		return header.getTxtTechnician();
	}

	public HasText getTxtTechnicianDescription() {
		return header.getTxtTechnicianDescription();
	}

	public HasText getTxtTicketNumber() {
		return header.getTxtTicketNumber();
	}

	/**
	 * Arrange widgets in a nice layout
	 */
	private void layout() {
		panel.add(header.getWidget());
		panel.add(new HTML("&nbsp;"));
		panel.add(gridContainer);

		HorizontalPanel btnPanel = new HorizontalPanel();
		btnPanel.setStyleName("BtnPanel");
		panel.add(btnPanel);

		btnPanel.add((Widget) btnSave);
		btnPanel.add((Widget) btnEdit);
		btnPanel.add((Widget) btnAllMaterial);
		btnPanel.add((Widget) btnCancel);
	}
}
