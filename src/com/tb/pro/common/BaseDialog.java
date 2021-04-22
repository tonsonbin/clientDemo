package com.tb.pro.common;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BaseDialog {

	private JDialog jDialog;
	public BasePanel bp;

	/**
	 * 弹窗信息
	 * @param message
	 */
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	/**
	 * 弹窗信息
	 * @param message
	 */
	public BaseDialog dialogOpen(String name,int width,int height,BasePanel bp) {
		
		this.bp = bp;
		
		if (jDialog == null) {
			
			jDialog = new JDialog(new JFrame(),name, true);
			
		}
		
		jDialog.setName(name);
		jDialog.setSize(width, height);
		jDialog.setLocationRelativeTo(null);
		jDialog.setLayout(new BorderLayout());
		jDialog.add(bp.getPanel(),BorderLayout.CENTER);
		((BasePanel)bp.parent).setComponent("dialog", jDialog);
		jDialog.setVisible(true);
		return this;
	}
	
}
