package com.tendervendor.dao;

import java.util.List;

import com.tendervendor.exception.TenderException;
import com.tendervendor.model.Tender;
import com.tendervendor.model.TenderStatus;

public interface TenderDao {
		
	public String addTender(Tender tender) throws TenderException;

	public String addTender(String tname, String ttype, int tprice, String tlocation, String tdeadline, String tdesc) throws TenderException;

	public List<Tender> getAllTenders() throws TenderException;
	
	public List<TenderStatus> getAllAcceptedTenders() throws TenderException;
	
	public List<TenderStatus> getAllRejectedTenders() throws TenderException;
	
	public List<TenderStatus> getAllPendingTenders() throws TenderException;
	
	public Tender getTenderById(int tid) throws TenderException;
	
	public Tender getTenderByName(int tname) throws TenderException;
	
	public String removeTenderById(int tid) throws TenderException;
	
	public String removeTenderByName(int tname) throws TenderException;
	
}
