package com.tendervendor.usecases;

import java.util.List;

import com.tendervendor.exception.BidException;
import com.tendervendor.model.Bid;

public class AdminGetBid {

	public static final String blue = "\u001B[36m";

	public static final String reset = "\u001B[0m";

	public static void menu() {

		System.out.println();
		System.out.println(blue + "1 :" + reset + " Get all Bids");
		System.out.println(blue + "2 :" + reset + " Get all accepted bids");
		System.out.println(blue + "3 :" + reset + " Get all pending bids");
		System.out.println(blue + "4 :" + reset + " get all rejected bids");
		System.out.println(blue + "5 :" + reset + " Get Bid By Tender Id");
		System.out.println(blue + "6 :" + reset + " Get Bid By Bid Id");
		System.out.println(blue + "7 :" + reset + " back");
		System.out.println(blue + "8 :" + reset + " Exit");

		enterOption();

	}

	static void enterOption() {

		System.out.println();

		try {

			int opt = Integer.parseInt(Main.sc.nextLine().trim());

			if (opt == 1) {

				getAllBid();

			} else if (opt == 2) {

				getAcceptedBid();

			} else if (opt == 3) {

				getPendingBid();

			} else if (opt == 4) {

				getRejectedBid();

			} else if (opt == 5) {

				getBidByTenderId();

			} else if (opt == 6) {

				getBidById();

			} else if (opt == 7) {

				Admin.menu();

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

	static void getAllBid() {

		try {
			List<Bid> bids = Main.bd.getAllBids();
			bids.forEach(b -> System.out.println(b));
		} catch (BidException e) {
			System.out.println(e.getMessage());
		} finally {
			menu();
		}

	}

	static void getAcceptedBid() {

		try {
			List<Bid> bids = Main.bd.getAllAcceptedBids();
			bids.forEach(b -> System.out.println(b));
		} catch (BidException e) {
			System.out.println(e.getMessage());
		} finally {
			menu();
		}

	}

	static void getPendingBid() {

		try {
			List<Bid> bids = Main.bd.getAllPendingBids();
			bids.forEach(b -> System.out.println(b));
		} catch (BidException e) {
			System.out.println(e.getMessage());
		} finally {
			menu();
		}

	}

	static void getRejectedBid() {

		try {
			List<Bid> bids = Main.bd.getAllRejectedBids();
			bids.forEach(b -> System.out.println(b));
		} catch (BidException e) {
			System.out.println(e.getMessage());
		} finally {
			menu();
		}

	}

	static void getBidByTenderId() {

		System.out.println("Enter Tender Id:");
		String tid = Main.sc.nextLine().trim();

		try {
			List<Bid> bids = Main.bd.getAllBidByTenderId(tid);
			bids.forEach(b -> System.out.println(b));
		} catch (BidException e) {
			System.out.println(e.getMessage());
		} finally {
			menu();
		}

	}

	static void getBidById() {
		System.out.println("Enter Bid Id:");
		String id = Main.sc.nextLine().trim();

		try {
			Bid bid = Main.bd.getBidbyBidId(id);
			System.out.println(bid);
		} catch (BidException e) {
			System.out.println(e.getMessage());
		} finally {
			menu();
		}

	}

}
