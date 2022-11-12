package com.tendervendor.dao;

import java.util.List;

import com.tendervendor.exception.BidException;
import com.tendervendor.exception.TenderException;
import com.tendervendor.model.Bid;

public interface BidDao {

	public boolean acceptBid(String bid) throws BidException;

	public boolean rejectBid(String bid) throws BidException;

	public List<Bid> getAllBids() throws BidException;

	public List<Bid> getAllAcceptedBids() throws BidException;

	public List<Bid> getAllPendingBids() throws BidException;

	public List<Bid> getAllRejectedBids() throws BidException;

	public boolean bidTender(Bid bid) throws BidException, TenderException;

	public List<Bid> getAllBidByTenderId(String tid) throws BidException;

	public List<Bid> getAllBidByVendorId(String vid) throws BidException;

	public Bid getBidbyBidId(String bid) throws BidException;

}
