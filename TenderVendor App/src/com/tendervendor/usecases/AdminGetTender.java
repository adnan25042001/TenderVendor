package com.tendervendor.usecases;

import java.util.List;
import com.tendervendor.exception.TenderException;
import com.tendervendor.model.Tender;
import com.tendervendor.model.TenderStatus;

public class AdminGetTender {

	public static final String blue = "\u001B[36m";

	public static final String reset = "\u001B[0m";

	public static void menu() {

		System.out.println();
		System.out.println(blue + "1 :" + reset + " Get all Tender");
		System.out.println(blue + "2 :" + reset + " Get Tender by Id");
		System.out.println(blue + "3 :" + reset + " Get assigned Tender");
		System.out.println(blue + "4 :" + reset + " Back");
		System.out.println(blue + "5 :" + reset + " exit");

		enterOption();

	}

	static void enterOption() {

		System.out.println();

		try {

			int opt = Integer.parseInt(Main.sc.nextLine().trim());

			if (opt == 1) {

				getTender();

			} else if (opt == 2) {

				getTenderById();

			} else if (opt == 3) {

				getAssignedTender();

			} else if (opt == 4) {

				System.out.println();
				Admin.menu();

			} else if (opt == 5) {

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

	static void getTender() {

		try {

			List<Tender> tenders = Main.td.getAllTenders();
			Tender.tenderHead();
			tenders.forEach(t -> {
				t.tenderData();
			});

		} catch (TenderException e) {
			System.out.println(e.getMessage());
		}finally {
			menu();
		}

	}

	static void getTenderById() {

		System.out.println("Enter Tender Id :");
		String id = Main.sc.nextLine().trim();

		try {

			Tender tender = Main.td.getTenderById(id);

			if (tender == null) {
				System.out.println("Invalid Tender Id!");
			} else {
				Tender.tenderHead();
				tender.tenderData();
			}

			menu();

		} catch (TenderException e) {
			System.out.println(e.getMessage());
			menu();
		}

	}

	static void getAssignedTender() {

		try {

			List<TenderStatus> tenders = Main.td.getAllAssignedTender();
			TenderStatus.tenderStatusHead();
			tenders.forEach(t -> {
				t.tenderStatusData();
			});

			menu();

		} catch (TenderException e) {
			System.out.println(e.getMessage());
			menu();
		}

	}

}
