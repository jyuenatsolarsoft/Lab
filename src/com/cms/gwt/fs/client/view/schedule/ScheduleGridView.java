package com.cms.gwt.fs.client.view.schedule;

import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.presenter.SchedulePresenter;
import com.cms.gwt.fs.client.util.DateUtil;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
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

public class ScheduleGridView extends Composite implements
		SchedulePresenter.GridView {

	private final VerticalPanel panel;
	private final HorizontalPanel gridContainer;
	private final GridPanel gridPanel;
	private final TextConstants txtConsts;

	private final HasClickHandlers btnAdd;
	private final HasClickHandlers btnUpdate;
	private final HasClickHandlers btnDelete;
	private final HasClickHandlers btnView;
	private final HasClickHandlers btnAccept;
	private final HasClickHandlers btnDecline;
	private final HasClickHandlers btnWorkHistory;

	private boolean flagInit;

	public static final String STATUS_CODE_FIELD = "STATUS_CODE";

	/**
	 * Constructor.
	 */
	public ScheduleGridView() {
		panel = new VerticalPanel();
		panel.setStyleName("ScheduleGrid");
		initWidget(panel);

		gridContainer = new HorizontalPanel();
		gridPanel = new GridPanel();
		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		btnAdd = new Button(txtConsts.Add());
		btnUpdate = new Button(txtConsts.Update());
		btnDelete = new Button(txtConsts.Delete());
		btnView = new Button(txtConsts.View());
		btnAccept = new Button(txtConsts.Accept());
		btnDecline = new Button(txtConsts.Decline());
		btnWorkHistory = new Button(txtConsts.WorkHistory());

		flagInit = false;
		reset();

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
	public void clearScheduleGrid() {
		if (flagInit) {
			gridPanel.clear();
		}
		gridContainer.clear();
	}

	/**
	 * {@inheritDoc}
	 */
	public GridPanel getScheduleGrid() {
		return gridPanel;
	}

	/**
	 * {@inheritDoc}
	 */
	public String initScheduleGrid(Object[][] schedules) {
		// display name and size
		String[][] metaData = new String[][] {
				{ txtConsts.ScheduleEventId(), "80" },
				{ txtConsts.ScheduleScheduleType(), "100" },
				{ txtConsts.ScheduleManPower(), "80" },
				{ txtConsts.ScheduleTimeEstimate(), "80" },
				{ txtConsts.ScheduleTechnician(), "100" },
				{ txtConsts.ScheduleDescription(), "200" },
				{ txtConsts.ScheduleScheduledDate(), "120" },
				{ txtConsts.ScheduleScheduledTime(), "120" },
				{ txtConsts.ScheduleTimeZone(), "80" },
				{ txtConsts.ScheduleStatus(), "80" },

				// this field will be hidden to store the status code.
				{ STATUS_CODE_FIELD, "0" } };

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
		MemoryProxy proxy = new MemoryProxy(schedules);
		ArrayReader reader = new ArrayReader(recordDef);
		Store store = new Store(proxy, reader);

		// make columns and set to grid
		ColumnConfig[] columns = new ColumnConfig[metaDataLen];
		for (int i = 0; i < metaDataLen; i++) {
			columns[i] = new ColumnConfig(metaData[i][0], metaDataId[i],
					Integer.parseInt(metaData[i][1]), true, null, "id_"
							+ metaDataId[i]);
		}

		columns[6].setRenderer(new Renderer() {
			public String render(Object value, CellMetadata cellMetadata,
					Record record, int rowIndex, int colNum, Store store) {
				return DateUtil.formatDate(value);
			}
		});

		// This is the hidden status code column.
		columns[metaDataLen - 1].setHidden(true);
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
			gridPanel.setWidth("1060px"); // add extra 20px for sorting
			gridPanel.setAutoHeight(true);
			// gridPanel.setTitle(txtConsts.Skills());

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

		// this should be done in constructor. but if you try to add grid
		// panel without setting store it will throw IllegalStateException
		gridContainer.add(gridPanel);

		// return the unique identifier for skills
		return metaDataId[0];
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset() {
		// all buttons should be disabled then would be enabled based on
		// selection on grid
		((Button) btnUpdate).setEnabled(false);
		((Button) btnDelete).setEnabled(false);
		((Button) btnView).setEnabled(false);
		((Button) btnAccept).setEnabled(false);
		((Button) btnDecline).setEnabled(false);
		((Button) btnWorkHistory).setEnabled(false);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setReadOnly(boolean readOnly) {
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

	public HasClickHandlers getBtnAccept() {
		return btnAccept;
	}

	public HasClickHandlers getBtnDecline() {
		return btnDecline;
	}

	public HasClickHandlers getBtnWorkHistory() {
		return btnWorkHistory;
	}

	/**
	 * Arrange widgets in a nice layout
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
		btnPanel.add((Widget) btnAccept);
		btnPanel.add((Widget) btnDecline);
		btnPanel.add((Widget) btnWorkHistory);
	}

}
