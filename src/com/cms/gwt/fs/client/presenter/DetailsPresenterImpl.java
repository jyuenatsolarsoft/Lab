package com.cms.gwt.fs.client.presenter;

import java.util.HashMap;
import java.util.Map;

import com.allen_sauer.gwt.log.client.Log;
import com.cms.gwt.common.client.ExtCmsMessageBox;
import com.cms.gwt.fs.client.HistoryConstants;
import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.exception.ActionNotSupportedException;
import com.cms.gwt.fs.client.model.ListenerInfo;
import com.cms.gwt.fs.client.model.details.ServiceDetail;
import com.cms.gwt.fs.client.model.details.ServiceDetailList;
import com.cms.gwt.fs.client.model.details.ServiceDetailListEntry;
import com.cms.gwt.fs.client.model.material.ServiceDetailMaterial;
import com.cms.gwt.fs.client.model.material.ServiceDetailMaterialList;
import com.cms.gwt.fs.client.notes.Notes;
import com.cms.gwt.fs.client.presenter.NotesWidgetPresenter.NoteType;
import com.cms.gwt.fs.client.rpc.ActionServices;
import com.cms.gwt.fs.client.rpc.action.GetNotes;
import com.cms.gwt.fs.client.rpc.action.GetServiceDetail;
import com.cms.gwt.fs.client.rpc.action.GetServiceDetailList;
import com.cms.gwt.fs.client.rpc.action.GetServiceDetailMaterialList;
import com.cms.gwt.fs.client.rpc.callback.GotNotes;
import com.cms.gwt.fs.client.rpc.callback.GotServiceDetail;
import com.cms.gwt.fs.client.rpc.callback.GotServiceDetailList;
import com.cms.gwt.fs.client.rpc.callback.GotServiceDetailMaterialList;
import com.cms.gwt.fs.client.util.StringUtil;
import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.inject.Inject;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.Record;
import com.gwtext.client.util.Format;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.event.GridRowListenerAdapter;

/**
 * Service Details' presenter implementation.
 * <p />
 * This class has 2 views: Grid View displayed in a tab, and Panel View
 * displayed separately in panel.
 * 
 */
