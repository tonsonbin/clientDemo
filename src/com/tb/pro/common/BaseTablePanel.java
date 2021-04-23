package com.tb.pro.common;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

public abstract class BaseTablePanel extends BasePanel{
	
	private List<String> queryComponentNames = new ArrayList<String>(); //查询文本控件名
	
	/**
	 * @param parent panel的父级窗口对象
	 * @param x panel位置的起点x坐标
	 * @param y panel位置的起点y坐标
	 * @param width  panel的宽
	 * @param height panel的高
	 */
	public BaseTablePanel(Object parent,int x,int y, int width, int height) {
		
		super(parent, x, y, width, height);
		
	}
	/**
	 * @param parent panel的父级窗口对象
	 * @param width  panel的宽
	 * @param height panel的高
	 */
	public BaseTablePanel(Object parent) {
		
		super(parent);
		
	}

	public JPanel getPanel() {
		
		if (this.jPanel != null) {
			return jPanel;
		}
		
		//默认的初始化
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setVisible(true);
		Border border = BorderFactory.createEtchedBorder();
		panel.setBorder(border);

		jPanel = panel;
		
		init();
		addCompont();
		addListener();
		
		tAddComponent();
		if (UserUtils.isAdmin()) {
			tTableListener();
		}

		return jPanel;
	};
	/**
	 * 重新加载table数据
	 */
	public JPanel reloadData() {

		JTable jTable = getDataTable();
		setComponent("jTable", jTable);
		
		JScrollPane jScrollPane = (JScrollPane)getComponent("jScrollPane");
		jScrollPane.setViewportView(jTable);
		jScrollPane.repaint();
		
		tTableListener();//由于table已经是新的对象了，所以这里重新声明监听事件
		
		return jPanel;
	}
	
	/**
	 * 添加右键点击菜单
	 * @param name 查询字段名
	 * @param labelName label显示字符串
	 */
	protected void addClickMenuComponent(String name,String labelName) {
		
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
	 * 获取数据表格
	 * @return
	 */
	protected abstract JTable getDataTable();
	/**
	 * 删除数据
	 * @return
	 * @throws SQLException 
	 */
	protected abstract boolean deleteData(String id) throws SQLException;
	/**
	 * 监听修改按钮点击事件
	 * @throws SQLException 
	 */
	protected abstract BasePanel updateButtonClick(String id,BasePanel updateFormPanel) throws SQLException;
	
	
	private void tAddComponent() {
		
		JTable jTable = getDataTable();
		setComponent("jTable", jTable);
		
		JScrollPane jScrollPane = new JScrollPane(jTable); 
		setComponent("jScrollPane", jScrollPane);
		
		jPanel.add(jScrollPane,BorderLayout.CENTER);
		
	}
	private void tTableListener() {
		
		JTable jTable = (JTable) getComponent("jTable");
		jTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent event) {
				
				if (event.getButton() == MouseEvent.BUTTON3) {//右键点击
					//通过点击位置找到点击为表格中的行
		            int focusedRowIndex = jTable.rowAtPoint(event.getPoint());
		            if (focusedRowIndex == -1) {
		                return;
		            }
		            //将表格所选项设为当前右键点击的行
		            jTable.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
		            //弹出菜单
		            JPopupMenu jpopupMenu = new JPopupMenu();
		            
		            JMenuItem delMenItem = new JMenuItem();
		            delMenItem.setText("  删除  ");
		            delMenItem.addActionListener(new ActionListener() {
		                public void actionPerformed(ActionEvent evt) {
		                	
		                	int c = JOptionPane.showConfirmDialog(null, "确定删除吗？");
		                	if (c == JOptionPane.OK_OPTION) {

			                    Object id = jTable.getValueAt(focusedRowIndex, 0);
			                    try {
									if (deleteData(id.toString())) {
										reloadData();
									}
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									showMessage("catch error "+e.getLocalizedMessage());
								}
			                    
							}
		                }
		            });
		            JMenuItem updateMenItem = new JMenuItem();
		            updateMenItem.setText("  修改  ");
		            updateMenItem.addActionListener(new ActionListener() {
		                public void actionPerformed(ActionEvent evt) {
		                    Object id = jTable.getValueAt(focusedRowIndex, 0);
		                    BasePanel updateFormPanel = (BasePanel) getComponent("updateFormPanel");
		                    try {
		                    	
		                    	updateFormPanel = updateButtonClick(id==null?"":id.toString(),updateFormPanel);
		                    	setComponent("updateFormPanel", updateFormPanel);
		                    }catch (Exception e) {
		                    	e.printStackTrace();
								showMessage("catch error "+e.getLocalizedMessage());
							}
		                }
		            });
		            jpopupMenu.add(updateMenItem);
		            jpopupMenu.add(delMenItem);
		            jpopupMenu.show(jTable, event.getX(), event.getY());
		            
				} else {
					
					//通过点击位置找到点击为表格中的行
		            int focusedRowIndex = jTable.rowAtPoint(event.getPoint());
		            if (focusedRowIndex == -1) {
		                return;
		            }
		            //将表格所选项设为当前右键点击的行
		            jTable.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
					
					
				}
				
			}
			
		});
		
	}
}
