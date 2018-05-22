package util;

import java.util.ArrayList;
import java.util.List;

import repository.FriendShipRepository;
import repository.ProfileRepository;
import repository.UserRepository;

/**
 * 
 * @author Raj
 *
 */
public class TransferFileToDB {
	private static final String peopleFile = "people.txt";
	private static final String friendshipFile = "relations.txt";
	
	public static void run()
	{
		 checkAndLoadPerson();
		 checkAndLoadFriendship();
	}

//	TOD): Handle already exists user
	private static void checkAndLoadPerson()
	{
		FileHandler fService = new FileHandler(peopleFile);
		
		if(!fService.fileExists()){
			System.out.printf("%s file not exists", peopleFile);
			return;
		} else {
			loadPersonData(fService.read());
		}
	}
	
	private static void checkAndLoadFriendship()
	{
		FileHandler fService = new FileHandler(friendshipFile);
		
		if(!fService.fileExists()){
			System.out.printf("%s file not exists", friendshipFile);
			return;
		} else {
			loadFriendshipData(fService.read());
		}
	}
	
	private static void loadPersonData(List<String> list)
	{		
		List<String> peopleData = new ArrayList<String>();
		peopleData = processData(list);
		UserRepository userRepo = new UserRepository();
		ProfileRepository profileRepo = new ProfileRepository();
		
		for(String data: peopleData) {
			String[] dataArray = data.split(",");
			String name = dataArray[0].trim();
			
			String[] nameArray = getSplitedName(name);
			String firstname = nameArray[0].trim();
			String lastname = nameArray[1].trim();
			
			String picUrl = dataArray[1].trim();
			String status = dataArray[2].trim();
			String gender = dataArray[3].trim();
			int age= ageConvertToInt(dataArray[4].trim());
			
			String type = getUserType(age);
			String state = dataArray[5].trim();
			if( userRepo.save(firstname, "password", type)) {
				if(profileRepo.save(firstname, firstname, lastname, age, gender, status, picUrl, state)){
					//
				} else {
					System.out.println("Profile not created.");
				}
			} else {
				System.out.println("User Not Created.");
			}
		}
		
	}
	
	private static void loadFriendshipData(List<String> fileData)
	{
		
		List<String> friendshipData = new ArrayList<String>();
		friendshipData = fileData;
		FriendShipRepository frndRepo = new FriendShipRepository();;
		
		for(String data : friendshipData)
		{
			String[] dataArray = data.split(",");
			String user1 = dataArray[0].trim();
			String user2 = dataArray[1].trim();
			String type = dataArray[2].trim();
			
			frndRepo.save(user1, user2, type);
		}
	}
	
	private static List<String> processData(List<String> dataList)
	{	
		List<String> newDataList = new ArrayList<String>();
		
		for(String data: dataList) {
//			remove extra whitespace from beginning and end of string
			data = data.trim();
			
//			check for blank line and skip to next line
			if(data.equals(null) || data.equals(""))
			{
				continue;
			}
			
			newDataList.add(data);
		}
		
		return newDataList;		
	}
	
	private static String getUserType(int age)
	{	
		String type;
		
		if(age > 16) {
			type = "Adult";
		} else {
			type = "Children";
		}
		
		return type;
	}
	
	private static int ageConvertToInt(String ageString)
	{
		int age = 0;
		try
		{
			age = Integer.parseInt(ageString);
		} catch(Exception e) {
//			e.printStackTrace();
			System.out.println("Age data is not in integer format.");
		}
		
		return age;
	}
	
//	split name on whitesapce to get firstname and last name
	private static String[] getSplitedName(String name)
	{
		String[] nameArray = name.split(" ");
		String firstname="", lastname="";
		for(int i=0; i < nameArray.length; i++)
		{	
			if(i == 0)
			{
				firstname = nameArray[i];
			} else {
				lastname += nameArray[i] + " ";
			}
			
		}
		
		return new String[]{firstname, lastname};
	}
}
