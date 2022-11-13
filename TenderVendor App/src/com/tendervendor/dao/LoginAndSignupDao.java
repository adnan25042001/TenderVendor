package com.tendervendor.dao;

import com.tendervendor.exception.LoginSignupException;
import com.tendervendor.model.Vendor;

public interface LoginAndSignupDao {
	
	public boolean loginAdmin(String username, String password) throws LoginSignupException;
	
	public boolean loginUser(String email, String password) throws LoginSignupException;
	
	public boolean signupUser(Vendor vendor) throws LoginSignupException;
	
}
