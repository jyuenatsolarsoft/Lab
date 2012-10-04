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
 * View for Service Item Product.
 *
 */
public class Product extends Composite implements BaseView {

	private final TextConstants txtConsts;
	private final FlexTable serviceItem;
	
	private final HasHTML lblProductDescription;
	private final HasText txtProductDescription1;
	private final HasText txtProductDescription2;
	private final HasHTML lblProductId;
	private final HasText txtProductId;
	private final HasHTML lblShipped;
	private final HasText txtShipped;
	private final HasHTML lblExpires;
	private final HasText txtExpires;
	private final HasHTML lblContractType;
	private final HasText txtContractType1;
	private final HasText txtContractType2;
	private final HasText txtContractType3;
	private final HasText txtContractType4;
	
	/**
	 * Constructor.
	 */
	public Product() {
		
		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		serviceItem = new FlexTable();
		initWidget(serviceItem);
		
		lblProductDescription = new HTML(txtConsts.ServiceItemProduct());
		txtProductDescription1 = new TextBox();
		txtProductDescription2 = new TextBox();
		lblProductId = new HTML(txtConsts.ServiceItemProductId());
		txtProductId = new TextBox();
		lblShipped = new HTML(txtConsts.ServiceItemShipped());
		txtShipped = new TextBox();
		lblExpires = new HTML(txtConsts.ServiceItemExpires());
		txtExpires = new TextBox();
		lblContractType = new HTML(txtConsts.ServiceItemContractType());
		txtContractType1 = new TextBox();
		txtContractType2 = new TextBox();
		txtContractType3 = new TextBox();
		txtContractType4 = new TextBox();
		
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
		((TextBox) txtProductDescription1).setReadOnly(readOnly);
		((TextBox) txtProductDescription2).setReadOnly(readOnly);
		((TextBox) txtProductId).setReadOnly(readOnly);
		((TextBox) txtShipped).setReadOnly(readOnly);
		((TextBox) txtExpires).setReadOnly(readOnly);
		((TextBox) txtContractType1).setReadOnly(readOnly);
		((TextBox) txtContractType2).setReadOnly(readOnly);
		((TextBox) txtContractType3).setReadOnly(readOnly);
		((TextBox) txtContractType4).setReadOnly(readOnly);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void reset() {
		((TextBox) txtProductDescription1).setText("");
		((TextBox) txtProductDescription2).setText("");
		((TextBox) txtProductId).setText("");
		((TextBox) txtShipped).setText("");
		((TextBox) txtExpires).setText("");
		((TextBox) txtContractType1).setText("");
		((TextBox) txtContractType2).setText("");
		((TextBox) txtContractType3).setText("");
		((TextBox) txtContractType4).setText("");
	}
	
	public HasText getTxtProductDescription1() {
		return txtProductDescription1;
	}

	public HasText getTxtProductDescription2() {
		return txtProductDescription2;
	}

	public HasText getTxtProductId() {
		return txtProductId;
	}

	public HasText getTxtShipped() {
		return txtShipped;
	}

	public HasText getTxtExpires() {
		return txtExpires;
	}

	public HasText getTxtContractType1() {
		return txtContractType1;
	}

	public HasText getTxtContractType2() {
		return txtContractType2;
	}

	public HasText getTxtContractType3() {
		return txtContractType3;
	}

	public HasText getTxtContractType4() {
		return txtContractType4;
	}

	/**
	 * Arrange widgets in a nice layout.
	 */
	private void layout() {
		serviceItem.addStyleName("ServiceItem");
		serviceItem.getColumnFormatter().setWidth(0, "150px");
		serviceItem.getColumnFormatter().setWidth(1, "300px");

		int row = 0, column = 0;
		serviceItem.setWidget(row, column, (Widget) lblProductDescription);
		column++;
		serviceItem.setWidget(row, column, (Widget) txtProductDescription1);
		row++;
		serviceItem.setWidget(row, column, (Widget) txtProductDescription2);
		column = 0;
		row++;
		serviceItem.setWidget(row, column, (Widget) lblProductId);
		column++;
		serviceItem.setWidget(row, column, (Widget) txtProductId);
		column = 0;
		row++;
		serviceItem.setWidget(row, column, (Widget) lblShipped);
		column++;
		serviceItem.setWidget(row, column, (Widget) txtShipped);
		column = 0;
		row++;
		serviceItem.setWidget(row, column, (Widget) lblExpires);
		column++;
		serviceItem.setWidget(row, column, (Widget) txtExpires);
		column = 0;
		row++;
		serviceItem.setWidget(row, column, (Widget) lblContractType);
		column++;
		serviceItem.setWidget(row, column, (Widget) txtContractType1);
		row++;
		serviceItem.setWidget(row, column, (Widget) txtContractType2);
		row++;
		serviceItem.setWidget(row, column, (Widget) txtContractType3);
		row++;
		serviceItem.setWidget(row, column, (Widget) txtContractType4);
	}
}
