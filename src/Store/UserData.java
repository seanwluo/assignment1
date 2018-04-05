package Store;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Raj
 * 
 * The ProfileData class maintain the Profile data of user
 * Save in HashMap with key:string and value:String
 */
public class UserData {
	private static Map<String, String> userMap = new HashMap<String, String>();
	
	// Load first at main program
	public static void load() {
		write("ryjhn", "adult");
		write("lihi", "adult");
		write("arist", "adult");
		write("samwel","adult");
		write("paust", "adult");
		write("wifo", "adult");
		write("vike", "adult");
		write("alist", "adult");
		write("chjhn", "dependent");
		write("rosros", "dependent");
		write("alhen", "dependent");
	}
	
	public static Map<String, String> get() {
		return userMap;
	}
	
	public static void write(String user, String type) {		
		userMap.put(user, type);
	}
	
	public static void remove(String username) {
		userMap.remove(username);
	}
}
