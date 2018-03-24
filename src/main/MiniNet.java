package main;

import java.util.List;
import java.util.Scanner;

import Services.UserService;
import Store.FriendshipData;
import Store.ProfileData;
import Store.UserData;
import models.User;

public class MiniNet {
	private static Scanner reader;
	private static String choice;
	
	public static void main(String[] args) {
		UserData.load();
		FriendshipData.load();
		ProfileData.load();
		
		reader = new Scanner(System.in);
		System.out.println("\nWelcome to MiniNet\n");
		do {
			menu();
			choice = reader.nextLine();
			processChoice(choice);
			
		} while (!choice.equals("?"));	
		System.out.println("\nExiting MiniNet. Bye bye!");
	}
	
	private static void menu() {
		System.out.println("\nMiniNet menu");
		System.out.println("===================");
		System.out.println("1. List everyone");
		System.out.println("2. Select a person");
		System.out.println("3. Add a person");
		System.out.println("4. Update a person");
		System.out.println("5. Delete a person");
		System.out.println("6. Connect to person");
		System.out.println("7. Are these two direct friends?");
		System.out.println("8. Find a parents of a children");
		System.out.println("?. Exit");
		System.out.print("\nEnter the choice: ");
	}

	private static void processChoice(String choice) {
//		System.out.print("\033[H\033[2J");  
//	    System.out.flush(); 
		switch(choice) {
			case "1":
				System.out.println("User list");
				System.out.printf("\n================================");

				List<User> users = UserService.allUser();
				
				for(User user : users) {
					System.out.println(user);
				}
				
				break;
			case "2":
				System.out.print("\nEnter username: ");
				String input = reader.nextLine();
				
				User user = UserService.findByUsername(input);
				if(user == null) {
					System.out.println("\nUser not found!");
				} else {
					System.out.println("\nUser found");
					System.out.println(user);
				}
				break;
			case "3":
				createUser();
				break;
			default:
				System.out.println("\nWARNINIG!! option out of range.");
				System.out.println("\nEnter options from menu list");
				break;
		}
	}
	
	private static void createUser() {
		System.out.println("\n1. Normal");
		System.out.println("2. Dependent");
		System.out.println("\nEnter the option: ");
		choice = reader.nextLine();
		String username, firstname, lastname, gender;
		int age;
		if(choice.equals("1")) {
			System.out.print("\nEnter username: ");
			username = reader.nextLine();
			System.out.print("\nEnter firstname: ");
			firstname  = reader.nextLine();
			System.out.print("\nEnter lastname: ");
			lastname  = reader.nextLine();
			System.out.print("\nEnter gender: ");
			gender  = reader.nextLine();
			System.out.print("\nEnter age:");
			age = Integer.parseInt(reader.nextLine());
			UserService.createAdult(username, firstname, lastname, gender, age);
		} else if(choice.equals("2")) {
			System.out.print("\nEnter username: ");
			username = reader.nextLine();
			System.out.print("\nEnter firstname: ");
			firstname  = reader.nextLine();
			System.out.print("\nEnter lastname: ");
			lastname  = reader.nextLine();
			System.out.print("\nEnter gender: ");
			gender  = reader.nextLine();
			System.out.print("\nEnter age:");
			age = Integer.parseInt(reader.nextLine());
//			UserService.createDependent(parent1, parent2, username, firstname, lastname, gender, age, int);
			
//			UserService.createDependent();
		} else {
			System.out.println("\n Out of range");
		}
		
	}
}
