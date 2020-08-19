package com.carel.report.sum;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carel.report.dao.FailureProductSumDao;
import com.carel.report.dao.ProductOrderDao;
import com.carel.report.model.FailureProductSum;
import com.carel.report.model.ProductOrder;

public class ReportDataSource {

	private static final String ICT_NTF = "ICT NTF";
	private Logger logger = LoggerFactory.getLogger(ReportDataSource.class);
	private List<FailureProductSum> allList = new ArrayList<FailureProductSum>();
	private List<FailureProductSum> ntfList = new ArrayList<FailureProductSum>();
	private String startDate;
	private String endDate;
	private boolean isHumLine;

	public ReportDataSource() {
	}

	public void setHumLine(boolean isHumLine) {
		this.isHumLine = isHumLine;	
	}
	public void setStartDate(String startDate){
		this.startDate = startDate;
	}
	
	public void setEndDate(String endDate){
		this.endDate = endDate;
	}
	
	public void fetchAll(){
		init(startDate, endDate, isHumLine);
	}
	
	private void init(String startDate,String endDate , boolean isHumLine) {
		logger.debug("Get sum for:{}-{},HUMLine:{}",  new Object[]{startDate, endDate, isHumLine});
		ProductOrderDao dao = new ProductOrderDao();
		List<ProductOrder> list = dao.where("lastmodified>=? and lastmodified<=?", startDate,endDate);

		if (list.size() == 0) {
			logger.debug("reuslt is empty}");
			return;
		}

		FailureProductSumDao failureProductSumDao = new FailureProductSumDao();
		for (ProductOrder productOrder : list) {
			String serialId = productOrder.getSerialId();
			sperate(serialId, failureProductSumDao , isHumLine);
		}
	}

	private void sperate(String serialId,
			FailureProductSumDao failureProductSumDao,boolean isHumLine) {
		logger.debug("seprate results for order:{}", serialId);
		String wherePart =String.format("serialid=? and line%s'HUM'", (isHumLine?"=":"<>"));
		List<FailureProductSum> list = failureProductSumDao.where(wherePart, serialId);
		for (FailureProductSum o : list) {
			String mode = o.getFailureMode();

			if (ICT_NTF.equals(mode)) {
				ntfList.add(o);
			} else {
				allList.add(o);
			}
		}
	}

	public List<FailureProductSum> getNtfList() {
		return this.ntfList;

	}

	public List<FailureProductSum> getAllList() {
		return this.allList;
	}	
}
