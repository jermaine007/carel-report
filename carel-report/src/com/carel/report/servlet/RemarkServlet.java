package com.carel.report.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carel.report.dao.DictConfigItemDao;
import com.carel.report.dao.ProductInfoDao;
import com.carel.report.model.DictConfigItem;
import com.carel.report.model.ProductInfo;
import com.carel.report.utils.UrlParams;
import com.carel.report.utils.Utils;

public class RemarkServlet extends BaseHttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1773448586982846661L;

	private static final String CFG_TYPE = "RM";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
		UrlParams params = new UrlParams(req);
		getAllRemarks(params,resp);
	}

	private void getAllRemarks(UrlParams params, HttpServletResponse response) throws IOException {
		logger.debug("get all remarks");
		String productId = params.getString("productId");
		String line = params.getString("line");
		if(Utils.isNullOrEmpty(productId)){
			JsonResult.postFailed(response, "缺少查询的产品名称");
			return;
		}
		String family = getFamily(productId);
		DictConfigItemDao dao = new DictConfigItemDao();
		List<DictConfigItem> list = dao.where("configtype=? and remark=? and tag=?", CFG_TYPE, family, line);
		if (list.size() == 0) {
			logger.debug("query result is empty");
		}
		JsonResult.post(response, "success", true, list);
	}
	
	private String getFamily(String productId) {
		logger.debug("product id:{}", productId);
		ProductInfoDao dao = new ProductInfoDao();
		ProductInfo o = dao.first("product=?", productId);
		return o == null ? null : o.getFamily();
	}
}
