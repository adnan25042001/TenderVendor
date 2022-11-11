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

	@Override
	public String toString() {
		return "TenderStatus [TenderId=" + tid + ", BidId=" + bid + ", VendorId=" + vid + ", Status=" + status + "]";
	}
	
	

}
