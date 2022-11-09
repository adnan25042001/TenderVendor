package com.tendervendor.dao;

import java.sql.SQLException;

public interface LoginAndSignupDao {
	
	public String loginAdmin(String username, String password) throws SQLException;
	
	public String loginUser(String email, String password) throws SQLException;
	
	public String signupUser(String name, String mobile, String email, String password, String company, String address) throws SQLException;

}
