package com.tb.pro.main;

import java.sql.SQLException;
import java.util.Map;

import com.tb.pro.common.BaseFormPanel;
import com.tb.pro.dao.OfficeDao;

/**
 * 机构表单
 * @author tb
 *
 */
public class OfficeFormPanel extends BaseFormPanel{
	
	private OfficeDao officeDao = new OfficeDao();

	public OfficeFormPanel(Object parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void addCompont() {
		
		addTextFieldComponent("name", "机构名");
		addTextFieldComponent("sort", "排序");
		
	}

	@Override
	protected int insertData(Map<String, Object> parameters) throws SQLException {
		
		String name = (String) parameters.get("name");
		String sort = (String) parameters.get("sort");
		String id = (String) parameters.get("id");
		
		return officeDao.insertOffice(id, name, sort);
		
	}

	@Override
	protected int updateData(Map<String, Object> parameters) throws SQLException {
		// TODO Auto-generated method stub
		String name = (String) parameters.get("name");
		String sort = (String) parameters.get("sort");
		String id = (String) parameters.get("id");
		return officeDao.updateOffice(id, name, sort);
	}
	
	@Override
	protected Map<String, Object> getData(String id) throws SQLException {
		
		Map<String, Object> bean = officeDao.getOffice(id);
		
		return bean;
	}

	
}
