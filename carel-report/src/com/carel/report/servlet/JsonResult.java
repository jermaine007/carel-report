package com.carel.report.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

public class JsonResult {

	public static void post(HttpServletResponse response, String message,
			boolean result) throws IOException {
		JsonResult o = new JsonResult(result, message);
		response.getWriter().write(o.toString());
	}

	public static void post(HttpServletResponse response, String message,
			boolean result, Object data) throws IOException {
		JsonResult o = new JsonResult(result, message, data);
		response.getWriter().write(o.toString());
	}

	public static void postSuccess(HttpServletResponse response)
			throws IOException {
		response.getWriter().write(SUCCESS.toString());
	}

	public static void postFailed(HttpServletResponse response, String message)
			throws IOException {
		FAIL.setMessage(message);
		response.getWriter().write(FAIL.toString());
	}

	public static JsonResult SUCCESS = new JsonResult(true, "");
	public static JsonResult FAIL = new JsonResult(false, "");
	private boolean success;
	private String message;
	private Object data;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public JsonResult() {
	}

	public JsonResult(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public JsonResult(boolean success, String message, Object data) {
		this.success = success;
		this.message = message;
		this.data = data;
	}

	@Override
	public String toString() {
		JSONObject json = new JSONObject();
		json.put("success", this.isSuccess());
		json.put("message", this.getMessage());
		if (this.getData() != null) {
			json.put("data", this.getData());
		}
		return json.toJSONString();
	}

}
