package models;

public class Friendship {
	private String _id;
	private String _user1;
	private String _user2;
	private String _type;
	
	public Friendship (String id, String user1, String user2, String type) {
		this._id = id;
		this._user1 = user1;
		this._user2 = user2;
		this._type = type;
	}
	
	public String toString() {
		return "\nId: " + _id + "User 1: " + _user1 + "\nUser 2: " + _user2 + 
				"\nRelation: " + _type; 
	}
}
