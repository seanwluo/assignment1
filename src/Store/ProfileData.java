package Store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
		//Need to check order while writing value
		write("ryjhn", new String[]{"Ryan", "Johnson", "Male", "27", ""});
		write("ryjhn", new String[]{"Ryan", "Johnson", "Male", "27", ""});
		write("chjhn", new String[]{"Chester", "Jones", "Male", "3", ""});
		write("lihi", new String[]{"Lily", "Higgins", "Female", "23", ""});
		write("arist", new String[]{"Arianna", "Stevens", "Female", "27", ""});
		write("rosros", new String[]{"Rosie", "Ross", "Female", "7", ""});
		write("samwel", new String[]{"Samantha", "Wells", "Female", "27", ""});
		write("paust", new String[]{"Paul", "Stevens", "Male", "18", ""});
		write("alhen", new String[]{"Alfred", "Henderson", "Male", "8", ""});
		write("wifo", new String[]{"Wilson", "Foster", "Male", "28", ""});
		write("vike", new String[]{"Victoria", "Kelley", "Female", "23", ""});
		write("alist", new String[]{ "Alina",  "Stevens",  "Female",  "30",  ""});
	}
	
	public static Map<String, ArrayList<String>> get() {
		return data;
	}
	
	public static void write(String key, String[] values) {
		data.put(key, prepareList(values));
	}
}
