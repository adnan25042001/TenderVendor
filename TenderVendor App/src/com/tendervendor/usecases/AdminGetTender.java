package com.tendervendor.usecases;

import java.util.List;

import com.tendervendor.dao.TenderDao;
import com.tendervendor.dao.TenderDaoImpl;
import com.tendervendor.exception.TenderException;
import com.tendervendor.model.Tender;
import com.tendervendor.model.TenderStatus;

public class AdminGetTender {

	public static TenderDao td = new TenderDaoImpl();

	public static void menu() {

		System.out.println();
		System.out.println("1 : Get all Tender");
		System.out.println("2 : Get Tender by Id");
		System.out.println("3 : Get assigned Tender");
		System.out.println("4 : Get pending Tender");
		System.out.println("4 : Get rejected Tender");
		System.out.println("5 : Back");
		System.out.println("6 : Home");
		System.out.println("7 : exit");

		enterOption();

	}

	static void enterOption() {

		int opt = Main.sc.nextInt();

		if (opt == 1) {

			getTender();

		} else if (opt == 2) {

			getTenderById();

		} else if (opt == 3) {

			getAssignedTender();

		} else if (opt == 4) {

			getPendingTender();

		} else if (opt == 5) {
			
			getRejectedTender();

		} else if (opt == 6) {

			System.out.println();
			Admin.menu();

		} else if (opt == 7) {

			System.out.println();
			Admin.menu();

		} else if (opt == 8) {

			System.out.println("exit...");
			System.exit(0);

		} else {
			System.out.println("Enter correct option...");
		}

	}

	static void getTender() {

		try {

			List<Tender> tenders = td.getAllTenders();

			tenders.forEach(t -> System.out.println(t));

			menu();

		} catch (TenderException e) {
			System.out.println(e.getMessage());
			menu();
		}

	}

	static void getTenderById() {
		
		System.out.println("Enter Tender Id :");
		String id = Main.sc.next();

		try {

			Tender tender = td.getTenderById(id);

			if(tender == null) {
				System.out.println("Invalid Tender Id!");
			}else {
				System.out.println(tender);
			}

			menu();

		} catch (TenderException e) {
			System.out.println(e.getMessage());
			menu();
		}

	}

	static void getAssignedTender() {

		try {

			List<TenderStatus> tenders = td.getAllAssignedTender();

			tenders.forEach(t -> System.out.println(t));

			menu();

		} catch (TenderException e) {
			System.out.println(e.getMessage());
			menu();
		}

	}

	static void getPendingTender() {

		try {

			List<Tender> tenders = td.getAllPendingTender();

			tenders.forEach(t -> System.out.println(t));

			menu();

		} catch (TenderException e) {
			System.out.println(e.getMessage());
			menu();
		}

	}

	static void getRejectedTender() {

		try {

			List<Tender> tenders = td.getAllRejectedTender();

			tenders.forEach(t -> System.out.println(t));

			menu();

		} catch (TenderException e) {
			System.out.println(e.getMessage());
			menu();
		}

	}

}
