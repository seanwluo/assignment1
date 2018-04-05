package Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Store.UserData;
import models.Adult;
import models.Dependent;
import models.User;

/**
 * @author Raj
 * 
 * The UserService class provide data to User object
 * and help to create users
 *  
 */
public class UserService {
	
	/*
	 * List all user form data to user object
	 * @param
	 * @return List<User>
	 * */
	public static List<User> allUser() {
		List<User> users = new ArrayList<User>();
		Map<String, String> userData = UserData.get();

		for (Map.Entry<String, String> entry : userData.entrySet()) {
			User user;
			
			if( entry.getValue().equals("adult") ) {
				user = new Adult(entry.getKey());
			} else {
				
				user = new Dependent(entry.getKey());
			}

	        users.add(user);
		}
		
		return users;
	}
	
	/*
	 * Find user by username
	 * @param username:String
	 * @return User
	 * */
	public static User findByUsername(String username) {
		Map<String, String> userData = UserData.get();
		String value = userData.get(username);
		
		if(value != null) {
			User user;
			if(value.equals("adult")) {
				user = new Adult(username);
			} else {
				user = new Dependent(username);
			}
			return user;
		}
		
		return null;
	}
	
	/*
	 * Creates Adult User
	 * @param username:String, firstname:String, lastname:String,  gender:string, 
	 *        age:double, status:String, picUrl:String
	 * @return 
	 * */
	public static void createAdult(String username, String firstname, String lastname, String gender, double age,
			String status, String picUrl) {
	
		User user = new Adult(username);
		
		if(user.create()) {
			boolean isProfileCreated = ProfileService.create(user, firstname, lastname, gender, age, status, picUrl);
			if(isProfileCreated == true ) {
				System.out.println("\nUser Created Sucessfully");
			}

		} else {
			System.out.println("\nError! Account not created.");
		}
	}
	
	/*
	 * Creates Dependent User and creates friendship between parent and child
	 * @param parenName1:String, parentName2: String, username:String, firstname:String, 
	 *        lastname:String,  gender:string, age:double, status:String, picUrl:String
	 * @return 
	 * */
	public static void createDependent(String parentName_1, String parentName_2, String username, String firstname,
			String lastname, String gender, double age, String status, String picUrl) {
		User parent1 = UserService.findByUsername(parentName_1);
		User parent2 = UserService.findByUsername(parentName_2);
		
		if(parent1 == null || parent2 == null) {
			System.out.println("\nError! Account not created.");
			System.out.println("\nBoth parents should have account.");
		} else {
			User user = new Dependent(username);
			
			if(user.create()) {
				boolean isProfileCreated = ProfileService.create(user, firstname, lastname, gender, age, status, picUrl);
				if(isProfileCreated == true ) {
					System.out.println("User Created Sucessfully");
				}
				
				//Create Default parent friendship connection
				//No other can create parent-child connection 
				FriendshipService.create(user, parent1, "parent-child");
				FriendshipService.create(user, parent2, "parent-child");
			} else {
				System.out.println("\nError! Account not created.");
			}
		}
		
	}
}