package com.tendervendor.usecases;

import java.sql.SQLException;

import com.tendervendor.dao.LoginAndSignupDao;
import com.tendervendor.dao.LoginAndSignupDaoImpl;
import com.tendervendor.model.Vendor;
import com.tendervendor.utility.IDUtil;

public class LoginSignup {

	public static LoginAndSignupDao ls = new LoginAndSignupDaoImpl();

	public static final String blue = "\u001B[36m";

	public static final String reset = "\u001B[0m";

	public static String userId = null;

	public static void menu() {

		System.out.println();
		System.out.println(blue + "1 :" + reset + " Login as Admin");
		System.out.println(blue + "2 :" + reset + " Login as Vendor");
		System.out.println(blue + "3 :" + reset + " Don't have account signup");
		System.out.println(blue + "4 :" + reset + " Exit");

		enterOption();

	}

	static void enterOption() {

		System.out.println();

		try {

			int opt = Integer.parseInt(Main.sc.nextLine().trim());

			if (opt == 1) {
				adminLog();
			} else if (opt == 2) {
				userLog();
			} else if (opt == 3) {
				userSign();
			} else if (opt == 4) {
				System.out.println("exit...");
				System.exit(0);
			} else {
				System.out.println("Enter correct option..");
				enterOption();
			}

		} catch (Exception e) {
			System.out.println("Enter correct option..");
			enterOption();
		}

	}

	static void adminLog() {

		System.out.println("Enter username:");
		String name = Main.sc.nextLine().trim();
		System.out.println("Enter password:");
		String pass = Main.sc.nextLine().trim();
		try {
			boolean flag = ls.loginAdmin(name, pass);
			if (flag) {
				System.out.println();
				System.out.println("Login Successfull..." + "\n" + "Welcome :" + name);
				System.out.println();
				Admin.menu();
			} else {
				System.out.println("Wrong username or password");
				menu();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			menu();
		}

	}

	static void userLog() {

		System.out.println("Enter username:");
		String mail = Main.sc.nextLine().trim();
		System.out.println("Enter password:");
		String pass = Main.sc.nextLine().trim();
		try {
			boolean flag = ls.loginUser(mail, pass);
			if (flag) {
				System.out.println();
				System.out.println("Login Successfull..." + "\n" + "Welcome :" + mail);
				System.out.println();
				User.menu();
			} else {
				System.out.println("Wrong Email or password");
				menu();
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			menu();
		}

	}

	static void userSign() {

		String vid = IDUtil.generateVenderId();
		System.out.println("Enter name:");
		String name = Main.sc.nextLine().trim();
		System.out.println("Enter email:");
		String email = Main.sc.nextLine().trim();
		System.out.println("Enter mobile no.:");
		String mobile = Main.sc.nextLine().trim();
		System.out.println("Enter password:");
		String pass = Main.sc.nextLine().trim();
		System.out.println("Enter company:");
		String company = Main.sc.nextLine().trim();
		System.out.println("Enter address:");
		String address = Main.sc.nextLine().trim();

		Vendor vendor = new Vendor(vid, name, mobile, email, pass, company, address);

		try {
			boolean flag = ls.signupUser(vendor);
			if (flag) {
				System.out.println();
				System.out.println("Signup Successfull..." + "\n" + "Welcome :" + name);
				System.out.println();
				userId = vid;
				User.menu();

			} 
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			menu();
		}

	}

}
