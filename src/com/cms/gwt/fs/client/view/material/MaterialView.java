package com.cms.gwt.fs.client.view.material;

import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.presenter.MaterialPresenter;
import com.cms.gwt.fs.client.util.StringUtil;
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
import com.gwtext.client.widgets.grid.GridView;
import com.gwtext.client.widgets.grid.Renderer;
import com.gwtext.client.widgets.grid.RowParams;

public class MaterialView extends Composite implements MaterialPresenter.View {

	private final VerticalPanel panel;
	private final VerticalPanel gridContainer;
	private final GridPanel grid;
	private final TextConstants txtConsts;

	private Header header;

	private final HasClickHandlers btnAdd;
	private final HasClickHandlers btnUpdate;
	private final HasClickHandlers btnDelete;
	private final HasClickHandlers btnCancel;

	private boolean flagInit;

	/**
	 * Constructor.
	 */
	public MaterialView() {
		panel = new VerticalPanel();
		panel.setStyleName("Material");
		initWidget(panel);

		gridContainer = new VerticalPanel();
		grid = new GridPanel();

		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		header = new Header();

		btnAdd = new Button(txtConsts.Add());
		btnUpdate = new Button(txtConsts.Update());
		btnDelete = new Button(txtConsts.Delete());
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
	public String initMaterialGrid(Object[][] materials) {
		// display name and size
		String[][] metaData = new String[][] {
				{ txtConsts.MatSequence(), "80" },
				{ txtConsts.MatPartDescription(), "200" },
				{ txtConsts.MatLine(), "80" },
				{ txtConsts.MatPartNumber(), "100" },
				{ txtConsts.MatDescription(), "200" },
				{ txtConsts.MatQuantity(), "80" },
				{ txtConsts.MatWarranty(), "80" },
				{ txtConsts.MatLotNumber(), "80" },
				{ txtConsts.MatSerialNumber(), "80" },
				{ txtConsts.MatIsPosted(), "0" } };

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
		columns[6].setRenderer(new Renderer() {
			public String render(Object value, CellMetadata cellMetadata,
					Record record, int rowIndex, int colNum, Store store) {
				String checked = ("true".equals(value)) ? "checked"
						: "unchecked";
				return "<img class=\"checkbox\" src=\"images/" + checked
						+ ".gif\"/>";
			}
		});

		columns[9].setHidden(true);

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
			grid.setWidth("1000px"); // add extra 20px for sorting
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

			grid.setView(new GridView() {
				public String getRowClass(Record record, int index,
						RowParams rowParams, Store store) {
					String value = record.getAsString(StringUtil
							.makeId(txtConsts.MatIsPosted()));
					if ("true".equalsIgnoreCase(value)) {
						return "Grid-Disabled";
					}
					if (index % 2 == 0) {
						return "Grid-Even";
					}
					return "Grid-Odd";
				}
			});

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
		((Button) btnUpdate).setEnabled(false);
		((Button) btnDelete).setEnabled(false);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setReadOnly(boolean readOnly) {
		boolean enabled = !readOnly;
		header.setReadOnly(readOnly);
		((Button) btnUpdate).setEnabled(enabled);
		((Button) btnDelete).setEnabled(enabled);
	}

	/**
	 * {@inheritDoc}
	 */
	public GridPanel getMaterialGrid() {
		return grid;
	}

	public HasClickHandlers getBtnAdd() {
		return btnAdd;
	}

	public HasClickHandlers getBtnCancel() {
		return btnCancel;
	}

	public HasClickHandlers getBtnDelete() {
		return btnDelete;
	}

	public HasClickHandlers getBtnUpdate() {
		return btnUpdate;
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

		btnPanel.add((Widget) btnAdd);
		btnPanel.add((Widget) btnUpdate);
		btnPanel.add((Widget) btnDelete);
		btnPanel.add((Widget) btnCancel);
	}

}