public class DetailsPresenterImpl extends BasePresenterImpl implements
		DetailsPresenter {

	private final GridView gridView;
	private final PanelView panelView;
	private final NotesWidgetPresenter notesWidgetPresenter;
	private final TextConstants txtConsts;

	private HandlerRegistration btnBackHandler;
	private HandlerRegistration tabPanelHandler;
	private boolean areListenersRegistered;
	private boolean areGridListenersRegistered;
	private boolean isNotesInit;

	private ListenerInfo listenerInfo;

	/**
	 * Constructor.
	 * 
	 * @param eventBus
	 *            The Events Manager
	 * @param gridView
	 *            Grid View of Details
	 * @param panelView
	 *            Panel View of a Details
	 */
	@Inject
	public DetailsPresenterImpl(GridView gridView, PanelView panelView,
			NotesWidgetPresenter notesWidgetPresenter) {

		this.gridView = gridView;
		this.panelView = panelView;
		this.notesWidgetPresenter = notesWidgetPresenter;
		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		// put the notes in the tab
		panelView.getTabPanel().add(notesWidgetPresenter.getView().getWidget(),
				txtConsts.DetailsNotes());

		areListenersRegistered = false;
		areGridListenersRegistered = false;

		isNotesInit = false;
		listenerInfo = new ListenerInfo();
	}

	/**
	 * {@inheritDoc}
	 */
	public BaseView getView() {
		return gridView;
	}

	/**
	 * {@inheritDoc}
	 */
	public BaseView getPanelView() {
		return panelView;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset() {
		unRegisterListenersGrid();
		gridView.clearDetailsGrid();
	}

	/**
	 * {@inheritDoc}
	 */
	public void resetPanel() {
		unRegisterListenersPanel();
		panelView.reset();
		notesWidgetPresenter.reset();
		
		isNotesInit = false;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setVisible(boolean visible) {
		gridView.getWidget().setVisible(visible);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setPanelVisible(boolean visible) {
		panelView.getWidget().setVisible(visible);
	}

	/**
	 * {@inheritDoc}
	 */
	public void showDetailsGrid(String ticketNumber, String panelNumber) {
		getDetailsGrid(ticketNumber, panelNumber);
	}

	/**
	 * {@inheritDoc}
	 */
	public void showDetailsPanel(String ticketNumber, String panelNumber,
			String detailId) {
		getDetailsPanel(ticketNumber, panelNumber, detailId);
	}

	/**
	 * Make RPC to get data for details grid.
	 */
	private void getDetailsGrid(String ticketNumber, String panelNumber) {

		getDetailsGrid(GetServiceDetailList.newInstance(ticketNumber),
				panelNumber);

	}

	/**
	 * Make RPC to get data for details grid.
	 */
	private void getDetailsGrid(GetServiceDetailList action, String panelNumber) {
		try {
			ActionServices.App.getInstance().execute(action,
					new GetDetailsGridCallback(panelNumber));
		} catch (ActionNotSupportedException e) {
			
			final String ERR_MSG = "Unable to retrieve service ticket details.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Make RPC to get data for details panel.
	 */
	private void getDetailsPanel(String ticketNumber, String panelNumber,
			String detailId) {

		getDetailsPanel(GetServiceDetail.newInstance(ticketNumber, detailId),
				ticketNumber, panelNumber);
		getMaterialGrid(GetServiceDetailMaterialList.newInstance(ticketNumber,
				detailId), ticketNumber, panelNumber);
	}

	/**
	 * Make RPC to get notes.
	 */
	private void getDetailsPanelNotes(String ticketNumber, int detailId) {

		Map<String, String> keys = new HashMap<String, String>();

		keys.put(Notes.NotesType.TASK.getKeyFieldNames()[0], ticketNumber);
		keys.put(Notes.NotesType.TASK.getKeyFieldNames()[1], Integer
				.toString(detailId));

		try {
			ActionServices.App.getInstance().execute(
					GetNotes.newInstance(Notes.NotesType.TASK, keys),
					new GetDetailNotesCallback());
		} catch (ActionNotSupportedException e) {
			final String ERR_MSG = "Unable to get Notes on Details Panel.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Make RPC to get data for details panel.
	 */
	private void getMaterialGrid(GetServiceDetailMaterialList action,
			String ticketNumber, String panelNumber) {

		try {
			ActionServices.App.getInstance().execute(action,
					new GetMaterialGridCallback(ticketNumber, panelNumber));
		} catch (ActionNotSupportedException e) {

			final String ERR_MSG = "Unable to get Service Detail Material List.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Make RPC to get data for details panel.
	 */
	private void getDetailsPanel(GetServiceDetail action, String ticketNumber,
			String panelNumber) {

		try {
			ActionServices.App.getInstance().execute(action,
					new GetDetailsPanelCallback(ticketNumber, panelNumber));

		} catch (ActionNotSupportedException e) {			
			final String ERR_MSG = "Unable to get the service ticket details panel.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Display details grid.
	 */
	private void renderDetailsGrid(String ticketNumber, String panelNumber,
			ServiceDetailList detailList) {

		Object[][] details = new Object[detailList.getServiceDetailList()
				.size()][];

		int i = 0;
		for (ServiceDetailListEntry entry : detailList.getServiceDetailList()) {
			details[i++] = new Object[] { entry.getSequence(),
					entry.getDescriptionLine1(),
					StringUtil.formatNumber(entry.getManpower(), 0),
					StringUtil.formatNumber(entry.getTimeEstimate(), 2),
					entry.isWarrantyTask(), entry.getStatusDescription() };
		}

		String uniqueId = gridView.initDetailsGrid(details);
		registerListenersGrid(ticketNumber, panelNumber, uniqueId);
	}

	/**
	 * Display details panel.
	 */
	private void renderPanel(String ticketNumber, String panelNumber,
			ServiceDetail detail) {

		panelView.getTxtSequence().setText(
				Integer.toString(detail.getSequence()));
		((ListBox) panelView.getLstWorkCode()).addItem(detail
				.getWorkCodeDescription());
		panelView.getTxtManPower().setText(
				Double.toString(detail.getManpower()));
		panelView.getTxtTimeEstimate().setText(
				Double.toString(detail.getTimeEstimate()));

		// TODO: Fix the down cast.
		((CheckBox) panelView.getChkWarrantyTask()).setValue(detail
				.isWarrantyTask());

		panelView.getTxtFromProcedure().setText(detail.getFromProcedure());
		panelView.getTxtDescription1().setText(detail.getDescriptionLine1());
		panelView.getTxtDescription2().setText(detail.getDescriptionLine2());
		panelView.getTxtDescription3().setText(detail.getDescriptionLine3());

		((ListBox) panelView.getLstStatus()).addItem(detail
				.getStatusDescription());

		registerListenersPanel(ticketNumber, panelNumber, detail.getSequence());
	}

	/**
	 * Display the data on the material grid.
	 * 
	 * @param ticketNumber
	 *            key of the service ticket which the material is recorded
	 *            under.
	 * @param panelNumber
	 * @param materialList
	 *            The data to be shown on the material grid.
	 */
	private void renderMaterialGrid(String ticketNumber, String panelNumber,
			ServiceDetailMaterialList materialList) {

		Object[][] materialData = new Object[materialList.getMaterials().size()][];

		int i = 0;
		for (ServiceDetailMaterial entry : materialList.getMaterials()) {
			materialData[i++] = new Object[] {
					entry.getLineNumber(),
					entry.getPart(),
					entry.getPartDescription(),
					StringUtil.formatNumber(formatDouble(entry
							.getQuantityRequired()), 5),
					entry.getQuantityRequiredUOM(),
					entry.getProbability(),
					StringUtil.formatNumber(formatDouble(entry
							.getQuantityUsed()), 5) };
		}

		// String uniqueId = gridView.initDetailsGrid(details);
		// registerListenersGrid(ticketNumber, uniqueId);

		panelView.initMaterialGrid(materialData);
	}

	/**
	 * Display notes in the tab panel
	 */
	private void renderPanelNotes(Notes notes) {
		notesWidgetPresenter.setNotes(notes, NoteType.BASIC);
	}

	/**
	 * Register Listeners for details grid.
	 * 
	 * @param uniqueId
	 *            Details unique identifier.
	 */
	private void registerListenersGrid(final String ticketNumber,
			final String panelNumber, final String uniqueId) {

		listenerInfo.setTicketNumber(ticketNumber);
		listenerInfo.setPanelNumber(panelNumber);
		listenerInfo.setUniqueId(uniqueId);

		if (areGridListenersRegistered) {
			return;
		}

		gridView.getDetailsGrid().addGridRowListener(
				new GridRowListenerAdapter() {
					@Override
					public void onRowDblClick(GridPanel grid, int rowIndex,
							EventObject e) {
						gridDblClick(grid, rowIndex);
					}
				});

		areGridListenersRegistered = true;
	}

	/**
	 * Clean-up listeners for details grid.
	 */
	private void unRegisterListenersGrid() {
		if (!areGridListenersRegistered) {
			return;
		}

		// gridView.getDetailsGrid().purgeListeners();
		// areGridListenersRegistered = false;
	}

	/**
	 * Register listeners for details panel.
	 * 
	 * @param ticketId
	 *            Ticket's unique identifier.
	 * 
	 */
	private void registerListenersPanel(final String ticketNumber,
			final String panelNumber, final int sequence) {

		if (areListenersRegistered) {
			return;
		}

		btnBackHandler = panelView.getBtnBack().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						History.newItem(Format.format(
								HistoryConstants.SERVICE_TICKET_VALUE,
								ticketNumber)
								+ Format.format(
										HistoryConstants.TAB_PANEL_VALUE,
										panelNumber));
					}
				});

		tabPanelHandler = panelView.getTabPanel().addSelectionHandler(
				new SelectionHandler<Integer>() {
					public void onSelection(SelectionEvent<Integer> event) {
						setPanelContent(event.getSelectedItem(), ticketNumber,
								sequence);
					}
				});

		areListenersRegistered = true;
	}

	/**
	 * Remove listeners for details panel.
	 */
	private void unRegisterListenersPanel() {
		if (!areListenersRegistered) {
			return;
		}

		btnBackHandler.removeHandler();
		tabPanelHandler.removeHandler();

		areListenersRegistered = false;
	}

	/**
	 * Sets the appropriate content in the panel (identified by panelNumber) of
	 * the tabs.
	 * 
	 * @param panelNumber
	 *            the panel number in which to set content
	 * @param sequence
	 *            The id of the detail.
	 */
	private void setPanelContent(int panelNumber, String ticketNumber,
			int sequence) {
		switch (panelNumber) {
		case 0:
			// materials tab
			break;
		case 1:
			// notes tab
			if (!isNotesInit) {
				getDetailsPanelNotes(ticketNumber, sequence);
			}
			isNotesInit = true;
			break;
		}
	}

	/**
	 * Call-Back class to get the detail list and render them.
	 * 
	 */
	protected class GetDetailsGridCallback extends GotServiceDetailList {

		private final String panelNumber;

		public GetDetailsGridCallback(String panelNumber) {
			this.panelNumber = panelNumber;
		}

		public void got(String ticketNumber, ServiceDetailList detailList) {
			renderDetailsGrid(ticketNumber, panelNumber, detailList);
		}
	}

	/**
	 * Call-Back class to get the detail and render them.
	 * 
	 */
	protected class GetDetailsPanelCallback extends GotServiceDetail {

		private String panelNumber;
		private String ticketNumber;

		public GetDetailsPanelCallback(String ticketNumber, String panelNumber) {
			this.panelNumber = panelNumber;
			this.ticketNumber = ticketNumber;
		}

		public void got(ServiceDetail detail) {
			renderPanel(ticketNumber, panelNumber, detail);
		}
	}

	/**
	 * Call-Back class to get the detail and render them.
	 * 
	 */
	protected class GetMaterialGridCallback extends
			GotServiceDetailMaterialList {

		private String panelNumber;
		private String ticketNumber;

		public GetMaterialGridCallback(String ticketNumber, String panelNumber) {
			this.panelNumber = panelNumber;
			this.ticketNumber = ticketNumber;
		}

		public void got(String ticketNumber,
				ServiceDetailMaterialList materialList) {
			renderMaterialGrid(this.ticketNumber, panelNumber, materialList);
		}
	}

	private void gridDblClick(GridPanel grid, int rowIndex) {
		Record selected = grid.getStore().getAt(rowIndex);
		final String detailId = selected
				.getAsString(listenerInfo.getUniqueId());

		History.newItem(Format.format(HistoryConstants.DETAILS_PANEL_VALUE,
				listenerInfo.getTicketNumber(), listenerInfo.getPanelNumber(),
				detailId));
	}

	/**
	 * Callback class to handle the Details Notes.
	 * 
	 */
	protected class GetDetailNotesCallback extends GotNotes {
		@Override
		public void got(Notes notes) {
			renderPanelNotes(notes);
		}
	}

}
