package com.tb.pro.main.base;

import java.awt.CardLayout;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JPanel;

import org.apache.commons.lang.StringUtils;

import com.tb.pro.common.BasePanel;
import com.tb.pro.main.OfficeListPanel;
import com.tb.pro.main.UserListPanel;

/**
 * 右侧数据窗口
 * @author tb
 *
 */
public class RightPanel extends BasePanel{
	
	public RightPanel(Object parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void init() {

		jPanel.setLayout(new CardLayout());
		
	}

	@Override
	protected void addCompont() {
		
		UserListPanel UserListPanel = new UserListPanel(this);
		setComponent("userListPanel", UserListPanel);
		JPanel ulPanel = UserListPanel.getPanel();
		
		OfficeListPanel officeListPanel = new OfficeListPanel(this);
		setComponent("officeListPanel", officeListPanel);
		JPanel olPanel = officeListPanel.getPanel();

		jPanel.add(ulPanel);
		jPanel.add(olPanel);

		jPanel.repaint();
	}


	public void changePanel(String panelName) {
		
		if (StringUtils.isBlank(panelName)) {
			showMessage("panelName 为空");
			return;
		}
		if (components == null) {
			showMessage("rightPanel中的没有数据视图面板");
			return;
		}
		if (!components.containsKey(panelName)) {
			showMessage("未找到名称为："+panelName+"的数据面板");
			return;
		}
		
		Set<String> keys = components.keySet();
		Iterator<String> tIterator = keys.iterator();
		
		BasePanel panel = null;
		while (tIterator.hasNext()) {
			
			String key = tIterator.next();
			panel = (BasePanel) components.get(key);
			if (panelName.equals(key)) {
				panel.getPanel().repaint();
				panel.getPanel().setVisible(true);
				
			}else panel.getPanel().setVisible(false);
			
		}

		jPanel.repaint();
	}

	@Override
	public JPanel reloadData() {
		
		return jPanel;
	}
	
}
