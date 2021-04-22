package com.tb.pro.main;

import java.sql.SQLException;
import java.util.Map;

import com.tb.pro.common.BaseFormPanel;
import com.tb.pro.dao.UserDao;

/**
 * 用户表单
 * @author tb
 *
 */
public class UserFormPanel extends BaseFormPanel{
	
	private UserDao userDao = new UserDao();

	public UserFormPanel(Object parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void addCompont() {
		
		//添加表单需要显示的字段
		addTextFieldComponent("name", "姓名");
		addTextFieldComponent("login_name", "登录名");
		
	}

	@Override
	protected int insertData(Map<String, Object> parameters) throws SQLException {
		
		String name = (String) parameters.get("name");
		String loginName = (String) parameters.get("login_name");
		String id = (String) parameters.get("id");
		
		return userDao.insertUsers(id, name, loginName);
		
	}

	@Override
	protected int updateData(Map<String, Object> parameters) throws SQLException {
		// TODO Auto-generated method stub
		String name = (String) parameters.get("name");
		String loginName = (String) parameters.get("login_name");
		String id = (String) parameters.get("id");
		return userDao.updateUser(id, name, loginName);
	}
	
	@Override
	protected Map<String, Object> getData(String id) throws SQLException {
		
		Map<String, Object> bean = userDao.getUser(id);
		
		return bean;
	}

	
}
