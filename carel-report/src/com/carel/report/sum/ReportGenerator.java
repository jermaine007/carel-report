package com.carel.report.sum;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class ReportGenerator {

	private String template;
	private String startDate;
	private String endDate;
	private boolean isHumLine;

	public ReportGenerator() {
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setHumLine(boolean isHumLine){
		this.isHumLine = isHumLine;
	}
	
	public void export(String path) throws ParsePropertyException, InvalidFormatException, IOException {
		ReportDataSource dataSource = new ReportDataSource();
		dataSource.setStartDate(startDate);
		dataSource.setEndDate(endDate);
		dataSource.setHumLine(isHumLine);
		dataSource.fetchAll();
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("dataSource", dataSource);
		XLSTransformer transformer = new XLSTransformer();
		transformer.transformXLS(template, beans, path);
	}
}
