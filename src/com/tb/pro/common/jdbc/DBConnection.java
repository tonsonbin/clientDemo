package com.tb.pro.common.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * ����c3p0��ȡ��ݿ�����?
 * ��Ʒ�ʽ���õ���ģʽ��ɴ�������
 * @author Administrator
 *
 */

public class DBConnection {
	private static DBConnection db = null;
	private static DataSource ds = null;
	
	private DBConnection() {
		if (ds == null) {
			ds = new ComboPooledDataSource(); //��ʼ��DataSource����?
		}
	}
	
	private static DBConnection getInstance() { //��ʼ��DBConnection�Ķ���
		if (db == null) {

			db = new DBConnection();
		}
		return db;
	}
	
	private DataSource getDataSource() { //�����Ѿ���ʼ���õ�DataSource����
		return ds;
	}
	
	public synchronized static Connection getConnection() throws SQLException {
		return getInstance().getDataSource().getConnection();
	}
	
	public synchronized static void closeConnection(Connection conn) throws SQLException {
		if (conn != null && !conn.isClosed()) {
			conn.close();
			conn = null;
		}
	}
}
