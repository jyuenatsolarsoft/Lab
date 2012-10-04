package com.cms.gwt.fs.client;

import com.cms.gwt.common.client.UserDetails;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class FSHeader extends Composite {

	/** The About dialog of the application. */ abc
	private static DialogBox aboutDialog;

	/** The main panel of the header. */
	private HorizontalPanel outer;

	/** Contains the user information. */
	private UserDetails userDetails;
	
	protected final TextConstants txtConsts = (TextConstants) GWT
	.create(TextConstants.class);	

	/**
	 * Return the singleton of this class.
	 * 
	 * @param currentViewName
	 * @param userDetails
	 */
	static public FSHeader getInstance(UserDetails userDetails) {

		return new FSHeader(userDetails);
	}

	/**
	 * Private constructor.
	 * 
	 * @param currentViewName
	 * @param userDetails
	 */
	private FSHeader(UserDetails userDetails) {
		super();
		this.userDetails = userDetails;
		init();
	}

	/**
	 * Private default constructor.
	 * <p/>
	 * This is a singleton class and therefore this constructor is disabled.
	 */
	private FSHeader() {

	}

	/**
	 * Initialize the header panel.
	 * 
	 * @param currentViewName
	 */
	private void init() {
		outer = new HorizontalPanel();
		outer.setStyleName("header-panel");
		outer.setWidth("100%");
		outer.setHeight("20px");

		outer.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		outer.add(buildRightPanel());

		initWidget(outer);
	}

	/**
	 * Build the right panel.
	 * 
	 * @return
	 */
	public Panel buildRightPanel() {
		HTML signoutLink = new HTML("<a href='javascript:;'>" + txtConsts.SignOut() + "</a>");
		HTML helpLink = new HTML(
				"<a href=\"http://www.solarsoft.com\"  target=\"_blank\">" + txtConsts.Help() + "</a>");
		HTML aboutLink = new HTML("<a href='javascript:;'>" + txtConsts.About()	 + "</a>");

		HorizontalPanel rightPanel = new HorizontalPanel();
		rightPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		rightPanel.setStyleName("right-panel");
		rightPanel.setWidth("40%");

		rightPanel
				.add(new HTML("<b>" + txtConsts.Greeting() + ", " + userDetails.getUserId() + "</b>"));
		rightPanel.add(new HTML("|"));
		rightPanel.add(signoutLink);
		rightPanel.add(new HTML("|"));
		rightPanel.add(helpLink);
		rightPanel.add(new HTML("|"));
		rightPanel.add(aboutLink);

		signoutLink.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				FieldServices.getInstance().logout();
			}
		});

		aboutLink.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				showAboutDialog();
			}
		});

		return rightPanel;
	}

	/**
	 * Build the about dialog.
	 */
	private DialogBox buildAboutDialog() {
		final DialogBox dialog = new DialogBox();
		dialog.setText(txtConsts.DialogAboutTitle());

		FlexTable layout = new FlexTable();
		layout.setCellPadding(10);
		layout.setCellSpacing(10);

		int y = 0;
		layout.setWidget(y++, 0, new Image("images/solarsoft200px.png"));
		layout.setWidget(y++, 0, new HTML(
				"<p>Solarsoft Field Services Beta</p>"));
		layout.setWidget(y++, 0, new HTML("<a>http://www.solarsoft.com</a>"));
		layout.setWidget(y++, 0, new HTML(
				"<p>Copyright 2009 Solarsoft Business Systems</p>"));
		layout.setWidget(y++, 0, new HTML("<p>All rights reserved.</p>"));

		Button btnOk = new Button("OK");
		btnOk.addClickListener(new ClickListener() {
			public void onClick(Widget widget) {
				dialog.hide();
			}
		});

		HorizontalPanel buttonPanel = new HorizontalPanel();
		buttonPanel.add(btnOk);
		buttonPanel.setStyleName("aboutDialogButton");

		FlexTable aboutPanel = new FlexTable();
		aboutPanel.setWidget(1, 1, layout);
		aboutPanel.setWidget(2, 1, buttonPanel);
		aboutPanel.getFlexCellFormatter().setStyleName(2, 1,
				"aboutDialogButtonContainer");

		dialog.add(aboutPanel);
		return dialog;
	}

	/**
	 * Show the about dialog on the screen.
	 */
	private void showAboutDialog() {
		if (aboutDialog == null) {
			aboutDialog = buildAboutDialog();
		}

		aboutDialog.center();
		aboutDialog.show();
	}

}
