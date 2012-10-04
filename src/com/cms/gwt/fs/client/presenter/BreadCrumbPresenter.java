package com.cms.gwt.fs.client.presenter;

import java.util.List;

import com.cms.gwt.fs.client.model.breadcrumb.BreadCrumb;
import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.user.client.ui.HorizontalPanel;

/**
 * Bread Crumb presenter interface.
 * 
 */
public interface BreadCrumbPresenter extends BasePresenter {

	interface View extends BaseView {

		HorizontalPanel getPanel();

	}

	void showBreadCrumb(List<BreadCrumb> breadCrumbs);

	void showBreadCrumb(List<BreadCrumb> breadCrumbs, boolean append);

}
