package com.cms.gwt.fs.client.notes;

import java.util.ArrayList;
import java.util.List;

public class NotesPage {

    private List<NotesLine>lines;
    private int pageNumber;
    
    public NotesPage(int pageNumber)
    {
    	this.pageNumber = pageNumber;
    	this.lines = new ArrayList<NotesLine>();
    }
    
    public NotesPage(int pageNumber, List<NotesLine> lines)
    {
    	this.pageNumber = pageNumber;
    	this.lines = lines;
    }    
    
	public List<NotesLine> getLines() {
		return lines;
	}
	public void setLines(List<NotesLine> lines) {
		this.lines = lines;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
    public void addLine(NotesLine line)
    {
    	lines.add(line);
    } 
    
}
