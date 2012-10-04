package com.cms.gwt.fs.client.presenter;

import java.util.ArrayList;
import java.util.List;

import com.cms.gwt.common.client.ExtCmsMessageBox;
import com.cms.gwt.common.client.CmsMessageBox.CmsConfirmCallback;
import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.notes.Notes;
import com.cms.gwt.fs.client.notes.NotesLine;
import com.cms.gwt.fs.client.notes.NotesPage;
import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.TextBox;
import com.google.inject.Inject;

public class NotesWidgetPresenterImpl implements NotesWidgetPresenter {

	private final View view;
	private final TextConstants txtConsts;

	private int maxCharCount;
	private int maxLineCount;
	private int currPageNum;
	private int maxPageNum;
	private boolean isInit;
	private boolean isDirty;

	private Notes notes;
	private NoteType noteType;

	private boolean areListenersRegistered;
	private HandlerRegistrationArray[] txtNotesChangeHandlers;
	private HandlerRegistrationArray[] txtNotesKeyHandlers;
	private HandlerRegistration btnPageUpHandler;
	private HandlerRegistration btnPageDownHandler;
	private HandlerRegistration btnClearHandler;
	private HandlerRegistration btnSaveHandler;
	private NotesSaveHandler notesSaveHandler;

	@Inject
	public NotesWidgetPresenterImpl(View view) {
		this.view = view;
		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		initVars();
	}

	public BaseView getView() {
		return view;
	}

	public BaseView getPanelView() {
		return null;
	}

	public void reset() {
		unRegisterListeners();
		view.reset();
		initVars();
	}

	public void resetPanel() {
	}

	public void setVisible(boolean visible) {
		view.getWidget().setVisible(visible);
	}

	public void setPanelVisible(boolean visible) {
	}

	public Notes getNotes() {
		return notes;
	}

	public boolean isDirty() {
		return isDirty;
	}

	public boolean isInit() {
		return isInit;
	}

	public void setNotes(Notes notes, NoteType noteType) {
		setNotes(notes, noteType, MAX_CHAR_COUNT, MAX_LINE_COUNT);
	}

	public void setNotes(Notes notes, NoteType noteType, int maxCharCount,
			int maxLineCount) {

		view.setNoteType(noteType, maxCharCount, maxLineCount);

		this.notes = notes;
		this.noteType = noteType;
		this.maxCharCount = maxCharCount;
		this.maxLineCount = maxLineCount;

		currPageNum = MIN_PAGE_NUM;
		maxPageNum = notes.getLastPageNum();

		updateNotesView();
		registerListeners();

		isDirty = false;
		isInit = true;
	}

	public void addNotesSaveHandler(NotesSaveHandler notesSaveHandler) {
		this.notesSaveHandler = notesSaveHandler;
	}

	public void removeNotesSaveHandler() {
		this.notesSaveHandler = null;
	}

	private void initVars() {
		maxCharCount = 0;
		maxLineCount = 0;
		currPageNum = MIN_PAGE_NUM;
		maxPageNum = MIN_PAGE_NUM;
		isInit = false;
		isDirty = false;
		areListenersRegistered = false;
	}

