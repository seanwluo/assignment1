package Services;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Exception.NoAvailableException;
import Store.FriendshipData;
import models.Adult;
import models.Children;
import models.Friendship;
import models.User;
import repository.FriendShipRepository;

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
	 * Creates Friendship connection between two users with given type
	 * @param user1:User, user2:User, type:String
	 * @return
	 */
	public static boolean create(User user1, User user2, String type) {
		
		Friendship frns = new Friendship(user1, user2, type);
		return frns.create();
	}
	
	/*
	 * Find friendships objects by username
	 * @param username:String
	 * @return List<Friendship>
	 */
 	public static List<Friendship> findByUsername(String username) {
 		FriendShipRepository frndshipRepository = new FriendShipRepository();
		ResultSet result = frndshipRepository.friendshipByUser(username);
		List<Friendship> friendships = new ArrayList<Friendship>();
		
		if(result == null)
		{
			return friendships;
		}
		
		Friendship friendship;
		try {
			while(result.next())
			{	
				Integer id = result.getInt("id");
				String username1 = result.getString("username1");
				String username2 = result.getString("username2");				
				String type = result.getString("type");
				
				User user = UserService.findByUsername(username1);
				User user2 = UserService.findByUsername(username2);
				friendship = new Friendship(id, user, user2, type);
				friendships.add(friendship);
			}
		} catch(Exception  e)
		{
			System.out.println("Error in query");
		}
	
		return friendships;
	}
	
 	/*
	 * Find friendships objects by type of friendship
	 * type: "friend" or "parent-child"
	 * @param type:String
	 * @return List<Friendship>
	 */
	public static List<Friendship> findByType(String type) throws NoAvailableException {
		FriendShipRepository frndshipRepository = new FriendShipRepository();
		ResultSet result = frndshipRepository.friendshipByType(type);
		List<Friendship> friendships = new ArrayList<Friendship>();
		
		if(result == null)
		{
			throw new NoAvailableException("Relation Not found");
		}
		
		Friendship friendship = null;
		try {
			while(result.next())
			{
				String username1 = result.getString("username1");
				String username2 = result.getString("username2");
				String type1 = result.getString("type");
				
				User user = UserService.findByUsername(username1);
				User user2 = UserService.findByUsername(username2);
				friendship = new Friendship(user, user2, type1);
				friendships.add(friendship);
			}
		} catch(Exception  e)
		{
			System.out.println("Error in query");
		}
	
		return friendships;
	}
	
 	/*
	 * Find parents of dependent
	 * @param dependent:Dependent
	 * @return List<User>
	 */
	public static List<User> getParents(Children children) {
		List<Friendship> allFriends = FriendshipService.findByUsername(children.get_username());	
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
			if(users[0] instanceof Children) {
				childrens.add(users[0]);
			} else if(users[1] instanceof Children) {
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
	public static Friendship findDirectConnection(String user1, String user2) throws NoAvailableException {
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
					Friendship friendship = new Friendship(usr1, usr2, type);
					return friendship;
				} else {
					throw new NoAvailableException("Friendship not found");
				}
			}
		}
		
		return null;
	}

	public static boolean existsFriendShip(User user1, User user2) {
		List<User> users = user1.getFriends();
		for(User user: users)
		{
			if(user.get_username().equals(user2.get_username()))
			{
				return true;
			}
		}
		return false;
	}

}