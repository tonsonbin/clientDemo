package com.tb.pro.main.base;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import com.tb.pro.common.BasePanel;
import com.tb.pro.main.base.LeftPanel;
import com.tb.pro.main.base.Main;
import com.tb.pro.utils.JTreeBean;
import com.tb.pro.utils.JTreeUtils;

/**
 * 菜单配置panel
 * @author tb
 *
 */
public class menuPanel extends BasePanel{
	
	
	public menuPanel(Object parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addCompont() {
		
		JTreeUtils jTreeUtils = new JTreeUtils();
		//============菜单配置 start==============================
		jTreeUtils.putItem("系统管理", "sysmanager", ""
				, new JTreeUtils()
				.putItem("用户管理", "usermanager", "userListPanel", null)
				.putItem("机构管理", "officemanager", "officeListPanel", null)
				.getJTreeBeans())
		.putItem("商品管理", "goodsmanager", ""
				, new JTreeUtils()
				.putItem("商品列表", "goodsList", "", null)
				.putItem("商品分类", "goodsCategory", "", null)
				.getJTreeBeans());
		//============菜单配置 end==============================
		
		JTree jTree = JTreeUtils.getJTree(jTreeUtils.getJTreeBeans());
		JScrollPane jScrollPane = new JScrollPane(jTree);
		
		jPanel.setLayout ( new  BoxLayout (jPanel, BoxLayout.X_AXIS));
		jPanel.add(jScrollPane);
		
		setComponent("jTree", jTree);
		setComponent("jScrollPane", jScrollPane);
	}

	@Override
	protected void addListener() {
		
		  JTree jTree = (JTree) getComponent("jTree");
		  jTree.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(e.getSource() == jTree) {//切换数据视图
					JTreeBean selectedNode = (JTreeBean) jTree.getLastSelectedPathComponent();
					if (selectedNode.getChildCount() == 0) {
						
						String panelName = selectedNode.getPanelName();
						//切换右侧显示panel
						LeftPanel leftPanel = (LeftPanel) parent;
						Main main = (Main) leftPanel.parent;
						main.rightPanel.changePanel(panelName);
					}
				}
				
			}
			  
		  });
		
	}

	

	
}
