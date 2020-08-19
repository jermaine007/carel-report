package com.carel.report.utils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

public class UrlParams {
	private Map<String, String> params = new LinkedHashMap<String, String>();

	public UrlParams(HttpServletRequest request) {
		Map<String, String[]> map = request.getParameterMap();
		for (Entry<String, String[]> e : map.entrySet()) {
			params.put(e.getKey(), e.getValue()[0]);
		}
	}
	
	public String getString(String key) {
		return params.containsKey(key) ? params.get(key) : "";
	}

	public int getInt(String key) {
		String value = params.get(key);
		if (value == null || "".equals(value)) {
			return -1;
		}
		return Integer.parseInt(value);
	}

	public double getDouble(String key) {
		String value = params.get(key);
		if (value == null || "".equals(value)) {
			return Double.NaN;
		}
		return Double.parseDouble(value);
	}

	public float getFloat(String key) {
		String value = params.get(key);
		if (value == null || "".equals(value)) {
			return Float.NaN;
		}
		return Float.parseFloat(value);
	}
}
