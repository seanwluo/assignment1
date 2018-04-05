package Services;

import java.util.ArrayList;
import java.util.Map;

import Store.ProfileData;
import models.Profile;
import models.User;

/**
 * @author Raj
 * 
 * The ProfileService class provide data to Profile object
 *   
 */
public class ProfileService {
	
	/*
	 *  Get Profile of given user object
	 *  @param user:User
	 *  @return Profile
	 * */
	public static Profile getByUser(User user) {
		Map<String, ArrayList<String>> profileData = ProfileData.get();
		ArrayList<String> value = profileData.get(user.get_username());
		
		if(value != null) {
			String firstname = value.get(0);
			String lastname = value.get(1);
			String gender = value.get(2);
			// TO DO: Need to catch error
			double age = Double.parseDouble(value.get(3));
			String status = value.get(4);
			String picUrl = value.get(5);
			
			Profile profile = new Profile(user, firstname, lastname, age, gender, status, picUrl);
			return profile;
		}
		
		return null;
	}

	/*
	 *  Get Profile of given user object
	 *  @param user:User, firstname:String, lastname:String, gender:String, age:double, 
	 *  	   status:String,picUrl:String
	 *  @return boolean
	 * */
	public static boolean create(User user, String firstname, String lastname, String gender, double age, String status,
			String picUrl) {
		Profile pf = new Profile(user, firstname, lastname, age, gender, status, picUrl);
		if(pf.create()) {
			System.out.println("\n User profile created Sucessfully");
			System.out.println(pf);
			return true;
		} else {
			System.out.println("\n User profile not created.");
			return false;
		}
	}
}
