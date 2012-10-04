package com.cms.gwt.fs.client.model.material;

import com.cms.gwt.fs.client.model.FSModel;

public class LotEntry extends FSModel {
	
	private int lotEntryNumber;
	private String lotNumber;
	private double lotQuantity;
	
	
	public LotEntry(int lotEntryNumber, String lotNumber, double lotQuantity)
	{
		this.lotEntryNumber = lotEntryNumber;
		this.lotNumber = lotNumber;
		this.lotQuantity = lotQuantity;
	}
	
	@SuppressWarnings("unused")
	private LotEntry()
	{
		// disable default constructor.
	}
	
	public int getLotEntryNumber() {
		return lotEntryNumber;
	}

	public String getLotNumber() {
		return lotNumber;
	}

	public double getLotQuantity() {
		return lotQuantity;
	}

}
