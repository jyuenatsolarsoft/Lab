package com.cms.gwt.fs.client.view.billing;

import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.presenter.BillingPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHTML;
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

public class BillingGridView extends Composite implements
		BillingPresenter.GridView {

	private final VerticalPanel panel;
	private final HorizontalPanel gridContainer;
	private final GridPanel gridPanel;
	private final FlexTable table;
	private final TextConstants txtConsts;

	private final HasClickHandlers btnAdd;
	private final HasClickHandlers btnUpdate;
	private final HasClickHandlers btnDelete;
	private final HasClickHandlers btnView;

	private final HasHTML lblCompleted;
	private final HasHTML lblUnCompleted;
	private final HasHTML lblLabour;
	private final HasHTML lblLabourCompleted;
	private final HasHTML lblLabourUnCompleted;
	private final HasHTML lblParts;
	private final HasHTML lblPartsCompleted;
	private final HasHTML lblPartsUncompleted;
	private final HasHTML lblMiscellaneous;
	private final HasHTML lblMiscellaneousCompleted;
	private final HasHTML lblMiscellaneousUncompleted;
	private final HasHTML lblSubtotal;
	private final HasHTML lblSubtotalCompleted;
	private final HasHTML lblSubtotalUncompleted;
	private final HasHTML lblTaxes;
	private final HasHTML lblTaxesCompleted;
	private final HasHTML lblTaxesUncompleted;
	private final HasHTML lblToPay;
	private final HasHTML lblToPayCompleted;
	private final HasHTML lblToPayUncompleted;
	private final HasHTML lblTotalToPay;
	private final HasHTML lblTotalToPayCompleted;
	private final HasHTML lblTotalToPayUncompleted;

	private boolean flagInit;

	/**
	 * Constructor.
	 */
	public BillingGridView() {
		panel = new VerticalPanel();
		panel.setStyleName("BillingGrid");
		initWidget(panel);

		gridContainer = new HorizontalPanel();
		gridPanel = new GridPanel();
		table = new FlexTable();
		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		btnAdd = new Button(txtConsts.Add());
		btnUpdate = new Button(txtConsts.Update());
		btnDelete = new Button(txtConsts.Delete());
		btnView = new Button(txtConsts.View());

		lblCompleted = new HTML(txtConsts.BillingCompleted());
		lblUnCompleted = new HTML(txtConsts.BillingUncompleted());
		lblLabour = new HTML(txtConsts.BillingLabour());
		lblLabourCompleted = new HTML();
		lblLabourUnCompleted = new HTML();
		lblParts = new HTML(txtConsts.BillingParts());
		lblPartsCompleted = new HTML();
		lblPartsUncompleted = new HTML();
		lblMiscellaneous = new HTML(txtConsts.BillingMiscellaneous());
		lblMiscellaneousCompleted = new HTML();
		lblMiscellaneousUncompleted = new HTML();
		lblSubtotal = new HTML(txtConsts.BillingSubtotal());
		lblSubtotalCompleted = new HTML();
		lblSubtotalUncompleted = new HTML();
		lblTaxes = new HTML(txtConsts.BillingTaxes());
		lblTaxesCompleted = new HTML();
		lblTaxesUncompleted = new HTML();
		lblToPay = new HTML(txtConsts.BillingToPay());
		lblToPayCompleted = new HTML();
		lblToPayUncompleted = new HTML();
		lblTotalToPay = new HTML(txtConsts.BillingTotalToPay());
		lblTotalToPayCompleted = new HTML();
		lblTotalToPayUncompleted = new HTML();

		flagInit = false;

		layout();
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
		lblLabourCompleted.setText(" ");
		lblLabourUnCompleted.setText(" ");
		lblPartsCompleted.setText(" ");
		lblPartsUncompleted.setText(" ");
		lblMiscellaneousCompleted.setText(" ");
		lblMiscellaneousUncompleted.setText(" ");
		lblSubtotalCompleted.setText(" ");
		lblSubtotalUncompleted.setText(" ");
		lblTaxesCompleted.setText(" ");
		lblTaxesUncompleted.setText(" ");
		lblToPayCompleted.setText(" ");
		lblToPayUncompleted.setText(" ");
		lblTotalToPayCompleted.setText(" ");
		lblTotalToPayUncompleted.setText(" ");
	}

	/**
	 * {@inheritDoc}
	 */
	public void setReadOnly(boolean readOnly) {
	}

	/**
	 * {@inheritDoc}
	 */
	public void clearBillingGrid() {
		if (flagInit) {
			gridPanel.clear();
		}
		gridContainer.clear();
		reset();
	}

	/**
	 * {@inheritDoc}
	 */
	public GridPanel getBillingGrid() {
		return gridPanel;
	}

	/**
	 * {@inheritDoc}
	 */
	public String initBillingGrid(Object[][] billings) {
		// display name and size
		String[][] metaData = new String[][] {
				{ txtConsts.BillingLineNumber(), "80" },
				{ txtConsts.BillingType(), "120" },
				{ txtConsts.BillingDescription(), "200" },
				{ txtConsts.BillingQuantity(), "80" },
				{ txtConsts.BillingUnitOfMeasure(), "100" },
				{ txtConsts.BillingUnitPrice(), "80" },
				{ txtConsts.BillingSubtotal(), "80" },
				{ txtConsts.BillingTaxes(), "80" },
				{ txtConsts.BillingTotal(), "80" },
				{ txtConsts.BillingWarranty(), "80" } };

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
		MemoryProxy proxy = new MemoryProxy(billings);
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
		columns[9].setRenderer(new Renderer() {
			public String render(Object value, CellMetadata cellMetadata,
					Record record, int rowIndex, int colNum, Store store) {
				String checked = (value.equals("true")) ? "checked"
						: "unchecked";
				return "<img class=\"checkbox\" src=\"images/" + checked
						+ ".gif\"/>";
			}
		});

		ColumnModel columnModel = new ColumnModel(columns);

		if (flagInit) {
			// reconfigure the data in grid
			gridPanel.reconfigure(store, columnModel);
			store.reload();

		} else {
			// first time setup
			store.load();
			gridPanel.setStore(store);
			gridPanel.setColumnModel(columnModel);

			// gridPanel.setFrame(true);
			gridPanel.setStripeRows(true);

			// gridPanel.setAutoWidth(true); // does NOT work as expected
			gridPanel.setWidth("1020px"); // add extra 20px for sorting
			gridPanel.setAutoHeight(true);
			// gridPanel.setTitle(txtConsts.Billing());

			Toolbar bottomToolbar = new Toolbar();
			bottomToolbar.addFill();
			bottomToolbar.addButton(new ToolbarButton(txtConsts
					.TicketListClearSort(), new ButtonListenerAdapter() {
				@Override
				public void onClick(com.gwtext.client.widgets.Button button,
						EventObject e) {
					gridPanel.clearSortState(true);
				}
			}));
			gridPanel.setBottomToolbar(bottomToolbar);

			flagInit = true; // grid panel has been initialized
		}

		// this should be done in constructor. but if you try to add grid panel
		// without setting store it will throw IllegalStateException
		gridContainer.add(gridPanel);

		// return the unique identifier for billings
		return metaDataId[0];
	}

	public HasClickHandlers getBtnAdd() {
		return btnAdd;
	}

	public HasClickHandlers getBtnUpdate() {
		return btnUpdate;
	}

	public HasClickHandlers getBtnDelete() {
		return btnDelete;
	}

	public HasClickHandlers getBtnView() {
		return btnView;
	}

	public HasHTML getLblLabourCompleted() {
		return lblLabourCompleted;
	}

	public HasHTML getLblLabourUnCompleted() {
		return lblLabourUnCompleted;
	}

	public HasHTML getLblPartsCompleted() {
		return lblPartsCompleted;
	}

	public HasHTML getLblPartsUncompleted() {
		return lblPartsUncompleted;
	}

	public HasHTML getLblMiscellaneousCompleted() {
		return lblMiscellaneousCompleted;
	}

	public HasHTML getLblMiscellaneousUncompleted() {
		return lblMiscellaneousUncompleted;
	}

	public HasHTML getLblSubtotalCompleted() {
		return lblSubtotalCompleted;
	}

	public HasHTML getLblSubtotalUncompleted() {
		return lblSubtotalUncompleted;
	}

	public HasHTML getLblTaxesCompleted() {
		return lblTaxesCompleted;
	}

	public HasHTML getLblTaxesUncompleted() {
		return lblTaxesUncompleted;
	}

	public HasHTML getLblToPayCompleted() {
		return lblToPayCompleted;
	}

	public HasHTML getLblToPayUncompleted() {
		return lblToPayUncompleted;
	}

	public HasHTML getLblTotalToPayCompleted() {
		return lblTotalToPayCompleted;
	}

	public HasHTML getLblTotalToPayUncompleted() {
		return lblTotalToPayUncompleted;
	}

	/**
	 * Arrange items in a nice layout.
	 */
	private void layout() {
		panel.add(gridContainer);

		HorizontalPanel btnPanel = new HorizontalPanel();
		btnPanel.setStyleName("BtnPanel");
		panel.add(btnPanel);

		btnPanel.add((Widget) btnAdd);
		btnPanel.add((Widget) btnUpdate);
		btnPanel.add((Widget) btnDelete);
		btnPanel.add((Widget) btnView);

		panel.add(new HTML("&nbsp;"));
		panel.add(table);

		table.setStyleName("TotalsTable");
		table.setCellPadding(3);
		table.setCellSpacing(0);
		table.getColumnFormatter().setWidth(0, "250px");
		table.getColumnFormatter().setWidth(1, "180px");
		table.getColumnFormatter().setWidth(2, "180px");
		int row = 0, column = 0;
		table.setWidget(row, column, new HTML("&nbsp;"));
		column++;
		table.setWidget(row, column, (Widget) lblCompleted);
		column++;
		table.setWidget(row, column, (Widget) lblUnCompleted);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblLabour);
		column++;
		table.setWidget(row, column, (Widget) lblLabourCompleted);
		column++;
		table.setWidget(row, column, (Widget) lblLabourUnCompleted);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblParts);
		column++;
		table.setWidget(row, column, (Widget) lblPartsCompleted);
		column++;
		table.setWidget(row, column, (Widget) lblPartsUncompleted);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblMiscellaneous);
		column++;
		table.setWidget(row, column, (Widget) lblMiscellaneousCompleted);
		column++;
		table.setWidget(row, column, (Widget) lblMiscellaneousUncompleted);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblSubtotal);
		column++;
		table.setWidget(row, column, (Widget) lblSubtotalCompleted);
		column++;
		table.setWidget(row, column, (Widget) lblSubtotalUncompleted);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblTaxes);
		column++;
		table.setWidget(row, column, (Widget) lblTaxesCompleted);
		column++;
		table.setWidget(row, column, (Widget) lblTaxesUncompleted);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblToPay);
		column++;
		table.setWidget(row, column, (Widget) lblToPayCompleted);
		column++;
		table.setWidget(row, column, (Widget) lblToPayUncompleted);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblTotalToPay);
		column++;
		table.setWidget(row, column, (Widget) lblTotalToPayCompleted);
		column++;
		table.setWidget(row, column, (Widget) lblTotalToPayUncompleted);

	}

}
