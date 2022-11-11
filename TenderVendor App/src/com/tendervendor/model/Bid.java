package com.tendervendor.model;

public class Bid {

	private String bid;
	private String tid;
	private String vid;
	private int bidAmount;
	private String deadline;
	private String status;

	public Bid() {
		super();
	}

	public Bid(String bid, String tid, String vid, int bidAmount, String deadline, String status) {
		super();
		this.bid = bid;
		this.tid = tid;
		this.vid = vid;
		this.bidAmount = bidAmount;
		this.deadline = deadline;
		this.status = status;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public int getBidAmount() {
		return bidAmount;
	}

	public void setBidAmount(int bidAmount) {
		this.bidAmount = bidAmount;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Bid [BidId=" + bid + ", TenderId=" + tid + ", VendorId=" + vid + ", BidAmount =" + bidAmount
				+ ", Deadline =" + deadline + ", Status=" + status + "]";
	}

}
