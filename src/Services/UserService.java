package Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Store.UserData;
import models.Adult;
import models.Dependent;
import models.Profile;
import models.User;

public class UserService {
	
	public static List<User> allUser() {
		List<User> users = new ArrayList<User>();
		Map<String, String> userData = UserData.get();

		for (Map.Entry<String, String> entry : userData.entrySet()) {
			User user;
			if(entry.getValue().toString().toLowerCase() == "adult") {
				user = new Adult(entry.getKey().toString());
			} else {
				user = new Dependent(entry.getKey().toString());
			}
			
	        users.add(user);
		}
		
		return users;
	}
	
	public static User findByUsername(String username) {
		Map<String, String> userData = UserData.get();
		String value = userData.get(username);
		
		if(value != null) {
			User user = new Adult(username);
			return user;
		}
		
		return null;
	}

	public static void createAdult(String username, String firstname, String lastname,
			String gender, int age) {
		Adult adult = new Adult(username);
		if(adult.create()) {
			Profile pf = new Profile(username);
			pf.set_firstname(firstname);
			pf.set_lastname(lastname);
			pf.set_gender(gender);
			pf.set_age(age);
			
			pf.create();
		}
	}
}
