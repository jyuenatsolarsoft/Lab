package com.cms.gwt.fs.client.model.breadcrumb;

import java.util.ArrayList;
import java.util.List;

/**
 * A node representation within a web structure that holds information about its
 * parent node, child nodes and the BreadCrumb value which is identified by the
 * uniquely given name.
 * 
 */
public class BreadCrumbNode {

	/**
	 * The name of this node. Note: must be unique.
	 */
	private String name;

	/**
	 * The BreadCrumb value this node holds .
	 */
	private BreadCrumb value;

	/**
	 * The parent node of this node.
	 */
	private BreadCrumbNode parent;

	/**
	 * All the child nodes of this node.
	 */
	private List<BreadCrumbNode> childs;

	/**
	 * Constructor.
	 * 
	 * @param name
	 *            Unique name of this node.
	 * @param value
	 *            BreadCrumb value of this node.
	 */
	public BreadCrumbNode(String name, BreadCrumb value) {
		this(name, value, null);
	}

	/**
	 * Constructor.
	 * 
	 * @param name
	 *            Unique name of this node.
	 * @param value
	 *            BreadCrumb value of this node.
	 * @param parent
	 *            Parent node of this node.
	 */
	public BreadCrumbNode(String name, BreadCrumb value, BreadCrumbNode parent) {
		childs = new ArrayList<BreadCrumbNode>();

		this.name = name;
		this.value = value;
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public BreadCrumb getValue() {
		return value;
	}

	public BreadCrumbNode getParent() {
		return parent;
	}

	public boolean addChild(BreadCrumbNode child) {
		return childs.add(child);
	}

	public List<BreadCrumbNode> getChilds() {
		return childs;
	}
}
