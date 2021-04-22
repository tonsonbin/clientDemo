package com.tb.pro.utils;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

public class JTreeUtils {
	
	private List<JTreeBean> treeDatas = new ArrayList<JTreeBean>();

	/**
	 * 根据菜单列表获取jtree数对象
	 * @param treeDatas
	 * @return
	 */
	public static JTree getJTree(List<JTreeBean> treeDatas) {
		
		  JTreeBean root  =   new  JTreeBean ("root");
		  
		  root = getNodes(treeDatas,root);
		  
		  DefaultTreeModel model  =   new  DefaultTreeModel (root);
		  
		  JTree tree  =   new  JTree (model);
		  tree.setRootVisible(false);
		
		  return tree;
	}
	/**
	 * 辅助菜单树构建生成方法
	 * @param treeDatas
	 * @param parent
	 * @return
	 */
	private static JTreeBean getNodes(List<JTreeBean> treeDatas,JTreeBean parent){
		
		for(JTreeBean jTreeBean : treeDatas) {
			  
			  String labelName = jTreeBean.getLabelName();
			  jTreeBean.setUserObject(labelName);

			  List<JTreeBean> cTreeDatas = jTreeBean.getChildren();
			  if (cTreeDatas != null && cTreeDatas.size() > 0) {
				  
				  jTreeBean = getNodes(cTreeDatas,jTreeBean);
				
			  }

			  parent.add(jTreeBean);
			  
		  }
		
		
		return parent;
	}
	/**
	 * 添加菜单
	 * @param labelName 菜单树先生名称
	 * @param name 菜单名称
	 * @param panelName 点击跳转的panel名称
	 * @param children 子菜单
	 * @return
	 */
	public JTreeUtils putItem(String labelName,String name,String panelName,List<JTreeBean> children) {
		

		JTreeBean item = new JTreeBean();
		item.setLabelName(labelName);
		item.setName(name);
		item.setPanelName(panelName);
		item.setChildren(children);
		
		this.treeDatas.add(item);
		
		return this;
	} 
	/**
	 * 获取的构建的菜单树列表
	 * @return
	 */
	public List<JTreeBean> getJTreeBeans(){
		
		return treeDatas;
	}
}
