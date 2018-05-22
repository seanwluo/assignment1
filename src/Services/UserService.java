package Services;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Exception.NoAccountCreatedException;
import Exception.NoAvailableException;
import Exception.NoParentException;
import Exception.NotToBeCoupledException;
import models.Adult;
import models.Children;
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
	public static List<User> allUser(String currUser) {
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
				if(currUser.equals(username)) {
					continue;
				}
				
				String password = result.getString("password");
				String type = result.getString("type");
				
				if(type.toLowerCase().equals("adult")) {
					user = new Adult(username, password);
				} else {
					user = new Children(username, password);
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
	public static User findByUsername(String username) throws NoAvailableException {
		UserRepository userRepo = new UserRepository();
		ResultSet result = userRepo.findByUsername(username);
		
		if (result == null) {
			throw new NoAvailableException("User not found");
		}
		
		User user = null;
		try {
			while (result.next()) {
				String usrname = result.getString("username");
				String password = result.getString("password");
				String type = result.getString("type");
				
				if(type.toLowerCase().equals("adult")) {
					user = new Adult(usrname, password);
				} else {
					user = new Children(username, password);
				}	
				System.out.println(user);
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
	public static boolean createAdult(String username) {
	
		User user = new Adult(username, "password");
		
		if(user.create()) {
//			System.out.println("\nUser Created Sucessfully");
			return true;
		} else {
//			System.out.println("\nError! Account not created.");
			return false;
		}
	}
	
	/*
	 * Creates Dependent User and creates friendship between parent and child
	 * @param parenName1:String, parentName2: String, username:String, firstname:String, 
	 *        lastname:String,  gender:string, age:double, status:String, picUrl:String
	 * @return 
	 * */
	public static String[] createDependent(String parentName_1, String parentName_2, String username) throws NoAvailableException,
	NoParentException, NoAccountCreatedException, NotToBeCoupledException {
		
		User parent1 = UserService.findByUsername(parentName_1);
		User parent2 = UserService.findByUsername(parentName_2);
		
		if(parent1 == null || parent2 == null) {
			throw new NoParentException("User not found");
		} else {
			if(!FriendshipService.existsFriendShip(parent1, parent2)) {
				throw new NotToBeCoupledException("Parents are not connected");
			}
			User user = new Children(username, "password");
			
			if(user.create()) {
				//Create Default parent friendship connection
				//No other can create parent-child connection 
				FriendshipService.create(user, parent1, "parent");
				FriendshipService.create(user, parent2, "parent");
				
				return new String[] {"success", "Account created."};
			} else {
				throw new NoAccountCreatedException("User Account not created.");
			}
		}
		
	}
}
