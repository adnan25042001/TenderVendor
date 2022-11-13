package com.tendervendor.model;

public class TenderStatus {
	
	private String tid;
	private String bid;
	private String vid;
	private String status;
	
	public TenderStatus() {
		super();
	}
	
	public TenderStatus(String tid, String bid, String vid, String status) {
		super();
		this.tid = tid;
		this.bid = bid;
		this.vid = vid;
		this.status = status;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static void tenderStatusHead() {
		
		 String vf = "| %-20s | %-20s | %-25s | %-20s |%n";

		tenderStatusLine();
		System.out.format(vf, g+"Tender Id"+r, g+"Bid Id"+r, g+"Vendor Id"+r, g+"status"+r);
		tenderStatusLine();

	}
	
	public void tenderStatusData() {
		
		System.out.format(tenderFormat, tid, bid, vid, status);
		tenderStatusLine();
		
	}

	public String tenderFormat = "| %-11s | %-11s | %-16s | %-11s |%n";

	public static void tenderStatusLine() {

		String tsline = "+ %45s + %n";

		String tsl = "";
		for (int i = 0; i < 58; i++) {
			tsl += "-";
		}
		System.out.format(tsline, tsl);

	}
	
	public static final String g = "\u001B[32m";
	
	public static final String r = "\u001B[0m";

}
