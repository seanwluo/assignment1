package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.rowset.CachedRowSetImpl;

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
	