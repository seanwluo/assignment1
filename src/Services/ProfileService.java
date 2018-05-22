package Services;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import models.Adult;
import models.Children;
import models.Profile;
import models.User;
import repository.ProfileRepository;
import repository.UserRepository;

/**
 * @author Raj
 * 
 * The ProfileService class provide data to Profile object
 *   
 */
public class ProfileService {
	public static List<Profile> list() {
		ProfileRepository profileRepository = new ProfileRepository();
		Profile profile = null;
		ResultSet result = profileRepository.all();
		
		if (result == null) {
			return null;
		}
		
		try {
			User user;
			while (result.next()) {
				String username = result.getString("user");
				String firstname = result.getString("firstname");
				
				System.out.println(firstname);
			}

		} catch(Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/*
	 *  Get Profile of given user object
	 *  @param user:User
	 *  @return Profile
	 * */
	public static Profile getByUser(User user) {
		ProfileRepository profileRepository = new ProfileRepository();
		Profile profile = null;
		ResultSet result = profileRepository.findByUsername(user.get_username());
		
		if(result == null)
		{	
			System.out.println("Profile not found");
			return profile;
		}
		
		try
		{
			while(result.next())
			{
				String username = result.getString("username");
				System.out.println(username);
				String firstname = result.getString("firstname");
				String lastname = result.getString("lastname");
				String gender = result.getString("gender");
				int age = result.getInt("age");
				String status = result.getString("status");
				String picUrl = result.getString("picUrl");
				String state = result.getString("state");
				
				profile = new Profile(user, firstname, lastname, age, gender, status, picUrl, state);
			}
		} catch(Exception e)
		{	
			e.printStackTrace();
			System.out.println("Profile not found");
		}
		
		return profile;
	}

	/*
	 *  Get Profile of given user object
	 *  @param user:User, firstname:String, lastname:String, gender:String, age:double, 
	 *  	   status:String,picUrl:String
	 *  @return boolean
	 * */
	public static boolean create(User user, String firstname, String lastname, String gender, int age, String status,
			String picUrl, String state) {
		Profile pf = new Profile(user, firstname, lastname, age, gender, status, picUrl, state);
		if(pf.create()) {
			System.out.println("\n User profile created Sucessfully");
			return true;
		} else {
			System.out.println("\n User profile not created.");
			return false;
		}
	}
}
