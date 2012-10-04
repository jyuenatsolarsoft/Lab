/**
 * Work History:
 * 
 * AffanJ		2009-10-06		Uncompleted Miscellaneous will always be blank.		
 */

package com.cms.gwt.fs.client.presenter;



import com.allen_sauer.gwt.log.client.Log;
import com.cms.gwt.common.client.ExtCmsMessageBox;
import com.cms.gwt.common.client.CmsMessageBox.CmsConfirmCallback;
import com.cms.gwt.fs.client.HistoryConstants;
import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.exception.ActionNotSupportedException;
import com.cms.gwt.fs.client.factory.CodeDictionaryFactory;
import com.cms.gwt.fs.client.model.CodeDictionary;
import com.cms.gwt.fs.client.model.ListenerInfo;
import com.cms.gwt.fs.client.model.TaxGroupDictionary;
import com.cms.gwt.fs.client.model.billing.BillingDetail;
import com.cms.gwt.fs.client.model.billing.BillingDetailsListEntry;
import com.cms.gwt.fs.client.model.billing.BillingSummary;
import com.cms.gwt.fs.client.model.billing.BillingTotals;
import com.cms.gwt.fs.client.rpc.ActionServices;
import com.cms.gwt.fs.client.rpc.action.AddBillingDetail;
import com.cms.gwt.fs.client.rpc.action.CalculateBillingDetail;
import com.cms.gwt.fs.client.rpc.action.DeleteBillingDetail;
import com.cms.gwt.fs.client.rpc.action.GetBillingDetail;
import com.cms.gwt.fs.client.rpc.action.GetBillingSummary;
import com.cms.gwt.fs.client.rpc.action.UpdateBillingDetail;
import com.cms.gwt.fs.client.rpc.actionResponse.DeleteBillingDetailResponse;
import com.cms.gwt.fs.client.rpc.callback.CalculatedBillingDetail;
import com.cms.gwt.fs.client.rpc.callback.DeletedBillingDetail;
import com.cms.gwt.fs.client.rpc.callback.GotBillingDetail;
import com.cms.gwt.fs.client.rpc.callback.GotBillingSummary;
import com.cms.gwt.fs.client.rpc.callback.UpdatedBillingDetail;
import com.cms.gwt.fs.client.util.StringUtil;
import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.inject.Inject;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.Record;
import com.gwtext.client.util.Format;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.event.GridCellListenerAdapter;

