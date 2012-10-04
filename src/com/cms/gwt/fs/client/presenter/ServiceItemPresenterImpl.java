package com.cms.gwt.fs.client.presenter;

import com.allen_sauer.gwt.log.client.Log;
import com.cms.gwt.common.client.ExtCmsMessageBox;
import com.cms.gwt.fs.client.exception.ActionNotSupportedException;
import com.cms.gwt.fs.client.model.item.ServiceTicketItem;
import com.cms.gwt.fs.client.rpc.ActionServices;
import com.cms.gwt.fs.client.rpc.action.GetServiceTicketItem;
import com.cms.gwt.fs.client.rpc.callback.GotServiceTicketItem;
import com.cms.gwt.fs.client.util.DateUtil;
import com.cms.gwt.fs.client.view.BaseView;
import com.google.inject.Inject;

/**
 * Items presenter's implementation.
 * 
 */
// TODO - this class changes based on the ServiceType
// pass in ServiceType param which will determine what to show
public class ServiceItemPresenterImpl extends BasePresenterImpl implements
		ServiceItemPresenter {

	private final View view;

	@Inject
	public ServiceItemPresenterImpl(View view) {
		this.view = view;
	}

	/**
	 * {@inheritDoc}
	 */
	public void showServiceItem(String ticketNumber) {
		getServiceItem(ticketNumber);
	}

	/**
	 * {@inheritDoc}
	 */
	public BaseView getView() {
		return view;
	}

	/**
	 * {@inheritDoc}
	 */
	public BaseView getPanelView() {
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void reset() {
		resetView();
	}

	/**
	 * {@inheritDoc}
	 */
	public void resetPanel() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void setVisible(boolean visible) {
		view.getWidget().setVisible(visible);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setPanelVisible(boolean visible) {		
	}
	
	/**
	 * Makes RPC to get Item's data.
	 * 
	 * @param ticketNumber
	 *            The key of service ticket which the item belongs to.
	 */
	private void getServiceItem(String ticketNumber) {
		getServiceItem(GetServiceTicketItem.newInstance(ticketNumber));

	}

	/**
	 * Makes RPC to get Item's data.
	 * 
	 */
	private void getServiceItem(GetServiceTicketItem action) {

		try {
			ActionServices.App.getInstance().execute(action,
					new GetServiceTicketItemCallback());
		} catch (ActionNotSupportedException e) {
			
			final String ERR_MSG = "Unable to get the ticket service items.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Display item data.
	 */
	private void render(ServiceTicketItem item) {

		if (item.getProductInformation() != null) {
			view.getTxtProductDescription1().setText(
					item.getProductInformation().getProduct());
			view.getTxtProductDescription2().setText(
					item.getProductInformation().getProductDescription());
			view.getTxtProductId().setText(
					item.getProductInformation().getProductId());
			view.getTxtShipped().setText(
					DateUtil.formatDate(item.getProductInformation()
							.getShippedDate()));
			view.getTxtExpires().setText(
					DateUtil.formatDate(item.getProductInformation()
							.getExpiryDate()));
			view.getTxtContractType1().setText(
					item.getProductInformation().getContractType());
			view.getTxtContractType2().setText(
					item.getProductInformation().getContractDescription1());
			view.getTxtContractType3().setText(
					item.getProductInformation().getContractDescription2());
			view.getTxtContractType4().setText(
					item.getProductInformation().getContractDescription3());
			view.showType(View.PRODUCT_VIEW);
		} else if (item.getAssetInformation() != null) {
			view.getTxtAssetType()
					.setText(item.getAssetInformation().getType());
			view.getTxtAssetNumber().setText(
					item.getAssetInformation().getAssetNumber());
			view.getTxtAssetDescription1().setText(
					item.getAssetInformation().getDescription1());
			view.getTxtAssetDescription2().setText(
					item.getAssetInformation().getDescription2());
			view.getTxtAssetDescription3().setText(
					item.getAssetInformation().getDescription3());
			view.showType(View.ASSET_VIEW);
		}
	}

	/**
	 * Reset the item view.
	 */
	private void resetView() {
		view.reset();
	}

	/**
	 * Call-Back class to get the skill grid data.
	 * 
	 */
	protected class GetServiceTicketItemCallback extends GotServiceTicketItem {
		public void got(ServiceTicketItem item) {
			render(item);
		}
	}
}
