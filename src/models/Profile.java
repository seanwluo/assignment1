package models;

import java.util.ArrayList;
import java.util.Map;

import Store.ProfileData;
import repository.ProfileRepository;

/**
 * @author Luo
 * 
 * This Profile class is associated with user and maintain user profile details
 * 
 */
public class Profile {
	private User _user;
	private String _firstname;
	private String _lastname;
	private int _age;
	private String _gender;
	private String _status;
	private String _picUrl;
	private String _state;
	private ProfileRepository profileRepository;
	
	public Profile(User user) {
		this._user = user;
		profileRepository = new ProfileRepository();
	}
	
	public Profile(User user, String firstname, String lastname,
			int age, String gender, String status, String picUrl, String state) {
		this._user = user;
		this._firstname = firstname;
		this._lastname = lastname;
		this._age = age;
		this._gender = gender;
		this._picUrl = picUrl;
		this._state = state;
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

	public double get_age() {
		return _age;
	}

	public void set_age(int age) {
		this._age = age;
	}

	public String get_gender() {
		return _gender;
	}

	public void set_gender(String gender) {
		this._gender = gender;
	}
	
	public String get_status() {
		return _status;
	}

	public void set_status(String status) {
		this._status = status;
	}
	
	public String get_picUrl() {
		return _picUrl;
	}

	public void set_picUrl(String picUrl) {
		this._picUrl = picUrl;
	}
	
	public String get_state() {
		return _state;
	}
	
	public void set_state(String state) {
		this._state = state;
	}
	
	public String toString() {
		return "\nUsername: " + _user.get_username() + "\nFirst name: "  +  _firstname +
				"\nLast name: " + _lastname + "\nGender: " + _gender + "\nAge: " + _age + 
				"\nStatus: " + _status + "\nProfile image: " + _picUrl + "\nState: " + _state;
	}

	public boolean create() {
//		check unique key
		return profileRepository.save(_user.get_username(), _firstname, _lastname, _age, _gender, 
				_status, _picUrl, _state);
//		if(isUniqUsername()) {
//			ProfileData.write(_user.get_username(), new String[]{_firstname, _lastname, _gender, Double.toString(_age), _status, _picUrl});
//			return true;
//		} else {
//			System.out.println("\nUser profile already created");
//			return false;
//		}
	}
	
	public boolean update() {
		ProfileData.write(_user.get_username(), new String[]{_firstname, _lastname, _gender, Double.toString(_age)});
//		profileRepository.update();
		return true;
	}
	
	public boolean delete() {
		ProfileData.remove(_user.get_username());
//		profileRepository.delete();
		return true;
	}
	
	private boolean isUniqUsername() {
		Map<String, ArrayList<String>> pfData = ProfileData.get();
		ArrayList<String> value = pfData.get(_user.get_username());
		
		return value == null;
	}
}
