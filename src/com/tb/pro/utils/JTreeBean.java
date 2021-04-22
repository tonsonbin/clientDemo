package com.tb.pro.utils;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

public class JTreeBean extends DefaultMutableTreeNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JTreeBean() {
	}
	
	public JTreeBean(Object userObject) {
		super(userObject);
	}
	
	private String labelName;
	private String name;
	private String panelName;
	
	private List<JTreeBean> children;
	
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPanelName() {
		return panelName;
	}
	public void setPanelName(String panelName) {
		this.panelName = panelName;
	}
	public List<JTreeBean> getChildren() {
		return children;
	}
	public void setChildren(List<JTreeBean> children) {
		this.children = children;
	}
	
	
}
