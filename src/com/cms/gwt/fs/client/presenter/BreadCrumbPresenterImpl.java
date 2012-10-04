package com.cms.gwt.fs.client.presenter;

import java.util.List;

import com.cms.gwt.fs.client.model.breadcrumb.BreadCrumb;
import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.inject.Inject;

/**
 * Bread Crumb presenter's implementation.
 * 
 */
public class BreadCrumbPresenterImpl implements BreadCrumbPresenter {

	private final View view;

	@Inject
	public BreadCrumbPresenterImpl(HandlerManager eventBus, View view) {
		this.view = view;
	}

	/**
	 * {@inheritDoc}
	 */
	public void showBreadCrumb(List<BreadCrumb> breadCrumbs) {
		showBreadCrumb(breadCrumbs, false);
	}

	/**
	 * {@inheritDoc}
	 */
	public void showBreadCrumb(List<BreadCrumb> breadCrumbs, boolean append) {
		final String arrows = "&nbsp;&raquo;&nbsp;";

		if (append) {
			view.getPanel().add(new HTML(arrows));
		} else {
			reset();
		}

		int size = breadCrumbs.size() - 1;
		if (size < 0) {
			return;
		}

		for (int i = 0; i < size; i++) {
			BreadCrumb breadCrumb = breadCrumbs.get(i);
			if (breadCrumb.getLink().equals("")) {
				// plain text
				view.getPanel().add(new HTML(breadCrumb.getText()));

			} else {
				// link
				view.getPanel().add(
						new Hyperlink(breadCrumb.getText(), breadCrumb
								.getLink()));
				view.getPanel().add(new HTML(arrows));
			}
		}

		// always plain text for last bread-crumb
		BreadCrumb breadCrumb = breadCrumbs.get(size);
		view.getPanel().add(new HTML(breadCrumb.getText()));

	}

	/**
	 * {@inheritDoc}
	 */
	public BaseView getView() {
		return view;
	}

	/**
	 * {@inheritDoc}
	 */
	public BaseView getPanelView() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setVisible(boolean visible) {
		view.getWidget().setVisible(visible);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setPanelVisible(boolean visible) {
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset() {
		view.reset();
	}

	/**
	 * {@inheritDoc}
	 */
	public void resetPanel() {
	}
}
