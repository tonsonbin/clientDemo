package com.tb.pro.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.tb.pro.common.jdbc.JdbcBuild;

public class OfficeDao extends JdbcBuild {
	

	public List<Map<String, Object>> findList(String name){
		
		
		StringBuffer sBuffer = new StringBuffer();
		
		sBuffer.append("select*from sys_office WHERE 1=1 ");
		if (StringUtils.isNotBlank(name)) {
			
			sBuffer.append(" AND name LIKE ? ");
			name = "%"+name+"%";
			
		}
		List<Map<String, Object>> datas = this.findAllList(sBuffer.toString(),name);
		
		return datas;
	}
	
	public int insertOffice(String id,String name,String sort) throws SQLException{
		
		
		StringBuffer sBuffer = new StringBuffer();
		
		sBuffer.append("insert into sys_office(id,name,sort) values(?,?,?) ");
		
		int res = this.getupdate(sBuffer.toString(),id,name, sort);
		
		return res;
	}
	
	public Map<String, Object> getOffice(String id) throws SQLException{
		
		
		StringBuffer sBuffer = new StringBuffer();
		
		sBuffer.append("select * from sys_office where id = ?");
		
		return this.getForMap(sBuffer.toString(), id);
	}
	
	public int updateOffice(String id,String name,String sort) throws SQLException{
		
		
		StringBuffer sBuffer = new StringBuffer();
		
		sBuffer.append("update sys_office set name=?,sort=? where id = ?");
		
		return this.getupdate(sBuffer.toString(), name,sort,id);
	}
}
