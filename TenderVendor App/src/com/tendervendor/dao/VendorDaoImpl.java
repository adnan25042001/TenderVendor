package com.tendervendor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.tendervendor.exception.VendorException;
import com.tendervendor.model.Bid;
import com.tendervendor.utility.DBUtil;
import com.tendervendor.utility.IDUtil;

public class VendorDaoImpl implements VendorDao{

	@Override
	public boolean addVendor(String name, String mobile, String email, String password, String company, String address)
			throws VendorException {
		boolean flag = false;

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select vemail from vendor where vemail = ?");
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("Email already exists...");
			}else {
				
				PreparedStatement ps1 = conn.prepareStatement("insert into vendor(vid,vname,vmob,vemail,password,company,address) values(?,?,?,?,?,?,?)");
				
				ps1.setString(1, IDUtil.generateId());
				ps1.setString(2, name);
				ps1.setString(3, mobile);
				ps1.setString(5, email);
				ps1.setString(6, password);
				ps1.setString(7, company);
				ps1.setString(8, address);
				
				int n = ps1.executeUpdate();
				
				if(n > 0)
//					System.out.println("Signup Successfull...");
					flag = true;
			
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public String changePassword(String email, String oldPassword, String newPassword) throws VendorException {
		return null;
	}

	@Override
	public List<Bid> allBidsOfUser(int vid) throws VendorException {
		// TODO Auto-generated method stub
		return null;
	}

}