public class BillingPresenterImpl extends BasePresenterImpl implements
		BillingPresenter {

	private final GridView gridView;
	private final PanelView panelView;
	private final TextConstants txtConsts;

	// grid handlers
	private HandlerRegistration gBtnAddHandler;
	private HandlerRegistration gBtnUpdateHandler;
	private HandlerRegistration gBtnDeleteHandler;
	private HandlerRegistration gBtnViewHandler;

	// panel handlers
	private HandlerRegistration pBtnCalculareHandler;
	private HandlerRegistration pBtnAddHandler;
	private HandlerRegistration pBtnUpdateHandler;
	private HandlerRegistration pBtnCancelHandler;
	private HandlerRegistration pBtnBackHandler;
	private HandlerRegistration pLstTaxGroupHandler;

	private boolean areListenersRegistered;
	private boolean areGridListenersRegistered;
	private boolean isGridListenerRegistered;

	private ListenerInfo listenerInfo;

	/**
	 * Current BillingDetail on the billing panel.
	 */
	private BillingDetail currBillingDetail;

	/**
	 * Constructor.
	 * 
	 * @param eventBus
	 * @param gridView
	 * @param panelView
	 */
	@Inject
	public BillingPresenterImpl(GridView gridView, PanelView panelView) {

		this.gridView = gridView;
		this.panelView = panelView;

		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		areListenersRegistered = false;
		areGridListenersRegistered = false;
		isGridListenerRegistered = false;

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
		gridView.clearBillingGrid();

		updateButtons(-1);
	}

	/**
	 * {@inheritDoc}
	 */
	public void resetPanel() {
		unRegisterListenersPanel();
		panelView.reset();
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
	public void showBillingGrid(String ticketNumber, String panelNumber) {
		getBillingGrid(ticketNumber, panelNumber);
	}

	/**
	 * {@inheritDoc}
	 */
	public void showBillingPanel(String ticketNumber, String panelNumber,
			String billingId, BillingPanelType billingPanelType) {

		if (BillingPanelType.ADD.equals(billingPanelType)) {
			renderPanel(ticketNumber, panelNumber, billingId, billingPanelType,
					new BillingDetail());
		} else {
			// Get Data first.
			getBillingPanelData(ticketNumber, panelNumber, billingId,
					billingPanelType);
		}
	}

	/**
	 * Make RPC to get data.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 */
	private void getBillingGrid(String ticketNumber, String panelNumber) {

		try {
			ActionServices.App.getInstance().execute(
					GetBillingSummary.newInstance(ticketNumber),
					new GetBillingGridCallback(ticketNumber, panelNumber));
		} catch (ActionNotSupportedException e) {
			final String ERR_MSG = "Unable to get Billing Summary.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Make RPC to get data.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param billingId
	 */
	private void getBillingPanelData(String ticketNumber, String panelNumber,
			String billingId, BillingPanelType billingPanelType) {

		try {
			ActionServices.App.getInstance().execute(
					GetBillingDetail.newInstance(ticketNumber, billingId),
					new GetBillingPanelCallBack(ticketNumber, panelNumber,
							billingId, billingPanelType));
		} catch (ActionNotSupportedException e) {
			final String ERR_MSG = "Unable to get Billing Panel.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Display billing grid.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 * @param billingSummary
	 *            contains the billing list entries and the
	 */
	private void renderGrid(String ticketNumber, String panelNumber,
			BillingSummary summary) {

		Object[][] billingData = new Object[summary.getDetailsList()
				.getEntries().size()][];

		int i = 0;
		for (BillingDetailsListEntry entry : summary.getDetailsList()
				.getEntries()) {
			billingData[i++] = new Object[] { entry.getLine(),
					entry.getChargeTypeDescription(), entry.getDescription(),
					StringUtil.formatNumber(entry.getQuantity(), 5),
					entry.getQuantityUOM(),
					StringUtil.formatNumber(entry.getUnitPrice(), 3),
					StringUtil.formatNumber(entry.getSubtotal(), 2),
					StringUtil.formatNumber(entry.getTaxes(), 2),
					StringUtil.formatNumber(entry.getTotal(), 2),
					entry.isWarranty() };
		}

		BillingTotals totals = summary.getTotals();

		gridView.getLblLabourCompleted().setText(
				StringUtil.formatNumber((totals.getCompletedLabour()), 2));
		gridView.getLblLabourUnCompleted().setText(
				StringUtil.formatNumber((totals.getUncompletedLabour()), 2));
		gridView.getLblPartsCompleted().setText(
				StringUtil.formatNumber((totals.getCompletedParts()), 2));
		gridView.getLblPartsUncompleted().setText(
				StringUtil.formatNumber((totals.getUncompletedParts()), 2));
		// gridView.getLblMiscellaneousUncompleted().setText(
		// formatDouble(totals.getUncompletedMisc()));
		gridView.getLblMiscellaneousCompleted().setText(
				StringUtil.formatNumber((totals.getCompletedMisc()), 2));
		gridView.getLblSubtotalCompleted().setText(
				StringUtil.formatNumber((totals.getCompletedSubtotal()), 2));
		gridView.getLblSubtotalUncompleted().setText(
				StringUtil.formatNumber((totals.getUncompletedSubtotal()), 2));
		gridView.getLblTaxesCompleted().setText(
				StringUtil.formatNumber((totals.getCompletedTaxes()), 2));
		gridView.getLblTaxesUncompleted().setText(
				StringUtil.formatNumber((totals.getUncompletedTaxes()), 2));
		gridView.getLblToPayCompleted().setText(
				StringUtil.formatNumber((totals.getCompletedToPay()), 2));
		gridView.getLblToPayUncompleted().setText(
				StringUtil.formatNumber((totals.getUncompletedToPay()), 2));
		gridView.getLblTotalToPayUncompleted().setText(
				StringUtil.formatNumber((totals.getToPayAndEstimate()), 2));

		String uniqueId = gridView.initBillingGrid(billingData);
		registerListenersGrid(ticketNumber, panelNumber, uniqueId);
	}

	private void renderPanel(String ticketNumber, String panelNumber,
			String billingId, BillingPanelType billingPanelType,
			BillingDetail detail) {

		String chargeType = "4";
		if (detail != null) {
			panelView.getTxtLineNumber().setText(formatInt(detail.getLine()));

			chargeType = detail.getChargeType();
			if (chargeType == null || "".equals(chargeType.trim())) {
				// assign the default charge type
				// TODO: change the design here.
				chargeType = "4";
			}

			populateListBox(((ListBox) panelView.getLstChargeType()),
					CodeDictionaryFactory.BILLING_CHARGE_TYPE, chargeType);

			populateListBox(((ListBox) panelView
					.getLstMiscellaneousChargeCode()),
					CodeDictionaryFactory.MISC_CHARGE_CODE, detail
							.getMiscChargeCode(), true);

			panelView.getTxtFromEventId().setText(
					formatInt(detail.getEventID()));
			panelView.getTxtDescription().setText(detail.getDescription());
			
			((ListBox)panelView.getLstPartNumber()).addItem(detail.getPartNumber());
			panelView.getTxtLotNumber().setText(detail.getLotNumber());
			panelView.getTxtSerialNumber().setText(Integer.toString(detail.getSerialNumber()));			
			
			panelView.getTxtQuantity().setText(
					StringUtil.formatNumber(detail.getQuantity(), 5));
			panelView.getTxtUnitPrice().setText(
					StringUtil.formatNumber(detail.getUnitPrice(), 3));
			panelView.getTxtSubtotal().setText(
					StringUtil.formatNumber(detail.getSubtotal(), 2));

			// Populate tax group and tax rate. Note that the tax rate list is
			// dependent on the selected tax group.
			TaxGroupDictionary taxGrpDictionary = CodeDictionaryFactory
					.getTaxGroupDictionary();
			populateListBox(((ListBox) panelView.getLstTaxGroup()),
					taxGrpDictionary, detail.getTaxGroup(), true);

			// Note that if tax group is null, then tax rate will not be
			// available.
			CodeDictionary taxRateDictionary = taxGrpDictionary
					.getTaxRateDictionary(detail.getTaxGroup());
			if (taxRateDictionary != null) {
				populateListBox(((ListBox) panelView.getLstTaxRate()),
						taxRateDictionary, detail.getTaxRate(), true);
			}

			panelView.getTxtTaxes().setText(
					StringUtil.formatNumber(detail.getTaxes(), 2));
			panelView.getTxtTotal().setText(
					StringUtil.formatNumber(detail.getTotal(), 2));
			((CheckBox) panelView.getChkWarranty()).setValue(detail
					.isWarranty());
		}

		// Set the current event.
		setCurrBillingDetail(detail);

		panelView.setPanelType(billingPanelType, ChargeType.get(chargeType));

		registerListenersPanel(ticketNumber, panelNumber);
	}

	/**
	 * Update the subtotal, taxes and total on the panel.
	 * 
	 * @param detail
	 *            The updated billing details.
	 */
	private void updatePanel(BillingDetail detail) {

		if (detail != null) {

			panelView.getTxtSubtotal().setText(
					formatDouble(detail.getSubtotal()));

			panelView.getTxtTaxes().setText(formatDouble(detail.getTaxes()));
			panelView.getTxtTotal().setText(formatDouble(detail.getTotal()));

		}
	}

	/**
	 * Register Listeners for grid.
	 * 
	 * @param uniqueId
	 *            unique identifier.
	 */
	private void registerListenersGrid(final String ticketNumber,
			final String panelNumber, final String uniqueId) {

		listenerInfo.setTicketNumber(ticketNumber);
		listenerInfo.setPanelNumber(panelNumber);
		listenerInfo.setUniqueId(uniqueId);

		if (!isGridListenerRegistered) {

			gridView.getBillingGrid().addGridCellListener(
					new GridCellListenerAdapter() {
						public void onCellClick(GridPanel grid, int rowIndex,
								int colIndex, EventObject e) {
							updateButtons(rowIndex);
						}

						public void onCellDblClick(GridPanel grid,
								int rowIndex, int colIndex, EventObject e) {
							gridDblClick(grid, rowIndex);
						}
					});

			isGridListenerRegistered = true;
		}

		if (areGridListenersRegistered) {
			return;
		}

		gBtnAddHandler = gridView.getBtnAdd().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						History.newItem(Format.format(
								HistoryConstants.BILLING_PANEL_VALUE,
								listenerInfo.getTicketNumber(), listenerInfo
										.getPanelNumber(), "", String
										.valueOf(BillingPanelType.ADD.value)));
					}
				});

		gBtnUpdateHandler = gridView.getBtnUpdate().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						try {
							GridPanel grid = gridView.getBillingGrid();
							final String billingId = grid.getSelectionModel()
									.getSelected().getAsString(
											StringUtil.makeId(listenerInfo
													.getUniqueId()));

							History
									.newItem(Format
											.format(
													HistoryConstants.BILLING_PANEL_VALUE,
													listenerInfo
															.getTicketNumber(),
													listenerInfo
															.getPanelNumber(),
													billingId,
													String
															.valueOf(BillingPanelType.UPDATE.value)));

						} catch (Exception e) {
							Log.error("Unable to bring up billing panel.", e);
						}
					}
				});

		gBtnDeleteHandler = gridView.getBtnDelete().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						try {
							GridPanel grid = gridView.getBillingGrid();
							final String billingId = grid.getSelectionModel()
									.getSelected().getAsString(
											StringUtil.makeId(uniqueId));

							ExtCmsMessageBox box = new ExtCmsMessageBox();
							box.confirm(txtConsts.Delete(), txtConsts.Confirm(
									txtConsts.Delete(), txtConsts.Billing(),
									billingId), new DeleteConfirmation(
									ticketNumber, panelNumber, billingId));
						} catch (Exception e) {
						}
					}
				});

		gBtnViewHandler = gridView.getBtnView().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						try {
							GridPanel grid = gridView.getBillingGrid();
							final String billingId = grid.getSelectionModel()
									.getSelected().getAsString(
											StringUtil.makeId(listenerInfo
													.getUniqueId()));

							History
									.newItem(Format
											.format(
													HistoryConstants.BILLING_PANEL_VALUE,
													listenerInfo
															.getTicketNumber(),
													listenerInfo
															.getPanelNumber(),
													billingId,
													String
															.valueOf(BillingPanelType.VIEW.value)));

						} catch (Exception e) {
						}
					}
				});

		areGridListenersRegistered = true;
	}

	private void setCurrBillingDetail(BillingDetail currBillingDetail) {
		this.currBillingDetail = currBillingDetail;
	}

	/**
	 * Update the Billing detail on the backend.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 */
	private void updateBillingDetail(String ticketNumber, String panelNumber) {
		try {
			ActionServices.App.getInstance().execute(
					UpdateBillingDetail.newInstance(currBillingDetail),
					new UpdateBillingDetailCallback(ticketNumber, panelNumber));
		} catch (ActionNotSupportedException e) {
			final String ERR_MSG = "Unable to update Billing Detail.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Calculate the billing detail. i.e. subtotal, tax and total.
	 */
	private void calculateBillingDetail() {
		try {
			ActionServices.App.getInstance().execute(
					CalculateBillingDetail.newInstance(currBillingDetail),
					new CalculateBillingDetailCallback());
		} catch (ActionNotSupportedException e) {
			final String ERR_MSG = "Unable to calculate Billing Detail.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Add the current scheduled event to the backend.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 */
	private void addBillingDetail(String ticketNumber, String panelNumber) {
		try {
			ActionServices.App.getInstance().execute(
					AddBillingDetail.newInstance(currBillingDetail),
					new UpdateBillingDetailCallback(ticketNumber, panelNumber));
		} catch (ActionNotSupportedException e) {
			final String ERR_MSG = "Unable to add billing detail.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Delete scheduled event from the backend.
	 * 
	 * @param ticketNumber
	 *            The number of ticket.
	 * @param line
	 *            The id of the billing detail to be deleted.
	 * @param panelId
	 */
	private void deleteBillingDetail(String ticketNumber, String line,
			String panelId) {
		try {
			ActionServices.App
					.getInstance()
					.execute(
							DeleteBillingDetail.newInstance(ticketNumber, line),
							new DeleteBillingDetailCallback(ticketNumber, line,
									panelId));
		} catch (ActionNotSupportedException e) {
			final String ERR_MSG = "Unable to delete Billing Detail.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	private void refreshBillingDetail(String ticketNumber) {

		// TODO: charge type
		currBillingDetail.setTicketNumber(ticketNumber);

		String chargeType = (getSelectedValue((ListBox) panelView
				.getLstChargeType()));
		// Set the type to be "4" by default.
		// TODO: define this default value somewhere else.
		chargeType = (chargeType == null || "".equals(chargeType.trim())) ? "4"
				: chargeType;
		currBillingDetail.setChargeType(chargeType);

		currBillingDetail
				.setMiscChargeCode(getSelectedValue((ListBox) panelView
						.getLstMiscellaneousChargeCode()));
		currBillingDetail.setDescription(panelView.getTxtDescription()
				.getText());
		currBillingDetail.setQuantity(parseDouble(panelView.getTxtQuantity()
				.getText()));
		currBillingDetail.setUnitPrice(parseDouble(panelView.getTxtUnitPrice()
				.getText()));
		currBillingDetail.setSubtotal(parseDouble(panelView.getTxtSubtotal()
				.getText()));
		currBillingDetail.setTaxGroup(getSelectedValue((ListBox) panelView
				.getLstTaxGroup()));
		currBillingDetail.setTaxRate(getSelectedValue((ListBox) panelView
				.getLstTaxRate()));

		currBillingDetail.setWarranty(((CheckBox) panelView.getChkWarranty())
				.getValue());

	}

	/**
	 * Register listeners for panel.
	 * 
	 * @param ticketNumber
	 * @param panelNumber
	 */
	private void registerListenersPanel(final String ticketNumber,
			final String panelNumber) {

		if (areListenersRegistered) {
			return;
		}

		pBtnCalculareHandler = panelView.getBtnCalculate().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {

						refreshBillingDetail(ticketNumber);
						calculateBillingDetail();
					}
				});

		pBtnAddHandler = panelView.getBtnAdd().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {

						refreshBillingDetail(ticketNumber);
						addBillingDetail(ticketNumber, panelNumber);

					}
				});

		pBtnUpdateHandler = panelView.getBtnUpdate().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {

						refreshBillingDetail(ticketNumber);
						updateBillingDetail(ticketNumber, panelNumber);
						
					}
				});

		pBtnCancelHandler = panelView.getBtnCancel().addClickHandler(
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

		pBtnBackHandler = panelView.getBtnBack().addClickHandler(
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

		// Update the tax code when there's a change on tax group
		pLstTaxGroupHandler = ((ListBox) panelView.getLstTaxGroup())
				.addChangeHandler(new ChangeHandler() {

					public void onChange(ChangeEvent event) {

						ListBox taxGrpListBox = ((ListBox) panelView
								.getLstTaxGroup());
						String taxGroupCode = taxGrpListBox
								.getValue(taxGrpListBox.getSelectedIndex());

						// Note that if tax group is null, then tax rate will
						// not be available.
						TaxGroupDictionary taxGrpDictionary = CodeDictionaryFactory
								.getTaxGroupDictionary();
						CodeDictionary taxRateDictionary = taxGrpDictionary
								.getTaxRateDictionary(taxGroupCode);
						if (taxRateDictionary != null) {
							// Do not select any tax code after a change has
							// been made on the tax group.
							populateListBox(((ListBox) panelView
									.getLstTaxRate()), taxRateDictionary, null,
									true);
						} else {
							// reset the listbox if no tax group has been
							// selected.
							((ListBox) panelView.getLstTaxRate()).clear();
						}
					}
				});

		areListenersRegistered = true;
	}

	/**
	 * Clean-up listeners for grid.
	 */
	private void unRegisterListenersGrid() {
		// if (isGridListenerRegistered) {
		// gridView.getBillingGrid().purgeListeners();
		// }
		// isGridListenerRegistered = false;

		if (!areGridListenersRegistered) {
			return;
		}

		gBtnAddHandler.removeHandler();
		gBtnUpdateHandler.removeHandler();
		gBtnDeleteHandler.removeHandler();
		gBtnViewHandler.removeHandler();

		areGridListenersRegistered = false;
	}

	/**
	 * Clean-up listeners for panel
	 */
	private void unRegisterListenersPanel() {
		if (!areListenersRegistered) {
			return;
		}

		pBtnCalculareHandler.removeHandler();
		pBtnAddHandler.removeHandler();
		pBtnUpdateHandler.removeHandler();
		pBtnCancelHandler.removeHandler();
		pBtnBackHandler.removeHandler();
		pLstTaxGroupHandler.removeHandler();

		areListenersRegistered = false;
	}

	private void updateButtons(int rowIndex) {

		boolean enabled = (rowIndex >= 0);

		((Button) gridView.getBtnUpdate()).setEnabled(enabled);
		((Button) gridView.getBtnDelete()).setEnabled(enabled);
		((Button) gridView.getBtnView()).setEnabled(enabled);
	}

	private void gridDblClick(GridPanel grid, int rowIndex) {
		Record selected = grid.getStore().getAt(rowIndex);
		final String billingId = selected.getAsString(StringUtil
				.makeId(listenerInfo.getUniqueId()));

		History.newItem(Format.format(HistoryConstants.BILLING_PANEL_VALUE,
				listenerInfo.getTicketNumber(), listenerInfo.getPanelNumber(),
				billingId, String.valueOf(BillingPanelType.VIEW.value)));
	}

	private class DeleteConfirmation implements CmsConfirmCallback {
		private final String ticketNumber;
		private final String panelNumber;
		private final String billingId;

		public DeleteConfirmation(String ticketNumber, String panelNumber,
				String billingId) {
			this.ticketNumber = ticketNumber;
			this.panelNumber = panelNumber;
			this.billingId = billingId;
		}

		public void execute(String btnID) {
			if (!"yes".equalsIgnoreCase(btnID)) {
				return;
			}

			deleteBillingDetail(ticketNumber, billingId, panelNumber);

			reset();
			showBillingGrid(ticketNumber, panelNumber);
		}
	}

	/**
	 * Call-Back class to get the detail and render them.
	 * 
	 */
	protected class GetBillingGridCallback extends GotBillingSummary {

		private String panelNumber;
		private String ticketNumber;

		public GetBillingGridCallback(String ticketNumber, String panelNumber) {
			this.panelNumber = panelNumber;
			this.ticketNumber = ticketNumber;
		}

		public void got(BillingSummary billingSummary) {
			renderGrid(ticketNumber, panelNumber, billingSummary);
		}
	}

	protected class GetBillingPanelCallBack extends GotBillingDetail {

		private String ticketNumber;
		private String panelNumber;
		private String billingId;
		private BillingPanelType panelType;

		public GetBillingPanelCallBack(String ticketNumber, String panelNumber,
				String line, BillingPanelType type) {
			this.ticketNumber = ticketNumber;
			this.panelNumber = panelNumber;
			this.billingId = line;
			this.panelType = type;
		}

		public void got(BillingDetail detail) {
			renderPanel(ticketNumber, panelNumber, billingId, panelType, detail);
		}

	}

	/**
	 * Callback class to handle the result of the model update.
	 * 
	 */
	protected class UpdateBillingDetailCallback extends UpdatedBillingDetail {
		private String ticketNumber;
		private String panelNumber;

		public UpdateBillingDetailCallback(String ticketNumber,
				String panelNumber) {

			this.ticketNumber = ticketNumber;
			this.panelNumber = panelNumber;
		}

		@Override
		public void got(BillingDetail detail) {
			History.newItem(Format.format(
					HistoryConstants.SERVICE_TICKET_VALUE, ticketNumber)
					+ Format.format(HistoryConstants.TAB_PANEL_VALUE,
							panelNumber));
		}

		@Override
		public void onFailure(Throwable throwable) {

			super.displayErrorMsg(throwable, txtConsts
					.UpdateBillingDetailFailure(), new ExtCmsMessageBox());

		}
	}

	/**
	 * Callback class to handle the result of the model update.
	 * 
	 */
	protected class AddBillingDetailCallback extends UpdatedBillingDetail {
		private String ticketNumber;
		private String panelNumber;

		public AddBillingDetailCallback(String ticketNumber, String panelNumber) {

			this.ticketNumber = ticketNumber;
			this.panelNumber = panelNumber;
		}

		@Override
		public void got(BillingDetail detail) {
			History.newItem(Format.format(
					HistoryConstants.SERVICE_TICKET_VALUE, ticketNumber)
					+ Format.format(HistoryConstants.TAB_PANEL_VALUE,
							panelNumber));
		}

		@Override
		public void onFailure(Throwable throwable) {

			super.displayErrorMsg(throwable, txtConsts
					.AddBillingDetailFailure(), new ExtCmsMessageBox());
		}
	}

	/**
	 * Callback class to handle the result of the deletion.
	 * 
	 */
	protected class DeleteBillingDetailCallback extends DeletedBillingDetail {
		private String ticketNumber;
		private String panelNumber;
		private String eventId;

		public DeleteBillingDetailCallback(String ticketNumber, String eventId,
				String panelNumber) {
			this.ticketNumber = ticketNumber;
			this.panelNumber = panelNumber;
			this.eventId = eventId;
		}

		/**
		 * 
		 * @param result
		 */
		protected void onSuccess(DeleteBillingDetailResponse result) {

			showNotifAndWarning(result, txtConsts
					.DeleteBillingDetailNotif(eventId));

			reset();
			showBillingGrid(ticketNumber, panelNumber);
		}

		@Override
		public void onFailure(Throwable throwable) {

			super.displayErrorMsg(throwable, txtConsts
					.DeleteBillingDetailFailure(), new ExtCmsMessageBox());
		}
	}

	/**
	 * Callback class to handle the result of the tax calculation.
	 * 
	 */
	protected class CalculateBillingDetailCallback extends
			CalculatedBillingDetail {

		@Override
		public void got(BillingDetail detail) {

			updatePanel(detail);
		}

		@Override
		public void onFailure(Throwable throwable) {

			super.displayErrorMsg(throwable, txtConsts
					.CalculateBillingDetailFailure(), new ExtCmsMessageBox());

		}
	}

}
