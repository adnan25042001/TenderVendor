package com.tendervendor.dao;

import java.util.List;

import com.tendervendor.exception.BidException;
import com.tendervendor.model.Bid;

public interface BidDao {
	
	public String acceptBid(String bid) throws BidException;
	
	public String rejectBid(String bid) throws BidException;
	
	public List<Bid> getAllBids() throws BidException;
	
	public List<Bid> getAllAcceptedBids() throws BidException;
	
	public List<Bid> getAllPendingBids() throws BidException;
	
	public List<Bid> getAllRejectedBids() throws BidException;

	public String bidTender(String tid, String vid, int bidamount, String deadline);
	
	public List<Bid> getAllBidByTendorId(String tid) throws BidException;
	
	public List<Bid> getAllBidByVendorId(String vid) throws BidException;
	
}
