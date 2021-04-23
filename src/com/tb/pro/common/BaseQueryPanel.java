package com.tb.pro.common;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import org.apache.log4j.Logger;

import com.tb.pro.utils.JButtonUtils;

public abstract class BaseQueryPanel extends BasePanel{

	private Logger logger = Logger.getLogger(BaseQueryPanel.class);
	private String objName = this.getClass().getName();
	
	private List<String> queryComponentNames = new ArrayList<String>(); //查询文本控件名
	
	
	private boolean showAddButton = true;
	
	/**
	 * @param parent panel的父级窗口对象
	 * @param x panel位置的起点x坐标
	 * @param y panel位置的起点y坐标
	 * @param width  panel的宽
	 * @param height panel的高
	 */
	public BaseQueryPanel(Object parent,int x,int y, int width, int height) {
		
		super(parent, x, y, width, height);
		
	}
	/**
	 * @param parent panel的父级窗口对象
	 * @param width  panel的宽
	 * @param height panel的高
	 */
	public BaseQueryPanel(Object parent) {
		
		super(parent);
		
	}

	public JPanel getPanel() {
		
		if (this.jPanel != null) {
			return jPanel;
		}
		
		//默认的初始化
		JPanel panel = new JPanel();
		panel.setLocation(x, y);
		panel.setSize(width, height);
		panel.setLayout(new FlowLayout(0));
		panel.setVisible(true);
		Border border = BorderFactory.createEtchedBorder();
		panel.setBorder(border);

		jPanel = panel;
		
		init();
		addCompont();
		addListener();
		
		tAddComponent();
		tAddListener();

		return jPanel;
	};
	
	/**
	 * 添加输入框
	 * @param name 查询字段名
	 * @param labelName label显示字符串
	 */
	protected void addTextFieldComponent(String name,String labelName) {
		
		JLabel bl1=new JLabel(labelName);
		
		bl1.setFont(new Font("宋体",Font.BOLD,20));
		bl1.setForeground(Color.blue);
		setComponent(name+"Label", bl1);
		
		jPanel.add(bl1);
		
		final TextField tField = new TextField("",5);
		jPanel.add(tField);
		setComponent(name+"TextField", tField);
		queryComponentNames.add(name);
	}
	/**
	 * 查询按钮点击事件，true表示进行提交，false表示不提交
	 * @param parameters 所有参数输入的键值，键就是name，值则是输入的值
	 * @return
	 */
	protected boolean queryButtonClick(Map<String,Object> parameters) { return true;};
	/**
	 * 新增按钮
	 */
	protected BasePanel addButtonClick(BasePanel addFromPanel) { return null;};
	
	private void tAddComponent() {
		
		JButton jButton = JButtonUtils.getButton("查询", Color.LIGHT_GRAY);
		setComponent("queryButton", jButton);
		jPanel.add(jButton);

		if (showAddButton) {

			JButton addButton = JButtonUtils.getButton("新增", Color.LIGHT_GRAY);
			setComponent("addButton", addButton);
			jPanel.add(addButton);
			
		}
		
	}
	private void tAddListener() {
		
		((JButton)getComponent("queryButton")).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				Map<String,Object> parameters = new HashMap<String,Object>();
				
				for(String name : queryComponentNames) {
					
					TextField tf = (TextField) getComponent(name+"TextField");
					String value = tf.getText();

					parameters.put(name, value);
				}

				logger.info(objName+" query parameters is : "+parameters);
				
				if (queryButtonClick(parameters)) {//提交
					
					BasePanel parentPanel = (BasePanel) parent;
					parentPanel.reloadData(parameters);
					
				}
				
			}
		});
		
		Object addButton = getComponent("addButton");
		if (addButton != null) {
			
			((JButton)addButton).addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					BasePanel addFormPanel = (BasePanel) getComponent("addFormPanel");
					
					addFormPanel = addButtonClick(addFormPanel);
					
					setComponent("addFormPanel", addFormPanel);
				}
			});
			
		}
		
		
	}
	/**
	 * 是否显示新增按钮
	 * @param showAddButton
	 */
	public void setShowAddButton(boolean showAddButton) {
		this.showAddButton = showAddButton;
	}
}
