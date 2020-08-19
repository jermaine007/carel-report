package com.carel.report.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carel.report.sum.PageReport;
import com.carel.report.utils.UrlParams;
import com.carel.report.utils.Utils;

public class PageReportServlet extends BaseHttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1320063002196872588L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
		UrlParams params = new UrlParams(req);
		
		getModels(params,resp);
	}

	private void getModels(UrlParams params, HttpServletResponse response) throws IOException {
		String startDate = params.getString("startDate");
		String endDate = params.getString("endDate");
		if(Utils.isNullOrEmpty(startDate,endDate)){
			JsonResult.postFailed(response, "缺少查询时间，操作无法继续");
			return;
		}
		logger.debug("get page report for:{}-{}",new String[]{startDate,endDate});
		PageReport report = new PageReport(startDate,endDate);
		JsonResult.post(response, "success", true, report.getModels());
		
	}
}
