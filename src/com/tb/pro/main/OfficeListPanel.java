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
import com.tb.pro.dao.OfficeDao;
import com.tb.pro.utils.JTableUtils;

/**
 * 机构列表
 * @author tb
 *
 */
public class OfficeListPanel extends BasePanel{
	
	private OfficeDao officeDao = new OfficeDao();

	
	
	public OfficeListPanel(Object parent) {
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
				addTextFieldComponent("name", "机构名");
			}

			@Override
			protected BasePanel addButtonClick(BasePanel addFormPanel) {
				
				if (addFormPanel == null) {
					addFormPanel = new OfficeFormPanel(OfficeListPanel.this);
				}
				dialogOpen("新增机构", 510, 500, addFormPanel);
				
				return addFormPanel;
			}
		};
		BaseTablePanel tablePanel = new BaseTablePanel(this) {

			@Override
			protected JTable getDataTable() {
				
				String name = (String) getParamenters("name");
				
				List<Map<String, Object>> datas = officeDao.findList(name);
				
				JTable table = JTableUtils.getTable(new Object[] {"id","机构名","排序"}, new String[] {"id","name","sort"},0, datas, width, height);
				
				return table;
				
			}



			@Override
			protected boolean deleteData(String id) {
		        showMessage("删除id："+id);
				return false;
			}
			
			@Override
			protected BasePanel updateButtonClick(String id,BasePanel updateFormPanel) throws SQLException {
				
				if (updateFormPanel == null) {
					
					updateFormPanel = new OfficeFormPanel(OfficeListPanel.this);
					
				}
				updateFormPanel.setParamenters("id", id);
				boolean refreshData = ((OfficeFormPanel)updateFormPanel).refreshData();
				if (!refreshData) {//刷新失败
					showMessage("数据刷新失败");
					return null;
				}
				dialogOpen("修改机构", 510, 500, updateFormPanel);
				
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
