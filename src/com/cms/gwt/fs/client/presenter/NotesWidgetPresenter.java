package com.cms.gwt.fs.client.presenter;

import com.cms.gwt.fs.client.notes.Notes;
import com.cms.gwt.fs.client.notes.NotesPage;
import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.TextBox;

public interface NotesWidgetPresenter extends BasePresenter {

	public enum NoteType {
		BASIC, ADVANCED;
	}

	final static int MIN_PAGE_NUM = 1;
	final static int MAX_LINE_COUNT = 15;
	final static int MAX_CHAR_COUNT = 75;

	interface View extends BaseView {
		void setNotes(String[] text, int totalPages, int currPage);

		void setNoteType(NoteType noteType, int maxCharCount, int maxLineCount);

		void resetNotes();
		
		HasClickHandlers getBtnPageUp();

		HasClickHandlers getBtnPageDown();

		HasClickHandlers getBtnClear();

		HasClickHandlers getBtnSave();

		TextBox[] getTxtNotes();
		
	}

	interface NotesSaveHandler {
		boolean saveNotes(NotesPage notesPage, int pageNumber);
	}

	void setNotes(Notes notes, NoteType noteType);

	void setNotes(Notes notes, NoteType noteType, int maxCharCount,
			int maxLineCount);

	Notes getNotes();

	boolean isInit();

	boolean isDirty();

	void addNotesSaveHandler(NotesSaveHandler handler);

	void removeNotesSaveHandler();

}
