package com.tendervendor.usecases;

import java.util.List;

import com.tendervendor.dao.TenderDao;
import com.tendervendor.dao.TenderDaoImpl;
import com.tendervendor.exception.TenderException;
import com.tendervendor.model.Tender;

public class Demo {
	public static void main(String[] args) {
		
		TenderDao tdao = new TenderDaoImpl();
		
		try {
			List<Tender> tenders =  tdao.getAllTenders();
			tenders.forEach(t -> System.out.println(t));
		} catch (TenderException e) {
			e.printStackTrace();
		}
		
	}
}
