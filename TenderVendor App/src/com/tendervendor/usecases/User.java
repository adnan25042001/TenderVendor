package com.tendervendor.usecases;

import java.util.Collections;
import java.util.List;

import com.tendervendor.exception.BidException;
import com.tendervendor.exception.TenderException;
import com.tendervendor.exception.VendorException;
import com.tendervendor.model.Bid;
import com.tendervendor.model.Tender;
import com.tendervendor.model.Vendor;
import com.tendervendor.utility.IDUtil;

public class User {

	public static final String blue = "\u001B[36m";

	public static final String reset = "\u001B[0m";

	public static void menu() {

		System.out.println();
		System.out.println(blue + "1 :" + reset + " User Profile");
		System.out.println(blue + "2 :" + reset + " Get all current Tenders");
		System.out.println(blue + "3 :" + reset + " Place Bid");
		System.out.println(blue + "4 :" + reset + " View Bid status");
		System.out.println(blue + "5 :" + reset + " View Bid History");
		System.out.println(blue + "6 :" + reset + " Change password");
		System.out.println(blue + "7 :" + reset + " Logout");
		System.out.println(blue + "8 :" + reset + " Exit");

		enterOption();

	}

	static void enterOption() {

		System.out.println();

		try {

			int opt = Integer.parseInt(Main.sc.nextLine().trim());

			if (opt == 1) {
				getUser();
			} else if (opt == 2) {
				getAllTender();
			} else if (opt == 3) {
				placeBid();
			} else if (opt == 4) {
				bidStatus();
			} else if (opt == 5) {
				bidHistory();
			} else if (opt == 6) {
				changePass();
			} else if (opt == 7) {
				System.out.println("Logout successfull...");
				LoginSignup.menu();
			} else if (opt == 8) {
				System.out.println("exit...");
				System.exit(0);
			} else {
				System.out.println("Enter correct option...");
				enterOption();
			}

		} catch (Exception e) {
			System.out.println("Enter correct option...");
			enterOption();
		}

	}

	static void getUser() {

		try {
			Vendor profile = Main.vd.getVender(LoginSignup.userId);
			profile.vendorHead();
			profile.vendorData();
		} catch (VendorException e) {
			System.out.println(e.getMessage());
		} finally {
			menu();
		}

	}

	static void getAllTender() {

		try {

			List<Tender> tenders = Main.td.getAllPendingTender();
			Tender.tenderHead();
			tenders.forEach(t -> {
				t.tenderData();
			});

		} catch (TenderException e) {
			System.out.println(e.getMessage());
		} finally {
			menu();
		}

	}

	static void placeBid() {

		System.out.println();
		String b = IDUtil.generateBidId();
		String vid = LoginSignup.userId;
		System.out.println("Enter Tender Id:");
		String tid = Main.sc.nextLine().trim();
		System.out.println("Enter Bid amount:");
		int amount = Integer.parseInt(Main.sc.nextLine().trim());
		System.out.println("Enter deadline:");
		String deadline = Main.sc.nextLine().trim();

		Bid bid = new Bid(b, tid, vid, amount, deadline, null);

		try {
			boolean flag = Main.bd.bidTender(bid);
			if (flag) {
				System.out.println("Bid placed successfully..");
			} else {
				System.out.println("Bid failed!");
			}

		} catch (BidException | TenderException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			menu();
		}

	}

	static void bidStatus() {

		String v = LoginSignup.userId;
		System.out.println("Enter Bid Id:");
		String b = Main.sc.nextLine().trim();

		try {
			Bid bid = Main.bd.getBidbyBidId(b);
			if (bid.getVid().equals(v)) {
				Bid.bidHead();
				bid.bidData();
			} else {
				System.out.println("Invalid Bid Id!");
			}
		} catch (BidException e) {
			System.out.println(e.getMessage());
		} finally {
			menu();
		}

	}

	static void bidHistory() {

		String vid = LoginSignup.userId;

		try {
			List<Bid> bids = Main.bd.getAllBidByVendorId(vid);
			Collections.sort(bids, (a, b) -> b.getBid().compareTo(a.getBid()));
			Bid.bidHead();
			bids.forEach(b -> {
				b.bidData();
			});
		} catch (BidException e) {
			System.out.println(e.getMessage());
		}finally {
			menu();
		}

	}
	
	static void changePass() {
		
		System.out.println("Enter email:");
		String mail = Main.sc.nextLine().trim();
		System.out.println("Enter old password:");
		String oldPass = Main.sc.nextLine().trim();
		System.out.println("Enter new password:");
		String newPass = Main.sc.nextLine().trim();
		
		try {
			boolean flag = Main.vd.changePassword(mail, oldPass, newPass);
			if(flag) {
				System.out.println("Password changed...");
			}else {
				System.out.println("Password not changed!");
			}
		} catch (VendorException e) {
			System.out.println(e.getMessage());
		}finally{
			menu();
		}
		
	}

}
