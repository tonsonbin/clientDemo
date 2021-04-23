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
	
	/**
	 * 当前登录用户是否是管理员
	 * @return
	 */
	public static boolean isAdmin() {
		if (user == null) {
			return false;
		}
		
		if (!Constant.USER_ADMIN_ID.equals(user.get("id"))) {
			return false;
		}
		
		return true;
	}
}