	private void registerListeners() {

		if (areListenersRegistered) {
			return;
		}

		final TextBox[] txtNotes = view.getTxtNotes();
		final int notesLen = txtNotes.length;

		txtNotesKeyHandlers = new HandlerRegistrationArray[notesLen];
		txtNotesChangeHandlers = new HandlerRegistrationArray[notesLen];
		for (int i = 0; i < notesLen; i++) {
			txtNotesKeyHandlers[i] = new HandlerRegistrationArray();
			final int txtNoteNum = i;
			txtNotesKeyHandlers[i].addHandler(txtNotes[txtNoteNum]
					.addKeyDownHandler(new KeyDownHandler() {
						public void onKeyDown(KeyDownEvent event) {
							int pressed = event.getNativeEvent().getKeyCode();
							if (pressed == KeyCodes.KEY_UP && txtNoteNum > 0) {
								txtNotes[txtNoteNum - 1].setFocus(true);

							} else if (pressed == KeyCodes.KEY_DOWN
									&& txtNoteNum < notesLen - 1) {
								txtNotes[txtNoteNum + 1].setFocus(true);
							}
						}
					}));

			txtNotesChangeHandlers[i] = new HandlerRegistrationArray();
			txtNotesChangeHandlers[i].addHandler(txtNotes[txtNoteNum]
					.addChangeHandler(new ChangeHandler() {
						public void onChange(ChangeEvent event) {
							isDirty = true;
						}
					}));

		}

		btnPageUpHandler = view.getBtnPageUp().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						if (isDirty) {
							confirmSave(-1);
						} else {
							currPageNum--;
							updateNotesView();
						}
					}
				});

		btnPageDownHandler = view.getBtnPageDown().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						if (isDirty) {
							confirmSave(1);
						} else {
							currPageNum++;
							updateNotesView();
						}
					}
				});

		btnClearHandler = view.getBtnClear().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						view.resetNotes();
						isDirty = true;
					}
				});

		btnSaveHandler = view.getBtnSave().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				saveNotes();
			}
		});

		areListenersRegistered = true;
	}

	private void unRegisterListeners() {

		if (!areListenersRegistered) {
			return;
		}

		for (HandlerRegistrationArray txtNotesKeyHandler : txtNotesKeyHandlers) {
			txtNotesKeyHandler.removeHandler();
		}
		for (HandlerRegistrationArray txtNotesChangeHandler : txtNotesChangeHandlers) {
			txtNotesChangeHandler.removeHandler();
		}
		btnPageUpHandler.removeHandler();
		btnPageDownHandler.removeHandler();
		btnClearHandler.removeHandler();
		btnSaveHandler.removeHandler();
		removeNotesSaveHandler();

		areListenersRegistered = false;
	}

	private void updateNotesView() {
		String[] texts = new String[maxLineCount];
		for (int i = 0; i < maxLineCount; i++) {
			texts[i] = "";
		}

		NotesPage notesPage = notes.getPage(currPageNum);
		if (notesPage == null) {

			if (noteType.equals(NoteType.BASIC) && maxPageNum == 0) {
				view.setNotes(texts, 0, 0);
			} else {
				view.setNotes(texts, Math.max(maxPageNum, currPageNum),
						currPageNum);
			}
			return;
		}

		List<NotesLine> notesLine = notes.getPage(currPageNum).getLines();
		int currLineNum = MIN_PAGE_NUM;
		for (NotesLine noteLine : notesLine) {
			int lineNum = noteLine.getLineNumber();
			while (currLineNum < lineNum) {
				currLineNum++;
			}
			if ((currLineNum - 1) < maxLineCount) {
				String line = noteLine.getLineText();
				texts[currLineNum - 1] = (line.length() > maxCharCount) ? line
						.substring(0, maxCharCount) : line;
			}
		}

		view.setNotes(texts, maxPageNum, currPageNum);
	}

	private void saveNotes() {
		makeAndSetPage();

		if (notesSaveHandler != null) {
			isDirty = !notesSaveHandler.saveNotes(notes.getPage(currPageNum),
					currPageNum);
			if (currPageNum > maxPageNum) {
				maxPageNum = currPageNum;
			}

		} else {
			ExtCmsMessageBox msg = new ExtCmsMessageBox();
			msg.alert("Error",
					"Programming Error!<br />Missing Notes Save Handler");
		}
	}

	private void makeAndSetPage() {
		List<NotesLine> notesLine = new ArrayList<NotesLine>();
		TextBox[] txtNotes = view.getTxtNotes();
		int currLine = MIN_PAGE_NUM;
		for (TextBox txtNote : txtNotes) {
			if (txtNote != null) {
				String line = txtNote.getText();
				if (!line.equals("")) {
					notesLine.add(new NotesLine(line, currLine));
				}
			}
			currLine++;
		}
		notes.addNotesPage(new NotesPage(currPageNum, notesLine));
	}

	private void confirmSave(int deltaChange) {
		ExtCmsMessageBox box = new ExtCmsMessageBox();
		box.confirm(txtConsts.Save(), txtConsts.NotesSaveConfirm(),
				new SaveConfirmation(deltaChange));

	}

	private class SaveConfirmation implements CmsConfirmCallback {

		private final int deltaChange;

		public SaveConfirmation(int deltaChange) {
			this.deltaChange = deltaChange;
		}

		public void execute(String btnID) {
			if ("yes".equalsIgnoreCase(btnID)) {
				saveNotes();
			}

			isDirty = false;
			currPageNum += deltaChange;
			updateNotesView();
		}
	}

	private class HandlerRegistrationArray {
		private HandlerRegistration handler;

		public HandlerRegistrationArray() {
		}

		public void addHandler(HandlerRegistration handler) {
			this.handler = handler;
		}

		public void removeHandler() {
			handler.removeHandler();
		}
	}

}
