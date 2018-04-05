package Store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Raj
 * 
 * The ProfileData class maintain the Profile data of user
 * Save in HashMap with key:string and values:ArrayList<String>
 */
public class ProfileData {
	private static Map<String, ArrayList<String>> data = new HashMap<String, ArrayList<String>>();
	
	private static ArrayList<String> prepareList(String[] params){
		ArrayList<String> values = new ArrayList<String>();
		
		for(int i=0; i< params.length; i++) {
			values.add(params[i]);
		}
		
		return values;
	}
	
	public static void load() {
		write("ryjhn", new String[]{"Ryan", "Johnson", "Male", "27", "status", "http://img_url"});
		write("chjhn", new String[]{"Chester", "Johnson", "Male", "2", "status", "http://img_url"});
		write("lihi", new String[]{"Lily", "Higgins", "Female", "23", "status", "http://img_url"});
		write("arist", new String[]{"Arianna", "Stevens", "Female", "27", "status", "http://img_url"});
		write("rosros", new String[]{"Rosie", "Johnson", "Female", "7", "status", "http://img_url"});
		write("samwel", new String[]{"Samantha", "Wells", "Female", "27", "status", "http://img_url"});
		write("paust", new String[]{"Paul", "Stevens", "Male", "18", "status", "http://img_url"});
		write("alhen", new String[]{"Alfred", "Henderson", "Male", "11", "status", "http://img_url"});
		write("wifo", new String[]{"Wilson", "Foster", "Male", "28", "status", "http://img_url"});
		write("vike", new String[]{"Victoria", "Kelley", "Female", "23", "status", "http://img_url"});
		write("alist", new String[]{ "Alina",  "Stevens",  "Female",  "30",  "status", "http://img_url"});
	}
	
	public static Map<String, ArrayList<String>> get() {
		return data;
	}
	
	public static void write(String key, String[] values) {
		data.put(key, prepareList(values));
	}

	public static void remove(String username) {
		data.remove(username);
	}
}
