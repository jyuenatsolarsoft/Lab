package com.cms.gwt.fs.client.presenter;

import java.util.HashMap;
import java.util.Map;

import com.allen_sauer.gwt.log.client.Log;
import com.cms.gwt.common.client.ExtCmsMessageBox;
import com.cms.gwt.fs.client.HistoryConstants;
import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.exception.ActionNotSupportedException;
import com.cms.gwt.fs.client.model.ListenerInfo;
import com.cms.gwt.fs.client.model.skill.RequiredSkill;
import com.cms.gwt.fs.client.model.skill.RequiredSkillsList;
import com.cms.gwt.fs.client.model.skill.RequiredSkillsListEntry;
import com.cms.gwt.fs.client.model.skill.SkillsCodeInfo;
import com.cms.gwt.fs.client.notes.Notes;
import com.cms.gwt.fs.client.notes.Notes.NotesType;
import com.cms.gwt.fs.client.presenter.NotesWidgetPresenter.NoteType;
import com.cms.gwt.fs.client.rpc.ActionServices;
import com.cms.gwt.fs.client.rpc.action.GetNotes;
import com.cms.gwt.fs.client.rpc.action.GetServiceTicketSkill;
import com.cms.gwt.fs.client.rpc.action.GetServiceTicketSkillsList;
import com.cms.gwt.fs.client.rpc.action.GetSkillsCodeInfo;
import com.cms.gwt.fs.client.rpc.callback.GotNotes;
import com.cms.gwt.fs.client.rpc.callback.GotServiceTicketSkill;
import com.cms.gwt.fs.client.rpc.callback.GotServiceTicketSkillsList;
import com.cms.gwt.fs.client.rpc.callback.GotSkillsCodeInfo;
import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.ListBox;
import com.google.inject.Inject;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.Record;
import com.gwtext.client.util.Format;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.event.GridRowListenerAdapter;

/**
 * Skills presenter's implementation.
 * <p />
 * This class has 2 views: Grid View displayed in a tab, and Panel View
 * displayed separately in panel.
 * 
 */
