package com.tendervendor.dao;

import com.tendervendor.exception.VendorException;
import com.tendervendor.model.Vendor;

public interface VendorDao {

	public boolean addVendor(Vendor vendor) throws VendorException;

	public boolean changePassword(String email, String oldPassword, String newPassword) throws VendorException;

	public Vendor getVender(String vid) throws VendorException;

}
