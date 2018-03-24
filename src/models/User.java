/**
 * 
 */
package models;

import java.util.Map;
import Store.UserData;

/**
 * @author Raj
 *
 */
public abstract class User {
	private String _username;
	private String _type;
	
	public User(String username) {
		this._username = username;
	}
	
	public User(String username, String type) {
		this._username = username;
		this._type = type;
	}
	
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
		return "\nUsername: " + _username + "\ntype: "  +  _type;
	}
	
	public abstract boolean create();
	public abstract boolean connect();
}
