package com.tb.pro.utils;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class JButtonUtils {

	public static JButton getButton(String label,Color color,ActionListener listener) {
		
		JButton button=new JButton(label);
		button.setBackground(color);
		button.addActionListener(listener);
		
		return button;
		
	}
	
	public static JButton getButton(String label,Color color) {
		
		JButton button=new JButton(label);
		button.setBackground(color);
		
		return button;
		
	}
}
