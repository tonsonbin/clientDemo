package com.tb.pro.utils;

import java.awt.Dimension;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class JTableUtils {

	/**
	 * 取jtable表格对象
	 * @param titles 表格列名 object[]
	 * @param datas 数据 object[][]
	 * @param width table宽度
	 * @param height table高度
	 * @return
	 */
	public static JTable getTable(Object[] titles,Object[][] datas,int width,int height) {

		
		
		JTable table = new JTable(datas,titles); 
		table.setPreferredScrollableViewportSize(new Dimension(width, height)); 

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		
		table.setEnabled(false);//禁止编辑
		
		return table;
	}
	/**
	 * 取jtable表格对象
	 * @param titles 表格列名 object[]
	 * @param keys 表格列名对应的数据库column的name
	 * @param idColumnIndex 表格id所在列索引
	 * @param datas 数据list<map>
	 * @param width table的宽度
	 * @param height table的高度
	 * @return
	 */
	public static JTable getTable(Object[] titles,String[] keys,int idColumnIndex,List<Map<String, Object>> datas,int width,int height) {

		Object[][] datas2 = new Object[datas.size()][];
		
		for(int index=0; index<datas.size(); index++) {
			
			Object[] data = new Object[keys.length];
			
			Map<String, Object> item = datas.get(index);
			for(int index2=0; index2<keys.length; index2++) {
				
				data[index2] = item.get(keys[index2]);
				
			}
			
			datas2[index] = data;
		}
		
		JTable table = new JTable(datas2,titles){
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column)
            {
				
                  return false;
                       
            }//表格不允许被编辑
        };
		table.setPreferredScrollableViewportSize(new Dimension(width, height)); 

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		
		if (idColumnIndex != -1) {//隐藏id列
			
			TableColumnModel tableColumnModel = table.getColumnModel();
			TableColumn tableColumn = tableColumnModel.getColumn(idColumnIndex);
			tableColumn.setMinWidth(0);
			tableColumn.setMaxWidth(0);
		}
		
		return table;
	}
}
