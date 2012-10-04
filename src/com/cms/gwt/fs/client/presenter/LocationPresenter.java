package com.cms.gwt.fs.client.presenter;

import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;

/**
 * Location presenter interface.
 * 
 */
public interface LocationPresenter extends BasePresenter {

	interface View extends BaseView {
		
		final static int CUSTOMER_TYPE = 1;
		final static int INTERNAL_ASSET_TYPE = 2;

		HasText getTxtCustomerNumber();

		HasText getTxtCustomerAddress1();

		HasText getTxtCustomerAddress2();

		HasText getTxtCustomerAddress3();

		HasText getTxtCustomerAddress4();

		HasText getTxtCustomerAddress5();

		HasText getTxtCustomerAddress6();

		HasText getTxtCustomerSite();

		HasName getLstCustomerTerritory();

		HasText getTxtCustomerTerritory1();

		HasText getTxtCustomerTerritory2();

		HasText getTxtCustomerTerritory3();

		HasText getTxtCustomerTerritory4();

		HasText getTxtCustomerTerritory5();

		HasText getTxtCustomerTerritory6();

		HasText getTxtAddressLine1();

		HasText getTxtAddressLine2();

		HasText getTxtAddressLine3();

		HasText getTxtAddressLine4();

		HasText getTxtAddressLine5();

		HasText getTxtAddressLine6();

		HasText getTxtInHousePlant();

		void showType(int type);

	}

	void showLocation(String ticketNumber);
	
	void setLocationType(boolean isInternalAsset);

}
