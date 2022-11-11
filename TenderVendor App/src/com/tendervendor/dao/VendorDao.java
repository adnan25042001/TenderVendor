package com.tendervendor.dao;

import java.util.List;

import com.tendervendor.exception.VendorException;
import com.tendervendor.model.Bid;

public interface VendorDao {
	
	public boolean addVendor(String vname, String vmob, String vemail, String password, String company, String address) throws VendorException;
	
	public String changePassword(String email, String oldPassword, String newPassword) throws VendorException;
	
	public List<Bid> allBidsOfUser(int vid) throws VendorException;
	
}
