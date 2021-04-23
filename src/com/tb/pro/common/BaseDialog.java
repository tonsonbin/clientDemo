package com.tb.pro.common;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

public class BaseDialog {
	
	private Logger logger = Logger.getLogger(BaseDialog.class);

	public JDialog jDialog;
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
		
		dialogOpen(name, width, height, bp, true);
		
		return this;
	}
	
	/**
	 * 弹窗信息
	 * @param message
	 */
	public BaseDialog dialogOpen(String name,int width,int height,BasePanel bp,boolean resizable) {
		
		this.bp = bp;
		
		if (jDialog == null) {
			
			jDialog = new JDialog(new JFrame(),name, true);
			
		}
		
		jDialog.setName(name);
		jDialog.setSize(width, height);
		jDialog.setLocationRelativeTo(null);
		jDialog.setLayout(new BorderLayout());
		jDialog.add(bp.getPanel(),BorderLayout.CENTER);
		jDialog.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				dialogClosing();
			}
			
		});;
		((BasePanel)bp.parent).setComponent("dialog", jDialog);
		jDialog.setResizable(resizable);
		jDialog.setVisible(true);
		return this;
	}
	
	protected void dialogClosing() {
		//logger.info("点击了关闭");
	}
}
