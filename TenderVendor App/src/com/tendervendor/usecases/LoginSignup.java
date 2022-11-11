package com.tendervendor.usecases;

import java.sql.SQLException;

import com.tendervendor.dao.LoginAndSignupDao;
import com.tendervendor.dao.LoginAndSignupDaoImpl;

public class LoginSignup {
		
	public static LoginAndSignupDao ls = new LoginAndSignupDaoImpl();
	
	
	public static void menu() {
		
		System.out.println();
		System.out.println("1 : Login as Admin");
		System.out.println("2 : Login as Vendor");
		System.out.println("3 : Don't have account signup");
		System.out.println("4 : Exit");
		
		enterOption();
		
	}
	
	
	static void enterOption() {
		
		int opt = Main.sc.nextInt();
		
		if(opt == 1) {
			adminLog();
		}else if(opt == 2) {
			userLog();
		}else if(opt == 3) {
			userSign();
		}else if(opt == 4) {
			System.out.println("exit...");
			System.exit(0);
		}else {
			System.out.println("Enter correct option..");
			enterOption();
		}
		
	}
	
	
	
	static void adminLog() {
		
		System.out.println("Enter username :");
		String name = Main.sc.next();
		System.out.println("Enter password");
		String pass = Main.sc.next();
		try {
			boolean flag = ls.loginAdmin(name, pass);
			if(flag) {
				System.out.println();
				System.out.println("Login Successfull..." + "\n" + "Welcome :" + name);
				System.out.println();
				Admin.menu();
			}else {
				System.out.println("Wrong username or password");
				adminLog();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	static void userLog() {
		
		System.out.println("Enter username :");
		String mail = Main.sc.next();
		System.out.println("Enter password :");
		String pass = Main.sc.next();
		try {
			boolean flag = ls.loginUser(mail, pass);
			if(flag) {
				System.out.println();
				System.out.println("Login Successfull..." + "\n" + "Welcome :" + mail);
				System.out.println();
				User.menu();
			}else {
				System.out.println("Wrong Email or password");
				userLog();
			}

		} catch (SQLException e) { 
			e.printStackTrace();
		}
		
	}
	
	
	
	static void userSign() {
		
		System.out.println("Enter name :");
		String name = Main.sc.next();
		System.out.println("Enter email :");
		String email = Main.sc.next();
		System.out.println("Enter mobile no. :");
		String mobile = Main.sc.next();
		System.out.println("Enter password :");
		String pass = Main.sc.next();
		System.out.println("Enter company :");
		String company = Main.sc.next();
		System.out.println("Enter address :");
		String address = Main.sc.next();
		
		try {
			boolean flag = ls.signupUser(name, mobile, email, pass, company, address);
			if(flag) {
				System.out.println();
				System.out.println("Signup Successfull..." + "\n" + "Welcome :" + name);
				System.out.println();
				User.menu();
			}else {
				System.out.println("Email already exists...");
				userSign();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
