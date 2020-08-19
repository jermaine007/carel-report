package com.carel.report.model;

import com.carel.report.dao.Column;
import com.carel.report.dao.DBType;
import com.carel.report.dao.Table;

@Table("product_order")
public class ProductOrder {
	
	@Column(name="serialid")
	private String serialId;
	
	@Column(name="productid")
	private String productId;
	
	@Column(name="amount",type = DBType.INT)
	private Integer amount;
	
	@Column(name="completedamount",type = DBType.INT)
	private Integer completedAmount;
	
	@Column(name="createddate")
	private String createdDate;
	
	@Column(name="lastmodified")
	private String lastModified;
	
	@Column(name="remark1")
	private String remark1;
	
	@Column(name="remark2")
	private String remark2;
	
	public String getSerialId() {
		return serialId;
	}
	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getCompletedAmount() {
		return completedAmount;
	}
	public void setCompletedAmount(int completedAmount) {
		this.completedAmount = completedAmount;
	}
	public String getRemark2() {
		return remark2;
	}
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	public String getRemark1() {
		return remark1;
	}
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	public String getLastModified() {
		return lastModified;
	}
	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
}
