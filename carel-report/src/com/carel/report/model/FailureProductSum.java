package com.carel.report.model;

import com.carel.report.dao.Column;
import com.carel.report.dao.DBType;
import com.carel.report.dao.Table;

@Table("failure_product_sum")
public class FailureProductSum {
	
	@Column(name="count",type = DBType.INT)
	private Long count;
	
	@Column(name="serialid")
	private String serialId;
	
	@Column(name="family")
	private String family;
	
	@Column(name="product")
	private String product;
	
	@Column(name="failuremode")
	private String failureMode;
	
	@Column(name="line")
	private String line;
	
	@Column(name="amount",type = DBType.INT)
	private Integer amount;
	
	@Column(name="subline")
	private String subline;
	
	@Column(name="createddate")
	private String createdDate;
	
	@Column(name="remark")
	private String remark;
	
	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getSerialId() {
		return serialId;
	}

	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
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

	public String getSubline() {
		return subline;
	}

	public void setSubline(String subline) {
		this.subline = subline;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	} 
}
