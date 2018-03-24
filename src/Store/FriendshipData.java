package Store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FriendshipData {
	private static Map<String, ArrayList<String>> data = new HashMap<String, ArrayList<String>>();
	
	private static ArrayList<String> prepareList(String[] params){
		ArrayList<String> values = new ArrayList<String>();
		
		for(int i=0; i< params.length; i++) {
			values.add(params[i]);
		}
		
		return values;
	}
	
	public static void load() {
		//Need to check order while writing value
		write("frn1", new String[]{"Ryan", "Johnson", "friend"});
		write("frn2", new String[]{"Chester", "Jones", "friend"});
		write("frn3", new String[]{"Lily", "Higgins", "parent"});
		write("frn4", new String[]{"Arianna", "Stevens", "parent"});
		write("frn5", new String[]{"Rosie", "Ross", "friend"});
		write("frn6", new String[]{"Samantha", "Wells", "friend"});
	}
	
	public static Map<String, ArrayList<String>> get() {
		return data;
	}
	
	public static void write(String key, String[] values) {
		data.put(key, prepareList(values));
	}
}
