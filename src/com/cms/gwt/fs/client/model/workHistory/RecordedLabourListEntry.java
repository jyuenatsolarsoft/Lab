package com.cms.gwt.fs.client.model.workHistory;

import com.cms.gwt.fs.client.model.FSModel;

public class RecordedLabourListEntry extends FSModel
{
    private int sequence;
    private String description;
    private int line;
    private double time;
    private boolean isOvertime;
    private boolean isWarranty;
    private boolean isPosted;
    
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
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
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
	public boolean isPosted() {
		return isPosted;
	}
	public void setPosted(boolean isPosted) {
		this.isPosted = isPosted;
	}     
}
