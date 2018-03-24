package Store;

import java.util.HashMap;
import java.util.Map;

public class UserData {
	private static Map<String, String> userMap = new HashMap<String, String>();
	
	// Load first at main program
	public static void load() {
		write("ryjhn", "Adult");
		write("chjhn", "Dependent");
		write("lihi", "Adult");
		write("arist", "Adult");
		write("rosro", "Dependent");
		write("samwe","Adult");
		write("paust", "Adult");
		write("alhen", "Adult");
		write("wifo", "Adult");
		write("vike", "Adult");
		write("alist", "Adult");
	}
	
	public static Map<String, String> get() {
		return userMap;
	}
	
	public static void write(String user, String type) {		
		userMap.put(user, type);
	}
}
