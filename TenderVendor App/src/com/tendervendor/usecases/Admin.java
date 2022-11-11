package com.tendervendor.usecases;

import com.tendervendor.dao.LoginAndSignupDao;
import com.tendervendor.dao.LoginAndSignupDaoImpl;
import com.tendervendor.dao.TenderDao;
import com.tendervendor.dao.TenderDaoImpl;
import com.tendervendor.exception.TenderException;

public class Admin {
	
	public static TenderDao td = new TenderDaoImpl();
	
	public static void menu(){
		
		System.out.println();
		System.out.println("1 : Add Tender");
		System.out.println("2 : Get Tender");
		System.out.println("3 : Remove Tender");
		System.out.println("4 : Logout");
		System.out.println("5 : Exit");
		
		enterOption();
		
	}
	
	static void enterOption() {
		
		
		int opt = Main.sc.nextInt();
		
		if(opt == 1) {
			
			addTender();
			
		}else if(opt == 2){
			
			System.out.println();
			AdminGetTender.menu();
		
		}else if(opt == 3) {
			
			removeTender();
			
		}else if(opt == 4) {
			
			LoginAndSignupDao ls = new LoginAndSignupDaoImpl();
			ls.logout();
			
		}else if(opt == 5) {
			
			System.out.println("exit...");
			System.exit(0);
			
		}else {
			System.out.println("Enter correct option...");
			enterOption();
		}
		
	}
	
	static void addTender() {
				
		try {
			boolean flag = td.addTender(null);
			
			if(flag) {
				System.out.println("Tender added successfully...");
			}else {
				System.out.println("Tender not added...");
				menu();
			}
			
		} catch (TenderException e) {
			e.printStackTrace();
		}
		
	}
	
	static void removeTender() {
		
		System.out.println("Enter Tender Id :");
		String id = Main.sc.next();
		
		try {
			boolean flag = td.removeTenderById(id);
			
			if(flag) {
				System.out.println("Tender deleted successfully...");
			}else {
				System.out.println("Invalid tender Id!");
			}
			
			menu();
			
		} catch (TenderException e) {
			System.out.println(e.getMessage());
			menu();
		}
		
	}

}
