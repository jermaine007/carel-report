package com.carel.report.dao;

import java.util.List;

public interface IBaseDao<T> {
	public abstract List<T> findAll();

	public abstract List<T> where(String query, Object... params);

	public abstract T first(String query, Object... params);
	
	public abstract boolean update(String update,String query,Object...params);
	
	public abstract boolean delete(String query,Object...params);
	
	public abstract boolean delete();

	public abstract boolean insert(T o);
}
