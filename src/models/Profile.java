package models;

import java.util.ArrayList;
import java.util.Map;

import Store.ProfileData;

public class Profile {
	private String _username;
	private String _firstname;
	private String _lastname;
	private float _age;
	private String _gender;
	private String _picUrl;
	
	public Profile(String username) {
		this._username = username;
	}
	
	public Profile(String username, String firstname, String lastname,
			float age, String gender, String picUrl) {
		this._username = username;
		this._firstname = firstname;
		this._lastname = lastname;
		this._age = age;
		this._gender = gender;
		this._picUrl = picUrl;
	}

	public String get_firstname() {
		return _firstname;
	}

	public void set_firstname(String _firstname) {
		this._firstname = _firstname;
	}

	public String get_lastname() {
		return _lastname;
	}

	public void set_lastname(String _lastname) {
		this._lastname = _lastname;
	}

	public float get_age() {
		return _age;
	}

	public void set_age(float _age) {
		this._age = _age;
	}

	public String get_gender() {
		return _gender;
	}

	public void set_gender(String gender) {
		this._gender = gender;
	}
	
	public String get_picUrl() {
		return _picUrl;
	}

	public void set_picUrl(String picUrl) {
		this._picUrl = picUrl;
	}
	
	public String toString() {
		return "\nUsername: " + _username + "\nFirst name: "  +  _firstname +
				"\nLast name: " + _lastname + "\nGender: " + _gender + "\nAge: " + _age;
	}

	public boolean create() {
		//check unique username
		ProfileData.write(_username, new String[]{_firstname, _lastname, _gender, Float.toString(_age)});
		return true;
	}
	
	public boolean edit() {
		return false;
	}
	
	protected boolean isUniqUsername() {
		Map<String, ArrayList<String>> pfData = ProfileData.get();
		ArrayList<String> value = pfData.get(_username);
		
		return value == null;
	}
}
