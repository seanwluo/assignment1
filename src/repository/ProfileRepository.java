package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import com.sun.rowset.CachedRowSetImpl;

import db.DBUtil;

public class ProfileRepository {
	private Connection _dbConnection;
	
	public ProfileRepository()
	{
		DBUtil db = new DBUtil();
		_dbConnection = db.getConnection();
	}
	
	public boolean save(String username, String firstname, String lastname, 
			int age, String gender, String status, String url)
	{	
		String sql = "insert into friendships(user, firstname, lastname, age, gender, status, url) "
				+ "values (?, ?, ?, ?, ?, ?, ?)";
	
		try {
			PreparedStatement prepStatement = _dbConnection.prepareStatement(sql);
			prepStatement.setString(1, username);
			prepStatement.setString(2, firstname);
			prepStatement.setString(3, lastname);
			prepStatement.setInt(4, age);
			prepStatement.setString(5, gender);
			prepStatement.setString(6, status);
			prepStatement.setString(7, url);			
			
			prepStatement.executeQuery();
			_dbConnection.commit();
			prepStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	
		return true;
	}
	
	public ResultSet findByUser(String username)
	{	
		CachedRowSet rowset = null;
		String sql = String.format("select * from friendships where user=%s;", username);
		
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
