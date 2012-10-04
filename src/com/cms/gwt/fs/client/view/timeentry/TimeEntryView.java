package com.cms.gwt.fs.client.view.timeentry;

import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.presenter.TimeEntryPresenter;
import com.cms.gwt.fs.client.presenter.TimeEntryPresenter.TimeEntryHeaderType;
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
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.form.Checkbox;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.grid.CellMetadata;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.EditorGridPanel;
import com.gwtext.client.widgets.grid.GridEditor;
import com.gwtext.client.widgets.grid.GridView;
import com.gwtext.client.widgets.grid.Renderer;
import com.gwtext.client.widgets.grid.RowParams;

public class TimeEntryView extends Composite implements TimeEntryPresenter.View {

	private final VerticalPanel panel;
	private final VerticalPanel gridContainer;
	private final EditorGridPanel grid;
	private final TextConstants txtConsts;

	private Header header;

	private final HasClickHandlers btnAddTime;
	private final HasClickHandlers btnSave;
	// private final HasClickHandlers btnEdit;
	private final HasClickHandlers btnDelete;
	private final HasClickHandlers btnCancel;
	private final ToolbarButton gridResetButton;

	private boolean flagInit;

	/**
	 * Constructor.
	 */
	public TimeEntryView() {
		panel = new VerticalPanel();
		panel.setStyleName("TimeEntry");
		initWidget(panel);

		gridContainer = new VerticalPanel();
		grid = new EditorGridPanel();

		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		header = new Header();
		header.setHeaderType(TimeEntryHeaderType.LABOUR_RECORDED);

		btnAddTime = new Button(txtConsts.TEAddTime());
		btnSave = new Button(txtConsts.Save());
		// btnEdit = new Button(txtConsts.Edit());
		btnDelete = new Button(txtConsts.Delete());
		btnCancel = new Button(txtConsts.Cancel());
		gridResetButton = new ToolbarButton(txtConsts.TicketListClearSort());

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
	public void reset() {
		header.reset();
		((Button) btnSave).setEnabled(false);
		// ((Button) btnEdit).setEnabled(false);
		((Button) btnDelete).setEnabled(false);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setReadOnly(boolean readOnly) {
		boolean enabled = !readOnly;
		header.setReadOnly(readOnly);
		// ((Button) btnEdit).setEnabled(enabled);
		((Button) btnDelete).setEnabled(enabled);
	}

	/**
	 * {@inheritDoc}
	 */
	public void clearTimeEntryGrid() {
		if (flagInit) {
			grid.clear();
		}
		gridContainer.clear();
	}

	/**
	 * {@inheritDoc}
	 */
	public EditorGridPanel getTimeEntryGrid() {
		return grid;
	}

	/**
	 * {@inheritDoc}
	 */
	public String initTimeEntryGrid(Object[][] timeEntries) {
		// display name and size
		String[][] metaData = new String[][] {
				{ txtConsts.TESequence(), "80" },
				{ txtConsts.TEDescription(), "200" },
				{ txtConsts.TELine(), "80" }, { txtConsts.TETime(), "80" },
				{ txtConsts.TEOvertime(), "80" },
				{ txtConsts.TEWarranty(), "80" },
				{ txtConsts.TEIsPosted(), "0" } };

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
		MemoryProxy proxy = new MemoryProxy(timeEntries);
		ArrayReader reader = new ArrayReader(recordDef);
		Store store = new Store(proxy, reader);

		// make columns and set to grid
		ColumnConfig[] columns = new ColumnConfig[metaDataLen];
		for (int i = 0; i < metaDataLen; i++) {
			columns[i] = new ColumnConfig(metaData[i][0], metaDataId[i],
					Integer.parseInt(metaData[i][1]), true, null, "id_"
							+ metaDataId[i]);
		}

		NumberField nfTime = new NumberField();
		nfTime.setAllowDecimals(true);
		nfTime.setDecimalPrecision(2);
		columns[3].setEditor(new GridEditor(nfTime));

		// custom renderer
		columns[4].setRenderer(new Renderer() {
			public String render(Object value, CellMetadata cellMetadata,
					Record record, int rowIndex, int colNum, Store store) {
				String checked = ("true".equals(value.toString())) ? "checked"
						: "unchecked";
				return "<img class=\"checkbox\" src=\"images/" + checked
						+ ".gif\"/>";
			}
		});
		columns[4].setEditor(new GridEditor(new Checkbox()));

		columns[5].setRenderer(new Renderer() {
			public String render(Object value, CellMetadata cellMetadata,
					Record record, int rowIndex, int colNum, Store store) {
				String checked = ("true".equals(value.toString())) ? "checked"
						: "unchecked";
				return "<img class=\"checkbox\" src=\"images/" + checked
						+ ".gif\"/>";
			}
		});
		columns[5].setEditor(new GridEditor(new Checkbox()));

		columns[6].setHidden(true);

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
			grid.setWidth("620px"); // add extra 20px for sorting
			grid.setAutoHeight(true);
			// grid.setTitle(txtConsts.TimeEntry());

			Toolbar bottomToolbar = new Toolbar();
			bottomToolbar.addFill();
			bottomToolbar.addButton(gridResetButton);
			grid.setBottomToolbar(bottomToolbar);

			grid.setClicksToEdit(1);

			grid.setView(new GridView() {
				public String getRowClass(Record record, int index,
						RowParams rowParams, Store store) {
					String value = record.getAsString(StringUtil
							.makeId(txtConsts.TEIsPosted()));
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

	public HasText getTxtTicketNumber() {
		return header.getTxtTicketNumber();
	}

	public HasText getTxtEventId() {
		return header.getTxtEventId();
	}

	public HasText getTxtCounterReading() {
		return header.getTxtCounterReading();
	}

	public HasText getTxtTechnician() {
		return header.getTxtTechnician();
	}

	public HasText getTxtTechnicianDescription() {
		return header.getTxtTechnicianDescription();
	}

	public HasText getTxtArrivalDate() {
		return header.getTxtArrivalDate();
	}

	public HasText getTxtArrivalTime() {
		return header.getTxtArrivalTime();
	}

	public HasText getTxtTotalTimeRecorded() {
		return header.getTxtTotalTimeRecorded();
	}

	public HasText getTxtTotalHoursWorked() {
		return header.getTxtTotalHoursWorked();
	}

	public HasClickHandlers getBtnAddTime() {
		return btnAddTime;
	}

	public HasClickHandlers getBtnSave() {
		return btnSave;
	}

	// public HasClickHandlers getBtnEdit() {
	// return btnEdit;
	// }

	public HasClickHandlers getBtnDelete() {
		return btnDelete;
	}

	public HasClickHandlers getBtnCancel() {
		return btnCancel;
	}

	public ToolbarButton getGridResetButton() {
		return gridResetButton;
	}

	/**
	 * Arrange widgets in a nice layout
	 */
	private void layout() {

		// ((Button) btnEdit).setVisible(false);

		panel.add(header.getWidget());
		panel.add(new HTML("&nbsp;"));
		panel.add(gridContainer);

		HorizontalPanel btnPanel = new HorizontalPanel();
		btnPanel.setStyleName("BtnPanel");
		panel.add(btnPanel);

		btnPanel.add((Widget) btnAddTime);
		btnPanel.add((Widget) btnSave);
		// btnPanel.add((Widget) btnEdit);
		btnPanel.add((Widget) btnDelete);
		btnPanel.add((Widget) btnCancel);
	}

}
