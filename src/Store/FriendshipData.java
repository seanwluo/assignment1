package Store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Raj
 * 
 * The FriendshipData class maintain the Friendship data of network
 * save in HashMap with key:string and values:ArrayList<String>
 */
public class FriendshipData {
	/*
	 * Key is random string
	 * values format username1, username2, type of friendship
	 * */
	private static Map<String, ArrayList<String>> data = new HashMap<String, ArrayList<String>>();
	
	private static ArrayList<String> prepareList(String[] params){
		ArrayList<String> values = new ArrayList<String>();
		
		for(int i=0; i< params.length; i++) {
			values.add(params[i]);
		}
		
		return values;
	}
	
	public static void load() {
		write("frn1", new String[] {"ryjhn", "lihi", "friend"});
		write("frn3", new String[] {"lihi", "chjhn", "parent-child"});
		write("frn4", new String[] {"ryjhn", "chjhn",  "parent-child"});
		write("frn5", new String[] {"lihi", "rosros", "parent-child"});
		write("frn6", new String[] {"ryjhn", "rosros", "parent-child"});
		write("frn7", new String[] {"samwel", "ryjhn", "friend"});
		write("frn8", new String[] {"paust", "ryjhn", "friend"});
		write("frn9", new String[] {"vike", "ryjhn", "friend"});
		write("frn10", new String[] {"vike", "wifo", "friend"});
		write("frn11", new String[] {"wifo", "alhen", "parent-child"});
		write("frn12", new String[] {"vike", "alhen", "parent-child"});
		write("frn13", new String[] {"alhen", "rosros", "friend"});
	}
	
	public static Map<String, ArrayList<String>> get() {
		return data;
	}
	
	public static void write(String key, String[] values) {
		data.put(key, prepareList(values));
	}
	
	public static void remove(String key) {
		data.remove(key);
	}
}
