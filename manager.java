package rmit.edu.au;

	import java.util.Collection;
	import java.util.HashMap;
	import java.util.Map;
	import java.util.Scanner;
	
public class manager {
	Map<String,adult>map=new HashMap<String,adult>();
	Scanner input=new Scanner(System.in);
	boolean isgo=true;
	
	String name=null;
	String gender=null;
	String status=null;
	int age=0;
	
	public manager() {
		adult one=new adult("Ellis","Female",22,"working in the farm");
		adult two=new adult("Fiona","Female",22,"working in the farm");
		adult three=new adult("Sean","Male",26,"studying in the RMIT");
		adult four=new adult("Zhelly","Female",25,"working in the school");
		adult five=new adult("yuni","Female",24,"looking for jobs");
		adult six=new adult("luoshiwei","male",26,"working for agent");
		
		map.put("Ellis", one);
		map.put("Fiona", two);
		map.put("Sean",three);
		map.put("Zhelly", four);
		map.put("yuni",five);
		map.put("luoshwei",six);	
	}
	//*check all the people's information
	public void seeall() {
	Collection<adult> values=map.values();
	System.out.println("the list of everyone's name");
	for(adult adult:values) {
		System.out.println("\t"+adult.getadultname());
		}
	System.out.println("\t---end---\n");
	}
	//select people' by search their name.
	public boolean seebyname(String name) {
		for(String key:map.keySet()) {
			if(key.equals(name)) {
				return true;
			}
		}
		return false;
	}
	//add one people
	public boolean addperson(String name,adult adult) {
		Collection<adult>values=map.values();
		for(adult adu:values) {
			if(adu.getadultname().equals(name)) {
				System.out.println("the person exit");
				return false;
			}
		}
		map.put(name,adult);
		return true;
	}
	public boolean remove(String name) {
		Collection<adult> values=map.values();
		for(adult adult:values) {
			if(adult.getadultname().equals(name)) {
				map.remove(name);
				return true;
			}
		}
		return false;
	}
	
	public void addprint() {
		System.out.println("please add person'name");
		name=input.nextLine();
		System.out.println("please add person's gender");
		gender=input.nextLine();
		System.out.println("please add person's age");
		age=input.nextInt();
		System.out.println("please add person's status");
		status=input.nextLine();	
	}
	
	public boolean editexitperson(String name,adult adult){
		Collection<adult>values=map.values();
		for(adult adu:values){
			if (adu.getadultname().equals(name))
			{
				return true;
			}	
		}
		System.out.println("the person doesnt in list");
		return false;
	}

	public void editprofile(adult adu){
		System.out.println("please edit person's status");
		adu.setstatus(status);
		{
			status=input.nextLine();
		};
		System.out.println("edit successful");
	}
	
	
	
	
	public void show() {
		int choose=0;
		while(isgo) {
			do {
				System.out.println(" \tMininet Menu");
				System.out.println("=========================");
				System.out.println("1.\tList everyone");
				System.out.println("2.\tSelect a perosn");
				System.out.println("3.\tUpdate the profile information of the selected person");
				System.out.println("4.\tdelect the selected person");
				System.out.println("5.\tedit exited person");
				System.out.println("6.\texit");
				System.out.println("============================");
				System.out.println("please unput your choose");
				if(input.hasNext()) {
				choose=input.nextInt();	
			}
			input.nextLine();
		}while(choose<1||choose>5);
			
		switch(choose) {
		case 1:
			seeall();
			break;
		case 2:
			System.out.println("please input the name");
			name=input.nextLine();
			if(seebyname(name)) {
				System.out.println("here is the information you r looking for");
				System.out.println("name"+"\t\tgende"+"\tage"+"\tstatus");
				//2
				System.out.println(map.get(name));
				}else {
					System.out.println("searching fail,please makr sure your input is appriporte");
					
				}
				break;
		case 3:
			addprint();
			adult adu=new adult(name,gender,age,status);
			if(addperson(name,adu)) {
				System.out.println("update successfully");
			}else {
					System.out.println("you may add the same person ");
			}
			break;
		case 4:
			System.out.println("please input the people's name that u wanna delete");
			name=input.nextLine();
			//adult adu=new adult(name);
			if(remove(name)) {
				System.out.println("people's information deleted successfully");
			}else {
				System.out.println("delete unseccessful");
			}
			break;
		case 5:
			adult adu1=new adult(name,gender,age,status);
			name=input.nextLine();
			if(editexitperson(name,adu1)){
				
					editprofile(adu1);
				}else{
						System.out.println("please enter an new person");
						
					}	
			break;
			
		case 6:
			isgo=false;
			break;
			}
		
			
			
		}
		
	}
	
	
		
	
}