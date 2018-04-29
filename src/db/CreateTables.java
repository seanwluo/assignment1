package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CreateTables {
	private Connection _dbConnection = null;
	private Map<String, String> tableQuries = new HashMap<String, String>();
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
			
			createQuery = String.format("create table %s (%s);", tableName, tableQuries.get(tableName));
			_dbConnection.prepareStatement(createQuery).execute();
		}
		_server.stop();
	}
	
	private void buildTablesQuery()
	{	
		tableQuries.put("users", "username varchar(25) not null, type varchar(5), primary key (username)");
	}
	
	public static void main(String[] args) throws SQLException
	{
		CreateTables ct = new CreateTables();
		ct.run();
	}
}
