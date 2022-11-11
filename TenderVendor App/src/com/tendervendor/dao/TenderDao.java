package com.tendervendor.dao;

import java.util.List;

import com.tendervendor.exception.TenderException;
import com.tendervendor.model.Tender;
import com.tendervendor.model.TenderStatus;

public interface TenderDao {
		
	public boolean addTender(Tender tender) throws TenderException;

	public boolean addTender(String tname, String ttype, int tprice, String tlocation, String tdeadline, String tdesc) throws TenderException;

	public List<Tender> getAllTenders() throws TenderException;
	
	public Tender getTenderById(String tid) throws TenderException;
	
	public boolean removeTenderById(String tid) throws TenderException;
	
	public List<TenderStatus> getAllAssignedTender() throws TenderException; 
	
	public List<Tender> getAllPendingTender() throws TenderException; 
	
	public List<Tender> getAllRejectedTender() throws TenderException;

}
