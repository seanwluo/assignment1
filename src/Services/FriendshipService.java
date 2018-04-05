package Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Store.FriendshipData;
import Store.UserData;
import models.Adult;
import models.Dependent;
import models.Friendship;
import models.User;

/**
 * @author Raj
 * 
 * The FriendshipService class data to Friendship objects
 * Maintain the network user connection 
 * and filter methods to filter parents and children
 *   
 */
public class FriendshipService {
	
	/*
	 * List all friendship form data to Friendship object
	 * @param
	 * @return List<Friendship>
	 * */
	public static List<Friendship> allFriendship() {
		List<Friendship> friendships = new ArrayList<Friendship>();
		Map<String, ArrayList<String>> data = FriendshipData.get();
		
		for (Entry<String, ArrayList<String>> entry : data.entrySet()) {
			ArrayList<String> value = entry.getValue();
			String username1 = value.get(0);
			String username2 = value.get(1);
			String type = value.get(2);
			
			//Find user and get user object
			User user1 = UserService.findByUsername(username1);
			User user2 = UserService.findByUsername(username2);
			
			//check both users exists
			if(user1 != null && user2 != null) {
				Friendship friendship = new Friendship(entry.getKey().toString(), user1, user2, type);
				friendships.add(friendship);
			}
		}
	
		return friendships;
	}
	
	/*
	 * Creates Friendship connection between two users with given type
	 * @param user1:User, user2:User, type:String
	 * @return
	 */
	public static void create(User user1, User user2, String type) {
		Friendship frns = new Friendship(user1, user2, type);
		frns.create();
	}
	
	/*
	 * Find friendships objects by username
	 * @param username:String
	 * @return List<Friendship>
	 */
 	public static List<Friendship> findByUsername(String username) {
		List<Friendship> friendships = new ArrayList<Friendship>();
		Map<String, ArrayList<String>> data = FriendshipData.get();
		
		for (Entry<String, ArrayList<String>> entry : data.entrySet()) {
			ArrayList<String> value = entry.getValue();
			String username1 = value.get(0);
			String username2 = value.get(1);
			String type = value.get(2);

			if(username.equals(username1) || username.equals(username2)) {
				
				User user1 = UserService.findByUsername(username1);
				User user2 = UserService.findByUsername(username2);
				
				if(user1 != null && user2 != null) {
					Friendship friendship = new Friendship(entry.getKey().toString(), user1, user2, type);
					friendships.add(friendship);
				}
			}
		}
		
		return friendships;
	}
	
 	/*
	 * Find friendships objects by type of friendship
	 * type: "friend" or "parent-child"
	 * @param type:String
	 * @return List<Friendship>
	 */
	public static List<Friendship> findByType(String type) {
		List<Friendship> friendships = new ArrayList<Friendship>();
		Map<String, ArrayList<String>> data = FriendshipData.get();
		
		for (Entry<String, ArrayList<String>> entry : data.entrySet()) {
			ArrayList<String> value = entry.getValue();
			
			if(value == null) {
				continue;
			}
			
			String username1 = value.get(0);
			String username2 = value.get(1);
			String _type = value.get(2);
			
			if(_type.toLowerCase().equals( type.toLowerCase() )) {
				User user1 = UserService.findByUsername(username1);
				User user2 = UserService.findByUsername(username2);
				
				if(user1 != null && user2 != null) {
					Friendship friendship = new Friendship(entry.getKey().toString(), user1, user2, type);
					friendships.add(friendship);
				}
			}
		}
		
		return friendships;
	}
	
 	/*
	 * Find parents of dependent
	 * @param dependent:Dependent
	 * @return List<User>
	 */
	public static List<User> getParents(Dependent dependent) {
		List<Friendship> allFriends = FriendshipService.findByUsername(dependent.get_username());	
		List<User> parents = new ArrayList<User>();
		
		for(Friendship frnd : allFriends) {
			User[] users = frnd.getUsers();
			
			// If User is Adult then store
			if(users[0] instanceof Adult) {
				parents.add(users[0]);
			} else if (users[1] instanceof Adult) {
				parents.add(users[1]);
			}
		}
		
		return parents;
	}
	
	/*
	 * Find dependents of parent
	 * @param adult:Adult
	 * @return List<User>
	 */
	public static List<User> getChildrens(Adult adult) {
		List<Friendship> allFriends = FriendshipService.findByUsername(adult.get_username());
		List<User> childrens = new ArrayList<User>();
		
		for(Friendship frnd : allFriends) {
			User[] users = frnd.getUsers();
			// If user is Dependent then store
			if(users[0] instanceof Dependent) {
				childrens.add(users[0]);
			} else if(users[1] instanceof Dependent) {
				childrens.add(users[1]);
			}
		}
		
		return childrens;
	}
	
	
	/*
	 * Find friendship connection between two users
	 * @param user1:String, user2:String
	 * @return Friendship
	 */
	public static Friendship findDirectConnection(String user1, String user2) {
		Map<String, ArrayList<String>> data = FriendshipData.get();
		
		for (Entry<String, ArrayList<String>> entry : data.entrySet()) {
			ArrayList<String> value = entry.getValue();
			String username1 = value.get(0);
			String username2 = value.get(1);
			String type = value.get(2);
			
			if( (username1.equals(user1) && username2.equals(user2)) || 
					(username1.equals(user2) && username2.equals(user1)) ) {
				User usr1 = UserService.findByUsername(username1);
				User usr2 = UserService.findByUsername(username2);
				
				if(user1 != null && user2 != null) {
					Friendship friendship = new Friendship(entry.getKey().toString(), usr1, usr2, type);
					return friendship;
				}
			}
		}
		
		return null;
	}

}