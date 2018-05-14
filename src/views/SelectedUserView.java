package views;

import java.util.Scanner;

import Services.UserService;
import models.Profile;
import models.User;

/**
 * @author Luo
 * 
 * This SelectedUser class display sub menu and perform on menu action
 * 
 */
public class SelectedUserView {
	private Scanner _reader;
	private User _user;
	
	public SelectedUserView(User user) {
		_reader = new Scanner(System.in);
		this._user = user;
	}
	
	public void load() {
		String choice;
		do {
			menuDisplay();
			choice = _reader.nextLine();
			processChoice(choice);
		} while (!choice.equals("?"));	
	}
	
	private void processChoice(String choice) {
		switch(choice) {
		case "1":
			view();
			break;
		case "2":
			edit();
			break;
		case "3":
			delete();
			break;
		case "4":
			connect();
			break;
		case "5":
			listFriends();
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
	
	private void view() {
		System.out.println(_user.get_profile());
	}
	
	private void edit() {
		String firstname, lastname, gender, status, picUrl;
		int age;
		System.out.println("\nEdit Profile.");
		System.out.print("\nEnter firstname: ");
		firstname  = _reader.nextLine();
		System.out.print("\nEnter lastname: ");
		lastname  = _reader.nextLine();
		System.out.print("\nEnter gender: ");
		gender  = _reader.nextLine();
		System.out.print("\nEnter age:");
		age = ageInput();
		System.out.println("\nEnter status: " );
		status = _reader.nextLine();
		System.out.println("\nEnter profile pic");
		picUrl = _reader.nextLine();
		
		Profile pf = _user.get_profile();
		pf.set_firstname(firstname);
		pf.set_lastname(lastname);
		pf.set_gender(gender);
		pf.set_age(age);
		pf.set_status(status);
		pf.set_picUrl(picUrl);
		
		pf.update();
		
	}
	
	private void delete() {
		Profile profile = _user.get_profile();
		profile.delete();
		
		System.out.println("\nUser profile deleted.");
	}
	
	private void listFriends() {
		System.out.println("\nFriend List");
		System.out.println("===================");
		
		for(User usr: _user.getFriends()) {
			System.out.println(usr.get_username());
		}
	}
	
	private String inputReader() {
		String gate, ch;
		do {
			System.out.println("\nEnter the option: ");
			ch = _reader.nextLine();
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
				input = _reader.nextLine();
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
	
	private void menuDisplay() {
		System.out.println("\nUsers menu");
		System.out.println("===================");
		System.out.println("1. View profile");
		System.out.println("2. Edit profile");
		System.out.println("3. Delete profile");
		System.out.println("4. Connect to person");
		System.out.println("5. list friends");
		System.out.println("?. Exit");
		System.out.print("\nEnter the choice: ");
	}
	
	private void connect() {
		System.out.println("\nEnter username to cnnect");
		String username = _reader.nextLine();
		User user2 = UserService.findByUsername(username);
		
		if(user2 == null) {
			System.out.println("\nUser not exists");
		} else {
			_user.connect(user2);
			System.out.println("\nConnected to the " + username);
		}
		
	}
}
