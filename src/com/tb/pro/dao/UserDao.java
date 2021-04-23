package com.tb.pro.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.tb.pro.common.Constant;
import com.tb.pro.common.jdbc.JdbcBuild;

public class UserDao extends JdbcBuild {
	

	/**
	 * 取用户列表
	 * @param name
	 * @return
	 */
	public List<Map<String, Object>> findUsers(String name){
		
		
		StringBuffer sBuffer = new StringBuffer();
		
		sBuffer.append("select*from sys_user WHERE 1=1 ");
		if (StringUtils.isNotBlank(name)) {
			
			sBuffer.append(" AND name LIKE ? ");
			name = "%"+name+"%";
			
		}
		List<Map<String, Object>> datas = this.findAllList(sBuffer.toString(),name);
		
		return datas;
	}
	
	public int insertUsers(String id,String name,String loginName, String loginPwd) throws SQLException{
		
		
		StringBuffer sBuffer = new StringBuffer();
		
		sBuffer.append("insert into sys_user(id,name,login_name,"+Constant.LOGIN_PWD_CLOUMNNAME+") values(?,?,?,?) ");
		
		int res = this.getupdate(sBuffer.toString(),id,name, loginName,loginPwd);
		
		return res;
	}
	
	public Map<String, Object> getUser(String id) throws SQLException{
		
		
		StringBuffer sBuffer = new StringBuffer();
		
		sBuffer.append("select * from sys_user where id = ?");
		
		return this.getForMap(sBuffer.toString(), id);
	}
	
	public int updateUser(String id,String name,String loginName,String loginPwd) throws SQLException{
		
		
		StringBuffer sBuffer = new StringBuffer();
		
		sBuffer.append("update sys_user set name=?,login_name=?,"+Constant.LOGIN_PWD_CLOUMNNAME+"=? where id = ?");
		
		return this.getupdate(sBuffer.toString(), name,loginName,loginPwd,id);
	}
	public int deleteUser(String id) throws SQLException{
		
		
		StringBuffer sBuffer = new StringBuffer();
		
		sBuffer.append("delete from sys_user where id = ?");
		
		return this.getupdate(sBuffer.toString(),id);
	}

	/**
	 * 根据登录名取用户信息
	 * @param loginName
	 * @return
	 */
	public List<Map<String, Object>> findDataByLoginName(String loginName) {
		StringBuffer sBuffer = new StringBuffer();
		
		sBuffer.append("select*from sys_user WHERE login_name = ?");
		
		List<Map<String, Object>> datas = this.findAllList(sBuffer.toString(),loginName);
		
		return datas;
	}
}
