package com.tendervendor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tendervendor.exception.BidException;
import com.tendervendor.exception.TenderException;
import com.tendervendor.model.Bid;
import com.tendervendor.utility.DBUtil;

public class BidDaoImpl implements BidDao {

	@Override
	public boolean acceptBid(String bid) throws BidException {

		boolean flag = false;

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

						throw new BidException("Bid: " + bid + " has been already assigned...");

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

					throw new BidException(
							"Bid: " + bid + " with Tender: " + tid + " is already assigned to Vendor: " + vid);

				}

			} else {
				throw new BidException("Invalid bid Id...");
			}

		} catch (SQLException e) {
			throw new BidException(e.getMessage());
		}

		return flag;
	}

	@Override
	public boolean rejectBid(String bid) throws BidException {
		boolean flag = false;

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from bids where bid = ?");
			ps.setString(1, bid);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				String tid = rs.getString("tid");
				String vid = rs.getString("vid");
				String status = rs.getString("status");

				if (status.equals("Accepted")) {
					throw new BidException(
							"Bid: " + bid + " with Tender: " + tid + " has been assigned to Vendor: " + vid);
				} else if (status.equals("Rejected")) {
					throw new BidException("Bid: " + bid + " is already rejected...");
				} else {
					PreparedStatement ps1 = conn.prepareStatement("update bids set status = 'Rejected' where bid = ?");
					ps1.setString(1, bid);
					ps1.executeUpdate();

					flag = true;
				}

			} else {
				throw new BidException("Invalid bid Id...");
			}

		} catch (SQLException e) {
			throw new BidException(e.getMessage());
		}

		return flag;
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
	public boolean bidTender(Bid bid) throws BidException, TenderException {

		boolean flag = false;

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps1 = conn.prepareStatement("select tid from tender where tid = ?");
			ps1.setString(1, bid.getTid());

			ResultSet rs = ps1.executeQuery();

			if (rs.next()) {

				PreparedStatement ps = conn.prepareStatement(
						"insert into tender_status(bid , tid, vid, bidamount, deadline, status) values(?,?,?,?,?,?)");
				ps.setString(1, bid.getBid());
				ps.setString(2, bid.getTid());
				ps.setString(3, bid.getVid());
				ps.setInt(4, bid.getBidAmount());
				ps.setString(5, bid.getDeadline());
				ps.setString(2, "Pending");

				int n = ps.executeUpdate();

				if (n > 0) {
					flag = true;
				} else {
					throw new BidException("Tender Bidding Failed!");
				}

			} else {

				throw new TenderException("Invalid Tender Id!");

			}

		} catch (SQLException e) {
			throw new BidException(e.getMessage());
		}

		return flag;
	}

	@Override
	public List<Bid> getAllBidByTenderId(String tid) throws BidException {
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

	@Override
	public Bid getBidbyBidId(String b) throws BidException {
		Bid bid = null;

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from bids where bid = ?");
			ps.setString(1, b);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				String tid = rs.getString("tid");
				String vid = rs.getString("vid");
				int bidAmount = rs.getInt("bidamount");
				String deadline = rs.getString("deadline");
				String status = rs.getString("status");

				bid = new Bid(b, tid, vid, bidAmount, deadline, status);

			} else {
				throw new BidException("Invalid Bid Id!");
			}

		} catch (SQLException e) {
			throw new BidException(e.getMessage());
		}

		return bid;
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