public class SkillsPresenterImpl extends BasePresenterImpl implements
		SkillsPresenter {

	private final GridView gridView;
	private final PanelView panelView;
	private final NotesWidgetPresenter notesWidgetPresenter;
	private final TextConstants txtConsts;

	private HandlerRegistration btnBackHandler;
	private HandlerRegistration tabPanelHandler;

	private boolean areListenersRegistered;
	private boolean areGridListenersRegistered;
	private boolean isNotesInit;

	private ListenerInfo listenerInfo;

	/**
	 * Constructor.
	 * 
	 * @param eventBus
	 *            The Events Manager
	 * @param gridView
	 *            Grid View of Skills
	 * @param panelView
	 *            Panel View of a Skill
	 */
	@Inject
	public SkillsPresenterImpl(GridView gridView, PanelView panelView,
			NotesWidgetPresenter notesWidgetPresenter) {

		this.gridView = gridView;
		this.panelView = panelView;
		this.notesWidgetPresenter = notesWidgetPresenter;
		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		// put the notes in the tab
		panelView.getTabPanel().add(notesWidgetPresenter.getView().getWidget(),
				txtConsts.SkillsNotes());

		areListenersRegistered = false;
		areGridListenersRegistered = false;

		isNotesInit = false;
		listenerInfo = new ListenerInfo();
	}

	/**
	 * {@inheritDoc}
	 */
	public void showSkillsGrid(String ticketNumber, String panelNumber) {
		getSkillsGrid(ticketNumber, panelNumber);
	}

	/**
	 * {@inheritDoc}
	 */
	public void showSkillsPanel(String ticketNumber, String panelNumber,
			String skillId) {
		getSkillsPanel(ticketNumber, panelNumber, skillId);
	}

	/**
	 * {@inheritDoc}
	 */
	public BaseView getView() {
		return gridView;
	}

	/**
	 * {@inheritDoc}
	 */
	public BaseView getPanelView() {
		return panelView;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset() {
		unRegisterListenersGrid();
		gridView.clearSkillsGrid();
	}

	/**
	 * {@inheritDoc}
	 */
	public void resetPanel() {
		unRegisterListenersPanel();
		panelView.reset();
		notesWidgetPresenter.reset();

		isNotesInit = false;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setVisible(boolean visible) {
		gridView.getWidget().setVisible(visible);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setPanelVisible(boolean visible) {
		panelView.getWidget().setVisible(visible);
	}

	/**
	 * Make RPC to get data for skills grid.
	 * 
	 * @param ticketId
	 *            The key of the service ticket.
	 */
	private void getSkillsGrid(String ticketNumber, String panelNumber) {
		getSkillsGrid(GetServiceTicketSkillsList.newInstance(ticketNumber),
				panelNumber);
	}

	/**
	 * Make RPC to get data for skills grid.
	 * 
	 * @param action
	 *            containing the details of the requested action.
	 */
	private void getSkillsGrid(GetServiceTicketSkillsList action,
			String panelNumber) {
		try {
			ActionServices.App.getInstance().execute(action,
					new GetSkillsGridCallback(panelNumber));
		} catch (ActionNotSupportedException e) {			
			final String ERR_MSG = "Unable to retrieve ticket skills list.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Make RPC to get data for skills panel.
	 */
	private void getSkillsPanel(String ticketNumber, String panelNumber,
			String skillId) {

		Map<String, String> key = new HashMap<String, String>();

		key.put(Notes.NotesType.SKILL.getKeyFieldNames()[0], ticketNumber);

		getSkillsPanel(
				GetServiceTicketSkill.newInstance(ticketNumber, skillId),
				GetSkillsCodeInfo.newInstance(skillId), ticketNumber,
				panelNumber);
	}

	/**
	 * Make RPC to get data for skills panel.
	 */
	private void getSkillsPanel(GetServiceTicketSkill getSkillAction,
			GetSkillsCodeInfo getSkillCodeAction, String ticketNumber,
			String panelNumber) {
		try {
			ActionServices.App.getInstance().execute(getSkillAction,
					new GetSkillsPanelCallback(ticketNumber, panelNumber));

			ActionServices.App.getInstance().execute(getSkillCodeAction,
					new GetSkillsDescriptionCallback());

		} catch (ActionNotSupportedException e) {			
			final String ERR_MSG = "Unable to retrieve skills panel.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Display the skill grid.
	 * 
	 * @param skillsList
	 *            The skill list to be displayed.
	 * @param ticketNumber
	 *            The ticket number which the skill list belongs to.
	 */
	private void renderGrid(RequiredSkillsList skillsList, String panelNumber,
			String ticketNumber) {

		Object[][] skills = new Object[skillsList
				.getRequiredSkillsListEntries().size()][];

		int i = 0;
		for (RequiredSkillsListEntry entry : skillsList
				.getRequiredSkillsListEntries()) {
			skills[i++] = new String[] { entry.getSkill(),
					entry.getDescription(), entry.getLevelRequired() };
		}

		String uniqueId = gridView.initSkillsGrid(skills);

		registerListenersGrid(ticketNumber, panelNumber, uniqueId);
	}

	/**
	 * Display skills panel.
	 */
	private void renderPanel(String ticketNumber, String panelNumber,
			RequiredSkill skill) {

		// TODO: consider creating our own interface instead of down-casting it?
		((ListBox) panelView.getLstSkill()).addItem(skill.getSkillCode());

		panelView.getTxtLevelRequired().setText(
				Integer.toString(skill.getLevelRequired()));
		// view.
		registerListenersPanel(ticketNumber, panelNumber, skill.getSkillCode());
	}

	/**
	 * Display skills panel.
	 */
	private void renderSkillDescription(SkillsCodeInfo skillsCodeInfo) {

		panelView.getTxtSkill1().setText(skillsCodeInfo.getDescription1());
		panelView.getTxtSkill2().setText(skillsCodeInfo.getDescription2());
		panelView.getTxtSkill3().setText(skillsCodeInfo.getDescription3());

	}

	/**
	 * Display notes in the tab panel
	 */
	private void renderSkillNotes(Notes notes) {
		notesWidgetPresenter.setNotes(notes, NoteType.BASIC);
	}

	/**
	 * Register Listeners for skills grid.
	 * 
	 * @param uniqueId
	 *            Skills unique identifier.
	 */
	private void registerListenersGrid(final String ticketNumber,
			final String panelNumber, final String uniqueId) {

		listenerInfo.setTicketNumber(ticketNumber);
		listenerInfo.setPanelNumber(panelNumber);
		listenerInfo.setUniqueId(uniqueId);

		// only register grid listeners once
		if (areGridListenersRegistered) {
			return;
		}

		gridView.getSkillsGrid().addGridRowListener(
				new GridRowListenerAdapter() {
					@Override
					public void onRowDblClick(GridPanel grid, int rowIndex,
							EventObject e) {
						gridDblClick(grid, rowIndex);
					}
				});

		areGridListenersRegistered = true;
	}

	/**
	 * Clean-up listeners for skills grid.
	 */
	private void unRegisterListenersGrid() {
		if (!areGridListenersRegistered) {
			return;
		}

		// gridView.getSkillsGrid().purgeListeners();
		// areGridListenersRegistered = false;
	}

	/**
	 * Register listeners for skills panel.
	 * 
	 * @param ticketId
	 *            Ticket's unique identifier.
	 * 
	 */
	private void registerListenersPanel(final String ticketNumber,
			final String panelNumber, final String skillCode) {

		if (areListenersRegistered) {
			return;
		}

		btnBackHandler = panelView.getBtnBack().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						History.newItem(Format.format(
								HistoryConstants.SERVICE_TICKET_VALUE,
								ticketNumber)
								+ Format.format(
										HistoryConstants.TAB_PANEL_VALUE,
										panelNumber));
					}
				});

		tabPanelHandler = panelView.getTabPanel().addSelectionHandler(
				new SelectionHandler<Integer>() {
					public void onSelection(SelectionEvent<Integer> event) {
						setPanelContent(event.getSelectedItem(), skillCode);
					}
				});

		areListenersRegistered = true;
	}

	/**
	 * Remove listeners for skills panel.
	 */
	private void unRegisterListenersPanel() {
		if (!areListenersRegistered) {
			return;
		}

		btnBackHandler.removeHandler();
		tabPanelHandler.removeHandler();

		areListenersRegistered = false;
	}

	/**
	 * Sets the appropriate content in the panel (identified by panelNumber) of
	 * the tabs.
	 * 
	 * @param panelNumber
	 *            the panel number in which to set content
	 * @param skillCode
	 *            The skill Code.
	 */
	private void setPanelContent(int panelNumber, String skillCode) {
		switch (panelNumber) {
		case 0:
			// skills tab
			// leave skills with the old data now
			break;
		case 1:
			// notes tab
			if (!isNotesInit) {
				getSkillPanelNotes(skillCode);
			}
			isNotesInit = true;
			break;
		}
	}

	/**
	 * Retrieve the notes from backend.
	 * 
	 * @param skillCode
	 */
	private void getSkillPanelNotes(String skillCode) {
		Map<String, String> keys = new HashMap<String, String>();
		keys.put(NotesType.SKILL.getKeyFieldNames()[0], skillCode);

		try {
			ActionServices.App.getInstance().execute(
					GetNotes.newInstance(Notes.NotesType.SKILL, keys),
					new GetSkillNotesCallback());
		} catch (ActionNotSupportedException e) {
			final String ERR_MSG = "Unable to get skill notes.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}

	/**
	 * Call-Back class to get the skill grid data.
	 * 
	 */
	protected class GetSkillsGridCallback extends GotServiceTicketSkillsList {

		private final String panelNumber;

		public GetSkillsGridCallback(String panelNumber) {
			this.panelNumber = panelNumber;
		}

		public void got(RequiredSkillsList skillsList, String ticketNumber) {
			renderGrid(skillsList, panelNumber, ticketNumber);
		}

	}

	/**
	 * Call-Back class to get the skill grid data.
	 * 
	 */
	protected class GetSkillsPanelCallback extends GotServiceTicketSkill {

		private String panelNumber;
		private String ticketNumber;

		public GetSkillsPanelCallback(String ticketNumber, String panelNumber) {
			this.panelNumber = panelNumber;
			this.ticketNumber = ticketNumber;
		}

		public void got(RequiredSkill skill) {
			renderPanel(ticketNumber, panelNumber, skill);
		}
	}

	/**
	 * Call back class to get the code descriptions.
	 * 
	 */
	protected class GetSkillsDescriptionCallback extends GotSkillsCodeInfo {

		public void got(SkillsCodeInfo skillsCodeInfo) {
			renderSkillDescription(skillsCodeInfo);
		}
	}

	private void gridDblClick(GridPanel grid, int rowIndex) {
		Record selected = grid.getStore().getAt(rowIndex);
		final String skillId = selected.getAsString(listenerInfo.getUniqueId());

		History.newItem(Format.format(HistoryConstants.SKILLS_PANEL_VALUE,
				listenerInfo.getTicketNumber(), listenerInfo.getPanelNumber(),
				skillId));
	}

	/**
	 * Callback class to handle the Skill Notes.
	 * 
	 */
	protected class GetSkillNotesCallback extends GotNotes {
		@Override
		public void got(Notes notes) {
			renderSkillNotes(notes);
		}
	}

}
