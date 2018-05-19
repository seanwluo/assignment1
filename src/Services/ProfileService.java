package Services;

import java.sql.ResultSet;
import models.Profile;
import models.User;
import repository.ProfileRepository;

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
		ProfileRepository profileRepository = new ProfileRepository();
		Profile profile = null;
		ResultSet result = profileRepository.findByUser(user.get_username());
		
		if(result == null)
		{
			return profile;
		}
		
		try
		{
			while(result.next())
			{
				String firstname = result.getString("firstname");
				String lastname = result.getString("lastname");
				String gender = result.getString("gender");
				// TO DO: Need to catch error
				int age = result.getInt("age");
				String status = result.getString("status");
				String picUrl = result.getString("ulr");
				String state = result.getString("state");
				
				profile = new Profile(user, firstname, lastname, age, gender, status, picUrl, state);
			}
		} catch(Exception e)
		{
			System.out.println("User not found");
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
			System.out.println(pf);
			return true;
		} else {
			System.out.println("\n User profile not created.");
			return false;
		}
	}
}
