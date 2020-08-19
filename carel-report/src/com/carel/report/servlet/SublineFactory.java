package com.carel.report.servlet;

import java.util.List;

import com.carel.report.dao.SublineInfoDao;
import com.carel.report.model.SublineInfo;

public class SublineFactory {
	private static SublineFactory instance;
	private List<SublineInfo> list;
	
	public static SublineFactory getInstance(){
		if(instance == null){
			instance = new SublineFactory();
		}
		return instance;
	}
	
	private SublineFactory(){
		initialize();
	}

	private void initialize() {
		SublineInfoDao dao = new SublineInfoDao();
		list = dao.findAll();
	}
	
	public SublineInfo getInfo(String ip){
		for (SublineInfo o : list) {
			if(isValidIP(o.getIpstart(), o.getIpend(), ip)){
				return o;
			}
		}
		return null;
	}
	
	static boolean isValidIP(String ipstart,String ipend,String ip) {     
	        String[] sips = ipstart.split("\\.");   
	        String[] sipe = ipend.split("\\.");   
	        String[] sipt = ip.split("\\.");   
	        long ips = 0L, ipe = 0L, ipt = 0L;   
	        for (int i = 0; i < 4; ++i) {   
	            ips = ips << 8 | Integer.parseInt(sips[i]);   
	            ipe = ipe << 8 | Integer.parseInt(sipe[i]);   
	            ipt = ipt << 8 | Integer.parseInt(sipt[i]);   
	        }   
	        if (ips > ipe) {   
	            long t = ips;   
	            ips = ipe;   
	            ipe = t;   
	        }   
	        return ips <= ipt && ipt <= ipe;   
	    }   
}
