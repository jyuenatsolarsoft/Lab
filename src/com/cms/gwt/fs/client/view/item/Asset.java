package com.cms.gwt.fs.client.view.item;

import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * View for Service Item Asset.
 *
 */
public class Asset extends Composite implements BaseView {

	private final TextConstants txtConsts;
	private final FlexTable serviceItem;
	
	private final HasHTML lblAssetType;
	private final HasText txtAssetType;
	private final HasHTML lblAssetNumber;
	private final HasText txtAssetNumber;
	private final HasHTML lblAssetDescription;
	private final HasText txtAssetDescription1;
	private final HasText txtAssetDescription2;
	private final HasText txtAssetDescription3;
	
	/**
	 * Constructor.
	 */
	public Asset() {
		
		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		serviceItem = new FlexTable();
		initWidget(serviceItem);
		
		lblAssetType = new HTML(txtConsts.ServiceItemAssetType());
		txtAssetType = new TextBox();
		lblAssetNumber = new HTML(txtConsts.ServiceItemAssetNumber());
		txtAssetNumber = new TextBox();
		lblAssetDescription = new HTML(txtConsts.ServiceItemAssetDescription());
		txtAssetDescription1 = new TextBox();
		txtAssetDescription2 = new TextBox();
		txtAssetDescription3 = new TextBox();

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
	public void setReadOnly(boolean readOnly) {		
		((TextBox) txtAssetType).setReadOnly(readOnly);
		((TextBox) txtAssetNumber).setReadOnly(readOnly);
		((TextBox) txtAssetDescription1).setReadOnly(readOnly);
		((TextBox) txtAssetDescription2).setReadOnly(readOnly);
		((TextBox) txtAssetDescription3).setReadOnly(readOnly);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void reset() {
		((TextBox) txtAssetType).setText("");
		((TextBox) txtAssetNumber).setText("");
		((TextBox) txtAssetDescription1).setText("");
		((TextBox) txtAssetDescription2).setText("");
		((TextBox) txtAssetDescription3).setText("");
	}
	
	public HasText getTxtAssetType() {
		return txtAssetType;
	}

	public HasText getTxtAssetNumber() {
		return txtAssetNumber;
	}

	public HasText getTxtAssetDescription1() {
		return txtAssetDescription1;
	}

	public HasText getTxtAssetDescription2() {
		return txtAssetDescription2;
	}

	public HasText getTxtAssetDescription3() {
		return txtAssetDescription3;
	}

	/**
	 * Arrange widgets in a nice layout.
	 */
	private void layout() {
		serviceItem.addStyleName("ServiceItem");
		serviceItem.getColumnFormatter().setWidth(0, "150px");
		serviceItem.getColumnFormatter().setWidth(1, "300px");

		int row = 0, column = 0;
		serviceItem.setWidget(row, column, (Widget) lblAssetType);
		column++;
		serviceItem.setWidget(row, column, (Widget) txtAssetType);
		column = 0;
		row++;
		serviceItem.setWidget(row, column, (Widget) lblAssetNumber);
		column++;
		serviceItem.setWidget(row, column, (Widget) txtAssetNumber);
		column = 0;
		row++;
		serviceItem.setWidget(row, column, (Widget) lblAssetDescription);
		column++;
		serviceItem.setWidget(row, column, (Widget) txtAssetDescription1);
		row++;
		serviceItem.setWidget(row, column, (Widget) txtAssetDescription2);
		row++;
		serviceItem.setWidget(row, column, (Widget) txtAssetDescription3);
	}
	
}
