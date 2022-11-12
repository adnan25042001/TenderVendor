package com.tendervendor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tendervendor.exception.VendorException;
import com.tendervendor.model.Vendor;
import com.tendervendor.utility.DBUtil;

public class LoginAndSignupDaoImpl implements LoginAndSignupDao {

	@Override
	public boolean loginAdmin(String username, String password) throws SQLException {
		boolean flag = false;

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from admin where username = ?");
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				String pass = rs.getString("password");

				if (password.equals(pass)) {
					flag = true;
				} else {
					throw new SQLException("Wrong Password!");
				}

			} else {
				throw new SQLException("Wrong Userneme!");
			}

		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}

		return flag;
	}

	@Override
	public boolean loginUser(String email, String password) throws SQLException {
		boolean flag = false;

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from vendor where vemail = ?");
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String pass = rs.getString("password");
				if (password.equals(pass)) {
					flag = true;
				} else {
					throw new SQLException("Wrong password!");
				}
			} else {
				throw new SQLException("Wrong Email!");
			}

		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}

		return flag;
	}

	@Override
	public boolean signupUser(Vendor vendor) throws SQLException {
		boolean flag = false;

		VendorDao vd = new VendorDaoImpl();

		try {
			flag = vd.addVendor(vendor);
		} catch (VendorException e) {
			e.printStackTrace();
		}

		return flag;
	}

}
