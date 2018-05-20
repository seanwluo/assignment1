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
	
	public UserRepository()
	{
		_dbConnection = DBUtil.getConnection();
	}
	
	public boolean save(String username, String password, String type)
	{
		String sql = "insert into users(username, password, type) values (?, ?, ?)";
		boolean execution;
		try {
			PreparedStatement prepStatement = _dbConnection.prepareStatement(sql);
			prepStatement.setString(1, username);
			prepStatement.setString(2, password);
			prepStatement.setString(3, type);
			
			execution = prepStatement.execute();
			_dbConnection.commit();
			prepStatement.close();
		} catch (SQLException e) {
//			e.printStackTrace();
			execution = false;
		}
	
		return execution;
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

			_dbConnection.commit();
			prepStatement.close();
			result.close();
		} catch(SQLException e) {
			System.out.println("Error in query");
		}
		
		return rowset;
		
	}

	public ResultSet findByUsername(String username)
	{
		CachedRowSet rowset = null;
		try {
			PreparedStatement prepStatement = _dbConnection.prepareStatement("select * from users where username = ?");
			prepStatement.setString(1, username);
						
			ResultSet result = prepStatement.executeQuery();
			rowset = new CachedRowSetImpl();
			rowset.populate(result);
			
			_dbConnection.commit();
			prepStatement.close();
			result.close();
		} catch (Exception e) {
			System.out.println("Error in query");
		}
		
		return rowset;		
	}
}
