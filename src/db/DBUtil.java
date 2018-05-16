package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hsqldb.Server;

/**
 * 
 * @author Raj
 *
 */
public class DBUtil 
{	
	private final static String DB_NAME = "MiniNetDB";
	private static Connection _dbConnection = null;
	
	public boolean connect() {
		if(_dbConnection != null)
		{
			System.out.println("DB already connected");
			return true;
		} else {
			// return true on successful connection
			return stablishConnection();
		}
	}
	
	/**
	 * 
	 * @return true if successfully connect to db
	 */
	private boolean stablishConnection() {
		try 
		{
			Class.forName("org.hsqldb.jdbcDriver");
			_dbConnection = DriverManager.getConnection("jdbc:hsqldb:" + DB_NAME, "user", "pass123");
//			_dbConnection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public static Connection getConnection()
	{
		return _dbConnection;
	}

}
