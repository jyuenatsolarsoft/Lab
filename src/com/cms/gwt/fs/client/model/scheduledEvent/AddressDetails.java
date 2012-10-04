package com.cms.gwt.fs.client.model.scheduledEvent;

import com.cms.gwt.fs.client.model.FSModel;

public class AddressDetails extends FSModel 
{		
	private String[] Address;
	private String city;
	private String provinceState;	
	private String postalCode;
	private String country;
	
	public String[] getAddress() {
		return Address;
	}
	public void setAddress(String[] address) {
		Address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvinceState() {
		return provinceState;
	}
	public void setProvinceState(String provinceState) {
		this.provinceState = provinceState;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
