package com.carel.report.dao;

import java.util.ArrayList;
import java.util.List;

public class SqlParameter {
	public String sql;
	public List<Object> parameters;

	public SqlParameter() {
		parameters = new ArrayList<Object>();
	}
}