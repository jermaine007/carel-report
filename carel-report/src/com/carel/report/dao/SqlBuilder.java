package com.carel.report.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carel.report.utils.Utils;

public class SqlBuilder<T> {
	
	private Logger logger = LoggerFactory.getLogger(SqlBuilder.class);
	public static final MetaInfo META_INFO = new MetaInfo();
	private T instance;
	private String clazz;
	private String tableName;

	private static String INSERT_STATEMENT = "INSERT INTO %s (%s) VALUES (%s)";
	private static String QUERY_STATEMENT = "SELECT %s FROM %s";
	private static String QUERY_STATEMENT1 = "SELECT %s FROM %s where %s";
	private static String DELETE_STATEMENT = "DELETE FROM %s";
	private static String DELETE_STATEMENT1 = "DELETE FROM %s where %s";
	private static String UPDATE_STATEMENT1 =  "UPDATE %s set %s where %s";
	
	public SqlBuilder(T instance) {
		this(instance.getClass());
		this.instance = instance;

	}

	public SqlBuilder(Class<?> cls) {
		this.tableName = getTableName(cls);
		this.clazz = cls.getCanonicalName();
		init(cls);
	}

	private void init(Class<?> cls) {
		logger.debug("Get meta info:{}",cls);
		String key = this.clazz;
		if (META_INFO.containsKey(key)) {
			return;
		}
		Map<String, Field> info = new HashMap<String, Field>();
		Field[] fields = Reflection.getAnnotationPresentFields(cls,
				Column.class);
		for (Field o : fields) {
			
			String columnName = getColumnName(o);
			logger.debug("find annotation present, field:{},column:{}",
					new String[]{o.getName(),columnName});
			info.put(columnName, o);
		}
		META_INFO.put(key, info);
	}
	
	public SqlParameter getUpdate(String update,String query,Object...params) {
		SqlParameter p = new SqlParameter();
		String sql = String.format(UPDATE_STATEMENT1,tableName,update,query);
		p.sql = sql;
		for (int i = 0; i < params.length; i++) {
			p.parameters.add(params[i]);
		}
		return p;
	}
	
	public SqlParameter getDelete(){
		SqlParameter p = new SqlParameter();
		String sql = String.format(DELETE_STATEMENT, tableName);
		p.sql = sql;
		return p;
	}
	
	public SqlParameter getDelete(String query,Object...params){
		SqlParameter p = new SqlParameter();
		String sql = String.format(DELETE_STATEMENT1,tableName,query);
		p.sql = sql;
		for (int i = 0; i < params.length; i++) {
			p.parameters.add(params[i]);
		}
		return p;
	}
	
	public SqlParameter getSelect(String query,Object... params){
		SqlParameter p = new SqlParameter();
		Map<String, Field> fields = META_INFO.get(this.clazz);
		List<String> fieldsPart = new ArrayList<String>();
		for (String o : fields.keySet()) {
			fieldsPart.add(o);
		}
		String sql = String.format(QUERY_STATEMENT1,
				Utils.join(",", fieldsPart), tableName,query);
		p.sql = sql;
		for (int i = 0; i < params.length; i++) {
			p.parameters.add(params[i]);
		}
		return p;
	}
	
	public SqlParameter getSelect() {
		SqlParameter p = new SqlParameter();
		Map<String, Field> fields = META_INFO.get(this.clazz);
		List<String> fieldsPart = new ArrayList<String>();
		for (String o : fields.keySet()) {
			fieldsPart.add(o);
		}
		String sql = String.format(QUERY_STATEMENT,
				Utils.join(",", fieldsPart), tableName);
		p.sql = sql;
		return p;
	}

	/**
	 * get insert sql
	 * 
	 * @return
	 */
	public SqlParameter getInsert() {
		SqlParameter p = new SqlParameter();
		Map<String, Field> fields = META_INFO.get(this.clazz);
		List<String> fieldsPart = new ArrayList<String>();
		List<String> paramsPart = new ArrayList<String>();
		for (Entry<String, Field> o : fields.entrySet()) {
			Object value = Reflection.getFieldValue(o.getValue(), instance);
			if (value != null) {
				fieldsPart.add(o.getKey());
				paramsPart.add("?");
				p.parameters.add(value);
			}
		}
		String sql = String.format(INSERT_STATEMENT, tableName,
				Utils.join(",", fieldsPart),
				Utils.join(",", paramsPart));
		p.sql = sql;
		return p;
	}

	private String getTableName(Class<?> cls) {
		logger.debug("get table name for:{}", cls);
		Table t = cls.getAnnotation(Table.class);
		return t.value();
	}

	private String getColumnName(Field field) {
		Column column = field.getAnnotation(Column.class);
		return column.name();

	}

	static class MetaInfo extends HashMap<String, Map<String, Field>> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 5527138142798290333L;

	}

}
