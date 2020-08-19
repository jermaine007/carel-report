package com.carel.report.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Utils {
	
	public static String join(String op,List<String> list){
		StringBuffer sb = new StringBuffer();
		int length = list.size();
		for (int i = 0; i <length; i++) {
			sb.append(list.get(i));
			if(i<length-1){
				sb.append(op);
			}
		}
		return sb.toString();
	}
	
	public static boolean isNullOrEmpty(String...params){
		for (String o : params) {
			if(o == null || "".equals(o)){
				return true;
			}
		}
		
		return false;
	}
	
	public static String SIMPLE_DATE_FMT= "yyyyMMdd";
	public static String SIMPLE_DATE_FMT2= "yyyy-MM-dd HH:mm:ss";
	public static String format(Date date,String format){
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}
	
	public static String format(Date date) {
		return format(date,SIMPLE_DATE_FMT);
	}
}
