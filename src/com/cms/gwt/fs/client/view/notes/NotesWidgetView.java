package com.cms.gwt.fs.client.view.notes;

import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.presenter.NotesWidgetPresenter;
import com.cms.gwt.fs.client.presenter.NotesWidgetPresenter.NoteType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Notes Widget
 */
public class NotesWidgetView extends Composite implements
		NotesWidgetPresenter.View {

	protected final HorizontalPanel panel;
	protected final VerticalPanel btnPanel;
	protected final TextConstants txtConsts;

	protected final HasHTML lblPageNum;
	protected final HTMLPanel notesPanel;
	protected TextBox[] txtNotes;

	protected final HasClickHandlers btnPageUp;
	protected final HasClickHandlers btnPageDown;
	protected final HasClickHandlers btnClear;
	protected final HasClickHandlers btnSave;

	protected NoteType noteType;
	protected String _id;

	/**
	 * Constructor.
	 */
	public NotesWidgetView() {
		_id = String.valueOf(Random.nextInt(999999999));

		panel = new HorizontalPanel();
		initWidget(panel);

		btnPanel = new VerticalPanel();
		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		lblPageNum = new HTML(txtConsts.PageNum("0", "0"));
		notesPanel = new HTMLPanel("<div id=\"" + _id + "\"></div>");
		notesPanel.setStyleName("FSNotes-Panel");

		btnPageUp = new Button(txtConsts.PageUp());
		btnPageDown = new Button(txtConsts.PageDown());
		btnClear = new Button(txtConsts.Clear());
		btnSave = new Button(txtConsts.Save());

		layout();

	}

	/**
	 * {@inheritDoc}
	 */
	public Widget getWidget() {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset() {
		notesPanel.clear();
		lblPageNum.setHTML(txtConsts.PageNum("0", "0"));
		disableButtons();
	}

	/**
	 * {@inheritDoc}
	 */
	public void setReadOnly(boolean readOnly) {
		for (TextBox txtNote : txtNotes) {
			if (txtNote != null) {
				txtNote.setReadOnly(readOnly);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void resetNotes() {
		for (TextBox txtNote : txtNotes) {
			if (txtNote != null) {
				txtNote.setText("");
			}
		}
	}

	/**
	 * Ideally, only this method should be called to set all the data. The
	 * method will then determine whether to enable or disable buttons.
	 * 
	 * @param text
	 *            The text to display
	 * @param totalPages
	 *            Total number of pages available
	 * @param currPage
	 *            Current page number
	 */
	public void setNotes(String[] text, int totalPages, int currPage) {

		// make text-boxes
		int txtLen = text.length, i = 0;
		for (TextBox txtNote : txtNotes) {
			if (txtNote != null) {
				if (i < txtLen) {
					txtNote.setText(text[i]);
				} else {
					txtNote.setText("");
				}
				i++;
			}
		}

		int maxPage = (currPage > totalPages) ? currPage : totalPages;

		lblPageNum.setHTML(txtConsts.PageNum(String.valueOf(currPage), String
				.valueOf(maxPage)));

		((Button) btnPageUp).setEnabled(currPage > 1);

		if (noteType.equals(NoteType.ADVANCED)) {
			((Button) btnPageDown).setEnabled(true); // can go to any page #
			((Button) btnClear).setEnabled(true);
			((Button) btnSave).setEnabled(true);
		} else {
			((Button) btnPageDown).setEnabled(currPage < totalPages);
		}
	}

	public void setNoteType(NoteType noteType, int maxCharCount,
			int maxLineCount) {

		// makes notes line
		notesPanel.clear();
		txtNotes = new TextBox[maxLineCount];
		for (int i = 0; i < maxLineCount; i++) {
			txtNotes[i] = new TextBox();
			txtNotes[i].setMaxLength(maxCharCount);
			txtNotes[i].getElement().setAttribute("size",
					String.valueOf(maxCharCount));
			txtNotes[i].setStyleName("TextBox");

			if (i == 0) {
				txtNotes[i].addStyleName("TextBox-Top");
			} else if (i == maxLineCount - 1) {
				txtNotes[i].addStyleName("TextBox-Btm");
			} else {
				txtNotes[i].addStyleName("TextBox-Mid");

			}

			notesPanel.add(txtNotes[i], _id);
		}

		this.noteType = noteType;

		if (noteType.equals(NoteType.ADVANCED)) {
			((Button) btnClear).setVisible(true);
			((Button) btnSave).setVisible(true);
			setReadOnly(false);

		} else {
			((Button) btnClear).setVisible(false);
			((Button) btnSave).setVisible(false);
			setReadOnly(true);
		}
	}

	public HasClickHandlers getBtnPageUp() {
		return btnPageUp;
	}

	public HasClickHandlers getBtnPageDown() {
		return btnPageDown;
	}

	public HasClickHandlers getBtnClear() {
		return btnClear;
	}

	public HasClickHandlers getBtnSave() {
		return btnSave;
	}

	public TextBox[] getTxtNotes() {
		return txtNotes;
	}

	/**
	 * Arrange widgets in a nice layout
	 */
	private void layout() {
		panel.addStyleName("FSNotes");

		VerticalPanel textPanel = new VerticalPanel();
		textPanel.setSpacing(0);
		panel.add(textPanel);
		panel.setCellWidth(textPanel, "615px");

		textPanel.add((Widget) lblPageNum);
		((HTML) lblPageNum).setStyleName("PageNum");
		textPanel.setCellHorizontalAlignment((Widget) lblPageNum,
				HasHorizontalAlignment.ALIGN_CENTER);

		textPanel.add(notesPanel);

		panel.add(btnPanel);
		panel.setCellVerticalAlignment(btnPanel,
				HasVerticalAlignment.ALIGN_MIDDLE);
		btnPanel.addStyleName("BtnPanel");
		btnPanel.add((Widget) btnPageUp);
		btnPanel.add((Widget) btnPageDown);
		btnPanel.add(new HTML("&nbsp;"));
		btnPanel.add((Widget) btnClear);
		btnPanel.add((Widget) btnSave);

		disableButtons();
		((Button) btnClear).setVisible(false);
		((Button) btnSave).setVisible(false);
	}

	private void disableButtons() {
		((Button) btnPageUp).setEnabled(false);
		((Button) btnPageDown).setEnabled(false);
		((Button) btnClear).setEnabled(false);
		((Button) btnSave).setEnabled(false);
	}
}