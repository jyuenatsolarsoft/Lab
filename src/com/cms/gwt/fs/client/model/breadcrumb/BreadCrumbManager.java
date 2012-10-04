package com.cms.gwt.fs.client.model.breadcrumb;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Class responsible for storing and maintaining the web directory structure.
 * Useful for obtaining valid BreadCrumbs based on a given location/key in web.
 * 
 */
public class BreadCrumbManager {

	/**
	 * The root/parent node.
	 */
	private BreadCrumbNode root;

	/**
	 * Constructor.
	 */
	public BreadCrumbManager() {
		root = new BreadCrumbNode(".", null, null);
	}

	/**
	 * Attach the BreadCrumb using node (with unique name) to the node (with
	 * parent name).
	 * 
	 * @param parentName
	 *            The parent node to which to attach this node.
	 * @param name
	 *            The unique name of this node.
	 * @param value
	 *            BrearCrumb of this node.
	 * @return True if insertion is successful, otherwise false.
	 */
	public boolean insert(String parentName, String name, BreadCrumb value) {
		boolean rval = false;
		BreadCrumbNode parent;
		if ((parent = find(parentName)) != null) {
			BreadCrumbNode child = new BreadCrumbNode(name, value, parent);
			parent.addChild(child);
			rval = true;
		}
		return rval;
	}

	/**
	 * Returns list of BreadCrumbs starting from the node of given name up until
	 * the root node.
	 * 
	 * @param name
	 *            Node at which to start making bread crumbs.
	 * @return List of BreadCrumbs from this node till root node.
	 */
	public List<BreadCrumb> getBreadCrumbs(String name) {
		return getBreadCrumbs(name, new String[][] {});
	}

	/**
	 * Overloaded method.
	 * 
	 * @param name
	 *            Node at which to start making bread crumbs.
	 * @param args
	 *            Replace ### in BreadCrumb with args. args are tuples of [text,
	 *            link]
	 * 
	 * @return List of BreadCrumbs from this node till root node.
	 */
	public List<BreadCrumb> getBreadCrumbs(String name, String[][] args) {
		List<BreadCrumb> breadCrumbs = new ArrayList<BreadCrumb>();

		// args contains tuples of [text, value]

		BreadCrumbNode child;
		if ((child = find(name)) != null) {

			int i = 0;
			while (!child.getName().equals(".")) {

				BreadCrumb oldBreadCrumb = child.getValue();
				BreadCrumb newBreadCrumb = new BreadCrumb("");

				if (args.length > i) {
					// args are present for this bread crumb
					newBreadCrumb.setText(oldBreadCrumb.getText().replaceFirst(
							"###", args[i][0]));
					newBreadCrumb.setLink(oldBreadCrumb.getLink().replaceFirst(
							"###", args[i][1]));

				} else {
					// no args
					newBreadCrumb.setText(oldBreadCrumb.getText());
					newBreadCrumb.setLink(oldBreadCrumb.getLink());

				}

				breadCrumbs.add(0, newBreadCrumb);
				child = child.getParent();
				i++;
			}
		}

		return breadCrumbs;
	}

	/**
	 * Searches and returns the node with the given name.
	 * 
	 * @param name
	 *            The node name to find.
	 * @return BreadCrumbNode if search is successful, otherwise null.
	 */
	public BreadCrumbNode find(String name) {
		return find(root, name);
	}

	/**
	 * Returns a String representation of the current web structure.
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		Queue<BreadCrumbNode> queue = new LinkedList<BreadCrumbNode>();
		queue.offer(root);

		String comma = "";
		while (queue.size() > 0) {
			BreadCrumbNode parent = queue.poll();
			sb.append(comma).append(parent.getName());
			for (BreadCrumbNode child : parent.getChilds()) {
				queue.offer(child);
			}
			comma = " ";
		}
		return sb.toString();
	}

	/**
	 * Find the node with the given name. Recursive search method.
	 * 
	 * @param parent
	 *            The parent node.
	 * @param name
	 *            The node name to search for.
	 * @return BreadCrumbNode with the given name if successful, otherwise
	 *         null.
	 */
	private BreadCrumbNode find(BreadCrumbNode parent, String name) {
		if (parent.getName().equals(name)) {
			return parent;
		}

		for (BreadCrumbNode child : parent.getChilds()) {
			BreadCrumbNode breadCrumbNode;
			if ((breadCrumbNode = find(child, name)) != null) {
				return breadCrumbNode;
			}
		}

		return null;
	}
}
