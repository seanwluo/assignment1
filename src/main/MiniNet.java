package main;

import Store.FriendshipData;
import Store.ProfileData;
import Store.UserData;
import javafx.application.Application;
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
	
	//finitialinterface fin=new finitialinterface();
	
	public static void main(String[] args) {
		// Load all hard coded data
		UserData.load();
		FriendshipData.load();
		ProfileData.load();

		Menu menu = new Menu();
		
	
	}

}
