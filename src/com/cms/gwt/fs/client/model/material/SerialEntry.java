package com.cms.gwt.fs.client.model.material;

import com.cms.gwt.fs.client.model.FSModel;


public class SerialEntry extends FSModel {
	
	private int serialEntryNumber;
	private String serialNumber;
	private double serialQuantity;
	
	public SerialEntry(int serialEntryNumber, String serialNumber, double serialQuantity)
	{
		this.serialEntryNumber = serialEntryNumber;
		this.serialNumber = serialNumber;
		this.serialQuantity = serialQuantity;
	}
	
	@SuppressWarnings("unused")
	private SerialEntry()
	{
		// Disable default constructor.
	}
	
	public int getSerialEntryNumber() {
		return serialEntryNumber;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public double getSerialQuantity() {
		return serialQuantity;
	}

		
}
