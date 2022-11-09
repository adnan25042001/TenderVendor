package com.tendervendor.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TenderStatus {

	private int tid;
	private String tname;
	private String ttype;
	private int tprice;
	private String tlocation;
	private Date tdeadline;
	private String tdesc;
	private String status;

	public TenderStatus() {
		super();
	}

	public TenderStatus(int tid, String tname, String ttype, int tprice, String tlocation, String tdeadline, String tdesc, String status) {
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
		this.status = status;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TenderStatus [tid=" + tid + ", tname=" + tname + ", ttype=" + ttype + ", tprice=" + tprice
				+ ", tlocation=" + tlocation + ", tdeadline=" + tdeadline + ", tdesc=" + tdesc + ", status=" + status
				+ "]";
	}

}
