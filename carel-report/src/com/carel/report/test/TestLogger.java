package com.carel.report.test;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLogger {
	private static Logger logger = LoggerFactory.getLogger(TestLogger.class);
	public static void main(String[] args){
		PropertyConfigurator.configure("./log4j.properties");
		logger.error("OK");
	}
}
