package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
/**
 * 
 * @author Raj
 *
 */
public class CreateTables {
	private Connection _dbConnection = null;
	private LinkedHashMap<String, String> tableQuries = new LinkedHashMap<String, String>();
	private HSQLServer _server = null;
	
	public CreateTables() throws SQLException {
		this._server = new HSQLServer();
		_server.start();
		DBUtil dbUtil = new DBUtil();
		dbUtil.connect();
		this._dbConnection = DBUtil.getConnection();
	}
	
	// TODO: Need to run at program first bootup
	public void run() throws SQLException
	{	
		String createQuery = null;
		buildTablesQuery();
		_dbConnection.prepareStatement("truncate schema public restart identity and commit no check").execute();

		try {
			for(String tableName: tableQuries.keySet())
			{	
//				TODO: Check for drop table which is referenced by fireign j=key constraint
//				_dbConnection.prepareStatement("drop table " + tableName + " if exists;").execute();
				
				// Build table create query
//				createQuery = String.format("create table %s (%s);", tableName, tableQuries.get(tableName));
//				_dbConnection.prepareStatement(createQuery).execute();
			}
			_dbConnection.commit();
		} finally {
			_dbConnection.close();
			_server.stop();
		}
	}
	
	private void buildTablesQuery()
	{	
		tableQuries.put("users", 
				"username varchar(25) not null, "
				+ "password varchar(25), "
				+ "type varchar(8), "
				+ "primary key (username)");

		tableQuries.put("profiles", 
				"id integer identity, "
				+ "user varchar(25) not null, "
				+ "firstname varchar(20) not null, "
				+ "lastname varchar(20) not null, "
				+ "age integer, "
				+ "gender varchar(6), "
				+ "status varchar(30), "
				+ "picUrl varchar(100), "
				+ "state varchar(6), "
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
