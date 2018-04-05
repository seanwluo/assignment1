package views;

import java.util.Scanner;

import models.User;

/**
 * @author Luo
 * 
 * This Menu class display main menu and perform on menu action
 * 
 */
public class Menu {
	private Scanner reader;
	
	public Menu() {
		reader = new Scanner(System.in);
	}
	
	public void load() {
		String choice;
		System.out.println("\nWelcome to MiniNet\n");
		do {
			menuDisplay();
			choice = reader.nextLine();
			processChoice(choice);
			
		} while (!choice.equals("?"));	
		System.out.println("\nExiting MiniNet. Bye bye!");
	}
	
	private void menuDisplay() {
		System.out.println("\nMiniNet menu");
		System.out.println("===================");
		System.out.println("1. List everyone");
		System.out.println("2. Select a person");
		System.out.println("3. Add a person");
		System.out.println("4. Are these two direct friends?");
		System.out.println("5. Find a user parents or childrens");
		System.out.println("?. Exit");
		System.out.print("\nEnter the choice: ");
	}
	
	private void processChoice(String choice) {
		UsersView usr = new UsersView();
		
		switch(choice) {
			case "1":
				usr.listAllUser();
				break;
			case "2":
				User user = usr.selectUser();
				
				if( user != null) {
					SelectedUserView sUser = new SelectedUserView(user);
					sUser.load();
				}
				
				break;
			case "3":
				usr.createUser();
				break;
			case "4":
				usr.findConnection();
				break;
			case "5":
				usr.findParentOrChildren();
				break;
			case "?":
				//Do nothing
				break;
			default:
				System.out.println("\nWARNINIG!! option out of range.");
				System.out.println("\nEnter options from menu list");
				break;
		}
	}

}
