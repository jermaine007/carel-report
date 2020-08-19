package com.carel.report.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carel.report.dao.ProductOrderDao;
import com.carel.report.model.ProductOrder;
import com.carel.report.utils.UrlParams;
import com.carel.report.utils.Utils;

public class ProductOrderServlet extends BaseHttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 613008166345501773L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
		checkAndInsertOrder(req, resp);
	}

	private void checkAndInsertOrder(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		UrlParams params = new UrlParams(request);
		String serialId = params.getString("serialId");
		if (Utils.isNullOrEmpty(serialId)) {
			JsonResult.postFailed(response, "缺少订单流水号,操作无法继续");
			return;
		}

		ProductOrderDao dao = new ProductOrderDao();
		boolean exists = dao.exists(serialId);
		if (!exists) {
			insertOrder(params, response, dao);
		} else {
			JsonResult.postSuccess(response);
		}
	}

	private void insertOrder(UrlParams params, HttpServletResponse response,
			ProductOrderDao dao) throws IOException {
		int amount = params.getInt("amount");
		String serialId = params.getString("serialId");
		String productId = params.getString("productId");

		logger.debug("serialid:{},productid:{},amount:{}", new Object[] {
				serialId, productId, amount });

		if (Utils.isNullOrEmpty(serialId, productId) || amount == -1) {
			JsonResult.postFailed(response, "缺少订单必须要元素,操作无法继续");
			return;
		}

		String date = Utils.format(new Date());
		ProductOrder o = new ProductOrder();

		o.setSerialId(serialId);
		o.setAmount(amount);
		o.setProductId(productId);
		o.setCompletedAmount(amount);
		o.setCreatedDate(date);
		o.setLastModified(date);

		boolean success = dao.insert(o);
		String message = success ? "" : "插入订单失败，具体信息请查看日志";
		JsonResult.post(response, message, success);
	}
}
