package com.cms.gwt.fs.client.model.workHistory;

public class TimeEntry {

    private int sequence;
    private String description;
    private double estimate;
    private double entered;
    private double actual;
    private boolean isOvertime;
    private boolean isWarranty;
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getEstimate() {
		return estimate;
	}
	public void setEstimate(double estimate) {
		this.estimate = estimate;
	}
	public double getEntered() {
		return entered;
	}
	public void setEntered(double entered) {
		this.entered = entered;
	}
	public double getActual() {
		return actual;
	}
	public void setActual(double actual) {
		this.actual = actual;
	}
	public boolean isOvertime() {
		return isOvertime;
	}
	public void setOvertime(boolean isOvertime) {
		this.isOvertime = isOvertime;
	}
	public boolean isWarranty() {
		return isWarranty;
	}
	public void setWarranty(boolean isWarranty) {
		this.isWarranty = isWarranty;
	}
    
    
}
