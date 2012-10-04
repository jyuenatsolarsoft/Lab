package com.cms.gwt.fs.client.view.details;

import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.presenter.DetailsPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
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
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.grid.CellMetadata;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.Renderer;

/**
 * Grid view of the Services Detail
 * 
 */
public class DetailsGridView extends Composite implements
		DetailsPresenter.GridView {

	private final VerticalPanel panel;
	private final GridPanel gridPanel;
	private final TextConstants txtConsts;
	private boolean flagInit;

	/**
	 * Constructor.
	 */
	public DetailsGridView() {
		panel = new VerticalPanel();
		panel.setStyleName("DetailsGrid");
		initWidget(panel);

		gridPanel = new GridPanel();
		flagInit = false;

		txtConsts = (TextConstants) GWT.create(TextConstants.class);
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
	public void clearDetailsGrid() {
		if (flagInit) {
			gridPanel.clear();
		}
		panel.clear();
	}

	/**
	 * {@inheritDoc}
	 */
	public GridPanel getDetailsGrid() {
		return gridPanel;
	}

	/**
	 * {@inheritDoc}
	 */
	public String initDetailsGrid(Object[][] details) {
		// display name and size
		String[][] metaData = new String[][] {
				{ txtConsts.DetailsSequence(), "80" },
				{ txtConsts.DetailsDescription(), "200" },
				{ txtConsts.DetailsManPower(), "80" },
				{ txtConsts.DetailsTimeEstimate(), "80" },
				{ txtConsts.DetailsWarrantyTask(), "80" },
				{ txtConsts.DetailsStatus(), "80" } };

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
		MemoryProxy proxy = new MemoryProxy(details);
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
		columns[4].setRenderer(new Renderer() {
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
			gridPanel.setWidth("620px"); // add extra 20px for sorting
			gridPanel.setAutoHeight(true);
			// gridPanel.setTitle(txtConsts.Details());

			Toolbar bottomToolbar = new Toolbar();
			bottomToolbar.addFill();
			bottomToolbar.addButton(new ToolbarButton(txtConsts
					.TicketListClearSort(), new ButtonListenerAdapter() {
				@Override
				public void onClick(Button button, EventObject e) {
					gridPanel.clearSortState(true);
				}
			}));
			gridPanel.setBottomToolbar(bottomToolbar);

			flagInit = true; // grid panel has been initialized
		}

		// this should be done in constructor. but if you try to add grid panel
		// without setting store it will throw IllegalStateException
		panel.add(gridPanel);

		// return the unique identifier for details
		return metaDataId[0];
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void setReadOnly(boolean readOnly) {
	}

}
