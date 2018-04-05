package models;

import Store.FriendshipData;

/**
 * @author Raj
 * 
 * The Friendship class maintain the connection between two users
 * by their friendship type
 *   
 */
public class Friendship {
	private String _id;
	private User _user1;
	private User _user2;
	private String _type;
	
	public Friendship (String id, User user1, User user2, String type) {
		this._id = id;
		this._user1 = user1;
		this._user2 = user2;
		this._type = type;
	}
	
	public Friendship (User user1, User user2, String type) {
		this._user1 = user1;
		this._user2 = user2;
		this._type = type;
	}
	
	public String get_type() {
		return _type;
	}
	
	public String toString() {
		return "\nId: " + _id + "User 1: " + _user1.get_username() + "\nUser 2: " + _user2.get_username() + 
				"\nRelation: " + _type; 
	}
	
	public User[] getUsers() {
		return new User[]{_user1, _user2};
	}
	
	/*
	 * Create Friendship connection
	 * @return boolean
	 */
	public boolean create() {
		String username1 = _user1.get_username();
		String username2 = _user2.get_username();
		String key = username1 + "_" + username2;
		
		FriendshipData.write(key, new String[]{username1, username2, _type});
		
		return true;
	}
	
	/*
	 * Remove the Friendship connection
	 * @return boolean
	 */
	public boolean delete() {
		FriendshipData.remove(_id);
		return true;
	}
}
