package com.tb.pro.common;

import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import net.sf.json.JSONObject;

/**
 * panel 公共父类
 * @author tb
 *
 */
public abstract class BasePanel extends BaseDialog{
	
	protected int width = 800;
	protected int height = 100;
	protected int x = 0;
	protected int y = 0;
	
	protected JPanel jPanel;
	public Object parent;//父级窗口对象
	protected Map<String,Object> parameters = new HashMap<String,Object>();//请求参数
	
	protected Map<String,Object> components = new HashMap<String,Object>();
	/**
	 * @param parent panel的父级窗口对象
	 * @param x panel位置的起点x坐标
	 * @param y panel位置的起点y坐标
	 * @param width  panel的宽
	 * @param height panel的高
	 */
	public BasePanel(Object parent,int x,int y, int width, int height) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.parent = parent;
		
		//getPanel();
	}
	/**
	 * @param parent panel的父级窗口对象
	 */
	public BasePanel(Object parent) {
		
		this.parent = parent;
		
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

		jPanel = panel;
		
		init();
		addCompont();
		addListener();
		
		return jPanel;
	};
	/**
	 * 重置panel的大小位置
	 * @param x panel位置的起点x坐标
	 * @param y panel位置的起点y坐标
	 * @param width  panel的宽
	 * @param height panel的高
	 */
	public JPanel resize(int x,int y,int width,int height) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		jPanel.setLocation(x,y);
		jPanel.setSize(width,height);
		jPanel.repaint();
		resize();
		
		
		return jPanel;
	};
	/**
	 * 设置边框
	 */
	public void setBorder() {
		
		getPanel();
		
		Border border = BorderFactory.createEtchedBorder();
		jPanel.setBorder(border);
		
	}
	/**
	 * 放入控件对象
	 * @param name 控件命名
	 * @param component 控件对象
	 */
	public void setComponent(String name,Object component) {
		
		this.components.put(name, component);
		
	}
	/**
	 * 获取控件对象
	 * @param name 控件命名
	 */
	public  Object getComponent(String name) {
		
		
		return this.components.get(name);
		
	}
	/**
	 * 重置请求参数
	 */
	public void resetParamenters() {
		
		parameters = new HashMap<String,Object>();
		
	}
	/**
	 * 设置请求参数
	 * @param name
	 * @param value
	 */
	public void setParamenters(String name,Object value) {
		
		parameters.put(name, value);
		
	}

	/**
	 * 获取请求参数
	 * @param name
	 */
	public Object getParamenters(String name) {
		
		Object value = parameters.get(name);
		if (value == null) {
			value = "";
		}
		
		return value;
	}
	/**
	 * 刷新panel中的数据
	 * @return
	 */
	public JPanel reloadData(Map<String,Object> parameters) {
		
		System.out.println(this.getClass().getName()+" reloadData parameters is :"+JSONObject.fromObject(parameters));
		
		this.parameters = parameters;
		
		reloadData();
		
		return jPanel;
	};
	/**
	 * panel初始化
	 */
	protected void init() {};
	/**
	 * panel中添加其他控件的方法
	 */
	protected void addCompont() {};

	/**
	 * 需要自定义重置大小，例如刷新面板里面的元素什么大小的
	 */
	protected void resize() {};
	/**
	 * 所有监听事件
	 */
	protected void addListener() {};
	
	protected JPanel reloadData() {
		
		return jPanel;
	};
}
