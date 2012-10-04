package com.cms.gwt.fs.client.model.breadcrumb;

/**
 * Actual model of BreadCrumb composed of a Text and an associated Link to make
 * a hyperlink for display.
 * 
 */
public class BreadCrumb {

	private String text;
	private String link;

	public BreadCrumb(String text) {
		this(text, "");
	}

	public BreadCrumb(String text, String link) {
		this.text = text;
		this.link = link;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
