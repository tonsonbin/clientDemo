package com.tb.pro.main.base;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.tb.pro.common.BasePanel;

/**
 * 左侧菜单窗口
 * @author tb
 *
 */
public class LeftPanel extends BasePanel{
	
	
	public LeftPanel(JFrame parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void init() {
		
		jPanel.setLayout(new BorderLayout());
		
	}

	@Override
	protected void addCompont() {
		
		jPanel.add(new menuPanel(this).getPanel(),BorderLayout.CENTER);
	}
	
}
