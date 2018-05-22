package models;

import Services.FriendshipService;
import Store.UserData;

/**
 * @author Luo
 * 
 * This Adult class is inherited from User class
 *  implement the methods create and connect
 * 
 */
public class Adult extends User {
	private final static String TYPE = "adult";	
	
	public Adult(String username, String password) {
		super(username, password, TYPE);
	}

	/*
	 * Connect to another Adult users only
	 * @param user2:User
	 * @return boolean
	 */
	@Override
	public String[] connect(User user2, String frnType) {
		System.out.println("From Adult");
		String[] callback;
		
		boolean frnsExists = FriendshipService.existsFriendShip(this, user2);
		if(frnsExists)
		{
			return new String[] {"error", "You are already connected"}; 
		}		
		
		if(user2 instanceof Adult) {
			Friendship frsd = new Friendship(this, user2, frnType);
			if(frsd.create()) {
				callback = new String[] {"success", "Sucessfully connected"};
			} else {

				callback = new String[] {"error", "Could not connect"};
			}
		} else {
			callback = new String[] {"error", "Under age to have other friends"};
		}
		
		return callback;
	}
}
