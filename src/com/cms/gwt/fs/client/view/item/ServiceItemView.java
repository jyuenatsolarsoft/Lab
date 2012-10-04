package com.cms.gwt.fs.client.view.item;

import com.cms.gwt.fs.client.presenter.ServiceItemPresenter;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * View for Service Item.
 * <p />
 * Two types: Asset, and Product.
 * 
 */
public class ServiceItemView extends Composite implements
		ServiceItemPresenter.View {
	
	private final VerticalPanel serviceItem;
	private final Product product;
	private final Asset asset;
	
	/**
	 * Constructor.
	 * 
	 * @param product
	 *            Product Item.
	 * @param asset
	 *            Asset Item.
	 */
	@Inject
	public ServiceItemView(Product product, Asset asset) {

		serviceItem = new VerticalPanel();
		initWidget(serviceItem);

		this.product = product;
		this.asset = asset;

		layout();

		// by-default read-only
		setReadOnly(true);

		// by-default show first type
		showType(PRODUCT_VIEW);
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
		product.setReadOnly(readOnly);
		asset.setReadOnly(readOnly);
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset() {
		product.reset();
		asset.reset();
	}

	public HasText getTxtContractType1() {
		return product.getTxtContractType1();
	}

	public HasText getTxtContractType2() {
		return product.getTxtContractType2();
	}

	public HasText getTxtContractType3() {
		return product.getTxtContractType3();
	}

	public HasText getTxtContractType4() {
		return product.getTxtContractType4();
	}

	public HasText getTxtExpires() {
		return product.getTxtExpires();
	}

	public HasText getTxtProductDescription1() {
		return product.getTxtProductDescription1();
	}

	public HasText getTxtProductDescription2() {
		return product.getTxtProductDescription2();
	}

	public HasText getTxtProductId() {
		return product.getTxtProductId();
	}

	public HasText getTxtShipped() {
		return product.getTxtShipped();
	}

	public HasText getTxtAssetDescription1() {
		return asset.getTxtAssetDescription1();
	}

	public HasText getTxtAssetDescription2() {
		return asset.getTxtAssetDescription2();
	}

	public HasText getTxtAssetDescription3() {
		return asset.getTxtAssetDescription3();
	}

	public HasText getTxtAssetNumber() {
		return asset.getTxtAssetNumber();
	}

	public HasText getTxtAssetType() {
		return asset.getTxtAssetType();
	}

	public void showType(int type) {
		switch (type) {
		case PRODUCT_VIEW:
			product.setVisible(true);
			asset.setVisible(false);
			break;
		case ASSET_VIEW:
			product.setVisible(false);
			asset.setVisible(true);
			break;
		}
	}

	/**
	 * Arrange widgets in a nice layout.
	 */
	private void layout() {
		serviceItem.add(product);
		serviceItem.add(asset);
	}

}
