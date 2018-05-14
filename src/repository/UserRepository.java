package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import com.sun.rowset.CachedRowSetImpl;

import db.DBUtil;

public class UserRepository
{	
	private Connection _dbConnection;
	
	public UserRepository() {
		DBUtil db = new DBUtil();
		_dbConnection = db.getConnection();
	}
	
	public boolean save(String username, String password, String type) {
		String sql = String.format("insert into users(username, password, type) values (%s, %s, %s);", 
				username, password, type);
		
		try {
			PreparedStatement prepStatement = _dbConnection.prepareStatement(sql);
			
			prepStatement.executeQuery();
			prepStatement.close();
			_dbConnection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	
		return true;
	}
	
	public ResultSet userList()
	{	
		CachedRowSet rowset = null;
		String sql = "select * from users;";
		
		try {
			PreparedStatement prepStatement = _dbConnection.prepareStatement(sql);
			ResultSet result = prepStatement.executeQuery();
			rowset = new CachedRowSetImpl();
			rowset.populate(result);
			
			prepStatement.close();
			_dbConnection.commit();
			result.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return rowset;
		
	}
}
