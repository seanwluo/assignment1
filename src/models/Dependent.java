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
public class Dependent extends User {
	private final static String TYPE = "dependent";
	
	public Dependent(String username) {
		super(username, TYPE);
	}
	
	/*
	 * Create Dependent user
	 * @return boolean
	 */
	@Override
	public boolean create() {
		if( !isUniqUsername() ) {
			System.out.println("\nUsername already exists.");
			return false;
		}
		
		UserData.write(this.get_username(), TYPE);
		return true;
	}

	/*
	 * Connect to another users
	 * @param user2:User
	 * @return boolean
	 */
	@Override
	public boolean connect(User user2) {
		Profile profile = this.get_profile();
		double age = profile.get_age();
		
		// Only friends with other dependent
		if (user2 instanceof Dependent) {
			// Dependent 2 years or under cannot have friends
			if(age <= 2) {
				System.out.println("\nUnder age to have other friends");
				return false;
			}
			
			Profile user2Profile = user2.get_profile();
			double user2Age = user2Profile.get_age();
			
			//Check for same parents
			//change class type User to Dependent
			if(hasSameParents( (Dependent)user2 )) {
				System.out.println("\nCan not be friends. Both has same parents.");
				return false;
			};
			
			//connecting user age should greater than 2 and 
			//age difference between Dependent is 3 or greater
			if( user2Age > 2 && Math.abs(age - user2Age) <= 3 ) {
				Friendship frnd = new Friendship(this, user2, "friend");
				frnd.create();
				
				return true;
			}
		}

		return false;
	}

	/*
	 * Check dependent and friends to be connected has same parents
	 * @param user2:Dependent
	 * @return boolean
	 * */
	private boolean hasSameParents(Dependent user2) {
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
