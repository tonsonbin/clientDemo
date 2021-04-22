package com.tb.pro.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {
	private static String url="jdbc:oracle:thin:@localhost:1521:orcl";
	private static String user;//="jdbc";
	private static String password;//="123";
	public static String getUrl() {
		return url;
	}
	public static void setUrl(String url) {
		JdbcUtils.url = url;
	}
	public static String getUser() {
		return user;
	}
	public static void setUser(String user) {
		JdbcUtils.user = user;
	}
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String password) {
		JdbcUtils.password = password;
	}
static{
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		System.out.println("can't found driver");
	}
}
public static Connection getConnection(){
	try {
		return DriverManager.getConnection(url,user,password);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("can't connect sql");
		return null;
	}
}
public static void free(ResultSet rs,Statement st,Connection connection){
	try{
		if(rs!=null)
			rs.close();
	}catch(SQLException e){
		System.out.println("ResultSet close error");
	}finally{
		try{
			if(st!=null)
				st.close();
		}catch(SQLException e){
			System.out.println("Statement close error");
		}finally{
			try{
				if(connection!=null)
					connection.close();
			}catch(SQLException e){
				System.out.println("connection close error");
			}
		}
	}
}
}
