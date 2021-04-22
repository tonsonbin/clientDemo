package com.tb.pro.main;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JTable;

import com.tb.pro.common.BasePanel;
import com.tb.pro.common.BaseQueryPanel;
import com.tb.pro.common.BaseTablePanel;
import com.tb.pro.dao.UserDao;
import com.tb.pro.utils.JTableUtils;

/**
 * 用户列表
 * @author tb
 *
 */
public class UserListPanel extends BasePanel{
	
	private UserDao userDao = new UserDao();
	
	public UserListPanel(Object parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void init() {
		
		jPanel.setLayout(new BorderLayout());
		
	}

	@Override
	protected void addCompont() {
		
		//query panel
		BaseQueryPanel queryPanel = new BaseQueryPanel(this) {
			
			@Override
			protected void addCompont() {
				// TODO Auto-generated method stub
				//添加需要查询的字段
				addTextFieldComponent("name", "姓名");
				addTextFieldComponent("login_name", "登录名");
			}

			@Override
			protected BasePanel addButtonClick(BasePanel addFormPanel) {
				
				//弹出表单
				if (addFormPanel == null) {
					addFormPanel = new UserFormPanel(UserListPanel.this);
				}
				dialogOpen("新增用户", 510, 500, addFormPanel);
				
				return addFormPanel;
			}
		};
		BaseTablePanel tablePanel = new BaseTablePanel(this) {

			@Override
			protected JTable getDataTable() {
				
				String name = (String) getParamenters("name");
				
				List<Map<String, Object>> datas = userDao.findUsers(name);
				
				JTable table = JTableUtils.getTable(new Object[] {"id","登录名","姓名"}, new String[] {"id","login_name","name"},0, datas, width, height);
				
				return table;
				
			}



			@Override
			protected boolean deleteData(String id) throws SQLException {
		        
				int res = userDao.deleteUser(id);
				if (res > 0) {
					return true;
				}
				return false;
			}
			
			@Override
			protected BasePanel updateButtonClick(String id,BasePanel updateFormPanel) throws SQLException {
				
				if (updateFormPanel == null) {
					
					updateFormPanel = new UserFormPanel(UserListPanel.this);
					
				}
				updateFormPanel.setParamenters("id", id);
				boolean refreshData = ((UserFormPanel)updateFormPanel).refreshData();
				if (!refreshData) {//刷新失败
					showMessage("数据刷新失败");
					return null;
				}
				dialogOpen("修改用户", 510, 500, updateFormPanel);
				
				return updateFormPanel;
			}
		};
		
		jPanel.add(queryPanel.getPanel(),BorderLayout.NORTH);
		jPanel.add(tablePanel.getPanel(),BorderLayout.CENTER);
		
		setComponent("queryPanel", queryPanel);
		setComponent("tablePanel", tablePanel);
	}

	@Override
	public JPanel reloadData() {
		
		BaseTablePanel tablePanel = (BaseTablePanel) getComponent("tablePanel");
		tablePanel.reloadData(parameters);
		
		return jPanel;
	}
}
