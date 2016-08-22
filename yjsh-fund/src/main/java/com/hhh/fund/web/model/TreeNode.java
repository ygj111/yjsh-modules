package com.hhh.fund.web.model;

import java.io.Serializable;

public class TreeNode implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6286907841856886580L;
	private String id;
	
	private String text;
	
	private String parent;
	
	private boolean children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public boolean isChildren() {
		return children;
	}

	public void setChildren(boolean children) {
		this.children = children;
	}
}
