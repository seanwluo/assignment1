package main;

import java.sql.SQLException;
import db.DBUtil;
import db.HSQLServer;
import views.Menu;

/**
 * @author Raj
 * 
 * The MiniNet class execute the program
 */
public class MiniNet {
	/*
	 * Program main method to run
	 * */
	public static void main(String[] args) throws SQLException {
		Thread thread = new Thread(){
		    public void run(){
				HSQLServer hsqlServer = new HSQLServer();
				hsqlServer.start();
		    }
		  };

		thread.start();		
		
		DBUtil dbUtil = new DBUtil();
		
		if(dbUtil.connect()) {
			System.out.println("Connected successfully");
			Menu menu = new Menu();
			menu.load();
		} else {
			System.out.println("Not connected");
		}
	}

}
	