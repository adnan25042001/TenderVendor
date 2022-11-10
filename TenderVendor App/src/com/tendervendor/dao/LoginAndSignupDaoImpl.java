package com.tendervendor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tendervendor.utility.DBUtil;

public class LoginAndSignupDaoImpl implements LoginAndSignupDao {

	@Override
	 public String loginAdmin(String username, String password) throws SQLException {
		String msg = "Invalid username or password";

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from admin where username = ? AND password = ?");
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				msg = "Login Successfull..." + "\n" + "Welcome :" + username;
			}

		} catch (SQLException e) {
			msg = e.getMessage();
		}

		return msg;
	}

	@Override
	public String loginUser(String email, String password) throws SQLException {
		String msg = "Invalid email or password";

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from vendor where vemail = ? AND password = ?");
			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String username = rs.getString("vname");
				msg = "Login Successfull..." + "\n" + "Welcome :" + username;
			}

		} catch (SQLException e) {
			msg = e.getMessage();
		}

		return msg;
	}

	@Override
	public String signupUser(String name, String mobile, String email, String password, String company, String address)
			throws SQLException {
		String msg = "Vendor not inserted...";

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select vemail from vendor where vemail = ?");
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("Email already exists...");
			}else {
				
				PreparedStatement ps1 = conn.prepareStatement("insert into vendor(vname,vmob,vemail,password,company,address) values(?,?,?,?,?,?)");
				ps1.setString(1, name);
				ps1.setString(2, mobile);
				ps1.setString(1, email);
				ps1.setString(2, password);
				ps1.setString(1, company);
				ps1.setString(2, address);
				
				int n = ps1.executeUpdate();
				
				if(n > 0)
					System.out.println("Signup Successfull...");
			
			}

		} catch (SQLException e) {
			msg = e.getMessage();
		}

		return msg;
	}

}
