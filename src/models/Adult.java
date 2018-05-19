package models;

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
	 * Create Adult user
	 * @return boolean
	 */
//	@Override
//	public boolean create() {
//		//Check username is already exists into the system
//		if(!isUniqUsername()) {
//			System.out.println("\nUsername already exists.");
//			return false;
//		}
//		
//		UserData.write(this.get_username(), TYPE);
//		
//		return true;
//	}

	/*
	 * Connect to another Adult users only
	 * @param user2:User
	 * @return boolean
	 */
	@Override
	public boolean connect(User user2) {
		if(user2 instanceof Adult) {
			Friendship frsd = new Friendship(this, user2, "Friend");
			frsd.create();
			return true;
		}
		
		return false;
	}
}
