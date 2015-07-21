package edu.nju.Homeworks.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface DaoHelper {
	
	public Connection getConnection();
	
	public void closeConnection(Connection c);
	
	public void closePreparedStatement(PreparedStatement ps);
	
	public void closeResultSet(ResultSet rs);

}
