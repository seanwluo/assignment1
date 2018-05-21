package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import com.sun.rowset.CachedRowSetImpl;

import db.DBUtil;

/**
 * 
 * @Raj
 *
 */
public class FriendShipRepository
{
	private Connection _dbConnection;
	
	public FriendShipRepository()
	{
		_dbConnection = DBUtil.getConnection();
	}
	
	public boolean save(String username1, String username2, String type)
	{	
		String sql = "insert into friendships(user1, user2, type) values (?, ?, ?)";
		int execution;
		try {
			PreparedStatement prepStatement = _dbConnection.prepareStatement(sql);
			prepStatement.setString(1, username1);
			prepStatement.setString(2, username2);
			prepStatement.setString(3, type);
			
			execution = prepStatement.executeUpdate();
			_dbConnection.commit();
			prepStatement.close();
		} catch (SQLException e) {
			execution = 0;
		} catch(Exception e)
		{
			execution = 0;
		}
	
		return execution == 1;
	}
	
	public ResultSet friendshipByUser(String username)
	{	
		CachedRowSet rowset = null;
		String sql = "select * from friendships where user1=? or user2=?";
		
		try {
			PreparedStatement prepStatement = _dbConnection.prepareStatement(sql);
			prepStatement.setString(1, username);
			prepStatement.setString(2, username);
			
			ResultSet result = prepStatement.executeQuery();
			rowset = new CachedRowSetImpl();
			rowset.populate(result);
			
			_dbConnection.commit();
			prepStatement.close();
			result.close();
		} catch(SQLException e) {
			System.out.println("Error query result");
		}
		
		return rowset;	
	}

	public ResultSet list() {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultSet friendshipByType(String type) {
		CachedRowSet rowset = null;
		String sql = "select * from friendships where type=?";
		
		try {
			PreparedStatement prepStatement = _dbConnection.prepareStatement(sql);
			prepStatement.setString(1, type);
			
			ResultSet result = prepStatement.executeQuery();
			rowset = new CachedRowSetImpl();
			rowset.populate(result);
			
			_dbConnection.commit();
			prepStatement.close();
			result.close();
		} catch(SQLException e) {
			System.out.println("Error query result");
		}
		
		return rowset;	
	}
}
