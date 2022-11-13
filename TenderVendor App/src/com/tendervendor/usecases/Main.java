package com.tendervendor.usecases;

import java.util.Scanner;

import com.tendervendor.dao.BidDao;
import com.tendervendor.dao.BidDaoImpl;
import com.tendervendor.dao.TenderDao;
import com.tendervendor.dao.TenderDaoImpl;
import com.tendervendor.dao.VendorDao;
import com.tendervendor.dao.VendorDaoImpl;

public class Main {

	public static TenderDao td = new TenderDaoImpl();

	public static VendorDao vd = new VendorDaoImpl();

	public static BidDao bd = new BidDaoImpl();

	public static Scanner sc = new Scanner(System.in);

	public static final String red = "\u001B[31m";

	public static final String bgWhite = "\u001B[47m";

	public static final String reset = "\u001B[0m";

	public static void main(String[] args) {

		System.out.format(red + bgWhite + "+---------------------------------------------------+%n" + reset);
		System.out.format(red + bgWhite + "|            Welcome to TenderVendor App            |%n" + reset);
		System.out.format(red + bgWhite + "+---------------------------------------------------+%n" + reset);
		
		LoginSignup.menu();

	}

}
