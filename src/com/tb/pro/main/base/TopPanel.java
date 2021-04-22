package com.tb.pro.main.base;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.tb.pro.common.BasePanel;
import com.tb.pro.utils.JButtonUtils;

/**
 * 顶部菜单窗口
 * @author tb
 *
 */
public class TopPanel extends BasePanel{
	
	
	public TopPanel(JFrame parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}

	protected void init() {
		
		setBorder();
		
	}
	
	protected void addCompont() {
		
		JButton fileButton = JButtonUtils.getButton("文件", Color.LIGHT_GRAY);
		
		this.jPanel.add(fileButton);
		
		setComponent("fileButton", fileButton);
	}

	@Override
	protected void addListener() {
		
		JButton jButton = (JButton) getComponent("fileButton");
		
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				showMessage("这个只是放在这里而已=-=");
				
			}
		});
	}
}
