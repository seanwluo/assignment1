package main;

import Store.FriendshipData;
import javafx.application.Application;
import Store.ProfileData;
import Store.UserData;
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
	public static void main(String[] args) {
		// Load all hard coded data
		UserData.load();
		FriendshipData.load();
		ProfileData.load();

		Menu menu = new Menu();
		menu.load();
		 
		
	}

}
