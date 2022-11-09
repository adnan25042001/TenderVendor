package com.tendervendor.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.tendervendor.exception.TenderException;
import com.tendervendor.model.Tender;
import com.tendervendor.model.TenderStatus;
import com.tendervendor.utility.DBUtil;

public class TenderDaoImpl implements TenderDao {

	@Override
	public String addTender(Tender tender) throws TenderException {
		String msg = "Tender not added...";

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement(
					"insert into tender(tname,ttype,tprice,tlocation,tdeadline,tdesc) values(?,?,?,?,?,?)");

			ps.setString(1, tender.getTname());
			ps.setString(2, tender.getTtype());
			ps.setInt(3, tender.getTprice());
			ps.setString(4, tender.getTlocation());

			Date d = tender.getTdeadline();
			java.sql.Date dl = new java.sql.Date(d.getTime());
			ps.setDate(5, dl);

			ps.setString(6, tender.getTdesc());

			int n = ps.executeUpdate();

			if (n > 0)
				msg = "Tendert submitted successfully...";

		} catch (SQLException e) {
			e.printStackTrace();
			msg = e.getMessage();
		}

		return msg;
	}

	@Override
	public String addTender(String tname, String ttype, int tprice, String tlocation, String tdeadline, String tdesc)
			throws TenderException {
		String msg = "Tender not added...";

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement(
					"insert into tender(tname,ttype,tprice,tlocation,tdeadline,tdesc) values(?,?,?,?,?,?)");

			ps.setString(1, tname);
			ps.setString(2, ttype);
			ps.setInt(3, tprice);
			ps.setString(4, tlocation);

			Date d = null;
			try {
				d = new SimpleDateFormat("yyyy-mm-dd").parse(tdeadline);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			java.sql.Date dl = new java.sql.Date(d.getTime());

			ps.setDate(5, dl);

			ps.setString(6, tdesc);

			int n = ps.executeUpdate();

			if (n > 0)
				msg = "Tendert submitted successfully...";

		} catch (SQLException e) {
			e.printStackTrace();
			msg = e.getMessage();
		}

		return msg;
	}

	@Override
	public List<Tender> getAllTenders() throws TenderException {
		List<Tender> tenders = new ArrayList<>();

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from tender");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int tid = rs.getInt("tid");
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
	public List<TenderStatus> getAllAcceptedTenders() throws TenderException {
		List<TenderStatus> tenders = new ArrayList<>();

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement(
					"select t.tid, t.tname, t.ttype, t.tprice, t.tlocation, t.tdeadline, t.tdesc, ts.status from tender t inner join tender_statusc ts ON t.tid = ts.tid");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int tid = rs.getInt("tid");
				String tname = rs.getString("tname");
				String ttype = rs.getString("ttype");
				int tprice = rs.getInt("tprice");
				String tlocation = rs.getString("tlocation");
				java.sql.Date d = rs.getDate("tdeadline");
				String tdeadline = d.toString();
				String tdesc = rs.getString("tdesc");
				String status = rs.getString("status");

				TenderStatus tender = new TenderStatus(tid, tname, ttype, tprice, tlocation, tdeadline, tdesc, status);

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
	public List<TenderStatus> getAllRejectedTenders() throws TenderException {
		List<TenderStatus> tenders = new ArrayList<>();

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement(
					"select t.tid, t.tname, t.ttype, t.tprice, t.tlocation, t.tdeadline, t.tdesc, ts.status from tender t inner join tender_statusc ts ON t.tid = ts.tid");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int tid = rs.getInt("tid");
				String tname = rs.getString("tname");
				String ttype = rs.getString("ttype");
				int tprice = rs.getInt("tprice");
				String tlocation = rs.getString("tlocation");
				java.sql.Date d = rs.getDate("tdeadline");
				String tdeadline = d.toString();
				String tdesc = rs.getString("tdesc");
				String status = rs.getString("status");

				TenderStatus tender = new TenderStatus(tid, tname, ttype, tprice, tlocation, tdeadline, tdesc, status);

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
	public List<TenderStatus> getAllPendingTenders() throws TenderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tender getTenderById(int tid) throws TenderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tender getTenderByName(int tname) throws TenderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String removeTenderById(int tid) throws TenderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String removeTenderByName(int tname) throws TenderException {
		// TODO Auto-generated method stub
		return null;
	}

}
