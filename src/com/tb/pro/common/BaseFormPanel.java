package com.tb.pro.common;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tb.pro.utils.JButtonUtils;

public abstract class BaseFormPanel extends BasePanel{

	private Logger logger = Logger.getLogger(BaseFormPanel.class);
	private String objName = this.getClass().getName();
	
	private List<String> formComponentNames = new ArrayList<String>(); //查询文本控件名
	/**
	 * @param parent panel的父级窗口对象
	 * @param width  panel的宽
	 * @param height panel的高
	 */
	public BaseFormPanel(Object parent) {
		
		super(parent);
		
	}

	public JPanel getPanel() {
		
		if (jPanel != null) {
			return jPanel;
		}
		
		//默认的初始化
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setVisible(false);
		Border border = BorderFactory.createEtchedBorder();
		panel.setBorder(border);

		jPanel = panel;
		
		init();
		tAddComponent();
		addCompont();
		addListener();
		
		tAddListener();
		refreshData();
		
		jPanel.setVisible(true);
		return jPanel;
	};
	
	/**
	 * 添加输入框
	 * @param name 查询字段名
	 * @param labelName label显示字符串
	 */
	protected void addTextFieldComponent(String name,String labelName) {
		
		BasePanel formPanel = (BasePanel) getComponent("formPanel");
		
		BasePanel itemPanel = new BasePanel(formPanel) {
			
			@Override
			protected void addCompont() {
				
				JLabel bl1=new JLabel(labelName);
				
				bl1.setFont(new Font("宋体",Font.BOLD,20));
				bl1.setForeground(Color.blue);
				bl1.setPreferredSize(new Dimension(100, 25));
				((BaseFormPanel)((BasePanel)parent).parent).setComponent(name+"Label", bl1);
				
				jPanel.add(bl1,BorderLayout.WEST);
				
				JTextField tField = new JTextField("",10);
				tField.setText("");
				jPanel.add(tField,BorderLayout.EAST);
				((BaseFormPanel)((BasePanel)parent).parent).setComponent(name+"TextField", tField);
				formComponentNames.add(name);
				jPanel.repaint();
			}
		};
		
		itemPanel.setBorder();
		formPanel.jPanel.repaint();
		formPanel.jPanel.add(itemPanel.getPanel());
		
	}
	
	private void tAddComponent() {
		
		BasePanel formPanel = new BasePanel(this) {};
		
		BasePanel buttonPanel = new BasePanel(this) {
			
			@Override
			protected void init() {
				jPanel.setLayout(new FlowLayout(1));
			}
			
			@Override
			protected void addCompont() {
				// TODO Auto-generated method stub
				
				JButton saveButton = JButtonUtils.getButton("保存", Color.LIGHT_GRAY);
				((BaseFormPanel)parent).setComponent("saveButton", saveButton);
				jPanel.add(saveButton);
			}
			
		};
		
		setComponent("formPanel", formPanel);
		setComponent("buttonPanel", buttonPanel);
		
		formPanel.setBorder();
		buttonPanel.setBorder();
		
		jPanel.add(formPanel.getPanel(),BorderLayout.CENTER);
		jPanel.add(buttonPanel.getPanel(),BorderLayout.SOUTH);
	}
	/**
	 * 新增的业务逻辑实现
	 * @throws SQLException 
	 */
	protected abstract int insertData(Map<String,Object> parameters) throws SQLException;
	/**
	 * 修改的业务逻辑实现
	 * @throws SQLException 
	 */
	protected abstract int updateData(Map<String,Object> parameters) throws SQLException;
	/**
	 * 根据id获取数据的业务逻辑实现
	 * @throws SQLException 
	 */
	protected abstract Map<String, Object> getData(String id) throws SQLException;
	/**
	 * 清空输入
	 */
	private void clearForm() {
		
		for(String name : formComponentNames) {
			
			JTextField tf = (JTextField) getComponent(name+"TextField");
			tf.setText("");
		}
		
	}
	/**
	 * 刷新表单数据
	 * @return
	 */
	public boolean refreshData() {
		
		String id = (String) getParamenters("id");
		if (StringUtils.isBlank(id)) {//为空，新增
			return true;
		}
		try {
			Map<String, Object> bean = getData(id);

			for(String name : formComponentNames) {
				
				JTextField tf = (JTextField) getComponent(name+"TextField");
				if (bean.containsKey(name)) {
					Object value = bean.get(name);
					tf.setText(value == null ?"":value.toString());
				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showMessage("获取id为："+id+"的数据失败");
			return false;
		}
		
		return true;
	}
	private void tAddListener() {
		
		((JButton)getComponent("saveButton")).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				if (parameters == null) {
					parameters = new HashMap<String,Object>();
				}
				
				for(String name : formComponentNames) {
					
					JTextField tf = (JTextField) getComponent(name+"TextField");
					String value = tf.getText();

					parameters.put(name, value);
				}

				logger.info(objName+" query parameters is : "+parameters);
				try {
					if (!parameters.containsKey("id")) {//新增
						
						parameters.put("id", UUID.randomUUID().toString().replaceAll("-", "").toLowerCase());

						if (insertData(parameters)>0) {
							showMessage("添加数据成功");
							BasePanel listPanel = (BasePanel)parent;
							JDialog jDialog = (JDialog) listPanel.getComponent("dialog");
							jDialog.dispose();
							
							listPanel.reloadData();
							
							clearForm();
						}else{

							showMessage("添加数据失败");
							
						};
					}else {//修改
						
						if (updateData(parameters)>0) {
							
							showMessage("修改数据成功");
							BasePanel listPanel = (BasePanel)parent;
							JDialog jDialog = (JDialog) listPanel.getComponent("dialog");
							jDialog.dispose();
							
							listPanel.reloadData();
							
						}else{

							showMessage("修改数据失败");
							
						};
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					showMessage(e1.getLocalizedMessage());
					
				}
			}
		});
		
		
	}
}
