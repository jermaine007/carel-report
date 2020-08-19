package com.carel.report.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carel.report.Config;
//import com.carel.report.excel.ReportGenerator;
import com.carel.report.sum.ReportGenerator;
import com.carel.report.utils.Utils;

public class ExportReportServlet extends BaseHttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4654570721244474669L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);

		try {
			String path = exportExcel(req);
			if(path == null){
				resp.getWriter().write("没有查询日期，无法继续操作");
				return;
			}
			File file = new File(path);
			String fileName = file.getName();
	        InputStream fis = new BufferedInputStream(new FileInputStream(file));
	        byte[] buffer = new byte[fis.available()];
	        fis.read(buffer);
	        fis.close();
	        resp.reset();
	        resp.addHeader("Content-Disposition", "attachment;filename=" + fileName);
	        resp.addHeader("Content-Length", "" + file.length());
	        OutputStream toClient = new BufferedOutputStream(resp.getOutputStream());
	        resp.setContentType("application/octet-stream");
	        toClient.write(buffer);
	        toClient.flush();
	        toClient.close();
		} catch(Exception e){
			logger.error("export error:{}",e);
			throw new ServletException(e);
		}
	}

	private String exportExcel(HttpServletRequest req)
			throws Exception {
		String startDate = req.getParameter("startDate");
		String endDate = req.getParameter("endDate");
		String line = req.getParameter("line");
		if(Utils.isNullOrEmpty(startDate,endDate,line)){
			logger.error("no query date, error");
			return null;
		}
		boolean isHumLine = "0".equals(line);
		
		String currentPath = getServletContext().getRealPath("/");
		String template = String.format("%s/%s",currentPath, isHumLine ? Config.XLS_HUMTPL_PATH: Config.XLS_TPL_PATH);
		
		String path = String.format("%s/%s/%s_%s_%s.xls",currentPath, Config.XLS_TARGET_DIR,
				(isHumLine? "report_hum": "report"),
				startDate,endDate);
		logger.debug("date:{}-{},template:{},path:{}",new String[]{startDate,endDate,template,path} );
		
		
		logger.debug("export report for date:{}-{}",new String[]{startDate,endDate});
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
		ReportGenerator report = new ReportGenerator();
		report.setStartDate(startDate);
		report.setEndDate(endDate);
		report.setTemplate(template);
		report.setHumLine(isHumLine);
		report.export(path);
		return path;
	}
}
