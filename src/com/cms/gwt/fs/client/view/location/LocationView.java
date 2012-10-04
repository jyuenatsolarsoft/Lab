package com.cms.gwt.fs.client.view.location;

import com.cms.gwt.fs.client.presenter.LocationPresenter;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * View for Location.
 * <p />
 * Two types: Customer, and Plant.
 * 
 */
public class LocationView extends Composite implements LocationPresenter.View {

	private final VerticalPanel location;
	private final Customer customer;
	private final Plant plant;

	/**
	 * Constructor.
	 * 
	 * @param customer
	 *            Customer Location.
	 * @param plant
	 *            Plant Location.
	 */
	@Inject
	public LocationView(Customer customer, Plant plant) {

		location = new VerticalPanel();
		initWidget(location);

		this.customer = customer;
		this.plant = plant;

		layout();

		// by-default read-only
		setReadOnly(true);

		// by-default show first type
		showType(1);
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
		customer.setReadOnly(readOnly);
		plant.setReadOnly(readOnly);
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset() {
		customer.reset();
		plant.reset();
	}

	public HasName getLstCustomerTerritory() {
		return customer.getLstCustomerTerritory();
	}

	public HasText getTxtCustomerAddress1() {
		return customer.getTxtCustomerAddress1();
	}

	public HasText getTxtCustomerAddress2() {
		return customer.getTxtCustomerAddress2();
	}

	public HasText getTxtCustomerAddress3() {
		return customer.getTxtCustomerAddress3();
	}

	public HasText getTxtCustomerAddress4() {
		return customer.getTxtCustomerAddress4();
	}

	public HasText getTxtCustomerAddress5() {
		return customer.getTxtCustomerAddress5();
	}

	public HasText getTxtCustomerAddress6() {
		return customer.getTxtCustomerAddress6();
	}

	public HasText getTxtCustomerNumber() {
		return customer.getTxtCustomerNumber();
	}

	public HasText getTxtCustomerSite() {
		return customer.getTxtCustomerSite();
	}

	public HasText getTxtCustomerTerritory1() {
		return customer.getTxtCustomerTerritory1();
	}

	public HasText getTxtCustomerTerritory2() {
		return customer.getTxtCustomerTerritory2();
	}

	public HasText getTxtCustomerTerritory3() {
		return customer.getTxtCustomerTerritory3();
	}

	public HasText getTxtCustomerTerritory4() {
		return customer.getTxtCustomerTerritory4();
	}

	public HasText getTxtCustomerTerritory5() {
		return customer.getTxtCustomerTerritory5();
	}

	public HasText getTxtCustomerTerritory6() {
		return customer.getTxtCustomerTerritory6();
	}

	public HasText getTxtAddressLine1() {
		return plant.getTxtAddressLine1();
	}

	public HasText getTxtAddressLine2() {
		return plant.getTxtAddressLine2();
	}

	public HasText getTxtAddressLine3() {
		return plant.getTxtAddressLine3();
	}

	public HasText getTxtAddressLine4() {
		return plant.getTxtAddressLine4();
	}

	public HasText getTxtAddressLine5() {
		return plant.getTxtAddressLine5();
	}

	public HasText getTxtAddressLine6() {
		return plant.getTxtAddressLine6();
	}

	public HasText getTxtInHousePlant() {
		return plant.getTxtInHousePlant();
	}

	public void showType(int type) {
		switch (type) {
		case 1:
			customer.setVisible(true);
			plant.setVisible(false);
			break;
		case 2:
			customer.setVisible(false);
			plant.setVisible(true);
			break;
		}
	}

	/**
	 * Arrange widgets in a nice layout.
	 */
	private void layout() {
		location.add(customer);
		location.add(plant);
	}

}
