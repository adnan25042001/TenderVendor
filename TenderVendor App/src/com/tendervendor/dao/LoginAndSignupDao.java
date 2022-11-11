package com.tendervendor.dao;

import java.sql.SQLException;

public interface LoginAndSignupDao {
	
	public boolean loginAdmin(String username, String password) throws SQLException;
	
	public boolean loginUser(String email, String password) throws SQLException;
	
	public boolean signupUser(String name, String mobile, String email, String password, String company, String address) throws SQLException;

	public void logout();
	
}
