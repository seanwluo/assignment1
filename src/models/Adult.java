package models;

public class Adult extends User {
	private final static String TYPE = "Adult";	
	
	public Adult(String username) {
		super(username, TYPE);
	}

	@Override
	public boolean create() {
		// username is already exists into the system
		if(!isUniqUsername()) {
			return false;
		}
		
		return true;
	}

	@Override
	public boolean connect() {
		// TODO Auto-generated method stub
		return false;
	}
}
