package com.cms.gwt.fs.client.presenter;

import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.user.client.ui.HasText;

/**
 * Item presenter interface.
 * 
 */
public interface ServiceItemPresenter extends BasePresenter {

	interface View extends BaseView {
		
		// TODO: use ENUM maybe?
		/** Type of the view. */
		int PRODUCT_VIEW = 1;	
		
		/** Type of the view. */
	 	int ASSET_VIEW = 2;

		HasText getTxtProductDescription1();

		HasText getTxtProductDescription2();

		HasText getTxtProductId();

		HasText getTxtShipped();

		HasText getTxtExpires();

		HasText getTxtContractType1();

		HasText getTxtContractType2();

		HasText getTxtContractType3();

		HasText getTxtContractType4();

		HasText getTxtAssetType();

		HasText getTxtAssetNumber();

		HasText getTxtAssetDescription1();

		HasText getTxtAssetDescription2();

		HasText getTxtAssetDescription3();

		void showType(int type);
	}

	/**
	 * Show the service item of the specified service ticket.
	 * 
	 * @param ticketNumber The key identifying the service ticket.
	 */
	void showServiceItem(String ticketNumber);

}
