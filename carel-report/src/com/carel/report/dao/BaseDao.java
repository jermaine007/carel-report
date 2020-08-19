package com.carel.report.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carel.report.Config;


public class BaseDao {
	private Logger logger = LoggerFactory.getLogger(AbstractBaseDao.class);	

	static {
		try {
			Class.forName(Config.DB_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected Connection getConnection() throws SQLException {
		return DriverManager.getConnection(Config.DB_URL,Config.DB_USER,Config.DB_PWD);
	}
	
	protected Logger getLogger(){
		return this.logger;
	}
	
	public static boolean execute(String sql) {

		Connection connection = null;
		Statement statement = null;
		BaseDao dao = new BaseDao();
		dao.getLogger().info("execute sql:" + sql);
		try {
			connection = dao.getConnection();
			statement = connection.createStatement();
			return statement.execute(sql);
		} catch (Exception e) {
			dao.getLogger().error("error-execute sql:"+sql,e);
		} finally{
			try {
				if(statement!=null){
					statement.close();
				}
				if(connection!=null){
					connection.close();
				}
			} catch (Exception e) {
				dao.getLogger().error("release error",e);
			}
			
		}
		return true;
	}
	
	public static List<Map<String, Object>> executeQuery(String sql) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Map<String, Object>> list= new ArrayList<Map<String, Object>>();
		BaseDao dao = new BaseDao();
		dao.getLogger().info("execute query:" + sql);
		try {
			connection = dao.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			ResultSetMetaData metaData =  resultSet.getMetaData();
			int count = metaData.getColumnCount();
			while(resultSet.next()){
				Map<String, Object> map =new LinkedHashMap<String, Object>();
				for (int i = 0; i < count; i++) {
					String name =  metaData.getColumnName(i+1).toLowerCase();
					Object value = resultSet.getObject(i+1);
					map.put(name, value);
				}
				list.add(map);
			}
		} catch (Exception e) {
			dao.getLogger().error("error-execute sql:"+sql,e);
		} finally{
			try {
				if(resultSet!=null){
					resultSet.close();
				}
				if(statement!=null){
					statement.close();
				}
				if(connection!=null){
					connection.close();
				}
			} catch (Exception e) {
				dao.getLogger().error("release error",e);
			}
			
		}
		return list;
	}

}
