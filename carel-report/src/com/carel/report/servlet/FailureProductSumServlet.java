package com.carel.report.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carel.report.dao.FailureProductSumDao;
import com.carel.report.model.FailureProductSum;
import com.carel.report.utils.UrlParams;
import com.carel.report.utils.Utils;

public class FailureProductSumServlet extends BaseHttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8238053235466818382L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
		UrlParams params = new UrlParams(req);
		String serialId = params.getString("serialId");
		if (Utils.isNullOrEmpty(serialId)) {
			JsonResult.postFailed(resp, "缺少订单必须要元素,操作无法继续");
			return;
		}

		FailureProductSumDao dao = new FailureProductSumDao();
		List<FailureProductSum> list = dao.where("serialid=?", serialId);
		if (list.size() == 0) {
			logger.debug("found no record");
		}

		JsonResult.post(resp, "success", true, list);
	}

}
