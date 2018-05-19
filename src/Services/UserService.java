package Services;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Adult;
import models.Dependent;
import models.User;
import repository.UserRepository;

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
		UserRepository userRepo = new UserRepository();
		ResultSet result = userRepo.userList();
		List<User> users = new ArrayList<User>();
		
		if (result == null) {
			return null;
		}
		
		try {
			User user;
			while (result.next()) {
				String username = result.getString("username");
				String type = result.getString("type");
				
				if(type.equals("adult")) {
					user = new Adult(username, "password");
				} else {
					user = new Dependent(username);
				}
				users.add(user);
			}

		} catch(Exception e) {
			e.printStackTrace();
		}

		return users;
	}
	
	/*
	 * Find user by username
	 * @param username:String
	 * @return User
	 * */
	public static User findByUsername(String username) {
		UserRepository userRepo = new UserRepository();
		ResultSet result = userRepo.findByUsername(username);
		
		if (result == null) {
			return null;
		}
		
		User user = null;
		try {
			while (result.next()) {
				String usrname = result.getString("username");
				String type = result.getString("type");
				
				if(type.equals("adult")) {
					user = new Adult(usrname, "password");
				} else {
					user = new Dependent(username);
				}	
			}
		} catch(Exception e) {
			System.out.println("Error in query");
		}
		
		return user;
	}
	
	/*
	 * Creates Adult User
	 * @param username:String, firstname:String, lastname:String,  gender:string, 
	 *        age:double, status:String, picUrl:String
	 * @return 
	 * */
	public static void createAdult(String username, String firstname, String lastname, String gender, int age,
			String status, String picUrl) {
	
		User user = new Adult(username, "password");
		
		if(user.create()) {
			boolean isProfileCreated = ProfileService.create(user, firstname, lastname, gender, age, status, picUrl);
			if(isProfileCreated) {
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
			String lastname, String gender, int age, String status, String picUrl) {
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
