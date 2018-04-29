package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import models.User;

public class CreateTables {
	private Connection _dbConnection = null;
	private LinkedHashMap<String, String> tableQuries = new LinkedHashMap<String, String>();
	private HSQLServer _server = null;
	
	public CreateTables() {
		this._server = new HSQLServer();
		_server.start();
		DBUtil dbUtil = new DBUtil();
		dbUtil.connect();
		this._dbConnection = dbUtil.getConnection();
	}
	
	// TODO: Need to run at program first bootup
	public void run() throws SQLException
	{	
		String createQuery = null;
		buildTablesQuery();
		
		for(String tableName: tableQuries.keySet())
		{	
			_dbConnection.prepareStatement("drop table " + tableName + " if exists;").execute();
			
			// Build table create query
			createQuery = String.format("create table %s (%s);", tableName, tableQuries.get(tableName));
			_dbConnection.prepareStatement(createQuery).execute();
		}
		_server.stop();
	}
	
	private void buildTablesQuery()
	{	
		tableQuries.put("users", "username varchar(25) not null, password varchar(25), type varchar(5), primary key (username)");

		tableQuries.put("profiles", 
				"id integer identity, "
				+ "user varchar(25) not null, "
				+ "firstname varchar(20) not null, "
				+ "lastname varchar(20) not null, "
				+ "age integer, "
				+ "gender varchar(6), "
				+ "status varchar(30), "
				+ "picUrl varchar(100), "
				+ "primary key (id, user), "
				+ "foreign key(user) references users(username)");
		
		tableQuries.put("friendships",
				"id integer identity, "
				+ "user1 varchar(25) not null, "
				+ "user2 varchar(25) not null, "
				+ "type varchar(10), "
				+ "primary key(id, user1, user2), "
				+ "foreign key(user1) references users(username), "
				+ "foreign key(user2) references users(username)");
	}
	
	public static void main(String[] args) throws SQLException
	{
		CreateTables ct = new CreateTables();
		ct.run();
	}
}
