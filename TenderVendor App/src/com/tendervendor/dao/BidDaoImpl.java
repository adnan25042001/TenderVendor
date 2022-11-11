package com.tendervendor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tendervendor.exception.BidException;
import com.tendervendor.model.Bid;
import com.tendervendor.utility.DBUtil;
import com.tendervendor.utility.IDUtil;

public class BidDaoImpl implements BidDao {

	@Override
	public String acceptBid(String bid) throws BidException {
		String msg = "Invalid bid Id...";

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from bids where bid = ?");
			ps.setString(1, bid);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				String tid = rs.getString("tid");
				String vid = rs.getString("vid");
				String status = rs.getString("status");

				if (status.equals("Pending")) {

					PreparedStatement ps1 = conn.prepareStatement("update bids set status = 'Accepted' where bid = ?");
					ps1.setString(1, bid);

					PreparedStatement ps2 = conn
							.prepareStatement("update bids set status = 'Rejected' where tid = ? AND vid != ?");
					ps2.setString(1, tid);
					ps2.setString(2, vid);

					ps1.executeUpdate();
					ps2.executeUpdate();

					PreparedStatement ps3 = conn
							.prepareStatement("insert into tender_status(tid,bid,vid,status) values(?,?,?,?)");
					ps3.setString(1, tid);
					ps3.setString(2, bid);
					ps3.setString(3, vid);
					ps3.setString(4, "Assigned");

					ps3.executeUpdate();

				} else if (status.equals("Rejected")) {

					PreparedStatement ps1 = conn.prepareStatement("select * from tender_status where tid = ?");
					ps1.setString(1, tid);

					ResultSet rs1 = ps1.executeQuery();

					if (rs1.next()) {

						msg = "Bid: " + bid + " has been already assigned...";

					} else {

						PreparedStatement ps2 = conn
								.prepareStatement("update bids set status = 'Accepted' where bid = ?");
						ps2.setString(1, bid);

						PreparedStatement ps3 = conn
								.prepareStatement("update bids set status = 'Rejected' where tid = ? AND vid != ?");
						ps3.setString(1, tid);
						ps3.setString(2, vid);

						ps2.executeUpdate();
						ps3.executeUpdate();

						PreparedStatement ps4 = conn
								.prepareStatement("insert into tender_status(tid,bid,vid,status) values(?,?,?,?)");
						ps4.setString(1, tid);
						ps4.setString(2, bid);
						ps4.setString(3, vid);
						ps4.setString(4, "Assigned");

						ps4.executeUpdate();

					}

				} else {

					msg = "Bid: " + bid + " with Tender: " + tid + " is already assigned to Vendor: " + vid;

				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
			msg = e.getMessage();
		}

		return msg;
	}

	@Override
	public String rejectBid(String bid) throws BidException {
		String msg = "Invalid bid Id...";

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from bids where bid = ?");
			ps.setString(1, bid);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				String tid = rs.getString("tid");
				String vid = rs.getString("vid");
				String status = rs.getString("status");

				if (status.equals("Accepted")) {
					msg = "Bid: " + bid + " with Tender: " + tid + " has been assigned to Vendor: " + vid;
				} else if (status.equals("Rejected")) {
					msg = "Bid: " + bid + " is already rejected...";
				} else {
					PreparedStatement ps1 = conn.prepareStatement("update bids set status = 'Rejected' where bid = ?");
					ps1.setString(1, bid);
					ps1.executeUpdate();

					msg = "Bid : " + bid + " rejected..";
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
			msg = e.getMessage();
		}

		return msg;
	}

	@Override
	public List<Bid> getAllBids() throws BidException {
		List<Bid> bids = new ArrayList<>();

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from bids");

			ResultSet rs = ps.executeQuery();

			getBid(bids, rs);

		} catch (SQLException e) {
			throw new BidException(e.getMessage());
		}

		if (bids.size() == 0) {
			throw new BidException("Bid not found...");
		}

		return bids;
	}

	@Override
	public List<Bid> getAllAcceptedBids() throws BidException {
		List<Bid> bids = new ArrayList<>();

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from bids where status = 'Accepted'");

			ResultSet rs = ps.executeQuery();

			getBid(bids, rs);

		} catch (SQLException e) {
			throw new BidException(e.getMessage());
		}

		if (bids.size() == 0) {
			throw new BidException("Bid not found...");
		}

		return bids;
	}

	@Override
	public List<Bid> getAllPendingBids() throws BidException {
		List<Bid> bids = new ArrayList<>();

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from bids where status = 'Pending'");

			ResultSet rs = ps.executeQuery();

			getBid(bids, rs);

		} catch (SQLException e) {
			throw new BidException(e.getMessage());
		}

		if (bids.size() == 0) {
			throw new BidException("Bid not found...");
		}

		return bids;
	}

	@Override
	public List<Bid> getAllRejectedBids() throws BidException {
		List<Bid> bids = new ArrayList<>();

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from bids where status = 'Rejected'");

			ResultSet rs = ps.executeQuery();

			getBid(bids, rs);

		} catch (SQLException e) {
			throw new BidException(e.getMessage());
		}

		if (bids.size() == 0) {
			throw new BidException("Bid not found...");
		}

		return bids;
	}

	@Override
	public String bidTender(String tid, String vid, int bidamount, String deadline) {
		String msg = "Tender Bidding Failed!";
		
		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("insert into tender_status(bid , tid, vid, bidamount, deadline, status) values(?,?,?,?,?,?)");
			ps.setString(1, IDUtil.generateId());
			ps.setString(2, tid);
			ps.setString(3, vid);
			ps.setInt(4, bidamount);
			ps.setString(5, deadline);
			ps.setString(2, "Pending");
			
			int n = ps.executeUpdate();
			
			if(n > 0)
				msg = "You have successfully Bid for the tender";

		} catch (SQLException e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		
		return msg;
	}

	@Override
	public List<Bid> getAllBidByTendorId(String tid) throws BidException {
		List<Bid> bids = new ArrayList<>();

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from bids where status = 'Rejected'");

			ResultSet rs = ps.executeQuery();

			getBid(bids, rs);

		} catch (SQLException e) {
			throw new BidException(e.getMessage());
		}

		if (bids.size() == 0) {
			throw new BidException("Bid not found...");
		}

		return bids;
	}

	@Override
	public List<Bid> getAllBidByVendorId(String vid) throws BidException {
		List<Bid> bids = new ArrayList<>();

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from bids where vid = ?");
			ps.setString(1, vid);

			ResultSet rs = ps.executeQuery();

			getBid(bids, rs);

		} catch (SQLException e) {
			throw new BidException(e.getMessage());
		}

		if (bids.size() == 0) {
			throw new BidException("Bid not found...");
		}

		return bids;
	}

	public static void getBid(List<Bid> bids, ResultSet rs) throws SQLException {

		while (rs.next()) {

			String bid = rs.getString("bid");
			String tid = rs.getString("tid");
			String vid = rs.getString("vid");
			int bidAmount = rs.getInt("bidamount");
			String deadline = rs.getString("deadline");
			String status = rs.getString("status");

			Bid b = new Bid(bid, tid, vid, bidAmount, deadline, status);

			bids.add(b);

		}

	}

}
