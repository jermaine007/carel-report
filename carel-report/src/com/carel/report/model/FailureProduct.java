package com.carel.report.model;

import com.carel.report.dao.Column;
import com.carel.report.dao.DBType;
import com.carel.report.dao.Table;

@Table("failure_product")
public class FailureProduct {

	@Column(name = "failureid", type = DBType.INT)
	private Integer failureId;

	@Column(name = "serialid")
	private String serialId;

	@Column(name = "familyName")
	private String familyName;

	@Column(name = "product")
	private String product;

	@Column(name = "failuremode")
	private String failureMode;

	@Column(name = "line")
	private String line;

	@Column(name = "amount", type = DBType.INT)
	private Integer amount;

	@Column(name = "createddate")
	private String createdDate;

	@Column(name = "operator")
	private String operator;

	@Column(name = "remark1")
	private String remark1;

	@Column(name = "remark2")
	private String remark2;

	@Column(name = "subline")
	private String subline;
	
	public Integer getFailureId() {
		return failureId;
	}

	public void setFailureId(Integer failureId) {
		this.failureId = failureId;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getFailureMode() {
		return failureMode;
	}

	public void setFailureMode(String failureMode) {
		this.failureMode = failureMode;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getSerialId() {
		return serialId;
	}

	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}

	public String getSubline() {
		return subline;
	}

	public void setSubline(String subline) {
		this.subline = subline;
	}

}
