package com.carel.report.model;

import com.carel.report.dao.Column;

public class SublineInfo {
	
	@Column(name="name")
	private String name;
	
	@Column(name="ipstart")
	private String ipstart;
	
	@Column(name="ipend")
	private String ipend;
	
	@Column(name="status")
	private String status;

	@Column(name="line")
	private String line;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIpstart() {
		return ipstart;
	}

	public void setIpstart(String ipstart) {
		this.ipstart = ipstart;
	}

	public String getIpend() {
		return ipend;
	}

	public void setIpend(String ipend) {
		this.ipend = ipend;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}
}
