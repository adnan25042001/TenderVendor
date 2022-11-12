package com.tendervendor.dao;

import java.sql.SQLException;

import com.tendervendor.model.Vendor;

public interface LoginAndSignupDao {
	
	public boolean loginAdmin(String username, String password) throws SQLException;
	
	public boolean loginUser(String email, String password) throws SQLException;
	
	public boolean signupUser(Vendor vendor) throws SQLException;
	
}
