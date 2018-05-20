/**
 * 
 */
package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Services.FriendshipService;
import Services.ProfileService;
import Store.UserData;
import repository.UserRepository;

/**
 * @author Luo
 * 
 * This User class is abstract, provides all getters and setters
 * 
 */
public abstract class User {
	private String _username;
	private String _type;
	private String _password;
	private UserRepository userRepository = new UserRepository();;
	/*
	 * Constructor
	 * @param username:String
	 * */
	public User(String username) {
		this._username = username;
	}
	
	/*
	 * Constructor
	 * @param username:String, type:String
	 * */
	public User(String username, String type) {
		this._username = username;
		this._type = type;
	}
	
	public User(String username, String password, String type) {
		this._username = username;
		this._password = password;
		this._type = type;
	}
	
	/*
	 * Check new username is unique than existing user.
	 * @return boolean
	 * */
	protected boolean isUniqUsername() {
		Map<String, String> userData = UserData.get();
		String value = userData.get(_username);
		
		return value == null;
	}
	
	public String get_username() {
		return _username;
	}

	public void set_username(String _username) {
		this._username = _username;
	}
	
	public String getType() {
		return _type;
	}

	public void setType(String type) {
		this._type = type;
	}
	
	public String toString() {
		return "\nUsername: " + _username;
	}
	
	/*
	 *  Get profile of current user
	 *  @return Profile
	 */
	public Profile get_profile() {
		return  ProfileService.getByUser(this);
	};
	
	/*
	 * Delete current user related all the data from store
	 * @return boolean
	 */
	public boolean delete() {
		//delete user profile
		this.get_profile().delete();
		
		// delete all friendship of users
		List<Friendship> friends = FriendshipService.findByUsername(_username);
		for(Friendship frn: friends) {
			frn.delete();
		}
		
		// delete user
		UserData.remove(_username);
	
		return true;
	}
	
	/*
	 *  Get list of users friends
	 *  @return List<User>
	 */
	public List<User> getFriends() {
		List<Friendship> frns = FriendshipService.findByUsername(_username);
		List<User> users = new ArrayList<User>();
		
		for(Friendship frn: frns) {
			User[] usrs = frn.getUsers();
			// filter users
			if(usrs[0].get_username().equals(_username)) { 
				users.add(usrs[1]);
			} else {
				users.add(usrs[0]);
			}
		}
		
		return users;
	}
	
//	public abstract boolean create();
	public abstract boolean connect(User user2);
	
	public boolean create() {
		return userRepository.save(_username, _password, _type);
	}
	
//	public boolean update() {
//		return userRepository.update(_username, _password, _type);
//	}
}
