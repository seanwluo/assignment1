package db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
/**
 * 
 * @author Raj
 * 
 * Check Tables exists if not create tables
 */
public class CreateTables {
	private static Connection _dbConnection = null;
	private static LinkedHashMap<String, String> tableQuries = new LinkedHashMap<String, String>();
	
	/* Class method to execute table create
	 * @param Connection
	 */
	public static void run(Connection dbConnection) throws Exception
	{	
		_dbConnection = dbConnection;
		String createQuery = null;
		buildTablesQuery();
		
		if(userTblExists() || profilesTblExists() || friendshpTblExists()) {
//			if any table exists do not continue
			return;
		}
		
		//to clear data
//		_dbConnection.prepareStatement("truncate schema public restart identity and commit no check").execute();

		try {
			for(String tableName: tableQuries.keySet())
			{	
				//drop db
//				_dbConnection.prepareStatement("drop table " + tableName + " if exists;").execute();
				
				// Build table create query
				createQuery = String.format("create table %s (%s);", tableName, tableQuries.get(tableName));
				_dbConnection.prepareStatement(createQuery).execute();
			}
			_dbConnection.commit();
		} catch(Exception e)
		{
//			System.out.println("Error table creation");
		}
	}

	private static boolean userTblExists() throws Exception {
		DatabaseMetaData dbm = _dbConnection.getMetaData();
	    ResultSet rsUser = dbm.getTables(null, null, "users", null);
	    if (rsUser.next()) {
	    	return true; 
	    } else {
	    	return false;
	    }
	}
	
	private static boolean profilesTblExists() throws Exception {
		DatabaseMetaData dbm = _dbConnection.getMetaData();
	    ResultSet rsUser = dbm.getTables(null, null, "profiles", null);
	    if (rsUser.next()) {
	    	return true; 
	    } else {
	    	return false;
	    }
	}
	
	private static boolean friendshpTblExists() throws Exception {
		DatabaseMetaData dbm = _dbConnection.getMetaData();
	    ResultSet rsUser = dbm.getTables(null, null, "friendships", null);
	    if (rsUser.next()) {
	    	return true; 
	    } else {
	    	return false;
	    }
	}
	
	private static void buildTablesQuery()
	{	
		tableQuries.put("users", 
				"username varchar(25) not null, "
				+ "password varchar(25), "
				+ "type varchar(8), "
				+ "primary key (username)");

		tableQuries.put("profiles", 
				"id integer identity, "
				+ "username varchar(25) not null, "
				+ "firstname varchar(20) not null, "
				+ "lastname varchar(20) not null, "
				+ "age integer, "
				+ "gender varchar(6), "
				+ "status varchar(30), "
				+ "picUrl varchar(100), "
				+ "state varchar(6), "
				+ "primary key (id), "
				+ "foreign key(username) references users(username)");
		
		tableQuries.put("friendships",
				"id integer identity, "
				+ "username1 varchar(25) not null, "
				+ "username2 varchar(25) not null, "
				+ "type varchar(10), "
				+ "primary key(id), "
				+ "foreign key(username1) references users(username), "
				+ "foreign key(username2) references users(username)");
	}
}
