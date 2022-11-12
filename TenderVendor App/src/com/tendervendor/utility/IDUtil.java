package com.tendervendor.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IDUtil {
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyddmmss");
	
	public static String generateTenderId() {
		String tid = null;
		
		tid = "T" + sdf.format(new Date());
		
		return tid;
	}
	
	public static String generateVenderId() {
		String vid = null;
		
		vid = "V" + sdf.format(new Date());
		
		return vid;
	}
	
	public static String generateBidId() {
		String bid = null;
		
		bid = "B" + sdf.format(new Date());
		
		return bid;
	}
	
}
