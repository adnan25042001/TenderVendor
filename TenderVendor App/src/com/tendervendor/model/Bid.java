package com.tendervendor.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bid {

	private String bid;
	private String tid;
	private String vid;
	private int bidAmount;
	private Date deadline;
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

		Date d = null;
		try {
			d = new SimpleDateFormat("yyyy-mm-dd").parse(deadline);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		this.deadline = d;

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

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		Date d = null;
		try {
			d = new SimpleDateFormat("yyyy-mm-dd").parse(deadline);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		this.deadline = d;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return "Bid [BidId=" + bid + ", TenderId=" + tid + ", VendorId=" + vid + ", BidAmount =" + bidAmount
				+ ", Deadline =" + sdf.format(deadline) + ", Status=" + status + "]";
	}

	public static void bidHead() {

		String vf = "| %-20s | %-20s | %-20s | %19s | %-19s | %-20s |%n";

		bidLine();
		System.out.format(vf, g + "Bid Id" + r, g + "Tender Id" + r, g + "Vendor Id" + r, g + "Bid Amount" + r,
				g + "Deadline" + r, g + "Status" + r);
		bidLine();

	}

	public void bidData() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		System.out.format(bidFormat, bid, tid, vid, bidAmount, sdf.format(deadline), status);
		bidLine();

	}

	public String bidFormat = "| %-11s | %-11s | %-11s | %10d | %-10s | %-11s |%n";

	public static void bidLine() {

		String bline = "+ %64s + %n";

		String bl = "";
		for (int i = 0; i < 79; i++) {
			bl += "-";
		}
		System.out.format(bline, bl);

	}

	public static final String g = "\u001B[32m";

	public static final String r = "\u001B[0m";

}
