package com.carel.report.model;

import com.carel.report.dao.Column;
import com.carel.report.dao.Table;

@Table("product_info")
public class ProductInfo {
	
	@Column(name="family")
	private String family;
	
	@Column(name="product")
	private String product;
	
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
}
