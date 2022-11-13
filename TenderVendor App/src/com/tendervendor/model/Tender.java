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

	public Tender(String tid, String tname, String ttype, int tprice, String tlocation, String tdeadline,
			String tdesc) {
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

	public void setTdeadline(String tdeadline) {
		Date d = null;
		try {
			d = new SimpleDateFormat("yyyy-mm-dd").parse(tdeadline);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		this.tdeadline = d;
	}

	public String getTdesc() {
		return tdesc;
	}

	public void setTdesc(String tdesc) {
		this.tdesc = tdesc;
	}

	public static void tenderHead() {

		String vf = "| %-20s | %-54s | %-29s | %20s | %-54s | %-21s | %-149s |%n";

		tenderLine();
		System.out.format(vf, g + "Tender Id" + r, g + "Name" + r, g + "Type" + r, g + "Price" + r, g + "Location" + r,
				g + "Deadline" + r, g + "Description" + r);
		tenderLine();

	}

	public void tenderData() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		System.out.format(tenderFormat, tid, tname, ttype, tprice, tlocation, sdf.format(tdeadline), tdesc);
		tenderLine();

	}

	public String tenderFormat = "| %-11s | %-45s | %-20s | %11d | %-45s | %-12s | %-140s |%n";

	public static void tenderLine() {

		String tline = "+ %284s + %n";

		String tl = "";
		for (int i = 0; i < 302; i++) {
			tl += "-";
		}
		System.out.format(tline, tl);

	}

	public static final String g = "\u001B[32m";

	public static final String r = "\u001B[0m";

}
