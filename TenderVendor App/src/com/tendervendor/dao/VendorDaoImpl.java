package com.tendervendor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tendervendor.exception.VendorException;
import com.tendervendor.model.Vendor;
import com.tendervendor.utility.DBUtil;

public class VendorDaoImpl implements VendorDao {

	@Override
	public boolean addVendor(Vendor vendor) throws VendorException {
		boolean flag = false;

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select vemail from vendor where vemail = ?");
			ps.setString(1, vendor.getVemail());

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				throw new VendorException("Email already exists...");
			} else {

				PreparedStatement ps1 = conn.prepareStatement(
						"insert into vendor(vid,vname,vmob,vemail,password,company,address) values(?,?,?,?,?,?,?)");

				ps1.setString(1, vendor.getVid());
				ps1.setString(2, vendor.getVname());
				ps1.setString(3, vendor.getVmob());
				ps1.setString(4, vendor.getVemail());
				ps1.setString(5, vendor.getPassword());
				ps1.setString(6, vendor.getCompany());
				ps1.setString(7, vendor.getAddress());

				int n = ps1.executeUpdate();

				if (n > 0)
					flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new VendorException(e.getMessage());
		}

		return flag;
	}

	@Override
	public boolean changePassword(String email, String oldPassword, String newPassword) throws VendorException {
		boolean flag = false;

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from vendor where vemail = ?");
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				PreparedStatement ps1 = conn.prepareStatement("update vendor set password = ? where vemial = ? and password = ?");
				ps1.setString(1, newPassword);
				ps1.setString(2, email);
				ps1.setString(3, oldPassword);
				
				int n = ps1.executeUpdate();
				
				if(n > 0) {
					flag = true;
				}else {
					throw new VendorException("Wrong Password!");
				}
				

			} else {
				throw new VendorException("Wrong Email!");
			}

		} catch (SQLException e) {
			throw new VendorException(e.getMessage());
		}

		return flag;
	}

	@Override
	public Vendor getVender(String vid) throws VendorException {
		Vendor vendor = null;

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from vendor where vid = ?");
			ps.setString(1, vid);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				String vname = rs.getString("vname");
				String mob = rs.getString("vmob");
				String email = rs.getString("vemail");
				String password = rs.getString("password");
				String company = rs.getString("company");
				String address = rs.getString("address");

				vendor = new Vendor(vid, vname, mob, email, password, company, address);

			} else {
				throw new VendorException("Invalid User Id!");
			}

		} catch (SQLException e) {
			throw new VendorException(e.getMessage());
		}

		return vendor;
	}

}
