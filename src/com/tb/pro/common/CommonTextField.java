package com.tb.pro.common;

import javax.swing.JTextField;

public class CommonTextField {

	//替换显示文字
	private String replaceValue;
	//实际值
	private String realValue;
	//textField对象
	private JTextField tField;
	
	public String getReplaceValue() {
		return replaceValue;
	}

	public void setReplaceValue(String replaceValue) {
		this.replaceValue = replaceValue;
	}

	public JTextField gettField() {
		
		if (this.tField == null) {
			
			JTextField tField = new JTextField("",10);
			tField.setText("");
			this.tField = tField;
			
		}
		
		return tField;
	}

	public void settField(JTextField tField) {
		this.tField = tField;
	}

	public String getRealValue() {
		return realValue;
	}

	public void setRealValue(String realValue) {
		this.realValue = realValue;
	}
	
	
}
