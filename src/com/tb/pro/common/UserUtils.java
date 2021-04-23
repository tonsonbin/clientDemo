package com.tb.pro.common;

import java.util.Map;

public class UserUtils {

	private static Map<String, Object> user;
	
	/**
	 * 取当前登录用户
	 * @return
	 */
	public static Map<String, Object> getCurrentUser() {
		return user;
	}
	
	/**
	 * 设置当前登录用户
	 * @return
	 */
	public static void setCurrentUser(Map<String, Object> userS) {
		user = userS;
	}
}
