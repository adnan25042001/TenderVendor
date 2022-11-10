package com.tendervendor.usecases;

import java.sql.SQLException;
import java.util.Scanner;

import com.tendervendor.dao.LoginAndSignupDao;
import com.tendervendor.dao.LoginAndSignupDaoImpl;

public class LoginSignup {
	
	public static Scanner sc = new Scanner(System.in);
	
	public void logSign() {
			
		int opt = sc.nextInt();
		LoginAndSignupDao ls = new LoginAndSignupDaoImpl();
		
		if(opt == 1) {
			
			System.out.println("Enter username :");
			String name = sc.next();
			System.out.println("Enter password");
			String pass = sc.next();
			String msg;
			try {
				msg = ls.loginAdmin(name, pass);
				System.out.println(msg);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}else if(opt == 2) {
			
			System.out.println("Enter username :");
			String mail = sc.next();
			System.out.println("Enter password :");
			String pass = sc.next();
			try {
				String msg = ls.loginUser(mail, pass);
				System.out.println(msg);
			} catch (SQLException e) { 
				e.printStackTrace();
			}
			
		}else if(opt == 3) {
			
			System.out.println("Enter name :");
			String name = sc.next();
			System.out.println("Enter email :");
			String email = sc.next();
			System.out.println("Enter mobile no. :");
			String mobile = sc.next();
			System.out.println("Enter password :");
			String pass = sc.next();
			System.out.println("Enter company :");
			String company = sc.next();
			System.out.println("Enter address :");
			String address = sc.next();
			
			try {
				String msg = ls.signupUser(name, mobile, email, pass, company, address);
				System.out.println(msg);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}else if(opt == 4) {
			
			System.out.println("Thankyou for visiting us...");
			return;
			
		}else {
			
			System.out.println("Choose correct option...");
			logSign();
			
		}
		
	}
	
}
