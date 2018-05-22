package Services;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Exception.NoAccountCreatedException;
import Exception.NoAvailableException;
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
	/*
	 *  Get Profile of given user object
	 *  @param user:User
	 *  @return Profile
	 * */
	public static Profile getByUser(User user) throws NoAvailableException {
		ProfileRepository profileRepository = new ProfileRepository();
		Profile profile = null;
		ResultSet result = profileRepository.findByUsername(user.get_username());
		
		if(result == null)
		{	
			throw new NoAvailableException("Profile not found.");
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
			throw new NoAvailableException("Profile not found. Somwthing went wrong.");
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
			String picUrl, String state) throws NoAccountCreatedException {
		Profile pf = new Profile(user, firstname, lastname, age, gender, status, picUrl, state);
		if(pf.create()) {
//			System.out.println("\n User profile created Sucessfully");
			return true;
		} else {
//			System.out.println("\n User profile not created.");
			throw new NoAccountCreatedException("Profile not found.");
		}
	}
}
