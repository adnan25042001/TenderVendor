package com.tendervendor.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tender {

	private String tid;
	private String tname;
	private String ttype;
	private int tprice;
	private String tlocation;
	private Date tdeadline;
	private String tdesc;

	public Tender() {
		super();
	}

	public Tender(String tid, String tname, String ttype, int tprice, String tlocation, String tdeadline, String tdesc) {
		super();
		this.tid = tid;
		this.tname = tname;
		this.ttype = ttype;
		this.tprice = tprice;
		this.tlocation = tlocation;

		Date d = null;
		try {
			d = new SimpleDateFormat("yyyy-mm-dd").parse(tdeadline);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		this.tdeadline = d;
		this.tdesc = tdesc;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTtype() {
		return ttype;
	}

	public void setTtype(String ttype) {
		this.ttype = ttype;
	}

	public int getTprice() {
		return tprice;
	}

	public void setTprice(int tprice) {
		this.tprice = tprice;
	}

	public String getTlocation() {
		return tlocation;
	}

	public void setTlocation(String tlocation) {
		this.tlocation = tlocation;
	}

	public Date getTdeadline() {
		return tdeadline;
	}

	public void setTdeadline(Date tdeadline) {
		this.tdeadline = tdeadline;
	}

	public String getTdesc() {
		return tdesc;
	}

	public void setTdesc(String tdesc) {
		this.tdesc = tdesc;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return "Tender [TenderId=" + tid + ", Name=" + tname + ", Type=" + ttype + ", Price=" + tprice + ", Location="
				+ tlocation + ", Deadline=" + sdf.format(tdeadline) + ", Description=" + tdesc + "]";
	}

}
