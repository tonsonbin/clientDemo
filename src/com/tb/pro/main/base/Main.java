package com.tb.pro.main.base;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.tb.pro.main.sys.LoginFormPanel;

/**
 * 运行入口
 * @author tb
 *
 */
public class Main extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int mainSizeHeight=850;
	private int mainSizeWidth=820;
	
	public TopPanel topPanel;
	public LeftPanel leftPanel;
	public RightPanel rightPanel;
	public LoginFormPanel loginFormPanel;
	
	public Main(String title) {
		
		this.init(title);
		this.addComponents();
		this.addListener();
		
		this.setVisible(true);
	}
	
	public void init(String title) {
		
		this.setTitle(title);
		this.setSize(mainSizeWidth,mainSizeHeight);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		this.setResizable(true);
		
	}
	
	public void addListener() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				/*int i = JOptionPane.showConfirmDialog(null, "are you sure",
						"ddd", JOptionPane.YES_NO_OPTION);
				
				if (i == JOptionPane.OK_OPTION) {
					System.exit(0);
				}*/

				System.exit(0);
			}
		});
		
		this.addComponentListener(new ComponentAdapter(){
			@Override public void componentResized(ComponentEvent e){
				
			}});
	}
	
	public void addComponents() {

		loginFormPanel = new LoginFormPanel(this);
		//显示登录界面
		loginFormPanel.dialog();
		if (loginFormPanel.loginSuccess) {

			topPanel = new TopPanel(this);
			leftPanel = new LeftPanel(this);
			rightPanel= new RightPanel(this);
			
			this.add(topPanel.getPanel(),BorderLayout.NORTH);
			this.add(leftPanel.getPanel(),BorderLayout.WEST);
			this.add(rightPanel.getPanel(), BorderLayout.CENTER);
			
		}
	}
	
	public static void main(String[] args) {
		
		new Main("测试");
		
	}
}
