package com.carel.report.model;

import com.carel.report.dao.Column;
import com.carel.report.dao.Table;

@Table("dict_config")
public class DictConfigItem {
	
	@Column(name="value")
	private String value;
	
	@Column(name="configtype")
	private String configType;
	
	@Column(name="tag")
	private String tag;
	
	@Column(name="remark")
	private String remark;
	
	public String getConfigType() {
		return configType;
	}
	public void setConfigType(String configType) {
		this.configType = configType;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
