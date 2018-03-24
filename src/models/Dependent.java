package models;

public class Dependent extends User{
	private final static String TYPE = "dependent";	
	
	public Dependent(String username) {
		super(username, TYPE);
	}

	@Override
	public boolean create() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean connect() {
		// TODO Auto-generated method stub
		return false;
	}
}
