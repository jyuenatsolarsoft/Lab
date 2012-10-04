package com.cms.gwt.fs.client.view;

import com.cms.gwt.fs.client.HistoryConstants;
import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.presenter.ServiceTicketListPresenter;
import com.cms.gwt.fs.client.util.StringUtil;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.PagingToolbar;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtextux.client.data.PagingMemoryProxy;

/**
 * View to show service tickets list.
 * 
 */
public class ServiceTicketListView extends Composite implements
		ServiceTicketListPresenter.View {

	private final VerticalPanel panel;
	private final VerticalPanel gridContainer;
	private final TextConstants txtConsts;
	private final HTML optionsLink;
	private final Label mapFrameInfo;
	private final Frame mapFrame;

	private GridPanel gridPanel;
	private boolean flagInit;

	/**
	 * Constructor.
	 */
	public ServiceTicketListView() {

		panel = new VerticalPanel();
		panel.setStyleName("TicketList");
		panel.setWidth("100%");
		panel.setSpacing(0);
		initWidget(panel);

		txtConsts = (TextConstants) GWT.create(TextConstants.class);
		flagInit = false;

		HorizontalPanel linkPanel = new HorizontalPanel();
		linkPanel.addStyleName("TicketList-LinkPanel");
		panel.add(linkPanel);

		Hyperlink link = new Hyperlink(txtConsts.TicketCalendarTitle(), true,
				HistoryConstants.SERVICE_TICKET_LIST_KEY
						+ HistoryConstants.VALUE_SPLITTER
						+ HistoryConstants.SERVICE_TICKET_EVENT_CALENDAR
						+ HistoryConstants.TOKEN_SPLITTER);
		link.setTitle(txtConsts.Show() + " " + txtConsts.TicketCalendarTitle());
		link.addStyleName("TicketList-Link");
		linkPanel.add(link);

		linkPanel.add(new HTML("|"));
		optionsLink = new HTML("<a href='javascript:;'>" + txtConsts.Options()
				+ "</a>");
		optionsLink.addStyleName("TicketList-Link");
		linkPanel.add(optionsLink);

		gridContainer = new VerticalPanel();
		gridContainer.setStyleName("TicketList-Grid");
		panel.add(gridContainer);

		// gridPanel = new GridPanel();

		mapFrameInfo = new Label();
		panel.add(mapFrameInfo);
		mapFrameInfo.setStyleName("MapFrameInfo");
		mapFrameInfo.setVisible(false);

		mapFrame = new Frame();
		panel.add(mapFrame);
		mapFrame.setStyleName("MapFrame");
		mapFrame.setVisible(false);
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
	public void setReadOnly(boolean readOnly) {
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset() {
		mapFrameInfo.setText("");
		mapFrameInfo.setVisible(false);
		mapFrame.setUrl("");
		mapFrame.setVisible(false);
	}

	/**
	 * {@inheritDoc}
	 */
	public GridPanel getTicketListGrid() {
		return gridPanel;
	}

	/**
	 * {@inheritDoc}
	 */
	public String initTicketListGrid(Object[][] tickets) {

		gridPanel = new GridPanel();

		// display name and size
		String[][] metaData = new String[][] {
				{ txtConsts.TicketListEventId(), "80" },
				{ txtConsts.TicketListCustomerCode(), "80" },
				{ txtConsts.TicketListCustomerName(), "100" },
				{ txtConsts.TicketListScheduleStartDate(), "80" },
				{ txtConsts.TicketListScheduleStartTime(), "80" },
				{ txtConsts.TicketListTypeDescription(), "150" },
				{ txtConsts.TicketListTicketNumber(), "80" },
				{ txtConsts.TicketListTimeEstimate(), "80" },
				{ txtConsts.TicketListDescription(), "150" },
				{ txtConsts.TicketListStatusDescription(), "150" } };

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
		PagingMemoryProxy proxy = new PagingMemoryProxy(tickets);
		ArrayReader reader = new ArrayReader(recordDef);
		Store store = new Store(proxy, reader, true);

		// make columns and set to grid
		ColumnConfig[] columns = new ColumnConfig[metaDataLen];
		for (int i = 0; i < metaDataLen; i++) {
			columns[i] = new ColumnConfig(metaData[i][0], metaDataId[i],
					Integer.parseInt(metaData[i][1]), true, null, "id_"
							+ metaDataId[i]);
		}

		ColumnModel columnModel = new ColumnModel(columns);

		gridPanel.setStore(store);
		gridPanel.setColumnModel(columnModel);

		gridPanel.setFrame(true);
		gridPanel.setStripeRows(true);

		// gridPanel.setAutoWidth(true); // does NOT work as expected
		gridPanel.setWidth("1050px");
		gridPanel.setAutoHeight(true);
		gridPanel.setTitle(txtConsts.EventsList());

		PagingToolbar pagingToolbar = new PagingToolbar(store);
		pagingToolbar.setPageSize(10);
		pagingToolbar.setDisplayInfo(true);
		pagingToolbar.setDisplayMsg(txtConsts.GridDisplaying());
		pagingToolbar.setEmptyMsg(txtConsts.GridNoRecords());

		gridPanel.setBottomToolbar(pagingToolbar);
		store.load(0, 10);

		flagInit = true;

		gridContainer.add(gridPanel);

		// return the unique identifier for tickets
		return metaDataId[6];
	}

	/**
	 * {@inheritDoc}
	 */
	public void clearTicketListGrid() {
		if (flagInit) {
			gridPanel.clear();
			//gridPanel.destroy();
		}
		gridContainer.clear();
	}

	public HTML getOptionsLink() {
		return optionsLink;
	}

	public Frame getMapFrame() {
		return mapFrame;
	}

	public Label getMapFrameInfo() {
		return mapFrameInfo;
	}

}
