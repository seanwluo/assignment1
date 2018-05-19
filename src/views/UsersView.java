package views;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Services.FriendshipService;
import Services.UserService;
import models.Adult;
import models.Children;
import models.Friendship;
import models.Profile;
import models.User;

/**
 * @author Raj
 * 
 * This UserView class perform on main menu action
 * 
 */
public class UsersView {
	private Scanner reader;
	private String choice;
	
	public UsersView() {
		reader = new Scanner(System.in);
	}
	
	public void listAllUser() {
		System.out.println("User list");
		System.out.printf("\n================================");

		List<User> users = UserService.allUser();
		
		for(User user : users) {
			System.out.println(user);
		}
	}
	
	public User selectUser() {
		System.out.print("\nEnter username: ");
		String username = reader.nextLine();
		
		User user = UserService.findByUsername(username);
		if(user == null) {
			System.out.println("\nUser not found!");
		} else {
			System.out.println("\nUser found");
		}
		
		return user;
	}
 	
	public void createUser() {
		System.out.println("\n1. Normal");
		System.out.println("2. Dependent");
		choice = inputReader();
		
		String username, firstname, lastname, gender, status, picUrl, state;
		int age;
		
		System.out.print("\nEnter username: ");
		username = reader.nextLine();
		System.out.print("\nEnter firstname: ");
		firstname  = reader.nextLine();
		System.out.print("\nEnter lastname: ");
		lastname  = reader.nextLine();
		System.out.print("\nEnter gender: ");
		gender  = reader.nextLine();
		System.out.print("\nEnter age:");
		age = ageInput();
		System.out.println("\nEnter status: " );
		status = reader.nextLine();
		System.out.println("\nEnter profile pic");
		picUrl = reader.nextLine();
		System.out.println("\nEnter state");
		state = reader.nextLine();
		
		if(choice.equals("1")) {
			UserService.createAdult(username, firstname, lastname, gender, age, status, picUrl, state);
		} else {
			System.out.println("\nEnter your parents username.");
			System.out.println("\nEnter first parent username: ");
			String parentName_1 = reader.nextLine();
			System.out.println("\nEnter second parent username: ");
			String parentName_2 = reader.nextLine();
			
			UserService.createDependent(parentName_1, parentName_2, username, firstname, lastname, 
					gender, age, status, picUrl, state);
		}
	}
	
	private String inputReader() {
		String gate, ch;
		do {
			System.out.println("\nEnter the option: ");
			ch = reader.nextLine();
			gate = (ch.equals("1") || ch.equals("2")) ? "exit" : "continue";
		} while(!gate.equals("exit"));
		
		return ch;
	}
	
	private int ageInput() {
		int age = 0;
		String input;
		boolean continueLoop = true;
		do {
			try {
				input = reader.nextLine();
				age = Integer.parseInt(input);
				
				if(age > 0) {
					continueLoop = false;
				}
			} catch(Exception e) {
				System.out.println("\nError in age input.");
				System.out.println("\nEnter age: ");
			}
		}while(continueLoop);
		
		return age;
	}

	public void findConnection() {
		System.out.println("\nEnter user1 username: ");
		String username1 = reader.nextLine();
		System.out.println("\nEnter user2 username: ");
		String username2 = reader.nextLine();
		
		User user1 = UserService.findByUsername(username1);
		User user2 = UserService.findByUsername(username2);
		
		if(user1 == null || user2 == null) {
			System.out.println("\nUsers not found!");
			return;
		}
		
		Friendship frn = FriendshipService.findDirectConnection(user1.get_username(), user2.get_username());
		
		if(frn != null) {
			System.out.println("\n Both has direct connection");
		} else {
			List<User> user1Friends = user1.getFriends();
			List<User> user2Friends = user2.getFriends();

			for(User user1Friend: user1Friends) {
				for(User user2Friend: user2Friends) {
					if(user1Friend.get_username().equals(user2Friend.get_username())) {
						System.out.println("\n Both user has common freind " + user1Friend.get_username());
					}
				}
			}
		}
	}

	public void findParentOrChildren() {
		System.out.print("\nEnter username: ");
		String username = reader.nextLine();
		
		User user = UserService.findByUsername(username);
		
		if(user == null ) {
			System.out.println("\nUser not found!");
			return;
		}
		
		List<User> users = new ArrayList<User>();
		if(user instanceof Children) {
			System.out.println("\n Parents: ");
			users = FriendshipService.getParents((Children) user);
		} else {
			System.out.println("\n Children: ");
			users = FriendshipService.getChildrens((Adult) user);
		}
		
		System.out.println("===================");
		for(User usr: users) {
			Profile pf = usr.get_profile();
			System.out.println(" -  " + usr.get_username());
		}
	}
}
