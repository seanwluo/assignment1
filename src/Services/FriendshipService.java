package Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Store.FriendshipData;
import Store.UserData;
import models.Adult;
import models.Dependent;
import models.Friendship;
import models.User;

public class FriendshipService {
	
	public static List<Friendship> allFriendship() {
		List<Friendship> friendships = new ArrayList<Friendship>();
		Map<String, ArrayList<String>> data = FriendshipData.get();
		
	
		for (Entry<String, ArrayList<String>> entry : data.entrySet()) {
			ArrayList<String> value = entry.getValue();
			String user1 = value.get(0);
			String user2 = value.get(1);
			String type = value.get(2);
			
			Friendship friendship = new Friendship(entry.getKey().toString(), user1, user2, type);
			friendships.add(friendship);
		}
		
		return friendships;
	}
	
	public static List<Friendship> findByUsername(String username) {
		List<Friendship> friendships = new ArrayList<Friendship>();
		
		
		return friendships;
	}
}