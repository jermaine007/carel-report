package com.carel.report;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Config {
	
	public static String DB_DRIVER;
	public static String DB_URL;
	public static String DB_USER;
	public static String DB_PWD;
	public static String DB_NAME;
	public static String OUTPUT_DIR;
	public static String XLS_TPL_PATH;
	public static String XLS_HUMTPL_PATH;
	public static String XLS_TARGET_DIR;
	public static String MAIL_TO;
	public static String MAIL_FROM;
	public static String MAIL_USR;
	public static String MAIL_PWD;
	public static String MAIL_SVR;
	public static String MAIL_PORT;
	private static Logger logger;
	private static boolean isInitialized = false;
	
	public static void  initialize(){
		if(!isInitialized){
			PropertyConfigurator.configure(Config.class.getResourceAsStream("log4j.properties"));
			logger = LoggerFactory.getLogger(Config.class);
			logger.info("Initialize config");
			InputStream is = null;
			try {
				is = Config.class.getResourceAsStream("config.properties");
				Properties properties = new Properties();
				properties.load(is);
				DB_DRIVER = properties.getProperty("db.driver","com.mysql.jdbc.Driver");
				DB_URL = properties.getProperty("db.url", "jdbc:mysql://localhost:3306");
				DB_USER = properties.getProperty("db.user", "careluser");
				DB_PWD = properties.getProperty("db.password", "");
				DB_NAME = properties.getProperty("db.name", "cldb");
				XLS_TPL_PATH = properties.getProperty("excel.tpl.path");
				XLS_HUMTPL_PATH = properties.getProperty("excel.humtpl.path");
				XLS_TARGET_DIR = properties.getProperty("excel.target.dir");
				OUTPUT_DIR = properties.getProperty("output.dir",Config.class.getResource("/").getPath());
				
				MAIL_TO = properties.getProperty("mail.smtp.to");
				MAIL_FROM = properties.getProperty("mail.smtp.from");
				MAIL_USR = properties.getProperty("mail.smtp.user");
				MAIL_PWD =  properties.getProperty("mail.smtp.pwd");
				MAIL_SVR = properties.getProperty("mail.smtp.server");
				MAIL_PORT = properties.getProperty("mail.smtp.port");
				print();
			} catch (Exception e) {
				logger.error("init error:{}",e);
			} finally {
				if (is != null) {

					try {
						is.close();
					} catch (IOException e) {
						logger.error("init error:{}",e);
					}
				}
			}
			isInitialized = true;
		}
	}
	
	
	private static void print(){
		logger.info("Version:{}","1.0.0.0");
		logger.info("Author:{}","Jermaine");
		logger.info("DBUrl:{}",DB_URL);
		logger.info("DBUser:{}",DB_USER);
		logger.info("DB Password:{}",DB_PWD);
		logger.info("Xls template path:{}",XLS_TPL_PATH);
		logger.info("Xls target path:{}",XLS_TARGET_DIR);
		logger.info("OutputDir:{}",OUTPUT_DIR);
		logger.info("mail.to:{}",MAIL_TO);
		logger.info("mail.from:{}",MAIL_FROM);
		logger.info("mail.user:{}",MAIL_USR);
		logger.info("mail.password:{}",MAIL_PWD);
		logger.info("mail.server:{}",MAIL_SVR);
		logger.info("mail.port:{}",MAIL_PORT);
	}
}
