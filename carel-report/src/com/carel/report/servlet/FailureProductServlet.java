package com.carel.report.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carel.report.dao.DictConfigItemDao;
import com.carel.report.dao.FailureProductDao;
import com.carel.report.dao.ProductInfoDao;
import com.carel.report.dao.ProductOrderDao;
import com.carel.report.model.DictConfigItem;
import com.carel.report.model.FailureProduct;
import com.carel.report.model.ProductInfo;
import com.carel.report.utils.UrlParams;
import com.carel.report.utils.Utils;

public class FailureProductServlet extends BaseHttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -96134090402417018L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
		insertFailureProduct(req, resp);
	}

	private void insertFailureProduct(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		UrlParams params = new UrlParams(request);
		int amount = params.getInt("amount");
		String serialId = params.getString("serialId");
		String productId = params.getString("productId");
		String failureMode = params.getString("failureMode");
		String remark = params.getString("remark");
		String line = params.getString("line");
		logger.debug("serialid:{},productid:{},amount:{},failuremode:{},line:{}", new Object[] {
				serialId, productId, amount ,failureMode,line});

		if (Utils.isNullOrEmpty(serialId, productId, failureMode)
				|| amount == -1) {
			JsonResult.postFailed(response, "缺少订单必须要元素,操作无法继续");
			return;
		}

		insert(serialId, productId, failureMode, amount,line, remark, response);

	}

	private void insert(String serialId, String productId, String failureMode,
			int amount, String line, String remark, HttpServletResponse response) throws IOException {
		String family = getFamily(productId);
		if(family == null){
			JsonResult.postFailed(response, "找不到此种产品所属的family,操作无法继续");
			return;
		}
		
		FailureProduct o = new FailureProduct();
		o.setAmount(amount);
		o.setCreatedDate(Utils.format(new Date()));
		o.setFailureMode(failureMode);
		o.setFamilyName(family);
		o.setLine(line);
		o.setOperator(getOperator());
		o.setProduct(productId);
		if(!"".equals(remark)){
			o.setRemark1(remark);
		}
		o.setSerialId(serialId);
		
		FailureProductDao dao = new FailureProductDao();
		boolean success = dao.insert(o);
		if(success){
			success = updateProductOrder(serialId) && insertNewRemark(remark,line,family);
		}
		String message = success ? "成功插入一个不良产品记录":"插入不良产品记录失败，请查看日志";
		JsonResult.post(response, message, success);
	}

	private boolean insertNewRemark(String remark,String line, String family) {
		if("".equals(remark)){
			return true;
		}
		DictConfigItemDao dao = new DictConfigItemDao();
		DictConfigItem item = dao.first("value=? and tag=? and configtype=? and remark=?", 
				remark, line, "RM", family);
		if(item == null){
			item = new DictConfigItem();
			item.setConfigType("RM");
			item.setTag(line);
			item.setValue(remark);
			item.setRemark(family);
			return dao.insert(item);
		}
		return true;
	}

	private boolean updateProductOrder(String serialId) {
		logger.info("update product order for {}",serialId);
		ProductOrderDao dao = new ProductOrderDao();
		return dao.update("completedamount = completedamount-1,lastmodified=?",
				"serialId=?",Utils.format(new Date()),serialId);
	}

	private String getOperator() {		
		return null;
	}

	private String getFamily(String productId) {
		logger.debug("product id:{}", productId);
		ProductInfoDao dao = new ProductInfoDao();
		ProductInfo o = dao.first("product=?", productId);
		return o == null ? null : o.getFamily();
	}
}
