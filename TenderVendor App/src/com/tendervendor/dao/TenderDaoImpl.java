package com.tendervendor.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.tendervendor.exception.TenderException;
import com.tendervendor.model.Tender;
import com.tendervendor.model.TenderStatus;
import com.tendervendor.utility.DBUtil;

public class TenderDaoImpl implements TenderDao {

	@Override
	public boolean addTender(Tender tender) throws TenderException {
		boolean flag = false;

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement(
					"insert into tender(tid,tname,ttype,tprice,tlocation,tdeadline,tdesc) values(?,?,?,?,?,?,?)");

			ps.setString(1, tender.getTid());
			ps.setString(2, tender.getTname());
			ps.setString(3, tender.getTtype());
			ps.setInt(4, tender.getTprice());
			ps.setString(5, tender.getTlocation());

			Date d = tender.getTdeadline();
			java.sql.Date dl = new java.sql.Date(d.getTime());
			ps.setDate(6, dl);

			ps.setString(7, tender.getTdesc());

			int n = ps.executeUpdate();

			if (n > 0)
				flag = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public List<Tender> getAllTenders() throws TenderException {
		List<Tender> tenders = new ArrayList<>();

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from tender");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				String tid = rs.getString("tid");
				String tname = rs.getString("tname");
				String ttype = rs.getString("ttype");
				int tprice = rs.getInt("tprice");
				String tlocation = rs.getString("tlocation");

				java.sql.Date d = rs.getDate("tdeadline");
				String tdeadline = d.toString();

				String tdesc = rs.getString("tdesc");

				Tender tender = new Tender(tid, tname, ttype, tprice, tlocation, tdeadline, tdesc);

				tenders.add(tender);
			}

		} catch (SQLException e) {
			throw new TenderException(e.getMessage());
		}

		if (tenders.size() == 0) {
			throw new TenderException("Tendor not found...");
		}

		return tenders;
	}

	@Override
	public Tender getTenderById(String tid) throws TenderException {
		Tender tender = null;

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from tender where tid = ?");

			ps.setString(1, tid);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				String id = rs.getString("tid");
				String tname = rs.getString("tname");
				String ttype = rs.getString("ttype");
				int tprice = rs.getInt("tprice");
				String tlocation = rs.getString("tlocation");

				java.sql.Date d = rs.getDate("tdeadline");
				String tdeadline = d.toString();

				String tdesc = rs.getString("tdesc");

				tender = new Tender(id, tname, ttype, tprice, tlocation, tdeadline, tdesc);

			}

		} catch (SQLException e) {
			throw new TenderException(e.getMessage());
		}

		return tender;
	}

	@Override
	public boolean removeTenderById(String tid) throws TenderException {
		boolean flag = false;

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("delete from tender where tid = ?");
			ps.setString(1, tid);

			int n = ps.executeUpdate();

			if (n > 0) {

				flag = true;

			}

		} catch (SQLException e) {
			throw new TenderException(e.getMessage());
		}

		return flag;
	}

	@Override
	public List<TenderStatus> getAllAssignedTender() throws TenderException {
		List<TenderStatus> tenders = new ArrayList<>();

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from tender_status");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				String tid = rs.getString("tid");
				String bid = rs.getString("bid");
				String vid = rs.getString("vid");
				String status = rs.getString("status");

				TenderStatus tender = new TenderStatus(tid, bid, vid, status);

				tenders.add(tender);
			}

		} catch (SQLException e) {
			throw new TenderException(e.getMessage());
		}

		if (tenders.size() == 0) {
			throw new TenderException("Tendor not found...");
		}

		return tenders;
	}

	@Override
	public List<Tender> getAllPendingTender() throws TenderException {
		List<Tender> tenders = new ArrayList<>();

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn
					.prepareStatement("select * from tender where tid not in(select tid from tender_status)");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				String id = rs.getString("tid");
				String tname = rs.getString("tname");
				String ttype = rs.getString("ttype");
				int tprice = rs.getInt("tprice");
				String tlocation = rs.getString("tlocation");

				java.sql.Date d = rs.getDate("tdeadline");
				String tdeadline = d.toString();

				String tdesc = rs.getString("tdesc");

				Tender tender = new Tender(id, tname, ttype, tprice, tlocation, tdeadline, tdesc);

				tenders.add(tender);

			}

		} catch (SQLException e) {
			throw new TenderException(e.getMessage());
		}

		if (tenders.size() == 0) {
			throw new TenderException("Tendor not found...");
		}

		return tenders;
	}

}
