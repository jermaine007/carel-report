package com.carel.report.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlCore<T> {
	private Logger logger = LoggerFactory.getLogger(SqlCore.class);
	private Connection connection;

	private Map<String, Field> getMetaInfo(Class<?> cls) {
		logger.debug("Get meta info for:{}",cls);
		String clazz = cls.getCanonicalName();
		if (SqlBuilder.META_INFO.containsKey(clazz)) {
			return SqlBuilder.META_INFO.get(clazz);
		}
		Map<String, Field> info = new HashMap<String, Field>();
		Field[] fields = Reflection.getAnnotationPresentFields(cls,
				Column.class);
		for (Field field : fields) {
			Column c = field.getAnnotation(Column.class);
			String value = c.name();
			logger.debug("find annotation present, field:{},column:{}",
					new String[]{field.getName(),value});
			info.put(value, field);
		}
		SqlBuilder.META_INFO.put(clazz, info);

		return info;

	}

	public SqlCore(Connection connection) {
		this.connection = connection;
	}
	
	public boolean update(Class<T> cls,String update,String where,Object... params){
		SqlBuilder<T> sqlBuilder = new SqlBuilder<T>(cls);
		SqlParameter p = sqlBuilder.getUpdate(update,where, params);
		PreparedStatement st = null;
		try {
			logger.info("execute update:" + p.sql);
			st = connection.prepareStatement(p.sql);
			int index=0;
			for (Object o : params) {
				st.setObject(index+1, o);
				logger.info("param{}:{}",new Object[]{index+1,o});
				index++;
			}
			st.execute();
			return true;
		} catch (SQLException e) {
			logger.error("error:", e);
		} finally {
			try {
				if(st != null){
					st.close();
				}
			} catch (SQLException e) {
				logger.error("error:", e);
			}
		}
		return false;
	}
	
	public boolean delete(Class<T> cls, String where, Object... params) {
		SqlBuilder<T> sqlBuilder = new SqlBuilder<T>(cls);
		SqlParameter p = sqlBuilder.getDelete(where, params);
		PreparedStatement st = null;
		try {
			logger.info("execute delete:" + p.sql);
			st = connection.prepareStatement(p.sql);
			for (int i = 0; i < params.length; i++) {
				st.setObject(i + 1, params[i]);
				logger.info("param{}:{}",new Object[]{i+1,params[i]});
			}
			st.execute();
			return true;
		} catch (SQLException e) {
			logger.error("error:", e);
		} finally {
			try {
				if(st != null){
					st.close();
				}
			} catch (SQLException e) {
				logger.error("error:", e);
			}
		}
		return false;
	}

	public boolean delete(Class<T> cls) {
		SqlBuilder<T> sqlBuilder = new SqlBuilder<T>(cls);
		SqlParameter p = sqlBuilder.getDelete();
		Statement st = null;
		try {
			logger.info("execute delete:" + p.sql);
			st = connection.createStatement();
			st.execute(p.sql);
			return true;
		} catch (SQLException e) {
			logger.error("error:", e);
		} finally {
			try {
				if(st != null){
					st.close();
				}
			} catch (SQLException e) {
				logger.error("error:", e);
			}
		}
		return false;
	}

	public T first(Class<T> cls, String where, Object... params) {
		SqlBuilder<T> sqlBuilder = new SqlBuilder<T>(cls);
		SqlParameter p = sqlBuilder.getSelect(where, params);
		Map<String, Field> metaInfo = getMetaInfo(cls);
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			logger.info("execute query:" + p.sql);
			st = connection.prepareStatement(p.sql);
			for (int i = 0; i < params.length; i++) {
				st.setObject(i + 1, params[i]);
				logger.info("param{}:{}",new Object[]{i+1,params[i]});
			}
			rs = st.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int count = metaData.getColumnCount();
			if (rs.next()) {
				T o = Reflection.newInstance(cls);
				for (int i = 0; i < count; i++) {
					String column = metaData.getColumnName(i + 1);
					Object value = rs.getObject(i + 1);

					Field field = metaInfo.get(column);
					if (field != null) {
						Reflection.setFieldValue(field, o, value);
					}
				}
				return o;
			}

		} catch (SQLException e) {
			logger.error("error:", e);
		} finally {
			try {
				if(rs != null){
					rs.close();
				}
				if(st != null){
					st.close();
				}
			} catch (SQLException e) {
				logger.error("error:", e);
			}
		}
		return null;
	}

	public List<T> find(Class<T> cls, String where, Object... params) {
		SqlBuilder<T> sqlBuilder = new SqlBuilder<T>(cls);
		SqlParameter p = sqlBuilder.getSelect(where, params);
		List<T> list = new ArrayList<T>();
		Map<String, Field> metaInfo = getMetaInfo(cls);
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			logger.info("execute query:" + p.sql);
			st = connection.prepareStatement(p.sql);
			for (int i = 0; i < params.length; i++) {
				st.setObject(i + 1, params[i]);
				logger.info("param{}:{}",new Object[]{i+1,params[i]});
			}
			rs = st.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int count = metaData.getColumnCount();
			while (rs.next()) {
				T o = Reflection.newInstance(cls);
				for (int i = 0; i < count; i++) {
					String column = metaData.getColumnName(i + 1);
					Object value = rs.getObject(i + 1);

					Field field = metaInfo.get(column);
					if (field != null) {
						Reflection.setFieldValue(field, o, value);
					}
				}
				list.add(o);
			}

		} catch (SQLException e) {
			logger.error("error:", e);
		} finally {
			try {
				if(rs != null){
					rs.close();
				}
				if(st != null){
					st.close();
				}
			} catch (SQLException e) {
				logger.error("error:", e);
			}
		}
		return list;
	}

	public List<T> findAll(Class<T> cls) {
		SqlBuilder<T> sqlBuilder = new SqlBuilder<T>(cls);
		SqlParameter p = sqlBuilder.getSelect();
		List<T> list = new ArrayList<T>();
		Map<String, Field> metaInfo = getMetaInfo(cls);
		Statement st = null;
		ResultSet rs = null;
		try {
			logger.info("execute query:" + p.sql);
			st = connection.createStatement();
			rs = st.executeQuery(p.sql);
			ResultSetMetaData metaData = rs.getMetaData();
			int count = metaData.getColumnCount();
			while (rs.next()) {
				T o = Reflection.newInstance(cls);
				for (int i = 0; i < count; i++) {
					String column = metaData.getColumnName(i + 1);
					Object value = rs.getObject(i + 1);

					Field field = metaInfo.get(column);
					if (field != null) {
						Reflection.setFieldValue(field, o, value);
					}
				}
				list.add(o);
			}

		} catch (SQLException e) {
			logger.error("error:", e);
		} finally {
			try {
				if(rs != null){
					rs.close();
				}
				if(st != null){
					st.close();
				}
			} catch (SQLException e) {
				logger.error("error:", e);
			}
		}
		return list;
	}

	public boolean insert(T o) {
		SqlBuilder<T> sqlBuilder = new SqlBuilder<T>(o);
		SqlParameter p = sqlBuilder.getInsert();
		PreparedStatement st = null;
		try {
			logger.info("execute insert:" + p.sql);
			st = connection.prepareStatement(p.sql);
			int index = 1;
			for (Object object : p.parameters) {
				st.setObject(index, object);
				logger.info("param{}:{}",new Object[]{index,object});
				index++;
			}
			st.execute();
			return true;
		} catch (SQLException e) {
			logger.error("error:", e);
		} finally {
			try {
				if(st != null){
					st.close();
				}
			} catch (SQLException e) {
				logger.error("error:", e);
			}
		}
		return false;
	}
}
