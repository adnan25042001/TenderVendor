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
			ps.setString(1, "username");
			ps.setString(2, "password");

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("Login Successfull...");
			}

		} catch (SQLException e) {
			msg = e.getMessage();
		}

		return msg;
	}

	@Override
	public String loginUser(String email, String password) throws SQLException {
		String msg = "Invalid username or password";

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from admin where username = ? AND password = ?");
			ps.setString(1, "username");
			ps.setString(2, "password");

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("Login Successfull...");
			}

		} catch (SQLException e) {
			msg = e.getMessage();
		}

		return msg;
	}

	@Override
	public String signupUser(String name, String mobile, String email, String password, String company, String address)
			throws SQLException {
		String msg = "Invalid username or password";

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from admin where username = ? AND password = ?");
			ps.setString(1, "username");
			ps.setString(2, "password");

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("Login Successfull...");
			}

		} catch (SQLException e) {
			msg = e.getMessage();
		}

		return msg;
	}

}
