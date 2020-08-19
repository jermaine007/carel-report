package com.carel.report.sum;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carel.report.dao.FailureProductSumDao;
import com.carel.report.dao.ProductOrderDao;
import com.carel.report.model.FailureProductSum;
import com.carel.report.model.ProductOrder;

public class PageReport {
	private Logger logger = LoggerFactory.getLogger(PageReport.class);
	private Map<String, CountModel> map = new HashMap<String, CountModel>();
	private static final String ICT_NTF = "ICT NTF";
	private static final List<String> WELD_LIST_PTH = Arrays.asList("²¨·åº¸¿Õº¸","²¨·åº¸¶ÌÂ·");
	private static final List<String> WELD_LIST_SMD = Arrays.asList("SMT ¶ÌÂ·","SMT ¿Õº¸");
	private static final List<String> POSITION_LIST_PTH = Arrays.asList("Â©²å¼þ","²å´í¼þ");
	private static final List<String> POSITION_LIST_SMD = Arrays.asList("SMT È±¼þ","SMT ´í¼þ","SMT Î»ÖÃ²»×¼");
	private static final List<String> BAD_LIST = Arrays.asList("SMT ²ÄÁÏ²»Á¼","²å¼þ²ÄÁÏ²»Á¼");
	
	public PageReport(String startDate,String endDate){
		init(startDate,endDate);
	}

	private void init(String startDate,String endDate) {
		logger.debug("Query for date:{}-{}",new String[]{startDate,endDate});
		ProductOrderDao productOrderDao = new ProductOrderDao();
		List<ProductOrder> orders = productOrderDao.where("lastmodified>=? and lastmodified<=?", startDate,endDate);
		if(orders.size() ==0){
			return;
		}
		FailureProductSumDao dao = new FailureProductSumDao();
		for (ProductOrder productOrder : orders) {
			String id = productOrder.getSerialId();
			fillMap(id,dao);
		}
		
	}
	
	public Collection<CountModel> getModels(){
		return this.map.values();
	}

	private CountModel getCountModel(FailureProductSum o){
		String family = o.getFamily();
		if(map.containsKey(family)){
			return map.get(family);
		}
		logger.debug("find a new family:{}",family);
		CountModel cm = new CountModel();
		cm.setFamily(family);
		map.put(family, cm);
		return cm;
	}
	
	private void fillMap(String id, FailureProductSumDao dao) {
		List<FailureProductSum> list = dao.where("serialid=? and line<>'HUM'", id);
		for (FailureProductSum o : list) {
			String failureMode = o.getFailureMode();
			int count = o.getCount().intValue();
			if(ICT_NTF.equals(failureMode)){
				logger.debug("Find {} count:{}",new Object[]{failureMode,count});
				CountModel cm = getCountModel(o);
				cm.setNtf(cm.getNtf()+count);
			} else if(WELD_LIST_PTH.contains(failureMode)){
				logger.debug("Find {} count:{}",new Object[]{failureMode,count});
				CountModel cm = getCountModel(o);
				cm.setWeldPth(cm.getWeldPth()+count);
			} else if(WELD_LIST_SMD.contains(failureMode)){
				logger.debug("Find {} count:{}",new Object[]{failureMode,count});
				CountModel cm = getCountModel(o);
				cm.setWeldSmd(cm.getWeldSmd()+count);
			} else if(POSITION_LIST_PTH.contains(failureMode)){
				logger.debug("Find {} count:{}",new Object[]{failureMode,count});
				CountModel cm = getCountModel(o);
				cm.setPositionPth(cm.getPositionPth()+count);
			} else if(POSITION_LIST_SMD.contains(failureMode)){
				logger.debug("Find {} count:{}",new Object[]{failureMode,count});
				CountModel cm = getCountModel(o);
				cm.setPositionSmd(cm.getPositionSmd()+count);
			} else if(BAD_LIST.contains(failureMode)){
				logger.debug("Find {} count:{}",new Object[]{failureMode,count});
				CountModel cm = getCountModel(o);
				cm.setBad(cm.getBad()+count);
			} 
		}
	}
}
