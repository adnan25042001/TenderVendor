package com.tendervendor.model;

public class Vendor {
	
	private String vid;
	private String vname;
	private String vmob;
	private String vemail;
	private String password;
	private String company;
	private String address;
	
	public Vendor() {
		super();
	}

	public Vendor(String vid, String vname, String vmob, String vemail, String password, String company, String address) {
		super();
		this.vid = vid;
		this.vname = vname;
		this.vmob = vmob;
		this.vemail = vemail;
		this.password = password;
		this.company = company;
		this.address = address;
	}

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public String getVmob() {
		return vmob;
	}

	public void setVmob(String vmob) {
		this.vmob = vmob;
	}

	public String getVemail() {
		return vemail;
	}

	public void setVemail(String vemail) {
		this.vemail = vemail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Vendor [VendorId=" + vid + ", Name=" + vname + ", Mobile=" + vmob + ", Email=" + vemail + ", Password="
				+ password + ", Company=" + company + ", Address=" + address + "]";
	}

}
