package com.cms.gwt.fs.client.notes;

public class NotesLine {

    private String lineText;
    private int lineNumber;    
    
    @SuppressWarnings("unused")
	private NotesLine() { 
    	// Disable default constructor.     	
    }
    
    public NotesLine(String lineText, int lineNumber)
    {
    	this.lineText = lineText;
    	this.lineNumber = lineNumber;
    }
    
	public String getLineText() {
		return lineText;
	}
	public void setLineText(String text) {
		this.lineText = text;
	}
	public int getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
    
    
}
