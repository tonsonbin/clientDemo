package com.tb.pro.common.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.log4j.Logger;

import com.tb.pro.common.log.Log;

import net.sf.json.JSONArray;
/**
 * 对dbutil组件进行简单封装，为了更加方便dao层数据提供操作
 * @author tb
 *
 */
public abstract class JdbcBuild {
	
	private Logger logger = Logger.getLogger(JdbcBuild.class);
	
	protected int getupdate(String sql,Object... params) throws SQLException{
		
		Log log = new Log("更新数据");
		log.append("db sql : "+sql+" , params : "+JSONArray.fromObject(params));
		
		List<Object> qList = new ArrayList<Object>();
		for(Object param : params) {
			
			if (param != null && !param.equals("")) {
				qList.add(param);
			}
			
		}
		params = qList.toArray();
		log.append("db sql deled : "+sql+" , params : "+JSONArray.fromObject(params));
		/**
		 * 更新，返回int类型
		 */
		Connection conn = DBConnection.getConnection();
		QueryRunner queryRunner = new QueryRunner();
		int s = queryRunner.update(conn, sql, params);
		DBConnection.closeConnection(conn);
		log.append("result : "+s);
		
		logger.info(log.get());
		return s;
	}
	protected Map<String, Object> getForMap(String sql,Object...params) throws SQLException
	{
		Log log = new Log("取详情");
		log.append("db sql : "+sql+" , params : "+JSONArray.fromObject(params));
		
		List<Object> qList = new ArrayList<Object>();
		for(Object param : params) {
			
			if (param != null && !param.equals("")) {
				qList.add(param);
			}
			
		}
		params = qList.toArray();
		log.append("db sql deled : "+sql+" , params : "+JSONArray.fromObject(params));
		Connection conn = DBConnection.getConnection();
		QueryRunner queryRunner = new QueryRunner();
		Map<String,Object> map = queryRunner.query(conn,sql,new MapHandler(),params);
		DBConnection.closeConnection(conn);
		log.append("result : "+map);
		
		logger.info(log.get());
		return map;
	}
	protected List<Map<String, Object>> findAllList(String sql, Object... params) {

		Log log = new Log("取列表");
		log.append("db sql : "+sql+" , params : "+JSONArray.fromObject(params));
		
		List<Object> qList = new ArrayList<Object>();
		for(Object param : params) {
			
			if (param != null && !param.equals("")) {
				qList.add(param);
			}
			
		}
		params = qList.toArray();
		log.append("db sql deled : "+sql+" , params : "+JSONArray.fromObject(params));
		Connection conn = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			conn = DBConnection.getConnection();
			QueryRunner queryRunner = new QueryRunner();
			list = queryRunner.query(conn, sql, new MapListHandler(), params);

			DBConnection.closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			list = error("findAllList is lose");
		}
		log.append("result : "+list);
		
		logger.info(log.get());
		
		return list;
	}

	/**
	 * 封装select查询语句返回boolean型
	 * 
	 */
	protected boolean findAllBoolean(String sql, Object... params) {
		Connection conn = null;
		QueryRunner queryRunner;
		boolean isTure = false;
		ResultSetHandler<Boolean> rsh;
		try {
			conn = DBConnection.getConnection();
			queryRunner = new QueryRunner();
			rsh = new ResultSetHandler<Boolean>() {
				@Override
				public Boolean handle(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					if (rs.next()) {
						return true;
					} else
						return false;
				}
			};
			isTure = queryRunner.query(conn, sql, rsh, params);
			DBConnection.closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			isTure = false;
		}
		return isTure;
	}

	/**
	 * 封装insert，update，delete方法
	 * 
	 */
	public static boolean updateAllForBoolean(String sql, Object... params) {
		Connection conn = null;
		boolean isTure = false;
		QueryRunner queryRunner = null;

		try {
			conn = DBConnection.getConnection();
			queryRunner = new QueryRunner();
			int i = queryRunner.update(conn, sql, params);
			if (i != 0) {
				isTure = true;
			} else {
				isTure = false;
			}
			DBConnection.closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			isTure = false;
			e.printStackTrace();
		}

		return isTure;
	}

	/**
	 * 
	 * 获取一个vector类型的列名集合
	 * 
	 * 
	 */
	protected Vector<Object> findNames(String sql, Object... params) {
		Connection conn = null;
		QueryRunner queryRunner = null;
		Vector<Object> vector1 = null;

		try {
			vector1=new Vector<Object>();
			conn = DBConnection.getConnection();
			queryRunner = new QueryRunner();
			ResultSetHandler<Vector<Object>> rsh = new ResultSetHandler<Vector<Object>>() {

				@Override
				public Vector<Object> handle(ResultSet rs) throws SQLException {
					ResultSetMetaData rsmd = rs.getMetaData();
					Vector<Object> vector = new Vector<Object>();
					int columnCount = rsmd.getColumnCount();
					System.out.println(columnCount);
					for (int i = 1; i < columnCount; i++) {
						String columnName = rsmd.getColumnName(i);
						vector.add(columnName);
					}
					return vector;
				}
			};
			vector1 = queryRunner.query(conn, sql, rsh, params);
			DBConnection.closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//vector.add("error");
			vector1.add("error");
		}

		return vector1;
	}
	
	/**
	 * 获取vector<Vector<Object>>类型的数据
	 * 
	 */
	protected Vector<Vector<Object>> findData(String sql,Object...params){
		Connection conn=null;
		QueryRunner queryRunner=null;
		Vector<Vector<Object>> vector2=null;
		
		try {
			conn=DBConnection.getConnection();
			queryRunner=new QueryRunner();
			vector2=new Vector<Vector<Object>>();
			ResultSetHandler<Vector<Vector<Object>>> rsh=new ResultSetHandler<Vector<Vector<Object>>>(){
				@Override
				public Vector<Vector<Object>> handle(ResultSet rs)
						throws SQLException {
					Vector<Vector<Object>> vector1=new Vector<Vector<Object>>();
					ResultSetMetaData rsmd=rs.getMetaData();
					int columnCount=rsmd.getColumnCount();
				while(rs.next()){
					Vector<Object> vector=new Vector<Object>();
					for(int i=1;i<columnCount;i++){
						Object data=rs.getObject(i);
						vector.add(data);
					}
					vector1.add(vector);
				}	
					return vector1;
				}
				
			};
			vector2=queryRunner.query(conn,sql, rsh,params);
			DBConnection.closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
		return vector2;
	}
	
	
	/**
	 * catch到错误时返回的List型参数
	 * 
	 * @param errorInfo
	 * @return
	 */
	List<Map<String, Object>> error(String errorInfo) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("#error#", errorInfo);
		list.add(map);
		return list;
	}
}
