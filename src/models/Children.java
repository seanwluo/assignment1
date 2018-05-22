package models;

import java.util.List;

import Services.FriendshipService;
import Store.UserData;

/**
 * @author Luo
 * 
 * This Dependent class is inherited from User class
 *  implement the methods create and connect
 * 
 */
public class Children extends User {
	private final static String TYPE = "children";
	
	public Children(String username, String password) {
		super(username, password, TYPE);
	}

	/*
	 * Connect to another users
	 * @param user2:User
	 * @return boolean
	 */
	@Override
	public String[] connect(User user2, String frnType) {
		boolean frnsExists = FriendshipService.existsFriendShip(this, user2);
		System.out.println("From Children");
		if(frnsExists)
		{
			return new String[] {"error", "You are already connected"}; 
		}
		
		Profile profile = this.get_profile();
		double age = profile.get_age();
		
		// Only friends with other dependent
		if (user2 instanceof Children) {
			// Dependent 2 years or under cannot have friends
			if(age <= 2) {
				return new String[] {"error", "Under age to have other friends"};
			}
			
			Profile user2Profile = user2.get_profile();
			double user2Age = user2Profile.get_age();
			
			//Check for same parents
			//change class type User to Dependent
			if(hasSameParents( (Children)user2 )) {
				return new String[] {"error", "Can not be friends. Both has same parents."};
			};
			
			//connecting user age should greater than 2 and 
			//age difference between Dependent is 3 or greater
			if( user2Age > 2 && Math.abs(age - user2Age) <= 3 ) {
				FriendshipService.create(this, user2, frnType);
				
				return new String[] {"success", "Sucessfully connected"};
			}
		}

		return new String[] {"error", "Could not connect"};
	}

	/*
	 * Check dependent and friends to be connected has same parents
	 * @param user2:Dependent
	 * @return boolean
	 * */
	private boolean hasSameParents(Children user2) {
		List<User> parents = FriendshipService.getParents(this);
		List<User> user2Parents = FriendshipService.getParents(user2);
		
		for(User parent: parents) {
			for(User user2parent : user2Parents) {
				if(parent.get_username().equals(user2parent.get_username())) {
					return true;
				}
			}
		}
		
		return false;
	}
	
}
