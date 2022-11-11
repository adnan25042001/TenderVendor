package com.tendervendor.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IDUtil {
	
	public static String generateId() {
		String tid = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyddmmss");
		
		tid = sdf.format(new Date());
		
		return tid;
	}
	
}
