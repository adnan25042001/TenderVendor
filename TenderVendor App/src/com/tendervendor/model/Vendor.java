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

	public Vendor(String vid, String vname, String vmob, String vemail, String password, String company,
			String address) {
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

	public void vendorHead() {
		
		 String vf = "| %-20s | %-40s | %-20s | %-45s | %-30s | %-30s | %-50s |%n";

		vendorLine();
		System.out.format(vf, g+"Vendor Id"+r, g+"Vendor Name"+r, g+"Mobile"+r, g+"Email"+r, g+"Password"+r, g+"Company"+r,
				g+"Address"+r);
		vendorLine();

	}
	
	public void vendorData() {
		
		System.out.format(vendorFormat, vid, vname, vmob, vemail, password, company, address);
		vendorLine();
		
	}

	public String vendorFormat = "| %-11s | %-31s | %-11s | %-36s | %-21s | %-21s | %-41s |%n";

	public void vendorLine() {

		String vendorLine = "+ %172s + %n";

		String vline = "";
		for (int i = 0; i < 190; i++) {
			vline += "-";
		}
		System.out.format(vendorLine, vline);

	}
	
	public static final String g = "\u001B[32m";
	
	public static final String r = "\u001B[0m";

}
