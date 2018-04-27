package main;

import db.DBUtil;
import db.HSQLServer;

/**
 * @author Raj
 * 
 * The MiniNet class execute the program
 */
public class MiniNet {
	/*
	 * Program main method to run
	 * */
	public static void main(String[] args) {
		// Load all hard coded data
//		UserData.load();
//		FriendshipData.load();
//		ProfileData.load();
//
//		Menu menu = new Menu();
//		menu.load();
		
		HSQLServer hsqlServer = new HSQLServer();
		hsqlServer.start();
		
		DBUtil dbUtil = new DBUtil();
		if(dbUtil.connect()) {
			System.out.println("Connected successfully");
		} else {
			System.out.println("Not connected");
		}
	}

}
	