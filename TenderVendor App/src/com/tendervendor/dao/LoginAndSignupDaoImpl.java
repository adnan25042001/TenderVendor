package com.tendervendor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tendervendor.exception.VendorException;
import com.tendervendor.usecases.LoginSignup;
import com.tendervendor.utility.DBUtil;

public class LoginAndSignupDaoImpl implements LoginAndSignupDao {

	@Override
	public boolean loginAdmin(String username, String password) throws SQLException {
		boolean flag = false;

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from admin where username = ? AND password = ?");
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public boolean loginUser(String email, String password) throws SQLException {
		boolean flag = false;

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from vendor where vemail = ? AND password = ?");
			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
//				String username = rs.getString("vname");
//				msg = "Login Successfull..." + "\n" + "Welcome :" + username;
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public boolean signupUser(String name, String mobile, String email, String password, String company, String address)
			throws SQLException {
		boolean flag = false;
		
		VendorDao vd = new VendorDaoImpl();
		
		try {
			 flag = vd.addVendor(name, mobile, email, password, company, address);
		} catch (VendorException e) {
			e.printStackTrace();
		}
		
		return flag;		
	}

	@Override
	public void logout() {
		
		System.out.println("Logout successfull...");
		
		LoginSignup.menu();
		
	}

}
