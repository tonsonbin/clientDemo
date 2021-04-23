package com.tb.pro.main.sys;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tb.pro.common.BaseFormPanel;
import com.tb.pro.common.BasePanel;
import com.tb.pro.common.CommonTextField;
import com.tb.pro.common.Constant;
import com.tb.pro.common.UserUtils;
import com.tb.pro.dao.UserDao;
import com.tb.pro.utils.JButtonUtils;

/**
 * 用户表单
 * @author tb
 *
 */
public class LoginFormPanel extends BaseFormPanel{

	private Logger logger = Logger.getLogger(LoginFormPanel.class);
	private String objName = this.getClass().getName();
	
	/**
	 * 是否登录成功，默认失败
	 */
	public boolean loginSuccess = false;
	
	private UserDao userDao = new UserDao();

	public LoginFormPanel(Object parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void init() {
		
	}
	
	@Override
	protected void addCompont() {
		
		//添加表单需要显示的字段
		addTextFieldComponent("login_name", "登录名");
		addTextFieldComponent(Constant.LOGIN_PWD_CLOUMNNAME, "登录密码");
		
	}
	
	@Override
	public void tAddComponent() {
		
		BasePanel formPanel = new BasePanel(this) {};
		
		BasePanel buttonPanel = new BasePanel(this) {
			
			@Override
			protected void init() {
				jPanel.setLayout(new FlowLayout(1));
			}
			
			@Override
			protected void addCompont() {
				// TODO Auto-generated method stub
				
				JButton saveButton = JButtonUtils.getButton("登录", Color.LIGHT_GRAY);
				((BaseFormPanel)parent).setComponent("loginButton", saveButton);
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
	
	@Override
	public void tAddListener() {
		
		((JButton)getComponent("loginButton")).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				if (parameters == null) {
					parameters = new HashMap<String,Object>();
				}
				
				for(String name : formComponentNames) {
					
					CommonTextField commonTextField = (CommonTextField) getComponent(name+"TextField");
					JTextField tf = commonTextField.gettField();
					String value = tf.getText();

					parameters.put(name, value);
				}

				logger.info(objName+" query parameters is : "+parameters);
				try {
					
					String loginName = (String) parameters.get("login_name");
					String loginPwd = (String) parameters.get(Constant.LOGIN_PWD_CLOUMNNAME);
					if (StringUtils.isBlank(loginName)) {
						showMessage("登录名不能为空！");
						return;
					}
					if (StringUtils.isBlank(loginPwd)) {
						showMessage("登录密码不能为空！");
						return;
					}
					
					Map<String, Object> userMap = findDataByLoginName(loginName);
					if (userMap == null) {
						showMessage("没有该用户！");
						return;
					}
					
					//比较用户密码
					String serverPwd = (String) userMap.get(Constant.LOGIN_PWD_CLOUMNNAME);
					if (!loginPwd.equals(serverPwd)) {
						showMessage("登录密码错误！");
						return;
					}
					
					LoginFormPanel loginPanel = (LoginFormPanel)parent;
					loginPanel.loginSuccess = true;
					//设置当前登录用户
					UserUtils.setCurrentUser(userMap);
					
					JDialog jDialog = (JDialog) loginPanel.getComponent("dialog");
					jDialog.dispose();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					showMessage(e1.getLocalizedMessage());
					
				}
			}
		});
		
	}

	
	public void dialog() {
		// TODO Auto-generated method stub
		LoginFormPanel loginFormPanel = new LoginFormPanel(this);
		dialogOpen("登录", 280, 190, loginFormPanel,false);
	}
	
	@Override
	protected void dialogClosing() {
		
		//showMessage("点击了关闭");
		System.exit(0);
	}

	@Override
	protected int insertData(Map<String, Object> parameters) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int updateData(Map<String, Object> parameters) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected Map<String, Object> getData(String id) throws SQLException {

		return null;
	}

	/**
	 * 根据登录名取用户信息
	 * @param loginName
	 * @return
	 * @throws SQLException
	 */
	protected Map<String, Object> findDataByLoginName(String loginName) throws SQLException {

		List<Map<String, Object>> userMaps = userDao.findDataByLoginName(loginName);
		if (userMaps == null || userMaps.size() == 0) {
			return null;
		}
		
		return userMaps.get(0);
	}
}
