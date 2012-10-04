package com.cms.gwt.fs.client.model.billing;

import com.cms.gwt.fs.client.model.FSModel;

public class BillingSummary extends FSModel 
{    
    private BillingDetailsList detailsList;
    private BillingTotals totals;
    
    public BillingSummary(BillingDetailsList detailsList, BillingTotals totals)
    {
    	this.detailsList = detailsList;
    	this.totals = totals;
    }

	public BillingDetailsList getDetailsList() {
		return detailsList;
	}

	public void setDetailsList(BillingDetailsList detailsList) {
		this.detailsList = detailsList;
	}

	public BillingTotals getTotals() {
		return totals;
	}

	public void setTotals(BillingTotals totals) {
		this.totals = totals;
	}
        	
}
