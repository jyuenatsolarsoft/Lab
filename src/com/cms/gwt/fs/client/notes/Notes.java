package com.cms.gwt.fs.client.notes;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.cms.gwt.fs.client.model.FSModel;

/**
 * The Notes class contains the content and metadata of the notes.
 * 
 *
 */
public class Notes extends FSModel 
{
    public static final int MAX_LINE_SIZE = 75;
    public static final int LINES_PER_PAGE = 15;
        
    private String ticketNumber;
    private NotesType type;
    private int lastPageNum;
    
    protected Map<Integer, NotesPage> pages;
    
    /**
     * Constructor
     * 
     * @param type Type of the notes. @see NotesType
     * 
     * 
     */
    public Notes(NotesType type)
    {
    	this.type = type;
    	this.pages = new HashMap<Integer, NotesPage>();
    }

    /**
     * Returns the type of this notes.
     * 
     * @return
     */
    public NotesType getType()
    {
    	return type;
    }
    
    
	/**
	 * Different types of notes.
	 *
	 */
	public enum NotesType 
	{
		SOLUTION("ServiceTicketSolutionNotes", "ticketNumber"), 
		TIMESHEET("ServiceTicketTimesheetNotes", "ticketNumber"), 
		PROBLEM("ServiceTicketProblemNotes", "ticketNumber"), 
		SKILL("SkillCodeNotes", "skill"),
		TASK("ServiceTicketTaskNotes", new String[] { "ticketNumber", "sequence" } );

		protected final String[] keyFieldNames;		
		protected final String typeName;

		/**
		 * The constructor.
		 * 
		 * @param typeName The name of this NotesType.
		 * @param keyFieldName The key field of the Notes.
		 * 
		 */
		NotesType(String typeName, String keyFieldName) 
		{
			this.keyFieldNames = new String[] { keyFieldName };
			this.typeName = typeName;
		}
		
		/**
		 * The constructor. 
		 * 
		 * @param typeName The name of this NotesType.
		 * @param keyFieldName The key field of the Notes.
		 * 
		 */
		NotesType(String typeName, String[] keyFieldNames) 
		{
			this.keyFieldNames = keyFieldNames;
			this.typeName = typeName;
		}		
				
		/**
		 * Returns the first key field of this notes type.
		 * 
		 * @return
		 */
		public String[] getKeyFieldNames()
		{
			return keyFieldNames;
		}		
		
		/**
		 * Returns the type name.
		 * 
		 * @return
		 */
		public String getTypeName()
		{
			return typeName;  
		}

		/**
		 * Get the NotesType based on the specified type name.
		 * 
		 * @param typeName
		 * @return
		 */
		public static NotesType get(String typeName) 
		{
			for (NotesType currentType : NotesType.values()) 
			{
				if (currentType.typeName == typeName) 
				{
					return currentType;
				}
			}
			return null;
		}
	}
	
	/**
	 * Add a page to the notes.
	 * 
	 * The existing page with the same page number will be overwritten.
	 * 
	 * @param page The page to be added.
	 */
	public void addNotesPage(NotesPage page)
	{
		int pageNum = page.getPageNumber(); 
		
		pages.put(pageNum, page);
		
		if (lastPageNum < pageNum)
		{
			lastPageNum = pageNum;
		}
	}
	
	/**
	 * Returns all pages in this notes.
	 * 
	 * @return
	 */
	public Collection<NotesPage> getPages()
	{
		return pages.values(); 
	}
	
	/**
	 * Returns a specific page.
	 * 
	 * @param pageNum page number identifying the page.
	 * 
	 * @return An instance of NotesPage with the specified page number. 
	 */
	public NotesPage getPage(int pageNum)
	{
		return pages.get(pageNum); 
	}

	/**
	 * Return the unique identifier of the ticket where this notes is stored.
	 * 
	 * @return ticket number.
	 */
	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	/**
	 * Return the last page number in this notes.
	 * 
	 * @return
	 */
	public int getLastPageNum() {
		return lastPageNum;
	}
}
