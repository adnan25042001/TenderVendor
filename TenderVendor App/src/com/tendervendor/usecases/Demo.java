package com.tendervendor.usecases;

public class Demo {

	public static void main(String[] args) {

		System.out.println("1 : Login as Admin");
		System.out.println("2: Login as Vendor");
		System.out.println("3 : Don't have account signup");
		System.out.println("4 : Exit");
		
		LoginSignup ls = new LoginSignup();
		
		ls.logSign();

	}
}
