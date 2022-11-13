package com.tendervendor.usecases;

import com.tendervendor.exception.BidException;
import com.tendervendor.exception.TenderException;
import com.tendervendor.model.Tender;
import com.tendervendor.utility.IDUtil;

public class Admin {

	public static final String blue = "\u001B[36m";

	public static final String reset = "\u001B[0m";

	public static void menu() {

		System.out.println();
		System.out.println(blue + "1 :" + reset + " Add Tender");
		System.out.println(blue + "2 :" + reset + " Get Tender");
		System.out.println(blue + "3 :" + reset + " Get Bids");
		System.out.println(blue + "4 :" + reset + " Accept Bid");
		System.out.println(blue + "5 :" + reset + " Rejectect Bid");
		System.out.println(blue + "6 :" + reset + " Remove Tender");
		System.out.println(blue + "7 :" + reset + " Logout");
		System.out.println(blue + "8 :" + reset + " Exit");

		enterOption();

	}

	static void enterOption() {

		System.out.println();

		try {

			int opt = Integer.parseInt(Main.sc.nextLine().trim());

			if (opt == 1) {

				addTender();

			} else if (opt == 2) {

				System.out.println();
				AdminGetTender.menu();

			} else if (opt == 3) {

				AdminGetBid.menu();

			} else if (opt == 4) {

				acceptBid();

			} else if (opt == 5) {

				rejectBid();

			} else if (opt == 6) {

				removeTender();

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

	static void addTender() {

		String tid = IDUtil.generateTenderId();
		System.out.println("Enter Tender name:");
		String tname = Main.sc.nextLine().trim();
		System.out.println("Enter Tender type:");
		String ttype = Main.sc.nextLine().trim();
		System.out.println("Enter Tender price:");
		int tprice = Integer.parseInt(Main.sc.nextLine().trim());
		System.out.println("Enter location:");
		String tlocation = Main.sc.nextLine().trim();
		System.out.println("Enter deadline in (yyyy-mm-dd) format:");
		String tdeadline = Main.sc.nextLine().trim();
		System.out.println("Enter Tender description:");
		String tdesc = Main.sc.nextLine().trim();

		Tender tender = new Tender(tid, tname, ttype, tprice, tlocation, tdeadline, tdesc);

		try {
			boolean flag = Main.td.addTender(tender);

			if (flag) {
				System.out.println("Tender added successfully...");
			} else {
				System.out.println("Tender not added...");
			}

		} catch (TenderException e) {
			System.out.println(e.getMessage());
		}finally {
			menu();
		}

	}

	static void removeTender() {

		System.out.println("Enter Tender Id :");
		String id = Main.sc.nextLine().trim();

		try {
			boolean flag = Main.td.removeTenderById(id);

			if (flag) {
				System.out.println("Tender deleted successfully...");
			} else {
				System.out.println("Invalid tender Id!");
			}

			menu();

		} catch (TenderException e) {
			System.out.println(e.getMessage());
			menu();
		}

	}

	static void acceptBid() {
		
		System.out.println("Enter Bid Id:");
		String bid = Main.sc.nextLine().trim();

		try {
			boolean flag = Main.bd.acceptBid(bid);
			if (flag) {
				System.out.println("Bid accepted successfully...");
			}else {
				System.out.println("Bid failed!");
			}
		} catch (BidException e) {
			System.out.println(e.getMessage());
		}finally {
			menu();
		}

	}

	static void rejectBid() {
		
		System.out.println("Enter Bid Id:");
		String bid = Main.sc.nextLine().trim();

		try {
			boolean flag = Main.bd.rejectBid(bid);
			if (flag) {
				System.out.println("Bid rejected successfully...");
			}
		} catch (BidException e) {
			System.out.println(e.getMessage());
		}finally {
			menu();
		}

	}

}
