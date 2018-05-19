package main;

import java.sql.SQLException;

import db.DBUtil;
import db.HSQLServer;
import util.TransferFileToDB;
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
		//TODO: Stop thread and server on the application close
		
		DBUtil dbUtil = new DBUtil();
		
		if(dbUtil.connect()) {
			System.out.println("Connected successfully");
//			Menu menu = new Menu();
//			menu.load();
			TransferFileToDB todb = new TransferFileToDB();
			todb.loadPersonData();
		} else {
			System.out.println("Not connected");
		}
	}

}
	